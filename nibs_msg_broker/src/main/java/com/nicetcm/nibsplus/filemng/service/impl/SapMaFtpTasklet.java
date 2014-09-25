/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nicetcm.nibsplus.filemng.service.impl;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.filemng.service.FileTransferService;

/**
 * Sap Master Ftp Task (STEP1)
 * <pre>
 * SAPMA
 * AS-IS의 FILEMng.pc에서 FTP로 파일가져오는 Task
 *
 * AS-IS 실행 흐름
 * 실행 명령어 : FILEMng SAPMA 20140414
 * argv[1]: SAPMA    -> szFILEType
 * argv[2]: 20140414 -> g_deal_date
 * argv[3]: 8228     -> g_jijum_cd
 *
 * 파일명 ex)
 * NICE_20140913_8228_MASTER.dat
 * NICE_20140915_8206_MASTER.dat
 *
 * -------------------------------------------------------------- STEP 1 --------------------------------------------------------------
 * if(argv[3] 가 들어온다면) {
 *     FTP로 파일Get (가져오려는 파일명의 형식은 NICE_%s_MASTER.dat)
 * } else {
 *     FTP로 파일Get (가져오려는 파일명의 형식은 NICE_%s_%s_MASTER.dat)
 *     EXEC SQL    delete FROM T_FN_SAP_MASTER WHERE deal_date = :g_deal_date AND   JIJUM_CD = :g_jijum_cd;
 *     commit;
 * }
 *
 * -------------------------------------------------------------- STEP 2 --------------------------------------------------------------
 * for(파일 Line단위로 Loop) {
 *     해당 지점 데이터가 아니라면 다음라인으로 넘어감 (skip)
 *
 *     마감완료 처리 된 데이터는, 다시 받지 않습니다.  by  최락경20120322
 *     마감완료 데이터 여부 체크
 *     EXEC SQL SELECT OFFICE_CONFIRM
 *         INTO    :confirmChk
 *         FROM    T_FN_SAP_MASTER
 *         WHERE DEAL_DATE = :suBody.deal_date
 *         AND     JIJUM_CD    = :suBody.jijum_cd
 *         AND MEMBER_ID = :suBody.member_id;
 *
 *    if( strcmp(confirmChk, "1") != 0 ) {
 *        if( memcmp( suBody.data_type, "DT", 2 ) == 0 ) {
 *
 *            DB Table에 데이터 로딩
 *            EXEC SQL INSERT INTO T_FN_SAP_MASTER (
 *                            DEAL_DATE,
 *                            JIJUM_CD,
 *                            MEMBER_ID,
 *                            MEMBER_NM,
 *                            MEMBER_TYPE,
 *                            CHECK_AMT,
 *                            CASH_AMT,
 *                            SELF_CUPON,
 *                            ETC_CUPON,
 *                            UPDATE_DATE
 *                     ) VALUES  (
 *                            :suBody.deal_date,
 *                            :suBody.jijum_cd,
 *                            :suBody.member_id,
 *                            :suBody.member_nm,
 *                            :suBody.member_type,
 *                            :suBody.check_amt,
 *                            :suBody.cash_amt,
 *                            :suBody.self_cupon,
 *                            :suBody.etc_cupon,
 *                            SYSDATE
 *                     );
 *        }
 *    }
 * } //end for
 *
 * if(마지막 인자로 g_jijum_cd 가 들어오지 않았다면) {
 *     파일명을 .dat 에서 .bak로 rename
 * }
 * -------------------------------------------------------------- ------ --------------------------------------------------------------
 *
 * ======================================================================
 * 이랜드 FTP 수신 FILE FORMAT SAP MASTER
 * ======================================================================
 * struct FTP_SAP_MASTER {
 *     [00] char    work_nm         [5 +1];
 *     [01] char    data_type       [2 +1];
 *     [02] char    data_seq        [7 +1];
 *     [03] char    deal_date       [8 +1];
 *     [04] char    jijum_cd        [4 +1];
 *     [05] char    member_id       [8 +1];
 *     [06] char    member_nm       [40+1];
 *     [07] char    member_type     [1 +1];
 *     [08] char    check_amt       [11+1];
 *     [09] char    cash_amt        [11+1];
 *     [10] char    self_cupon      [11+1];
 *     [11] char    etc_cupon       [11+1];
 *     [12] char    filler          [31+1];    //여분
 * };
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("sapMaFtpTasklet")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/filemng/spring/context-filemng.xml"})
public class SapMaFtpTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(SapMaFtpTasklet.class);

    @Resource(name="ftpTransfer")
    private FileTransferService fileTransferService;

    @Value("#{config['eland.host']}")
    private String host;

    @Value("#{config['eland.availableServerPort']}")
    private int availableServerPort;

    @Value("#{config['eland.userid']}")
    private String userId;

    @Value("#{config['eland.password']}")
    private String password;

    @Value("#{config['sapma.remote.path']}")
    private String remotePath;

    @Value("#{config['sapma.local.path']}")
    private String localPath;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();

        //NICE_20140913_8228_MASTER.dat
        final String prefix = "NICE_";
        final String suffix = "_MASTER.dat";
        final String delimiter = "_";

        String fileName = null;
        if(jobParameters.getString("branchCd") != null) {
            fileName = prefix + jobParameters.getString("yyyymmdd") + delimiter + jobParameters.getString("branchCd") + suffix;
        } else {
            fileName = prefix + jobParameters.getString("yyyymmdd") + suffix;
        }

        logger.debug("■■■ Receive File Name: {}", fileName);

        TransferVO transferVO = new TransferVO();
        transferVO.setHost(host);
        transferVO.setAvailableServerPort(availableServerPort);
        transferVO.setUserId(userId);
        transferVO.setPassword(password);
        transferVO.setRemotePath(remotePath);
        transferVO.setLocalPath(localPath);
        transferVO.setFileName(fileName);

        fileTransferService.getFile(transferVO);


        StepContext stepContext = chunkContext.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        jobContext.put("fileName", "filemng/data/report.csv");

        return RepeatStatus.FINISHED;
    }

    //@Test
    public void testTasklet() {
    }

    public static void main(String[] args) {


    }
}

package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;

import com.nicetcm.nibsplus.filemng.common.FileMngException;
import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

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
 * if(argv[3] 가 NULL 일경우) {
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
 * -------------------------------------------------------------- STEP 3 --------------------------------------------------------------
 * if(마지막 인자로 g_jijum_cd 가 들어오지 않았다면) {
 *     파일명을 .dat 에서 .bak로 rename
 * }
 * -------------------------------------------------------------- ------ --------------------------------------------------------------
 *
 * ======================================================================
 * 이랜드 FTP 수신 FILE FORMAT SAP MASTER
 * ======================================================================
 *           10        20        30        40        50        60        70        80        90        100       110
 * 012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
 * 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
 * FTP01DT00000012014091382280005101 후아유1                                 P00000000000000017340000000000000000000000000
 *
 *
 * struct FTP_SAP_MASTER {
 *     [00] char    work_nm         [5 +1];  FTP01          1-5
 *     [01] char    data_type       [2 +1];  DT             6-7
 *     [02] char    data_seq        [7 +1];  0000001        8-14
 *     [03] char    deal_date       [8 +1];  20140913       15-22
 *     [04] char    jijum_cd        [4 +1];  8228           23-26
 *     [05] char    member_id       [8 +1];  0005101        27-34
 *     [06] char    member_nm       [40+1];  후아유1        35-74
 *     [07] char    member_type     [1 +1];                 75-75
 *     [08] char    check_amt       [11+1];                 76-86
 *     [09] char    cash_amt        [11+1];                 87-97
 *     [10] char    self_cupon      [11+1];                 98-108
 *     [11] char    etc_cupon       [11+1];                 109-119
 *     [12] char    filler          [31+1];    //여분       120-150
 * };
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class ShinhanFtpTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(ShinhanFtpTasklet.class);

    @Value("#{config['host.host']}")
    private String host;

    @Value("#{config['host.availableServerPort']}")
    private int availableServerPort;

    @Value("#{config['host.userid']}")
    private String userId;

    @Value("#{config['host.password']}")
    private String password;

    @Value("#{config['shinhan.remote.path']}")
    private String remotePath;

    @Value("#{config['shinhan.local.path']}")
    private String localPath;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        // ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        //Q20140913B11.dat
        String fileName = jobParameters.getString("fileName");

        logger.debug("■■■ Receive File Name   : {}", fileName);
        logger.debug("■■■ host                : {}", host);
        logger.debug("■■■ availableServerPort : {}", availableServerPort);
        logger.debug("■■■ userId              : {}", userId);
        logger.debug("■■■ password            : {}", password);
        logger.debug("■■■ remotePath          : {}", remotePath);
        logger.debug("■■■ localPath           : {}", localPath);
        logger.debug("■■■ fileName            : {}", fileName);

        TransferVO transferVO = new TransferVO();
        transferVO.setHost(host);
        transferVO.setAvailableServerPort(availableServerPort);
        transferVO.setUserId(userId);
        transferVO.setPassword(password);
        transferVO.setRemotePath(remotePath);
        transferVO.setLocalPath(localPath);
        transferVO.setFileName(fileName);

        try {
        	getFile(transferVO);
	        //jobParameters.getParameters().put("shinhanFileName", new JobParameter(getFile(transferVO).getAbsolutePath()));        	
        } catch(Exception e) {
        	logger.error(e.getMessage());
        }
        
        return RepeatStatus.FINISHED;
    }
    
    private File getFile(TransferVO transferVO) throws FileMngException {
    	
    	if (findBackupFile(transferVO.getLocalPath(), transferVO.getFileName()).isFile()) {
            throw new FileMngException(ExceptionType.VM_STOP, "이미 처리된 파일입니다.");
    	}

		return SFtpTransfer.getFile(transferVO);
    }
    
    private File findBackupFile(String path, String name) {
    	return new File(path, name+".bak");
    }
}

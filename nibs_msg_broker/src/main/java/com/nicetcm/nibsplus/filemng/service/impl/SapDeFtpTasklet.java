package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.filemng.common.FileMngException;
import com.nicetcm.nibsplus.filemng.dao.SapdeMapper;
import com.nicetcm.nibsplus.filemng.model.FileMngParameterVO;
import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.filemng.service.FileTransferService;
import com.nicetcm.nibsplus.orgsend.constant.ExceptionType;

/**
 *
 * <pre>
 * ======================================================================
 *  이랜드 FTP 수신 FILE FORMAT   SAP DETAIL
 * ======================================================================
 * struct FTP_SAP_DETAIL {
 *     char    work_nm         [5 +1]; 1-5
 *     char    data_type       [2 +1]; 6-7
 *     char    data_seq        [7 +1]; 8-14
 *     char    deal_date       [8 +1]; 15-22
 *     char    jijum_cd        [4 +1]; 23-26
 *     char    member_id       [8 +1]; 27-34
 *     char    cupon_cd        [3 +1]; 35-37
 *     char    cupon_type      [3 +1]; 38-40
 *     char    cupon_nm        [20+1]; 41-60
 *     char    cupon_amt       [11+1]; 61-71
 *     char    filler          [79+1]; 72-150
 *};
 *           10        20        30        40        50        60        70        80        90        100       110
 * 012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789
 * 123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890
 * FTP02DT00000012014091582060005108 001001신이랜드            00000030000
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/filemng/spring/context-filemng.xml"})
public class SapDeFtpTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(SapDeFtpTasklet.class);

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

    @Value("#{config['sapde.remote.path']}")
    private String remotePath;

    @Value("#{config['sapde.local.path']}")
    private String localPath;

    @Autowired
    private SapdeMapper sapdeMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        // ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        final String prefix = "NICE_";
        final String suffix = "_DETAIL.dat";
        final String delimiter = "_";

        final String yyyymmdd = jobParameters.getString("yyyymmdd");
        final String branchCd = jobParameters.getString("branchCd");

        String fileName = null;
        if(branchCd != null) {
            fileName = prefix + yyyymmdd + delimiter + branchCd + suffix;
            //NICE_20140913_8228_DETAIL.dat
        } else {
            fileName = prefix + yyyymmdd + suffix;
            //NICE_20140913_DETAIL.dat
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

        try {
	        File file = getFile(transferVO);
	        
	        logger.info("file.getAbsolutePath(): {}", file.getAbsolutePath());
	        
	        if(branchCd != null) {
	            FileMngParameterVO fileMngParameterVO = new FileMngParameterVO();
	            fileMngParameterVO.setDealDate(yyyymmdd);
	            fileMngParameterVO.setBranchCd(branchCd);
	
	            int affectRows = sapdeMapper.deleteTFnSapDetail(fileMngParameterVO);
	
	            logger.info("DELETE T_FN_SAP_DETAIL Affect Rows: {}", affectRows);
	        }
	
	        jobParameters.getParameters().put("eland.file.name", new JobParameter(file.getAbsolutePath()));
        } catch(Exception e) {
        	logger.error(e.getMessage());
        }

        return RepeatStatus.FINISHED;
    }
    
    private File getFile(TransferVO transferVO) throws FileMngException {
    	if (findBackupFile(transferVO.getLocalPath(), transferVO.getFileName()).isFile()) {
            throw new FileMngException(ExceptionType.VM_STOP, "이미 처리된 파일입니다.");
    	}

		return fileTransferService.getFile(transferVO);
    }
    
    private File findBackupFile(String path, String name) {
    	return new File(path, name.substring(0, name.length() - 4) + ".bak");
    }
}

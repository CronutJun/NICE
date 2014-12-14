package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;

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

import com.nicetcm.nibsplus.filemng.dao.ElandMapper;
import com.nicetcm.nibsplus.filemng.model.FileMngParameterVO;
import com.nicetcm.nibsplus.filemng.model.TransferVO;

/**
 *
 * <pre>
 *
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class CouponFtpTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(CouponFtpTasklet.class);

    @Value("#{config['host.host']}")
    private String host;

    @Value("#{config['host.availableServerPort']}")
    private int availableServerPort;

    @Value("#{config['host.userid']}")
    private String userId;

    @Value("#{config['host.password']}")
    private String password;

    @Value("#{config['coupon.remote.path']}")
    private String remotePath;

    @Value("#{config['coupon.local.path']}")
    private String localPath;

    @Autowired
    private ElandMapper elandMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        // ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        final String fileName = jobParameters.getString("fileName");
        final String yyyymmdd = jobParameters.getString("yyyymmdd");
        String branchCd = fileName.substring(fileName.lastIndexOf("/") + 6);
        branchCd = branchCd.startsWith(yyyymmdd) ? null : branchCd.substring(0, 4);

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
	        File file = SFtpTransfer.getFile(transferVO); // getFile(transferVO);
	        
	        logger.info("file.getAbsolutePath(): {}", file.getAbsolutePath());
	        
	        if(branchCd != null) {
	            FileMngParameterVO fileMngParameterVO = new FileMngParameterVO();
	            fileMngParameterVO.setDealDate(yyyymmdd);
	            fileMngParameterVO.setBranchCd(branchCd);
	
	            int affectRows = elandMapper.deleteTFnElandCoupon(fileMngParameterVO);
	
	            logger.info("DELETE T_FN_SAP_DETAIL Affect Rows: {}", affectRows);
	        }

	        jobParameters.getParameters().put("eland.file.name", new JobParameter(file.getAbsolutePath()));
	    } catch(Exception e) {
	    	logger.error(e.getMessage());
	    }
	
	    return RepeatStatus.FINISHED;
    }
    
    /*private File getFile(TransferVO transferVO) throws FileMngException {
    	if (findBackupFile(transferVO.getLocalPath(), transferVO.getFileName()).isFile()) {
            throw new FileMngException(ExceptionType.VM_STOP, "이미 처리된 파일입니다.");
    	}

		return SFtpTransfer.getFile(transferVO);
    }
    
    private File findBackupFile(String path, String name) {
    	return new File(path, name.substring(0, name.length() - 4) + ".bak");
    }*/
}

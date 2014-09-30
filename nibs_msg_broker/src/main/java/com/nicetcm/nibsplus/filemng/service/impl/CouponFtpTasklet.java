package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.nicetcm.nibsplus.filemng.dao.CouponMapper;
import com.nicetcm.nibsplus.filemng.model.FileMngParameterVO;
import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.filemng.service.FileTransferService;

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

    @Value("#{config['coupon.remote.path']}")
    private String remotePath;

    @Value("#{config['coupon.local.path']}")
    private String localPath;

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();


        final String prefix = "NICE_";
        final String suffix = "_COUPON.dat";
        final String delimiter = "_";

        final String yyyymmdd = jobParameters.getString("yyyymmdd");
        final String branchCd = jobParameters.getString("branchCd");

        String fileName = null;
        if(branchCd != null) {
            fileName = prefix + branchCd + delimiter + yyyymmdd + suffix;
            //NICE_8228_20140913_COUPON.dat
        } else {
            fileName = prefix + yyyymmdd + suffix;
            //NICE_20140917_COUPON.dat
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

        File file = fileTransferService.getFile(transferVO);
        logger.info("file.getAbsolutePath(): {}", file.getAbsolutePath());
        if(branchCd != null) {

            FileMngParameterVO fileMngParameterVO = new FileMngParameterVO();
            fileMngParameterVO.setDealDate(yyyymmdd);
            fileMngParameterVO.setBranchCd(branchCd);

            int affectRows = couponMapper.deleteTFnElandCoupon(fileMngParameterVO);

            logger.info("DELETE T_FN_SAP_DETAIL Affect Rows: {}", affectRows);
        }

        jobContext.put("file.getAbsolutePath", file.getAbsolutePath());

        return RepeatStatus.FINISHED;
    }

    //@Test
    public void testTasklet() {
    }

    public static void main(String[] args) {


    }
}

package com.nicetcm.nibsplus.filemng.service.impl;

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

import com.nicetcm.nibsplus.filemng.dao.DealNoMapper;
import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.filemng.service.FileTransferService;
import com.nicetcm.nibsplus.util.NibsBatchUtil;

public class DealNoFtpTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(DealNoFtpTasklet.class);

    @Resource(name="ftpTransfer")
    private FileTransferService fileTransferService;

    @Value("#{config['dealno.host']}")
    private String host;

    @Value("#{config['dealno.availableServerPort']}")
    private int availableServerPort;

    @Value("#{config['dealno.userid']}")
    private String userId;

    @Value("#{config['dealno.password']}")
    private String password;

    @Value("#{config['dealno.remote.path']}")
    private String remotePath;

    @Value("#{config['dealno.local.path']}")
    private String localPath;

    @Autowired
    private DealNoMapper dealNoMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        final String yyyymmdd = jobParameters.getString("yyyymmdd");
        final String branchCd = jobParameters.getString("branchCd");

        String fileName = jobContext.getString("file.send.filename");

        TransferVO transferVO = new TransferVO();
        transferVO.setHost(host);
        transferVO.setAvailableServerPort(availableServerPort);
        transferVO.setUserId(userId);
        transferVO.setPassword(password);
        transferVO.setRemotePath(remotePath);
        transferVO.setLocalPath(localPath);
        transferVO.setFileName(fileName);

        String command = null;
        try
        {
            fileTransferService.putFile(transferVO);
            command = "[나이스거래누락] 누락 고유번호 HOST 전송처리 완료[" + NibsBatchUtil.SysDate() + "-" + NibsBatchUtil.SysTime() + "]";
        } catch (Exception e)
        {
            e.printStackTrace();
            command = "[나이스거래누락] 거래고유번호 DB UPLOAD 혹은 FTP전송 실패";
        } finally {
            dealNoMapper.spIfSendSMSTranCntMismatch(command);
        }

        return RepeatStatus.FINISHED;
    }

    //@Test
    public void testTasklet() {
    }

    public static void main(String[] args) {


    }
}

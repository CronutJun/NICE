package com.nicetcm.nibsplus.filemng.service.impl;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.nicetcm.nibsplus.filemng.dao.DealNoMapper;
import com.nicetcm.nibsplus.filemng.model.TransferVO;
import com.nicetcm.nibsplus.util.NibsBatchUtil;

public class DealNoPutFtpTasklet implements Tasklet {

    // private static final Logger logger = LoggerFactory.getLogger(DealNoFtpTasklet.class);

    @Value("#{config['host.host']}")
    private String host;

    @Value("#{config['host.availableServerPort']}")
    private int availableServerPort;

    @Value("#{config['host.userid']}")
    private String userId;

    @Value("#{config['host.password']}")
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
        // JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        // final String yyyymmdd = jobParameters.getString("yyyymmdd");
        // final String branchCd = jobParameters.getString("branchCd");

        String fileName = jobContext.getString("file.send.filename", null);

        TransferVO transferVO = new TransferVO();
        transferVO.setHost(host);
        transferVO.setAvailableServerPort(availableServerPort);
        transferVO.setUserId(userId);
        transferVO.setPassword(password);
        transferVO.setRemotePath(remotePath);
        transferVO.setLocalPath(localPath);
        transferVO.setFileName(fileName);

        String command = null;
        try {
        	if (fileName == null) {
        		command = "[나이스거래누락] 누락 고유번호 HOST 전송할 데이터가 없습니다.[" + NibsBatchUtil.SysDate() + "-" + NibsBatchUtil.SysTime() + "]";
        	} else {
        		SFtpTransfer.putFile(transferVO);
        		command = "[나이스거래누락] 누락 고유번호 HOST 전송처리 완료[" + NibsBatchUtil.SysDate() + "-" + NibsBatchUtil.SysTime() + "]";
        	}
        } catch (Exception e)
        {
            e.printStackTrace();
            command = "[나이스거래누락] 거래고유번호 DB UPLOAD 혹은 FTP전송 실패";
        } finally {
            dealNoMapper.spIfSendSMSTranCntMismatch(command);
        }

        return RepeatStatus.FINISHED;
    }
}

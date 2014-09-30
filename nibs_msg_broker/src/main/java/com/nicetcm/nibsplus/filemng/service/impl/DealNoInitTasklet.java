package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;

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
import com.nicetcm.nibsplus.filemng.model.FileMngParameterVO;

public class DealNoInitTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(DealNoInitTasklet.class);

    @Value("#{config['dealno.local.path']}")
    private String readPath;

    @Autowired
    private DealNoMapper dealNoMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        final String yyyymmdd = jobParameters.getString("yyyymmdd");

        FileMngParameterVO fileMngParameterVO = new FileMngParameterVO();
        fileMngParameterVO.setDealDate(yyyymmdd);

        dealNoMapper.deleteTFnHostDealNo(fileMngParameterVO);

        File file = new File(readPath + "/" + yyyymmdd + ".dat");

        //로딩할 파일명 셋팅
        jobContext.put("file.getAbsolutePath", file.getAbsolutePath());

        return RepeatStatus.FINISHED;
    }

}

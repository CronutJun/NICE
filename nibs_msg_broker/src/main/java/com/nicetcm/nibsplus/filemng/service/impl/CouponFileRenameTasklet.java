package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;

public class CouponFileRenameTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(CouponFileRenameTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        final String branchCd = jobParameters.getString("branchCd");

        if(branchCd == null) {
            String oldAbsolutePath = jobContext.get("file.getAbsolutePath").toString();
            String newAbsolutePath = oldAbsolutePath.substring(0, oldAbsolutePath.length() - 4) + ".bak";

            FileUtils.moveFile(new File(oldAbsolutePath), new File(newAbsolutePath));
        }

        return RepeatStatus.FINISHED;
    }

}

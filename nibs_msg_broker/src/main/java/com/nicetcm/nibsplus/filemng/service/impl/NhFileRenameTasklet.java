package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class NhFileRenameTasklet implements Tasklet {

    // private static final Logger logger = LoggerFactory.getLogger(NhFileRenameTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        File bakFile = null;
        // ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        //String oldAbsolutePath = jobParameters.getString("dealno.file.name").toString();
        String oldAbsolutePath = jobParameters.getString("nh.file.name").toString();
        String newAbsolutePath = (bakFile = new File(oldAbsolutePath.substring(0, oldAbsolutePath.length() - 4) + ".bak")).getAbsolutePath();

        if (bakFile.isFile()) bakFile.delete();
        
        FileUtils.moveFile(new File(oldAbsolutePath), new File(newAbsolutePath));

        return RepeatStatus.FINISHED;
    }

}

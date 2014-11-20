package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ElandFileRenameTasklet implements Tasklet {

    // private static final Logger logger = LoggerFactory.getLogger(FileRenameTasklet.class);

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();

        String branchCd = jobParameters.getString("branchCd");
        String oldAbsolutePath = jobParameters.getString("eland.file.name");

        if(branchCd == null && oldAbsolutePath != null) {
            String newAbsolutePath = oldAbsolutePath.substring(0, oldAbsolutePath.length() - 4) + ".bak";
            File newFile = new File(newAbsolutePath);

            if (!newFile.isFile()) {
            	FileUtils.moveFile(new File(oldAbsolutePath), newFile);
            }
        }

        return RepeatStatus.FINISHED;
    }

}

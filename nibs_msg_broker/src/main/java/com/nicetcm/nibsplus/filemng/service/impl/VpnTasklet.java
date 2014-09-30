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

import com.nicetcm.nibsplus.filemng.dao.VpnMapper;
import com.nicetcm.nibsplus.filemng.model.FileMngParameterVO;
import com.nicetcm.nibsplus.filemng.model.TransferVO;

public class VpnTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(VpnTasklet.class);

    @Value("#{config['vpn.local.path']}")
    private String localPath;

    @Autowired
    private VpnMapper vpnMapper;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
        ExecutionContext jobContext = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();

        final String yyyymmdd = jobParameters.getString("yyyymmdd");
        final String branchCd = jobParameters.getString("branchCd");



        return RepeatStatus.FINISHED;
    }

    //@Test
    public void testTasklet() {
    }

    public static void main(String[] args) {


    }
}

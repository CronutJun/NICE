package com.nicetcm.nibsplus.filemng.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
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
import com.nicetcm.nibsplus.filemng.model.TFnDealNoVO;

public class DealNoNotExistTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(DealNoNotExistTasklet.class);

    @Value("#{config['dealno.local.path']}")
    private String writePath;

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

        dealNoMapper.updateTFnHostDealNo(fileMngParameterVO);

        String fileName = "NOT_" + yyyymmdd + ".lst";
        File writeFile = new File(writePath + "/" + fileName);

        TFnDealNoVO[] tFnDealNoVOArray = dealNoMapper.selectTFnHostDealNo(fileMngParameterVO);

        if(tFnDealNoVOArray != null && tFnDealNoVOArray.length > 0) {

            List<String> dealNoList = new ArrayList<String>();

            for(TFnDealNoVO tFnDealNoVO : tFnDealNoVOArray) {
                dealNoList.add(tFnDealNoVO.getDealNo());
            }

            FileUtils.writeLines(writeFile, dealNoList);

            jobContext.put("file.send.filename", fileName);
        }

        return RepeatStatus.FINISHED;
    }

}

/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nicetcm.nibsplus.filemng.service.impl;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.scope.context.StepContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nicetcm.nibsplus.scheduler.common.SchduleException;
import com.nicetcm.nibsplus.scheduler.service.ScheduleExecuter;

/**
 *
 * @author Oleg Zhurakousky
 * @author Gunnar Hillert
 *
 */
@Service("sapMaTasklet")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/filemng/spring/context-filemng.xml", "classpath:/filemng/integration/context-filemng-FtpInboundChannelAdapter.xml"})
public class SapMaTasklet implements Tasklet {

    private static final Logger logger = LoggerFactory.getLogger(SapMaTasklet.class);

    @Resource(name="sapmaFtpChannel")
    private PollableChannel sapmaFtpChannel;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {

        Message<?> message1 = sapmaFtpChannel.receive(2000);
        logger.info(String.format("Received file message: %s.", message1));

        chunkContext.setAttribute("test", "1234");

        StepContext stepContext = chunkContext.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        jobContext.put("fileName", "filemng/data/report.csv");

        return RepeatStatus.FINISHED;
    }

    //@Test
    public void testTasklet() {
        Message<?> message1 = sapmaFtpChannel.receive(2000);

        logger.info(String.format("Received first file message: %s.", message1));
    }

    public static void main(String[] args) {

        try
        {
            logger.info("FileMngService 를 시작 합니다.");


            ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(new String[]{"classpath:/filemng/spring/context-filemng.xml", "classpath:/filemng/integration/context-filemng-FtpInboundChannelAdapter.xml"});


            PollableChannel channel = applicationContext.getBean("sapmaFtpChannel", PollableChannel.class);
            Message<?> message1 = channel.receive(2000);

            logger.info(String.format("Received first file message: %s.", message1));

            logger.info("FileMngService 를 [정상적] 으로 실행 되었습니다.");

            applicationContext.close();
        } catch (Exception e)
        {
            //e.printStackTrace();
            logger.error(e.getMessage());
            logger.error("FileMngService 를 [비정상적] 으로 종료 되었습니다.");
            System.exit(-1);
        } finally {

        }
    }
}

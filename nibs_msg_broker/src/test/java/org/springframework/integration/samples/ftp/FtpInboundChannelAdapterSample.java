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
package org.springframework.integration.samples.ftp;

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
import org.springframework.integration.Message;
import org.springframework.integration.core.PollableChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Oleg Zhurakousky
 * @author Gunnar Hillert
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/filemng/spring/context-filemng.xml", "classpath:/filemng/integration/FtpInboundChannelAdapterSample-context.xml"})
public class FtpInboundChannelAdapterSample implements Tasklet {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name="ftpChannel")
    private PollableChannel ftpChannel;

    @Test
    public void runDemo() throws Exception{


        Message<?> message1 = ftpChannel.receive(2000);
        Message<?> message2 = ftpChannel.receive(2000);
        Message<?> message3 = ftpChannel.receive(1000);

        logger.info(String.format("Received first file message: %s.", message1));
        logger.info(String.format("Received second file message: %s.", message2));
        logger.info(String.format("Received nothing else: %s.", message3));
/*
        assertNotNull(message1);
        assertNotNull(message2);
        assertNull("Was NOT expecting a third message.", message3);
*/
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception
    {
        runDemo();

        chunkContext.setAttribute("test", "1234");

        StepContext stepContext = chunkContext.getStepContext();
        StepExecution stepExecution = stepContext.getStepExecution();
        JobExecution jobExecution = stepExecution.getJobExecution();
        ExecutionContext jobContext = jobExecution.getExecutionContext();
        jobContext.put("fileName", "filemng/data/report.csv");

        return RepeatStatus.FINISHED;
    }

}

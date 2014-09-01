package com.nicetcm.nibsplus.broker.ams;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerUpdSchedJob
 *
 *  배포 스케쥴 실행 클래스 (Job)
 *
 *
 * @author  K.D.J
 * @since   2014.08.29
 */

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMSBrokerSchedJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerSchedJob.class);

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        logger.debug("Job Executed.[{}, {}, {}, {}................", arg0.getJobDetail().getKey().getGroup(),
                                                                     arg0.getJobDetail().getKey().getName(),
                                                                     arg0.getTrigger().getKey().getGroup(),
                                                                     arg0.getTrigger().getKey().getName() );
        /**
         * 배포 스케쥴
         */
        if( arg0.getJobDetail().getKey().getName().equals("UPDATES") ) {

        }
        /**
         * 저널업로드 스케쥴
         */
        else if(  arg0.getJobDetail().getKey().getName().equals("JOURNAL") ) {

        }
    }

}

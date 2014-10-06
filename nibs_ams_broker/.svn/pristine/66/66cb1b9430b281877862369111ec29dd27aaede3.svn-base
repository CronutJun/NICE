package com.nicetcm.nibsplus.broker.ams;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerSchedWorker
 *
 *  AMS 스케쥴잡 처리
 *
 *
 * @author  K.D.J
 * @since   2014.08.28
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;

public class AMSBrokerSchedWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerSchedWorker.class);

    private AMSBrokerReqJob reqJob;

    public AMSBrokerSchedWorker(AMSBrokerReqJob reqJob) {
        this.reqJob = reqJob;
    }

    @Override
    public void run() {

        try {
            reqJob.requestJob();
        }
        catch(Exception e) {
            logger.error("AMSBrokerSchedWorker raise error: {}", e.getMessage());
        }
    }

}

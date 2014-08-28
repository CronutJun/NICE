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

public class AMSBrokerSchedWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerSchedWorker.class);

    private String svc;

    public AMSBrokerSchedWorker(String svc) {
        this.svc = svc;
    }

    @Override
    public void run() {

        try {
            logger.debug("Thread {} is start[{}]", Thread.currentThread().getName(), svc );
            Thread.sleep(10000);
            logger.debug("Thread {} is finished", Thread.currentThread().getName() );
        }
        catch(InterruptedException ie) {
            logger.debug("interrupted..");
        }
    }

}

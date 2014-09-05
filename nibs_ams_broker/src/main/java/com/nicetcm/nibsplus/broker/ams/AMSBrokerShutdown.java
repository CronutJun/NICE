package com.nicetcm.nibsplus.broker.ams;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * AMSBrokerShutdown
 *
 *  AMSBroker 서버 종료
 *
 *
 * @author  K.D.J
 * @since   2014.09.05
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AMSBrokerShutdown extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerShutdown.class);

    public AMSBrokerShutdown() {
        this.setName("SHUTDOWN");
    }

    public void run()  {
        try {
            logger.debug("Start scheduler shutdown..");
            AMSBrokerMain.getScheduler().shutdown();
            logger.debug("Scheduler stopped.");
            logger.debug("Start server socket shutdown..");
            AMSBrokerServer.getServer().close();
            logger.debug("Server socket stopped.");
            logger.debug("Start RMI shutdown..");
            AMSBrokerMain.getRMI().unbind();
            logger.debug("RMI stopped.");

            logger.debug("Thread's count = {}", Thread.activeCount() );
            //for (Thread t : Thread.getAllStackTraces().keySet()) {
            //    if (t.getState()==Thread.State.RUNNABLE) {
            //        logger.debug("Thread id = {},{}", t.getId(), t.getName());
            //        t.interrupt();
            //    }
            //}
            //System.exit(0);
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

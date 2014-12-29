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
            logger.warn("Start RMI shutdown..");
            AMSBrokerMain.getRMI().unbind();
            logger.warn("RMI stopped.");
            logger.warn("Start listen of requesting to MAC shutdown.");
            AMSBrokerReqJob.stopListenerAll();
            logger.warn("listen of requesting to MAC is stopped.");
            logger.warn("Start thread group of schedule shutdown.");
            AMSBrokerSchedWorkGroup.getInstance().shutdownNow();
            logger.warn("Thread group of schedule is stopped.");
            logger.warn("Start scheduler shutdown.");
            AMSBrokerMain.getScheduler().shutdown();
            logger.warn("Scheduler stopped.");
            logger.warn("Start server socket shutdown.");
            AMSBrokerServer.getServer().close();
            logger.warn("Server socket stopped.");
            AMSBrokerMain.stopJMX();
            logger.warn("JMXAgent is stopped.");

            logger.warn("Thread's count = {}", Thread.activeCount() );
            logger.warn("JMXAgent is stopped.");
            logger.warn("Shutdown complete.");
            for( Thread t: Thread.getAllStackTraces().keySet() ) {
                if( t.isAlive() && this.getId() != t.getId() ) {
                    t.interrupt();
                }
            }
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

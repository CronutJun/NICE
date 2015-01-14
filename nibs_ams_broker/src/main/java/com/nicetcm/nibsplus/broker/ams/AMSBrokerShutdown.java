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
    private static boolean isRun = false;

    public AMSBrokerShutdown() {
        this.setName("SHUTDOWN");
    }

    public void run()  {
        try {

            if( !isRun ) isRun = true;
            else return;

            logger.warn("Start RMI shutdown..");
            AMSBrokerMain.getRMI().unbind();
            logger.warn("RMI stopped.");
            logger.warn("Start listening of request to MAC shutdown.");
            AMSBrokerReqJob.stopListenerAll();
            logger.warn("listening of request to MAC is stopped.");
            logger.warn("Start thread group of schedule shutdown.");
            AMSBrokerSchedWorkGroup.getInstance().shutdownNow();
            logger.warn("Thread group of schedule is stopped.");
            logger.warn("Start scheduler shutdown.");
            AMSBrokerMain.getScheduler().shutdown(true);
            Thread.sleep(1000);
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
            logger.error(e.getMessage());
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
    }
}

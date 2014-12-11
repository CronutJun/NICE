package com.nicetcm.nibsplus.broker.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MsgBrokerShutdown
 *
 *  MsgBroker 서버 자원 동시 종료
 *
 *
 * @author  K.D.J
 * @since   2014.12.11
 */

public class MsgBrokerShutdownAgent extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerShutdownAgent.class);

    private String type = null;
    private String nm   = null;
    private MsgBrokerConsumer con = null;

    public MsgBrokerShutdownAgent(String type, String name, MsgBrokerConsumer con) {

        super("SHUTDOWN-" + type);

        this.type = type;
        this.nm   = name;
        this.con  = con;
    }


    public void run()  {

        Thread.currentThread().setName("SHUTDOWN-" + type);
        try {
            if( type.equals("consumer") ) {
                con.close();
                logger.warn("Consumer \"{}\" is closed.", nm);
                for(MsgBrokerBlockingWorker th: con.getListener().getBlockingWorkGroup().getBlockingThreads()) {
                    logger.warn("parallel threads {} is going to stop", th.getName() );
                    th.stopWork();
                    logger.warn("parallel threads {} is stopped", th.getName() );
                }
                MsgBrokerConsumer.consumers.remove(nm);
            }
        }
        catch( Exception e ) {
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
    }
}

package com.nicetcm.nibsplus.broker.msg;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MsgBrokerShutdown
 *
 *  MsgBroker 서버 종료
 *
 *
 * @author  K.D.J
 * @since   2014.09.06
 */

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgBrokerShutdown extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerShutdown.class);

    public MsgBrokerShutdown() {
        this.setName("SHUTDOWN");
    }

    public void run()  {
        try {
            logger.debug("Start RMI shutdown..");
            MsgBrokerMain.getRMI().unbind();
            logger.debug("RMI stopped.");

            logger.debug("Stopping consumer..");
            Iterator<Map.Entry<String, MsgBrokerConsumer>>  itrC = MsgBrokerConsumer.consumers.entrySet().iterator();
            while ( itrC.hasNext() ){
                Map.Entry<String, MsgBrokerConsumer> e = (Map.Entry<String, MsgBrokerConsumer>)itrC.next();
                e.getValue().close();
                logger.debug("Consumer \"{}\" is closed.", e.getKey());
                MsgBrokerConsumer.consumers.remove(e.getKey());
            }
            logger.debug("Consumer is stopped.");

            logger.debug("Stopping producer..");
            Iterator<Map.Entry<String, MsgBrokerProducer>>  itrP = MsgBrokerProducer.producers.entrySet().iterator();
            while ( itrP.hasNext() ){
                Map.Entry<String, MsgBrokerProducer> e = (Map.Entry<String, MsgBrokerProducer>)itrP.next();
                e.getValue().close();
                logger.debug("Producer \"{}\" is closed.", e.getKey());
                MsgBrokerConsumer.consumers.remove(e.getKey());
            }
            logger.debug("Producer is stopped.");

            MsgBrokerMain.stopJMX();
            logger.debug("JMXAgent is stopped.");

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

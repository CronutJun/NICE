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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.msg.util.ActiveMQ;

public class MsgBrokerShutdown extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerShutdown.class);
    private static boolean isRun = false;

    public MsgBrokerShutdown() {
        this.setName("SHUTDOWN");
    }

    public void run()  {
        try {

            if( !isRun ) isRun = true;
            else return;

            logger.warn("Start RMI shutdown..");
            MsgBrokerMain.getRMI().unbind();
            logger.warn("RMI stopped.");

            logger.warn("Stopping consumer..");
            ExecutorService shutCon = Executors.newCachedThreadPool();
            Iterator<Map.Entry<String, MsgBrokerConsumer>>  itrC = MsgBrokerConsumer.consumers.entrySet().iterator();
            while ( itrC.hasNext() ){
                Map.Entry<String, MsgBrokerConsumer> e = (Map.Entry<String, MsgBrokerConsumer>)itrC.next();
                shutCon.execute( new MsgBrokerShutdownAgent("consumer", e.getKey(), e.getValue()) );
            }
            shutCon.shutdown();
            shutCon.awaitTermination(1, TimeUnit.HOURS);
            logger.warn("Consumer is stopped.");

            logger.warn("Stopping producer..");
            Iterator<Map.Entry<String, MsgBrokerProducer>>  itrP = MsgBrokerProducer.producers.entrySet().iterator();
            while ( itrP.hasNext() ){
                Map.Entry<String, MsgBrokerProducer> e = (Map.Entry<String, MsgBrokerProducer>)itrP.next();
                e.getValue().close();
                logger.warn("Producer \"{}\" is closed.", e.getKey());
                MsgBrokerConsumer.consumers.remove(e.getKey());
            }
            logger.warn("Producer is stopped.");

            logger.warn("Releasing other resources..");
            Iterator<Map.Entry<String, ActiveMQ>>  itrCR = MsgBrokerConsumer.req.entrySet().iterator();
            while ( itrCR.hasNext() ){
                Map.Entry<String, ActiveMQ> e = (Map.Entry<String, ActiveMQ>)itrCR.next();
                e.getValue().close();
                logger.warn("Request ActiveMQ \"{}\" is closed.", e.getKey());
                MsgBrokerConsumer.req.remove(e.getKey());
            }
            logger.warn("Other resources are released.");

            logger.warn("Thread's count = {}", Thread.getAllStackTraces().keySet().size() );

            MsgBrokerWorkGroup.getInstance().shutdown();

            MsgBrokerMain.stopJMX();
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

package com.nicetcm.nibsplus.broker.msg;

import java.io.InputStream;

import javax.jms.BytesMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class MsgBrokerMain {
    
    public static final Logger logger = LoggerFactory.getLogger(MsgBrokerMain.class);
    
    public MsgBrokerMain() {
        try {
            /* 
             * Spring Component 초기화
             */
            logger.debug("Going to scanning");
            MsgBrokerSpringMain.sprCtx.register(MsgBrokerAppConfig.class);
            MsgBrokerSpringMain.sprCtx.scan("com.nicetcm.nibsplus.broker.msg.services");
            MsgBrokerSpringMain.sprCtx.refresh();
            
            
            /*
             *  ActiveMQ 초기화
             */
            MsgBrokerQInitializer.getInstance("qconfig.json")
                                 .initProducers()
                                 .initConsumers();
            /*
             * Thread Executor 초기화
             */
            MsgBrokerWorkGroup.getInstance();
            
//            while( !Thread.interrupted() ) {
//                logger.debug("Going to sleep");
//                Thread.sleep(1000 * 60 * 60);
//            }
//            logger.debug("Out of loop");
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    
    
    public static void main(String[] args) {
        
        try {
            InputStream is = MsgBrokerMain.class.getResourceAsStream("/msg.properties");
            MsgCommon.msgProps.load(is);
            MsgBrokerMain main = new MsgBrokerMain();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        
    }

}

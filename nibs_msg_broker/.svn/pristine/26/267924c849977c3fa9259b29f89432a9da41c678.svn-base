package com.nicetcm.nibsplus.broker.msg;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class MsgBrokerMain {

    private MsgBrokerRMIServer rmi;
    
    public static final Logger logger = LoggerFactory.getLogger(MsgBrokerMain.class);
    
    public MsgBrokerMain() {
        try {
            /* 
             * Spring Component 초기화
             */
            logger.debug("Going to scanning");
            MsgBrokerSpringMain.sprCtx.register(MsgBrokerAppConfig.class);
            //MsgBrokerSpringMain.sprCtx.scan("com.nicetcm.nibsplus.broker.msg.services"); @ComponentScan으로 대체
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
            /*
             * RMI Server 초기화
             */
            rmi = new MsgBrokerRMIServer();
            rmi.bind();
            
        }
        catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    
    
    public static void main(String[] args) {
        
        try {
            org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", MsgBrokerConst.SVR_TYPE)));
            InputStream is = MsgBrokerMain.class.getResourceAsStream(
                    String.format("/%s/msg.properties", MsgBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            MsgBrokerMain main = new MsgBrokerMain();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        
    }

}

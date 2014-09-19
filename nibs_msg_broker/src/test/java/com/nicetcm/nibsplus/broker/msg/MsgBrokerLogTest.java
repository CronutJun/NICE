package com.nicetcm.nibsplus.broker.msg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MsgBrokerLogTest {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerLogTest.class);

    private class InThread implements Runnable {

        public void run() {
            logger.debug("This is innerClass");
        }
    }

    public void printLog() {
        logger.debug("abc");
        logger.debug("def");
        new Thread(new InThread()).start();
        logger.debug("kkk");
    }

    public static void main(String args[]) {
        try {
            org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", "uat")));
            new MsgBrokerLogTest().printLog();

        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

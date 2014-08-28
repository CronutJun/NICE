package com.nicetcm.nibsplus.broker.ams;

import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerSchedWorkGroup;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerSchedWorker;

public class ThreadPoolTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

   public ThreadPoolTest() {
       try {
           InputStream is = AMSBrokerMain.class.getResourceAsStream(
                   String.format("/%s/ams.properties", AMSBrokerConst.SVR_TYPE));
           MsgCommon.msgProps.load(is);
           MsgCommon.READ_BUF_SIZE = Integer.parseInt(MsgCommon.msgProps.getProperty("read_buf_size"));
       }
       catch( Exception e ) {
           MsgCommon.READ_BUF_SIZE = 2000;
       }
        for( int i = 0; i < 30; i++ ) {

            logger.debug("i = {}", i);
            try {
                AMSBrokerSchedWorkGroup.getInstance().execute(new AMSBrokerSchedWorker(Integer.toString(i)));
            }
            catch( Exception e ) {
                logger.debug("error occured = {}", e.getMessage());
            }
        }
        logger.debug("OK");
        try {
            AMSBrokerSchedWorkGroup.getInstance().shutdown();
        }
        catch( Exception e ) {
            logger.debug("Exception");
        }
    }

    public static void main(String args[]) {
        new ThreadPoolTest();
    }
}
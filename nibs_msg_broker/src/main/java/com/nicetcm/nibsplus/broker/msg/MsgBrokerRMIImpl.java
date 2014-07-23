package com.nicetcm.nibsplus.broker.msg;

import java.nio.ByteBuffer;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerRMI;

public class MsgBrokerRMIImpl implements MsgBrokerRMI {

    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerRMIImpl.class);
    private FileOutputStream fOut;
    
    public MsgBrokerRMIImpl(){
    }
    
    public byte[] callBrokerSync(byte[] msg, int timeout) throws java.rmi.RemoteException {
        logger.debug("Thread " + Thread.currentThread().getId() + "'s data = " + msg);
        try {
            Thread.sleep(10000);
            logger.debug("Thread " + Thread.currentThread().getId() + "call complete");
        }
        catch ( Exception err ) {
            logger.debug("Exception raised " + err.getMessage());
        }
        return new byte[10];
    }

    public void callBrokerAsync(byte[] msg) throws java.rmi.RemoteException {

    }
}

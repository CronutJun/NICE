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
        
        return msg;
    }

    public void callBrokerAsync(byte[] msg) throws java.rmi.RemoteException {

    }
}

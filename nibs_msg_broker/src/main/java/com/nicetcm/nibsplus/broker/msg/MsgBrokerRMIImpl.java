package com.nicetcm.nibsplus.broker.msg;

import java.nio.ByteBuffer;
import java.io.*;
import java.util.concurrent.*;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerRMI;

public class MsgBrokerRMIImpl implements MsgBrokerRMI {

    public static final ConcurrentMap<String, BlockingQueue<byte[]>> rmiSyncAns = new ConcurrentHashMap<String, BlockingQueue<byte[]>>();
    
    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerRMIImpl.class);
    private FileOutputStream fOut;
    
    public MsgBrokerRMIImpl(){
    }
    
    public byte[] callBrokerSync(byte[] msg, int timeout) throws java.rmi.RemoteException {
        BlockingQueue<byte[]> waitQ = new LinkedBlockingQueue<byte[]>();
        
        rmiSyncAns.putIfAbsent("1111", waitQ);
        try {
            try {
                waitQ.poll(timeout, TimeUnit.SECONDS);
                if( waitQ.size() == 0 )
                    throw new java.rmi.RemoteException("Timeout");
             }
             catch (InterruptedException ie ) {
                 logger.debug("Interrupted..");
             }
             catch ( java.rmi.RemoteException re ) {
                 logger.debug("callBrokerSync Remote error raised.. {}", re.getMessage());
                 throw re;
             }
             catch ( Exception e) {
                 logger.debug("callBrokerSync error raised.. {}", e.getMessage());
             }
        }
        finally {
            rmiSyncAns.remove("1111");
            logger.debug("remove element of rmiSyncAns");
        }
        
        return msg;
    }

    public void callBrokerAsync(byte[] msg) throws java.rmi.RemoteException {

    }
}

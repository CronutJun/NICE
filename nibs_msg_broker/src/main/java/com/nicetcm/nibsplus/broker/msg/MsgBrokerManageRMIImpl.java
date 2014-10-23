package com.nicetcm.nibsplus.broker.msg;

import java.util.concurrent.BlockingQueue;
import java.util.HashMap;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerManageRMI;

public class MsgBrokerManageRMIImpl implements MsgBrokerManageRMI {

    public static final Logger logger = LoggerFactory.getLogger(MsgBrokerManageRMIImpl.class);

    private static final HashMap<String, MsgBrokerManageRMI> manageRMIs = new HashMap<String, MsgBrokerManageRMI>();

    public static void ansRMIAvailability( byte[] msg ) {

        try {
            logger.debug("ansRMI");
            if( Boolean.parseBoolean(MsgCommon.msgProps.getProperty("rmi.availability", "false")) ) {
                logger.debug("availability is true");
                for( String svrIp: MsgCommon.msgProps.getProperty("rmi.availability.servers").split(",") ) {
                    logger.debug("svrIp = {}", svrIp );
                    if( svrIp.trim().length() > 0 ) {
                        MsgBrokerManageRMI remoteObj = manageRMIs.get(svrIp);
                        if( remoteObj == null ) {
                            Registry registry = LocateRegistry.getRegistry(svrIp.trim(), Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port", "10199")));
                            remoteObj = (MsgBrokerManageRMI)registry.lookup("MsgBrokerManageRMI");
                            manageRMIs.put(svrIp.trim(), remoteObj);
                        }
                        remoteObj.putRMIAns( msg );
                    }
                }
            }
        }
        catch( Exception e ) {
            logger.info("manage RMI Error raised. Message = {}", e.getMessage() );
            for( StackTraceElement se: e.getStackTrace() )
                logger.info(se.toString());
        }
    }

    @Override
    public void putRMIAns(byte[] msg) throws Exception {
        final int transSeqNoLen = 7;
        final int transSeqNoOfs = 33;

        byte[] bTransSeqNo = new byte[transSeqNoLen];

        System.arraycopy( msg, transSeqNoOfs, bTransSeqNo, 0, transSeqNoLen );

        BlockingQueue<byte[]> waitQ = MsgBrokerRMIImpl.rmiSyncAns.get(new String(bTransSeqNo));
        if( waitQ != null ) {
            waitQ.put( msg );
        }
    }

}

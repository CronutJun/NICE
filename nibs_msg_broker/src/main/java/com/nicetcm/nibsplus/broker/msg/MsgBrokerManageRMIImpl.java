package com.nicetcm.nibsplus.broker.msg;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerManageRMI;

public class MsgBrokerManageRMIImpl implements MsgBrokerManageRMI {

    public static final Logger logger = LoggerFactory.getLogger(MsgBrokerManageRMIImpl.class);

    public static void ansRMIAvailability( String transSeqNo, byte[] msg ) {

        try {
            logger.debug("ansRMI");
            if( Boolean.parseBoolean(MsgCommon.msgProps.getProperty("rmi.availability", "false")) ) {
                logger.debug("availability is true");
                for( String svrIp: MsgCommon.msgProps.getProperty("rmi.availability.servers").split(",") ) {
                    logger.warn("svrIp = {}", svrIp );
                    if( svrIp.trim().length() > 0 ) {
                        Registry registry = LocateRegistry.getRegistry(svrIp.trim(), Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port", "10199")));
                        MsgBrokerManageRMI remoteObj = (MsgBrokerManageRMI)registry.lookup("MsgBrokerManageRMI");
                        logger.warn("Going to find remote waiter {}. [{}]", transSeqNo );
                        remoteObj.putRMIAns( transSeqNo, msg );
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

    public static void putRMIOrigMsgAvailability( String transSeqNo, byte[] msg ) {

        try {
            logger.debug("putRMI");
            if( Boolean.parseBoolean(MsgCommon.msgProps.getProperty("rmi.availability", "false")) ) {
                logger.debug("availability is true");
                for( String svrIp: MsgCommon.msgProps.getProperty("rmi.availability.servers").split(",") ) {
                    logger.debug("svrIp = {}", svrIp );
                    if( svrIp.trim().length() > 0 ) {
                        Registry registry = LocateRegistry.getRegistry(svrIp.trim(), Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port", "10199")));
                        MsgBrokerManageRMI remoteObj = (MsgBrokerManageRMI)registry.lookup("MsgBrokerManageRMI");
                        remoteObj.putRMIOrigMsg( transSeqNo, msg );
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

    public static void removeRMIOrigMsgAvailability( String transSeqNo ) {

        try {
            logger.debug("removeRMI");
            if( Boolean.parseBoolean(MsgCommon.msgProps.getProperty("rmi.availability", "false")) ) {
                logger.debug("availability is true");
                for( String svrIp: MsgCommon.msgProps.getProperty("rmi.availability.servers").split(",") ) {
                    logger.debug("svrIp = {}", svrIp );
                    if( svrIp.trim().length() > 0 ) {
                        Registry registry = LocateRegistry.getRegistry(svrIp.trim(), Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port", "10199")));
                        MsgBrokerManageRMI remoteObj = (MsgBrokerManageRMI)registry.lookup("MsgBrokerManageRMI");
                        remoteObj.removeRMIOrigMsg( transSeqNo );
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
    public void putRMIAns(String transSeqNo, byte[] msg) throws Exception {

        logger.warn("Going to find remote waiter [{}]", transSeqNo );
        BlockingQueue<byte[]> waitQ = MsgBrokerRMIImpl.rmiSyncAns.get(transSeqNo);
        if( waitQ != null ) {
            logger.warn("Findout remote waiter {}. [{}]", waitQ, transSeqNo );
            waitQ.put( msg );
        }
    }

    @Override
    public void putRMIOrigMsg(String transSeqNo, byte[] msg) throws Exception {

        MsgBrokerRMIImpl.rmiOrigMsg.put(transSeqNo, msg);
    }

    @Override
    public void removeRMIOrigMsg(String transSeqNo) throws Exception {

        MsgBrokerRMIImpl.rmiOrigMsg.remove(new String(transSeqNo));
    }

}

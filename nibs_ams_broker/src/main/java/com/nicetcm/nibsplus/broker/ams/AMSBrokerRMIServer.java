package com.nicetcm.nibsplus.broker.ams;

import java.rmi.registry.*;
import java.rmi.server.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.nicetcm.nibsplus.broker.ams.rmi.*;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class AMSBrokerRMIServer {

    public static final Logger logger = LoggerFactory.getLogger(AMSBrokerRMIServer.class);

    private Registry registry;
    private AMSBrokerRMI stub;
    private AMSBrokerRMIImpl remoteObj;

    public AMSBrokerRMIServer() {
    }

    public void bind() {

        try {
            logger.debug("Going to bind..");
            remoteObj = new AMSBrokerRMIImpl();

            stub = (AMSBrokerRMI)UnicastRemoteObject.exportObject(remoteObj, Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port")));

            LocateRegistry.createRegistry(Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port")));
            // Bind the remote object's stub in the registry
            registry = LocateRegistry.getRegistry(Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port")));
            logger.debug("rebind");
            registry.rebind("AMSBrokerRMI", stub);
           //java.rmi.Naming.rebind("rmi://localhost:1099/AMSBrokerRMI", remoteObj);
            logger.debug("AMSBrokerRMI Remote Object bound to the registry and ready to service incoming client calls...");
        }
        catch(java.rmi.RemoteException e) {
            logger.error("Exception occurred during processing incoming method call");
            logger.error("Error raised. Message = {}", e.getMessage() );
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
        catch (Exception e) {
            logger.error("Server exception: " + e.toString());
            logger.error("Error raised. Message = {}", e.getMessage() );
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
    }

    public void unbind() {
        try {
            registry.unbind("AMSBrokerRMI");
            UnicastRemoteObject.unexportObject(remoteObj, true);
            logger.debug("registry unbound");
        }
        catch( Exception e ) {
            logger.error("Error raised. Message = {}", e.getMessage() );
            for( StackTraceElement se: e.getStackTrace() )
                logger.error(se.toString());
        }
    }
}

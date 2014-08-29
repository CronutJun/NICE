package com.nicetcm.nibsplus.broker.ams;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.rmi.Naming;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.nicetcm.nibsplus.broker.ams.rmi.*;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class AMSBrokerRMIServer {

    public static final Logger logger = LoggerFactory.getLogger(AMSBrokerRMIServer.class);

    private Registry registry;

    public AMSBrokerRMIServer() {
    }

    public void bind() {

        try {
            logger.debug("Going to bind..");
            AMSBrokerRMIImpl remoteObj = new AMSBrokerRMIImpl();

            AMSBrokerRMI stub = (AMSBrokerRMI)UnicastRemoteObject.exportObject(remoteObj, Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port")));

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
            e.printStackTrace();
        }
        catch (Exception e) {
            logger.error("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public void unbind() {
        try {
            registry.unbind("AMSBrokerRMI");
            logger.debug("registry unbound");
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

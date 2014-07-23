package com.nicetcm.nibsplus.broker.msg;

import java.rmi.registry.*;
import java.rmi.server.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerRMI;

public class MsgBrokerRMIServer {
    
    public static final Logger logger = LoggerFactory.getLogger(MsgBrokerRMIServer.class);
    
    public MsgBrokerRMIServer() {
    }
    
    public void bind() {
        
        try { 
            MsgBrokerRMIImpl remoteObj = new MsgBrokerRMIImpl();
            
            MsgBrokerRMI stub = (MsgBrokerRMI)UnicastRemoteObject.exportObject(remoteObj, Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port")));

            LocateRegistry.createRegistry(Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port")));
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry(Integer.parseInt(MsgCommon.msgProps.getProperty("rmi.port")));
            logger.debug("rebind");
            registry.rebind("MsgBrokerRMI", stub);
            logger.debug("MsgBrokerRMI Remote Object bound to the registry and ready to service incoming client calls..."); 
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
}

package com.nicetcm.nibsplus.broker.ams;

import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.*;
import java.rmi.Naming;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class AMSBrokerRMIServer {
    
    public static final Logger logger = LoggerFactory.getLogger(AMSBrokerRMIServer.class);
    
    public AMSBrokerRMIServer() {
    }
    
    public void bind() {
        
        try { 
            AMSBrokerRMIImpl remoteObj = new AMSBrokerRMIImpl();
            
            AMSBrokerRMI stub = (AMSBrokerRMI)UnicastRemoteObject.exportObject(remoteObj, 1099);

            LocateRegistry.createRegistry(1099); // rmiregistry를 사용하지 않고 직접 생성
            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry(1099);
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
}

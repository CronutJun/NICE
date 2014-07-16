package com.nicetcm.nibsplus.broker.ams;

import java.rmi.Remote;

public interface AMSBrokerRMI extends Remote {
    
    public String sayHello(String name) throws java.rmi.RemoteException;
    public String threadTest(String data) throws java.rmi.RemoteException;
    public void dataUploadToBroker( byte[] data, boolean isFirst, boolean hasNext ) throws java.rmi.RemoteException;
}

package com.nicetcm.nibsplus.broker.msg.rmi;

import java.rmi.Remote;

public interface MsgBrokerRMI extends Remote {
    
    public byte[] callBrokerSync(byte[] msg, int timeout) throws Exception;
    public void callBrokerAsync(byte[] msg) throws Exception;
}

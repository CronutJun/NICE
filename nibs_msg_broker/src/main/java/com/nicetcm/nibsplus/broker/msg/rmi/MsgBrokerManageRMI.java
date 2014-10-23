package com.nicetcm.nibsplus.broker.msg.rmi;

import java.rmi.Remote;

public interface MsgBrokerManageRMI extends Remote {

    public void putRMIAns(byte[] msg) throws Exception;

}

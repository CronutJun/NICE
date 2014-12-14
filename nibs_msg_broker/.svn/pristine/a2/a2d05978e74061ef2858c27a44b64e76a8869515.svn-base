package com.nicetcm.nibsplus.broker.msg.rmi;

import java.rmi.Remote;

public interface MsgBrokerManageRMI extends Remote {

    public void putRMIAns(String transSeqNo, byte[] msg) throws Exception;
    public void putRMIOrigMsg(String transSeqNo, byte[] msg) throws Exception;
    public void removeRMIOrigMsg(String transSeqNo) throws Exception;

}

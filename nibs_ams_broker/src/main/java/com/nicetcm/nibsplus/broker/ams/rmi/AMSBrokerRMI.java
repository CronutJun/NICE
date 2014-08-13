package com.nicetcm.nibsplus.broker.ams.rmi;

import java.rmi.Remote;
import java.util.Date;
import java.util.ArrayList;

public interface AMSBrokerRMI extends Remote {

    public String threadTest(String data) throws Exception;
    public void dataUploadToBroker( byte[] data, boolean isFirst, boolean hasNext ) throws Exception;


    public void reqEnvInfToMac( Date trxDate, String trxNo, String macNo, int timeOut ) throws Exception;
    public void reqEnvInfToMacs( Date trxDate, String trxNo, ArrayList<String> macs ) throws Exception;
}

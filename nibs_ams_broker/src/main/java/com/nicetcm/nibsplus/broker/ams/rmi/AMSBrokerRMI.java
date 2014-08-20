package com.nicetcm.nibsplus.broker.ams.rmi;

import java.rmi.Remote;
import java.util.ArrayList;

public interface AMSBrokerRMI extends Remote {

    public String threadTest(String data) throws Exception;
    public void dataUploadToBroker( byte[] data, boolean isFirst, boolean hasNext ) throws Exception;


    public void reqEnvInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, int timeOut ) throws Exception;
    public void reqEnvInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs ) throws Exception;
    public void reqRegInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqRegInfo reqRegInfo, int timeOut ) throws Exception;
    public void reqRegInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqRegInfo> reqRegInfos ) throws Exception;
    public void reqIniInfToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqIniInfo reqIniInfo, int timeOut ) throws Exception;
    public void reqIniInfToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqIniInfo> reqIniInfos ) throws Exception;
    public void reqEnvChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, String macNo, ArrayList<RMIEnvValue> envValues ) throws Exception;
    public void reqEnvChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<String> macs, RMIEnvValue envValue ) throws Exception;
    public void reqRegChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqRegInfo reqRegInfo, int timeOut ) throws Exception;
    public void reqRegChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqRegInfo> reqRegInfos ) throws Exception;
    public void reqIniChgToMac( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, RMIReqIniInfo reqIniInfo, int timeOut ) throws Exception;
    public void reqIniChgToMacs( String trxDate, String trxNo, String trxCd, String actCd, String trxUid, ArrayList<RMIReqIniInfo> reqIniInfos ) throws Exception;
}

package com.nicetcm.nibsplus.broker.ams;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;
import java.io.*;

import com.nicetcm.nibsplus.broker.ams.rmi.*;

public class RMIClientTest {

    public void threadTest() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");
        Calendar c = Calendar.getInstance();
        java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("HH:mm:ss.SSS");
        String tm = fmt.format(c.getTime());
        System.out.println(remoteObj.threadTest(tm));
        System.out.println(remoteObj.threadTest(tm));
    }

    public void dataUpload() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        FileInputStream file = new FileInputStream("D:/CronutWorks/NICE/Documents/Design/07. �������/����/P140217_DS 06 ȭ�����Ǽ�_AMS v1.1.pptx");
        boolean isFirst = true;
        boolean hasNext = false;
        while ( file.available() > 0) {
            byte[] data = file.available() > 262144 ? new byte[262144] : new byte[file.available()];
            file.read(data);
            hasNext = file.available() > 0;
            remoteObj.dataUploadToBroker(data, isFirst, hasNext);
            isFirst = false;
        }
        System.out.println("Upload complete");
    }

    public void reqEnvInfToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        remoteObj.reqEnvInfToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), "000001", "1000", "1000", "test", "3001", 0 );
    }

    public void reqRegInfToMac() throws Exception {
        Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10299);
        System.out.println("registry");
        AMSBrokerRMI remoteObj = (AMSBrokerRMI)registry.lookup("AMSBrokerRMI");
        System.out.println("lookup");

        RMIReqRegInfo reqRegInfo = new RMIReqRegInfo();
        reqRegInfo.setMacNo( "3001" );
        reqRegInfo.setBaseKey( "HKEY_CURRENT_USER" );
        reqRegInfo.setKeyPath( "Software\\ANGARA" );
        reqRegInfo.setKeyName( "TraceFileName" );

        remoteObj.reqRegInfToMac( AMSBrokerLib.getMsgDate(AMSBrokerLib.getSysDate()), "000001", "2000", "1000", "test", reqRegInfo, 0 );
    }

    public static void main(String[] args) {
        try {
            new RMIClientTest().reqRegInfToMac();
        }
        catch( java.rmi.RemoteException e ) {
            System.out.println("Something has gone wrong during remote method call...");
            e.printStackTrace();
        }
        catch( java.rmi.NotBoundException e ) {
            System.out.println("Could't bound...");
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

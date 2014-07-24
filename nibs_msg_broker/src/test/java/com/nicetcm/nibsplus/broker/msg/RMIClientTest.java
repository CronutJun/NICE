package com.nicetcm.nibsplus.broker.msg;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.*;
import com.nicetcm.nibsplus.broker.msg.rmi.*;

public class RMIClientTest {
    public static void main(String[] args) { 
        try { 
            Registry registry = LocateRegistry.getRegistry("10.3.28.180", 10199);
            System.out.println("registry");
            MsgBrokerRMI remoteObj = (MsgBrokerRMI)registry.lookup("MsgBrokerRMI");
            System.out.println("lookup");
            System.out.println(remoteObj.callBrokerSync("005B00  HOST   0422201407160830013819872         CM03101112                               2014071520140715002300230191    066         000000000000000000000000000000000050000000050000000000000005000000005000000                                                                                                                                                                                                                                                                                                               ".getBytes(), 5).length);
            /*
            String msg = remoteObj.sayHello("KDJ"); 
            System.out.println(msg);
            */ 
            
            /*
            FileInputStream file = new FileInputStream("D:/CronutWorks/NICE/Documents/Design/07. �����������/��������/P140217_DS 06 ȭ�����Ǽ�_AMS v1.1.pptx");
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
            */
            
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

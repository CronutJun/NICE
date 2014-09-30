package com.nicetcm.nibsplus.broker.msg.util;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MsgBrokerTester
 *
 *  MsgBroker 서버의 테스트를 위한 전문을 파일에서 읽어 MQ PUT
 *
 *
 * @author  K.D.J
 * @since   2014.09.18
 */


import java.io.File;
import java.io.InputStream;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;
import java.nio.ByteBuffer;

import javax.jms.BytesMessage;

import com.nicetcm.nibsplus.broker.msg.rmi.MsgBrokerRMI;
import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgFmtRec;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerMain;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;

public class MsgBrokerTester {

    private File srcFile;
    private String mode;

    private Scanner scan;
    private HashMap<String, ActiveMQ> mqMap = new HashMap<String, ActiveMQ>();

    private Registry registry;
    MsgBrokerRMI remoteObj;

    public MsgBrokerTester(String mode, String fileName) {
        srcFile = new File(fileName);
        if( !srcFile.exists() ) {
            System.out.println("Source file doesn't exist [" + fileName + "]");
        }
        this.mode = mode;
    }

    public void sendToMQ() {
        String line    = null;
        ByteBuffer buf = null;
        byte[] bMsgType = new byte[4];
        byte[] bWrkType = new byte[4];

        try {
            if( !srcFile.exists() ) return;
            scan = new Scanner(srcFile);
            ActiveMQ mq = null;
            if( mode.equals("P") ) {
                registry = LocateRegistry.getRegistry(MsgCommon.msgProps.getProperty("msgbroker.server.ip", "10.3.28.62"),
                                                      Integer.parseInt(MsgCommon.msgProps.getProperty("msgbroker.server.port","10199")));
                remoteObj = (MsgBrokerRMI)registry.lookup("MsgBrokerRMI");
            }

            while( scan.hasNext() ) {
                line = scan.nextLine();
                byte[] lineBytes = line.getBytes();
                buf = ByteBuffer.allocateDirect(lineBytes.length);
                buf.put(lineBytes);
                buf.position(51);
                buf.get(bMsgType);
                buf.get(bWrkType);
                buf.position(0);
                //System.out.println("msgType = " + new String(bMsgType));
                if( mode.equals("I") ) {
                    if( bMsgType[2] == '1')
                        continue;
                }
                else if( mode.equals("P") ) {
                    if( bMsgType[2] == '1')
                        continue;
                }
                else if( mode.equals("R") ) {
                    if( bMsgType[2] == '0')
                        continue;
                    bMsgType[2] = '0';
                }
                if( mode.equals("I") || mode.equals("R") ) {
                    String qName = String.format("%s.%s", new String(bMsgType), new String(bWrkType));
                    mq = mqMap.get(qName);
                    if( mq == null ) {
                        try {
                            mq = new ActiveMQ(MsgCommon.msgProps.getProperty("consumer.host"), qName, null);
                        }
                        catch( Exception e ) {
                            System.out.println("Error raised..: It couldn't be found MQ." + e.getMessage());
                            continue;
                        }
                        mqMap.put(qName, mq);
                    }
                    BytesMessage bm = mq.getBytesMessage();
                    bm.writeBytes(lineBytes);
                    mq.produce(bm);
                }
                else {
                    //RMI 호출
                    remoteObj.callBrokerAsync(lineBytes);
                }
                System.out.println(line);
            }
            scan.close();
            Iterator<Map.Entry<String, ActiveMQ>>  itr = mqMap.entrySet().iterator();
            while ( itr.hasNext() ){
                Map.Entry<String, ActiveMQ> e = (Map.Entry<String, ActiveMQ>)itr.next();
                e.getValue().close();
            }

            System.out.println("Finished..");
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if( args.length != 2) {
            System.out.println("Usage: java MsgBrokerTester [\"I\" - Incomming |\"P\" - ByPass | \"R\" - Response of ByPassing] [FileName]");
            return;
        }
        try {
            org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", MsgBrokerConst.SVR_TYPE)));
            InputStream is = MsgBrokerMain.class.getResourceAsStream(
                    String.format("/%s/msg.properties", MsgBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            new MsgBrokerTester(args[0], args[1]).sendToMQ();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

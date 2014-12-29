package com.nicetcm.nibsplus.broker.msg.util;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MsgBrokerMsgDisplay
 *
 *  MsgBroker 전문출력
 *
 *
 * @author  K.D.J
 * @since   2014.09.18
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerMain;

public class MsgBrokerMsgDisplay {

    private File srcFile;

    private BufferedReader rdr;

    public MsgBrokerMsgDisplay(String fileName) {
        srcFile = new File(fileName);
        if( !srcFile.exists() ) {
            System.out.println("Source file doesn't exist [" + fileName + "]");
        }
    }

    public void displayMessages() {
        String line    = null;
        ByteBuffer buf = null;
        byte[] bMsgType = new byte[4];
        byte[] bWrkType = new byte[4];

        try {
            if( !srcFile.exists() ) return;

            rdr = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile)));
            while((line = rdr.readLine()) != null) {
                byte[] lineBytes = line.getBytes();
                buf = ByteBuffer.allocateDirect(lineBytes.length);
                buf.put(lineBytes);
                buf.position(MsgBrokerConst.MSG_TYPE_OFS);
                buf.get(bMsgType);
                buf.get(bWrkType);
                buf.position(0);

                bMsgType[2] = '0';
                System.out.println("***********************************************************************************************");
                String qName = MsgCommon.msgProps.getProperty("schema_path") + new String(bMsgType).trim() + new String(bWrkType).trim() + ".json";
                System.out.println(qName);
                MsgParser msgPsr = MsgParser.getInstance(qName).parseMessage(buf);
                System.out.println("parse OK");
                try {
                    msgPsr.printMsgData("S");
                }
                finally {
                    msgPsr.clearMessage();
                }
            }
            rdr.close();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if( args.length != 1) {
            System.out.println("Usage: java MsgBrokerMsgDisplay [FileName]");
            return;
        }
        try {
            org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", MsgBrokerConst.SVR_TYPE)));
            InputStream is = MsgBrokerMain.class.getResourceAsStream(
                    String.format("/%s/msg.properties", MsgBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            new MsgBrokerMsgDisplay(args[0]).displayMessages();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

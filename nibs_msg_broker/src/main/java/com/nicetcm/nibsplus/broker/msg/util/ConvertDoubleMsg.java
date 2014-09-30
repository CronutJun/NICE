package com.nicetcm.nibsplus.broker.msg.util;

import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerMain;

public class ConvertDoubleMsg {

    private File srcFile;
    private File destFile;
    private BufferedReader rdr;
    private MsgParser sMsgPsr;
    private MsgParser dMsgPsr;

    public ConvertDoubleMsg(String srcFileNm, String destFileNm) {

        System.out.println("srcFileNm [" + srcFileNm + "], destFileNm [" + destFileNm + "]");
        srcFile = new File(srcFileNm);
        if( !srcFile.exists() ) {
            System.out.println("Source file doesn't exist [" + srcFileNm + "]");
        }
        destFile = new File(destFileNm);

    }

    public void convert() {
        String line    = null;
        ByteBuffer srcBuf = null;
        byte[] bMsgType = new byte[4];
        byte[] bWrkType = new byte[4];
        String inQNm;
        try {
            rdr = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), Charset.forName("MS949")));
            while((line = rdr.readLine()) != null) {
                System.out.println(line);
                byte[] lineBytes = line.getBytes("MS949");
                srcBuf = ByteBuffer.allocateDirect(lineBytes.length);
                srcBuf.put(lineBytes);
                srcBuf.position(51);
                srcBuf.get(bMsgType);
                srcBuf.get(bWrkType);
                srcBuf.position(0);
                if( bMsgType[2] == '1')
                    bMsgType[2] = '0';

                inQNm = MsgCommon.msgProps.getProperty("schema_org_path") + new String(bMsgType) + new String(bWrkType) + ".json";
                System.out.println("Source QNm = " + inQNm);

                sMsgPsr = MsgParser.getInstance(inQNm).parseMessage(srcBuf);

                inQNm = MsgCommon.msgProps.getProperty("schema_path") + new String(bMsgType) + new String(bWrkType) + ".json";
                System.out.println("Dest QNm = " + inQNm);
                dMsgPsr = MsgParser.getInstance(inQNm);
            }
            rdr.close();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if( args.length != 2) {
            System.out.println("Usage: java ConvertDoubleMsg [SrcFile] [DestFile]");
            return;
        }
        try {
            org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", MsgBrokerConst.SVR_TYPE)));
            InputStream is = MsgBrokerMain.class.getResourceAsStream(
                    String.format("/%s/msg.properties", MsgBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            new ConvertDoubleMsg(args[0], args[1]).convert();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

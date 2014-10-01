package com.nicetcm.nibsplus.broker.msg.util;

import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Map.Entry;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.common.MsgData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerMain;

public class ConvertDoubleMsg {

    private File srcFile;
    private File destFile;
    private BufferedReader rdr;
    private FileOutputStream wdr;
    private MsgParser sMsgPsr;

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
        ByteBuffer destBuf = null;
        byte[] bMsgType = new byte[4];
        byte[] bWrkType = new byte[4];
        byte blf[] = new byte[1];
        blf[0] = 0x0A;
        String inQNm;
        try {
            rdr = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), Charset.forName("EUC-KR")));
            wdr = new FileOutputStream(destFile);

            while((line = rdr.readLine()) != null) {
                System.out.println(line);
                byte[] lineBytes = line.getBytes("EUC-KR");
                srcBuf = ByteBuffer.allocateDirect(lineBytes.length);
                destBuf = ByteBuffer.allocateDirect(lineBytes.length * 2);
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
                destBuf.position(0);
                for(Entry<String, MsgData> entry: sMsgPsr.getAllFields().entrySet()) {
                    byte cData[] = new byte[entry.getValue().length * 2];
                    String fmt = String.format("%%-%ds", cData.length);
                    String sData = String.format(fmt, new String(sMsgPsr.getBytes(entry.getKey()), "MS949"));
                    System.arraycopy(sData.getBytes(), 0, cData, 0,
                            sData.getBytes().length > cData.length ? cData.length : sData.getBytes().length);
                    destBuf.put(cData);
                }
                destBuf.position(0);
                byte dData[] = new byte[destBuf.limit()];
                destBuf.get(dData);
                System.out.println("Data = " + new String(dData));
                wdr.write(dData);
                wdr.write(blf);
            }
            wdr.close();
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
        if( args[0].equals(args[1]) ) {
            System.out.println("Source file name and Destination file name is same.");
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

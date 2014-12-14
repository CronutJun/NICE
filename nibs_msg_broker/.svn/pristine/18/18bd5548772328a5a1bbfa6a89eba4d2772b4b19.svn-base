package com.nicetcm.nibsplus.broker.msg.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerMain;

public class BytesLengthChecker {

    private File srcFile;
    private BufferedReader rdr;
    private String charSet;

    public BytesLengthChecker(String fileName, String charSet) {
        srcFile = new File(fileName);
        if( !srcFile.exists() ) {
            System.out.println("File doesn't exist [" + fileName + "]");
        }
        if( charSet.length() == 0 )
            this.charSet = "UTF-8";
        else {
            if( charSet.equals("e") )
                this.charSet = "EUC-KR";
            else
                this.charSet = charSet;
        }
    }

    public void checkLength() {
        String line    = null;
        try {
            rdr = new BufferedReader(new InputStreamReader(new FileInputStream(srcFile), Charset.forName(charSet)));
            while((line = rdr.readLine()) != null) {
                byte[] lineBytes = line.getBytes(charSet);
                System.out.println(
                        String.format("Length = %5d,  Data = [%-90s]", lineBytes.length, line.substring(0, 90))
                    );
            }
            rdr.close();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        if( args.length > 2 || args.length < 1) {
            System.out.println("Usage: java BytesLengthChecker [FileName] [CharSet]");
            return;
        }
        try {
            org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", MsgBrokerConst.SVR_TYPE)));
            InputStream is = MsgBrokerMain.class.getResourceAsStream(
                    String.format("/%s/msg.properties", MsgBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            if( args.length == 2 )
                new BytesLengthChecker(args[0], args[1]).checkLength();
            else
                new BytesLengthChecker(args[0], "UTF-8").checkLength();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }
}

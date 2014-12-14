package com.nicetcm.nibsplus.broker.msg.util;

import java.io.FileReader;
import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonObject;
import javax.json.JsonArray;
import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerMain;

public class DiscoverSchema {

    private String fileName;

    public DiscoverSchema(String fileName) {
        this.fileName = fileName;
    }

    public void list() {
        try {
            JsonReader rdr = Json.createReader(new FileReader(fileName));
            JsonObject obj = rdr.readObject();
            JsonArray  arr = obj.getJsonArray("columns");
            for( JsonObject result: arr.getValuesAs(JsonObject.class) ) {
                System.out.println(result.getString("name"));
            }
            rdr.close();
        }
        catch( Exception e ) {
            System.out.println("FileName: " + this.fileName);
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        if( args.length != 1) {
            System.out.println("Usage: java DiscoverSchema [FileName]");
            return;
        }
        try {
            org.apache.log4j.xml.DOMConfigurator.configure(MsgBrokerMain.class.getResource(
                    String.format("/%s/log4j.xml", MsgBrokerConst.SVR_TYPE)));
            InputStream is = MsgBrokerMain.class.getResourceAsStream(
                    String.format("/%s/msg.properties", MsgBrokerConst.SVR_TYPE));
            MsgCommon.msgProps.load(is);
            new DiscoverSchema(args[0]).list();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
    }

}

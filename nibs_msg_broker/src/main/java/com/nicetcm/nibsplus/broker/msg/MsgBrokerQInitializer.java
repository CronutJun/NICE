package com.nicetcm.nibsplus.broker.msg;

import java.io.InputStream;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonObject;
import javax.json.JsonArray;

import com.nicetcm.nibsplus.broker.common.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MsgBrokerQInitializer {
    
    private static final Logger logger = LoggerFactory.getLogger(MsgBrokerQInitializer.class);
    
    private static MsgBrokerQInitializer qinit = null;
    private JsonReader rdr;
    private JsonObject obj;
    
    private MsgBrokerQInitializer(String configFile) {
        InputStream is = MsgBrokerQInitializer.class.getResourceAsStream("/" + configFile);
        rdr = Json.createReader(is);
        obj = rdr.readObject();
    }
    
    public static MsgBrokerQInitializer getInstance(String configFile) throws Exception {
        if( qinit == null) {
            qinit = new MsgBrokerQInitializer(configFile);
        }
        return qinit;
    }
    
    public MsgBrokerQInitializer initConsumers() throws Exception {
        
        if( obj.containsKey("consumers")) {
            JsonArray arr = obj.getJsonArray("consumers");
            for (JsonObject elem : arr.getValuesAs(JsonObject.class)) {
                new MsgBrokerConsumer(MsgCommon.msgProps.getProperty("consumer.host"), elem.getString("name"), new MsgBrokerListener());
            }
        }
        return this;
    }

    public MsgBrokerQInitializer initProducers() throws Exception {
        
        if( obj.containsKey("producers")) {
            JsonArray arr = obj.getJsonArray("producers");
            for (JsonObject elem : arr.getValuesAs(JsonObject.class)) {
                MsgBrokerProducer.producers
                .put(elem.getString("name"),
                    new MsgBrokerProducer(MsgCommon.msgProps.getProperty("producer.host"), elem.getString("name")));
            }
            /*
             * NS 나이스 상태전문 Producers 
             */
            // MsgBrokerProducer.producers.put("N200.0120", new MsgBrokerProducer(MsgCommon.msgProps.getProperty("producer.host"), "N200.0120"));
            /*
             * ES 신한 통전문 중계 Producers
             */
            // MsgBrokerProducer.producers.put("0500.0110", new MsgBrokerProducer(MsgCommon.msgProps.getProperty("producer.host"), "0500.0110"));
        }
        return this;
    }
}

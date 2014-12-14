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
    private int defPrefetchSize = 0;
    private JsonReader rdr;
    private JsonObject obj;

    private MsgBrokerQInitializer(String configFile) {
        InputStream is = MsgBrokerQInitializer.class.getResourceAsStream(String.format("/%s/%s", MsgBrokerConst.SVR_TYPE, configFile));
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

        defPrefetchSize = Integer.parseInt(MsgCommon.msgProps.getProperty("consumer.prefetch.size", "1"));

        if( obj.containsKey("consumers")) {
            JsonArray arr = obj.getJsonArray("consumers");
            for (JsonObject elem : arr.getValuesAs(JsonObject.class)) {
                if( elem.getBoolean("disabled", false) )
                    continue;
                MsgBrokerConsumer.consumers
                .put(elem.getString("name"),
                    new MsgBrokerConsumer(MsgCommon.msgProps.getProperty("consumer.host"), elem.getString("name"), elem.getBoolean("exclusive", true), elem.getInt("prefetch_size", defPrefetchSize),
                                              new MsgBrokerListener(
                                                      elem.getInt("prefetch_size", defPrefetchSize),
                                                      elem.getInt("parallels", 0),
                                                      elem.getInt("msg_para_pos", 0),
                                                      elem.getInt("msg_para_len", 0),
                                                      elem.getBoolean("force_resp", false),
                                                      elem.getString("redirect_to", ""),
                                                      elem.getBoolean("no_resp", false)
                                              )
                                          )
                    );
            }
        }

        return this;
    }

    public MsgBrokerQInitializer initProducers() throws Exception {

        if( obj.containsKey("producers")) {
            JsonArray arr = obj.getJsonArray("producers");
            for (JsonObject elem : arr.getValuesAs(JsonObject.class)) {
                if( elem.getBoolean("disabled", false) )
                    continue;
                MsgBrokerProducer.producers
                .put(elem.getString("name"),
                    new MsgBrokerProducer(MsgCommon.msgProps.getProperty("producer.host"), elem.getString("name")));
            }
        }

        return this;
    }
}

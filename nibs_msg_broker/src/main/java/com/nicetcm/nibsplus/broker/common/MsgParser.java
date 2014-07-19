package com.nicetcm.nibsplus.broker.common;

import java.nio.*;

import javax.json.Json;
import javax.json.JsonReader;
import javax.json.JsonObject;
import javax.json.JsonArray;
//import javax.json.JsonStructure;


import java.util.*;
import java.util.concurrent.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class MsgParser {
    
    private static final Logger logger = LoggerFactory.getLogger(MsgParser.class);
    
    private static final Map<String, MsgParser> instPool = new HashMap<String, MsgParser>();
    
    private Map<String, MsgFmtRec> msgFmtMap;
    private Map<String, String> includeMap;
    private Map<Long, ThrData>   msgThrMap;
    
    private int schemaLength;
    
    public class ResponseInfo {
        private String code;
        private String type;
        private String schema;
        
        public String getCode(){
            return code;
        }
        public String getType(){
            return type;
        }
        public String getSchema(){
            return schema;
        }
    }
    private ResponseInfo resInf;
    
    private MsgParser(String incFile) throws Exception {
        
        msgFmtMap = new LinkedHashMap<String, MsgFmtRec>();
        includeMap = new HashMap<String, String>();
        msgThrMap = new ConcurrentHashMap<Long, ThrData>();
        resInf = new ResponseInfo();
        
        msgFmtMap.clear();
        includeMap.clear();
        
        schemaLength = 0;
        readSchema(incFile, msgFmtMap);
    }
    
    public static MsgParser getInstance(String incFile) throws Exception {
        synchronized (instPool) {
            if( instPool.containsKey(incFile) )
                return instPool.get(incFile);
            else {
                logger.debug("Creating Instance");
                MsgParser mp = new MsgParser(incFile);
                instPool.put( incFile, mp );
                return mp;
            }
        }
    }
        
    private void readSchema(String incFile, Map<String, MsgFmtRec> msgMap) throws Exception {
        
        if( includeMap.containsKey(incFile) )
            throw new Exception("reference is recursived.");
        
        includeMap.put(incFile,  incFile);
        
        //JsonReader rdr = Json.createReader(new FileReader(incFile));
        JsonReader rdr = Json.createReader(MsgParser.class.getResourceAsStream(incFile));
        JsonObject obj = rdr.readObject();
        
        if( obj.containsKey("response") ) {
            JsonObject res = obj.getJsonObject("response");
            if( res.containsKey("code") )
                resInf.code = res.getString("code");
            if( res.containsKey("type") )
                resInf.type = res.getString("type");
            if( res.containsKey("schema") )
                resInf.schema = res.getString("schema");
        }
        JsonArray results = obj.getJsonArray("columns");

        readSubSchema( results, msgMap );
    }
    
    private void readSubSchema(JsonArray arr, Map<String, MsgFmtRec> msgMap) throws Exception {

        MsgFmtRec msgFmtRec;
        
        for (JsonObject result : arr.getValuesAs(JsonObject.class)) {
            
            msgFmtRec            = new MsgFmtRec();
            msgFmtRec.name       = result.getString("name");
            msgFmtRec.type       = result.getString("type");
            
            if( result.containsKey("ref_iteration") ) {
                msgFmtRec.ref_iteration = result.getString("ref_iteration");
            }
            
            if( result.containsKey("ref_size") ) {
                msgFmtRec.ref_size = result.getString("ref_size");
            }
            
            if( result.containsKey("length") ) {
                msgFmtRec.length = result.getInt("length");
                schemaLength += msgFmtRec.length;
            }
            
            if( msgFmtRec.type.equals("STRUCT") ) {
                msgFmtRec.schema = new LinkedHashMap<String, MsgFmtRec>();
                readSubSchema(result.getJsonArray("struct"), msgFmtRec.schema);
            }
            
            if( msgFmtRec.type.equals("INC") ) {
                msgFmtRec.schema = new LinkedHashMap<String, MsgFmtRec>();
                readSchema(MsgCommon.msgProps.getProperty("schema_path") + result.getString("include"), msgFmtRec.schema);
            }
            
            msgMap.put(result.getString("name"), msgFmtRec);
        }
    }
    
    public int getSchemaLength() {
        return schemaLength;
    }
    
    public MsgParser parseMessage( ByteBuffer msg ) throws Exception {
        
        ThrData td;
        logger.debug("Thr Id = " + java.lang.Thread.currentThread().getId() );
        synchronized (msgThrMap) {
            if( msgThrMap.containsKey(java.lang.Thread.currentThread().getId()))
                td = msgThrMap.get(java.lang.Thread.currentThread().getId());
            else {
                td = new ThrData();
                msgThrMap.put(java.lang.Thread.currentThread().getId(), td);
            }
            td.pos = 0;
            td.isLive = true;
            td.msg = msg;
            td.msg.position(0);
            td.msgDatMap.clear();
            td.messageLength = 0;
        
            parseSubMessage( td, msgFmtMap, td.msgDatMap );
        
            td.msg.position(0);
        }
        
        return this;
    }
    
    public MsgParser parseMessage( ThrData td ) throws Exception {
        
        logger.debug("Thr Id = " + java.lang.Thread.currentThread().getId() );
        td.pos = 0;
        td.isLive = true;
        td.msg.position(0);
        td.msgDatMap.clear();
        td.messageLength = 0;
        
        parseSubMessage( td, msgFmtMap, td.msgDatMap );
        
        td.msg.position(0);
        
        return this;
    }
    
    private MsgParser parseSubMessage( ThrData td, Map<String, MsgFmtRec> msgMap, Map<String, MsgData> dataMap ) throws Exception {
        
        byte[] buf;
        int savePos, i;
        Map<String, MsgData> dMap;
        MsgData data;
        
        Set<Map.Entry<String, MsgFmtRec>> set = msgMap.entrySet();
        Iterator<Map.Entry<String, MsgFmtRec>>  itr = set.iterator();
        while ( itr.hasNext() ){
            Map.Entry<String, MsgFmtRec> e = (Map.Entry<String, MsgFmtRec>)itr.next();
            
            data = new MsgData();
            dataMap.put(e.getKey(), data);
            
            savePos = td.msg.position();
            if( e.getValue().ref_iteration.length() > 0) {
                data.iteration = getMsgData(dataMap, e.getValue().ref_iteration).getInt();
            }
            td.msg.position(savePos);
            
            if( data.iteration > 0 ) {
                for( i = 0; i < data.iteration; i++) {
                    
                    if( e.getValue().schema != null ){
                        dMap = new LinkedHashMap<String, MsgData>();
                        data.adata.add(dMap);
                        parseSubMessage( td, e.getValue().schema, dMap );
                    }
                    else {
                        logger.debug("e.getKey() " + e.getKey());
                        dMap = new LinkedHashMap<String, MsgData>();
                        data.adata.add(dMap);
                        dMap.put(e.getKey(), new MsgData());
                        
                        data.pos = td.pos;
                        dMap.get(e.getKey()).refFmt = e.getValue();
                        dMap.get(e.getKey()).refMap = dMap;
                        dMap.get(e.getKey()).name   = e.getKey();
                        dMap.get(e.getKey()).pos    = td.pos;

                        savePos = td.msg.position();
                        if( e.getValue().ref_size.length() > 0) {
                            dMap.get(e.getKey()).length  
                                =  getMsgData(dMap, e.getValue().ref_size).getInt();
                        }
                        else {
                            dMap.get(e.getKey()).length =  e.getValue().length;
                        }
                        td.msg.position(savePos);
                    
                        buf = new byte[dMap.get(e.getKey()).length];
                        logger.debug("Name = " + e.getKey() + ", Type = " + e.getValue().type + ", Pos  = " 
                                + dMap.get(e.getKey()).pos
                                + ", Len  = " + dMap.get(e.getKey()).length + ", idx = " + i);
                        try {
                            td.msg.get(buf);
                        }
                        catch( Exception err ) {
                            if( err instanceof BufferUnderflowException )
                                throw new MsgParseException("Array parse error.");
                            else throw err;
                        }
                        td.pos += dMap.get(e.getKey()).length;
                        td.messageLength += dMap.get(e.getKey()).length;

                        dMap.get(e.getKey()).setString(new String(buf));
                    
                        logger.debug("Value [" + dMap.get(e.getKey()).getString() + "]");
                        logger.debug("Position = " + td.msg.position() + ", Capacity = " + td.msg.capacity() + ", limit = " + td.msg.limit());
                        logger.debug("----------------------------------------------------");
                    }
                }
            }
            else {
                data.refFmt = e.getValue();
                data.refMap = dataMap;
                data.name = e.getKey();
                data.pos = td.pos;
                
                savePos = td.msg.position();
                if( e.getValue().ref_size.length() > 0) {
                    data.length = getMsgData(dataMap, e.getValue().ref_size).getInt();
                }
                else {
                    data.length = e.getValue().length;
                }
                td.msg.position(savePos);
                
                if( e.getValue().ref_iteration.length() == 0) { /* ���� struct ���翩�� */
                    if( e.getValue().schema != null ) {
                        dMap = new LinkedHashMap<String, MsgData>();
                        data.adata.add(dMap);
                        parseSubMessage( td, e.getValue().schema, dMap );
                    }
                    else {        
                        buf = new byte[data.length];
                    
                        logger.debug("Name = " + e.getKey() + ", Type = " + e.getValue().type + ", Pos  = " 
                                + data.pos
                                + ", Len  = " 
                                + data.length );
                        try {
                            td.msg.get(buf);
                        }
                        catch (Exception err) {
                            if( err instanceof BufferUnderflowException )
                                throw new MsgParseException("Field parse error.");
                            else throw err;
                        }
                        td.pos += data.length;
                        td.messageLength += data.length;

                        data.setString(new String(buf));
                
                        logger.debug("Value [" + dataMap.get(e.getKey()).getString() + "]");
                        logger.debug("Position = " + td.msg.position() + ", Capacity = " + td.msg.capacity() + ", limit = " + td.msg.limit());
                        logger.debug("----------------------------------------------------");
                    }
                }
            }
        }
        
        return this;
    }

    public int getMessageLength() {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return td.messageLength;
    }
    
    public void clearMessage() throws Exception {
        synchronized (msgThrMap) {
            msgThrMap.remove(java.lang.Thread.currentThread().getId());
        }
    }
    
    public  MsgParser newMessage( ByteBuffer msg ) throws Exception {
        
        ThrData td;
        
        synchronized ( msgThrMap ) {
            if( msgThrMap.containsKey(java.lang.Thread.currentThread().getId()))
                td = msgThrMap.get(java.lang.Thread.currentThread().getId());
            else {
                td = new ThrData();
                msgThrMap.put(java.lang.Thread.currentThread().getId(), td);
            }
        
            td.pos = 0;
            td.isLive = false;
            td.msg = msg;
            td.msg.position(0);
            td.msgDatMap.clear();
            td.messageLength = 0;

            parseSubMessage( td, msgFmtMap, td.msgDatMap );
        
            td.msg.position(0);
        }
        return this;
    }
    
    public  MsgParser newMessage( ThrData td ) throws Exception {
        
        td.pos = 0;
        td.isLive = false;
        td.msg.position(0);
        td.msgDatMap.clear();
        td.messageLength = 0;

        parseSubMessage( td, msgFmtMap, td.msgDatMap );
        
        td.msg.position(0);
        
        return this;
    }
    
    public MsgData getField(Map<String, MsgData> msgData, String name) throws Exception {
       
        int didx = name.indexOf(".");
        int aoidx = name.indexOf("[");
        int acidx = name.indexOf("]");
        
        int idx = 0;
        String fname;
        
        if(didx > -1 && (didx < aoidx || didx < acidx)) throw new Exception("Field path syntax error : " + name);
        if(acidx < aoidx) throw new Exception("Field path syntax error " + name);
        
        if( didx > 0)
            fname = name.substring(0, didx).replace(".", "");
        else fname = name;
         
        if( aoidx > -1) {
            idx = Integer.parseInt(fname.substring(aoidx+1, acidx));
            fname = fname.substring(0, aoidx);
        }
        
        logger.debug("name = " + name + ", fname = " + fname + ", idx = " + idx);
        
        if( didx > 0 ) {
           return getField(msgData.get(fname).adata.get(idx), name.substring(didx+1));
        }
        else {
           if( aoidx > -1)
               return msgData.get(fname).adata.get(idx).get(fname);
           else
               return msgData.get(name);
        }
    }
    
    private MsgData getMsgData(Map<String, MsgData> msgData, String name) throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        if( msgData == null ) return td.msgDatMap.get(name);        
        else return msgData.get(name);
        
    }
    
    public byte[] getBytes(String name) throws Exception {
        
        logger.debug("Thr Id = " + java.lang.Thread.currentThread().getId());
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return getBytes(td, name);
    }
    
    public byte[] getBytes(ThrData td, String name) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive ) {
            byte[] data = new byte[md.length];
        
            td.msg.position(md.pos);
            td.msg.get(data);
        
            return data;
        }
        else {
            return md.getBytes();
        }
    }
        
    public MsgParser setBytes(String name, byte[] value) throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return setBytes(td, name, value);
        
    }
    
    public MsgParser setBytes(ThrData td, String name, byte[] value) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive) {
            String fmt = String.format("%%-%ds", md.length);
            byte[] data = new byte[md.length];
        
            System.arraycopy(value, 0, data, 0, 
                md.length > value.length ? value.length : md.length);
        
            String fmted = String.format(fmt, new String(data));

            td.msg.position(md.pos);
            td.msg.put(fmted.getBytes());
        }
        else {
            md.setBytes(value);
        }
        
        return this;
    }
    
    public String getString(String name) throws Exception {
        
        logger.debug("Thr Id = " + java.lang.Thread.currentThread().getId());
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return getString(td, name);
    }
    
    public String getString(ThrData td, String name) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive ) {
            byte[] data = new byte[md.length];
        
            td.msg.position(md.pos);
            td.msg.get(data);
        
            return new String(data).trim();
        }
        else {
            return md.getString();
        }
    }
    
    public MsgParser setString(String name, String value) throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return setString(td, name, value);
    }
    
    public MsgParser setString(ThrData td, String name, String value) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive) {
            String fmt = String.format("%%-%ds", md.length);
            byte[] data = new byte[md.length];
        
            System.arraycopy(value.getBytes(), 0, data, 0, 
                md.length > value.length() ? value.length() : md.length);
        
            String fmted = String.format(fmt, new String(data));

            td.msg.position(md.pos);
            td.msg.put(fmted.getBytes());
        }
        else {
            md.setString(value);
        }
        
        return this;
    }
    
    public short getShort(String name) throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return getShort(td, name);
    }
    
    public short getShort(ThrData td, String name) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive ) {
            byte[] data = new byte[md.length];
        
            td.msg.position(md.pos);
            td.msg.get(data);
            
            String conv = new String(data).trim();
            return Short.parseShort(conv.length() == 0 ? "0" : conv);
        }
        else {
            return md.getShort();
        }
    }
    
    public MsgParser setShort(String name, short value) throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return setShort(td, name, value);
    }
    
    public MsgParser setShort(ThrData td, String name, short value) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive) {
            String fmt = String.format("%%0%dd", md.length);
        
            String fmted = String.format(fmt, value);

            td.msg.position(md.pos);
            td.msg.put(fmted.getBytes());
        }
        else {
            md.setShort(value);
        }
        
        return this;
    }
    
    public int getInt(String name) throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return getInt(td, name);
    }
    
    public int getInt(ThrData td, String name) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive ) {
            byte[] data = new byte[md.length];
        
            td.msg.position(md.pos);
            td.msg.get(data);
            
            String conv = new String(data).trim();
            return Integer.parseInt(conv.length() == 0 ? "0" : conv);
        }
        else {
            return md.getInt();
        }
    }
    
    public MsgParser setInt(String name, int value) throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return setInt(td, name, value);
    }
    
    public MsgParser setInt(ThrData td, String name, int value) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive) {
            String fmt = String.format("%%0%dd", md.length);
        
            String fmted = String.format(fmt, value);

            td.msg.position(md.pos);
            td.msg.put(fmted.getBytes());
        }
        else {
            md.setInt(value);
        }
        
        return this;
    }
    
    public long getLong(String name) throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return getLong(td, name);
    }
    
    public long getLong(ThrData td, String name) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive ) {
            byte[] data = new byte[md.length];
        
            td.msg.position(md.pos);
            td.msg.get(data);
            
            String conv = new String(data).trim();
            return Long.parseLong(conv.length() == 0 ? "0" : conv);
        }
        else {
            return md.getLong();
        }
    }
    
    public MsgParser setLong(String name, long value) throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return setLong(td, name, value);
    }
    
    public MsgParser setLong(ThrData td, String name, long value) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive) {
            String fmt = String.format("%%0%dd", md.length);
        
            String fmted = String.format(fmt, value);

            td.msg.position(md.pos);
            td.msg.put(fmted.getBytes());
        }
        else {
            md.setLong(value);
        }
        
        return this;
    }
    
    public MsgParser addRow(String name) throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return addRow(td, name);
    }
    
    public MsgParser addRow(ThrData td, String name) throws Exception {
        
        MsgData md = getField(td.msgDatMap, name);
        
        if( td.isLive )
            throw new Exception("Parsed data can't be extended iterations..");
        else {
            md.addArrayData(false);
        }
        
        return this;
    }
    
    public MsgParser syncMessage() throws Exception {
        
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return syncMessage(td);
    }
    
    public MsgParser syncMessage(ThrData td) throws Exception {
        
        td.pos = 0;
        td.msg.position(0);
        td.messageLength = 0;
        
        syncSubMessage(td, td.msgDatMap);
        
        td.msg.position(0);

        return this;
    }
    
    private void syncSubMessage(ThrData thrData, Map<String, MsgData> mapData) throws Exception {

        String fmt, fmted;
        byte[] data;
        int i;
        MsgData aElem;
        
        Set<Map.Entry<String, MsgData>> set = mapData.entrySet();
        Iterator<Map.Entry<String, MsgData>>  itr = set.iterator();
        while ( itr.hasNext() ){
            Map.Entry<String, MsgData> e = (Map.Entry<String, MsgData>)itr.next();
            
            if( e.getValue().iteration > 0 ) {
                for( i = 0; i < e.getValue().iteration; i++) {
                    
                    if( e.getValue().refFmt.schema != null ){
                        syncSubMessage( thrData, e.getValue().adata.get(i) );
                    }
                    else {
                        aElem = e.getValue().adata.get(i).get(e.getKey());
                        if( aElem.refFmt.type.toUpperCase().equals("N") ) {
                            fmt = String.format("%%0%dd", aElem.length);
                            data = new byte[aElem.length];
                    
                            fmted = String.format(fmt, aElem.getInt());

                            thrData.msg.put(fmted.getBytes());
                            thrData.pos += aElem.length;
                        }
                        else {
                            fmt = String.format("%%-%ds", aElem.length);
                            data = new byte[aElem.length];
                    
                            System.arraycopy(aElem.getString().getBytes(), 0, data, 0, 
                                aElem.length > aElem.getString().length() 
                                ? aElem.getString().length() : aElem.length);
                    
                            fmted = String.format(fmt, new String(data));

                            thrData.msg.put(fmted.getBytes());
                            thrData.pos += aElem.length;
                        }
                        thrData.messageLength += aElem.length;
                    }
                }
            }
            else {
                if( e.getValue().refFmt.ref_iteration.length() == 0 ) { /* ���� struct */
                    if( e.getValue().refFmt.schema != null ) {
                        syncSubMessage( thrData, e.getValue().adata.get(0) );
                    }
                    else {
                        if( e.getValue().refFmt.type.toUpperCase().equals("N") ) {
                            fmt = String.format("%%0%dd", e.getValue().length);
                            data = new byte[e.getValue().length];
            
                            fmted = String.format(fmt, e.getValue().getInt());

                            thrData.msg.put(fmted.getBytes());
                            thrData.pos += e.getValue().length;
                        }
                        else {
                            fmt = String.format("%%-%ds", e.getValue().length);
                            data = new byte[e.getValue().length];
            
                            System.arraycopy(e.getValue().getString().getBytes(), 0, data, 0, 
                                    e.getValue().length > e.getValue().getString().length() 
                                    ? e.getValue().getString().length() : e.getValue().length);

                            logger.debug("Field = " + e.getKey() + ", Format = " + fmt + ", Length = " + e.getValue().length);
                            fmted = String.format(fmt, new String(data));

                            thrData.msg.put(fmted.getBytes());
                            thrData.pos += e.getValue().length;
                        }
                        thrData.messageLength += e.getValue().length;
                    }
                }
            }
        }
    }
    
    public int lastPosition() throws Exception {
        ThrData td = msgThrMap.get(java.lang.Thread.currentThread().getId());
        
        return lastPosition(td);
    }
    
    public int lastPosition(ThrData td) throws Exception {
        return td.pos;
    }
    
    public ResponseInfo getResponseInfo() throws Exception {
        
        return resInf;
        
    }
}

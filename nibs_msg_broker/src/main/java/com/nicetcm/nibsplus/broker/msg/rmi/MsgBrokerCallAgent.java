package com.nicetcm.nibsplus.broker.msg.rmi;

import java.util.List;

import com.nicetcm.nibsplus.broker.common.MsgParser;

public class MsgBrokerCallAgent <PT> {

    private String msgId;
    private PT params;
    private MsgBrokerCallBack callBack;
    
    public MsgBrokerCallAgent( String msgId, PT params, MsgBrokerCallBack callBack ) {
        
        this.msgId = msgId;
        this.params = params;
        this.callBack = callBack;
        
    }
    
    public void callBrokerSync() throws Exception {
        /*
         * 
         */
        MsgParser msgPsr = null;
        
        callBack.doPreCallBroker(msgPsr, params);
        
        //rmi.callBrokerSync();
        callBack.doPostCallBroker(msgPsr, params);
    }
    
    public void callBrokerAync() throws Exception {
        /*
         * 
         */
        MsgParser msgPsr = null;
        
        callBack.doPreCallBroker(msgPsr, params);
        
        //rmi.callBrokerAsync();
    }
}

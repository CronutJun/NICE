package com.nicetcm.nibsplus.broker.msg;

import java.util.List;
import java.util.ArrayList;

import com.nicetcm.nibsplus.broker.msg.rmi.*;
import com.nicetcm.nibsplus.broker.common.*;

public class CallAgentTest implements MsgBrokerCallBack<List<Object>> {
    
    public static void main(String[] args) throws Exception {
        
        /* User may write the code below at here */
        
        MsgBrokerCallAgent<List<Object>> agent = new MsgBrokerCallAgent<List<Object>>("03001100", new ArrayList<Object>(), new CallAgentTest());
        agent.callBrokerSync();
    }
    
    public void doPreCallBroker(MsgParser parsed, List<Object> params) throws Exception {
        System.out.println("do something before, List size = " + params.size());
    }
    
    public void doPostCallBroker(MsgParser parsed, List<Object> params) throws Exception {
        System.out.println("do something after, List size = " + params.size());
    }
    
}

package com.nicetcm.nibsplus.broker.msg.services;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;

public interface RespAckNakHandler {

    public void procAckNak( MsgBrokerData safeData, MsgParser parsed, int errorCode ) throws Exception;

}

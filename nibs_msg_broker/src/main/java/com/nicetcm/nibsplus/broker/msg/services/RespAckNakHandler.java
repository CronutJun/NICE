package com.nicetcm.nibsplus.broker.msg.services;

import com.nicetcm.nibsplus.broker.common.MsgParser;

public interface RespAckNakHandler {

    public void procAckNak( MsgParser parsed, int errorCode ) throws Exception;

}

package com.nicetcm.nibsplus.broker.msg.services;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;

public interface OutMsgHandler {

    public void outMsgHandle(MsgBrokerData safeData, MsgParser parsed) throws Exception;

}

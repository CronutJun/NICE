package com.nicetcm.nibsplus.broker.msg.services;

import com.nicetcm.nibsplus.broker.common.*;

public interface InMsgHandler {

    public void inMsgHandle(MsgParser parsed) throws Exception;
    
}

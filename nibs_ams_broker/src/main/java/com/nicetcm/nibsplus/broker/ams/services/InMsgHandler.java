package com.nicetcm.nibsplus.broker.ams.services;

import com.nicetcm.nibsplus.broker.common.*;

public interface InMsgHandler {

    public void inMsgHandle(MsgParser parsed, String fileLoc) throws Exception;
    
}

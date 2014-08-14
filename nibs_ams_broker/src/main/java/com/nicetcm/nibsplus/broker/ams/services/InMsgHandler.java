package com.nicetcm.nibsplus.broker.ams.services;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;

public interface InMsgHandler {

    public void inMsgHandle(AMSBrokerData safeData, MsgParser parsed, String fileLoc) throws Exception;

}

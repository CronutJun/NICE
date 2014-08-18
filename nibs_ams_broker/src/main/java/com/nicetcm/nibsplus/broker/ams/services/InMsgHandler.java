package com.nicetcm.nibsplus.broker.ams.services;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;

public interface InMsgHandler {

    public void inMsgHandle(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception;

}

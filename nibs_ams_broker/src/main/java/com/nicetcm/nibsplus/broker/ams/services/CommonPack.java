package com.nicetcm.nibsplus.broker.ams.services;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgHis;
import com.nicetcm.nibsplus.broker.common.MsgParser;

public interface CommonPack {

    public void insUpdMsg(AMSBrokerData safeData, AMSBrokerReqJob reqJob, TRmMsg msg, TRmMsgHis msgHis) throws Exception;
    public void insUpdMacEnv(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob) throws Exception;
}

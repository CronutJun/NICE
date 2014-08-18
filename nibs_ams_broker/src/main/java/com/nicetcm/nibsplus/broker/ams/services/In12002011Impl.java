package com.nicetcm.nibsplus.broker.ams.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("in12002011")
public class In12002011Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In12002011Impl.class);

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

    }

}

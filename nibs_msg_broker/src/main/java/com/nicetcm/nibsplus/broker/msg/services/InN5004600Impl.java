package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;

@Service("inN5004600")
public class InN5004600Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN5004600Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    
    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

    }
}

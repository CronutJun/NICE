package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;

@Service("in03001130")
public class In03001130Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03001130Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    
    @Override
    public void inMsgBizProc(MsgParser parsed) throws Exception {

    }
}

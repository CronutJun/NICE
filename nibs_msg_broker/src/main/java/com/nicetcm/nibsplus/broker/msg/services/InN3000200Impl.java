package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;

@Service("inN3000200")
public class InN3000200Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN3000200Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    
    @Override
    public void inMsgBizProc(MsgParser parsed) throws Exception {

    }
}

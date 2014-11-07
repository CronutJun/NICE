package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * 빈 항목
 *
 *
 *           2014. 10. 29    K.D.J.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmCashKioskMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmCashKiosk;

@Service("in03101120")
public class In03101120Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101120Impl.class);

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.KEB_CODE) ) {
            logger.warn("tot_msg_cnt = {}, msg_index = {}", parsed.getString("tot_msg_cnt"), parsed.getString("msg_index"));
            if( parsed.getInt("tot_msg_cnt") != parsed.getInt("msg_index") ) {
                safeData.setSkipAnswer(true);
            }
        }
    }//end method

}

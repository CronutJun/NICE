package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * In11001000Impl
 *
 *  POLL전문 수신처리
 *
 *
 * @author  K.D.J
 * @since   2014.08.26
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("in11001000")
public class In11001000Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In11001000Impl.class);

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

    }
}

package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * OutMsgHandler
 *
 *  전문별 요청전문 구성 인터페이스
 *
 *
 * @author  K.D.J
 * @since   2014.08.20
 */

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;

import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

public interface OutMsgHandler {

    public void outMsgHandle(MsgParser outMsg, AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo, TRmMsg msg) throws Exception;

}

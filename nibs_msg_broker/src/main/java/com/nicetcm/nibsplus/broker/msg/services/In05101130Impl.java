package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 기업은행 브랜드제휴 장애, 개국, 상태 (Outbound 응답 추가, 2014/11/19 KDJ)
 *
 * <pre>
 * MngES_SaveIBKBrandErrState
 * </pre>
 *
 *           2014. 07. 29    K.D.J.
 */

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorBasicMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasicSpec;

@Service("in05101130")
public class In05101130Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In05101130Impl.class);

    @Autowired private In05001130Impl in05001130;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        in05001130.inMsgBizProc(safeData, parsed);

    }
}

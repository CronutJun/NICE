package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker NICE 개별망 집계요청
 *
 * <pre>
 * MngNQ_NiceBNetCalc
 * </pre>
 *
 *           2014. 08. 01    K.D.J.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceTranMapper;
import com.nicetcm.nibsplus.broker.msg.model.BNetCalc;

@Service("inN5000300")
public class InN5000300Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN5000300Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnNiceTranMapper fnNiceTranMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        BNetCalc cond = new BNetCalc();
        BNetCalc rslt = null;

        cond.setOrgCd( parsed.getString("org_cd") );
        cond.setMacNo( parsed.getString("mac_no") );
        cond.setDealDate( parsed.getString("deal_date") );
        try {
            rslt = fnNiceTranMap.selectBNetCalc( cond );
            if( rslt == null ) {
                rslt = new BNetCalc();
            }
            else if( rslt.getDealDate().equals("ERROR") ) {
                throw new MsgBrokerException("Error Fired..", rslt.getCashCnt() );
            }
        }
        catch( Exception e ) {
            throw e;
        }
    }
}

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
        cond.setDealTimeType( parsed.getString("deal_time_type") );
        try {
            rslt = fnNiceTranMap.selectBNetCalc( cond );
            if( rslt == null ) {
                rslt = new BNetCalc();
            }
            else if( rslt.getDealDate().equals("ERROR") ) {
                throw new MsgBrokerException("Error raised..", rslt.getCashCnt() );
            }
        }
        catch( Exception e ) {
            throw e;
        }
        parsed.setInt ( "cash_cnt",              rslt.getCashCnt()            );
        parsed.setLong( "cash_amt",              rslt.getCashAmt()            );
        parsed.setInt ( "cash_cancel_cnt",       rslt.getCashCancelCnt()      );
        parsed.setLong( "cash_cancel_amt",       rslt.getCashCancelAmt()      );
        parsed.setInt ( "same_cnt",              rslt.getSameCnt()            );
        parsed.setLong( "same_amt",              rslt.getSameAmt()            );
        parsed.setInt ( "diff_cnt",              rslt.getDiffCnt()            );
        parsed.setLong( "diff_amt",              rslt.getDiffAmt()            );
        parsed.setInt ( "cash_svc_cnt",          rslt.getCashSvcCnt()         );
        parsed.setLong( "cash_svc_amt",          rslt.getCashSvcAmt()         );
        parsed.setInt ( "cash_in_cnt",           rslt.getCashInCnt()          );
        parsed.setLong( "cash_in_amt",           rslt.getCashInAmt()          );
        parsed.setLong( "cash_in_cancel_cnt",    rslt.getCashInCancelCnt()    );
        parsed.setLong( "cash_in_cancel_amt",    rslt.getCashInCancelAmt()    );
        parsed.setLong( "real_trade_amt",        rslt.getRealTradeAmt()       );
        parsed.setLong( "out_cust_fee_amt",      rslt.getOutCustFeeAmt()      );
        parsed.setLong( "same_cust_fee_amt",     rslt.getSameCustFeeAmt()     );
        parsed.setLong( "diff_cust_fee_amt",     rslt.getDiffCustFeeAmt()     );
        parsed.setInt ( "after_cash_cnt",        rslt.getAfterCashCnt()       );
        parsed.setLong( "after_cash_amt",        rslt.getAfterCashAmt()       );
        parsed.setInt ( "after_cash_cancel_cnt", rslt.getAfterCashCancelCnt() );
        parsed.setLong( "after_cash_cancel_amt", rslt.getAfterCashCancelAmt() );
        parsed.setInt ( "after_same_cnt",        rslt.getAfterSameCnt()       );
        parsed.setLong( "after_same_amt",        rslt.getAfterSameAmt()       );
    }
}

package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker CD-VAN 운영자금 청구
 *
 * <pre>
 * MngNQ_NICEOperFunds
 * </pre>
 *
 *           2014. 08. 04    K.D.J.
 */


import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnWrVanDemandMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemand;

@Service("inN5000110")
public class InN5000110Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN5000110Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnWrVanDemandMapper fnWrVanDemandMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TFnWrVanDemand cond = new TFnWrVanDemand();

        TFnWrVanDemand rslt = null;

        cond.setDemandDate( parsed.getString("demand_date") );
        cond.setOrgCd( parsed.getString("org_cd") );
        try {
            rslt = fnWrVanDemandMap.selectByPrimaryKey( cond );
            if( rslt == null ) {
                /*
                 *  모두 0을 채워 보낸다. 응답코드 '91'
                 */
                parsed.setString( "CM.ret_cd", "91" );
                logger.warn( "...해당 운영자금데이터 없음" );
                throw new MsgBrokerException( "...해당 운영자금데이터 없음", -91 );
            }
        }
        catch( Exception e ) {
            parsed.setString( "CM.ret_cd", "91" );
            logger.warn( ">>>  운영자금 파악 실패[{}]", e.getLocalizedMessage() );
            throw e;
        }

        String sMsgType = parsed.getString("CM.msg_type");

        parsed.setString    ( "start_date",      rslt.getStartDate()           )
              .setString    ( "end_date",        rslt.getEndDate()             )
              .setString    ( "use_days",        rslt.getUseDays()             )
              .setString    ( "calc_type",       "1"                           )
              .setBigInteger( "mac_cnt",         ((BigDecimal)rslt.getMacCnt()).toBigInteger()        )
              .setBigInteger( "pre_invent_amt",  ((BigDecimal)rslt.getPreInventAmt()).toBigInteger()  )
              .setBigInteger( "invent_amt",      ((BigDecimal)rslt.getInventAmt()).toBigInteger()     )
              .setBigInteger( "pre_demand_amt",  ((BigDecimal)rslt.getPreDemandAmt()).toBigInteger()  )
              .setBigInteger( "out_amt",         ((BigDecimal)rslt.getOutAmt()).toBigInteger()        )
              .setBigInteger( "out_cnt",         ((BigDecimal)rslt.getOutCnt()).toBigInteger()        )
              .setBigInteger( "dif_amt",         ((BigDecimal)rslt.getDifAmt()).toBigInteger()        )
              .setBigInteger( "yst_amt",         ((BigDecimal)rslt.getYstAmt()).toBigInteger()        )
              .setBigInteger( "return_amt",      ((BigDecimal)rslt.getReturnAmt()).toBigInteger()     )
              .setString    ( "out_rate",        rslt.getOutRate()                                    )
              .setString    ( "berate",          rslt.getBerate()                                     )
              .setBigInteger( "oper_amt",        ((BigDecimal)rslt.getOperAmt()).toBigInteger()       )
              .setBigInteger( "notend_amt",      ((BigDecimal)rslt.getNotendAmt()).toBigInteger()     )
              .setBigInteger( "ord_amt",         ((BigDecimal)rslt.getOrdAmt()).toBigInteger()        )
              .setBigInteger( "wkd_amt",         ((BigDecimal)rslt.getWkdAmt()).toBigInteger()        )
              .setBigInteger( "jan_amt",         ((BigDecimal)rslt.getJanAmt()).toBigInteger()        )
              .setBigInteger( "demand_amt",      ((BigDecimal)rslt.getDemandAmt()).toBigInteger()     )
              .setBigInteger( "oper_invent_amt", ((BigDecimal)rslt.getOperInventAmt()).toBigInteger() )
              .setBigInteger( "fee_amt",         ((BigDecimal)rslt.getFeeAmt()).toBigInteger()        );

        safeData.setKeepResData( false );

    }
}

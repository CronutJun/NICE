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

        cond.setDemandDate( parsed.getString("deman_date") );
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

        parsed.setString( "CM.ret_cd", "S" );
        parsed.setString( "CM.msg_type",   sMsgType.substring(0, 2) + MsgBrokerConst.ANS_CODE );
        parsed.setString( "start_date",      rslt.getStartDate()           )
              .setString( "end_date",        rslt.getEndDate()             )
              .setString( "use_days",        rslt.getUseDays()             )
              .setString( "calc_type",       "1"                           )
              .setLong  ( "mac_cnt",         (Long)rslt.getMacCnt()        )
              .setLong  ( "pre_invent_amt",  (Long)rslt.getPreInventAmt()  )
              .setLong  ( "invent_amt",      (Long)rslt.getInventAmt()     )
              .setLong  ( "pre_demand_amt",  (Long)rslt.getPreDemandAmt()  )
              .setLong  ( "out_amt",         (Long)rslt.getOutAmt()        )
              .setLong  ( "out_cnt",         (Long)rslt.getOutCnt()        )
              .setLong  ( "dif_amt",         (Long)rslt.getDifAmt()        )
              .setLong  ( "yst_amt",         (Long)rslt.getYstAmt()        )
              .setLong  ( "return_amt",      (Long)rslt.getReturnAmt()     )
              .setString( "out_rate",        rslt.getOutRate()             )
              .setString( "berate",          rslt.getBerate()              )
              .setLong  ( "oper_amt",        (Long)rslt.getOperAmt()       )
              .setLong  ( "notend_amt",      (Long)rslt.getNotendAmt()     )
              .setLong  ( "ord_amt",         (Long)rslt.getOrdAmt()        )
              .setLong  ( "wkd_amt",         (Long)rslt.getWkdAmt()        )
              .setLong  ( "jan_amt",         (Long)rslt.getJanAmt()        )
              .setLong  ( "demand_amt",      (Long)rslt.getDemandAmt()     )
              .setLong  ( "oper_invent_amt", (Long)rslt.getOperInventAmt() )
              .setLong  ( "fee_amt",         (Long)rslt.getFeeAmt()        );
    }
}

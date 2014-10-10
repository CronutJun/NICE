package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * 실시간 거래_키오스크(홈플러스)
 *
 * <pre>
 * MngCM_SaveRealTimeTradeKiosk
 * </pre>
 *
 *           2014. 10. 10    K.D.J.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnRealtimeTradeKioskMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnRealtimeTradeKiosk;

@Service("in03000212")
public class In03000212Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000212Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnRealtimeTradeKioskMapper rtkMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TFnRealtimeTradeKiosk fnRTK = new TFnRealtimeTradeKiosk();

        if( parsed.getInt("tot_bill_cnt") > 0 ) {
            for( int i = 0; i < parsed.getInt("tot_bill_cnt"); i++ ) {
                fnRTK.setDealDate   ( parsed.getString("deal_date")   );
                fnRTK.setOrgCd      ( parsed.getString("CM.org_cd")   );
                fnRTK.setBranchCd   ( parsed.getString("brch_cd")     );
                fnRTK.setMacNo      ( parsed.getString("mac_no")      );
                fnRTK.setAtmDealNo  ( parsed.getInt   ("atm_deal_no") );
                fnRTK.setTicketCd   ( parsed.getString(String.format("BI[%d].ticket_cd", i)) );
                fnRTK.setBillGubunCd( parsed.getString(String.format("BI[%d].bill_class_cd", i)) );
                fnRTK.setDealTime   ( parsed.getString("deal_time")   );
                fnRTK.setOutCnt     ( parsed.getInt   (String.format("BI[%d].cnt_type1", i)) ); /* 방출매수   */
                fnRTK.setCollectCnt ( parsed.getInt   (String.format("BI[%d].cnt_type2", i)) ); /* 회수매수   */
                fnRTK.setRemCnt     ( parsed.getInt   (String.format("BI[%d].cnt_type3", i)) ); /* 잔량매수   */
                fnRTK.setDealCnt    ( parsed.getInt   (String.format("BI[%d].cnt_type4", i)) ); /* 거래매수   */
                fnRTK.setUpdateDate ( MsgBrokerLib.toDate(parsed.getString("CM.trans_date"), "yyyyMMdd") );
                fnRTK.setUpdateUid  ( "online" );
                try {
                    if( rtkMap.updateByPrimaryKeySelective(fnRTK) == 0 ) {
                        rtkMap.insertSelective( fnRTK );
                    }
                }
                catch( Exception e ) {
                    logger.info("[T_FN_REALTIME_TRADE_KIOSK] DB Err [{}]", e.getLocalizedMessage() );
                    throw e;
                }
            }
        }
    }//end method

}

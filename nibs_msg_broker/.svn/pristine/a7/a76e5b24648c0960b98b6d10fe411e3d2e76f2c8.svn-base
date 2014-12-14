package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * 현재시재조회_키오스크(홈플러스)
 *
 * <pre>
 * MngCM_AP_SaveCurrentAmtKiosk
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
import com.nicetcm.nibsplus.broker.msg.mapper.TCmCashKioskMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmCashKiosk;

@Service("in03101122")
public class In03101122Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101122Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCmCashKioskMapper cashKioskMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmCashKiosk cashKiosk = new TCmCashKiosk();

        if( parsed.getString("send_type").equals("C") && parsed.getInt("tot_bill_cnt") > 0 ) {
            for( int i = 0; i < parsed.getInt("tot_bill_cnt"); i++ ) {
                cashKiosk.setCashDate   ( parsed.getString("inq_date")   );
                cashKiosk.setOrgCd      ( parsed.getString("CM.org_cd")   );
                cashKiosk.setBranchCd   ( parsed.getString("brch_cd")     );
                cashKiosk.setMacNo      ( parsed.getString("mac_no")      );
                cashKiosk.setCashType   ( "1" ); /* 현재시재 '1', 마감시재'2' */
                cashKiosk.setTicketCd   ( parsed.getString(String.format("BI[%d].ticket_cd", i)) );
                cashKiosk.setBillGubunCd( parsed.getString(String.format("BI[%d].bill_class_cd", i)) );
                cashKiosk.setCashTime   ( parsed.getString("inq_time")   );
                cashKiosk.setSendCnt    ( parsed.getInt   (String.format("BI[%d].cnt_type1", i)) ); /* 투입매수 */
                cashKiosk.setOutCnt     ( parsed.getInt   (String.format("BI[%d].cnt_type2", i)) ); /* 방출매수 */
                cashKiosk.setReturnCnt  ( parsed.getInt   (String.format("BI[%d].cnt_type3", i)) ); /* 회수매수 */
                cashKiosk.setRemCnt     ( parsed.getInt   (String.format("BI[%d].cnt_type4", i)) ); /* 잔량매수 */
                cashKiosk.setUpdateDate ( MsgBrokerLib.SysDateD(0) );
                cashKiosk.setUpdateUid  ( "online" );

                try {
                    if( cashKioskMap.updateByPrimaryKeySelective( cashKiosk ) == 0 ) {
                        cashKioskMap.insertSelective( cashKiosk );
                    }
                }
                catch( Exception e ) {
                    logger.warn("[T_CM_CASH_KIOSK] DB Err [{}]", e.getLocalizedMessage() );
                    throw e;
                }
                logger.warn( "[T_CM_CASH_KIOSK] SAVE OK" );
            }
        }

    }//end method

}

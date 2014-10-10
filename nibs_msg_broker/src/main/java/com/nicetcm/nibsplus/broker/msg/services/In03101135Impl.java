package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * 마감시재조회_키오스크(홈플러스)
 *
 * <pre>
 * MngCM_AP_SaveCloseAmtKiosk
 * </pre>
 *
 *           2014. 10. 10    K.D.J.
 */


import java.util.List;

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
import com.nicetcm.nibsplus.broker.msg.model.TCmCashKioskSpec;

@Service("in03101135")
public class In03101135Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101135Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCmCashKioskMapper cashKioskMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmCashKiosk cashKiosk = new TCmCashKiosk();

        TCmCashKioskSpec cashKioskSpec = new TCmCashKioskSpec();
        cashKioskSpec.createCriteria().andCashDateEqualTo( parsed.getString("inq_date")  )
                                      .andOrgCdEqualTo   ( parsed.getString("CM.org_cd") )
                                      .andBranchCdEqualTo( parsed.getString("branch_cd") )
                                      .andMacNoEqualTo   ( parsed.getString("mac_no")    )
                                      .andCashTypeEqualTo( "2" );
        try {
            List<TCmCashKiosk> cntChk = cashKioskMap.selectBySpec( cashKioskSpec );
            if( cntChk.size() > 0 ) {
                logger.info( "[MngCM_AP_SaveCloseAmtKiosk] 기 마감건 있음 [{}-{}-{}-{}]",
                    parsed.getString("inq_date"), parsed.getString("CM.org_cd"),
                    parsed.getString("branch_cd"), parsed.getString("mac_no") );
                return;
            }
        }
        catch( Exception e ) {
            logger.info( ">>> [T_CM_CASH_KIOSK] ERROR [{}]", e.getLocalizedMessage() );
            return;
        }

        if( parsed.getInt("tot_bill_cnt") > 0 ) {
            for( int i = 0; i < parsed.getInt("tot_bill_cnt"); i++ ) {
                cashKiosk.setCashDate   ( parsed.getString("inq_date")   );
                cashKiosk.setOrgCd      ( parsed.getString("CM.org_cd")   );
                cashKiosk.setBranchCd   ( parsed.getString("brch_cd")     );
                cashKiosk.setMacNo      ( parsed.getString("mac_no")      );
                cashKiosk.setCashType   ( "9" ); /* 현재시재 '1', 나이스 집계마감시재'2', 기기마감시재 '9' */
                cashKiosk.setTicketCd   ( parsed.getString(String.format("BI[%d].ticket_cd", i)) );
                cashKiosk.setBillGubunCd( parsed.getString(String.format("BI[%d].bill_class_cd", i)) );
                cashKiosk.setCashTime   ( parsed.getString("close_time")   );
                cashKiosk.setSendCnt    ( parsed.getInt   (String.format("BI[%d].cnt_type1", i)) ); /* 투입매수             */
                cashKiosk.setOutCnt     ( parsed.getInt   (String.format("BI[%d].cnt_type2", i)) ); /* 방출매수(호스트기준) */
                cashKiosk.setCloseCnt   ( parsed.getInt   (String.format("BI[%d].cnt_type3", i)) ); /* 마감매수(호스트기준) */
                cashKiosk.setMacOutCnt  ( parsed.getInt   (String.format("BI[%d].cnt_type4", i)) ); /* 방출매수(기기 기준)  */
                cashKiosk.setMacCloseCnt( parsed.getInt   (String.format("BI[%d].cnt_type5", i)) ); /* 마감매수(기기 기준)  */
                cashKiosk.setUpdateDate ( MsgBrokerLib.SysDateD(0) );
                cashKiosk.setUpdateUid  ( "online" );

                try {
                    cashKioskMap.insertSelective( cashKiosk );
                }
                catch( org.springframework.dao.DataIntegrityViolationException de ) {
                    logger.info( "[T_CM_CASH_KIOSK] INSERT Duplicate !! 이미 저장된 건이 있습니다. [{}]", de.getLocalizedMessage() );
                }
                catch( Exception e ) {
                    logger.info("[T_FN_REALTIME_TRADE_KIOSK] Insert Err [{}]", e.getLocalizedMessage() );
                    throw e;
                }
                logger.info( "[T_CM_CASH_KIOSK] SAVE OK" );
            }
        }

    }//end method

}

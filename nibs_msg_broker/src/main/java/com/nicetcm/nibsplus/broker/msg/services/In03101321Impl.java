package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * 일마감요청_키오스크(홈플러스)
 *
 * <pre>
 * MngCM_AP_SaveDayCloseKiosk
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
import com.nicetcm.nibsplus.broker.msg.mapper.TFnDayCloseKioskMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseKiosk;

@Service("in03101321")
public class In03101321Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101321Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnDayCloseKioskMapper dckMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TFnDayCloseKiosk fnDCK = new TFnDayCloseKiosk();

        if( parsed.getInt("tot_bill_cnt") > 0 ) {
            for( int i = 0; i < parsed.getInt("tot_bill_cnt"); i++ ) {
                fnDCK.setCloseDate  ( parsed.getString("close_date")  );
                fnDCK.setOrgCd      ( parsed.getString("CM.org_cd")   );
                fnDCK.setBranchCd   ( parsed.getString("brch_cd")     );
                fnDCK.setMacNo      ( parsed.getString("mac_no")      );
                fnDCK.setCloseType  ( "ORG"                           ); /* 마감구분 'ORG':기관, ('NICE':나이스집계->프로시져에서 넣는다. SP_FN_DAY_CLOSE_KIOSK) */
                fnDCK.setTicketCd   ( parsed.getString(String.format("BI[%d].ticket_cd", i)) );
                fnDCK.setBillGubunCd( parsed.getString(String.format("BI[%d].bill_class_cd", i)) );
                fnDCK.setCloseTime  ( parsed.getString("close_time")  );
                fnDCK.setOutCnt     ( parsed.getInt   (String.format("BI[%d].cnt_type1", i)) ); /* 방출매수   */
                fnDCK.setUpdateDate ( MsgBrokerLib.SysDateD(0) );
                fnDCK.setUpdateUid  ( "online" );
                try {
                    if( dckMap.updateByPrimaryKeySelective(fnDCK) == 0 ) {
                        dckMap.insertSelective( fnDCK );
                    }
                }
                catch( Exception e ) {
                    logger.info("[T_FN_DAY_CLOSE_KIOSK] DB Err [{}]", e.getLocalizedMessage() );
                    throw e;
                }
            }
        }

    }//end method

}

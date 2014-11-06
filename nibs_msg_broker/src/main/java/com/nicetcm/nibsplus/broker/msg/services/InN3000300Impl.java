package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker NICE 점주관리 일마감
 * 
 * <pre>
 * MngNC_NiceJumjuDayClose
 * </pre>
 * 
 *           2014. 07. 31    K.D.J. 
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnStorekeeperDayCloseMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnStorekeeperDayClose;

@Service("inN3000300")
public class InN3000300Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN3000300Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnStorekeeperDayCloseMapper fnStorekeeperDayCloseMap;
    
    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        
        TFnStorekeeperDayClose fnStkprDayClose = new TFnStorekeeperDayClose();
        
        fnStkprDayClose.setOrgCd      ( parsed.getString("org_cd"       ) );
        fnStkprDayClose.setBranchCd   ( "9600"                            );
        fnStkprDayClose.setMacNo      ( parsed.getString("mac_no"       ) );
        fnStkprDayClose.setCloseDate  ( parsed.getString("close_date"   ) );
        fnStkprDayClose.setCashOutCnt ( parsed.getShort ("cash_out_cnt" ) );
        fnStkprDayClose.setCashOutAmt ( parsed.getLong  ("cash_out_amt" ) );
        fnStkprDayClose.setNotendCnt  ( parsed.getShort ("notend_cnt"   ) );
        fnStkprDayClose.setNotendAmt  ( parsed.getLong  ("notend_amt"   ) );
        fnStkprDayClose.setPartOutCnt ( parsed.getShort ("part_out_cnt" ) );
        fnStkprDayClose.setPartOutAmt ( parsed.getLong  ("part_out_amt" ) );
        fnStkprDayClose.setFieldPayAmt( parsed.getLong  ("field_pay_amt") );
        fnStkprDayClose.setInsertDate ( safeData.getDSysDate()            );
        fnStkprDayClose.setInsertUid  ( "DataMng"                         );
        fnStkprDayClose.setUpdateDate ( safeData.getDSysDate()            );
        fnStkprDayClose.setUpdateUid  ( "DataMng"                         );
        
        try {
            fnStorekeeperDayCloseMap.insert( fnStkprDayClose );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnStkprDayClose.setInsertDate( null );
                fnStkprDayClose.setInsertUid( null );
                fnStorekeeperDayCloseMap.updateByPrimaryKeySelective( fnStkprDayClose );
            }
            catch( Exception e ) {
                logger.warn( "[T_FN_JUMJU_DAY_CLOSE] UPDATE Err [{}-{}][{}]",
                        parsed.getString("close_date"), parsed.getString("mac_no"), e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( "[T_FN_JUMJU_DAY_CLOSE] INSERT Err [{}-{}][{}]",
                    parsed.getString("close_date"), parsed.getString("mac_no"), e.getLocalizedMessage() );
            throw e;
        }
    }
}

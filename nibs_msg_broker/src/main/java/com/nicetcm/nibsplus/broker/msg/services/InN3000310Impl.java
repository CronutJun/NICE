package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker NICE 점주관리 시재 장애내역
 *
 * <pre>
 * MngNC_NiceJumjuCashFault
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
import com.nicetcm.nibsplus.broker.msg.mapper.TCtStorekeeperCashFaultMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtStorekeeperCashFault;

@Service("inN3000310")
public class InN3000310Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN3000310Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCtStorekeeperCashFaultMapper ctStorekeeperCashFaultMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCtStorekeeperCashFault ctStkprCashFault = new TCtStorekeeperCashFault();

        ctStkprCashFault.setOrgCd      ( parsed.getString("CM.org_cd"    ) );
        ctStkprCashFault.setBranchCd   ( "9600"                            );
        ctStkprCashFault.setMacNo      ( parsed.getString("mac_no"       ) );
        ctStkprCashFault.setDealDate   ( parsed.getString("deal_date"    ) );
        ctStkprCashFault.setAtmDealNo  ( parsed.getString("atm_deal_no"  ) );
        ctStkprCashFault.setDealTime   ( parsed.getString("deal_time"    ) );
        ctStkprCashFault.setFaultGb    ( parsed.getString("fault_gb"     ) );
        ctStkprCashFault.setDealAmt    ( parsed.getLong  ("deal_amt"     ) );
        ctStkprCashFault.setPartOutAmt ( parsed.getLong  ("part_out_amt" ) );
        ctStkprCashFault.setFieldPayAmt( parsed.getLong  ("field_pay_amt") );
        ctStkprCashFault.setMadeErrorCd( parsed.getString("made_error_cd") );
        ctStkprCashFault.setInsertDate ( safeData.getDSysDate()            );
        ctStkprCashFault.setInsertUid  ( "DataMng"                         );
        ctStkprCashFault.setUpdateDate ( safeData.getDSysDate()            );
        ctStkprCashFault.setUpdateUid  ( "DataMng"                         );

        try {
            ctStorekeeperCashFaultMap.insertSelective( ctStkprCashFault );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                ctStkprCashFault.setInsertDate( null );
                ctStkprCashFault.setInsertUid( null );
                ctStorekeeperCashFaultMap.updateByPrimaryKeySelective( ctStkprCashFault );
            }
            catch( Exception e ) {
                logger.warn( "[T_CT_JUMJU_CASH_FAULT] UPDATE Err [{}-{}-{}][{}]",
                        parsed.getString("deal_date"), parsed.getString("mac_no"), parsed.getString("atm_deal_no"), e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e ) {
            logger.warn( "[T_CT_JUMJU_CASH_FAULT] INSERT Err [{}-{}-{}][{}]",
                    parsed.getString("deal_date"), parsed.getString("mac_no"), parsed.getString("atm_deal_no"), e.getLocalizedMessage() );
            throw e;
        }
    }
}

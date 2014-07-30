package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 집계처리 ROUTINE을 CALL
 * 
 * <pre>
 * MngNC_NiceMacClose
 * </pre>
 * 
 *           2014. 07. 30    K.D.J. 
 */

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceCloseOrgMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceCloseTmpMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseOrg;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseTmp;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseTmpSpec;

@Service("inN3000100")
public class InN3000100Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN3000100Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnNiceCloseOrgMapper fnNiceCloseOrgMap;
    @Autowired private TFnNiceCloseTmpMapper fnNiceCloseTmpMap;
    
    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

    }
    
    private void InsertNiceCloseOrgTicket( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        
        TFnNiceCloseOrg fnNCO = new TFnNiceCloseOrg();
        fnNCO.setMacNo               (  parsed.getString("mac_no"                ) );
        fnNCO.setCloseDate           (  parsed.getString("close_date"            ) );
        fnNCO.setCloseTime           (  parsed.getString("close_time"            ) );
        fnNCO.setTrack1EmitCnt       (  parsed.getInt   ("track1_emit_cnt"       ) );
        fnNCO.setTrack1BackCnt       (  parsed.getInt   ("track1_back_cnt"       ) );
        fnNCO.setTrack2EmitCnt       (  parsed.getInt   ("track2_emit_cnt"       ) );
        fnNCO.setTrack2BackCnt       (  parsed.getInt   ("track2_back_cnt"       ) );
        fnNCO.setTrack3EmitCnt       (  parsed.getInt   ("track3_emit_cnt"       ) );
        fnNCO.setTrack3BackCnt       (  parsed.getInt   ("track3_back_cnt"       ) );
        fnNCO.setTrack4EmitCnt       (  parsed.getInt   ("track4_emit_cnt"       ) );
        fnNCO.setTrack4BackCnt       (  parsed.getInt   ("track4_back_cnt"       ) );
        fnNCO.setTotEmitCnt          (  parsed.getInt   ("tot_emit_cnt"          ) );
        fnNCO.setTotEmitAmt          (  parsed.getLong  ("tot_emit_amt"          ) );
        fnNCO.setEmitCnt10000        (  parsed.getInt   ("emit_cnt_10000"        ) );
        fnNCO.setEmitCnt50000        (  parsed.getInt   ("emit_cnt_50000"        ) );
        fnNCO.setEmitCnt100000       (  parsed.getInt   ("emit_cnt_100000"       ) );
        fnNCO.setDepositCnt          (  parsed.getInt   ("deposit_cnt"           ) );
        fnNCO.setDepositAmt          (  parsed.getLong  ("deposit_amt"           ) );
        fnNCO.setServiceCnt          (  parsed.getInt   ("service_cnt"           ) );
        fnNCO.setServiceAmt          (  parsed.getLong  ("service_amt"           ) );
        fnNCO.setAbroadCnt           (  parsed.getInt   ("abroad_cnt"            ) );
        fnNCO.setAbroadAmt           (  parsed.getLong  ("abroad_amt"            ) );
        fnNCO.setEtcCnt              (  parsed.getInt   ("etc_cnt"               ) );
        fnNCO.setEtcAmt              (  parsed.getLong  ("etc_amt"               ) );
        fnNCO.setCashNotTakenCnt     (  parsed.getInt   ("cash_not_taken_cnt"    ) );
        fnNCO.setCashNotTakenAmt     (  parsed.getLong  ("cash_not_taken_amt"    ) );
        fnNCO.setUndeterminedBillsCnt(  parsed.getInt   ("undetermined_bills_cnt") );
        fnNCO.setUndeterminedBillsAmt(  parsed.getLong  ("undetermined_bills_amt") );
        fnNCO.setSendFailsCnt        (  parsed.getInt   ("send_fails_cnt"        ) );
        fnNCO.setSentFailsAmt        (  parsed.getLong  ("send_fails_amt"        ) );
        fnNCO.setTimeOutCnt          (  parsed.getInt   ("time_out_cnt"          ) );
        fnNCO.setTimeOutAmt          (  parsed.getLong  ("time_out_amt"          ) );
        fnNCO.setInsertDate          (  safeData.getDSysDate()                     );
        fnNCO.setInsertUid           (  parsed.getString("CM.msg_id"             ) );
        fnNCO.setTotInCnt            (  parsed.getInt   ("tot_in_cnt"            ) );
        fnNCO.setTotInAmt            (  parsed.getLong  ("tot_in_amt"            ) );
        fnNCO.setInCnt10000          (  parsed.getInt   ("in_cnt_10000"          ) );
        fnNCO.setInCnt50000          (  parsed.getInt   ("in_cnt_50000"          ) );
        fnNCO.setInCnt100000         (  parsed.getInt   ("in_cnt_100000"         ) );
        fnNCO.setInAmtEtc            (  parsed.getLong  ("in_amt_etc"            ) );
        fnNCO.setCheckInCnt          (  parsed.getInt   ("check_in_cnt"          ) );
        fnNCO.setCheckInAmt          (  parsed.getLong  ("check_in_amt"          ) );
        fnNCO.setCheckOutCnt         (  parsed.getInt   ("check_out_cnt"         ) );
        fnNCO.setCheckOutAmt         (  parsed.getLong  ("check_out_amt"         ) );
        fnNCO.setTrackUseType        (  parsed.getString("track_use_type"        ) );
        try {
            fnNiceCloseOrgMap.insert( fnNCO );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            logger.info( "[T_FN_NICE_CLOSE_ORG] Duplication [{}][{}][{}] !!!",
                    parsed.getString("mac_no"), parsed.getString("close_date"), parsed.getString("close_time") );
            throw de;
        }
        catch( Exception e ) {
            logger.info( "[T_FN_NICE_CLOSE_ORG] Insert Error.. {}", e.getLocalizedMessage() );
            throw e;
        }
    }
    
    private int compCloseDate( String macNo, String chkDate ) {
        
        TFnNiceCloseTmp fnNCTCond = new TFnNiceCloseTmp();
        
        fnNCTCond.setCloseDate( chkDate );
        fnNCTCond.setMacNo( macNo );
        List<TFnNiceCloseTmp> rslt = null;
        try {
            rslt = fnNiceCloseTmpMap.selectByCond1( fnNCTCond );
            if( rslt.size() == 0 ) {
                return 1;
            }
        }
        catch( Exception e ) {
            logger.info( "CompCloseDate Error mac_no[{}] date[{}] => {}",
                    macNo, chkDate, e.getLocalizedMessage() );
            return -1;
        }
        if( chkDate.compareTo(rslt.get(0).getCloseDate()) > 0 ) {
            return 1;
        }
        else if ( chkDate.compareTo(rslt.get(0).getCloseDate()) == 0 ) {
            return 0;
        }
        else {
            logger.info( "CompCloseDate Error close_date[{}]", rslt.get(0).getCloseDate() );
            return -1;
        }
    }
    
    private void insertNiceCloseTmp( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        
        TFnNiceCloseTmp fnNCT = new TFnNiceCloseTmp();
        
        fnNCT.setMacNo               ( parsed.getString("mac_no"                ) );
        fnNCT.setCloseDate           ( parsed.getString("close_date"            ) );
        fnNCT.setCloseTime           ( parsed.getString("close_time"            ) );
        fnNCT.setTrack1EmitCnt       ( parsed.getInt   ("track1_emit_cnt"       ) );
        fnNCT.setTrack1BackCnt       ( parsed.getInt   ("track1_back_cnt"       ) );
        fnNCT.setTrack2EmitCnt       ( parsed.getInt   ("track2_emit_cnt"       ) );
        fnNCT.setTrack2BackCnt       ( parsed.getInt   ("track2_back_cnt"       ) );
        fnNCT.setTrack3EmitCnt       ( parsed.getInt   ("track3_emit_cnt"       ) );
        fnNCT.setTrack3BackCnt       ( parsed.getInt   ("track3_back_cnt"       ) );
        fnNCT.setTrack4EmitCnt       ( parsed.getInt   ("track4_emit_cnt"       ) );
        fnNCT.setTrack4BackCnt       ( parsed.getInt   ("track4_back_cnt"       ) );
        fnNCT.setTotEmitCnt          ( parsed.getInt   ("tot_emit_cnt"          ) );
        fnNCT.setTotEmitAmt          ( parsed.getLong  ("tot_emit_amt"          ) );
        fnNCT.setEmitCnt10000        ( parsed.getInt   ("emit_cnt_10000"        ) );
        fnNCT.setEmitCnt50000        ( parsed.getInt   ("emit_cnt_50000"        ) );
        fnNCT.setEmitCnt100000       ( parsed.getInt   ("emit_cnt_10000"        ) );
        fnNCT.setDepositCnt          ( parsed.getInt   ("deposit_cnt"           ) );
        fnNCT.setDepositAmt          ( parsed.getLong  ("deposit_amt"           ) );
        fnNCT.setServiceCnt          ( parsed.getInt   ("service_cnt"           ) );
        fnNCT.setServiceAmt          ( parsed.getLong  ("service_amt"           ) );
        fnNCT.setAbroadCnt           ( parsed.getInt   ("abroad_cnt"            ) );
        fnNCT.setAbroadAmt           ( parsed.getLong  ("abroad_amt"            ) );
        fnNCT.setEtcCnt              ( parsed.getInt   ("etc_cnt"               ) );
        fnNCT.setEtcAmt              ( parsed.getLong  ("etc_amt"               ) );
        fnNCT.setCashNotTakenCnt     ( parsed.getInt   ("cash_not_taken_cnt"    ) );
        fnNCT.setCashNotTakenAmt     ( parsed.getLong  ("cash_not_taken_amt"    ) );
        fnNCT.setUndeterminedBillsCnt( parsed.getInt   ("undetermined_bills_cnt") );
        fnNCT.setUndeterminedBillsAmt( parsed.getLong  ("undetermined_bills_amt") );
        fnNCT.setSendFailsCnt        ( parsed.getInt   ("send_fails_cnt"        ) );
        fnNCT.setSentFailsAmt        ( parsed.getLong  ("send_fails_amt"        ) );
        fnNCT.setTimeOutCnt          ( parsed.getInt   ("time_out_cnt"          ) );
        fnNCT.setTimeOutAmt          ( parsed.getLong  ("time_out_amt"          ) );
        fnNCT.setInsertDate          ( safeData.getDSysDate()                     );
        fnNCT.setInsertUid           ( parsed.getString("CM.msg_id"             ) );
        fnNCT.setTotInCnt            ( parsed.getInt   ("tot_in_cnt"            ) );
        fnNCT.setTotInAmt            ( parsed.getLong  ("tot_in_amt"            ) );
        fnNCT.setInCnt10000          ( parsed.getInt   ("in_cnt_10000"          ) );
        fnNCT.setInCnt50000          ( parsed.getInt   ("in_cnt_50000"          ) );
        fnNCT.setInCnt100000         ( parsed.getInt   ("in_cnt_100000"         ) );
        fnNCT.setInAmtEtc            ( parsed.getLong  ("in_amt_etc"            ) );
        fnNCT.setCheckInCnt          ( parsed.getInt   ("check_in_cnt"          ) );
        fnNCT.setCheckInAmt          ( parsed.getLong  ("check_in_amt"          ) );
        fnNCT.setCheckOutCnt         ( parsed.getInt   ("check_out_cnt"         ) );
        fnNCT.setCheckOutAmt         ( parsed.getLong  ("check_out_amt"         ) );
        fnNCT.setTrackUseType        ( parsed.getString("track_use_type"        ) );
        
        try {
            fnNiceCloseTmpMap.insert( fnNCT );
        }
        catch( Exception e ) {
            logger.info( "[T_FN_NICE_CLOSE_TMP] Insert Error.. {}", e.getLocalizedMessage() );
            throw e;
        }

    }
    
    private void insertNiceCloseSum( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        
        TFnNiceCloseTmp cond = new TFnNiceCloseTmp();
        
        cond.setMacNo               ( parsed.getString("mac_no"                ) );
        cond.setCloseDate           ( parsed.getString("close_date"            ) );
        cond.setCloseTime           ( parsed.getString("close_time"            ) );
        cond.setTrack1EmitCnt       ( parsed.getInt   ("track1_emit_cnt"       ) );
        cond.setTrack1BackCnt       ( parsed.getInt   ("track1_back_cnt"       ) );
        cond.setTrack2EmitCnt       ( parsed.getInt   ("track2_emit_cnt"       ) );
        cond.setTrack2BackCnt       ( parsed.getInt   ("track2_back_cnt"       ) );
        cond.setTrack3EmitCnt       ( parsed.getInt   ("track3_emit_cnt"       ) );
        cond.setTrack3BackCnt       ( parsed.getInt   ("track3_back_cnt"       ) );
        cond.setTrack4EmitCnt       ( parsed.getInt   ("track4_emit_cnt"       ) );
        cond.setTrack4BackCnt       ( parsed.getInt   ("track4_back_cnt"       ) );
        cond.setTotEmitCnt          ( parsed.getInt   ("tot_emit_cnt"          ) );
        cond.setTotEmitAmt          ( parsed.getLong  ("tot_emit_amt"          ) );
        cond.setEmitCnt10000        ( parsed.getInt   ("emit_cnt_10000"        ) );
        cond.setEmitCnt50000        ( parsed.getInt   ("emit_cnt_50000"        ) );
        cond.setEmitCnt100000       ( parsed.getInt   ("emit_cnt_10000"        ) );
        cond.setDepositCnt          ( parsed.getInt   ("deposit_cnt"           ) );
        cond.setDepositAmt          ( parsed.getLong  ("deposit_amt"           ) );
        cond.setServiceCnt          ( parsed.getInt   ("service_cnt"           ) );
        cond.setServiceAmt          ( parsed.getLong  ("service_amt"           ) );
        cond.setAbroadCnt           ( parsed.getInt   ("abroad_cnt"            ) );
        cond.setAbroadAmt           ( parsed.getLong  ("abroad_amt"            ) );
        cond.setEtcCnt              ( parsed.getInt   ("etc_cnt"               ) );
        cond.setEtcAmt              ( parsed.getLong  ("etc_amt"               ) );
        cond.setCashNotTakenCnt     ( parsed.getInt   ("cash_not_taken_cnt"    ) );
        cond.setCashNotTakenAmt     ( parsed.getLong  ("cash_not_taken_amt"    ) );
        cond.setUndeterminedBillsCnt( parsed.getInt   ("undetermined_bills_cnt") );
        cond.setUndeterminedBillsAmt( parsed.getLong  ("undetermined_bills_amt") );
        cond.setSendFailsCnt        ( parsed.getInt   ("send_fails_cnt"        ) );
        cond.setSentFailsAmt        ( parsed.getLong  ("send_fails_amt"        ) );
        cond.setTimeOutCnt          ( parsed.getInt   ("time_out_cnt"          ) );
        cond.setTimeOutAmt          ( parsed.getLong  ("time_out_amt"          ) );
        cond.setTotInCnt            ( parsed.getInt   ("tot_in_cnt"            ) );
        cond.setTotInAmt            ( parsed.getLong  ("tot_in_amt"            ) );
        cond.setInCnt10000          ( parsed.getInt   ("in_cnt_10000"          ) );
        cond.setInCnt50000          ( parsed.getInt   ("in_cnt_50000"          ) );
        cond.setInCnt100000         ( parsed.getInt   ("in_cnt_100000"         ) );
        cond.setInAmtEtc            ( parsed.getLong  ("in_amt_etc"            ) );
        cond.setCheckInCnt          ( parsed.getInt   ("check_in_cnt"          ) );
        cond.setCheckInAmt          ( parsed.getLong  ("check_in_amt"          ) );
        cond.setCheckOutCnt         ( parsed.getInt   ("check_out_cnt"         ) );
        cond.setCheckOutAmt         ( parsed.getLong  ("check_out_amt"         ) );
        
        TFnNiceCloseTmp sum = null;
        try {
            sum = fnNiceCloseTmpMap.selectBySum1( cond );
            if( sum == null) {
                logger.info( "[T_FN_NICE_CLOSE_TMP] Select Sum is nothing" );
                throw new Exception("[T_FN_NICE_CLOSE_TMP] Select Sum is nothing");
            }
        }
        catch( Exception e ) {
            logger.info( "[T_FN_NICE_CLOSE_TMP] Select Sum Error.. {}", e.getLocalizedMessage() );
            throw e;

        }
    }
}

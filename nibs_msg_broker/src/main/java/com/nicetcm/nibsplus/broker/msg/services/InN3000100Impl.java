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
import org.apache.commons.beanutils.BeanUtils;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceCloseOrgMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceCloseTmpMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceCloseMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNiceCloseGiftMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseOrg;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseTmp;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseTmpSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceClose;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseGift;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;
import com.nicetcm.nibsplus.broker.msg.model.FnMacClose;

@Service("inN3000100")
public class InN3000100Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN3000100Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnNiceCloseOrgMapper fnNiceCloseOrgMap;
    @Autowired private TFnNiceCloseTmpMapper fnNiceCloseTmpMap;
    @Autowired private TFnNiceCloseMapper fnNiceCloseMap;
    @Autowired private TFnNiceCloseGiftMapper fnNiceCloseGiftMap;
    
    
    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        try {
            insertNiceCloseOrg( safeData, parsed );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            /* 이미 수신된 데이타인 경우 (Duplicate)
             * DB 행으로 인해 Time Out 이 발생하여 HOST에서 같은 전문을 재 송신한 경우
             * HOST 정상으로으로 응답을 송신한다.
             */
            return;
        }
        int iRtn = compCloseDate( parsed.getString("mac_no"), parsed.getString("close_date") );
        if( iRtn < 0 ) {
            logger.info( "CompCloseDate function result error" );
            throw new Exception("CompCloseDate function result error");
        }
        /*
         *  동일일자에 두번 마감을 눌렀을 경우 첫번째 마감을 실 마감으로 보고
         *  두번째 마감 이후 올라온것은 다음날 마감시 합산처리하기위해 마감시간을 넣어
         *  T_FN_NICE_CLOSE_TMP에만 저장처리하도록 한다.
         */
        else if( iRtn == 0 ) {
            try {
                insertNiceCloseTmp( safeData, parsed );
            }
            catch( Exception e ) {
                logger.info( "insertNiceCloseTmp Error." );
                throw e;
            }
        }
        /*
         *  T_FN_NICE_CLOSE_TMP 에 저장된 데이터가 없거나 전일 두번째 이상 마감된 데이터 가있을경우
         */
        else if( iRtn == 1 ) {
            try {
                insertNiceCloseSum( safeData, parsed );
            }
            catch( Exception e ) {
                logger.info( "insertNiceCloseSum Error." );
                throw e;
            }
            logger.debug( "close_date[{}], mac_no[{}], update_uid[{}]",
                    parsed.getString("close_date"), parsed.getString("mac_no"), parsed.getString("CM.msg_id") );
            FnMacClose macClose = new FnMacClose();
            macClose.setCloseDate( parsed.getString("close_date") );
            macClose.setOrgCode( "096" );
            macClose.setJijumCode( "9600" );
            macClose.setMacNo( parsed.getString("mac_no") );
            macClose.setUserId( parsed.getString("CM.msg_id") );
            try {
                splMap.spFnMacClose( macClose );
                if( !macClose.getResult().equals("OK") )
                    logger.info( "sp_fn_macclose procedure Error. [{}]", macClose.getResult() );
            }
            catch( Exception e ) {
                
            }
        }
        msgTX.rollback(safeData.getTXS());
        safeData.setTXS(msgTX.getTransaction(MsgBrokerTransaction.defMSGTX));

        /*
         *  상품권 마감일 경우 별도 테이블에 저장 처리 하도록 한다.
         */
        if( parsed.getInt("gv_out_cnt") > 0 ) {
            TFnNiceCloseGift fnNCG = new TFnNiceCloseGift();
            
            fnNCG.setOrgCd     ( "096"                          );
            fnNCG.setBranchCd  ( "9600"                         );
            fnNCG.setMacNo     ( parsed.getString("mac_no"    ) );
            fnNCG.setCloseDate ( parsed.getString("close_date") );
            fnNCG.setCloseTime ( parsed.getString("close_time") );
            fnNCG.setGvComCd   ( parsed.getString("gv_com_cd" ) );
            fnNCG.setGvType    ( parsed.getString("gv_type"   ) );
            fnNCG.setGvOutCnt  ( parsed.getInt   ("gv_out_cnt") );
            fnNCG.setUpdateDate( safeData.getDSysDate()         );
            fnNCG.setUpdateUid ( "online"                       );
            try {
                fnNiceCloseGiftMap.insert( fnNCG );
            }
            catch( org.springframework.dao.DataIntegrityViolationException de ) {
                logger.info("]T_FN_NICE_CLOSE_GIFT] dup Error!!!");
            }
            catch( Exception e ) {
                logger.info("[T_FN_NICE_CLOSE_GIFT] Insert Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
    }
    
    private void insertNiceCloseOrg( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        
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
        
        /*
         *  차세대 NTMS 운영부 및 지사 필드에 자금부서 와 자금지사코드를 넣도록함
         * 송호석 과장 요청
         */
        
        TFnNiceClose fnNiceClose = new TFnNiceClose();
        
        BeanUtils.copyProperties( fnNiceClose, sum );
        
        fnNiceClose.setCloseDate( parsed.getString("close_date") );
        fnNiceClose.setCloseTime( parsed.getString("close_time") );
        fnNiceClose.setMacNo( parsed.getString("mac_no") );
        fnNiceClose.setTrackUseType( parsed.getString("track_use_type") );
        
        TMisc miscCond = new TMisc();
        TMisc rsltMisc = null;
        miscCond.setArgType( "FD" );
        miscCond.setOrgCd( "096" );
        miscCond.setBranchCd( "9600" );
        miscCond.setMacNo( parsed.getString("mac_no") );
        try {
            rsltMisc = splMap.fcGetOrnzCdByMacNo( miscCond );
            if( rsltMisc != null ) {
                fnNiceClose.setDeptCd( rsltMisc.getOrnzCd().substring(0, 2) );
            }
        }
        catch( Exception e ) {
            logger.info( "FC_GET_ORNZ_CD_BY_MACNO Call Error {}", e.getLocalizedMessage() );
        }
        miscCond.setArgType( "FO" );
        miscCond.setOrgCd( "096" );
        miscCond.setBranchCd( "9600" );
        miscCond.setMacNo( parsed.getString("mac_no") );
        try {
            rsltMisc = splMap.fcGetOrnzCdByMacNo( miscCond );
            if( rsltMisc != null ) {
                fnNiceClose.setOfficeCd( rsltMisc.getOrnzCd().substring(2, 4) );
            }
        }
        catch( Exception e ) {
            logger.info( "FC_GET_ORNZ_CD_BY_MACNO Call Error {}", e.getLocalizedMessage() );
        }
        
        fnNiceClose.setInsertDate( safeData.getDSysDate() );
        fnNiceClose.setInsertUid( parsed.getString("CM.msg_id") );
        try {
            fnNiceCloseMap.insert( fnNiceClose );
        }
        catch( Exception e ) {
            logger.info( "[T_FN_NICE_CLOSE] Insert Error.. {}", e.getLocalizedMessage() );
            throw e;
        }
        
        TFnNiceCloseTmpSpec fnNCTSpec = new TFnNiceCloseTmpSpec();
        fnNCTSpec.createCriteria().andMacNoEqualTo( cond.getMacNo() )
                                  .andCloseDateLessThan( cond.getCloseDate() );
        try {
            fnNiceCloseTmpMap.deleteBySpec( fnNCTSpec );
        }
        catch( Exception e ) {
            logger.info( "[T_FN_NICE_CLOSE_TMP] Delete Error.. {}", e.getLocalizedMessage() );
            throw e;
        }
        try {
            insertNiceCloseTmp( safeData, parsed );
        }
        catch( Exception e ) {
            logger.info( "DBInsertNiceCloseSum => DBInsertNiceCloseTmp Error." );
            throw e;
        }
    }
}

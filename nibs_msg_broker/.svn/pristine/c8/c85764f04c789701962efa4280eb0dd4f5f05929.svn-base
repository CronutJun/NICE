package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 집계처리 ROUTINE을 CALL
 *
 * <pre>
 * MngNC_NiceTicketClose
 * </pre>
 *
 *           2014. 07. 31    K.D.J.
 */


import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnTicketMacCloseOrgMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnTicketMacCloseTmpMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnTicketMacCloseMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacCloseOrg;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacCloseTmp;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacCloseTmpSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacClose;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

@Service("inN3000200")
public class InN3000200Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN3000200Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TFnTicketMacCloseOrgMapper fnTicketMacCloseOrgMap;
    @Autowired private TFnTicketMacCloseTmpMapper fnTicketMacCloseTmpMap;
    @Autowired private TFnTicketMacCloseMapper fnTicketMacCloseMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        try {
            insertNiceCloseOrgTicket( safeData, parsed );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            /* 이미 수신된 데이타인 경우 (Duplicate)
             * DB 행으로 인해 Time Out 이 발생하여 HOST에서 같은 전문을 재 송신한 경우
             * HOST 정상으로으로 응답을 송신한다.
             */
            return;
        }
        int iRtn = compCloseDateTicket( parsed );
        if( iRtn < 0 ) {
            logger.warn( "CompCloseDateTicket function result error" );
            throw new Exception("CompCloseDateTicket function result error");
        }
        /* 동일일자에 두번 마감을 눌렀을 경우 첫번째 마감을 실 마감으로 보고
         * 두번째 마감 이후 올라온것은 다음날 마감시 합산처리하기위해 마감시간을 넣어
         * T_FN_NICE_CLOSE_TICKET_TMP 저장처리하도록 한다.
         */
        else if( iRtn == 0 ) {
            try {
                insertNiceCloseTicketTmp( safeData, parsed );
            }
            catch( Exception e ) {
                logger.warn( "insertNiceCloseTicketTmp Error." );
                throw e;
            }
        }
        /*
         *  T_FN_NICE_CLOSE_TICKET_TMP 에 저장된 데이터가 없거나 전일 두번째 이상 마감된 데이터 가있을경우
         */
        else if( iRtn == 1 ) {
            try {
                insertNiceCloseTicketSum( safeData, parsed );
            }
            catch( Exception e ) {
                logger.warn( "insertNiceCloseTicketSum Error." );
                throw e;
            }
        }
    }

    private void insertNiceCloseOrgTicket( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        TFnTicketMacCloseOrg fnTMCO = new TFnTicketMacCloseOrg();

        fnTMCO.setMacNo             ( parsed.getString("mac_no"               ) );
        fnTMCO.setCloseDate         ( parsed.getString("close_date"           ) );
        fnTMCO.setCloseTime         ( parsed.getString("close_time"           ) );
        fnTMCO.setTicket1EmitComp   ( parsed.getString("ticket1_emit_comp"    ) );
        fnTMCO.setTicket1EmitGubunCd( parsed.getString("ticket1_emit_gubun_cd") );
        fnTMCO.setTicket1EmitCnt    ( parsed.getInt   ("ticket1_emit_cnt"     ) );
        fnTMCO.setTicket1BackComp   ( parsed.getString("ticket1_back_comp"    ) );
        fnTMCO.setTicket1BackGubunCd( parsed.getString("ticket1_back_gubun_cd") );
        fnTMCO.setTicket1BackCnt    ( parsed.getInt   ("ticket1_back_cnt"     ) );
        fnTMCO.setInsertDate        ( safeData.getDSysDate()                    );
        fnTMCO.setInsertUid         ( parsed.getString("CM.msg_id"            ) );

        try {
            fnTicketMacCloseOrgMap.insertSelective( fnTMCO );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            logger.warn( "[T_FN_TICKET_MAC_CLOSE_ORG] Duplication [{}][{}][{}] !!!",
                    parsed.getString("mac_no"), parsed.getString("close_date"), parsed.getString("close_time") );
            throw de;
        }
        catch( Exception e ) {
            logger.warn( "[T_FN_TICKET_MAC_CLOSE_ORG] Insert Error.. {}", e.getLocalizedMessage() );
            throw e;
        }
    }

    private int compCloseDateTicket( MsgParser parsed ) {
        try {
            TFnTicketMacCloseTmp fnTMCTCond = new TFnTicketMacCloseTmp();

            fnTMCTCond.setCloseDate( parsed.getString("close_date") );
            fnTMCTCond.setOrgCd( "096" );
            fnTMCTCond.setBranchCd( "9600" );
            fnTMCTCond.setMacNo( parsed.getString("mac_no") );
            fnTMCTCond.setTicketCd( parsed.getString("ticket1_emit_comp") );
            fnTMCTCond.setTicketGubunCd( parsed.getString("ticket1_emit_gubun_cd") );
            List<TFnTicketMacCloseTmp> rslt = null;
            try {
                rslt = fnTicketMacCloseTmpMap.selectByCond1( fnTMCTCond );
                if( rslt.size() == 0 ) {
                    return 1;
                }
            }
            catch( Exception e ){
                logger.warn( "CompCloseDateTicket Error mac_no[{}] date[{}] => {}",
                        parsed.getString("mac_no"), parsed.getString("close_date"), e.getMessage() );
                return -1;
            }
            if( parsed.getString("close_date").compareTo(rslt.get(0).getCloseDate()) > 0 ) {
                return 1;
            }
            else if ( parsed.getString("close_date").compareTo(rslt.get(0).getCloseDate()) == 0 ) {
                return 0;
            }
            else {
                logger.warn( "CompCloseDateTicket Error close_date[{}]", rslt.get(0).getCloseDate() );
                return -1;
            }
        }
        catch( Exception e ) {
            logger.warn("comCloseDateTicket's error raised!! {}", e.getMessage() );
            return -1;
        }
    }

    private void insertNiceCloseTicketTmp( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        TFnTicketMacCloseTmp fnTMCT = new TFnTicketMacCloseTmp();

        fnTMCT.setCloseDate     ( parsed.getString("close_date")            );
        fnTMCT.setOrgCd         ( "096"                                     );
        fnTMCT.setBranchCd      ( "9600"                                    );
        fnTMCT.setMacNo         ( parsed.getString("mac_no"               ) );
        fnTMCT.setTicketCd      ( parsed.getString("ticket1_emit_comp"    ) );
        fnTMCT.setTicketGubunCd ( parsed.getString("ticket1_emit_gubun_cd") );
        fnTMCT.setDealType      ( "0"                                       );  /* 방출 */
        fnTMCT.setCloseTime     ( parsed.getString("close_time")            );
        fnTMCT.setTicket1EmitCnt( parsed.getInt   ("ticket1_emit_cnt")      );
        fnTMCT.setInsertDate    ( safeData.getDSysDate()                    );
        fnTMCT.setInsertUid     ( parsed.getString("CM.msg_id")             );

        try {
            fnTicketMacCloseTmpMap.insertSelective( fnTMCT );
        }
        catch( Exception e ) {
            logger.warn( "[T_FN_TICKET_MAC_CLOSE_TMP] Insert Error.. {}", e.getLocalizedMessage() );
            throw e;
        }
    }

    private void insertNiceCloseTicketSum( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        TFnTicketMacCloseTmp fnTMCTCond = new TFnTicketMacCloseTmp();

        fnTMCTCond.setCloseDate     ( parsed.getString("close_date")            );
        fnTMCTCond.setOrgCd         ( "096"                                     );
        fnTMCTCond.setBranchCd      ( "9600"                                    );
        fnTMCTCond.setMacNo         ( parsed.getString("mac_no"               ) );
        fnTMCTCond.setTicketCd      ( parsed.getString("ticket1_emit_comp"    ) );
        fnTMCTCond.setTicketGubunCd ( parsed.getString("ticket1_emit_gubun_cd") );
        fnTMCTCond.setDealType      ( "0"                                       );  /* 방출 */
        fnTMCTCond.setCloseTime     ( parsed.getString("close_time")            );
        fnTMCTCond.setTicket1EmitCnt( parsed.getInt   ("ticket1_emit_cnt")      );

        TFnTicketMacCloseTmp sum = null;
        try {
            sum = fnTicketMacCloseTmpMap.selectBySum1( fnTMCTCond );
            if( sum == null ) {
                logger.warn( "[T_FN_TICKET_MAC_CLOSE_TMP] Select Sum Error.." );
                throw new Exception("[T_FN_TICKET_MAC_CLOSE_TMP] Select Sum Error..");
            }
        }
        catch( Exception e ) {
            logger.warn( "[T_FN_TICKET_MAC_CLOSE_TMP] Select Sum Error..{}", e.getLocalizedMessage() );
            throw e;
        }

        TFnTicketMacClose fnTicketMacClose = new TFnTicketMacClose();

        BeanUtils.copyProperties( fnTicketMacClose, sum );

        fnTicketMacClose.setCloseDate     ( parsed.getString("close_date")            );
        fnTicketMacClose.setOrgCd         ( "096"                                     );
        fnTicketMacClose.setBranchCd      ( "9600"                                    );
        fnTicketMacClose.setMacNo         ( parsed.getString("mac_no"               ) );
        fnTicketMacClose.setTicketCd      ( parsed.getString("ticket1_emit_comp"    ) );
        fnTicketMacClose.setTicketGubunCd ( parsed.getString("ticket1_emit_gubun_cd") );
        fnTicketMacClose.setDealType      ( "0"                                       );  /* 방출 */
        fnTicketMacClose.setCloseTime     ( parsed.getString("close_time")            );

        TMisc miscCond = new TMisc();
        TMisc rsltMisc = null;
        miscCond.setArgType( "FD" );
        miscCond.setOrgCd( "096" );
        miscCond.setBranchCd( "9600" );
        miscCond.setMacNo( parsed.getString("mac_no") );
        try {
            rsltMisc = splMap.fcGetOrnzCdByMacNo( miscCond );
            if( rsltMisc != null ) {
                fnTicketMacClose.setDeptCd( rsltMisc.getOrnzCd().substring(0, 2) );
            }
        }
        catch( Exception e ) {
            logger.warn( "FC_GET_ORNZ_CD_BY_MACNO Call Error {}", e.getLocalizedMessage() );
        }
        miscCond.setArgType( "FO" );
        miscCond.setOrgCd( "096" );
        miscCond.setBranchCd( "9600" );
        miscCond.setMacNo( parsed.getString("mac_no") );
        try {
            rsltMisc = splMap.fcGetOrnzCdByMacNo( miscCond );
            if( rsltMisc != null ) {
                fnTicketMacClose.setOfficeCd( rsltMisc.getOrnzCd().substring(2, 4) );
            }
        }
        catch( Exception e ) {
            logger.warn( "FC_GET_ORNZ_CD_BY_MACNO Call Error {}", e.getLocalizedMessage() );
        }

        fnTicketMacClose.setInsertDate( safeData.getDSysDate() );
        fnTicketMacClose.setInsertUid( parsed.getString("CM.msg_id") );

        try {
            fnTicketMacCloseMap.insertSelective( fnTicketMacClose );
        }
        catch( Exception e ) {
            logger.warn( "[T_FN_TICKET_MAC_CLOSE] Insert Error.. {}", e.getLocalizedMessage() );
            throw e;
        }

        TFnTicketMacCloseTmpSpec fnTMCTSpec = new TFnTicketMacCloseTmpSpec();
        fnTMCTSpec.createCriteria().andCloseDateLessThan( parsed.getString("close_date") )
                                   .andOrgCdEqualTo( "096" )
                                   .andBranchCdEqualTo( "9600" )
                                   .andMacNoEqualTo( parsed.getString("mac_no") )
                                   .andTicketCdEqualTo( parsed.getString("ticket1_emit_comp") )
                                   .andTicketGubunCdEqualTo( parsed.getString("ticket1_emit_gubun_cd") )
                                   .andDealTypeEqualTo( "0" );
        try {
            fnTicketMacCloseTmpMap.deleteBySpec( fnTMCTSpec );
        }
        catch( Exception e ) {
            logger.warn( "[T_FN_TICKET_MAC_CLOSE_TMP] Delete Error.. {}", e.getLocalizedMessage() );
            throw e;
        }
        try {
            insertNiceCloseTicketTmp( safeData, parsed );
        }
        catch( Exception e ) {
            logger.warn( "DBInsertNiceCloseTicketSum => DBInsertNiceCloseTicketTmp Error." );
            throw e;
        }
    }
}

package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 출동요청 전문 수신처리
 *
 * <pre>
 * MngEM_SaveErrCall
 * </pre>
 *
 *           2014. 06. 24    K.D.J.
 */

import java.util.List;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.*;
import com.nicetcm.nibsplus.broker.msg.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Service("in01000130")
public class In01000130Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In01000130Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TCmCommonMapper cmCommonMap;
    @Autowired private TCtErrorBasicMapper errBasicMap;
    @Autowired private TCtErrorRcptMapper errRcptMap;
    @Autowired private TCtErrorNotiMapper errNotiMap;
    @Autowired private TCtErrorCallMapper errCallMap;
    @Autowired private TCtErrorTxnMapper errTxnMap;
    @Autowired private TCtErrorMngMadeComMapper errMngMadeComMap;

    @Override
    public void inMsgBizProc( MsgBrokerData safeData, MsgParser parsed ) throws Exception {

        logger.debug("Msg Received");
        logger.debug(parsed.getString("CM.work_type"));

        TMacInfo macInfo = new TMacInfo();
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );
        macInfo.setOrgSiteCd( parsed.getString("org_site_cd") );

        /**
         * 지점코드, 기번 길이 검증
         */
        try { comPack.checkBranchMacLength( macInfo ); } catch( Exception e ) {}
        logger.debug("BrchCd = {}, MacNo = {}", macInfo.getBranchCd(), macInfo.getMacNo());

        if( macInfo.getMacNo().equals("0000")
        ||  macInfo.getMacNo().trim().length() == 0
        || ( macInfo.getOrgCd().equals(  MsgBrokerConst.KEB_CODE ) && macInfo.getMacNo().equals("999") )) {
            comPack.getMacNoIntoSite( macInfo );
            logger.debug("SiteCd = {}, MacNo = {}", macInfo.getSiteCd(), macInfo.getMacNo());
        }
        try {
            /**
             * 기기정보 취득
             */
            comPack.getMacInfo( macInfo );
        }
        catch (Exception e) {
            logger.debug("GetMacInfo Error");
            /**
             * SMS 전송
             */
            if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.SHATMS_CODE) )
                splMap.SendSMSMacInfo( macInfo );
            throw new MsgBrokerException( String.format("[01003100] 기기정보 검색 실패 기관[%s] 지점[%s] 기번[%s]",
                                            macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo()), -7 );
        }
        logger.warn("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(),
                    macInfo.getMacNm(), macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );

        TCtErrorBasic errBasic = new TCtErrorBasic();
        TCtErrorMngMadeCom errMngMadeCom = new TCtErrorMngMadeCom();
        TCtErrorRcpt errRcpt = new TCtErrorRcpt();
        TCtErrorNoti errNoti = new TCtErrorNoti();
        TCtErrorCall errCall = new TCtErrorCall();
        TCtErrorTxn  errTxn  = new TCtErrorTxn();

        /**
         *  macInfo의 값을 errMng로 일괄 복사
         */
        BeanUtils.copyProperties( errBasic, macInfo );
        BeanUtils.copyProperties( errMngMadeCom, macInfo );

        errBasic.setCreateDate( parsed.getString("call_date") );
        errBasic.setCreateTime( parsed.getString("call_time") );
        errBasic.setOrgMsgNo( parsed.getString("trans1_seq") );
        errBasic.setTransDate( parsed.getString("trans1_date") );
        errBasic.setOrgMsg( parsed.getString("memo") );

        BeanUtils.copyProperties( errMngMadeCom, errBasic );

        errMngMadeCom.setMadeComCd( macInfo.getMadeComCd() );
        errMngMadeCom.setMacModel( parsed.getString("mac_model") );
        errMngMadeCom.setCallClass( parsed.getString("call_class") );
        errMngMadeCom.setCallType( parsed.getString("call_type") );
        errMngMadeCom.setCallCntType( parsed.getString("call_cnt_type") );
        errMngMadeCom.setAsAcptDate( safeData.getSysDate() );

        errBasic.setGroupErrorCd( parsed.getString("group_error_cd") );
        errBasic.setMidErrorCd( parsed.getString("mid_error_cd") );
        errBasic.setMadeErrCd( parsed.getString("error_mtc_cd") );
        errBasic.setCrtNo( parsed.getString("crt_no") );
        errBasic.setOrgCallCnt( parsed.getLong("org_call_cnt") );

        errMngMadeCom.setOrgCallCnt( parsed.getShort("org_call_cnt") );
        errMngMadeCom.setMtcCd( errBasic.getCrtNo() );
        errMngMadeCom.setUpdateDate( safeData.getDSysDate() );
        errMngMadeCom.setUpdateUid  ( "ERRmng" );

        /**
         * 2일전 전문은 취소요청 응답 전송.
         */
        if( String.valueOf(errBasic.getCreateDate()).compareTo(MsgBrokerLib.SysDate(-2)) <= 0 ) {
            logger.debug( ">>> [SaveErrCall] 재수신 전문 삭제요청 응답");
            throw new MsgBrokerException(">>> [SaveErrCall] 재수신 전문 삭제요청 응답", -8 );
        }

        if( parsed.getString("wait_cust_cnt").equals("1")) {
            errBasic.setOrgCustRecvYn("Y");
        }

        /**
         * 신한은행 토탈 전환에 따라 2차 출동요청도 처리 함. 2013.12.17 by B.H.J
         * 2차 출동요청 중 키지원(출동요청사유코드 '3'-> SUBSTR(SEC, 2,1) = '3')의 경우를 제외 하고
         * T_CT_ERROR_MNG_MADE_COM 테이블에 저장하여 기기사로 전문 전송 처리 하도록 하고
          *장애테이블에는 저장 하지 않음.
         */
        if( macInfo.getOrgCd().equals(MsgBrokerConst.SHATMS_CODE)
        &&  errMngMadeCom.getCallCntType().equals("2")
        &&  !errMngMadeCom.getCallType().equals("3") ) {
            insertErrMngMadeCom( errBasic, errMngMadeCom );
        }

        /**
         * 신한은행 ATMS의 경우 출동요청 차수를 기관 메모에 보여주도록 한다.
         */
        if( errBasic.getOrgCallCnt() > 0 ) {
            errBasic.setOrgMsg( errBasic.getOrgMsg() + String.format("요청차수[%s]", errBasic.getOrgCallCnt()) );
        }

        if( parsed.getString("req_visit_date").trim().length() > 0 ) {
            errBasic.setOrgMsg(  errBasic.getOrgMsg() + String.format("방문요청일시[%s-", parsed.getString("req_visit_date")) );
        }
        if( parsed.getString("req_visit_time").trim().length() > 0 ) {
            errBasic.setOrgMsg(  errBasic.getOrgMsg() + String.format("%s]", parsed.getString("req_visit_time")) );
        }
        if( parsed.getInt("fac_yn") == 2 ) {
            errBasic.setOrgMsg(  errBasic.getOrgMsg() + "[시설물관련]" );
        }
        if( parsed.getInt("urgency_yn") == 1 ) {
            errBasic.setOrgMsg(  errBasic.getOrgMsg() + "긴급건 " );
        }

        errBasic.setOrgSendYn("0");
        errBasic.setSec( parsed.getString("call_class").trim() + parsed.getString("call_type").trim() + parsed.getString("call_cnt_type").trim() );
        errBasic.setErrorCd( parsed.getString("error_cd") );
        errBasic.setMadeErrCd( parsed.getString("error_mtc_cd") );
        if( errBasic.getOrgCd().equals(MsgBrokerConst.KBST_CODE)
        ||  errBasic.getOrgCd().equals(MsgBrokerConst.KIUP_CODE)
        ||  errBasic.getOrgCd().equals(MsgBrokerConst.HANAATMS_CODE)   /* HANA ATMS */
        ||  errBasic.getOrgCd().equals(MsgBrokerConst.KNB_CODE)
        ||  errBasic.getOrgCd().equals(MsgBrokerConst.SHATMS_CODE)     /* 신한 ATMS */
        ||  errBasic.getOrgCd().equals(MsgBrokerConst.WRATMS_CODE)     /* 우리 ATMS */
        ||  errBasic.getOrgCd().equals(MsgBrokerConst.KNATMS_CODE) ) { /* 경남 ATMS */
            /**
             *  HOST에서 출동요청구분이 수동, 자동일경우는 '9' 민원일경우는 '3' 그이외에는 '1'을 설정하여 준다
             *  예)국민은행 출동요청구분이 수동일경우 KB9+출동요청사유(2)+_LC코드
             */
            if( errBasic.getErrorCd().substring(2, 3).equals(MsgBrokerConst.CALL_TYPE_AUTO) ) {
                /**
                 * 업무구분 '21'-자동,수동출동요청전문
                 */
                errBasic.setFormatType("21");
            }
            else if( errBasic.getErrorCd().substring(2, 3).equals(MsgBrokerConst.CALL_TYPE_TEL) ) {
                /**
                 * 업무구분 '31'-민원출동요청전문
                 */
                errBasic.setFormatType("31");
            }
            else  {
                /**
                 * 업무구분 '11'-일반출동요청전문
                 */
                errBasic.setFormatType("11");
            }
        }
        else if( errBasic.getOrgCd().equals(MsgBrokerConst.KEB_CODE)
              ||  errBasic.getOrgCd().equals(MsgBrokerConst.SHB_CODE)
              ||  errBasic.getOrgCd().equals(MsgBrokerConst.BU_CODE)
              ||  errBasic.getOrgCd().equals(MsgBrokerConst.NONGH_CODE)
              ||  errBasic.getOrgCd().equals(MsgBrokerConst.BUATMS_CODE) ) {
            /**
             * 업무구분 '21'-자동,수동출동요청전문
             */
            errBasic.setFormatType("21");
        }
        else if ( errBasic.getOrgCd().equals(MsgBrokerConst.DGB_CODE) ) {
            TCtError ctErr;
            try {
                ctErr = errBasicMap.getErrGroupCd( errBasic );
            }
            catch ( Exception e ) {
                logger.warn("대구은행 비교대상 접수장애 조회실패[{}]", e.getMessage());
                ctErr = new TCtError();
            }
            TCmCommonSpec cmCommonSpc = new TCmCommonSpec();
            cmCommonSpc.createCriteria().andCdNm3EqualTo( errBasic.getOrgCd() )
                                        .andCdNm2EqualTo( errBasic.getErrorCd() );
            try {
                List<TCmCommon> cmCommons = cmCommonMap.selectBySpec( cmCommonSpc );
                if( cmCommons.size() == 0 ) {
                    logger.warn("대구은행 그룹장애코드 맵핑 정보 없음 [{}-{}][{}]",
                            errBasic.getBranchCd(), errBasic.getMacNo(), errBasic.getErrorCd() );
                }
                else {
                    for( TCmCommon cmCommon : cmCommons ) {
                        if( cmCommon.getCdNm1().equals(ctErr.getGroupErrorCd()) ) {
                            throw new Exception("중복된 출동요청으로 접수하지 않음");
                        }
                    }
                }
            }
            catch ( Exception e ) {
                logger.warn("대구은행 그룹장애코드 맵핑 Error[{}-{}][{}][{}]",
                        errBasic.getBranchCd(), errBasic.getMacNo(), errBasic.getErrorCd(), e.getMessage() );
            }

            /**
             * 업무구분 '21'-자동,수동출동요청전문
             */
            errBasic.setFormatType("21");
        }
        else {
            /**
             * 업무구분 '11'-일반출동요청전문
             */
            errBasic.setFormatType("11");
        }

        comPack.insertErrBasic( safeData, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, parsed.getString("part_mng_yn") );
    }

    private void insertErrMngMadeCom( TCtErrorBasic errBasic, TCtErrorMngMadeCom errMngMadeCom ) throws Exception {
        TCtErrorBasic rsltErrBasic = errBasicMap.selectByCond1( errBasic );

        if( rsltErrBasic == null ) {
            logger.warn("[insertErrMngMadeCom]해당 장애 없음 trans_date[{}] org_msg_no[{}] org_call_cnt[{}]\n",
                    errBasic.getTransDate(), errBasic.getOrgMsgNo(), errMngMadeCom.getOrgCallCnt() );
        }

        try {
            errMngMadeComMap.insert( errMngMadeCom );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            logger.warn(">>> [DBInsertErrMngMadeCom] T_CT_ERROR_MNG_MADE_COM 같은요청전문 수신");
            throw de;
        }
    }
}

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
    @Autowired private TCtErrorMngMapper errMngMap;
    @Autowired private TCtErrorRcptMapper errRcptMap;
    @Autowired private TCtErrorNotiMapper errNotiMap;
    @Autowired private TCtErrorCallMapper errCallMap;
    @Autowired private TCtErrorTxnMapper errTxnMap;
    @Autowired private TCtErrorMngMadeComMapper errMngMadeComMap;
    
    @Override
    public void inMsgBizProc( MsgParser parsed ) throws Exception {

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
        comPack.checkBranchMacLength( macInfo );
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
            splMap.SendSMSMacInfo( macInfo );
            throw new MsgBrokerException( String.format("[01003100] 기기정보 검색 실패 기관[%s] 지점[%s] 기번[%s]",
                                            macInfo.getOrgCd(), macInfo.getMacNo()), -7 );
        }
        logger.info("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(),
                    macInfo.getMacNm(), macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );
        
        TCtErrorMng errMng = new TCtErrorMng();
        TCtErrorMngMadeCom errMngMadeCom = new TCtErrorMngMadeCom();
        
        /**
         *  macInfo의 값을 errMng로 일괄 복사
         */
        BeanUtils.copyProperties( errMng, macInfo );
        BeanUtils.copyProperties( errMngMadeCom, macInfo );
        
        errMng.setCreateDate( parsed.getInt("call_date") );
        errMng.setCreateTime( parsed.getString("call_time") );
        errMng.setOrgMsgNo( parsed.getString("trans1_seq") );
        errMng.setTransDate( parsed.getString("trans1_date") );
        errMng.setOrgMsg( parsed.getString("memo") );
        
        errMngMadeCom.setMadeComCd( parsed.getString("mac_model_com_cd") );
        errMngMadeCom.setMacModel( parsed.getString("mac_model") );
        errMngMadeCom.setCallClass( parsed.getString("call_class") );
        errMngMadeCom.setCallType( parsed.getString("call_type") );
        errMngMadeCom.setCallCntType( parsed.getString("call_cnt_type") );
        
        errMng.setGroupErrorCd( parsed.getString("group_error_cd") );
        errMng.setMidErrorCd( parsed.getString("mid_error_cd") );
        errMng.setMadeErrCd( parsed.getString("error_mtc_cd") );
        errMng.setCrtNo( parsed.getString("crt_no") );
        errMng.setOrgCallCnt( Double.parseDouble(Integer.toString(parsed.getInt("org_call_cnt"))) );
        
        errMngMadeCom.setOrgCallCnt( Short.parseShort(Integer.toString(parsed.getInt("org_call_cnt"))) );
        errMngMadeCom.setMtcCd( errMng.getCrtNo() );
        
        /**
         * 2일전 전문은 취소요청 응답 전송.
         */
        if( String.valueOf(errMng.getCreateDate()).compareTo(MsgBrokerLib.SysDate(-2)) <= 0 ) {
            logger.debug( ">>> [SaveErrCall] 재수신 전문 삭제요청 응답");
            throw new MsgBrokerException(">>> [SaveErrCall] 재수신 전문 삭제요청 응답", -8 );
        }
        
        if( parsed.getString("wait_cust_cnt").equals("1")) {
            errMng.setOrgCustRecvYn("Y");
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
            insertErrMngMadeCom( errMng, errMngMadeCom );
        }
        
        /**
         * 신한은행 ATMS의 경우 출동요청 차수를 기관 메모에 보여주도록 한다. 
         */
        //if( errMng.getOrgCallCnt() > 0 ) {
        //    errMng.setOrgMsg( errMng.getOrgMsg() + String.format("요청차수[%s]", errMng.getOrgCallCnt()) );
        //}
        
        if( parsed.getString("req_visit_date").trim().length() > 0 ) {
            errMng.setOrgMsg(  errMng.getOrgMsg() + String.format("방문요청일시[%s-", parsed.getString("req_visit_date")) );
        }
        if( parsed.getString("req_visit_time").trim().length() > 0 ) {
            errMng.setOrgMsg(  errMng.getOrgMsg() + String.format("%s]", parsed.getString("req_visit_time")) );
        }
        if( parsed.getInt("fac_yn") == 2 ) {
            errMng.setOrgMsg(  errMng.getOrgMsg() + "[시설물관련]" );
        }
        if( parsed.getInt("urgency_yn") == 1 ) {
            errMng.setOrgMsg(  errMng.getOrgMsg() + "긴급건 " );
        }
        
        errMng.setOrgSendYn("0");
        errMng.setSec( parsed.getString("call_class").trim() + parsed.getString("call_type").trim() + parsed.getString("call_cnt_type").trim() );
        errMng.setErrorCd( parsed.getString("error_cd") );
        errMng.setMadeErrCd( parsed.getString("error_mtc_cd") );
        if( errMng.getOrgCd().equals(MsgBrokerConst.KBST_CODE)
        ||  errMng.getOrgCd().equals(MsgBrokerConst.KIUP_CODE)
        ||  errMng.getOrgCd().equals(MsgBrokerConst.HANAATMS_CODE)   /* HANA ATMS */
        ||  errMng.getOrgCd().equals(MsgBrokerConst.KNB_CODE)
        ||  errMng.getOrgCd().equals(MsgBrokerConst.SHATMS_CODE)     /* 신한 ATMS */
        ||  errMng.getOrgCd().equals(MsgBrokerConst.WRATMS_CODE)     /* 우리 ATMS */
        ||  errMng.getOrgCd().equals(MsgBrokerConst.KNATMS_CODE) ) { /* 경남 ATMS */
            /**
             *  HOST에서 출동요청구분이 수동, 자동일경우는 '9' 민원일경우는 '3' 그이외에는 '1'을 설정하여 준다 
             *  예)국민은행 출동요청구분이 수동일경우 KB9+출동요청사유(2)+_LC코드
             */
            if( errMng.getErrorCd().substring(2, 3).equals(MsgBrokerConst.CALL_TYPE_AUTO) ) {
                /** 
                 * 업무구분 '21'-자동,수동출동요청전문
                 */
                errMng.setFormatType("21");
            }
            else if( errMng.getErrorCd().substring(2, 3).equals(MsgBrokerConst.CALL_TYPE_TEL) ) {
                /**
                 * 업무구분 '31'-민원출동요청전문
                 */
                errMng.setFormatType("31");
            }
            else  {
                /**
                 * 업무구분 '11'-일반출동요청전문
                 */
                errMng.setFormatType("11");
            }
        }
        else if( errMng.getOrgCd().equals(MsgBrokerConst.KEB_CODE)
              ||  errMng.getOrgCd().equals(MsgBrokerConst.SHB_CODE)
              ||  errMng.getOrgCd().equals(MsgBrokerConst.BU_CODE)
              ||  errMng.getOrgCd().equals(MsgBrokerConst.NONGH_CODE)
              ||  errMng.getOrgCd().equals(MsgBrokerConst.BUATMS_CODE) ) {
            /** 
             * 업무구분 '21'-자동,수동출동요청전문
             */
            errMng.setFormatType("21");
        }
        else if ( errMng.getOrgCd().equals(MsgBrokerConst.DGB_CODE) ) {
            TCtError ctErr;
            try {
                ctErr = errMngMap.getErrGroupCd( errMng );
            }
            catch ( Exception e ) {
                logger.info("대구은행 비교대상 접수장애 조회실패[{}]", e.getMessage());
                ctErr = new TCtError();
            }
            TCmCommonSpec cmCommonSpc = new TCmCommonSpec();
            cmCommonSpc.createCriteria().andCdNm3EqualTo( errMng.getOrgCd() )
                                        .andCdNm2EqualTo( errMng.getErrorCd() );
            try {
                List<TCmCommon> cmCommons = cmCommonMap.selectBySpec( cmCommonSpc );
                if( cmCommons.size() == 0 ) {
                    logger.info("대구은행 그룹장애코드 맵핑 정보 없음 [{}-{}][{}]",
                            errMng.getBranchCd(), errMng.getMacNo(), errMng.getErrorCd() );
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
                logger.info("대구은행 그룹장애코드 맵핑 Error[{}-{}][{}][{}]",
                        errMng.getBranchCd(), errMng.getMacNo(), errMng.getErrorCd(), e.getMessage() );
            }
            
        }
        else {
            /**
             * 업무구분 '11'-일반출동요청전문
             */
            errMng.setFormatType("11");
        }
        
        comPack.insertErrMng( errMng, macInfo, parsed.getString("part_mng_yn") );
    }
    
    private void insertErrMngMadeCom( TCtErrorMng errMng, TCtErrorMngMadeCom errMngMadeCom ) throws Exception {
        TCtErrorMng rsltErrMng = errMngMap.selectByCond1( errMng );
        
        if( rsltErrMng == null ) {
            logger.info("[insertErrMngMadeCom]해당 장애 없음 trans_date[{}] org_msg_no[{}] org_call_cnt[{}]\n",
                    errMng.getTransDate(), errMng.getOrgMsgNo(), errMngMadeCom.getOrgCallCnt() );
        }
        
        errMngMadeComMap.insert( errMngMadeCom );
    }
}

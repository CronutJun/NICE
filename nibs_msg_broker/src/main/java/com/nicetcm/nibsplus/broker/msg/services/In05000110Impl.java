package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker ATM 상태전문 수신처리
 * 
 * <pre>
 * MngES_SaveErrState
 * </pre>
 * 
 *           2014. 07. 16    K.D.J.
 */


import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.ErrorState;

@Service("in05000110")
public class In05000110Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In05000110Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    
    @Override
    public void inMsgBizProc(MsgParser parsed) throws Exception {
        
        TMacInfo macInfo = new TMacInfo();
        TCtErrorMng errMng = new TCtErrorMng();
        
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );
        /**
         * 지점코드, 기번 길이 검증
         */
        comPack.checkBranchMacLength( macInfo );
        
        /*
         * 출동알림은 코너정보만 들어오므로 대표기번을 따야 한다. 
         */
        if( parsed.getString("CM.msg_id").equals("ALARM") ) {
            /*
             * 출동알림은 기번자리에 코너코드를 받는다 
             */
            comPack.getMacNoIntoSite( macInfo );
            parsed.setString("mac_no", macInfo.getMacNo());
        }
        try {
            /**
             * 기기정보 취득
             */
            comPack.getMacInfo( macInfo );
        }
        catch (Exception e) {
            logger.debug("GetMacInfo Error");
            logger.info( "[01003100] 기기정보 검색 실패 기관[{}] 지점[{}] 기번[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo() );
            /**
             * SMS 전송
             */
            splMap.SendSMSMacInfo( macInfo );
            throw e;
        }
        
        logger.info("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}] 보수모드[{}] 중지여부[{}]",
                macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(), macInfo.getMacNm(),
                macInfo.getDeptCd(),macInfo.getOfficeCd(), macInfo.getTeamCd(), parsed.getString("state_term_mode"),
                parsed.getString("state_off_yn"));
        
        /**
         *  macInfo의 값을 errMng로 일괄 복사
         */
        BeanUtils.copyProperties( errMng, macInfo );
        errMng.setCreateDate( parsed.getInt("create_date") );
        errMng.setCreateTime( parsed.getString("create_time") );
        errMng.setAtmState( parsed.getString("atm_mode") );
        errMng.setOrgMsg( parsed.getString("memo") );

        /*
         * 상태전문에 대한 응답을 보내지 않는 기관
         * - 기업은행 상관없이 걍 보냄
         */
        
        /*
         * 부산은행일 경우 발생일자 발생시간을 1차통지 거래일자, 및 일련번호로 설정 
         * 20080624 by BHJ 
         * 부산은행일 경우 발생일자 발생시간은 접수일 접수시간으로 20110601 채정아 요청 
         * 현대증권일 경우 발생일자 발생시간은 접수일 접수시간으로 20120621 조규석 요청 
         */
        if( macInfo.getOrgCd().equals(MsgBrokerConst.BU_CODE)
        ||  macInfo.getOrgCd().equals(MsgBrokerConst.HD_CODE) ) {
            /*
             * 10. 전문추적번호
             */
            errMng.setTransDate( parsed.getString("create_date") );
            /*
             * 송신(거래)일자
             */
            errMng.setOrgMsgNo( parsed.getString("create_time") );
            
            errMng.setCreateDate( Integer.parseInt(sSysDate) );
            errMng.setCreateTime( sSysTime );
            
            /*
             * 전문 응답은 수신 전문에서 보내므로 발생과 복구 시간을 위해 suBody.create_time을 현재시간으로 바꿔준다 
             */
            parsed.setString("create_date", sSysDate );
            parsed.setString("create_time", sSysTime );
        }
        else {
            /*
             * 송신(거래)일자
             */
            errMng.setTransDate( parsed.getString("CM.trans_date") );
            /*
             * 10. 전문추적번호
             */
            errMng.setOrgMsgNo( parsed.getString("CM.org_msg_no") );
        }
        /*
         * 업무구분 '11'-장애관리전문
         */
        errMng.setFormatType( "11" );

        MsgBrokerConst.EnumStateSkipYN enumStateSkip = MsgBrokerConst.EnumStateSkipYN.ORG_DFT;
        
        for( MsgBrokerConst.EnumStateSkipYN e: MsgBrokerConst.EnumStateSkipYN.values() ) {
            enumStateSkip = e;
            if( e.name().equals("ORG_" + parsed.getString("CM.org_cd")) ) {
                break;
            }
        }

        logger.info(">>>>>>>>>>> parsed.atm_state[{}]", parsed.getString("atm_state") );
        
        ErrorState errState = new ErrorState();
        errState.setMacType( MsgBrokerConst.CURERR_ATM );
        errState.setOrgCd( errMng.getOrgCd() );
        errState.setBranchCd( errMng.getBranchCd() );
        errState.setMacNo( errMng.getMacNo() );
        byte[] retErrStates = comPack.getCurrentErrorState( errState );
        logger.info("CUR_ERROR_LIST[{}]", new String(retErrStates) );
        
        /*
         * 20090603 개국일 경우 상태전문을 처리하지 않도록 한다. BY.BHJ 
         * 20090702 개국일 경우라도 신한은행의 경우 상태전문 장애 발생일경우는 참조하도록 한다. 복구 제외 BY BHJ 
         */
        /*
         *  개국전문이 아닐경우
         */
        if( !parsed.getString("error_hw_yn").equals(MsgBrokerConst.HWERR_OPEN)
           /*
            *  신한은행의 경우는 개국전문에서 상태 발생만 체크한다. => 20111208 상태복구도 처리해야 한다.
            */
        || ( parsed.getString("error_hw_yn").equals(MsgBrokerConst.HWERR_OPEN)
          && macInfo.getOrgCd().equals(MsgBrokerConst.SHATMS_CODE) ) ) {
            /*
             *현금, 수표, 전표 관련 장애 처리 
             */
            for( MsgBrokerConst.EnumOrgErrorState e: MsgBrokerConst.EnumOrgErrorState.values() ){
                /*
                 * 해당 상태관련 장애 무시 
                 */
                if( enumStateSkip.getErrStates().getBytes()[e.ordinal()] == '0' )
                    continue;
                
                /*
                 * 현금입금함 관련 장애 이지만 CD 기 일경우(출금만하는 기기) 장애수신 무시 
                 */
                if( (e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_CASHBOX 
                  || e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_CASHBOX_50000
                  || e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_CASHBOX_100000
                  || e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_INPUTSTOP)
                &&  (macInfo.getMacModel().equals("1100")
                  || macInfo.getMacModel().equals("1200")
                  || macInfo.getMacModel().equals("1500")
                  || macInfo.getMacModel().equals("1600")) ) {
                    logger.info(">>> [SaveErrState] CD기 입금함 상태 수신 ... 무시...");
                    continue;
                }
                /*
                 * 수표관련 장애는 수표를 취급하지 않는 기기 일 경우 장애수신 무시 하도록 한다.   
                 * 20080624 by bhj 
                 */
                else if( (e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_CHECK 
                        || e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_CHECKBOX)
                      &&  (macInfo.getCheckYn().equals("0")
                        || macInfo.getCheckYn().length() == 0) ) {
                   logger.info(">>> [SaveErrState] 수표 미취급 기기 수표 관련 장애 수신 ... 무시...");
                   continue;
                }
                else if( (e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_50000 
                        && !parsed.getString("cash_5000_yn").equals("2") 
                        && parsed.getString("cash_5000_yn").length() > 0) 
                      ||  (e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_100000
                        && !parsed.getString("cash_100000_yn").equals("2")
                        && parsed.getString("cash_100000_yn").length() > 0) ) {
                   logger.info(">>> [SaveErrState] 고액권 거래불가 기기 고액권 관련 장애 수신 ... 무시...");
                   continue;
                }
                
                /*
                 * 복구일 경우는 NE0**, 예보일 경우는 NE1**, 장애일 경우는 NE2** 
                 */
                errMng.setErrorCd( String.format("NE%c%02s", parsed.getBytes("atm_state")[e.ordinal()], e.getErrorCd()) );
                
                /*
                 * 예보 및 장애 
                 */

            }
        }
    }
}

package com.nicetcm.nibsplus.broker.msg.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 나이스 장애처리
 * 
 * <pre>
 * MngNS_NiceSaveErrState
 * </pre>
 * 
 * @author   K.D.J
 * @since    2014.07.23
 * 
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtNiceMngMapper;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCtNiceMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;


@Service("inN2000120")
public class InN2000120Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN2000120Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TCtNiceMngMapper niceMngMap;
    
    @Override
    public void inMsgBizProc( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        
        TMacInfo macInfo = new TMacInfo();
        TCtErrorBasic errBasic = new TCtErrorBasic();
        /*
         * NICE ATM H/W 장애 
         */
        String[] saNiceErrState = {
                "",
                MsgBrokerConst.NICE_ERROR_CASHOUT_ERROR   , /* "121" 지폐방출기                  */
                MsgBrokerConst.NICE_ERROR_CARDREAD_ERROR  , /* "122" 카드판독기                  */
                MsgBrokerConst.NICE_ERROR_SPECS_ERROR     , /* "123" 명세표                        */
                "",
                MsgBrokerConst.NICE_ERROR_INBOX_ERROR     , /* "126" 입금함 2005.11.25 추가  */
                "",
                MsgBrokerConst.NICE_ERROR_BANKBOOK_ERROR  , /* "128" 통장정리부 2005.12.13 추가 */
                "",
                MsgBrokerConst.NICE_ERROR_DEAL_LIST_ERROR , /* "129" 거래내역출력부 2008.12.16 추가 */
                "",
                MsgBrokerConst.NICE_ERROR_CASHIN_ERROR    , /* "131" 지폐미수취              */
                "",
                MsgBrokerConst.NICE_ERROR_DONGUL_ERROR    , /* "133" 동글이                    */
                MsgBrokerConst.NICE_ERROR_DVR_ERROR       , /* "134" DVR 2003.11.13 추가      */
                MsgBrokerConst.NICE_ERROR_INPUT_CHECK     , /* "135" 수표입금부                  */
                MsgBrokerConst.NICE_ERROR_OUT_CHECK       , /* "136" 수표출금부              */
                MsgBrokerConst.NICE_ERROR_INPUT_BOX_50000 , /* "137" 오만원권입금부            */
                MsgBrokerConst.NICE_ERROR_INPUT_BOX_100000, /* "138" 십만원권입금부            */
                "",
                MsgBrokerConst.NICE_ERROR_REMAIN_MONEY
        };
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );

        /*
         *  픽에러, 픽이상, 무거래, 잔액조회, 지폐방출후잔액조회
         *  --> 위의 관련에러는 T_CT_NICE_MNG에 insert하지 않는다.
         */
        if( parsed.getString("user_made_err").equals("0") ) {
            insertNiceMng( safeData, parsed );
        }
        
        try {
            comPack.getMacInfo( macInfo );
        }
        catch ( Exception e) {
            logger.info("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(), macInfo.getMacNm(),
                    macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );
            throw e;
        }
        
    }
    
    /**
     * NICE-장애전문 모니터링을 위한 데이터 저장
     * 
     * @author KDJ originated by 방혜진
     * @since 2008/12/02
     * @param parsed  파싱된 전문
     * @throws Exception
     */

    private void insertNiceMng( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        
        TCtNiceMng niceMng = new TCtNiceMng();
        
        niceMng.setjDate( parsed.getString("create_date") );      /* 일자 */
        niceMng.setjTime( parsed.getString("create_time") );      /* 시간 */
        niceMng.setMacNo( parsed.getString("mac_no") );           /* 기번 */
        niceMng.setNetS( parsed.getString("network_info") );      /* 통신망 */
        niceMng.setStat( parsed.getString("atm_state") );         /* 상태 */
        
        if( niceMng.getNetS().equals(MsgBrokerConst.NICE_STATE_LINE_ERR) ) {
            TCtNiceMng rsltNiceMng;
            try {
                rsltNiceMng = niceMngMap.selectByCond1( niceMng );
                if( rsltNiceMng == null ) {
                    rsltNiceMng = new TCtNiceMng();
                    rsltNiceMng.setNetS("");
                }
            }
            catch ( Exception e ) {
                rsltNiceMng = new TCtNiceMng();
                rsltNiceMng.setNetS("");
            }
            if( rsltNiceMng.getNetS().equals(MsgBrokerConst.NICE_STATE_LINE_ERR) )
                return;
        }
        niceMng.setPowerFail( parsed.getString("atm_off_day").substring(4) + parsed.getString("atm_off_time") );/* POWER_FAIL */
        niceMng.setJp1 ( parsed.getString("atm_cash").substring(0, 1) );          /* 지폐함1  */
        niceMng.setJp2 ( parsed.getString("atm_cash").substring(1, 2) );          /* 지폐함2  */
        niceMng.setJp3 ( parsed.getString("atm_cash").substring(2, 3) );          /* 지폐함3  */
        niceMng.setJp4 ( parsed.getString("atm_cash").substring(3, 4) );          /* 지폐함4  */
        niceMng.setHsh ( parsed.getString("atm_cash").substring(4, 5) );          /* 회수함   */
        niceMng.setMsp1( parsed.getString("atm_cash").substring(5, 6) );          /* 명세표   */
        niceMng.setGsy1( parsed.getString("atm_cash").substring(6, 7) );          /* 감사용지 */
        niceMng.setDummy1( parsed.getString("atm_cash").substring(7, 8) );        /* 입금함   */
        niceMng.setDummy2( parsed.getString("atm_cash").substring(8) );           /* 수표함   */
        niceMng.setDummy3( parsed.getString("atm_dummy").substring(0, 1) );       /* 더미3    */
        niceMng.setDummy4( parsed.getString("atm_dummy").substring(1, 2) );       /* 더미4    */
        niceMng.setDummy5( parsed.getString("atm_dummy").substring(2, 3) );       /* 더미5    */
        niceMng.setDummy6( parsed.getString("atm_dummy").substring(3, 4) );       /* 더미6    */
        niceMng.setDummy7( parsed.getString("atm_dummy").substring(4, 5) );       /* 더미7    */
        niceMng.setDummy8( parsed.getString("user_made_err").substring(4, 5) );   /* 더미8    */

        niceMng.setNetErr   ( parsed.getString("atm_hw_error[0]" ) );             /* 통신장애 */
        niceMng.setJpbc     ( parsed.getString("atm_hw_error[1]" ) );             /* 지폐방출 */
        niceMng.setKdpd     ( parsed.getString("atm_hw_error[2]" ) );             /* 카드판독 */
        niceMng.setMspy     ( parsed.getString("atm_hw_error[3]" ) );             /* 명세표용지 */
        niceMng.setGsyj     ( parsed.getString("atm_hw_error[4]" ) );             /* 감사용지1 */
        niceMng.setIg1      ( parsed.getString("atm_hw_error[5]" ) );             /* 입금함1->만원권 */
        niceMng.setIg2      ( parsed.getString("atm_hw_error[6]" ) );             /* System Disk->HW MON */
        niceMng.setIg3      ( parsed.getString("atm_hw_error[7]" ) );             /* 통장정리부 */
        niceMng.setIg4      ( parsed.getString("atm_hw_error[8]" ) );             /* System 제어부 */
        niceMng.setIg5      ( parsed.getString("atm_hw_error[9]" ) );             /* 접객부->거래내역출력부로 바뀜 */
        niceMng.setPassMac  ( parsed.getString("atm_hw_error[10]") );             /* 암호화장비 */
        niceMng.setJpMsc    ( parsed.getString("atm_hw_error[11]") );             /* 지폐미수취 */
        niceMng.setDummy9   ( parsed.getString("atm_hw_error[12]") );             /* T-Money 모듈상태 */
        niceMng.setDgi      ( parsed.getString("atm_hw_error[13]") );             /* 동글이 */
        niceMng.setDummy10  ( parsed.getString("atm_hw_error[14]") );             /* 화상카메라 */
        niceMng.setInCheck  ( parsed.getString("atm_hw_error[15]") );             /* 수표입금부 */
        niceMng.setOutCheck ( parsed.getString("atm_hw_error[16]") );             /* 수표출금부 */
        niceMng.setInCash5  ( parsed.getString("atm_hw_error[17]") );             /* 입금함2->오만원권 */
        niceMng.setInCash10 ( parsed.getString("atm_hw_error[18]") );             /* 입금함3->십만원권 */
        niceMng.setRemMoney ( parsed.getString("atm_hw_error[20]") );             /* 지폐잔류 */

        niceMng.setTermMode ( parsed.getString("atm_monitor").substring(0, 1) );  /* 터미널모드 */
        niceMng.setGggp     ( parsed.getString("atm_monitor").substring(1, 2) );  /* 금고개폐 */
        niceMng.setGgjg     ( parsed.getString("atm_monitor").substring(2, 3) );  /* 금고잠금 */
        niceMng.setGgjd     ( parsed.getString("atm_monitor").substring(3, 4) );  /* 금고진동 */
        niceMng.setGgygj    ( parsed.getString("atm_monitor").substring(4, 5) );  /* 금고열감지 */
        niceMng.setGgjc     ( parsed.getString("atm_monitor").substring(5, 6) );  /* 문열림 */
        niceMng.setHshtc    ( parsed.getString("atm_monitor").substring(6, 7) );  /* 회수함탈착 */
        niceMng.setJp1tc    ( parsed.getString("atm_monitor").substring(7, 8) );  /* 지폐함1탈착 */
        niceMng.setJp2tc    ( parsed.getString("atm_monitor").substring(8, 9) );  /* 지폐함2탈착 */
        niceMng.setJp3tc    ( parsed.getString("atm_monitor").substring(9, 10) ); /* 지폐함3탈착 */
        niceMng.setJp4tc    ( parsed.getString("atm_monitor").substring(10, 11) );/* 지폐함4탈착 */
        niceMng.setCdpd     ( parsed.getString("atm_monitor").substring(11, 12) );/* 카드판독기 */
        niceMng.setPass1    ( parsed.getString("atm_monitor").substring(12, 13) );/* 암호장비 */
        niceMng.setDummy11  ( parsed.getString("atm_monitor").substring(13, 14) );/* 통장부 */
        niceMng.setDummy12  ( parsed.getString("atm_monitor").substring(14) );    /* 더미12 */

        niceMng.setPgmVer  ( parsed.getString("pgm_version") );                   /* 기기프로그램 버젼 */
        //niceMng.setSerialNo( parsed.getString("serial_no"  ) );                   /* 시리얼 번호 */
        niceMng.setUpdateDate( safeData.getDSysDate() );
        try {
            niceMngMap.insertSelective( niceMng );
        }
        catch ( Exception e ){
            logger.info( ">>> [DBInsertNICE_MNG] (T_CT_NICE_MNG) INSERT ERROR [{}]",e.getMessage() );
            logger.info( "         J_DATE[{}] J_TIME[{}]", niceMng.getjDate(), niceMng.getjTime() );
            logger.info( "         MAC_NO[{}] NET_S [{}]", niceMng.getMacNo(), niceMng.getNetS() );
            throw e;
        }
    }
}

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

import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.lpad;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCall;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNoti;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorRcpt;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.ErrorState;

@Service("in05000110")
public class In05000110Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In05000110Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TMacInfo macInfo = new TMacInfo();
        TCtErrorBasic errBasic = new TCtErrorBasic();
        TCtErrorRcpt errRcpt = new TCtErrorRcpt();
        TCtErrorNoti errNoti = new TCtErrorNoti();
        TCtErrorCall errCall = new TCtErrorCall();
        TCtErrorTxn  errTxn  = new TCtErrorTxn();

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
        BeanUtils.copyProperties( errBasic, macInfo );
        errBasic.setCreateDate( parsed.getInt("create_date") );
        errBasic.setCreateTime( parsed.getString("create_time") );
        errBasic.setAtmState( parsed.getString("atm_mode") );
        errBasic.setOrgMsg( parsed.getString("memo") );

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
            errBasic.setTransDate( parsed.getString("create_date") );
            /*
             * 송신(거래)일자
             */
            errBasic.setOrgMsgNo( parsed.getString("create_time") );

            errBasic.setCreateDate( Integer.parseInt(safeData.getSysDate()) );
            errBasic.setCreateTime( safeData.getSysTime() );

            /*
             * 전문 응답은 수신 전문에서 보내므로 발생과 복구 시간을 위해 suBody.create_time을 현재시간으로 바꿔준다
             */
            parsed.setString("create_date", safeData.getSysDate() );
            parsed.setString("create_time", safeData.getSysTime() );
        }
        else {
            /*
             * 송신(거래)일자
             */
            errBasic.setTransDate( parsed.getString("CM.trans_date") );
            /*
             * 10. 전문추적번호
             */
            errBasic.setOrgMsgNo( parsed.getString("CM.trans_seq_no") );
        }
        /*
         * 업무구분 '11'-장애관리전문
         */
        errBasic.setFormatType( "11" );

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
        errState.setOrgCd( errBasic.getOrgCd() );
        errState.setBranchCd( errBasic.getBranchCd() );
        errState.setMacNo( errBasic.getMacNo() );
        byte[] retErrStates = comPack.getCurrentErrorState( errState );
        errState.setErrorStates( new String(retErrStates) );
        logger.info("CUR_ERROR_LIST[{}]", errState.getErrorStates() );

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
                      &&  (macInfo.getCheckYn() == null
                        || macInfo.getCheckYn().equals("0")
                        || macInfo.getCheckYn().length() == 0) ) {
                   logger.info(">>> [SaveErrState] 수표 미취급 기기 수표 관련 장애 수신 ... 무시...");
                   continue;
                }
                else if( (e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_50000
                        && !parsed.getString("cash_50000_yn").equals("2")
                        && parsed.getString("cash_50000_yn").length() > 0)
                      ||  (e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_100000
                        && !parsed.getString("cash_100000_yn").equals("2")
                        && parsed.getString("cash_100000_yn").length() > 0) ) {
                   logger.info(">>> [SaveErrState] 고액권 거래불가 기기 고액권 관련 장애 수신 ... 무시...");
                   continue;
                }

                /*
                 * 복구일 경우는 NE0**, 예보일 경우는 NE1**, 장애일 경우는 NE2**
                 */
                errBasic.setErrorCd( String.format("NE%c%s", parsed.getBytes("atm_state")[e.ordinal()], lpad(e.getErrorCd(), 2, "0")) );

                /*
                 * 예보 및 장애
                 */
                if( parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_NEAR
                ||  parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_END ) {
                    /*
                     * 하나은행 경우 장애는 발생시키지 않는다.
                     */
                    if( macInfo.getOrgCd().equals(MsgBrokerConst.HANAATMS_CODE) ) {
                        if( parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_NEAR )
                            comPack.insertErrBasic( errBasic,  errRcpt, errNoti, errCall, errTxn, macInfo, "");
                    }
                    else {
                        comPack.insertErrBasic( errBasic,  errRcpt, errNoti, errCall, errTxn, macInfo, "");
                    }
                }
                /*
                 * 복구
                 */
                else if( parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_CLEAR ) {
                    /*
                     *  1. 복구일자(발생일자)
                     */
                    errTxn.setRepairDate( parsed.getString("create_date") );
                    /*
                     *  2. 복구시간(발생시간)
                     */
                    errTxn.setRepairTime( parsed.getString("create_time") );

                    /*
                     * 복구 일때는 예보와 장애 모두를 복구 시킨다.
                     */
                    errBasic.setErrorCd( String.format("NE1%2s", lpad(e.getErrorCd(),2,"0")) );
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall,
                            errTxn, macInfo, retErrStates );
                    errBasic.setErrorCd( String.format("NE2%2s", lpad(e.getErrorCd(),2,"0")) );
                    comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall,
                            errTxn, macInfo, retErrStates );
                    /*
                     * 20100225 양유석주임요청 하나은행의 경우 현금부족이나 수표부족 상태 복구시
                     * 출동요청으로 들어온 현금부족(HN90B)와 수표부족(HN90E)도 복구
                     */
                    if( macInfo.getOrgCd().equals(MsgBrokerConst.HANAATMS_CODE) ) {
                        errBasic.setErrorCd("");
                        if( e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_CASH ) {
                            errBasic.setErrorCd("HN90B");
                            comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall,
                                    errTxn, macInfo, retErrStates );
                        }
                        else if( e == MsgBrokerConst.EnumOrgErrorState.IDX_ST_CHECK ) {
                            errBasic.setErrorCd("HN90E");
                            comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt, errNoti, errCall,
                                    errTxn, macInfo, retErrStates );
                        }
                    }
                }
                else if ( parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_SKIP1
                      ||   parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_SKIP2
                      ||   parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_SKIP3 ) {
                /*
                logger.info(">>> [SaveErrState] 상태무시");
                */
                }
                else {
                    logger.info(String.format(">>> [SaveErrState] 잘못된 상태코드 수신 %s-코드(%c) - ex)복구:0,예보:1,장애:2, 무시:' ' or 9",
                            e.getErrorName(), parsed.getBytes("atm_state")[e.ordinal()]) );
                }
            }
        }

        /*
         *  하나은행 일 경우
         */
        if( macInfo.getOrgCd().equals(MsgBrokerConst.HANAATMS_CODE) ) {
            /*
             * 개시전문
             */
            if( parsed.getString("error_hw_yn").equals(MsgBrokerConst.HWERR_OPEN) ) {
                /*
                 * 장애내용 관련 모든 H/W Error 복구( 상태 장애 제외 )
                 */
                /*
                 *  1. 복구일자(발생일자)
                 */
                errTxn.setRepairDate( parsed.getString("create_date") );
                /*
                 *  2. 복구시간(발생시간)
                 */
                errTxn.setRepairTime( parsed.getString("create_time") );
                comPack.insertUpdateMacOpen( safeData, macInfo, errBasic );
                comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_HW_ALL_CLEAR,
                        errBasic, errRcpt, errNoti, errCall,  errTxn, macInfo, retErrStates );

            }
        }
        else {
            /*
             * 개국
             */
            if( parsed.getString("error_hw_yn").equals(MsgBrokerConst.HWERR_OPEN) ) {
                /*
                 * 장애내용 관련 모든 H/W Error 복구( 상태 장애 제외 )
                 */
                /*
                 *  1. 복구일자(발생일자)
                 */
                errTxn.setRepairDate( parsed.getString("create_date") );
                /*
                 *  2. 복구시간(발생시간)
                 */
                errTxn.setRepairTime( parsed.getString("create_time") );
                comPack.insertUpdateMacOpen( safeData, macInfo, errBasic );
                comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_HW_ALL_CLEAR,
                        errBasic, errRcpt, errNoti, errCall,  errTxn, macInfo, retErrStates );
            }
            /*
             * 장애
             */
            else if( parsed.getString("error_hw_yn").equals(MsgBrokerConst.HWERR_ERROR) ) {
                errBasic.setErrorCd( parsed.getString("error_cd") );
                errBasic.setMadeErrCd( parsed.getString("error_mtc_cd") );
                /*
                 * 하나은행(구) 일 경우 정액수표부족(67) 일경우 수표 취급 여부를 체크하여 발생시킨다.
                 */
                if( macInfo.getOrgCd().equals(MsgBrokerConst.HANA_CODE)
                &&  errBasic.getErrorCd().equals("67")
                && (macInfo.getCheckYn() == null
                 || macInfo.getCheckYn().equals("0")
                 || macInfo.getCheckYn().length() == 0) ) {
                    logger.info(">>> [SaveErrState] 수표 미취급 기기 수표 관련 장애 수신 ... 무시...");
                    return;
                }
                comPack.insertErrBasic( errBasic,  errRcpt, errNoti, errCall, errTxn, macInfo, "");

                /*
                 * ERRMon 에서 만든 user 정의 장애( 나이스 발생 장애 ) 라면 응답 송신하지 않는다.
                 */
                if( errBasic.getErrorCd().equals("NE999")
                ||  errBasic.getErrorCd().equals("USR01") ) {
                    throw new MsgBrokerException("", -99);
                }
            }
            /*
             * 폐국
             */
            else if( parsed.getString("error_hw_yn").equals(MsgBrokerConst.HWERR_CLOSE) ) {
                logger.info(">>> [SaveErrState] 폐국거래 수신 ... 무시...");
            }
            /*
             *  H/W 장애 복구
             */
            else if( parsed.getString("error_hw_yn").equals(MsgBrokerConst.HWERR_CLEAR) ) {
                /*
                 *  1. 복구일자(발생일자)
                 */
                errTxn.setRepairDate( parsed.getString("create_date") );
                /*
                 *  2. 복구시간(발생시간)
                 */
                errTxn.setRepairTime( parsed.getString("create_time") );

                errBasic.setErrorCd( parsed.getString("error_cd") );

                /*
                 * 출동알림 모니터 장애의 경우 H/W장애 '0'에 뒷부분 상태가 따로 들어온다. 20110502'
                 */
                if( parsed.getString("CM.msg_id").equals("ALARM") ) {

                    if( parsed.getString("agency_off").equals("1") ) {
                        errTxn.setRepairDate(null);
                        errTxn.setRepairTime(null);
                        errBasic.setErrorCd( MsgBrokerConst.ALARM_AGENCY_OFF );
                        comPack.insertErrBasic( errBasic,  errRcpt, errNoti, errCall, errTxn, macInfo, "");
                    }
                    else if( parsed.getString("agency_off").equals("0") ) {
                        errBasic.setErrorCd( MsgBrokerConst.ALARM_AGENCY_OFF );
                        comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_ONLY_HW_CLEAR,
                                errBasic, errRcpt, errNoti, errCall,  errTxn, macInfo, retErrStates );
                    }
                    if( parsed.getString("player_off").equals("1") ) {
                        errTxn.setRepairDate(null);
                        errTxn.setRepairTime(null);
                        errBasic.setErrorCd( MsgBrokerConst.ALARM_PLAYER_OFF );
                        comPack.insertErrBasic( errBasic,  errRcpt, errNoti, errCall, errTxn, macInfo, "");
                    }
                    else if( parsed.getString("player_off").equals("0") ) {
                        errBasic.setErrorCd( MsgBrokerConst.ALARM_PLAYER_OFF );
                        comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_ONLY_HW_CLEAR,
                                errBasic, errRcpt, errNoti, errCall,  errTxn, macInfo, retErrStates );
                    }
                    if( parsed.getString("settop_off").equals("1") ) {
                        errTxn.setRepairDate(null);
                        errTxn.setRepairTime(null);
                        errBasic.setErrorCd( MsgBrokerConst.ALARM_SETTOP_OFF );
                        comPack.insertErrBasic( errBasic,  errRcpt, errNoti, errCall, errTxn, macInfo, "");
                    }
                    else if( parsed.getString("settop_off").equals("0") ) {
                        errBasic.setErrorCd( MsgBrokerConst.ALARM_SETTOP_OFF );
                        comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_ONLY_HW_CLEAR,
                                errBasic, errRcpt, errNoti, errCall,  errTxn, macInfo, retErrStates );
                    }
                    if( parsed.getString("non_schdule").equals("1") ) {
                        errTxn.setRepairDate(null);
                        errTxn.setRepairTime(null);
                        errBasic.setErrorCd( MsgBrokerConst.ALARM_NON_SCHDULE );
                        comPack.insertErrBasic( errBasic,  errRcpt, errNoti, errCall, errTxn, macInfo, "");
                    }
                    else if( parsed.getString("non_schdule").equals("0") ) {
                        errBasic.setErrorCd( MsgBrokerConst.ALARM_NON_SCHDULE );
                        comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_ONLY_HW_CLEAR,
                                errBasic, errRcpt, errNoti, errCall,  errTxn, macInfo, retErrStates );
                    }
                    if( parsed.getString("non_file").equals("1") ) {
                        errTxn.setRepairDate(null);
                        errTxn.setRepairTime(null);
                        errBasic.setErrorCd( MsgBrokerConst.ALARM_NON_FILE );
                        comPack.insertErrBasic( errBasic,  errRcpt, errNoti, errCall, errTxn, macInfo, "");
                    }
                    else if( parsed.getString("non_file").equals("0") ) {
                        errBasic.setErrorCd( MsgBrokerConst.ALARM_NON_FILE );
                        comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_ONLY_HW_CLEAR,
                                errBasic, errRcpt, errNoti, errCall,  errTxn, macInfo, retErrStates );
                    }
                    /*
                     * 출동알림 상태는 응답송신 하지않는다.
                     */
                    throw new MsgBrokerException("", -99);
                }
                if( errBasic.getOrgCd().equals(MsgBrokerConst.KBST_CODE)
                 && parsed.getString("error_cd").equals("00000") ) {
                    comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_HW_ALL_CLEAR,
                            errBasic, errRcpt, errNoti, errCall,  errTxn, macInfo, retErrStates );
                }
                else if( (errBasic.getOrgCd().equals(MsgBrokerConst.KJB_CODE)
                        || errBasic.getOrgCd().equals(MsgBrokerConst.WRATMS_CODE)
                        || errBasic.getOrgCd().equals(MsgBrokerConst.KNATMS_CODE))
                      &&  parsed.getString("error_cd").length() == 0 ) {
                    /*
                     * hw장애 복구 일 경우
                     */
                    comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_HW_ALL_CLEAR,
                            errBasic, errRcpt, errNoti, errCall,  errTxn, macInfo, retErrStates );
                }
                else  {
                    logger.debug("retErrState = {}", retErrStates);
                    comPack.updateErrBasic(  safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_ONLY_HW_CLEAR,
                            errBasic, errRcpt, errNoti, errCall,  errTxn, macInfo, retErrStates );
                }
                /*~
                 * ERRMon 에서 만든 user 정의 장애( 나이스 발생 장애 ) 라면 응답 송신하지 않는다.
                 */
                if( errBasic.getErrorCd().equals("NE999") ) {
                    throw new MsgBrokerException("", -99);
                }
            }
            else {
                logger.info(">>> [SaveErrState] 잘못된 HW 장애 여부 코드 수신 코드(%c)", parsed.getBytes("error_hw_yn")[0] );
            }
        }
    }
}

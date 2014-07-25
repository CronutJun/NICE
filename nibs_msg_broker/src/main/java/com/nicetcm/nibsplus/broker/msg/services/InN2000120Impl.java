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
import com.nicetcm.nibsplus.broker.msg.mapper.TFnBrandSetStateMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnRcCntMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCall;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNoti;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorRcpt;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCtNiceMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.ErrorState;
import com.nicetcm.nibsplus.broker.msg.model.TCmMac;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandSetState;
import com.nicetcm.nibsplus.broker.msg.model.TFnRcCnt;


@Service("inN2000120")
public class InN2000120Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN2000120Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TCtNiceMngMapper niceMngMap;
    @Autowired private TFnBrandSetStateMapper fnBrandSetStateMap;
    @Autowired private TFnRcCntMapper fnRcCntMap;
    
    private enum EnumNHME {
        IDX_HW_LINE_ERR,        /* 0 - 통신장애         */
        IDX_HW_CASH_OUT,        /* 1 - 지폐방출기        */
        IDX_HW_CARD_READ,       /* 2 - 카드판독기        */
        IDX_HW_SPECS,           /* 3 - 명세표              */
        IDX_HW_JOURNAL,         /* 4 - 감사용지         */
        IDX_HW_INPUT_BOX,       /* 5 - 입금함              */
        IDX_HW_SYS_DISK,        /* 6 - SYSTEM DISK      */
        IDX_HW_BANK_BOOK,       /* 7 - 통장정리부        */
        IDX_HW_SYS_CONTROLOR,   /* 8 - SYSTEM 제어부       */
        IDX_HW_DEAL_LIST,       /* 9 - 거래내역출력부  */
        IDX_HW_ENCODE_MAC,      /* 10- 암호화장비        */
        IDX_HW_CASHIN,          /* 11- 지폐미수취        */
        IDX_HW_T_MONEY,         /* 12- T-Money 모듈상태 */
        IDX_HW_DONGGLEI,        /* 13- 동글이              */
        IDX_HW_DVR_ERR,         /* 14- 화상카메라        */
        IDX_HW_INPUT_CHECK,     /* 15- 수표입금부        */
        IDX_HW_OUT_CHECK,       /* 16- 수표출금부        */
        IDX_HW_INPUT_BOX_50000, /* 17- 오만원입금함       */
        IDX_HW_INPUT_BOX_100000,/* 18- 십만원입금함       */
        IDX_HW_IMSI,            /* 19- 임시               */
        IDX_HW_REMAIN_MONEY,    /* 20- 지폐잔류         */
    };
    
    @Override
    public void inMsgBizProc( MsgBrokerData safeData, MsgParser parsed ) throws Exception {
        
        TMacInfo macInfo = new TMacInfo();
        TCtErrorBasic errBasic = new TCtErrorBasic();
        TCtErrorRcpt errRcpt = new TCtErrorRcpt();
        TCtErrorNoti errNoti = new TCtErrorNoti();
        TCtErrorCall errCall = new TCtErrorCall();
        TCtErrorTxn  errTxn  = new TCtErrorTxn();
       
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

        /*
         * 점주관리 기기의 경우 t_ct_nice_mng에만 저장하고 장애 발생 하지 않는다.
         * 점주관리 기기는 dept_cd like '3%' 인 기기
         * 2014.04.02
         */
        if( macInfo.getDeptCd().substring(0, 1).equals("3") ) {
            logger.info( "점주관리기기-[{}] skip", parsed.getString("mac_no") );
            return;
        }

        errBasic.setCreateDate( parsed.getInt("create_date") );                       /*  발생시간        */
        errBasic.setCreateTime( parsed.getString("create_time") );                    /*  발생시간        */
        errBasic.setOrgCd( macInfo.getOrgCd() );                                      /*  기관코드        */
        errBasic.setBranchCd( macInfo.getBranchCd() );                                /*  지점코드        */
        errBasic.setMacNo( macInfo.getMacNo() );                                      /*  기번            */
        errBasic.setSiteCd( macInfo.getSiteCd() );                                    /* 사이트코드       */
        errBasic.setDeptCd( macInfo.getDeptCd() );
        errBasic.setOfficeCd( macInfo.getOfficeCd() );
        errBasic.setTeamCd( macInfo.getTeamCd()) ;
        errBasic.setOrgMsgNo( parsed.getString("CM.trans_seq_no") );                 /* 10. 전문추적번호   */
        
        /*
         *  ATM 감시 전문 
         */
        ErrorState errState = new ErrorState();
        errState.setMacType( MsgBrokerConst.CURERR_NICE );
        errState.setOrgCd( errBasic.getOrgCd() );
        errState.setMacNo( errBasic.getMacNo() );
        
        byte[] retErrState = comPack.getCurrentErrorState( errState );
        logger.info(" CUR_ERROR_LIST[{}]", errState.getErrorStates() );
        
        /*
         * 개시전문 
         */
        logger.info( "network_info[{}]", parsed.getString("network_info") );
        if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_START) ) {
            insertNiceOpenData( safeData, parsed, macInfo, retErrState );
            
            /*
             * 608(회선장애) 발생후 302(회선복구) 발생하지않고 301(장애), 001(개국) 이 발생하면 
             * 회선장애를 복구 시킨다. 
             */
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_LINE_ERROR );
            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                    errNoti, errCall, errTxn, macInfo, retErrState );
            
            /*
             * 310(AC전원차단) 301(장애), 001(개국) 이 발생하면 
             * AC전원차단 복구 시킨다. 
             */
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_AC_ERROR );
            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                    errNoti, errCall, errTxn, macInfo, retErrState );
        }
        /*
         * 회선장애 
         */
        else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_LINE_ERR) ) {
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_LINE_ERROR );
            comPack.insertErrBasic( errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );
        }
        /*
         * AC전원차단 
         * AC 전원차단의 경우 회선장애와 같이 이후 상태 무시하고 장애 발생 처리 후 SKIP    
         */
        else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_AC_ERR) ) {
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_AC_ERROR );
            comPack.insertErrBasic( errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );
        }
        /*
         * 회선장애복구 
         */
        else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_LINE_CLEAR) ) {
            errBasic.setErrorCd( MsgBrokerConst.NICE_STATE_LINE_CLEAR );
            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                    errNoti, errCall, errTxn, macInfo, retErrState );
        }
        /*
         * 트란에서의 나이스 발생 장애 복구 요청 처리   
         */
        else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_USER_ERR_REPAIR) ) {
            
            /*
             * 유저 정의 에러 일때는 이것만 처리 하고 리턴 한다 
             * --> 나머지 장애 관련 데이타는 임의로 만든 데이타 이기 때문에 처리하면 안된다 
             */
            if( !parsed.getString("user_made_err").substring(0, 1).equals("0") ) {
                switch( parsed.getString("user_made_err").getBytes()[0] ) {
                    case '2' :
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N02 ); 
                        break;
                    case '3' :
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N03 ); 
                        break;
                    case '4' :
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N04 ); 
                        break;
                    case '5' :
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N05 ); 
                        break;
                    case '6' :
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N06 ); 
                        break;
                    case '7' : /* 무거래(특별) 추가 */
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N07 ); 
                        break;
                    case '8' :  /* 무거래(일반) 추가 */
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N08 ); 
                        break;
                    case 'a' :  /* IC 무거래 */
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N10 ); 
                        break;
                    case 'b' : /*11 픽에러(EMPTY) */
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N11 ); 
                        break;
                    case 'c' : /*12 현금부족(기준금액) */
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N12 ); 
                        break;
                    case 'd' : /*13 기준금액 없음*/
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N13 ); 
                        break;
                    case 'e' : /*14 현금부족 예보 */
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N14 ); 
                        break;
                    case 'f' : /*  금고침투 장애 15 => 금고진동은 user define 장애로 변경 */
                        /* 금고 침투는 유저 복구 없음 */
                        break;
                    default :
                        break;
                }
                if( errBasic.getErrorCd().length() > 0 ) {
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                            errNoti, errCall, errTxn, macInfo, retErrState );
                }
                for( EnumNHME enumNHME: EnumNHME.values() ) {
                    if( enumNHME.name().equals("IDX_HW_CASH_OUT" )           /* 1 지폐방출기 */
                    ||  enumNHME.name().equals("IDX_HW_CARD_READ")           /* 2 카드판독기 */
                    ||  enumNHME.name().equals("IDX_HW_SPECS"    )           /* 3 명세표       */
                    ||  enumNHME.name().equals("IDX_HW_INPUT_BOX")           /* 5 입금함       */
                    ||  enumNHME.name().equals("IDX_HW_BANK_BOOK")           /* 7 통장정리부 */
                    ||  enumNHME.name().equals("IDX_HW_DEAL_LIST")           /* 9 거래내역출력부 */
                    ||  enumNHME.name().equals("IDX_HW_CASHIN"   )           /* 11 지폐미수취     */
                        /* 
                         * 2006.11.17 계약 만료에 의해 동글이 장애 무시
                         * i == IDX_HW_DONGGLEI  ||   /+ 13 동글이+/
                         * 2006.11.17 DVR 장애 관제 하지 않음 .. 무시
                         * 2007.02.23 DVR 장애 관제는 하지 않으나 조건조회에서 조회 가능하도록 발생과 동시에 복구 처리 - 운총요청
                         */
                    ||  enumNHME.name().equals("IDX_HW_DVR_ERR"         )    /* 14 DVR   */
                    ||  enumNHME.name().equals("IDX_HW_INPUT_CHECK"     )    /* 15- 수표입금부        */
                    ||  enumNHME.name().equals("IDX_HW_OUT_CHECK"       )    /* 16- 수표출금부        */
                    ||  enumNHME.name().equals("IDX_HW_INPUT_BOX_50000" )    /* 17- 오만원입금함       */
                    ||  enumNHME.name().equals("IDX_HW_INPUT_BOX_100000")    /* 18- 십만원입금함       */
                    ||  enumNHME.name().equals("IDX_HW_REMAIN_MONEY"    ) ) {/* 19- 지폐잔류         */
                        errBasic.setErrorCd( saNiceErrState[enumNHME.ordinal()] );
                        if( parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal()))
                                            .substring(0,1).equals(MsgBrokerConst.NICE_HW_NEARERROR)
                        ||  parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal()))
                                            .substring(0,1).equals(MsgBrokerConst.NICE_HW_ERROR) ) {
                            /*
                             * 2007.02.12 지폐방출기, 카드 판독기의 경우 '3'인 경우는 무시,  
                             * '4'인경우만 장애 발생하도록 수정                            
                             * 2007.02.23. FKM 기기가 지폐방출장애를 3->4로 먼저 변경 후 적용하기위해 임시 주석처리 
                             */
                            if( (enumNHME.name().equals("IDX_HW_CASH_OUT") || enumNHME.name().equals("IDX_HW_CARD_READ"))
                             && parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal()))
                                                .substring(0,1).equals(MsgBrokerConst.NICE_HW_NEARERROR) ) {
                                continue;
                            }

                            errBasic.setErrorCd( parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal())) );
                            comPack.insertErrBasic( errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );

                            errBasic.setMadeErrCd(null);

                            /*
                             * 2007.02.23 DVR 장애 관제는 하지 않으나 조건조회에서 조회 가능하도록 발생과 동시에 복구 처리 - 운총요청 
                             */
                            if( enumNHME.name().equals("IDX_HW_DVR_ERR") ) {
                                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                                        errNoti, errCall, errTxn, macInfo, retErrState );
                            }
                        }
                        else if( parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal()))
                                .substring(0,1).equals(MsgBrokerConst.NICE_HW_GOOD) ) {
                            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                                    errNoti, errCall, errTxn, macInfo, retErrState );
                        }
                    }
                    else
                        continue;
                }
                for( EnumNHME enumNHME: EnumNHME.values() ) {
                    if( enumNHME.name().equals("IDX_HW_CASH_OUT" )           /* 1 지폐방출기 */
                    ||  enumNHME.name().equals("IDX_HW_CARD_READ")           /* 2 카드판독기 */
                    ||  enumNHME.name().equals("IDX_HW_SPECS"    )           /* 3 명세표       */
                    ||  enumNHME.name().equals("IDX_HW_INPUT_BOX")           /* 5 입금함       */
                    ||  enumNHME.name().equals("IDX_HW_BANK_BOOK")           /* 7 통장정리부 */
                    ||  enumNHME.name().equals("IDX_HW_DEAL_LIST")           /* 9 거래내역출력부 */
                    ||  enumNHME.name().equals("IDX_HW_CASHIN"   )           /* 11 지폐미수취     */
                        /* 
                         * 2006.11.17 계약 만료에 의해 동글이 장애 무시
                         * i == IDX_HW_DONGGLEI  ||   /+ 13 동글이+/
                         * 2006.11.17 DVR 장애 관제 하지 않음 .. 무시
                         * 2007.02.23 DVR 장애 관제는 하지 않으나 조건조회에서 조회 가능하도록 발생과 동시에 복구 처리 - 운총요청
                         */
                    ||  enumNHME.name().equals("IDX_HW_DVR_ERR"         )    /* 14 DVR   */
                    ||  enumNHME.name().equals("IDX_HW_INPUT_CHECK"     )    /* 15- 수표입금부        */
                    ||  enumNHME.name().equals("IDX_HW_OUT_CHECK"       )    /* 16- 수표출금부        */
                    ||  enumNHME.name().equals("IDX_HW_INPUT_BOX_50000" )    /* 17- 오만원입금함       */
                    ||  enumNHME.name().equals("IDX_HW_INPUT_BOX_100000")    /* 18- 십만원입금함       */
                    ||  enumNHME.name().equals("IDX_HW_REMAIN_MONEY"    ) ) {/* 19- 지폐잔류         */
                        errBasic.setErrorCd( saNiceErrState[enumNHME.ordinal()] );
                        if( parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal()))
                                            .substring(0,1).equals(MsgBrokerConst.NICE_HW_NEARERROR)
                        ||  parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal()))
                                            .substring(0,1).equals(MsgBrokerConst.NICE_HW_ERROR) ) {
                            /*
                             * 2007.02.12 지폐방출기, 카드 판독기의 경우 '3'인 경우는 무시,  
                             * '4'인경우만 장애 발생하도록 수정                            
                             * 2007.02.23. FKM 기기가 지폐방출장애를 3->4로 먼저 변경 후 적용하기위해 임시 주석처리 
                             */
                            if( (enumNHME.name().equals("IDX_HW_CASH_OUT") || enumNHME.name().equals("IDX_HW_CARD_READ"))
                             && parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal()))
                                                .substring(0,1).equals(MsgBrokerConst.NICE_HW_NEARERROR) ) {
                                continue;
                            }

                            errBasic.setErrorCd( parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal())) );
                            comPack.insertErrBasic( errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );

                            errBasic.setMadeErrCd(null);

                            /*
                             * 2007.02.23 DVR 장애 관제는 하지 않으나 조건조회에서 조회 가능하도록 발생과 동시에 복구 처리 - 운총요청 
                             */
                            if( enumNHME.name().equals("IDX_HW_DVR_ERR") ) {
                                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                                        errNoti, errCall, errTxn, macInfo, retErrState );
                            }
                        }
                        else if( parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal()))
                                .substring(0,1).equals(MsgBrokerConst.NICE_HW_GOOD) ) {
                            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                                    errNoti, errCall, errTxn, macInfo, retErrState );
                        }
                    }
                    else
                        continue;
                }
            }
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
    
    /**
     * NICE-개국 데이타 insert 위한 데이타 복사
     * 
     * @author KDJ originated by 방혜진
     * @since 2006/09/04
     * @param parsed  파싱된 전문
     * @param macInfo 기기정보
     * @param curErrList 에러상태정보
     * @throws Exception
     */
    private void insertNiceOpenData( MsgBrokerData safeData, MsgParser parsed, TMacInfo macInfo, byte[] curErrList ) throws Exception {
        
        TCtErrorBasic errBasic = new TCtErrorBasic();
        TCtErrorRcpt errRcpt = new TCtErrorRcpt();
        TCtErrorNoti errNoti = new TCtErrorNoti();
        TCtErrorCall errCall = new TCtErrorCall();
        TCtErrorTxn  errTxn  = new TCtErrorTxn();

        errBasic.setOrgCd( MsgBrokerConst.NICE_CODE );                      /* 2. 기관코드              */
        errBasic.setBranchCd( MsgBrokerConst.NICE_BRCH_CD);                 /* 3. 지점코드              */
        errBasic.setMacNo( parsed.getString("mac_no") );                    /* 4. 기번                  */
        errBasic.setCreateDate( parsed.getInt("create_date") );
        errBasic.setCreateTime( parsed.getString("create_time") );          /* 8. 발생시간              */

        /*
         *  복구를 위한 데이타
         */
        errTxn.setRepairDate( parsed.getString("create_date") );           /* 복구일자(장애발생일자)    */
        errTxn.setRepairTime( parsed.getString("create_time") );           /* 복구시간                  */

        /*
         *  나이스는 개국 시 HW 장애 복구 하지 않는다.
         *  단 회선장애 608은 복구 한다.
         */
        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_LINE_ERROR );       /* 6. 장애코드              */;

        TCmMac cmMac = new TCmMac();
        cmMac.setMacVer( parsed.getString("pgm_version") );
        cmMac.setSerialNo( parsed.getString("serial_no") );
        cmMac.setMacAddress( parsed.getString("mac_address") );
        comPack.updateMacInfo( safeData, macInfo, cmMac );
        comPack.insertUpdateMacOpen( safeData,  macInfo, errBasic );
        comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_HW_ONE_CLEAR,
                errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, curErrList );

        /* 
         * 20110719 브랜드제휴기관 정보 저장
         */
        if( parsed.getString("brand_org_cd").length() > 0 ) {
            insertUpdateBrandOrg( safeData, parsed );
        }

        /*
         *  20110719 러시앤캐시 집계 데이터 저장
         *  20110719 브랜드제휴기관 정보 저장 
         */
        if( parsed.getString("rc_deal_date").length() > 0 ) {
            insertUpdateRCCnt( safeData, parsed );
        }
    }
    
    private void insertUpdateBrandOrg(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TFnBrandSetState fnBrandSetState = new TFnBrandSetState();
        
        fnBrandSetState.setBrandOrgCd( parsed.getString("brand_org_cd") );
        fnBrandSetState.setMacNo( parsed.getString("mac_no") );
        fnBrandSetState.setUpdateDate( safeData.getDSysDate() );
        
        try {
            fnBrandSetStateMap.insert( fnBrandSetState );
        }
        catch (org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnBrandSetStateMap.updateByPrimaryKeySelective( fnBrandSetState );
            }
            catch (Exception e) {
                logger.info( ">>> [DBInsertUpdateBrandOrg] (T_FN_BRAND_SET_STATE) UPDATE ERROR [{}]", e.getLocalizedMessage());
                throw e;
            }
        }
        catch ( Exception e ) {
            logger.info( ">>> [DBInsertUpdateBrandOrg] (T_FN_BRAND_SET_STATE) INSERT ERROR [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }
    
    private void insertUpdateRCCnt(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TFnRcCnt fnRcCnt = new TFnRcCnt();
        
        fnRcCnt.setDealDate( parsed.getString("rc_deal_date") );
        fnRcCnt.setMacNo( parsed.getString("mac_no") );
        fnRcCnt.setType1Cnt( parsed.getInt("rc_type1_cnt") );
        fnRcCnt.setType2Cnt( parsed.getInt("rc_type2_cnt") );
        fnRcCnt.setType3Cnt( parsed.getInt("rc_type3_cnt") );
        fnRcCnt.setType4Cnt( parsed.getInt("rc_type4_cnt") );

        try {
            fnRcCntMap.insert( fnRcCnt );
        }
        catch (org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnRcCntMap.updateByPrimaryKeySelective( fnRcCnt );
            }
            catch (Exception e) {
                logger.info( ">>> [DBInsertUpdateRCCnt] (T_FN_RC_CNT) UPDATE ERROR [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch ( Exception e ) {
            logger.info( ">>> [DBInsertUpdateRCCnt] (T_FN_RC_CNT) INSERT ERROR [{}]\n", e.getLocalizedMessage() );
            throw e;
        }
    }
}
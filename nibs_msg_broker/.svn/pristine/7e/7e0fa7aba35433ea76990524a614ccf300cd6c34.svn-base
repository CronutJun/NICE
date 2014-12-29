package com.nicetcm.nibsplus.broker.msg.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 나이스 장애처리
 *
 * <pre>
 * MngNS_SaveNiceErrState
 * </pre>
 *
 * @author   K.D.J
 * @since    2014.07.23
 *
 */


import java.nio.ByteBuffer;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.nstr;
import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.substr;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerProducer;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtNiceMngMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnBrandSetStateMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnMacMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnRcCntMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmMacMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorBasicMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorTxnMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtRemoteHistoryMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorCustInfoMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSiteMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnArpcFaultMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCall;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNoti;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorRcpt;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxnSpec;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCtNiceMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtNiceMac;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasicSpec;
import com.nicetcm.nibsplus.broker.msg.model.ErrorState;
import com.nicetcm.nibsplus.broker.msg.model.TCmMac;
import com.nicetcm.nibsplus.broker.msg.model.TCmMacKey;
import com.nicetcm.nibsplus.broker.msg.model.TCmMacSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandSetState;
import com.nicetcm.nibsplus.broker.msg.model.TFnRcCnt;
import com.nicetcm.nibsplus.broker.msg.model.TFnMac;
import com.nicetcm.nibsplus.broker.msg.model.TFnMacKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtRemoteHistory;
import com.nicetcm.nibsplus.broker.msg.model.TCtRemoteHistorySpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCustInfo;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;
import com.nicetcm.nibsplus.broker.msg.model.TCmSite;
import com.nicetcm.nibsplus.broker.msg.model.TCmSiteKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnArpcFault;


@Service("inN2000120")
public class InN2000120Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN2000120Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCtNiceMngMapper niceMngMap;
    @Autowired private TFnBrandSetStateMapper fnBrandSetStateMap;
    @Autowired private TFnRcCntMapper fnRcCntMap;
    @Autowired private TCmMacMapper cmMacMap;
    @Autowired private TFnMacMapper fnMacMap;
    @Autowired private TCtErrorBasicMapper errBasicMap;
    @Autowired private TCtErrorTxnMapper errTxnMap;
    @Autowired private TCtRemoteHistoryMapper remoteHistoryMap;
    @Autowired private TCtErrorCustInfoMapper errCustInfoMap;
    @Autowired private TCmSiteMapper cmSiteMap;
    @Autowired private TFnArpcFaultMapper fnArpcFaultMap;


    private static final int CNT_CASH_BOX = 4; /* 총 지폐함 수 */

    private enum EnumNCBS {
        IDX_STATE_CASH_BOX1,    /* 0 - 지폐함 1     */
        IDX_STATE_CASH_BOX2,    /* 1 - 지폐함 2     */
        IDX_STATE_CASH_BOX3,    /* 2 - 지폐함 3     */
        IDX_STATE_CASH_BOX4,    /* 3 - 지폐함 4     */
        IDX_STATE_RECOLL_BOX,   /* 4 - 회수함       */
        IDX_STATE_SPECS,        /* 5 - 명세표       */
        IDX_STATE_JOURNAL,      /* 6 - 감사용지     */
        IDX_STATE_INPUT_BOX,    /* 7 - 입금함       */
        IDX_STATE_CHECK_BOX     /* 8 - 수표함       */
    }

    private enum EnumNHME {
        IDX_HW_LINE_ERR,        /* 0 - 통신장애         */
        IDX_HW_CASH_OUT,        /* 1 - 지폐방출기       */
        IDX_HW_CARD_READ,       /* 2 - 카드판독기       */
        IDX_HW_SPECS,           /* 3 - 명세표           */
        IDX_HW_JOURNAL,         /* 4 - 감사용지         */
        IDX_HW_INPUT_BOX,       /* 5 - 입금함           */
        IDX_HW_SYS_DISK,        /* 6 - SYSTEM DISK      */
        IDX_HW_BANK_BOOK,       /* 7 - 통장정리부       */
        IDX_HW_SYS_CONTROLOR,   /* 8 - SYSTEM 제어부    */
        IDX_HW_DEAL_LIST,       /* 9 - 거래내역출력부   */
        IDX_HW_ENCODE_MAC,      /* 10- 암호화장비       */
        IDX_HW_CASHIN,          /* 11- 지폐미수취       */
        IDX_HW_T_MONEY,         /* 12- T-Money 모듈상태 */
        IDX_HW_DONGGLEI,        /* 13- 동글이           */
        IDX_HW_DVR_ERR,         /* 14- 화상카메라       */
        IDX_HW_INPUT_CHECK,     /* 15- 수표입금부       */
        IDX_HW_OUT_CHECK,       /* 16- 수표출금부       */
        IDX_HW_INPUT_BOX_50000, /* 17- 오만원입금함     */
        IDX_HW_INPUT_BOX_100000,/* 18- 십만원입금함     */
        IDX_HW_RPC,             /* 19- RPC 모듈 상태    */
        IDX_HW_REMAIN_MONEY     /* 20- 지폐잔류         */
    };

    private enum EnumNM {
        IDX_MON_TERM_MODE,      /* 0 - Terminal Mode    */
        IDX_MON_OPNE_SAFE_YN,   /* 1 - 금고개폐여부     */
        IDX_MON_LOCK_SAFE_YN,   /* 2 - 금고잠금여부     */
        IDX_MON_SHAKE_SAFE_YN,  /* 3 - 금고진동여부     */
        IDX_MON_SAFE_HEAT,      /* 4 - 금고열감지       */
        IDX_MON_OPEN_DOOR,      /* 5 - 문열림           */
        IDX_MON_RECALL_CASH,    /* 6 - 지폐회수함       */
        IDX_MON_CASH_BOX1,      /* 7 - 지폐함 1         */
        IDX_MON_CASH_BOX2,      /* 8 - 지폐함 2         */
        IDX_MON_CASH_BOX3,      /* 9 - 지폐함 3         */
        IDX_MON_CASH_BOX4,      /* 10- 지폐함 4         */
        IDX_MON_CARD_READ,      /* 11- 카드판독기       */
        IDX_MON_ENCODE_MAC,     /* 12- 암호장비         */
        IDX_MON_BANK_BOOK       /* 13- 통장부           */
    };

    @Override
    public void inMsgBizProc( MsgBrokerData safeData, MsgParser parsed ) throws Exception {

        int iCashBox = 0, iCash = 0;
        int nRtn = -1;

        TMacInfo macInfo = new TMacInfo();
        TCtErrorBasic errBasic = new TCtErrorBasic();
        TCtErrorRcpt errRcpt = new TCtErrorRcpt();
        TCtErrorNoti errNoti = new TCtErrorNoti();
        TCtErrorCall errCall = new TCtErrorCall();
        TCtErrorTxn  errTxn  = new TCtErrorTxn();

        /**
         * 2014.12.17
         * CM.msg_id = 'sSERVER'이면 응답처리 하지 않는다.
         * KDJ
         */
        if( parsed.getString("CM.msg_id").equals("sSERVER") || parsed.getString("CM.msg_id").equals("TRANRPR") ) {
            safeData.setNoResponse( true );
        }
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
                MsgBrokerConst.NICE_ERROR_CASHIN_ERROR    , /* "131" 지폐미수취                */
                "",
                MsgBrokerConst.NICE_ERROR_DONGUL_ERROR    , /* "133" 동글이                    */
                MsgBrokerConst.NICE_ERROR_DVR_ERROR       , /* "134" DVR 2003.11.13 추가       */
                MsgBrokerConst.NICE_ERROR_INPUT_CHECK     , /* "135" 수표입금부                */
                MsgBrokerConst.NICE_ERROR_OUT_CHECK       , /* "136" 수표출금부                */
                MsgBrokerConst.NICE_ERROR_INPUT_BOX_50000 , /* "137" 오만원권입금부            */
                MsgBrokerConst.NICE_ERROR_INPUT_BOX_100000, /* "138" 십만원권입금부            */
                MsgBrokerConst.NICE_ERROR_RPC,              /* "151" RPC 모듈 상태 2014.05.20  */
                MsgBrokerConst.NICE_ERROR_REMAIN_MONEY      /* "139" 지폐잔류                  */
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
            logger.warn("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(), macInfo.getMacNm(),
                    macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );
            throw e;
        }

        errBasic.setCreateDate( parsed.getString("create_date") );                    /*  발생시간        */
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
        errState.setBranchCd( errBasic.getBranchCd() );
        errState.setMacNo( errBasic.getMacNo() );

        byte[] retErrState = comPack.getCurrentErrorState( errState );
        logger.warn(" CUR_ERROR_LIST[{}]", errState.getErrorStates() );

        /*
         * 점주관리 기기의 경우 t_ct_nice_mng에만 저장하고 장애 발생 하지 않는다.
         * 2014.04.02
         * 점주관리 기기라도 문열림(상판열림)은 장애 발생시키도록 함. 2014.06.30
         * 점주관리 기기의 기기정보 저장을 위해 개국신호는 처리하도록 함. 금진선과장 요청 2014.08.28
         */
        /*
         *  점주관리 기기이면서 개국전문이라면 아래 일반 CD-VAN 개국 프로세스를 타도록 한다.
         */
         if( macInfo.getStoreKeeperYn().equals("1")
         &&  !parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_START) ) {
             /*
              *  회선장애 전문이 아닌경우 즉 상태전문인 경우에 작업모드가 아닌 문열림 상태만 발생/복구 처리
              */
             /*
              *  점주모드 suBody.atm_monitor[IDX_MON_TERM_MODE] == '5' 인경우도 상판열림 장애 발생시킴
              */
             if( !parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_LINE_ERR)
             &&  !substr(parsed.getString("atm_monitor"), EnumNM.IDX_MON_TERM_MODE.ordinal(), EnumNM.IDX_MON_TERM_MODE.ordinal()+1).equals("") /* 2014/12/22 BHJ요청 추가 */
             &&  !substr(parsed.getString("atm_monitor"), EnumNM.IDX_MON_TERM_MODE.ordinal(), EnumNM.IDX_MON_TERM_MODE.ordinal()+1).equals("1") ) {

                 errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_ATMWATCH_OPENDOOR_ERROR );

                 if( substr(parsed.getString("atm_monitor"), EnumNM.IDX_MON_OPEN_DOOR.ordinal(), EnumNM.IDX_MON_OPEN_DOOR.ordinal()+1).equals(MsgBrokerConst.NICE_NORMAL)
                 ||  substr(parsed.getString("atm_monitor"), EnumNM.IDX_MON_OPEN_DOOR.ordinal(), EnumNM.IDX_MON_OPEN_DOOR.ordinal()+1).equals(MsgBrokerConst.NICE_NO_SET) ) {
                     TCtErrorBasic errBasicL = new TCtErrorBasic();
                     TCtErrorRcpt errRcptL = new TCtErrorRcpt();
                     TCtErrorNoti errNotiL = new TCtErrorNoti();
                     TCtErrorCall errCallL = new TCtErrorCall();
                     TCtErrorTxn  errTxnL  = new TCtErrorTxn();

                     BeanUtils.copyProperties(  errBasicL, errBasic );
                     BeanUtils.copyProperties(  errRcptL,  errRcpt );
                     BeanUtils.copyProperties(  errNotiL,  errNoti );
                     BeanUtils.copyProperties(  errCallL,  errCall );
                     BeanUtils.copyProperties(  errTxnL,   errTxn );
                     comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                             errNotiL, errCallL, errTxnL, macInfo, retErrState );
                 }
                 else {
                     TCtErrorBasic errBasicL = new TCtErrorBasic();
                     TCtErrorRcpt errRcptL = new TCtErrorRcpt();
                     TCtErrorNoti errNotiL = new TCtErrorNoti();
                     TCtErrorCall errCallL = new TCtErrorCall();
                     TCtErrorTxn  errTxnL  = new TCtErrorTxn();

                     BeanUtils.copyProperties(  errBasicL, errBasic );
                     BeanUtils.copyProperties(  errRcptL,  errRcpt );
                     BeanUtils.copyProperties(  errNotiL,  errNoti );
                     BeanUtils.copyProperties(  errCallL,  errCall );
                     BeanUtils.copyProperties(  errTxnL,   errTxn );
                     comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                 }
             }

             logger.warn( "점주관리기기-[%s] skip", parsed.getString("mac_no") );
             return;
         }

        /*
         * 개시전문
         */
        logger.warn( "network_info[{}]", parsed.getString("network_info") );
        if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_START) ) {
            insertNiceOpenData( safeData, parsed, macInfo, retErrState );

            /*
             * 608(회선장애) 발생후 302(회선복구) 발생하지않고 301(장애), 001(개국) 이 발생하면
             * 회선장애를 복구 시킨다.
             */
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_LINE_ERROR );
            TCtErrorBasic errBasicL = new TCtErrorBasic();
            TCtErrorRcpt errRcptL = new TCtErrorRcpt();
            TCtErrorNoti errNotiL = new TCtErrorNoti();
            TCtErrorCall errCallL = new TCtErrorCall();
            TCtErrorTxn  errTxnL  = new TCtErrorTxn();

            BeanUtils.copyProperties(  errBasicL, errBasic );
            BeanUtils.copyProperties(  errRcptL,  errRcpt );
            BeanUtils.copyProperties(  errNotiL,  errNoti );
            BeanUtils.copyProperties(  errCallL,  errCall );
            BeanUtils.copyProperties(  errTxnL,   errTxn );
            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                    errNotiL, errCallL, errTxnL, macInfo, retErrState );

            /*
             * 310(AC전원차단) 301(장애), 001(개국) 이 발생하면
             * AC전원차단 복구 시킨다.
             */
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_AC_ERROR );

            BeanUtils.copyProperties(  errBasicL, errBasic );
            BeanUtils.copyProperties(  errRcptL,  errRcpt );
            BeanUtils.copyProperties(  errNotiL,  errNoti );
            BeanUtils.copyProperties(  errCallL,  errCall );
            BeanUtils.copyProperties(  errTxnL,   errTxn );
            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                    errNotiL, errCallL, errTxnL, macInfo, retErrState );
            nRtn = 0;
        }
        /*
         * 회선장애
         */
        else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_LINE_ERR) ) {
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_LINE_ERROR );
            TCtErrorBasic errBasicL = new TCtErrorBasic();
            TCtErrorRcpt errRcptL = new TCtErrorRcpt();
            TCtErrorNoti errNotiL = new TCtErrorNoti();
            TCtErrorCall errCallL = new TCtErrorCall();
            TCtErrorTxn  errTxnL  = new TCtErrorTxn();

            BeanUtils.copyProperties(  errBasicL, errBasic );
            BeanUtils.copyProperties(  errRcptL,  errRcpt );
            BeanUtils.copyProperties(  errNotiL,  errNoti );
            BeanUtils.copyProperties(  errCallL,  errCall );
            BeanUtils.copyProperties(  errTxnL,   errTxn );
            comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
            nRtn = 0;
        }
        /*
         * AC전원차단
         * AC 전원차단의 경우 회선장애와 같이 이후 상태 무시하고 장애 발생 처리 후 SKIP
         */
        else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_AC_ERR) ) {
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_AC_ERROR );
            TCtErrorBasic errBasicL = new TCtErrorBasic();
            TCtErrorRcpt errRcptL = new TCtErrorRcpt();
            TCtErrorNoti errNotiL = new TCtErrorNoti();
            TCtErrorCall errCallL = new TCtErrorCall();
            TCtErrorTxn  errTxnL  = new TCtErrorTxn();

            BeanUtils.copyProperties(  errBasicL, errBasic );
            BeanUtils.copyProperties(  errRcptL,  errRcpt );
            BeanUtils.copyProperties(  errNotiL,  errNoti );
            BeanUtils.copyProperties(  errCallL,  errCall );
            BeanUtils.copyProperties(  errTxnL,   errTxn );
            comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
            nRtn = 0;
        }
        /*
         * 회선장애복구
         */
        else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_LINE_CLEAR) ) {
            errBasic.setErrorCd( MsgBrokerConst.NICE_STATE_LINE_CLEAR );
            TCtErrorBasic errBasicL = new TCtErrorBasic();
            TCtErrorRcpt errRcptL = new TCtErrorRcpt();
            TCtErrorNoti errNotiL = new TCtErrorNoti();
            TCtErrorCall errCallL = new TCtErrorCall();
            TCtErrorTxn  errTxnL  = new TCtErrorTxn();

            BeanUtils.copyProperties(  errBasicL, errBasic );
            BeanUtils.copyProperties(  errRcptL,  errRcpt );
            BeanUtils.copyProperties(  errNotiL,  errNoti );
            BeanUtils.copyProperties(  errCallL,  errCall );
            BeanUtils.copyProperties(  errTxnL,   errTxn );
            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                    errNotiL, errCallL, errTxnL, macInfo, retErrState );
            nRtn = 0;
        }
        /*
         * 트란에서의 나이스 발생 장애 복구 요청 처리
         */
        else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_USER_ERR_REPAIR) ) {

            /*
             * 유저 정의 에러 일때는 이것만 처리 하고 리턴 한다
             * --> 나머지 장애 관련 데이타는 임의로 만든 데이타 이기 때문에 처리하면 안된다
             */
            if( parsed.getString("user_made_err").length() > 0
            &&  !substr(parsed.getString("user_made_err"), 0, 1).equals("0") ) {
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
                if( nstr(errBasic.getErrorCd()).length() > 0 ) {
                    TCtErrorBasic errBasicL = new TCtErrorBasic();
                    TCtErrorRcpt errRcptL = new TCtErrorRcpt();
                    TCtErrorNoti errNotiL = new TCtErrorNoti();
                    TCtErrorCall errCallL = new TCtErrorCall();
                    TCtErrorTxn  errTxnL  = new TCtErrorTxn();

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                            errNotiL, errCallL, errTxnL, macInfo, retErrState );
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
                    ||  enumNHME.name().equals("IDX_HW_INPUT_CHECK"     )    /* 15- 수표입금부         */
                    ||  enumNHME.name().equals("IDX_HW_OUT_CHECK"       )    /* 16- 수표출금부         */
                    ||  enumNHME.name().equals("IDX_HW_INPUT_BOX_50000" )    /* 17- 오만원입금함       */
                    ||  enumNHME.name().equals("IDX_HW_INPUT_BOX_100000")    /* 18- 십만원입금함       */
                    ||  enumNHME.name().equals("IDX_HW_RPC"             )    /* 19- RPC                */
                    ||  enumNHME.name().equals("IDX_HW_REMAIN_MONEY"    ) ) {/* 20- 지폐잔류           */
                       if( parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal())).equals(MsgBrokerConst.NICE_HW_GOOD) ) {
                           errBasic.setErrorCd( saNiceErrState[enumNHME.ordinal()] );
                           TCtErrorBasic errBasicL = new TCtErrorBasic();
                           TCtErrorRcpt errRcptL = new TCtErrorRcpt();
                           TCtErrorNoti errNotiL = new TCtErrorNoti();
                           TCtErrorCall errCallL = new TCtErrorCall();
                           TCtErrorTxn  errTxnL  = new TCtErrorTxn();

                           BeanUtils.copyProperties(  errBasicL, errBasic );
                           BeanUtils.copyProperties(  errRcptL,  errRcpt );
                           BeanUtils.copyProperties(  errNotiL,  errNoti );
                           BeanUtils.copyProperties(  errCallL,  errCall );
                           BeanUtils.copyProperties(  errTxnL,   errTxn );
                           comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                                   errNotiL, errCallL, errTxnL, macInfo, retErrState );
                       }

                    }
                    else
                        continue;
                }
            }
            /*
             * 유저정의 장애 복구는 응답송신하지 않는다.
             */
            throw new MsgBrokerException( "유저정의 장애 복구는 응답송신하지 않는다.", -99 );
        }
        /*
         * 장애전문
         */
        else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_ERR) ) {
            /*
             * 유저 정의 에러 일때는 이것만 처리 하고 리턴 한다
             * --> 나머지 장애 관련 데이타는 임의로 만든 데이타 이기 때문에 처리하면 안된다
             */
            if( !substr(parsed.getString("user_made_err"), 0, 1).equals("0") ) {
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
                        if( substr(parsed.getString("create_time"), 0, 2).compareTo("08") >= 0
                        &&  substr(parsed.getString("create_time"), 0, 2).compareTo("21") <= 0 ) {
                            /*
                             * 주간 ATM 감시 전문 처리
                             */
                            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_SUPERVISOR );
                            if( substr(parsed.getString("atm_monitor"), 0, 1).equals(MsgBrokerConst.NICE_ERROR) ) {
                                TCtErrorBasic errBasicL = new TCtErrorBasic();
                                TCtErrorRcpt errRcptL = new TCtErrorRcpt();
                                TCtErrorNoti errNotiL = new TCtErrorNoti();
                                TCtErrorCall errCallL = new TCtErrorCall();
                                TCtErrorTxn  errTxnL  = new TCtErrorTxn();

                                BeanUtils.copyProperties(  errBasicL, errBasic );
                                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                                BeanUtils.copyProperties(  errNotiL,  errNoti );
                                BeanUtils.copyProperties(  errCallL,  errCall );
                                BeanUtils.copyProperties(  errTxnL,   errTxn );
                                comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                                /*
                                 * Terminal Mode 가 '1' 일 경우 작업 상태 이므로 슈퍼바이저 에러만 발생 시키고
                                 * 금고 침투 장애를 발생 시키지 않는다.
                                 * 유저정의 장애는 응답송신하지 않는다.
                                 */
                                throw new MsgBrokerException("유저정의 장애는 응답송신하지 않는다", -99);
                            }
                            else {
                                TCtErrorBasic errBasicL = new TCtErrorBasic();
                                TCtErrorRcpt errRcptL = new TCtErrorRcpt();
                                TCtErrorNoti errNotiL = new TCtErrorNoti();
                                TCtErrorCall errCallL = new TCtErrorCall();
                                TCtErrorTxn  errTxnL  = new TCtErrorTxn();

                                BeanUtils.copyProperties(  errBasicL, errBasic );
                                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                                BeanUtils.copyProperties(  errNotiL,  errNoti );
                                BeanUtils.copyProperties(  errCallL,  errCall );
                                BeanUtils.copyProperties(  errTxnL,   errTxn );
                                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                                        errNotiL, errCallL, errTxnL, macInfo, retErrState );
                            }

                            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N15 );
                            /*
                             * 금고침투 장애가 아닌 경우 아래에서 복구 처리
                             * UpdateNICEErrMng(&suBody, NICE_ERROR_ATMWATCH_ERROR);
                             */
                        }
                        else {
                            /*
                             * 야간 ATM 감시 전문 처리 (금고침투긴급)
                             */
                            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_URGENCY_ERROR );
                            TCtErrorBasic errBasicL = new TCtErrorBasic();
                            TCtErrorRcpt errRcptL = new TCtErrorRcpt();
                            TCtErrorNoti errNotiL = new TCtErrorNoti();
                            TCtErrorCall errCallL = new TCtErrorCall();
                            TCtErrorTxn  errTxnL  = new TCtErrorTxn();

                            BeanUtils.copyProperties(  errBasicL, errBasic );
                            BeanUtils.copyProperties(  errRcptL,  errRcpt );
                            BeanUtils.copyProperties(  errNotiL,  errNoti );
                            BeanUtils.copyProperties(  errCallL,  errCall );
                            BeanUtils.copyProperties(  errTxnL,   errTxn );
                            comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                            /*
                             * 유저정의 장애는 응답송신하지 않는다.
                             */
                            throw new MsgBrokerException("유저정의 장애는 응답송신하지 않는다", -99);

                            /*
                             * 야간에 모든것이 정상이라면 주간에 발생한 슈퍼바이저 에대한
                             * 복구도 처리해야 한다     아래에서 복구 처리
                             */
                        }
                        break;
                    default :
                        break;
                }
                TCtErrorBasic errBasicL = new TCtErrorBasic();
                TCtErrorRcpt errRcptL = new TCtErrorRcpt();
                TCtErrorNoti errNotiL = new TCtErrorNoti();
                TCtErrorCall errCallL = new TCtErrorCall();
                TCtErrorTxn  errTxnL  = new TCtErrorTxn();

                BeanUtils.copyProperties(  errBasicL, errBasic );
                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                BeanUtils.copyProperties(  errNotiL,  errNoti );
                BeanUtils.copyProperties(  errCallL,  errCall );
                BeanUtils.copyProperties(  errTxnL,   errTxn );
                comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                /*
                 * 유저정의 장애는 응답송신하지 않는다.
                 */
                throw new MsgBrokerException("유저정의 장애는 응답송신하지 않는다", -99);
            }
            /*
             * 608(회선장애) 발생후 302(회선복구) 발생하지않고 301(장애), 001(개국) 이 발생하면
             * 회선장애를 복구 시킨다.
             */
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_LINE_ERROR );
            TCtErrorBasic errBasicL = new TCtErrorBasic();
            TCtErrorRcpt errRcptL = new TCtErrorRcpt();
            TCtErrorNoti errNotiL = new TCtErrorNoti();
            TCtErrorCall errCallL = new TCtErrorCall();
            TCtErrorTxn  errTxnL  = new TCtErrorTxn();

            BeanUtils.copyProperties(  errBasicL, errBasic );
            BeanUtils.copyProperties(  errRcptL,  errRcpt );
            BeanUtils.copyProperties(  errNotiL,  errNoti );
            BeanUtils.copyProperties(  errCallL,  errCall );
            BeanUtils.copyProperties(  errTxnL,   errTxn );
            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                    errNotiL, errCallL, errTxnL, macInfo, retErrState );
            /*
             * 310(AC전원차단) 301(장애), 001(개국) 이 발생하면
             * AC전원차단 복구 시킨다.
             */
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_AC_ERROR );

            BeanUtils.copyProperties(  errBasicL, errBasic );
            BeanUtils.copyProperties(  errRcptL,  errRcpt );
            BeanUtils.copyProperties(  errNotiL,  errNoti );
            BeanUtils.copyProperties(  errCallL,  errCall );
            BeanUtils.copyProperties(  errTxnL,   errTxn );
            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                    errNotiL, errCallL, errTxnL, macInfo, retErrState );
            /*
             * 지폐함 및 용지상태
             * 지폐함 1, 2, 3, 4 상태 검사
             */
            iCashBox = getNiceCashBox( errBasic.getMacNo() );

            /*
             * 지폐함 갯수를 검사한다
             */
            if( iCashBox > 0 ) {   /* 지폐함 갯수가 한개 이상이라면 */
                iCash = 0; /*부족 설정 */
                for( int i = 0 ; i < CNT_CASH_BOX ; i++) {/* 모든 지폐함을 체크하도록 수정 20080531 */
                    if( substr(parsed.getString("atm_cash"), i, i+1).equals(MsgBrokerConst.NICE_BOX_GOOD) ) {
                        /*
                         * 지폐함중 하나라도 '0'(정상) 이라면 정상상태이다
                         */
                        /*
                         *  오만원권이 먼저 방출되기때문에 고액권 체크 없이 그냥 비교해도 무난 20090706
                         */
                        iCash = 1;
                        break;
                    }
                }

                if( iCash == 1 ) {
                   /*
                    *  지폐함 현금 충분
                    */
                    errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_CASHBOX_EMPTY );

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                            errNotiL, errCallL, errTxnL, macInfo, retErrState );
                }
                else {
                   /*
                    * 지폐함 현금 부족
                    * 지폐함 현금 부족 발생시 기기 현금 상태를 확인 하여
                    * 현금이 500만원 이상이라면EMPTY센서이상 장애를 발생 시킨다
                    * # 현금이 500만원 이상이라면 현금 부족 장애가 발생 할수 없다
                    */
                   if( getIsCashState( macInfo.getMacNo() ) == -1 ) {
                       /*
                        *   [엠티센서이상]장애처리 by 최락경
                        *  지폐함이 EMPTY일 경우에만 장애발생시킴. NEAREND도 패스한다. 20130226 신남철과장
                        */
                       for ( int i = 0 ; i < CNT_CASH_BOX ; i ++ ) {
                           if( substr(parsed.getString("atm_cash"), i, i+1).equals(MsgBrokerConst.NICE_BOX_EMPTY) ) {
                               break;
                           }
                           /*
                            *  최종 지폐함까지 NICE_BOX_EMPTY일 경우 엠티센서이상 장애 발생시킴
                            */
                           if ( i >= CNT_CASH_BOX - 1 ) {
                               errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N09 );

                               BeanUtils.copyProperties(  errBasicL, errBasic );
                               BeanUtils.copyProperties(  errRcptL,  errRcpt );
                               BeanUtils.copyProperties(  errNotiL,  errNoti );
                               BeanUtils.copyProperties(  errCallL,  errCall );
                               BeanUtils.copyProperties(  errTxnL,   errTxn );
                               comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                           }
                       }
                   }
                   else  {
                       errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_CASHBOX_EMPTY );

                       BeanUtils.copyProperties(  errBasicL, errBasic );
                       BeanUtils.copyProperties(  errRcptL,  errRcpt );
                       BeanUtils.copyProperties(  errNotiL,  errNoti );
                       BeanUtils.copyProperties(  errCallL,  errCall );
                       BeanUtils.copyProperties(  errTxnL,   errTxn );
                       comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                   }
                }
            }
            else {
                /*
                 *  지폐함 갯수를 알수 없는 경우
                 */
                if( substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_CASH_BOX1.ordinal(),
                        EnumNCBS.IDX_STATE_CASH_BOX1.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_GOOD)
                ||  substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_CASH_BOX2.ordinal(),
                        EnumNCBS.IDX_STATE_CASH_BOX2.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_GOOD)
                ||  substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_CASH_BOX3.ordinal(),
                        EnumNCBS.IDX_STATE_CASH_BOX3.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_GOOD)
                ||  substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_CASH_BOX4.ordinal(),
                        EnumNCBS.IDX_STATE_CASH_BOX4.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_GOOD) ) {
                    /*
                     *  지폐함 현금 충분
                     */
                    errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_CASHBOX_EMPTY );

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                            errNotiL, errCallL, errTxnL, macInfo, retErrState );

                }
                else {
                    /*
                     *  지폐함 현금 부족
                     */
                    if( getIsCashState( macInfo.getMacNo() ) == -1 ) {
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_USER_N09 );

                        BeanUtils.copyProperties(  errBasicL, errBasic );
                        BeanUtils.copyProperties(  errRcptL,  errRcpt );
                        BeanUtils.copyProperties(  errNotiL,  errNoti );
                        BeanUtils.copyProperties(  errCallL,  errCall );
                        BeanUtils.copyProperties(  errTxnL,   errTxn );
                        comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                    }
                    else {
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_CASHBOX_EMPTY );

                        BeanUtils.copyProperties(  errBasicL, errBasic );
                        BeanUtils.copyProperties(  errRcptL,  errRcpt );
                        BeanUtils.copyProperties(  errNotiL,  errNoti );
                        BeanUtils.copyProperties(  errCallL,  errCall );
                        BeanUtils.copyProperties(  errTxnL,   errTxn );
                        comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                    }

                }

            }
            /*
             * 회수함 상태 검사
             */
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_COLLECTBOX_EMPTY );
            if( substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_RECOLL_BOX.ordinal(),
                    EnumNCBS.IDX_STATE_RECOLL_BOX.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_EMPTY) ) {
                /*
                 *  회수함 부족
                 */

                BeanUtils.copyProperties(  errBasicL, errBasic );
                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                BeanUtils.copyProperties(  errNotiL,  errNoti );
                BeanUtils.copyProperties(  errCallL,  errCall );
                BeanUtils.copyProperties(  errTxnL,   errTxn );
                comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
            }
            else if( substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_RECOLL_BOX.ordinal(),
                         EnumNCBS.IDX_STATE_RECOLL_BOX.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_GOOD) ) {
                /*
                 *  회수함 충분
                 */

                BeanUtils.copyProperties(  errBasicL, errBasic );
                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                BeanUtils.copyProperties(  errNotiL,  errNoti );
                BeanUtils.copyProperties(  errCallL,  errCall );
                BeanUtils.copyProperties(  errTxnL,   errTxn );
                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                        errNotiL, errCallL, errTxnL, macInfo, retErrState );
            }

            /*
             * 명세표 상태 검사
             */
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_SPECSBOX_EMPTY );
            if( substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_SPECS.ordinal(),
                    EnumNCBS.IDX_STATE_SPECS.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_EMPTY) ) {
                /*
                 *  명세표 부족
                 */

                BeanUtils.copyProperties(  errBasicL, errBasic );
                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                BeanUtils.copyProperties(  errNotiL,  errNoti );
                BeanUtils.copyProperties(  errCallL,  errCall );
                BeanUtils.copyProperties(  errTxnL,   errTxn );
                comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
            }
            else if( substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_SPECS.ordinal(),
                    EnumNCBS.IDX_STATE_SPECS.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_GOOD) ) {
                /*
                 *  명세표 충분
                 */

                BeanUtils.copyProperties(  errBasicL, errBasic );
                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                BeanUtils.copyProperties(  errNotiL,  errNoti );
                BeanUtils.copyProperties(  errCallL,  errCall );
                BeanUtils.copyProperties(  errTxnL,   errTxn );
                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                        errNotiL, errCallL, errTxnL, macInfo, retErrState );
            }

            /*
             * 2005.11.25 입금업무 추가로 인한 수정 BY BHJ
             * 입금함 상태 검사
             */
            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_INBOX_FULL );
            /*
             *  임금함과 회수함은 FULL의 뜻으로 쓰임
             */
            if( substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_INPUT_BOX.ordinal(),
                    EnumNCBS.IDX_STATE_INPUT_BOX.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_EMPTY) ) {
                /*
                 *  입금함 참
                 */

                BeanUtils.copyProperties(  errBasicL, errBasic );
                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                BeanUtils.copyProperties(  errNotiL,  errNoti );
                BeanUtils.copyProperties(  errCallL,  errCall );
                BeanUtils.copyProperties(  errTxnL,   errTxn );
                comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
            }
            else if( substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_INPUT_BOX.ordinal(),
                    EnumNCBS.IDX_STATE_INPUT_BOX.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_GOOD) ) {
                /*
                 *  입금함 빔
                 */

                BeanUtils.copyProperties(  errBasicL, errBasic );
                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                BeanUtils.copyProperties(  errNotiL,  errNoti );
                BeanUtils.copyProperties(  errCallL,  errCall );
                BeanUtils.copyProperties(  errTxnL,   errTxn );
                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                        errNotiL, errCallL, errTxnL, macInfo, retErrState );
            }

            /*
             *  2008.07.14 동양종금 수표업무 추가로 인한 수정 BY BHJ
             * 수표함 상태 검사
             */

            errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_CHECKBOX_EMPTY );
            if( substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_CHECK_BOX.ordinal(),
                    EnumNCBS.IDX_STATE_CHECK_BOX.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_EMPTY) ) {
                /*
                 *  수표함 빔
                 */

                BeanUtils.copyProperties(  errBasicL, errBasic );
                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                BeanUtils.copyProperties(  errNotiL,  errNoti );
                BeanUtils.copyProperties(  errCallL,  errCall );
                BeanUtils.copyProperties(  errTxnL,   errTxn );
                comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
            }
            else if( substr(parsed.getString("atm_cash"), EnumNCBS.IDX_STATE_CHECK_BOX.ordinal(),
                    EnumNCBS.IDX_STATE_CHECK_BOX.ordinal() + 1).equals(MsgBrokerConst.NICE_BOX_GOOD) ) {
                /*
                 *  수표함 충분
                 */

                BeanUtils.copyProperties(  errBasicL, errBasic );
                BeanUtils.copyProperties(  errRcptL,  errRcpt );
                BeanUtils.copyProperties(  errNotiL,  errNoti );
                BeanUtils.copyProperties(  errCallL,  errCall );
                BeanUtils.copyProperties(  errTxnL,   errTxn );
                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                        errNotiL, errCallL, errTxnL, macInfo, retErrState );
                errTxn.setRepairDate(null);
                errTxn.setRepairTime(null);
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
                ||  enumNHME.name().equals("IDX_HW_DVR_ERR"         )    /* 14 DVR                 */
                ||  enumNHME.name().equals("IDX_HW_INPUT_CHECK"     )    /* 15- 수표입금부         */
                ||  enumNHME.name().equals("IDX_HW_OUT_CHECK"       )    /* 16- 수표출금부         */
                ||  enumNHME.name().equals("IDX_HW_INPUT_BOX_50000" )    /* 17- 오만원입금함       */
                ||  enumNHME.name().equals("IDX_HW_INPUT_BOX_100000")    /* 18- 십만원입금함       */
                ||  enumNHME.name().equals("IDX_HW_RPC"             )    /* 19- RPC                */
                ||  enumNHME.name().equals("IDX_HW_REMAIN_MONEY"    ) ) {/* 20- 지폐잔류           */
                    errBasic.setErrorCd( saNiceErrState[enumNHME.ordinal()] );
                    if( substr(parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal())),
                                        0,1).equals(MsgBrokerConst.NICE_HW_NEARERROR)
                    ||  substr(parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal())),
                                        0,1).equals(MsgBrokerConst.NICE_HW_ERROR) ) {
                        /*
                         * 2007.02.12 지폐방출기, 카드 판독기의 경우 '3'인 경우는 무시,
                         * '4'인경우만 장애 발생하도록 수정
                         * 2007.02.23. FKM 기기가 지폐방출장애를 3->4로 먼저 변경 후 적용하기위해 임시 주석처리
                         */
                        if( (enumNHME.name().equals("IDX_HW_CASH_OUT") || enumNHME.name().equals("IDX_HW_CARD_READ"))
                         && substr(parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal())),
                                            0,1).equals(MsgBrokerConst.NICE_HW_NEARERROR) ) {
                            continue;
                        }

                        errBasic.setMadeErrCd( parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal())) );

                        BeanUtils.copyProperties(  errBasicL, errBasic );
                        BeanUtils.copyProperties(  errRcptL,  errRcpt );
                        BeanUtils.copyProperties(  errNotiL,  errNoti );
                        BeanUtils.copyProperties(  errCallL,  errCall );
                        BeanUtils.copyProperties(  errTxnL,   errTxn );
                        comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );

                        errBasic.setMadeErrCd(null);

                        /*
                         * 2007.02.23 DVR 장애 관제는 하지 않으나 조건조회에서 조회 가능하도록 발생과 동시에 복구 처리 - 운총요청
                         */
                        if( enumNHME.name().equals("IDX_HW_DVR_ERR") ) {

                            BeanUtils.copyProperties(  errBasicL, errBasic );
                            BeanUtils.copyProperties(  errRcptL,  errRcpt );
                            BeanUtils.copyProperties(  errNotiL,  errNoti );
                            BeanUtils.copyProperties(  errCallL,  errCall );
                            BeanUtils.copyProperties(  errTxnL,   errTxn );
                            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                                    errNotiL, errCallL, errTxnL, macInfo, retErrState );
                        }
                    }
                    else if( substr(parsed.getString(String.format("atm_hw_error[%d]", enumNHME.ordinal())),
                            0,1).equals(MsgBrokerConst.NICE_HW_GOOD) ) {

                        BeanUtils.copyProperties(  errBasicL, errBasic );
                        BeanUtils.copyProperties(  errRcptL,  errRcpt );
                        BeanUtils.copyProperties(  errNotiL,  errNoti );
                        BeanUtils.copyProperties(  errCallL,  errCall );
                        BeanUtils.copyProperties(  errTxnL,   errTxn );
                        comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                                errNotiL, errCallL, errTxnL, macInfo, retErrState );
                    }
                }
                else
                    continue;
            }

            int nNormal = 0;    /* ATM 감시 전문중 정상 상태인것 체크 위해 */
            int errorType = 0;

            for( EnumNM enumNM: EnumNM.values() ) {
                /*
                 * 문열림(앞문개폐)는 무시하도록 20070420  && 금고진동(user define 장애)은 user define error ,
                 * 열감지,회수함,지폐함1,2,3,4 무시(금고잠금은 NCR 때문에 무시안함) 20071105 김희천대리 요청
                 * 문열림(상판열림)은 점주관리 기기일 경우 발생시킴 2014.06.30
                 */

                if( !enumNM.name().equals("IDX_MON_TERM_MODE" )
                &&  !enumNM.name().equals("IDX_MON_OPNE_SAFE_YN" )
                &&  !enumNM.name().equals("IDX_MON_LOCK_SAFE_YN" ) ) {
                    nNormal = nNormal + 1;
                }
                else  {
                    if( substr(parsed.getString("atm_monitor"), enumNM.ordinal(), enumNM.ordinal()+1).equals(MsgBrokerConst.NICE_NORMAL)
                    ||  substr(parsed.getString("atm_monitor"), enumNM.ordinal(), enumNM.ordinal()+1).equals(MsgBrokerConst.NICE_NO_SET) ) {
                        nNormal = nNormal + 1;
                    }
                    else {
                        if ( enumNM.name().equals("IDX_MON_OPNE_SAFE_YN") ) { /* 금고개폐 20080109 */
                            errorType = 1;
                        }
                        else if ( enumNM.name().equals("IDX_MON_LOCK_SAFE_YN") ) { /* 금고잠금 20080109 */
                            errorType = 2;

                        }
                        else {
                            errorType = 0;
                        }
                    }
                }
            }

            if( parsed.getString("create_time").substring(0, 2).compareTo("08") >= 0
            &&  parsed.getString("create_time").substring(0, 2).compareTo("21") <= 0 ) {
                /*
                 * 주간 ATM 감시 전문 처리
                 */
                errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_SUPERVISOR );
                if( parsed.getString("atm_monitor").substring(EnumNM.IDX_MON_TERM_MODE.ordinal(),
                        EnumNM.IDX_MON_TERM_MODE.ordinal()+1).equals(MsgBrokerConst.NICE_ERROR) ) {

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                    /*
                     * Terminal Mode 가 '1' 일 경우 작업 상태 이므로 슈퍼바이저 에러만 발생 시키고
                     * 금고 침투 장애를 발생 시키지 않는다.
                     */
                    return;
                }
                else {

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                            errNotiL, errCallL, errTxnL, macInfo, retErrState );
                }

                if( nNormal != EnumNM.values().length ) {
                    if( errorType == 1 ) {
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_ATMWATCH_OPEN_ERROR );
                    }
                    else if( errorType == 2 ) {
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_ATMWATCH_CLOSE_ERROR );
                    }
                    else {
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_ATMWATCH_ERROR );
                    }

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                }
                else  {
                    errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_ATMWATCH_OPEN_ERROR );

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                            errNotiL, errCallL, errTxnL, macInfo, retErrState );

                    errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_ATMWATCH_CLOSE_ERROR );

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                            errNotiL, errCallL, errTxnL, macInfo, retErrState );
                    errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_ATMWATCH_ERROR );

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                            errNotiL, errCallL, errTxnL, macInfo, retErrState );
                }
            }
            else {
                /*
                 *  야간 ATM 감시 전문 처리
                 */
                if( nNormal != EnumNM.values().length ) {
                    /*
                     *  야간에 Terminal Mode 상태이거나 금고 개패인경우는 긴급장애로 처리
                     */
                    if( parsed.getString("atm_monitor").substring(EnumNM.IDX_MON_TERM_MODE.ordinal(),
                            EnumNM.IDX_MON_TERM_MODE.ordinal()+1).equals(MsgBrokerConst.NICE_ERROR)
                    ||  parsed.getString("atm_monitor").substring(EnumNM.IDX_MON_OPNE_SAFE_YN.ordinal(),
                            EnumNM.IDX_MON_OPNE_SAFE_YN.ordinal()+1).equals(MsgBrokerConst.NICE_ERROR) ) {
                        errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_URGENCY_ERROR );

                        BeanUtils.copyProperties(  errBasicL, errBasic );
                        BeanUtils.copyProperties(  errRcptL,  errRcpt );
                        BeanUtils.copyProperties(  errNotiL,  errNoti );
                        BeanUtils.copyProperties(  errCallL,  errCall );
                        BeanUtils.copyProperties(  errTxnL,   errTxn );
                        comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                        return;
                    }

                    errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_ATMWATCH_ERROR );

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );
                }
                else {
                    /*
                     * 야간에 모든것이 정상이라면 주간에 발생한 슈퍼바이저 에대한
                     * 복구도 처리해야 한다
                     */
                    errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_SUPERVISOR );

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                            errNotiL, errCallL, errTxnL, macInfo, retErrState );

                    errBasic.setErrorCd( MsgBrokerConst.NICE_ERROR_ATMWATCH_ERROR );

                    BeanUtils.copyProperties(  errBasicL, errBasic );
                    BeanUtils.copyProperties(  errRcptL,  errRcpt );
                    BeanUtils.copyProperties(  errNotiL,  errNoti );
                    BeanUtils.copyProperties(  errCallL,  errCall );
                    BeanUtils.copyProperties(  errTxnL,   errTxn );
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                            errNotiL, errCallL, errTxnL, macInfo, retErrState );
                }
            }

            /*
             * 원격 명령 요청 후 수신된 개국의 경우 해당 장애 복구 여부를 체크하여 원격관리 테이블에 update 해준다.
             */
            if( parsed.getString("rm_seq").length() > 0 ) {
                updateRemoteMng( safeData, parsed.getString("rm_create_date"), parsed.getString("rm_error_no"),
                        parsed.getString("rm_seq") );
            }
        }
        else {
            logger.warn(">>> [NICE] ERROR-전문이상 - 알수없는전문입니다.");
            throw new Exception(">>> [NICE] ERROR-전문이상 - 알수없는전문입니다.");
        }

        /*
         * (입금) 장애의 경우 해당 고객의 계좌번호를 별도로 저장하도록 함.
         */
        if( parsed.getString("cust_account_no").length() > 0 ) {
            try {
                insertUpdateCustInfo( safeData, parsed );
            }
            catch ( Exception e ) {
                //do Noting
            }
        }

        /*
         * [기업은행] 브랜드CD기 상태전문 전송
         *  - 브랜드제휴기기 상태전문을 기업은행으로 전송한다.
         *  - 개국전문만 바이패스한다. 장애/복구신호는 OrgAutoSend로 보낸다.
         *  - 응답코드목록은 그대로 하드코딩하였다.
         */
        if( parsed.getString("brand_org_cd").equals(MsgBrokerConst.KIUP_CODE)
        ||  parsed.getString("brand_org_cd").equals(MsgBrokerConst.BK_CODE) ) {
            /*return nRtn;*/        /* 오류날 경우 주석 해제 */
            /*
             * 공통데이터
             * 상태전문 데이터를 기업은행 브랜드CD 상태전문으로 옮기고,
             */
            MsgParser msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path")
                    + "05001130" + ".json");
            try {
                ByteBuffer msg = ByteBuffer.allocateDirect( msgPsr.getSchemaLength() );
                msgPsr.newMessage( msg );
                msg.position(0);
                msgPsr.setString( "mac_no",                       parsed.getString("mac_no") )         // 기기번호
                      .setString( "create_date",                  parsed.getString("create_date") )    // 발생일자
                      .setString( "create_time",                  parsed.getString("create_time") )    // 발생시각
                      .setString( "made_seq",                     parsed.getString("serial_no") )      // 제조일련번호
                      .setString( "encrypt_state",                "E" );                               // 암호화상태
                /*
                 * 긁어올 수 있는 DB데이터를 긁어온다.
                 */
                TCmMacSpec cmMacSpec = new TCmMacSpec();
                cmMacSpec.createCriteria().andOrgCdEqualTo( parsed.getString("CM.org_cd") )
                                          .andMacNoEqualTo( parsed.getString("mac_no") );
                try {
                    List<TCmMac> rslt = cmMacMap.selectBySpec( cmMacSpec );
                    if( rslt.size() == 1 ) {
                        TCmMac cmMac = rslt.get(0);
                        msgPsr.setString( "des_board_yn", cmMac.getDesYn() );       // DES보드가능구분
                        if( cmMac.getBillUseType().equals("6") )
                            msgPsr.setString( "cash_50000_yn", "2" );               // 현금5만원권가능구분
                        else
                            msgPsr.setString( "cash_50000_yn", "0" );

                        TCmSiteKey cmSiteKey = new TCmSiteKey();
                        cmSiteKey.setOrgCd( cmMac.getOrgCd() );
                        cmSiteKey.setBranchCd( cmMac.getBranchCd() );
                        cmSiteKey.setSiteCd( cmMac.getSiteCd() );
                        try {
                            TCmSite cmSite = cmSiteMap.selectByPrimaryKey( cmSiteKey );
                            if( cmSite != null ) {
                                msgPsr.setString( "site_nm", cmSite.getSiteNm() ); //코너명
                                /*
                                 * 가동시간
                                 */
                                msgPsr.setString( "oper_time", String.format("%s:%s~%s:%s", cmSite.getOperStartTime().substring(0,2)
                                                                                          , cmSite.getOperStartTime().substring(2,4)
                                                                                          , cmSite.getOperEndTime().substring(0,2)
                                                                                          , cmSite.getOperEndTime().substring(2,4)) );
                                if( cmSite.getOutType().equals("0") )
                                    msgPsr.setString( "booth_type", "1" );         // 부스형태
                                else
                                    msgPsr.setString( "booth_type", "2" );
                            }
                        }
                        catch ( Exception e ) {
                            // No necessary to handle some...
                        }
                    }
                }
                catch ( Exception e ) {
                    // No handling
                }
                msgPsr.setString( "emv_yn",        "0" )                           // EMV가능구분
                      .setString( "ir_yn",         "0" )                           // IR가능구분
                      .setString( "rf_yn",         "0" )                           // RF가능구분
                      .setString( "thumb_print_yn","0" );                          // 지문인식가능구분
                /*
                 * 개국전문 - 개국 or 장애복구
                 */
                if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_START) ) {
                    msgPsr.setString( "gubun_cd",   "5" )                          // 전문구분코드(5:개국)
                          .setString( "total_cd",   "00" );                        // 집계분류코드(00: -)
                    //msgPsr.setString( "error_class","0" );                         // 장애구분(0:정상)
                }
                /*
                 * 회선장애(는 보내지 않는다.)
                 */
                else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_LINE_ERR) ) {
                    if( nRtn == 0 )
                        return;
                    else
                        throw new Exception("회선장애(는 보내지 않는다.");
                }
                /*
                 * 장애전문, 장애복구도 network_info=301 로 올라온다.
                 * 역시 보내지 않는다. OrgAutoSend에서 보냄.
                 */
                else if( parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_ERR)
                      ||  parsed.getString("network_info").equals(MsgBrokerConst.NICE_STATE_AC_ERR) ) {
                    if( nRtn == 0 )
                        return;
                    else
                        throw new Exception("장애전문, 장애복구도 network_info=301 로 올라온다.");
                }
                /*
                 * 나머지는 정상(이용중)
                 */
                else {
                    msgPsr.setString( "gubun_cd",   "0" )                          // 전문구분코드(5:개국)
                          .setString( "total_cd",   "00" )                         // 집계분류코드(00: -)
                          .setString( "error_class","0" );                         // 장애구분(0:정상)
                }

                /*
                 * 기업은행 ATMS로 전송한다.
                 */
                msgPsr.setString( "CM.org_cd", MsgBrokerConst.KIUP_CODE )
                      .setString( "CM.ret_cd_src", "S" )
                      .setString( "CM.msg_id", "MngAuto" )
                      .setInt   ( "CM.body_len", msgPsr.getMessageLength() - MsgBrokerConst.HEADER_LEN )
                      .setString( "CM.trans_date", safeData.getSysDate() )
                      .setString( "CM.trans_time", safeData.getSysTime() )
                      .setString( "CM.format_type", "ES" )
                      .setString( "CM.msg_type", MsgBrokerConst.ES_REQ )
                      .setString( "CM.work_type", "1130" )
                      .setString( "CM.service_gb", "1" );

                msgPsr.setString("CM.trans_seq_no", comPack.getTransSeqNo(safeData, msgPsr.getString("CM.org_cd"), msgPsr.getString("CM.trans_date")));

                msgPsr.syncMessage();

                MsgBrokerProducer.putDataToPrd(msgPsr);
            }
            catch ( Exception e ) {

            }
            finally {
                msgPsr.clearMessage();
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

        /*
         *  2014.09.29 ARPC 오류 처리 저장 추가
         */
        if( parsed.getString("arpc_fault_dealno").length() > 0 ) {
            TFnArpcFault fnArpcFault = new TFnArpcFault();
            fnArpcFault.setDealYear( parsed.getString("create_date").substring(0, 4) );
            fnArpcFault.setDealDate(  parsed.getString("create_date") );
            fnArpcFault.setDealNo( parsed.getString("arpc_fault_dealno") );
            try {
                fnArpcFaultMap.insertSelective( fnArpcFault );
            }
            catch( Exception e ) {
                logger.warn( "T_FN_ARPC_FAULT[DEAL_NO=%{}] Insert 오류 ... 정상진행..", parsed.getString("arpc_fault_dealno") );
            }
        }
        /*
         * NET_S 가 608인 경우 처리 방법 변경
         * 일자 기준으로 최종 데이타가 608인 상태이고
         * 새로운 데이타가 608이라면 이 데이타는 버린다
         */
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
        niceMng.setPowerFail( substr(parsed.getString("atm_off_day"), 4) + parsed.getString("atm_off_time") );/* POWER_FAIL */
        niceMng.setJp1 ( substr(parsed.getString("atm_cash"), 0, 1) );            /* 지폐함1  */
        niceMng.setJp2 ( substr(parsed.getString("atm_cash"), 1, 2) );            /* 지폐함2  */
        niceMng.setJp3 ( substr(parsed.getString("atm_cash"), 2, 3) );            /* 지폐함3  */
        niceMng.setJp4 ( substr(parsed.getString("atm_cash"), 3, 4) );            /* 지폐함4  */
        niceMng.setHsh ( substr(parsed.getString("atm_cash"), 4, 5) );            /* 회수함   */
        niceMng.setMsp1( substr(parsed.getString("atm_cash"), 5, 6) );            /* 명세표   */
        niceMng.setGsy1( substr(parsed.getString("atm_cash"), 6, 7) );            /* 감사용지 */
        niceMng.setDummy1( substr(parsed.getString("atm_cash"), 7, 8) );          /* 입금함   */
        niceMng.setDummy2( substr(parsed.getString("atm_cash"), 8) );             /* 수표함   */
        niceMng.setDummy3( substr(parsed.getString("atm_dummy"), 0, 1) );         /* 더미3    */
        niceMng.setDummy4( substr(parsed.getString("atm_dummy"), 1, 2) );         /* 더미4    */
        niceMng.setDummy5( substr(parsed.getString("atm_dummy"), 2, 3) );         /* 더미5    */
        niceMng.setDummy6( substr(parsed.getString("atm_dummy"), 3, 4) );         /* 더미6    */
        niceMng.setDummy7( substr(parsed.getString("atm_dummy"), 4, 5) );         /* 더미7    */
        niceMng.setDummy8( substr(parsed.getString("user_made_err"), 4, 5) );     /* 더미8    */

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
        niceMng.setRpc      ( parsed.getString("atm_hw_error[19]") );             /* RPC모듈상태 */
        niceMng.setRemMoney ( parsed.getString("atm_hw_error[20]") );             /* 지폐잔류 */

        niceMng.setTermMode ( substr(parsed.getString("atm_monitor"), 0, 1) );    /* 터미널모드 */
        niceMng.setGggp     ( substr(parsed.getString("atm_monitor"), 1, 2) );    /* 금고개폐 */
        niceMng.setGgjg     ( substr(parsed.getString("atm_monitor"), 2, 3) );    /* 금고잠금 */
        niceMng.setGgjd     ( substr(parsed.getString("atm_monitor"), 3, 4) );    /* 금고진동 */
        niceMng.setGgygj    ( substr(parsed.getString("atm_monitor"), 4, 5) );    /* 금고열감지 */
        niceMng.setGgjc     ( substr(parsed.getString("atm_monitor"), 5, 6) );    /* 문열림 */
        niceMng.setHshtc    ( substr(parsed.getString("atm_monitor"), 6, 7) );    /* 회수함탈착 */
        niceMng.setJp1tc    ( substr(parsed.getString("atm_monitor"), 7, 8) );    /* 지폐함1탈착 */
        niceMng.setJp2tc    ( substr(parsed.getString("atm_monitor"), 8, 9) );    /* 지폐함2탈착 */
        niceMng.setJp3tc    ( substr(parsed.getString("atm_monitor"), 9, 10) );   /* 지폐함3탈착 */
        niceMng.setJp4tc    ( substr(parsed.getString("atm_monitor"), 10, 11) );  /* 지폐함4탈착 */
        niceMng.setCdpd     ( substr(parsed.getString("atm_monitor"), 11, 12) );  /* 카드판독기 */
        niceMng.setPass1    ( substr(parsed.getString("atm_monitor"), 12, 13) );  /* 암호장비 */
        niceMng.setDummy11  ( substr(parsed.getString("atm_monitor"), 13, 14) );  /* 통장부 */
        niceMng.setDummy12  ( substr(parsed.getString("atm_monitor"), 14) );      /* 더미12 */

        niceMng.setPgmVer  ( parsed.getString("pgm_version") );                   /* 기기프로그램 버젼 */
        //niceMng.setSerialNo( parsed.getString("serial_no"  ) );                 /* 시리얼 번호 */
        niceMng.setUpdateDate( safeData.getDSysDate() );
        try {
            niceMngMap.insertSelective( niceMng );
        }
        catch ( Exception e ){
            logger.warn( ">>> [DBInsertNICE_MNG] (T_CT_NICE_MNG) INSERT ERROR [{}]",e.getMessage() );
            logger.warn( "         J_DATE[{}] J_TIME[{}]", niceMng.getjDate(), niceMng.getjTime() );
            logger.warn( "         MAC_NO[{}] NET_S [{}]", niceMng.getMacNo(), niceMng.getNetS() );
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
        errBasic.setCreateDate( parsed.getString("create_date") );
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
        TCtNiceMac niceMac = new TCtNiceMac();
        cmMac.setMacVer( parsed.getString("pgm_version") );
        cmMac.setSerialNo( parsed.getString("serial_no") );
        cmMac.setMacAddress( parsed.getString("mac_address") );
        niceMac.setRpcYn( parsed.getString("rpc_yn") );
        niceMac.setModemRelayYn( parsed.getString("modem_relay_yn") );
        comPack.updateMacInfo( safeData, macInfo, cmMac, niceMac );
        comPack.insertUpdateMacOpen( safeData,  macInfo, errBasic );

        TCtErrorBasic errBasicL = new TCtErrorBasic();
        TCtErrorRcpt errRcptL = new TCtErrorRcpt();
        TCtErrorNoti errNotiL = new TCtErrorNoti();
        TCtErrorCall errCallL = new TCtErrorCall();
        TCtErrorTxn  errTxnL  = new TCtErrorTxn();

        BeanUtils.copyProperties(  errBasicL, errBasic );
        BeanUtils.copyProperties(  errRcptL,  errRcpt );
        BeanUtils.copyProperties(  errNotiL,  errNoti );
        BeanUtils.copyProperties(  errCallL,  errCall );
        BeanUtils.copyProperties(  errTxnL,   errTxn );
        comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_HW_ONE_CLEAR,
                errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, curErrList );

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
            fnBrandSetStateMap.insertSelective( fnBrandSetState );
        }
        catch (org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnBrandSetStateMap.updateByPrimaryKeySelective( fnBrandSetState );
            }
            catch (Exception e) {
                logger.warn( ">>> [DBInsertUpdateBrandOrg] (T_FN_BRAND_SET_STATE) UPDATE ERROR [{}]", e.getLocalizedMessage());
                throw e;
            }
        }
        catch ( Exception e ) {
            logger.warn( ">>> [DBInsertUpdateBrandOrg] (T_FN_BRAND_SET_STATE) INSERT ERROR [{}]", e.getLocalizedMessage() );
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
            fnRcCntMap.insertSelective( fnRcCnt );
        }
        catch (org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                fnRcCntMap.updateByPrimaryKeySelective( fnRcCnt );
            }
            catch (Exception e) {
                logger.warn( ">>> [DBInsertUpdateRCCnt] (T_FN_RC_CNT) UPDATE ERROR [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch ( Exception e ) {
            logger.warn( ">>> [DBInsertUpdateRCCnt] (T_FN_RC_CNT) INSERT ERROR [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }

    /**
     * NICE-기기정보에 입력된 지폐함 개수를 가져온다.
     *
     * @author KDJ originated by 방혜진
     * @since 2006/09/04
     * @param macNo  기기번호
     * @return short 갯수
     * @throws Exception
     */
    private short getNiceCashBox(String macNo) throws Exception {
        TCmMacKey cmMacKey = new TCmMacKey();

        cmMacKey.setMacNo( macNo );
        cmMacKey.setBranchCd( "9600" );
        cmMacKey.setOrgCd( "096" );

        TCmMac cmMac = null;
        try {
            cmMac = cmMacMap.selectByPrimaryKey( cmMacKey );
            if( cmMac == null ) {
                logger.warn( ">>> [DBGetNICECashBox] 지폐함 갯수 파악 실패 [NOT FOUND]" );
                throw new Exception( ">>> [DBGetNICECashBox] 지폐함 갯수 파악 실패" );
            }
        }
        catch ( Exception e ) {
            logger.warn( ">>> [DBGetNICECashBox] 지폐함 갯수 파악 실패 [{}]", e.getLocalizedMessage() );
            throw e;
        }

        return (cmMac.getjCnt() == null || cmMac.getjCnt().length() == 0) ? 0 : Short.parseShort(cmMac.getjCnt() );
    }

    /**
     * NICE-기기 잔액을 가져온다.
     *
     * @author KDJ originated by 방혜진
     * @since 2006/09/04
     * @param macNo  기기번호
     * @return short 갯수
     * @throws Exception
     */
    short getIsCashState( String macNo ) {

        TFnMacKey fnMacKey = new TFnMacKey();

        fnMacKey.setMacNo( macNo );
        fnMacKey.setBranchCd( "9600" );
        fnMacKey.setOrgCd( "096" );

        TFnMac fnMac = null;
        try {
            fnMac = fnMacMap.selectByPrimaryKey( fnMacKey );
            if( fnMac == null ) {
                logger.warn( ">>> [DBGetIsCashState] 기기 잔액 파악 실패 [NOT FOUND]" );
                return 0;
            }
        }
        catch ( Exception e ) {
            logger.warn( ">>> [DBGetIsCashState] 기기 잔액 파악 실패 [{}]", e.getLocalizedMessage() );
            return 0;
        }

        /*
         *  기기의 현금이 5백만원 이상이라면 empty 센서 에러 발생 위해 RET_ERROR로 리턴
         */
        if( fnMac.getInMacAmt() >= 5000000 )
            return -1;


        return 0;
    }

    private void updateRemoteMng( MsgBrokerData safeData, String createDate, String errorNo, String rmSeq ) throws Exception {

        TCtErrorBasicSpec  errBasicSpec = new TCtErrorBasicSpec();
        TCtErrorTxnSpec    errTxnSpec   = new TCtErrorTxnSpec();
        TCtErrorBasic      errBasic;
        TCtErrorTxn        errTxn;

        errBasicSpec.createCriteria().andErrorNoEqualTo( errorNo )
                                     .andCreateDateEqualTo( createDate );
        try {
            List<TCtErrorBasic> rslt = errBasicMap.selectBySpec( errBasicSpec );
            if( rslt.size() == 0 ) {
                logger.warn( "...해당 장애 없음 create_date[{}] error_no[{}]", createDate, errorNo );
                return;
            }
            errBasic = rslt.get(0);
        }
        catch ( Exception e ) {
            logger.warn(">>>  원격요청 장애 파악 실패[{}] create_date[{}] error_no[{}]",
                    e.getLocalizedMessage(), createDate, errorNo );
                return ;
        }

        errTxnSpec.createCriteria().andErrorNoEqualTo( errorNo )
                                   .andCreateDateEqualTo( createDate );
        try {
            List<TCtErrorTxn> rslt = errTxnMap.selectBySpec( errTxnSpec );
            if( rslt.size() == 0 ) {
                logger.warn( "...해당 장애 없음 create_date[{}] error_no[{}]", createDate, errorNo );
                return;
            }
            errTxn = rslt.get(0);
        }
        catch ( Exception e ) {
            logger.warn(">>>  원격요청 장애 파악 실패[{}] create_date[{}] error_no[{}]",
                    e.getLocalizedMessage(), createDate, errorNo );
                return ;
        }

        /*
         *  기기 상태 장애에 대한 원격일경우
         *  복구 성공 'SU'
         *  복구 실패 'FA'
         *  상태장애가 아닌 콜센터 접수 장애에 대한 원격일 경우는
         * 원격 실행여부에 대한 관리만  'EX'
         */
        TCtRemoteHistory   rmtHist = new TCtRemoteHistory();
        if( errBasic.getErrorCd().substring(0, 2).equals("NI") ) {
            if( errTxn.getRepairTime().equals("999999") ) {
                rmtHist.setRmtStatus( "FA" );
            }
            else {
                rmtHist.setRmtStatus( "SU" );
            }
        }
        else {
            rmtHist.setRmtStatus( "EX" );
        }


        TCtRemoteHistorySpec rmtHistSpec = new TCtRemoteHistorySpec();
        rmtHistSpec.createCriteria().andCreateDateEqualTo( createDate )
                                    .andErrorNoEqualTo( errorNo )
                                    .andCreateSeqEqualTo( rmSeq );
        rmtHist.setExeDate( safeData.getDSysDate());
        try {
            if( remoteHistoryMap.updateBySpecSelective( rmtHist, rmtHistSpec ) == 0) {
                logger.warn("[t_ct_remote_history] 상태전문수신 - 원격요청값 없음 CREATE_DATE[{}]-ERROR_NO[{}]-CREATE_SEQ[{}]",
                        createDate, errorNo, rmSeq );
                return;
            }
        }
        catch ( Exception e ) {
            logger.warn( "샹태전문 수신 t_ct_remote_history UPDATE Error [{}]", e.getLocalizedMessage() );
            return ;
        }
    }

    /**
     * CD-VAN 상태장애 중 고객정보에 대한 저장 처리
     *
     * @author KDJ
     * @since 2014/07/27
     * @param macNo  기기번호
     * @return short 갯수
     * @throws Exception
     */
    private void insertUpdateCustInfo( MsgBrokerData safeData, MsgParser parsed ) throws Exception {

        TCtErrorCustInfo errCustInfo = new TCtErrorCustInfo();
        errCustInfo.setCreateDate( parsed.getString("create_date") );
        errCustInfo.setOrgCd( "096");
        errCustInfo.setBranchCd( "9600" );
        errCustInfo.setMacNo( parsed.getString("mac_no") );
        errCustInfo.setAtmDealNo( parsed.getString("atm_deal_no") );
        errCustInfo.setCreateTime( parsed.getString("create_time") );
        errCustInfo.setCustOrgCd( parsed.getString("cust_org_cd") );

        TMisc misc = new TMisc();
        misc.setArgValue( parsed.getString("cust_account_no") );
        misc.setArgType( "1" );
        TMisc secRslt;
        try {
            secRslt = splMap.fcFnSecurity( misc );
        }
        catch ( Exception e ) {
            secRslt = new TMisc();
            secRslt.setSecureResult("");
        }
        errCustInfo.setCustAccountNo( secRslt.getSecureResult() );
        errCustInfo.setUpdateDate( safeData.getDSysDate() );
        try {
            errCustInfoMap.insertSelective( errCustInfo );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            try {
                errCustInfoMap.updateByPrimaryKeySelective( errCustInfo );
            }
            catch ( Exception e) {
                logger.warn( ">>> [DBInsertUpdateCustInfo] (T_CT_ERROR_CUST_INFO) UPDATE ERROR [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        catch( Exception e) {
            logger.warn(">>> [DBInsertUpdateCustInfo] T_CT_ERROR_CUST_INFO INSERT ERROR [{}]", e.getLocalizedMessage() );
            throw e;
        }
        logger.warn(">>> [DBInsertUpdateCustInfo] 고객정보 [{}-{}-{}] 저장  완료",
                parsed.getString("create_date"), parsed.getString("mac_no"), parsed.getString("atm_deal_no") );
    }

}
package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 정산기상태전문 수신처리
 *
 * <pre>
 * MngES_SaveCalcMacErrState
 * </pre>
 *
 *           2014. 07. 28    K.D.J.
 */


import java.io.File;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.lpad;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.ErrorState;
import com.nicetcm.nibsplus.broker.msg.model.TCmMac;
import com.nicetcm.nibsplus.broker.msg.model.TCtNiceMac;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCall;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNoti;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorRcpt;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

@Service("in05000111")
public class In05000111Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In05000111Impl.class);

    private static final int CNT_CALC_MAC_ATMWATCH_STATE = 6;

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

        try {
            comPack.getMacInfo( macInfo );
            logger.warn("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(), macInfo.getMacNm(),
                    macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );
        }
        catch( Exception e ) {
            logger.warn("기기정보 검색 실패-기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(), macInfo.getMacNm(),
                    macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );
            throw e;
        }
        try { comPack.checkBranchMacLength( macInfo ); } catch ( Exception e ) {}

        /*
         *  기기 버전 UPDATE
         */
        /*
         *  나이스 기기 UPDATE 함수를 함께 쓴다.
         */
        TCmMac cmMac = new TCmMac();
        TCtNiceMac niceMac = new TCtNiceMac();
        cmMac.setMacVer( parsed.getString("mac_ver") );
        comPack.updateMacInfo( safeData, macInfo, cmMac, niceMac ); // 기기 프로그램 버전, 시리얼 번호 업데이트


        errBasic.setOrgCd( macInfo.getOrgCd() );
        errBasic.setCreateDate( parsed.getString("create_date") );
        errBasic.setCreateTime( parsed.getString("create_time") );
        errBasic.setBranchCd( macInfo.getBranchCd() );
        errBasic.setMacNo( macInfo.getMacNo() );
        //errBasic.setMacNm( macInfo.getMacNm()) );
        errBasic.setSiteCd( macInfo.getSiteCd() );                                              // 5. 사이트코드
        errBasic.setDeptCd( macInfo.getDeptCd() );
        errBasic.setOfficeCd( macInfo.getOfficeCd() );
        errBasic.setTeamCd( macInfo.getTeamCd() );
        //errBasic.setAsAcptYn( macInfo.getAsAcptYn() );
        //memcpy( suDBErr.as_acpt_yn  , suMacInfo.as_acpt_yn  , strlen(suMacInfo.as_acpt_yn)  );  /* as 접수여부  */
        //memcpy( suDBErr.open_date   , suMacInfo.open_date   , strlen(suMacInfo.open_date)   );
        //memcpy( suDBErr.close_date  , suMacInfo.close_date  , strlen(suMacInfo.close_date)  );
        errBasic.setMadeErrCd( parsed.getString("mtc_cd") );
        //memcpy( suDBErr.mac_grade   , suMacInfo.mac_grade   , strlen(suMacInfo.mac_grade)   );
        errBasic.setFormatType( "11" );                                                         // 업무구분 '11'-장애관리전문

        MsgBrokerConst.EnumCalcMacStateSkipYN enumChosen = null;
        //logger.warn("KDJ#0 {}", parsed.getString("CM.org_cd"));
        for( MsgBrokerConst.EnumCalcMacStateSkipYN enumSkipYN: MsgBrokerConst.EnumCalcMacStateSkipYN.values() ) {
            enumChosen = enumSkipYN;
            if( enumSkipYN.name().equals("ORG_" + parsed.getString("CM.org_cd")) )
                break;
        }

        ErrorState errState = new ErrorState();
        errState.setMacType( MsgBrokerConst.CURERR_CALC );
        errState.setOrgCd( errBasic.getOrgCd() );
        errState.setBranchCd( errBasic.getBranchCd() );
        errState.setMacNo( errBasic.getMacNo() );
        byte[] retErrStates = comPack.getCurrentErrorState( errState );

        /*
         *  현금, 수표, 전표 관련 장애 처리
         */
        for( MsgBrokerConst.EnumCalcErrorState e: MsgBrokerConst.EnumCalcErrorState.values() ) {
            TCtErrorBasic errBasicL = new TCtErrorBasic();
            TCtErrorRcpt errRcptL = new TCtErrorRcpt();
            TCtErrorNoti errNotiL = new TCtErrorNoti();
            TCtErrorCall errCallL = new TCtErrorCall();
            TCtErrorTxn  errTxnL  = new TCtErrorTxn();

            BeanUtils.copyProperties( errBasicL, errBasic );
            /*
             *  해당 상태관련 장애 무시
             */
            //logger.warn("KDJ#1 {}, {}", enumChosen.getErrStates(), enumChosen.name());
            if( enumChosen.getErrStates().substring(e.ordinal(), e.ordinal()+1).equals("0") ) {
                /*logger(">>> [SaveCalcMacErrState] 기관[%.3s]-[%s] 상태 ... 무시...\n", suHead.org_cd, szaStateNm[i]); */
                continue;
            }
            /*
             *  복구일 경우는 NE0**, 예보일 경우는 NE1**, 장애일 경우는 NE2**
             */
            errBasicL.setErrorCd( String.format("NE%c%s", parsed.getBytes("atm_state")[e.ordinal()], lpad(e.getErrorCd(), 2, "0")) );

            /*
             *  예보 및 장애
             */
            //logger.warn("KDJ#2 {}", parsed.getBytes("atm_state")[e.ordinal()]);
            if( parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_NEAR
            ||  parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_END ) {
                comPack.insertErrBasic( safeData, errBasicL, errRcptL, errNotiL, errCallL, errTxnL, macInfo, "" );

                /*
                 *  회선장애가 발생한 상태라면 이후 장애는 체크하지 않는다.
                 */
                /*
                 *  회선장애의 경우 HOST에서 발생시키는 것이므로 ftp_cnt 체크하지 않는다.
                 */
                if ( e.name().equals("IDX_ST_LINE") ) {
                    return;
                }
            }
            /* 복구 */
            else if ( parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_CLEAR ) {
                errTxnL.setRepairDate( parsed.getString("create_date") );                        // 1. 복구일자(발생일자)
                errTxnL.setRepairTime( parsed.getString("create_time") );                        // 2. 복구시간(발생시간)

                /*
                 *  복구 일때는 예보와 장애 모두를 복구 시킨다.
                 */
                errBasicL.setErrorCd( String.format("NE1%s", lpad(e.getErrorCd(), 2, "0")) );
                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                        errNotiL, errCallL, errTxnL, macInfo, retErrStates );

                errBasicL.setErrorCd( String.format("NE2%s", lpad(e.getErrorCd(), 2, "0")) );
                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasicL, errRcptL,
                        errNotiL, errCallL, errTxnL, macInfo, retErrStates );

                /*logger.warn(">>> [SaveCalcMacErrState] 기관[{}] 상태[index-{}][{}][{}] ... 복구...",
                parsed.getString("CM.org_cd"), e.ordinal(), parsed.getBytes("atm_state")[e.ordinal()], errBasic.getErrorCd());*/
            }
            else if( parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_SKIP1
                 ||  parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_SKIP2
                 ||  parsed.getBytes("atm_state")[e.ordinal()] == MsgBrokerConst.STATE_SKIP3 ) {
            /*
                logger.warn(">>> [SaveCalcMacErrState] 상태무시");
            */
            }
            else {
                logger.warn(">>> [SaveCalcMacErrState] 잘못된 상태코드 수신-코드({}) - ex)복구:0,예보:1,장애:2, 무시:' ' or 9",
                     parsed.getString("atm_state").substring(e.ordinal(), e.ordinal()+1) );
            }
        }
        /*
         *  이랜드 일 경우 상태 부분말고 made_err_cd 부분에 장애가 들어온다.
         */
        /*
         *  이랜드 일 경우  made_err_cd 부분에 '7' 회선장애 '8' 회선장애 복구
         */
        if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.ELAND_CODE) ) {
            if( parsed.getString("error_hw_yn").equals("2") ) {
                errBasic.setErrorCd( parsed.getString("mtc_cd") );
                comPack.insertErrBasic( safeData, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );
            }
            else if( parsed.getString("error_hw_yn").equals("6") ) {
                errBasic.setErrorCd( parsed.getString("mtc_cd") );
                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_ONLY_HW_CLEAR, errBasic, errRcpt,
                        errNoti, errCall, errTxn, macInfo, retErrStates );
                errTxn.setRepairDate( null );
                errTxn.setRepairTime( null );
            }
            if( parsed.getString("error_hw_yn").equals("7") ) {
                errBasic.setErrorCd( "NE211" );
                comPack.insertErrBasic( safeData, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );
            }
            else if( parsed.getString("error_hw_yn").equals("8") ) {
                errBasic.setErrorCd( "NE211" );
                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_ONLY_HW_CLEAR, errBasic, errRcpt,
                        errNoti, errCall, errTxn, macInfo, retErrStates );
                errTxn.setRepairDate( null );
                errTxn.setRepairTime( null );
            }
        }

        /*
         *  정산기 금고침투 감시 장애 처리
         */
        int nNormal = 0;
        for( int i = 0; i < CNT_CALC_MAC_ATMWATCH_STATE; i++ ) {
            if( parsed.getBytes("atm_monitor")[i] == MsgBrokerConst.CALC_MAC_NORMAL
            ||  parsed.getBytes("atm_monitor")[i] == MsgBrokerConst.STATE_SKIP1
            ||  parsed.getBytes("atm_monitor")[i] == MsgBrokerConst.STATE_SKIP2
            ||  parsed.getBytes("atm_monitor")[i] == MsgBrokerConst.STATE_SKIP3 )
                nNormal = nNormal + 1;
        }
        if( parsed.getBytes("atm_monitor")[0] == MsgBrokerConst.CALC_MAC_SUPERVISOR ) {  // 슈퍼바이저 모드일 경우
            errBasic.setErrorCd( String.format("NE%d%s", 2, lpad(MsgBrokerConst.CD_CALC_MAC_SUPERVISOR, 2, "0")) );

            logger.warn(">>> [SaveCalcMacErrState] [정산기 슈퍼바이져 상태] {}", errBasic.getErrorCd() );

            comPack.insertErrBasic( safeData, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );
            /*
             *  Terminal Mode 가 '1' 일 경우 작업 상태 이므로 슈퍼바이저 에러만 발생 시키고
             * 금고 침투 장애를 발생 시키지 않는다.
             */
        }
        else if( parsed.getBytes("atm_monitor")[0] == MsgBrokerConst.CALC_MAC_NORMAL ) {
            errBasic.setErrorCd( String.format("NE%c%s", MsgBrokerConst.CALC_MAC_ERROR, lpad(MsgBrokerConst.CD_CALC_MAC_SUPERVISOR, 2, "0")) );

            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_ONLY_HW_CLEAR, errBasic, errRcpt,
                    errNoti, errCall, errTxn, macInfo, retErrStates );
            errTxn.setRepairDate( null );
            errTxn.setRepairTime( null );

            if( nNormal != CNT_CALC_MAC_ATMWATCH_STATE ) {
                errBasic.setErrorCd( String.format("NE2%s", lpad(MsgBrokerConst.CD_CALC_MAC_WATCH_ERR, 2, "0")) );
                comPack.insertErrBasic( safeData, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );
            }
            else {
                errBasic.setErrorCd( String.format("NE2%s", lpad(MsgBrokerConst.CD_CALC_MAC_WATCH_ERR, 2, "0")) );
                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_ONLY_HW_CLEAR, errBasic, errRcpt,
                        errNoti, errCall, errTxn, macInfo, retErrStates );
                errTxn.setRepairDate( null );
                errTxn.setRepairTime( null );
            }
        }
        /*
         *  정산기 ftp_Cnt가 설정되어 있다면 해당 file의 수신 개수를 확인 처리하여 응답
         *  개수가 맞지 않다면 오류코드 001' 세팅
         */
        if( parsed.getInt("ftp_cnt") > 0 ) {
            int nCnt = getFtpFileCnt( parsed.getString("create_date").substring(4), cmMac.getMacNo() );

            if ( nCnt == -1 )
                throw new Exception("getFtpFileCnt Error");
            else if ( nCnt < parsed.getInt("ftp_cnt") ) {
                logger.warn("MngES_SaveCalcMacErrState : file 개수 맞지 않음 [{}][{}]", parsed.getString("ftp_cnt"),  nCnt);
                throw new MsgBrokerException(String.format("MngES_SaveCalcMacErrState : file 개수 맞지 않음 [%d][%s]",
                        parsed.getInt("ftp_cnt"), nCnt), -7 );
            }
        }

    }

    /**
     * FTP로 수신받은 파일갯수 Count
     *
     * @author KDJ, 2014/07/29
     * @param MMDD  월일
     * @param macNo 기기번호
     */
    private int getFtpFileCnt( String MMDD, String macNo ) {

        int iCnt = 0;

        File dir = new File(MsgCommon.msgProps.getProperty("file.dir.casher"));

        if( dir.isDirectory() ) {
            File[] fList = dir.listFiles();
            for( File f: fList ) {
                if( f.getName().substring(3, 7).equals(MMDD)
                &&  f.getName().substring(16, 20).equals(macNo) ) {
                    iCnt++;
                }
            }
            return iCnt;
        }
        else return -1;
    }
}

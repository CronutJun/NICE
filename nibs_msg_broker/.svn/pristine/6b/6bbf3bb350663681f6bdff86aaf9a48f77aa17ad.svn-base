package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 신규(농협관련) 일괄 장애, 개국(복구)
 * 
 * <pre>
 * MngES_SaveNHErrState
 * </pre>
 * 
 *           2014. 07. 29    K.D.J.
 */

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorBasicMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasicJoin;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCall;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNoti;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorRcpt;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

@Service("in05000120")
public class In05000120Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In05000120Impl.class);
    
    @Autowired private StoredProcMapper splMap;
    @Autowired private TCtErrorBasicMapper errBasicMap;
    
    private String[] saValidErr = {
            "00100000",
            "00000000",
            "00000000",
            "10111100",
            "30330000",
            "22202220",
            "22222220",
            "11111110",
            "11111000",
            "00000000"
        };
    private short[] usCheckBit = {0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01};
    
    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        
        String[] saOldErrList = new String[saValidErr.length];
        int iErrorExitYn = 0;
        int iRtn = 0;
        
        TMacInfo macInfo = new TMacInfo();
        TCtErrorBasic errBasic = new TCtErrorBasic();
        TCtErrorRcpt errRcpt = new TCtErrorRcpt();
        TCtErrorNoti errNoti = new TCtErrorNoti();
        TCtErrorCall errCall = new TCtErrorCall();
        TCtErrorTxn  errTxn  = new TCtErrorTxn();

        for(int i = 0; i < saOldErrList.length; i++ )
            saOldErrList[i] = "00000000";
        
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );

        try {
            comPack.getMacInfo( macInfo );
            logger.info("기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(), macInfo.getMacNm(),
                    macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );
        }
        catch( Exception e ) {
            logger.info("기기정보 검색 실패-기관[{}] 지점[{}] 기번[{}] 기기명[{}] 부서[{}] 사무소[{}] 지소[{}]",
                    macInfo.getOrgCd(), macInfo.getBranchCd(), macInfo.getMacNo(), macInfo.getMacNm(),
                    macInfo.getDeptCd(), macInfo.getOfficeCd(), macInfo.getTeamCd() );
            throw e;
        }
        comPack.checkBranchMacLength( macInfo );
        
        errBasic.setOrgCd( macInfo.getOrgCd() );
        errBasic.setCreateDate( parsed.getInt("create_date") );
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
        //memcpy( suDBErr.mac_grade   , suMacInfo.mac_grade   , strlen(suMacInfo.mac_grade)   );
        
        errBasic.setOrgSiteCd( parsed.getString("org_site_cd") );
        
        errBasic.setTransDate( parsed.getString("create_date") );
        errBasic.setOrgMsgNo( parsed.getString("org_seq_no") );
        errBasic.setFormatType( "21" );                                                         // 농협은 21로

        /*
         *  개국전문은 개국테이블에
         *  개국전문 수신시 미개국 장애 복구처리
         */
        if( parsed.getString("state_type").equals("1") ) {
            comPack.insertUpdateMacOpen( safeData, macInfo, errBasic ); // 개시관리에
            errBasic.setErrorCd( "USR01" );
            errTxn.setRepairDate( parsed.getString("create_date") ); // 1. 복구일자(개국일자)
            errTxn.setRepairTime( parsed.getString("create_time") ); // 2. 복구시간(개국시간)
            comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, MsgBrokerConst.MODE_UPDATE_HW_ONE_CLEAR, errBasic, errRcpt,
                    errNoti, errCall, errTxn, macInfo, null );
        }
        /*
         *  해당 기기의 미완료 장애 중 미도착건에 대해 기존 장애가 있는지 체크
         */
        List<TCtErrorBasicJoin> rslt = null;
        try {
            rslt = errBasicMap.selectByJoin4( errBasic );
        }
        catch( Exception e ) {
            logger.info(">>> [MngES_SaveNHErrState] Select Error [{}]", e.getLocalizedMessage());
            throw e;
        }
        for( TCtErrorBasicJoin errJoin: rslt ) {
            /*
             *  단말상태는 F0:기기off, F1:기기on(ping미응답) 일경우만 출동, 복구 처리
             */
            /*
             *  F0, F1 => 소문자로 수정 20100920 엘지 김원형
             */
            if( errJoin.getErrorCd().substring(0, 4).equals("ESf0")
            ||  errJoin.getErrorCd().substring(0, 4).equals("ESf1") ) {
                /*
                 *  f0, f1 이 아닌경우 회선은 살았다는 것이므로 모두 복구로 본다.
                 */
                if( !parsed.getString("mac_state").equals("f0")
                &&  !parsed.getString("mac_state").equals("f1") ) {
                    errBasic.setErrorCd( errJoin.getErrorCd().substring(0, 4) );
                    errTxn.setRepairDate( parsed.getString("create_date") ); // 1. 복구일자(개국일자)
                    errTxn.setRepairTime( parsed.getString("create_time") ); // 2. 복구시간(개국시간)
                    comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                            errNoti, errCall, errTxn, macInfo, null );
                }
                else if( parsed.getString("mac_state").equals(errJoin.getErrorCd().substring(2)) ) {
                    logger.info( "중복장애응답처리 error_cd[{}]", errJoin.getErrorCd() );
                    /*
                     *  나머지 장애는 발생처리 함
                     */
                    iRtn = -12;
                }
            }
            else {
                /*
                 *  회선장애가 아닌 다른 기발생 장애 있음
                 */
                iErrorExitYn = 1;
                if( errJoin.getErrorCd().length() >= 5 ) {
                    int iRow = Integer.parseInt(errJoin.getErrorCd().substring(2,4));
                    int iCol = Integer.parseInt(errJoin.getErrorCd().substring(4,5));
                    if( saValidErr[iRow].substring(iCol,iCol+1).equals("1") ) {
                        logger.info(String.format("즉시출동 항목중 기발생 장애 있음. 중복장애응답처리 error_cd[%s]", errJoin.getErrorCd()));
                        iRtn = -12;

                    }
                    saOldErrList[iRow] = saOldErrList[iRow].substring(0,iCol)
                                       + "1"
                                       + saOldErrList[iRow].substring(iCol+1);
                }
            }
        }
        if( parsed.getString("mac_state").equals("f0")
        ||  parsed.getString("mac_state").equals("f1") ) {
            errBasic.setErrorCd( String.format("ES%s", parsed.getString("mac_state")) );
            comPack.insertErrBasic( errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );
            logger.info( "회선장애 처리 후 다른 상태 skip" );
            /*
             *  AS 기 접수 장애는 장애 정상 발생 처리 후 중복 응답 처리 20101220 농협 회의결과
             */
            if( macInfo.getAsAcptYn().length() == 8) {
                logger.info( "AS기 접수 장애 중복장애처리 응답");
                throw new MsgBrokerException("AS기 접수 장애 중복장애처리 응답]", -12);
            }
            if( iRtn == 0 ) return;
            else if( iRtn == -1 ) throw new Exception("ErrorRaised in In05000120");
            else throw new MsgBrokerException( "ErrorRaised in In05000120", iRtn );
        }
        
        /*
         *  20110830 농협요청 단말상태 'A1' && 유효비트들이 모두 정상 이면 복구 하도록 추가
         */
        int iAllNormal = 1;
        for( int iRow = 0; iRow < saValidErr.length; iRow++ ) {
            for( int iCol = 0; iCol < saValidErr[iRow].length(); iCol++ ) {
                /*
                 *  유효한 장애인지 체크
                 */
                if( saValidErr[iRow].charAt(iCol) == '1'
                ||  saValidErr[iRow].charAt(iCol) == '2'
                ||  saValidErr[iRow].charAt(iCol) == '3' ) {
                    errBasic.setErrorCd( String.format("ES%02d%01d", iRow, iCol) );
                    /*
                     *  발생장애에 대한 복구 여부 체크
                     */
                    if( saOldErrList[iRow].charAt(iCol) == '1' ) {
                        if( (parsed.getBytes("modul_state")[iRow] & usCheckBit[iCol]) == 0x00 ) {
                            /*
                             *  20101110 복구 처리는 단말상태가 00(정상) 이거나 A3(정상&수표부족)일경우만
                             *  복구하도록 LG이지은 요청
                             */
                            if( parsed.getString("mac_state").equals("00")
                            ||  parsed.getString("mac_state").equals("A3") ) {
                                errTxn.setRepairDate( parsed.getString("create_date") ); // 1. 복구일자(개국일자)
                                errTxn.setRepairTime( parsed.getString("create_time") ); // 2. 복구시간(개국시간)
                                comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                                        errNoti, errCall, errTxn, macInfo, null );
                            }
                            else {
                                logger.info("단말상태('00','A3')에의한 복구 아님 SKIP! 단말상태[{}]", parsed.getString("mac_state") );
                            }
                        }
                        else {
                            logger.info( "기발생 장애 있음 error_cd[{}]", errBasic.getErrorCd() );
                            iRtn = -12;
                        }
                    }
                    else {
                        if( (parsed.getBytes("modul_state")[iRow] & usCheckBit[iCol]) == usCheckBit[iCol] ) {
                            comPack.insertErrBasic( errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );
                        }
                    }
                    if( parsed.getString("mac_state").equals("A1") ) {
                        if( (parsed.getBytes("modul_state")[iRow] & usCheckBit[iCol]) == usCheckBit[iCol] ) {
                            iAllNormal = 0;
                        }
                    }
                    /*
                     *  보수모드 일 경우에는 보수모드에 대한 장애발생및 복구 처리만 하고
                     *  다른 상태는 무시한다.
                     */
                    if( iRow == 0 && iCol == 2 && (parsed.getBytes("modul_state")[iRow] & usCheckBit[iCol]) == usCheckBit[iCol] ) {
                        logger.info( "보수모드 처리 후 다른 상태 skip" );
                        /*
                         *  AS 기 접수 장애는 장애 정상 발생 처리 후 중복 응답 처리 20101220 농협 회의결과
                         */
                        if( macInfo.getAsAcptYn().length() == 8 ) {
                            logger.info("AS기 접수 장애 중복장애처리 응답");
                            throw new MsgBrokerException("AS기 접수 장애 중복장애처리 응답]", -12);
                        }
                        if( iRtn == 0 ) return;
                        else if( iRtn == -1 ) throw new Exception("ErrorRaised in In05000120");
                        else throw new MsgBrokerException( "ErrorRaised in In05000120", iRtn );
                    }
                }
            }
        }
        /*
         *  20110830 농협요청 단말상태 'A1' && 유효비트들이 모두 정상 이면 복구 하도록 추가
         */
        if( parsed.getString("mac_state").equals("A1") && iAllNormal == 1 ) {
            for( int iRow = 0; iRow < saValidErr.length; iRow++ ) {
                for( int iCol = 0; iCol < saValidErr[iRow].length(); iCol++ ) {
                    if( saOldErrList[iRow].charAt(iCol) == '1' ) {
                        errBasic.setErrorCd( String.format("ES%02d%01d", iRow, iCol) );
                        errTxn.setRepairDate( parsed.getString("create_date") ); // 1. 복구일자(개국일자)
                        errTxn.setRepairTime( parsed.getString("create_time") ); // 2. 복구시간(개국시간)
                        comPack.updateErrBasic( safeData, MsgBrokerConst.DB_UPDATE_ERROR_MNG, "", errBasic, errRcpt,
                                errNoti, errCall, errTxn, macInfo, null );
                    }
                }
            }
        }
        /*
         *  AS 기 접수 장애는 장애 정상 발생 처리 후 중복 응답 처리 20101220 농협 회의결과
         */
        if( macInfo.getAsAcptYn().length() == 8) {
            logger.info( "AS기 접수 장애 중복장애처리 응답");
            throw new MsgBrokerException("AS기 접수 장애 중복장애처리 응답]", -12);
        }
        if( iRtn == 0 ) return;
        else if( iRtn == -1 ) throw new Exception("ErrorRaised in In05000120");
        else throw new MsgBrokerException( "ErrorRaised in In05000120", iRtn );
    }
}

package com.nicetcm.nibsplus.broker.msg.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 서비스 공통 Class
 *
 *   전문 업무처리를 위한 공통기능을 모아둔 class이다.
 *
 *           2014. 06. 24
 *
 *           @author KDJ
 */

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.time.DateUtils;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.*;
import com.nicetcm.nibsplus.broker.msg.model.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;


@Component
public class CommonPackImpl implements CommonPack {

    private static final Logger logger = LoggerFactory.getLogger(CommonPackImpl.class);

    @Autowired protected DataSourceTransactionManager msgTX;

    @Autowired private TCmCommonMapper         cmMap;
    @Autowired private TCmMacMapper            cmMacMap;
    @Autowired private TCmMacNoMapper          cmMacNoMap;
    @Autowired private TCmSiteMapper           cmSiteMap;
    @Autowired private TCmSite01Mapper         cmSite01Map;
    @Autowired private TCmMemberMapper         cmMemberMap;

    @Autowired private TCtOpenMapper           openMap;
    @Autowired private TCtErrorMapper          errMap;
    @Autowired private TCtErrorBasicMapper     errBasicMap;
    @Autowired private TCtErrorRcptMapper      errRcptMap;
    @Autowired private TCtErrorNotiMapper      errNotiMap;
    @Autowired private TCtErrorCallMapper      errCallMap;
    @Autowired private TCtErrorTxnMapper       errTxnMap;
    @Autowired private TCtMacSetMngMapper      macSetMngMap;
    @Autowired private TCtOrgSiteChangeMapper  orgSiteChangeMap;

    @Autowired private TCtNiceMacMapper        niceMacMap;

    @Autowired private TCtUnfinishMapper       unfinishMap;

    @Autowired private TFnSendReportMapper     fnSRptMap;

    @Autowired private TMiscMapper             miscMap;

    @Autowired private TCtErrorMngMapper       ctErrorMngMap;

    /**
     *
     *  기관마다 점번과 기번을 전문에 다르게 표시
     *  따라서 나이스 점기번에 맞춰 재 저장 해야 한다.
     *    ex)제일은행 전문상 점/기번 : 0123/0012 로 들어오지만
     *    나이스에 맞게 [123 /12  ]로 바꿔 주어야 한다.
     *
     * @param MacInfo  기기정보 모델클래스의 인스턴스
     * @throws Exception
     *
     */
    public void checkBranchMacLength( TMacInfo MacInfo ) throws Exception {

        String sBrchCd, sMacNo;
        logger.debug("Calling CheckBranchMacLength");
        if( !MacInfo.getOrgCd().equals(MsgBrokerConst.KBST_CODE) ) {
            TCmCommonSpec spec = new TCmCommonSpec();
            spec.createCriteria().andLargeCdEqualTo("1501")
                                 .andSmallCdLike(MacInfo.getOrgCd() + "%");

            List<TCmCommon> rslt = cmMap.selectBySpec(spec);
            logger.debug("Result length = {}", rslt.size());
            if( rslt.size() == 0 ) {
                throw new Exception(String.format(">>> [DBGetError]-정의된 점/기번길이 없음.기관[{}]", MacInfo.getOrgCd()));
            }
            else if( rslt.size() > 1 ) {
                throw new Exception(String.format(">>> [DBGetErrort]-점/기번길이 파악 실패 기관[{}]", MacInfo.getOrgCd()));
            }

            int iBranchLen = Integer.parseInt(rslt.get(0).getCdNm1());
            int iMacLen = Integer.parseInt(rslt.get(0).getCdNm2());

            logger.debug("BrchCd[0] = {}, iBranchLen = {}, iMacLen = {}", MacInfo.getBranchCd(), iBranchLen, iMacLen);
            if( MacInfo.getBranchCd().length() > 0 && MacInfo.getBranchCd().length() < iBranchLen ) {
                sBrchCd = String.format("%" + iBranchLen + "s", MacInfo.getBranchCd());
            }
            else {
                sBrchCd = MacInfo.getBranchCd().substring(MacInfo.getBranchCd().length() - iBranchLen, MacInfo.getBranchCd().length() );
            }
            if( MacInfo.getMacNo().length() > 0 && MacInfo.getMacNo().length() < iMacLen ) {
                sMacNo = String.format("%" + iMacLen + "s", MacInfo.getMacNo());
            }
            else {
                sMacNo = MacInfo.getMacNo().substring(MacInfo.getMacNo().length() - iMacLen, MacInfo.getMacNo().length() );
            }
            MacInfo.setBranchCd(sBrchCd);
            MacInfo.setMacNo(sMacNo);
        }

    }

    /**
     *
     * 점번 기번 확인 및 변경
     * <pre>
     *  기관마다 점번과 기번을 전문에 다르게 표시
     *  따라서 나이스 점기번에 맞춰 재 저장 해야 한다.
     *    ex)제일은행 전문상 점/기번 : 0123/0012 로 들어오지만
     *    나이스에 맞게 [123 /12  ]로 바꿔 주어야 한다.
     * </pre>
     *
     * @param parsed
     * @throws Exception
     */
    @Override
    public void checkBranchMacLength( MsgParser parsed ) throws Exception {
        TMacInfo tMacInfo = new TMacInfo();
        tMacInfo.setOrgCd(parsed.getString("CM.org_cd"));
        tMacInfo.setBranchCd(parsed.getString("brch_cd"));
        tMacInfo.setMacNo(parsed.getString("mac_no"));

        checkBranchMacLength(tMacInfo);

        parsed.setString("CM.org_cd", tMacInfo.getOrgCd());
        parsed.setString("brch_cd", tMacInfo.getBranchCd());
        parsed.setString("mac_no", tMacInfo.getMacNo());
    }

    /**
     *
     *  기관 사이트 코드로 해당 코너 대표 기번을 가져온다.
     *
     *  @param MacInfo 기기정보 모델 클래스의 인스턴스
     *  @throws Exception
     *
     */
    public void getMacNoIntoSite( TMacInfo MacInfo ) throws Exception {

        TCmMacCond cond = new TCmMacCond();
        cond.setOrgCd( MacInfo.getOrgCd() );
        cond.setBranchCd( MacInfo.getBranchCd() );
        cond.setOrgSiteCd( MacInfo.getOrgSiteCd() );
        TCmMac mac = cmMacMap.selectByJoin1(cond);
        if( mac == null ) {
            logger.debug("mac is null.. no data");
            TCmMacNo macNo = new TCmMacNo();
            macNo.setOrgCd( MacInfo.getOrgCd() );
            macNo.setBranchCd( MacInfo.getBranchCd() );
            macNo.setMacNo("9999");
            try {
                cmMacNoMap.insert(macNo);
            }
            catch( org.springframework.dao.DataIntegrityViolationException e ) {
                logger.debug("ViolateException : {}", e.getLocalizedMessage());
                TCmMacNoSpec macNoSpec = new TCmMacNoSpec();
                macNoSpec.createCriteria().andOrgCdEqualTo( MacInfo.getOrgCd() )
                                          .andBranchCdEqualTo( MacInfo.getBranchCd() )
                                          .andMacNoEqualTo("9999");
                macNo.setErrorDate(new GregorianCalendar().getTime());
                cmMacNoMap.updateBySpec(macNo, macNoSpec);
            }
            catch( Exception e ) {
                logger.debug("Error raised : {}, {}", e, e.getMessage());
                throw e;
            }
        }
        else {
            logger.debug("mac.site_cd = {}, mac.mac_no = {}", mac.getSiteCd(), mac.getMacNo() );
            MacInfo.setSiteCd( mac.getSiteCd() );
            MacInfo.setMacNo( mac.getMacNo() );
        }
        logger.debug("GetMacNoIntoSite complete..");
    }
/*
    public void getMacNoIntoSite( MsgParser parsed ) throws Exception {

    }
*/
    /**
     *
     * 기번관리 table에서 지점코드, 사이트코드등을 얻는다
     *
     * @author KDJ
     * @param MacInfo 기기정보 모델 class의 인스턴스
     */
    public void getMacInfo( TMacInfo MacInfo ) throws Exception {

        TCmMac cmMac = null;

        if( MacInfo.getOrgCd().equals(MsgBrokerConst.CS_CODE) && MacInfo.getBranchCd().length() == 0 ) {
            TCmMacKey macKey = new TCmMacKey();
            macKey.setOrgCd( MacInfo.getOrgCd() );
            macKey.setBranchCd( MacInfo.getBranchCd() );
            macKey.setMacNo( MacInfo.getMacNo() );

            cmMac = cmMacMap.selectByPrimaryKey( macKey );
            if( cmMac == null ) {
                throw new Exception("첼시코드 파악 실패..");
            }
        }

        TMacInfo macInfo = cmMacMap.selectMacInfo( MacInfo );
        if( macInfo == null ) {
            TCmMacNo macNo = new TCmMacNo();
            macNo.setOrgCd(MacInfo.getOrgCd());
            macNo.setBranchCd(MacInfo.getBranchCd());
            macNo.setMacNo("9999");
            try {
                cmMacNoMap.insert(macNo);
            }
            catch( org.springframework.dao.DataIntegrityViolationException e ) {
                logger.debug("ViolateException : {}", e.getLocalizedMessage());
                TCmMacNoSpec macNoSpec = new TCmMacNoSpec();
                macNoSpec.createCriteria().andOrgCdEqualTo(MacInfo.getOrgCd())
                                          .andBranchCdEqualTo(MacInfo.getBranchCd())
                                          .andMacNoEqualTo("9999");
                macNo.setErrorDate(new GregorianCalendar().getTime());
                cmMacNoMap.updateBySpec(macNo, macNoSpec);
            }
            catch( Exception e ) {
                logger.debug("Error raised : {}, {}", e, e.getMessage());
                throw e;
            }
        }
        else {
            MacInfo.setSiteCd(macInfo.getSiteCd());
            MacInfo.setMacNm(macInfo.getMacNm());
            MacInfo.setMacModel(macInfo.getMacModel());
            MacInfo.setMadeComCd(macInfo.getMadeComCd());
            MacInfo.setDeptCd(macInfo.getDeptCd());
            MacInfo.setOfficeCd(macInfo.getOfficeCd());
            MacInfo.setTeamCd(macInfo.getTeamCd());
            MacInfo.setFdeptCd(macInfo.getFdeptCd());
            MacInfo.setFofficeCd(macInfo.getFofficeCd());
            MacInfo.setSiteNm(macInfo.getSiteNm());
            MacInfo.setSerialNo(macInfo.getSerialNo());
            MacInfo.setCheckYn(macInfo.getCheckYn());
            MacInfo.setAsAcptYn(macInfo.getAsAcptYn());
            MacInfo.setOpenDate(macInfo.getOpenDate());
            MacInfo.setCloseDate(macInfo.getCloseDate());
            MacInfo.setMacGrade(macInfo.getMacGrade());
            MacInfo.setMacAddress(macInfo.getMacAddress());
            MacInfo.setRpcYn(macInfo.getRpcYn());
            MacInfo.setModelRelayYn(macInfo.getModelRelayYn());
        }
    }

    /**
     *
     * 공통사용 DB insert, update 관련
     *
     * @author KDJ
     * @param ErrBasic T_CT_ERROR_MNG테이블 모델 클래스의 인스턴스
     * @throws Exception
     * @see TCtErrorBasic
     */
    public void insertErrBasic( TCtErrorBasic ErrBasic, TCtErrorRcpt ErrRcpt, TCtErrorNoti ErrNoti, TCtErrorCall ErrCall,
            TCtErrorTxn ErrTxn, TMacInfo MacInfo, String PartMngYn ) throws Exception {

        int iAutoSMS;
        String sSendTypeDetail = "";

        String sSysDate = MsgBrokerLib.SysDate();
        String sNSysDate = MsgBrokerLib.SysDate(1);
        String sSysTime = MsgBrokerLib.SysTime();
        Date   dSysDate = MsgBrokerLib.SysDateD(0);

        /*
         * 현재시간을 얻는다.
         */
        ErrRcpt.setAcceptDate( sSysDate );
        ErrRcpt.setAcceptTime( sSysTime );

        /*
         * 장애관리, 개시관리 관련 변수 대입
         */
        ErrRcpt.setAcceptNm("online");
        ErrRcpt.setAcceptUid("online");

        /*
         * 장애발생전문 - 장애 관리 테이블에 처리
         * 중복 장애 발생 여부 확인
         * 국민, 기업, 외환 같이  기관에서 1차통지 전문 일련번호를 관리 할 경우 1차통지 전문 일련번호로 중복 체크
         * 그 이외에는 같은 기기의 장애 코드로 만 체크 단 신한은 발생 시간으로 일련번호를 대신하므로 추가
         */
        if( (ErrBasic.getOrgCd().equals(MsgBrokerConst.KBST_CODE)
          || ErrBasic.getOrgCd().equals(MsgBrokerConst.KIUP_CODE)
          || ErrBasic.getOrgCd().equals(MsgBrokerConst.KEB_CODE)
          || ErrBasic.getOrgCd().equals(MsgBrokerConst.HANAATMS_CODE)      /* HANA ATMS  */
          || ErrBasic.getOrgCd().equals(MsgBrokerConst.SHATMS_CODE)        /* 신한ATMS   */
          || ErrBasic.getOrgCd().equals(MsgBrokerConst.KNB_CODE)           /* 경남ATMS   */
          || ErrBasic.getOrgCd().equals(MsgBrokerConst.NONGH_CODE)         /* 농협 ATMS  */
          || ErrBasic.getOrgCd().equals(MsgBrokerConst.BU_CODE)            /* 부산은행   */
          || ErrBasic.getOrgCd().equals(MsgBrokerConst.WRATMS_CODE)
          || ErrBasic.getOrgCd().equals(MsgBrokerConst.KNATMS_CODE)
          || ErrBasic.getOrgCd().equals(MsgBrokerConst.BUATMS_CODE))
        &&   ErrBasic.getSec().length() > 0 ) {                           /* 출동요청 전문 일경우 */
            if( getDupErrorMng(ErrBasic, 0) )
                return;
        }
        else {
            if( insertCheckErrorMng(ErrBasic) )
                return;
        }
        /*
         *   신한은행 부분관리일 경우 ==> 은행으로부터 S1이 콜을 직접 받아 처리하는 장애는 자동 완료 처리
         */
        AutoSendInfoReturn retASI = new AutoSendInfoReturn();
        if( PartMngYn.equals("1") ){
            iAutoSMS = MsgBrokerConst.AUTO_FINISH;
            ErrBasic.setOrgMsg(ErrBasic.getOrgMsg().trim() + "==>부분관리 자동완료처리");
        }
        else {
            /*
             * 자동 SMS 전송 정보관련
             */
            iAutoSMS = getAutoSendInfo( ErrBasic, MacInfo, retASI );
            /**
             *  중복 장애가 있으면
             */
            if( getDupError( ErrBasic ) == 1 ) {
                ErrNoti.setSendCheckYn("Y");
            }
            else {
                ErrNoti.setSendCheckYn("N");
            }
        }

        /*
         *  자동통보
         */
        if( iAutoSMS == MsgBrokerConst.AUTO_SEND_TRUE ) {
            /*
             * 자동통보이면서 Wait 시간이 0일경우 자동통보한다. 0이 아닐경우는 batch 작업에 의해서 체크한다.
             */
            logger.info("   SMS 자동 통보건 입니다");
            ErrNoti.setSendStatus("1");
            /*
             * 즉시 통보 건
             */
            if( retASI.waitTime == 0 ) {
                /*
                 * 외주 자동 통보
                 */
                if( retASI.guardSite == 1 )
                    ErrNoti.setSendType("5");
                /*
                 * NICE 자동 통보
                 */
                else
                    ErrNoti.setSendType("0");
                sSendTypeDetail = "5";
            }
            /*
             *  수분 후 자동 통보
             */
            else {
                ErrNoti.setSendStatus("0");
                /*
                 * 외주 수분 후 자동 통보
                 */
                if( retASI.guardSite == 1 ) {
                    ErrNoti.setSendType("6");
                }
                /*
                 * NICE 수분 후 자동 통보
                 */
                else {
                    ErrNoti.setSendType("1");
                }
                sSendTypeDetail = "1";
            }

            /*
             * 나이스 장애중 22시 이후 오전 8시 이전 데이타는 대기후 자동통보로 변경한다.
             */
            if( ErrBasic.getOrgCd().equals( MsgBrokerConst.NICE_CODE )
            &&  ( ErrRcpt.getAcceptTime().compareTo("220000") >= 0
              ||  ErrRcpt.getAcceptTime().compareTo("080000") < 0 )) {
                ErrNoti.setSendStatus("0");
                /*
                 * 외주 자동 통보
                 */
                if( retASI.guardSite == 1 )
                    ErrNoti.setSendType("6");
                else
                    ErrNoti.setSendType("1");

                /*
                 * 나이스이고 22시 이후
                 */
                if( ErrRcpt.getAcceptTime().compareTo("220000") >= 0 ) {
                    sSendTypeDetail = "2";
                }
                /*
                 * 나이스이고 0시부터 8시 이전
                 */
                else {
                    sSendTypeDetail = "3";
                }
            }
        }
        /*
         * 자동완료
         */
        else if( iAutoSMS == MsgBrokerConst.AUTO_FINISH ) {
            ErrNoti.setSendStatus( "0" );
            ErrNoti.setSendType("2");
            ErrTxn.setFinishDate( ErrRcpt.getAcceptDate() );
            ErrTxn.setFinishTime( ErrRcpt.getAcceptTime() );
            /* 처리완료 */
            ErrBasic.setErrorStatus("7000");
        }
        /*
         * 수동통보
         */
        else if( iAutoSMS == MsgBrokerConst.AUTO_SEND_FALSE ) {
            ErrNoti.setSendStatus("0");
            /*
             * 외주 수동 통보
             */
            if( retASI.guardSite == 1 )
                ErrNoti.setSendType("7");
            /*
             * NICE 수동 통보
             */
            else
                ErrNoti.setSendType("2");
            logger.info(" SMS 수동 혹은 자동중 Wait시간이 있는 통보건 [ORG_CD = '{}', BRANCH_CD = '{}', MAC_NO = '{}', TRANS_DATE = '{}', ORG_MSG_NO = '{}'",
                    ErrBasic.getOrgCd(), ErrBasic.getBranchCd(), ErrBasic.getMacNo(), ErrBasic.getTransDate(), ErrBasic.getOrgMsgNo());
        }
        /*
         * 장애가 아님
         */
        else if( iAutoSMS == MsgBrokerConst.AUTO_NOT_CREATE ) {
            logger.info( "장애발생하지 않게함 AutoSms[{}]", iAutoSMS );
             return;
        }
        /*
         * 자동통보, 수동통보, 장애완료처리 외에 걸러지지 않은 데이터 체크
         */
        else {
            logger.info( "걸리지않아 체크필요 AutoSms[{}]", iAutoSMS );
        }

        TMisc miscRec;
        try {
            miscRec = miscMap.getNiceBranchCd( ErrBasic );
            if( miscRec != null ) {
                ErrBasic.setBranchCd(miscRec.getBranchCd() );
            }
        }
        catch ( Exception e ){
            logger.info("F_GET_NICE_BRANCH_CD Call Error [{}]", e.getMessage() );
            throw e;
        }

        /*
         * 장애등록 번호 채번
         */
        try {
            miscRec = miscMap.generateErrorNo();
            if( miscRec != null ) {
                ErrBasic.setErrorNo( miscRec.getErrorNo() );
            }
            else {
                throw new Exception("ERROR_NO Generate error.");
            }
        }
        catch ( Exception e ){
            logger.info("F_GET_NICE_BRANCH_CD Call Error [{}]", e.getMessage() );
            throw e;
        }
        ErrBasic.setRegDt( dSysDate );
        ErrBasic.setRegId( ErrRcpt.getAcceptUid() );
        ErrRcpt.setErrorNo( ErrBasic.getErrorNo() );
        ErrRcpt.setCreateDate( ErrBasic.getCreateDate() );
        ErrRcpt.setCreateTime( ErrBasic.getCreateTime() );
        ErrRcpt.setRegDt( ErrBasic.getRegDt() );
        ErrRcpt.setRegId( ErrBasic.getRegId() );
        ErrNoti.setErrorNo( ErrBasic.getErrorNo() );
        ErrNoti.setCreateDate( ErrBasic.getCreateDate() );
        ErrNoti.setCreateTime( ErrBasic.getCreateTime() );
        ErrNoti.setRegDt( ErrBasic.getRegDt() );
        ErrNoti.setRegId( ErrBasic.getRegId() );
        ErrCall.setErrorNo( ErrBasic.getErrorNo() );
        ErrCall.setCreateDate( ErrBasic.getCreateDate() );
        ErrCall.setCreateTime( ErrBasic.getCreateTime() );
        ErrCall.setRegDt( ErrBasic.getRegDt() );
        ErrCall.setRegId( ErrBasic.getRegId() );
        ErrTxn.setErrorNo( ErrBasic.getErrorNo() );
        ErrTxn.setCreateDate( ErrBasic.getCreateDate() );
        ErrTxn.setCreateTime( ErrBasic.getCreateTime() );
        ErrTxn.setRegDt( ErrBasic.getRegDt() );
        ErrTxn.setRegId( ErrBasic.getRegId() );

        if( ErrBasic.getCreateTime().length() == 0 )
            ErrBasic.setCreateTime( ErrRcpt.getAcceptTime() );

        if( sSendTypeDetail.equals("0") ) {
            ErrNoti.setSendDate( ErrRcpt.getAcceptDate() );
            ErrNoti.setSendTime( ErrRcpt.getAcceptTime() );
            ErrNoti.setSendNm( ErrRcpt.getAcceptNm() );
            ErrNoti.setSendUid( ErrRcpt.getAcceptUid() );
        }
        else {
            ErrNoti.setSendDate( "" );
            ErrNoti.setSendTime( "" );
            ErrNoti.setSendNm( "" );
            ErrNoti.setSendUid( "" );
        }
        if( sSendTypeDetail.equals("0") ) {
            ErrNoti.setSendCheckDatetime( dSysDate );
            ErrNoti.setSendPlanDatetime( dSysDate );
        }
        else if( sSendTypeDetail.equals("1") ) {
            ErrNoti.setSendCheckDatetime( DateUtils.addDays( dSysDate,  (1 / (24*60)) * (retASI.waitTime - 1) ));
            ErrNoti.setSendPlanDatetime( DateUtils.addDays( dSysDate,  (1 / (24*60)) * retASI.waitTime ));
        }
        else if( sSendTypeDetail.equals("2") ) {
            ErrNoti.setSendCheckDatetime(DateUtils.parseDate( sNSysDate + " 080000", new String[]{"yyyyMMdd HHmmss"}));
            ErrNoti.setSendPlanDatetime(DateUtils.parseDate( sNSysDate + " 080000", new String[]{"yyyyMMdd HHmmss"}));
        }
        else if( sSendTypeDetail.equals("3") ) {
            ErrNoti.setSendCheckDatetime(DateUtils.parseDate( sSysDate + " 080000", new String[]{"yyyyMMdd HHmmss"}));
            ErrNoti.setSendPlanDatetime(DateUtils.parseDate( sSysDate + " 080000", new String[]{"yyyyMMdd HHmmss"}));
        }
        else {
            ErrNoti.setSendCheckDatetime( null );
            ErrNoti.setSendPlanDatetime( null );
        }
        ErrNoti.setSendPlanNm( ErrRcpt.getAcceptNm() );
        ErrNoti.setSendUid( ErrRcpt.getAcceptUid() );
        if( sSendTypeDetail.equals("0") || sSendTypeDetail.equals("1") ) {
            if( retASI.guardSite == 1 )
                ErrNoti.setSendTool("TEL");
            else
                ErrNoti.setSendTool("SMS");
        }
        else
            ErrNoti.setSendTool( null );

        if( sSendTypeDetail.equals("0") ) {
            ErrNoti.setSendSmsStatus( "6020" );
            if( retASI.guardSite == 1 ) {
                ErrNoti.setRecvPlace( retASI.outCd.trim() );
                ErrNoti.setRecvUserUid( "9999999" );
                ErrNoti.setRecvUserNm( null );
                ErrNoti.setRecvTeleNo( null );

            }
            else {
                ErrNoti.setRecvPlace( "NICE" );
                ErrNoti.setRecvUserUid( retASI.memberId.trim() );
                ErrNoti.setRecvUserNm( retASI.memberNm.trim() );
                ErrNoti.setRecvTeleNo( retASI.memberTel.trim() );
            }
        }
        else {
            ErrNoti.setSendSmsStatus( null );
            ErrNoti.setRecvPlace( null );
            ErrNoti.setRecvUserUid( null );
            ErrNoti.setRecvUserNm( null );
            ErrNoti.setRecvTeleNo( null );
        }

        ErrNoti.setOrgUserNm( ErrRcpt.getAcceptNm() );
        ErrNoti.setOrgUserType( "0" );

        ErrBasic.setUpdateDate( dSysDate );
        ErrBasic.setUpdateUid( ErrRcpt.getAcceptUid() );

        ErrBasic.setDeptCd( MacInfo.getDeptCd() );
        ErrBasic.setOfficeCd( MacInfo.getOfficeCd() );
        ErrBasic.setTeamCd( MacInfo.getTeamCd() );
        ErrBasic.setRegDt( dSysDate );
        ErrBasic.setRegId( ErrRcpt.getAcceptNm() );

        try {
            errBasicMap.insert( ErrBasic );
            errRcptMap.insert( ErrRcpt );
            errNotiMap.insert( ErrNoti );
            errTxnMap.insert( ErrTxn );
            errCallMap.insert( ErrCall );
        }
        catch ( Exception e ) {
            logger.info( "Insert ErrorMng error = {}", e.getMessage() );
            throw e;
        }
    }

    /**
     *
     * 공통사용 장애원장 갱신
     *
     * @author KDJ
     * @param WorkType 작업유형 (상태/취소)
     * @param ErrBasic T_CT_ERROR_MNG테이블 모델 클래스의 인스턴스
     * @throws Exception
     * @see TCtErrorBasic
     */
    public void updateErrBasic( MsgBrokerData safeData, int WorkType, String DbMode, TCtErrorBasic ErrBasic, TCtErrorRcpt ErrRcpt, TCtErrorNoti ErrNoti, TCtErrorCall ErrCall,
            TCtErrorTxn ErrTxn, TMacInfo MacInfo, byte[] curMacStateError ) throws Exception {
        String[] saNiceErrorList = {
                "NI101",
                "NI110",
                "NI111",
                "NI112",
                "NI113",
                "NI114",
                "NI120",
                "NI121",
                "NI122",
                "NI123",
                "NI124",
                "NI125",
                "NI126",
                "NI128",
                "NI129",
                "NI131",
                "NI133",
                "NI134",
                "NI135",
                "NI136",
                "NI137",
                "NI138",
                "NI139",
                "NI140",
                "NI141",
                "NI142",
                "NI143",
                "NI144",
                "NI902",
                "NI903",
                "NI904",
                "NI905",
                "NI906",
                "NI907",
                "NI908",
                "NI909",
                "NI911",
                "NI912",
                "NI913",
                "NI914",
                "NI915",
                "NI102",
                ""
            };
        String[] saAtmErrorList = {
                "NE100",
                "NE101",
                "NE102",
                "NE103",
                "NE104",
                "NE105",
                "NE106",
                "NE107",
                "NE108",
                "NE109",
                "NE110",
                "NE111",
                "NE112",
                "NE113",
                "NE115",
                "NE116",
                "NE117",
                "NE118",
                "NE119",
                "NE120",
                "NE121",
                "NE122",
                "NE123",
                "NE124",
                "NE125",
                "NE126",
                "NE200",
                "NE201",
                "NE202",
                "NE203",
                "NE204",
                "NE205",
                "NE206",
                "NE207",
                "NE208",
                "NE209",
                "NE210",
                "NE211",
                "NE212",
                "NE213",
                "NE214",
                "NE215",
                "NE216",
                "NE217",
                "NE218",
                "NE219",
                "NE220",
                "NE221",
                "NE222",
                "NE223",
                "NE224",
                "NE225",
                "NE226",
                "NE250",
                "NE251",
                "NE252",
                "NE253",
                "NE254",
                "NE255",
                "NE256",
                "NE257",
                "NE258",
                "NE259",
                "NE260",
                "NE261",
                "NE230",
                "NE231",
                "NE232",
                "NE233",
                "NE240",
                "NE241",
                "NE270",
                "NE271",
                "NE272",
                "NE273",
                "NE275",
                "NE276",
                "NE277",
                "NE278",
                "NE170",
                "NE171",
                "NE172",
                "NE173",
                ""
            };

        String[] saCalcErrorList = {
                "NE102",
                "NE115",
                "NE124",
                "NE123",
                "NE100",
                "NE117",
                "NE118",
                "NE119",
                "NE120",
                "NE121",
                "NE122",
                "NE104",
                "NE148",
                "NE126",
                "NE125",
                "NE103",
                "NE142",
                "NE143",
                "NE144",
                "NE145",
                "NE146",
                "NE147",
                "NE211",
                "NE202",
                "NE215",
                "NE224",
                "NE223",
                "NE200",
                "NE217",
                "NE218",
                "NE219",
                "NE220",
                "NE221",
                "NE222",
                "NE204",
                "NE248",
                "NE226",
                "NE225",
                "NE203",
                "NE242",
                "NE243",
                "NE244",
                "NE245",
                "NE246",
                "NE247",
                "NE262",
                "NE263",
                "NE264",
                "NE265",
                "NE253",
                "NE252",
                "NE232",
                "NE233",
                "NE234",
                "NE250",
                "NE235",
                "NE236",
                "NE237",
                "NE238",
                "NE239",
                ""
            };
        /*
         *  상태전문 - 개국, 장애복구전문 처리
         */
        if( WorkType == MsgBrokerConst.DB_UPDATE_ERROR_MNG ) {
            if( DbMode.equals(MsgBrokerConst.MODE_UPDATE_HW_ALL_CLEAR) ) {
                try {
                    List<TCtErrorBasicJoin> rslt = errBasicMap.selectByJoin1( ErrBasic, ErrTxn );
                    if( rslt.size() == 0 ) {
                        logger.info("[DBInsertUpdate] 장애복구건 없음");
                        msgTX.rollback(safeData.getTXS());
                        safeData.setTXS(msgTX.getTransaction(MsgBrokerTransaction.defMSGTX));
                        return;
                    }
                    for( TCtErrorBasicJoin errBasicJoin: rslt ) {
                        TCtErrorBasic updErrBasic = new TCtErrorBasic();
                        TCtErrorNoti updErrNoti = new TCtErrorNoti();
                        TCtErrorTxn updErrTxn = new TCtErrorTxn();
                        if( errBasicJoin.getRepairDate() == null )
                            updErrTxn.setRepairDate( ErrTxn.getRepairDate() );
                        if( errBasicJoin.getRepairTime().equals("999999") )
                            updErrTxn.setRepairTime( ErrTxn.getRepairTime() );
                        if( errBasicJoin.getFinishDate() == null )
                            updErrTxn.setFinishDate( ErrTxn.getRepairDate() );
                        if( errBasicJoin.getFinishTime() == null )
                            updErrTxn.setFinishTime( ErrTxn.getRepairTime() );
                        if( errBasicJoin.getFinishUid() == null )
                            errBasicJoin.setFinishUid("online");
                        if( errBasicJoin.getSendStatus().equals("0") )
                            updErrNoti.setSendStatus("1");
                        updErrBasic.setErrorStatus("7000");
                        updErrBasic.setUpdateDate( safeData.getDSysDate() );
                        updErrBasic.setUpdateUid("online");
                        updErrBasic.setErrorNo( errBasicJoin.getErrorNo() );
                        updErrBasic.setCreateDate( errBasicJoin.getCreateDate() );
                        updErrBasic.setCreateTime( errBasicJoin.getCreateTime() );
                        updErrTxn.setUpdateDate( safeData.getDSysDate() );
                        updErrTxn.setUpdateUid("online");;;
                        updErrTxn.setErrorNo( errBasicJoin.getErrorNo() );
                        updErrTxn.setCreateDate( errBasicJoin.getCreateDate() );
                        updErrTxn.setCreateTime( errBasicJoin.getCreateTime() );
                        updErrNoti.setUpdateDate( safeData.getDSysDate() );
                        updErrNoti.setUpdateUid("online");;;
                        updErrNoti.setErrorNo( errBasicJoin.getErrorNo() );
                        updErrNoti.setCreateDate( errBasicJoin.getCreateDate() );
                        updErrNoti.setCreateTime( errBasicJoin.getCreateTime() );
                        /*
                         * Update
                         */
                        errBasicMap.updateByPrimaryKeySelective( updErrBasic );
                        errNotiMap.updateByPrimaryKeySelective( updErrNoti );
                        errTxnMap.updateByPrimaryKeySelective( updErrTxn );

                        /*
                         * Update Trigger
                         */
                    }
                }
                catch ( Exception e ) {
                    logger.info( ">>> [DBInsertUpdate] (T_CT_ERROR_BASIC)UPDATE ERROR [{}-{}][{}]",
                            ErrBasic.getCreateDate(), ErrBasic.getErrorNo(), e.getLocalizedMessage());
                    throw e;
                }
            }
            else {
                /*
                 *  해당 장애만 Clear
                 */
                if( ErrBasic.getOrgCd().equals(MsgBrokerConst.NICE_CODE) )  {
                    int i = 0;
                    for( i = 0; i < saNiceErrorList.length; i++ ) {
                        if( ErrBasic.getErrorCd().equals(saNiceErrorList[i]) ) {
                            if( curMacStateError[i] == '1' )
                                break;
                            else {
                                /*
                                 * 완료를 먼저 찍고 복구가 나중에 들어온 경우 unfinish에서 빠지게 됨으로
                                 * 복구시간이 넣어지지 않음에 따라 나이스 인 경우에만 .szCurErrList 이외에
                                 * 완료후복구 장애를 체크하여 복구 시간을 update 하도록 수정 2014.01.23
                                 */
                                try {
                                    if( errBasicMap.countByCond1( ErrBasic ) == 0 )
                                        return;
                                }
                                catch ( Exception e ) {
                                    return;
                                }
                                break;
                            }
                        }
                    }
                    if( i >= saNiceErrorList.length) return;
                }
                /*
                 *  정산기 일경우
                 */
                else if( ErrBasic.getOrgCd().equals(MsgBrokerConst.CS_CODE)
                      ||  ErrBasic.getOrgCd().equals(MsgBrokerConst.EMART_CODE) ) {
                    int i = 0;
                    for( i = 0; i < saCalcErrorList.length; i++ ) {
                        if( ErrBasic.getErrorCd().equals(saCalcErrorList[i]) ) {
                            if( curMacStateError[i] == '1' )
                                break;
                            else {
                                //logger.info("복구장애없음");
                                return;
                            }
                        }
                    }

                    if( i >= saCalcErrorList.length ) return;
                }
                /*
                 *  농협 신 상태전문이 아닌 타기관 전문일경우 처리
                 *  농협일경우에는 기존 장애 여부를 위에서 체크하므로 해당 장애 코드를 복구 처리
                 *  출동알림의 경우는 기본 상태필드 atm_state 가 아닌 FILLER 추가부분을 사용하므로 따로 처리
                 */
                else if( !ErrBasic.getOrgCd().equals(MsgBrokerConst.NONGH_CODE)
                      &&  !ErrBasic.getOrgCd().equals(MsgBrokerConst.ALARM_CODE) ) {
                    int i = 0;
                    for( i = 0 ; i < saAtmErrorList.length; i++ ) {
                        if( ErrBasic.getErrorCd().equals(saAtmErrorList[i]) ) {
                            if( curMacStateError[i] == '1' )
                                break;
                            else {
                                //logger.info("복구장애없음");
                                return;
                            }
                        }
                    }

                    if( i >= saAtmErrorList.length ) return;
                }
            }
            /*
             * 장애복구건 검색시 finish_date의 상태는 체크하지 않는다.
             * 관제요원들이 복구가 되지 않아도 날짜(finish_date)를 입력 하는경우가 있다.
             */
            logger.info("Update<<===");
            /*
             *  삼성생명으로 인해 f_get... function을 호출함에 따른 부하때문에 임시 분리 20090330 by BHJ
             */
            try {
                List<TCtErrorBasicJoin> rslt = errBasicMap.selectByJoin2( ErrBasic, ErrTxn );
                if( rslt.size() == 0 ) {
                    logger.info("[DBInsertUpdate] 장애복구건 없음");
                    msgTX.rollback(safeData.getTXS());
                    safeData.setTXS(msgTX.getTransaction(MsgBrokerTransaction.defMSGTX));
                    return;
                }
                for( TCtErrorBasicJoin errBasicJoin: rslt ) {
                    TCtErrorBasic updErrBasic = new TCtErrorBasic();
                    TCtErrorNoti updErrNoti = new TCtErrorNoti();
                    TCtErrorTxn updErrTxn = new TCtErrorTxn();
                    if( errBasicJoin.getRepairDate() == null )
                        updErrTxn.setRepairDate( ErrTxn.getRepairDate() );
                    if( errBasicJoin.getRepairTime().equals("999999") )
                        updErrTxn.setRepairTime( ErrTxn.getRepairTime() );
                    if( errBasicJoin.getFinishDate() == null )
                        updErrTxn.setFinishDate( ErrTxn.getRepairDate() );
                    if( errBasicJoin.getFinishTime() == null )
                        updErrTxn.setFinishTime( ErrTxn.getRepairTime() );
                    if( errBasicJoin.getFinishUid() == null )
                        errBasicJoin.setFinishUid("online");
                    if( errBasicJoin.getSendStatus().equals("0") )
                        updErrNoti.setSendStatus("1");
                    updErrBasic.setErrorStatus("7000");
                    updErrBasic.setUpdateDate( safeData.getDSysDate() );
                    updErrBasic.setUpdateUid("online");
                    updErrBasic.setErrorNo( errBasicJoin.getErrorNo() );
                    updErrBasic.setCreateDate( errBasicJoin.getCreateDate() );
                    updErrBasic.setCreateTime( errBasicJoin.getCreateTime() );
                    updErrTxn.setUpdateDate( safeData.getDSysDate() );
                    updErrTxn.setUpdateUid("online");;;
                    updErrTxn.setErrorNo( errBasicJoin.getErrorNo() );
                    updErrTxn.setCreateDate( errBasicJoin.getCreateDate() );
                    updErrTxn.setCreateTime( errBasicJoin.getCreateTime() );
                    updErrNoti.setUpdateDate( safeData.getDSysDate() );
                    updErrNoti.setUpdateUid("online");;;
                    updErrNoti.setErrorNo( errBasicJoin.getErrorNo() );
                    updErrNoti.setCreateDate( errBasicJoin.getCreateDate() );
                    updErrNoti.setCreateTime( errBasicJoin.getCreateTime() );
                    /*
                     * Update
                     */
                    errBasicMap.updateByPrimaryKeySelective( updErrBasic );
                    errNotiMap.updateByPrimaryKeySelective( updErrNoti );
                    errTxnMap.updateByPrimaryKeySelective( updErrTxn );

                    /*
                     * Update Trigger
                     */
                }
            }
            catch ( Exception e ) {
                logger.info( ">>> [DBInsertUpdate] (T_CT_ERROR_BASIC)UPDATE ERROR [{}-{}][{}]",
                        ErrBasic.getCreateDate(), ErrBasic.getErrorNo(), e.getLocalizedMessage());
                throw e;
            }
        }
        /*
         * 출동요청 취소
         */
        else if( WorkType == MsgBrokerConst.DB_UPDATE_CANCEL_MNG ) {
            TCtErrorBasic rsltErrBasic;
            try {
                rsltErrBasic = errBasicMap.selectByCond4( ErrBasic );
                if( rsltErrBasic == null ) {
                    logger.info("[DBInsertUpdate] ORG_CD[{}], BRANCH_CD[{}], MAC_NO[{}]",
                            ErrBasic.getOrgCd(), ErrBasic.getBranchCd(), ErrBasic.getMacNo() );
                    rsltErrBasic = new TCtErrorBasic();
                    rsltErrBasic.setOrgMsg("");
                }
            }
            catch( Exception e ) {
                logger.info(">>> [DBInsertUpdate] GET ORG_MSG ERROR [{}]", e.getMessage() );
                rsltErrBasic = new TCtErrorBasic();
                rsltErrBasic.setOrgMsg("");
            }
            /*
             * 외환은행 출동취소 무시. 20080305 김희천대리 요청
             * 그 외 기관은 출동취소 처리
             */
            if( !ErrBasic.getOrgCd().equals(MsgBrokerConst.KEB_CODE) ) {
                /*
                 * ORG_MSG에 기관 메모 덧붙이는 작업
                 */
                try {
                    List<TCtErrorBasicJoin> rslt = errBasicMap.selectByJoin3( ErrBasic );
                    if( rslt.size() == 0 ) {
                        logger.info("[DBInsertUpdate DB_UPDATE_CANCEL_MNG ] ORG_CD[{}], BRANCH_CD[{}], MAC_NO[{}]",
                                ErrBasic.getOrgCd(), ErrBasic.getBranchCd(), ErrBasic.getMacNo());
                        throw new Exception( String.format("[DBInsertUpdate DB_UPDATE_CANCEL_MNG ] ORG_CD[%s], BRANCH_CD[%s], MAC_NO[%s]",
                                ErrBasic.getOrgCd(), ErrBasic.getBranchCd(), ErrBasic.getMacNo()) );
                    }
                    for( TCtErrorBasicJoin errBasicJoin: rslt ) {
                        TCtErrorBasic updErrBasic = new TCtErrorBasic();
                        TCtErrorNoti updErrNoti = new TCtErrorNoti();
                        TCtErrorTxn updErrTxn = new TCtErrorTxn();
                        if( errBasicJoin.getRepairDate() == null )
                            updErrTxn.setRepairDate( ErrTxn.getRepairDate() );
                        if( errBasicJoin.getRepairTime().equals("999999") )
                            updErrTxn.setRepairTime( ErrTxn.getRepairTime() );
                        if( errBasicJoin.getFinishDate() == null )
                            updErrTxn.setFinishDate( ErrTxn.getRepairDate() );
                        if( errBasicJoin.getFinishTime() == null )
                            updErrTxn.setFinishTime( ErrTxn.getRepairTime() );
                        if( errBasicJoin.getFinishUid() == null )
                            errBasicJoin.setFinishUid("online");
                        if( errBasicJoin.getSendStatus().equals("0") )
                            updErrNoti.setSendStatus("1");
                        updErrBasic.setErrorStatus("7000");
                        updErrBasic.setOrgSendYn("3");
                        updErrBasic.setOrgMsg(rsltErrBasic.getOrgMsg() + "/" + ErrBasic.getOrgMsg());
                        updErrBasic.setUpdateDate( safeData.getDSysDate() );
                        updErrBasic.setUpdateUid("online");
                        updErrBasic.setErrorNo( errBasicJoin.getErrorNo() );
                        updErrBasic.setCreateDate( errBasicJoin.getCreateDate() );
                        updErrBasic.setCreateTime( errBasicJoin.getCreateTime() );
                        updErrTxn.setFinishType("0001");
                        updErrTxn.setFinishNm("취소");
                        updErrTxn.setUpdateDate( safeData.getDSysDate() );
                        updErrTxn.setUpdateUid("online");;;
                        updErrTxn.setErrorNo( errBasicJoin.getErrorNo() );
                        updErrTxn.setCreateDate( errBasicJoin.getCreateDate() );
                        updErrTxn.setCreateTime( errBasicJoin.getCreateTime() );
                        updErrNoti.setUpdateDate( safeData.getDSysDate() );
                        updErrNoti.setUpdateUid("online");;;
                        updErrNoti.setErrorNo( errBasicJoin.getErrorNo() );
                        updErrNoti.setCreateDate( errBasicJoin.getCreateDate() );
                        updErrNoti.setCreateTime( errBasicJoin.getCreateTime() );
                        /*
                         * Update
                         */
                        errBasicMap.updateByPrimaryKeySelective( updErrBasic );
                        errNotiMap.updateByPrimaryKeySelective( updErrNoti );
                        errTxnMap.updateByPrimaryKeySelective( updErrTxn );

                        /*
                         * Update Trigger
                         */
                    }
                }
                catch ( Exception e ) {
                    logger.info( ">>> [DBInsertUpdate] (T_CT_ERROR_BASIC)UPDATE ERROR [{}-{}][{}]",
                            ErrBasic.getTransDate(), ErrBasic.getOrgMsgNo(), e.getLocalizedMessage());
                    throw e;
                }
            }
            logger.info("출동취소 처리완료 [ORG_CD = '{}', BRANCH_CD = '{}', MAC_NO = '{}', TRANS_DATE = '{}', ORG_MSG_NO = '{}'",
                    ErrBasic.getOrgCd(), ErrBasic.getBranchCd(), ErrBasic.getMacNo(), ErrBasic.getTransDate(), ErrBasic.getOrgMsgNo());
       }
       logger.info("@@@ 장애복구 처리완료");
    }

   /**
    *
    * 기기 개시관리
    *
    * @author KDJ
    * @param MacInfo TMacInfo테이블 모델 클래스의 인스턴스 (기기정보)
    * @param ErrBasic T_CT_ERROR_MNG테이블 모델 클래스의 인스턴스
    * @throws Exception
    * @see TMacInfo
    * @see TCtErrorBasic
    */
    public void insertUpdateMacOpen( MsgBrokerData safeData, TMacInfo MacInfo, TCtErrorBasic ErrBasic ) throws Exception {

        TCtOpen open = new TCtOpen();
        open.setOrgCd( ErrBasic.getOrgCd() );                          // 기관코드
        open.setBranchCd( ErrBasic.getBranchCd() );                    // 지점코드
        open.setMacNo( ErrBasic.getErrorNo() );                        // 기번
        open.setOpenDate( ErrBasic.getCreateDate().toString() );       // 개시일
        open.setOpenTime( ErrBasic.getCreateTime() );                  // 개시시간
        open.setSerialNo( MacInfo.getSerialNo() );                     // 일련번호
        open.setMacAddress( MacInfo.getMacAddress() );                 // 맥어드레스
        open.setUpdateDate( safeData.getDSysDate() );
        try {
            openMap.insertSelective(open);
        }
        catch( org.springframework.dao.DataIntegrityViolationException e ) {
            try {
                openMap.updateByPrimaryKeySelective( open );
            }
            catch ( Exception ne ) {
                logger.info( ">>> [DBInsertUpdate] (T_CT_OPEN) UPDATE ERROR [{}]", ne.getMessage());
                throw ne;
            }
        }
        catch( Exception e ) {
            logger.info( ">>> [DBInsertUpdate] (T_CT_OPEN) INSERT ERROR [{}]", e.getMessage());
            throw e;
        }
    }

    /**
     *
     * 같은 전문추적 번호가 있는지 검사한다.
     * 기업 ATMS 1차통지 전문번호가 같은것이 있는지 Check
     *
     * @author KDJ, originated by 방혜진 (005/07/01)
     * @param ErrBasic    T_CT_ERROR_MNG테이블 모델 클래스의 인스턴스
     * @param CancelYN  장애출동 취소여부 1 - 취소, 0 - 정상
     * @throws Exception
     * @see TCtErrorBasic
     *
     */
    @Override
    public boolean getDupErrorMng( TCtErrorBasic ErrBasic, int CancelYN ) throws Exception {

        TCtUnfinish unfinish;

        if( CancelYN == 0 ) {
            unfinish = unfinishMap.selectByCond1(ErrBasic);
        }
        else {
            unfinish = unfinishMap.selectByCond2(ErrBasic);
        }

        if( unfinish == null ) {
            logger.info("!!!출동요청 장애 발생함!!! NO_DATA_FOUND");
            return false;
        }

        logger.info("...기존 출동요청 장애 발생껀_AAA...");

        /*
         *   경남은행 출동요청의 경우 출동요청 전문에 특이사항을 나중에 보내주므로
         *   특이사항 비교하여 UPDATE 처리 20090526
         */
        if( unfinish.getOrgCd().equals(MsgBrokerConst.KNATMS_CODE)
        &&  CancelYN == 0
        && ( unfinish.getOrgMsg().length()  == 0
          || !unfinish.getOrgMsg().equals(ErrBasic.getOrgMsg()) ) ) {
            errBasicMap.updateByCond1( ErrBasic );
        }
        return true;
    }

    /**
     *
     * T_CT_ERROR_MNG에 insert 가능여부 확인한다.
     *
     * @author KDJ
     * @param ErrBasic    T_CT_ERROR_MNG테이블 모델 클래스의 인스턴스
     * @throws Exception
     * @see TCtErrorBasic
     *
     */
    private boolean insertCheckErrorMng( TCtErrorBasic ErrBasic ) throws Exception {

        /*
         * 후처리 장애는 중복 체크하지 않고 모두 장애 발생 시킨다. 20111101
         */
        if( ErrBasic.getErrorCd().equals("AFTMNG") )
            return false;

        TCtUnfinish unfinish = unfinishMap.selectByCond3(ErrBasic);
        if( unfinish == null ) {
            logger.info("!!!장애 발생함!!!");
            return false;
        }

        logger.info("... 기존 장애 발생껀[{}]...", ErrBasic.getErrorCd() );
        return true;
    }

    private class AutoSendInfoReturn {

        String memberId;
        String memberNm;
        String memberTel;
        int waitTime;
        int guardSite;
        String outCd;

    }
    /**
     * 장애코드에 의해 자동통보 유무와
     * 자동통보 Wait시간을 얻는다.
     *
     * @author KDJ, originated by 허훈 (2007/11/12)
     * @param ErrBasic  T_CT_ERROR_MNG 테이블 모델 클래스의 인스턴스
     * @param MacInfo  기기정보 모델 클래스의 인스턴스
     * @param RetASI  함수의 반환값 처리 클래스의 인스턴스
     * @return int    AutoSMS flag
     * @throws Exception
     */
    private int getAutoSendInfo( TCtErrorBasic ErrBasic, TMacInfo MacInfo, AutoSendInfoReturn RetASI ) throws Exception {

        String sDayOfWeek;
        TCmSiteCond cmSiteCond = new TCmSiteCond();
        TCmSite01Cond cmSite01Cond = new TCmSite01Cond();
        TCtNiceMac niceMac = new TCtNiceMac();
        TCmSite cmSite = new TCmSite();
        TMisc miscHoli = new TMisc();

        /*
         * Initialize
         */
        cmSiteCond.setSendTypeDetail(0);
        cmSite01Cond.setSendTypeDetail(0);

        miscHoli.setHoliday("0");

        /*
         *[기업은행] 장애코드 예외처리사항
         */
        if( ErrBasic.getOrgCd().equals(MsgBrokerConst.KIUP_CODE) ) {
            /*
             * 현금부족 5만원,1만원이 같은 코드로 들어온다.
             * ex) ORG_MSG : [만:정상,오만:부족] 자동출동:현금부족(현금출금부) 요청차수[01]/
             * 1만원 부족일 경우에는 기존코드로,
             * 1만원 정상일 경우(5만원만 부족 또는 기타)일 경우는 별도코드로 강제수정한다.
             *
             */
            if( ErrBasic.getErrorCd().equals("KI901_73") ) {
                if( ErrBasic.getOrgMsg().indexOf("[만:정상") >= 0 ) {
                    ErrBasic.setErrorCd("KI901Z73");
                    logger.info(" 기업은행 ORG_MSG[{}] -> 장애코드[{}]으로 강제수정", ErrBasic.getOrgMsg(), ErrBasic.getErrorCd());
                }
            }
        }

        /*
         * 나이스 장애중 마사회장애는 금요일 토요일 일요일에만 장애 발생하고 그외에는 완료처리한다.
         */
        if( ErrBasic.getOrgCd().equals(MsgBrokerConst.NICE_CODE) ) {
            TCmSiteKey cmSiteKey = new TCmSiteKey();
            cmSiteKey.setOrgCd( ErrBasic.getOrgCd() );
            cmSiteKey.setBranchCd( ErrBasic.getBranchCd() );
            cmSiteKey.setSiteCd( ErrBasic.getSiteCd() );
            try {
                cmSite = cmSiteMap.selectByPrimaryKey( cmSiteKey );
                if( cmSite == null ) {
                    cmSite = new TCmSite();
                    logger.info(">>> [getAutoSendInfo] AUTO SEND INFO NOT FOUND");
                    logger.info(">>>             기관코드 ORG_CD   [{}]\n", cmSiteKey.getOrgCd());
                    logger.info(">>>             에러코드 ERROR_CD [{}]\n", ErrBasic.getErrorCd());
                    cmSite.setOrgCd( cmSiteKey.getOrgCd() );
                    cmSite.setBranchCd( cmSiteKey.getBranchCd() );
                    cmSite.setSiteCd( cmSiteKey.getSiteCd() );
                    cmSite.setPlaceType("    ");
                }
            }
            catch ( Exception e ) {
                logger.info( ">>> [DBGetAutoSendInfo][t_cm_site] Error [{}]\n", e.getMessage());
                cmSite = new TCmSite();
                cmSite.setOrgCd( cmSiteKey.getOrgCd() );
                cmSite.setBranchCd( cmSiteKey.getBranchCd() );
                cmSite.setSiteCd( cmSiteKey.getSiteCd() );
                cmSite.setPlaceType("    ");
            }

            /*
             * 마사회장회일시에는 금 토 일에만 장애발생 , 경륜장 장애도 마찬가지
             */
            sDayOfWeek = String.valueOf(MsgBrokerLib.currentDayOfWeek());
            if( cmSite.getPlaceType().equals("0026")
            ||  cmSite.getPlaceType().equals("0086") ) {
                if( cmSite.getDetailArea1().equals("8602") ) {
                    /*
                     * 상세장소구분이 경정일 경우는 수목금토일 외에 장애 발생 안함
                     */
                    if( sDayOfWeek.matches("[1|6|7|4|5]") )
                        return MsgBrokerConst.AUTO_NOT_CREATE;
                }
                else {
                    /*
                     * 마사회,상세장소구분이 경륜일 경우 장애시 금토일 외에는 장애 발생 안함
                     */
                    if( sDayOfWeek.matches("[1|6|7]") )
                        return MsgBrokerConst.AUTO_NOT_CREATE;
                }

            }

            /*
             * 나이스 장애중 22시 이후 오전 8시 이전 데이타는 대기후 자동통보로 변경한다.
             */
            if( ErrBasic.getCreateTime().compareTo("220000") >= 0
            ||  ErrBasic.getCreateTime().compareTo("080000") < 0 ) {
                /*
                 * 나이스이고 22시 이후
                 */
                if( ErrBasic.getCreateTime().compareTo("080000") < 0 ) {
                    /*
                     * 익일 08:00분 통보
                     */
                    cmSite01Cond.setSendTypeDetail(2);
                    cmSiteCond.setSendTypeDetail(2);
                }
                /*
                 * 나이스이고 0시부터 8시 이전
                 */
                else {
                    /*
                     * 당일 08:00분 통보
                     */
                    cmSite01Cond.setSendTypeDetail(3);
                    cmSiteCond.setSendTypeDetail(3);
                }

            }

            /*
             * 나이스 기기중 브랜드 제휴 기관일 경우 기관코드를 가져 온다
             */
            TCtNiceMacKey niceMacKey = new TCtNiceMacKey();
            niceMacKey.setOrgCd( ErrBasic.getOrgCd() );
            niceMacKey.setBranchCd( ErrBasic.getBranchCd() );
            niceMacKey.setMacNo( ErrBasic.getMacNo() );

            try {
                niceMac = niceMacMap.selectByCond1( niceMacKey );
                if( niceMacMap == null ) {
                    niceMac = new TCtNiceMac();
                }
            }
            catch ( Exception e ) {
                niceMac = new TCtNiceMac();
                logger.info( ">>> [getAutoSendInfo][t_ct_nice_mac] Error 브랜드제휴 검색실패{}", e.getMessage());
            }
        }

        /*
         * 현금부족 장애가 아닐 경우 경비사를 체크한다. 수표부족도 제외, 경비사 처리불가 장애도 제외 2008.09.19
         */
        TCtErrorKey errKey = new TCtErrorKey();
        errKey.setOrgCd( ErrBasic.getOrgCd() );
        errKey.setErrorCd( ErrBasic.getErrorCd() );
        TCtError errRec;
        try {
            errRec = errMap.selectByPrimaryKey( errKey );
            if( errRec == null ) {
                errRec = new TCtError();
                errRec.setGroupErrorCd("");
                errRec.setGuardYn("");
                logger.info(">>> [getAutoSendInfo] 그룹에러코드 찾기 실패[NO_DATA_FOUND]");
                logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate() );
                logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd() );
                logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd() );
                logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd() );
            }
        }
        catch ( Exception e ) {
            errRec = new TCtError();
        }
        logger.debug("KDJ #1");
        logger.debug(" errRec = {}", errRec);
        logger.debug(" errRec.GroupErrorCd = {}", errRec.getGroupErrorCd());
        logger.debug(" errRec.GroupErrorCd len = {}", errRec.getGroupErrorCd().length());
        logger.debug(" errRec.GroupErrorCd match = {}", errRec.getGroupErrorCd().equals("1100"));
        if( !errRec.getGroupErrorCd().equals("1100")
        &&  !errRec.getGroupErrorCd().equals("1300")
        &&  !errRec.getGuardYn().equals("N") ) {
            logger.info(">>> [getAutoSendInfo] GuardYn[{}]", errRec.getGuardYn());
            /*
             * 전체외주 사이트인 경우에는 수동통보처리, 부분외주인 경우에는 나이스 시간이 아닌경우 수동통보처리한다.
             * 긴급 : 삼성생명은 제외한다.
             */
            TCmSite cmSiteRec;
            cmSiteCond.setOrgCd( ErrBasic.getOrgCd() );
            cmSiteCond.setBranchCd( ErrBasic.getBranchCd() );
            cmSiteCond.setSiteCd( ErrBasic.getSiteCd() );
            cmSiteCond.setMacNo( ErrBasic.getMacNo() );
            try {
                if( ErrBasic.getOrgCd().equals(MsgBrokerConst.SL_CODE)) {
                    cmSiteRec = cmSiteMap.selectByCond1( cmSiteCond );
                }
                else {
                    cmSiteRec = cmSiteMap.selectByCond2( cmSiteCond );
                }
            }
            catch ( Exception e ) {
                cmSiteRec = new TCmSite();
                logger.info(">>> [getAutoSendInfo] 외주타입 찾기 실패[{}]", e.getMessage());
                logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
            }
            if( cmSiteRec == null ) {
                cmSiteRec = new TCmSite();
                logger.info(">>> [getAutoSendInfo] 외주타입 찾기 실패[NO_DATA_FOUND]");
                logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
            }
            /*
             * 전체외주일 경우 수동통보
             */
            if( cmSiteRec.getOutType().equals("1") ) {
                /*
                 * 외주일경우 값을 1로 넘겨줌
                 */
                RetASI.guardSite = 1;
                /*
                 * 20080311 수정 : 외주통보시 recv_place추가
                 */
                RetASI.outCd = cmSiteRec.getOutCd();

            }
            /*
             * 부분외주일 경우에는 시간대를 조사하여 나이스 시간대에 맞으면 정상처리 그렇지 않으면 수동통보한다.
             */
            else if( cmSiteRec.getOutType().equals("2") ) {
                try {
                    miscHoli = miscMap.selectHoliday();
                }
                catch ( Exception e ) {
                    miscHoli = new TMisc();
                    logger.info(">>> [getAutoSendInfo] 주말인지 찾기 실패 에러-> 정상처리한다.[{}]", e.getMessage());
                    logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                    logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                    logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                    logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
                    miscHoli.setHoliday("0");
                }
                if( miscHoli == null ) {
                    miscHoli = new TMisc();
                    logger.info(">>> [getAutoSendInfo] 주말인지 찾기 실패 에러-> 정상처리한다.[NO_DATA_FOUND]");
                    logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                    logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                    logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                    logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
                    miscHoli.setHoliday("0");
                }
                /*
                 * 쉬는날인 경우에는 t_cm_site_01을 뒤진다.
                 * 통보 대기 시간을 고려하여 시간 체크 2008.07.03
                 */
                try {
                    if( miscHoli.getHoliday().compareTo("0") > 0 ) {
                        cmSite01Cond.setOrgCd( ErrBasic.getOrgCd() );
                        cmSite01Cond.setBranchCd( ErrBasic.getBranchCd() );
                        cmSite01Cond.setSiteCd( ErrBasic.getSiteCd() );
                        cmSite01Cond.setMacNo( ErrBasic.getMacNo() );
                        TCmSite01 retSite01Rec = cmSite01Map.selectByCond1( cmSite01Cond );
                        if( retSite01Rec == null ) {
                            logger.info(">>> [getAutoSendInfo] 나이스 시간내 인지 찾기 실패 -> 부분 외주");
                            logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                            logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                            logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                            logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
                            /*
                             * 외주일경우 값을 1로 넘겨줌
                             */
                            RetASI.guardSite = 1;
                            RetASI.outCd = cmSiteRec.getOutCd();
                        }
                    }
                    else {
                        TCmSite retSiteRec = cmSiteMap.selectByCond3( cmSiteCond );
                        if( retSiteRec == null ) {
                            logger.info(">>> [getAutoSendInfo] 나이스 시간내 인지 찾기 실패 -> 부분 외주");
                            logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                            logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                            logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                            logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
                            /*
                             * 외주일경우 값을 1로 넘겨줌
                             */
                            RetASI.guardSite = 1;
                            RetASI.outCd = cmSiteRec.getOutCd();
                        }
                    }
                }
                catch ( Exception e ) {
                    logger.info(">>> [getAutoSendInfo] 나이스 시간내 인지 찾기 실패 에러-> 정상처리한다.[{}]\n", e.getMessage());
                    logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                    logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                    logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                    logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
                }
            }
        }

        logger.debug("KDJ #2");
        /*
         * CD_VAN 명세표부족/예보의 경우 명세표부 사용여부에 따라 장애 발생 여부 설정
         * 2012.07.10 명세표부 설정이 잘못된 경우가 있어 입금설정이 '0'(입금가능)으로 되어 있는 경우도 추가
         */
        if( ErrBasic.getOrgCd().equals(MsgBrokerConst.NICE_CODE)
        &&  (ErrBasic.getErrorCd().equals("NI112")
          || ErrBasic.getErrorCd().equals("NI123")) ) {
            TCtNiceMacKey niceMacKey = new TCtNiceMacKey();
            niceMacKey.setOrgCd( ErrBasic.getOrgCd() );
            niceMacKey.setBranchCd( ErrBasic.getBranchCd() );
            niceMacKey.setMacNo( ErrBasic.getMacNo() );

            TCtNiceMac niceMacRec;
            try {
                niceMacRec = niceMacMap.selectByPrimaryKey( niceMacKey );
                if( niceMacRec ==  null ) {
                    logger.info(">>> [getAutoSendInfo] gubun : 1  org_cd : {}, error_cd : NI112,NI123 Error [NO_DATA_FOUND]", ErrBasic.getOrgCd());
                    return  MsgBrokerConst.AUTO_NOT_CREATE;
                }
                /*
                 * 20120710 명세표 사용 안함 보다 입금가능이 우선, 입금가능이라면 명세표 장애 발생처리
                 * "1"=> 입금불가능 = CD기
                 */
                if( niceMacRec.getInAmtYn().equals("1") ) {
                    /*
                     * 명세표 사용안함이라면 데이타 생성 안함
                     * "1"=> 명세표 사용
                     */
                    if( !niceMacRec.getSpecsYn().equals("1") ) {
                        return  MsgBrokerConst.AUTO_NOT_CREATE;
                    }
                }
            }
            catch ( Exception e ) {
                logger.info( ">>> [getAutoSendInfo] RET_ERROR (t_cm_mac_nicecommon)IS SELECT CHECK ERROR [{}]", e.getMessage());
                return MsgBrokerConst.AUTO_NOT_CREATE;
            }
        }

        logger.debug("KDJ #3");
        /*
         * 나이스 브랜드 제휴 기관
         */
        TCtError errQryRec = new TCtError();
        TCtErrorCond errCond = new TCtErrorCond();
        if( niceMac.getJoinCd().length() > 0 ) {
            /*
             * 20101227 나이스 기기중 브랜드 제휴 기기 일 경우 장애 통보 참조 테이블을 t_ct_error_brand 로
             * 회선장애 이면서 'A'등급이라면 20분 나머지는 'T_CT_ERROR_BRAND' 에 정의된데로
             * 20120119 CD-VAN 기업은행 브랜드 제휴 중 KT공중전화 설치 기기의 회선 장애는 즉시 통보 하도록 한다. 운총 최시명 요청
             */
            errCond.setOrgCd( ErrBasic.getOrgCd() );
            errCond.setErrorCd( ErrBasic.getErrorCd() );
            errCond.setMacGrade( MacInfo.getMacGrade() );
            errCond.setBrandOrgCd( niceMac.getJoinCd() );

            try {
                errQryRec = errMap.selectByJoin1( errCond );
                if( errQryRec == null ) {
                    logger.info(">>> [getAutoSendInfo] AUTO SEND INFO FIND ERROR [NO_DATA_FOUND]\n");
                    logger.info(">>>             기관코드 ORG_CD   [{}]\n", ErrBasic.getOrgCd());
                    logger.info(">>>             에러코드 ERROR_CD [{}]\n", ErrBasic.getErrorCd());
                    return MsgBrokerConst.AUTO_SEND_FALSE;
                }
            }
            catch ( Exception e ) {
                logger.info( ">>> [gGetAutSendInfo] Error [{}]\n", e.getMessage());
                return MsgBrokerConst.AUTO_SEND_FALSE;
            }
            if( ErrBasic.getErrorCd().equals("NI101")
            &&  cmSite.getPlaceType().equals("0084")
            &&  niceMac.getJoinCd().equals("003") ) {
                logger.info( "기업은행 브랜드 제휴-KT공중전화 설치 사이트 AutoYn[{}] WaitTime[{}] - 즉시통보 대상 기기",
                             errQryRec.getAutoSendYn(), errQryRec.getWaitTime1() );
            }

        }
        else {
            errCond.setOrgCd( ErrBasic.getOrgCd() );
            errCond.setErrorCd( ErrBasic.getErrorCd() );
            errCond.setMacGrade( MacInfo.getMacGrade() );
            errCond.setBrandOrgCd( niceMac.getJoinCd() );

            try {
                errQryRec = errMap.selectByCond1( errCond );
                if( errQryRec == null ) {
                    logger.info(">>> [getAutoSendInfo] AUTO SEND INFO FIND ERROR [NO_DATA_FOUND]\n");
                    logger.info(">>>             기관코드 ORG_CD   [{}]\n", ErrBasic.getOrgCd());
                    logger.info(">>>             에러코드 ERROR_CD [{}]\n", ErrBasic.getErrorCd());
                    return MsgBrokerConst.AUTO_SEND_FALSE;
                }
            }
            catch ( Exception e ) {
                logger.info( ">>> [gGetAutSendInfo] Error [{}]\n", e.getMessage());
                return MsgBrokerConst.AUTO_SEND_FALSE;
            }

        }

        /*
         * 장애 코드 검색 => 위치 변경 (아래에서 위로) 그래서 바로 아래 장애그룹코드 가져오는 부분은 자동 삭제 2008.06.30
         * 20090407 요청 김희천, 나이스 회선장애의 경우 기기등급이 A 등급일 경우 대기시간 20분 나머지 장애 WAIT TIME으로
         */

        /*
         * 장애처리 여부 가 false이면
         */
        if( errQryRec.getErrorMotYn().equals("N") ) {
            return MsgBrokerConst.AUTO_FINISH;
        }

        /*
         * 국민은행이면서 에러코드가 NI141인경우 22시에서 08시 사이이면 자동 완료시킨다.
         */
        if( (MsgBrokerLib.SysTime().compareTo("220000") >= 0
          || MsgBrokerLib.SysTime().compareTo("080000") <= 0)
         && ErrBasic.getOrgCd().equals(MsgBrokerConst.KBST_CODE)
         && ErrBasic.getErrorCd().equals("NI141") ) {
            return MsgBrokerConst.AUTO_FINISH;
        }
        TMisc misc;
        try {
            misc = miscMap.selectHoliday();
        }
        catch ( Exception e ) {
            misc = new TMisc();
            logger.info(">>> [getAutoSendInfo] 주말인지 찾기 실패 에러-> 정상처리한다.[{}]", e.getMessage());
            logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
            logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
            logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
            logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
            misc.setHoliday("0");
        }
        if( misc == null ) {
            misc = new TMisc();
            logger.info(">>> [getAutoSendInfo] 주말인지 찾기 실패 에러-> 정상처리한다.[NO_DATA_FOUND]");
            logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
            logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
            logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
            logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
            misc.setHoliday("0");
        }

        /*
         * 무거래건은 무거래 통보 시작시간 ~ 무거래 통보 완료시간으로 가져온다  20080429 적용 운영중단시간 체크 추가 20080429
         * 무거래 통보는 무거래통보시작시간 이후 2시간 후에 통보하도록
         */
        TCmSite retRec;
        try {
            if( ErrBasic.getErrorCd().equals(MsgBrokerConst.NICE_ERROR_USER_N04) ) {
                cmSiteCond.setOrgCd( ErrBasic.getOrgCd() );
                cmSiteCond.setBranchCd( ErrBasic.getBranchCd() );
                cmSiteCond.setSiteCd( ErrBasic.getSiteCd() );
                cmSiteCond.setMacNo( ErrBasic.getMacNo() );
                cmSiteCond.setCreateDate( String.valueOf(ErrBasic.getCreateDate()) );
                cmSiteCond.setCreateTime( ErrBasic.getCreateTime() );
                cmSiteCond.setWaitTime(errQryRec.getWaitTime1());

                retRec = cmSiteMap.selectByJoin1( cmSiteCond );
            }
            else {
                /*
                 * 평일일 경우
                 */
                if( miscHoli.getHoliday().equals("0") ) {
                    cmSiteCond.setOrgCd( ErrBasic.getOrgCd() );
                    cmSiteCond.setBranchCd( ErrBasic.getBranchCd() );
                    cmSiteCond.setSiteCd( ErrBasic.getSiteCd() );
                    cmSiteCond.setMacNo( ErrBasic.getMacNo() );
                    cmSiteCond.setCreateDate( String.valueOf(ErrBasic.getCreateDate()) );
                    cmSiteCond.setCreateTime( ErrBasic.getCreateTime() );
                    cmSiteCond.setWaitTime(errQryRec.getWaitTime1());

                    retRec = cmSiteMap.selectByCond4( cmSiteCond );
                }
                /*
                 * 휴일일 경우
                 */
                else {
                    /*
                     * 20090730 시간내기기 휴일 자동통보 방지 요청에 따라 수정 요청자 김희천
                     */
                    TCmSiteKey cmSiteKey = new TCmSiteKey();
                    cmSiteKey.setOrgCd( ErrBasic.getOrgCd() );
                    cmSiteKey.setBranchCd( ErrBasic.getBranchCd() );
                    cmSiteKey.setSiteCd( ErrBasic.getSiteCd() );
                    try {
                        cmSite = cmSiteMap.selectByPrimaryKey( cmSiteKey );
                        if( cmSite == null ) {
                            logger.info(">>> [getAutoSendInfo] 사이트 데이터 없음 기관[{}]지점[{}]사이트[{}]",
                            cmSiteKey.getOrgCd(), cmSiteKey.getBranchCd(), cmSiteKey.getSiteCd() );
                            return MsgBrokerConst.AUTO_SEND_FALSE;
                        }
                    }
                    catch ( Exception e ) {
                        logger.info( ">>> [getAutoSendInfo] 운영형태 검색실패 others 기관[{}]지점[{}]사이트[{}] [{}]",
                                cmSiteKey.getOrgCd(), cmSiteKey.getBranchCd(), cmSiteKey.getSiteCd(), e.getMessage());
                        logger.info(">>>             발생일자 [{}]", cmSiteCond.getCreateDate());
                        logger.info(">>>             발생시간 [{}]", cmSiteCond.getCreateTime());
                        return MsgBrokerConst.AUTO_SEND_FALSE;
                    }
                    if( cmSite.getOperType().equals("1100") ) {
                        logger.info( ">>> 시간내 기기의 휴일 장애 발생 수동처리" );
                        return MsgBrokerConst.AUTO_SEND_FALSE;
                    }
                    cmSiteCond.setOrgCd( ErrBasic.getOrgCd() );
                    cmSiteCond.setBranchCd( ErrBasic.getBranchCd() );
                    cmSiteCond.setSiteCd( ErrBasic.getSiteCd() );
                    cmSiteCond.setMacNo( ErrBasic.getMacNo() );
                    cmSiteCond.setCreateDate( String.valueOf(ErrBasic.getCreateDate()) );
                    cmSiteCond.setCreateTime( ErrBasic.getCreateTime() );
                    cmSiteCond.setWaitTime(errQryRec.getWaitTime1());

                    retRec = cmSiteMap.selectByJoin2( cmSiteCond );
                }
            }
            if( retRec == null ) {
                logger.info(">>> [getAutoSendInfo] 운영시간 외 장애 발생 [NO_DATA_FOUND]");
                logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                logger.info(">>>             발생시간 [{}]", ErrBasic.getCreateTime());
                return MsgBrokerConst.AUTO_SEND_FALSE;
            }
        }
        catch( Exception e ) {
            logger.info(">>> [getAutoSendInfo] 운영시간 외 장애 발생 [{}]", e.getMessage());
            logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
            logger.info(">>>             발생시간 [{}]", ErrBasic.getCreateTime());
            return MsgBrokerConst.AUTO_SEND_FALSE;

        }

        /*
         * 나이스 장애 중 카드판독기이상
         * 장애수신시 카드판독기이상일시에 MADE_ERROR_CD=400,420은 즉시통보, 그렇지 않다면 수동통보
         */
        if( ErrBasic.getOrgCd().equals(MsgBrokerConst.NICE_CODE)
        &&  ErrBasic.getErrorCd().equals("NI122") ) {
            if( ErrBasic.getMadeErrCd().equals("400") || ErrBasic.getMadeErrCd().equals("420") ) {
                return MsgBrokerConst.AUTO_SEND_FALSE;
            }
        }

        /*
         * 추가요청사항 4월 14일 - 현금부족 장애(일괄)의 경우에는 장애발생시 현송중이면 수동통보로 변경
         */
        if( errQryRec.getGroupErrorCd().equals("1100") ) {
            TFnSendReport     fnSRpt;
            TFnSendReportCond fnSRptCond = new TFnSendReportCond();
            fnSRptCond.setOrgCd( ErrBasic.getOrgCd() );
            fnSRptCond.setBranchCd( ErrBasic.getBranchCd() );
            fnSRptCond.setSiteCd( ErrBasic.getSiteCd() );
            fnSRptCond.setMacNo( ErrBasic.getMacNo() );
            try {
                fnSRpt = fnSRptMap.selectByCond1( fnSRptCond );
                if( fnSRpt == null ) {
                    logger.info(">>> [getAutoSendInfo] 현금부족 장애시 해당 싸이트에 대한 도착보고 없음");
                    logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                    logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                    logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                    logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
                }
                else {
                    logger.info("AUTO_SEND_FALSE : 현송 중 수동통보");
                    return MsgBrokerConst.AUTO_SEND_FALSE;
                }
            }
            catch ( Exception e ) {
                logger.info(">>> [getAutoSendInfo] 현금부족 장애시 해당 싸이트에 대한 도착보고 찾기 실패[{}]", e.getMessage());
                logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
            }
        }
        RetASI.waitTime = cmSiteCond.getWaitTime();

        /*
         * 20090520 김희천 원복 요청에 의해 마킹 ===> 20090625 김희천 임시요청 요청사항 개발 전까지 수동으로 처리하도록
         * 금일 AS접수된 기기가 아니라면 수동으로 처리하도록 금일 접수 기기는 자동으로     20090708 김희천 요청
         */
        if( !MacInfo.getAsAcptYn().equals(MsgBrokerLib.SysDate())
         && !MacInfo.getAsAcptYn().equals("0")
         && MacInfo.getAsAcptYn().length() > 0 ) {
            logger.info("  이전일 AS 접수기기 수동통보처리" );
            return MsgBrokerConst.AUTO_SEND_FALSE;
        }

        /*
         * 우리은행 미개국 출동요청 'WR409'의 경우 철수기기(임시철수기기)의 경우 수동통보 처리 BY BHJ 20120103
         */
        if( ErrBasic.getOrgCd().equals(MsgBrokerConst.WRATMS_CODE)
        &&  ErrBasic.getErrorCd().equals("WR409")
        && ( MacInfo.getOpenDate().length() == 0
          || MacInfo.getOpenDate().compareTo(MsgBrokerLib.SysDate()) > 0
          || ( MacInfo.getCloseDate().length() > 0 && MacInfo.getCloseDate().compareTo(MsgBrokerLib.SysDate()) <= 0) )) {
            logger.info(" 우리은행 미운영기기의 출동요청_미개국점검 장애 발생 수동통보처리");
            return MsgBrokerConst.AUTO_SEND_FALSE;
        }

        if( errQryRec.getAutoSendYn().equals("N") ) {
            logger.info(">>> [getAutoSendInfo] AutoSendYN == 'N'");
            return MsgBrokerConst.AUTO_SEND_FALSE;
        }
        /*
         * 외주사이트이면 (경비사)
         */
        else if( RetASI.guardSite == 1) {
            return MsgBrokerConst.AUTO_SEND_TRUE;
        }
        else if( errQryRec.getAutoSendYn().equals("Y") && errQryRec.getWaitTime1() == 0 ) {
            /*
             * 경비사 전문이 완료되기 전까지는 경비사로 처리하지 않는다. ==> 아래로 이동
             */
            RetASI.guardSite = 0;
            TCmMember retMemberRec;
            TCmMemberCond cmMemberCond = new TCmMemberCond();
            cmMemberCond.setOrgCd( ErrBasic.getOrgCd() );
            cmMemberCond.setBranchCd( ErrBasic.getBranchCd() );
            cmMemberCond.setSiteCd( ErrBasic.getSiteCd() );
            cmMemberCond.setMacNo( ErrBasic.getMacNo() );
            try {
                retMemberRec = cmMemberMap.selectByJoin1( cmMemberCond );
                if( retMemberRec == null ) {
                    logger.info(">>> [getAutoSendInfo] AUTO SEND INFO MEMBER FIND ERROR");
                    logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                    logger.info(">>>             에러코드 [{}]", ErrBasic.getErrorCd());
                    logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                    logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                    logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
                    return MsgBrokerConst.AUTO_SEND_FALSE;
                }
                RetASI.memberId = retMemberRec.getMemberId();
                RetASI.memberNm = retMemberRec.getMemberNm();
                RetASI.memberTel = retMemberRec.getNiceHp();
            }
            catch ( Exception e ) {
                logger.info(">>> [getAutoSendInfo] Error [{}]\n", e.getMessage());
                logger.info(">>>             발생일자 [{}]", ErrBasic.getCreateDate());
                logger.info(">>>             에러코드 [{}]", ErrBasic.getErrorCd());
                logger.info(">>>             기관 [{}]", ErrBasic.getOrgCd());
                logger.info(">>>             지점 [{}]", ErrBasic.getBranchCd());
                logger.info(">>>             사이트 [{}]", ErrBasic.getSiteCd());
                return MsgBrokerConst.AUTO_SEND_FALSE;
            }
            if( retMemberRec.getNiceHp().substring(0, 4).equals("010") ) {
                return MsgBrokerConst.AUTO_SEND_TRUE;
            }
            else {
               logger.info(">>> [gGetAutoSendInfo] 전화번호가 010이 아님");
               return MsgBrokerConst.AUTO_SEND_FALSE;
            }
        }
        else
            return MsgBrokerConst.AUTO_SEND_TRUE;
    }

    /**
     *
     * 해당 기기에서 장애가 중복으로 발생했을 경우에
     * 이미 통보된   장애가 완료가 안된 건은 이전 장애가
     * 기타장애가 아닌 경우 후발생 장애를 중복장애로 본다.
     *
     * @author KDJ, originated by 방혜진 (005/07/01)
     * @param ErrBasic    T_CT_ERROR_MNG테이블 모델 클래스의 인스턴스
     * @throws Exception
     */
    private int getDupError( TCtErrorBasic ErrBasic ) throws Exception {
        TCtErrorBasic retErrorMng;

        try {
            if( ErrBasic.getOrgCd().equals(MsgBrokerConst.SL_CODE) ) {
                retErrorMng = errBasicMap.selectByCond2( ErrBasic );
            }
            else {
                retErrorMng = errBasicMap.selectByCond3( ErrBasic );

            }
            if( retErrorMng == null ) {
                logger.info(">>> [getDupError] org_cd[{}], branch_cd[{}], mac_no[{}], error_cd[{}] 해당 기기 중복장애건 없음",
                        ErrBasic.getOrgCd(), ErrBasic.getBranchCd(), ErrBasic.getMacNo(), ErrBasic.getErrorCd());
                return 0;
            }
        }
        catch ( Exception e ) {
            logger.info(">>> [getDupError] DB Error 발생 [{}]", e.getMessage() );
            return -1;
        }
        logger.info(">>> [getDupError] org_cd[{}], branch_cd[{}], mac_no[{}], error_cd[{}], error_no[{}] 해당 기기 중복장애건 있음",
                ErrBasic.getOrgCd(), ErrBasic.getBranchCd(), ErrBasic.getMacNo(), ErrBasic.getErrorCd(), ErrBasic.getErrorNo());
        return 1;

    }

    /**
     * 해당 기기의 현재 발생되어있는 상태장애 목록을 얻는다.
     *
     * @author KDJ, originated by 방혜진
     * @param ErrorState 에러 상태 확인을 위한 대상 기기 정보
     * @throws Exception
     */
    public byte[] getCurrentErrorState( ErrorState ErrorState ) throws Exception {

        ErrorState retErrState = null;
        try {
            if( ErrorState.getMacType() == MsgBrokerConst.CURERR_NICE ) {
                retErrState = unfinishMap.selectByCond4( ErrorState );
            }
            else if( ErrorState.getMacType() == MsgBrokerConst.CURERR_CALC ) {
                retErrState = unfinishMap.selectByCond5( ErrorState );
            }
            else {
                /*
                 * 삼성생명으로 인해 f_get... function을 호출함에 따른 부하때문에 임시 분리 20090330 by BHJ
                 */
                if( ErrorState.getOrgCd().equals(MsgBrokerConst.SL_CODE) ) {
                    retErrState = unfinishMap.selectByCond6( ErrorState );
                }
                else {
                    retErrState = unfinishMap.selectByCond7( ErrorState );
                }
            }
            if( retErrState == null ) {
                retErrState = new ErrorState();
                retErrState.setErrorStates("000000000000000000000000000000000000000000000000000000000000000000000000");
            }
        }
        catch (Exception e) {
            retErrState = new ErrorState();
            retErrState.setErrorStates("000000000000000000000000000000000000000000000000000000000000000000000000");
        }
        return retErrState.getErrorStates().getBytes();
    }

    /**
     * 기기 갱신
     *
     * @author KDJ
     * @since 2014/07/25
     * @param safeData 비지니스 공유정보
     * @param MacInfo  기기정보
     * @param Mac      변경하려는 기기의 정보
     * @throws Exception
     */
    public void updateMacInfo( MsgBrokerData safeData, TMacInfo MacInfo, TCmMac Mac) throws Exception {
        /*
         *  기존 정보 모두 변경이 없다면 return
         */
        if( MacInfo.getMacVer().equals(Mac.getMacVer())
        &&  MacInfo.getSerialNo().equals(Mac.getSerialNo())
        &&  MacInfo.getMacAddress().equals(Mac.getMacAddress()) ) {
            return;
        }
        Mac.setOrgCd( MacInfo.getOrgCd() );
        Mac.setBranchCd( MacInfo.getBranchCd() );
        Mac.setMacNo( MacInfo.getMacNo() );

        try {
            cmMacMap.updateByPrimaryKeySelective( Mac );
        }
        catch (Exception e) {
            logger.info( ">>> [DBUpdateMacInfo] (T_CM_MAC) UPDATE ERROR [{}]", e.getMessage() );
            throw e;
        }
        logger.info("기기버전[{}], 시리얼번호[{}] Mac_address[{}]변경 완료", Mac.getMacVer(), Mac.getSerialNo(), Mac.getMacAddress() );
    }

    /**
     *
     * 원거래 데이터가 존재하는지 체크
     * <pre>
     * int DBGetOwnTradeSeqYN( char * pOrgCd, char * pJijumCd, char * pMacNo, char * pOwnTradeDate, char * pOwnSeqNo )
     * </pre>
     *
     * @param tMisc
     * @return
     */
    @Override
    public int getOwnTradeSeqYN(TMisc tMisc) {
        try
        {
            miscMap.getOwnTradeSeqYN(tMisc);
        } catch (Exception e)
        {
            logger.info(">>> [DBGetOwnTradeSeqYN]-정산기 원거래 데이터 없음. {}", e.getMessage());
            return -1;
        }

        return 0;   //AS-IS에서 Exception발생 없이 정상수행되면 0을 리턴함.
    }

    /**
     *
     * BOX 데이터가 RETRY 되어 중복 수신되었을 경우 저장 하지 않고 정상으로 RETURN 처리
     * <pre>
     * DBGetBoxRecvYN( char * pOrgCd, char * pJijumCd, char * pMacNo, char * pOwnTradeDate, char * pOwnSeqNo, char * pBoxGubun1, char * pBoxGubun2, char * pKJGubun )
     * </pre>
     *
     * @param tMisc
     * @return
     */
    @Override
    public int getBoxRecvYN(TMisc tMisc) {
        TMisc resultTMisc = null;

        try
        {
            resultTMisc = miscMap.getBoxRecvYN(tMisc);
        } catch (Exception e)
        {
            logger.info(">>> [DBGetBoxRecvYN]-정산기 BOX 데이터 파악 실패 {}", e.getMessage());
            return -1;
        }

        if(resultTMisc != null && resultTMisc.getCnt() > 0) {
            logger.info(">>> [DBGetBoxRecvYN]-정산기 BOX 데이터 중복수신");
            return 1;
        }

        return 0;
    }

    /**
     *
     * RETRY로 인한 중복 수신 처리
     * <pre>
     * DBGetTicketDealRecvYN( char * pOrgCd, char * pJijumCd, char * pMacNo, char * pOwnTradeDate, char * pOwnSeqNo, char * pTicketCd, char * pKJGubun )
     * </pre>
     *
     * @param tMisc
     * @return
     */
    @Override
    public int getTicketDealRecvYN(TMisc tMisc) {
        TMisc resultTMisc = null;

        try
        {
            resultTMisc = miscMap.getTicketDealRecvYN(tMisc);
        } catch (Exception e)
        {
            logger.info(">>> [DBGetTicketDealRecvYN]-정산기 상품권기기입금 데이터 파악 실패 {}", e.getMessage());
            return -1;
        }

        if(resultTMisc != null && resultTMisc.getCnt() > 0) {
            logger.info(">>> [DBGetTicketDealRecvYN]-정산기 상품권기기입금 데이터 중복수신");
            return 1;
        }

        return 0;
    }

    /**
     * 설치관리전문 의 DB 처리
     *
     * @author KDJ originated by 홍민표
     * @since 2004/03/08
     * @param safeData   비지니스 공유정보
     * @param Worktype   작업유형
     * @param MacSetMng  기기설치 정보
     * @throws Exception
     */
    public void insertUpdateMacSet( MsgBrokerData safeData, int WorkType, TCtMacSetMng MacSetMng ) throws Exception {

        if( WorkType == MsgBrokerConst.DB_WORK_INSERT ) {
            MacSetMng.setUpdateDate( safeData.getDSysDate() );
            try {
                macSetMngMap.insert( MacSetMng );
            }
            catch( org.springframework.dao.DataIntegrityViolationException de ) {
                logger.info("...중복요청건...");
            }
            catch( Exception e ) {
                logger.info( ">>> [DBInsertUpdateMacSet] (T_CT_MAC_SET_MNG) INSERT ERROR [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        else if( WorkType == MsgBrokerConst.DB_WORK_UPDATE ) {
            try {
                macSetMngMap.updateByPrimaryKey( MacSetMng );
            }
            catch( Exception e ) {
                logger.info( ">>> [DBInsertUpdateMacSet] (T_CT_MAC_SET_MNG) UPDATE ERROR [{}]", e.getLocalizedMessage() );
                throw e;
            }

        }
        else if( WorkType == MsgBrokerConst.DB_WORK_CANCEL ) {
            TCtMacSetMng record = new TCtMacSetMng();
            record.setCancelDate   ( safeData.getSysDate()        ); // 취소 요청일
            record.setCancelType   ( "1"                          ); // 취소여부('1':Y, '0':N)
            record.setOrgCancelMemo( MacSetMng.getOrgCancelMemo() ); // 기관 취소건 메모
            record.setSetStatus    ( "9000 "                      );
            record.setUpdateDate   ( safeData.getDSysDate()       );
            /*
             * Key
             */
            record.setOrgCd   ( MacSetMng.getOrgCd()    );
            record.setBranchCd( MacSetMng.getBranchCd() );
            record.setSiteCd  ( MacSetMng.getSiteCd()   );
            record.setMacNo   ( MacSetMng.getMacNo()    );
            record.setSetType ( MacSetMng.getSetType()  );
            record.setSetDate ( MacSetMng.getSetDate()  );
            record.setSetTime ( MacSetMng.getSetTime()  );
            try {
                macSetMngMap.updateByPrimaryKey( record );
            }
            catch( Exception e ) {
                logger.info( ">>> [DBInsertUpdateMacSet] (T_CT_MAC_SET_MNG) CANCEL ERROR [{}]", e.getLocalizedMessage() );
                throw e;
            }

        }
    }

    /**
     *
     * 설치관리전문 의 DB 처리
     *
     * @author KDJ originated by 홍민표
     * @since 2004/03/08
     * @param safeData   비지니스 공유정보
     * @param OrgSiteChange  기기설치 정보
     * @throws Exception
     */
    public void insertOrgSigeChnage( MsgBrokerData safeData, TCtOrgSiteChange OrgSiteChange ) throws Exception {
        try {
            orgSiteChangeMap.insert( OrgSiteChange );
        }
        catch( org.springframework.dao.DataIntegrityViolationException de ) {
            logger.info("[T_CT_ORG_SITE_CHANGE]...중복요청건...");
            throw de;
        }
        catch( Exception e ) {
            logger.info( ">>> [DBInsertOrgSigeChnage] (T_CT_ORG_SITE_CHANGE) INSERT ERROR [{}]", e.getLocalizedMessage() );
            throw e;
        }
    }

    /**
     * T_CT_ERROR_MNG 의 5분활 Table Update
     * <pre>
     * AS-IS T_CT_ERROR_MNG 테이블 정보를 TO-BE의 스키마로 Update
     *
     * T_CT_ERROR_BASIC
     * T_CT_ERROR_RCPT
     * T_CT_ERROR_NOTI
     * T_CT_ERROR_CALL
     * T_CT_ERROR_TXN
     *
     * </pre>
     *
     * @param updateTCtErrorMng Update대상값
     * @param tCtErrorMngSpec   Update조건문
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @Override
    public int updateErrorMng(TCtErrorMng updateTCtErrorMng, TCtErrorMngSpec tCtErrorMngSpec) throws IllegalAccessException, InvocationTargetException {

        int returnValue = 0;

        //TCtErrorMngSpec을 이용하여 T_CT_ERROR_MNG VIEW를 Select
        List<TCtErrorMng> tCtErrorMngList = ctErrorMngMap.selectBySpec(tCtErrorMngSpec);


        if (tCtErrorMngList != null)
        {

            logger.info("Select T_CT_ERROR_MNG View Count: {}", tCtErrorMngList.size());
            if (tCtErrorMngList.size() > 0)
            {

                //변경해야될 값을 각 테이블별 MODEL에 설정
                TCtErrorBasic tCtErrorBasic = new TCtErrorBasic();
                BeanUtils.copyProperties(tCtErrorBasic, updateTCtErrorMng);

                TCtErrorRcpt tCterrRcpt = new TCtErrorRcpt();
                BeanUtils.copyProperties(tCterrRcpt, updateTCtErrorMng);

                TCtErrorNoti tCterrNoti = new TCtErrorNoti();
                BeanUtils.copyProperties(tCterrNoti, updateTCtErrorMng);

                TCtErrorCall tCterrCall = new TCtErrorCall();
                BeanUtils.copyProperties(tCterrCall, updateTCtErrorMng);

                TCtErrorTxn tCterrTxn = new TCtErrorTxn();
                BeanUtils.copyProperties(tCterrTxn, updateTCtErrorMng);

                //멀터건이 조회될경우 반복하며 Update
                for (TCtErrorMng tCtErrorMng : tCtErrorMngList)
                {

                    //조회된 TCtErrorMng에서 PK를 추출
                    //errorNo              ;   //장애번호
                    //createDate           ;   //장애 일자
                    //createTime           ;   //발생시각

                    int updatedTableCnt = 0; //Update가 발생한 Table건수 (max: 5)

                    if (isNotNullFieldExists(tCtErrorBasic))
                    {
                        TCtErrorBasicSpec tCtErrorBasicSpec = new TCtErrorBasicSpec();
                        tCtErrorBasicSpec.createCriteria().andErrorNoEqualTo(tCtErrorMng.getErrorNo()).andCreateDateEqualTo(tCtErrorMng.getCreateDate())
                                        .andCreateTimeEqualTo(tCtErrorMng.getCreateTime());

                        int updatedCnt = errBasicMap.updateBySpecSelective(tCtErrorBasic, tCtErrorBasicSpec);

                        logger.info(">>> T_CT_ERROR_BASIC Updated", updatedCnt);
                        updatedTableCnt++;
                    }

                    if (isNotNullFieldExists(tCterrRcpt))
                    {
                        TCtErrorRcptSpec tCtErrorRcptSpec = new TCtErrorRcptSpec();
                        tCtErrorRcptSpec.createCriteria().andErrorNoEqualTo(tCtErrorMng.getErrorNo()).andCreateDateEqualTo(tCtErrorMng.getCreateDate())
                                        .andCreateTimeEqualTo(tCtErrorMng.getCreateTime());

                        int updatedCnt = errRcptMap.updateBySpecSelective(tCterrRcpt, tCtErrorRcptSpec);

                        logger.info(">>> T_CT_ERROR_RCPT Updated: {}", updatedCnt);
                        updatedTableCnt++;
                    }

                    if (isNotNullFieldExists(tCterrNoti))
                    {
                        TCtErrorNotiSpec tCtErrorNotiSpec = new TCtErrorNotiSpec();
                        tCtErrorNotiSpec.createCriteria().andErrorNoEqualTo(tCtErrorMng.getErrorNo()).andCreateDateEqualTo(tCtErrorMng.getCreateDate())
                                        .andCreateTimeEqualTo(tCtErrorMng.getCreateTime());

                        int updatedCnt = errNotiMap.updateBySpecSelective(tCterrNoti, tCtErrorNotiSpec);

                        logger.info(">>> T_CT_ERROR_NOTI Updated: {}", updatedCnt);
                        updatedTableCnt++;
                    }

                    if (isNotNullFieldExists(tCterrCall))
                    {
                        TCtErrorCallSpec tCtErrorCallSpec = new TCtErrorCallSpec();
                        tCtErrorCallSpec.createCriteria().andErrorNoEqualTo(tCtErrorMng.getErrorNo()).andCreateDateEqualTo(tCtErrorMng.getCreateDate())
                                        .andCreateTimeEqualTo(tCtErrorMng.getCreateTime());

                        int updatedCnt = errCallMap.updateBySpecSelective(tCterrCall, tCtErrorCallSpec);

                        logger.info(">>> T_CT_ERROR_CALL Updated: {}", updatedCnt);
                        updatedTableCnt++;
                    }

                    if (isNotNullFieldExists(tCterrTxn))
                    {
                        TCtErrorTxnSpec tCtErrorTxnSpec = new TCtErrorTxnSpec();
                        tCtErrorTxnSpec.createCriteria().andErrorNoEqualTo(tCtErrorMng.getErrorNo()).andCreateDateEqualTo(tCtErrorMng.getCreateDate())
                                        .andCreateTimeEqualTo(tCtErrorMng.getCreateTime());

                        int updatedCnt = errTxnMap.updateBySpecSelective(tCterrTxn, tCtErrorTxnSpec);

                        logger.info(">>> T_CT_ERROR_TXN Updated: {}", updatedCnt);
                        updatedTableCnt++;
                    }

                    logger.info(">>>>>> TOTAL Updated Table Count: {}", updatedTableCnt);
                    returnValue++;
                }
            }

        } else {
            logger.info("tCtErrorMngList is null");
        }

        logger.info(">>>>>> TOTAL Updated Row Count: {}", returnValue);

        return returnValue;
    }

    /**
     * 인스턴스의 Not Null 필드 체크
     * <pre>
     * 해당 인스턴스의 필드를 체크하여 NotNull Field가 하나라도 존재하면 True 리턴
     * 단 updateDate, updateUid등 Row에 대한 기타정보 값들은 Null이 아니라도, Null로 판단함.
     * </pre>
     *
     * @param obj
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    private boolean isNotNullFieldExists(Object obj) throws IllegalArgumentException, IllegalAccessException {

        Field[] classFields = (obj.getClass()).getDeclaredFields();

        for(Field field : classFields) {
            field.setAccessible(true);

            if(field.get(obj) != null) {
                String fieldName = field.getName();
                if(fieldName.equals("updateDate") || fieldName.equals("updateUid")) {
                    //수정정보는 값이 존재하여도, NULL이라고 판단
                } else {
                    logger.debug("fieldName: {} is not null", fieldName);
                    return true;
                }
            }
        }

        return false;
    }

}
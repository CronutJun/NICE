package com.nicetcm.nibsplus.broker.msg.services;

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
import com.nicetcm.nibsplus.broker.msg.mapper.TCmCommonMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmCommon;
import com.nicetcm.nibsplus.broker.msg.model.TCmCommonSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCall;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNoti;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorRcpt;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

/**
 *
 * 후처리내역통보
 * <pre>
 * MngEM_SaveAfterCall( char * pRecvData, int nRecvLen )
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01000310")
public class In01000310Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private StoredProcMapper storedProcMapper;

    @Autowired private TCmCommonMapper tCmCommonMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        comPack.checkBranchMacLength(parsed);

        TMacInfo macInfo = new TMacInfo();
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );
        macInfo.setOrgSiteCd( parsed.getString("org_site_cd") );

        if(parsed.getString("mac_no").equals("0000") || parsed.getString("mac_no").equals("    ") || parsed.getString("mac_no").equals("")
           || (MsgBrokerConst.KEB_CODE.equals(parsed.getString("CM.org_cd")) && parsed.getString("mac_no").equals("999"))
        ) {

            try
            {
                comPack.getMacNoIntoSite( macInfo );
                parsed.setString("mac_no", macInfo.getMacNo());
                parsed.setString("site_cd", macInfo.getSiteCd());

            } catch (Exception e)
            {
                logger.warn(String.format("기번정보 없음(코너 대표기번 검색 실패),org_cd[%s],jijum_cd[%s], org_site_cd[%s]", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("org_site_cd")) );
                throw new Exception(String.format("기번정보 없음(코너 대표기번 검색 실패),org_cd[%s],jijum_cd[%s], org_site_cd[%s]", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("org_site_cd")));
            }

        }

        try {
            /**
             * 기기정보 취득
             */
            comPack.getMacInfo( macInfo );
        }
        catch (Exception e) {

            if( macInfo.getOrgCd().equals(MsgBrokerConst.SHATMS_CODE)) {
                storedProcMapper.SendSMSMacInfo(macInfo);
            }

            throw new MsgBrokerException( String.format("[01003100] 기기정보 검색 실패 기관[%s] 지점[%s] 기번[%s]",
                                            macInfo.getOrgCd(), macInfo.getMacNo()), -7 );

        }

        TCtErrorBasic errBasic = new TCtErrorBasic();
        TCtErrorRcpt errRcpt = new TCtErrorRcpt();
        TCtErrorNoti errNoti = new TCtErrorNoti();
        TCtErrorCall errCall = new TCtErrorCall();
        TCtErrorTxn  errTxn  = new TCtErrorTxn();

        errBasic.setOrgCd     (macInfo.getOrgCd());   // org_cd
        errBasic.setCreateDate(parsed.getInt("call_date"));      // create_date
        errBasic.setCreateTime(parsed.getString("call_time"));   // create_time
        errBasic.setBranchCd   (macInfo.getBranchCd());  // jijum_cd
        errBasic.setMacNo     (macInfo.getMacNo());   // mac_no
        //errBasic.setMacNm     (macInfo.getMacNm());   // mac_nm   -- macInfo
        errBasic.setSiteCd    (macInfo.getSiteCd());   // site_cd
        errBasic.setDeptCd    (macInfo.getDeptCd());   // dept_cd
        errBasic.setOfficeCd  (macInfo.getOfficeCd());   // office_cd
        errBasic.setTeamCd    (macInfo.getTeamCd());   // jiso_cd
        //errBasic.setAsAcptYn  (parsed.getString(""));   // as_acpt_yn -- macInfo
        //errBasic.setOpenDate  (parsed.getString(""));   // open_date -- macInfo
        //errBasic.setCloseDate (parsed.getString(""));   // close_date -- macInfo
        //errBasic.setMacGrade  (parsed.getString(""));   // mac_grade -- macInfo
        errBasic.setOrgSiteCd (macInfo.getOrgSiteCd());   // org_site_cd
        errBasic.setTransDate (parsed.getString("create_date"));   // trans_date
        errBasic.setOrgMsg    (parsed.getString("memo"));   // org_msg

        errBasic.setOrgSendYn("0"); /* 은행통보여부 기관,민원출동시'0'*/
        errBasic.setErrorCd("AFTMNG");  /* 후처리 장애코드 'AFTMNG' */

        /*
         * 후처리 일련번호 처리
         * 기존 6자리가 짧으므로, 20자리필드 존재여부와 기관코드를 체크하여 어떤 필드를 사용할지 선택한다.
         */
        if(parsed.getString("trans1_seq_long").length() != 0 && MsgBrokerConst.KIUP_CODE.equals(parsed.getString("CM.org_cd"))) { /*    기업은행        */
            errBasic.setOrgMsgNo(parsed.getString("trans1_seq_long"));  /* 처리일련번호   */
        } else {
            errBasic.setOrgMsgNo(parsed.getString("trans1_seq"));   /* 처리일련번호   */
        }

        TCmCommonSpec tCmCommonSpec = new TCmCommonSpec();
        tCmCommonSpec.createCriteria()
        .andLargeCdEqualTo("0122")
        .andCdNm3EqualTo(parsed.getString("CM.org_cd"))
        .andCdNm2EqualTo(parsed.getString("deal_cl"));

        String szDealGb = "";
        List<TCmCommon> tCmCommonList = tCmCommonMapper.selectBySpec(tCmCommonSpec);

        if(tCmCommonList != null && tCmCommonList.size() > 0) {
            szDealGb = tCmCommonList.get(0).getCdNm1();
        }

        String szSMSyn = null;
        if(parsed.getString("sms_yn").startsWith("1")) {
            szSMSyn = "SMS 대상";
        } else if(parsed.getString("sms_yn").startsWith("0")) {
            szSMSyn = "SMS 미대상";
        }

        String szCustMsg = String.format("발생일시[%s %s] 통보자[%s-%s] 고객명[%s-%s] [%s] 거래[%s] 계좌[%s] 거래금액[%s] 거래일련번호[%s] ",
             parsed.getString("create_date")
            ,parsed.getString("create_time")
            ,parsed.getString("call_nm")
            ,parsed.getString("call_telno")
            ,parsed.getString("cust_nm")
            ,parsed.getString("cust_telno")
            ,szSMSyn
            ,szDealGb
            ,parsed.getString("account_no")
            ,parsed.getString("deal_amt")
            ,parsed.getString("atm_deal_no")
        );

        /* 강성고객일 경우, 해당 내용을 메세지에 추가한다. */
        if(parsed.getString("hard_cust_yn").startsWith("1")) {
            szCustMsg = szCustMsg + " [강성고객]";
        }

        /* 거래은행이 있을경우, 해당 내용을 메세지에 추가한다. */
        if(parsed.getString("trade_org_nm").length() != 0) {
            szCustMsg = szCustMsg + " 거래기관[" + parsed.getString("trade_org_nm") + "]";
        }

        errBasic.setOrgCustMsg(szCustMsg);

        boolean isDbDupData = false;

        try
        {
            comPack.insertErrBasic( safeData, errBasic, errRcpt, errNoti, errCall, errTxn, macInfo, "" );

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {
            isDbDupData = true;

        }

        if (isDbDupData)
        {
            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            updateTCtErrorMng.setOrgMsg(parsed.getString("memo"));
            updateTCtErrorMng.setOrgCustMsg(szCustMsg);
            updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
            updateTCtErrorMng.setUpdateUid("ERRmng");
            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria().andCreateDateEqualTo(parsed.getInt("call_date")).andTransDateEqualTo(parsed.getString("create_date"))
                            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq")).andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                            .andBranchCdEqualTo(parsed.getString("brch_cd")).andMacNoEqualTo(parsed.getString("mac_no"));
            try
            {
                comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);
            } catch (Exception e)
            {
                logger.warn(">>> [MngEM_SaveAfterCall] (T_CT_ERROR_MNG) UPDATE ERROR [{}]", e.getMessage());
                throw e;
            }

            logger.warn("!!! 후처리 요청 기관 특이사항  처리완료(T_CT_ERROR_MNG UPDATE)!!!");

            logger.warn( ">>> [MngEM_SaveAfterCall] 1차통지 거래일 ({}), 전문추적번호 ({}) , 장애코드 ({}) 중복출동 수신",
                            parsed.getString("create_date"), parsed.getString("trans1_seq"), errBasic.getErrorCd());
            /* return RET_INSERT_DUP_ERROR; */
            /* 중복 출동 요청 수신이라도 은행에는 정상 응답 */
        }
    }//end method
}
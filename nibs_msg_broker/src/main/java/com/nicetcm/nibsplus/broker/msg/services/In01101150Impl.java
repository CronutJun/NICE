package com.nicetcm.nibsplus.broker.msg.services;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMadeComMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeCom;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeComSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngSpec;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

/**
 *
 * 도착예정 - 기관으로부터 응답을 처리한다.
 * <pre>
 * MngEM_AP_SaveArrivalSchdule( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01101150")
public class In01101150Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtErrorMngMadeComMapper tCtErrorMngMadeComMapper;

    private static List<String> ORG_CD_GROUP = Arrays.asList(new String[]{
        MsgBrokerConst.KIUP_CODE
        ,MsgBrokerConst.HANAATMS_CODE
        ,MsgBrokerConst.SHATMS_CODE
        ,MsgBrokerConst.NONGH_CODE
        ,MsgBrokerConst.BU_CODE
        ,MsgBrokerConst.WRATMS_CODE
        ,MsgBrokerConst.KNATMS_CODE
        ,MsgBrokerConst.KFCC_CODE
        ,MsgBrokerConst.KEB_CODE
        ,MsgBrokerConst.BUATMS_CODE
        ,MsgBrokerConst.WC_CODE
        ,MsgBrokerConst.DGB_CODE
    });

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        String hSEND_YN;

        if(parsed.getString("mac_no").equals("0000") || parsed.getString("mac_no").equals("    ") || parsed.getString("mac_no").equals("")) {
            TMacInfo macInfo = new TMacInfo();
            macInfo.setOrgCd( parsed.getString("CM.org_cd") );
            macInfo.setBranchCd( parsed.getString("brch_cd") );
            macInfo.setMacNo( parsed.getString("mac_no") );
            macInfo.setOrgSiteCd( parsed.getString("org_site_cd") );

            try
            {
                comPack.getMacNoIntoSite( macInfo );
                parsed.setString("mac_no", macInfo.getMacNo());

            } catch (Exception e)
            {
                logger.warn(String.format("기번정보 없음(코너 대표기번 검색 실패),org_cd[%s],jijum_cd[%s], org_site_cd[%s]", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("org_site_cd")) );
                throw new Exception(String.format("기번정보 없음(코너 대표기번 검색 실패),org_cd[%s],jijum_cd[%s], org_site_cd[%s]", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("org_site_cd")));
            }

        }

        try{ comPack.checkBranchMacLength(parsed); } catch( Exception e ) {}

        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd"))) {
            /***********************************************************************************************
             [신한은행]의 경우 민원 출동 건은 기번 없이 보내므로 일련번호로 비교하도록 수정
                2012.2.28 by BHJ
            ************************************************************************************************/

            /**********************************************************************************************
            신한은행 토탈 전환에 따라 2차 출동요청도 처리 함. 2013.12.17 by B.H.J
            2차 출동요청 중 키지원(출동요청사유코드 '3'-> SUBSTR(SEC, 2,1) = '3')의 경우를 제외 하고
            T_CT_ERROR_MNG_MADE_COM 테이블에 저장하여 기기사로 전문 전송 처리 하도록 하고
            장애테이블에는 저장 하지 않음.
            도착 예정, 도착 , 조치 결과 전문에 사유 필드가 없음에 따라 2차 출동의 경우 2차 출동 테이블
            비교 후 없는 경우 장애 테이블을 UPDATE 함.
           **********************************************************************************************/
            if(parsed.getString("call_class").substring(1).equals("2")) {
                TCtErrorMngMadeCom tCtErrorMngMadeCom = new TCtErrorMngMadeCom();
                tCtErrorMngMadeCom.setOrgSendYn("2");
                tCtErrorMngMadeCom.setUpdateDate(safeData.getDSysDate());
                tCtErrorMngMadeCom.setUpdateUid("apmng");

                TCtErrorMngMadeComSpec tCtErrorMngMadeComSpec = new TCtErrorMngMadeComSpec();
                tCtErrorMngMadeComSpec.createCriteria()
                .andTransDateEqualTo(parsed.getString("trans1_date"))
                .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andOrgCallCntEqualTo(parsed.getShort("org_call_cnt"));

                int resultCnt = 0;

                try
                {
                    resultCnt = tCtErrorMngMadeComMapper.updateBySpecSelective(tCtErrorMngMadeCom, tCtErrorMngMadeComSpec);
                } catch (Exception e)
                {
                    logger.warn( ">>> [MngEM_SaveErrCall] (T_CT_ERROR_MNG_MADE_COM) UPDATE ERROR [%.200s]", e.getMessage());
                    throw e;
                }

                if(resultCnt == 0) {
                    logger.warn( "...해당 장애 없음 trans_date[{}] org_msg_no[{}]", parsed.getString("trans1_date"), parsed.getString("trans1_seq"));
                    /* 아래부분에서 t_ct_error_mng update 치도록 함. */
                }

                logger.warn("!!! 신한은행 2차출동 도착예정보고  처리완료(T_CT_ERROR_MNG_MADE_COM UPDATE)!!!");

                return;

            }

            /***********************************************************************************************
             응답코드 B99 로 오면 재송신 하지 않고 차후 모니터링 하도록 처리
            ************************************************************************************************/
            if(parsed.getString("CM.ret_cd_src").equals("B")) {
                if(parsed.getString("CM.ret_cd").equals("00")) {
                    hSEND_YN = "1";
                } else {
                    hSEND_YN = "a";
                }
            } else {
                logger.warn("[SaveArrivalSchdule] 응답코드[{}]수신", parsed.getString("CM.ret_cd_src") + parsed.getString("CM.ret_cd"));
                return;
            }

            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            updateTCtErrorMng.setOrgSendYn(hSEND_YN);
            updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
            updateTCtErrorMng.setUpdateUid("ERRmng");

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andCreateDateGreaterThan(MsgBrokerLib.SysDate(-10))
            .andTransDateEqualTo(parsed.getString("trans1_date"))
            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"));

            comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);

        } else {
            /***********************************************************************************************
             그 외, 나머지 기관들
            ************************************************************************************************/

            /***********************************************************************************************
             국민은행 Service
            ************************************************************************************************/

            boolean hSQLsub = false;

            if(MsgBrokerConst.KBST_CODE.equals(parsed.getString("CM.org_cd"))) {
                hSEND_YN = "1";
                hSQLsub = true;

            } else if(ORG_CD_GROUP.contains(parsed.getString("CM.org_cd"))) {
                /***********************************************************************************************
                 응답코드 B99 로 오면 재송신 하지 않고 차후 모니터링 하도록 처리
                ************************************************************************************************/

                if(parsed.getString("CM.ret_cd_src").equals("B")) {
                    if(parsed.getString("CM.ret_cd").equals("00")) {
                        hSEND_YN = "1";
                    } else {
                        hSEND_YN = "a";
                    }
                } else {
                    logger.warn("[SaveArrivalSchdule] 응답코드[{}]수신", parsed.getString("CM.ret_cd_src") + parsed.getString("CM.ret_cd"));
                    return;
                }


            } else if(MsgBrokerConst.KNB_CODE.equals(parsed.getString("CM.org_cd")) || MsgBrokerConst.KNATMS_CODE.equals(parsed.getString("CM.org_cd"))) {
                /***********************************************************************************************
                 [경남은행]은 응답코드가 4자리이므로 따로 처리
                ************************************************************************************************/

                /***********************************************************************************************
                 응답코드 B99 로 오면 재송신 하지 않고 차후 모니터링 하도록 처리
                ************************************************************************************************/
                if(parsed.getString("CM.ret_cd_src").equals("B")) {
                    if(parsed.getString("CM.ret_cd").equals("0000")) {
                        hSEND_YN = "1";
                    } else {
                        hSEND_YN = "a";
                    }
                } else {
                    logger.warn("[SaveArrivalSchdule] 응답코드[{}]수신", parsed.getString("CM.ret_cd_src") + parsed.getString("CM.ret_cd"));
                    return;
                }

            } else {
                logger.warn("[SaveArrivalSchdule] 도착예정통보가 지원되지 않는 기관입니다.[%s]", parsed.getString("CM.org_cd"));
                throw new Exception("[SaveArrivalSchdule] 도착예정통보가 지원되지 않는 기관입니다.[" + parsed.getString("CM.org_cd") + "]");
            }

            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            updateTCtErrorMng.setOrgSendYn(hSEND_YN);
            updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
            updateTCtErrorMng.setUpdateUid("ERRmng");

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();

            if(hSQLsub) {
                tCtErrorMngSpec.createCriteria()
                .andCreateDateGreaterThan(MsgBrokerLib.SysDate(-10))
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andTransDateEqualTo(parsed.getString("trans1_date"))
                .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
                .andFormatTypeEqualTo("21");

            } else {
                tCtErrorMngSpec.createCriteria()
                .andCreateDateGreaterThan(MsgBrokerLib.SysDate(-10))
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andTransDateEqualTo(parsed.getString("trans1_date"))
                .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"));
            }

            comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);

        }

        logger.warn( "[SaveArrivalSchdule] Update OK" );

    }//end method
}
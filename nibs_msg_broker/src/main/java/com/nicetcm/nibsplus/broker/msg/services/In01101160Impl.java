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
 * 도착통보 - 기관으로부터 받은 도착통보응답 처리
 * <pre>
 * MngEM_AP_SaveArrival( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01101160")
public class In01101160Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtErrorMngMadeComMapper tCtErrorMngMadeComMapper;


    private static List<String> ORG_CD_GROUP = Arrays.asList(new String[]{
        MsgBrokerConst.HANAATMS_CODE    /* 하나은행 ATMS */
        ,MsgBrokerConst.NONGH_CODE      /* 농협 ATMS */
        ,MsgBrokerConst.SHATMS_CODE     /* 신한 ATMS */
        ,MsgBrokerConst.JEJU_CODE       /* 제주 */
        ,MsgBrokerConst.WRATMS_CODE     /* 우리 ATMS */
        ,MsgBrokerConst.KNATMS_CODE     /* 우리 ATMS */
        ,MsgBrokerConst.KFCC_CODE       /* 새마을 ATMS */
        ,MsgBrokerConst.DGB_CODE        /* 대구 ATMS */
    });


    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        String hSEND_YN = null;

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

        /*********************************************************************************************
            브랜드제휴 도착통보 - 기관별 점기번길이를 체크하지 않는다.
        *********************************************************************************************/
        if(parsed.getString("CM.service_gb").equals("1")) {

        } else {
            comPack.checkBranchMacLength(parsed);
        }




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

            Short horgCallCnt = Short.parseShort(comPack.parseErrorMsg(parsed.getString("error_msg"), 15));

            /*출동구분필드에 출동구분(1)+출동요청차수구분(1)이 같이 들어옴 */
            if(parsed.getString("call_class").substring(1).equals("2")) {

                TCtErrorMngMadeCom tCtErrorMngMadeCom = new TCtErrorMngMadeCom();
                tCtErrorMngMadeCom.setOrgSendYn("3");
                tCtErrorMngMadeCom.setUpdateDate(safeData.getDSysDate());
                tCtErrorMngMadeCom.setUpdateUid("apmng");

                TCtErrorMngMadeComSpec tCtErrorMngMadeComSpec = new TCtErrorMngMadeComSpec();
                tCtErrorMngMadeComSpec.createCriteria()
                .andTransDateEqualTo(parsed.getString("trans1_date"))
                .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andOrgCallCntEqualTo(horgCallCnt);

                int resultCnt = 0;

                try
                {
                    resultCnt = tCtErrorMngMadeComMapper.updateBySpecSelective(tCtErrorMngMadeCom, tCtErrorMngMadeComSpec);
                } catch (Exception e)
                {
                    logger.warn( ">>> [MngEM_AP_SAVEaRRIVAL] (T_CT_ERROR_MNG_MADE_COM) UPDATE ERROR [%.200s]", e.getMessage());
                    throw e;
                }

                if(resultCnt == 0) {
                    logger.warn( "...해당 장애 없음 trans_date[{}] org_msg_no[{}]", parsed.getString("trans1_date"), parsed.getString("trans1_seq"));
                    /* 아래부분에서 t_ct_error_mng update 치도록 함. */
                }

                logger.warn("!!! 신한은행 2차출동 도착보고  처리완료(T_CT_ERROR_MNG_MADE_COM UPDATE)!!!");

                return;

            }

            /***********************************************************************************************
             응답코드 B99 로 오면 재송신 하지 않고 차후 모니터링 하도록 처리
            ************************************************************************************************/

            if(parsed.getString("CM.ret_cd_src").equals("B")) {
                if(parsed.getString("CM.ret_cd").equals("00")) {
                    hSEND_YN = "2";
                } else {
                    hSEND_YN = "b";
                }
            } else {
                logger.warn("[MngEM_AP_SaveArrival] 응답코드[{}]수신", parsed.getString("CM.ret_cd_src") + parsed.getString("CM.ret_cd"));
                return;
            }

            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            updateTCtErrorMng.setOrgSendYn(hSEND_YN);

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andCreateDateGreaterThan(Integer.parseInt(MsgBrokerLib.SysDate(-10)))
            .andTransDateEqualTo(parsed.getString("trans1_date"))
            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"));

            comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);

        } else if(MsgBrokerConst.KIUP_CODE.equals(parsed.getString("CM.org_cd"))) {
            /*********************************************************************************************
                [기업은행] 도착통보 - 일괄업무, 브랜드제휴업무 구분
                - 일괄업무, 브랜드제휴업무를 구분하하여 도착통보 응답전문을 처리한다.     2012.12.24. 최락경
            *********************************************************************************************/

            /***********************************************************************************************
             응답코드 B99 로 오면 재송신 하지 않고 차후 모니터링 하도록 처리
            ************************************************************************************************/

            if(parsed.getString("CM.ret_cd_src").equals("B")) {
                if(parsed.getString("CM.ret_cd").equals("00")) {
                    hSEND_YN = "2";
                } else {
                    hSEND_YN = "b";
                }
            } else {
                logger.warn("[MngEM_AP_SaveArrival] 응답코드[{}]수신", parsed.getString("CM.ret_cd_src") + parsed.getString("CM.ret_cd"));
                return;
            }

            /*********************************************************************************************
                브랜드제휴
            *********************************************************************************************/
            if(parsed.getString("CM.service_gb").equals("1")) {
                TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
                updateTCtErrorMng.setOrgSendYn(hSEND_YN);

                TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
                tCtErrorMngSpec.createCriteria()
                .andOrgCdEqualTo("096")
                .andBranchCdEqualTo("9600")
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andCreateDateEqualTo(parsed.getInt("trans1_date"))
                .andCreateTimeEqualTo(parsed.getString("trans1_seq"))
                .andErrorCdEqualTo(parsed.getString("error_msg").trim())
                .andOrgSendYnEqualTo("1");

                try
                {
                    comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);
                } catch (Exception e)
                {
                    logger.warn( String.format("[MngEM_AP_SaveArrival] 브랜드제휴 Org_cd[%3s] mac_no[%s]", parsed.getString("CM.org_cd"), parsed.getString("mac_no")) );
                    logger.warn( "[MngEM_AP_SaveArrival] Update Err [%.200s]\n", e.getMessage() );
                    throw e;
                }
            } else {
                /*********************************************************************************************
                    일괄업무
                *********************************************************************************************/
                TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
                updateTCtErrorMng.setOrgSendYn(hSEND_YN);

                TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
                tCtErrorMngSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andCreateDateGreaterThan(Integer.parseInt(MsgBrokerLib.SysDate(-10)))
                .andTransDateEqualTo(parsed.getString("trans1_date"))
                .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"));


            }

        } else {
            if(ORG_CD_GROUP.contains(parsed.getString("CM.org_cd"))) {
                /* 하나은행 ATMS */
                /* 농협 ATMS */
                /* 신한 ATMS */
                /* 제주 */
                /* 우리 ATMS */
                /* 우리 ATMS */
                /* 새마을 ATMS */
                /* 대구 ATMS */

                /*********************************************************************************************
                 응답코드 B99 로 오면 재송신 하지 않고 차후 모니터링 하도록 처리
                *********************************************************************************************/
                if(parsed.getString("CM.ret_cd_src").equals("B")) {
                    if(parsed.getString("CM.ret_cd").equals("00")) {
                        hSEND_YN = "2";
                    } else {
                        hSEND_YN = "b";
                    }
                } else {
                    logger.warn("[MngEM_AP_SaveArrival] 응답코드[{}]수신", parsed.getString("CM.ret_cd_src") + parsed.getString("CM.ret_cd"));
                    return;
                }

            } else if(MsgBrokerConst.KEB_CODE.equals(parsed.getString("CM.org_cd"))) {

                if(parsed.getString("CM.ret_cd_src").equals("B")) {
                    /*********************************************************************************************
                     ===> 임시 정상 코드가 정의 되지 않았다.
                    *********************************************************************************************/

                    if(parsed.getString("CM.ret_cd").equals("")) {
                        hSEND_YN = "2";
                    }

                } else {
                    /*********************************************************************************************
                     외환은 도착예정이 없으므로
                    *********************************************************************************************/
                    /*hSEND_YN[0] = '0';*/
                    return;

                }

            } else if(MsgBrokerConst.KNB_CODE.equals(parsed.getString("CM.org_cd")) || MsgBrokerConst.KNATMS_CODE.equals(parsed.getString("CM.org_cd"))) {
                /* 경남은행 */
                /*********************************************************************************************
                 응답코드 B99 로 오면 재송신 하지 않고 차후 모니터링 하도록 처리
                *********************************************************************************************/
                if(parsed.getString("CM.ret_cd_src").equals("B")) {
                    /*********************************************************************************************
                     ===> 임시 정상 코드가 정의 되지 않았다.
                    *********************************************************************************************/

                    if(parsed.getString("CM.ret_cd").equals("0000")) {
                        hSEND_YN = "2";
                    } else {
                        hSEND_YN = "b";
                    }

                } else {

                    logger.warn("[MngEM_AP_SaveArrival] 응답코드[%s%s]수신\n", parsed.getString("CM.ret_cd_src"), parsed.getString("CM.ret_cd"));
                    return;

                }

            } else {
                /*
                logger("[MngEM_AP_SaveArrival] 도착통보가 지원되지 않는 기관입니다.[%s]\n", suHead.org_cd);
                return -1;
                */
                if(parsed.getString("CM.ret_cd_src").equals("B")) {
                    /*********************************************************************************************
                     ===> 임시 정상 코드가 정의 되지 않았다.
                    *********************************************************************************************/

                    if(parsed.getString("CM.ret_cd").equals("") || parsed.getInt("CM.ret_cd") == 0) {
                        hSEND_YN = "2";
                    }

                } else {
                    return;
                }

            }

            String branchCd = comPack.fGetNiceJijumCd(parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), "", parsed.getString("mac_no"));

            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            updateTCtErrorMng.setOrgSendYn(hSEND_YN);

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(branchCd)
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andCreateDateGreaterThan(Integer.parseInt(MsgBrokerLib.SysDate(-10)))
            .andTransDateEqualTo(parsed.getString("trans1_date"))
            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"));

            try
            {
                comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec);
            } catch (Exception e)
            {
                logger.warn("[MngEM_AP_SaveArrival] Update Err [{}]", e.getMessage());
                throw e;
            }

        }

        logger.warn( "[MngEM_AP_SaveArrival] Update OK" );

    }//end method
}
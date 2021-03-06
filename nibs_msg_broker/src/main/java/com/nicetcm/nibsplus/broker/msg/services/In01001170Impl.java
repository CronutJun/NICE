package com.nicetcm.nibsplus.broker.msg.services;

import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.nstr;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMadeComMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCtErrorMngMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeCom;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeComSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngSpec;

/**
 *
 * 조치결과통보
 * <pre>
 * MngEM_SaveMngResult( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in01001170")
public class In01001170Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCtErrorMngMapper tCtErrorMngMapper;

    @Autowired private TCtErrorMngMadeComMapper tCtErrorMngMadeComMapper;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {


        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd"))) {
            /* 신한은행 부분관리의 경우 무조건 완료로 처리 */
            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andCreateDateGreaterThan(MsgBrokerLib.SysDate(-10))
            .andTransDateEqualTo(parsed.getString("trans1_date"))
            .andOrgMsgNoEqualTo(parsed.getString("trans1_seq"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"));

            List<TCtErrorMng> tCtErrorMngList = tCtErrorMngMapper.selectBySpec(tCtErrorMngSpec);

            if(tCtErrorMngList != null && tCtErrorMngList.size() > 0) {

                for(TCtErrorMng tCtErrorMng : tCtErrorMngList) {
                    TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
                    updateTCtErrorMng.setFinishDate (parsed.getString("finish_date"));
                    updateTCtErrorMng.setFinishTime (parsed.getString("finish_time"));
                    updateTCtErrorMng.setFinishType (tCtErrorMng.getRecvPlace());
                    updateTCtErrorMng.setErrorStatus("7000");
                    updateTCtErrorMng.setFinishNm   ("부분관리");
                    updateTCtErrorMng.setRecvTeleNo ("");
                    updateTCtErrorMng.setWorkStatus ("6070");
                    updateTCtErrorMng.setMsg        (null);
                    updateTCtErrorMng.setOrgSendYn  ("S");
                    updateTCtErrorMng.setUpdateDate (safeData.getDSysDate());
                    updateTCtErrorMng.setUpdateUid  ("ERRmng");

                    try
                    {
                        comPack.updateErrorMng(safeData, updateTCtErrorMng, tCtErrorMng);
                    } catch (Exception e)
                    {
                        logger.warn( "[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                        throw e;
                    }
                }

            }

        } else if(MsgBrokerConst.ATM_HY_CODE.equals(parsed.getString("CM.org_cd")) || MsgBrokerConst.ATM_CH_CODE.equals(parsed.getString("CM.org_cd")) || MsgBrokerConst.ATM_LG_CODE.equals(parsed.getString("CM.org_cd"))) {
            /* 현재 기기사와 전문 연결하여 요청하는 기관은 신한은행만 이므로
               기관코드를 임시로 '088'로 넣어 주도록 한다.
               - 전기관 확대시 기기사와의 전문 사양도 수정 필요
            */
            parsed.setString("CM.org_cd", MsgBrokerConst.SHATMS_CODE);

            TCtErrorMngMadeComSpec tCtErrorMngMadeComSpec = new TCtErrorMngMadeComSpec();
            tCtErrorMngMadeComSpec.createCriteria()
            .andAsAcptDateEqualTo(parsed.getString("trans1_date"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andOrgCallCntEqualTo(parsed.getShort("org_call_cnt"));

            List<TCtErrorMngMadeCom> tCtErrorMngMadeComList = null;
            try
            {
                tCtErrorMngMadeComList = tCtErrorMngMadeComMapper.selectBySpec(tCtErrorMngMadeComSpec);
            } catch (Exception e)
            {
                logger.warn(String.format(">>>  [T_CT_ERROR_MNG_MADE_COM_FINISH] 장애건검색실패 AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s] [%.200s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), parsed.getString("org_call_cnt"), e.getMessage()));
                throw e;
            }

            if(tCtErrorMngMadeComList == null || tCtErrorMngMadeComList.size() == 0) {
                logger.warn(String.format("[T_CT_ERROR_MNG_MADE_COM_FINISH] 해당데이터가 없습니다. AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), parsed.getString("org_call_cnt")));
                throw new MsgBrokerException(-1);
            }

            TCtErrorMngMadeCom tCtErrorMngMadeCom = tCtErrorMngMadeComList.get(0);
            int htimeConf = 0;

            if(String.format("%s%s", tCtErrorMngMadeCom.getArrivalDate(), tCtErrorMngMadeCom.getArrivalTime()).replace("null", "").compareTo(String.format("%s%s", parsed.getString("finish_date"), parsed.getString("finish_time")).replace("null", "")) > 0
            || String.format("%s%s", tCtErrorMngMadeCom.getSendDate(), tCtErrorMngMadeCom.getSendTime()).replace("null", "").compareTo(String.format("%s%s", parsed.getString("finish_date"), parsed.getString("finish_time")).replace("null", "")) > 0 ) {
                htimeConf = 0;
            } else {
                htimeConf = 1;
            }

            /* 이미 완료된 건은 update 하지 않도록 한다. */
            if(nstr(tCtErrorMngMadeCom.getFinishStatus()).equals("0")) {
                logger.warn(String.format("[SaveErrArrSchdule] 완료장애에 대한 처리결과 수신 AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), parsed.getString("org_call_cnt")));
                throw new MsgBrokerException(-2);
            }

            String hmsg = tCtErrorMngMadeCom.getOrgMsg();
            if(parsed.getString("memo").length() > 0) {
                hmsg = nstr(hmsg) + "[기-" + parsed.getString("memo") + "]";
            }

            parsed.setString("finish_tel_no", parsed.getString("finish_tel_no").replaceAll("-", ""));

            TCtErrorMngMadeCom tCtErrorMngMadeCom2 = new TCtErrorMngMadeCom();

            /* 조치 완료 건에 대해서만 완료시간을 넣어주도록 한다. */
            if(parsed.getString("not_mng_cd").equals("0")) {

                tCtErrorMngMadeCom2.setFinishDate   (parsed.getString("finish_date"));
                tCtErrorMngMadeCom2.setFinishTime   (parsed.getString("finish_time"));
                tCtErrorMngMadeCom2.setFinishStatus (parsed.getString("not_mng_cd"));
                tCtErrorMngMadeCom2.setComManNm     (parsed.getString("finish_nm"));
                tCtErrorMngMadeCom2.setComManTelNo  (parsed.getString("finish_tel_no"));
                tCtErrorMngMadeCom2.setOrgMsg       (hmsg);
                tCtErrorMngMadeCom2.setAsAcptDate   (parsed.getString("trans1_date"));
                tCtErrorMngMadeCom2.setOrgCd        (parsed.getString("CM.org_cd"));
                tCtErrorMngMadeCom2.setBranchCd     (parsed.getString("brch_cd"));
                tCtErrorMngMadeCom2.setMacNo        (parsed.getString("mac_no"));
                tCtErrorMngMadeCom2.setOrgCallCnt   (parsed.getShort("org_call_cnt"));

            } else {

                tCtErrorMngMadeCom2.setFinishStatus (parsed.getString("not_mng_cd"));
                tCtErrorMngMadeCom2.setComManNm     (parsed.getString("finish_nm"));
                tCtErrorMngMadeCom2.setComManTelNo  (parsed.getString("finish_tel_no"));
                tCtErrorMngMadeCom2.setOrgMsg       (hmsg);
                tCtErrorMngMadeCom2.setAsAcptDate   (parsed.getString("trans1_date"));
                tCtErrorMngMadeCom2.setOrgCd        (parsed.getString("CM.org_cd"));
                tCtErrorMngMadeCom2.setBranchCd     (parsed.getString("brch_cd"));
                tCtErrorMngMadeCom2.setMacNo        (parsed.getString("mac_no"));
                tCtErrorMngMadeCom2.setOrgCallCnt   (parsed.getShort("org_call_cnt"));
            }

            try
            {
                tMiscMapper.updateCtErrorMngMadeCom3(tCtErrorMngMadeCom2);
            } catch (Exception e)
            {
                logger.warn( "[T_CT_ERROR_MNG_MADE_COM-FINISH] Update Err [{}]", e.getMessage());
                throw e;
            }

        } else {

            TCtErrorMngSpec tCtErrorMngSpec = new TCtErrorMngSpec();
            tCtErrorMngSpec.createCriteria()
            .andCreateDateEqualTo(parsed.getString("trans1_date"))
            .andErrorNoEqualTo   (parsed.getString("trans1_seq"));

            List<TCtErrorMng> tCtErrorMngList = null;
            try
            {
                tCtErrorMngList = tCtErrorMngMapper.selectBySpec(tCtErrorMngSpec);
            } catch (Exception e)
            {
                logger.warn(String.format(">>> [SaveErrArrSchdule] 장애건검색실패 create_date[%s], error_no[%s] [%.200s]", parsed.getString("trans1_date"), parsed.getString("trans1_seq"), e.getMessage()));
                throw e;
            }

            if (tCtErrorMngList == null || tCtErrorMngList.size() == 0) {
                logger.warn("[SaveErrArrSchdule] 해당데이터가 없습니다. create_date[%s], error_no[%s]\n", parsed.getString("trans1_date"), parsed.getString("trans1_seq"));
                throw new Exception(String.format("[SaveErrArrSchdule] 해당데이터가 없습니다. create_date[%s], error_no[%s]\n", parsed.getString("trans1_date"), parsed.getString("trans1_seq")));
            }

            TCtErrorMng tCtErrorMng = tCtErrorMngList.get(0);
            int htimeConf = 0;
            String hformat_type = tCtErrorMng.getFormatType() == null ? "11" : tCtErrorMng.getFormatType();

            if(tCtErrorMng.getArrivalTime() == null || tCtErrorMng.getSendTime() == null) {
                htimeConf = 0;
            } else {
                if(String.format("%s%s", tCtErrorMng.getArrivalDate(), tCtErrorMng.getArrivalTime()).replace("null", "").compareTo(String.format("%s%s", parsed.getString("finish_date"), parsed.getString("finish_time")).replace("null", "")) > 0
                || String.format("%s%s", tCtErrorMng.getSendDate(), tCtErrorMng.getSendTime()).replace("null", "").compareTo(String.format("%s%s",  parsed.getString("finish_date"), parsed.getString("finish_time")).replace("null", "")) > 0 ) {
                    htimeConf = 0;
                } else {
                    htimeConf = 1;
                }
            }

            /* 이미 완료된 건은 update 하지 않도록 한다. */
            if(nstr(tCtErrorMng.getErrorStatus()).equals("7000")) {
                logger.warn(String.format("[SaveErrArrSchdule] 완료장애에 대한 처리결과 수신 AS_ACPT_date[%s], ORG_CD[%s], JIJUM_CD[%s], MAC_NO[%s], ORG_CALL_CNT[%s]",
                                parsed.getString("trans1_date"), parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"), parsed.getString("org_call_cnt")));
                throw new MsgBrokerException(-2);
            } else if(tCtErrorMng.getAsMedium() != null && tCtErrorMng.getAsMedium().equals("0000") == false) {

                /* A/S로 이미 접수되어 있는건도 UPDATE 하지 않도록 한다. 20090414*/
                /* 관제에서 저장시 error_status 없이 as_medium만 저장하고 as 접수를 하는 경우가 있어서
                    as_medium 만으로 체크 하도록 함 20090414 */

                logger.warn(String.format(">>> [SaveErrArrSchdule] 접수 장애에 대한 처리결과 수신 create_date[%s], error_no[%s]\n", parsed.getString("trans1_date"), parsed.getString("trans1_seq")));
                throw new MsgBrokerException(-2);
            }

            String hmsg = nstr(tCtErrorMng.getMsg());
            if(parsed.getString("memo").length() > 0) {
                hmsg = hmsg + "[기-" + parsed.getString("memo") + "]";
            }

            if(parsed.getString("finish_nm").length() > 0) {
                parsed.setString("finish_nm", tMiscMapper.getArrivalNm(parsed.getString("trans1_date"), parsed.getString("trans1_seq")));
            }

            parsed.setString("finish_tel_no", parsed.getString("finish_tel_no").replaceAll("-", ""));

            /* (조치결과=비정상, 불가사유=정상) 시, A/S접수 하지 않고 정상처리함. 20121101 운총조규석대리 요청 */
            if(parsed.getString("mng_result").startsWith("0") || parsed.getString("not_mng_cd").startsWith("0")) {
                tCtErrorMng.setErrorStatus("7000");
                tCtErrorMng.setAsMedium("0000");

                /* 경비사 AS 접수건은 전화통화로 처리하기위해 SKIP
                2009.08.27 김희천 요청 조치불가인경우 경비세트 시간이 완료시간으로 등록되지 않도록 하기위해
                조치불가의 경우 ERROR_STATUS = '2000'(미완료)로 넣고 특이사항에 사유를 텍스트로 넣어준다.
                단 사유에 상관없이 미완료로...*/
            } else {
                /* 조치불가 */
                tCtErrorMng.setErrorStatus("2000");
                String notMngCd = parsed.getString("not_mng_cd").substring(0, 1);

                if(notMngCd.equals("1")) {
                    hmsg = nstr(hmsg) + "[경-기기A/S]";
                } else if(notMngCd.equals("2")) {
                    hmsg = nstr(hmsg) + "[경-통신이상]";
                } else if(notMngCd.equals("3")) {
                    hmsg = nstr(hmsg) + "[경-전원]";
                } else if(notMngCd.equals("4")) {
                    hmsg = nstr(hmsg) + "[경-비품없음]";
                } else if(notMngCd.equals("5")) {
                    hmsg = nstr(hmsg) + "[경-장소폐쇄]";
                } else if(notMngCd.equals("7")) {
                    hmsg = nstr(hmsg) + "[경-기타]";
                }
            }

            /*

            경비사 조치 완료
            finish_date
            finish_time
            finish_type
            finish_nm
            finish_uid
            error_status
            work_status='6070',
            update_date
            update_uid

            */
                /* 도착이 없이 완료가 들어온 건은 비고만 저장하고 통보실패 SMS 통보실패로 저장해 준다. */
                /* 도착시간 보다 빠른시간으로 완료가 들어온건도  통보실패 SMS 통보실패로 저장해 준다.
                   20090928 BY 김희천 */

            TCtErrorMng updateTCtErrorMng = new TCtErrorMng();
            TCtErrorMngSpec tCtErrorMngSpec2 = new TCtErrorMngSpec();

            if(tCtErrorMng.getArrivalTime() == null || tCtErrorMng.getArrivalTime().length() == 0
            || Integer.parseInt(tCtErrorMng.getArrivalTime()) == 0 || htimeConf == 0) {

                updateTCtErrorMng.setSendStatus("3");
                updateTCtErrorMng.setSendSmsStatus("6030");  /*통보실패*/
                updateTCtErrorMng.setWorkStatus("6030");
                updateTCtErrorMng.setMsg(hmsg);
                updateTCtErrorMng.setUpdateDate(safeData.getDSysDate());
                updateTCtErrorMng.setUpdateUid("ERRmng");


                tCtErrorMngSpec2.createCriteria()
                .andCreateDateEqualTo(parsed.getString("trans1_date"))
                .andErrorNoEqualTo   (parsed.getString("trans1_seq"));

                try
                {
                    comPack.updateErrorMng(updateTCtErrorMng, tCtErrorMngSpec2);
                } catch (Exception e)
                {
                    logger.warn( "[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                    throw e;
                }

            } else {

                /* 조치 완료 건에 대해서만 완료시간을 넣어주도록 한다. */
                if(nstr(tCtErrorMng.getErrorStatus()).equals("7000")) {

                    TCtErrorMngSpec tCtErrorMngSpec3 = new TCtErrorMngSpec();
                    tCtErrorMngSpec3.createCriteria()
                    .andCreateDateEqualTo(parsed.getString("trans1_date"))
                    .andErrorNoEqualTo   (parsed.getString("trans1_seq"));

                    List<TCtErrorMng> tCtErrorMngList3 = tCtErrorMngMapper.selectBySpec(tCtErrorMngSpec3);

                    if(tCtErrorMngList3 != null && tCtErrorMngList3.size() > 0) {

                        for(TCtErrorMng tCtErrorMng3 : tCtErrorMngList3) {
                            TCtErrorMng updateTCtErrorMng3 = new TCtErrorMng();
                            updateTCtErrorMng3.setFinishDate (parsed.getString("finish_date"));
                            updateTCtErrorMng3.setFinishTime (parsed.getString("finish_time"));
                            updateTCtErrorMng3.setFinishType (tCtErrorMng3.getRecvPlace());
                            updateTCtErrorMng3.setErrorStatus(tCtErrorMng.getErrorStatus());
                            updateTCtErrorMng3.setFinishNm   (parsed.getString("finish_nm"));
                            updateTCtErrorMng3.setRecvTeleNo (parsed.getString("finish_tel_no"));
                            updateTCtErrorMng3.setWorkStatus ("6070");
                            updateTCtErrorMng3.setMsg        (hmsg);
                            updateTCtErrorMng3.setUpdateDate (safeData.getDSysDate());
                            updateTCtErrorMng3.setUpdateUid  ("ERRmng");

                            try
                            {
                                comPack.updateErrorMng(safeData, updateTCtErrorMng3, tCtErrorMng3);
                            } catch (Exception e)
                            {
                                logger.warn( "[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                                throw e;
                            }
                        }

                    }

                } else {
                    TCtErrorMngSpec tCtErrorMngSpec3 = new TCtErrorMngSpec();
                    tCtErrorMngSpec3.createCriteria()
                    .andCreateDateEqualTo(parsed.getString("trans1_date"))
                    .andErrorNoEqualTo   (parsed.getString("trans1_seq"));

                    List<TCtErrorMng> tCtErrorMngList3 = tCtErrorMngMapper.selectBySpec(tCtErrorMngSpec3);

                    if(tCtErrorMngList3 != null && tCtErrorMngList3.size() > 0) {

                        for(TCtErrorMng tCtErrorMng3 : tCtErrorMngList3) {
                            TCtErrorMng updateTCtErrorMng3 = new TCtErrorMng();

                            updateTCtErrorMng3.setErrorStatus(tCtErrorMng.getErrorStatus());
                            /*AS_MEDIUM  = :has_media, 경비사 AS 접수건은 전화통화로 처리하기위해 SKIP */
                            updateTCtErrorMng3.setFinishType (tCtErrorMng3.getRecvPlace());
                            updateTCtErrorMng3.setFinishNm   (parsed.getString("finish_nm"));
                            updateTCtErrorMng3.setRecvTeleNo (parsed.getString("finish_tel_no"));
                            updateTCtErrorMng3.setWorkStatus ("6070");
                            updateTCtErrorMng3.setMsg        (hmsg);
                            updateTCtErrorMng3.setUpdateDate (safeData.getDSysDate());
                            updateTCtErrorMng3.setUpdateUid  ("ERRmng");

                            try
                            {
                                comPack.updateErrorMng(safeData, updateTCtErrorMng3, tCtErrorMng3);
                            } catch (Exception e)
                            {
                                logger.warn( "[T_CT_ERROR_MNG] Update Err [{}]", e.getMessage());
                                throw e;
                            }
                        }

                    }
                }

            }

        }

        logger.warn( "[T_CT_ERROR_MNG] Update OK" );

    }//end method
}

package com.nicetcm.nibsplus.broker.msg.services;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 0900 전문중계용 전체전문수신
 * <pre>
 * MngIQ_SaveRelayMsg( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04000900")
public class In04000900Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04000900Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        if(MsgBrokerConst.SHATMS_CODE.equals(parsed.getString("CM.org_cd"))) {

            CommMsgHeader suHeadSend = new CommMsgHeader();

            if(parsed.getString("CM.msg_type").equals("0100") && parsed.getString("CM.work_type").equals("1110")) {

                suHeadSend.setOrgCd(MsgBrokerConst.SHATMS_CODE);
                suHeadSend.setRetCdSrc("S");
                suHeadSend.setMsgId("DataMng");
                suHeadSend.setTransSeqNo(parsed.getString("trans_seq_no"));
                suHeadSend.setFormatType(MsgBrokerConst.ES_CODE);
                suHeadSend.setMsgType(MsgBrokerConst.ES_REQ);
                suHeadSend.setWorkType("0110");

                Map<String, String> columnMap = new HashMap<String, String>();
                columnMap.put("create_date", parsed.getString("create_date"));
                columnMap.put("create_time", parsed.getString("create_time"));
                columnMap.put("brch_cd", parsed.getString("brch_cd"));
                columnMap.put("mac_no", parsed.getString("mac_no"));
                columnMap.put("mac_model", parsed.getString("mac_model"));
                columnMap.put("mac_made_com_cd", parsed.getString("mac_comp"));

                StringBuffer atm_state = new StringBuffer();
                String[] atmStateArray = new String[39];

                if(parsed.getString("cash_state").equals("0") || parsed.getString("cash_state").equals("1") || parsed.getString("cash_state").equals("2")) {
                    atmStateArray[0] = parsed.getString("cash_state");
                }

                if(parsed.getString("check_state").equals("0") || parsed.getString("check_state").equals("1") || parsed.getString("check_state").equals("2")) {
                    atmStateArray[1] = parsed.getString("check_state");
                }

                if(parsed.getString("cash_state_5").equals("0") || parsed.getString("cash_state_5").equals("1") || parsed.getString("cash_state_5").equals("2")) {
                    atmStateArray[21] = parsed.getString("cash_state_5");
                }

                for(String a : atmStateArray) {
                    if(a == null) {
                        atm_state.append(" ");
                    } else {
                        atm_state.append(a);
                    }
                }

                columnMap.put("state_term_mode", parsed.getString("term_mode"));
                columnMap.put("state_off_yn", parsed.getString("stop_yn"));
                columnMap.put("error_hw_yn", parsed.getString("hw_yn"));
                columnMap.put("error_cd", parsed.getString("error_cd"));
                columnMap.put("error_mtc_cd", parsed.getString("lc_cd"));

                comPack.msgSnd(suHeadSend, columnMap, safeData);

                /* 추가사항
                    기관BODY 부분을 기기사 상태전문으로 바꿔서 호스트로 전송처리 한다.
                */

                TMisc tMisc = tMiscMapper.getMadeComCd(parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"));

                if(tMisc == null) {
                    logger.warn( String.format("[MngEM_SaveManyErrCall] 기기제조사 정보  검색 실패 기관[%s] 지점[%s] 기번[%s]", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no") ));
                    throw new MsgBrokerException(-1);
                }

                Map<String, String> suCompBody = new HashMap<String, String>();
                suCompBody.put("create_date"  , parsed.getString("create_date"  ));
                suCompBody.put("create_time"  , parsed.getString("create_time"  ));
                suCompBody.put("brch_cd"     , parsed.getString("brch_cd"     ));
                suCompBody.put("brch_nm"     , parsed.getString("brch_nm"     ));
                suCompBody.put("brch_tel_no" , parsed.getString("brch_tel_no" ));
                suCompBody.put("site_cd"      , parsed.getString("site_cd"      ));
                suCompBody.put("site_nm"      , parsed.getString("site_nm"      ));
                suCompBody.put("mac_no"       , parsed.getString("mac_no"       ));
                suCompBody.put("place_gb"     , parsed.getString("place_gb"     ));
                suCompBody.put("oper_type"    , parsed.getString("oper_type"    ));
                suCompBody.put("oper_time"    , parsed.getString("oper_time"    ));
                suCompBody.put("serial_no"    , parsed.getString("serial_no"    ));

                /* 은행 기기업체코드 청호(1), LG(2), 효성(3), FKM(4)
                토탈사 기기업체코드 청호(11), 효성(12), FKM(13), LG(14) */

                String mac_comp = parsed.getString("mac_comp");

                if(mac_comp.equals("1")) {
                    suCompBody.put("mac_comp", "11");
                } else if(mac_comp.equals("2")) {
                    suCompBody.put("mac_comp", "14");
                } else if(mac_comp.equals("3")) {
                    suCompBody.put("mac_comp", "12");
                } else if(mac_comp.equals("4")) {
                    suCompBody.put("mac_comp", "13");
                }

                suCompBody.put("mac_model"       , parsed.getString("mac_model"        ));
                suCompBody.put("pro_state"       , parsed.getString("pro_state"        ));
                suCompBody.put("msg_cd"          , parsed.getString("msg_cd"           ));
                suCompBody.put("lc_cd"           , parsed.getString("lc_cd"            ));
                suCompBody.put("mtc_cd"          , parsed.getString("mtc_cd"           ));
                suCompBody.put("error_cd"        , parsed.getString("error_cd"         ));
                suCompBody.put("error_type"      , parsed.getString("error_type"       ));
                suCompBody.put("cash_state"      , parsed.getString("cash_state"       ));
                suCompBody.put("check_state"     , parsed.getString("check_state"      ));
                suCompBody.put("bill_state"      , parsed.getString("bill_state"       ));
                suCompBody.put("jurnal_state"    , parsed.getString("jurnal_state"     ));
                suCompBody.put("collect_state"   , parsed.getString("collect_state"    ));
                suCompBody.put("card_state"      , parsed.getString("card_state"       ));
                suCompBody.put("book_state"      , parsed.getString("book_state"       ));
                suCompBody.put("jiro_state"      , parsed.getString("jiro_state"       ));
                suCompBody.put("stop_yn"         , parsed.getString("stop_yn"          ));
                suCompBody.put("hw_yn"           , parsed.getString("hw_yn"            ));
                suCompBody.put("term_mode"       , parsed.getString("term_mode"        ));
                suCompBody.put("version"         , parsed.getString("version"          ));
                suCompBody.put("des_yn"          , parsed.getString("des_yn"           ));
                suCompBody.put("ic_yn"           , parsed.getString("ic_yn"            ));
                suCompBody.put("emv_yn"          , parsed.getString("emv_yn"           ));
                suCompBody.put("ir_yn"           , parsed.getString("ir_yn"            ));
                suCompBody.put("rf_yn"           , parsed.getString("rf_yn"            ));
                suCompBody.put("finger_print"    , parsed.getString("finger_print"     ));
                suCompBody.put("encryption"      , parsed.getString("encryption"       ));
                suCompBody.put("mac_info"        , parsed.getString("mac_info"         ));
                suCompBody.put("atms_mac_no"     , parsed.getString("atms_mac_no"      ));
                suCompBody.put("cash_state_5"    , parsed.getString("cash_state_5"     ));
                suCompBody.put("auto_calc_error" , parsed.getString("auto_calc_error"  ));
                suCompBody.put("auto_calc_rslt"  , parsed.getString("auto_calc_rslt"   ));

                suHeadSend.setOrgCd(tMisc.getOrgCd());
                suHeadSend.setWorkType("1130");

                comPack.msgSnd(suHeadSend, suCompBody, safeData);

            } else if(parsed.getString("CM.msg_type").equals("0400") && parsed.getString("CM.work_type").equals("1170")) {
                //suHeadSend.setFormatType(MsgBrokerConst.IQ_CODE);
                //suHeadSend.setMsgType(MsgBrokerConst.IQ_REQ);
                //suHeadSend.setWorkType("0900");
                parsed.setString("format_type", MsgBrokerConst.IQ_CODE);
                parsed.setString("msg_type", MsgBrokerConst.IQ_REQ);
                parsed.setString("work_type", "0900");


                TMisc tMisc = tMiscMapper.getMadeComCd(parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no"));

                if(tMisc == null) {
                    logger.warn( String.format("[MngEM_SaveManyErrCall] 기기제조사 정보  검색 실패 기관[%s] 지점[%s] 기번[%s]", parsed.getString("CM.org_cd"), parsed.getString("brch_cd"), parsed.getString("mac_no") ));
                    throw new MsgBrokerException(-1);
                }

                //suHeadSend.setOrgCd(tMisc.getMadeOrgCd());
                parsed.setString("CM.org_cd", tMisc.getMadeOrgCd());
                comPack.msgSnd(parsed);
            }
        }
    }//end method
}

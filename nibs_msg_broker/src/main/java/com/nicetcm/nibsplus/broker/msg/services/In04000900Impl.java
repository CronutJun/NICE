package com.nicetcm.nibsplus.broker.msg.services;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
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

            ByteBuffer orgBuf = parsed.getMessage();
            byte[] orgMsg = new byte[parsed.getSchemaLength()];
            byte[] shMsg  = new byte[parsed.getSchemaLength() - MsgBrokerConst.HEADER_LEN];
            orgBuf.position(0);
            orgBuf.get(orgMsg);

            System.arraycopy(orgMsg,MsgBrokerConst.HEADER_LEN, shMsg, 0, parsed.getSchemaLength() - MsgBrokerConst.HEADER_LEN);

            ByteBuffer shBuf  = ByteBuffer.allocateDirect(parsed.getSchemaLength() - MsgBrokerConst.HEADER_LEN);
            shBuf.put(shMsg);
            shBuf.position(0);

            MsgParser msgSHHead = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path")
                    + "shatms_msg_header.json").parseMessage(shBuf);
            try {
                CommMsgHeader suHeadSend = new CommMsgHeader();

                if( msgSHHead.getString("msg_type").equals("0100") && msgSHHead.getString("work_type").equals("1110") ) {

                    suHeadSend.setOrgCd(MsgBrokerConst.SHATMS_CODE);
                    suHeadSend.setRetCdSrc("S");
                    suHeadSend.setMsgId("DataMng");
                    suHeadSend.setFormatType(MsgBrokerConst.ES_CODE);
                    suHeadSend.setMsgType(MsgBrokerConst.ES_REQ);
                    suHeadSend.setWorkType("0110");

                    byte[] shBodyMsg  = new byte[shMsg.length - msgSHHead.getSchemaLength()];
                    System.arraycopy(shMsg, msgSHHead.getSchemaLength(), shBodyMsg, 0, shMsg.length - msgSHHead.getSchemaLength());

                    ByteBuffer shBodyBuf = ByteBuffer.allocateDirect(shMsg.length - msgSHHead.getSchemaLength());
                    shBodyBuf.put(shBodyMsg);
                    shBodyBuf.position(0);

                    MsgParser msgSHBody = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path")
                            + "shatms_state_body.json").parseMessage(shBodyBuf);
                    try {
                        Map<String, byte[]> columnMap = new HashMap<String, byte[]>();
                        columnMap.put("create_date",     msgSHBody.getBytes("create_date"));
                        columnMap.put("create_time",     msgSHBody.getBytes("create_time"));
                        columnMap.put("brch_cd",         msgSHBody.getBytes("brch_cd"));
                        columnMap.put("mac_no",          msgSHBody.getBytes("mac_no"));
                        columnMap.put("mac_model",       msgSHBody.getBytes("mac_model"));
                        columnMap.put("mac_model_com_cd",msgSHBody.getBytes("mac_comp"));

                        StringBuffer atm_state = new StringBuffer();
                        String[] atmStateArray = new String[39];

                        if( msgSHBody.getString("cash_state").equals("0")
                        ||  msgSHBody.getString("cash_state").equals("1")
                        ||  msgSHBody.getString("cash_state").equals("2") ) {
                            atmStateArray[0] = msgSHBody.getString("cash_state");
                        }

                        if( msgSHBody.getString("check_state").equals("0")
                        ||  msgSHBody.getString("check_state").equals("1")
                        ||  msgSHBody.getString("check_state").equals("2") ) {
                            atmStateArray[1] = msgSHBody.getString("check_state");
                        }

                        if( msgSHBody.getString("cash_state_5").equals("0")
                        ||  msgSHBody.getString("cash_state_5").equals("1")
                        ||  msgSHBody.getString("cash_state_5").equals("2") ) {
                            atmStateArray[21] = msgSHBody.getString("cash_state_5");
                        }

                        for(String a : atmStateArray) {
                            if(a == null) {
                                atm_state.append(" ");
                            } else {
                                atm_state.append(a);
                            }
                        }

                        columnMap.put("atm_state",       atm_state.toString().getBytes() );
                        columnMap.put("state_term_mode", msgSHBody.getBytes("term_mode") );
                        columnMap.put("state_off_yn",    msgSHBody.getBytes("stop_yn")   );
                        columnMap.put("error_hw_yn",     msgSHBody.getBytes("hw_yn")     );
                        columnMap.put("error_cd",        msgSHBody.getBytes("error_cd")  );
                        columnMap.put("error_mtc_cd",    msgSHBody.getBytes("lc_cd")     );

                        comPack.msgSnd( safeData, suHeadSend, columnMap, "C" );

                        /* 추가사항
                            기관BODY 부분을 기기사 상태전문으로 바꿔서 호스트로 전송처리 한다.
                        */

                        TMisc tMisc = tMiscMapper.getMadeComCd(parsed.getString("CM.org_cd"), msgSHBody.getString("brch_cd"), msgSHBody.getString("mac_no"));

                        if(tMisc == null) {
                            logger.warn( String.format("[MngEM_SaveManyErrCall] 기기제조사 정보  검색 실패 기관[%s] 지점[%s] 기번[%s]", parsed.getString("CM.org_cd"), msgSHBody.getString("brch_cd"), msgSHBody.getString("mac_no")) );
                            throw new Exception( String.format("[MngEM_SaveManyErrCall] 기기제조사 정보  검색 실패 기관[%s] 지점[%s] 기번[%s]", parsed.getString("CM.org_cd"), msgSHBody.getString("brch_cd"), msgSHBody.getString("mac_no")) );
                        }

                        Map<String, byte[]> suCompBody = new HashMap<String, byte[]>();
                        suCompBody.put("create_date"  , msgSHBody.getBytes("create_date"  ));
                        suCompBody.put("create_time"  , msgSHBody.getBytes("create_time"  ));
                        suCompBody.put("brch_cd"     ,  msgSHBody.getBytes("brch_cd"     ));
                        suCompBody.put("brch_nm"     ,  new String(msgSHBody.getBytes("brch_nm"), "MS949").getBytes() );
                        suCompBody.put("brch_tel_no" ,  msgSHBody.getBytes("brch_tel_no" ));
                        suCompBody.put("site_cd"      , msgSHBody.getBytes("site_cd"      ));
                        suCompBody.put("site_nm"      , new String(msgSHBody.getBytes("site_nm"), "MS949").getBytes() );
                        suCompBody.put("mac_no"       , msgSHBody.getBytes("mac_no"       ));
                        suCompBody.put("place_cl"     , msgSHBody.getBytes("place_gb"     ));
                        suCompBody.put("oper_type"    , msgSHBody.getBytes("oper_type"    ));
                        suCompBody.put("oper_time"    , msgSHBody.getBytes("oper_time"    ));
                        suCompBody.put("serial_no"    , msgSHBody.getBytes("serial_no"    ));

                        /* 은행 기기업체코드 청호(1), LG(2), 효성(3), FKM(4)
                        토탈사 기기업체코드 청호(11), 효성(12), FKM(13), LG(14) */

                        String mac_comp = msgSHBody.getString("mac_comp");

                        if(mac_comp.equals("1")) {
                            suCompBody.put("mac_comp", "11".getBytes());
                        }
                        else if(mac_comp.equals("2")) {
                            suCompBody.put("mac_comp", "14".getBytes());
                        }
                        else if(mac_comp.equals("3")) {
                            suCompBody.put("mac_comp", "12".getBytes());
                        }
                        else if(mac_comp.equals("4")) {
                            suCompBody.put("mac_comp", "13".getBytes());
                        }

                        suCompBody.put("mac_model"       , msgSHBody.getBytes("mac_model"        ));
                        suCompBody.put("pro_state"       , msgSHBody.getBytes("pro_state"        ));
                        suCompBody.put("msg_cd"          , msgSHBody.getBytes("msg_cd"           ));
                        suCompBody.put("lc_cd"           , msgSHBody.getBytes("lc_cd"            ));
                        suCompBody.put("mtc_cd"          , msgSHBody.getBytes("mtc_cd"           ));
                        suCompBody.put("error_cd"        , msgSHBody.getBytes("error_cd"         ));
                        suCompBody.put("error_type"      , msgSHBody.getBytes("error_type"       ));
                        suCompBody.put("cash_state"      , msgSHBody.getBytes("cash_state"       ));
                        suCompBody.put("check_state"     , msgSHBody.getBytes("check_state"      ));
                        suCompBody.put("bill_state"      , msgSHBody.getBytes("bill_state"       ));
                        suCompBody.put("jurnal_state"    , msgSHBody.getBytes("jurnal_state"     ));
                        suCompBody.put("collect_state"   , msgSHBody.getBytes("collect_state"    ));
                        suCompBody.put("card_state"      , msgSHBody.getBytes("card_state"       ));
                        suCompBody.put("book_state"      , msgSHBody.getBytes("book_state"       ));
                        suCompBody.put("jiro_state"      , msgSHBody.getBytes("jiro_state"       ));
                        suCompBody.put("stop_yn"         , msgSHBody.getBytes("stop_yn"          ));
                        suCompBody.put("hw_yn"           , msgSHBody.getBytes("hw_yn"            ));
                        suCompBody.put("term_mode"       , msgSHBody.getBytes("term_mode"        ));
                        suCompBody.put("version"         , msgSHBody.getBytes("version"          ));
                        suCompBody.put("des_yn"          , msgSHBody.getBytes("des_yn"           ));
                        suCompBody.put("ic_yn"           , msgSHBody.getBytes("ic_yn"            ));
                        suCompBody.put("emv_yn"          , msgSHBody.getBytes("emv_yn"           ));
                        suCompBody.put("ir_yn"           , msgSHBody.getBytes("ir_yn"            ));
                        suCompBody.put("rf_yn"           , msgSHBody.getBytes("rf_yn"            ));
                        suCompBody.put("finger_print"    , msgSHBody.getBytes("finger_print"     ));
                        suCompBody.put("encryption"      , msgSHBody.getBytes("encryption"       ));
                        suCompBody.put("mac_info"        , msgSHBody.getBytes("mac_info"         ));
                        suCompBody.put("atms_mac_no"     , msgSHBody.getBytes("atms_mac_no"      ));
                        suCompBody.put("cash_state_5"    , msgSHBody.getBytes("cash_state_5"     ));
                        suCompBody.put("auto_calc_error" , msgSHBody.getBytes("auto_calc_error"  ));
                        suCompBody.put("auto_calc_rslt"  , msgSHBody.getBytes("auto_calc_rslt"   ));

                        suHeadSend.setOrgCd(tMisc.getMadeOrgCd());
                        suHeadSend.setWorkType("1140");

                        comPack.msgSnd(safeData, suHeadSend, suCompBody, "P");
                    }
                    finally {
                        msgSHBody.clearMessage();
                    }

                }
                else if( msgSHHead.getString("msg_type").equals("0400") && msgSHHead.getString("work_type").equals("1170") ) {

                    suHeadSend.setRetCdSrc("S");
                    suHeadSend.setMsgId("DataMng");
                    suHeadSend.setFormatType(MsgBrokerConst.IQ_CODE);
                    suHeadSend.setMsgType(MsgBrokerConst.IQ_REQ);
                    suHeadSend.setWorkType("0800");

                    byte[] shBodyMsg  = new byte[shMsg.length - msgSHHead.getSchemaLength()];
                    System.arraycopy(shMsg, msgSHHead.getSchemaLength(), shBodyMsg, 0, shMsg.length - msgSHHead.getSchemaLength());

                    ByteBuffer shBodyBuf = ByteBuffer.allocateDirect(shMsg.length - msgSHHead.getSchemaLength());
                    shBodyBuf.put(shBodyMsg);
                    shBodyBuf.position(0);

                    MsgParser msgSHBody = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path")
                            + "shatms_mac_info.json").parseMessage(shBodyBuf);
                    try {
                        TMisc tMisc = tMiscMapper.getMadeComCd(parsed.getString("CM.org_cd"), msgSHBody.getString("brch_cd"), msgSHBody.getString("mac_no"));

                        if(tMisc == null) {
                            logger.warn( String.format("[MngEM_SaveManyErrCall] 기기제조사 정보  검색 실패 기관[%s] 지점[%s] 기번[%s]", parsed.getString("CM.org_cd"), msgSHBody.getString("brch_cd"), msgSHBody.getString("mac_no")) );
                            throw new Exception( String.format("[MngEM_SaveManyErrCall] 기기제조사 정보  검색 실패 기관[%s] 지점[%s] 기번[%s]", parsed.getString("CM.org_cd"), msgSHBody.getString("brch_cd"), msgSHBody.getString("mac_no")) );
                        }

                        suHeadSend.setOrgCd(tMisc.getMadeOrgCd());
                        Map<String, byte[]> columnMap = new HashMap<String, byte[]>();
                        columnMap.put("full_msg",     msgSHBody.getBytes("msg"));


                        comPack.msgSnd(safeData, suHeadSend, columnMap, "P");
                    }
                    finally {
                        msgSHBody.clearMessage();
                    }
                }
            }
            finally {
                msgSHHead.clearMessage();
            }
        }
    }//end method
}

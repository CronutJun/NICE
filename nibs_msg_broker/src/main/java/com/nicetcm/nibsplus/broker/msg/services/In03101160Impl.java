package com.nicetcm.nibsplus.broker.msg.services;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.nicetcm.nibsplus.broker.msg.MsgBrokerLib.nstr;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnAddCashRealtimeMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnAtmsAddCashReportMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnAtmsAddNhReportMapper;
import com.nicetcm.nibsplus.broker.msg.model.FnMacClose;
import com.nicetcm.nibsplus.broker.msg.model.TFnAddCashRealtime;
import com.nicetcm.nibsplus.broker.msg.model.TFnAddCashRealtimeSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsAddCashReport;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsAddCashReportSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsAddNhReport;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsAddNhReportSpec;

/**
 *
 * 1160 추가현송 보고서
 * <pre>
 * MngCM_AP_SaveAddCash( pRecvData, pSendBuf, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101160")
public class In03101160Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101160Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnAtmsAddCashReportMapper tFnAtmsAddCashReportMapper;

    @Autowired private TFnAddCashRealtimeMapper tFnAddCashRealtimeMapper;

    @Autowired private TFnAtmsAddNhReportMapper tFnAtmsAddNhReportMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        try{ comPack.checkBranchMacLength( parsed ); } catch( Exception e ) {}

        /* 실시간전송일때는 skip, 배치일경우만 update */
        /* 부산은행의 경우는 배치만 존재하므로 update 하도록 수정 2014.11.23 BHJ */
        if(parsed.getString("addcash_type").equals("B")||parsed.getString("CM.org_cd").equals(MsgBrokerConst.BU_CODE)) {

            TFnAtmsAddCashReport tFnAtmsAddCashReport = new TFnAtmsAddCashReport();
            tFnAtmsAddCashReport.setOrgSendYn("1");
            tFnAtmsAddCashReport.setUpdateUid("APmng");
            tFnAtmsAddCashReport.setUpdateDate(safeData.getDSysDate());

            TFnAtmsAddCashReportSpec tFnAtmsAddCashReportSpec = new TFnAtmsAddCashReportSpec();

            /* 추가현송 시리얼 번호가 전문에 정의 되지 않은 기관(외환등..)은 시리얼 번호 비교 하지 않도록 한다. */
            if(parsed.getString("serial_no").length() == 0) {
                tFnAtmsAddCashReportSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andAddDateEqualTo(parsed.getString("add_date"));

            } else {
                tFnAtmsAddCashReportSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andAddDateEqualTo(parsed.getString("add_date"))
                .andSerialNoEqualTo(parsed.getString("serial_no"));
            }

            try
            {
                tFnAtmsAddCashReportMapper.updateBySpecSelective(tFnAtmsAddCashReport, tFnAtmsAddCashReportSpec);
            } catch (Exception e)
            {
                logger.warn( "[T_FN_ATMS_ADD_CASH_REPORT] Update Error {}", e.getMessage() );
                throw e;
            }

            logger.warn( "[T_FN_ATMS_ADD_CASH_REPORT] Update OK" );

        } else if(parsed.getString("addcash_type").equals("A")) {
            /* 시티은행의 경우 일반 추가 현송과, 미현송 기기의 강제마감에 따른 추가 현송을 구별해 주어야 하므로
                'A' 값 추가 구분 2014.02.20
            */

            TFnAddCashRealtime tFnAddCashRealtime = new TFnAddCashRealtime();
            tFnAddCashRealtime.setOrgSendYn("1");
            tFnAddCashRealtime.setUpdateUid("APmng");
            tFnAddCashRealtime.setUpdateDate(safeData.getDSysDate());

            TFnAddCashRealtimeSpec tFnAddCashRealtimeSpec = new TFnAddCashRealtimeSpec();
            tFnAddCashRealtimeSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andAddDateEqualTo(parsed.getString("add_date"))
            .andAddTimeEqualTo(parsed.getString("add_time"));

            tFnAddCashRealtimeMapper.updateBySpecSelective(tFnAddCashRealtime, tFnAddCashRealtimeSpec);

        }
        /* 농협 실시간 추가현송일 경우에는 별도의 테이블에 송신 플레그를 update 해 준다. */
        /* 송신플레그에 오류코드를 넣어준다. 성공일 경우 '00' */
        /* 경남은행도 농협과 같이 실시간 테이블에서 실시간으로 전송 추가 (익일배치 삭제)2014.10.04
        단 경남은행은 배치 없이 모두 실시간 이므로... */
        else if( (parsed.getString("addcash_type").equals("R") && parsed.getString("CM.org_cd").equals(MsgBrokerConst.NONGH_CODE))
              ||  (!parsed.getString("addcash_type").equals("B") && parsed.getString("CM.org_cd").equals(MsgBrokerConst.KNATMS_CODE)) ) {

            TFnAtmsAddNhReport tFnAtmsAddNhReport = new TFnAtmsAddNhReport();
            tFnAtmsAddNhReport.setOrgSendYn(parsed.getString("CM.ret_cd"));

            TFnAtmsAddNhReportSpec tFnAtmsAddNhReportSpec = new TFnAtmsAddNhReportSpec();
            tFnAtmsAddNhReportSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andAddDateEqualTo(parsed.getString("add_date"))
            .andSerialNoEqualTo(parsed.getString("serial_no"));

            try
            {
                tFnAtmsAddNhReportMapper.updateBySpecSelective(tFnAtmsAddNhReport, tFnAtmsAddNhReportSpec);
            } catch (Exception e)
            {
                logger.warn( "[T_FN_ATMS_ADD_NH_REPORT] Update Error {}", e.getMessage() );
                throw e;
            }

            logger.warn( "[T_FN_ATMS_ADD_NH_REPORT] Update OK" );
        }//endif

        // PDA여부 (요청대기시 요청내역 DB(T_IF_DATA_LOG)에서 조회)
        String origMsg = comPack.getIfDataLog( safeData, "QS", parsed );
        logger.warn( "trans_seq_no = {}, origMsg = {}", parsed.getString("CM.trans_seq_no"), origMsg );
        if( origMsg != null ) {
            ByteBuffer origBuf  = ByteBuffer.allocateDirect(origMsg.getBytes().length);
            origBuf.put(origMsg.getBytes());
            origBuf.position(0);
            MsgParser msgOrig = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path")
                    + "03001160.json").parseMessage(origBuf);
            try {
                if( msgOrig.getString("pda_yn").equals("1") ) {
                    if( comPack.getError(parsed.getString("CM.ret_cd_src"), parsed.getString("CM.org_cd"), parsed.getString("CM.ret_cd")) < 0 )
                        throw new MsgBrokerException(-1);

                    /*
                     * 광주은행의 경우 기관과 나이스 점기번 체계가 다르므로 2014.06.30
                     * 삼성생명의 경우 제외
                     */
                    if( !parsed.getString("CM.org_cd").equals(MsgBrokerConst.SL_CODE) ) {
                        try { comPack.checkBranchMacLength( parsed ); } catch ( Exception e ) {}
                    }

                    /*
                     *  농협은 현송중에 추가현송을 찍으므로 해당 프로시져 호출하면 안된다. 20100920 정희성
                     */
                    if( !parsed.getString("CM.org_cd").equals(MsgBrokerConst.NONGH_CODE) ) {
                        /*
                         *  유승범 요청 insert_time으로 호출하도록 20090113
                         */
                        FnMacClose fnMacClose = new FnMacClose();
                        fnMacClose.setAddDate  ( parsed.getString("add_date")    );
                        fnMacClose.setOrgCode  ( parsed.getString("CM.org_cd")   );
                        fnMacClose.setJijumCode( parsed.getString("brch_cd")     );
                        fnMacClose.setMacNo    ( parsed.getString("mac_no")      );
                        fnMacClose.setInTime   ( parsed.getString("insert_time") );
                        fnMacClose.setUserId   ( parsed.getString("CM.msg_id")   );

                        try {
                            splMap.spFnPlusSendKn( fnMacClose );
                            /* 2004.03.30 PDA 수정 요청한 사항 */
                            if( nstr(fnMacClose.getResult()).equals("OK") ) {
                                parsed.setString( "CM.ret_cd", "00" );
                            }
                            else {
                                if( nstr(fnMacClose.getResult()).equals("01")
                                ||  nstr(fnMacClose.getResult()).equals("08") ) {
                                    parsed.setString("CM.ret_cd", fnMacClose.getResult() );
                                }
                                else {
                                    parsed.setString("CM.ret_cd", "09" );
                                }
                            }
                        }
                        catch( Exception e) {
                            logger.warn("SP_FN_PLUSSENDKN CALL ERROR: {}", e.getLocalizedMessage() );
                        }
                    }
                }
            }
            finally {
                msgOrig.clearMessage();
            }
        }

    }//end method
}

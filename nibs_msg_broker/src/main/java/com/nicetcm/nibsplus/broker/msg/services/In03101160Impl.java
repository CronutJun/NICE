package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnAddCashRealtimeMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnAtmsAddCashReportMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnAtmsAddNhReportMapper;
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
        comPack.checkBranchMacLength( parsed );

        /* 실시간전송일때는 skip, 배치일경우만 update */
        if(parsed.getString("addcash_type").equals("B")) {

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
                logger.info( "[T_FN_ATMS_ADD_CASH_REPORT] Update Error {}", e.getMessage() );
                throw e;
            }

            logger.info( "[T_FN_ATMS_ADD_CASH_REPORT] Update OK" );

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

        } else if(parsed.getString("addcash_type").equals("R") && MsgBrokerConst.NONGH_CODE.equals(parsed.getString("CM.org_cd"))) {
            /* 농협 실시간 추가현송일 경우에는 별도의 테이블에 송신 플레그를 update 해 준다. */
            /* 송신플레그에 오류코드를 넣어준다. 성공일 경우 '00' */

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
                logger.info( "[T_FN_ATMS_ADD_NH_REPORT] Update Error {}", e.getMessage() );
                throw e;
            }

            logger.info( "[T_FN_ATMS_ADD_NH_REPORT] Update OK" );
        }//endif

    }//end method
}

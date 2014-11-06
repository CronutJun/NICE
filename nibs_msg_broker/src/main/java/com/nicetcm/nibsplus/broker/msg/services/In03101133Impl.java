package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnSendBusanMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnSendBusan;
import com.nicetcm.nibsplus.broker.msg.model.TFnSendBusanSpec;

/**
 *
 * 1133 현금인도(장전) 통보
 * <pre>
 * MngCM_AP_SaveCashCharge( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101133")
public class In03101133Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101133Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnSendBusanMapper tFnSendBusanMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        String hTransYn;

        if(parsed.getLong("CM.ret_cd") == 0) {
            hTransYn = "1";

        } else {
            hTransYn = "a";

        }

        TFnSendBusan tFnSendBusan = new TFnSendBusan();
        TFnSendBusanSpec tFnSendBusanSpec = new TFnSendBusanSpec();

        /* 대구은행의 경우 응답전문에 현송일자 시간을 리턴해 주지 않으므로 */
        if(MsgBrokerConst.DGB_CODE.equals(parsed.getLong("CM.org_cd"))) {

            tFnSendBusan.setTransYn(hTransYn);

            tFnSendBusanSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andSendDateEqualTo(safeData.getSysDate())
            .andTransYnEqualTo("S");

        } else {

            tFnSendBusan.setTransYn(hTransYn);

            tFnSendBusanSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andSendDateEqualTo(parsed.getString("close_date"));
        }

        try
        {
            tFnSendBusanMapper.updateBySpecSelective(tFnSendBusan, tFnSendBusanSpec);
        } catch (Exception e)
        {
            logger.warn("[T_FN_SEND_BUSAN] UPDATE Err {}", e.getMessage());
            throw e;
        }

        logger.warn("[T_FN_SEND_BUSAN] UPDATE OK");

    }
}

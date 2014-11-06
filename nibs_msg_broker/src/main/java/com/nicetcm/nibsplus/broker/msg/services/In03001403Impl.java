package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnBrandSvcFeeMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandSvcFee;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandSvcFeeSpec;

/**
 *
 * 1403 확정청구용역료 제출
 * <pre>
 * MngCM_UpdateFinalServiceFee( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03001403")
public class In03001403Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03001403Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnBrandSvcFeeMapper tFnBrandSvcFeeMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        /*  송신내역에 대한 응답을 처리한다.
         *  ORG_SEND_YN = '1'로 업데이트한다. */

        TFnBrandSvcFee tFnBrandSvcFee = new TFnBrandSvcFee();
        tFnBrandSvcFee.setOrgSendYn("1");
        tFnBrandSvcFee.setSendDate(safeData.getDSysDate());

        TFnBrandSvcFeeSpec tFnBrandSvcFeeSpec = new TFnBrandSvcFeeSpec();
        tFnBrandSvcFeeSpec.createCriteria()
        .andOrgCdEqualTo("096")
        .andBranchCdEqualTo("9600")
        .andMacNoEqualTo(parsed.getString("mac_no"))
        .andBrandOrgCdEqualTo(parsed.getString("CM.org_cd"));

        try
        {
            tFnBrandSvcFeeMapper.updateBySpecSelective(tFnBrandSvcFee, tFnBrandSvcFeeSpec);
        } catch (Exception e)
        {
            logger.warn(">>> [T_FN_BRAND_SVC_FEE] UPDATE ERROR {}", e.getMessage());
            throw e;
        }

        logger.warn(">>> [T_FN_BRAND_SVC_FEE] UPDATE OK");
    }
}

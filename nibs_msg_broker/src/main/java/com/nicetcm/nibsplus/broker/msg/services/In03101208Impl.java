package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnNotendEmartFrgnSpec;

/**
 *
 * 1208 정산기 회수금내역(외화)
 * <pre>
 * MngCM_AP_SaveCollectEmartForeign( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101208")
public class In03101208Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101208Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
/*        TFnForeigncurrency tFnForeigncurrency = new TFnForeigncurrency();
        tFnNotendEmartFrgn.setOrgSendYn("1");

        TFnNotendEmartFrgnSpec tFnNotendEmartFrgnSpec = new TFnNotendEmartFrgnSpec();
        tFnNotendEmartFrgnSpec.createCriteria()
        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
        .andBranchCdEqualTo(parsed.getString("brch_cd"))
        .andMacNoEqualTo(parsed.getString("mac_no"))
        .andDealDateEqualTo(parsed.getString("deal_date"))
        .andDealTimeEqualTo(parsed.getString("deal_time"))
        .andMemberIdEqualTo(parsed.getString("member_id"));

        try
        {
            tFnNotendEmartFrgnMapper.updateBySpecSelective(tFnNotendEmartFrgn, tFnNotendEmartFrgnSpec);

        } catch (Exception e)
        {
            logger.info(">>> [T_FN_NOTEND_EMART_FRGN] UPDATE ERROR {}", e.getMessage());
            throw e;
        }

        logger.info(">>> [T_FN_NOTEND_EMART_FRGN] UPDATE OK");*/
    }
}

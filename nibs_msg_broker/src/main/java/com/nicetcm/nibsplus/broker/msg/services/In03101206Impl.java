package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnNotendEmartMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnNotendEmart;
import com.nicetcm.nibsplus.broker.msg.model.TFnNotendEmartSpec;

/**
 *
 * 1206 정산기 미마감내역(일반)
 * <pre>
 * MngCM_AP_SaveNotEndEmart( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101206")
public class In03101206Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101206Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnNotendEmartMapper tFnNotendEmartMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TFnNotendEmart tFnNotendEmart = new TFnNotendEmart();
        tFnNotendEmart.setOrgSendYn("1");

        TFnNotendEmartSpec tFnNotendEmartSpec = new TFnNotendEmartSpec();
        tFnNotendEmartSpec.createCriteria()
        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
        .andBranchCdEqualTo(parsed.getString("brch_cd"))
        .andMacNoEqualTo(parsed.getString("mac_no"))
        .andDealDateEqualTo(parsed.getString("deal_date"))
        .andDealTimeEqualTo(parsed.getString("deal_time"))
        .andMemberIdEqualTo(parsed.getString("member_id"));

        try
        {
            tFnNotendEmartMapper.updateBySpecSelective(tFnNotendEmart, tFnNotendEmartSpec);
        } catch (Exception e)
        {
            logger.warn(">>> [T_FN_NOTEND_EMART] UPDATE ERROR {}", e.getMessage());
            throw e;
        }

        logger.warn(">>> [T_FN_NOTEND_EMART] UPDATE OK");
    }
}

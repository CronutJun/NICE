package com.nicetcm.nibsplus.broker.msg.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSetDemandOtMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetDemandOt;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetDemandOtSpec;

/**
*
* 1420 일회성비용청구

*
* @author 2014.11.26 b.h.j
* @version 1.0
* @see
*/
@Service("in02101420")
public class In02101420Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCmSetDemandOtMapper tCmSetDemandOtMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TCmSetDemandOt tCmSetDemandOt = new TCmSetDemandOt();
        tCmSetDemandOt.setOrgSendYn("1");
        tCmSetDemandOt.setUpdateUid("APmng");
        tCmSetDemandOt.setInsertUid("APmng");

        TCmSetDemandOtSpec tCmSetDemandOtSpec = new TCmSetDemandOtSpec();
        tCmSetDemandOtSpec.createCriteria()
        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
        .andDemandMonEqualTo(parsed.getString("demand_mon"))
        .andCostTypeEqualTo(parsed.getString("cost_type"))
        .andDetailTypeEqualTo(parsed.getString("detail_type"))
        .andSeqEqualTo(parsed.getString("seq"))
        .andWorkDateEqualTo(parsed.getString("work_date"));

        try
        {
            tCmSetDemandOtMapper.updateBySpecSelective(tCmSetDemandOt, tCmSetDemandOtSpec);
        } catch (Exception e)
        {
            logger.warn("[T_CM_SET_DEMAND_OT] Update Error [{}]", e.getMessage());
            throw e;
        }

        logger.warn("[T_CM_SET_DEMAND_OT] Update OK");
    }
}
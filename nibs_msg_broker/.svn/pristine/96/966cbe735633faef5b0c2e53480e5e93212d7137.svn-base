package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnWrVanDemandMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemand;
import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemandSpec;

/**
 *
 * 1193 브랜드제휴 운영자금통보
 * <pre>
 * MngCM_AP_BrandOperFunds( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101193")
public class In03101193Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101193Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnWrVanDemandMapper tFnWrVanDemandMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TFnWrVanDemand tFnWrVanDemand = new TFnWrVanDemand();
        tFnWrVanDemand.setOrgSendYn("1");

        TFnWrVanDemandSpec tFnWrVanDemandSpec = new TFnWrVanDemandSpec();
        tFnWrVanDemandSpec.createCriteria()
        .andDemandDateEqualTo(parsed.getString("demand_date"))
        .andOrgCdEqualTo( (parsed.getString("CM.org_cd").equals("0KU") ? "003" : parsed.getString("CM.org_cd")) );

        try
        {
            tFnWrVanDemandMapper.updateBySpecSelective(tFnWrVanDemand, tFnWrVanDemandSpec);

        } catch (Exception e)
        {
            logger.info(String.format("[T_FN_WR_VAN_DEMAND] Update Err!! Err_cd[%d] Demand_date[%s] OrgCd[%s]", e.getMessage(), parsed.getString("demand_date"), parsed.getString("CM.org_cd")));
            throw e;
        }

        logger.info("[T_FN_WR_VAN_DEMAND] Update Success!! Demand_date[%s] OrgCd[%s]\n", parsed.getString("demand_date"), parsed.getString("CM.org_cd"));
    }


}

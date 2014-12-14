package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSiteEnvCheckMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSiteEnvCheck;
import com.nicetcm.nibsplus.broker.msg.model.TCmSiteEnvCheckSpec;

/**
 *
 * 1260 코너환경관리결과
 * <pre>
 * MngIQ_AP_SaveEnvChk( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04101260")
public class In04101260Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCmSiteEnvCheckMapper tCmSiteEnvCheckMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TCmSiteEnvCheck tCmSiteEnvCheck = new TCmSiteEnvCheck();
        tCmSiteEnvCheck.setOrgSendYn("1");
        tCmSiteEnvCheck.setOrgSendDate(safeData.getDSysDate());
        tCmSiteEnvCheck.setUpdateUid("APmng");

        TCmSiteEnvCheckSpec tCmSiteEnvCheckSpec = new TCmSiteEnvCheckSpec();
        tCmSiteEnvCheckSpec.createCriteria()
        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
        .andBranchCdEqualTo(parsed.getString("brch_cd"))
        .andSiteCdEqualTo(parsed.getString("site_cd"))
        .andCheckDateEqualTo(parsed.getString("check_date"));

        try
        {
            tCmSiteEnvCheckMapper.updateBySpecSelective(tCmSiteEnvCheck, tCmSiteEnvCheckSpec);
        } catch (Exception e)
        {
            logger.warn("[T_CM_SITE_ENV_CHECK] Update Error [{}]", e.getMessage());
            throw e;
        }

        logger.warn("[T_CM_SITE_ENV_CHECK] Update OK");
    }
}
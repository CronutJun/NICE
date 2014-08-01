package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSiteCheckDayMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSiteCheckDay;
import com.nicetcm.nibsplus.broker.msg.model.TCmSiteCheckDaySpec;

/**
 *
 * 1250 코너일일점검사진정보
 * <pre>
 * MngIQ_AP_SaveSiteChkURL( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04101250")
public class In04101250Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TCmSiteCheckDayMapper tCmSiteCheckDayMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TCmSiteCheckDay tCmSiteCheckDay = new TCmSiteCheckDay();
        tCmSiteCheckDay.setOrgSendYn("2");
        tCmSiteCheckDay.setOrgSendDate(safeData.getDSysDate());
        tCmSiteCheckDay.setUpdateUid("APmng");

        TCmSiteCheckDaySpec tCmSiteCheckDaySpec = new TCmSiteCheckDaySpec();
        tCmSiteCheckDaySpec.createCriteria()
        .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
        .andBranchCdEqualTo(parsed.getString("brch_cd"))
        .andSiteCdEqualTo(parsed.getString("site_cd"))
        .andCheckDateEqualTo(parsed.getString("check_date"));

        try
        {
            tCmSiteCheckDayMapper.updateBySpecSelective(tCmSiteCheckDay, tCmSiteCheckDaySpec);
        } catch (Exception e)
        {
            logger.info("[T_CM_SITE_CHECK_DAY] Update Error [{}]", e.getMessage());
            throw e;
        }

        logger.info("[T_CM_SITE_CHECK_DAY] Update OK");
    }
}
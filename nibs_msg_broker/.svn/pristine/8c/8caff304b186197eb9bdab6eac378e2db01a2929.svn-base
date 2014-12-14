package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnBranchEmartMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnEmartDemandDateMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnBranchEmart;
import com.nicetcm.nibsplus.broker.msg.model.TFnBranchEmartSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnEmartDemandDate;
import com.nicetcm.nibsplus.broker.msg.model.TFnEmartDemandDateSpec;

/**
 *
 * 1210 현송주기 통보
 * <pre>
 * MngCM_AP_CashCycle( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101210")
public class In03101210Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101210Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnEmartDemandDateMapper tFnEmartDemandDateMapper;

    @Autowired private TFnBranchEmartMapper tFnBranchEmartMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        if(MsgBrokerConst.EMART_CODE.equals(parsed.getString("CM.org_cd"))) {
            if(parsed.getString("info_type").equals("0") || parsed.getString("info_type").equals("2")) {
                TFnEmartDemandDate tFnEmartDemandDate = new TFnEmartDemandDate();
                tFnEmartDemandDate.setOrgSendYn("1");

                TFnEmartDemandDateSpec tFnEmartDemandDateSpec = new TFnEmartDemandDateSpec();
                tFnEmartDemandDateSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andBaseDateEqualTo(parsed.getString("trans_date"));

                try
                {
                    tFnEmartDemandDateMapper.updateBySpecSelective(tFnEmartDemandDate, tFnEmartDemandDateSpec);
                } catch (Exception e)
                {
                    logger.warn("[T_FN_EMART_DEMAND_DATE] UPDATE ERROR {}", e.getMessage());
                    throw e;
                }
            }//endif

            if(parsed.getString("info_type").equals("0") || parsed.getString("info_type").equals("1")) {
                TFnBranchEmart tFnBranchEmart = new TFnBranchEmart();
                tFnBranchEmart.setOrgSendYn("1");

                TFnBranchEmartSpec tFnBranchEmartSpec = new TFnBranchEmartSpec();
                tFnBranchEmartSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"));

                try
                {
                    tFnBranchEmartMapper.updateBySpecSelective(tFnBranchEmart, tFnBranchEmartSpec);
                } catch (Exception e)
                {
                    logger.warn("[T_FN_BRANCH_EMART] UPDATE ERROR {}", e.getMessage());
                    throw e;
                }
            }//endif
        }
    }
}

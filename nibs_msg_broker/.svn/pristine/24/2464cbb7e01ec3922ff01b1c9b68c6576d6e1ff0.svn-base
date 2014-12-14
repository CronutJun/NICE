package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnSapMasterMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnSapMaster;
import com.nicetcm.nibsplus.broker.msg.model.TFnSapMasterSpec;

/**
 *
 * 이랜드 정산기 SAP File Master 정보 "1300"
 * <pre>
 * MngIQ_AP_SaveCalcSapMaster( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04101300")
public class In04101300Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TFnSapMasterMapper tFnSapMasterMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TFnSapMaster tFnSapMaster = new TFnSapMaster();
        tFnSapMaster.setOrgSendYn("1");

        TFnSapMasterSpec tFnSapMasterSpec = new TFnSapMasterSpec();
        tFnSapMasterSpec.createCriteria()
        .andDealDateEqualTo(parsed.getString("deal_date"))
        .andBranchCdEqualTo(parsed.getString("brch_cd"))
        .andMemberIdEqualTo(parsed.getString("member_id"));

        try
        {
            tFnSapMasterMapper.updateBySpecSelective(tFnSapMaster, tFnSapMasterSpec);
        } catch (Exception e)
        {
            logger.warn( "[T_FN_SAP_MASTER] Update Error [{}]", e.getMessage());
            throw e;
        }

        logger.warn( "[T_FN_SAP_MASTER] Update OK" );
    }
}
package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnSapDetailMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnSapDetail;
import com.nicetcm.nibsplus.broker.msg.model.TFnSapDetailSpec;

/**
 *
 * 이랜드 정산기 SAP File Detail 정보 "1301"
 * <pre>
 * MngIQ_AP_SaveCalcSapDetail( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04101301")
public class In04101301Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TFnSapDetailMapper tFnSapDetailMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TFnSapDetail tFnSapDetail = new TFnSapDetail();
        tFnSapDetail.setOrgSendYn("1");

        TFnSapDetailSpec tFnSapDetailSpec = new TFnSapDetailSpec();
        tFnSapDetailSpec.createCriteria()
        .andDealDateEqualTo(parsed.getString("deal_date"))
        .andBranchCdEqualTo(parsed.getString("brch_cd"))
        .andMemberIdEqualTo(parsed.getString("member_id"))
        .andCuponCdEqualTo(parsed.getString("cupon_cd"));

        try
        {
            tFnSapDetailMapper.updateBySpecSelective(tFnSapDetail, tFnSapDetailSpec);
        } catch (Exception e)
        {
            logger.info( "[T_FN_SAP_DETAIL] Update Error [{}]", e.getMessage() );
            throw e;
        }

        logger.info( "[T_FN_SAP_DETAIL] Update OK" );
    }
}
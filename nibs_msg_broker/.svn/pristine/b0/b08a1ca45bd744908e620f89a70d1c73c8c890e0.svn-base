package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmGoodsApply;

/**
 *
 * 용도(소모)품 신청      "1400"
 * <pre>
 * MngIQ_SaveApplyGoods( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04001400")
public class In04001400Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04001400Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TCmGoodsApply tCmGoodsApply = new TCmGoodsApply();
        tCmGoodsApply.setOrgSendYn("1");
        tCmGoodsApply.setApplyDate(parsed.getString("apply_date"));
        tCmGoodsApply.setOrgCd(parsed.getString("CM.org_cd"));
        tCmGoodsApply.setBranchCd(parsed.getString("brch_cd"));
        tCmGoodsApply.setGoodCd(parsed.getString("good_cd"));

        try
        {
            tMiscMapper.updateCmGoodsApply(tCmGoodsApply);
        } catch (Exception e)
        {
            logger.info( "[T_CM_GOODS_APPLY] Update Error [{}]", e.getMessage());
            throw e;
        }

        logger.info( "[T_CM_GOODS_APPLY] Update OK" );

    }
}

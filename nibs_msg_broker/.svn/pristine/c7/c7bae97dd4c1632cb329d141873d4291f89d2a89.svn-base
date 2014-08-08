package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnElandCouponMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnElandCoupon;
import com.nicetcm.nibsplus.broker.msg.model.TFnElandCouponSpec;

/**
 *
 * 이랜드 상품권 마감정보   "1310"
 * <pre>
 * MngIQ_AP_SaveCloseCoupon( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in04101310")
public class In04101310Impl extends InMsgHandlerImpl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired private TFnElandCouponMapper tFnElandCouponMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        TFnElandCoupon tFnElandCoupon = new TFnElandCoupon();
        tFnElandCoupon.setOrgSendYn("1");

        TFnElandCouponSpec tFnElandCouponSpec = new TFnElandCouponSpec();
        tFnElandCouponSpec.createCriteria()
        .andDealDateEqualTo(parsed.getString("deal_date"))
        .andBranchCdEqualTo(parsed.getString("brch_cd"))
        .andCouponCdEqualTo(parsed.getString("coupon_cd"))
        .andVolumeCdEqualTo(parsed.getString("volume_cd"));

        try
        {
            tFnElandCouponMapper.updateBySpecSelective(tFnElandCoupon, tFnElandCouponSpec);
        } catch (Exception e)
        {
            logger.info( "[T_FN_ELAND_COUPON] Update Error [{}]", e.getMessage());
            throw e;
        }

        logger.info( "[T_FN_ELAND_COUPON] Update OK" );
    }
}
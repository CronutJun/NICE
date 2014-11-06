package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnBrandCommissionFeeMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandCommissionFee;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandCommissionFeeSpec;

/**
 *
 * 1404 수수료 청구내역 제출
 * <pre>
 * MngCM_UpdateCommissionFee( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03001404")
public class In03001404Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03001404Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnBrandCommissionFeeMapper tFnBrandCommissionFeeMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        /*  송신내역에 대한 응답을 처리한다.
         *  ORG_SEND_YN = '1'로 업데이트한다.                                  */

        TFnBrandCommissionFee tFnBrandCommissionFee = new TFnBrandCommissionFee();
        tFnBrandCommissionFee.setOrgSendYn("1");
        tFnBrandCommissionFee.setUpdateDate(safeData.getDSysDate());
        tFnBrandCommissionFee.setUpdateUid("online");

        TFnBrandCommissionFeeSpec tFnBrandCommissionFeeSpec = new TFnBrandCommissionFeeSpec();
        tFnBrandCommissionFeeSpec.createCriteria()
        .andYearMonEqualTo(parsed.getString("year_mon"))
        .andOrgCdEqualTo(parsed.getString("CM.org_cd"));

        try
        {
            tFnBrandCommissionFeeMapper.updateBySpecSelective(tFnBrandCommissionFee, tFnBrandCommissionFeeSpec);
        } catch (Exception e)
        {
            logger.warn(">>> [T_FN_BRAND_COMMISSION_FEE] Update ERROR {}", e.getMessage());
            throw e;
        }

        logger.warn("[T_FN_BRAND_COMMISSION_FEE] Update Complete");
    }
}

package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnBrandSuspenseMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandSuspense;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandSuspenseSpec;

/**
 *
 * 1402 가수금 미정리내역 통보
 * <pre>
 * MngCM_SaveSuspenseAmt( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000402")
public class In03000402Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000402Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnBrandSuspenseMapper tFnBrandSuspenseMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        boolean isDbDupData = false;

        try
        {
            TFnBrandSuspense tFnBrandSuspense = new TFnBrandSuspense();
            tFnBrandSuspense.setStdDate   (parsed.getString("std_date"));
            tFnBrandSuspense.setOrgCd     (parsed.getString("CM.org_cd"));
            tFnBrandSuspense.setReceiptAmt(parsed.getLong("suspnd_amt"));
            tFnBrandSuspense.setUpdateDate(safeData.getDSysDate());
            tFnBrandSuspense.setUpdateUid ("online");

            tFnBrandSuspenseMapper.insertSelective(tFnBrandSuspense);

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {
            isDbDupData = true;

        } catch (Exception e)
        {
            logger.warn(">>> [T_FN_BRAND_SUSPENSE] Insert ERROR {}", e.getMessage());
            throw e;
        }

        if(isDbDupData) {
            TFnBrandSuspense tFnBrandSuspense = new TFnBrandSuspense();
            tFnBrandSuspense.setReceiptAmt(parsed.getLong("suspnd_amt"));
            tFnBrandSuspense.setUpdateDate(safeData.getDSysDate());
            tFnBrandSuspense.setUpdateUid ("online");

            TFnBrandSuspenseSpec tFnBrandSuspenseSpec = new TFnBrandSuspenseSpec();
            tFnBrandSuspenseSpec.createCriteria()
            .andStdDateEqualTo(parsed.getString("std_date"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"));

            try
            {
                tFnBrandSuspenseMapper.updateBySpecSelective(tFnBrandSuspense, tFnBrandSuspenseSpec);
            } catch (Exception e)
            {
                logger.warn(">>> [T_FN_BRAND_SUSPENSE] Update ERROR {}", e.getMessage());
                throw e;
            }

            logger.warn("[T_FN_BRAND_SUSPENSE] Update Complete");
        } else {
            logger.warn("[T_FN_BRAND_SUSPENSE] Insert Complete");
        }
    }
}

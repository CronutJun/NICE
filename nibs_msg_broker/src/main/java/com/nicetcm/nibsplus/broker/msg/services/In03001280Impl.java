package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnKibMacDsumMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnKibMacDsum;
import com.nicetcm.nibsplus.broker.msg.model.TFnKibMacDsumSpec;

/**
 *
 * 1280 거래일별 실적 통보
 * <pre>
 * MngCM_SaveTransDate( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03001280")
public class In03001280Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03001280Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnKibMacDsumMapper tFnKibMacDsumMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {


        boolean isDbDupData = false;

        try
        {
            TFnKibMacDsum tFnKibMacDsum = new TFnKibMacDsum();
            tFnKibMacDsum.setDealDate          (parsed.getString("deal_date"));
            tFnKibMacDsum.setOrgCd             (parsed.getString("CM.org_cd"));
            tFnKibMacDsum.setBranchCd          (parsed.getString("brch_cd"));
            tFnKibMacDsum.setMacNo             (parsed.getString("mac_no"));
            tFnKibMacDsum.setPreCloseOutAmt    (parsed.getString("pre_close_out_amt"));
            tFnKibMacDsum.setPreCloseInAmt     (parsed.getString("pre_close_in_amt"));
            tFnKibMacDsum.setAfterCloseOutAmt  (parsed.getString("after_close_out_amt"));
            tFnKibMacDsum.setAfterCloseInAmt   (parsed.getString("after_close_in_amt"));
            tFnKibMacDsum.setOutTotalAmt       (parsed.getString("out_amt"));
            tFnKibMacDsum.setInTotalAmt        (parsed.getString("in_amt"));
            tFnKibMacDsum.setInsertDate        (safeData.getDSysDate());
            tFnKibMacDsum.setInsertUid         ("MNG_IN");

            tFnKibMacDsumMapper.insertSelective(tFnKibMacDsum);

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {
            isDbDupData = true;

        } catch (Exception e)
        {
            logger.warn(">>> (T_FN_KIB_MAC_DSUM) INSERT ERROR {}", e.getMessage());
            throw e;
        }

        if(isDbDupData) {
            logger.warn("...기기별 일집계 중복수신 << ...");

            TFnKibMacDsum tFnKibMacDsum = new TFnKibMacDsum();
            tFnKibMacDsum.setPreCloseOutAmt    (parsed.getString("pre_close_out_amt"));
            tFnKibMacDsum.setPreCloseInAmt     (parsed.getString("pre_close_in_amt"));
            tFnKibMacDsum.setAfterCloseOutAmt  (parsed.getString("after_close_out_amt"));
            tFnKibMacDsum.setAfterCloseInAmt   (parsed.getString("after_close_in_amt"));
            tFnKibMacDsum.setOutTotalAmt       (parsed.getString("out_amt"));
            tFnKibMacDsum.setInTotalAmt        (parsed.getString("in_amt"));
            tFnKibMacDsum.setInsertDate        (safeData.getDSysDate());
            tFnKibMacDsum.setInsertUid         (parsed.getString("MNG_UP"));

            TFnKibMacDsumSpec tFnKibMacDsumSpec = new TFnKibMacDsumSpec();
            tFnKibMacDsumSpec.createCriteria()
            .andDealDateEqualTo(parsed.getString("deal_date"))
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"));

            try
            {
                tFnKibMacDsumMapper.updateBySpecSelective(tFnKibMacDsum, tFnKibMacDsumSpec);
            } catch (Exception e)
            {
                logger.warn(">>> (T_FN_KIB_MAC_DSUM) UPDATE ERROR {}", e.getMessage());
                throw e;
            }

            logger.warn("!!!기기별 일집계 처리완료(UPDATE)!!!");
        }


        logger.warn("!!!확정현송금액 처리완료!!!");
    }
}

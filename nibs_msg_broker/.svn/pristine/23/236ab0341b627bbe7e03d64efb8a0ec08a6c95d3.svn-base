package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnEmartAmtMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnEmartAmt;
import com.nicetcm.nibsplus.broker.msg.model.TFnEmartAmtSpec;

/**
 *
 * 0250 잔전요청
 * <pre>
 * MngCM_SaveReqRemainAmt( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000250")
public class In03000250Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000250Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnEmartAmtMapper tFnEmartAmtMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        if(parsed.getString("work_type").equals("1")) {

            boolean isDbDupData = false;

            /* 등록, 수정 */
            try
            {
                TFnEmartAmt tFnEmartAmt = new TFnEmartAmt();
                tFnEmartAmt.setSendDate    (parsed.getString("send_date"));
                tFnEmartAmt.setCarryGroupCd("10");
                tFnEmartAmt.setCarryOrgCd  (parsed.getString("carry_org_cd"));
                tFnEmartAmt.setEmartCw53Amt(parsed.getLong("req_amt_5000"));
                tFnEmartAmt.setEmartCw13Amt(parsed.getLong("req_amt_1000"));
                tFnEmartAmt.setEmartCw52Amt(parsed.getLong("req_amt_500"));
                tFnEmartAmt.setEmartCw12Amt(parsed.getLong("req_amt_100"));
                tFnEmartAmt.setEmartCw51Amt(parsed.getLong("req_amt_50"));
                tFnEmartAmt.setEmartCw11Amt(parsed.getLong("req_amt_10"));
                tFnEmartAmt.setInsertUid   (parsed.getString("ETC_In"));
                tFnEmartAmt.setInsertDate  (safeData.getDSysDate());
                tFnEmartAmt.setUpdateUid   (parsed.getString("ETC_In"));
                tFnEmartAmt.setUpdateDate  (safeData.getDSysDate());

                tFnEmartAmtMapper.insertSelective(tFnEmartAmt);

            } catch( org.springframework.dao.DataIntegrityViolationException e ) {
                isDbDupData = true;

            } catch (Exception e)
            {
                logger.info(">>> [T_FN_EMART_AMT] INSERT ERROR {}", e.getMessage());
                throw e;
            }

            if(isDbDupData) {
                TFnEmartAmt tFnEmartAmt = new TFnEmartAmt();
                tFnEmartAmt.setEmartCw53Amt(parsed.getLong("req_amt_5000"));
                tFnEmartAmt.setEmartCw13Amt(parsed.getLong("req_amt_1000"));
                tFnEmartAmt.setEmartCw52Amt(parsed.getLong("req_amt_500"));
                tFnEmartAmt.setEmartCw12Amt(parsed.getLong("req_amt_100"));
                tFnEmartAmt.setEmartCw51Amt(parsed.getLong("req_amt_50"));
                tFnEmartAmt.setEmartCw11Amt(parsed.getLong("req_amt_10"));
                tFnEmartAmt.setInsertUid   (parsed.getString("ETC_Up"));
                tFnEmartAmt.setInsertDate  (safeData.getDSysDate());
                tFnEmartAmt.setUpdateUid   (parsed.getString("ETC_Up"));
                tFnEmartAmt.setUpdateDate  (safeData.getDSysDate());

                TFnEmartAmtSpec tFnEmartAmtSpec = new TFnEmartAmtSpec();
                tFnEmartAmtSpec.createCriteria()
                .andSendDateEqualTo(parsed.getString("send_date"))
                .andCarryGroupCdEqualTo("10")
                .andCarryOrgCdEqualTo(parsed.getString("carry_org_cd"));

                try
                {
                    tFnEmartAmtMapper.updateBySpecSelective(tFnEmartAmt, tFnEmartAmtSpec);
                } catch (Exception e)
                {
                    logger.info(">>> [T_FN_EMART_AMT] UPDATE ERROR {}", e.getMessage());
                    throw e;
                }

            }
        } else {
            /* 삭제 */
            TFnEmartAmtSpec tFnEmartAmtSpec = new TFnEmartAmtSpec();
            tFnEmartAmtSpec.createCriteria()
            .andSendDateEqualTo(parsed.getString("send_date"))
            .andCarryGroupCdEqualTo("10")
            .andCarryOrgCdEqualTo(parsed.getString("carry_org_cd"));

            try
            {
                tFnEmartAmtMapper.deleteBySpec(tFnEmartAmtSpec);
            } catch (Exception e)
            {
                logger.info(">>> [T_FN_EMART_AMT] DELETE ERROR {}", e.getMessage());
                throw e;
            }
        }


    }//end method
}

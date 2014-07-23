package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnChalsiDealDsum;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 0132 정산기 일마감/시재회수
 * <pre>
 * MngCM_SaveCalcMacAmt( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000132")
public class In03000132Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000132Impl.class);

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        /* 같은날자의 같은 기번으로 데이터가 중복 들어오면 오류 응답 송신 */
        /* 일마감 전문일 경우는 deal_date + 1 의 날짜를 체크해야 함  => 20080310 */
        TMisc tMiscParam = new TMisc();
        tMiscParam.setCloseType(parsed.getString("close_type"));
        tMiscParam.setDealDate(parsed.getString("deal_date"));
        tMiscParam.setOrgCd(parsed.getString("CM.org_cd"));
        tMiscParam.setBranchCd(parsed.getString("brch_cd"));
        tMiscParam.setMacNo(parsed.getString("mac_no"));

        TMisc tMiscResult = null;

        try
        {
            tMiscResult = tMiscMapper.selectCountFnChalsiDealDsum(tMiscParam);
        } catch (Exception e)
        {
            logger.info(">>> [DBCalcMacAmtInsertProc] 마감/시재회수 존재여부 체크오류 {}", e.getMessage());
            throw e;
        }

        if(tMiscResult == null) {
            throw new MsgBrokerException(">>> [DBCalcMacAmtInsertProc] 마감/시재회수 존재여부 체크오류", -1);
        }

        if(tMiscResult.getCnt() > 0) {
            logger.info(">>> [DBCalcMacAmtInsertProc] 마감/시재회수 데이터가 이미 있습니다 ");
            return; /* 중복 데이타에 대해 정상응답 전송 */
        }

        TFnChalsiDealDsum tFnChalsiDealDsum = new TFnChalsiDealDsum();


    }
}

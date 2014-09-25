package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnInoutRealtimeMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnInoutRealtime;
import com.nicetcm.nibsplus.broker.msg.model.TFnInoutRealtimeSpec;

/**
 *
 * 0201 실시간 입출금액 통보
 * <pre>
 * MngCM_SaveRealInOut( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000201")
public class In03000201Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000201Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnInoutRealtimeMapper tFnInoutRealtimeMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {


        boolean isDbDupData = false;

        try
        {
            TFnInoutRealtime tFnInoutRealtime = new TFnInoutRealtime();
            tFnInoutRealtime.setOrgCd       (parsed.getString("CM.org_cd"));
            tFnInoutRealtime.setBranchCd    (parsed.getString("brch_cd"));
            tFnInoutRealtime.setMacNo       (parsed.getString("mac_no"));

            tFnInoutRealtime.setSeqNo       ((parsed.getInt("seq_no")));
            tFnInoutRealtime.setDealDate    (MsgBrokerLib.rtrim(parsed.getString("trade_date")));
            tFnInoutRealtime.setDealTime    (MsgBrokerLib.rtrim(parsed.getString("trade_time")));
            tFnInoutRealtime.setDealAmt     ((parsed.getLong("trade_amt")));
            tFnInoutRealtime.setDealType    (MsgBrokerLib.rtrim(parsed.getString("trade_type")));
            tFnInoutRealtime.setDealStatus  (MsgBrokerLib.rtrim(parsed.getString("trade_status")));

            tFnInoutRealtime.setUpdateUid   ( "ONLINE" );
            tFnInoutRealtime.setUpdateDate  (safeData.getDSysDate());

            tFnInoutRealtimeMapper.insertSelective(tFnInoutRealtime);

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {
            isDbDupData = true;
        } catch (Exception e)
        {
            logger.info(">>> [DBInUIOutRealTime] (T_FN_INOUT_REALTIME) INSERT ERROR {}", e.getMessage());
            throw e;
        }

        if(isDbDupData) {
            logger.info("...중복요청건...");

            try
            {
                TFnInoutRealtime tFnInoutRealtime = new TFnInoutRealtime();
                tFnInoutRealtime.setDealDate    (MsgBrokerLib.rtrim(parsed.getString("trade_date")));
                tFnInoutRealtime.setDealTime    (MsgBrokerLib.rtrim(parsed.getString("trade_time")));
                tFnInoutRealtime.setDealAmt     ((parsed.getLong("trade_amt")));
                tFnInoutRealtime.setDealType    (MsgBrokerLib.rtrim(parsed.getString("trade_type")));
                tFnInoutRealtime.setDealStatus  (MsgBrokerLib.rtrim(parsed.getString("trade_status")));

                tFnInoutRealtime.setUpdateUid   (parsed.getString("ONLINE"));
                tFnInoutRealtime.setUpdateDate  (safeData.getDSysDate());

                TFnInoutRealtimeSpec tFnInoutRealtimeSpec = new TFnInoutRealtimeSpec();
                tFnInoutRealtimeSpec.createCriteria()
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andDealDateEqualTo(MsgBrokerLib.rtrim(parsed.getString("trade_date")))
                .andSeqNoEqualTo(parsed.getInt("seq_no"));

            } catch (Exception e)
            {
                logger.info(">>> [DBInUIOutRealTime] (T_FN_INOUT_REALTIME) UPDATE ERROR", e.getMessage());
                throw e;
            }

            logger.info("!!!처리완료(UPDATE)!!!");

        }

        logger.info("!!!처리완료(INSERT)!!!");
    }
}

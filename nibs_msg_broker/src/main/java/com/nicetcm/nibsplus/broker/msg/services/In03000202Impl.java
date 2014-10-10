package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnMacMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnRealtimeTradeMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnMac;
import com.nicetcm.nibsplus.broker.msg.model.TFnMacSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnRealtimeTrade;
import com.nicetcm.nibsplus.broker.msg.model.TFnRealtimeTradeSpec;

/**
 *
 * 0202 실시간거래내역(요넷) 통보
 * <pre>
 * MngCM_SaveRealTimeTrade( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000202")
public class In03000202Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000202Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnRealtimeTradeMapper tFnRealtimeTradeMapper;

    @Autowired private TFnMacMapper tFnMacMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        boolean isDbDupData = false;

        try
        {
            TFnRealtimeTrade tFnRealtimeTrade = new TFnRealtimeTrade();

            tFnRealtimeTrade.setOrgCd             (MsgBrokerLib.rtrim(parsed.getString("CM.org_cd")));
            tFnRealtimeTrade.setBranchCd          (MsgBrokerLib.rtrim(parsed.getString("brch_cd")));
            tFnRealtimeTrade.setMacNo             (MsgBrokerLib.rtrim(parsed.getString("mac_no")));
            tFnRealtimeTrade.setDealDate          (MsgBrokerLib.rtrim(parsed.getString("deal_date")));
            tFnRealtimeTrade.setDealTime          (MsgBrokerLib.rtrim(parsed.getString("deal_time")));
            tFnRealtimeTrade.setRemainAmt         (parsed.getLong("remain_amt"));
            tFnRealtimeTrade.setIn10000Remcnt     (parsed.getInt("in_10000_remcnt"));
            tFnRealtimeTrade.setIn5000Remcnt      (parsed.getInt("in_5000_remcnt"));
            tFnRealtimeTrade.setIn1000Remcnt      (parsed.getInt("in_1000_remcnt"));
            tFnRealtimeTrade.setOut10000Remcnt    (parsed.getInt("out_10000_remcnt"));
            tFnRealtimeTrade.setOut5000Remcnt     (parsed.getInt("out_5000_remcnt"));
            tFnRealtimeTrade.setOut1000Remcnt     (parsed.getInt("out_1000_remcnt"));
            tFnRealtimeTrade.setOut100Remcnt      (parsed.getInt("out_100_remcnt"));
            tFnRealtimeTrade.setIn10000Cnt        (parsed.getInt("in_10000_cnt"));
            tFnRealtimeTrade.setIn5000Cnt         (parsed.getInt("in_5000_cnt"));
            tFnRealtimeTrade.setIn1000Cnt         (parsed.getInt("in_1000_cnt"));
            tFnRealtimeTrade.setOut10000Cnt       (parsed.getInt("out_10000_cnt"));
            tFnRealtimeTrade.setOut5000Cnt        (parsed.getInt("out_5000_cnt"));
            tFnRealtimeTrade.setOut1000Cnt        (parsed.getInt("out_1000_cnt"));
            tFnRealtimeTrade.setOut100Cnt         (parsed.getInt("out_100_cnt"));
            tFnRealtimeTrade.setNoRtnAmt          (parsed.getLong("no_rtn_amt"));
            tFnRealtimeTrade.setNoRtnNo           (MsgBrokerLib.rtrim(parsed.getString("no_rtn_no")));
            tFnRealtimeTrade.setIn50000Remcnt     (parsed.getInt("in_50000_remcnt"));
            tFnRealtimeTrade.setIn50000Cnt        (parsed.getInt("in_50000_cnt"));
            tFnRealtimeTrade.setIn500Remcnt       (parsed.getInt("in_500_remcnt"));
            tFnRealtimeTrade.setIn100Remcnt       (parsed.getInt("in_100_remcnt"));
            tFnRealtimeTrade.setIn50Remcnt        (parsed.getInt("in_50_remcnt"));
            tFnRealtimeTrade.setIn10Remcnt        (parsed.getInt("in_10_remcnt"));
            tFnRealtimeTrade.setIn500Cnt          (parsed.getInt("in_500_cnt"));
            tFnRealtimeTrade.setIn100Cnt          (parsed.getInt("in_100_cnt"));
            tFnRealtimeTrade.setIn50Cnt           (parsed.getInt("in_50_cnt"));
            tFnRealtimeTrade.setIn10Cnt           (parsed.getInt("in_10_cnt"));
            tFnRealtimeTrade.setOut50000Remcnt    (parsed.getInt("out_50000_remcnt"));
            tFnRealtimeTrade.setOut50000Cnt       (parsed.getInt("out_50000_cnt"));
            tFnRealtimeTrade.setOut500Remcnt      (parsed.getInt("out_500_remcnt"));
            tFnRealtimeTrade.setOut50Remcnt       (parsed.getInt("out_50_remcnt"));
            tFnRealtimeTrade.setOut10Remcnt       (parsed.getInt("out_10_remcnt"));
            tFnRealtimeTrade.setOut500Cnt         (parsed.getInt("out_500_cnt"));
            tFnRealtimeTrade.setOut50Cnt          (parsed.getInt("out_50_cnt"));
            tFnRealtimeTrade.setOut10Cnt          (parsed.getInt("out_10_cnt"));
            tFnRealtimeTrade.setSubOrgCd          (parsed.getString("sub_org_cd"));
            tFnRealtimeTrade.setAtmDealNo         (parsed.getInt("atm_deal_no"));
            tFnRealtimeTrade.setTransDate         (parsed.getString("CM.trans_date"));
            tFnRealtimeTrade.setTransSeq          (parsed.getString("CM.trans_seq_no"));
            tFnRealtimeTrade.setUpdateUid         ("ETC_In");
            tFnRealtimeTrade.setUpdateDate        (safeData.getDSysDate());

            tFnRealtimeTradeMapper.insertSelective(tFnRealtimeTrade);

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {

            isDbDupData = true;

        } catch (Exception e)
        {
            logger.info(">>> [T_FN_REALTIME_TRADE] INSERT ERROR {}", e.getMessage());
            throw e;
        }

        if(isDbDupData) {
            try
            {
                TFnRealtimeTrade tFnRealtimeTrade = new TFnRealtimeTrade();

                tFnRealtimeTrade.setRemainAmt         (parsed.getLong("remain_amt"));
                tFnRealtimeTrade.setIn10000Remcnt     (parsed.getInt("in_10000_remcnt"));
                tFnRealtimeTrade.setIn5000Remcnt      (parsed.getInt("in_5000_remcnt"));
                tFnRealtimeTrade.setIn1000Remcnt      (parsed.getInt("in_1000_remcnt"));
                tFnRealtimeTrade.setOut10000Remcnt    (parsed.getInt("out_10000_remcnt"));
                tFnRealtimeTrade.setOut5000Remcnt     (parsed.getInt("out_5000_remcnt"));
                tFnRealtimeTrade.setOut1000Remcnt     (parsed.getInt("out_1000_remcnt"));
                tFnRealtimeTrade.setOut100Remcnt      (parsed.getInt("out_100_remcnt"));
                tFnRealtimeTrade.setIn10000Cnt        (parsed.getInt("in_10000_cnt"));
                tFnRealtimeTrade.setIn5000Cnt         (parsed.getInt("in_5000_cnt"));
                tFnRealtimeTrade.setIn1000Cnt         (parsed.getInt("in_1000_cnt"));
                tFnRealtimeTrade.setOut10000Cnt       (parsed.getInt("out_10000_cnt"));
                tFnRealtimeTrade.setOut5000Cnt        (parsed.getInt("out_5000_cnt"));
                tFnRealtimeTrade.setOut1000Cnt        (parsed.getInt("out_1000_cnt"));
                tFnRealtimeTrade.setOut100Cnt         (parsed.getInt("out_100_cnt"));
                tFnRealtimeTrade.setNoRtnAmt          (parsed.getLong("no_rtn_amt"));
                tFnRealtimeTrade.setNoRtnNo           (MsgBrokerLib.rtrim(parsed.getString("no_rtn_no")));
                tFnRealtimeTrade.setIn50000Remcnt     (parsed.getInt("in_50000_remcnt"));
                tFnRealtimeTrade.setIn50000Cnt        (parsed.getInt("in_50000_cnt"));
                tFnRealtimeTrade.setIn500Remcnt       (parsed.getInt("in_500_remcnt"));
                tFnRealtimeTrade.setIn100Remcnt       (parsed.getInt("in_100_remcnt"));
                tFnRealtimeTrade.setIn50Remcnt        (parsed.getInt("in_50_remcnt"));
                tFnRealtimeTrade.setIn10Remcnt        (parsed.getInt("in_10_remcnt"));
                tFnRealtimeTrade.setIn500Cnt          (parsed.getInt("in_500_cnt"));
                tFnRealtimeTrade.setIn100Cnt          (parsed.getInt("in_100_cnt"));
                tFnRealtimeTrade.setIn50Cnt           (parsed.getInt("in_50_cnt"));
                tFnRealtimeTrade.setIn10Cnt           (parsed.getInt("in_10_cnt"));
                tFnRealtimeTrade.setOut50000Remcnt    (parsed.getInt("out_50000_remcnt"));
                tFnRealtimeTrade.setOut50000Cnt       (parsed.getInt("out_50000_cnt"));
                tFnRealtimeTrade.setOut500Remcnt      (parsed.getInt("out_500_remcnt"));
                tFnRealtimeTrade.setOut50Remcnt       (parsed.getInt("out_50_remcnt"));
                tFnRealtimeTrade.setOut10Remcnt       (parsed.getInt("out_10_remcnt"));
                tFnRealtimeTrade.setOut500Cnt         (parsed.getInt("out_500_cnt"));
                tFnRealtimeTrade.setOut50Cnt          (parsed.getInt("out_50_cnt"));
                tFnRealtimeTrade.setOut10Cnt          (parsed.getInt("out_10_cnt"));
                tFnRealtimeTrade.setSubOrgCd          (parsed.getString("sub_org_cd"));
                tFnRealtimeTrade.setAtmDealNo         (parsed.getInt("atm_deal_no"));
                tFnRealtimeTrade.setUpdateUid         ("ETC_Up");
                tFnRealtimeTrade.setUpdateDate        (safeData.getDSysDate());

                TFnRealtimeTradeSpec tFnRealtimeTradeSpec = new TFnRealtimeTradeSpec();
                tFnRealtimeTradeSpec.createCriteria()
                .andOrgCdEqualTo(MsgBrokerLib.rtrim(parsed.getString("CM.org_cd")))
                .andBranchCdEqualTo(MsgBrokerLib.rtrim(parsed.getString("brch_cd")))
                .andMacNoEqualTo(MsgBrokerLib.rtrim(parsed.getString("mac_no")))
                .andDealDateEqualTo(MsgBrokerLib.rtrim(parsed.getString("deal_date")))
                .andDealTimeEqualTo(MsgBrokerLib.rtrim(parsed.getString("deal_time")));

                tFnRealtimeTradeMapper.updateBySpecSelective(tFnRealtimeTrade, tFnRealtimeTradeSpec);

            } catch (Exception e)
            {
                logger.info(">>> [T_FN_REALTIME_TRADE] UPDATE ERROR {}", e.getMessage());
                throw e;
            }

        }//endif

        msgTX.commit(safeData.getTXS());
        safeData.setTXS(msgTX.getTransaction(MsgBrokerTransaction.defMSGTX));

        /* ktTR 거래 전문일 경우 T_FN_MAC의 잔액을 UPDATE 해 준다. */
        if(MsgBrokerConst.TAXRF_CODE.equals(parsed.getString("CM.org_cd"))) {
            TFnMac tFnMac = new TFnMac();
            tFnMac.setInMacAmtCw14(parsed.getLong("out_10000_remcnt") * 10000);
            tFnMac.setInMacAmtCw54(parsed.getLong("out_50000_remcnt") * 50000);
            tFnMac.setInMacAmtCw11(parsed.getLong("out_10_remcnt") * 10);
            tFnMac.setInMacAmtCw51(parsed.getLong("out_50_remcnt") * 50);
            tFnMac.setInMacAmtCw12(parsed.getLong("out_100_remcnt") * 100);
            tFnMac.setInMacAmtCw52(parsed.getLong("out_500_remcnt") * 500);
            tFnMac.setInMacAmtCw13(parsed.getLong("out_1000_remcnt") * 1000);
            tFnMac.setInMacAmtCw53(parsed.getLong("out_5000_remcnt") * 5000);
            tFnMac.setInMacAmt    (
                                     parsed.getLong("out_10000_remcnt")  * 10000
                                   + parsed.getLong("out_50000_remcnt")  * 50000
                                   + parsed.getLong("out_10_remcnt")     * 10
                                   + parsed.getLong("out_50_remcnt")     * 50
                                   + parsed.getLong("out_100_remcnt")    * 100
                                   + parsed.getLong("out_500_remcnt")    * 500
                                   + parsed.getLong("out_1000_remcnt")   * 1000
                                   + parsed.getLong("out_5000_remcnt")   * 5000
                            );

            TFnMacSpec tFnMacSpec = new TFnMacSpec();
            tFnMacSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"));

            try
            {
                tFnMacMapper.updateBySpecSelective(tFnMac, tFnMacSpec);
            } catch (Exception e)
            {
                logger.info("[T_FN_MAC] Update Error {}", e.getMessage());
                msgTX.rollback(safeData.getTXS());
                safeData.setTXS(msgTX.getTransaction(MsgBrokerTransaction.defMSGTX));
                /*오류시에도 정상 처리 */
            }
        }

    }
}

package com.nicetcm.nibsplus.broker.msg.services;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnInoutOrgMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnInoutOrg;
import com.nicetcm.nibsplus.broker.msg.model.TFnInoutOrgSpec;

/**
 *
 * 0203 정산기 실시간거래내역
 * <pre>
 * MngCM_SaveCalcRealInout( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000203")
public class In03000203Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000203Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private TFnInoutOrgMapper tFnInoutOrgMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {


        boolean isDbDupData = false;

        try
        {
            TFnInoutOrg tFnInoutOrg = new TFnInoutOrg();
            tFnInoutOrg.setOrgCd            (parsed.getString("CM.org_cd"));
            tFnInoutOrg.setBranchCd         (parsed.getString("brch_cd"));
            tFnInoutOrg.setMacNo            (parsed.getString("mac_no"));

            tFnInoutOrg.setDealDate         (parsed.getString("deal_date"));
            tFnInoutOrg.setSeq              (parsed.getString("atm_deal_seqno"));
            tFnInoutOrg.setMemberId         (parsed.getString("casher_id"));
            tFnInoutOrg.setDealTime         (parsed.getString("deal_time"));
            tFnInoutOrg.setInoutGb          (MsgBrokerLib.decode(parsed.getString("in_type"), "5", "0002", "6", "0002", "7", "0002", "0001").toString()); /* 입출금구분 DB 검색해서 넣는 루틴 필요  <---INOUT_GB             */
            tFnInoutOrg.setDetailGb         (StringUtils.leftPad(parsed.getString("in_type"), 4, "0")); /* DETAIL_GB    */
            tFnInoutOrg.setInKjGb           (MsgBrokerLib.decode(parsed.getString("in_detail"), "", "", StringUtils.leftPad(parsed.getString("in_detail"), 4, "0")).toString()); /*IN_KJ_GB */

            tFnInoutOrg.setCw15Cnt(parsed.getInt("cash_cnt_100000"));
            tFnInoutOrg.setCw54Cnt(parsed.getInt("cash_cnt_50000"));
            tFnInoutOrg.setCw14Cnt(parsed.getInt("cash_cnt_10000"));
            tFnInoutOrg.setCw53Cnt(parsed.getInt("cash_cnt_5000"));
            tFnInoutOrg.setCw13Cnt(parsed.getInt("cash_cnt_1000"));
            tFnInoutOrg.setCw52Cnt(parsed.getInt("cash_cnt_500"));
            tFnInoutOrg.setCw12Cnt(parsed.getInt("cash_cnt_100"));
            tFnInoutOrg.setCw51Cnt(parsed.getInt("cash_cnt_50"));
            tFnInoutOrg.setCw11Cnt(parsed.getInt("cash_cnt_10"));
            tFnInoutOrg.setHw16Cnt(parsed.getInt("check_cnt_1000000"));
            tFnInoutOrg.setHw55Cnt(parsed.getInt("check_cnt_500000"));
            tFnInoutOrg.setHw35Cnt(parsed.getInt("check_cnt_300000"));
            tFnInoutOrg.setHw15Cnt(parsed.getInt("check_cnt_100000"));
            tFnInoutOrg.setCheckNonfixedCnt(parsed.getInt("check_cnt_nonfixed"));
            tFnInoutOrg.setCheckNonfixedAmt(parsed.getLong("check_amt_nonfixed"));
            tFnInoutOrg.setBoxCashAmt(parsed.getLong("box_cash_amt"));
            tFnInoutOrg.setCoinAmt(parsed.getLong("box_coin_amt"));
            tFnInoutOrg.setCheckAmt(parsed.getLong("box_check_amt"));
            tFnInoutOrg.setSelfTicketAmt(parsed.getLong("box_self_ticket_amt"));
            tFnInoutOrg.setEtcTicketAmt(parsed.getLong("box_etc_ticket_amt"));
            tFnInoutOrg.setCuponAmt(parsed.getLong("box_cupon_amt"));
            tFnInoutOrg.setCashCuponAmt(parsed.getLong("box_cash_cupon_amt"));
            tFnInoutOrg.setEtcInAmt(parsed.getLong("box_etc_in_amt"));
            tFnInoutOrg.setOwnTradeDate(parsed.getString("own_trade_date"));
            tFnInoutOrg.setOwnTradeSeq(parsed.getString("own_trade_seq"));
            tFnInoutOrg.setOwnTradeMacNo(parsed.getString("own_trade_mac_no"));
            tFnInoutOrg.setGoodTeleNo(parsed.getString("good_tele_no"));
            tFnInoutOrg.setRateCuponCnt(parsed.getInt("box_rate_cupon_cnt"));   /* 정률할인권 매수*/
            tFnInoutOrg.setTicketAmt(parsed.getLong("ticket_amt"));             /* 상품권기기입금 */
            tFnInoutOrg.setDollarAmt(parsed.getLong("box_dollar_amt"));         /* 달러 금액      */
            tFnInoutOrg.setEuroAmt(parsed.getLong("box_euro_amt"));             /* 유로 금액      */
            tFnInoutOrg.setYenAmt(parsed.getLong("box_yen_amt"));               /* 엔화 금액      */
            tFnInoutOrg.setYuanAmt(parsed.getLong("box_yuan_amt"));             /* 위안화 금액     */
            tFnInoutOrg.setUpdateDate       (safeData.getDSysDate());
            tFnInoutOrg.setUpdateUid        ("online");

            tFnInoutOrgMapper.insertSelective(tFnInoutOrg);

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {
            isDbDupData = true;

        } catch (Exception e)
        {
            logger.info(">>> [MngCM_SaveCalcRealInout] (T_FN_INOUT) Insert ERROR {}", e.getMessage());
            throw e;
        }

        if(isDbDupData) {
            logger.info("...중복요청건...");

            TFnInoutOrg tFnInoutOrg = new TFnInoutOrg();
            tFnInoutOrg.setMemberId         (parsed.getString("casher_id"));
            tFnInoutOrg.setDealTime         (parsed.getString("deal_time"));
            tFnInoutOrg.setInoutGb          (MsgBrokerLib.decode(parsed.getString("in_type"), "5", "0002", "6", "0002", "7", "0002", "0001").toString()); /* 입출금구분 DB 검색해서 넣는 루틴 필요  <---INOUT_GB             */
            tFnInoutOrg.setDetailGb         (StringUtils.leftPad(parsed.getString("in_type"), 4, "0")); /* DETAIL_GB    */
            tFnInoutOrg.setInKjGb           (MsgBrokerLib.decode(parsed.getString("in_detail"), "", "", StringUtils.leftPad(parsed.getString("in_detail"), 4, "0")).toString()); /*IN_KJ_GB */

            tFnInoutOrg.setCw15Cnt(parsed.getInt("cash_cnt_100000"));
            tFnInoutOrg.setCw54Cnt(parsed.getInt("cash_cnt_50000"));
            tFnInoutOrg.setCw14Cnt(parsed.getInt("cash_cnt_10000"));
            tFnInoutOrg.setCw53Cnt(parsed.getInt("cash_cnt_5000"));
            tFnInoutOrg.setCw13Cnt(parsed.getInt("cash_cnt_1000"));
            tFnInoutOrg.setCw52Cnt(parsed.getInt("cash_cnt_500"));
            tFnInoutOrg.setCw12Cnt(parsed.getInt("cash_cnt_100"));
            tFnInoutOrg.setCw51Cnt(parsed.getInt("cash_cnt_50"));
            tFnInoutOrg.setCw11Cnt(parsed.getInt("cash_cnt_10"));
            tFnInoutOrg.setHw16Cnt(parsed.getInt("check_cnt_1000000"));
            tFnInoutOrg.setHw55Cnt(parsed.getInt("check_cnt_500000"));
            tFnInoutOrg.setHw35Cnt(parsed.getInt("check_cnt_300000"));
            tFnInoutOrg.setHw15Cnt(parsed.getInt("check_cnt_100000"));
            tFnInoutOrg.setCheckNonfixedCnt(parsed.getInt("check_cnt_nonfixed"));
            tFnInoutOrg.setCheckNonfixedAmt(parsed.getLong("check_amt_nonfixed"));
            tFnInoutOrg.setBoxCashAmt(parsed.getLong("box_cash_amt"));
            tFnInoutOrg.setCoinAmt(parsed.getLong("box_coin_amt"));
            tFnInoutOrg.setCheckAmt(parsed.getLong("box_check_amt"));
            tFnInoutOrg.setSelfTicketAmt(parsed.getLong("box_self_ticket_amt"));
            tFnInoutOrg.setEtcTicketAmt(parsed.getLong("box_etc_ticket_amt"));
            tFnInoutOrg.setCuponAmt(parsed.getLong("box_cupon_amt"));
            tFnInoutOrg.setCashCuponAmt(parsed.getLong("box_cash_cupon_amt"));
            tFnInoutOrg.setEtcInAmt(parsed.getLong("box_etc_in_amt"));
            tFnInoutOrg.setOwnTradeDate(parsed.getString("own_trade_date"));
            tFnInoutOrg.setOwnTradeSeq(parsed.getString("own_trade_seq"));
            tFnInoutOrg.setOwnTradeMacNo(parsed.getString("own_trade_mac_no"));
            tFnInoutOrg.setGoodTeleNo(parsed.getString("good_tele_no"));
            tFnInoutOrg.setRateCuponCnt(parsed.getInt("box_rate_cupon_cnt"));   /* 정률할인권 매수*/
            tFnInoutOrg.setTicketAmt(parsed.getLong("ticket_amt"));             /* 상품권기기입금 */
            tFnInoutOrg.setUpdateDate       (safeData.getDSysDate());
            tFnInoutOrg.setUpdateUid        ("online");

            TFnInoutOrgSpec tFnInoutOrgSpec = new TFnInoutOrgSpec();
            tFnInoutOrgSpec.createCriteria()
            .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
            .andBranchCdEqualTo(parsed.getString("brch_cd"))
            .andMacNoEqualTo(parsed.getString("mac_no"))
            .andDealDateEqualTo(parsed.getString("deal_date"))
            .andSeqEqualTo(parsed.getString("atm_deal_seqno"));

            try
            {
                tFnInoutOrgMapper.updateBySpecSelective(tFnInoutOrg, tFnInoutOrgSpec);
            } catch (Exception e)
            {
                logger.info(">>> [MngCM_SaveCalcRealInout] (T_FN_INOUT) UPDATE ERROR [{}]", e.getMessage());
                throw e;
            }

            logger.info("!!!처리완료(UPDATE)!!!");

        }

        logger.info("!!!처리완료(INSERT)!!!");
    }
}

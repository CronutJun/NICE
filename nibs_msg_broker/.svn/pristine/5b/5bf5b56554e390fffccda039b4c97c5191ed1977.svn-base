package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.TFnChalsiDealDsumMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.TFnChalsiDealDsum;
import com.nicetcm.nibsplus.broker.msg.model.TFnChalsiDealDsumSpec;
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

    @Autowired private TFnChalsiDealDsumMapper tFnChalsiDealDsumMapper;

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

        boolean isDbDupData = false;

        try
        {
            /* 일마감일 경우 거래일자 + 1 로, 회수일 경우 거래일자 그대로 db에 insert => 20080310 */

            TFnChalsiDealDsum tFnChalsiDealDsum = new TFnChalsiDealDsum();
            tFnChalsiDealDsum.setDealDate     ( (parsed.getString("close_type").equals("01") ? MsgBrokerLib.SysDate(parsed.getString("deal_date"), 1) : parsed.getString("deal_date")) );
            tFnChalsiDealDsum.setDealTime     (parsed.getString("CM.trans_time"));
            tFnChalsiDealDsum.setOrgCd        (parsed.getString("CM.org_cd"));
            tFnChalsiDealDsum.setBranchCd     (parsed.getString("brch_cd"));
            tFnChalsiDealDsum.setMacNo        (parsed.getString("mac_no"));
            tFnChalsiDealDsum.setCloseTime    (parsed.getString("close_time"));
            tFnChalsiDealDsum.setCloseType    (parsed.getString("close_type"));

            tFnChalsiDealDsum.setCw11Cnt      (parsed.getInt("cash_10_cnt"));
            tFnChalsiDealDsum.setCw12Cnt      (parsed.getInt("cash_100_cnt"));
            tFnChalsiDealDsum.setCw13Cnt      (parsed.getInt("cash_1000_cnt"));
            tFnChalsiDealDsum.setCw14Cnt      (parsed.getInt("cash_10000_cnt"));
            tFnChalsiDealDsum.setCw15Cnt      (parsed.getInt("cash_100000_cnt"));
            tFnChalsiDealDsum.setCw51Cnt      (parsed.getInt("cash_50_cnt"));
            tFnChalsiDealDsum.setCw52Cnt      (parsed.getInt("cash_500_cnt"));
            tFnChalsiDealDsum.setCw53Cnt      (parsed.getInt("cash_5000_cnt"));
            tFnChalsiDealDsum.setCw54Cnt      (parsed.getInt("cash_50000_cnt"));
            tFnChalsiDealDsum.setHw15Cnt      (parsed.getInt("check_10_cnt"));
            tFnChalsiDealDsum.setHw16Cnt      (parsed.getInt("check_100_cnt"));
            tFnChalsiDealDsum.setHw35Cnt      (parsed.getInt("check_30_cnt"));
            tFnChalsiDealDsum.setHw55Cnt      (parsed.getInt("check_50_cnt"));

            tFnChalsiDealDsum.setCheckEtcAmt  (parsed.getLong("check_etc_amt"));
            tFnChalsiDealDsum.setBoxCashAmt   (parsed.getLong("box_cash_amt"));
            tFnChalsiDealDsum.setBoxCoinAmt   (parsed.getLong("box_coin_amt"));
            tFnChalsiDealDsum.setBoxCheckAmt  (parsed.getLong("box_check_amt"));
            tFnChalsiDealDsum.setBoxIncomAmt  (parsed.getLong("box_incom_amt"));
            tFnChalsiDealDsum.setBoxOutcomAmt (parsed.getLong("box_outcom_amt"));
            tFnChalsiDealDsum.setBoxPpcardAmt (parsed.getLong("box_ppcard_amt"));
            tFnChalsiDealDsum.setInTicketAmt  (parsed.getLong("self_tot_amt"));
            tFnChalsiDealDsum.setOutTicketAmt (parsed.getLong("etc_tot_amt"));
            tFnChalsiDealDsum.setInsertDate   (safeData.getDSysDate());
            tFnChalsiDealDsum.setInsertUid    ("ETCIn");

            tFnChalsiDealDsumMapper.insertSelective(tFnChalsiDealDsum);

        } catch( org.springframework.dao.DataIntegrityViolationException e ) {

            isDbDupData = true;

        } catch (Exception e)
        {
            logger.info(">>> [T_FN_CHALSI_DEAL_DSUM] INSERT ERROR {}", e.getMessage());
            throw e;
        }

        if(isDbDupData) {
            try
            {
                TFnChalsiDealDsum tFnChalsiDealDsum = new TFnChalsiDealDsum();
                tFnChalsiDealDsum.setCw11Cnt      (parsed.getInt("cash_10_cnt"));
                tFnChalsiDealDsum.setCw12Cnt      (parsed.getInt("cash_100_cnt"));
                tFnChalsiDealDsum.setCw13Cnt      (parsed.getInt("cash_1000_cnt"));
                tFnChalsiDealDsum.setCw14Cnt      (parsed.getInt("cash_10000_cnt"));
                tFnChalsiDealDsum.setCw15Cnt      (parsed.getInt("cash_100000_cnt"));
                tFnChalsiDealDsum.setCw51Cnt      (parsed.getInt("cash_50_cnt"));
                tFnChalsiDealDsum.setCw52Cnt      (parsed.getInt("cash_500_cnt"));
                tFnChalsiDealDsum.setCw53Cnt      (parsed.getInt("cash_5000_cnt"));
                tFnChalsiDealDsum.setCw54Cnt      (parsed.getInt("cash_50000_cnt"));
                tFnChalsiDealDsum.setHw15Cnt      (parsed.getInt("check_10_cnt"));
                tFnChalsiDealDsum.setHw16Cnt      (parsed.getInt("check_100_cnt"));
                tFnChalsiDealDsum.setHw35Cnt      (parsed.getInt("check_30_cnt"));
                tFnChalsiDealDsum.setHw55Cnt      (parsed.getInt("check_50_cnt"));
                tFnChalsiDealDsum.setCheckEtcAmt  (parsed.getLong("check_etc_amt"));
                tFnChalsiDealDsum.setBoxCashAmt   (parsed.getLong("box_cash_amt"));
                tFnChalsiDealDsum.setBoxCoinAmt   (parsed.getLong("box_coin_amt"));
                tFnChalsiDealDsum.setBoxCheckAmt  (parsed.getLong("box_check_amt"));
                tFnChalsiDealDsum.setBoxIncomAmt  (parsed.getLong("box_incom_amt"));
                tFnChalsiDealDsum.setBoxOutcomAmt (parsed.getLong("box_outcom_amt"));
                tFnChalsiDealDsum.setBoxPpcardAmt (parsed.getLong("box_ppcard_amt"));
                tFnChalsiDealDsum.setInTicketAmt  (parsed.getLong("self_tot_amt"));
                tFnChalsiDealDsum.setOutTicketAmt (parsed.getLong("etc_tot_amt"));

                tFnChalsiDealDsum.setInsertDate   (safeData.getDSysDate());
                tFnChalsiDealDsum.setInsertUid    ("ETCUp");

                TFnChalsiDealDsumSpec tFnChalsiDealDsumSpec = new TFnChalsiDealDsumSpec();
                tFnChalsiDealDsumSpec.createCriteria()
                .andDealDateEqualTo(parsed.getString("deal_date"))
                .andDealTimeEqualTo(parsed.getString("CM.trans_time"))
                .andOrgCdEqualTo(parsed.getString("CM.org_cd"))
                .andBranchCdEqualTo(parsed.getString("brch_cd"))
                .andMacNoEqualTo(parsed.getString("mac_no"))
                .andMacNoEqualTo(parsed.getString("close_time"))
                .andMacNoEqualTo(parsed.getString("close_type"));

                tFnChalsiDealDsumMapper.updateBySpecSelective(tFnChalsiDealDsum, tFnChalsiDealDsumSpec);

            } catch (Exception e)
            {
                logger.info(">>> [T_FN_CHALSI_DEAL_DSUM] UPDATE ERROR {}", e.getMessage());
                throw e;
            }
        }//endif

        logger.info("[T_FN_CHALSI_DEAL_DSUM] Save OK");
    }
}

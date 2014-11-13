package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.IfCashInsert;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 1220 지점별 기기마감 일괄조회
 * <pre>
 * MngCM_AP_SaveJumCloseAmt( pRecvData, nLen );
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101220")
public class In03101220Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101220Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        if(parsed.getString("close_date").equals("")) {

            /* 마감일자가 없다면 조회일자로 넣어준다 */
            parsed.setString("close_date", parsed.getString("inq_date"));
        }

        if( MsgBrokerConst.WRATMS_CODE.equals(parsed.getString("CM.org_cd")) && (parsed.getString("close_time").equals("") || parsed.getString("close_time").equals("000000")) ) {
            throw new Exception("우리은행 마감시간 없음");
        } else if(parsed.getString("close_time").equals("")) {
            try { parsed.setString("close_time", "000000"); } catch ( Exception e ) {}
        }

        /* 총지급액이 있으면 총지급액 그대로, 총지급액이 없으면 현금 지급 + 수표 지급 */
        /* 현금입지급액 란은 지운다.   - 자금팀 요청                                */
        if(parsed.getLong("total_out_amt") == 0) {
            parsed.setLong("total_out_amt", parsed.getLong("cash_out_amt") + parsed.getLong("check_out_amt"));
        }

        if(parsed.getLong("total_in_amt") == 0) {
            parsed.setLong("total_in_amt", parsed.getLong("cash_in_amt") + parsed.getLong("check_in_amt"));
        }

        parsed.setLong("cash_out_amt", 0);
        parsed.setLong("cash_in_amt", 0);

        String hcashType = "2";

        comPack.checkBranchMacLength( parsed );

        TMisc tMisc = new TMisc();
        tMisc.sethCashType(hcashType);
        tMisc.setInqDate(parsed.getString("close_date"));
        tMisc.setOrgCd(parsed.getString("CM.org_cd"));
        tMisc.setBranchCd(parsed.getString("brch_cd"));
        tMisc.setMacNo(parsed.getString("mac_no"));
        tMisc.sethRtnVal(-1);

        splMap.spIfGetpreamt(tMisc);

        if(tMisc.gethRtnVal() != 0) {
            throw new Exception( String.format("[MngCM_AP_SaveJumCloseAmt] 프로시져 오류 1: [%s]", tMisc.gethRtnMsg()));
        }

        /* 현금 잔액이 없는 경우는 장전금액에서 계산해 준다. */
        if( parsed.getLong("cash_remain_amt") == 0) {
            parsed.setLong("cash_remain_amt", tMisc.gethPreAmt() + parsed.getLong("cash_in_amt") - parsed.getLong("cash_out_amt"));
        }

        IfCashInsert ifCashInsert = new IfCashInsert();

        ifCashInsert.setnUpdateCheckYN    (0);
        ifCashInsert.setpOrgCd            (parsed.getString("CM.org_cd"));
        ifCashInsert.setpJijumCd          (parsed.getString("brch_cd"));
        ifCashInsert.setpMacNo            (parsed.getString("mac_no"));
        ifCashInsert.setpCashType         (hcashType);
        ifCashInsert.setpCashDate         (parsed.getString("close_date"));
        ifCashInsert.setpCashTime         (parsed.getString("close_time"));
        ifCashInsert.setpChargeAmt        (tMisc.gethPreAmt());
        ifCashInsert.setpTotInAmt         (parsed.getLong("total_in_amt"));
        ifCashInsert.setpTotOutAmt        (parsed.getLong("total_out_amt"));
        ifCashInsert.setpMoneyInAmt       (parsed.getLong("cash_in_amt"));
        ifCashInsert.setpMoneyOutAmt      (parsed.getLong("cash_out_amt"));
        ifCashInsert.setpCheckInAmt       (parsed.getLong("check_in_amt"));
        ifCashInsert.setpCheckOutAmt      (parsed.getLong("check_out_amt"));
        ifCashInsert.setpRemainAmt        (parsed.getLong("cash_remain_amt"));
        ifCashInsert.setpInCnt            (0);
        ifCashInsert.setpOutCnt           (0);
        ifCashInsert.setpChkInCnt         (0);
        ifCashInsert.setpChkOutCnt        (0);
        ifCashInsert.setpAddAmt           (0);
        ifCashInsert.setpCollectAmt       (0);
        ifCashInsert.setpRemainCheckAmt   (0);
        ifCashInsert.setpRemain10Amt      (0);
        ifCashInsert.setpRemain50Amt      (0);
        ifCashInsert.setpRemain100Amt     (0);
        ifCashInsert.setpRemain500Amt     (0);
        ifCashInsert.setpRemain1000Amt    (0);
        ifCashInsert.setpRemain5000Amt    (0);
        ifCashInsert.setpRemain10000Amt   (0);
        ifCashInsert.setpRemain50000Amt   (0);
        ifCashInsert.setpMoneyIn50000Amt  (0);
        ifCashInsert.setpMoneyOut50000Amt (0);
        ifCashInsert.setpMoneyIn5000Amt   (0);
        ifCashInsert.setpMoneyIn1000Amt   (0);
        ifCashInsert.setpTodayChargeAmt   (0);
        ifCashInsert.setpPreChargeAmt     (0);
        ifCashInsert.setpPreAddAmt        (0);
        ifCashInsert.setpHolyAddAmt       (0);
        ifCashInsert.setpTodayAddAmt      (0);
        ifCashInsert.setpSafeNo           ("");
        ifCashInsert.setvFirstInqYN       (0);
        ifCashInsert.setvResult           (-1);

        splMap.spIfCashinsert(ifCashInsert);

        if(ifCashInsert.getvResult() != 0) {
            throw new MsgBrokerException(String.format("[MngCM_AP_SaveJumCloseAmt] 프로시져 오류 2: %s ", ifCashInsert.getvResultMsg()));
        }

        logger.warn(String.format("지점별 마감 조회 저장[%s]", ifCashInsert.getvResultMsg()));

    }//end method
}

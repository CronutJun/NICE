package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.model.FnMacClose;
import com.nicetcm.nibsplus.broker.msg.model.IfCashInsert;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 여기에 클래스(한글)명.
 * <pre>
 * MngCM_SaveRealCloseAmt( char * pRecvData, int nRecvLen )
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03000131")
public class In03000131Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03000131Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        if( MsgBrokerConst.TAXRF_CODE.equals(parsed.getString("CM.org_cd")) && parsed.getString("oper_type").startsWith("A")) {
            logger.warn("KTTR 추가현송 수신");
            return;
        }

        if(parsed.getString("close_date").equals("")) {
            /* 마감일자가 없다면 조회일자로 넣어준다 */
            parsed.setString("close_date", safeData.getSysDate());
        }

        if( MsgBrokerConst.WRATMS_CODE.equals(parsed.getString("CM.org_cd")) && (parsed.getString("close_time").equals("") || parsed.getString("close_time").equals("000000")) ) {
            throw new Exception("우리은행 마감시간 없음");
        } else if(parsed.getString("close_time").equals("")) {
            parsed.setString("close_time", "000000");
        }

        if(parsed.getLong("total_out_amt") == 0) {
            parsed.setLong("total_out_amt", parsed.getLong("cash_out_amt") + parsed.getLong("check_out_amt"));
        }

        /* 실시간 마감통보에는 총 입금액이 안들어온다. */
        /* 20100830 농협은 총입금금액이 들어온다. 총입금금액으로 넣어준다.  0으로 들어오는 경우도 있다. */
        /* 기관에 따라 권종별로 주면서 현금입금액에 만원권을 주던지 권종별 모든것을 합산한 것을 현금입금액에
        주는지 체크해 봐야 한다. */
        if( parsed.getLong("total_in_amt") == 0 ) {
            parsed.setLong("total_in_amt", parsed.getLong("cash_in_amt") + parsed.getLong("check_in_amt"));
        }

        parsed.setLong("cash_out_amt", 0);
        parsed.setLong("cash_in_amt", 0);

        /* 마감시재는 시재구분 2 */
        String hcashType = "2";

        /* 광주은행은 기관으에서 들어올때는 4-4 자리, NIBS 3-3 자리이므로 */
        if( MsgBrokerConst.KJB_CODE.equals(parsed.getString("CM.org_cd")) ){
            try{ comPack.checkBranchMacLength( parsed ); } catch( Exception e ) {}
        }

        TMisc tMisc = new TMisc();
        tMisc.sethCashType(hcashType);
        tMisc.setInqDate(parsed.getString("close_date"));
        tMisc.setOrgCd(parsed.getString("CM.org_cd"));
        tMisc.setBranchCd(parsed.getString("brch_cd"));
        tMisc.setMacNo(parsed.getString("mac_no"));
        tMisc.sethRtnVal(-1);

        splMap.spIfGetpreamt(tMisc);

        if(tMisc.gethRtnVal() != 0) {
            throw new Exception( String.format("[MngCM_SaveRealCloseAmt] 프로시져 오류 1: [%s]", tMisc.gethRtnMsg()));
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
            throw new MsgBrokerException(String.format("[MngCM_SaveRealCloseAmt] 프로시져 오류 2: %s ", ifCashInsert.getvResultMsg()));
        } else {

            if(MsgBrokerConst.NONGH_CODE.equals(parsed.getString("CM.org_cd"))) {

                /* 농협일 경우 정희성 요청에 의해 T_CM_CASH에 쌓은 후 별도의 프로시져 호출 하도록 함. 20100920 */
                FnMacClose fnMacClose = new FnMacClose();
                fnMacClose.setCloseDate(parsed.getString("close_date"));
                fnMacClose.setOrgCode  (parsed.getString("CM.org_cd"));
                fnMacClose.setJijumCode(parsed.getString("brch_cd"));
                fnMacClose.setMacNo    (parsed.getString("mac_no"));
                fnMacClose.setUserId   (parsed.getString("CM.msg_id"));

                splMap.spFnMacCloseNh(fnMacClose);

                logger.warn("[sp_fn_macClose_nh] 프로시져 결과: {}", fnMacClose.getResult());

                if("OK".equals(fnMacClose.getResult())) {
                    msgTX.commit(safeData.getTXS());
                    safeData.setTXS(msgTX.getTransaction( MsgBrokerTransaction.defMSGTX ));

                } else {
                    msgTX.rollback(safeData.getTXS());
                    safeData.setTXS(msgTX.getTransaction( MsgBrokerTransaction.defMSGTX ));
                }
            }
        }
    }
}

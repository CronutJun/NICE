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
import com.nicetcm.nibsplus.broker.msg.mapper.TMiscMapper;
import com.nicetcm.nibsplus.broker.msg.model.CloseAmt;
import com.nicetcm.nibsplus.broker.msg.model.IfCashInsert;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;

/**
 *
 * 현재시재조회
 * <pre>
 * MngCM_AP_SaveCurrentAmt
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
@Service("in03101110")
public class In03101110Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03101110Impl.class);

    @Autowired private StoredProcMapper storedProcMapper;

    @Autowired private TMiscMapper tMiscMapper;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        logger.debug("Msg Received");
        logger.debug(parsed.getString("CM.work_type"));



        /**************************************************************************************************
         농협atms는 조회일자 조회시간이 전문상에 없으므로 현재 시간을 세팅하도록 한다.
        ***************************************************************************************************/

        parsed.setString("inq_date", safeData.getSysDate());
        parsed.setString("inq_time", safeData.getSysTime());

        /**
         * 지점코드, 기번 길이 검증
         */
        try { comPack.checkBranchMacLength( parsed ); } catch( Exception e ) {}

        TMisc tMisc = new TMisc();
        tMisc.sethCashType("1");
        tMisc.setInqDate(parsed.getString("inq_date"));
        tMisc.setOrgCd(parsed.getString("CM.org_cd"));
        tMisc.setBranchCd(parsed.getString("brch_cd"));
        tMisc.setMacNo(parsed.getString("mac_no"));
        tMisc.sethRtnVal(-1);

        logger.debug(tMisc.toString());

        storedProcMapper.spIfGetpreamt(tMisc);

        logger.debug(tMisc.toString());

        if(tMisc.gethRtnVal() != 0) {
            throw new Exception( String.format("[MngCM_AP_SaveCurrentAmt] 프로시져 오류 1: [%s]", tMisc.gethRtnMsg()));
        }


        long hRemain10Amt    = parsed.getLong("rem_10_cnt") * 10;
        long hRemain50Amt    = parsed.getLong("rem_50_cnt") * 50;
        long hRemain100Amt   = parsed.getLong("rem_100_cnt") * 100;
        long hRemain500Amt   = parsed.getLong("rem_500_cnt") * 500;
        long hRemain1000Amt  = parsed.getLong("rem_1000_cnt") * 1000;
        long hRemain5000Amt  = parsed.getLong("rem_5000_cnt") * 5000;
        long hRemain10000Amt = parsed.getLong("rem_10000_cnt") * 10000;
        long hRemain50000Amt = parsed.getLong("rem_50000_cnt") * 50000;

        long hRemIn10Amt     = parsed.getLong("in_10_cnt") * 10;
        long hRemIn50Amt     = parsed.getLong("in_50_cnt") * 50;
        long hRemIn100Amt    = parsed.getLong("in_100_cnt") * 100;
        long hRemIn500Amt    = parsed.getLong("in_500_cnt") * 500;
        long hRemIn1000Amt   = parsed.getLong("in_1000_cnt") * 1000;
        long hRemIn5000Amt   = parsed.getLong("in_5000_cnt") * 5000;
        long hRemIn10000Amt  = parsed.getLong("in_10000_cnt") * 10000;
        long hRemIn50000Amt  = parsed.getLong("in_50000_cnt") * 50000;



        /***************************************************************************************************
         이마트일경우 다른 테이블 이용
        ***************************************************************************************************/
        if(MsgBrokerConst.EMART_CODE.equals(parsed.getString("CM.org_cd"))) {

            IfCashInsertEmart ifCashInsertEmart = new IfCashInsertEmart();
            ifCashInsertEmart.setnUpdateCheckYN   (0);
            ifCashInsertEmart.setpOrgCd           (parsed.getString("CM.org_cd"));
            ifCashInsertEmart.setpJijumCd         (parsed.getString("brch_cd"));
            ifCashInsertEmart.setpMacNo           (parsed.getString("mac_no"));
            ifCashInsertEmart.setpCashType        (tMisc.gethCashType());
            ifCashInsertEmart.setpCashDate        (parsed.getString("inq_date"));
            ifCashInsertEmart.setpCashTime        (parsed.getString("inq_time"));

            ifCashInsertEmart.setpRemIn10Amt      (hRemain10Amt        );
            ifCashInsertEmart.setpRemIn50Amt      (hRemain50Amt        );
            ifCashInsertEmart.setpRemIn100Amt     (hRemain100Amt       );
            ifCashInsertEmart.setpRemIn500Amt     (hRemain500Amt       );
            ifCashInsertEmart.setpRemIn1000Amt    (hRemain1000Amt      );
            ifCashInsertEmart.setpRemIn5000Amt    (hRemain5000Amt      );
            ifCashInsertEmart.setpRemIn10000Amt   (hRemain10000Amt     );
            ifCashInsertEmart.setpRemIn50000Amt   (hRemain50000Amt     );
            ifCashInsertEmart.setpRemOut10Amt     (hRemIn10Amt         );
            ifCashInsertEmart.setpRemOut50Amt     (hRemIn50Amt         );
            ifCashInsertEmart.setpRemOut100Amt    (hRemIn100Amt        );
            ifCashInsertEmart.setpRemOut500Amt    (hRemIn500Amt        );
            ifCashInsertEmart.setpRemOut1000Amt   (hRemIn1000Amt       );
            ifCashInsertEmart.setpRemOut5000Amt   (hRemIn5000Amt       );
            ifCashInsertEmart.setpRemOut10000Amt  (hRemIn10000Amt      );
            ifCashInsertEmart.setpRemOut50000Amt  (hRemIn50000Amt      );

            ifCashInsertEmart.setvFirstInqYN      (0);
            ifCashInsertEmart.setvResult          (-1);

            storedProcMapper.spIfCashinsertEmart(ifCashInsertEmart);

            if(ifCashInsertEmart.getvResult() != 0) {
                throw new MsgBrokerException(String.format("[sp_if_CashInsert_EMART] 프로시져 오류 2: %s ", ifCashInsertEmart.getvResultMsg()), -99);
            }
        }
        /***************************************************************************************************
         이마트 외, 다른 기관
        ***************************************************************************************************/
        else {
            /*************************************************************************************************
             [새마을금고] 이거나 20080403
             [대구은행] 이거나 20090811
             현금 잔액이 없는 경우는 장전금액에서 계산해 준다.
             [경남은행] 시재조회출처구분이 '1'(ATMS 인경우도) --> 경남은행은 무조건 장전금액에서 계산하도록 2014.10.06

             if( strcmp(suBody.cash_remain_amt, "" ) == 0 )
            ***************************************************************************************************/

            if(parsed.getString("cash_remain_amt").equals("") ||
               MsgBrokerConst.KFCC_CODE.equals(parsed.getString("CM.org_cd")) ||
               MsgBrokerConst.DGB_CODE.equals(parsed.getString("CM.org_cd")) ||
            (  MsgBrokerConst.KNATMS_CODE.equals(parsed.getString("CM.org_cd")) /* && parsed.getString("inq_source").equals("1")*/) ) {

                parsed.setLong("cash_remain_amt", 0);
                /**************************************************************************************************
                 주기현송에 따른 잔액 계산법 변경 20111103. 정희성 요청
                 정규현송이 있다면 기존과 동일 (현금잔액 = 장전(직전)금액 + 현금입금액 - 현금지급액)
                 정규현송이 없다면 마감조회 검색하여
                        마감기기 라면  (현금잔액 =장전(직전)금액 - 마감지급 + 마감입금 + 현금입금액 - 현금지급액)
                        미마감기기라면 기존과 동일 (현금잔액 = 장전(직전)금액 + 현금입금액 - 현금지급액)
                ***************************************************************************************************/

                CloseAmt closeAmt;

                try
                {
                    TMacInfo tMacInfo = new TMacInfo();
                    tMacInfo.setOrgCd( parsed.getString("CM.org_cd") );
                    tMacInfo.setBranchCd( parsed.getString("brch_cd") );
                    tMacInfo.setMacNo( parsed.getString("mac_no") );
                    closeAmt = tMiscMapper.selectCloseAmt(tMacInfo);
                } catch (Exception e)
                {
                    logger.warn(">>> [T_CM_CASH] ERROR");
                    throw e;
                }

                if(closeAmt == null)
                {
                    /**************************************************************************************************
                     미현송 미마감기기 혹은 정규현송 기기의 경우 기존 식 따름
                    ***************************************************************************************************/
                    closeAmt = new CloseAmt();
                }


                /**************************************************************************************************
                 현금잔액 = 장전(직전)금액 + 현금입금액 - 현금지급액
                ***************************************************************************************************/
                parsed.setLong("cash_remain_amt", tMisc.gethPreAmt() + parsed.getLong("cash_in_amt") - parsed.getLong("cash_out_amt") - closeAmt.getCloseOutAmt() + closeAmt.getCloseInAmt());

            }//endif

            IfCashInsert ifCashInsert = new IfCashInsert();

            ifCashInsert.setnUpdateCheckYN    (0);
            ifCashInsert.setpOrgCd            (parsed.getString("CM.org_cd"));
            ifCashInsert.setpJijumCd          (parsed.getString("brch_cd"));
            ifCashInsert.setpMacNo            (parsed.getString("mac_no"));
            ifCashInsert.setpCashType         (tMisc.gethCashType());
            ifCashInsert.setpCashDate         (parsed.getString("inq_date"));
            ifCashInsert.setpCashTime         (parsed.getString("inq_time"));
            ifCashInsert.setpChargeAmt        (tMisc.gethPreAmt());
            ifCashInsert.setpTotInAmt         (0);
            ifCashInsert.setpTotOutAmt        (parsed.getLong("total_out_amt"));
            ifCashInsert.setpMoneyInAmt       (parsed.getLong("cash_in_amt"));
            ifCashInsert.setpMoneyOutAmt      (parsed.getLong("cash_out_amt"));
            ifCashInsert.setpCheckInAmt       (parsed.getLong("check_in_amt"));
            ifCashInsert.setpCheckOutAmt      (parsed.getLong("check_out_amt"));
            ifCashInsert.setpRemainAmt        (parsed.getLong("cash_remain_amt"));
            ifCashInsert.setpInCnt            (0);
            ifCashInsert.setpOutCnt           (0);
            ifCashInsert.setpChkInCnt         (parsed.getLong("check_in_cnt"));
            ifCashInsert.setpChkOutCnt        (parsed.getLong("check_out_cnt"));
            ifCashInsert.setpAddAmt           (0);
            ifCashInsert.setpCollectAmt       (0);
            ifCashInsert.setpRemainCheckAmt   (parsed.getLong("check_amt"));
            ifCashInsert.setpRemain10Amt      (hRemain10Amt);
            ifCashInsert.setpRemain50Amt      (hRemain50Amt);
            ifCashInsert.setpRemain100Amt     (hRemain100Amt);
            ifCashInsert.setpRemain500Amt     (hRemain500Amt);
            ifCashInsert.setpRemain1000Amt    (hRemain1000Amt);
            ifCashInsert.setpRemain5000Amt    (hRemain5000Amt);
            ifCashInsert.setpRemain10000Amt   (hRemain10000Amt);
            ifCashInsert.setpRemain50000Amt   (hRemain50000Amt);
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

            storedProcMapper.spIfCashinsert(ifCashInsert);

            if(ifCashInsert.getvResult() != 0) {
                throw new MsgBrokerException(String.format("[MngCM_AP_SaveCurrentAmt] 프로시져 오류 2: %s ", ifCashInsert.getvResultMsg()), -99);
            }

            /**************************************************************************************************
             현재시재의 마감조회 구분이 "1" 이라면 수표 잔액을 마감시재에 저장해 주고
             마감조회 후 자동 전송 건이므로 AP로 응답전송하지 않는다.
            ***************************************************************************************************/

            if(parsed.getString("inq_close_yn").equals("1")) {
                String hCASH_TYPE = "2";

                IfCashInsert ifCashInsert2 = new IfCashInsert();

                ifCashInsert2.setnUpdateCheckYN    (1);
                ifCashInsert2.setpOrgCd            (parsed.getString("CM.org_cd"));
                ifCashInsert2.setpJijumCd          (parsed.getString("brch_cd"));
                ifCashInsert2.setpMacNo            (parsed.getString("mac_no"));
                ifCashInsert2.setpCashType         (tMisc.gethCashType());
                ifCashInsert2.setpCashDate         (parsed.getString("inq_date"));
                ifCashInsert2.setpCashTime         (parsed.getString("inq_time"));
                ifCashInsert2.setpChargeAmt        (tMisc.gethPreAmt());
                ifCashInsert2.setpTotInAmt         (0);
                ifCashInsert2.setpTotOutAmt        (parsed.getLong("total_out_amt"));
                ifCashInsert2.setpMoneyInAmt       (parsed.getLong("cash_in_amt"));
                ifCashInsert2.setpMoneyOutAmt      (parsed.getLong("cash_out_amt"));
                ifCashInsert2.setpCheckInAmt       (parsed.getLong("check_in_amt"));
                ifCashInsert2.setpCheckOutAmt      (parsed.getLong("check_out_amt"));
                ifCashInsert2.setpRemainAmt        (parsed.getLong("cash_remain_amt"));
                ifCashInsert2.setpInCnt            (0);
                ifCashInsert2.setpOutCnt           (0);
                ifCashInsert2.setpChkInCnt         (0);
                ifCashInsert2.setpChkOutCnt        (0);
                ifCashInsert2.setpAddAmt           (0);
                ifCashInsert2.setpCollectAmt       (0);
                ifCashInsert2.setpRemainCheckAmt   (parsed.getLong("check_amt"));
                ifCashInsert2.setpRemain10Amt      (0);
                ifCashInsert2.setpRemain50Amt      (0);
                ifCashInsert2.setpRemain100Amt     (0);
                ifCashInsert2.setpRemain500Amt     (0);
                ifCashInsert2.setpRemain1000Amt    (0);
                ifCashInsert2.setpRemain5000Amt    (0);
                ifCashInsert2.setpRemain10000Amt   (0);
                ifCashInsert2.setpRemain50000Amt   (0);
                ifCashInsert2.setpMoneyIn50000Amt  (0);
                ifCashInsert2.setpMoneyOut50000Amt (0);
                ifCashInsert2.setpMoneyIn5000Amt   (0);
                ifCashInsert2.setpMoneyIn1000Amt   (0);
                ifCashInsert2.setpTodayChargeAmt   (0);
                ifCashInsert2.setpPreChargeAmt     (0);
                ifCashInsert2.setpPreAddAmt        (0);
                ifCashInsert2.setpHolyAddAmt       (0);
                ifCashInsert2.setpTodayAddAmt      (0);
                ifCashInsert2.setpSafeNo           ("");
                ifCashInsert2.setvFirstInqYN       (0);
                ifCashInsert2.setvResult           (-1);

                storedProcMapper.spIfCashinsert(ifCashInsert2);

                if(ifCashInsert2.getvResult() != 0) {
                    throw new MsgBrokerException(String.format("[MngCM_AP_SaveCurrentAmt] 프로시져 오류 3: %s ", ifCashInsert2.getvResultMsg()), -99);
                }
            }//endif

        }//endif


    }//end method


    public class IfCashInsertEmart {
        /*
        nUpdateCheckYN      IN        NUMBER,
        pOrgCd              IN        t_cm_cash_emart.org_cd%TYPE,
        pJijumCd            IN        t_cm_cash_emart.jijum_cd%TYPE,
        pMacNo              IN        t_cm_cash_emart.mac_no%TYPE,
        pCashType           IN        t_cm_cash_emart.cash_type%TYPE,
        pCashDate           IN        t_cm_cash_emart.cash_date%TYPE,
        pCashTime           IN        t_cm_cash_emart.cash_time%TYPE,
        pRemIn10Amt         IN        t_cm_cash_emart.rem_in_10_amt%TYPE,
        pRemIn50Amt         IN        t_cm_cash_emart.rem_in_50_amt%TYPE,
        pRemIn100Amt        IN        t_cm_cash_emart.rem_in_100_amt%TYPE,
        pRemIn500Amt        IN        t_cm_cash_emart.rem_in_500_amt%TYPE,
        pRemIn1000Amt       IN        t_cm_cash_emart.rem_in_1000_amt%TYPE,
        pRemIn5000Amt       IN        t_cm_cash_emart.rem_in_5000_amt%TYPE,
        pRemIn10000Amt      IN        t_cm_cash_emart.rem_in_10000_amt%TYPE,
        pRemIn50000Amt      IN        t_cm_cash_emart.rem_in_50000_amt%TYPE,
        pRemOut10Amt        IN        t_cm_cash_emart.rem_out_10_amt%TYPE,
        pRemOut50Amt        IN        t_cm_cash_emart.rem_out_50_amt%TYPE,
        pRemOut100Amt       IN        t_cm_cash_emart.rem_out_100_amt%TYPE,
        pRemOut500Amt       IN        t_cm_cash_emart.rem_out_500_amt%TYPE,
        pRemOut1000Amt      IN        t_cm_cash_emart.rem_out_1000_amt%TYPE,
        pRemOut5000Amt      IN        t_cm_cash_emart.rem_out_5000_amt%TYPE,
        pRemOut10000Amt     IN        t_cm_cash_emart.rem_out_10000_amt%TYPE,
        pRemOut50000Amt     IN        t_cm_cash_emart.rem_out_50000_amt%TYPE,
        vFirstInqYN         OUT       NUMBER,
        vResult             OUT       NUMBER,
        vResultMsg          OUT       VARCHAR2
         */

        private int nUpdateCheckYN = 0    ;
        private String pOrgCd              ;
        private String pJijumCd            ;
        private String pMacNo              ;
        private String pCashType           ;
        private String pCashDate           ;
        private String pCashTime           ;
        private long pRemIn10Amt         ;
        private long pRemIn50Amt         ;
        private long pRemIn100Amt        ;
        private long pRemIn500Amt        ;
        private long pRemIn1000Amt       ;
        private long pRemIn5000Amt       ;
        private long pRemIn10000Amt      ;
        private long pRemIn50000Amt      ;
        private long pRemOut10Amt        ;
        private long pRemOut50Amt        ;
        private long pRemOut100Amt       ;
        private long pRemOut500Amt       ;
        private long pRemOut1000Amt      ;
        private long pRemOut5000Amt      ;
        private long pRemOut10000Amt     ;
        private long pRemOut50000Amt     ;
        private int vFirstInqYN         ;
        private int vResult             ;
        private String vResultMsg          ;
        /**
         * @return the nUpdateCheckYN
         */
        public int getnUpdateCheckYN()
        {
            return nUpdateCheckYN;
        }
        /**
         * @param nUpdateCheckYN the nUpdateCheckYN to set
         */
        public void setnUpdateCheckYN(int nUpdateCheckYN)
        {
            this.nUpdateCheckYN = nUpdateCheckYN;
        }
        /**
         * @return the pOrgCd
         */
        public String getpOrgCd()
        {
            return pOrgCd;
        }
        /**
         * @param pOrgCd the pOrgCd to set
         */
        public void setpOrgCd(String pOrgCd)
        {
            this.pOrgCd = pOrgCd;
        }
        /**
         * @return the pJijumCd
         */
        public String getpJijumCd()
        {
            return pJijumCd;
        }
        /**
         * @param pJijumCd the pJijumCd to set
         */
        public void setpJijumCd(String pJijumCd)
        {
            this.pJijumCd = pJijumCd;
        }
        /**
         * @return the pMacNo
         */
        public String getpMacNo()
        {
            return pMacNo;
        }
        /**
         * @param pMacNo the pMacNo to set
         */
        public void setpMacNo(String pMacNo)
        {
            this.pMacNo = pMacNo;
        }
        /**
         * @return the pCashType
         */
        public String getpCashType()
        {
            return pCashType;
        }
        /**
         * @param pCashType the pCashType to set
         */
        public void setpCashType(String pCashType)
        {
            this.pCashType = pCashType;
        }
        /**
         * @return the pCashDate
         */
        public String getpCashDate()
        {
            return pCashDate;
        }
        /**
         * @param pCashDate the pCashDate to set
         */
        public void setpCashDate(String pCashDate)
        {
            this.pCashDate = pCashDate;
        }
        /**
         * @return the pCashTime
         */
        public String getpCashTime()
        {
            return pCashTime;
        }
        /**
         * @param pCashTime the pCashTime to set
         */
        public void setpCashTime(String pCashTime)
        {
            this.pCashTime = pCashTime;
        }
        /**
         * @return the pRemIn10Amt
         */
        public long getpRemIn10Amt()
        {
            return pRemIn10Amt;
        }
        /**
         * @param pRemIn10Amt the pRemIn10Amt to set
         */
        public void setpRemIn10Amt(long pRemIn10Amt)
        {
            this.pRemIn10Amt = pRemIn10Amt;
        }
        /**
         * @return the pRemIn50Amt
         */
        public long getpRemIn50Amt()
        {
            return pRemIn50Amt;
        }
        /**
         * @param pRemIn50Amt the pRemIn50Amt to set
         */
        public void setpRemIn50Amt(long pRemIn50Amt)
        {
            this.pRemIn50Amt = pRemIn50Amt;
        }
        /**
         * @return the pRemIn100Amt
         */
        public long getpRemIn100Amt()
        {
            return pRemIn100Amt;
        }
        /**
         * @param pRemIn100Amt the pRemIn100Amt to set
         */
        public void setpRemIn100Amt(long pRemIn100Amt)
        {
            this.pRemIn100Amt = pRemIn100Amt;
        }
        /**
         * @return the pRemIn500Amt
         */
        public long getpRemIn500Amt()
        {
            return pRemIn500Amt;
        }
        /**
         * @param pRemIn500Amt the pRemIn500Amt to set
         */
        public void setpRemIn500Amt(long pRemIn500Amt)
        {
            this.pRemIn500Amt = pRemIn500Amt;
        }
        /**
         * @return the pRemIn1000Amt
         */
        public long getpRemIn1000Amt()
        {
            return pRemIn1000Amt;
        }
        /**
         * @param pRemIn1000Amt the pRemIn1000Amt to set
         */
        public void setpRemIn1000Amt(long pRemIn1000Amt)
        {
            this.pRemIn1000Amt = pRemIn1000Amt;
        }
        /**
         * @return the pRemIn5000Amt
         */
        public long getpRemIn5000Amt()
        {
            return pRemIn5000Amt;
        }
        /**
         * @param pRemIn5000Amt the pRemIn5000Amt to set
         */
        public void setpRemIn5000Amt(long pRemIn5000Amt)
        {
            this.pRemIn5000Amt = pRemIn5000Amt;
        }
        /**
         * @return the pRemIn10000Amt
         */
        public long getpRemIn10000Amt()
        {
            return pRemIn10000Amt;
        }
        /**
         * @param pRemIn10000Amt the pRemIn10000Amt to set
         */
        public void setpRemIn10000Amt(long pRemIn10000Amt)
        {
            this.pRemIn10000Amt = pRemIn10000Amt;
        }
        /**
         * @return the pRemIn50000Amt
         */
        public long getpRemIn50000Amt()
        {
            return pRemIn50000Amt;
        }
        /**
         * @param pRemIn50000Amt the pRemIn50000Amt to set
         */
        public void setpRemIn50000Amt(long pRemIn50000Amt)
        {
            this.pRemIn50000Amt = pRemIn50000Amt;
        }
        /**
         * @return the pRemOut10Amt
         */
        public long getpRemOut10Amt()
        {
            return pRemOut10Amt;
        }
        /**
         * @param pRemOut10Amt the pRemOut10Amt to set
         */
        public void setpRemOut10Amt(long pRemOut10Amt)
        {
            this.pRemOut10Amt = pRemOut10Amt;
        }
        /**
         * @return the pRemOut50Amt
         */
        public long getpRemOut50Amt()
        {
            return pRemOut50Amt;
        }
        /**
         * @param pRemOut50Amt the pRemOut50Amt to set
         */
        public void setpRemOut50Amt(long pRemOut50Amt)
        {
            this.pRemOut50Amt = pRemOut50Amt;
        }
        /**
         * @return the pRemOut100Amt
         */
        public long getpRemOut100Amt()
        {
            return pRemOut100Amt;
        }
        /**
         * @param pRemOut100Amt the pRemOut100Amt to set
         */
        public void setpRemOut100Amt(long pRemOut100Amt)
        {
            this.pRemOut100Amt = pRemOut100Amt;
        }
        /**
         * @return the pRemOut500Amt
         */
        public long getpRemOut500Amt()
        {
            return pRemOut500Amt;
        }
        /**
         * @param pRemOut500Amt the pRemOut500Amt to set
         */
        public void setpRemOut500Amt(long pRemOut500Amt)
        {
            this.pRemOut500Amt = pRemOut500Amt;
        }
        /**
         * @return the pRemOut1000Amt
         */
        public long getpRemOut1000Amt()
        {
            return pRemOut1000Amt;
        }
        /**
         * @param pRemOut1000Amt the pRemOut1000Amt to set
         */
        public void setpRemOut1000Amt(long pRemOut1000Amt)
        {
            this.pRemOut1000Amt = pRemOut1000Amt;
        }
        /**
         * @return the pRemOut5000Amt
         */
        public long getpRemOut5000Amt()
        {
            return pRemOut5000Amt;
        }
        /**
         * @param pRemOut5000Amt the pRemOut5000Amt to set
         */
        public void setpRemOut5000Amt(long pRemOut5000Amt)
        {
            this.pRemOut5000Amt = pRemOut5000Amt;
        }
        /**
         * @return the pRemOut10000Amt
         */
        public long getpRemOut10000Amt()
        {
            return pRemOut10000Amt;
        }
        /**
         * @param pRemOut10000Amt the pRemOut10000Amt to set
         */
        public void setpRemOut10000Amt(long pRemOut10000Amt)
        {
            this.pRemOut10000Amt = pRemOut10000Amt;
        }
        /**
         * @return the pRemOut50000Amt
         */
        public long getpRemOut50000Amt()
        {
            return pRemOut50000Amt;
        }
        /**
         * @param pRemOut50000Amt the pRemOut50000Amt to set
         */
        public void setpRemOut50000Amt(long pRemOut50000Amt)
        {
            this.pRemOut50000Amt = pRemOut50000Amt;
        }
        /**
         * @return the vFirstInqYN
         */
        public int getvFirstInqYN()
        {
            return vFirstInqYN;
        }
        /**
         * @param vFirstInqYN the vFirstInqYN to set
         */
        public void setvFirstInqYN(int vFirstInqYN)
        {
            this.vFirstInqYN = vFirstInqYN;
        }
        /**
         * @return the vResult
         */
        public int getvResult()
        {
            return vResult;
        }
        /**
         * @param vResult the vResult to set
         */
        public void setvResult(int vResult)
        {
            this.vResult = vResult;
        }
        /**
         * @return the vResultMsg
         */
        public String getvResultMsg()
        {
            return vResultMsg;
        }
        /**
         * @param vResultMsg the vResultMsg to set
         */
        public void setvResultMsg(String vResultMsg)
        {
            this.vResultMsg = vResultMsg;
        }

    }
}

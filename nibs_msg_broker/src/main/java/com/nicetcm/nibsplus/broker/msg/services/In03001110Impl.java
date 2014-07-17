package com.nicetcm.nibsplus.broker.msg.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
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
@Service("in03001110")
public class In03001110Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In03001110Impl.class);

    @Autowired private StoredProcMapper splMap;

    @Autowired private StoredProcMapper storedProcMapper;



    @Override
    public void inMsgBizProc(MsgParser parsed) throws Exception {

        logger.debug("Msg Received");
        logger.debug(parsed.getString("CM.work_type"));

        TMacInfo macInfo = new TMacInfo();
        macInfo.setOrgCd( parsed.getString("CM.org_cd") );
        macInfo.setBranchCd( parsed.getString("brch_cd") );
        macInfo.setMacNo( parsed.getString("mac_no") );

        /**************************************************************************************************
         농협atms는 조회일자 조회시간이 전문상에 없으므로 현재 시간을 세팅하도록 한다.
        ***************************************************************************************************/
        final String inqDate = MsgBrokerLib.SysDate();
        final String inqTime = MsgBrokerLib.SysTime();

        /**
         * 지점코드, 기번 길이 검증
         */
        comPack.checkBranchMacLength( macInfo );

        TMisc tMisc = new TMisc();
        tMisc.sethCashType("1");
        tMisc.setInqDate(inqDate);
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
        if(MsgBrokerConst.EMART_CODE.equals(parsed.getLong("CM.org_cd"))) {

            IfCashinsertEmart ifCashinsertEmart = new IfCashinsertEmart();
            ifCashinsertEmart.setnUpdateCheckYN   (0);
            ifCashinsertEmart.setpOrgCd           (parsed.getString("CM.org_cd"));
            ifCashinsertEmart.setpJijumCd         (parsed.getString("brch_cd"));
            ifCashinsertEmart.setpMacNo           (parsed.getString("mac_no"));
            ifCashinsertEmart.setpCashType        (tMisc.gethCashType());
            ifCashinsertEmart.setpCashDate        (inqDate);
            ifCashinsertEmart.setpCashTime        (inqTime);

            ifCashinsertEmart.setpRemIn10Amt      (hRemain10Amt        );
            ifCashinsertEmart.setpRemIn50Amt      (hRemain50Amt        );
            ifCashinsertEmart.setpRemIn100Amt     (hRemain100Amt       );
            ifCashinsertEmart.setpRemIn500Amt     (hRemain500Amt       );
            ifCashinsertEmart.setpRemIn1000Amt    (hRemain1000Amt      );
            ifCashinsertEmart.setpRemIn5000Amt    (hRemain5000Amt      );
            ifCashinsertEmart.setpRemIn10000Amt   (hRemain10000Amt     );
            ifCashinsertEmart.setpRemIn50000Amt   (hRemain50000Amt     );
            ifCashinsertEmart.setpRemOut10Amt     (hRemIn10Amt         );
            ifCashinsertEmart.setpRemOut50Amt     (hRemIn50Amt         );
            ifCashinsertEmart.setpRemOut100Amt    (hRemIn100Amt        );
            ifCashinsertEmart.setpRemOut500Amt    (hRemIn500Amt        );
            ifCashinsertEmart.setpRemOut1000Amt   (hRemIn1000Amt       );
            ifCashinsertEmart.setpRemOut5000Amt   (hRemIn5000Amt       );
            ifCashinsertEmart.setpRemOut10000Amt  (hRemIn10000Amt      );
            ifCashinsertEmart.setpRemOut50000Amt  (hRemIn50000Amt      );

            ifCashinsertEmart.setvFirstInqYN      (0);
            ifCashinsertEmart.setvResult          (-1);
            //ifCashinsertEmart.setvResultMsg       ();

            if(ifCashinsertEmart.getvResult() != 0) {
                throw new MsgBrokerException(String.format("[sp_if_CashInsert_EMART] 프로시져 오류 2: %s ", ifCashinsertEmart.getvResultMsg()), -99);
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
             [경남은행] 시재조회출처구분이 '1'(ATMS 인경우도)

             if( strcmp(suBody.cash_remain_amt, "" ) == 0 )
            ***************************************************************************************************/

            if(parsed.getString("cash_remain_amt").substring(0, 1).equals(" ") ||
               parsed.getString("cash_remain_amt").equals("") ||
               MsgBrokerConst.KFCC_CODE.equals(parsed.getString("CM.org_cd")) ||
               MsgBrokerConst.DGB_CODE.equals(parsed.getString("CM.org_cd")) ||
            (  MsgBrokerConst.KNATMS_CODE.equals(parsed.getString("CM.org_cd")) && parsed.getString("inq_source").equals("1") )) {

                /**************************************************************************************************
                 주기현송에 따른 잔액 계산법 변경 20111103. 정희성 요청
                 정규현송이 있다면 기존과 동일 (현금잔액 = 장전(직전)금액 + 현금입금액 - 현금지급액)
                 정규현송이 없다면 마감조회 검색하여
                        마감기기 라면  (현금잔액 =장전(직전)금액 - 마감지급 + 마감입금 + 현금입금액 - 현금지급액)
                        미마감기기라면 기존과 동일 (현금잔액 = 장전(직전)금액 + 현금입금액 - 현금지급액)
                ***************************************************************************************************/
                /*
                  memset( suBody.cash_remain_amt,   0x00, sizeof(suBody.cash_remain_amt) );

                                EXEC SQL    SELECT (CASH.TOT_IN_AMT - CASH.CHECK_IN_AMT) AS CLOSE_IN_AMT,
                                (CASH.TOT_OUT_AMT - CASH.CHECK_OUT_AMT ) AS CLOSE_OUT_AMT
                         INTO    :hCloseInAmt,
                                 :hCloseOutAmt
                         FROM T_CM_CASH CASH,
                             (
                                 SELECT  ORG_CD, jijum_cd,
                                         mac_no
                                 FROM    T_CM_MAC
                                 WHERE   org_cd = :suHead.org_cd
                                 AND     JIJUM_CD    =:suBody.jijum_cd
                                 AND     MAC_NO     = :suBody.mac_no
                                 MINUS
                                 SELECT  ORG_CD,
                                         jijum_cd,
                                         mac_no
                                 FROM    T_FN_SEND
                                 WHERE   SEND_DATE(+) = TO_CHAR(SYSDATE, 'YYYYMMDD')
                                 AND     SEND_TYPE = '1'
                                 AND     ORG_CD = :suHead.org_cd
                                 AND     JIJUM_CD    =:suBody.jijum_cd
                                 AND     MAC_NO     = :suBody.mac_no
                             ) BB
                         WHERE   CASH.CASH_DATE  = TO_CHAR(SYSDATE, 'YYYYMMDD')
                         AND     CASH.CASH_TYPE  = '2'
                         AND     CASH.ORG_CD     = :suHead.org_cd
                         AND     CASH.JIJUM_CD   =:suBody.jijum_cd
                         AND     CASH.MAC_NO     = :suBody.mac_no
                         AND     CASH.ORG_CD     = BB.ORG_CD
                         AND     CASH.JIJUM_CD   = BB.JIJUM_CD
                         AND     CASH.MAC_NO     = BB.MAC_NO;;

                    if(sqlca.sqlcode == DB_NO_DATA)
                    {
                        /**************************************************************************************************
                         미현송 미마감기기 혹은 정규현송 기기의 경우 기존 식 따름
                        ***************************************************************************************************
                        hCloseInAmt = 0;
                        hCloseOutAmt = 0;
                    }
                    else if(sqlca.sqlcode)
                    {
                        logger(">>> [T_CM_CASH] ERROR [%.200s]\n", SqlErrMsg);
                        return -1;
                    }
                */

                /**************************************************************************************************
                 현금잔액 = 장전(직전)금액 + 현금입금액 - 현금지급액
                ***************************************************************************************************
                sprintf(suBody.cash_remain_amt, "%ld",
                            hPRE_AMT + atol(suBody.cash_in_amt) - atol(suBody.cash_out_amt) - hCloseOutAmt + hCloseInAmt
                            );
                */


            }

        }


    }//end method

    public class IfCashinsertEmart {
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

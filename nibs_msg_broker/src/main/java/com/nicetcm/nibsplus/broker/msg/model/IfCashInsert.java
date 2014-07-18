package com.nicetcm.nibsplus.broker.msg.model;

/**
 *
 * IfCashInsert
 * <pre>
 * op.sp_if_CashInsert Input VO
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class IfCashInsert {
    /*
    nUpdateCheckYN      IN        NUMBER,
    pOrgCd              IN        t_cm_cash.org_cd%TYPE,
    pJijumCd            IN        t_cm_cash.jijum_cd%TYPE,
    pMacNo              IN        t_cm_cash.mac_no%TYPE,
    pCashType           IN        t_cm_cash.cash_type%TYPE,
    pCashDate           IN        t_cm_cash.cash_date%TYPE,
    pCashTime           IN        t_cm_cash.cash_time%TYPE,
    pChargeAmt          IN        t_cm_cash.charge_amt%TYPE,
    pTotInAmt           IN        t_cm_cash.tot_in_amt%TYPE,
    pTotOutAmt          IN        t_cm_cash.tot_out_amt%TYPE,
    pMoneyInAmt         IN        t_cm_cash.money_in_amt%TYPE,
    pMoneyOutAmt        IN        t_cm_cash.money_out_amt%TYPE,
    pCheckInAmt         IN        t_cm_cash.check_in_amt%TYPE,
    pCheckOutAmt        IN        t_cm_cash.check_out_amt%TYPE,
    pRemainAmt          IN        t_cm_cash.remain_amt%TYPE,
    pInCnt              IN        t_cm_cash.in_cnt%TYPE,
    pOutCnt             IN        t_cm_cash.out_cnt%TYPE,
    pChkInCnt           IN        t_cm_cash.chk_in_cnt%TYPE,
    pChkOutCnt          IN        t_cm_cash.chk_out_cnt%TYPE,
    pAddAmt             IN        t_cm_cash.add_amt%TYPE,
    pCollectAmt         IN        t_cm_cash.collect_amt%TYPE,
    pRemainCheckAmt     IN        t_cm_cash.remain_check_amt%TYPE,
    pRemain10Amt        IN        t_cm_cash.remain_10_amt%TYPE,
    pRemain50Amt        IN        t_cm_cash.remain_50_amt%TYPE,
    pRemain100Amt       IN        t_cm_cash.remain_100_amt%TYPE,
    pRemain500Amt       IN        t_cm_cash.remain_500_amt%TYPE,
    pRemain1000Amt      IN        t_cm_cash.remain_1000_amt%TYPE,
    pRemain5000Amt      IN        t_cm_cash.remain_5000_amt%TYPE,
    pRemain10000Amt     IN        t_cm_cash.remain_10000_amt%TYPE,
    pRemain50000Amt     IN        t_cm_cash.remain_50000_amt%TYPE,
    pMoneyIn50000Amt    IN        t_cm_cash.money_in_50000_amt%TYPE,
    pMoneyOut50000Amt   IN        t_cm_cash.money_out_50000_amt%TYPE,
    pMoneyIn5000Amt     IN        t_cm_cash.money_in_5000_amt%TYPE,
    pMoneyIn1000Amt     IN        t_cm_cash.money_in_1000_amt%TYPE,
    pTodayChargeAmt     IN        t_cm_cash.today_charge_amt%TYPE,
    pPreChargeAmt       IN        t_cm_cash.pre_charge_amt%TYPE,
    pPreAddAmt          IN        t_cm_cash.pre_add_amt%TYPE,
    pHolyAddAmt         IN        t_cm_cash.holy_add_amt%TYPE,
    pTodayAddAmt        IN        t_cm_cash.today_add_amt%TYPE,
    pSafeNo             IN        t_cm_cash.safe_no%TYPE,
    vFirstInqYN         OUT       NUMBER,
    vResult             OUT       NUMBER,
    vResultMsg          OUT       VARCHAR2
    */

    private long nUpdateCheckYN      ;
    private String pOrgCd              ;
    private String pJijumCd            ;
    private String pMacNo              ;
    private String pCashType           ;
    private String pCashDate           ;
    private String pCashTime           ;
    private long pChargeAmt          ;
    private long pTotInAmt           ;
    private long pTotOutAmt          ;
    private long pMoneyInAmt         ;
    private long pMoneyOutAmt        ;
    private long pCheckInAmt         ;
    private long pCheckOutAmt        ;
    private long pRemainAmt          ;
    private long pInCnt              ;
    private long pOutCnt             ;
    private long pChkInCnt           ;
    private long pChkOutCnt          ;
    private long pAddAmt             ;
    private long pCollectAmt         ;
    private long pRemainCheckAmt     ;
    private long pRemain10Amt        ;
    private long pRemain50Amt        ;
    private long pRemain100Amt       ;
    private long pRemain500Amt       ;
    private long pRemain1000Amt      ;
    private long pRemain5000Amt      ;
    private long pRemain10000Amt     ;
    private long pRemain50000Amt     ;
    private long pMoneyIn50000Amt    ;
    private long pMoneyOut50000Amt   ;
    private long pMoneyIn5000Amt     ;
    private long pMoneyIn1000Amt     ;
    private long pTodayChargeAmt     ;
    private long pPreChargeAmt       ;
    private long pPreAddAmt          ;
    private long pHolyAddAmt         ;
    private long pTodayAddAmt        ;
    private String pSafeNo             ;
    private int vFirstInqYN         ;
    private int vResult             ;
    private String vResultMsg          ;
    /**
     * @return the nUpdateCheckYN
     */
    public long getnUpdateCheckYN()
    {
        return nUpdateCheckYN;
    }
    /**
     * @param nUpdateCheckYN the nUpdateCheckYN to set
     */
    public void setnUpdateCheckYN(long nUpdateCheckYN)
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
     * @return the pChargeAmt
     */
    public long getpChargeAmt()
    {
        return pChargeAmt;
    }
    /**
     * @param pChargeAmt the pChargeAmt to set
     */
    public void setpChargeAmt(long pChargeAmt)
    {
        this.pChargeAmt = pChargeAmt;
    }
    /**
     * @return the pTotInAmt
     */
    public long getpTotInAmt()
    {
        return pTotInAmt;
    }
    /**
     * @param pTotInAmt the pTotInAmt to set
     */
    public void setpTotInAmt(long pTotInAmt)
    {
        this.pTotInAmt = pTotInAmt;
    }
    /**
     * @return the pTotOutAmt
     */
    public long getpTotOutAmt()
    {
        return pTotOutAmt;
    }
    /**
     * @param pTotOutAmt the pTotOutAmt to set
     */
    public void setpTotOutAmt(long pTotOutAmt)
    {
        this.pTotOutAmt = pTotOutAmt;
    }
    /**
     * @return the pMoneyInAmt
     */
    public long getpMoneyInAmt()
    {
        return pMoneyInAmt;
    }
    /**
     * @param pMoneyInAmt the pMoneyInAmt to set
     */
    public void setpMoneyInAmt(long pMoneyInAmt)
    {
        this.pMoneyInAmt = pMoneyInAmt;
    }
    /**
     * @return the pMoneyOutAmt
     */
    public long getpMoneyOutAmt()
    {
        return pMoneyOutAmt;
    }
    /**
     * @param pMoneyOutAmt the pMoneyOutAmt to set
     */
    public void setpMoneyOutAmt(long pMoneyOutAmt)
    {
        this.pMoneyOutAmt = pMoneyOutAmt;
    }
    /**
     * @return the pCheckInAmt
     */
    public long getpCheckInAmt()
    {
        return pCheckInAmt;
    }
    /**
     * @param pCheckInAmt the pCheckInAmt to set
     */
    public void setpCheckInAmt(long pCheckInAmt)
    {
        this.pCheckInAmt = pCheckInAmt;
    }
    /**
     * @return the pCheckOutAmt
     */
    public long getpCheckOutAmt()
    {
        return pCheckOutAmt;
    }
    /**
     * @param pCheckOutAmt the pCheckOutAmt to set
     */
    public void setpCheckOutAmt(long pCheckOutAmt)
    {
        this.pCheckOutAmt = pCheckOutAmt;
    }
    /**
     * @return the pRemainAmt
     */
    public long getpRemainAmt()
    {
        return pRemainAmt;
    }
    /**
     * @param pRemainAmt the pRemainAmt to set
     */
    public void setpRemainAmt(long pRemainAmt)
    {
        this.pRemainAmt = pRemainAmt;
    }
    /**
     * @return the pInCnt
     */
    public long getpInCnt()
    {
        return pInCnt;
    }
    /**
     * @param pInCnt the pInCnt to set
     */
    public void setpInCnt(long pInCnt)
    {
        this.pInCnt = pInCnt;
    }
    /**
     * @return the pOutCnt
     */
    public long getpOutCnt()
    {
        return pOutCnt;
    }
    /**
     * @param pOutCnt the pOutCnt to set
     */
    public void setpOutCnt(long pOutCnt)
    {
        this.pOutCnt = pOutCnt;
    }
    /**
     * @return the pChkInCnt
     */
    public long getpChkInCnt()
    {
        return pChkInCnt;
    }
    /**
     * @param pChkInCnt the pChkInCnt to set
     */
    public void setpChkInCnt(long pChkInCnt)
    {
        this.pChkInCnt = pChkInCnt;
    }
    /**
     * @return the pChkOutCnt
     */
    public long getpChkOutCnt()
    {
        return pChkOutCnt;
    }
    /**
     * @param pChkOutCnt the pChkOutCnt to set
     */
    public void setpChkOutCnt(long pChkOutCnt)
    {
        this.pChkOutCnt = pChkOutCnt;
    }
    /**
     * @return the pAddAmt
     */
    public long getpAddAmt()
    {
        return pAddAmt;
    }
    /**
     * @param pAddAmt the pAddAmt to set
     */
    public void setpAddAmt(long pAddAmt)
    {
        this.pAddAmt = pAddAmt;
    }
    /**
     * @return the pCollectAmt
     */
    public long getpCollectAmt()
    {
        return pCollectAmt;
    }
    /**
     * @param pCollectAmt the pCollectAmt to set
     */
    public void setpCollectAmt(long pCollectAmt)
    {
        this.pCollectAmt = pCollectAmt;
    }
    /**
     * @return the pRemainCheckAmt
     */
    public long getpRemainCheckAmt()
    {
        return pRemainCheckAmt;
    }
    /**
     * @param pRemainCheckAmt the pRemainCheckAmt to set
     */
    public void setpRemainCheckAmt(long pRemainCheckAmt)
    {
        this.pRemainCheckAmt = pRemainCheckAmt;
    }
    /**
     * @return the pRemain10Amt
     */
    public long getpRemain10Amt()
    {
        return pRemain10Amt;
    }
    /**
     * @param pRemain10Amt the pRemain10Amt to set
     */
    public void setpRemain10Amt(long pRemain10Amt)
    {
        this.pRemain10Amt = pRemain10Amt;
    }
    /**
     * @return the pRemain50Amt
     */
    public long getpRemain50Amt()
    {
        return pRemain50Amt;
    }
    /**
     * @param pRemain50Amt the pRemain50Amt to set
     */
    public void setpRemain50Amt(long pRemain50Amt)
    {
        this.pRemain50Amt = pRemain50Amt;
    }
    /**
     * @return the pRemain100Amt
     */
    public long getpRemain100Amt()
    {
        return pRemain100Amt;
    }
    /**
     * @param pRemain100Amt the pRemain100Amt to set
     */
    public void setpRemain100Amt(long pRemain100Amt)
    {
        this.pRemain100Amt = pRemain100Amt;
    }
    /**
     * @return the pRemain500Amt
     */
    public long getpRemain500Amt()
    {
        return pRemain500Amt;
    }
    /**
     * @param pRemain500Amt the pRemain500Amt to set
     */
    public void setpRemain500Amt(long pRemain500Amt)
    {
        this.pRemain500Amt = pRemain500Amt;
    }
    /**
     * @return the pRemain1000Amt
     */
    public long getpRemain1000Amt()
    {
        return pRemain1000Amt;
    }
    /**
     * @param pRemain1000Amt the pRemain1000Amt to set
     */
    public void setpRemain1000Amt(long pRemain1000Amt)
    {
        this.pRemain1000Amt = pRemain1000Amt;
    }
    /**
     * @return the pRemain5000Amt
     */
    public long getpRemain5000Amt()
    {
        return pRemain5000Amt;
    }
    /**
     * @param pRemain5000Amt the pRemain5000Amt to set
     */
    public void setpRemain5000Amt(long pRemain5000Amt)
    {
        this.pRemain5000Amt = pRemain5000Amt;
    }
    /**
     * @return the pRemain10000Amt
     */
    public long getpRemain10000Amt()
    {
        return pRemain10000Amt;
    }
    /**
     * @param pRemain10000Amt the pRemain10000Amt to set
     */
    public void setpRemain10000Amt(long pRemain10000Amt)
    {
        this.pRemain10000Amt = pRemain10000Amt;
    }
    /**
     * @return the pRemain50000Amt
     */
    public long getpRemain50000Amt()
    {
        return pRemain50000Amt;
    }
    /**
     * @param pRemain50000Amt the pRemain50000Amt to set
     */
    public void setpRemain50000Amt(long pRemain50000Amt)
    {
        this.pRemain50000Amt = pRemain50000Amt;
    }
    /**
     * @return the pMoneyIn50000Amt
     */
    public long getpMoneyIn50000Amt()
    {
        return pMoneyIn50000Amt;
    }
    /**
     * @param pMoneyIn50000Amt the pMoneyIn50000Amt to set
     */
    public void setpMoneyIn50000Amt(long pMoneyIn50000Amt)
    {
        this.pMoneyIn50000Amt = pMoneyIn50000Amt;
    }
    /**
     * @return the pMoneyOut50000Amt
     */
    public long getpMoneyOut50000Amt()
    {
        return pMoneyOut50000Amt;
    }
    /**
     * @param pMoneyOut50000Amt the pMoneyOut50000Amt to set
     */
    public void setpMoneyOut50000Amt(long pMoneyOut50000Amt)
    {
        this.pMoneyOut50000Amt = pMoneyOut50000Amt;
    }
    /**
     * @return the pMoneyIn5000Amt
     */
    public long getpMoneyIn5000Amt()
    {
        return pMoneyIn5000Amt;
    }
    /**
     * @param pMoneyIn5000Amt the pMoneyIn5000Amt to set
     */
    public void setpMoneyIn5000Amt(long pMoneyIn5000Amt)
    {
        this.pMoneyIn5000Amt = pMoneyIn5000Amt;
    }
    /**
     * @return the pMoneyIn1000Amt
     */
    public long getpMoneyIn1000Amt()
    {
        return pMoneyIn1000Amt;
    }
    /**
     * @param pMoneyIn1000Amt the pMoneyIn1000Amt to set
     */
    public void setpMoneyIn1000Amt(long pMoneyIn1000Amt)
    {
        this.pMoneyIn1000Amt = pMoneyIn1000Amt;
    }
    /**
     * @return the pTodayChargeAmt
     */
    public long getpTodayChargeAmt()
    {
        return pTodayChargeAmt;
    }
    /**
     * @param pTodayChargeAmt the pTodayChargeAmt to set
     */
    public void setpTodayChargeAmt(long pTodayChargeAmt)
    {
        this.pTodayChargeAmt = pTodayChargeAmt;
    }
    /**
     * @return the pPreChargeAmt
     */
    public long getpPreChargeAmt()
    {
        return pPreChargeAmt;
    }
    /**
     * @param pPreChargeAmt the pPreChargeAmt to set
     */
    public void setpPreChargeAmt(long pPreChargeAmt)
    {
        this.pPreChargeAmt = pPreChargeAmt;
    }
    /**
     * @return the pPreAddAmt
     */
    public long getpPreAddAmt()
    {
        return pPreAddAmt;
    }
    /**
     * @param pPreAddAmt the pPreAddAmt to set
     */
    public void setpPreAddAmt(long pPreAddAmt)
    {
        this.pPreAddAmt = pPreAddAmt;
    }
    /**
     * @return the pHolyAddAmt
     */
    public long getpHolyAddAmt()
    {
        return pHolyAddAmt;
    }
    /**
     * @param pHolyAddAmt the pHolyAddAmt to set
     */
    public void setpHolyAddAmt(long pHolyAddAmt)
    {
        this.pHolyAddAmt = pHolyAddAmt;
    }
    /**
     * @return the pTodayAddAmt
     */
    public long getpTodayAddAmt()
    {
        return pTodayAddAmt;
    }
    /**
     * @param pTodayAddAmt the pTodayAddAmt to set
     */
    public void setpTodayAddAmt(long pTodayAddAmt)
    {
        this.pTodayAddAmt = pTodayAddAmt;
    }
    /**
     * @return the pSafeNo
     */
    public String getpSafeNo()
    {
        return pSafeNo;
    }
    /**
     * @param pSafeNo the pSafeNo to set
     */
    public void setpSafeNo(String pSafeNo)
    {
        this.pSafeNo = pSafeNo;
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
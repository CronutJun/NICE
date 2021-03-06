package com.nicetcm.nibsplus.broker.msg.model;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * 특정 테이블에 종속 되지 않은 Query들을 모아둔 클래스
 *
 * @author KDJ on 2014.07.02
 *
 */
public class TMisc {

    /*
     * holiday 값 F_GET_HOLIDAY StoredFunction을 통해 취득
     */
    private String holiday;
    /*
     * F_GET_NICE_BRANCH_CD StoredFunction을 통해 branchCd 취득 (삼성생명?)
     */
    private String branchCd;
    /*
     * 장애관리 Key
     */
    private String errorNo;

    private String hCashType;

    private long hRtnVal;

    private String hRtnMsg;

    private long hPreAmt;

    private String inqDate;

    private String orgCd;

    private String macNo;

    private String orgSendConfirmYn;

    private String cashDate;

    private String dealDate;

    private String dealNo;

    private String closeType;

    private long cnt;

    private String argValue;

    private String argType;

    private String secureResult;

    private String seq;

    private String boxGubun1;

    private String boxGubun2;

    private String kjGubun;

    private String ticketCd;

    private String ornzCd;

    private String madeComCd;

    private String madeOrgCd;

    private String sendMsg;

    private float sendMode;

    private String telNo;

    private String createDate;

    private String transSeqNo;

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public String getBranchCd() {
        return branchCd;
    }

    public void setBranchCd(String branchCd) {
        this.branchCd = branchCd;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    /**
     * @return the hCashType
     */
    public String gethCashType()
    {
        return hCashType;
    }

    /**
     * @param hCashType the hCashType to set
     */
    public void sethCashType(String hCashType)
    {
        this.hCashType = hCashType;
    }

    /**
     * @return the hRtnVal
     */
    public long gethRtnVal()
    {
        return hRtnVal;
    }

    /**
     * @param hRtnVal the hRtnVal to set
     */
    public void sethRtnVal(long hRtnVal)
    {
        this.hRtnVal = hRtnVal;
    }

    /**
     * @return the hRtnMsg
     */
    public String gethRtnMsg()
    {
        return hRtnMsg;
    }

    /**
     * @param hRtnMsg the hRtnMsg to set
     */
    public void sethRtnMsg(String hRtnMsg)
    {
        this.hRtnMsg = hRtnMsg;
    }

    /**
     * @return the hPreAmt
     */
    public long gethPreAmt()
    {
        return hPreAmt;
    }

    /**
     * @param hPreAmt the hPreAmt to set
     */
    public void sethPreAmt(long hPreAmt)
    {
        this.hPreAmt = hPreAmt;
    }

    /**
     * @return the inqDate
     */
    public String getInqDate()
    {
        return inqDate;
    }

    /**
     * @param inqDate the inqDate to set
     */
    public void setInqDate(String inqDate)
    {
        this.inqDate = inqDate;
    }

    /**
     * @return the orgCd
     */
    public String getOrgCd()
    {
        return orgCd;
    }

    /**
     * @param orgCd the orgCd to set
     */
    public void setOrgCd(String orgCd)
    {
        this.orgCd = orgCd;
    }

    /**
     * @return the macNo
     */
    public String getMacNo()
    {
        return macNo;
    }

    /**
     * @param macNo the macNo to set
     */
    public void setMacNo(String macNo)
    {
        this.macNo = macNo;
    }

    /**
     * @return the orgSendConfirmYn
     */
    public String getOrgSendConfirmYn()
    {
        return orgSendConfirmYn;
    }

    /**
     * @param orgSendConfirmYn the orgSendConfirmYn to set
     */
    public void setOrgSendConfirmYn(String orgSendConfirmYn)
    {
        this.orgSendConfirmYn = orgSendConfirmYn;
    }

    /**
     * @return the cashDate
     */
    public String getCashDate()
    {
        return cashDate;
    }

    /**
     * @param cashDate the cashDate to set
     */
    public void setCashDate(String cashDate)
    {
        this.cashDate = cashDate;
    }

    /**
     * @return the dealDate
     */
    public String getDealDate()
    {
        return dealDate;
    }

    /**
     * @param dealDate the dealDate to set
     */
    public void setDealDate(String dealDate)
    {
        this.dealDate = dealDate;
    }

    /**
     * @return the closeType
     */
    public String getCloseType()
    {
        return closeType;
    }

    /**
     * @param closeType the closeType to set
     */
    public void setCloseType(String closeType)
    {
        this.closeType = closeType;
    }

    /**
     * @return the cnt
     */
    public long getCnt()
    {
        return cnt;
    }

    /**
     * @param cnt the cnt to set
     */
    public void setCnt(long cnt)
    {
        this.cnt = cnt;
    }

    /**
     * @return the argValue
     */
    public String getArgValue()
    {
        return argValue;
    }

    /**
     * @param argValue the argValue to set
     */
    public void setArgValue(String argValue)
    {
        this.argValue = argValue;
    }

    /**
     * @return the argType
     */
    public String getArgType()
    {
        return argType;
    }

    /**
     * @param argType the argType to set
     */
    public void setArgType(String argType)
    {
        this.argType = argType;
    }

    /**
     * @return the secureResult
     */
    public String getSecureResult()
    {
        return secureResult;
    }

    /**
     * @param secureResult the secureResult to set
     */
    public void setSecureResult(String secureResult)
    {
        this.secureResult = secureResult;
    }

    /**
     * @return the seq
     */
    public String getSeq()
    {
        return seq;
    }

    /**
     * @param seq the seq to set
     */
    public void setSeq(String seq)
    {
        this.seq = seq;
    }

    /**
     * @return the boxGubun1
     */
    public String getBoxGubun1()
    {
        return boxGubun1;
    }

    /**
     * @param boxGubun1 the boxGubun1 to set
     */
    public void setBoxGubun1(String boxGubun1)
    {
        this.boxGubun1 = boxGubun1;
    }

    /**
     * @return the boxGubun2
     */
    public String getBoxGubun2()
    {
        return boxGubun2;
    }

    /**
     * @param boxGubun2 the boxGubun2 to set
     */
    public void setBoxGubun2(String boxGubun2)
    {
        this.boxGubun2 = boxGubun2;
    }

    /**
     * @return the kjGubun
     */
    public String getKjGubun()
    {
        return kjGubun;
    }

    /**
     * @param kjGubun the kjGubun to set
     */
    public void setKjGubun(String kjGubun)
    {
        this.kjGubun = kjGubun;
    }

    /**
     * @return the ticketCd
     */
    public String getTicketCd()
    {
        return ticketCd;
    }

    /**
     * @param ticketCd the ticketCd to set
     */
    public void setTicketCd(String ticketCd)
    {
        this.ticketCd = ticketCd;
    }

    /**
     * @return the ornzCd
     */
    public String getOrnzCd()
    {
        return ornzCd;
    }

    /**
     * @param ornzCd the ornzCd to set
     */
    public void setOrnzCd(String ornzCd)
    {
        this.ornzCd = ornzCd;
    }

    /**
     * @return the madeComCd
     */
    public String getMadeComCd()
    {
        return madeComCd;
    }

    /**
     * @param madeComCd the madeComCd to set
     */
    public void setMadeComCd(String madeComCd)
    {
        this.madeComCd = madeComCd;
    }

    /**
     * @return the madeOrgCd
     */
    public String getMadeOrgCd()
    {
        return madeOrgCd;
    }

    /**
     * @param madeOrgCd the madeOrgCd to set
     */
    public void setMadeOrgCd(String madeOrgCd)
    {
        this.madeOrgCd = madeOrgCd;
    }

    public String getSendMsg() {
        return sendMsg;
    }

    public void setSendMsg(String sendMsg) {
        this.sendMsg = sendMsg;
    }

    public float getSendMode() {
        return sendMode;
    }

    public void setSendMode(float sendMode) {
        this.sendMode = sendMode;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getTransSeqNo() {
        return transSeqNo;
    }

    public void setTransSeqNo(String transSeqNo) {
        this.transSeqNo = transSeqNo;
    }

    public String getDealNo() {
        return dealNo;
    }

    public void setDealNo(String dealNo) {
        this.dealNo = dealNo;
    }


}
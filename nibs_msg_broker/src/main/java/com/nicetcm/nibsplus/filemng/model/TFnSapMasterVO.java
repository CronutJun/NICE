package com.nicetcm.nibsplus.filemng.model;

/**
 *
 * <pre>
 * ======================================================================
 * 이랜드 FTP 수신 FILE FORMAT SAP MASTER
 * ======================================================================
 * struct FTP_SAP_MASTER {
 *     [00] char    work_nm         [5 +1];
 *     [01] char    data_type       [2 +1];
 *     [02] char    data_seq        [7 +1];
 *     [03] char    deal_date       [8 +1];
 *     [04] char    jijum_cd        [4 +1];
 *     [05] char    member_id       [8 +1];
 *     [06] char    member_nm       [40+1];
 *     [07] char    member_type     [1 +1];
 *     [08] char    check_amt       [11+1];
 *     [09] char    cash_amt        [11+1];
 *     [10] char    self_cupon      [11+1];
 *     [11] char    etc_cupon       [11+1];
 *     [12] char    filler          [31+1];    //여분
 * };
 * </pre>
 *
 * @author s7760ker@gmail.com
 * @version 1.0
 * @see
 */
public class TFnSapMasterVO
{
    private String workNm;
    private String dataType;
    private String dataSeq;
    private String dealDate;
    private String branchCd;
    private String memberId;
    private String memberNm;
    private String memberType;
    private String checkAmt;
    private String cashAmt;
    private String selfCupon;
    private String etcCupon;
    private String filler;

    private String officeConfirm;

    /**
     * @return the workNm
     */
    public String getWorkNm()
    {
        return workNm;
    }
    /**
     * @param workNm the workNm to set
     */
    public void setWorkNm(String workNm)
    {
        this.workNm = workNm;
    }
    /**
     * @return the dataType
     */
    public String getDataType()
    {
        return dataType;
    }
    /**
     * @param dataType the dataType to set
     */
    public void setDataType(String dataType)
    {
        this.dataType = dataType;
    }
    /**
     * @return the dataSeq
     */
    public String getDataSeq()
    {
        return dataSeq;
    }
    /**
     * @param dataSeq the dataSeq to set
     */
    public void setDataSeq(String dataSeq)
    {
        this.dataSeq = dataSeq;
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
     * @return the branchCd
     */
    public String getBranchCd()
    {
        return branchCd;
    }
    /**
     * @param branchCd the branchCd to set
     */
    public void setBranchCd(String branchCd)
    {
        this.branchCd = branchCd;
    }
    /**
     * @return the memberId
     */
    public String getMemberId()
    {
        return memberId;
    }
    /**
     * @param memberId the memberId to set
     */
    public void setMemberId(String memberId)
    {
        this.memberId = memberId;
    }
    /**
     * @return the memberNm
     */
    public String getMemberNm()
    {
        return memberNm;
    }
    /**
     * @param memberNm the memberNm to set
     */
    public void setMemberNm(String memberNm)
    {
        this.memberNm = memberNm;
    }
    /**
     * @return the memberType
     */
    public String getMemberType()
    {
        return memberType;
    }
    /**
     * @param memberType the memberType to set
     */
    public void setMemberType(String memberType)
    {
        this.memberType = memberType;
    }
    /**
     * @return the checkAmt
     */
    public String getCheckAmt()
    {
        return checkAmt;
    }
    /**
     * @param checkAmt the checkAmt to set
     */
    public void setCheckAmt(String checkAmt)
    {
        this.checkAmt = checkAmt;
    }
    /**
     * @return the cashAmt
     */
    public String getCashAmt()
    {
        return cashAmt;
    }
    /**
     * @param cashAmt the cashAmt to set
     */
    public void setCashAmt(String cashAmt)
    {
        this.cashAmt = cashAmt;
    }
    /**
     * @return the selfCupon
     */
    public String getSelfCupon()
    {
        return selfCupon;
    }
    /**
     * @param selfCupon the selfCupon to set
     */
    public void setSelfCupon(String selfCupon)
    {
        this.selfCupon = selfCupon;
    }
    /**
     * @return the etcCupon
     */
    public String getEtcCupon()
    {
        return etcCupon;
    }
    /**
     * @param etcCupon the etcCupon to set
     */
    public void setEtcCupon(String etcCupon)
    {
        this.etcCupon = etcCupon;
    }
    /**
     * @return the filler
     */
    public String getFiller()
    {
        return filler;
    }
    /**
     * @param filler the filler to set
     */
    public void setFiller(String filler)
    {
        this.filler = filler;
    }
    /**
     * @return the officeConfirm
     */
    public String getOfficeConfirm()
    {
        return officeConfirm;
    }
    /**
     * @param officeConfirm the officeConfirm to set
     */
    public void setOfficeConfirm(String officeConfirm)
    {
        this.officeConfirm = officeConfirm;
    }
}

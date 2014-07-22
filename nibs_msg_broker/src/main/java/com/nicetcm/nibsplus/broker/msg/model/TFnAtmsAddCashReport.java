package com.nicetcm.nibsplus.broker.msg.model;

import java.util.Date;

public class TFnAtmsAddCashReport extends TFnAtmsAddCashReportKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.DEMAND_DATE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private String demandDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_CASH_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private Long addCashAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.OFFICE_NM
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private String officeNm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ORG_SEND_YN
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private String orgSendYn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.UPDATE_DATE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private Date updateDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.UPDATE_UID
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private String updateUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_TIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private String addTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CLOSE_YN
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private String closeYn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.SEND_DATE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private String sendDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_CHECK_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private Long addCheckAmt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_10_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private Integer check10Cnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_30_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private Integer check30Cnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_50_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private Integer check50Cnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_100_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private Integer check100Cnt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.SAFE_TYPE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private String safeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_50000_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private Long add50000Amt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_10000_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    private Long add10000Amt;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.DEMAND_DATE
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.DEMAND_DATE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String getDemandDate() {
        return demandDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.DEMAND_DATE
     *
     * @param demandDate the value for OP.T_FN_ATMS_ADD_CASH_REPORT.DEMAND_DATE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setDemandDate(String demandDate) {
        this.demandDate = demandDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_CASH_AMT
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_CASH_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public Long getAddCashAmt() {
        return addCashAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_CASH_AMT
     *
     * @param addCashAmt the value for OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_CASH_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setAddCashAmt(Long addCashAmt) {
        this.addCashAmt = addCashAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.OFFICE_NM
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.OFFICE_NM
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String getOfficeNm() {
        return officeNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.OFFICE_NM
     *
     * @param officeNm the value for OP.T_FN_ATMS_ADD_CASH_REPORT.OFFICE_NM
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setOfficeNm(String officeNm) {
        this.officeNm = officeNm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ORG_SEND_YN
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.ORG_SEND_YN
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String getOrgSendYn() {
        return orgSendYn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ORG_SEND_YN
     *
     * @param orgSendYn the value for OP.T_FN_ATMS_ADD_CASH_REPORT.ORG_SEND_YN
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setOrgSendYn(String orgSendYn) {
        this.orgSendYn = orgSendYn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.UPDATE_DATE
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.UPDATE_DATE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.UPDATE_DATE
     *
     * @param updateDate the value for OP.T_FN_ATMS_ADD_CASH_REPORT.UPDATE_DATE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.UPDATE_UID
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.UPDATE_UID
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String getUpdateUid() {
        return updateUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.UPDATE_UID
     *
     * @param updateUid the value for OP.T_FN_ATMS_ADD_CASH_REPORT.UPDATE_UID
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setUpdateUid(String updateUid) {
        this.updateUid = updateUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_TIME
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_TIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String getAddTime() {
        return addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_TIME
     *
     * @param addTime the value for OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_TIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CLOSE_YN
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.CLOSE_YN
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String getCloseYn() {
        return closeYn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CLOSE_YN
     *
     * @param closeYn the value for OP.T_FN_ATMS_ADD_CASH_REPORT.CLOSE_YN
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setCloseYn(String closeYn) {
        this.closeYn = closeYn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.SEND_DATE
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.SEND_DATE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String getSendDate() {
        return sendDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.SEND_DATE
     *
     * @param sendDate the value for OP.T_FN_ATMS_ADD_CASH_REPORT.SEND_DATE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_CHECK_AMT
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_CHECK_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public Long getAddCheckAmt() {
        return addCheckAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_CHECK_AMT
     *
     * @param addCheckAmt the value for OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_CHECK_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setAddCheckAmt(Long addCheckAmt) {
        this.addCheckAmt = addCheckAmt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_10_CNT
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_10_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public Integer getCheck10Cnt() {
        return check10Cnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_10_CNT
     *
     * @param check10Cnt the value for OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_10_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setCheck10Cnt(Integer check10Cnt) {
        this.check10Cnt = check10Cnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_30_CNT
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_30_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public Integer getCheck30Cnt() {
        return check30Cnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_30_CNT
     *
     * @param check30Cnt the value for OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_30_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setCheck30Cnt(Integer check30Cnt) {
        this.check30Cnt = check30Cnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_50_CNT
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_50_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public Integer getCheck50Cnt() {
        return check50Cnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_50_CNT
     *
     * @param check50Cnt the value for OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_50_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setCheck50Cnt(Integer check50Cnt) {
        this.check50Cnt = check50Cnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_100_CNT
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_100_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public Integer getCheck100Cnt() {
        return check100Cnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_100_CNT
     *
     * @param check100Cnt the value for OP.T_FN_ATMS_ADD_CASH_REPORT.CHECK_100_CNT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setCheck100Cnt(Integer check100Cnt) {
        this.check100Cnt = check100Cnt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.SAFE_TYPE
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.SAFE_TYPE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String getSafeType() {
        return safeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.SAFE_TYPE
     *
     * @param safeType the value for OP.T_FN_ATMS_ADD_CASH_REPORT.SAFE_TYPE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setSafeType(String safeType) {
        this.safeType = safeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_50000_AMT
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_50000_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public Long getAdd50000Amt() {
        return add50000Amt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_50000_AMT
     *
     * @param add50000Amt the value for OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_50000_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setAdd50000Amt(Long add50000Amt) {
        this.add50000Amt = add50000Amt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_10000_AMT
     *
     * @return the value of OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_10000_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public Long getAdd10000Amt() {
        return add10000Amt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_10000_AMT
     *
     * @param add10000Amt the value for OP.T_FN_ATMS_ADD_CASH_REPORT.ADD_10000_AMT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public void setAdd10000Amt(Long add10000Amt) {
        this.add10000Amt = add10000Amt;
    }
}
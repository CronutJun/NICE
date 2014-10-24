package com.nicetcm.nibsplus.broker.ams.model;

import java.util.Date;

public class TRcRegInfHis extends TRcRegInfHisKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AMS.T_RC_REG_AMS._HIS.AMS.ERT_UID
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    private String insertUid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AMS.T_RC_REG_AMS._HIS.AMS.ERT_DATE
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    private Date insertDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column AMS.T_RC_REG_AMS._HIS.REG_VAL
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    private String regVal;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AMS.T_RC_REG_AMS._HIS.AMS.ERT_UID
     *
     * @return the value of AMS.T_RC_REG_AMS._HIS.AMS.ERT_UID
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public String getInsertUid() {
        return insertUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AMS.T_RC_REG_AMS._HIS.AMS.ERT_UID
     *
     * @param insertUid the value for AMS.T_RC_REG_AMS._HIS.AMS.ERT_UID
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public void setInsertUid(String insertUid) {
        this.insertUid = insertUid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AMS.T_RC_REG_AMS._HIS.AMS.ERT_DATE
     *
     * @return the value of AMS.T_RC_REG_AMS._HIS.AMS.ERT_DATE
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public Date getInsertDate() {
        return insertDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AMS.T_RC_REG_AMS._HIS.AMS.ERT_DATE
     *
     * @param insertDate the value for AMS.T_RC_REG_AMS._HIS.AMS.ERT_DATE
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column AMS.T_RC_REG_AMS._HIS.REG_VAL
     *
     * @return the value of AMS.T_RC_REG_AMS._HIS.REG_VAL
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public String getRegVal() {
        return regVal;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column AMS.T_RC_REG_AMS._HIS.REG_VAL
     *
     * @param regVal the value for AMS.T_RC_REG_AMS._HIS.REG_VAL
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public void setRegVal(String regVal) {
        this.regVal = regVal;
    }
}
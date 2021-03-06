package com.nicetcm.nibsplus.broker.ams.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TPmUpdsSchedSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public TPmUpdsSchedSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andGrpCdIsNull() {
            addCriterion("GRP_CD is null");
            return (Criteria) this;
        }

        public Criteria andGrpCdIsNotNull() {
            addCriterion("GRP_CD is not null");
            return (Criteria) this;
        }

        public Criteria andGrpCdEqualTo(String value) {
            addCriterion("GRP_CD =", value, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdNotEqualTo(String value) {
            addCriterion("GRP_CD <>", value, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdGreaterThan(String value) {
            addCriterion("GRP_CD >", value, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdGreaterThanOrEqualTo(String value) {
            addCriterion("GRP_CD >=", value, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdLessThan(String value) {
            addCriterion("GRP_CD <", value, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdLessThanOrEqualTo(String value) {
            addCriterion("GRP_CD <=", value, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdLike(String value) {
            addCriterion("GRP_CD like", value, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdNotLike(String value) {
            addCriterion("GRP_CD not like", value, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdIn(List<String> values) {
            addCriterion("GRP_CD in", values, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdNotIn(List<String> values) {
            addCriterion("GRP_CD not in", values, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdBetween(String value1, String value2) {
            addCriterion("GRP_CD between", value1, value2, "grpCd");
            return (Criteria) this;
        }

        public Criteria andGrpCdNotBetween(String value1, String value2) {
            addCriterion("GRP_CD not between", value1, value2, "grpCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdIsNull() {
            addCriterion("MKR_CD is null");
            return (Criteria) this;
        }

        public Criteria andMkrCdIsNotNull() {
            addCriterion("MKR_CD is not null");
            return (Criteria) this;
        }

        public Criteria andMkrCdEqualTo(String value) {
            addCriterion("MKR_CD =", value, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdNotEqualTo(String value) {
            addCriterion("MKR_CD <>", value, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdGreaterThan(String value) {
            addCriterion("MKR_CD >", value, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdGreaterThanOrEqualTo(String value) {
            addCriterion("MKR_CD >=", value, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdLessThan(String value) {
            addCriterion("MKR_CD <", value, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdLessThanOrEqualTo(String value) {
            addCriterion("MKR_CD <=", value, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdLike(String value) {
            addCriterion("MKR_CD like", value, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdNotLike(String value) {
            addCriterion("MKR_CD not like", value, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdIn(List<String> values) {
            addCriterion("MKR_CD in", values, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdNotIn(List<String> values) {
            addCriterion("MKR_CD not in", values, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdBetween(String value1, String value2) {
            addCriterion("MKR_CD between", value1, value2, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andMkrCdNotBetween(String value1, String value2) {
            addCriterion("MKR_CD not between", value1, value2, "mkrCd");
            return (Criteria) this;
        }

        public Criteria andModelCdIsNull() {
            addCriterion("MODEL_CD is null");
            return (Criteria) this;
        }

        public Criteria andModelCdIsNotNull() {
            addCriterion("MODEL_CD is not null");
            return (Criteria) this;
        }

        public Criteria andModelCdEqualTo(String value) {
            addCriterion("MODEL_CD =", value, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdNotEqualTo(String value) {
            addCriterion("MODEL_CD <>", value, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdGreaterThan(String value) {
            addCriterion("MODEL_CD >", value, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdGreaterThanOrEqualTo(String value) {
            addCriterion("MODEL_CD >=", value, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdLessThan(String value) {
            addCriterion("MODEL_CD <", value, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdLessThanOrEqualTo(String value) {
            addCriterion("MODEL_CD <=", value, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdLike(String value) {
            addCriterion("MODEL_CD like", value, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdNotLike(String value) {
            addCriterion("MODEL_CD not like", value, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdIn(List<String> values) {
            addCriterion("MODEL_CD in", values, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdNotIn(List<String> values) {
            addCriterion("MODEL_CD not in", values, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdBetween(String value1, String value2) {
            addCriterion("MODEL_CD between", value1, value2, "modelCd");
            return (Criteria) this;
        }

        public Criteria andModelCdNotBetween(String value1, String value2) {
            addCriterion("MODEL_CD not between", value1, value2, "modelCd");
            return (Criteria) this;
        }

        public Criteria andVerIdIsNull() {
            addCriterion("VER_ID is null");
            return (Criteria) this;
        }

        public Criteria andVerIdIsNotNull() {
            addCriterion("VER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andVerIdEqualTo(String value) {
            addCriterion("VER_ID =", value, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdNotEqualTo(String value) {
            addCriterion("VER_ID <>", value, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdGreaterThan(String value) {
            addCriterion("VER_ID >", value, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdGreaterThanOrEqualTo(String value) {
            addCriterion("VER_ID >=", value, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdLessThan(String value) {
            addCriterion("VER_ID <", value, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdLessThanOrEqualTo(String value) {
            addCriterion("VER_ID <=", value, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdLike(String value) {
            addCriterion("VER_ID like", value, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdNotLike(String value) {
            addCriterion("VER_ID not like", value, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdIn(List<String> values) {
            addCriterion("VER_ID in", values, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdNotIn(List<String> values) {
            addCriterion("VER_ID not in", values, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdBetween(String value1, String value2) {
            addCriterion("VER_ID between", value1, value2, "verId");
            return (Criteria) this;
        }

        public Criteria andVerIdNotBetween(String value1, String value2) {
            addCriterion("VER_ID not between", value1, value2, "verId");
            return (Criteria) this;
        }

        public Criteria andInsertDateIsNull() {
            addCriterion("AMS.ERT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInsertDateIsNotNull() {
            addCriterion("AMS.ERT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInsertDateEqualTo(Date value) {
            addCriterion("AMS.ERT_DATE =", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotEqualTo(Date value) {
            addCriterion("AMS.ERT_DATE <>", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateGreaterThan(Date value) {
            addCriterion("AMS.ERT_DATE >", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateGreaterThanOrEqualTo(Date value) {
            addCriterion("AMS.ERT_DATE >=", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateLessThan(Date value) {
            addCriterion("AMS.ERT_DATE <", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateLessThanOrEqualTo(Date value) {
            addCriterion("AMS.ERT_DATE <=", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateIn(List<Date> values) {
            addCriterion("AMS.ERT_DATE in", values, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotIn(List<Date> values) {
            addCriterion("AMS.ERT_DATE not in", values, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateBetween(Date value1, Date value2) {
            addCriterion("AMS.ERT_DATE between", value1, value2, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotBetween(Date value1, Date value2) {
            addCriterion("AMS.ERT_DATE not between", value1, value2, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertUidIsNull() {
            addCriterion("AMS.ERT_UID is null");
            return (Criteria) this;
        }

        public Criteria andInsertUidIsNotNull() {
            addCriterion("AMS.ERT_UID is not null");
            return (Criteria) this;
        }

        public Criteria andInsertUidEqualTo(String value) {
            addCriterion("AMS.ERT_UID =", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidNotEqualTo(String value) {
            addCriterion("AMS.ERT_UID <>", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidGreaterThan(String value) {
            addCriterion("AMS.ERT_UID >", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidGreaterThanOrEqualTo(String value) {
            addCriterion("AMS.ERT_UID >=", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidLessThan(String value) {
            addCriterion("AMS.ERT_UID <", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidLessThanOrEqualTo(String value) {
            addCriterion("AMS.ERT_UID <=", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidLike(String value) {
            addCriterion("AMS.ERT_UID like", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidNotLike(String value) {
            addCriterion("AMS.ERT_UID not like", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidIn(List<String> values) {
            addCriterion("AMS.ERT_UID in", values, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidNotIn(List<String> values) {
            addCriterion("AMS.ERT_UID not in", values, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidBetween(String value1, String value2) {
            addCriterion("AMS.ERT_UID between", value1, value2, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidNotBetween(String value1, String value2) {
            addCriterion("AMS.ERT_UID not between", value1, value2, "insertUid");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNull() {
            addCriterion("UPDATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIsNotNull() {
            addCriterion("UPDATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateDateEqualTo(Date value) {
            addCriterion("UPDATE_DATE =", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotEqualTo(Date value) {
            addCriterion("UPDATE_DATE <>", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThan(Date value) {
            addCriterion("UPDATE_DATE >", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE >=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThan(Date value) {
            addCriterion("UPDATE_DATE <", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_DATE <=", value, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateIn(List<Date> values) {
            addCriterion("UPDATE_DATE in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotIn(List<Date> values) {
            addCriterion("UPDATE_DATE not in", values, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_DATE not between", value1, value2, "updateDate");
            return (Criteria) this;
        }

        public Criteria andUpdateUidIsNull() {
            addCriterion("UPDATE_UID is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUidIsNotNull() {
            addCriterion("UPDATE_UID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUidEqualTo(String value) {
            addCriterion("UPDATE_UID =", value, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidNotEqualTo(String value) {
            addCriterion("UPDATE_UID <>", value, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidGreaterThan(String value) {
            addCriterion("UPDATE_UID >", value, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_UID >=", value, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidLessThan(String value) {
            addCriterion("UPDATE_UID <", value, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_UID <=", value, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidLike(String value) {
            addCriterion("UPDATE_UID like", value, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidNotLike(String value) {
            addCriterion("UPDATE_UID not like", value, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidIn(List<String> values) {
            addCriterion("UPDATE_UID in", values, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidNotIn(List<String> values) {
            addCriterion("UPDATE_UID not in", values, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidBetween(String value1, String value2) {
            addCriterion("UPDATE_UID between", value1, value2, "updateUid");
            return (Criteria) this;
        }

        public Criteria andUpdateUidNotBetween(String value1, String value2) {
            addCriterion("UPDATE_UID not between", value1, value2, "updateUid");
            return (Criteria) this;
        }

        public Criteria andDeployDateIsNull() {
            addCriterion("DEPLOY_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDeployDateIsNotNull() {
            addCriterion("DEPLOY_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDeployDateEqualTo(String value) {
            addCriterion("DEPLOY_DATE =", value, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateNotEqualTo(String value) {
            addCriterion("DEPLOY_DATE <>", value, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateGreaterThan(String value) {
            addCriterion("DEPLOY_DATE >", value, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateGreaterThanOrEqualTo(String value) {
            addCriterion("DEPLOY_DATE >=", value, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateLessThan(String value) {
            addCriterion("DEPLOY_DATE <", value, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateLessThanOrEqualTo(String value) {
            addCriterion("DEPLOY_DATE <=", value, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateLike(String value) {
            addCriterion("DEPLOY_DATE like", value, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateNotLike(String value) {
            addCriterion("DEPLOY_DATE not like", value, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateIn(List<String> values) {
            addCriterion("DEPLOY_DATE in", values, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateNotIn(List<String> values) {
            addCriterion("DEPLOY_DATE not in", values, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateBetween(String value1, String value2) {
            addCriterion("DEPLOY_DATE between", value1, value2, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployDateNotBetween(String value1, String value2) {
            addCriterion("DEPLOY_DATE not between", value1, value2, "deployDate");
            return (Criteria) this;
        }

        public Criteria andDeployTimeIsNull() {
            addCriterion("DEPLOY_TIME is null");
            return (Criteria) this;
        }

        public Criteria andDeployTimeIsNotNull() {
            addCriterion("DEPLOY_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andDeployTimeEqualTo(String value) {
            addCriterion("DEPLOY_TIME =", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeNotEqualTo(String value) {
            addCriterion("DEPLOY_TIME <>", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeGreaterThan(String value) {
            addCriterion("DEPLOY_TIME >", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeGreaterThanOrEqualTo(String value) {
            addCriterion("DEPLOY_TIME >=", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeLessThan(String value) {
            addCriterion("DEPLOY_TIME <", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeLessThanOrEqualTo(String value) {
            addCriterion("DEPLOY_TIME <=", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeLike(String value) {
            addCriterion("DEPLOY_TIME like", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeNotLike(String value) {
            addCriterion("DEPLOY_TIME not like", value, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeIn(List<String> values) {
            addCriterion("DEPLOY_TIME in", values, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeNotIn(List<String> values) {
            addCriterion("DEPLOY_TIME not in", values, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeBetween(String value1, String value2) {
            addCriterion("DEPLOY_TIME between", value1, value2, "deployTime");
            return (Criteria) this;
        }

        public Criteria andDeployTimeNotBetween(String value1, String value2) {
            addCriterion("DEPLOY_TIME not between", value1, value2, "deployTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated do_not_delete_during_merge Thu Sep 18 14:36:19 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table AMS.T_PM_UPDS_SCHED
     *
     * @mbggenerated Thu Sep 18 14:36:19 KST 2014
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
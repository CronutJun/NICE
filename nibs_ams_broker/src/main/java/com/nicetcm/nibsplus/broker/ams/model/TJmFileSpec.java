package com.nicetcm.nibsplus.broker.ams.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TJmFileSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public TJmFileSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
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
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
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

        public Criteria andMacTrxDateIsNull() {
            addCriterion("MAC_TRX_DATE is null");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateIsNotNull() {
            addCriterion("MAC_TRX_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateEqualTo(String value) {
            addCriterion("MAC_TRX_DATE =", value, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateNotEqualTo(String value) {
            addCriterion("MAC_TRX_DATE <>", value, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateGreaterThan(String value) {
            addCriterion("MAC_TRX_DATE >", value, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateGreaterThanOrEqualTo(String value) {
            addCriterion("MAC_TRX_DATE >=", value, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateLessThan(String value) {
            addCriterion("MAC_TRX_DATE <", value, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateLessThanOrEqualTo(String value) {
            addCriterion("MAC_TRX_DATE <=", value, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateLike(String value) {
            addCriterion("MAC_TRX_DATE like", value, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateNotLike(String value) {
            addCriterion("MAC_TRX_DATE not like", value, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateIn(List<String> values) {
            addCriterion("MAC_TRX_DATE in", values, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateNotIn(List<String> values) {
            addCriterion("MAC_TRX_DATE not in", values, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateBetween(String value1, String value2) {
            addCriterion("MAC_TRX_DATE between", value1, value2, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andMacTrxDateNotBetween(String value1, String value2) {
            addCriterion("MAC_TRX_DATE not between", value1, value2, "macTrxDate");
            return (Criteria) this;
        }

        public Criteria andOrgCdIsNull() {
            addCriterion("ORG_CD is null");
            return (Criteria) this;
        }

        public Criteria andOrgCdIsNotNull() {
            addCriterion("ORG_CD is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCdEqualTo(String value) {
            addCriterion("ORG_CD =", value, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdNotEqualTo(String value) {
            addCriterion("ORG_CD <>", value, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdGreaterThan(String value) {
            addCriterion("ORG_CD >", value, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_CD >=", value, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdLessThan(String value) {
            addCriterion("ORG_CD <", value, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdLessThanOrEqualTo(String value) {
            addCriterion("ORG_CD <=", value, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdLike(String value) {
            addCriterion("ORG_CD like", value, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdNotLike(String value) {
            addCriterion("ORG_CD not like", value, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdIn(List<String> values) {
            addCriterion("ORG_CD in", values, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdNotIn(List<String> values) {
            addCriterion("ORG_CD not in", values, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdBetween(String value1, String value2) {
            addCriterion("ORG_CD between", value1, value2, "orgCd");
            return (Criteria) this;
        }

        public Criteria andOrgCdNotBetween(String value1, String value2) {
            addCriterion("ORG_CD not between", value1, value2, "orgCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdIsNull() {
            addCriterion("BRANCH_CD is null");
            return (Criteria) this;
        }

        public Criteria andBranchCdIsNotNull() {
            addCriterion("BRANCH_CD is not null");
            return (Criteria) this;
        }

        public Criteria andBranchCdEqualTo(String value) {
            addCriterion("BRANCH_CD =", value, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdNotEqualTo(String value) {
            addCriterion("BRANCH_CD <>", value, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdGreaterThan(String value) {
            addCriterion("BRANCH_CD >", value, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdGreaterThanOrEqualTo(String value) {
            addCriterion("BRANCH_CD >=", value, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdLessThan(String value) {
            addCriterion("BRANCH_CD <", value, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdLessThanOrEqualTo(String value) {
            addCriterion("BRANCH_CD <=", value, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdLike(String value) {
            addCriterion("BRANCH_CD like", value, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdNotLike(String value) {
            addCriterion("BRANCH_CD not like", value, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdIn(List<String> values) {
            addCriterion("BRANCH_CD in", values, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdNotIn(List<String> values) {
            addCriterion("BRANCH_CD not in", values, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdBetween(String value1, String value2) {
            addCriterion("BRANCH_CD between", value1, value2, "branchCd");
            return (Criteria) this;
        }

        public Criteria andBranchCdNotBetween(String value1, String value2) {
            addCriterion("BRANCH_CD not between", value1, value2, "branchCd");
            return (Criteria) this;
        }

        public Criteria andMacNoIsNull() {
            addCriterion("MAC_NO is null");
            return (Criteria) this;
        }

        public Criteria andMacNoIsNotNull() {
            addCriterion("MAC_NO is not null");
            return (Criteria) this;
        }

        public Criteria andMacNoEqualTo(String value) {
            addCriterion("MAC_NO =", value, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoNotEqualTo(String value) {
            addCriterion("MAC_NO <>", value, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoGreaterThan(String value) {
            addCriterion("MAC_NO >", value, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoGreaterThanOrEqualTo(String value) {
            addCriterion("MAC_NO >=", value, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoLessThan(String value) {
            addCriterion("MAC_NO <", value, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoLessThanOrEqualTo(String value) {
            addCriterion("MAC_NO <=", value, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoLike(String value) {
            addCriterion("MAC_NO like", value, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoNotLike(String value) {
            addCriterion("MAC_NO not like", value, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoIn(List<String> values) {
            addCriterion("MAC_NO in", values, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoNotIn(List<String> values) {
            addCriterion("MAC_NO not in", values, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoBetween(String value1, String value2) {
            addCriterion("MAC_NO between", value1, value2, "macNo");
            return (Criteria) this;
        }

        public Criteria andMacNoNotBetween(String value1, String value2) {
            addCriterion("MAC_NO not between", value1, value2, "macNo");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNull() {
            addCriterion("FILE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andFileNameIsNotNull() {
            addCriterion("FILE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andFileNameEqualTo(String value) {
            addCriterion("FILE_NAME =", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotEqualTo(String value) {
            addCriterion("FILE_NAME <>", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThan(String value) {
            addCriterion("FILE_NAME >", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_NAME >=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThan(String value) {
            addCriterion("FILE_NAME <", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLessThanOrEqualTo(String value) {
            addCriterion("FILE_NAME <=", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameLike(String value) {
            addCriterion("FILE_NAME like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotLike(String value) {
            addCriterion("FILE_NAME not like", value, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameIn(List<String> values) {
            addCriterion("FILE_NAME in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotIn(List<String> values) {
            addCriterion("FILE_NAME not in", values, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameBetween(String value1, String value2) {
            addCriterion("FILE_NAME between", value1, value2, "fileName");
            return (Criteria) this;
        }

        public Criteria andFileNameNotBetween(String value1, String value2) {
            addCriterion("FILE_NAME not between", value1, value2, "fileName");
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

        public Criteria andFilePathIsNull() {
            addCriterion("FILE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andFilePathIsNotNull() {
            addCriterion("FILE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andFilePathEqualTo(String value) {
            addCriterion("FILE_PATH =", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotEqualTo(String value) {
            addCriterion("FILE_PATH <>", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThan(String value) {
            addCriterion("FILE_PATH >", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("FILE_PATH >=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThan(String value) {
            addCriterion("FILE_PATH <", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLessThanOrEqualTo(String value) {
            addCriterion("FILE_PATH <=", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathLike(String value) {
            addCriterion("FILE_PATH like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotLike(String value) {
            addCriterion("FILE_PATH not like", value, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathIn(List<String> values) {
            addCriterion("FILE_PATH in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotIn(List<String> values) {
            addCriterion("FILE_PATH not in", values, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathBetween(String value1, String value2) {
            addCriterion("FILE_PATH between", value1, value2, "filePath");
            return (Criteria) this;
        }

        public Criteria andFilePathNotBetween(String value1, String value2) {
            addCriterion("FILE_PATH not between", value1, value2, "filePath");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated do_not_delete_during_merge Thu Sep 04 09:48:36 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table AMS.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
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
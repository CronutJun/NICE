package com.nicetcm.nibsplus.broker.msg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCmBranchEmartsuperSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    public TCmBranchEmartsuperSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
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
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
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

        public Criteria andBranchNmIsNull() {
            addCriterion("BRANCH_NM is null");
            return (Criteria) this;
        }

        public Criteria andBranchNmIsNotNull() {
            addCriterion("BRANCH_NM is not null");
            return (Criteria) this;
        }

        public Criteria andBranchNmEqualTo(String value) {
            addCriterion("BRANCH_NM =", value, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmNotEqualTo(String value) {
            addCriterion("BRANCH_NM <>", value, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmGreaterThan(String value) {
            addCriterion("BRANCH_NM >", value, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmGreaterThanOrEqualTo(String value) {
            addCriterion("BRANCH_NM >=", value, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmLessThan(String value) {
            addCriterion("BRANCH_NM <", value, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmLessThanOrEqualTo(String value) {
            addCriterion("BRANCH_NM <=", value, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmLike(String value) {
            addCriterion("BRANCH_NM like", value, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmNotLike(String value) {
            addCriterion("BRANCH_NM not like", value, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmIn(List<String> values) {
            addCriterion("BRANCH_NM in", values, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmNotIn(List<String> values) {
            addCriterion("BRANCH_NM not in", values, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmBetween(String value1, String value2) {
            addCriterion("BRANCH_NM between", value1, value2, "branchNm");
            return (Criteria) this;
        }

        public Criteria andBranchNmNotBetween(String value1, String value2) {
            addCriterion("BRANCH_NM not between", value1, value2, "branchNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmIsNull() {
            addCriterion("STOREKEEPER_NM is null");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmIsNotNull() {
            addCriterion("STOREKEEPER_NM is not null");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmEqualTo(String value) {
            addCriterion("STOREKEEPER_NM =", value, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmNotEqualTo(String value) {
            addCriterion("STOREKEEPER_NM <>", value, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmGreaterThan(String value) {
            addCriterion("STOREKEEPER_NM >", value, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmGreaterThanOrEqualTo(String value) {
            addCriterion("STOREKEEPER_NM >=", value, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmLessThan(String value) {
            addCriterion("STOREKEEPER_NM <", value, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmLessThanOrEqualTo(String value) {
            addCriterion("STOREKEEPER_NM <=", value, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmLike(String value) {
            addCriterion("STOREKEEPER_NM like", value, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmNotLike(String value) {
            addCriterion("STOREKEEPER_NM not like", value, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmIn(List<String> values) {
            addCriterion("STOREKEEPER_NM in", values, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmNotIn(List<String> values) {
            addCriterion("STOREKEEPER_NM not in", values, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmBetween(String value1, String value2) {
            addCriterion("STOREKEEPER_NM between", value1, value2, "storekeeperNm");
            return (Criteria) this;
        }

        public Criteria andStorekeeperNmNotBetween(String value1, String value2) {
            addCriterion("STOREKEEPER_NM not between", value1, value2, "storekeeperNm");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated do_not_delete_during_merge Mon Aug 04 13:28:32 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CM_BRANCH_EMARTSUPER
     *
     * @mbggenerated Mon Aug 04 13:28:32 KST 2014
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
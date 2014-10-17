package com.nicetcm.nibsplus.broker.msg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TFnArpcFaultSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public TFnArpcFaultSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
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
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
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

        public Criteria andDealYearIsNull() {
            addCriterion("DEAL_YEAR is null");
            return (Criteria) this;
        }

        public Criteria andDealYearIsNotNull() {
            addCriterion("DEAL_YEAR is not null");
            return (Criteria) this;
        }

        public Criteria andDealYearEqualTo(String value) {
            addCriterion("DEAL_YEAR =", value, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearNotEqualTo(String value) {
            addCriterion("DEAL_YEAR <>", value, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearGreaterThan(String value) {
            addCriterion("DEAL_YEAR >", value, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearGreaterThanOrEqualTo(String value) {
            addCriterion("DEAL_YEAR >=", value, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearLessThan(String value) {
            addCriterion("DEAL_YEAR <", value, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearLessThanOrEqualTo(String value) {
            addCriterion("DEAL_YEAR <=", value, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearLike(String value) {
            addCriterion("DEAL_YEAR like", value, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearNotLike(String value) {
            addCriterion("DEAL_YEAR not like", value, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearIn(List<String> values) {
            addCriterion("DEAL_YEAR in", values, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearNotIn(List<String> values) {
            addCriterion("DEAL_YEAR not in", values, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearBetween(String value1, String value2) {
            addCriterion("DEAL_YEAR between", value1, value2, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealYearNotBetween(String value1, String value2) {
            addCriterion("DEAL_YEAR not between", value1, value2, "dealYear");
            return (Criteria) this;
        }

        public Criteria andDealNoIsNull() {
            addCriterion("DEAL_NO is null");
            return (Criteria) this;
        }

        public Criteria andDealNoIsNotNull() {
            addCriterion("DEAL_NO is not null");
            return (Criteria) this;
        }

        public Criteria andDealNoEqualTo(String value) {
            addCriterion("DEAL_NO =", value, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoNotEqualTo(String value) {
            addCriterion("DEAL_NO <>", value, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoGreaterThan(String value) {
            addCriterion("DEAL_NO >", value, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoGreaterThanOrEqualTo(String value) {
            addCriterion("DEAL_NO >=", value, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoLessThan(String value) {
            addCriterion("DEAL_NO <", value, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoLessThanOrEqualTo(String value) {
            addCriterion("DEAL_NO <=", value, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoLike(String value) {
            addCriterion("DEAL_NO like", value, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoNotLike(String value) {
            addCriterion("DEAL_NO not like", value, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoIn(List<String> values) {
            addCriterion("DEAL_NO in", values, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoNotIn(List<String> values) {
            addCriterion("DEAL_NO not in", values, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoBetween(String value1, String value2) {
            addCriterion("DEAL_NO between", value1, value2, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealNoNotBetween(String value1, String value2) {
            addCriterion("DEAL_NO not between", value1, value2, "dealNo");
            return (Criteria) this;
        }

        public Criteria andDealDateIsNull() {
            addCriterion("DEAL_DATE is null");
            return (Criteria) this;
        }

        public Criteria andDealDateIsNotNull() {
            addCriterion("DEAL_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andDealDateEqualTo(String value) {
            addCriterion("DEAL_DATE =", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotEqualTo(String value) {
            addCriterion("DEAL_DATE <>", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateGreaterThan(String value) {
            addCriterion("DEAL_DATE >", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateGreaterThanOrEqualTo(String value) {
            addCriterion("DEAL_DATE >=", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateLessThan(String value) {
            addCriterion("DEAL_DATE <", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateLessThanOrEqualTo(String value) {
            addCriterion("DEAL_DATE <=", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateLike(String value) {
            addCriterion("DEAL_DATE like", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotLike(String value) {
            addCriterion("DEAL_DATE not like", value, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateIn(List<String> values) {
            addCriterion("DEAL_DATE in", values, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotIn(List<String> values) {
            addCriterion("DEAL_DATE not in", values, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateBetween(String value1, String value2) {
            addCriterion("DEAL_DATE between", value1, value2, "dealDate");
            return (Criteria) this;
        }

        public Criteria andDealDateNotBetween(String value1, String value2) {
            addCriterion("DEAL_DATE not between", value1, value2, "dealDate");
            return (Criteria) this;
        }

        public Criteria andApplyYnIsNull() {
            addCriterion("APPLY_YN is null");
            return (Criteria) this;
        }

        public Criteria andApplyYnIsNotNull() {
            addCriterion("APPLY_YN is not null");
            return (Criteria) this;
        }

        public Criteria andApplyYnEqualTo(String value) {
            addCriterion("APPLY_YN =", value, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnNotEqualTo(String value) {
            addCriterion("APPLY_YN <>", value, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnGreaterThan(String value) {
            addCriterion("APPLY_YN >", value, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnGreaterThanOrEqualTo(String value) {
            addCriterion("APPLY_YN >=", value, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnLessThan(String value) {
            addCriterion("APPLY_YN <", value, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnLessThanOrEqualTo(String value) {
            addCriterion("APPLY_YN <=", value, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnLike(String value) {
            addCriterion("APPLY_YN like", value, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnNotLike(String value) {
            addCriterion("APPLY_YN not like", value, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnIn(List<String> values) {
            addCriterion("APPLY_YN in", values, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnNotIn(List<String> values) {
            addCriterion("APPLY_YN not in", values, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnBetween(String value1, String value2) {
            addCriterion("APPLY_YN between", value1, value2, "applyYn");
            return (Criteria) this;
        }

        public Criteria andApplyYnNotBetween(String value1, String value2) {
            addCriterion("APPLY_YN not between", value1, value2, "applyYn");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated do_not_delete_during_merge Fri Oct 17 10:53:18 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
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
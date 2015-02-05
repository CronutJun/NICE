package com.nicetcm.nibsplus.broker.msg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TFnNiceTranNhFeeSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    public TFnNiceTranNhFeeSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
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
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
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

        public Criteria andSrcCustFeeIsNull() {
            addCriterion("SRC_CUST_FEE is null");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeIsNotNull() {
            addCriterion("SRC_CUST_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeEqualTo(Integer value) {
            addCriterion("SRC_CUST_FEE =", value, "srcCustFee");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeNotEqualTo(Integer value) {
            addCriterion("SRC_CUST_FEE <>", value, "srcCustFee");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeGreaterThan(Integer value) {
            addCriterion("SRC_CUST_FEE >", value, "srcCustFee");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("SRC_CUST_FEE >=", value, "srcCustFee");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeLessThan(Integer value) {
            addCriterion("SRC_CUST_FEE <", value, "srcCustFee");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeLessThanOrEqualTo(Integer value) {
            addCriterion("SRC_CUST_FEE <=", value, "srcCustFee");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeIn(List<Integer> values) {
            addCriterion("SRC_CUST_FEE in", values, "srcCustFee");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeNotIn(List<Integer> values) {
            addCriterion("SRC_CUST_FEE not in", values, "srcCustFee");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeBetween(Integer value1, Integer value2) {
            addCriterion("SRC_CUST_FEE between", value1, value2, "srcCustFee");
            return (Criteria) this;
        }

        public Criteria andSrcCustFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("SRC_CUST_FEE not between", value1, value2, "srcCustFee");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeIsNull() {
            addCriterion("SRC_BANK_FEE is null");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeIsNotNull() {
            addCriterion("SRC_BANK_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeEqualTo(Integer value) {
            addCriterion("SRC_BANK_FEE =", value, "srcBankFee");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeNotEqualTo(Integer value) {
            addCriterion("SRC_BANK_FEE <>", value, "srcBankFee");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeGreaterThan(Integer value) {
            addCriterion("SRC_BANK_FEE >", value, "srcBankFee");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("SRC_BANK_FEE >=", value, "srcBankFee");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeLessThan(Integer value) {
            addCriterion("SRC_BANK_FEE <", value, "srcBankFee");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeLessThanOrEqualTo(Integer value) {
            addCriterion("SRC_BANK_FEE <=", value, "srcBankFee");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeIn(List<Integer> values) {
            addCriterion("SRC_BANK_FEE in", values, "srcBankFee");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeNotIn(List<Integer> values) {
            addCriterion("SRC_BANK_FEE not in", values, "srcBankFee");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeBetween(Integer value1, Integer value2) {
            addCriterion("SRC_BANK_FEE between", value1, value2, "srcBankFee");
            return (Criteria) this;
        }

        public Criteria andSrcBankFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("SRC_BANK_FEE not between", value1, value2, "srcBankFee");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeIsNull() {
            addCriterion("SRC_DEAL_TIME_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeIsNotNull() {
            addCriterion("SRC_DEAL_TIME_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeEqualTo(String value) {
            addCriterion("SRC_DEAL_TIME_TYPE =", value, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeNotEqualTo(String value) {
            addCriterion("SRC_DEAL_TIME_TYPE <>", value, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeGreaterThan(String value) {
            addCriterion("SRC_DEAL_TIME_TYPE >", value, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SRC_DEAL_TIME_TYPE >=", value, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeLessThan(String value) {
            addCriterion("SRC_DEAL_TIME_TYPE <", value, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeLessThanOrEqualTo(String value) {
            addCriterion("SRC_DEAL_TIME_TYPE <=", value, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeLike(String value) {
            addCriterion("SRC_DEAL_TIME_TYPE like", value, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeNotLike(String value) {
            addCriterion("SRC_DEAL_TIME_TYPE not like", value, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeIn(List<String> values) {
            addCriterion("SRC_DEAL_TIME_TYPE in", values, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeNotIn(List<String> values) {
            addCriterion("SRC_DEAL_TIME_TYPE not in", values, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeBetween(String value1, String value2) {
            addCriterion("SRC_DEAL_TIME_TYPE between", value1, value2, "srcDealTimeType");
            return (Criteria) this;
        }

        public Criteria andSrcDealTimeTypeNotBetween(String value1, String value2) {
            addCriterion("SRC_DEAL_TIME_TYPE not between", value1, value2, "srcDealTimeType");
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
     * This class corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated do_not_delete_during_merge Tue Feb 03 14:18:21 KST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
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
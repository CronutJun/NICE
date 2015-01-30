package com.nicetcm.nibsplus.broker.msg.model;

import java.util.ArrayList;
import java.util.List;

public class TFnRefundAmtSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    public TFnRefundAmtSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
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
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
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

        public Criteria andReqDateIsNull() {
            addCriterion("REQ_DATE is null");
            return (Criteria) this;
        }

        public Criteria andReqDateIsNotNull() {
            addCriterion("REQ_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andReqDateEqualTo(String value) {
            addCriterion("REQ_DATE =", value, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateNotEqualTo(String value) {
            addCriterion("REQ_DATE <>", value, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateGreaterThan(String value) {
            addCriterion("REQ_DATE >", value, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_DATE >=", value, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateLessThan(String value) {
            addCriterion("REQ_DATE <", value, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateLessThanOrEqualTo(String value) {
            addCriterion("REQ_DATE <=", value, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateLike(String value) {
            addCriterion("REQ_DATE like", value, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateNotLike(String value) {
            addCriterion("REQ_DATE not like", value, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateIn(List<String> values) {
            addCriterion("REQ_DATE in", values, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateNotIn(List<String> values) {
            addCriterion("REQ_DATE not in", values, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateBetween(String value1, String value2) {
            addCriterion("REQ_DATE between", value1, value2, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqDateNotBetween(String value1, String value2) {
            addCriterion("REQ_DATE not between", value1, value2, "reqDate");
            return (Criteria) this;
        }

        public Criteria andReqTimeIsNull() {
            addCriterion("REQ_TIME is null");
            return (Criteria) this;
        }

        public Criteria andReqTimeIsNotNull() {
            addCriterion("REQ_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andReqTimeEqualTo(String value) {
            addCriterion("REQ_TIME =", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeNotEqualTo(String value) {
            addCriterion("REQ_TIME <>", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeGreaterThan(String value) {
            addCriterion("REQ_TIME >", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeGreaterThanOrEqualTo(String value) {
            addCriterion("REQ_TIME >=", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeLessThan(String value) {
            addCriterion("REQ_TIME <", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeLessThanOrEqualTo(String value) {
            addCriterion("REQ_TIME <=", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeLike(String value) {
            addCriterion("REQ_TIME like", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeNotLike(String value) {
            addCriterion("REQ_TIME not like", value, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeIn(List<String> values) {
            addCriterion("REQ_TIME in", values, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeNotIn(List<String> values) {
            addCriterion("REQ_TIME not in", values, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeBetween(String value1, String value2) {
            addCriterion("REQ_TIME between", value1, value2, "reqTime");
            return (Criteria) this;
        }

        public Criteria andReqTimeNotBetween(String value1, String value2) {
            addCriterion("REQ_TIME not between", value1, value2, "reqTime");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNull() {
            addCriterion("SEQ_NO is null");
            return (Criteria) this;
        }

        public Criteria andSeqNoIsNotNull() {
            addCriterion("SEQ_NO is not null");
            return (Criteria) this;
        }

        public Criteria andSeqNoEqualTo(String value) {
            addCriterion("SEQ_NO =", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotEqualTo(String value) {
            addCriterion("SEQ_NO <>", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThan(String value) {
            addCriterion("SEQ_NO >", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoGreaterThanOrEqualTo(String value) {
            addCriterion("SEQ_NO >=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThan(String value) {
            addCriterion("SEQ_NO <", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLessThanOrEqualTo(String value) {
            addCriterion("SEQ_NO <=", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoLike(String value) {
            addCriterion("SEQ_NO like", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotLike(String value) {
            addCriterion("SEQ_NO not like", value, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoIn(List<String> values) {
            addCriterion("SEQ_NO in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotIn(List<String> values) {
            addCriterion("SEQ_NO not in", values, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoBetween(String value1, String value2) {
            addCriterion("SEQ_NO between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andSeqNoNotBetween(String value1, String value2) {
            addCriterion("SEQ_NO not between", value1, value2, "seqNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNull() {
            addCriterion("ACCOUNT_NO is null");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNotNull() {
            addCriterion("ACCOUNT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNoEqualTo(String value) {
            addCriterion("ACCOUNT_NO =", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotEqualTo(String value) {
            addCriterion("ACCOUNT_NO <>", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThan(String value) {
            addCriterion("ACCOUNT_NO >", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NO >=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThan(String value) {
            addCriterion("ACCOUNT_NO <", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NO <=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLike(String value) {
            addCriterion("ACCOUNT_NO like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotLike(String value) {
            addCriterion("ACCOUNT_NO not like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIn(List<String> values) {
            addCriterion("ACCOUNT_NO in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotIn(List<String> values) {
            addCriterion("ACCOUNT_NO not in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NO between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NO not between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andDealAmtIsNull() {
            addCriterion("DEAL_AMT is null");
            return (Criteria) this;
        }

        public Criteria andDealAmtIsNotNull() {
            addCriterion("DEAL_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andDealAmtEqualTo(String value) {
            addCriterion("DEAL_AMT =", value, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtNotEqualTo(String value) {
            addCriterion("DEAL_AMT <>", value, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtGreaterThan(String value) {
            addCriterion("DEAL_AMT >", value, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtGreaterThanOrEqualTo(String value) {
            addCriterion("DEAL_AMT >=", value, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtLessThan(String value) {
            addCriterion("DEAL_AMT <", value, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtLessThanOrEqualTo(String value) {
            addCriterion("DEAL_AMT <=", value, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtLike(String value) {
            addCriterion("DEAL_AMT like", value, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtNotLike(String value) {
            addCriterion("DEAL_AMT not like", value, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtIn(List<String> values) {
            addCriterion("DEAL_AMT in", values, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtNotIn(List<String> values) {
            addCriterion("DEAL_AMT not in", values, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtBetween(String value1, String value2) {
            addCriterion("DEAL_AMT between", value1, value2, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andDealAmtNotBetween(String value1, String value2) {
            addCriterion("DEAL_AMT not between", value1, value2, "dealAmt");
            return (Criteria) this;
        }

        public Criteria andResultYnIsNull() {
            addCriterion("RESULT_YN is null");
            return (Criteria) this;
        }

        public Criteria andResultYnIsNotNull() {
            addCriterion("RESULT_YN is not null");
            return (Criteria) this;
        }

        public Criteria andResultYnEqualTo(String value) {
            addCriterion("RESULT_YN =", value, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnNotEqualTo(String value) {
            addCriterion("RESULT_YN <>", value, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnGreaterThan(String value) {
            addCriterion("RESULT_YN >", value, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnGreaterThanOrEqualTo(String value) {
            addCriterion("RESULT_YN >=", value, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnLessThan(String value) {
            addCriterion("RESULT_YN <", value, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnLessThanOrEqualTo(String value) {
            addCriterion("RESULT_YN <=", value, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnLike(String value) {
            addCriterion("RESULT_YN like", value, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnNotLike(String value) {
            addCriterion("RESULT_YN not like", value, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnIn(List<String> values) {
            addCriterion("RESULT_YN in", values, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnNotIn(List<String> values) {
            addCriterion("RESULT_YN not in", values, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnBetween(String value1, String value2) {
            addCriterion("RESULT_YN between", value1, value2, "resultYn");
            return (Criteria) this;
        }

        public Criteria andResultYnNotBetween(String value1, String value2) {
            addCriterion("RESULT_YN not between", value1, value2, "resultYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnIsNull() {
            addCriterion("ORG_SEND_YN is null");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnIsNotNull() {
            addCriterion("ORG_SEND_YN is not null");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnEqualTo(String value) {
            addCriterion("ORG_SEND_YN =", value, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnNotEqualTo(String value) {
            addCriterion("ORG_SEND_YN <>", value, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnGreaterThan(String value) {
            addCriterion("ORG_SEND_YN >", value, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_SEND_YN >=", value, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnLessThan(String value) {
            addCriterion("ORG_SEND_YN <", value, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnLessThanOrEqualTo(String value) {
            addCriterion("ORG_SEND_YN <=", value, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnLike(String value) {
            addCriterion("ORG_SEND_YN like", value, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnNotLike(String value) {
            addCriterion("ORG_SEND_YN not like", value, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnIn(List<String> values) {
            addCriterion("ORG_SEND_YN in", values, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnNotIn(List<String> values) {
            addCriterion("ORG_SEND_YN not in", values, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnBetween(String value1, String value2) {
            addCriterion("ORG_SEND_YN between", value1, value2, "orgSendYn");
            return (Criteria) this;
        }

        public Criteria andOrgSendYnNotBetween(String value1, String value2) {
            addCriterion("ORG_SEND_YN not between", value1, value2, "orgSendYn");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated do_not_delete_during_merge Mon Jan 26 18:05:49 KST 2015
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_REFUND_AMT
     *
     * @mbggenerated Mon Jan 26 18:05:49 KST 2015
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
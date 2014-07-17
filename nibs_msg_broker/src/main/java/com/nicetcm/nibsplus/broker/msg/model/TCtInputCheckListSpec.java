package com.nicetcm.nibsplus.broker.msg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCtInputCheckListSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public TCtInputCheckListSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
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
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
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

        public Criteria andFromDateIsNull() {
            addCriterion("FROM_DATE is null");
            return (Criteria) this;
        }

        public Criteria andFromDateIsNotNull() {
            addCriterion("FROM_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andFromDateEqualTo(String value) {
            addCriterion("FROM_DATE =", value, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateNotEqualTo(String value) {
            addCriterion("FROM_DATE <>", value, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateGreaterThan(String value) {
            addCriterion("FROM_DATE >", value, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateGreaterThanOrEqualTo(String value) {
            addCriterion("FROM_DATE >=", value, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateLessThan(String value) {
            addCriterion("FROM_DATE <", value, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateLessThanOrEqualTo(String value) {
            addCriterion("FROM_DATE <=", value, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateLike(String value) {
            addCriterion("FROM_DATE like", value, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateNotLike(String value) {
            addCriterion("FROM_DATE not like", value, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateIn(List<String> values) {
            addCriterion("FROM_DATE in", values, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateNotIn(List<String> values) {
            addCriterion("FROM_DATE not in", values, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateBetween(String value1, String value2) {
            addCriterion("FROM_DATE between", value1, value2, "fromDate");
            return (Criteria) this;
        }

        public Criteria andFromDateNotBetween(String value1, String value2) {
            addCriterion("FROM_DATE not between", value1, value2, "fromDate");
            return (Criteria) this;
        }

        public Criteria andToDateIsNull() {
            addCriterion("TO_DATE is null");
            return (Criteria) this;
        }

        public Criteria andToDateIsNotNull() {
            addCriterion("TO_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andToDateEqualTo(String value) {
            addCriterion("TO_DATE =", value, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateNotEqualTo(String value) {
            addCriterion("TO_DATE <>", value, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateGreaterThan(String value) {
            addCriterion("TO_DATE >", value, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateGreaterThanOrEqualTo(String value) {
            addCriterion("TO_DATE >=", value, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateLessThan(String value) {
            addCriterion("TO_DATE <", value, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateLessThanOrEqualTo(String value) {
            addCriterion("TO_DATE <=", value, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateLike(String value) {
            addCriterion("TO_DATE like", value, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateNotLike(String value) {
            addCriterion("TO_DATE not like", value, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateIn(List<String> values) {
            addCriterion("TO_DATE in", values, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateNotIn(List<String> values) {
            addCriterion("TO_DATE not in", values, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateBetween(String value1, String value2) {
            addCriterion("TO_DATE between", value1, value2, "toDate");
            return (Criteria) this;
        }

        public Criteria andToDateNotBetween(String value1, String value2) {
            addCriterion("TO_DATE not between", value1, value2, "toDate");
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

        public Criteria andOwnCheckCntIsNull() {
            addCriterion("OWN_CHECK_CNT is null");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntIsNotNull() {
            addCriterion("OWN_CHECK_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntEqualTo(Long value) {
            addCriterion("OWN_CHECK_CNT =", value, "ownCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntNotEqualTo(Long value) {
            addCriterion("OWN_CHECK_CNT <>", value, "ownCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntGreaterThan(Long value) {
            addCriterion("OWN_CHECK_CNT >", value, "ownCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntGreaterThanOrEqualTo(Long value) {
            addCriterion("OWN_CHECK_CNT >=", value, "ownCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntLessThan(Long value) {
            addCriterion("OWN_CHECK_CNT <", value, "ownCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntLessThanOrEqualTo(Long value) {
            addCriterion("OWN_CHECK_CNT <=", value, "ownCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntIn(List<Long> values) {
            addCriterion("OWN_CHECK_CNT in", values, "ownCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntNotIn(List<Long> values) {
            addCriterion("OWN_CHECK_CNT not in", values, "ownCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntBetween(Long value1, Long value2) {
            addCriterion("OWN_CHECK_CNT between", value1, value2, "ownCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckCntNotBetween(Long value1, Long value2) {
            addCriterion("OWN_CHECK_CNT not between", value1, value2, "ownCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtIsNull() {
            addCriterion("OWN_CHECK_AMT is null");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtIsNotNull() {
            addCriterion("OWN_CHECK_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtEqualTo(Long value) {
            addCriterion("OWN_CHECK_AMT =", value, "ownCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtNotEqualTo(Long value) {
            addCriterion("OWN_CHECK_AMT <>", value, "ownCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtGreaterThan(Long value) {
            addCriterion("OWN_CHECK_AMT >", value, "ownCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("OWN_CHECK_AMT >=", value, "ownCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtLessThan(Long value) {
            addCriterion("OWN_CHECK_AMT <", value, "ownCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtLessThanOrEqualTo(Long value) {
            addCriterion("OWN_CHECK_AMT <=", value, "ownCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtIn(List<Long> values) {
            addCriterion("OWN_CHECK_AMT in", values, "ownCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtNotIn(List<Long> values) {
            addCriterion("OWN_CHECK_AMT not in", values, "ownCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtBetween(Long value1, Long value2) {
            addCriterion("OWN_CHECK_AMT between", value1, value2, "ownCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOwnCheckAmtNotBetween(Long value1, Long value2) {
            addCriterion("OWN_CHECK_AMT not between", value1, value2, "ownCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntIsNull() {
            addCriterion("OTHER_CHECK_CNT is null");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntIsNotNull() {
            addCriterion("OTHER_CHECK_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntEqualTo(Long value) {
            addCriterion("OTHER_CHECK_CNT =", value, "otherCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntNotEqualTo(Long value) {
            addCriterion("OTHER_CHECK_CNT <>", value, "otherCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntGreaterThan(Long value) {
            addCriterion("OTHER_CHECK_CNT >", value, "otherCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntGreaterThanOrEqualTo(Long value) {
            addCriterion("OTHER_CHECK_CNT >=", value, "otherCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntLessThan(Long value) {
            addCriterion("OTHER_CHECK_CNT <", value, "otherCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntLessThanOrEqualTo(Long value) {
            addCriterion("OTHER_CHECK_CNT <=", value, "otherCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntIn(List<Long> values) {
            addCriterion("OTHER_CHECK_CNT in", values, "otherCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntNotIn(List<Long> values) {
            addCriterion("OTHER_CHECK_CNT not in", values, "otherCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntBetween(Long value1, Long value2) {
            addCriterion("OTHER_CHECK_CNT between", value1, value2, "otherCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckCntNotBetween(Long value1, Long value2) {
            addCriterion("OTHER_CHECK_CNT not between", value1, value2, "otherCheckCnt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtIsNull() {
            addCriterion("OTHER_CHECK_AMT is null");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtIsNotNull() {
            addCriterion("OTHER_CHECK_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtEqualTo(Long value) {
            addCriterion("OTHER_CHECK_AMT =", value, "otherCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtNotEqualTo(Long value) {
            addCriterion("OTHER_CHECK_AMT <>", value, "otherCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtGreaterThan(Long value) {
            addCriterion("OTHER_CHECK_AMT >", value, "otherCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("OTHER_CHECK_AMT >=", value, "otherCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtLessThan(Long value) {
            addCriterion("OTHER_CHECK_AMT <", value, "otherCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtLessThanOrEqualTo(Long value) {
            addCriterion("OTHER_CHECK_AMT <=", value, "otherCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtIn(List<Long> values) {
            addCriterion("OTHER_CHECK_AMT in", values, "otherCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtNotIn(List<Long> values) {
            addCriterion("OTHER_CHECK_AMT not in", values, "otherCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtBetween(Long value1, Long value2) {
            addCriterion("OTHER_CHECK_AMT between", value1, value2, "otherCheckAmt");
            return (Criteria) this;
        }

        public Criteria andOtherCheckAmtNotBetween(Long value1, Long value2) {
            addCriterion("OTHER_CHECK_AMT not between", value1, value2, "otherCheckAmt");
            return (Criteria) this;
        }

        public Criteria andTotCntIsNull() {
            addCriterion("TOT_CNT is null");
            return (Criteria) this;
        }

        public Criteria andTotCntIsNotNull() {
            addCriterion("TOT_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andTotCntEqualTo(Long value) {
            addCriterion("TOT_CNT =", value, "totCnt");
            return (Criteria) this;
        }

        public Criteria andTotCntNotEqualTo(Long value) {
            addCriterion("TOT_CNT <>", value, "totCnt");
            return (Criteria) this;
        }

        public Criteria andTotCntGreaterThan(Long value) {
            addCriterion("TOT_CNT >", value, "totCnt");
            return (Criteria) this;
        }

        public Criteria andTotCntGreaterThanOrEqualTo(Long value) {
            addCriterion("TOT_CNT >=", value, "totCnt");
            return (Criteria) this;
        }

        public Criteria andTotCntLessThan(Long value) {
            addCriterion("TOT_CNT <", value, "totCnt");
            return (Criteria) this;
        }

        public Criteria andTotCntLessThanOrEqualTo(Long value) {
            addCriterion("TOT_CNT <=", value, "totCnt");
            return (Criteria) this;
        }

        public Criteria andTotCntIn(List<Long> values) {
            addCriterion("TOT_CNT in", values, "totCnt");
            return (Criteria) this;
        }

        public Criteria andTotCntNotIn(List<Long> values) {
            addCriterion("TOT_CNT not in", values, "totCnt");
            return (Criteria) this;
        }

        public Criteria andTotCntBetween(Long value1, Long value2) {
            addCriterion("TOT_CNT between", value1, value2, "totCnt");
            return (Criteria) this;
        }

        public Criteria andTotCntNotBetween(Long value1, Long value2) {
            addCriterion("TOT_CNT not between", value1, value2, "totCnt");
            return (Criteria) this;
        }

        public Criteria andTotAmtIsNull() {
            addCriterion("TOT_AMT is null");
            return (Criteria) this;
        }

        public Criteria andTotAmtIsNotNull() {
            addCriterion("TOT_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andTotAmtEqualTo(Long value) {
            addCriterion("TOT_AMT =", value, "totAmt");
            return (Criteria) this;
        }

        public Criteria andTotAmtNotEqualTo(Long value) {
            addCriterion("TOT_AMT <>", value, "totAmt");
            return (Criteria) this;
        }

        public Criteria andTotAmtGreaterThan(Long value) {
            addCriterion("TOT_AMT >", value, "totAmt");
            return (Criteria) this;
        }

        public Criteria andTotAmtGreaterThanOrEqualTo(Long value) {
            addCriterion("TOT_AMT >=", value, "totAmt");
            return (Criteria) this;
        }

        public Criteria andTotAmtLessThan(Long value) {
            addCriterion("TOT_AMT <", value, "totAmt");
            return (Criteria) this;
        }

        public Criteria andTotAmtLessThanOrEqualTo(Long value) {
            addCriterion("TOT_AMT <=", value, "totAmt");
            return (Criteria) this;
        }

        public Criteria andTotAmtIn(List<Long> values) {
            addCriterion("TOT_AMT in", values, "totAmt");
            return (Criteria) this;
        }

        public Criteria andTotAmtNotIn(List<Long> values) {
            addCriterion("TOT_AMT not in", values, "totAmt");
            return (Criteria) this;
        }

        public Criteria andTotAmtBetween(Long value1, Long value2) {
            addCriterion("TOT_AMT between", value1, value2, "totAmt");
            return (Criteria) this;
        }

        public Criteria andTotAmtNotBetween(Long value1, Long value2) {
            addCriterion("TOT_AMT not between", value1, value2, "totAmt");
            return (Criteria) this;
        }

        public Criteria andInsertDateIsNull() {
            addCriterion("INSERT_DATE is null");
            return (Criteria) this;
        }

        public Criteria andInsertDateIsNotNull() {
            addCriterion("INSERT_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andInsertDateEqualTo(Date value) {
            addCriterion("INSERT_DATE =", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotEqualTo(Date value) {
            addCriterion("INSERT_DATE <>", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateGreaterThan(Date value) {
            addCriterion("INSERT_DATE >", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateGreaterThanOrEqualTo(Date value) {
            addCriterion("INSERT_DATE >=", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateLessThan(Date value) {
            addCriterion("INSERT_DATE <", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateLessThanOrEqualTo(Date value) {
            addCriterion("INSERT_DATE <=", value, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateIn(List<Date> values) {
            addCriterion("INSERT_DATE in", values, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotIn(List<Date> values) {
            addCriterion("INSERT_DATE not in", values, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateBetween(Date value1, Date value2) {
            addCriterion("INSERT_DATE between", value1, value2, "insertDate");
            return (Criteria) this;
        }

        public Criteria andInsertDateNotBetween(Date value1, Date value2) {
            addCriterion("INSERT_DATE not between", value1, value2, "insertDate");
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
     * This class corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated do_not_delete_during_merge Thu Jul 17 17:30:56 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
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
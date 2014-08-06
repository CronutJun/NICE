package com.nicetcm.nibsplus.broker.msg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCtErrorCustInfoSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    public TCtErrorCustInfoSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
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
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
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

        public Criteria andCreateDateIsNull() {
            addCriterion("CREATE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("CREATE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(String value) {
            addCriterion("CREATE_DATE =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(String value) {
            addCriterion("CREATE_DATE <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(String value) {
            addCriterion("CREATE_DATE >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(String value) {
            addCriterion("CREATE_DATE <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(String value) {
            addCriterion("CREATE_DATE <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLike(String value) {
            addCriterion("CREATE_DATE like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotLike(String value) {
            addCriterion("CREATE_DATE not like", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<String> values) {
            addCriterion("CREATE_DATE in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<String> values) {
            addCriterion("CREATE_DATE not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(String value1, String value2) {
            addCriterion("CREATE_DATE between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(String value1, String value2) {
            addCriterion("CREATE_DATE not between", value1, value2, "createDate");
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

        public Criteria andAtmDealNoIsNull() {
            addCriterion("ATM_DEAL_NO is null");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoIsNotNull() {
            addCriterion("ATM_DEAL_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoEqualTo(String value) {
            addCriterion("ATM_DEAL_NO =", value, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoNotEqualTo(String value) {
            addCriterion("ATM_DEAL_NO <>", value, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoGreaterThan(String value) {
            addCriterion("ATM_DEAL_NO >", value, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoGreaterThanOrEqualTo(String value) {
            addCriterion("ATM_DEAL_NO >=", value, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoLessThan(String value) {
            addCriterion("ATM_DEAL_NO <", value, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoLessThanOrEqualTo(String value) {
            addCriterion("ATM_DEAL_NO <=", value, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoLike(String value) {
            addCriterion("ATM_DEAL_NO like", value, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoNotLike(String value) {
            addCriterion("ATM_DEAL_NO not like", value, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoIn(List<String> values) {
            addCriterion("ATM_DEAL_NO in", values, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoNotIn(List<String> values) {
            addCriterion("ATM_DEAL_NO not in", values, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoBetween(String value1, String value2) {
            addCriterion("ATM_DEAL_NO between", value1, value2, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andAtmDealNoNotBetween(String value1, String value2) {
            addCriterion("ATM_DEAL_NO not between", value1, value2, "atmDealNo");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(String value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(String value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(String value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(String value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(String value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLike(String value) {
            addCriterion("CREATE_TIME like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotLike(String value) {
            addCriterion("CREATE_TIME not like", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<String> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<String> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(String value1, String value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(String value1, String value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdIsNull() {
            addCriterion("CUST_ORG_CD is null");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdIsNotNull() {
            addCriterion("CUST_ORG_CD is not null");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdEqualTo(String value) {
            addCriterion("CUST_ORG_CD =", value, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdNotEqualTo(String value) {
            addCriterion("CUST_ORG_CD <>", value, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdGreaterThan(String value) {
            addCriterion("CUST_ORG_CD >", value, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_ORG_CD >=", value, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdLessThan(String value) {
            addCriterion("CUST_ORG_CD <", value, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdLessThanOrEqualTo(String value) {
            addCriterion("CUST_ORG_CD <=", value, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdLike(String value) {
            addCriterion("CUST_ORG_CD like", value, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdNotLike(String value) {
            addCriterion("CUST_ORG_CD not like", value, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdIn(List<String> values) {
            addCriterion("CUST_ORG_CD in", values, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdNotIn(List<String> values) {
            addCriterion("CUST_ORG_CD not in", values, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdBetween(String value1, String value2) {
            addCriterion("CUST_ORG_CD between", value1, value2, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustOrgCdNotBetween(String value1, String value2) {
            addCriterion("CUST_ORG_CD not between", value1, value2, "custOrgCd");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoIsNull() {
            addCriterion("CUST_ACCOUNT_NO is null");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoIsNotNull() {
            addCriterion("CUST_ACCOUNT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoEqualTo(String value) {
            addCriterion("CUST_ACCOUNT_NO =", value, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoNotEqualTo(String value) {
            addCriterion("CUST_ACCOUNT_NO <>", value, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoGreaterThan(String value) {
            addCriterion("CUST_ACCOUNT_NO >", value, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("CUST_ACCOUNT_NO >=", value, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoLessThan(String value) {
            addCriterion("CUST_ACCOUNT_NO <", value, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoLessThanOrEqualTo(String value) {
            addCriterion("CUST_ACCOUNT_NO <=", value, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoLike(String value) {
            addCriterion("CUST_ACCOUNT_NO like", value, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoNotLike(String value) {
            addCriterion("CUST_ACCOUNT_NO not like", value, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoIn(List<String> values) {
            addCriterion("CUST_ACCOUNT_NO in", values, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoNotIn(List<String> values) {
            addCriterion("CUST_ACCOUNT_NO not in", values, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoBetween(String value1, String value2) {
            addCriterion("CUST_ACCOUNT_NO between", value1, value2, "custAccountNo");
            return (Criteria) this;
        }

        public Criteria andCustAccountNoNotBetween(String value1, String value2) {
            addCriterion("CUST_ACCOUNT_NO not between", value1, value2, "custAccountNo");
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
     * This class corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated do_not_delete_during_merge Mon Jul 28 09:33:17 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
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
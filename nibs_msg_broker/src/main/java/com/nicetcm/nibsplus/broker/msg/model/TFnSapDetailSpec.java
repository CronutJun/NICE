package com.nicetcm.nibsplus.broker.msg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TFnSapDetailSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    public TFnSapDetailSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
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
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
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

        public Criteria andMemberIdIsNull() {
            addCriterion("MEMBER_ID is null");
            return (Criteria) this;
        }

        public Criteria andMemberIdIsNotNull() {
            addCriterion("MEMBER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMemberIdEqualTo(String value) {
            addCriterion("MEMBER_ID =", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotEqualTo(String value) {
            addCriterion("MEMBER_ID <>", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThan(String value) {
            addCriterion("MEMBER_ID >", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEMBER_ID >=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThan(String value) {
            addCriterion("MEMBER_ID <", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLessThanOrEqualTo(String value) {
            addCriterion("MEMBER_ID <=", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdLike(String value) {
            addCriterion("MEMBER_ID like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotLike(String value) {
            addCriterion("MEMBER_ID not like", value, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdIn(List<String> values) {
            addCriterion("MEMBER_ID in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotIn(List<String> values) {
            addCriterion("MEMBER_ID not in", values, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdBetween(String value1, String value2) {
            addCriterion("MEMBER_ID between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andMemberIdNotBetween(String value1, String value2) {
            addCriterion("MEMBER_ID not between", value1, value2, "memberId");
            return (Criteria) this;
        }

        public Criteria andCuponCdIsNull() {
            addCriterion("CUPON_CD is null");
            return (Criteria) this;
        }

        public Criteria andCuponCdIsNotNull() {
            addCriterion("CUPON_CD is not null");
            return (Criteria) this;
        }

        public Criteria andCuponCdEqualTo(String value) {
            addCriterion("CUPON_CD =", value, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdNotEqualTo(String value) {
            addCriterion("CUPON_CD <>", value, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdGreaterThan(String value) {
            addCriterion("CUPON_CD >", value, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdGreaterThanOrEqualTo(String value) {
            addCriterion("CUPON_CD >=", value, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdLessThan(String value) {
            addCriterion("CUPON_CD <", value, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdLessThanOrEqualTo(String value) {
            addCriterion("CUPON_CD <=", value, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdLike(String value) {
            addCriterion("CUPON_CD like", value, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdNotLike(String value) {
            addCriterion("CUPON_CD not like", value, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdIn(List<String> values) {
            addCriterion("CUPON_CD in", values, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdNotIn(List<String> values) {
            addCriterion("CUPON_CD not in", values, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdBetween(String value1, String value2) {
            addCriterion("CUPON_CD between", value1, value2, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponCdNotBetween(String value1, String value2) {
            addCriterion("CUPON_CD not between", value1, value2, "cuponCd");
            return (Criteria) this;
        }

        public Criteria andCuponNmIsNull() {
            addCriterion("CUPON_NM is null");
            return (Criteria) this;
        }

        public Criteria andCuponNmIsNotNull() {
            addCriterion("CUPON_NM is not null");
            return (Criteria) this;
        }

        public Criteria andCuponNmEqualTo(String value) {
            addCriterion("CUPON_NM =", value, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmNotEqualTo(String value) {
            addCriterion("CUPON_NM <>", value, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmGreaterThan(String value) {
            addCriterion("CUPON_NM >", value, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmGreaterThanOrEqualTo(String value) {
            addCriterion("CUPON_NM >=", value, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmLessThan(String value) {
            addCriterion("CUPON_NM <", value, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmLessThanOrEqualTo(String value) {
            addCriterion("CUPON_NM <=", value, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmLike(String value) {
            addCriterion("CUPON_NM like", value, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmNotLike(String value) {
            addCriterion("CUPON_NM not like", value, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmIn(List<String> values) {
            addCriterion("CUPON_NM in", values, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmNotIn(List<String> values) {
            addCriterion("CUPON_NM not in", values, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmBetween(String value1, String value2) {
            addCriterion("CUPON_NM between", value1, value2, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponNmNotBetween(String value1, String value2) {
            addCriterion("CUPON_NM not between", value1, value2, "cuponNm");
            return (Criteria) this;
        }

        public Criteria andCuponTypeIsNull() {
            addCriterion("CUPON_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andCuponTypeIsNotNull() {
            addCriterion("CUPON_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andCuponTypeEqualTo(String value) {
            addCriterion("CUPON_TYPE =", value, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeNotEqualTo(String value) {
            addCriterion("CUPON_TYPE <>", value, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeGreaterThan(String value) {
            addCriterion("CUPON_TYPE >", value, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CUPON_TYPE >=", value, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeLessThan(String value) {
            addCriterion("CUPON_TYPE <", value, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeLessThanOrEqualTo(String value) {
            addCriterion("CUPON_TYPE <=", value, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeLike(String value) {
            addCriterion("CUPON_TYPE like", value, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeNotLike(String value) {
            addCriterion("CUPON_TYPE not like", value, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeIn(List<String> values) {
            addCriterion("CUPON_TYPE in", values, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeNotIn(List<String> values) {
            addCriterion("CUPON_TYPE not in", values, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeBetween(String value1, String value2) {
            addCriterion("CUPON_TYPE between", value1, value2, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponTypeNotBetween(String value1, String value2) {
            addCriterion("CUPON_TYPE not between", value1, value2, "cuponType");
            return (Criteria) this;
        }

        public Criteria andCuponAmtIsNull() {
            addCriterion("CUPON_AMT is null");
            return (Criteria) this;
        }

        public Criteria andCuponAmtIsNotNull() {
            addCriterion("CUPON_AMT is not null");
            return (Criteria) this;
        }

        public Criteria andCuponAmtEqualTo(Object value) {
            addCriterion("CUPON_AMT =", value, "cuponAmt");
            return (Criteria) this;
        }

        public Criteria andCuponAmtNotEqualTo(Object value) {
            addCriterion("CUPON_AMT <>", value, "cuponAmt");
            return (Criteria) this;
        }

        public Criteria andCuponAmtGreaterThan(Object value) {
            addCriterion("CUPON_AMT >", value, "cuponAmt");
            return (Criteria) this;
        }

        public Criteria andCuponAmtGreaterThanOrEqualTo(Object value) {
            addCriterion("CUPON_AMT >=", value, "cuponAmt");
            return (Criteria) this;
        }

        public Criteria andCuponAmtLessThan(Object value) {
            addCriterion("CUPON_AMT <", value, "cuponAmt");
            return (Criteria) this;
        }

        public Criteria andCuponAmtLessThanOrEqualTo(Object value) {
            addCriterion("CUPON_AMT <=", value, "cuponAmt");
            return (Criteria) this;
        }

        public Criteria andCuponAmtIn(List<Object> values) {
            addCriterion("CUPON_AMT in", values, "cuponAmt");
            return (Criteria) this;
        }

        public Criteria andCuponAmtNotIn(List<Object> values) {
            addCriterion("CUPON_AMT not in", values, "cuponAmt");
            return (Criteria) this;
        }

        public Criteria andCuponAmtBetween(Object value1, Object value2) {
            addCriterion("CUPON_AMT between", value1, value2, "cuponAmt");
            return (Criteria) this;
        }

        public Criteria andCuponAmtNotBetween(Object value1, Object value2) {
            addCriterion("CUPON_AMT not between", value1, value2, "cuponAmt");
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
     * This class corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated do_not_delete_during_merge Thu Aug 14 16:45:51 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_SAP_DETAIL
     *
     * @mbggenerated Thu Aug 14 16:45:51 KST 2014
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
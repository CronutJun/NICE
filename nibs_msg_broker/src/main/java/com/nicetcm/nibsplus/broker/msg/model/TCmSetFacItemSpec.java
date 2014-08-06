package com.nicetcm.nibsplus.broker.msg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCmSetFacItemSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public TCmSetFacItemSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
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
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
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

        public Criteria andFacTypeIsNull() {
            addCriterion("FAC_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andFacTypeIsNotNull() {
            addCriterion("FAC_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andFacTypeEqualTo(String value) {
            addCriterion("FAC_TYPE =", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotEqualTo(String value) {
            addCriterion("FAC_TYPE <>", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeGreaterThan(String value) {
            addCriterion("FAC_TYPE >", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeGreaterThanOrEqualTo(String value) {
            addCriterion("FAC_TYPE >=", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeLessThan(String value) {
            addCriterion("FAC_TYPE <", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeLessThanOrEqualTo(String value) {
            addCriterion("FAC_TYPE <=", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeLike(String value) {
            addCriterion("FAC_TYPE like", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotLike(String value) {
            addCriterion("FAC_TYPE not like", value, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeIn(List<String> values) {
            addCriterion("FAC_TYPE in", values, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotIn(List<String> values) {
            addCriterion("FAC_TYPE not in", values, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeBetween(String value1, String value2) {
            addCriterion("FAC_TYPE between", value1, value2, "facType");
            return (Criteria) this;
        }

        public Criteria andFacTypeNotBetween(String value1, String value2) {
            addCriterion("FAC_TYPE not between", value1, value2, "facType");
            return (Criteria) this;
        }

        public Criteria andFacCdIsNull() {
            addCriterion("FAC_CD is null");
            return (Criteria) this;
        }

        public Criteria andFacCdIsNotNull() {
            addCriterion("FAC_CD is not null");
            return (Criteria) this;
        }

        public Criteria andFacCdEqualTo(String value) {
            addCriterion("FAC_CD =", value, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdNotEqualTo(String value) {
            addCriterion("FAC_CD <>", value, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdGreaterThan(String value) {
            addCriterion("FAC_CD >", value, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdGreaterThanOrEqualTo(String value) {
            addCriterion("FAC_CD >=", value, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdLessThan(String value) {
            addCriterion("FAC_CD <", value, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdLessThanOrEqualTo(String value) {
            addCriterion("FAC_CD <=", value, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdLike(String value) {
            addCriterion("FAC_CD like", value, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdNotLike(String value) {
            addCriterion("FAC_CD not like", value, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdIn(List<String> values) {
            addCriterion("FAC_CD in", values, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdNotIn(List<String> values) {
            addCriterion("FAC_CD not in", values, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdBetween(String value1, String value2) {
            addCriterion("FAC_CD between", value1, value2, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacCdNotBetween(String value1, String value2) {
            addCriterion("FAC_CD not between", value1, value2, "facCd");
            return (Criteria) this;
        }

        public Criteria andFacNmIsNull() {
            addCriterion("FAC_NM is null");
            return (Criteria) this;
        }

        public Criteria andFacNmIsNotNull() {
            addCriterion("FAC_NM is not null");
            return (Criteria) this;
        }

        public Criteria andFacNmEqualTo(String value) {
            addCriterion("FAC_NM =", value, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmNotEqualTo(String value) {
            addCriterion("FAC_NM <>", value, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmGreaterThan(String value) {
            addCriterion("FAC_NM >", value, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmGreaterThanOrEqualTo(String value) {
            addCriterion("FAC_NM >=", value, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmLessThan(String value) {
            addCriterion("FAC_NM <", value, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmLessThanOrEqualTo(String value) {
            addCriterion("FAC_NM <=", value, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmLike(String value) {
            addCriterion("FAC_NM like", value, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmNotLike(String value) {
            addCriterion("FAC_NM not like", value, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmIn(List<String> values) {
            addCriterion("FAC_NM in", values, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmNotIn(List<String> values) {
            addCriterion("FAC_NM not in", values, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmBetween(String value1, String value2) {
            addCriterion("FAC_NM between", value1, value2, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacNmNotBetween(String value1, String value2) {
            addCriterion("FAC_NM not between", value1, value2, "facNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmIsNull() {
            addCriterion("FAC_COM_NM is null");
            return (Criteria) this;
        }

        public Criteria andFacComNmIsNotNull() {
            addCriterion("FAC_COM_NM is not null");
            return (Criteria) this;
        }

        public Criteria andFacComNmEqualTo(String value) {
            addCriterion("FAC_COM_NM =", value, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmNotEqualTo(String value) {
            addCriterion("FAC_COM_NM <>", value, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmGreaterThan(String value) {
            addCriterion("FAC_COM_NM >", value, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmGreaterThanOrEqualTo(String value) {
            addCriterion("FAC_COM_NM >=", value, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmLessThan(String value) {
            addCriterion("FAC_COM_NM <", value, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmLessThanOrEqualTo(String value) {
            addCriterion("FAC_COM_NM <=", value, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmLike(String value) {
            addCriterion("FAC_COM_NM like", value, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmNotLike(String value) {
            addCriterion("FAC_COM_NM not like", value, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmIn(List<String> values) {
            addCriterion("FAC_COM_NM in", values, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmNotIn(List<String> values) {
            addCriterion("FAC_COM_NM not in", values, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmBetween(String value1, String value2) {
            addCriterion("FAC_COM_NM between", value1, value2, "facComNm");
            return (Criteria) this;
        }

        public Criteria andFacComNmNotBetween(String value1, String value2) {
            addCriterion("FAC_COM_NM not between", value1, value2, "facComNm");
            return (Criteria) this;
        }

        public Criteria andRentFeeIsNull() {
            addCriterion("RENT_FEE is null");
            return (Criteria) this;
        }

        public Criteria andRentFeeIsNotNull() {
            addCriterion("RENT_FEE is not null");
            return (Criteria) this;
        }

        public Criteria andRentFeeEqualTo(Object value) {
            addCriterion("RENT_FEE =", value, "rentFee");
            return (Criteria) this;
        }

        public Criteria andRentFeeNotEqualTo(Object value) {
            addCriterion("RENT_FEE <>", value, "rentFee");
            return (Criteria) this;
        }

        public Criteria andRentFeeGreaterThan(Object value) {
            addCriterion("RENT_FEE >", value, "rentFee");
            return (Criteria) this;
        }

        public Criteria andRentFeeGreaterThanOrEqualTo(Object value) {
            addCriterion("RENT_FEE >=", value, "rentFee");
            return (Criteria) this;
        }

        public Criteria andRentFeeLessThan(Object value) {
            addCriterion("RENT_FEE <", value, "rentFee");
            return (Criteria) this;
        }

        public Criteria andRentFeeLessThanOrEqualTo(Object value) {
            addCriterion("RENT_FEE <=", value, "rentFee");
            return (Criteria) this;
        }

        public Criteria andRentFeeIn(List<Object> values) {
            addCriterion("RENT_FEE in", values, "rentFee");
            return (Criteria) this;
        }

        public Criteria andRentFeeNotIn(List<Object> values) {
            addCriterion("RENT_FEE not in", values, "rentFee");
            return (Criteria) this;
        }

        public Criteria andRentFeeBetween(Object value1, Object value2) {
            addCriterion("RENT_FEE between", value1, value2, "rentFee");
            return (Criteria) this;
        }

        public Criteria andRentFeeNotBetween(Object value1, Object value2) {
            addCriterion("RENT_FEE not between", value1, value2, "rentFee");
            return (Criteria) this;
        }

        public Criteria andUseYnIsNull() {
            addCriterion("USE_YN is null");
            return (Criteria) this;
        }

        public Criteria andUseYnIsNotNull() {
            addCriterion("USE_YN is not null");
            return (Criteria) this;
        }

        public Criteria andUseYnEqualTo(String value) {
            addCriterion("USE_YN =", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotEqualTo(String value) {
            addCriterion("USE_YN <>", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnGreaterThan(String value) {
            addCriterion("USE_YN >", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnGreaterThanOrEqualTo(String value) {
            addCriterion("USE_YN >=", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLessThan(String value) {
            addCriterion("USE_YN <", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLessThanOrEqualTo(String value) {
            addCriterion("USE_YN <=", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnLike(String value) {
            addCriterion("USE_YN like", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotLike(String value) {
            addCriterion("USE_YN not like", value, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnIn(List<String> values) {
            addCriterion("USE_YN in", values, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotIn(List<String> values) {
            addCriterion("USE_YN not in", values, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnBetween(String value1, String value2) {
            addCriterion("USE_YN between", value1, value2, "useYn");
            return (Criteria) this;
        }

        public Criteria andUseYnNotBetween(String value1, String value2) {
            addCriterion("USE_YN not between", value1, value2, "useYn");
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

        public Criteria andCloseDateIsNull() {
            addCriterion("CLOSE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCloseDateIsNotNull() {
            addCriterion("CLOSE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCloseDateEqualTo(Date value) {
            addCriterion("CLOSE_DATE =", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotEqualTo(Date value) {
            addCriterion("CLOSE_DATE <>", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateGreaterThan(Date value) {
            addCriterion("CLOSE_DATE >", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateGreaterThanOrEqualTo(Date value) {
            addCriterion("CLOSE_DATE >=", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateLessThan(Date value) {
            addCriterion("CLOSE_DATE <", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateLessThanOrEqualTo(Date value) {
            addCriterion("CLOSE_DATE <=", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateIn(List<Date> values) {
            addCriterion("CLOSE_DATE in", values, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotIn(List<Date> values) {
            addCriterion("CLOSE_DATE not in", values, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateBetween(Date value1, Date value2) {
            addCriterion("CLOSE_DATE between", value1, value2, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotBetween(Date value1, Date value2) {
            addCriterion("CLOSE_DATE not between", value1, value2, "closeDate");
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
     * This class corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated do_not_delete_during_merge Wed Aug 06 09:58:31 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CM_SET_FAC_ITEM
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
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
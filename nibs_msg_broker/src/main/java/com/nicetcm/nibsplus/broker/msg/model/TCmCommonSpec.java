package com.nicetcm.nibsplus.broker.msg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TCmCommonSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    public TCmCommonSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
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
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
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

        public Criteria andLargeCdIsNull() {
            addCriterion("LARGE_CD is null");
            return (Criteria) this;
        }

        public Criteria andLargeCdIsNotNull() {
            addCriterion("LARGE_CD is not null");
            return (Criteria) this;
        }

        public Criteria andLargeCdEqualTo(String value) {
            addCriterion("LARGE_CD =", value, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdNotEqualTo(String value) {
            addCriterion("LARGE_CD <>", value, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdGreaterThan(String value) {
            addCriterion("LARGE_CD >", value, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdGreaterThanOrEqualTo(String value) {
            addCriterion("LARGE_CD >=", value, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdLessThan(String value) {
            addCriterion("LARGE_CD <", value, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdLessThanOrEqualTo(String value) {
            addCriterion("LARGE_CD <=", value, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdLike(String value) {
            addCriterion("LARGE_CD like", value, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdNotLike(String value) {
            addCriterion("LARGE_CD not like", value, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdIn(List<String> values) {
            addCriterion("LARGE_CD in", values, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdNotIn(List<String> values) {
            addCriterion("LARGE_CD not in", values, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdBetween(String value1, String value2) {
            addCriterion("LARGE_CD between", value1, value2, "largeCd");
            return (Criteria) this;
        }

        public Criteria andLargeCdNotBetween(String value1, String value2) {
            addCriterion("LARGE_CD not between", value1, value2, "largeCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdIsNull() {
            addCriterion("SMALL_CD is null");
            return (Criteria) this;
        }

        public Criteria andSmallCdIsNotNull() {
            addCriterion("SMALL_CD is not null");
            return (Criteria) this;
        }

        public Criteria andSmallCdEqualTo(String value) {
            addCriterion("SMALL_CD =", value, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdNotEqualTo(String value) {
            addCriterion("SMALL_CD <>", value, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdGreaterThan(String value) {
            addCriterion("SMALL_CD >", value, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdGreaterThanOrEqualTo(String value) {
            addCriterion("SMALL_CD >=", value, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdLessThan(String value) {
            addCriterion("SMALL_CD <", value, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdLessThanOrEqualTo(String value) {
            addCriterion("SMALL_CD <=", value, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdLike(String value) {
            addCriterion("SMALL_CD like", value, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdNotLike(String value) {
            addCriterion("SMALL_CD not like", value, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdIn(List<String> values) {
            addCriterion("SMALL_CD in", values, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdNotIn(List<String> values) {
            addCriterion("SMALL_CD not in", values, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdBetween(String value1, String value2) {
            addCriterion("SMALL_CD between", value1, value2, "smallCd");
            return (Criteria) this;
        }

        public Criteria andSmallCdNotBetween(String value1, String value2) {
            addCriterion("SMALL_CD not between", value1, value2, "smallCd");
            return (Criteria) this;
        }

        public Criteria andCdNmIsNull() {
            addCriterion("CD_NM is null");
            return (Criteria) this;
        }

        public Criteria andCdNmIsNotNull() {
            addCriterion("CD_NM is not null");
            return (Criteria) this;
        }

        public Criteria andCdNmEqualTo(String value) {
            addCriterion("CD_NM =", value, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmNotEqualTo(String value) {
            addCriterion("CD_NM <>", value, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmGreaterThan(String value) {
            addCriterion("CD_NM >", value, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmGreaterThanOrEqualTo(String value) {
            addCriterion("CD_NM >=", value, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmLessThan(String value) {
            addCriterion("CD_NM <", value, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmLessThanOrEqualTo(String value) {
            addCriterion("CD_NM <=", value, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmLike(String value) {
            addCriterion("CD_NM like", value, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmNotLike(String value) {
            addCriterion("CD_NM not like", value, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmIn(List<String> values) {
            addCriterion("CD_NM in", values, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmNotIn(List<String> values) {
            addCriterion("CD_NM not in", values, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmBetween(String value1, String value2) {
            addCriterion("CD_NM between", value1, value2, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNmNotBetween(String value1, String value2) {
            addCriterion("CD_NM not between", value1, value2, "cdNm");
            return (Criteria) this;
        }

        public Criteria andCdNm1IsNull() {
            addCriterion("CD_NM1 is null");
            return (Criteria) this;
        }

        public Criteria andCdNm1IsNotNull() {
            addCriterion("CD_NM1 is not null");
            return (Criteria) this;
        }

        public Criteria andCdNm1EqualTo(String value) {
            addCriterion("CD_NM1 =", value, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1NotEqualTo(String value) {
            addCriterion("CD_NM1 <>", value, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1GreaterThan(String value) {
            addCriterion("CD_NM1 >", value, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1GreaterThanOrEqualTo(String value) {
            addCriterion("CD_NM1 >=", value, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1LessThan(String value) {
            addCriterion("CD_NM1 <", value, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1LessThanOrEqualTo(String value) {
            addCriterion("CD_NM1 <=", value, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1Like(String value) {
            addCriterion("CD_NM1 like", value, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1NotLike(String value) {
            addCriterion("CD_NM1 not like", value, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1In(List<String> values) {
            addCriterion("CD_NM1 in", values, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1NotIn(List<String> values) {
            addCriterion("CD_NM1 not in", values, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1Between(String value1, String value2) {
            addCriterion("CD_NM1 between", value1, value2, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm1NotBetween(String value1, String value2) {
            addCriterion("CD_NM1 not between", value1, value2, "cdNm1");
            return (Criteria) this;
        }

        public Criteria andCdNm2IsNull() {
            addCriterion("CD_NM2 is null");
            return (Criteria) this;
        }

        public Criteria andCdNm2IsNotNull() {
            addCriterion("CD_NM2 is not null");
            return (Criteria) this;
        }

        public Criteria andCdNm2EqualTo(String value) {
            addCriterion("CD_NM2 =", value, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2NotEqualTo(String value) {
            addCriterion("CD_NM2 <>", value, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2GreaterThan(String value) {
            addCriterion("CD_NM2 >", value, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2GreaterThanOrEqualTo(String value) {
            addCriterion("CD_NM2 >=", value, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2LessThan(String value) {
            addCriterion("CD_NM2 <", value, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2LessThanOrEqualTo(String value) {
            addCriterion("CD_NM2 <=", value, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2Like(String value) {
            addCriterion("CD_NM2 like", value, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2NotLike(String value) {
            addCriterion("CD_NM2 not like", value, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2In(List<String> values) {
            addCriterion("CD_NM2 in", values, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2NotIn(List<String> values) {
            addCriterion("CD_NM2 not in", values, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2Between(String value1, String value2) {
            addCriterion("CD_NM2 between", value1, value2, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm2NotBetween(String value1, String value2) {
            addCriterion("CD_NM2 not between", value1, value2, "cdNm2");
            return (Criteria) this;
        }

        public Criteria andCdNm3IsNull() {
            addCriterion("CD_NM3 is null");
            return (Criteria) this;
        }

        public Criteria andCdNm3IsNotNull() {
            addCriterion("CD_NM3 is not null");
            return (Criteria) this;
        }

        public Criteria andCdNm3EqualTo(String value) {
            addCriterion("CD_NM3 =", value, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3NotEqualTo(String value) {
            addCriterion("CD_NM3 <>", value, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3GreaterThan(String value) {
            addCriterion("CD_NM3 >", value, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3GreaterThanOrEqualTo(String value) {
            addCriterion("CD_NM3 >=", value, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3LessThan(String value) {
            addCriterion("CD_NM3 <", value, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3LessThanOrEqualTo(String value) {
            addCriterion("CD_NM3 <=", value, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3Like(String value) {
            addCriterion("CD_NM3 like", value, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3NotLike(String value) {
            addCriterion("CD_NM3 not like", value, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3In(List<String> values) {
            addCriterion("CD_NM3 in", values, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3NotIn(List<String> values) {
            addCriterion("CD_NM3 not in", values, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3Between(String value1, String value2) {
            addCriterion("CD_NM3 between", value1, value2, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm3NotBetween(String value1, String value2) {
            addCriterion("CD_NM3 not between", value1, value2, "cdNm3");
            return (Criteria) this;
        }

        public Criteria andCdNm4IsNull() {
            addCriterion("CD_NM4 is null");
            return (Criteria) this;
        }

        public Criteria andCdNm4IsNotNull() {
            addCriterion("CD_NM4 is not null");
            return (Criteria) this;
        }

        public Criteria andCdNm4EqualTo(String value) {
            addCriterion("CD_NM4 =", value, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4NotEqualTo(String value) {
            addCriterion("CD_NM4 <>", value, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4GreaterThan(String value) {
            addCriterion("CD_NM4 >", value, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4GreaterThanOrEqualTo(String value) {
            addCriterion("CD_NM4 >=", value, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4LessThan(String value) {
            addCriterion("CD_NM4 <", value, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4LessThanOrEqualTo(String value) {
            addCriterion("CD_NM4 <=", value, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4Like(String value) {
            addCriterion("CD_NM4 like", value, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4NotLike(String value) {
            addCriterion("CD_NM4 not like", value, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4In(List<String> values) {
            addCriterion("CD_NM4 in", values, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4NotIn(List<String> values) {
            addCriterion("CD_NM4 not in", values, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4Between(String value1, String value2) {
            addCriterion("CD_NM4 between", value1, value2, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm4NotBetween(String value1, String value2) {
            addCriterion("CD_NM4 not between", value1, value2, "cdNm4");
            return (Criteria) this;
        }

        public Criteria andCdNm5IsNull() {
            addCriterion("CD_NM5 is null");
            return (Criteria) this;
        }

        public Criteria andCdNm5IsNotNull() {
            addCriterion("CD_NM5 is not null");
            return (Criteria) this;
        }

        public Criteria andCdNm5EqualTo(String value) {
            addCriterion("CD_NM5 =", value, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5NotEqualTo(String value) {
            addCriterion("CD_NM5 <>", value, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5GreaterThan(String value) {
            addCriterion("CD_NM5 >", value, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5GreaterThanOrEqualTo(String value) {
            addCriterion("CD_NM5 >=", value, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5LessThan(String value) {
            addCriterion("CD_NM5 <", value, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5LessThanOrEqualTo(String value) {
            addCriterion("CD_NM5 <=", value, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5Like(String value) {
            addCriterion("CD_NM5 like", value, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5NotLike(String value) {
            addCriterion("CD_NM5 not like", value, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5In(List<String> values) {
            addCriterion("CD_NM5 in", values, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5NotIn(List<String> values) {
            addCriterion("CD_NM5 not in", values, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5Between(String value1, String value2) {
            addCriterion("CD_NM5 between", value1, value2, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm5NotBetween(String value1, String value2) {
            addCriterion("CD_NM5 not between", value1, value2, "cdNm5");
            return (Criteria) this;
        }

        public Criteria andCdNm6IsNull() {
            addCriterion("CD_NM6 is null");
            return (Criteria) this;
        }

        public Criteria andCdNm6IsNotNull() {
            addCriterion("CD_NM6 is not null");
            return (Criteria) this;
        }

        public Criteria andCdNm6EqualTo(String value) {
            addCriterion("CD_NM6 =", value, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6NotEqualTo(String value) {
            addCriterion("CD_NM6 <>", value, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6GreaterThan(String value) {
            addCriterion("CD_NM6 >", value, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6GreaterThanOrEqualTo(String value) {
            addCriterion("CD_NM6 >=", value, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6LessThan(String value) {
            addCriterion("CD_NM6 <", value, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6LessThanOrEqualTo(String value) {
            addCriterion("CD_NM6 <=", value, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6Like(String value) {
            addCriterion("CD_NM6 like", value, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6NotLike(String value) {
            addCriterion("CD_NM6 not like", value, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6In(List<String> values) {
            addCriterion("CD_NM6 in", values, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6NotIn(List<String> values) {
            addCriterion("CD_NM6 not in", values, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6Between(String value1, String value2) {
            addCriterion("CD_NM6 between", value1, value2, "cdNm6");
            return (Criteria) this;
        }

        public Criteria andCdNm6NotBetween(String value1, String value2) {
            addCriterion("CD_NM6 not between", value1, value2, "cdNm6");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated do_not_delete_during_merge Tue Jun 24 10:40:32 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_CM_COMMON
     *
     * @mbggenerated Tue Jun 24 10:40:32 KST 2014
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
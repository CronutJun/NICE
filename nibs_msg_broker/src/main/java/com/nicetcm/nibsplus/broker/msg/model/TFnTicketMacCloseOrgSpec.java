package com.nicetcm.nibsplus.broker.msg.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TFnTicketMacCloseOrgSpec {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public TFnTicketMacCloseOrgSpec() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
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
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
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

        public Criteria andCloseDateIsNull() {
            addCriterion("CLOSE_DATE is null");
            return (Criteria) this;
        }

        public Criteria andCloseDateIsNotNull() {
            addCriterion("CLOSE_DATE is not null");
            return (Criteria) this;
        }

        public Criteria andCloseDateEqualTo(String value) {
            addCriterion("CLOSE_DATE =", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotEqualTo(String value) {
            addCriterion("CLOSE_DATE <>", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateGreaterThan(String value) {
            addCriterion("CLOSE_DATE >", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateGreaterThanOrEqualTo(String value) {
            addCriterion("CLOSE_DATE >=", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateLessThan(String value) {
            addCriterion("CLOSE_DATE <", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateLessThanOrEqualTo(String value) {
            addCriterion("CLOSE_DATE <=", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateLike(String value) {
            addCriterion("CLOSE_DATE like", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotLike(String value) {
            addCriterion("CLOSE_DATE not like", value, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateIn(List<String> values) {
            addCriterion("CLOSE_DATE in", values, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotIn(List<String> values) {
            addCriterion("CLOSE_DATE not in", values, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateBetween(String value1, String value2) {
            addCriterion("CLOSE_DATE between", value1, value2, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseDateNotBetween(String value1, String value2) {
            addCriterion("CLOSE_DATE not between", value1, value2, "closeDate");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNull() {
            addCriterion("CLOSE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNotNull() {
            addCriterion("CLOSE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeEqualTo(String value) {
            addCriterion("CLOSE_TIME =", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotEqualTo(String value) {
            addCriterion("CLOSE_TIME <>", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThan(String value) {
            addCriterion("CLOSE_TIME >", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CLOSE_TIME >=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThan(String value) {
            addCriterion("CLOSE_TIME <", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThanOrEqualTo(String value) {
            addCriterion("CLOSE_TIME <=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLike(String value) {
            addCriterion("CLOSE_TIME like", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotLike(String value) {
            addCriterion("CLOSE_TIME not like", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIn(List<String> values) {
            addCriterion("CLOSE_TIME in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotIn(List<String> values) {
            addCriterion("CLOSE_TIME not in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeBetween(String value1, String value2) {
            addCriterion("CLOSE_TIME between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotBetween(String value1, String value2) {
            addCriterion("CLOSE_TIME not between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompIsNull() {
            addCriterion("TICKET1_EMIT_COMP is null");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompIsNotNull() {
            addCriterion("TICKET1_EMIT_COMP is not null");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompEqualTo(String value) {
            addCriterion("TICKET1_EMIT_COMP =", value, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompNotEqualTo(String value) {
            addCriterion("TICKET1_EMIT_COMP <>", value, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompGreaterThan(String value) {
            addCriterion("TICKET1_EMIT_COMP >", value, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET1_EMIT_COMP >=", value, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompLessThan(String value) {
            addCriterion("TICKET1_EMIT_COMP <", value, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompLessThanOrEqualTo(String value) {
            addCriterion("TICKET1_EMIT_COMP <=", value, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompLike(String value) {
            addCriterion("TICKET1_EMIT_COMP like", value, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompNotLike(String value) {
            addCriterion("TICKET1_EMIT_COMP not like", value, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompIn(List<String> values) {
            addCriterion("TICKET1_EMIT_COMP in", values, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompNotIn(List<String> values) {
            addCriterion("TICKET1_EMIT_COMP not in", values, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompBetween(String value1, String value2) {
            addCriterion("TICKET1_EMIT_COMP between", value1, value2, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCompNotBetween(String value1, String value2) {
            addCriterion("TICKET1_EMIT_COMP not between", value1, value2, "ticket1EmitComp");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdIsNull() {
            addCriterion("TICKET1_EMIT_GUBUN_CD is null");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdIsNotNull() {
            addCriterion("TICKET1_EMIT_GUBUN_CD is not null");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdEqualTo(String value) {
            addCriterion("TICKET1_EMIT_GUBUN_CD =", value, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdNotEqualTo(String value) {
            addCriterion("TICKET1_EMIT_GUBUN_CD <>", value, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdGreaterThan(String value) {
            addCriterion("TICKET1_EMIT_GUBUN_CD >", value, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET1_EMIT_GUBUN_CD >=", value, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdLessThan(String value) {
            addCriterion("TICKET1_EMIT_GUBUN_CD <", value, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdLessThanOrEqualTo(String value) {
            addCriterion("TICKET1_EMIT_GUBUN_CD <=", value, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdLike(String value) {
            addCriterion("TICKET1_EMIT_GUBUN_CD like", value, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdNotLike(String value) {
            addCriterion("TICKET1_EMIT_GUBUN_CD not like", value, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdIn(List<String> values) {
            addCriterion("TICKET1_EMIT_GUBUN_CD in", values, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdNotIn(List<String> values) {
            addCriterion("TICKET1_EMIT_GUBUN_CD not in", values, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdBetween(String value1, String value2) {
            addCriterion("TICKET1_EMIT_GUBUN_CD between", value1, value2, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitGubunCdNotBetween(String value1, String value2) {
            addCriterion("TICKET1_EMIT_GUBUN_CD not between", value1, value2, "ticket1EmitGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntIsNull() {
            addCriterion("TICKET1_EMIT_CNT is null");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntIsNotNull() {
            addCriterion("TICKET1_EMIT_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntEqualTo(Integer value) {
            addCriterion("TICKET1_EMIT_CNT =", value, "ticket1EmitCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntNotEqualTo(Integer value) {
            addCriterion("TICKET1_EMIT_CNT <>", value, "ticket1EmitCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntGreaterThan(Integer value) {
            addCriterion("TICKET1_EMIT_CNT >", value, "ticket1EmitCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("TICKET1_EMIT_CNT >=", value, "ticket1EmitCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntLessThan(Integer value) {
            addCriterion("TICKET1_EMIT_CNT <", value, "ticket1EmitCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntLessThanOrEqualTo(Integer value) {
            addCriterion("TICKET1_EMIT_CNT <=", value, "ticket1EmitCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntIn(List<Integer> values) {
            addCriterion("TICKET1_EMIT_CNT in", values, "ticket1EmitCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntNotIn(List<Integer> values) {
            addCriterion("TICKET1_EMIT_CNT not in", values, "ticket1EmitCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntBetween(Integer value1, Integer value2) {
            addCriterion("TICKET1_EMIT_CNT between", value1, value2, "ticket1EmitCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1EmitCntNotBetween(Integer value1, Integer value2) {
            addCriterion("TICKET1_EMIT_CNT not between", value1, value2, "ticket1EmitCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompIsNull() {
            addCriterion("TICKET1_BACK_COMP is null");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompIsNotNull() {
            addCriterion("TICKET1_BACK_COMP is not null");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompEqualTo(String value) {
            addCriterion("TICKET1_BACK_COMP =", value, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompNotEqualTo(String value) {
            addCriterion("TICKET1_BACK_COMP <>", value, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompGreaterThan(String value) {
            addCriterion("TICKET1_BACK_COMP >", value, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET1_BACK_COMP >=", value, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompLessThan(String value) {
            addCriterion("TICKET1_BACK_COMP <", value, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompLessThanOrEqualTo(String value) {
            addCriterion("TICKET1_BACK_COMP <=", value, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompLike(String value) {
            addCriterion("TICKET1_BACK_COMP like", value, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompNotLike(String value) {
            addCriterion("TICKET1_BACK_COMP not like", value, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompIn(List<String> values) {
            addCriterion("TICKET1_BACK_COMP in", values, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompNotIn(List<String> values) {
            addCriterion("TICKET1_BACK_COMP not in", values, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompBetween(String value1, String value2) {
            addCriterion("TICKET1_BACK_COMP between", value1, value2, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCompNotBetween(String value1, String value2) {
            addCriterion("TICKET1_BACK_COMP not between", value1, value2, "ticket1BackComp");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdIsNull() {
            addCriterion("TICKET1_BACK_GUBUN_CD is null");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdIsNotNull() {
            addCriterion("TICKET1_BACK_GUBUN_CD is not null");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdEqualTo(String value) {
            addCriterion("TICKET1_BACK_GUBUN_CD =", value, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdNotEqualTo(String value) {
            addCriterion("TICKET1_BACK_GUBUN_CD <>", value, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdGreaterThan(String value) {
            addCriterion("TICKET1_BACK_GUBUN_CD >", value, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdGreaterThanOrEqualTo(String value) {
            addCriterion("TICKET1_BACK_GUBUN_CD >=", value, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdLessThan(String value) {
            addCriterion("TICKET1_BACK_GUBUN_CD <", value, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdLessThanOrEqualTo(String value) {
            addCriterion("TICKET1_BACK_GUBUN_CD <=", value, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdLike(String value) {
            addCriterion("TICKET1_BACK_GUBUN_CD like", value, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdNotLike(String value) {
            addCriterion("TICKET1_BACK_GUBUN_CD not like", value, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdIn(List<String> values) {
            addCriterion("TICKET1_BACK_GUBUN_CD in", values, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdNotIn(List<String> values) {
            addCriterion("TICKET1_BACK_GUBUN_CD not in", values, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdBetween(String value1, String value2) {
            addCriterion("TICKET1_BACK_GUBUN_CD between", value1, value2, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackGubunCdNotBetween(String value1, String value2) {
            addCriterion("TICKET1_BACK_GUBUN_CD not between", value1, value2, "ticket1BackGubunCd");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntIsNull() {
            addCriterion("TICKET1_BACK_CNT is null");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntIsNotNull() {
            addCriterion("TICKET1_BACK_CNT is not null");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntEqualTo(Integer value) {
            addCriterion("TICKET1_BACK_CNT =", value, "ticket1BackCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntNotEqualTo(Integer value) {
            addCriterion("TICKET1_BACK_CNT <>", value, "ticket1BackCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntGreaterThan(Integer value) {
            addCriterion("TICKET1_BACK_CNT >", value, "ticket1BackCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("TICKET1_BACK_CNT >=", value, "ticket1BackCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntLessThan(Integer value) {
            addCriterion("TICKET1_BACK_CNT <", value, "ticket1BackCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntLessThanOrEqualTo(Integer value) {
            addCriterion("TICKET1_BACK_CNT <=", value, "ticket1BackCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntIn(List<Integer> values) {
            addCriterion("TICKET1_BACK_CNT in", values, "ticket1BackCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntNotIn(List<Integer> values) {
            addCriterion("TICKET1_BACK_CNT not in", values, "ticket1BackCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntBetween(Integer value1, Integer value2) {
            addCriterion("TICKET1_BACK_CNT between", value1, value2, "ticket1BackCnt");
            return (Criteria) this;
        }

        public Criteria andTicket1BackCntNotBetween(Integer value1, Integer value2) {
            addCriterion("TICKET1_BACK_CNT not between", value1, value2, "ticket1BackCnt");
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

        public Criteria andInsertUidIsNull() {
            addCriterion("INSERT_UID is null");
            return (Criteria) this;
        }

        public Criteria andInsertUidIsNotNull() {
            addCriterion("INSERT_UID is not null");
            return (Criteria) this;
        }

        public Criteria andInsertUidEqualTo(String value) {
            addCriterion("INSERT_UID =", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidNotEqualTo(String value) {
            addCriterion("INSERT_UID <>", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidGreaterThan(String value) {
            addCriterion("INSERT_UID >", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidGreaterThanOrEqualTo(String value) {
            addCriterion("INSERT_UID >=", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidLessThan(String value) {
            addCriterion("INSERT_UID <", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidLessThanOrEqualTo(String value) {
            addCriterion("INSERT_UID <=", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidLike(String value) {
            addCriterion("INSERT_UID like", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidNotLike(String value) {
            addCriterion("INSERT_UID not like", value, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidIn(List<String> values) {
            addCriterion("INSERT_UID in", values, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidNotIn(List<String> values) {
            addCriterion("INSERT_UID not in", values, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidBetween(String value1, String value2) {
            addCriterion("INSERT_UID between", value1, value2, "insertUid");
            return (Criteria) this;
        }

        public Criteria andInsertUidNotBetween(String value1, String value2) {
            addCriterion("INSERT_UID not between", value1, value2, "insertUid");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated do_not_delete_during_merge Wed Jul 30 15:45:45 KST 2014
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
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
package com.nicetcm.nibsplus.broker.msg.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.nicetcm.nibsplus.broker.msg.model.TFnRealtimeTrade;
import com.nicetcm.nibsplus.broker.msg.model.TFnRealtimeTradeSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnRealtimeTradeSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnRealtimeTradeSpec;
import java.util.List;
import java.util.Map;

public class TFnRealtimeTradeSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String countBySpec(TFnRealtimeTradeSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_REALTIME_TRADE");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String deleteBySpec(TFnRealtimeTradeSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_REALTIME_TRADE");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String insertSelective(TFnRealtimeTrade record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_REALTIME_TRADE");
        
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            VALUES("BRANCH_CD", "#{branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDealDate() != null) {
            VALUES("DEAL_DATE", "#{dealDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDealTime() != null) {
            VALUES("DEAL_TIME", "#{dealTime,jdbcType=VARCHAR}");
        }
        
        if (record.getRemainAmt() != null) {
            VALUES("REMAIN_AMT", "#{remainAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10000Remcnt() != null) {
            VALUES("IN_10000_REMCNT", "#{in10000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn5000Remcnt() != null) {
            VALUES("IN_5000_REMCNT", "#{in5000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn1000Remcnt() != null) {
            VALUES("IN_1000_REMCNT", "#{in1000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10000Remcnt() != null) {
            VALUES("OUT_10000_REMCNT", "#{out10000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut5000Remcnt() != null) {
            VALUES("OUT_5000_REMCNT", "#{out5000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut1000Remcnt() != null) {
            VALUES("OUT_1000_REMCNT", "#{out1000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut100Remcnt() != null) {
            VALUES("OUT_100_REMCNT", "#{out100Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10000Cnt() != null) {
            VALUES("IN_10000_CNT", "#{in10000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn5000Cnt() != null) {
            VALUES("IN_5000_CNT", "#{in5000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn1000Cnt() != null) {
            VALUES("IN_1000_CNT", "#{in1000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10000Cnt() != null) {
            VALUES("OUT_10000_CNT", "#{out10000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut5000Cnt() != null) {
            VALUES("OUT_5000_CNT", "#{out5000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut1000Cnt() != null) {
            VALUES("OUT_1000_CNT", "#{out1000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut100Cnt() != null) {
            VALUES("OUT_100_CNT", "#{out100Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getNoRtnAmt() != null) {
            VALUES("NO_RTN_AMT", "#{noRtnAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getNoRtnNo() != null) {
            VALUES("NO_RTN_NO", "#{noRtnNo,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            VALUES("UPDATE_UID", "#{updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getIn50000Remcnt() != null) {
            VALUES("IN_50000_REMCNT", "#{in50000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn50000Cnt() != null) {
            VALUES("IN_50000_CNT", "#{in50000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn500Remcnt() != null) {
            VALUES("IN_500_REMCNT", "#{in500Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn100Remcnt() != null) {
            VALUES("IN_100_REMCNT", "#{in100Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn50Remcnt() != null) {
            VALUES("IN_50_REMCNT", "#{in50Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10Remcnt() != null) {
            VALUES("IN_10_REMCNT", "#{in10Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn500Cnt() != null) {
            VALUES("IN_500_CNT", "#{in500Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn100Cnt() != null) {
            VALUES("IN_100_CNT", "#{in100Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn50Cnt() != null) {
            VALUES("IN_50_CNT", "#{in50Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10Cnt() != null) {
            VALUES("IN_10_CNT", "#{in10Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50000Remcnt() != null) {
            VALUES("OUT_50000_REMCNT", "#{out50000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50000Cnt() != null) {
            VALUES("OUT_50000_CNT", "#{out50000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut500Remcnt() != null) {
            VALUES("OUT_500_REMCNT", "#{out500Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut500Cnt() != null) {
            VALUES("OUT_500_CNT", "#{out500Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50Remcnt() != null) {
            VALUES("OUT_50_REMCNT", "#{out50Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50Cnt() != null) {
            VALUES("OUT_50_CNT", "#{out50Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10Remcnt() != null) {
            VALUES("OUT_10_REMCNT", "#{out10Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10Cnt() != null) {
            VALUES("OUT_10_CNT", "#{out10Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getSubOrgCd() != null) {
            VALUES("SUB_ORG_CD", "#{subOrgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getAtmDealNo() != null) {
            VALUES("ATM_DEAL_NO", "#{atmDealNo,jdbcType=DECIMAL}");
        }
        
        if (record.getTransSeq() != null) {
            VALUES("TRANS_SEQ", "#{transSeq,jdbcType=VARCHAR}");
        }
        
        if (record.getTransDate() != null) {
            VALUES("TRANS_DATE", "#{transDate,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String selectBySpec(TFnRealtimeTradeSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("ORG_CD");
        } else {
            SELECT("ORG_CD");
        }
        SELECT("BRANCH_CD");
        SELECT("MAC_NO");
        SELECT("DEAL_DATE");
        SELECT("DEAL_TIME");
        SELECT("REMAIN_AMT");
        SELECT("IN_10000_REMCNT");
        SELECT("IN_5000_REMCNT");
        SELECT("IN_1000_REMCNT");
        SELECT("OUT_10000_REMCNT");
        SELECT("OUT_5000_REMCNT");
        SELECT("OUT_1000_REMCNT");
        SELECT("OUT_100_REMCNT");
        SELECT("IN_10000_CNT");
        SELECT("IN_5000_CNT");
        SELECT("IN_1000_CNT");
        SELECT("OUT_10000_CNT");
        SELECT("OUT_5000_CNT");
        SELECT("OUT_1000_CNT");
        SELECT("OUT_100_CNT");
        SELECT("NO_RTN_AMT");
        SELECT("NO_RTN_NO");
        SELECT("UPDATE_DATE");
        SELECT("UPDATE_UID");
        SELECT("IN_50000_REMCNT");
        SELECT("IN_50000_CNT");
        SELECT("IN_500_REMCNT");
        SELECT("IN_100_REMCNT");
        SELECT("IN_50_REMCNT");
        SELECT("IN_10_REMCNT");
        SELECT("IN_500_CNT");
        SELECT("IN_100_CNT");
        SELECT("IN_50_CNT");
        SELECT("IN_10_CNT");
        SELECT("OUT_50000_REMCNT");
        SELECT("OUT_50000_CNT");
        SELECT("OUT_500_REMCNT");
        SELECT("OUT_500_CNT");
        SELECT("OUT_50_REMCNT");
        SELECT("OUT_50_CNT");
        SELECT("OUT_10_REMCNT");
        SELECT("OUT_10_CNT");
        SELECT("SUB_ORG_CD");
        SELECT("ATM_DEAL_NO");
        SELECT("TRANS_SEQ");
        SELECT("TRANS_DATE");
        FROM("OP.T_FN_REALTIME_TRADE");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnRealtimeTrade record = (TFnRealtimeTrade) parameter.get("record");
        TFnRealtimeTradeSpec spec = (TFnRealtimeTradeSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_FN_REALTIME_TRADE");
        
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDealDate() != null) {
            SET("DEAL_DATE = #{record.dealDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDealTime() != null) {
            SET("DEAL_TIME = #{record.dealTime,jdbcType=VARCHAR}");
        }
        
        if (record.getRemainAmt() != null) {
            SET("REMAIN_AMT = #{record.remainAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10000Remcnt() != null) {
            SET("IN_10000_REMCNT = #{record.in10000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn5000Remcnt() != null) {
            SET("IN_5000_REMCNT = #{record.in5000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn1000Remcnt() != null) {
            SET("IN_1000_REMCNT = #{record.in1000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10000Remcnt() != null) {
            SET("OUT_10000_REMCNT = #{record.out10000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut5000Remcnt() != null) {
            SET("OUT_5000_REMCNT = #{record.out5000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut1000Remcnt() != null) {
            SET("OUT_1000_REMCNT = #{record.out1000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut100Remcnt() != null) {
            SET("OUT_100_REMCNT = #{record.out100Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10000Cnt() != null) {
            SET("IN_10000_CNT = #{record.in10000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn5000Cnt() != null) {
            SET("IN_5000_CNT = #{record.in5000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn1000Cnt() != null) {
            SET("IN_1000_CNT = #{record.in1000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10000Cnt() != null) {
            SET("OUT_10000_CNT = #{record.out10000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut5000Cnt() != null) {
            SET("OUT_5000_CNT = #{record.out5000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut1000Cnt() != null) {
            SET("OUT_1000_CNT = #{record.out1000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut100Cnt() != null) {
            SET("OUT_100_CNT = #{record.out100Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getNoRtnAmt() != null) {
            SET("NO_RTN_AMT = #{record.noRtnAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getNoRtnNo() != null) {
            SET("NO_RTN_NO = #{record.noRtnNo,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getIn50000Remcnt() != null) {
            SET("IN_50000_REMCNT = #{record.in50000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn50000Cnt() != null) {
            SET("IN_50000_CNT = #{record.in50000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn500Remcnt() != null) {
            SET("IN_500_REMCNT = #{record.in500Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn100Remcnt() != null) {
            SET("IN_100_REMCNT = #{record.in100Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn50Remcnt() != null) {
            SET("IN_50_REMCNT = #{record.in50Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10Remcnt() != null) {
            SET("IN_10_REMCNT = #{record.in10Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn500Cnt() != null) {
            SET("IN_500_CNT = #{record.in500Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn100Cnt() != null) {
            SET("IN_100_CNT = #{record.in100Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn50Cnt() != null) {
            SET("IN_50_CNT = #{record.in50Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10Cnt() != null) {
            SET("IN_10_CNT = #{record.in10Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50000Remcnt() != null) {
            SET("OUT_50000_REMCNT = #{record.out50000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50000Cnt() != null) {
            SET("OUT_50000_CNT = #{record.out50000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut500Remcnt() != null) {
            SET("OUT_500_REMCNT = #{record.out500Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut500Cnt() != null) {
            SET("OUT_500_CNT = #{record.out500Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50Remcnt() != null) {
            SET("OUT_50_REMCNT = #{record.out50Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50Cnt() != null) {
            SET("OUT_50_CNT = #{record.out50Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10Remcnt() != null) {
            SET("OUT_10_REMCNT = #{record.out10Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10Cnt() != null) {
            SET("OUT_10_CNT = #{record.out10Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getSubOrgCd() != null) {
            SET("SUB_ORG_CD = #{record.subOrgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getAtmDealNo() != null) {
            SET("ATM_DEAL_NO = #{record.atmDealNo,jdbcType=DECIMAL}");
        }
        
        if (record.getTransSeq() != null) {
            SET("TRANS_SEQ = #{record.transSeq,jdbcType=VARCHAR}");
        }
        
        if (record.getTransDate() != null) {
            SET("TRANS_DATE = #{record.transDate,jdbcType=VARCHAR}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_REALTIME_TRADE");
        
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("DEAL_DATE = #{record.dealDate,jdbcType=VARCHAR}");
        SET("DEAL_TIME = #{record.dealTime,jdbcType=VARCHAR}");
        SET("REMAIN_AMT = #{record.remainAmt,jdbcType=DECIMAL}");
        SET("IN_10000_REMCNT = #{record.in10000Remcnt,jdbcType=DECIMAL}");
        SET("IN_5000_REMCNT = #{record.in5000Remcnt,jdbcType=DECIMAL}");
        SET("IN_1000_REMCNT = #{record.in1000Remcnt,jdbcType=DECIMAL}");
        SET("OUT_10000_REMCNT = #{record.out10000Remcnt,jdbcType=DECIMAL}");
        SET("OUT_5000_REMCNT = #{record.out5000Remcnt,jdbcType=DECIMAL}");
        SET("OUT_1000_REMCNT = #{record.out1000Remcnt,jdbcType=DECIMAL}");
        SET("OUT_100_REMCNT = #{record.out100Remcnt,jdbcType=DECIMAL}");
        SET("IN_10000_CNT = #{record.in10000Cnt,jdbcType=DECIMAL}");
        SET("IN_5000_CNT = #{record.in5000Cnt,jdbcType=DECIMAL}");
        SET("IN_1000_CNT = #{record.in1000Cnt,jdbcType=DECIMAL}");
        SET("OUT_10000_CNT = #{record.out10000Cnt,jdbcType=DECIMAL}");
        SET("OUT_5000_CNT = #{record.out5000Cnt,jdbcType=DECIMAL}");
        SET("OUT_1000_CNT = #{record.out1000Cnt,jdbcType=DECIMAL}");
        SET("OUT_100_CNT = #{record.out100Cnt,jdbcType=DECIMAL}");
        SET("NO_RTN_AMT = #{record.noRtnAmt,jdbcType=DECIMAL}");
        SET("NO_RTN_NO = #{record.noRtnNo,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("IN_50000_REMCNT = #{record.in50000Remcnt,jdbcType=DECIMAL}");
        SET("IN_50000_CNT = #{record.in50000Cnt,jdbcType=DECIMAL}");
        SET("IN_500_REMCNT = #{record.in500Remcnt,jdbcType=DECIMAL}");
        SET("IN_100_REMCNT = #{record.in100Remcnt,jdbcType=DECIMAL}");
        SET("IN_50_REMCNT = #{record.in50Remcnt,jdbcType=DECIMAL}");
        SET("IN_10_REMCNT = #{record.in10Remcnt,jdbcType=DECIMAL}");
        SET("IN_500_CNT = #{record.in500Cnt,jdbcType=DECIMAL}");
        SET("IN_100_CNT = #{record.in100Cnt,jdbcType=DECIMAL}");
        SET("IN_50_CNT = #{record.in50Cnt,jdbcType=DECIMAL}");
        SET("IN_10_CNT = #{record.in10Cnt,jdbcType=DECIMAL}");
        SET("OUT_50000_REMCNT = #{record.out50000Remcnt,jdbcType=DECIMAL}");
        SET("OUT_50000_CNT = #{record.out50000Cnt,jdbcType=DECIMAL}");
        SET("OUT_500_REMCNT = #{record.out500Remcnt,jdbcType=DECIMAL}");
        SET("OUT_500_CNT = #{record.out500Cnt,jdbcType=DECIMAL}");
        SET("OUT_50_REMCNT = #{record.out50Remcnt,jdbcType=DECIMAL}");
        SET("OUT_50_CNT = #{record.out50Cnt,jdbcType=DECIMAL}");
        SET("OUT_10_REMCNT = #{record.out10Remcnt,jdbcType=DECIMAL}");
        SET("OUT_10_CNT = #{record.out10Cnt,jdbcType=DECIMAL}");
        SET("SUB_ORG_CD = #{record.subOrgCd,jdbcType=VARCHAR}");
        SET("ATM_DEAL_NO = #{record.atmDealNo,jdbcType=DECIMAL}");
        SET("TRANS_SEQ = #{record.transSeq,jdbcType=VARCHAR}");
        SET("TRANS_DATE = #{record.transDate,jdbcType=VARCHAR}");
        
        TFnRealtimeTradeSpec spec = (TFnRealtimeTradeSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnRealtimeTrade record) {
        BEGIN();
        UPDATE("OP.T_FN_REALTIME_TRADE");
        
        if (record.getRemainAmt() != null) {
            SET("REMAIN_AMT = #{remainAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10000Remcnt() != null) {
            SET("IN_10000_REMCNT = #{in10000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn5000Remcnt() != null) {
            SET("IN_5000_REMCNT = #{in5000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn1000Remcnt() != null) {
            SET("IN_1000_REMCNT = #{in1000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10000Remcnt() != null) {
            SET("OUT_10000_REMCNT = #{out10000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut5000Remcnt() != null) {
            SET("OUT_5000_REMCNT = #{out5000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut1000Remcnt() != null) {
            SET("OUT_1000_REMCNT = #{out1000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut100Remcnt() != null) {
            SET("OUT_100_REMCNT = #{out100Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10000Cnt() != null) {
            SET("IN_10000_CNT = #{in10000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn5000Cnt() != null) {
            SET("IN_5000_CNT = #{in5000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn1000Cnt() != null) {
            SET("IN_1000_CNT = #{in1000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10000Cnt() != null) {
            SET("OUT_10000_CNT = #{out10000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut5000Cnt() != null) {
            SET("OUT_5000_CNT = #{out5000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut1000Cnt() != null) {
            SET("OUT_1000_CNT = #{out1000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut100Cnt() != null) {
            SET("OUT_100_CNT = #{out100Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getNoRtnAmt() != null) {
            SET("NO_RTN_AMT = #{noRtnAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getNoRtnNo() != null) {
            SET("NO_RTN_NO = #{noRtnNo,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getIn50000Remcnt() != null) {
            SET("IN_50000_REMCNT = #{in50000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn50000Cnt() != null) {
            SET("IN_50000_CNT = #{in50000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn500Remcnt() != null) {
            SET("IN_500_REMCNT = #{in500Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn100Remcnt() != null) {
            SET("IN_100_REMCNT = #{in100Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn50Remcnt() != null) {
            SET("IN_50_REMCNT = #{in50Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10Remcnt() != null) {
            SET("IN_10_REMCNT = #{in10Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn500Cnt() != null) {
            SET("IN_500_CNT = #{in500Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn100Cnt() != null) {
            SET("IN_100_CNT = #{in100Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn50Cnt() != null) {
            SET("IN_50_CNT = #{in50Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getIn10Cnt() != null) {
            SET("IN_10_CNT = #{in10Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50000Remcnt() != null) {
            SET("OUT_50000_REMCNT = #{out50000Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50000Cnt() != null) {
            SET("OUT_50000_CNT = #{out50000Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut500Remcnt() != null) {
            SET("OUT_500_REMCNT = #{out500Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut500Cnt() != null) {
            SET("OUT_500_CNT = #{out500Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50Remcnt() != null) {
            SET("OUT_50_REMCNT = #{out50Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut50Cnt() != null) {
            SET("OUT_50_CNT = #{out50Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10Remcnt() != null) {
            SET("OUT_10_REMCNT = #{out10Remcnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOut10Cnt() != null) {
            SET("OUT_10_CNT = #{out10Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getSubOrgCd() != null) {
            SET("SUB_ORG_CD = #{subOrgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getAtmDealNo() != null) {
            SET("ATM_DEAL_NO = #{atmDealNo,jdbcType=DECIMAL}");
        }
        
        if (record.getTransSeq() != null) {
            SET("TRANS_SEQ = #{transSeq,jdbcType=VARCHAR}");
        }
        
        if (record.getTransDate() != null) {
            SET("TRANS_DATE = #{transDate,jdbcType=VARCHAR}");
        }
        
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        WHERE("DEAL_DATE = #{dealDate,jdbcType=VARCHAR}");
        WHERE("DEAL_TIME = #{dealTime,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    protected void applyWhere(TFnRealtimeTradeSpec spec, boolean includeSpecPhrase) {
        if (spec == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeSpecPhrase) {
            parmPhrase1 = "%s #{spec.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{spec.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{spec.oredCriteria[%d].allCriteria[%d].value} and #{spec.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{spec.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{spec.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{spec.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{spec.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = spec.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}
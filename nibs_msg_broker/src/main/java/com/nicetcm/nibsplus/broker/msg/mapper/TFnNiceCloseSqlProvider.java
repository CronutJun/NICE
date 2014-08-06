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

import com.nicetcm.nibsplus.broker.msg.model.TFnNiceClose;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseSpec;
import java.util.List;
import java.util.Map;

public class TFnNiceCloseSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String countBySpec(TFnNiceCloseSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_NICE_CLOSE");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String deleteBySpec(TFnNiceCloseSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_NICE_CLOSE");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String insertSelective(TFnNiceClose record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_NICE_CLOSE");
        
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseDate() != null) {
            VALUES("CLOSE_DATE", "#{closeDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDeptCd() != null) {
            VALUES("DEPT_CD", "#{deptCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeCd() != null) {
            VALUES("OFFICE_CD", "#{officeCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTeamCd() != null) {
            VALUES("TEAM_CD", "#{teamCd,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseTime() != null) {
            VALUES("CLOSE_TIME", "#{closeTime,jdbcType=VARCHAR}");
        }
        
        if (record.getTrack1EmitCnt() != null) {
            VALUES("TRACK1_EMIT_CNT", "#{track1EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack1BackCnt() != null) {
            VALUES("TRACK1_BACK_CNT", "#{track1BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack2EmitCnt() != null) {
            VALUES("TRACK2_EMIT_CNT", "#{track2EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack2BackCnt() != null) {
            VALUES("TRACK2_BACK_CNT", "#{track2BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack3EmitCnt() != null) {
            VALUES("TRACK3_EMIT_CNT", "#{track3EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack3BackCnt() != null) {
            VALUES("TRACK3_BACK_CNT", "#{track3BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack4EmitCnt() != null) {
            VALUES("TRACK4_EMIT_CNT", "#{track4EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack4BackCnt() != null) {
            VALUES("TRACK4_BACK_CNT", "#{track4BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotEmitCnt() != null) {
            VALUES("TOT_EMIT_CNT", "#{totEmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotEmitAmt() != null) {
            VALUES("TOT_EMIT_AMT", "#{totEmitAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDepositCnt() != null) {
            VALUES("DEPOSIT_CNT", "#{depositCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getDepositAmt() != null) {
            VALUES("DEPOSIT_AMT", "#{depositAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getServiceCnt() != null) {
            VALUES("SERVICE_CNT", "#{serviceCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getServiceAmt() != null) {
            VALUES("SERVICE_AMT", "#{serviceAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getAbroadCnt() != null) {
            VALUES("ABROAD_CNT", "#{abroadCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getAbroadAmt() != null) {
            VALUES("ABROAD_AMT", "#{abroadAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getEtcCnt() != null) {
            VALUES("ETC_CNT", "#{etcCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getEtcAmt() != null) {
            VALUES("ETC_AMT", "#{etcAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCashNotTakenCnt() != null) {
            VALUES("CASH_NOT_TAKEN_CNT", "#{cashNotTakenCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCashNotTakenAmt() != null) {
            VALUES("CASH_NOT_TAKEN_AMT", "#{cashNotTakenAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getUndeterminedBillsCnt() != null) {
            VALUES("UNDETERMINED_BILLS_CNT", "#{undeterminedBillsCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getUndeterminedBillsAmt() != null) {
            VALUES("UNDETERMINED_BILLS_AMT", "#{undeterminedBillsAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getSendFailsCnt() != null) {
            VALUES("SEND_FAILS_CNT", "#{sendFailsCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getSentFailsAmt() != null) {
            VALUES("SENT_FAILS_AMT", "#{sentFailsAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getTimeOutCnt() != null) {
            VALUES("TIME_OUT_CNT", "#{timeOutCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTimeOutAmt() != null) {
            VALUES("TIME_OUT_AMT", "#{timeOutAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInsertUid() != null) {
            VALUES("INSERT_UID", "#{insertUid,jdbcType=VARCHAR}");
        }
        
        if (record.getTotInCnt() != null) {
            VALUES("TOT_IN_CNT", "#{totInCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotInAmt() != null) {
            VALUES("TOT_IN_AMT", "#{totInAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckInCnt() != null) {
            VALUES("CHECK_IN_CNT", "#{checkInCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckInAmt() != null) {
            VALUES("CHECK_IN_AMT", "#{checkInAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckOutCnt() != null) {
            VALUES("CHECK_OUT_CNT", "#{checkOutCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckOutAmt() != null) {
            VALUES("CHECK_OUT_AMT", "#{checkOutAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getEmitCntCw14() != null) {
            VALUES("EMIT_CNT_CW14", "#{emitCntCw14,jdbcType=DECIMAL}");
        }
        
        if (record.getEmitCntCw54() != null) {
            VALUES("EMIT_CNT_CW54", "#{emitCntCw54,jdbcType=DECIMAL}");
        }
        
        if (record.getEmitCntCw15() != null) {
            VALUES("EMIT_CNT_CW15", "#{emitCntCw15,jdbcType=DECIMAL}");
        }
        
        if (record.getInCntCw14() != null) {
            VALUES("IN_CNT_CW14", "#{inCntCw14,jdbcType=DECIMAL}");
        }
        
        if (record.getInCntCw54() != null) {
            VALUES("IN_CNT_CW54", "#{inCntCw54,jdbcType=DECIMAL}");
        }
        
        if (record.getInCntCw15() != null) {
            VALUES("IN_CNT_CW15", "#{inCntCw15,jdbcType=DECIMAL}");
        }
        
        if (record.getInAmtEtc() != null) {
            VALUES("IN_AMT_ETC", "#{inAmtEtc,jdbcType=DECIMAL}");
        }
        
        if (record.getTrackUseType() != null) {
            VALUES("TRACK_USE_TYPE", "#{trackUseType,jdbcType=CHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String selectBySpec(TFnNiceCloseSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("MAC_NO");
        } else {
            SELECT("MAC_NO");
        }
        SELECT("CLOSE_DATE");
        SELECT("DEPT_CD");
        SELECT("OFFICE_CD");
        SELECT("TEAM_CD");
        SELECT("CLOSE_TIME");
        SELECT("TRACK1_EMIT_CNT");
        SELECT("TRACK1_BACK_CNT");
        SELECT("TRACK2_EMIT_CNT");
        SELECT("TRACK2_BACK_CNT");
        SELECT("TRACK3_EMIT_CNT");
        SELECT("TRACK3_BACK_CNT");
        SELECT("TRACK4_EMIT_CNT");
        SELECT("TRACK4_BACK_CNT");
        SELECT("TOT_EMIT_CNT");
        SELECT("TOT_EMIT_AMT");
        SELECT("DEPOSIT_CNT");
        SELECT("DEPOSIT_AMT");
        SELECT("SERVICE_CNT");
        SELECT("SERVICE_AMT");
        SELECT("ABROAD_CNT");
        SELECT("ABROAD_AMT");
        SELECT("ETC_CNT");
        SELECT("ETC_AMT");
        SELECT("CASH_NOT_TAKEN_CNT");
        SELECT("CASH_NOT_TAKEN_AMT");
        SELECT("UNDETERMINED_BILLS_CNT");
        SELECT("UNDETERMINED_BILLS_AMT");
        SELECT("SEND_FAILS_CNT");
        SELECT("SENT_FAILS_AMT");
        SELECT("TIME_OUT_CNT");
        SELECT("TIME_OUT_AMT");
        SELECT("INSERT_DATE");
        SELECT("INSERT_UID");
        SELECT("TOT_IN_CNT");
        SELECT("TOT_IN_AMT");
        SELECT("CHECK_IN_CNT");
        SELECT("CHECK_IN_AMT");
        SELECT("CHECK_OUT_CNT");
        SELECT("CHECK_OUT_AMT");
        SELECT("EMIT_CNT_CW14");
        SELECT("EMIT_CNT_CW54");
        SELECT("EMIT_CNT_CW15");
        SELECT("IN_CNT_CW14");
        SELECT("IN_CNT_CW54");
        SELECT("IN_CNT_CW15");
        SELECT("IN_AMT_ETC");
        SELECT("TRACK_USE_TYPE");
        FROM("OP.T_FN_NICE_CLOSE");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnNiceClose record = (TFnNiceClose) parameter.get("record");
        TFnNiceCloseSpec spec = (TFnNiceCloseSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_FN_NICE_CLOSE");
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseDate() != null) {
            SET("CLOSE_DATE = #{record.closeDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDeptCd() != null) {
            SET("DEPT_CD = #{record.deptCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeCd() != null) {
            SET("OFFICE_CD = #{record.officeCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTeamCd() != null) {
            SET("TEAM_CD = #{record.teamCd,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseTime() != null) {
            SET("CLOSE_TIME = #{record.closeTime,jdbcType=VARCHAR}");
        }
        
        if (record.getTrack1EmitCnt() != null) {
            SET("TRACK1_EMIT_CNT = #{record.track1EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack1BackCnt() != null) {
            SET("TRACK1_BACK_CNT = #{record.track1BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack2EmitCnt() != null) {
            SET("TRACK2_EMIT_CNT = #{record.track2EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack2BackCnt() != null) {
            SET("TRACK2_BACK_CNT = #{record.track2BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack3EmitCnt() != null) {
            SET("TRACK3_EMIT_CNT = #{record.track3EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack3BackCnt() != null) {
            SET("TRACK3_BACK_CNT = #{record.track3BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack4EmitCnt() != null) {
            SET("TRACK4_EMIT_CNT = #{record.track4EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack4BackCnt() != null) {
            SET("TRACK4_BACK_CNT = #{record.track4BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotEmitCnt() != null) {
            SET("TOT_EMIT_CNT = #{record.totEmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotEmitAmt() != null) {
            SET("TOT_EMIT_AMT = #{record.totEmitAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDepositCnt() != null) {
            SET("DEPOSIT_CNT = #{record.depositCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getDepositAmt() != null) {
            SET("DEPOSIT_AMT = #{record.depositAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getServiceCnt() != null) {
            SET("SERVICE_CNT = #{record.serviceCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getServiceAmt() != null) {
            SET("SERVICE_AMT = #{record.serviceAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getAbroadCnt() != null) {
            SET("ABROAD_CNT = #{record.abroadCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getAbroadAmt() != null) {
            SET("ABROAD_AMT = #{record.abroadAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getEtcCnt() != null) {
            SET("ETC_CNT = #{record.etcCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getEtcAmt() != null) {
            SET("ETC_AMT = #{record.etcAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCashNotTakenCnt() != null) {
            SET("CASH_NOT_TAKEN_CNT = #{record.cashNotTakenCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCashNotTakenAmt() != null) {
            SET("CASH_NOT_TAKEN_AMT = #{record.cashNotTakenAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getUndeterminedBillsCnt() != null) {
            SET("UNDETERMINED_BILLS_CNT = #{record.undeterminedBillsCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getUndeterminedBillsAmt() != null) {
            SET("UNDETERMINED_BILLS_AMT = #{record.undeterminedBillsAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getSendFailsCnt() != null) {
            SET("SEND_FAILS_CNT = #{record.sendFailsCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getSentFailsAmt() != null) {
            SET("SENT_FAILS_AMT = #{record.sentFailsAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getTimeOutCnt() != null) {
            SET("TIME_OUT_CNT = #{record.timeOutCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTimeOutAmt() != null) {
            SET("TIME_OUT_AMT = #{record.timeOutAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        }
        
        if (record.getTotInCnt() != null) {
            SET("TOT_IN_CNT = #{record.totInCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotInAmt() != null) {
            SET("TOT_IN_AMT = #{record.totInAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckInCnt() != null) {
            SET("CHECK_IN_CNT = #{record.checkInCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckInAmt() != null) {
            SET("CHECK_IN_AMT = #{record.checkInAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckOutCnt() != null) {
            SET("CHECK_OUT_CNT = #{record.checkOutCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckOutAmt() != null) {
            SET("CHECK_OUT_AMT = #{record.checkOutAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getEmitCntCw14() != null) {
            SET("EMIT_CNT_CW14 = #{record.emitCntCw14,jdbcType=DECIMAL}");
        }
        
        if (record.getEmitCntCw54() != null) {
            SET("EMIT_CNT_CW54 = #{record.emitCntCw54,jdbcType=DECIMAL}");
        }
        
        if (record.getEmitCntCw15() != null) {
            SET("EMIT_CNT_CW15 = #{record.emitCntCw15,jdbcType=DECIMAL}");
        }
        
        if (record.getInCntCw14() != null) {
            SET("IN_CNT_CW14 = #{record.inCntCw14,jdbcType=DECIMAL}");
        }
        
        if (record.getInCntCw54() != null) {
            SET("IN_CNT_CW54 = #{record.inCntCw54,jdbcType=DECIMAL}");
        }
        
        if (record.getInCntCw15() != null) {
            SET("IN_CNT_CW15 = #{record.inCntCw15,jdbcType=DECIMAL}");
        }
        
        if (record.getInAmtEtc() != null) {
            SET("IN_AMT_ETC = #{record.inAmtEtc,jdbcType=DECIMAL}");
        }
        
        if (record.getTrackUseType() != null) {
            SET("TRACK_USE_TYPE = #{record.trackUseType,jdbcType=CHAR}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_NICE_CLOSE");
        
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("CLOSE_DATE = #{record.closeDate,jdbcType=VARCHAR}");
        SET("DEPT_CD = #{record.deptCd,jdbcType=VARCHAR}");
        SET("OFFICE_CD = #{record.officeCd,jdbcType=VARCHAR}");
        SET("TEAM_CD = #{record.teamCd,jdbcType=VARCHAR}");
        SET("CLOSE_TIME = #{record.closeTime,jdbcType=VARCHAR}");
        SET("TRACK1_EMIT_CNT = #{record.track1EmitCnt,jdbcType=DECIMAL}");
        SET("TRACK1_BACK_CNT = #{record.track1BackCnt,jdbcType=DECIMAL}");
        SET("TRACK2_EMIT_CNT = #{record.track2EmitCnt,jdbcType=DECIMAL}");
        SET("TRACK2_BACK_CNT = #{record.track2BackCnt,jdbcType=DECIMAL}");
        SET("TRACK3_EMIT_CNT = #{record.track3EmitCnt,jdbcType=DECIMAL}");
        SET("TRACK3_BACK_CNT = #{record.track3BackCnt,jdbcType=DECIMAL}");
        SET("TRACK4_EMIT_CNT = #{record.track4EmitCnt,jdbcType=DECIMAL}");
        SET("TRACK4_BACK_CNT = #{record.track4BackCnt,jdbcType=DECIMAL}");
        SET("TOT_EMIT_CNT = #{record.totEmitCnt,jdbcType=DECIMAL}");
        SET("TOT_EMIT_AMT = #{record.totEmitAmt,jdbcType=DECIMAL}");
        SET("DEPOSIT_CNT = #{record.depositCnt,jdbcType=DECIMAL}");
        SET("DEPOSIT_AMT = #{record.depositAmt,jdbcType=DECIMAL}");
        SET("SERVICE_CNT = #{record.serviceCnt,jdbcType=DECIMAL}");
        SET("SERVICE_AMT = #{record.serviceAmt,jdbcType=DECIMAL}");
        SET("ABROAD_CNT = #{record.abroadCnt,jdbcType=DECIMAL}");
        SET("ABROAD_AMT = #{record.abroadAmt,jdbcType=DECIMAL}");
        SET("ETC_CNT = #{record.etcCnt,jdbcType=DECIMAL}");
        SET("ETC_AMT = #{record.etcAmt,jdbcType=DECIMAL}");
        SET("CASH_NOT_TAKEN_CNT = #{record.cashNotTakenCnt,jdbcType=DECIMAL}");
        SET("CASH_NOT_TAKEN_AMT = #{record.cashNotTakenAmt,jdbcType=DECIMAL}");
        SET("UNDETERMINED_BILLS_CNT = #{record.undeterminedBillsCnt,jdbcType=DECIMAL}");
        SET("UNDETERMINED_BILLS_AMT = #{record.undeterminedBillsAmt,jdbcType=DECIMAL}");
        SET("SEND_FAILS_CNT = #{record.sendFailsCnt,jdbcType=DECIMAL}");
        SET("SENT_FAILS_AMT = #{record.sentFailsAmt,jdbcType=DECIMAL}");
        SET("TIME_OUT_CNT = #{record.timeOutCnt,jdbcType=DECIMAL}");
        SET("TIME_OUT_AMT = #{record.timeOutAmt,jdbcType=DECIMAL}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        SET("TOT_IN_CNT = #{record.totInCnt,jdbcType=DECIMAL}");
        SET("TOT_IN_AMT = #{record.totInAmt,jdbcType=DECIMAL}");
        SET("CHECK_IN_CNT = #{record.checkInCnt,jdbcType=DECIMAL}");
        SET("CHECK_IN_AMT = #{record.checkInAmt,jdbcType=DECIMAL}");
        SET("CHECK_OUT_CNT = #{record.checkOutCnt,jdbcType=DECIMAL}");
        SET("CHECK_OUT_AMT = #{record.checkOutAmt,jdbcType=DECIMAL}");
        SET("EMIT_CNT_CW14 = #{record.emitCntCw14,jdbcType=DECIMAL}");
        SET("EMIT_CNT_CW54 = #{record.emitCntCw54,jdbcType=DECIMAL}");
        SET("EMIT_CNT_CW15 = #{record.emitCntCw15,jdbcType=DECIMAL}");
        SET("IN_CNT_CW14 = #{record.inCntCw14,jdbcType=DECIMAL}");
        SET("IN_CNT_CW54 = #{record.inCntCw54,jdbcType=DECIMAL}");
        SET("IN_CNT_CW15 = #{record.inCntCw15,jdbcType=DECIMAL}");
        SET("IN_AMT_ETC = #{record.inAmtEtc,jdbcType=DECIMAL}");
        SET("TRACK_USE_TYPE = #{record.trackUseType,jdbcType=CHAR}");
        
        TFnNiceCloseSpec spec = (TFnNiceCloseSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnNiceClose record) {
        BEGIN();
        UPDATE("OP.T_FN_NICE_CLOSE");
        
        if (record.getDeptCd() != null) {
            SET("DEPT_CD = #{deptCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeCd() != null) {
            SET("OFFICE_CD = #{officeCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTeamCd() != null) {
            SET("TEAM_CD = #{teamCd,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseTime() != null) {
            SET("CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}");
        }
        
        if (record.getTrack1EmitCnt() != null) {
            SET("TRACK1_EMIT_CNT = #{track1EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack1BackCnt() != null) {
            SET("TRACK1_BACK_CNT = #{track1BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack2EmitCnt() != null) {
            SET("TRACK2_EMIT_CNT = #{track2EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack2BackCnt() != null) {
            SET("TRACK2_BACK_CNT = #{track2BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack3EmitCnt() != null) {
            SET("TRACK3_EMIT_CNT = #{track3EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack3BackCnt() != null) {
            SET("TRACK3_BACK_CNT = #{track3BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack4EmitCnt() != null) {
            SET("TRACK4_EMIT_CNT = #{track4EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTrack4BackCnt() != null) {
            SET("TRACK4_BACK_CNT = #{track4BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotEmitCnt() != null) {
            SET("TOT_EMIT_CNT = #{totEmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotEmitAmt() != null) {
            SET("TOT_EMIT_AMT = #{totEmitAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getDepositCnt() != null) {
            SET("DEPOSIT_CNT = #{depositCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getDepositAmt() != null) {
            SET("DEPOSIT_AMT = #{depositAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getServiceCnt() != null) {
            SET("SERVICE_CNT = #{serviceCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getServiceAmt() != null) {
            SET("SERVICE_AMT = #{serviceAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getAbroadCnt() != null) {
            SET("ABROAD_CNT = #{abroadCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getAbroadAmt() != null) {
            SET("ABROAD_AMT = #{abroadAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getEtcCnt() != null) {
            SET("ETC_CNT = #{etcCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getEtcAmt() != null) {
            SET("ETC_AMT = #{etcAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCashNotTakenCnt() != null) {
            SET("CASH_NOT_TAKEN_CNT = #{cashNotTakenCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCashNotTakenAmt() != null) {
            SET("CASH_NOT_TAKEN_AMT = #{cashNotTakenAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getUndeterminedBillsCnt() != null) {
            SET("UNDETERMINED_BILLS_CNT = #{undeterminedBillsCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getUndeterminedBillsAmt() != null) {
            SET("UNDETERMINED_BILLS_AMT = #{undeterminedBillsAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getSendFailsCnt() != null) {
            SET("SEND_FAILS_CNT = #{sendFailsCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getSentFailsAmt() != null) {
            SET("SENT_FAILS_AMT = #{sentFailsAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getTimeOutCnt() != null) {
            SET("TIME_OUT_CNT = #{timeOutCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTimeOutAmt() != null) {
            SET("TIME_OUT_AMT = #{timeOutAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{insertUid,jdbcType=VARCHAR}");
        }
        
        if (record.getTotInCnt() != null) {
            SET("TOT_IN_CNT = #{totInCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotInAmt() != null) {
            SET("TOT_IN_AMT = #{totInAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckInCnt() != null) {
            SET("CHECK_IN_CNT = #{checkInCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckInAmt() != null) {
            SET("CHECK_IN_AMT = #{checkInAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckOutCnt() != null) {
            SET("CHECK_OUT_CNT = #{checkOutCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheckOutAmt() != null) {
            SET("CHECK_OUT_AMT = #{checkOutAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getEmitCntCw14() != null) {
            SET("EMIT_CNT_CW14 = #{emitCntCw14,jdbcType=DECIMAL}");
        }
        
        if (record.getEmitCntCw54() != null) {
            SET("EMIT_CNT_CW54 = #{emitCntCw54,jdbcType=DECIMAL}");
        }
        
        if (record.getEmitCntCw15() != null) {
            SET("EMIT_CNT_CW15 = #{emitCntCw15,jdbcType=DECIMAL}");
        }
        
        if (record.getInCntCw14() != null) {
            SET("IN_CNT_CW14 = #{inCntCw14,jdbcType=DECIMAL}");
        }
        
        if (record.getInCntCw54() != null) {
            SET("IN_CNT_CW54 = #{inCntCw54,jdbcType=DECIMAL}");
        }
        
        if (record.getInCntCw15() != null) {
            SET("IN_CNT_CW15 = #{inCntCw15,jdbcType=DECIMAL}");
        }
        
        if (record.getInAmtEtc() != null) {
            SET("IN_AMT_ETC = #{inAmtEtc,jdbcType=DECIMAL}");
        }
        
        if (record.getTrackUseType() != null) {
            SET("TRACK_USE_TYPE = #{trackUseType,jdbcType=CHAR}");
        }
        
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        WHERE("CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    protected void applyWhere(TFnNiceCloseSpec spec, boolean includeSpecPhrase) {
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
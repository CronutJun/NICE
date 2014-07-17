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

import com.nicetcm.nibsplus.broker.msg.model.TFnMac;
import com.nicetcm.nibsplus.broker.msg.model.TFnMacSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnMacSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnMacSpec;
import java.util.List;
import java.util.Map;

public class TFnMacSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String countBySpec(TFnMacSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_MAC");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String deleteBySpec(TFnMacSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_MAC");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String insertSelective(TFnMac record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_MAC");
        
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            VALUES("BRANCH_CD", "#{branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteCd() != null) {
            VALUES("SITE_CD", "#{siteCd,jdbcType=VARCHAR}");
        }
        
        if (record.getInMacAmt() != null) {
            VALUES("IN_MAC_AMT", "#{inMacAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreMacAmt() != null) {
            VALUES("PRE_MAC_AMT", "#{preMacAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreInMacAmt() != null) {
            VALUES("PRE_IN_MAC_AMT", "#{preInMacAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreSafeNo() != null) {
            VALUES("PRE_SAFE_NO", "#{preSafeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeMacTempAmt() != null) {
            VALUES("OFFICE_MAC_TEMP_AMT", "#{officeMacTempAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreAmt() != null) {
            VALUES("PRE_AMT", "#{preAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPlusCnt() != null) {
            VALUES("PLUS_CNT", "#{plusCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getBeforeCloseDate() != null) {
            VALUES("BEFORE_CLOSE_DATE", "#{beforeCloseDate,jdbcType=VARCHAR}");
        }
        
        if (record.getBeforeCloseTime() != null) {
            VALUES("BEFORE_CLOSE_TIME", "#{beforeCloseTime,jdbcType=VARCHAR}");
        }
        
        if (record.getOldCloseDate() != null) {
            VALUES("OLD_CLOSE_DATE", "#{oldCloseDate,jdbcType=VARCHAR}");
        }
        
        if (record.getOldCloseTime() != null) {
            VALUES("OLD_CLOSE_TIME", "#{oldCloseTime,jdbcType=VARCHAR}");
        }
        
        if (record.getAtmDealNo() != null) {
            VALUES("ATM_DEAL_NO", "#{atmDealNo,jdbcType=VARCHAR}");
        }
        
        if (record.getLastDealTime() != null) {
            VALUES("LAST_DEAL_TIME", "#{lastDealTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFirstDealDate() != null) {
            VALUES("FIRST_DEAL_DATE", "#{firstDealDate,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstAtmDealNo() != null) {
            VALUES("FIRST_ATM_DEAL_NO", "#{firstAtmDealNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSendCheckAmt() != null) {
            VALUES("SEND_CHECK_AMT", "#{sendCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw14() != null) {
            VALUES("IN_MAC_AMT_CW14", "#{inMacAmtCw14,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw54() != null) {
            VALUES("IN_MAC_AMT_CW54", "#{inMacAmtCw54,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw15() != null) {
            VALUES("IN_MAC_AMT_CW15", "#{inMacAmtCw15,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw11() != null) {
            VALUES("IN_MAC_AMT_CW11", "#{inMacAmtCw11,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw51() != null) {
            VALUES("IN_MAC_AMT_CW51", "#{inMacAmtCw51,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw12() != null) {
            VALUES("IN_MAC_AMT_CW12", "#{inMacAmtCw12,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw52() != null) {
            VALUES("IN_MAC_AMT_CW52", "#{inMacAmtCw52,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw13() != null) {
            VALUES("IN_MAC_AMT_CW13", "#{inMacAmtCw13,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw53() != null) {
            VALUES("IN_MAC_AMT_CW53", "#{inMacAmtCw53,jdbcType=DECIMAL}");
        }
        
        if (record.getInsertUid() != null) {
            VALUES("INSERT_UID", "#{insertUid,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            VALUES("UPDATE_UID", "#{updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String selectBySpec(TFnMacSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("ORG_CD");
        } else {
            SELECT("ORG_CD");
        }
        SELECT("BRANCH_CD");
        SELECT("MAC_NO");
        SELECT("SITE_CD");
        SELECT("IN_MAC_AMT");
        SELECT("PRE_MAC_AMT");
        SELECT("PRE_IN_MAC_AMT");
        SELECT("PRE_SAFE_NO");
        SELECT("OFFICE_MAC_TEMP_AMT");
        SELECT("PRE_AMT");
        SELECT("PLUS_CNT");
        SELECT("BEFORE_CLOSE_DATE");
        SELECT("BEFORE_CLOSE_TIME");
        SELECT("OLD_CLOSE_DATE");
        SELECT("OLD_CLOSE_TIME");
        SELECT("ATM_DEAL_NO");
        SELECT("LAST_DEAL_TIME");
        SELECT("FIRST_DEAL_DATE");
        SELECT("FIRST_ATM_DEAL_NO");
        SELECT("SEND_CHECK_AMT");
        SELECT("IN_MAC_AMT_CW14");
        SELECT("IN_MAC_AMT_CW54");
        SELECT("IN_MAC_AMT_CW15");
        SELECT("IN_MAC_AMT_CW11");
        SELECT("IN_MAC_AMT_CW51");
        SELECT("IN_MAC_AMT_CW12");
        SELECT("IN_MAC_AMT_CW52");
        SELECT("IN_MAC_AMT_CW13");
        SELECT("IN_MAC_AMT_CW53");
        SELECT("INSERT_UID");
        SELECT("INSERT_DATE");
        SELECT("UPDATE_UID");
        SELECT("UPDATE_DATE");
        FROM("OP.T_FN_MAC");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnMac record = (TFnMac) parameter.get("record");
        TFnMacSpec spec = (TFnMacSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_FN_MAC");
        
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteCd() != null) {
            SET("SITE_CD = #{record.siteCd,jdbcType=VARCHAR}");
        }
        
        if (record.getInMacAmt() != null) {
            SET("IN_MAC_AMT = #{record.inMacAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreMacAmt() != null) {
            SET("PRE_MAC_AMT = #{record.preMacAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreInMacAmt() != null) {
            SET("PRE_IN_MAC_AMT = #{record.preInMacAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreSafeNo() != null) {
            SET("PRE_SAFE_NO = #{record.preSafeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeMacTempAmt() != null) {
            SET("OFFICE_MAC_TEMP_AMT = #{record.officeMacTempAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreAmt() != null) {
            SET("PRE_AMT = #{record.preAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPlusCnt() != null) {
            SET("PLUS_CNT = #{record.plusCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getBeforeCloseDate() != null) {
            SET("BEFORE_CLOSE_DATE = #{record.beforeCloseDate,jdbcType=VARCHAR}");
        }
        
        if (record.getBeforeCloseTime() != null) {
            SET("BEFORE_CLOSE_TIME = #{record.beforeCloseTime,jdbcType=VARCHAR}");
        }
        
        if (record.getOldCloseDate() != null) {
            SET("OLD_CLOSE_DATE = #{record.oldCloseDate,jdbcType=VARCHAR}");
        }
        
        if (record.getOldCloseTime() != null) {
            SET("OLD_CLOSE_TIME = #{record.oldCloseTime,jdbcType=VARCHAR}");
        }
        
        if (record.getAtmDealNo() != null) {
            SET("ATM_DEAL_NO = #{record.atmDealNo,jdbcType=VARCHAR}");
        }
        
        if (record.getLastDealTime() != null) {
            SET("LAST_DEAL_TIME = #{record.lastDealTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFirstDealDate() != null) {
            SET("FIRST_DEAL_DATE = #{record.firstDealDate,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstAtmDealNo() != null) {
            SET("FIRST_ATM_DEAL_NO = #{record.firstAtmDealNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSendCheckAmt() != null) {
            SET("SEND_CHECK_AMT = #{record.sendCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw14() != null) {
            SET("IN_MAC_AMT_CW14 = #{record.inMacAmtCw14,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw54() != null) {
            SET("IN_MAC_AMT_CW54 = #{record.inMacAmtCw54,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw15() != null) {
            SET("IN_MAC_AMT_CW15 = #{record.inMacAmtCw15,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw11() != null) {
            SET("IN_MAC_AMT_CW11 = #{record.inMacAmtCw11,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw51() != null) {
            SET("IN_MAC_AMT_CW51 = #{record.inMacAmtCw51,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw12() != null) {
            SET("IN_MAC_AMT_CW12 = #{record.inMacAmtCw12,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw52() != null) {
            SET("IN_MAC_AMT_CW52 = #{record.inMacAmtCw52,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw13() != null) {
            SET("IN_MAC_AMT_CW13 = #{record.inMacAmtCw13,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw53() != null) {
            SET("IN_MAC_AMT_CW53 = #{record.inMacAmtCw53,jdbcType=DECIMAL}");
        }
        
        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_MAC");
        
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("SITE_CD = #{record.siteCd,jdbcType=VARCHAR}");
        SET("IN_MAC_AMT = #{record.inMacAmt,jdbcType=DECIMAL}");
        SET("PRE_MAC_AMT = #{record.preMacAmt,jdbcType=DECIMAL}");
        SET("PRE_IN_MAC_AMT = #{record.preInMacAmt,jdbcType=DECIMAL}");
        SET("PRE_SAFE_NO = #{record.preSafeNo,jdbcType=VARCHAR}");
        SET("OFFICE_MAC_TEMP_AMT = #{record.officeMacTempAmt,jdbcType=DECIMAL}");
        SET("PRE_AMT = #{record.preAmt,jdbcType=DECIMAL}");
        SET("PLUS_CNT = #{record.plusCnt,jdbcType=DECIMAL}");
        SET("BEFORE_CLOSE_DATE = #{record.beforeCloseDate,jdbcType=VARCHAR}");
        SET("BEFORE_CLOSE_TIME = #{record.beforeCloseTime,jdbcType=VARCHAR}");
        SET("OLD_CLOSE_DATE = #{record.oldCloseDate,jdbcType=VARCHAR}");
        SET("OLD_CLOSE_TIME = #{record.oldCloseTime,jdbcType=VARCHAR}");
        SET("ATM_DEAL_NO = #{record.atmDealNo,jdbcType=VARCHAR}");
        SET("LAST_DEAL_TIME = #{record.lastDealTime,jdbcType=TIMESTAMP}");
        SET("FIRST_DEAL_DATE = #{record.firstDealDate,jdbcType=VARCHAR}");
        SET("FIRST_ATM_DEAL_NO = #{record.firstAtmDealNo,jdbcType=VARCHAR}");
        SET("SEND_CHECK_AMT = #{record.sendCheckAmt,jdbcType=DECIMAL}");
        SET("IN_MAC_AMT_CW14 = #{record.inMacAmtCw14,jdbcType=DECIMAL}");
        SET("IN_MAC_AMT_CW54 = #{record.inMacAmtCw54,jdbcType=DECIMAL}");
        SET("IN_MAC_AMT_CW15 = #{record.inMacAmtCw15,jdbcType=DECIMAL}");
        SET("IN_MAC_AMT_CW11 = #{record.inMacAmtCw11,jdbcType=DECIMAL}");
        SET("IN_MAC_AMT_CW51 = #{record.inMacAmtCw51,jdbcType=DECIMAL}");
        SET("IN_MAC_AMT_CW12 = #{record.inMacAmtCw12,jdbcType=DECIMAL}");
        SET("IN_MAC_AMT_CW52 = #{record.inMacAmtCw52,jdbcType=DECIMAL}");
        SET("IN_MAC_AMT_CW13 = #{record.inMacAmtCw13,jdbcType=DECIMAL}");
        SET("IN_MAC_AMT_CW53 = #{record.inMacAmtCw53,jdbcType=DECIMAL}");
        SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        
        TFnMacSpec spec = (TFnMacSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnMac record) {
        BEGIN();
        UPDATE("OP.T_FN_MAC");
        
        if (record.getSiteCd() != null) {
            SET("SITE_CD = #{siteCd,jdbcType=VARCHAR}");
        }
        
        if (record.getInMacAmt() != null) {
            SET("IN_MAC_AMT = #{inMacAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreMacAmt() != null) {
            SET("PRE_MAC_AMT = #{preMacAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreInMacAmt() != null) {
            SET("PRE_IN_MAC_AMT = #{preInMacAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreSafeNo() != null) {
            SET("PRE_SAFE_NO = #{preSafeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeMacTempAmt() != null) {
            SET("OFFICE_MAC_TEMP_AMT = #{officeMacTempAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPreAmt() != null) {
            SET("PRE_AMT = #{preAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getPlusCnt() != null) {
            SET("PLUS_CNT = #{plusCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getBeforeCloseDate() != null) {
            SET("BEFORE_CLOSE_DATE = #{beforeCloseDate,jdbcType=VARCHAR}");
        }
        
        if (record.getBeforeCloseTime() != null) {
            SET("BEFORE_CLOSE_TIME = #{beforeCloseTime,jdbcType=VARCHAR}");
        }
        
        if (record.getOldCloseDate() != null) {
            SET("OLD_CLOSE_DATE = #{oldCloseDate,jdbcType=VARCHAR}");
        }
        
        if (record.getOldCloseTime() != null) {
            SET("OLD_CLOSE_TIME = #{oldCloseTime,jdbcType=VARCHAR}");
        }
        
        if (record.getAtmDealNo() != null) {
            SET("ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}");
        }
        
        if (record.getLastDealTime() != null) {
            SET("LAST_DEAL_TIME = #{lastDealTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getFirstDealDate() != null) {
            SET("FIRST_DEAL_DATE = #{firstDealDate,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstAtmDealNo() != null) {
            SET("FIRST_ATM_DEAL_NO = #{firstAtmDealNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSendCheckAmt() != null) {
            SET("SEND_CHECK_AMT = #{sendCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw14() != null) {
            SET("IN_MAC_AMT_CW14 = #{inMacAmtCw14,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw54() != null) {
            SET("IN_MAC_AMT_CW54 = #{inMacAmtCw54,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw15() != null) {
            SET("IN_MAC_AMT_CW15 = #{inMacAmtCw15,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw11() != null) {
            SET("IN_MAC_AMT_CW11 = #{inMacAmtCw11,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw51() != null) {
            SET("IN_MAC_AMT_CW51 = #{inMacAmtCw51,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw12() != null) {
            SET("IN_MAC_AMT_CW12 = #{inMacAmtCw12,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw52() != null) {
            SET("IN_MAC_AMT_CW52 = #{inMacAmtCw52,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw13() != null) {
            SET("IN_MAC_AMT_CW13 = #{inMacAmtCw13,jdbcType=DECIMAL}");
        }
        
        if (record.getInMacAmtCw53() != null) {
            SET("IN_MAC_AMT_CW53 = #{inMacAmtCw53,jdbcType=DECIMAL}");
        }
        
        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{insertUid,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    protected void applyWhere(TFnMacSpec spec, boolean includeSpecPhrase) {
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
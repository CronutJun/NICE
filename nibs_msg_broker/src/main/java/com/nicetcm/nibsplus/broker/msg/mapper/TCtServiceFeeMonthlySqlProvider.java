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

import com.nicetcm.nibsplus.broker.msg.model.TCtServiceFeeMonthly;
import com.nicetcm.nibsplus.broker.msg.model.TCtServiceFeeMonthlySpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TCtServiceFeeMonthlySpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TCtServiceFeeMonthlySpec;
import java.util.List;
import java.util.Map;

public class TCtServiceFeeMonthlySqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    public String countBySpec(TCtServiceFeeMonthlySpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_CT_SERVICE_FEE_MONTHLY");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    public String deleteBySpec(TCtServiceFeeMonthlySpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_CT_SERVICE_FEE_MONTHLY");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    public String insertSelective(TCtServiceFeeMonthly record) {
        BEGIN();
        INSERT_INTO("OP.T_CT_SERVICE_FEE_MONTHLY");
        
        if (record.getYearMon() != null) {
            VALUES("YEAR_MON", "#{yearMon,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            VALUES("BRANCH_CD", "#{branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteCd() != null) {
            VALUES("SITE_CD", "#{siteCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchNm() != null) {
            VALUES("BRANCH_NM", "#{branchNm,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteNm() != null) {
            VALUES("SITE_NM", "#{siteNm,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeNm() != null) {
            VALUES("OFFICE_NM", "#{officeNm,jdbcType=VARCHAR}");
        }
        
        if (record.getMacCnt() != null) {
            VALUES("MAC_CNT", "#{macCnt,jdbcType=VARCHAR}");
        }
        
        if (record.getBaseFee() != null) {
            VALUES("BASE_FEE", "#{baseFee,jdbcType=VARCHAR}");
        }
        
        if (record.getMonthFee() != null) {
            VALUES("MONTH_FEE", "#{monthFee,jdbcType=VARCHAR}");
        }
        
        if (record.getAddFee() != null) {
            VALUES("ADD_FEE", "#{addFee,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeVatEsc() != null) {
            VALUES("FEE_VAT_ESC", "#{feeVatEsc,jdbcType=VARCHAR}");
        }
        
        if (record.getOvertimeFee() != null) {
            VALUES("OVERTIME_FEE", "#{overtimeFee,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckFee() != null) {
            VALUES("CHECK_FEE", "#{checkFee,jdbcType=VARCHAR}");
        }
        
        if (record.getGuardMacFee() != null) {
            VALUES("GUARD_MAC_FEE", "#{guardMacFee,jdbcType=VARCHAR}");
        }
        
        if (record.getSurtax() != null) {
            VALUES("SURTAX", "#{surtax,jdbcType=VARCHAR}");
        }
        
        if (record.getDemandFee() != null) {
            VALUES("DEMAND_FEE", "#{demandFee,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteFee() != null) {
            VALUES("SITE_FEE", "#{siteFee,jdbcType=VARCHAR}");
        }
        
        if (record.getOperDay() != null) {
            VALUES("OPER_DAY", "#{operDay,jdbcType=VARCHAR}");
        }
        
        if (record.getPenaltyAmt() != null) {
            VALUES("PENALTY_AMT", "#{penaltyAmt,jdbcType=VARCHAR}");
        }
        
        if (record.getJumSum() != null) {
            VALUES("JUM_SUM", "#{jumSum,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            VALUES("REMARK", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckYn() != null) {
            VALUES("CHECK_YN", "#{checkYn,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgSendYn() != null) {
            VALUES("ORG_SEND_YN", "#{orgSendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getAgreeYn() != null) {
            VALUES("AGREE_YN", "#{agreeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getLocateType() != null) {
            VALUES("LOCATE_TYPE", "#{locateType,jdbcType=VARCHAR}");
        }
        
        if (record.getOperTimeType() != null) {
            VALUES("OPER_TIME_TYPE", "#{operTimeType,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMacAddYn() != null) {
            VALUES("MAC_ADD_YN", "#{macAddYn,jdbcType=VARCHAR}");
        }
        
        if (record.getOvertimeYn() != null) {
            VALUES("OVERTIME_YN", "#{overtimeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckInYn() != null) {
            VALUES("CHECK_IN_YN", "#{checkInYn,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckInFee() != null) {
            VALUES("CHECK_IN_FEE", "#{checkInFee,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeType() != null) {
            VALUES("FEE_TYPE", "#{feeType,jdbcType=VARCHAR}");
        }
        
        if (record.getIncentiveAmt() != null) {
            VALUES("INCENTIVE_AMT", "#{incentiveAmt,jdbcType=DECIMAL}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    public String selectBySpec(TCtServiceFeeMonthlySpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("YEAR_MON");
        } else {
            SELECT("YEAR_MON");
        }
        SELECT("ORG_CD");
        SELECT("BRANCH_CD");
        SELECT("SITE_CD");
        SELECT("MAC_NO");
        SELECT("BRANCH_NM");
        SELECT("SITE_NM");
        SELECT("OFFICE_NM");
        SELECT("MAC_CNT");
        SELECT("BASE_FEE");
        SELECT("MONTH_FEE");
        SELECT("ADD_FEE");
        SELECT("FEE_VAT_ESC");
        SELECT("OVERTIME_FEE");
        SELECT("CHECK_FEE");
        SELECT("GUARD_MAC_FEE");
        SELECT("SURTAX");
        SELECT("DEMAND_FEE");
        SELECT("SITE_FEE");
        SELECT("OPER_DAY");
        SELECT("PENALTY_AMT");
        SELECT("JUM_SUM");
        SELECT("REMARK");
        SELECT("CHECK_YN");
        SELECT("ORG_SEND_YN");
        SELECT("AGREE_YN");
        SELECT("LOCATE_TYPE");
        SELECT("OPER_TIME_TYPE");
        SELECT("INSERT_DATE");
        SELECT("UPDATE_DATE");
        SELECT("MAC_ADD_YN");
        SELECT("OVERTIME_YN");
        SELECT("CHECK_IN_YN");
        SELECT("CHECK_IN_FEE");
        SELECT("FEE_TYPE");
        SELECT("INCENTIVE_AMT");
        FROM("OP.T_CT_SERVICE_FEE_MONTHLY");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TCtServiceFeeMonthly record = (TCtServiceFeeMonthly) parameter.get("record");
        TCtServiceFeeMonthlySpec spec = (TCtServiceFeeMonthlySpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_CT_SERVICE_FEE_MONTHLY");
        
        if (record.getYearMon() != null) {
            SET("YEAR_MON = #{record.yearMon,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteCd() != null) {
            SET("SITE_CD = #{record.siteCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchNm() != null) {
            SET("BRANCH_NM = #{record.branchNm,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteNm() != null) {
            SET("SITE_NM = #{record.siteNm,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeNm() != null) {
            SET("OFFICE_NM = #{record.officeNm,jdbcType=VARCHAR}");
        }
        
        if (record.getMacCnt() != null) {
            SET("MAC_CNT = #{record.macCnt,jdbcType=VARCHAR}");
        }
        
        if (record.getBaseFee() != null) {
            SET("BASE_FEE = #{record.baseFee,jdbcType=VARCHAR}");
        }
        
        if (record.getMonthFee() != null) {
            SET("MONTH_FEE = #{record.monthFee,jdbcType=VARCHAR}");
        }
        
        if (record.getAddFee() != null) {
            SET("ADD_FEE = #{record.addFee,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeVatEsc() != null) {
            SET("FEE_VAT_ESC = #{record.feeVatEsc,jdbcType=VARCHAR}");
        }
        
        if (record.getOvertimeFee() != null) {
            SET("OVERTIME_FEE = #{record.overtimeFee,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckFee() != null) {
            SET("CHECK_FEE = #{record.checkFee,jdbcType=VARCHAR}");
        }
        
        if (record.getGuardMacFee() != null) {
            SET("GUARD_MAC_FEE = #{record.guardMacFee,jdbcType=VARCHAR}");
        }
        
        if (record.getSurtax() != null) {
            SET("SURTAX = #{record.surtax,jdbcType=VARCHAR}");
        }
        
        if (record.getDemandFee() != null) {
            SET("DEMAND_FEE = #{record.demandFee,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteFee() != null) {
            SET("SITE_FEE = #{record.siteFee,jdbcType=VARCHAR}");
        }
        
        if (record.getOperDay() != null) {
            SET("OPER_DAY = #{record.operDay,jdbcType=VARCHAR}");
        }
        
        if (record.getPenaltyAmt() != null) {
            SET("PENALTY_AMT = #{record.penaltyAmt,jdbcType=VARCHAR}");
        }
        
        if (record.getJumSum() != null) {
            SET("JUM_SUM = #{record.jumSum,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckYn() != null) {
            SET("CHECK_YN = #{record.checkYn,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgSendYn() != null) {
            SET("ORG_SEND_YN = #{record.orgSendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getAgreeYn() != null) {
            SET("AGREE_YN = #{record.agreeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getLocateType() != null) {
            SET("LOCATE_TYPE = #{record.locateType,jdbcType=VARCHAR}");
        }
        
        if (record.getOperTimeType() != null) {
            SET("OPER_TIME_TYPE = #{record.operTimeType,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMacAddYn() != null) {
            SET("MAC_ADD_YN = #{record.macAddYn,jdbcType=VARCHAR}");
        }
        
        if (record.getOvertimeYn() != null) {
            SET("OVERTIME_YN = #{record.overtimeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckInYn() != null) {
            SET("CHECK_IN_YN = #{record.checkInYn,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckInFee() != null) {
            SET("CHECK_IN_FEE = #{record.checkInFee,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeType() != null) {
            SET("FEE_TYPE = #{record.feeType,jdbcType=VARCHAR}");
        }
        
        if (record.getIncentiveAmt() != null) {
            SET("INCENTIVE_AMT = #{record.incentiveAmt,jdbcType=DECIMAL}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_CT_SERVICE_FEE_MONTHLY");
        
        SET("YEAR_MON = #{record.yearMon,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("SITE_CD = #{record.siteCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("BRANCH_NM = #{record.branchNm,jdbcType=VARCHAR}");
        SET("SITE_NM = #{record.siteNm,jdbcType=VARCHAR}");
        SET("OFFICE_NM = #{record.officeNm,jdbcType=VARCHAR}");
        SET("MAC_CNT = #{record.macCnt,jdbcType=VARCHAR}");
        SET("BASE_FEE = #{record.baseFee,jdbcType=VARCHAR}");
        SET("MONTH_FEE = #{record.monthFee,jdbcType=VARCHAR}");
        SET("ADD_FEE = #{record.addFee,jdbcType=VARCHAR}");
        SET("FEE_VAT_ESC = #{record.feeVatEsc,jdbcType=VARCHAR}");
        SET("OVERTIME_FEE = #{record.overtimeFee,jdbcType=VARCHAR}");
        SET("CHECK_FEE = #{record.checkFee,jdbcType=VARCHAR}");
        SET("GUARD_MAC_FEE = #{record.guardMacFee,jdbcType=VARCHAR}");
        SET("SURTAX = #{record.surtax,jdbcType=VARCHAR}");
        SET("DEMAND_FEE = #{record.demandFee,jdbcType=VARCHAR}");
        SET("SITE_FEE = #{record.siteFee,jdbcType=VARCHAR}");
        SET("OPER_DAY = #{record.operDay,jdbcType=VARCHAR}");
        SET("PENALTY_AMT = #{record.penaltyAmt,jdbcType=VARCHAR}");
        SET("JUM_SUM = #{record.jumSum,jdbcType=VARCHAR}");
        SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        SET("CHECK_YN = #{record.checkYn,jdbcType=VARCHAR}");
        SET("ORG_SEND_YN = #{record.orgSendYn,jdbcType=VARCHAR}");
        SET("AGREE_YN = #{record.agreeYn,jdbcType=VARCHAR}");
        SET("LOCATE_TYPE = #{record.locateType,jdbcType=VARCHAR}");
        SET("OPER_TIME_TYPE = #{record.operTimeType,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("MAC_ADD_YN = #{record.macAddYn,jdbcType=VARCHAR}");
        SET("OVERTIME_YN = #{record.overtimeYn,jdbcType=VARCHAR}");
        SET("CHECK_IN_YN = #{record.checkInYn,jdbcType=VARCHAR}");
        SET("CHECK_IN_FEE = #{record.checkInFee,jdbcType=VARCHAR}");
        SET("FEE_TYPE = #{record.feeType,jdbcType=VARCHAR}");
        SET("INCENTIVE_AMT = #{record.incentiveAmt,jdbcType=DECIMAL}");
        
        TCtServiceFeeMonthlySpec spec = (TCtServiceFeeMonthlySpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    public String updateByPrimaryKeySelective(TCtServiceFeeMonthly record) {
        BEGIN();
        UPDATE("OP.T_CT_SERVICE_FEE_MONTHLY");
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchNm() != null) {
            SET("BRANCH_NM = #{branchNm,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteNm() != null) {
            SET("SITE_NM = #{siteNm,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeNm() != null) {
            SET("OFFICE_NM = #{officeNm,jdbcType=VARCHAR}");
        }
        
        if (record.getMacCnt() != null) {
            SET("MAC_CNT = #{macCnt,jdbcType=VARCHAR}");
        }
        
        if (record.getBaseFee() != null) {
            SET("BASE_FEE = #{baseFee,jdbcType=VARCHAR}");
        }
        
        if (record.getMonthFee() != null) {
            SET("MONTH_FEE = #{monthFee,jdbcType=VARCHAR}");
        }
        
        if (record.getAddFee() != null) {
            SET("ADD_FEE = #{addFee,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeVatEsc() != null) {
            SET("FEE_VAT_ESC = #{feeVatEsc,jdbcType=VARCHAR}");
        }
        
        if (record.getOvertimeFee() != null) {
            SET("OVERTIME_FEE = #{overtimeFee,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckFee() != null) {
            SET("CHECK_FEE = #{checkFee,jdbcType=VARCHAR}");
        }
        
        if (record.getGuardMacFee() != null) {
            SET("GUARD_MAC_FEE = #{guardMacFee,jdbcType=VARCHAR}");
        }
        
        if (record.getSurtax() != null) {
            SET("SURTAX = #{surtax,jdbcType=VARCHAR}");
        }
        
        if (record.getDemandFee() != null) {
            SET("DEMAND_FEE = #{demandFee,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteFee() != null) {
            SET("SITE_FEE = #{siteFee,jdbcType=VARCHAR}");
        }
        
        if (record.getOperDay() != null) {
            SET("OPER_DAY = #{operDay,jdbcType=VARCHAR}");
        }
        
        if (record.getPenaltyAmt() != null) {
            SET("PENALTY_AMT = #{penaltyAmt,jdbcType=VARCHAR}");
        }
        
        if (record.getJumSum() != null) {
            SET("JUM_SUM = #{jumSum,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            SET("REMARK = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckYn() != null) {
            SET("CHECK_YN = #{checkYn,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgSendYn() != null) {
            SET("ORG_SEND_YN = #{orgSendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getAgreeYn() != null) {
            SET("AGREE_YN = #{agreeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getLocateType() != null) {
            SET("LOCATE_TYPE = #{locateType,jdbcType=VARCHAR}");
        }
        
        if (record.getOperTimeType() != null) {
            SET("OPER_TIME_TYPE = #{operTimeType,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMacAddYn() != null) {
            SET("MAC_ADD_YN = #{macAddYn,jdbcType=VARCHAR}");
        }
        
        if (record.getOvertimeYn() != null) {
            SET("OVERTIME_YN = #{overtimeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckInYn() != null) {
            SET("CHECK_IN_YN = #{checkInYn,jdbcType=VARCHAR}");
        }
        
        if (record.getCheckInFee() != null) {
            SET("CHECK_IN_FEE = #{checkInFee,jdbcType=VARCHAR}");
        }
        
        if (record.getFeeType() != null) {
            SET("FEE_TYPE = #{feeType,jdbcType=VARCHAR}");
        }
        
        if (record.getIncentiveAmt() != null) {
            SET("INCENTIVE_AMT = #{incentiveAmt,jdbcType=DECIMAL}");
        }
        
        WHERE("YEAR_MON = #{yearMon,jdbcType=VARCHAR}");
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("SITE_CD = #{siteCd,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    protected void applyWhere(TCtServiceFeeMonthlySpec spec, boolean includeSpecPhrase) {
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
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

import com.nicetcm.nibsplus.broker.msg.model.TFnVanDemandDetail;
import com.nicetcm.nibsplus.broker.msg.model.TFnVanDemandDetailSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnVanDemandDetailSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnVanDemandDetailSpec;
import java.util.List;
import java.util.Map;

public class TFnVanDemandDetailSqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_VAN_DEMAND_DETAIL
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String countBySpec(TFnVanDemandDetailSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_VAN_DEMAND_DETAIL");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_VAN_DEMAND_DETAIL
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String deleteBySpec(TFnVanDemandDetailSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_VAN_DEMAND_DETAIL");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_VAN_DEMAND_DETAIL
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String insertSelective(TFnVanDemandDetail record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_VAN_DEMAND_DETAIL");
        if (record.getDemandDate() != null) {
            VALUES("DEMAND_DATE", "#{demandDate,jdbcType=VARCHAR}");
        }
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        if (record.getIntimeWithdrawCnt() != null) {
            VALUES("INTIME_WITHDRAW_CNT",
                    "#{intimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getIntimeWithdrawAmt() != null) {
            VALUES("INTIME_WITHDRAW_AMT",
                    "#{intimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeWithdrawCnt() != null) {
            VALUES("OUTTIME_WITHDRAW_CNT",
                    "#{outtimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeWithdrawAmt() != null) {
            VALUES("OUTTIME_WITHDRAW_AMT",
                    "#{outtimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getIntimeDepositCnt() != null) {
            VALUES("INTIME_DEPOSIT_CNT", "#{intimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getIntimeDepositAmt() != null) {
            VALUES("INTIME_DEPOSIT_AMT", "#{intimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeDepositCnt() != null) {
            VALUES("OUTTIME_DEPOSIT_CNT",
                    "#{outtimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeDepositAmt() != null) {
            VALUES("OUTTIME_DEPOSIT_AMT",
                    "#{outtimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeWithdrawCnt() != null) {
            VALUES("BR_INTIME_WITHDRAW_CNT",
                    "#{brIntimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeWithdrawAmt() != null) {
            VALUES("BR_INTIME_WITHDRAW_AMT",
                    "#{brIntimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeWithdrawCnt() != null) {
            VALUES("BR_OUTTIME_WITHDRAW_CNT",
                    "#{brOuttimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeWithdrawAmt() != null) {
            VALUES("BR_OUTTIME_WITHDRAW_AMT",
                    "#{brOuttimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeDepositCnt() != null) {
            VALUES("BR_INTIME_DEPOSIT_CNT",
                    "#{brIntimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeDepositAmt() != null) {
            VALUES("BR_INTIME_DEPOSIT_AMT",
                    "#{brIntimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeDepositCnt() != null) {
            VALUES("BR_OUTTIME_DEPOSIT_CNT",
                    "#{brOuttimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeDepositAmt() != null) {
            VALUES("BR_OUTTIME_DEPOSIT_AMT",
                    "#{brOuttimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getOrgSendYn() != null) {
            VALUES("ORG_SEND_YN", "#{orgSendYn,jdbcType=VARCHAR}");
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
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_VAN_DEMAND_DETAIL
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String selectBySpec(TFnVanDemandDetailSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("DEMAND_DATE");
        } else {
            SELECT("DEMAND_DATE");
        }
        SELECT("ORG_CD");
        SELECT("INTIME_WITHDRAW_CNT");
        SELECT("INTIME_WITHDRAW_AMT");
        SELECT("OUTTIME_WITHDRAW_CNT");
        SELECT("OUTTIME_WITHDRAW_AMT");
        SELECT("INTIME_DEPOSIT_CNT");
        SELECT("INTIME_DEPOSIT_AMT");
        SELECT("OUTTIME_DEPOSIT_CNT");
        SELECT("OUTTIME_DEPOSIT_AMT");
        SELECT("BR_INTIME_WITHDRAW_CNT");
        SELECT("BR_INTIME_WITHDRAW_AMT");
        SELECT("BR_OUTTIME_WITHDRAW_CNT");
        SELECT("BR_OUTTIME_WITHDRAW_AMT");
        SELECT("BR_INTIME_DEPOSIT_CNT");
        SELECT("BR_INTIME_DEPOSIT_AMT");
        SELECT("BR_OUTTIME_DEPOSIT_CNT");
        SELECT("BR_OUTTIME_DEPOSIT_AMT");
        SELECT("ORG_SEND_YN");
        SELECT("UPDATE_UID");
        SELECT("UPDATE_DATE");
        FROM("OP.T_FN_VAN_DEMAND_DETAIL");
        applyWhere(spec, false);
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_VAN_DEMAND_DETAIL
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnVanDemandDetail record = (TFnVanDemandDetail) parameter
                .get("record");
        TFnVanDemandDetailSpec spec = (TFnVanDemandDetailSpec) parameter
                .get("spec");
        BEGIN();
        UPDATE("OP.T_FN_VAN_DEMAND_DETAIL");
        if (record.getDemandDate() != null) {
            SET("DEMAND_DATE = #{record.demandDate,jdbcType=VARCHAR}");
        }
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        if (record.getIntimeWithdrawCnt() != null) {
            SET("INTIME_WITHDRAW_CNT = #{record.intimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getIntimeWithdrawAmt() != null) {
            SET("INTIME_WITHDRAW_AMT = #{record.intimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeWithdrawCnt() != null) {
            SET("OUTTIME_WITHDRAW_CNT = #{record.outtimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeWithdrawAmt() != null) {
            SET("OUTTIME_WITHDRAW_AMT = #{record.outtimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getIntimeDepositCnt() != null) {
            SET("INTIME_DEPOSIT_CNT = #{record.intimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getIntimeDepositAmt() != null) {
            SET("INTIME_DEPOSIT_AMT = #{record.intimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeDepositCnt() != null) {
            SET("OUTTIME_DEPOSIT_CNT = #{record.outtimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeDepositAmt() != null) {
            SET("OUTTIME_DEPOSIT_AMT = #{record.outtimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeWithdrawCnt() != null) {
            SET("BR_INTIME_WITHDRAW_CNT = #{record.brIntimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeWithdrawAmt() != null) {
            SET("BR_INTIME_WITHDRAW_AMT = #{record.brIntimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeWithdrawCnt() != null) {
            SET("BR_OUTTIME_WITHDRAW_CNT = #{record.brOuttimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeWithdrawAmt() != null) {
            SET("BR_OUTTIME_WITHDRAW_AMT = #{record.brOuttimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeDepositCnt() != null) {
            SET("BR_INTIME_DEPOSIT_CNT = #{record.brIntimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeDepositAmt() != null) {
            SET("BR_INTIME_DEPOSIT_AMT = #{record.brIntimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeDepositCnt() != null) {
            SET("BR_OUTTIME_DEPOSIT_CNT = #{record.brOuttimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeDepositAmt() != null) {
            SET("BR_OUTTIME_DEPOSIT_AMT = #{record.brOuttimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getOrgSendYn() != null) {
            SET("ORG_SEND_YN = #{record.orgSendYn,jdbcType=VARCHAR}");
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
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_VAN_DEMAND_DETAIL
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_VAN_DEMAND_DETAIL");
        SET("DEMAND_DATE = #{record.demandDate,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("INTIME_WITHDRAW_CNT = #{record.intimeWithdrawCnt,jdbcType=DECIMAL}");
        SET("INTIME_WITHDRAW_AMT = #{record.intimeWithdrawAmt,jdbcType=DECIMAL}");
        SET("OUTTIME_WITHDRAW_CNT = #{record.outtimeWithdrawCnt,jdbcType=DECIMAL}");
        SET("OUTTIME_WITHDRAW_AMT = #{record.outtimeWithdrawAmt,jdbcType=DECIMAL}");
        SET("INTIME_DEPOSIT_CNT = #{record.intimeDepositCnt,jdbcType=DECIMAL}");
        SET("INTIME_DEPOSIT_AMT = #{record.intimeDepositAmt,jdbcType=DECIMAL}");
        SET("OUTTIME_DEPOSIT_CNT = #{record.outtimeDepositCnt,jdbcType=DECIMAL}");
        SET("OUTTIME_DEPOSIT_AMT = #{record.outtimeDepositAmt,jdbcType=DECIMAL}");
        SET("BR_INTIME_WITHDRAW_CNT = #{record.brIntimeWithdrawCnt,jdbcType=DECIMAL}");
        SET("BR_INTIME_WITHDRAW_AMT = #{record.brIntimeWithdrawAmt,jdbcType=DECIMAL}");
        SET("BR_OUTTIME_WITHDRAW_CNT = #{record.brOuttimeWithdrawCnt,jdbcType=DECIMAL}");
        SET("BR_OUTTIME_WITHDRAW_AMT = #{record.brOuttimeWithdrawAmt,jdbcType=DECIMAL}");
        SET("BR_INTIME_DEPOSIT_CNT = #{record.brIntimeDepositCnt,jdbcType=DECIMAL}");
        SET("BR_INTIME_DEPOSIT_AMT = #{record.brIntimeDepositAmt,jdbcType=DECIMAL}");
        SET("BR_OUTTIME_DEPOSIT_CNT = #{record.brOuttimeDepositCnt,jdbcType=DECIMAL}");
        SET("BR_OUTTIME_DEPOSIT_AMT = #{record.brOuttimeDepositAmt,jdbcType=DECIMAL}");
        SET("ORG_SEND_YN = #{record.orgSendYn,jdbcType=VARCHAR}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        TFnVanDemandDetailSpec spec = (TFnVanDemandDetailSpec) parameter
                .get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_VAN_DEMAND_DETAIL
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnVanDemandDetail record) {
        BEGIN();
        UPDATE("OP.T_FN_VAN_DEMAND_DETAIL");
        if (record.getIntimeWithdrawCnt() != null) {
            SET("INTIME_WITHDRAW_CNT = #{intimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getIntimeWithdrawAmt() != null) {
            SET("INTIME_WITHDRAW_AMT = #{intimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeWithdrawCnt() != null) {
            SET("OUTTIME_WITHDRAW_CNT = #{outtimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeWithdrawAmt() != null) {
            SET("OUTTIME_WITHDRAW_AMT = #{outtimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getIntimeDepositCnt() != null) {
            SET("INTIME_DEPOSIT_CNT = #{intimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getIntimeDepositAmt() != null) {
            SET("INTIME_DEPOSIT_AMT = #{intimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeDepositCnt() != null) {
            SET("OUTTIME_DEPOSIT_CNT = #{outtimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getOuttimeDepositAmt() != null) {
            SET("OUTTIME_DEPOSIT_AMT = #{outtimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeWithdrawCnt() != null) {
            SET("BR_INTIME_WITHDRAW_CNT = #{brIntimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeWithdrawAmt() != null) {
            SET("BR_INTIME_WITHDRAW_AMT = #{brIntimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeWithdrawCnt() != null) {
            SET("BR_OUTTIME_WITHDRAW_CNT = #{brOuttimeWithdrawCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeWithdrawAmt() != null) {
            SET("BR_OUTTIME_WITHDRAW_AMT = #{brOuttimeWithdrawAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeDepositCnt() != null) {
            SET("BR_INTIME_DEPOSIT_CNT = #{brIntimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrIntimeDepositAmt() != null) {
            SET("BR_INTIME_DEPOSIT_AMT = #{brIntimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeDepositCnt() != null) {
            SET("BR_OUTTIME_DEPOSIT_CNT = #{brOuttimeDepositCnt,jdbcType=DECIMAL}");
        }
        if (record.getBrOuttimeDepositAmt() != null) {
            SET("BR_OUTTIME_DEPOSIT_AMT = #{brOuttimeDepositAmt,jdbcType=DECIMAL}");
        }
        if (record.getOrgSendYn() != null) {
            SET("ORG_SEND_YN = #{orgSendYn,jdbcType=VARCHAR}");
        }
        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{updateUid,jdbcType=VARCHAR}");
        }
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        WHERE("DEMAND_DATE = #{demandDate,jdbcType=VARCHAR}");
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_VAN_DEMAND_DETAIL
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    protected void applyWhere(TFnVanDemandDetailSpec spec,
            boolean includeSpecPhrase) {
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
                            sb.append(String.format(parmPhrase1,
                                    criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th,
                                    criterion.getCondition(), i, j,
                                    criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2,
                                    criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th,
                                    criterion.getCondition(), i, j,
                                    criterion.getTypeHandler(), i, j,
                                    criterion.getTypeHandler()));
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
                                sb.append(String.format(parmPhrase3_th, i, j,
                                        k, criterion.getTypeHandler()));
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
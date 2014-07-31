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

import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemand;
import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemandSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemandSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnWrVanDemandSpec;
import java.util.List;
import java.util.Map;

public class TFnWrVanDemandSqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_WR_VAN_DEMAND
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String countBySpec(TFnWrVanDemandSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_WR_VAN_DEMAND");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_WR_VAN_DEMAND
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String deleteBySpec(TFnWrVanDemandSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_WR_VAN_DEMAND");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_WR_VAN_DEMAND
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String insertSelective(TFnWrVanDemand record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_WR_VAN_DEMAND");
        if (record.getDemandDate() != null) {
            VALUES("DEMAND_DATE", "#{demandDate,jdbcType=VARCHAR}");
        }
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        if (record.getStartDate() != null) {
            VALUES("START_DATE", "#{startDate,jdbcType=VARCHAR}");
        }
        if (record.getEndDate() != null) {
            VALUES("END_DATE", "#{endDate,jdbcType=VARCHAR}");
        }
        if (record.getUseDays() != null) {
            VALUES("USE_DAYS", "#{useDays,jdbcType=VARCHAR}");
        }
        if (record.getMacCnt() != null) {
            VALUES("MAC_CNT", "#{macCnt,jdbcType=OTHER}");
        }
        if (record.getPreInventAmt() != null) {
            VALUES("PRE_INVENT_AMT", "#{preInventAmt,jdbcType=OTHER}");
        }
        if (record.getInventAmt() != null) {
            VALUES("INVENT_AMT", "#{inventAmt,jdbcType=OTHER}");
        }
        if (record.getPreDemandAmt() != null) {
            VALUES("PRE_DEMAND_AMT", "#{preDemandAmt,jdbcType=OTHER}");
        }
        if (record.getOutAmt() != null) {
            VALUES("OUT_AMT", "#{outAmt,jdbcType=OTHER}");
        }
        if (record.getOutCnt() != null) {
            VALUES("OUT_CNT", "#{outCnt,jdbcType=OTHER}");
        }
        if (record.getDifAmt() != null) {
            VALUES("DIF_AMT", "#{difAmt,jdbcType=OTHER}");
        }
        if (record.getYstAmt() != null) {
            VALUES("YST_AMT", "#{ystAmt,jdbcType=OTHER}");
        }
        if (record.getReturnAmt() != null) {
            VALUES("RETURN_AMT", "#{returnAmt,jdbcType=OTHER}");
        }
        if (record.getOutRate() != null) {
            VALUES("OUT_RATE", "#{outRate,jdbcType=VARCHAR}");
        }
        if (record.getBerate() != null) {
            VALUES("BERATE", "#{berate,jdbcType=VARCHAR}");
        }
        if (record.getOperAmt() != null) {
            VALUES("OPER_AMT", "#{operAmt,jdbcType=OTHER}");
        }
        if (record.getNotendAmt() != null) {
            VALUES("NOTEND_AMT", "#{notendAmt,jdbcType=OTHER}");
        }
        if (record.getOrdAmt() != null) {
            VALUES("ORD_AMT", "#{ordAmt,jdbcType=OTHER}");
        }
        if (record.getWkdAmt() != null) {
            VALUES("WKD_AMT", "#{wkdAmt,jdbcType=OTHER}");
        }
        if (record.getJanAmt() != null) {
            VALUES("JAN_AMT", "#{janAmt,jdbcType=OTHER}");
        }
        if (record.getDemandAmt() != null) {
            VALUES("DEMAND_AMT", "#{demandAmt,jdbcType=OTHER}");
        }
        if (record.getOperInventAmt() != null) {
            VALUES("OPER_INVENT_AMT", "#{operInventAmt,jdbcType=OTHER}");
        }
        if (record.getFeeAmt() != null) {
            VALUES("FEE_AMT", "#{feeAmt,jdbcType=OTHER}");
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
        if (record.getInAmt() != null) {
            VALUES("IN_AMT", "#{inAmt,jdbcType=OTHER}");
        }
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_WR_VAN_DEMAND
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String selectBySpec(TFnWrVanDemandSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("DEMAND_DATE");
        } else {
            SELECT("DEMAND_DATE");
        }
        SELECT("ORG_CD");
        SELECT("START_DATE");
        SELECT("END_DATE");
        SELECT("USE_DAYS");
        SELECT("MAC_CNT");
        SELECT("PRE_INVENT_AMT");
        SELECT("INVENT_AMT");
        SELECT("PRE_DEMAND_AMT");
        SELECT("OUT_AMT");
        SELECT("OUT_CNT");
        SELECT("DIF_AMT");
        SELECT("YST_AMT");
        SELECT("RETURN_AMT");
        SELECT("OUT_RATE");
        SELECT("BERATE");
        SELECT("OPER_AMT");
        SELECT("NOTEND_AMT");
        SELECT("ORD_AMT");
        SELECT("WKD_AMT");
        SELECT("JAN_AMT");
        SELECT("DEMAND_AMT");
        SELECT("OPER_INVENT_AMT");
        SELECT("FEE_AMT");
        SELECT("ORG_SEND_YN");
        SELECT("UPDATE_UID");
        SELECT("UPDATE_DATE");
        SELECT("IN_AMT");
        FROM("OP.T_FN_WR_VAN_DEMAND");
        applyWhere(spec, false);
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_WR_VAN_DEMAND
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnWrVanDemand record = (TFnWrVanDemand) parameter.get("record");
        TFnWrVanDemandSpec spec = (TFnWrVanDemandSpec) parameter
                .get("spec");
        BEGIN();
        UPDATE("OP.T_FN_WR_VAN_DEMAND");
        if (record.getDemandDate() != null) {
            SET("DEMAND_DATE = #{record.demandDate,jdbcType=VARCHAR}");
        }
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        if (record.getStartDate() != null) {
            SET("START_DATE = #{record.startDate,jdbcType=VARCHAR}");
        }
        if (record.getEndDate() != null) {
            SET("END_DATE = #{record.endDate,jdbcType=VARCHAR}");
        }
        if (record.getUseDays() != null) {
            SET("USE_DAYS = #{record.useDays,jdbcType=VARCHAR}");
        }
        if (record.getMacCnt() != null) {
            SET("MAC_CNT = #{record.macCnt,jdbcType=OTHER}");
        }
        if (record.getPreInventAmt() != null) {
            SET("PRE_INVENT_AMT = #{record.preInventAmt,jdbcType=OTHER}");
        }
        if (record.getInventAmt() != null) {
            SET("INVENT_AMT = #{record.inventAmt,jdbcType=OTHER}");
        }
        if (record.getPreDemandAmt() != null) {
            SET("PRE_DEMAND_AMT = #{record.preDemandAmt,jdbcType=OTHER}");
        }
        if (record.getOutAmt() != null) {
            SET("OUT_AMT = #{record.outAmt,jdbcType=OTHER}");
        }
        if (record.getOutCnt() != null) {
            SET("OUT_CNT = #{record.outCnt,jdbcType=OTHER}");
        }
        if (record.getDifAmt() != null) {
            SET("DIF_AMT = #{record.difAmt,jdbcType=OTHER}");
        }
        if (record.getYstAmt() != null) {
            SET("YST_AMT = #{record.ystAmt,jdbcType=OTHER}");
        }
        if (record.getReturnAmt() != null) {
            SET("RETURN_AMT = #{record.returnAmt,jdbcType=OTHER}");
        }
        if (record.getOutRate() != null) {
            SET("OUT_RATE = #{record.outRate,jdbcType=VARCHAR}");
        }
        if (record.getBerate() != null) {
            SET("BERATE = #{record.berate,jdbcType=VARCHAR}");
        }
        if (record.getOperAmt() != null) {
            SET("OPER_AMT = #{record.operAmt,jdbcType=OTHER}");
        }
        if (record.getNotendAmt() != null) {
            SET("NOTEND_AMT = #{record.notendAmt,jdbcType=OTHER}");
        }
        if (record.getOrdAmt() != null) {
            SET("ORD_AMT = #{record.ordAmt,jdbcType=OTHER}");
        }
        if (record.getWkdAmt() != null) {
            SET("WKD_AMT = #{record.wkdAmt,jdbcType=OTHER}");
        }
        if (record.getJanAmt() != null) {
            SET("JAN_AMT = #{record.janAmt,jdbcType=OTHER}");
        }
        if (record.getDemandAmt() != null) {
            SET("DEMAND_AMT = #{record.demandAmt,jdbcType=OTHER}");
        }
        if (record.getOperInventAmt() != null) {
            SET("OPER_INVENT_AMT = #{record.operInventAmt,jdbcType=OTHER}");
        }
        if (record.getFeeAmt() != null) {
            SET("FEE_AMT = #{record.feeAmt,jdbcType=OTHER}");
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
        if (record.getInAmt() != null) {
            SET("IN_AMT = #{record.inAmt,jdbcType=OTHER}");
        }
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_WR_VAN_DEMAND
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_WR_VAN_DEMAND");
        SET("DEMAND_DATE = #{record.demandDate,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("START_DATE = #{record.startDate,jdbcType=VARCHAR}");
        SET("END_DATE = #{record.endDate,jdbcType=VARCHAR}");
        SET("USE_DAYS = #{record.useDays,jdbcType=VARCHAR}");
        SET("MAC_CNT = #{record.macCnt,jdbcType=OTHER}");
        SET("PRE_INVENT_AMT = #{record.preInventAmt,jdbcType=OTHER}");
        SET("INVENT_AMT = #{record.inventAmt,jdbcType=OTHER}");
        SET("PRE_DEMAND_AMT = #{record.preDemandAmt,jdbcType=OTHER}");
        SET("OUT_AMT = #{record.outAmt,jdbcType=OTHER}");
        SET("OUT_CNT = #{record.outCnt,jdbcType=OTHER}");
        SET("DIF_AMT = #{record.difAmt,jdbcType=OTHER}");
        SET("YST_AMT = #{record.ystAmt,jdbcType=OTHER}");
        SET("RETURN_AMT = #{record.returnAmt,jdbcType=OTHER}");
        SET("OUT_RATE = #{record.outRate,jdbcType=VARCHAR}");
        SET("BERATE = #{record.berate,jdbcType=VARCHAR}");
        SET("OPER_AMT = #{record.operAmt,jdbcType=OTHER}");
        SET("NOTEND_AMT = #{record.notendAmt,jdbcType=OTHER}");
        SET("ORD_AMT = #{record.ordAmt,jdbcType=OTHER}");
        SET("WKD_AMT = #{record.wkdAmt,jdbcType=OTHER}");
        SET("JAN_AMT = #{record.janAmt,jdbcType=OTHER}");
        SET("DEMAND_AMT = #{record.demandAmt,jdbcType=OTHER}");
        SET("OPER_INVENT_AMT = #{record.operInventAmt,jdbcType=OTHER}");
        SET("FEE_AMT = #{record.feeAmt,jdbcType=OTHER}");
        SET("ORG_SEND_YN = #{record.orgSendYn,jdbcType=VARCHAR}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("IN_AMT = #{record.inAmt,jdbcType=OTHER}");
        TFnWrVanDemandSpec spec = (TFnWrVanDemandSpec) parameter
                .get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_WR_VAN_DEMAND
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnWrVanDemand record) {
        BEGIN();
        UPDATE("OP.T_FN_WR_VAN_DEMAND");
        if (record.getStartDate() != null) {
            SET("START_DATE = #{startDate,jdbcType=VARCHAR}");
        }
        if (record.getEndDate() != null) {
            SET("END_DATE = #{endDate,jdbcType=VARCHAR}");
        }
        if (record.getUseDays() != null) {
            SET("USE_DAYS = #{useDays,jdbcType=VARCHAR}");
        }
        if (record.getMacCnt() != null) {
            SET("MAC_CNT = #{macCnt,jdbcType=OTHER}");
        }
        if (record.getPreInventAmt() != null) {
            SET("PRE_INVENT_AMT = #{preInventAmt,jdbcType=OTHER}");
        }
        if (record.getInventAmt() != null) {
            SET("INVENT_AMT = #{inventAmt,jdbcType=OTHER}");
        }
        if (record.getPreDemandAmt() != null) {
            SET("PRE_DEMAND_AMT = #{preDemandAmt,jdbcType=OTHER}");
        }
        if (record.getOutAmt() != null) {
            SET("OUT_AMT = #{outAmt,jdbcType=OTHER}");
        }
        if (record.getOutCnt() != null) {
            SET("OUT_CNT = #{outCnt,jdbcType=OTHER}");
        }
        if (record.getDifAmt() != null) {
            SET("DIF_AMT = #{difAmt,jdbcType=OTHER}");
        }
        if (record.getYstAmt() != null) {
            SET("YST_AMT = #{ystAmt,jdbcType=OTHER}");
        }
        if (record.getReturnAmt() != null) {
            SET("RETURN_AMT = #{returnAmt,jdbcType=OTHER}");
        }
        if (record.getOutRate() != null) {
            SET("OUT_RATE = #{outRate,jdbcType=VARCHAR}");
        }
        if (record.getBerate() != null) {
            SET("BERATE = #{berate,jdbcType=VARCHAR}");
        }
        if (record.getOperAmt() != null) {
            SET("OPER_AMT = #{operAmt,jdbcType=OTHER}");
        }
        if (record.getNotendAmt() != null) {
            SET("NOTEND_AMT = #{notendAmt,jdbcType=OTHER}");
        }
        if (record.getOrdAmt() != null) {
            SET("ORD_AMT = #{ordAmt,jdbcType=OTHER}");
        }
        if (record.getWkdAmt() != null) {
            SET("WKD_AMT = #{wkdAmt,jdbcType=OTHER}");
        }
        if (record.getJanAmt() != null) {
            SET("JAN_AMT = #{janAmt,jdbcType=OTHER}");
        }
        if (record.getDemandAmt() != null) {
            SET("DEMAND_AMT = #{demandAmt,jdbcType=OTHER}");
        }
        if (record.getOperInventAmt() != null) {
            SET("OPER_INVENT_AMT = #{operInventAmt,jdbcType=OTHER}");
        }
        if (record.getFeeAmt() != null) {
            SET("FEE_AMT = #{feeAmt,jdbcType=OTHER}");
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
        if (record.getInAmt() != null) {
            SET("IN_AMT = #{inAmt,jdbcType=OTHER}");
        }
        WHERE("DEMAND_DATE = #{demandDate,jdbcType=VARCHAR}");
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_WR_VAN_DEMAND
     * @mbggenerated  Thu Jul 31 14:31:01 KST 2014
     */
    protected void applyWhere(TFnWrVanDemandSpec spec,
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
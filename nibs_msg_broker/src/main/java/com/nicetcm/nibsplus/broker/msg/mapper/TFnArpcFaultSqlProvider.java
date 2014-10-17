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

import com.nicetcm.nibsplus.broker.msg.model.TFnArpcFault;
import com.nicetcm.nibsplus.broker.msg.model.TFnArpcFaultSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnArpcFaultSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnArpcFaultSpec;
import java.util.List;
import java.util.Map;

public class TFnArpcFaultSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public String countBySpec(TFnArpcFaultSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_ARPC_FAULT");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public String deleteBySpec(TFnArpcFaultSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_ARPC_FAULT");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public String insertSelective(TFnArpcFault record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_ARPC_FAULT");

        if (record.getDealYear() != null) {
            VALUES("DEAL_YEAR", "#{dealYear,jdbcType=VARCHAR}");
        }

        if (record.getDealNo() != null) {
            VALUES("DEAL_NO", "#{dealNo,jdbcType=VARCHAR}");
        }

        if (record.getDealDate() != null) {
            VALUES("DEAL_DATE", "#{dealDate,jdbcType=VARCHAR}");
        }

        if (record.getApplyYn() != null) {
            VALUES("APPLY_YN", "#{applyYn,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public String selectBySpec(TFnArpcFaultSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("DEAL_YEAR");
        } else {
            SELECT("DEAL_YEAR");
        }
        SELECT("DEAL_NO");
        SELECT("DEAL_DATE");
        SELECT("APPLY_YN");
        SELECT("UPDATE_DATE");
        FROM("OP.T_FN_ARPC_FAULT");
        applyWhere(spec, false);

        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnArpcFault record = (TFnArpcFault) parameter.get("record");
        TFnArpcFaultSpec spec = (TFnArpcFaultSpec) parameter.get("spec");

        BEGIN();
        UPDATE("OP.T_FN_ARPC_FAULT");

        if (record.getDealYear() != null) {
            SET("DEAL_YEAR = #{record.dealYear,jdbcType=VARCHAR}");
        }

        if (record.getDealNo() != null) {
            SET("DEAL_NO = #{record.dealNo,jdbcType=VARCHAR}");
        }

        if (record.getDealDate() != null) {
            SET("DEAL_DATE = #{record.dealDate,jdbcType=VARCHAR}");
        }

        if (record.getApplyYn() != null) {
            SET("APPLY_YN = #{record.applyYn,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }

        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_ARPC_FAULT");

        SET("DEAL_YEAR = #{record.dealYear,jdbcType=VARCHAR}");
        SET("DEAL_NO = #{record.dealNo,jdbcType=VARCHAR}");
        SET("DEAL_DATE = #{record.dealDate,jdbcType=VARCHAR}");
        SET("APPLY_YN = #{record.applyYn,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");

        TFnArpcFaultSpec spec = (TFnArpcFaultSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnArpcFault record) {
        BEGIN();
        UPDATE("OP.T_FN_ARPC_FAULT");

        if (record.getDealDate() != null) {
            SET("DEAL_DATE = #{dealDate,jdbcType=VARCHAR}");
        }

        if (record.getApplyYn() != null) {
            SET("APPLY_YN = #{applyYn,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }

        WHERE("DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}");
        WHERE("DEAL_NO = #{dealNo,jdbcType=VARCHAR}");

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    protected void applyWhere(TFnArpcFaultSpec spec, boolean includeSpecPhrase) {
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
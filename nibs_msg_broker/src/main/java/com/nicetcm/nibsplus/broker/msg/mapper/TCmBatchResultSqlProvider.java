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

import com.nicetcm.nibsplus.broker.msg.model.TCmBatchResult;
import com.nicetcm.nibsplus.broker.msg.model.TCmBatchResultSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TCmBatchResultSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TCmBatchResultSpec;
import java.util.List;
import java.util.Map;

public class TCmBatchResultSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BATCH_RESULT
     *
     * @mbggenerated Mon Aug 04 11:43:59 KST 2014
     */
    public String countBySpec(TCmBatchResultSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_CM_BATCH_RESULT");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BATCH_RESULT
     *
     * @mbggenerated Mon Aug 04 11:43:59 KST 2014
     */
    public String deleteBySpec(TCmBatchResultSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_CM_BATCH_RESULT");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BATCH_RESULT
     *
     * @mbggenerated Mon Aug 04 11:43:59 KST 2014
     */
    public String insertSelective(TCmBatchResult record) {
        BEGIN();
        INSERT_INTO("OP.T_CM_BATCH_RESULT");

        if (record.getJobDate() != null) {
            VALUES("JOB_DATE", "#{jobDate,jdbcType=VARCHAR}");
        }

        if (record.getPgmId() != null) {
            VALUES("PGM_ID", "#{pgmId,jdbcType=VARCHAR}");
        }

        if (record.getPgmResult() != null) {
            VALUES("PGM_RESULT", "#{pgmResult,jdbcType=VARCHAR}");
        }

        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getInsertUid() != null) {
            VALUES("INSERT_UID", "#{insertUid,jdbcType=VARCHAR}");
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BATCH_RESULT
     *
     * @mbggenerated Mon Aug 04 11:43:59 KST 2014
     */
    public String selectBySpec(TCmBatchResultSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("JOB_DATE");
        } else {
            SELECT("JOB_DATE");
        }
        SELECT("PGM_ID");
        SELECT("PGM_RESULT");
        SELECT("INSERT_DATE");
        SELECT("INSERT_UID");
        FROM("OP.T_CM_BATCH_RESULT");
        applyWhere(spec, false);

        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BATCH_RESULT
     *
     * @mbggenerated Mon Aug 04 11:43:59 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TCmBatchResult record = (TCmBatchResult) parameter.get("record");
        TCmBatchResultSpec spec = (TCmBatchResultSpec) parameter.get("spec");

        BEGIN();
        UPDATE("OP.T_CM_BATCH_RESULT");

        if (record.getJobDate() != null) {
            SET("JOB_DATE = #{record.jobDate,jdbcType=VARCHAR}");
        }

        if (record.getPgmId() != null) {
            SET("PGM_ID = #{record.pgmId,jdbcType=VARCHAR}");
        }

        if (record.getPgmResult() != null) {
            SET("PGM_RESULT = #{record.pgmResult,jdbcType=VARCHAR}");
        }

        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        }

        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BATCH_RESULT
     *
     * @mbggenerated Mon Aug 04 11:43:59 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_CM_BATCH_RESULT");

        SET("JOB_DATE = #{record.jobDate,jdbcType=VARCHAR}");
        SET("PGM_ID = #{record.pgmId,jdbcType=VARCHAR}");
        SET("PGM_RESULT = #{record.pgmResult,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");

        TCmBatchResultSpec spec = (TCmBatchResultSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BATCH_RESULT
     *
     * @mbggenerated Mon Aug 04 11:43:59 KST 2014
     */
    public String updateByPrimaryKeySelective(TCmBatchResult record) {
        BEGIN();
        UPDATE("OP.T_CM_BATCH_RESULT");

        if (record.getPgmResult() != null) {
            SET("PGM_RESULT = #{pgmResult,jdbcType=VARCHAR}");
        }

        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{insertUid,jdbcType=VARCHAR}");
        }

        WHERE("JOB_DATE = #{jobDate,jdbcType=VARCHAR}");
        WHERE("PGM_ID = #{pgmId,jdbcType=VARCHAR}");

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_BATCH_RESULT
     *
     * @mbggenerated Mon Aug 04 11:43:59 KST 2014
     */
    protected void applyWhere(TCmBatchResultSpec spec, boolean includeSpecPhrase) {
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
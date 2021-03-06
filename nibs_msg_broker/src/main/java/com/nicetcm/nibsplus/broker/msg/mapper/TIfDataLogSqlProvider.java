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

import com.nicetcm.nibsplus.broker.msg.model.TIfDataLog;
import com.nicetcm.nibsplus.broker.msg.model.TIfDataLogSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TIfDataLogSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TIfDataLogSpec;
import java.util.List;
import java.util.Map;

public class TIfDataLogSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_IF_DATA_LOG
     *
     * @mbggenerated Thu Jul 31 14:31:01 KST 2014
     */
    public String countBySpec(TIfDataLogSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_IF_DATA_LOG");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_IF_DATA_LOG
     *
     * @mbggenerated Thu Jul 31 14:31:01 KST 2014
     */
    public String deleteBySpec(TIfDataLogSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_IF_DATA_LOG");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_IF_DATA_LOG
     *
     * @mbggenerated Thu Jul 31 14:31:01 KST 2014
     */
    public String insertSelective(TIfDataLog record) {
        BEGIN();
        INSERT_INTO("OP.T_IF_DATA_LOG");
        
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTransType() != null) {
            VALUES("TRANS_TYPE", "#{transType,jdbcType=VARCHAR}");
        }
        
        if (record.getTransDate() != null) {
            VALUES("TRANS_DATE", "#{transDate,jdbcType=VARCHAR}");
        }
        
        if (record.getTransTime() != null) {
            VALUES("TRANS_TIME", "#{transTime,jdbcType=VARCHAR}");
        }
        
        if (record.getTransSeqNo() != null) {
            VALUES("TRANS_SEQ_NO", "#{transSeqNo,jdbcType=DECIMAL}");
        }
        
        if (record.getApClassNo() != null) {
            VALUES("AP_CLASS_NO", "#{apClassNo,jdbcType=VARCHAR}");
        }
        
        if (record.getTransData() != null) {
            VALUES("TRANS_DATA", "#{transData,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_IF_DATA_LOG
     *
     * @mbggenerated Thu Jul 31 14:31:01 KST 2014
     */
    public String selectBySpec(TIfDataLogSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("ORG_CD");
        } else {
            SELECT("ORG_CD");
        }
        SELECT("TRANS_TYPE");
        SELECT("TRANS_DATE");
        SELECT("TRANS_TIME");
        SELECT("TRANS_SEQ_NO");
        SELECT("AP_CLASS_NO");
        SELECT("TRANS_DATA");
        SELECT("INSERT_DATE");
        SELECT("UPDATE_DATE");
        FROM("OP.T_IF_DATA_LOG");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_IF_DATA_LOG
     *
     * @mbggenerated Thu Jul 31 14:31:01 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TIfDataLog record = (TIfDataLog) parameter.get("record");
        TIfDataLogSpec spec = (TIfDataLogSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_IF_DATA_LOG");
        
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTransType() != null) {
            SET("TRANS_TYPE = #{record.transType,jdbcType=VARCHAR}");
        }
        
        if (record.getTransDate() != null) {
            SET("TRANS_DATE = #{record.transDate,jdbcType=VARCHAR}");
        }
        
        if (record.getTransTime() != null) {
            SET("TRANS_TIME = #{record.transTime,jdbcType=VARCHAR}");
        }
        
        if (record.getTransSeqNo() != null) {
            SET("TRANS_SEQ_NO = #{record.transSeqNo,jdbcType=DECIMAL}");
        }
        
        if (record.getApClassNo() != null) {
            SET("AP_CLASS_NO = #{record.apClassNo,jdbcType=VARCHAR}");
        }
        
        if (record.getTransData() != null) {
            SET("TRANS_DATA = #{record.transData,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_IF_DATA_LOG
     *
     * @mbggenerated Thu Jul 31 14:31:01 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_IF_DATA_LOG");
        
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("TRANS_TYPE = #{record.transType,jdbcType=VARCHAR}");
        SET("TRANS_DATE = #{record.transDate,jdbcType=VARCHAR}");
        SET("TRANS_TIME = #{record.transTime,jdbcType=VARCHAR}");
        SET("TRANS_SEQ_NO = #{record.transSeqNo,jdbcType=DECIMAL}");
        SET("AP_CLASS_NO = #{record.apClassNo,jdbcType=VARCHAR}");
        SET("TRANS_DATA = #{record.transData,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        
        TIfDataLogSpec spec = (TIfDataLogSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_IF_DATA_LOG
     *
     * @mbggenerated Thu Jul 31 14:31:01 KST 2014
     */
    public String updateByPrimaryKeySelective(TIfDataLog record) {
        BEGIN();
        UPDATE("OP.T_IF_DATA_LOG");
        
        if (record.getApClassNo() != null) {
            SET("AP_CLASS_NO = #{apClassNo,jdbcType=VARCHAR}");
        }
        
        if (record.getTransData() != null) {
            SET("TRANS_DATA = #{transData,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("TRANS_TYPE = #{transType,jdbcType=VARCHAR}");
        WHERE("TRANS_DATE = #{transDate,jdbcType=VARCHAR}");
        WHERE("TRANS_TIME = #{transTime,jdbcType=VARCHAR}");
        WHERE("TRANS_SEQ_NO = #{transSeqNo,jdbcType=DECIMAL}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_IF_DATA_LOG
     *
     * @mbggenerated Thu Jul 31 14:31:01 KST 2014
     */
    protected void applyWhere(TIfDataLogSpec spec, boolean includeSpecPhrase) {
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
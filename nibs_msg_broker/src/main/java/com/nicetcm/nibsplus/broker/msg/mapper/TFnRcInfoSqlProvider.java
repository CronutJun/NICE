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

import com.nicetcm.nibsplus.broker.msg.model.TFnRcInfo;
import com.nicetcm.nibsplus.broker.msg.model.TFnRcInfoSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnRcInfoSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnRcInfoSpec;
import java.util.List;
import java.util.Map;

public class TFnRcInfoSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_RC_INFO
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String countBySpec(TFnRcInfoSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_RC_INFO");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_RC_INFO
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String deleteBySpec(TFnRcInfoSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_RC_INFO");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_RC_INFO
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String insertSelective(TFnRcInfo record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_RC_INFO");
        
        if (record.getDealDate() != null) {
            VALUES("DEAL_DATE", "#{dealDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDealNo() != null) {
            VALUES("DEAL_NO", "#{dealNo,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDealTime() != null) {
            VALUES("DEAL_TIME", "#{dealTime,jdbcType=VARCHAR}");
        }
        
        if (record.getDealClass() != null) {
            VALUES("DEAL_CLASS", "#{dealClass,jdbcType=VARCHAR}");
        }
        
        if (record.getCompGb() != null) {
            VALUES("COMP_GB", "#{compGb,jdbcType=VARCHAR}");
        }
        
        if (record.getHpNo() != null) {
            VALUES("HP_NO", "#{hpNo,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            VALUES("STATE", "#{state,jdbcType=VARCHAR}");
        }
        
        if (record.getDealType() != null) {
            VALUES("DEAL_TYPE", "#{dealType,jdbcType=VARCHAR}");
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
     * This method corresponds to the database table OP.T_FN_RC_INFO
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String selectBySpec(TFnRcInfoSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("DEAL_DATE");
        } else {
            SELECT("DEAL_DATE");
        }
        SELECT("DEAL_NO");
        SELECT("MAC_NO");
        SELECT("DEAL_TIME");
        SELECT("DEAL_CLASS");
        SELECT("COMP_GB");
        SELECT("HP_NO");
        SELECT("STATE");
        SELECT("DEAL_TYPE");
        SELECT("INSERT_DATE");
        SELECT("UPDATE_DATE");
        FROM("OP.T_FN_RC_INFO");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_RC_INFO
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnRcInfo record = (TFnRcInfo) parameter.get("record");
        TFnRcInfoSpec spec = (TFnRcInfoSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_FN_RC_INFO");
        
        if (record.getDealDate() != null) {
            SET("DEAL_DATE = #{record.dealDate,jdbcType=VARCHAR}");
        }
        
        if (record.getDealNo() != null) {
            SET("DEAL_NO = #{record.dealNo,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDealTime() != null) {
            SET("DEAL_TIME = #{record.dealTime,jdbcType=VARCHAR}");
        }
        
        if (record.getDealClass() != null) {
            SET("DEAL_CLASS = #{record.dealClass,jdbcType=VARCHAR}");
        }
        
        if (record.getCompGb() != null) {
            SET("COMP_GB = #{record.compGb,jdbcType=VARCHAR}");
        }
        
        if (record.getHpNo() != null) {
            SET("HP_NO = #{record.hpNo,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            SET("STATE = #{record.state,jdbcType=VARCHAR}");
        }
        
        if (record.getDealType() != null) {
            SET("DEAL_TYPE = #{record.dealType,jdbcType=VARCHAR}");
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
     * This method corresponds to the database table OP.T_FN_RC_INFO
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_RC_INFO");
        
        SET("DEAL_DATE = #{record.dealDate,jdbcType=VARCHAR}");
        SET("DEAL_NO = #{record.dealNo,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("DEAL_TIME = #{record.dealTime,jdbcType=VARCHAR}");
        SET("DEAL_CLASS = #{record.dealClass,jdbcType=VARCHAR}");
        SET("COMP_GB = #{record.compGb,jdbcType=VARCHAR}");
        SET("HP_NO = #{record.hpNo,jdbcType=VARCHAR}");
        SET("STATE = #{record.state,jdbcType=VARCHAR}");
        SET("DEAL_TYPE = #{record.dealType,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        
        TFnRcInfoSpec spec = (TFnRcInfoSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_RC_INFO
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnRcInfo record) {
        BEGIN();
        UPDATE("OP.T_FN_RC_INFO");
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDealTime() != null) {
            SET("DEAL_TIME = #{dealTime,jdbcType=VARCHAR}");
        }
        
        if (record.getDealClass() != null) {
            SET("DEAL_CLASS = #{dealClass,jdbcType=VARCHAR}");
        }
        
        if (record.getCompGb() != null) {
            SET("COMP_GB = #{compGb,jdbcType=VARCHAR}");
        }
        
        if (record.getHpNo() != null) {
            SET("HP_NO = #{hpNo,jdbcType=VARCHAR}");
        }
        
        if (record.getState() != null) {
            SET("STATE = #{state,jdbcType=VARCHAR}");
        }
        
        if (record.getDealType() != null) {
            SET("DEAL_TYPE = #{dealType,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        WHERE("DEAL_DATE = #{dealDate,jdbcType=VARCHAR}");
        WHERE("DEAL_NO = #{dealNo,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_RC_INFO
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    protected void applyWhere(TFnRcInfoSpec spec, boolean includeSpecPhrase) {
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
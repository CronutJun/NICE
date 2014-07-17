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

import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckList;
import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckListSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckListSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckListSpec;
import java.util.List;
import java.util.Map;

public class TCtInputCheckListSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public String countBySpec(TCtInputCheckListSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_CT_INPUT_CHECK_LIST");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public String deleteBySpec(TCtInputCheckListSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_CT_INPUT_CHECK_LIST");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public String insertSelective(TCtInputCheckList record) {
        BEGIN();
        INSERT_INTO("OP.T_CT_INPUT_CHECK_LIST");
        
        if (record.getFromDate() != null) {
            VALUES("FROM_DATE", "#{fromDate,jdbcType=VARCHAR}");
        }
        
        if (record.getToDate() != null) {
            VALUES("TO_DATE", "#{toDate,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            VALUES("BRANCH_CD", "#{branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnCheckCnt() != null) {
            VALUES("OWN_CHECK_CNT", "#{ownCheckCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOwnCheckAmt() != null) {
            VALUES("OWN_CHECK_AMT", "#{ownCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherCheckCnt() != null) {
            VALUES("OTHER_CHECK_CNT", "#{otherCheckCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherCheckAmt() != null) {
            VALUES("OTHER_CHECK_AMT", "#{otherCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotCnt() != null) {
            VALUES("TOT_CNT", "#{totCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotAmt() != null) {
            VALUES("TOT_AMT", "#{totAmt,jdbcType=DECIMAL}");
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
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public String selectBySpec(TCtInputCheckListSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("FROM_DATE");
        } else {
            SELECT("FROM_DATE");
        }
        SELECT("TO_DATE");
        SELECT("ORG_CD");
        SELECT("BRANCH_CD");
        SELECT("MAC_NO");
        SELECT("OWN_CHECK_CNT");
        SELECT("OWN_CHECK_AMT");
        SELECT("OTHER_CHECK_CNT");
        SELECT("OTHER_CHECK_AMT");
        SELECT("TOT_CNT");
        SELECT("TOT_AMT");
        SELECT("INSERT_DATE");
        SELECT("UPDATE_DATE");
        FROM("OP.T_CT_INPUT_CHECK_LIST");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TCtInputCheckList record = (TCtInputCheckList) parameter.get("record");
        TCtInputCheckListSpec spec = (TCtInputCheckListSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_CT_INPUT_CHECK_LIST");
        
        if (record.getFromDate() != null) {
            SET("FROM_DATE = #{record.fromDate,jdbcType=VARCHAR}");
        }
        
        if (record.getToDate() != null) {
            SET("TO_DATE = #{record.toDate,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOwnCheckCnt() != null) {
            SET("OWN_CHECK_CNT = #{record.ownCheckCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOwnCheckAmt() != null) {
            SET("OWN_CHECK_AMT = #{record.ownCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherCheckCnt() != null) {
            SET("OTHER_CHECK_CNT = #{record.otherCheckCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherCheckAmt() != null) {
            SET("OTHER_CHECK_AMT = #{record.otherCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotCnt() != null) {
            SET("TOT_CNT = #{record.totCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotAmt() != null) {
            SET("TOT_AMT = #{record.totAmt,jdbcType=DECIMAL}");
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
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_CT_INPUT_CHECK_LIST");
        
        SET("FROM_DATE = #{record.fromDate,jdbcType=VARCHAR}");
        SET("TO_DATE = #{record.toDate,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("OWN_CHECK_CNT = #{record.ownCheckCnt,jdbcType=DECIMAL}");
        SET("OWN_CHECK_AMT = #{record.ownCheckAmt,jdbcType=DECIMAL}");
        SET("OTHER_CHECK_CNT = #{record.otherCheckCnt,jdbcType=DECIMAL}");
        SET("OTHER_CHECK_AMT = #{record.otherCheckAmt,jdbcType=DECIMAL}");
        SET("TOT_CNT = #{record.totCnt,jdbcType=DECIMAL}");
        SET("TOT_AMT = #{record.totAmt,jdbcType=DECIMAL}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        
        TCtInputCheckListSpec spec = (TCtInputCheckListSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    public String updateByPrimaryKeySelective(TCtInputCheckList record) {
        BEGIN();
        UPDATE("OP.T_CT_INPUT_CHECK_LIST");
        
        if (record.getOwnCheckCnt() != null) {
            SET("OWN_CHECK_CNT = #{ownCheckCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOwnCheckAmt() != null) {
            SET("OWN_CHECK_AMT = #{ownCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherCheckCnt() != null) {
            SET("OTHER_CHECK_CNT = #{otherCheckCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getOtherCheckAmt() != null) {
            SET("OTHER_CHECK_AMT = #{otherCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotCnt() != null) {
            SET("TOT_CNT = #{totCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTotAmt() != null) {
            SET("TOT_AMT = #{totAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        WHERE("FROM_DATE = #{fromDate,jdbcType=VARCHAR}");
        WHERE("TO_DATE = #{toDate,jdbcType=VARCHAR}");
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    protected void applyWhere(TCtInputCheckListSpec spec, boolean includeSpecPhrase) {
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
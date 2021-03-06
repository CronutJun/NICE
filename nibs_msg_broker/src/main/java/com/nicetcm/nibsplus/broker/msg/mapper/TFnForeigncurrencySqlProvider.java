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

import com.nicetcm.nibsplus.broker.msg.model.TFnForeigncurrency;
import com.nicetcm.nibsplus.broker.msg.model.TFnForeigncurrencySpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnForeigncurrencySpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnForeigncurrencySpec;
import java.util.List;
import java.util.Map;

public class TFnForeigncurrencySqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String countBySpec(TFnForeigncurrencySpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_FOREIGNCURRENCY");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String deleteBySpec(TFnForeigncurrencySpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_FOREIGNCURRENCY");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String insertSelective(TFnForeigncurrency record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_FOREIGNCURRENCY");
        
        if (record.getDepoDate() != null) {
            VALUES("DEPO_DATE", "#{depoDate,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            VALUES("BRANCH_CD", "#{branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getDeptCd() != null) {
            VALUES("DEPT_CD", "#{deptCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeCd() != null) {
            VALUES("OFFICE_CD", "#{officeCd,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoSdate() != null) {
            VALUES("DEPO_SDATE", "#{depoSdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoStime() != null) {
            VALUES("DEPO_STIME", "#{depoStime,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoEdate() != null) {
            VALUES("DEPO_EDATE", "#{depoEdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoEtime() != null) {
            VALUES("DEPO_ETIME", "#{depoEtime,jdbcType=VARCHAR}");
        }
        
        if (record.getDollarAmt() != null) {
            VALUES("DOLLAR_AMT", "#{dollarAmt,jdbcType=OTHER}");
        }
        
        if (record.getEuroAmt() != null) {
            VALUES("EURO_AMT", "#{euroAmt,jdbcType=OTHER}");
        }
        
        if (record.getYenAmt() != null) {
            VALUES("YEN_AMT", "#{yenAmt,jdbcType=OTHER}");
        }
        
        if (record.getYuanAmt() != null) {
            VALUES("YUAN_AMT", "#{yuanAmt,jdbcType=OTHER}");
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
        
        if (record.getOrgSendYn() != null) {
            VALUES("ORG_SEND_YN", "#{orgSendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseSdate() != null) {
            VALUES("DEPO_CLOSE_SDATE", "#{depoCloseSdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseEdate() != null) {
            VALUES("DEPO_CLOSE_EDATE", "#{depoCloseEdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseStime() != null) {
            VALUES("DEPO_CLOSE_STIME", "#{depoCloseStime,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseEtime() != null) {
            VALUES("DEPO_CLOSE_ETIME", "#{depoCloseEtime,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String selectBySpec(TFnForeigncurrencySpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("DEPO_DATE");
        } else {
            SELECT("DEPO_DATE");
        }
        SELECT("ORG_CD");
        SELECT("BRANCH_CD");
        SELECT("DEPT_CD");
        SELECT("OFFICE_CD");
        SELECT("DEPO_SDATE");
        SELECT("DEPO_STIME");
        SELECT("DEPO_EDATE");
        SELECT("DEPO_ETIME");
        SELECT("DOLLAR_AMT");
        SELECT("EURO_AMT");
        SELECT("YEN_AMT");
        SELECT("YUAN_AMT");
        SELECT("INSERT_UID");
        SELECT("INSERT_DATE");
        SELECT("UPDATE_UID");
        SELECT("UPDATE_DATE");
        SELECT("ORG_SEND_YN");
        SELECT("DEPO_CLOSE_SDATE");
        SELECT("DEPO_CLOSE_EDATE");
        SELECT("DEPO_CLOSE_STIME");
        SELECT("DEPO_CLOSE_ETIME");
        FROM("OP.T_FN_FOREIGNCURRENCY");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnForeigncurrency record = (TFnForeigncurrency) parameter.get("record");
        TFnForeigncurrencySpec spec = (TFnForeigncurrencySpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_FN_FOREIGNCURRENCY");
        
        if (record.getDepoDate() != null) {
            SET("DEPO_DATE = #{record.depoDate,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getDeptCd() != null) {
            SET("DEPT_CD = #{record.deptCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeCd() != null) {
            SET("OFFICE_CD = #{record.officeCd,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoSdate() != null) {
            SET("DEPO_SDATE = #{record.depoSdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoStime() != null) {
            SET("DEPO_STIME = #{record.depoStime,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoEdate() != null) {
            SET("DEPO_EDATE = #{record.depoEdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoEtime() != null) {
            SET("DEPO_ETIME = #{record.depoEtime,jdbcType=VARCHAR}");
        }
        
        if (record.getDollarAmt() != null) {
            SET("DOLLAR_AMT = #{record.dollarAmt,jdbcType=OTHER}");
        }
        
        if (record.getEuroAmt() != null) {
            SET("EURO_AMT = #{record.euroAmt,jdbcType=OTHER}");
        }
        
        if (record.getYenAmt() != null) {
            SET("YEN_AMT = #{record.yenAmt,jdbcType=OTHER}");
        }
        
        if (record.getYuanAmt() != null) {
            SET("YUAN_AMT = #{record.yuanAmt,jdbcType=OTHER}");
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
        
        if (record.getOrgSendYn() != null) {
            SET("ORG_SEND_YN = #{record.orgSendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseSdate() != null) {
            SET("DEPO_CLOSE_SDATE = #{record.depoCloseSdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseEdate() != null) {
            SET("DEPO_CLOSE_EDATE = #{record.depoCloseEdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseStime() != null) {
            SET("DEPO_CLOSE_STIME = #{record.depoCloseStime,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseEtime() != null) {
            SET("DEPO_CLOSE_ETIME = #{record.depoCloseEtime,jdbcType=VARCHAR}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_FOREIGNCURRENCY");
        
        SET("DEPO_DATE = #{record.depoDate,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("DEPT_CD = #{record.deptCd,jdbcType=VARCHAR}");
        SET("OFFICE_CD = #{record.officeCd,jdbcType=VARCHAR}");
        SET("DEPO_SDATE = #{record.depoSdate,jdbcType=VARCHAR}");
        SET("DEPO_STIME = #{record.depoStime,jdbcType=VARCHAR}");
        SET("DEPO_EDATE = #{record.depoEdate,jdbcType=VARCHAR}");
        SET("DEPO_ETIME = #{record.depoEtime,jdbcType=VARCHAR}");
        SET("DOLLAR_AMT = #{record.dollarAmt,jdbcType=OTHER}");
        SET("EURO_AMT = #{record.euroAmt,jdbcType=OTHER}");
        SET("YEN_AMT = #{record.yenAmt,jdbcType=OTHER}");
        SET("YUAN_AMT = #{record.yuanAmt,jdbcType=OTHER}");
        SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("ORG_SEND_YN = #{record.orgSendYn,jdbcType=VARCHAR}");
        SET("DEPO_CLOSE_SDATE = #{record.depoCloseSdate,jdbcType=VARCHAR}");
        SET("DEPO_CLOSE_EDATE = #{record.depoCloseEdate,jdbcType=VARCHAR}");
        SET("DEPO_CLOSE_STIME = #{record.depoCloseStime,jdbcType=VARCHAR}");
        SET("DEPO_CLOSE_ETIME = #{record.depoCloseEtime,jdbcType=VARCHAR}");
        
        TFnForeigncurrencySpec spec = (TFnForeigncurrencySpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnForeigncurrency record) {
        BEGIN();
        UPDATE("OP.T_FN_FOREIGNCURRENCY");
        
        if (record.getDeptCd() != null) {
            SET("DEPT_CD = #{deptCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeCd() != null) {
            SET("OFFICE_CD = #{officeCd,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoSdate() != null) {
            SET("DEPO_SDATE = #{depoSdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoStime() != null) {
            SET("DEPO_STIME = #{depoStime,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoEdate() != null) {
            SET("DEPO_EDATE = #{depoEdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoEtime() != null) {
            SET("DEPO_ETIME = #{depoEtime,jdbcType=VARCHAR}");
        }
        
        if (record.getDollarAmt() != null) {
            SET("DOLLAR_AMT = #{dollarAmt,jdbcType=OTHER}");
        }
        
        if (record.getEuroAmt() != null) {
            SET("EURO_AMT = #{euroAmt,jdbcType=OTHER}");
        }
        
        if (record.getYenAmt() != null) {
            SET("YEN_AMT = #{yenAmt,jdbcType=OTHER}");
        }
        
        if (record.getYuanAmt() != null) {
            SET("YUAN_AMT = #{yuanAmt,jdbcType=OTHER}");
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
        
        if (record.getOrgSendYn() != null) {
            SET("ORG_SEND_YN = #{orgSendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseSdate() != null) {
            SET("DEPO_CLOSE_SDATE = #{depoCloseSdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseEdate() != null) {
            SET("DEPO_CLOSE_EDATE = #{depoCloseEdate,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseStime() != null) {
            SET("DEPO_CLOSE_STIME = #{depoCloseStime,jdbcType=VARCHAR}");
        }
        
        if (record.getDepoCloseEtime() != null) {
            SET("DEPO_CLOSE_ETIME = #{depoCloseEtime,jdbcType=VARCHAR}");
        }
        
        WHERE("DEPO_DATE = #{depoDate,jdbcType=VARCHAR}");
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    protected void applyWhere(TFnForeigncurrencySpec spec, boolean includeSpecPhrase) {
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
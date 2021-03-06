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

import com.nicetcm.nibsplus.broker.msg.model.TFnBrandSvcFee;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandSvcFeeSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandSvcFeeSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnBrandSvcFeeSpec;
import java.util.List;
import java.util.Map;

public class TFnBrandSvcFeeSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BRAND_SVC_FEE
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    public String countBySpec(TFnBrandSvcFeeSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_BRAND_SVC_FEE");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BRAND_SVC_FEE
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    public String deleteBySpec(TFnBrandSvcFeeSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_BRAND_SVC_FEE");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BRAND_SVC_FEE
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    public String insertSelective(TFnBrandSvcFee record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_BRAND_SVC_FEE");
        
        if (record.getYearMon() != null) {
            VALUES("YEAR_MON", "#{yearMon,jdbcType=VARCHAR}");
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
        
        if (record.getBaseFee() != null) {
            VALUES("BASE_FEE", "#{baseFee,jdbcType=DECIMAL}");
        }
        
        if (record.getPenaltyAmt() != null) {
            VALUES("PENALTY_AMT", "#{penaltyAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getSvcFee() != null) {
            VALUES("SVC_FEE", "#{svcFee,jdbcType=DECIMAL}");
        }
        
        if (record.getDocNo() != null) {
            VALUES("DOC_NO", "#{docNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSendUid() != null) {
            VALUES("SEND_UID", "#{sendUid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgSendYn() != null) {
            VALUES("ORG_SEND_YN", "#{orgSendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            VALUES("UPDATE_UID", "#{updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getBrandOrgCd() != null) {
            VALUES("BRAND_ORG_CD", "#{brandOrgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSendDate() != null) {
            VALUES("SEND_DATE", "#{sendDate,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BRAND_SVC_FEE
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    public String selectBySpec(TFnBrandSvcFeeSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("YEAR_MON");
        } else {
            SELECT("YEAR_MON");
        }
        SELECT("ORG_CD");
        SELECT("BRANCH_CD");
        SELECT("MAC_NO");
        SELECT("BASE_FEE");
        SELECT("PENALTY_AMT");
        SELECT("SVC_FEE");
        SELECT("DOC_NO");
        SELECT("SEND_UID");
        SELECT("ORG_SEND_YN");
        SELECT("UPDATE_DATE");
        SELECT("UPDATE_UID");
        SELECT("BRAND_ORG_CD");
        SELECT("INSERT_DATE");
        SELECT("SEND_DATE");
        FROM("OP.T_FN_BRAND_SVC_FEE");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BRAND_SVC_FEE
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnBrandSvcFee record = (TFnBrandSvcFee) parameter.get("record");
        TFnBrandSvcFeeSpec spec = (TFnBrandSvcFeeSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_FN_BRAND_SVC_FEE");
        
        if (record.getYearMon() != null) {
            SET("YEAR_MON = #{record.yearMon,jdbcType=VARCHAR}");
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
        
        if (record.getBaseFee() != null) {
            SET("BASE_FEE = #{record.baseFee,jdbcType=DECIMAL}");
        }
        
        if (record.getPenaltyAmt() != null) {
            SET("PENALTY_AMT = #{record.penaltyAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getSvcFee() != null) {
            SET("SVC_FEE = #{record.svcFee,jdbcType=DECIMAL}");
        }
        
        if (record.getDocNo() != null) {
            SET("DOC_NO = #{record.docNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSendUid() != null) {
            SET("SEND_UID = #{record.sendUid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgSendYn() != null) {
            SET("ORG_SEND_YN = #{record.orgSendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getBrandOrgCd() != null) {
            SET("BRAND_ORG_CD = #{record.brandOrgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSendDate() != null) {
            SET("SEND_DATE = #{record.sendDate,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BRAND_SVC_FEE
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_BRAND_SVC_FEE");
        
        SET("YEAR_MON = #{record.yearMon,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("BASE_FEE = #{record.baseFee,jdbcType=DECIMAL}");
        SET("PENALTY_AMT = #{record.penaltyAmt,jdbcType=DECIMAL}");
        SET("SVC_FEE = #{record.svcFee,jdbcType=DECIMAL}");
        SET("DOC_NO = #{record.docNo,jdbcType=VARCHAR}");
        SET("SEND_UID = #{record.sendUid,jdbcType=VARCHAR}");
        SET("ORG_SEND_YN = #{record.orgSendYn,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("BRAND_ORG_CD = #{record.brandOrgCd,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("SEND_DATE = #{record.sendDate,jdbcType=TIMESTAMP}");
        
        TFnBrandSvcFeeSpec spec = (TFnBrandSvcFeeSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BRAND_SVC_FEE
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnBrandSvcFee record) {
        BEGIN();
        UPDATE("OP.T_FN_BRAND_SVC_FEE");
        
        if (record.getBaseFee() != null) {
            SET("BASE_FEE = #{baseFee,jdbcType=DECIMAL}");
        }
        
        if (record.getPenaltyAmt() != null) {
            SET("PENALTY_AMT = #{penaltyAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getSvcFee() != null) {
            SET("SVC_FEE = #{svcFee,jdbcType=DECIMAL}");
        }
        
        if (record.getDocNo() != null) {
            SET("DOC_NO = #{docNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSendUid() != null) {
            SET("SEND_UID = #{sendUid,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgSendYn() != null) {
            SET("ORG_SEND_YN = #{orgSendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getBrandOrgCd() != null) {
            SET("BRAND_ORG_CD = #{brandOrgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getSendDate() != null) {
            SET("SEND_DATE = #{sendDate,jdbcType=TIMESTAMP}");
        }
        
        WHERE("YEAR_MON = #{yearMon,jdbcType=VARCHAR}");
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BRAND_SVC_FEE
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    protected void applyWhere(TFnBrandSvcFeeSpec spec, boolean includeSpecPhrase) {
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
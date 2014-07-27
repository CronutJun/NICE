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

import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsAddCashReport;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsAddCashReportSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsAddCashReportSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnAtmsAddCashReportSpec;
import java.util.List;
import java.util.Map;

public class TFnAtmsAddCashReportSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ATMS_ADD_CASH_REPORT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String countBySpec(TFnAtmsAddCashReportSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_ATMS_ADD_CASH_REPORT");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ATMS_ADD_CASH_REPORT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String deleteBySpec(TFnAtmsAddCashReportSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_ATMS_ADD_CASH_REPORT");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ATMS_ADD_CASH_REPORT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String insertSelective(TFnAtmsAddCashReport record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_ATMS_ADD_CASH_REPORT");
        
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
        
        if (record.getAddDate() != null) {
            VALUES("ADD_DATE", "#{addDate,jdbcType=VARCHAR}");
        }
        
        if (record.getSerialNo() != null) {
            VALUES("SERIAL_NO", "#{serialNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDemandDate() != null) {
            VALUES("DEMAND_DATE", "#{demandDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAddCashAmt() != null) {
            VALUES("ADD_CASH_AMT", "#{addCashAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getOfficeNm() != null) {
            VALUES("OFFICE_NM", "#{officeNm,jdbcType=VARCHAR}");
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
        
        if (record.getAddTime() != null) {
            VALUES("ADD_TIME", "#{addTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseYn() != null) {
            VALUES("CLOSE_YN", "#{closeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSendDate() != null) {
            VALUES("SEND_DATE", "#{sendDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAddCheckAmt() != null) {
            VALUES("ADD_CHECK_AMT", "#{addCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck10Cnt() != null) {
            VALUES("CHECK_10_CNT", "#{check10Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck30Cnt() != null) {
            VALUES("CHECK_30_CNT", "#{check30Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck50Cnt() != null) {
            VALUES("CHECK_50_CNT", "#{check50Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck100Cnt() != null) {
            VALUES("CHECK_100_CNT", "#{check100Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getSafeType() != null) {
            VALUES("SAFE_TYPE", "#{safeType,jdbcType=VARCHAR}");
        }
        
        if (record.getAdd50000Amt() != null) {
            VALUES("ADD_50000_AMT", "#{add50000Amt,jdbcType=DECIMAL}");
        }
        
        if (record.getAdd10000Amt() != null) {
            VALUES("ADD_10000_AMT", "#{add10000Amt,jdbcType=DECIMAL}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ATMS_ADD_CASH_REPORT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String selectBySpec(TFnAtmsAddCashReportSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("ORG_CD");
        } else {
            SELECT("ORG_CD");
        }
        SELECT("BRANCH_CD");
        SELECT("SITE_CD");
        SELECT("MAC_NO");
        SELECT("ADD_DATE");
        SELECT("SERIAL_NO");
        SELECT("DEMAND_DATE");
        SELECT("ADD_CASH_AMT");
        SELECT("OFFICE_NM");
        SELECT("ORG_SEND_YN");
        SELECT("UPDATE_DATE");
        SELECT("UPDATE_UID");
        SELECT("ADD_TIME");
        SELECT("CLOSE_YN");
        SELECT("SEND_DATE");
        SELECT("ADD_CHECK_AMT");
        SELECT("CHECK_10_CNT");
        SELECT("CHECK_30_CNT");
        SELECT("CHECK_50_CNT");
        SELECT("CHECK_100_CNT");
        SELECT("SAFE_TYPE");
        SELECT("ADD_50000_AMT");
        SELECT("ADD_10000_AMT");
        FROM("OP.T_FN_ATMS_ADD_CASH_REPORT");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ATMS_ADD_CASH_REPORT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnAtmsAddCashReport record = (TFnAtmsAddCashReport) parameter.get("record");
        TFnAtmsAddCashReportSpec spec = (TFnAtmsAddCashReportSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_FN_ATMS_ADD_CASH_REPORT");
        
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
        
        if (record.getAddDate() != null) {
            SET("ADD_DATE = #{record.addDate,jdbcType=VARCHAR}");
        }
        
        if (record.getSerialNo() != null) {
            SET("SERIAL_NO = #{record.serialNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDemandDate() != null) {
            SET("DEMAND_DATE = #{record.demandDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAddCashAmt() != null) {
            SET("ADD_CASH_AMT = #{record.addCashAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getOfficeNm() != null) {
            SET("OFFICE_NM = #{record.officeNm,jdbcType=VARCHAR}");
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
        
        if (record.getAddTime() != null) {
            SET("ADD_TIME = #{record.addTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseYn() != null) {
            SET("CLOSE_YN = #{record.closeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSendDate() != null) {
            SET("SEND_DATE = #{record.sendDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAddCheckAmt() != null) {
            SET("ADD_CHECK_AMT = #{record.addCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck10Cnt() != null) {
            SET("CHECK_10_CNT = #{record.check10Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck30Cnt() != null) {
            SET("CHECK_30_CNT = #{record.check30Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck50Cnt() != null) {
            SET("CHECK_50_CNT = #{record.check50Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck100Cnt() != null) {
            SET("CHECK_100_CNT = #{record.check100Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getSafeType() != null) {
            SET("SAFE_TYPE = #{record.safeType,jdbcType=VARCHAR}");
        }
        
        if (record.getAdd50000Amt() != null) {
            SET("ADD_50000_AMT = #{record.add50000Amt,jdbcType=DECIMAL}");
        }
        
        if (record.getAdd10000Amt() != null) {
            SET("ADD_10000_AMT = #{record.add10000Amt,jdbcType=DECIMAL}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ATMS_ADD_CASH_REPORT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_ATMS_ADD_CASH_REPORT");
        
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("SITE_CD = #{record.siteCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("ADD_DATE = #{record.addDate,jdbcType=VARCHAR}");
        SET("SERIAL_NO = #{record.serialNo,jdbcType=VARCHAR}");
        SET("DEMAND_DATE = #{record.demandDate,jdbcType=VARCHAR}");
        SET("ADD_CASH_AMT = #{record.addCashAmt,jdbcType=DECIMAL}");
        SET("OFFICE_NM = #{record.officeNm,jdbcType=VARCHAR}");
        SET("ORG_SEND_YN = #{record.orgSendYn,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("ADD_TIME = #{record.addTime,jdbcType=VARCHAR}");
        SET("CLOSE_YN = #{record.closeYn,jdbcType=VARCHAR}");
        SET("SEND_DATE = #{record.sendDate,jdbcType=VARCHAR}");
        SET("ADD_CHECK_AMT = #{record.addCheckAmt,jdbcType=DECIMAL}");
        SET("CHECK_10_CNT = #{record.check10Cnt,jdbcType=DECIMAL}");
        SET("CHECK_30_CNT = #{record.check30Cnt,jdbcType=DECIMAL}");
        SET("CHECK_50_CNT = #{record.check50Cnt,jdbcType=DECIMAL}");
        SET("CHECK_100_CNT = #{record.check100Cnt,jdbcType=DECIMAL}");
        SET("SAFE_TYPE = #{record.safeType,jdbcType=VARCHAR}");
        SET("ADD_50000_AMT = #{record.add50000Amt,jdbcType=DECIMAL}");
        SET("ADD_10000_AMT = #{record.add10000Amt,jdbcType=DECIMAL}");
        
        TFnAtmsAddCashReportSpec spec = (TFnAtmsAddCashReportSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ATMS_ADD_CASH_REPORT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnAtmsAddCashReport record) {
        BEGIN();
        UPDATE("OP.T_FN_ATMS_ADD_CASH_REPORT");
        
        if (record.getDemandDate() != null) {
            SET("DEMAND_DATE = #{demandDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAddCashAmt() != null) {
            SET("ADD_CASH_AMT = #{addCashAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getOfficeNm() != null) {
            SET("OFFICE_NM = #{officeNm,jdbcType=VARCHAR}");
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
        
        if (record.getAddTime() != null) {
            SET("ADD_TIME = #{addTime,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseYn() != null) {
            SET("CLOSE_YN = #{closeYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSendDate() != null) {
            SET("SEND_DATE = #{sendDate,jdbcType=VARCHAR}");
        }
        
        if (record.getAddCheckAmt() != null) {
            SET("ADD_CHECK_AMT = #{addCheckAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck10Cnt() != null) {
            SET("CHECK_10_CNT = #{check10Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck30Cnt() != null) {
            SET("CHECK_30_CNT = #{check30Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck50Cnt() != null) {
            SET("CHECK_50_CNT = #{check50Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getCheck100Cnt() != null) {
            SET("CHECK_100_CNT = #{check100Cnt,jdbcType=DECIMAL}");
        }
        
        if (record.getSafeType() != null) {
            SET("SAFE_TYPE = #{safeType,jdbcType=VARCHAR}");
        }
        
        if (record.getAdd50000Amt() != null) {
            SET("ADD_50000_AMT = #{add50000Amt,jdbcType=DECIMAL}");
        }
        
        if (record.getAdd10000Amt() != null) {
            SET("ADD_10000_AMT = #{add10000Amt,jdbcType=DECIMAL}");
        }
        
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("SITE_CD = #{siteCd,jdbcType=VARCHAR}");
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        WHERE("ADD_DATE = #{addDate,jdbcType=VARCHAR}");
        WHERE("SERIAL_NO = #{serialNo,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ATMS_ADD_CASH_REPORT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    protected void applyWhere(TFnAtmsAddCashReportSpec spec, boolean includeSpecPhrase) {
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
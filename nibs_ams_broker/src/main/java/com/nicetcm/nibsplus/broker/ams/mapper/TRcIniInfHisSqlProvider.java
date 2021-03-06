package com.nicetcm.nibsplus.broker.ams.mapper;

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

import com.nicetcm.nibsplus.broker.ams.model.TRcIniInfHis;
import com.nicetcm.nibsplus.broker.ams.model.TRcIniInfHisSpec.Criteria;
import com.nicetcm.nibsplus.broker.ams.model.TRcIniInfHisSpec.Criterion;
import com.nicetcm.nibsplus.broker.ams.model.TRcIniInfHisSpec;
import java.util.List;
import java.util.Map;

public class TRcIniInfHisSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public String countBySpec(TRcIniInfHisSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("AMS.T_RC_INI_INF_HIS");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public String deleteBySpec(TRcIniInfHisSpec spec) {
        BEGIN();
        DELETE_FROM("AMS.T_RC_INI_INF_HIS");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public String insertSelective(TRcIniInfHis record) {
        BEGIN();
        INSERT_INTO("AMS.T_RC_INI_INF_HIS");

        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }

        if (record.getBranchCd() != null) {
            VALUES("BRANCH_CD", "#{branchCd,jdbcType=VARCHAR}");
        }

        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }

        if (record.getIniFileNm() != null) {
            VALUES("INI_FILE_NM", "#{iniFileNm,jdbcType=VARCHAR}");
        }

        if (record.getIniSectNm() != null) {
            VALUES("INI_SECT_NM", "#{iniSectNm,jdbcType=VARCHAR}");
        }

        if (record.getIniKeyNm() != null) {
            VALUES("INI_KEY_NM", "#{iniKeyNm,jdbcType=VARCHAR}");
        }

        if (record.getTrxDate() != null) {
            VALUES("TRX_DATE", "#{trxDate,jdbcType=VARCHAR}");
        }

        if (record.getTrxNo() != null) {
            VALUES("TRX_NO", "#{trxNo,jdbcType=VARCHAR}");
        }

        if (record.getInsertUid() != null) {
            VALUES("INSERT_UID", "#{insertUid,jdbcType=VARCHAR}");
        }

        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getIniVal() != null) {
            VALUES("INI_VAL", "#{iniVal,jdbcType=VARCHAR}");
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public String selectBySpec(TRcIniInfHisSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("ORG_CD");
        } else {
            SELECT("ORG_CD");
        }
        SELECT("BRANCH_CD");
        SELECT("MAC_NO");
        SELECT("INI_FILE_NM");
        SELECT("INI_SECT_NM");
        SELECT("INI_KEY_NM");
        SELECT("TRX_DATE");
        SELECT("TRX_NO");
        SELECT("INSERT_UID");
        SELECT("INSERT_DATE");
        SELECT("INI_VAL");
        FROM("AMS.T_RC_INI_INF_HIS");
        applyWhere(spec, false);

        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TRcIniInfHis record = (TRcIniInfHis) parameter.get("record");
        TRcIniInfHisSpec spec = (TRcIniInfHisSpec) parameter.get("spec");

        BEGIN();
        UPDATE("AMS.T_RC_INI_INF_HIS");

        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }

        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        }

        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }

        if (record.getIniFileNm() != null) {
            SET("INI_FILE_NM = #{record.iniFileNm,jdbcType=VARCHAR}");
        }

        if (record.getIniSectNm() != null) {
            SET("INI_SECT_NM = #{record.iniSectNm,jdbcType=VARCHAR}");
        }

        if (record.getIniKeyNm() != null) {
            SET("INI_KEY_NM = #{record.iniKeyNm,jdbcType=VARCHAR}");
        }

        if (record.getTrxDate() != null) {
            SET("TRX_DATE = #{record.trxDate,jdbcType=VARCHAR}");
        }

        if (record.getTrxNo() != null) {
            SET("TRX_NO = #{record.trxNo,jdbcType=VARCHAR}");
        }

        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        }

        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getIniVal() != null) {
            SET("INI_VAL = #{record.iniVal,jdbcType=VARCHAR}");
        }

        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("AMS.T_RC_INI_INF_HIS");

        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("INI_FILE_NM = #{record.iniFileNm,jdbcType=VARCHAR}");
        SET("INI_SECT_NM = #{record.iniSectNm,jdbcType=VARCHAR}");
        SET("INI_KEY_NM = #{record.iniKeyNm,jdbcType=VARCHAR}");
        SET("TRX_DATE = #{record.trxDate,jdbcType=VARCHAR}");
        SET("TRX_NO = #{record.trxNo,jdbcType=VARCHAR}");
        SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("INI_VAL = #{record.iniVal,jdbcType=VARCHAR}");

        TRcIniInfHisSpec spec = (TRcIniInfHisSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    public String updateByPrimaryKeySelective(TRcIniInfHis record) {
        BEGIN();
        UPDATE("AMS.T_RC_INI_INF_HIS");

        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{insertUid,jdbcType=VARCHAR}");
        }

        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getIniVal() != null) {
            SET("INI_VAL = #{iniVal,jdbcType=VARCHAR}");
        }

        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        WHERE("INI_FILE_NM = #{iniFileNm,jdbcType=VARCHAR}");
        WHERE("INI_SECT_NM = #{iniSectNm,jdbcType=VARCHAR}");
        WHERE("INI_KEY_NM = #{iniKeyNm,jdbcType=VARCHAR}");
        WHERE("TRX_DATE = #{trxDate,jdbcType=VARCHAR}");
        WHERE("TRX_NO = #{trxNo,jdbcType=VARCHAR}");

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    protected void applyWhere(TRcIniInfHisSpec spec, boolean includeSpecPhrase) {
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
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

import com.nicetcm.nibsplus.broker.ams.model.TJmFile;
import com.nicetcm.nibsplus.broker.ams.model.TJmFileSpec.Criteria;
import com.nicetcm.nibsplus.broker.ams.model.TJmFileSpec.Criterion;
import com.nicetcm.nibsplus.broker.ams.model.TJmFileSpec;
import java.util.List;
import java.util.Map;

public class TJmFileSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public String countBySpec(TJmFileSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("IN.T_JM_FILE");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public String deleteBySpec(TJmFileSpec spec) {
        BEGIN();
        DELETE_FROM("IN.T_JM_FILE");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public String insertSelective(TJmFile record) {
        BEGIN();
        INSERT_INTO("IN.T_JM_FILE");

        if (record.getMacTrxDate() != null) {
            VALUES("MAC_TRX_DATE", "#{macTrxDate,jdbcType=VARCHAR}");
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

        if (record.getFileName() != null) {
            VALUES("FILE_NAME", "#{fileName,jdbcType=VARCHAR}");
        }

        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getInsertUid() != null) {
            VALUES("INSERT_UID", "#{insertUid,jdbcType=VARCHAR}");
        }

        if (record.getFilePath() != null) {
            VALUES("FILE_PATH", "#{filePath,jdbcType=VARCHAR}");
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public String selectBySpec(TJmFileSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("MAC_TRX_DATE");
        } else {
            SELECT("MAC_TRX_DATE");
        }
        SELECT("ORG_CD");
        SELECT("BRANCH_CD");
        SELECT("MAC_NO");
        SELECT("FILE_NAME");
        SELECT("INSERT_DATE");
        SELECT("INSERT_UID");
        SELECT("FILE_PATH");
        FROM("IN.T_JM_FILE");
        applyWhere(spec, false);

        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TJmFile record = (TJmFile) parameter.get("record");
        TJmFileSpec spec = (TJmFileSpec) parameter.get("spec");

        BEGIN();
        UPDATE("IN.T_JM_FILE");

        if (record.getMacTrxDate() != null) {
            SET("MAC_TRX_DATE = #{record.macTrxDate,jdbcType=VARCHAR}");
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

        if (record.getFileName() != null) {
            SET("FILE_NAME = #{record.fileName,jdbcType=VARCHAR}");
        }

        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        }

        if (record.getFilePath() != null) {
            SET("FILE_PATH = #{record.filePath,jdbcType=VARCHAR}");
        }

        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("IN.T_JM_FILE");

        SET("MAC_TRX_DATE = #{record.macTrxDate,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("FILE_NAME = #{record.fileName,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        SET("FILE_PATH = #{record.filePath,jdbcType=VARCHAR}");

        TJmFileSpec spec = (TJmFileSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    public String updateByPrimaryKeySelective(TJmFile record) {
        BEGIN();
        UPDATE("IN.T_JM_FILE");

        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{insertUid,jdbcType=VARCHAR}");
        }

        if (record.getFilePath() != null) {
            SET("FILE_PATH = #{filePath,jdbcType=VARCHAR}");
        }

        WHERE("MAC_TRX_DATE = #{macTrxDate,jdbcType=VARCHAR}");
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        WHERE("FILE_NAME = #{fileName,jdbcType=VARCHAR}");

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    protected void applyWhere(TJmFileSpec spec, boolean includeSpecPhrase) {
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
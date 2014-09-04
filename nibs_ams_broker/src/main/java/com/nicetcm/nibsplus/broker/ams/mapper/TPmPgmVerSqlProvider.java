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

import com.nicetcm.nibsplus.broker.ams.model.TPmPgmVer;
import com.nicetcm.nibsplus.broker.ams.model.TPmPgmVerSpec.Criteria;
import com.nicetcm.nibsplus.broker.ams.model.TPmPgmVerSpec.Criterion;
import com.nicetcm.nibsplus.broker.ams.model.TPmPgmVerSpec;
import java.util.List;
import java.util.Map;

public class TPmPgmVerSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_PGM_VER
     *
     * @mbggenerated Wed Sep 03 17:52:52 KST 2014
     */
    public String countBySpec(TPmPgmVerSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("IN.T_PM_PGM_VER");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_PGM_VER
     *
     * @mbggenerated Wed Sep 03 17:52:52 KST 2014
     */
    public String deleteBySpec(TPmPgmVerSpec spec) {
        BEGIN();
        DELETE_FROM("IN.T_PM_PGM_VER");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_PGM_VER
     *
     * @mbggenerated Wed Sep 03 17:52:52 KST 2014
     */
    public String insertSelective(TPmPgmVer record) {
        BEGIN();
        INSERT_INTO("IN.T_PM_PGM_VER");

        if (record.getVerId() != null) {
            VALUES("VER_ID", "#{verId,jdbcType=VARCHAR}");
        }

        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getInsertUid() != null) {
            VALUES("INSERT_UID", "#{insertUid,jdbcType=VARCHAR}");
        }

        if (record.getDepVerId() != null) {
            VALUES("DEP_VER_ID", "#{depVerId,jdbcType=VARCHAR}");
        }

        if (record.getSortId() != null) {
            VALUES("SORT_ID", "#{sortId,jdbcType=VARCHAR}");
        }

        if (record.getMasterYn() != null) {
            VALUES("MASTER_YN", "#{masterYn,jdbcType=CHAR}");
        }

        if (record.getCreateDate() != null) {
            VALUES("CREATE_DATE", "#{createDate,jdbcType=VARCHAR}");
        }

        if (record.getFileSeq() != null) {
            VALUES("FILE_SEQ", "#{fileSeq,jdbcType=VARCHAR}");
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_PGM_VER
     *
     * @mbggenerated Wed Sep 03 17:52:52 KST 2014
     */
    public String selectBySpec(TPmPgmVerSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("VER_ID");
        } else {
            SELECT("VER_ID");
        }
        SELECT("INSERT_DATE");
        SELECT("INSERT_UID");
        SELECT("DEP_VER_ID");
        SELECT("SORT_ID");
        SELECT("MASTER_YN");
        SELECT("CREATE_DATE");
        SELECT("FILE_SEQ");
        FROM("IN.T_PM_PGM_VER");
        applyWhere(spec, false);

        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_PGM_VER
     *
     * @mbggenerated Wed Sep 03 17:52:52 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TPmPgmVer record = (TPmPgmVer) parameter.get("record");
        TPmPgmVerSpec spec = (TPmPgmVerSpec) parameter.get("spec");

        BEGIN();
        UPDATE("IN.T_PM_PGM_VER");

        if (record.getVerId() != null) {
            SET("VER_ID = #{record.verId,jdbcType=VARCHAR}");
        }

        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        }

        if (record.getDepVerId() != null) {
            SET("DEP_VER_ID = #{record.depVerId,jdbcType=VARCHAR}");
        }

        if (record.getSortId() != null) {
            SET("SORT_ID = #{record.sortId,jdbcType=VARCHAR}");
        }

        if (record.getMasterYn() != null) {
            SET("MASTER_YN = #{record.masterYn,jdbcType=CHAR}");
        }

        if (record.getCreateDate() != null) {
            SET("CREATE_DATE = #{record.createDate,jdbcType=VARCHAR}");
        }

        if (record.getFileSeq() != null) {
            SET("FILE_SEQ = #{record.fileSeq,jdbcType=VARCHAR}");
        }

        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_PGM_VER
     *
     * @mbggenerated Wed Sep 03 17:52:52 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("IN.T_PM_PGM_VER");

        SET("VER_ID = #{record.verId,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        SET("DEP_VER_ID = #{record.depVerId,jdbcType=VARCHAR}");
        SET("SORT_ID = #{record.sortId,jdbcType=VARCHAR}");
        SET("MASTER_YN = #{record.masterYn,jdbcType=CHAR}");
        SET("CREATE_DATE = #{record.createDate,jdbcType=VARCHAR}");
        SET("FILE_SEQ = #{record.fileSeq,jdbcType=VARCHAR}");

        TPmPgmVerSpec spec = (TPmPgmVerSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_PGM_VER
     *
     * @mbggenerated Wed Sep 03 17:52:52 KST 2014
     */
    public String updateByPrimaryKeySelective(TPmPgmVer record) {
        BEGIN();
        UPDATE("IN.T_PM_PGM_VER");

        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }

        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{insertUid,jdbcType=VARCHAR}");
        }

        if (record.getDepVerId() != null) {
            SET("DEP_VER_ID = #{depVerId,jdbcType=VARCHAR}");
        }

        if (record.getSortId() != null) {
            SET("SORT_ID = #{sortId,jdbcType=VARCHAR}");
        }

        if (record.getMasterYn() != null) {
            SET("MASTER_YN = #{masterYn,jdbcType=CHAR}");
        }

        if (record.getCreateDate() != null) {
            SET("CREATE_DATE = #{createDate,jdbcType=VARCHAR}");
        }

        if (record.getFileSeq() != null) {
            SET("FILE_SEQ = #{fileSeq,jdbcType=VARCHAR}");
        }

        WHERE("VER_ID = #{verId,jdbcType=VARCHAR}");

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_PGM_VER
     *
     * @mbggenerated Wed Sep 03 17:52:52 KST 2014
     */
    protected void applyWhere(TPmPgmVerSpec spec, boolean includeSpecPhrase) {
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
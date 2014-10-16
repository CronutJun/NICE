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

import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseKiosk;
import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseKioskSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseKioskSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseKioskSpec;
import java.util.List;
import java.util.Map;

public class TFnDayCloseKioskSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KIOSK
     *
     * @mbggenerated Fri Oct 10 14:06:16 KST 2014
     */
    public String countBySpec(TFnDayCloseKioskSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_DAY_CLOSE_KIOSK");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KIOSK
     *
     * @mbggenerated Fri Oct 10 14:06:16 KST 2014
     */
    public String deleteBySpec(TFnDayCloseKioskSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_DAY_CLOSE_KIOSK");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KIOSK
     *
     * @mbggenerated Fri Oct 10 14:06:16 KST 2014
     */
    public String insertSelective(TFnDayCloseKiosk record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_DAY_CLOSE_KIOSK");

        if (record.getCloseDate() != null) {
            VALUES("CLOSE_DATE", "#{closeDate,jdbcType=VARCHAR}");
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

        if (record.getCloseType() != null) {
            VALUES("CLOSE_TYPE", "#{closeType,jdbcType=VARCHAR}");
        }

        if (record.getTicketCd() != null) {
            VALUES("TICKET_CD", "#{ticketCd,jdbcType=VARCHAR}");
        }

        if (record.getBillGubunCd() != null) {
            VALUES("BILL_GUBUN_CD", "#{billGubunCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseTime() != null) {
            VALUES("CLOSE_TIME", "#{closeTime,jdbcType=VARCHAR}");
        }

        if (record.getOutCnt() != null) {
            VALUES("OUT_CNT", "#{outCnt,jdbcType=DECIMAL}");
        }

        if (record.getDeptCd() != null) {
            VALUES("DEPT_CD", "#{deptCd,jdbcType=VARCHAR}");
        }

        if (record.getOfficeCd() != null) {
            VALUES("OFFICE_CD", "#{officeCd,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateUid() != null) {
            VALUES("UPDATE_UID", "#{updateUid,jdbcType=VARCHAR}");
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KIOSK
     *
     * @mbggenerated Fri Oct 10 14:06:16 KST 2014
     */
    public String selectBySpec(TFnDayCloseKioskSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("CLOSE_DATE");
        } else {
            SELECT("CLOSE_DATE");
        }
        SELECT("ORG_CD");
        SELECT("BRANCH_CD");
        SELECT("MAC_NO");
        SELECT("CLOSE_TYPE");
        SELECT("TICKET_CD");
        SELECT("BILL_GUBUN_CD");
        SELECT("CLOSE_TIME");
        SELECT("OUT_CNT");
        SELECT("DEPT_CD");
        SELECT("OFFICE_CD");
        SELECT("UPDATE_DATE");
        SELECT("UPDATE_UID");
        FROM("OP.T_FN_DAY_CLOSE_KIOSK");
        applyWhere(spec, false);

        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KIOSK
     *
     * @mbggenerated Fri Oct 10 14:06:16 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnDayCloseKiosk record = (TFnDayCloseKiosk) parameter.get("record");
        TFnDayCloseKioskSpec spec = (TFnDayCloseKioskSpec) parameter.get("spec");

        BEGIN();
        UPDATE("OP.T_FN_DAY_CLOSE_KIOSK");

        if (record.getCloseDate() != null) {
            SET("CLOSE_DATE = #{record.closeDate,jdbcType=VARCHAR}");
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

        if (record.getCloseType() != null) {
            SET("CLOSE_TYPE = #{record.closeType,jdbcType=VARCHAR}");
        }

        if (record.getTicketCd() != null) {
            SET("TICKET_CD = #{record.ticketCd,jdbcType=VARCHAR}");
        }

        if (record.getBillGubunCd() != null) {
            SET("BILL_GUBUN_CD = #{record.billGubunCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseTime() != null) {
            SET("CLOSE_TIME = #{record.closeTime,jdbcType=VARCHAR}");
        }

        if (record.getOutCnt() != null) {
            SET("OUT_CNT = #{record.outCnt,jdbcType=DECIMAL}");
        }

        if (record.getDeptCd() != null) {
            SET("DEPT_CD = #{record.deptCd,jdbcType=VARCHAR}");
        }

        if (record.getOfficeCd() != null) {
            SET("OFFICE_CD = #{record.officeCd,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        }

        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KIOSK
     *
     * @mbggenerated Fri Oct 10 14:06:16 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_DAY_CLOSE_KIOSK");

        SET("CLOSE_DATE = #{record.closeDate,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("CLOSE_TYPE = #{record.closeType,jdbcType=VARCHAR}");
        SET("TICKET_CD = #{record.ticketCd,jdbcType=VARCHAR}");
        SET("BILL_GUBUN_CD = #{record.billGubunCd,jdbcType=VARCHAR}");
        SET("CLOSE_TIME = #{record.closeTime,jdbcType=VARCHAR}");
        SET("OUT_CNT = #{record.outCnt,jdbcType=DECIMAL}");
        SET("DEPT_CD = #{record.deptCd,jdbcType=VARCHAR}");
        SET("OFFICE_CD = #{record.officeCd,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");

        TFnDayCloseKioskSpec spec = (TFnDayCloseKioskSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KIOSK
     *
     * @mbggenerated Fri Oct 10 14:06:16 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnDayCloseKiosk record) {
        BEGIN();
        UPDATE("OP.T_FN_DAY_CLOSE_KIOSK");

        if (record.getCloseTime() != null) {
            SET("CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}");
        }

        if (record.getOutCnt() != null) {
            SET("OUT_CNT = #{outCnt,jdbcType=DECIMAL}");
        }

        if (record.getDeptCd() != null) {
            SET("DEPT_CD = #{deptCd,jdbcType=VARCHAR}");
        }

        if (record.getOfficeCd() != null) {
            SET("OFFICE_CD = #{officeCd,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{updateUid,jdbcType=VARCHAR}");
        }

        WHERE("CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}");
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        WHERE("CLOSE_TYPE = #{closeType,jdbcType=VARCHAR}");
        WHERE("TICKET_CD = #{ticketCd,jdbcType=VARCHAR}");
        WHERE("BILL_GUBUN_CD = #{billGubunCd,jdbcType=VARCHAR}");

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KIOSK
     *
     * @mbggenerated Fri Oct 10 14:06:16 KST 2014
     */
    protected void applyWhere(TFnDayCloseKioskSpec spec, boolean includeSpecPhrase) {
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
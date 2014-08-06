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

import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrg;
import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrgSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrgSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrgSpec;
import java.util.List;
import java.util.Map;

public class TFnBoxOrgSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String countBySpec(TFnBoxOrgSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_BOX_ORG");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String deleteBySpec(TFnBoxOrgSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_BOX_ORG");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String insertSelective(TFnBoxOrg record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_BOX_ORG");
        
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            VALUES("BRANCH_CD", "#{branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDealDate() != null) {
            VALUES("DEAL_DATE", "#{dealDate,jdbcType=VARCHAR}");
        }
        
        if (record.getSeq() != null) {
            VALUES("SEQ", "#{seq,jdbcType=VARCHAR}");
        }
        
        if (record.getBoxSeq() != null) {
            VALUES("BOX_SEQ", "#{boxSeq,jdbcType=VARCHAR}");
        }
        
        if (record.getBoxGubun1() != null) {
            VALUES("BOX_GUBUN1", "#{boxGubun1,jdbcType=VARCHAR}");
        }
        
        if (record.getBoxGubun2() != null) {
            VALUES("BOX_GUBUN2", "#{boxGubun2,jdbcType=VARCHAR}");
        }
        
        if (record.getKjGubun() != null) {
            VALUES("KJ_GUBUN", "#{kjGubun,jdbcType=VARCHAR}");
        }
        
        if (record.getKjCnt() != null) {
            VALUES("KJ_CNT", "#{kjCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getKjAmt() != null) {
            VALUES("KJ_AMT", "#{kjAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getTicketSeq() != null) {
            VALUES("TICKET_SEQ", "#{ticketSeq,jdbcType=VARCHAR}");
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
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String selectBySpec(TFnBoxOrgSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("ORG_CD");
        } else {
            SELECT("ORG_CD");
        }
        SELECT("BRANCH_CD");
        SELECT("MAC_NO");
        SELECT("DEAL_DATE");
        SELECT("SEQ");
        SELECT("BOX_SEQ");
        SELECT("BOX_GUBUN1");
        SELECT("BOX_GUBUN2");
        SELECT("KJ_GUBUN");
        SELECT("KJ_CNT");
        SELECT("KJ_AMT");
        SELECT("TICKET_SEQ");
        SELECT("UPDATE_DATE");
        SELECT("UPDATE_UID");
        FROM("OP.T_FN_BOX_ORG");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnBoxOrg record = (TFnBoxOrg) parameter.get("record");
        TFnBoxOrgSpec spec = (TFnBoxOrgSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_FN_BOX_ORG");
        
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getDealDate() != null) {
            SET("DEAL_DATE = #{record.dealDate,jdbcType=VARCHAR}");
        }
        
        if (record.getSeq() != null) {
            SET("SEQ = #{record.seq,jdbcType=VARCHAR}");
        }
        
        if (record.getBoxSeq() != null) {
            SET("BOX_SEQ = #{record.boxSeq,jdbcType=VARCHAR}");
        }
        
        if (record.getBoxGubun1() != null) {
            SET("BOX_GUBUN1 = #{record.boxGubun1,jdbcType=VARCHAR}");
        }
        
        if (record.getBoxGubun2() != null) {
            SET("BOX_GUBUN2 = #{record.boxGubun2,jdbcType=VARCHAR}");
        }
        
        if (record.getKjGubun() != null) {
            SET("KJ_GUBUN = #{record.kjGubun,jdbcType=VARCHAR}");
        }
        
        if (record.getKjCnt() != null) {
            SET("KJ_CNT = #{record.kjCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getKjAmt() != null) {
            SET("KJ_AMT = #{record.kjAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getTicketSeq() != null) {
            SET("TICKET_SEQ = #{record.ticketSeq,jdbcType=VARCHAR}");
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
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_BOX_ORG");
        
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("DEAL_DATE = #{record.dealDate,jdbcType=VARCHAR}");
        SET("SEQ = #{record.seq,jdbcType=VARCHAR}");
        SET("BOX_SEQ = #{record.boxSeq,jdbcType=VARCHAR}");
        SET("BOX_GUBUN1 = #{record.boxGubun1,jdbcType=VARCHAR}");
        SET("BOX_GUBUN2 = #{record.boxGubun2,jdbcType=VARCHAR}");
        SET("KJ_GUBUN = #{record.kjGubun,jdbcType=VARCHAR}");
        SET("KJ_CNT = #{record.kjCnt,jdbcType=DECIMAL}");
        SET("KJ_AMT = #{record.kjAmt,jdbcType=DECIMAL}");
        SET("TICKET_SEQ = #{record.ticketSeq,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        
        TFnBoxOrgSpec spec = (TFnBoxOrgSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnBoxOrg record) {
        BEGIN();
        UPDATE("OP.T_FN_BOX_ORG");
        
        if (record.getBoxGubun1() != null) {
            SET("BOX_GUBUN1 = #{boxGubun1,jdbcType=VARCHAR}");
        }
        
        if (record.getBoxGubun2() != null) {
            SET("BOX_GUBUN2 = #{boxGubun2,jdbcType=VARCHAR}");
        }
        
        if (record.getKjGubun() != null) {
            SET("KJ_GUBUN = #{kjGubun,jdbcType=VARCHAR}");
        }
        
        if (record.getKjCnt() != null) {
            SET("KJ_CNT = #{kjCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getKjAmt() != null) {
            SET("KJ_AMT = #{kjAmt,jdbcType=DECIMAL}");
        }
        
        if (record.getTicketSeq() != null) {
            SET("TICKET_SEQ = #{ticketSeq,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{updateUid,jdbcType=VARCHAR}");
        }
        
        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        WHERE("DEAL_DATE = #{dealDate,jdbcType=VARCHAR}");
        WHERE("SEQ = #{seq,jdbcType=VARCHAR}");
        WHERE("BOX_SEQ = #{boxSeq,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    protected void applyWhere(TFnBoxOrgSpec spec, boolean includeSpecPhrase) {
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
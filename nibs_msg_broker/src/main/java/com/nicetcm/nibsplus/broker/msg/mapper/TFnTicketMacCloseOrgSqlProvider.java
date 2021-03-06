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

import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacCloseOrg;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacCloseOrgSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacCloseOrgSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacCloseOrgSpec;
import java.util.List;
import java.util.Map;

public class TFnTicketMacCloseOrgSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String countBySpec(TFnTicketMacCloseOrgSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_TICKET_MAC_CLOSE_ORG");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String deleteBySpec(TFnTicketMacCloseOrgSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_TICKET_MAC_CLOSE_ORG");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String insertSelective(TFnTicketMacCloseOrg record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_TICKET_MAC_CLOSE_ORG");
        
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseDate() != null) {
            VALUES("CLOSE_DATE", "#{closeDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseTime() != null) {
            VALUES("CLOSE_TIME", "#{closeTime,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1EmitComp() != null) {
            VALUES("TICKET1_EMIT_COMP", "#{ticket1EmitComp,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1EmitGubunCd() != null) {
            VALUES("TICKET1_EMIT_GUBUN_CD", "#{ticket1EmitGubunCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1EmitCnt() != null) {
            VALUES("TICKET1_EMIT_CNT", "#{ticket1EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTicket1BackComp() != null) {
            VALUES("TICKET1_BACK_COMP", "#{ticket1BackComp,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1BackGubunCd() != null) {
            VALUES("TICKET1_BACK_GUBUN_CD", "#{ticket1BackGubunCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1BackCnt() != null) {
            VALUES("TICKET1_BACK_CNT", "#{ticket1BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInsertUid() != null) {
            VALUES("INSERT_UID", "#{insertUid,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String selectBySpec(TFnTicketMacCloseOrgSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("MAC_NO");
        } else {
            SELECT("MAC_NO");
        }
        SELECT("CLOSE_DATE");
        SELECT("CLOSE_TIME");
        SELECT("TICKET1_EMIT_COMP");
        SELECT("TICKET1_EMIT_GUBUN_CD");
        SELECT("TICKET1_EMIT_CNT");
        SELECT("TICKET1_BACK_COMP");
        SELECT("TICKET1_BACK_GUBUN_CD");
        SELECT("TICKET1_BACK_CNT");
        SELECT("INSERT_DATE");
        SELECT("INSERT_UID");
        FROM("OP.T_FN_TICKET_MAC_CLOSE_ORG");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnTicketMacCloseOrg record = (TFnTicketMacCloseOrg) parameter.get("record");
        TFnTicketMacCloseOrgSpec spec = (TFnTicketMacCloseOrgSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_FN_TICKET_MAC_CLOSE_ORG");
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseDate() != null) {
            SET("CLOSE_DATE = #{record.closeDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCloseTime() != null) {
            SET("CLOSE_TIME = #{record.closeTime,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1EmitComp() != null) {
            SET("TICKET1_EMIT_COMP = #{record.ticket1EmitComp,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1EmitGubunCd() != null) {
            SET("TICKET1_EMIT_GUBUN_CD = #{record.ticket1EmitGubunCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1EmitCnt() != null) {
            SET("TICKET1_EMIT_CNT = #{record.ticket1EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTicket1BackComp() != null) {
            SET("TICKET1_BACK_COMP = #{record.ticket1BackComp,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1BackGubunCd() != null) {
            SET("TICKET1_BACK_GUBUN_CD = #{record.ticket1BackGubunCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1BackCnt() != null) {
            SET("TICKET1_BACK_CNT = #{record.ticket1BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_TICKET_MAC_CLOSE_ORG");
        
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("CLOSE_DATE = #{record.closeDate,jdbcType=VARCHAR}");
        SET("CLOSE_TIME = #{record.closeTime,jdbcType=VARCHAR}");
        SET("TICKET1_EMIT_COMP = #{record.ticket1EmitComp,jdbcType=VARCHAR}");
        SET("TICKET1_EMIT_GUBUN_CD = #{record.ticket1EmitGubunCd,jdbcType=VARCHAR}");
        SET("TICKET1_EMIT_CNT = #{record.ticket1EmitCnt,jdbcType=DECIMAL}");
        SET("TICKET1_BACK_COMP = #{record.ticket1BackComp,jdbcType=VARCHAR}");
        SET("TICKET1_BACK_GUBUN_CD = #{record.ticket1BackGubunCd,jdbcType=VARCHAR}");
        SET("TICKET1_BACK_CNT = #{record.ticket1BackCnt,jdbcType=DECIMAL}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        
        TFnTicketMacCloseOrgSpec spec = (TFnTicketMacCloseOrgSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnTicketMacCloseOrg record) {
        BEGIN();
        UPDATE("OP.T_FN_TICKET_MAC_CLOSE_ORG");
        
        if (record.getTicket1EmitComp() != null) {
            SET("TICKET1_EMIT_COMP = #{ticket1EmitComp,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1EmitGubunCd() != null) {
            SET("TICKET1_EMIT_GUBUN_CD = #{ticket1EmitGubunCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1EmitCnt() != null) {
            SET("TICKET1_EMIT_CNT = #{ticket1EmitCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getTicket1BackComp() != null) {
            SET("TICKET1_BACK_COMP = #{ticket1BackComp,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1BackGubunCd() != null) {
            SET("TICKET1_BACK_GUBUN_CD = #{ticket1BackGubunCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTicket1BackCnt() != null) {
            SET("TICKET1_BACK_CNT = #{ticket1BackCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getInsertUid() != null) {
            SET("INSERT_UID = #{insertUid,jdbcType=VARCHAR}");
        }
        
        WHERE("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        WHERE("CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}");
        WHERE("CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE_ORG
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    protected void applyWhere(TFnTicketMacCloseOrgSpec spec, boolean includeSpecPhrase) {
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
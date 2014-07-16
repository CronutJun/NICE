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

import com.nicetcm.nibsplus.broker.msg.model.TCtUnfinish;
import com.nicetcm.nibsplus.broker.msg.model.TCtUnfinishSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TCtUnfinishSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TCtUnfinishSpec;
import java.util.List;
import java.util.Map;

public class TCtUnfinishSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_UNFINISH
     *
     * @mbggenerated Tue Jul 01 09:59:32 KST 2014
     */
    public String countBySpec(TCtUnfinishSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_CT_UNFINISH");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_UNFINISH
     *
     * @mbggenerated Tue Jul 01 09:59:32 KST 2014
     */
    public String deleteBySpec(TCtUnfinishSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_CT_UNFINISH");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_UNFINISH
     *
     * @mbggenerated Tue Jul 01 09:59:32 KST 2014
     */
    public String insertSelective(TCtUnfinish record) {
        BEGIN();
        INSERT_INTO("OP.T_CT_UNFINISH");
        
        if (record.getErrorNo() != null) {
            VALUES("ERROR_NO", "#{errorNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            VALUES("CREATE_DATE", "#{createDate,jdbcType=DECIMAL}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("CREATE_TIME", "#{createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getSendDate() != null) {
            VALUES("SEND_DATE", "#{sendDate,jdbcType=VARCHAR}");
        }
        
        if (record.getSendTime() != null) {
            VALUES("SEND_TIME", "#{sendTime,jdbcType=VARCHAR}");
        }
        
        if (record.getErrorCd() != null) {
            VALUES("ERROR_CD", "#{errorCd,jdbcType=VARCHAR}");
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
        
        if (record.getSiteCd() != null) {
            VALUES("SITE_CD", "#{siteCd,jdbcType=VARCHAR}");
        }
        
        if (record.getSendStatus() != null) {
            VALUES("SEND_STATUS", "#{sendStatus,jdbcType=CHAR}");
        }
        
        if (record.getSendYn() != null) {
            VALUES("SEND_YN", "#{sendYn,jdbcType=CHAR}");
        }
        
        if (record.getErrorStatus() != null) {
            VALUES("ERROR_STATUS", "#{errorStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelaySendYn() != null) {
            VALUES("DELAY_SEND_YN", "#{delaySendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSendTool() != null) {
            VALUES("SEND_TOOL", "#{sendTool,jdbcType=VARCHAR}");
        }
        
        if (record.getRecvUserUid() != null) {
            VALUES("RECV_USER_UID", "#{recvUserUid,jdbcType=VARCHAR}");
        }
        
        if (record.getRecvTeleNo() != null) {
            VALUES("RECV_TELE_NO", "#{recvTeleNo,jdbcType=VARCHAR}");
        }
        
        if (record.getTransDate() != null) {
            VALUES("TRANS_DATE", "#{transDate,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgMsgNo() != null) {
            VALUES("ORG_MSG_NO", "#{orgMsgNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgMsg() != null) {
            VALUES("ORG_MSG", "#{orgMsg,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_UNFINISH
     *
     * @mbggenerated Tue Jul 01 09:59:32 KST 2014
     */
    public String selectBySpec(TCtUnfinishSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("ERROR_NO");
        } else {
            SELECT("ERROR_NO");
        }
        SELECT("CREATE_DATE");
        SELECT("CREATE_TIME");
        SELECT("SEND_DATE");
        SELECT("SEND_TIME");
        SELECT("ERROR_CD");
        SELECT("ORG_CD");
        SELECT("BRANCH_CD");
        SELECT("MAC_NO");
        SELECT("SITE_CD");
        SELECT("SEND_STATUS");
        SELECT("SEND_YN");
        SELECT("ERROR_STATUS");
        SELECT("UPDATE_DATE");
        SELECT("DELAY_SEND_YN");
        SELECT("SEND_TOOL");
        SELECT("RECV_USER_UID");
        SELECT("RECV_TELE_NO");
        SELECT("TRANS_DATE");
        SELECT("ORG_MSG_NO");
        SELECT("ORG_MSG");
        FROM("OP.T_CT_UNFINISH");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_UNFINISH
     *
     * @mbggenerated Tue Jul 01 09:59:32 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TCtUnfinish record = (TCtUnfinish) parameter.get("record");
        TCtUnfinishSpec spec = (TCtUnfinishSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_CT_UNFINISH");
        
        if (record.getErrorNo() != null) {
            SET("ERROR_NO = #{record.errorNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateDate() != null) {
            SET("CREATE_DATE = #{record.createDate,jdbcType=DECIMAL}");
        }
        
        if (record.getCreateTime() != null) {
            SET("CREATE_TIME = #{record.createTime,jdbcType=VARCHAR}");
        }
        
        if (record.getSendDate() != null) {
            SET("SEND_DATE = #{record.sendDate,jdbcType=VARCHAR}");
        }
        
        if (record.getSendTime() != null) {
            SET("SEND_TIME = #{record.sendTime,jdbcType=VARCHAR}");
        }
        
        if (record.getErrorCd() != null) {
            SET("ERROR_CD = #{record.errorCd,jdbcType=VARCHAR}");
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
        
        if (record.getSiteCd() != null) {
            SET("SITE_CD = #{record.siteCd,jdbcType=VARCHAR}");
        }
        
        if (record.getSendStatus() != null) {
            SET("SEND_STATUS = #{record.sendStatus,jdbcType=CHAR}");
        }
        
        if (record.getSendYn() != null) {
            SET("SEND_YN = #{record.sendYn,jdbcType=CHAR}");
        }
        
        if (record.getErrorStatus() != null) {
            SET("ERROR_STATUS = #{record.errorStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelaySendYn() != null) {
            SET("DELAY_SEND_YN = #{record.delaySendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSendTool() != null) {
            SET("SEND_TOOL = #{record.sendTool,jdbcType=VARCHAR}");
        }
        
        if (record.getRecvUserUid() != null) {
            SET("RECV_USER_UID = #{record.recvUserUid,jdbcType=VARCHAR}");
        }
        
        if (record.getRecvTeleNo() != null) {
            SET("RECV_TELE_NO = #{record.recvTeleNo,jdbcType=VARCHAR}");
        }
        
        if (record.getTransDate() != null) {
            SET("TRANS_DATE = #{record.transDate,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgMsgNo() != null) {
            SET("ORG_MSG_NO = #{record.orgMsgNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgMsg() != null) {
            SET("ORG_MSG = #{record.orgMsg,jdbcType=VARCHAR}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_UNFINISH
     *
     * @mbggenerated Tue Jul 01 09:59:32 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_CT_UNFINISH");
        
        SET("ERROR_NO = #{record.errorNo,jdbcType=VARCHAR}");
        SET("CREATE_DATE = #{record.createDate,jdbcType=DECIMAL}");
        SET("CREATE_TIME = #{record.createTime,jdbcType=VARCHAR}");
        SET("SEND_DATE = #{record.sendDate,jdbcType=VARCHAR}");
        SET("SEND_TIME = #{record.sendTime,jdbcType=VARCHAR}");
        SET("ERROR_CD = #{record.errorCd,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("SITE_CD = #{record.siteCd,jdbcType=VARCHAR}");
        SET("SEND_STATUS = #{record.sendStatus,jdbcType=CHAR}");
        SET("SEND_YN = #{record.sendYn,jdbcType=CHAR}");
        SET("ERROR_STATUS = #{record.errorStatus,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("DELAY_SEND_YN = #{record.delaySendYn,jdbcType=VARCHAR}");
        SET("SEND_TOOL = #{record.sendTool,jdbcType=VARCHAR}");
        SET("RECV_USER_UID = #{record.recvUserUid,jdbcType=VARCHAR}");
        SET("RECV_TELE_NO = #{record.recvTeleNo,jdbcType=VARCHAR}");
        SET("TRANS_DATE = #{record.transDate,jdbcType=VARCHAR}");
        SET("ORG_MSG_NO = #{record.orgMsgNo,jdbcType=VARCHAR}");
        SET("ORG_MSG = #{record.orgMsg,jdbcType=VARCHAR}");
        
        TCtUnfinishSpec spec = (TCtUnfinishSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_UNFINISH
     *
     * @mbggenerated Tue Jul 01 09:59:32 KST 2014
     */
    public String updateByPrimaryKeySelective(TCtUnfinish record) {
        BEGIN();
        UPDATE("OP.T_CT_UNFINISH");
        
        if (record.getSendDate() != null) {
            SET("SEND_DATE = #{sendDate,jdbcType=VARCHAR}");
        }
        
        if (record.getSendTime() != null) {
            SET("SEND_TIME = #{sendTime,jdbcType=VARCHAR}");
        }
        
        if (record.getErrorCd() != null) {
            SET("ERROR_CD = #{errorCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSiteCd() != null) {
            SET("SITE_CD = #{siteCd,jdbcType=VARCHAR}");
        }
        
        if (record.getSendStatus() != null) {
            SET("SEND_STATUS = #{sendStatus,jdbcType=CHAR}");
        }
        
        if (record.getSendYn() != null) {
            SET("SEND_YN = #{sendYn,jdbcType=CHAR}");
        }
        
        if (record.getErrorStatus() != null) {
            SET("ERROR_STATUS = #{errorStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDelaySendYn() != null) {
            SET("DELAY_SEND_YN = #{delaySendYn,jdbcType=VARCHAR}");
        }
        
        if (record.getSendTool() != null) {
            SET("SEND_TOOL = #{sendTool,jdbcType=VARCHAR}");
        }
        
        if (record.getRecvUserUid() != null) {
            SET("RECV_USER_UID = #{recvUserUid,jdbcType=VARCHAR}");
        }
        
        if (record.getRecvTeleNo() != null) {
            SET("RECV_TELE_NO = #{recvTeleNo,jdbcType=VARCHAR}");
        }
        
        if (record.getTransDate() != null) {
            SET("TRANS_DATE = #{transDate,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgMsgNo() != null) {
            SET("ORG_MSG_NO = #{orgMsgNo,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgMsg() != null) {
            SET("ORG_MSG = #{orgMsg,jdbcType=VARCHAR}");
        }
        
        WHERE("ERROR_NO = #{errorNo,jdbcType=VARCHAR}");
        WHERE("CREATE_DATE = #{createDate,jdbcType=DECIMAL}");
        WHERE("CREATE_TIME = #{createTime,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_UNFINISH
     *
     * @mbggenerated Tue Jul 01 09:59:32 KST 2014
     */
    protected void applyWhere(TCtUnfinishSpec spec, boolean includeSpecPhrase) {
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
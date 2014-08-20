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

import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgSpec.Criteria;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgSpec.Criterion;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgSpec;
import java.util.List;
import java.util.Map;

public class TRmMsgSqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table IN.T_RM_MSG
     * @mbggenerated  Mon Aug 18 10:55:09 KST 2014
     */
    public String countBySpec(TRmMsgSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("IN.T_RM_MSG");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table IN.T_RM_MSG
     * @mbggenerated  Mon Aug 18 10:55:09 KST 2014
     */
    public String deleteBySpec(TRmMsgSpec spec) {
        BEGIN();
        DELETE_FROM("IN.T_RM_MSG");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table IN.T_RM_MSG
     * @mbggenerated  Mon Aug 18 10:55:09 KST 2014
     */
    public String insertSelective(TRmMsg record) {
        BEGIN();
        INSERT_INTO("IN.T_RM_MSG");
        if (record.getCreateDate() != null) {
            VALUES("CREATE_DATE", "#{createDate,jdbcType=VARCHAR}");
        }
        if (record.getMsgSeq() != null) {
            VALUES("MSG_SEQ", "#{msgSeq,jdbcType=VARCHAR}");
        }
        if (record.getCreateTime() != null) {
            VALUES("CREATE_TIME", "#{createTime,jdbcType=VARCHAR}");
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
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        if (record.getMacMsgSeq() != null) {
            VALUES("MAC_MSG_SEQ", "#{macMsgSeq,jdbcType=VARCHAR}");
        }
        if (record.getIoCl() != null) {
            VALUES("IO_CL", "#{ioCl,jdbcType=VARCHAR}");
        }
        if (record.getMsgSts() != null) {
            VALUES("MSG_STS", "#{msgSts,jdbcType=VARCHAR}");
        }
        if (record.getMsgType() != null) {
            VALUES("MSG_TYPE", "#{msgType,jdbcType=VARCHAR}");
        }
        if (record.getMsgCd() != null) {
            VALUES("MSG_CD", "#{msgCd,jdbcType=VARCHAR}");
        }
        if (record.getSvcCd() != null) {
            VALUES("SVC_CD", "#{svcCd,jdbcType=VARCHAR}");
        }
        if (record.getMacSerNo() != null) {
            VALUES("MAC_SER_NO", "#{macSerNo,jdbcType=VARCHAR}");
        }
        if (record.getBranchCd() != null) {
            VALUES("BRANCH_CD", "#{branchCd,jdbcType=VARCHAR}");
        }
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        if (record.getTrxDate() != null) {
            VALUES("TRX_DATE", "#{trxDate,jdbcType=VARCHAR}");
        }
        if (record.getTrxNo() != null) {
            VALUES("TRX_NO", "#{trxNo,jdbcType=VARCHAR}");
        }
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table IN.T_RM_MSG
     * @mbggenerated  Mon Aug 18 10:55:09 KST 2014
     */
    public String selectBySpec(TRmMsgSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("CREATE_DATE");
        } else {
            SELECT("CREATE_DATE");
        }
        SELECT("MSG_SEQ");
        SELECT("CREATE_TIME");
        SELECT("INSERT_UID");
        SELECT("INSERT_DATE");
        SELECT("UPDATE_UID");
        SELECT("UPDATE_DATE");
        SELECT("MAC_NO");
        SELECT("MAC_MSG_SEQ");
        SELECT("IO_CL");
        SELECT("MSG_STS");
        SELECT("MSG_TYPE");
        SELECT("MSG_CD");
        SELECT("SVC_CD");
        SELECT("MAC_SER_NO");
        SELECT("BRANCH_CD");
        SELECT("ORG_CD");
        SELECT("TRX_DATE");
        SELECT("TRX_NO");
        FROM("IN.T_RM_MSG");
        applyWhere(spec, false);
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table IN.T_RM_MSG
     * @mbggenerated  Mon Aug 18 10:55:09 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TRmMsg record = (TRmMsg) parameter.get("record");
        TRmMsgSpec spec = (TRmMsgSpec) parameter.get("spec");
        BEGIN();
        UPDATE("IN.T_RM_MSG");
        if (record.getCreateDate() != null) {
            SET("CREATE_DATE = #{record.createDate,jdbcType=VARCHAR}");
        }
        if (record.getMsgSeq() != null) {
            SET("MSG_SEQ = #{record.msgSeq,jdbcType=VARCHAR}");
        }
        if (record.getCreateTime() != null) {
            SET("CREATE_TIME = #{record.createTime,jdbcType=VARCHAR}");
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
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        if (record.getMacMsgSeq() != null) {
            SET("MAC_MSG_SEQ = #{record.macMsgSeq,jdbcType=VARCHAR}");
        }
        if (record.getIoCl() != null) {
            SET("IO_CL = #{record.ioCl,jdbcType=VARCHAR}");
        }
        if (record.getMsgSts() != null) {
            SET("MSG_STS = #{record.msgSts,jdbcType=VARCHAR}");
        }
        if (record.getMsgType() != null) {
            SET("MSG_TYPE = #{record.msgType,jdbcType=VARCHAR}");
        }
        if (record.getMsgCd() != null) {
            SET("MSG_CD = #{record.msgCd,jdbcType=VARCHAR}");
        }
        if (record.getSvcCd() != null) {
            SET("SVC_CD = #{record.svcCd,jdbcType=VARCHAR}");
        }
        if (record.getMacSerNo() != null) {
            SET("MAC_SER_NO = #{record.macSerNo,jdbcType=VARCHAR}");
        }
        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        }
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        if (record.getTrxDate() != null) {
            SET("TRX_DATE = #{record.trxDate,jdbcType=VARCHAR}");
        }
        if (record.getTrxNo() != null) {
            SET("TRX_NO = #{record.trxNo,jdbcType=VARCHAR}");
        }
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table IN.T_RM_MSG
     * @mbggenerated  Mon Aug 18 10:55:09 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("IN.T_RM_MSG");
        SET("CREATE_DATE = #{record.createDate,jdbcType=VARCHAR}");
        SET("MSG_SEQ = #{record.msgSeq,jdbcType=VARCHAR}");
        SET("CREATE_TIME = #{record.createTime,jdbcType=VARCHAR}");
        SET("INSERT_UID = #{record.insertUid,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("MAC_MSG_SEQ = #{record.macMsgSeq,jdbcType=VARCHAR}");
        SET("IO_CL = #{record.ioCl,jdbcType=VARCHAR}");
        SET("MSG_STS = #{record.msgSts,jdbcType=VARCHAR}");
        SET("MSG_TYPE = #{record.msgType,jdbcType=VARCHAR}");
        SET("MSG_CD = #{record.msgCd,jdbcType=VARCHAR}");
        SET("SVC_CD = #{record.svcCd,jdbcType=VARCHAR}");
        SET("MAC_SER_NO = #{record.macSerNo,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("TRX_DATE = #{record.trxDate,jdbcType=VARCHAR}");
        SET("TRX_NO = #{record.trxNo,jdbcType=VARCHAR}");
        TRmMsgSpec spec = (TRmMsgSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table IN.T_RM_MSG
     * @mbggenerated  Mon Aug 18 10:55:09 KST 2014
     */
    public String updateByPrimaryKeySelective(TRmMsg record) {
        BEGIN();
        UPDATE("IN.T_RM_MSG");
        if (record.getCreateTime() != null) {
            SET("CREATE_TIME = #{createTime,jdbcType=VARCHAR}");
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
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        }
        if (record.getMacMsgSeq() != null) {
            SET("MAC_MSG_SEQ = #{macMsgSeq,jdbcType=VARCHAR}");
        }
        if (record.getIoCl() != null) {
            SET("IO_CL = #{ioCl,jdbcType=VARCHAR}");
        }
        if (record.getMsgSts() != null) {
            SET("MSG_STS = #{msgSts,jdbcType=VARCHAR}");
        }
        if (record.getMsgType() != null) {
            SET("MSG_TYPE = #{msgType,jdbcType=VARCHAR}");
        }
        if (record.getMsgCd() != null) {
            SET("MSG_CD = #{msgCd,jdbcType=VARCHAR}");
        }
        if (record.getSvcCd() != null) {
            SET("SVC_CD = #{svcCd,jdbcType=VARCHAR}");
        }
        if (record.getMacSerNo() != null) {
            SET("MAC_SER_NO = #{macSerNo,jdbcType=VARCHAR}");
        }
        if (record.getBranchCd() != null) {
            SET("BRANCH_CD = #{branchCd,jdbcType=VARCHAR}");
        }
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        }
        if (record.getTrxDate() != null) {
            SET("TRX_DATE = #{trxDate,jdbcType=VARCHAR}");
        }
        if (record.getTrxNo() != null) {
            SET("TRX_NO = #{trxNo,jdbcType=VARCHAR}");
        }
        WHERE("CREATE_DATE = #{createDate,jdbcType=VARCHAR}");
        WHERE("MSG_SEQ = #{msgSeq,jdbcType=VARCHAR}");
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table IN.T_RM_MSG
     * @mbggenerated  Mon Aug 18 10:55:09 KST 2014
     */
    protected void applyWhere(TRmMsgSpec spec, boolean includeSpecPhrase) {
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
                            sb.append(String.format(parmPhrase1,
                                    criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th,
                                    criterion.getCondition(), i, j,
                                    criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2,
                                    criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th,
                                    criterion.getCondition(), i, j,
                                    criterion.getTypeHandler(), i, j,
                                    criterion.getTypeHandler()));
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
                                sb.append(String.format(parmPhrase3_th, i, j,
                                        k, criterion.getTypeHandler()));
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
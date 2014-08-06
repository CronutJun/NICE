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

import com.nicetcm.nibsplus.broker.msg.model.TCmSetMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetMacInfoSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetMacInfoSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetMacInfoSpec;
import java.util.List;
import java.util.Map;

public class TCmSetMacInfoSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public String countBySpec(TCmSetMacInfoSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_CM_SET_MAC_INFO");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public String deleteBySpec(TCmSetMacInfoSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_CM_SET_MAC_INFO");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public String insertSelective(TCmSetMacInfo record) {
        BEGIN();
        INSERT_INTO("OP.T_CM_SET_MAC_INFO");

        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }

        if (record.getWorkSeq() != null) {
            VALUES("WORK_SEQ", "#{workSeq,jdbcType=VARCHAR}");
        }

        if (record.getWorkClass() != null) {
            VALUES("WORK_CLASS", "#{workClass,jdbcType=VARCHAR}");
        }

        if (record.getMacSeqNo() != null) {
            VALUES("MAC_SEQ_NO", "#{macSeqNo,jdbcType=VARCHAR}");
        }

        if (record.getCloseType() != null) {
            VALUES("CLOSE_TYPE", "#{closeType,jdbcType=VARCHAR}");
        }

        if (record.getChangeNo() != null) {
            VALUES("CHANGE_NO", "#{changeNo,jdbcType=DECIMAL}");
        }

        if (record.getOrgBranchCd() != null) {
            VALUES("ORG_BRANCH_CD", "#{orgBranchCd,jdbcType=VARCHAR}");
        }

        if (record.getOrgSiteCd() != null) {
            VALUES("ORG_SITE_CD", "#{orgSiteCd,jdbcType=VARCHAR}");
        }

        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }

        if (record.getMadeComCd() != null) {
            VALUES("MADE_COM_CD", "#{madeComCd,jdbcType=VARCHAR}");
        }

        if (record.getMacModel() != null) {
            VALUES("MAC_MODEL", "#{macModel,jdbcType=VARCHAR}");
        }

        if (record.getMacRentYn() != null) {
            VALUES("MAC_RENT_YN", "#{macRentYn,jdbcType=VARCHAR}");
        }

        if (record.getMacSetType() != null) {
            VALUES("MAC_SET_TYPE", "#{macSetType,jdbcType=VARCHAR}");
        }

        if (record.getMacFee() != null) {
            VALUES("MAC_FEE", "#{macFee,jdbcType=VARCHAR}");
        }

        if (record.getGwIp() != null) {
            VALUES("GW_IP", "#{gwIp,jdbcType=VARCHAR}");
        }

        if (record.getMacIp() != null) {
            VALUES("MAC_IP", "#{macIp,jdbcType=VARCHAR}");
        }

        if (record.getSubnetMask() != null) {
            VALUES("SUBNET_MASK", "#{subnetMask,jdbcType=VARCHAR}");
        }

        if (record.getExpectDate() != null) {
            VALUES("EXPECT_DATE", "#{expectDate,jdbcType=VARCHAR}");
        }

        if (record.getExpectTime() != null) {
            VALUES("EXPECT_TIME", "#{expectTime,jdbcType=VARCHAR}");
        }

        if (record.getCloseOrgBranchCd() != null) {
            VALUES("CLOSE_ORG_BRANCH_CD", "#{closeOrgBranchCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseOrgSiteCd() != null) {
            VALUES("CLOSE_ORG_SITE_CD", "#{closeOrgSiteCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseMacNo() != null) {
            VALUES("CLOSE_MAC_NO", "#{closeMacNo,jdbcType=VARCHAR}");
        }

        if (record.getCloseMadeComCd() != null) {
            VALUES("CLOSE_MADE_COM_CD", "#{closeMadeComCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseMacModel() != null) {
            VALUES("CLOSE_MAC_MODEL", "#{closeMacModel,jdbcType=VARCHAR}");
        }

        if (record.getCloseMacRentYn() != null) {
            VALUES("CLOSE_MAC_RENT_YN", "#{closeMacRentYn,jdbcType=VARCHAR}");
        }

        if (record.getWorkResult() != null) {
            VALUES("WORK_RESULT", "#{workResult,jdbcType=VARCHAR}");
        }

        if (record.getWorkEndDate() != null) {
            VALUES("WORK_END_DATE", "#{workEndDate,jdbcType=VARCHAR}");
        }

        if (record.getWorkMsg() != null) {
            VALUES("WORK_MSG", "#{workMsg,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateUid() != null) {
            VALUES("UPDATE_UID", "#{updateUid,jdbcType=VARCHAR}");
        }

        if (record.getDataType() != null) {
            VALUES("DATA_TYPE", "#{dataType,jdbcType=VARCHAR}");
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public String selectBySpec(TCmSetMacInfoSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("ORG_CD");
        } else {
            SELECT("ORG_CD");
        }
        SELECT("WORK_SEQ");
        SELECT("WORK_CLASS");
        SELECT("MAC_SEQ_NO");
        SELECT("CLOSE_TYPE");
        SELECT("CHANGE_NO");
        SELECT("ORG_BRANCH_CD");
        SELECT("ORG_SITE_CD");
        SELECT("MAC_NO");
        SELECT("MADE_COM_CD");
        SELECT("MAC_MODEL");
        SELECT("MAC_RENT_YN");
        SELECT("MAC_SET_TYPE");
        SELECT("MAC_FEE");
        SELECT("GW_IP");
        SELECT("MAC_IP");
        SELECT("SUBNET_MASK");
        SELECT("EXPECT_DATE");
        SELECT("EXPECT_TIME");
        SELECT("CLOSE_ORG_BRANCH_CD");
        SELECT("CLOSE_ORG_SITE_CD");
        SELECT("CLOSE_MAC_NO");
        SELECT("CLOSE_MADE_COM_CD");
        SELECT("CLOSE_MAC_MODEL");
        SELECT("CLOSE_MAC_RENT_YN");
        SELECT("WORK_RESULT");
        SELECT("WORK_END_DATE");
        SELECT("WORK_MSG");
        SELECT("UPDATE_DATE");
        SELECT("UPDATE_UID");
        SELECT("DATA_TYPE");
        FROM("OP.T_CM_SET_MAC_INFO");
        applyWhere(spec, false);

        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TCmSetMacInfo record = (TCmSetMacInfo) parameter.get("record");
        TCmSetMacInfoSpec spec = (TCmSetMacInfoSpec) parameter.get("spec");

        BEGIN();
        UPDATE("OP.T_CM_SET_MAC_INFO");

        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }

        if (record.getWorkSeq() != null) {
            SET("WORK_SEQ = #{record.workSeq,jdbcType=VARCHAR}");
        }

        if (record.getWorkClass() != null) {
            SET("WORK_CLASS = #{record.workClass,jdbcType=VARCHAR}");
        }

        if (record.getMacSeqNo() != null) {
            SET("MAC_SEQ_NO = #{record.macSeqNo,jdbcType=VARCHAR}");
        }

        if (record.getCloseType() != null) {
            SET("CLOSE_TYPE = #{record.closeType,jdbcType=VARCHAR}");
        }

        if (record.getChangeNo() != null) {
            SET("CHANGE_NO = #{record.changeNo,jdbcType=DECIMAL}");
        }

        if (record.getOrgBranchCd() != null) {
            SET("ORG_BRANCH_CD = #{record.orgBranchCd,jdbcType=VARCHAR}");
        }

        if (record.getOrgSiteCd() != null) {
            SET("ORG_SITE_CD = #{record.orgSiteCd,jdbcType=VARCHAR}");
        }

        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }

        if (record.getMadeComCd() != null) {
            SET("MADE_COM_CD = #{record.madeComCd,jdbcType=VARCHAR}");
        }

        if (record.getMacModel() != null) {
            SET("MAC_MODEL = #{record.macModel,jdbcType=VARCHAR}");
        }

        if (record.getMacRentYn() != null) {
            SET("MAC_RENT_YN = #{record.macRentYn,jdbcType=VARCHAR}");
        }

        if (record.getMacSetType() != null) {
            SET("MAC_SET_TYPE = #{record.macSetType,jdbcType=VARCHAR}");
        }

        if (record.getMacFee() != null) {
            SET("MAC_FEE = #{record.macFee,jdbcType=VARCHAR}");
        }

        if (record.getGwIp() != null) {
            SET("GW_IP = #{record.gwIp,jdbcType=VARCHAR}");
        }

        if (record.getMacIp() != null) {
            SET("MAC_IP = #{record.macIp,jdbcType=VARCHAR}");
        }

        if (record.getSubnetMask() != null) {
            SET("SUBNET_MASK = #{record.subnetMask,jdbcType=VARCHAR}");
        }

        if (record.getExpectDate() != null) {
            SET("EXPECT_DATE = #{record.expectDate,jdbcType=VARCHAR}");
        }

        if (record.getExpectTime() != null) {
            SET("EXPECT_TIME = #{record.expectTime,jdbcType=VARCHAR}");
        }

        if (record.getCloseOrgBranchCd() != null) {
            SET("CLOSE_ORG_BRANCH_CD = #{record.closeOrgBranchCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseOrgSiteCd() != null) {
            SET("CLOSE_ORG_SITE_CD = #{record.closeOrgSiteCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseMacNo() != null) {
            SET("CLOSE_MAC_NO = #{record.closeMacNo,jdbcType=VARCHAR}");
        }

        if (record.getCloseMadeComCd() != null) {
            SET("CLOSE_MADE_COM_CD = #{record.closeMadeComCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseMacModel() != null) {
            SET("CLOSE_MAC_MODEL = #{record.closeMacModel,jdbcType=VARCHAR}");
        }

        if (record.getCloseMacRentYn() != null) {
            SET("CLOSE_MAC_RENT_YN = #{record.closeMacRentYn,jdbcType=VARCHAR}");
        }

        if (record.getWorkResult() != null) {
            SET("WORK_RESULT = #{record.workResult,jdbcType=VARCHAR}");
        }

        if (record.getWorkEndDate() != null) {
            SET("WORK_END_DATE = #{record.workEndDate,jdbcType=VARCHAR}");
        }

        if (record.getWorkMsg() != null) {
            SET("WORK_MSG = #{record.workMsg,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        }

        if (record.getDataType() != null) {
            SET("DATA_TYPE = #{record.dataType,jdbcType=VARCHAR}");
        }

        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_CM_SET_MAC_INFO");

        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("WORK_SEQ = #{record.workSeq,jdbcType=VARCHAR}");
        SET("WORK_CLASS = #{record.workClass,jdbcType=VARCHAR}");
        SET("MAC_SEQ_NO = #{record.macSeqNo,jdbcType=VARCHAR}");
        SET("CLOSE_TYPE = #{record.closeType,jdbcType=VARCHAR}");
        SET("CHANGE_NO = #{record.changeNo,jdbcType=DECIMAL}");
        SET("ORG_BRANCH_CD = #{record.orgBranchCd,jdbcType=VARCHAR}");
        SET("ORG_SITE_CD = #{record.orgSiteCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("MADE_COM_CD = #{record.madeComCd,jdbcType=VARCHAR}");
        SET("MAC_MODEL = #{record.macModel,jdbcType=VARCHAR}");
        SET("MAC_RENT_YN = #{record.macRentYn,jdbcType=VARCHAR}");
        SET("MAC_SET_TYPE = #{record.macSetType,jdbcType=VARCHAR}");
        SET("MAC_FEE = #{record.macFee,jdbcType=VARCHAR}");
        SET("GW_IP = #{record.gwIp,jdbcType=VARCHAR}");
        SET("MAC_IP = #{record.macIp,jdbcType=VARCHAR}");
        SET("SUBNET_MASK = #{record.subnetMask,jdbcType=VARCHAR}");
        SET("EXPECT_DATE = #{record.expectDate,jdbcType=VARCHAR}");
        SET("EXPECT_TIME = #{record.expectTime,jdbcType=VARCHAR}");
        SET("CLOSE_ORG_BRANCH_CD = #{record.closeOrgBranchCd,jdbcType=VARCHAR}");
        SET("CLOSE_ORG_SITE_CD = #{record.closeOrgSiteCd,jdbcType=VARCHAR}");
        SET("CLOSE_MAC_NO = #{record.closeMacNo,jdbcType=VARCHAR}");
        SET("CLOSE_MADE_COM_CD = #{record.closeMadeComCd,jdbcType=VARCHAR}");
        SET("CLOSE_MAC_MODEL = #{record.closeMacModel,jdbcType=VARCHAR}");
        SET("CLOSE_MAC_RENT_YN = #{record.closeMacRentYn,jdbcType=VARCHAR}");
        SET("WORK_RESULT = #{record.workResult,jdbcType=VARCHAR}");
        SET("WORK_END_DATE = #{record.workEndDate,jdbcType=VARCHAR}");
        SET("WORK_MSG = #{record.workMsg,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("DATA_TYPE = #{record.dataType,jdbcType=VARCHAR}");

        TCmSetMacInfoSpec spec = (TCmSetMacInfoSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    public String updateByPrimaryKeySelective(TCmSetMacInfo record) {
        BEGIN();
        UPDATE("OP.T_CM_SET_MAC_INFO");

        if (record.getCloseType() != null) {
            SET("CLOSE_TYPE = #{closeType,jdbcType=VARCHAR}");
        }

        if (record.getChangeNo() != null) {
            SET("CHANGE_NO = #{changeNo,jdbcType=DECIMAL}");
        }

        if (record.getOrgBranchCd() != null) {
            SET("ORG_BRANCH_CD = #{orgBranchCd,jdbcType=VARCHAR}");
        }

        if (record.getOrgSiteCd() != null) {
            SET("ORG_SITE_CD = #{orgSiteCd,jdbcType=VARCHAR}");
        }

        if (record.getMacNo() != null) {
            SET("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        }

        if (record.getMadeComCd() != null) {
            SET("MADE_COM_CD = #{madeComCd,jdbcType=VARCHAR}");
        }

        if (record.getMacModel() != null) {
            SET("MAC_MODEL = #{macModel,jdbcType=VARCHAR}");
        }

        if (record.getMacRentYn() != null) {
            SET("MAC_RENT_YN = #{macRentYn,jdbcType=VARCHAR}");
        }

        if (record.getMacSetType() != null) {
            SET("MAC_SET_TYPE = #{macSetType,jdbcType=VARCHAR}");
        }

        if (record.getMacFee() != null) {
            SET("MAC_FEE = #{macFee,jdbcType=VARCHAR}");
        }

        if (record.getGwIp() != null) {
            SET("GW_IP = #{gwIp,jdbcType=VARCHAR}");
        }

        if (record.getMacIp() != null) {
            SET("MAC_IP = #{macIp,jdbcType=VARCHAR}");
        }

        if (record.getSubnetMask() != null) {
            SET("SUBNET_MASK = #{subnetMask,jdbcType=VARCHAR}");
        }

        if (record.getExpectDate() != null) {
            SET("EXPECT_DATE = #{expectDate,jdbcType=VARCHAR}");
        }

        if (record.getExpectTime() != null) {
            SET("EXPECT_TIME = #{expectTime,jdbcType=VARCHAR}");
        }

        if (record.getCloseOrgBranchCd() != null) {
            SET("CLOSE_ORG_BRANCH_CD = #{closeOrgBranchCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseOrgSiteCd() != null) {
            SET("CLOSE_ORG_SITE_CD = #{closeOrgSiteCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseMacNo() != null) {
            SET("CLOSE_MAC_NO = #{closeMacNo,jdbcType=VARCHAR}");
        }

        if (record.getCloseMadeComCd() != null) {
            SET("CLOSE_MADE_COM_CD = #{closeMadeComCd,jdbcType=VARCHAR}");
        }

        if (record.getCloseMacModel() != null) {
            SET("CLOSE_MAC_MODEL = #{closeMacModel,jdbcType=VARCHAR}");
        }

        if (record.getCloseMacRentYn() != null) {
            SET("CLOSE_MAC_RENT_YN = #{closeMacRentYn,jdbcType=VARCHAR}");
        }

        if (record.getWorkResult() != null) {
            SET("WORK_RESULT = #{workResult,jdbcType=VARCHAR}");
        }

        if (record.getWorkEndDate() != null) {
            SET("WORK_END_DATE = #{workEndDate,jdbcType=VARCHAR}");
        }

        if (record.getWorkMsg() != null) {
            SET("WORK_MSG = #{workMsg,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{updateUid,jdbcType=VARCHAR}");
        }

        if (record.getDataType() != null) {
            SET("DATA_TYPE = #{dataType,jdbcType=VARCHAR}");
        }

        WHERE("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        WHERE("WORK_SEQ = #{workSeq,jdbcType=VARCHAR}");
        WHERE("WORK_CLASS = #{workClass,jdbcType=VARCHAR}");
        WHERE("MAC_SEQ_NO = #{macSeqNo,jdbcType=VARCHAR}");

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    protected void applyWhere(TCmSetMacInfoSpec spec, boolean includeSpecPhrase) {
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
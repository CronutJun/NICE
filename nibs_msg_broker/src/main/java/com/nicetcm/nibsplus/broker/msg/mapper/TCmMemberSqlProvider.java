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

import com.nicetcm.nibsplus.broker.msg.model.TCmMember;
import com.nicetcm.nibsplus.broker.msg.model.TCmMemberSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TCmMemberSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TCmMemberSpec;
import java.util.List;
import java.util.Map;

public class TCmMemberSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_MEMBER
     *
     * @mbggenerated Tue Jul 15 14:49:16 KST 2014
     */
    public String countBySpec(TCmMemberSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_CM_MEMBER");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_MEMBER
     *
     * @mbggenerated Tue Jul 15 14:49:16 KST 2014
     */
    public String deleteBySpec(TCmMemberSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_CM_MEMBER");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_MEMBER
     *
     * @mbggenerated Tue Jul 15 14:49:16 KST 2014
     */
    public String insertSelective(TCmMember record) {
        BEGIN();
        INSERT_INTO("OP.T_CM_MEMBER");
        
        if (record.getMemberId() != null) {
            VALUES("MEMBER_ID", "#{memberId,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberNm() != null) {
            VALUES("MEMBER_NM", "#{memberNm,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberType() != null) {
            VALUES("MEMBER_TYPE", "#{memberType,jdbcType=VARCHAR}");
        }
        
        if (record.getPassWord() != null) {
            VALUES("PASS_WORD", "#{passWord,jdbcType=VARCHAR}");
        }
        
        if (record.getInTelNo() != null) {
            VALUES("IN_TEL_NO", "#{inTelNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCtiId() != null) {
            VALUES("CTI_ID", "#{ctiId,jdbcType=VARCHAR}");
        }
        
        if (record.getNiceHp() != null) {
            VALUES("NICE_HP", "#{niceHp,jdbcType=VARCHAR}");
        }
        
        if (record.getPriHp() != null) {
            VALUES("PRI_HP", "#{priHp,jdbcType=VARCHAR}");
        }
        
        if (record.getTeleNo() != null) {
            VALUES("TELE_NO", "#{teleNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAddr() != null) {
            VALUES("ADDR", "#{addr,jdbcType=VARCHAR}");
        }
        
        if (record.geteMail() != null) {
            VALUES("E_MAIL", "#{eMail,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkType() != null) {
            VALUES("WORK_TYPE", "#{workType,jdbcType=VARCHAR}");
        }
        
        if (record.getRetireYn() != null) {
            VALUES("RETIRE_YN", "#{retireYn,jdbcType=VARCHAR}");
        }
        
        if (record.getResidentNo() != null) {
            VALUES("RESIDENT_NO", "#{residentNo,jdbcType=VARCHAR}");
        }
        
        if (record.getLicenceNo() != null) {
            VALUES("LICENCE_NO", "#{licenceNo,jdbcType=VARCHAR}");
        }
        
        if (record.getEnterDate() != null) {
            VALUES("ENTER_DATE", "#{enterDate,jdbcType=VARCHAR}");
        }
        
        if (record.getRetireDate() != null) {
            VALUES("RETIRE_DATE", "#{retireDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCtRight() != null) {
            VALUES("CT_RIGHT", "#{ctRight,jdbcType=VARCHAR}");
        }
        
        if (record.getFnRight() != null) {
            VALUES("FN_RIGHT", "#{fnRight,jdbcType=VARCHAR}");
        }
        
        if (record.getDeptCd() != null) {
            VALUES("DEPT_CD", "#{deptCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeCd() != null) {
            VALUES("OFFICE_CD", "#{officeCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTeamCd() != null) {
            VALUES("TEAM_CD", "#{teamCd,jdbcType=VARCHAR}");
        }
        
        if (record.getDegreeNo() != null) {
            VALUES("DEGREE_NO", "#{degreeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getMisOrgCd() != null) {
            VALUES("MIS_ORG_CD", "#{misOrgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMisOrgNm() != null) {
            VALUES("MIS_ORG_NM", "#{misOrgNm,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            VALUES("UPDATE_UID", "#{updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getNicememType() != null) {
            VALUES("NICEMEM_TYPE", "#{nicememType,jdbcType=VARCHAR}");
        }
        
        if (record.getUseType() != null) {
            VALUES("USE_TYPE", "#{useType,jdbcType=VARCHAR}");
        }
        
        if (record.getPwdFailCnt() != null) {
            VALUES("PWD_FAIL_CNT", "#{pwdFailCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getPwdChDate() != null) {
            VALUES("PWD_CH_DATE", "#{pwdChDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMisRight() != null) {
            VALUES("MIS_RIGHT", "#{misRight,jdbcType=VARCHAR}");
        }
        
        if (record.getSysType() != null) {
            VALUES("SYS_TYPE", "#{sysType,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_MEMBER
     *
     * @mbggenerated Tue Jul 15 14:49:16 KST 2014
     */
    public String selectBySpec(TCmMemberSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("MEMBER_ID");
        } else {
            SELECT("MEMBER_ID");
        }
        SELECT("MEMBER_NM");
        SELECT("MEMBER_TYPE");
        SELECT("PASS_WORD");
        SELECT("IN_TEL_NO");
        SELECT("CTI_ID");
        SELECT("NICE_HP");
        SELECT("PRI_HP");
        SELECT("TELE_NO");
        SELECT("ADDR");
        SELECT("E_MAIL");
        SELECT("WORK_TYPE");
        SELECT("RETIRE_YN");
        SELECT("RESIDENT_NO");
        SELECT("LICENCE_NO");
        SELECT("ENTER_DATE");
        SELECT("RETIRE_DATE");
        SELECT("CT_RIGHT");
        SELECT("FN_RIGHT");
        SELECT("DEPT_CD");
        SELECT("OFFICE_CD");
        SELECT("TEAM_CD");
        SELECT("DEGREE_NO");
        SELECT("MIS_ORG_CD");
        SELECT("MIS_ORG_NM");
        SELECT("UPDATE_DATE");
        SELECT("UPDATE_UID");
        SELECT("NICEMEM_TYPE");
        SELECT("USE_TYPE");
        SELECT("PWD_FAIL_CNT");
        SELECT("PWD_CH_DATE");
        SELECT("MIS_RIGHT");
        SELECT("SYS_TYPE");
        FROM("OP.T_CM_MEMBER");
        applyWhere(spec, false);
        
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_MEMBER
     *
     * @mbggenerated Tue Jul 15 14:49:16 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TCmMember record = (TCmMember) parameter.get("record");
        TCmMemberSpec spec = (TCmMemberSpec) parameter.get("spec");
        
        BEGIN();
        UPDATE("OP.T_CM_MEMBER");
        
        if (record.getMemberId() != null) {
            SET("MEMBER_ID = #{record.memberId,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberNm() != null) {
            SET("MEMBER_NM = #{record.memberNm,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberType() != null) {
            SET("MEMBER_TYPE = #{record.memberType,jdbcType=VARCHAR}");
        }
        
        if (record.getPassWord() != null) {
            SET("PASS_WORD = #{record.passWord,jdbcType=VARCHAR}");
        }
        
        if (record.getInTelNo() != null) {
            SET("IN_TEL_NO = #{record.inTelNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCtiId() != null) {
            SET("CTI_ID = #{record.ctiId,jdbcType=VARCHAR}");
        }
        
        if (record.getNiceHp() != null) {
            SET("NICE_HP = #{record.niceHp,jdbcType=VARCHAR}");
        }
        
        if (record.getPriHp() != null) {
            SET("PRI_HP = #{record.priHp,jdbcType=VARCHAR}");
        }
        
        if (record.getTeleNo() != null) {
            SET("TELE_NO = #{record.teleNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAddr() != null) {
            SET("ADDR = #{record.addr,jdbcType=VARCHAR}");
        }
        
        if (record.geteMail() != null) {
            SET("E_MAIL = #{record.eMail,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkType() != null) {
            SET("WORK_TYPE = #{record.workType,jdbcType=VARCHAR}");
        }
        
        if (record.getRetireYn() != null) {
            SET("RETIRE_YN = #{record.retireYn,jdbcType=VARCHAR}");
        }
        
        if (record.getResidentNo() != null) {
            SET("RESIDENT_NO = #{record.residentNo,jdbcType=VARCHAR}");
        }
        
        if (record.getLicenceNo() != null) {
            SET("LICENCE_NO = #{record.licenceNo,jdbcType=VARCHAR}");
        }
        
        if (record.getEnterDate() != null) {
            SET("ENTER_DATE = #{record.enterDate,jdbcType=VARCHAR}");
        }
        
        if (record.getRetireDate() != null) {
            SET("RETIRE_DATE = #{record.retireDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCtRight() != null) {
            SET("CT_RIGHT = #{record.ctRight,jdbcType=VARCHAR}");
        }
        
        if (record.getFnRight() != null) {
            SET("FN_RIGHT = #{record.fnRight,jdbcType=VARCHAR}");
        }
        
        if (record.getDeptCd() != null) {
            SET("DEPT_CD = #{record.deptCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeCd() != null) {
            SET("OFFICE_CD = #{record.officeCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTeamCd() != null) {
            SET("TEAM_CD = #{record.teamCd,jdbcType=VARCHAR}");
        }
        
        if (record.getDegreeNo() != null) {
            SET("DEGREE_NO = #{record.degreeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getMisOrgCd() != null) {
            SET("MIS_ORG_CD = #{record.misOrgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMisOrgNm() != null) {
            SET("MIS_ORG_NM = #{record.misOrgNm,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getNicememType() != null) {
            SET("NICEMEM_TYPE = #{record.nicememType,jdbcType=VARCHAR}");
        }
        
        if (record.getUseType() != null) {
            SET("USE_TYPE = #{record.useType,jdbcType=VARCHAR}");
        }
        
        if (record.getPwdFailCnt() != null) {
            SET("PWD_FAIL_CNT = #{record.pwdFailCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getPwdChDate() != null) {
            SET("PWD_CH_DATE = #{record.pwdChDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMisRight() != null) {
            SET("MIS_RIGHT = #{record.misRight,jdbcType=VARCHAR}");
        }
        
        if (record.getSysType() != null) {
            SET("SYS_TYPE = #{record.sysType,jdbcType=VARCHAR}");
        }
        
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_MEMBER
     *
     * @mbggenerated Tue Jul 15 14:49:16 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_CM_MEMBER");
        
        SET("MEMBER_ID = #{record.memberId,jdbcType=VARCHAR}");
        SET("MEMBER_NM = #{record.memberNm,jdbcType=VARCHAR}");
        SET("MEMBER_TYPE = #{record.memberType,jdbcType=VARCHAR}");
        SET("PASS_WORD = #{record.passWord,jdbcType=VARCHAR}");
        SET("IN_TEL_NO = #{record.inTelNo,jdbcType=VARCHAR}");
        SET("CTI_ID = #{record.ctiId,jdbcType=VARCHAR}");
        SET("NICE_HP = #{record.niceHp,jdbcType=VARCHAR}");
        SET("PRI_HP = #{record.priHp,jdbcType=VARCHAR}");
        SET("TELE_NO = #{record.teleNo,jdbcType=VARCHAR}");
        SET("ADDR = #{record.addr,jdbcType=VARCHAR}");
        SET("E_MAIL = #{record.eMail,jdbcType=VARCHAR}");
        SET("WORK_TYPE = #{record.workType,jdbcType=VARCHAR}");
        SET("RETIRE_YN = #{record.retireYn,jdbcType=VARCHAR}");
        SET("RESIDENT_NO = #{record.residentNo,jdbcType=VARCHAR}");
        SET("LICENCE_NO = #{record.licenceNo,jdbcType=VARCHAR}");
        SET("ENTER_DATE = #{record.enterDate,jdbcType=VARCHAR}");
        SET("RETIRE_DATE = #{record.retireDate,jdbcType=VARCHAR}");
        SET("CT_RIGHT = #{record.ctRight,jdbcType=VARCHAR}");
        SET("FN_RIGHT = #{record.fnRight,jdbcType=VARCHAR}");
        SET("DEPT_CD = #{record.deptCd,jdbcType=VARCHAR}");
        SET("OFFICE_CD = #{record.officeCd,jdbcType=VARCHAR}");
        SET("TEAM_CD = #{record.teamCd,jdbcType=VARCHAR}");
        SET("DEGREE_NO = #{record.degreeNo,jdbcType=VARCHAR}");
        SET("MIS_ORG_CD = #{record.misOrgCd,jdbcType=VARCHAR}");
        SET("MIS_ORG_NM = #{record.misOrgNm,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("NICEMEM_TYPE = #{record.nicememType,jdbcType=VARCHAR}");
        SET("USE_TYPE = #{record.useType,jdbcType=VARCHAR}");
        SET("PWD_FAIL_CNT = #{record.pwdFailCnt,jdbcType=DECIMAL}");
        SET("PWD_CH_DATE = #{record.pwdChDate,jdbcType=TIMESTAMP}");
        SET("MIS_RIGHT = #{record.misRight,jdbcType=VARCHAR}");
        SET("SYS_TYPE = #{record.sysType,jdbcType=VARCHAR}");
        
        TCmMemberSpec spec = (TCmMemberSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_MEMBER
     *
     * @mbggenerated Tue Jul 15 14:49:16 KST 2014
     */
    public String updateByPrimaryKeySelective(TCmMember record) {
        BEGIN();
        UPDATE("OP.T_CM_MEMBER");
        
        if (record.getMemberNm() != null) {
            SET("MEMBER_NM = #{memberNm,jdbcType=VARCHAR}");
        }
        
        if (record.getMemberType() != null) {
            SET("MEMBER_TYPE = #{memberType,jdbcType=VARCHAR}");
        }
        
        if (record.getPassWord() != null) {
            SET("PASS_WORD = #{passWord,jdbcType=VARCHAR}");
        }
        
        if (record.getInTelNo() != null) {
            SET("IN_TEL_NO = #{inTelNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCtiId() != null) {
            SET("CTI_ID = #{ctiId,jdbcType=VARCHAR}");
        }
        
        if (record.getNiceHp() != null) {
            SET("NICE_HP = #{niceHp,jdbcType=VARCHAR}");
        }
        
        if (record.getPriHp() != null) {
            SET("PRI_HP = #{priHp,jdbcType=VARCHAR}");
        }
        
        if (record.getTeleNo() != null) {
            SET("TELE_NO = #{teleNo,jdbcType=VARCHAR}");
        }
        
        if (record.getAddr() != null) {
            SET("ADDR = #{addr,jdbcType=VARCHAR}");
        }
        
        if (record.geteMail() != null) {
            SET("E_MAIL = #{eMail,jdbcType=VARCHAR}");
        }
        
        if (record.getWorkType() != null) {
            SET("WORK_TYPE = #{workType,jdbcType=VARCHAR}");
        }
        
        if (record.getRetireYn() != null) {
            SET("RETIRE_YN = #{retireYn,jdbcType=VARCHAR}");
        }
        
        if (record.getResidentNo() != null) {
            SET("RESIDENT_NO = #{residentNo,jdbcType=VARCHAR}");
        }
        
        if (record.getLicenceNo() != null) {
            SET("LICENCE_NO = #{licenceNo,jdbcType=VARCHAR}");
        }
        
        if (record.getEnterDate() != null) {
            SET("ENTER_DATE = #{enterDate,jdbcType=VARCHAR}");
        }
        
        if (record.getRetireDate() != null) {
            SET("RETIRE_DATE = #{retireDate,jdbcType=VARCHAR}");
        }
        
        if (record.getCtRight() != null) {
            SET("CT_RIGHT = #{ctRight,jdbcType=VARCHAR}");
        }
        
        if (record.getFnRight() != null) {
            SET("FN_RIGHT = #{fnRight,jdbcType=VARCHAR}");
        }
        
        if (record.getDeptCd() != null) {
            SET("DEPT_CD = #{deptCd,jdbcType=VARCHAR}");
        }
        
        if (record.getOfficeCd() != null) {
            SET("OFFICE_CD = #{officeCd,jdbcType=VARCHAR}");
        }
        
        if (record.getTeamCd() != null) {
            SET("TEAM_CD = #{teamCd,jdbcType=VARCHAR}");
        }
        
        if (record.getDegreeNo() != null) {
            SET("DEGREE_NO = #{degreeNo,jdbcType=VARCHAR}");
        }
        
        if (record.getMisOrgCd() != null) {
            SET("MIS_ORG_CD = #{misOrgCd,jdbcType=VARCHAR}");
        }
        
        if (record.getMisOrgNm() != null) {
            SET("MIS_ORG_NM = #{misOrgNm,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{updateUid,jdbcType=VARCHAR}");
        }
        
        if (record.getNicememType() != null) {
            SET("NICEMEM_TYPE = #{nicememType,jdbcType=VARCHAR}");
        }
        
        if (record.getUseType() != null) {
            SET("USE_TYPE = #{useType,jdbcType=VARCHAR}");
        }
        
        if (record.getPwdFailCnt() != null) {
            SET("PWD_FAIL_CNT = #{pwdFailCnt,jdbcType=DECIMAL}");
        }
        
        if (record.getPwdChDate() != null) {
            SET("PWD_CH_DATE = #{pwdChDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getMisRight() != null) {
            SET("MIS_RIGHT = #{misRight,jdbcType=VARCHAR}");
        }
        
        if (record.getSysType() != null) {
            SET("SYS_TYPE = #{sysType,jdbcType=VARCHAR}");
        }
        
        WHERE("MEMBER_ID = #{memberId,jdbcType=VARCHAR}");
        
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_MEMBER
     *
     * @mbggenerated Tue Jul 15 14:49:16 KST 2014
     */
    protected void applyWhere(TCmMemberSpec spec, boolean includeSpecPhrase) {
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
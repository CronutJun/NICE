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

import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngGuard;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngGuardSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngGuardSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngGuardSpec;
import java.util.List;
import java.util.Map;

public class TCtErrorMngGuardSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_MNG_GUARD
     *
     * @mbggenerated Wed Nov 26 19:29:51 KST 2014
     */
    public String countBySpec(TCtErrorMngGuardSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_CT_ERROR_MNG_GUARD");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_MNG_GUARD
     *
     * @mbggenerated Wed Nov 26 19:29:51 KST 2014
     */
    public String deleteBySpec(TCtErrorMngGuardSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_CT_ERROR_MNG_GUARD");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_MNG_GUARD
     *
     * @mbggenerated Wed Nov 26 19:29:51 KST 2014
     */
    public String insertSelective(TCtErrorMngGuard record) {
        BEGIN();
        INSERT_INTO("OP.T_CT_ERROR_MNG_GUARD");

        if (record.getErrorNo() != null) {
            VALUES("ERROR_NO", "#{errorNo,jdbcType=VARCHAR}");
        }

        if (record.getCreateDate() != null) {
            VALUES("CREATE_DATE", "#{createDate,jdbcType=VARCHAR}");
        }

        if (record.getCreateTime() != null) {
            VALUES("CREATE_TIME", "#{createTime,jdbcType=VARCHAR}");
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

        if (record.getErrorCd() != null) {
            VALUES("ERROR_CD", "#{errorCd,jdbcType=VARCHAR}");
        }

        if (record.getAcceptDate() != null) {
            VALUES("ACCEPT_DATE", "#{acceptDate,jdbcType=VARCHAR}");
        }

        if (record.getAcceptTime() != null) {
            VALUES("ACCEPT_TIME", "#{acceptTime,jdbcType=VARCHAR}");
        }

        if (record.getOrgMsg() != null) {
            VALUES("ORG_MSG", "#{orgMsg,jdbcType=VARCHAR}");
        }

        if (record.getRepairDate() != null) {
            VALUES("REPAIR_DATE", "#{repairDate,jdbcType=VARCHAR}");
        }

        if (record.getRepairTime() != null) {
            VALUES("REPAIR_TIME", "#{repairTime,jdbcType=VARCHAR}");
        }

        if (record.getFinishType() != null) {
            VALUES("FINISH_TYPE", "#{finishType,jdbcType=VARCHAR}");
        }

        if (record.getArrivalEstDate() != null) {
            VALUES("ARRIVAL_EST_DATE", "#{arrivalEstDate,jdbcType=VARCHAR}");
        }

        if (record.getArrivalEstTime() != null) {
            VALUES("ARRIVAL_EST_TIME", "#{arrivalEstTime,jdbcType=VARCHAR}");
        }

        if (record.getRecvUserNm() != null) {
            VALUES("RECV_USER_NM", "#{recvUserNm,jdbcType=VARCHAR}");
        }

        if (record.getRecvTeleNo() != null) {
            VALUES("RECV_TELE_NO", "#{recvTeleNo,jdbcType=VARCHAR}");
        }

        if (record.getRecvDate() != null) {
            VALUES("RECV_DATE", "#{recvDate,jdbcType=VARCHAR}");
        }

        if (record.getRecvTime() != null) {
            VALUES("RECV_TIME", "#{recvTime,jdbcType=VARCHAR}");
        }

        if (record.getArrivalDate() != null) {
            VALUES("ARRIVAL_DATE", "#{arrivalDate,jdbcType=VARCHAR}");
        }

        if (record.getArrivalTime() != null) {
            VALUES("ARRIVAL_TIME", "#{arrivalTime,jdbcType=VARCHAR}");
        }

        if (record.getArrivalNm() != null) {
            VALUES("ARRIVAL_NM", "#{arrivalNm,jdbcType=VARCHAR}");
        }

        if (record.getArrivalTeleNo() != null) {
            VALUES("ARRIVAL_TELE_NO", "#{arrivalTeleNo,jdbcType=VARCHAR}");
        }

        if (record.getFinishDate() != null) {
            VALUES("FINISH_DATE", "#{finishDate,jdbcType=VARCHAR}");
        }

        if (record.getFinishTime() != null) {
            VALUES("FINISH_TIME", "#{finishTime,jdbcType=VARCHAR}");
        }

        if (record.getFinishNm() != null) {
            VALUES("FINISH_NM", "#{finishNm,jdbcType=VARCHAR}");
        }

        if (record.getFinishTeleNo() != null) {
            VALUES("FINISH_TELE_NO", "#{finishTeleNo,jdbcType=VARCHAR}");
        }

        if (record.getSec() != null) {
            VALUES("SEC", "#{sec,jdbcType=VARCHAR}");
        }

        if (record.getErrorStatus() != null) {
            VALUES("ERROR_STATUS", "#{errorStatus,jdbcType=VARCHAR}");
        }

        if (record.getAsMedium() != null) {
            VALUES("AS_MEDIUM", "#{asMedium,jdbcType=VARCHAR}");
        }

        if (record.getGuardSendYn() != null) {
            VALUES("GUARD_SEND_YN", "#{guardSendYn,jdbcType=VARCHAR}");
        }

        if (record.getRegDt() != null) {
            VALUES("REG_DT", "#{regDt,jdbcType=TIMESTAMP}");
        }

        if (record.getRegId() != null) {
            VALUES("REG_ID", "#{regId,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateUid() != null) {
            VALUES("UPDATE_UID", "#{updateUid,jdbcType=VARCHAR}");
        }

        if (record.getRecvPlace() != null) {
            VALUES("RECV_PLACE", "#{recvPlace,jdbcType=VARCHAR}");
        }

        if (record.getMsg() != null) {
            VALUES("MSG", "#{msg,jdbcType=VARCHAR}");
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_MNG_GUARD
     *
     * @mbggenerated Wed Nov 26 19:29:51 KST 2014
     */
    public String selectBySpec(TCtErrorMngGuardSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("ERROR_NO");
        } else {
            SELECT("ERROR_NO");
        }
        SELECT("CREATE_DATE");
        SELECT("CREATE_TIME");
        SELECT("ORG_CD");
        SELECT("BRANCH_CD");
        SELECT("MAC_NO");
        SELECT("SITE_CD");
        SELECT("ERROR_CD");
        SELECT("ACCEPT_DATE");
        SELECT("ACCEPT_TIME");
        SELECT("ORG_MSG");
        SELECT("REPAIR_DATE");
        SELECT("REPAIR_TIME");
        SELECT("FINISH_TYPE");
        SELECT("ARRIVAL_EST_DATE");
        SELECT("ARRIVAL_EST_TIME");
        SELECT("RECV_USER_NM");
        SELECT("RECV_TELE_NO");
        SELECT("RECV_DATE");
        SELECT("RECV_TIME");
        SELECT("ARRIVAL_DATE");
        SELECT("ARRIVAL_TIME");
        SELECT("ARRIVAL_NM");
        SELECT("ARRIVAL_TELE_NO");
        SELECT("FINISH_DATE");
        SELECT("FINISH_TIME");
        SELECT("FINISH_NM");
        SELECT("FINISH_TELE_NO");
        SELECT("SEC");
        SELECT("ERROR_STATUS");
        SELECT("AS_MEDIUM");
        SELECT("GUARD_SEND_YN");
        SELECT("REG_DT");
        SELECT("REG_ID");
        SELECT("UPDATE_DATE");
        SELECT("UPDATE_UID");
        SELECT("RECV_PLACE");
        SELECT("MSG");
        FROM("OP.T_CT_ERROR_MNG_GUARD");
        applyWhere(spec, false);

        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_MNG_GUARD
     *
     * @mbggenerated Wed Nov 26 19:29:51 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TCtErrorMngGuard record = (TCtErrorMngGuard) parameter.get("record");
        TCtErrorMngGuardSpec spec = (TCtErrorMngGuardSpec) parameter.get("spec");

        BEGIN();
        UPDATE("OP.T_CT_ERROR_MNG_GUARD");

        if (record.getErrorNo() != null) {
            SET("ERROR_NO = #{record.errorNo,jdbcType=VARCHAR}");
        }

        if (record.getCreateDate() != null) {
            SET("CREATE_DATE = #{record.createDate,jdbcType=VARCHAR}");
        }

        if (record.getCreateTime() != null) {
            SET("CREATE_TIME = #{record.createTime,jdbcType=VARCHAR}");
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

        if (record.getErrorCd() != null) {
            SET("ERROR_CD = #{record.errorCd,jdbcType=VARCHAR}");
        }

        if (record.getAcceptDate() != null) {
            SET("ACCEPT_DATE = #{record.acceptDate,jdbcType=VARCHAR}");
        }

        if (record.getAcceptTime() != null) {
            SET("ACCEPT_TIME = #{record.acceptTime,jdbcType=VARCHAR}");
        }

        if (record.getOrgMsg() != null) {
            SET("ORG_MSG = #{record.orgMsg,jdbcType=VARCHAR}");
        }

        if (record.getRepairDate() != null) {
            SET("REPAIR_DATE = #{record.repairDate,jdbcType=VARCHAR}");
        }

        if (record.getRepairTime() != null) {
            SET("REPAIR_TIME = #{record.repairTime,jdbcType=VARCHAR}");
        }

        if (record.getFinishType() != null) {
            SET("FINISH_TYPE = #{record.finishType,jdbcType=VARCHAR}");
        }

        if (record.getArrivalEstDate() != null) {
            SET("ARRIVAL_EST_DATE = #{record.arrivalEstDate,jdbcType=VARCHAR}");
        }

        if (record.getArrivalEstTime() != null) {
            SET("ARRIVAL_EST_TIME = #{record.arrivalEstTime,jdbcType=VARCHAR}");
        }

        if (record.getRecvUserNm() != null) {
            SET("RECV_USER_NM = #{record.recvUserNm,jdbcType=VARCHAR}");
        }

        if (record.getRecvTeleNo() != null) {
            SET("RECV_TELE_NO = #{record.recvTeleNo,jdbcType=VARCHAR}");
        }

        if (record.getRecvDate() != null) {
            SET("RECV_DATE = #{record.recvDate,jdbcType=VARCHAR}");
        }

        if (record.getRecvTime() != null) {
            SET("RECV_TIME = #{record.recvTime,jdbcType=VARCHAR}");
        }

        if (record.getArrivalDate() != null) {
            SET("ARRIVAL_DATE = #{record.arrivalDate,jdbcType=VARCHAR}");
        }

        if (record.getArrivalTime() != null) {
            SET("ARRIVAL_TIME = #{record.arrivalTime,jdbcType=VARCHAR}");
        }

        if (record.getArrivalNm() != null) {
            SET("ARRIVAL_NM = #{record.arrivalNm,jdbcType=VARCHAR}");
        }

        if (record.getArrivalTeleNo() != null) {
            SET("ARRIVAL_TELE_NO = #{record.arrivalTeleNo,jdbcType=VARCHAR}");
        }

        if (record.getFinishDate() != null) {
            SET("FINISH_DATE = #{record.finishDate,jdbcType=VARCHAR}");
        }

        if (record.getFinishTime() != null) {
            SET("FINISH_TIME = #{record.finishTime,jdbcType=VARCHAR}");
        }

        if (record.getFinishNm() != null) {
            SET("FINISH_NM = #{record.finishNm,jdbcType=VARCHAR}");
        }

        if (record.getFinishTeleNo() != null) {
            SET("FINISH_TELE_NO = #{record.finishTeleNo,jdbcType=VARCHAR}");
        }

        if (record.getSec() != null) {
            SET("SEC = #{record.sec,jdbcType=VARCHAR}");
        }

        if (record.getErrorStatus() != null) {
            SET("ERROR_STATUS = #{record.errorStatus,jdbcType=VARCHAR}");
        }

        if (record.getAsMedium() != null) {
            SET("AS_MEDIUM = #{record.asMedium,jdbcType=VARCHAR}");
        }

        if (record.getGuardSendYn() != null) {
            SET("GUARD_SEND_YN = #{record.guardSendYn,jdbcType=VARCHAR}");
        }

        if (record.getRegDt() != null) {
            SET("REG_DT = #{record.regDt,jdbcType=TIMESTAMP}");
        }

        if (record.getRegId() != null) {
            SET("REG_ID = #{record.regId,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        }

        if (record.getRecvPlace() != null) {
            SET("RECV_PLACE = #{record.recvPlace,jdbcType=VARCHAR}");
        }

        if (record.getMsg() != null) {
            SET("MSG = #{record.msg,jdbcType=VARCHAR}");
        }

        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_MNG_GUARD
     *
     * @mbggenerated Wed Nov 26 19:29:51 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_CT_ERROR_MNG_GUARD");

        SET("ERROR_NO = #{record.errorNo,jdbcType=VARCHAR}");
        SET("CREATE_DATE = #{record.createDate,jdbcType=VARCHAR}");
        SET("CREATE_TIME = #{record.createTime,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("BRANCH_CD = #{record.branchCd,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("SITE_CD = #{record.siteCd,jdbcType=VARCHAR}");
        SET("ERROR_CD = #{record.errorCd,jdbcType=VARCHAR}");
        SET("ACCEPT_DATE = #{record.acceptDate,jdbcType=VARCHAR}");
        SET("ACCEPT_TIME = #{record.acceptTime,jdbcType=VARCHAR}");
        SET("ORG_MSG = #{record.orgMsg,jdbcType=VARCHAR}");
        SET("REPAIR_DATE = #{record.repairDate,jdbcType=VARCHAR}");
        SET("REPAIR_TIME = #{record.repairTime,jdbcType=VARCHAR}");
        SET("FINISH_TYPE = #{record.finishType,jdbcType=VARCHAR}");
        SET("ARRIVAL_EST_DATE = #{record.arrivalEstDate,jdbcType=VARCHAR}");
        SET("ARRIVAL_EST_TIME = #{record.arrivalEstTime,jdbcType=VARCHAR}");
        SET("RECV_USER_NM = #{record.recvUserNm,jdbcType=VARCHAR}");
        SET("RECV_TELE_NO = #{record.recvTeleNo,jdbcType=VARCHAR}");
        SET("RECV_DATE = #{record.recvDate,jdbcType=VARCHAR}");
        SET("RECV_TIME = #{record.recvTime,jdbcType=VARCHAR}");
        SET("ARRIVAL_DATE = #{record.arrivalDate,jdbcType=VARCHAR}");
        SET("ARRIVAL_TIME = #{record.arrivalTime,jdbcType=VARCHAR}");
        SET("ARRIVAL_NM = #{record.arrivalNm,jdbcType=VARCHAR}");
        SET("ARRIVAL_TELE_NO = #{record.arrivalTeleNo,jdbcType=VARCHAR}");
        SET("FINISH_DATE = #{record.finishDate,jdbcType=VARCHAR}");
        SET("FINISH_TIME = #{record.finishTime,jdbcType=VARCHAR}");
        SET("FINISH_NM = #{record.finishNm,jdbcType=VARCHAR}");
        SET("FINISH_TELE_NO = #{record.finishTeleNo,jdbcType=VARCHAR}");
        SET("SEC = #{record.sec,jdbcType=VARCHAR}");
        SET("ERROR_STATUS = #{record.errorStatus,jdbcType=VARCHAR}");
        SET("AS_MEDIUM = #{record.asMedium,jdbcType=VARCHAR}");
        SET("GUARD_SEND_YN = #{record.guardSendYn,jdbcType=VARCHAR}");
        SET("REG_DT = #{record.regDt,jdbcType=TIMESTAMP}");
        SET("REG_ID = #{record.regId,jdbcType=VARCHAR}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_UID = #{record.updateUid,jdbcType=VARCHAR}");
        SET("RECV_PLACE = #{record.recvPlace,jdbcType=VARCHAR}");
        SET("MSG = #{record.msg,jdbcType=VARCHAR}");

        TCtErrorMngGuardSpec spec = (TCtErrorMngGuardSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_MNG_GUARD
     *
     * @mbggenerated Wed Nov 26 19:29:51 KST 2014
     */
    public String updateByPrimaryKeySelective(TCtErrorMngGuard record) {
        BEGIN();
        UPDATE("OP.T_CT_ERROR_MNG_GUARD");

        if (record.getCreateTime() != null) {
            SET("CREATE_TIME = #{createTime,jdbcType=VARCHAR}");
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

        if (record.getErrorCd() != null) {
            SET("ERROR_CD = #{errorCd,jdbcType=VARCHAR}");
        }

        if (record.getAcceptDate() != null) {
            SET("ACCEPT_DATE = #{acceptDate,jdbcType=VARCHAR}");
        }

        if (record.getAcceptTime() != null) {
            SET("ACCEPT_TIME = #{acceptTime,jdbcType=VARCHAR}");
        }

        if (record.getOrgMsg() != null) {
            SET("ORG_MSG = #{orgMsg,jdbcType=VARCHAR}");
        }

        if (record.getRepairDate() != null) {
            SET("REPAIR_DATE = #{repairDate,jdbcType=VARCHAR}");
        }

        if (record.getRepairTime() != null) {
            SET("REPAIR_TIME = #{repairTime,jdbcType=VARCHAR}");
        }

        if (record.getFinishType() != null) {
            SET("FINISH_TYPE = #{finishType,jdbcType=VARCHAR}");
        }

        if (record.getArrivalEstDate() != null) {
            SET("ARRIVAL_EST_DATE = #{arrivalEstDate,jdbcType=VARCHAR}");
        }

        if (record.getArrivalEstTime() != null) {
            SET("ARRIVAL_EST_TIME = #{arrivalEstTime,jdbcType=VARCHAR}");
        }

        if (record.getRecvUserNm() != null) {
            SET("RECV_USER_NM = #{recvUserNm,jdbcType=VARCHAR}");
        }

        if (record.getRecvTeleNo() != null) {
            SET("RECV_TELE_NO = #{recvTeleNo,jdbcType=VARCHAR}");
        }

        if (record.getRecvDate() != null) {
            SET("RECV_DATE = #{recvDate,jdbcType=VARCHAR}");
        }

        if (record.getRecvTime() != null) {
            SET("RECV_TIME = #{recvTime,jdbcType=VARCHAR}");
        }

        if (record.getArrivalDate() != null) {
            SET("ARRIVAL_DATE = #{arrivalDate,jdbcType=VARCHAR}");
        }

        if (record.getArrivalTime() != null) {
            SET("ARRIVAL_TIME = #{arrivalTime,jdbcType=VARCHAR}");
        }

        if (record.getArrivalNm() != null) {
            SET("ARRIVAL_NM = #{arrivalNm,jdbcType=VARCHAR}");
        }

        if (record.getArrivalTeleNo() != null) {
            SET("ARRIVAL_TELE_NO = #{arrivalTeleNo,jdbcType=VARCHAR}");
        }

        if (record.getFinishDate() != null) {
            SET("FINISH_DATE = #{finishDate,jdbcType=VARCHAR}");
        }

        if (record.getFinishTime() != null) {
            SET("FINISH_TIME = #{finishTime,jdbcType=VARCHAR}");
        }

        if (record.getFinishNm() != null) {
            SET("FINISH_NM = #{finishNm,jdbcType=VARCHAR}");
        }

        if (record.getFinishTeleNo() != null) {
            SET("FINISH_TELE_NO = #{finishTeleNo,jdbcType=VARCHAR}");
        }

        if (record.getSec() != null) {
            SET("SEC = #{sec,jdbcType=VARCHAR}");
        }

        if (record.getErrorStatus() != null) {
            SET("ERROR_STATUS = #{errorStatus,jdbcType=VARCHAR}");
        }

        if (record.getAsMedium() != null) {
            SET("AS_MEDIUM = #{asMedium,jdbcType=VARCHAR}");
        }

        if (record.getGuardSendYn() != null) {
            SET("GUARD_SEND_YN = #{guardSendYn,jdbcType=VARCHAR}");
        }

        if (record.getRegDt() != null) {
            SET("REG_DT = #{regDt,jdbcType=TIMESTAMP}");
        }

        if (record.getRegId() != null) {
            SET("REG_ID = #{regId,jdbcType=VARCHAR}");
        }

        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }

        if (record.getUpdateUid() != null) {
            SET("UPDATE_UID = #{updateUid,jdbcType=VARCHAR}");
        }

        if (record.getRecvPlace() != null) {
            SET("RECV_PLACE = #{recvPlace,jdbcType=VARCHAR}");
        }

        if (record.getMsg() != null) {
            SET("MSG = #{msg,jdbcType=VARCHAR}");
        }

        WHERE("ERROR_NO = #{errorNo,jdbcType=VARCHAR}");
        WHERE("CREATE_DATE = #{createDate,jdbcType=VARCHAR}");

        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_MNG_GUARD
     *
     * @mbggenerated Wed Nov 26 19:29:51 KST 2014
     */
    protected void applyWhere(TCtErrorMngGuardSpec spec, boolean includeSpecPhrase) {
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
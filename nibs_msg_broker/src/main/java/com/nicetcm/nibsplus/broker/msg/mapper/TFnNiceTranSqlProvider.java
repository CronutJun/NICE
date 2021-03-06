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

import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTran;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranSpec.Criteria;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranSpec.Criterion;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranSpec;
import java.util.List;
import java.util.Map;

public class TFnNiceTranSqlProvider {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    public String countBySpec(TFnNiceTranSpec spec) {
        BEGIN();
        SELECT("count(*)");
        FROM("OP.T_FN_NICE_TRAN");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    public String deleteBySpec(TFnNiceTranSpec spec) {
        BEGIN();
        DELETE_FROM("OP.T_FN_NICE_TRAN");
        applyWhere(spec, false);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    public String insertSelective(TFnNiceTran record) {
        BEGIN();
        INSERT_INTO("OP.T_FN_NICE_TRAN");
        if (record.getDealYear() != null) {
            VALUES("DEAL_YEAR", "#{dealYear,jdbcType=VARCHAR}");
        }
        if (record.getDealNo() != null) {
            VALUES("DEAL_NO", "#{dealNo,jdbcType=VARCHAR}");
        }
        if (record.getDealDate() != null) {
            VALUES("DEAL_DATE", "#{dealDate,jdbcType=VARCHAR}");
        }
        if (record.getMacNo() != null) {
            VALUES("MAC_NO", "#{macNo,jdbcType=VARCHAR}");
        }
        if (record.getAtmDealNo() != null) {
            VALUES("ATM_DEAL_NO", "#{atmDealNo,jdbcType=VARCHAR}");
        }
        if (record.getDealType() != null) {
            VALUES("DEAL_TYPE", "#{dealType,jdbcType=VARCHAR}");
        }
        if (record.getDealClss() != null) {
            VALUES("DEAL_CLSS", "#{dealClss,jdbcType=VARCHAR}");
        }
        if (record.getTrackNo() != null) {
            VALUES("TRACK_NO", "#{trackNo,jdbcType=VARCHAR}");
        }
        if (record.getInstOrgCd() != null) {
            VALUES("INST_ORG_CD", "#{instOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getInstBranchCd() != null) {
            VALUES("INST_BRANCH_CD", "#{instBranchCd,jdbcType=VARCHAR}");
        }
        if (record.getAccountNo() != null) {
            VALUES("ACCOUNT_NO", "#{accountNo,jdbcType=VARCHAR}");
        }
        if (record.getRealAccountNo() != null) {
            VALUES("REAL_ACCOUNT_NO", "#{realAccountNo,jdbcType=VARCHAR}");
        }
        if (record.getDealAmt() != null) {
            VALUES("DEAL_AMT", "#{dealAmt,jdbcType=DECIMAL}");
        }
        if (record.getCustFee() != null) {
            VALUES("CUST_FEE", "#{custFee,jdbcType=DECIMAL}");
        }
        if (record.getBankFee() != null) {
            VALUES("BANK_FEE", "#{bankFee,jdbcType=DECIMAL}");
        }
        if (record.getTransOrgCd() != null) {
            VALUES("TRANS_ORG_CD", "#{transOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getTransBranchCd() != null) {
            VALUES("TRANS_BRANCH_CD", "#{transBranchCd,jdbcType=VARCHAR}");
        }
        if (record.getTransAccountNo() != null) {
            VALUES("TRANS_ACCOUNT_NO", "#{transAccountNo,jdbcType=VARCHAR}");
        }
        if (record.getTransDepositor() != null) {
            VALUES("TRANS_DEPOSITOR", "#{transDepositor,jdbcType=VARCHAR}");
        }
        if (record.getResponseCd() != null) {
            VALUES("RESPONSE_CD", "#{responseCd,jdbcType=VARCHAR}");
        }
        if (record.getRefuseCd() != null) {
            VALUES("REFUSE_CD", "#{refuseCd,jdbcType=VARCHAR}");
        }
        if (record.getOrgResponseCd() != null) {
            VALUES("ORG_RESPONSE_CD", "#{orgResponseCd,jdbcType=VARCHAR}");
        }
        if (record.getOrgCd() != null) {
            VALUES("ORG_CD", "#{orgCd,jdbcType=VARCHAR}");
        }
        if (record.getDealMmdd() != null) {
            VALUES("DEAL_MMDD", "#{dealMmdd,jdbcType=VARCHAR}");
        }
        if (record.getAdmisOrg() != null) {
            VALUES("ADMIS_ORG", "#{admisOrg,jdbcType=VARCHAR}");
        }
        if (record.getDealStatus() != null) {
            VALUES("DEAL_STATUS", "#{dealStatus,jdbcType=VARCHAR}");
        }
        if (record.getDealHour() != null) {
            VALUES("DEAL_HOUR", "#{dealHour,jdbcType=VARCHAR}");
        }
        if (record.getDealMi() != null) {
            VALUES("DEAL_MI", "#{dealMi,jdbcType=VARCHAR}");
        }
        if (record.getDealSec() != null) {
            VALUES("DEAL_SEC", "#{dealSec,jdbcType=VARCHAR}");
        }
        if (record.getErrorStatus() != null) {
            VALUES("ERROR_STATUS", "#{errorStatus,jdbcType=VARCHAR}");
        }
        if (record.getDealTimeType() != null) {
            VALUES("DEAL_TIME_TYPE", "#{dealTimeType,jdbcType=VARCHAR}");
        }
        if (record.getJoinOrgDealNo() != null) {
            VALUES("JOIN_ORG_DEAL_NO", "#{joinOrgDealNo,jdbcType=VARCHAR}");
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
        if (record.getInsertDate() != null) {
            VALUES("INSERT_DATE", "#{insertDate,jdbcType=TIMESTAMP}");
        }
        if (record.getUpdateDate() != null) {
            VALUES("UPDATE_DATE", "#{updateDate,jdbcType=TIMESTAMP}");
        }
        if (record.getNetOrgCd() != null) {
            VALUES("NET_ORG_CD", "#{netOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getCheckAmt() != null) {
            VALUES("CHECK_AMT", "#{checkAmt,jdbcType=DECIMAL}");
        }
        if (record.getFdeptCd() != null) {
            VALUES("FDEPT_CD", "#{fdeptCd,jdbcType=VARCHAR}");
        }
        if (record.getFofficeCd() != null) {
            VALUES("FOFFICE_CD", "#{fofficeCd,jdbcType=VARCHAR}");
        }
        if (record.getDealAmt10000() != null) {
            VALUES("DEAL_AMT_10000", "#{dealAmt10000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt50000() != null) {
            VALUES("DEAL_AMT_50000", "#{dealAmt50000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt100000() != null) {
            VALUES("DEAL_AMT_100000", "#{dealAmt100000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt1000() != null) {
            VALUES("DEAL_AMT_1000", "#{dealAmt1000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt5000() != null) {
            VALUES("DEAL_AMT_5000", "#{dealAmt5000,jdbcType=DECIMAL}");
        }
        if (record.getBrandOrgCd() != null) {
            VALUES("BRAND_ORG_CD", "#{brandOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getUserTelNo() != null) {
            VALUES("USER_TEL_NO", "#{userTelNo,jdbcType=VARCHAR}");
        }
        if (record.getGiftInitial() != null) {
            VALUES("GIFT_INITIAL", "#{giftInitial,jdbcType=VARCHAR}");
        }
        if (record.getGiftSubCd() != null) {
            VALUES("GIFT_SUB_CD", "#{giftSubCd,jdbcType=VARCHAR}");
        }
        if (record.getGiftSeqNo() != null) {
            VALUES("GIFT_SEQ_NO", "#{giftSeqNo,jdbcType=VARCHAR}");
        }
        if (record.getOriginDealDate() != null) {
            VALUES("ORIGIN_DEAL_DATE", "#{originDealDate,jdbcType=VARCHAR}");
        }
        if (record.getOriginDealNo() != null) {
            VALUES("ORIGIN_DEAL_NO", "#{originDealNo,jdbcType=VARCHAR}");
        }
        if (record.getGiftJumCd() != null) {
            VALUES("GIFT_JUM_CD", "#{giftJumCd,jdbcType=VARCHAR}");
        }
        if (record.getCalcDate() != null) {
            VALUES("CALC_DATE", "#{calcDate,jdbcType=VARCHAR}");
        }
        if (record.getArpcFault() != null) {
            VALUES("ARPC_FAULT", "#{arpcFault,jdbcType=VARCHAR}");
        }
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    public String selectBySpec(TFnNiceTranSpec spec) {
        BEGIN();
        if (spec != null && spec.isDistinct()) {
            SELECT_DISTINCT("DEAL_YEAR");
        } else {
            SELECT("DEAL_YEAR");
        }
        SELECT("DEAL_NO");
        SELECT("DEAL_DATE");
        SELECT("MAC_NO");
        SELECT("ATM_DEAL_NO");
        SELECT("DEAL_TYPE");
        SELECT("DEAL_CLSS");
        SELECT("TRACK_NO");
        SELECT("INST_ORG_CD");
        SELECT("INST_BRANCH_CD");
        SELECT("ACCOUNT_NO");
        SELECT("REAL_ACCOUNT_NO");
        SELECT("DEAL_AMT");
        SELECT("CUST_FEE");
        SELECT("BANK_FEE");
        SELECT("TRANS_ORG_CD");
        SELECT("TRANS_BRANCH_CD");
        SELECT("TRANS_ACCOUNT_NO");
        SELECT("TRANS_DEPOSITOR");
        SELECT("RESPONSE_CD");
        SELECT("REFUSE_CD");
        SELECT("ORG_RESPONSE_CD");
        SELECT("ORG_CD");
        SELECT("DEAL_MMDD");
        SELECT("ADMIS_ORG");
        SELECT("DEAL_STATUS");
        SELECT("DEAL_HOUR");
        SELECT("DEAL_MI");
        SELECT("DEAL_SEC");
        SELECT("ERROR_STATUS");
        SELECT("DEAL_TIME_TYPE");
        SELECT("JOIN_ORG_DEAL_NO");
        SELECT("DEPT_CD");
        SELECT("OFFICE_CD");
        SELECT("TEAM_CD");
        SELECT("INSERT_DATE");
        SELECT("UPDATE_DATE");
        SELECT("NET_ORG_CD");
        SELECT("CHECK_AMT");
        SELECT("FDEPT_CD");
        SELECT("FOFFICE_CD");
        SELECT("DEAL_AMT_10000");
        SELECT("DEAL_AMT_50000");
        SELECT("DEAL_AMT_100000");
        SELECT("DEAL_AMT_1000");
        SELECT("DEAL_AMT_5000");
        SELECT("BRAND_ORG_CD");
        SELECT("USER_TEL_NO");
        SELECT("GIFT_INITIAL");
        SELECT("GIFT_SUB_CD");
        SELECT("GIFT_SEQ_NO");
        SELECT("ORIGIN_DEAL_DATE");
        SELECT("ORIGIN_DEAL_NO");
        SELECT("GIFT_JUM_CD");
        SELECT("CALC_DATE");
        SELECT("ARPC_FAULT");
        FROM("OP.T_FN_NICE_TRAN");
        applyWhere(spec, false);
        if (spec != null && spec.getOrderByClause() != null) {
            ORDER_BY(spec.getOrderByClause());
        }
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    public String updateBySpecSelective(Map<String, Object> parameter) {
        TFnNiceTran record = (TFnNiceTran) parameter.get("record");
        TFnNiceTranSpec spec = (TFnNiceTranSpec) parameter.get("spec");
        BEGIN();
        UPDATE("OP.T_FN_NICE_TRAN");
        if (record.getDealYear() != null) {
            SET("DEAL_YEAR = #{record.dealYear,jdbcType=VARCHAR}");
        }
        if (record.getDealNo() != null) {
            SET("DEAL_NO = #{record.dealNo,jdbcType=VARCHAR}");
        }
        if (record.getDealDate() != null) {
            SET("DEAL_DATE = #{record.dealDate,jdbcType=VARCHAR}");
        }
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        }
        if (record.getAtmDealNo() != null) {
            SET("ATM_DEAL_NO = #{record.atmDealNo,jdbcType=VARCHAR}");
        }
        if (record.getDealType() != null) {
            SET("DEAL_TYPE = #{record.dealType,jdbcType=VARCHAR}");
        }
        if (record.getDealClss() != null) {
            SET("DEAL_CLSS = #{record.dealClss,jdbcType=VARCHAR}");
        }
        if (record.getTrackNo() != null) {
            SET("TRACK_NO = #{record.trackNo,jdbcType=VARCHAR}");
        }
        if (record.getInstOrgCd() != null) {
            SET("INST_ORG_CD = #{record.instOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getInstBranchCd() != null) {
            SET("INST_BRANCH_CD = #{record.instBranchCd,jdbcType=VARCHAR}");
        }
        if (record.getAccountNo() != null) {
            SET("ACCOUNT_NO = #{record.accountNo,jdbcType=VARCHAR}");
        }
        if (record.getRealAccountNo() != null) {
            SET("REAL_ACCOUNT_NO = #{record.realAccountNo,jdbcType=VARCHAR}");
        }
        if (record.getDealAmt() != null) {
            SET("DEAL_AMT = #{record.dealAmt,jdbcType=DECIMAL}");
        }
        if (record.getCustFee() != null) {
            SET("CUST_FEE = #{record.custFee,jdbcType=DECIMAL}");
        }
        if (record.getBankFee() != null) {
            SET("BANK_FEE = #{record.bankFee,jdbcType=DECIMAL}");
        }
        if (record.getTransOrgCd() != null) {
            SET("TRANS_ORG_CD = #{record.transOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getTransBranchCd() != null) {
            SET("TRANS_BRANCH_CD = #{record.transBranchCd,jdbcType=VARCHAR}");
        }
        if (record.getTransAccountNo() != null) {
            SET("TRANS_ACCOUNT_NO = #{record.transAccountNo,jdbcType=VARCHAR}");
        }
        if (record.getTransDepositor() != null) {
            SET("TRANS_DEPOSITOR = #{record.transDepositor,jdbcType=VARCHAR}");
        }
        if (record.getResponseCd() != null) {
            SET("RESPONSE_CD = #{record.responseCd,jdbcType=VARCHAR}");
        }
        if (record.getRefuseCd() != null) {
            SET("REFUSE_CD = #{record.refuseCd,jdbcType=VARCHAR}");
        }
        if (record.getOrgResponseCd() != null) {
            SET("ORG_RESPONSE_CD = #{record.orgResponseCd,jdbcType=VARCHAR}");
        }
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        }
        if (record.getDealMmdd() != null) {
            SET("DEAL_MMDD = #{record.dealMmdd,jdbcType=VARCHAR}");
        }
        if (record.getAdmisOrg() != null) {
            SET("ADMIS_ORG = #{record.admisOrg,jdbcType=VARCHAR}");
        }
        if (record.getDealStatus() != null) {
            SET("DEAL_STATUS = #{record.dealStatus,jdbcType=VARCHAR}");
        }
        if (record.getDealHour() != null) {
            SET("DEAL_HOUR = #{record.dealHour,jdbcType=VARCHAR}");
        }
        if (record.getDealMi() != null) {
            SET("DEAL_MI = #{record.dealMi,jdbcType=VARCHAR}");
        }
        if (record.getDealSec() != null) {
            SET("DEAL_SEC = #{record.dealSec,jdbcType=VARCHAR}");
        }
        if (record.getErrorStatus() != null) {
            SET("ERROR_STATUS = #{record.errorStatus,jdbcType=VARCHAR}");
        }
        if (record.getDealTimeType() != null) {
            SET("DEAL_TIME_TYPE = #{record.dealTimeType,jdbcType=VARCHAR}");
        }
        if (record.getJoinOrgDealNo() != null) {
            SET("JOIN_ORG_DEAL_NO = #{record.joinOrgDealNo,jdbcType=VARCHAR}");
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
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        }
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        }
        if (record.getNetOrgCd() != null) {
            SET("NET_ORG_CD = #{record.netOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getCheckAmt() != null) {
            SET("CHECK_AMT = #{record.checkAmt,jdbcType=DECIMAL}");
        }
        if (record.getFdeptCd() != null) {
            SET("FDEPT_CD = #{record.fdeptCd,jdbcType=VARCHAR}");
        }
        if (record.getFofficeCd() != null) {
            SET("FOFFICE_CD = #{record.fofficeCd,jdbcType=VARCHAR}");
        }
        if (record.getDealAmt10000() != null) {
            SET("DEAL_AMT_10000 = #{record.dealAmt10000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt50000() != null) {
            SET("DEAL_AMT_50000 = #{record.dealAmt50000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt100000() != null) {
            SET("DEAL_AMT_100000 = #{record.dealAmt100000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt1000() != null) {
            SET("DEAL_AMT_1000 = #{record.dealAmt1000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt5000() != null) {
            SET("DEAL_AMT_5000 = #{record.dealAmt5000,jdbcType=DECIMAL}");
        }
        if (record.getBrandOrgCd() != null) {
            SET("BRAND_ORG_CD = #{record.brandOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getUserTelNo() != null) {
            SET("USER_TEL_NO = #{record.userTelNo,jdbcType=VARCHAR}");
        }
        if (record.getGiftInitial() != null) {
            SET("GIFT_INITIAL = #{record.giftInitial,jdbcType=VARCHAR}");
        }
        if (record.getGiftSubCd() != null) {
            SET("GIFT_SUB_CD = #{record.giftSubCd,jdbcType=VARCHAR}");
        }
        if (record.getGiftSeqNo() != null) {
            SET("GIFT_SEQ_NO = #{record.giftSeqNo,jdbcType=VARCHAR}");
        }
        if (record.getOriginDealDate() != null) {
            SET("ORIGIN_DEAL_DATE = #{record.originDealDate,jdbcType=VARCHAR}");
        }
        if (record.getOriginDealNo() != null) {
            SET("ORIGIN_DEAL_NO = #{record.originDealNo,jdbcType=VARCHAR}");
        }
        if (record.getGiftJumCd() != null) {
            SET("GIFT_JUM_CD = #{record.giftJumCd,jdbcType=VARCHAR}");
        }
        if (record.getCalcDate() != null) {
            SET("CALC_DATE = #{record.calcDate,jdbcType=VARCHAR}");
        }
        if (record.getArpcFault() != null) {
            SET("ARPC_FAULT = #{record.arpcFault,jdbcType=VARCHAR}");
        }
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    public String updateBySpec(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("OP.T_FN_NICE_TRAN");
        SET("DEAL_YEAR = #{record.dealYear,jdbcType=VARCHAR}");
        SET("DEAL_NO = #{record.dealNo,jdbcType=VARCHAR}");
        SET("DEAL_DATE = #{record.dealDate,jdbcType=VARCHAR}");
        SET("MAC_NO = #{record.macNo,jdbcType=VARCHAR}");
        SET("ATM_DEAL_NO = #{record.atmDealNo,jdbcType=VARCHAR}");
        SET("DEAL_TYPE = #{record.dealType,jdbcType=VARCHAR}");
        SET("DEAL_CLSS = #{record.dealClss,jdbcType=VARCHAR}");
        SET("TRACK_NO = #{record.trackNo,jdbcType=VARCHAR}");
        SET("INST_ORG_CD = #{record.instOrgCd,jdbcType=VARCHAR}");
        SET("INST_BRANCH_CD = #{record.instBranchCd,jdbcType=VARCHAR}");
        SET("ACCOUNT_NO = #{record.accountNo,jdbcType=VARCHAR}");
        SET("REAL_ACCOUNT_NO = #{record.realAccountNo,jdbcType=VARCHAR}");
        SET("DEAL_AMT = #{record.dealAmt,jdbcType=DECIMAL}");
        SET("CUST_FEE = #{record.custFee,jdbcType=DECIMAL}");
        SET("BANK_FEE = #{record.bankFee,jdbcType=DECIMAL}");
        SET("TRANS_ORG_CD = #{record.transOrgCd,jdbcType=VARCHAR}");
        SET("TRANS_BRANCH_CD = #{record.transBranchCd,jdbcType=VARCHAR}");
        SET("TRANS_ACCOUNT_NO = #{record.transAccountNo,jdbcType=VARCHAR}");
        SET("TRANS_DEPOSITOR = #{record.transDepositor,jdbcType=VARCHAR}");
        SET("RESPONSE_CD = #{record.responseCd,jdbcType=VARCHAR}");
        SET("REFUSE_CD = #{record.refuseCd,jdbcType=VARCHAR}");
        SET("ORG_RESPONSE_CD = #{record.orgResponseCd,jdbcType=VARCHAR}");
        SET("ORG_CD = #{record.orgCd,jdbcType=VARCHAR}");
        SET("DEAL_MMDD = #{record.dealMmdd,jdbcType=VARCHAR}");
        SET("ADMIS_ORG = #{record.admisOrg,jdbcType=VARCHAR}");
        SET("DEAL_STATUS = #{record.dealStatus,jdbcType=VARCHAR}");
        SET("DEAL_HOUR = #{record.dealHour,jdbcType=VARCHAR}");
        SET("DEAL_MI = #{record.dealMi,jdbcType=VARCHAR}");
        SET("DEAL_SEC = #{record.dealSec,jdbcType=VARCHAR}");
        SET("ERROR_STATUS = #{record.errorStatus,jdbcType=VARCHAR}");
        SET("DEAL_TIME_TYPE = #{record.dealTimeType,jdbcType=VARCHAR}");
        SET("JOIN_ORG_DEAL_NO = #{record.joinOrgDealNo,jdbcType=VARCHAR}");
        SET("DEPT_CD = #{record.deptCd,jdbcType=VARCHAR}");
        SET("OFFICE_CD = #{record.officeCd,jdbcType=VARCHAR}");
        SET("TEAM_CD = #{record.teamCd,jdbcType=VARCHAR}");
        SET("INSERT_DATE = #{record.insertDate,jdbcType=TIMESTAMP}");
        SET("UPDATE_DATE = #{record.updateDate,jdbcType=TIMESTAMP}");
        SET("NET_ORG_CD = #{record.netOrgCd,jdbcType=VARCHAR}");
        SET("CHECK_AMT = #{record.checkAmt,jdbcType=DECIMAL}");
        SET("FDEPT_CD = #{record.fdeptCd,jdbcType=VARCHAR}");
        SET("FOFFICE_CD = #{record.fofficeCd,jdbcType=VARCHAR}");
        SET("DEAL_AMT_10000 = #{record.dealAmt10000,jdbcType=DECIMAL}");
        SET("DEAL_AMT_50000 = #{record.dealAmt50000,jdbcType=DECIMAL}");
        SET("DEAL_AMT_100000 = #{record.dealAmt100000,jdbcType=DECIMAL}");
        SET("DEAL_AMT_1000 = #{record.dealAmt1000,jdbcType=DECIMAL}");
        SET("DEAL_AMT_5000 = #{record.dealAmt5000,jdbcType=DECIMAL}");
        SET("BRAND_ORG_CD = #{record.brandOrgCd,jdbcType=VARCHAR}");
        SET("USER_TEL_NO = #{record.userTelNo,jdbcType=VARCHAR}");
        SET("GIFT_INITIAL = #{record.giftInitial,jdbcType=VARCHAR}");
        SET("GIFT_SUB_CD = #{record.giftSubCd,jdbcType=VARCHAR}");
        SET("GIFT_SEQ_NO = #{record.giftSeqNo,jdbcType=VARCHAR}");
        SET("ORIGIN_DEAL_DATE = #{record.originDealDate,jdbcType=VARCHAR}");
        SET("ORIGIN_DEAL_NO = #{record.originDealNo,jdbcType=VARCHAR}");
        SET("GIFT_JUM_CD = #{record.giftJumCd,jdbcType=VARCHAR}");
        SET("CALC_DATE = #{record.calcDate,jdbcType=VARCHAR}");
        SET("ARPC_FAULT = #{record.arpcFault,jdbcType=VARCHAR}");
        TFnNiceTranSpec spec = (TFnNiceTranSpec) parameter.get("spec");
        applyWhere(spec, true);
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    public String updateByPrimaryKeySelective(TFnNiceTran record) {
        BEGIN();
        UPDATE("OP.T_FN_NICE_TRAN");
        if (record.getDealDate() != null) {
            SET("DEAL_DATE = #{dealDate,jdbcType=VARCHAR}");
        }
        if (record.getMacNo() != null) {
            SET("MAC_NO = #{macNo,jdbcType=VARCHAR}");
        }
        if (record.getAtmDealNo() != null) {
            SET("ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}");
        }
        if (record.getDealType() != null) {
            SET("DEAL_TYPE = #{dealType,jdbcType=VARCHAR}");
        }
        if (record.getDealClss() != null) {
            SET("DEAL_CLSS = #{dealClss,jdbcType=VARCHAR}");
        }
        if (record.getTrackNo() != null) {
            SET("TRACK_NO = #{trackNo,jdbcType=VARCHAR}");
        }
        if (record.getInstOrgCd() != null) {
            SET("INST_ORG_CD = #{instOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getInstBranchCd() != null && record.getInstBranchCd().trim().length() > 0) {
            SET("INST_BRANCH_CD = OP.FC_FN_SECURITY(#{instBranchCd,jdbcType=VARCHAR}, '1')");
        }
        if (record.getAccountNo() != null && record.getAccountNo().trim().length() > 0) {
            SET("ACCOUNT_NO = OP.FC_FN_SECURITY(#{accountNo,jdbcType=VARCHAR}, '1')");
        }
        if (record.getRealAccountNo() != null && record.getRealAccountNo().trim().length() > 0) {
            SET("REAL_ACCOUNT_NO = OP.FC_FN_SECURITY(#{realAccountNo,jdbcType=VARCHAR}, '1')");
        }
        if (record.getDealAmt() != null) {
            SET("DEAL_AMT = #{dealAmt,jdbcType=DECIMAL}");
        }
        if (record.getCustFee() != null) {
            SET("CUST_FEE = #{custFee,jdbcType=DECIMAL}");
        }
        if (record.getBankFee() != null) {
            SET("BANK_FEE = #{bankFee,jdbcType=DECIMAL}");
        }
        if (record.getTransOrgCd() != null) {
            SET("TRANS_ORG_CD = #{transOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getTransBranchCd() != null && record.getTransBranchCd().trim().length() > 0) {
            SET("TRANS_BRANCH_CD = OP.FC_FN_SECURITY(#{transBranchCd,jdbcType=VARCHAR}, '1')");
        }
        if (record.getTransAccountNo() != null && record.getTransAccountNo().trim().length() > 0) {
            SET("TRANS_ACCOUNT_NO = OP.FC_FN_SECURITY(#{transAccountNo,jdbcType=VARCHAR}, '1')");
        }
        if (record.getTransDepositor() != null) {
            SET("TRANS_DEPOSITOR = #{transDepositor,jdbcType=VARCHAR}");
        }
        if (record.getResponseCd() != null) {
            SET("RESPONSE_CD = #{responseCd,jdbcType=VARCHAR}");
        }
        if (record.getRefuseCd() != null) {
            SET("REFUSE_CD = #{refuseCd,jdbcType=VARCHAR}");
        }
        if (record.getOrgResponseCd() != null) {
            SET("ORG_RESPONSE_CD = #{orgResponseCd,jdbcType=VARCHAR}");
        }
        if (record.getOrgCd() != null) {
            SET("ORG_CD = #{orgCd,jdbcType=VARCHAR}");
        }
        if (record.getDealMmdd() != null) {
            SET("DEAL_MMDD = #{dealMmdd,jdbcType=VARCHAR}");
        }
        if (record.getAdmisOrg() != null) {
            SET("ADMIS_ORG = #{admisOrg,jdbcType=VARCHAR}");
        }
        if (record.getDealStatus() != null) {
            SET("DEAL_STATUS = #{dealStatus,jdbcType=VARCHAR}");
        }
        if (record.getDealHour() != null) {
            SET("DEAL_HOUR = #{dealHour,jdbcType=VARCHAR}");
        }
        if (record.getDealMi() != null) {
            SET("DEAL_MI = #{dealMi,jdbcType=VARCHAR}");
        }
        if (record.getDealSec() != null) {
            SET("DEAL_SEC = #{dealSec,jdbcType=VARCHAR}");
        }
        if (record.getErrorStatus() != null) {
            SET("ERROR_STATUS = #{errorStatus,jdbcType=VARCHAR}");
        }
        if (record.getDealTimeType() != null) {
            SET("DEAL_TIME_TYPE = #{dealTimeType,jdbcType=VARCHAR}");
        }
        if (record.getJoinOrgDealNo() != null) {
            SET("JOIN_ORG_DEAL_NO = #{joinOrgDealNo,jdbcType=VARCHAR}");
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
        if (record.getInsertDate() != null) {
            SET("INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP}");
        }
        if (record.getUpdateDate() != null) {
            SET("UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}");
        }
        if (record.getNetOrgCd() != null) {
            SET("NET_ORG_CD = #{netOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getCheckAmt() != null) {
            SET("CHECK_AMT = #{checkAmt,jdbcType=DECIMAL}");
        }
        if (record.getFdeptCd() != null) {
            SET("FDEPT_CD = #{fdeptCd,jdbcType=VARCHAR}");
        }
        if (record.getFofficeCd() != null) {
            SET("FOFFICE_CD = #{fofficeCd,jdbcType=VARCHAR}");
        }
        if (record.getDealAmt10000() != null) {
            SET("DEAL_AMT_10000 = #{dealAmt10000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt50000() != null) {
            SET("DEAL_AMT_50000 = #{dealAmt50000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt100000() != null) {
            SET("DEAL_AMT_100000 = #{dealAmt100000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt1000() != null) {
            SET("DEAL_AMT_1000 = #{dealAmt1000,jdbcType=DECIMAL}");
        }
        if (record.getDealAmt5000() != null) {
            SET("DEAL_AMT_5000 = #{dealAmt5000,jdbcType=DECIMAL}");
        }
        if (record.getBrandOrgCd() != null) {
            SET("BRAND_ORG_CD = #{brandOrgCd,jdbcType=VARCHAR}");
        }
        if (record.getUserTelNo() != null && record.getUserTelNo().trim().length() > 0) {
            SET("USER_TEL_NO = OP.FC_FN_SECURITY(#{userTelNo,jdbcType=VARCHAR}, '1')");
        }
        if (record.getGiftInitial() != null) {
            SET("GIFT_INITIAL = #{giftInitial,jdbcType=VARCHAR}");
        }
        if (record.getGiftSubCd() != null) {
            SET("GIFT_SUB_CD = #{giftSubCd,jdbcType=VARCHAR}");
        }
        if (record.getGiftSeqNo() != null) {
            SET("GIFT_SEQ_NO = #{giftSeqNo,jdbcType=VARCHAR}");
        }
        if (record.getOriginDealDate() != null) {
            SET("ORIGIN_DEAL_DATE = #{originDealDate,jdbcType=VARCHAR}");
        }
        if (record.getOriginDealNo() != null) {
            SET("ORIGIN_DEAL_NO = #{originDealNo,jdbcType=VARCHAR}");
        }
        if (record.getGiftJumCd() != null) {
            SET("GIFT_JUM_CD = #{giftJumCd,jdbcType=VARCHAR}");
        }
        if (record.getCalcDate() != null) {
            SET("CALC_DATE = #{calcDate,jdbcType=VARCHAR}");
        }
        if (record.getArpcFault() != null) {
            SET("ARPC_FAULT = #{arpcFault,jdbcType=VARCHAR}");
        }
        WHERE("DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}");
        WHERE("DEAL_NO = #{dealNo,jdbcType=VARCHAR}");
        return SQL();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    protected void applyWhere(TFnNiceTranSpec spec,
            boolean includeSpecPhrase) {
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
package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTran;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranSpec;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranJoin;
import com.nicetcm.nibsplus.broker.msg.model.BNetCalc;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TFnNiceTranMapper {
    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @SelectProvider(type = TFnNiceTranSqlProvider.class, method = "countBySpec")
    int countBySpec(TFnNiceTranSpec spec);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @DeleteProvider(type = TFnNiceTranSqlProvider.class, method = "deleteBySpec")
    int deleteBySpec(TFnNiceTranSpec spec);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @Delete({ "delete from OP.T_FN_NICE_TRAN",
            "where DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}",
            "and DEAL_NO = #{dealNo,jdbcType=VARCHAR}" })
    int deleteByPrimaryKey(TFnNiceTranKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @Insert({
            "insert into OP.T_FN_NICE_TRAN (DEAL_YEAR, DEAL_NO, ",
            "DEAL_DATE, MAC_NO, ",
            "ATM_DEAL_NO, DEAL_TYPE, ",
            "DEAL_CLSS, TRACK_NO, ",
            "INST_ORG_CD, INST_BRANCH_CD, ",
            "ACCOUNT_NO, REAL_ACCOUNT_NO, ",
            "DEAL_AMT, CUST_FEE, ",
            "BANK_FEE, TRANS_ORG_CD, ",
            "TRANS_BRANCH_CD, TRANS_ACCOUNT_NO, ",
            "TRANS_DEPOSITOR, RESPONSE_CD, ",
            "REFUSE_CD, ORG_RESPONSE_CD, ",
            "ORG_CD, DEAL_MMDD, ",
            "ADMIS_ORG, DEAL_STATUS, ",
            "DEAL_HOUR, DEAL_MI, ",
            "DEAL_SEC, ERROR_STATUS, ",
            "DEAL_TIME_TYPE, JOIN_ORG_DEAL_NO, ",
            "DEPT_CD, OFFICE_CD, ",
            "TEAM_CD, INSERT_DATE, ",
            "UPDATE_DATE, NET_ORG_CD, ",
            "CHECK_AMT, FDEPT_CD, ",
            "FOFFICE_CD, DEAL_AMT_10000, ",
            "DEAL_AMT_50000, DEAL_AMT_100000, ",
            "DEAL_AMT_1000, DEAL_AMT_5000, ",
            "BRAND_ORG_CD, USER_TEL_NO, ",
            "GIFT_INITIAL, GIFT_SUB_CD, ",
            "GIFT_SEQ_NO, ORIGIN_DEAL_DATE, ",
            "ORIGIN_DEAL_NO, GIFT_JUM_CD, ",
            "CALC_DATE, ARPC_FAULT)",
            "values (#{dealYear,jdbcType=VARCHAR}, #{dealNo,jdbcType=VARCHAR}, ",
            "#{dealDate,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
            "#{atmDealNo,jdbcType=VARCHAR}, #{dealType,jdbcType=VARCHAR}, ",
            "#{dealClss,jdbcType=VARCHAR}, #{trackNo,jdbcType=VARCHAR}, ",
            "#{instOrgCd,jdbcType=VARCHAR}, #{instBranchCd,jdbcType=VARCHAR}, ",
            "#{accountNo,jdbcType=VARCHAR}, #{realAccountNo,jdbcType=VARCHAR}, ",
            "#{dealAmt,jdbcType=DECIMAL}, #{custFee,jdbcType=DECIMAL}, ",
            "#{bankFee,jdbcType=DECIMAL}, #{transOrgCd,jdbcType=VARCHAR}, ",
            "#{transBranchCd,jdbcType=VARCHAR}, #{transAccountNo,jdbcType=VARCHAR}, ",
            "#{transDepositor,jdbcType=VARCHAR}, #{responseCd,jdbcType=VARCHAR}, ",
            "#{refuseCd,jdbcType=VARCHAR}, #{orgResponseCd,jdbcType=VARCHAR}, ",
            "#{orgCd,jdbcType=VARCHAR}, #{dealMmdd,jdbcType=VARCHAR}, ",
            "#{admisOrg,jdbcType=VARCHAR}, #{dealStatus,jdbcType=VARCHAR}, ",
            "#{dealHour,jdbcType=VARCHAR}, #{dealMi,jdbcType=VARCHAR}, ",
            "#{dealSec,jdbcType=VARCHAR}, #{errorStatus,jdbcType=VARCHAR}, ",
            "#{dealTimeType,jdbcType=VARCHAR}, #{joinOrgDealNo,jdbcType=VARCHAR}, ",
            "#{deptCd,jdbcType=VARCHAR}, #{officeCd,jdbcType=VARCHAR}, ",
            "#{teamCd,jdbcType=VARCHAR}, #{insertDate,jdbcType=TIMESTAMP}, ",
            "#{updateDate,jdbcType=TIMESTAMP}, #{netOrgCd,jdbcType=VARCHAR}, ",
            "#{checkAmt,jdbcType=DECIMAL}, #{fdeptCd,jdbcType=VARCHAR}, ",
            "#{fofficeCd,jdbcType=VARCHAR}, #{dealAmt10000,jdbcType=DECIMAL}, ",
            "#{dealAmt50000,jdbcType=DECIMAL}, #{dealAmt100000,jdbcType=DECIMAL}, ",
            "#{dealAmt1000,jdbcType=DECIMAL}, #{dealAmt5000,jdbcType=DECIMAL}, ",
            "#{brandOrgCd,jdbcType=VARCHAR}, #{userTelNo,jdbcType=VARCHAR}, ",
            "#{giftInitial,jdbcType=VARCHAR}, #{giftSubCd,jdbcType=VARCHAR}, ",
            "#{giftSeqNo,jdbcType=VARCHAR}, #{originDealDate,jdbcType=VARCHAR}, ",
            "#{originDealNo,jdbcType=VARCHAR}, #{giftJumCd,jdbcType=VARCHAR}, ",
            "#{calcDate,jdbcType=VARCHAR}, #{arpcFault,jdbcType=VARCHAR})" })
    int insert(TFnNiceTran record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @InsertProvider(type = TFnNiceTranSqlProvider.class, method = "insertSelective")
    int insertSelective(TFnNiceTran record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @SelectProvider(type = TFnNiceTranSqlProvider.class, method = "selectBySpec")
    @Results({
            @Result(column = "DEAL_YEAR", property = "dealYear", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "DEAL_NO", property = "dealNo", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "DEAL_DATE", property = "dealDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "MAC_NO", property = "macNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ATM_DEAL_NO", property = "atmDealNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_TYPE", property = "dealType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_CLSS", property = "dealClss", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TRACK_NO", property = "trackNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "INST_ORG_CD", property = "instOrgCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "INST_BRANCH_CD", property = "instBranchCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ACCOUNT_NO", property = "accountNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REAL_ACCOUNT_NO", property = "realAccountNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_AMT", property = "dealAmt", jdbcType = JdbcType.DECIMAL),
            @Result(column = "CUST_FEE", property = "custFee", jdbcType = JdbcType.DECIMAL),
            @Result(column = "BANK_FEE", property = "bankFee", jdbcType = JdbcType.DECIMAL),
            @Result(column = "TRANS_ORG_CD", property = "transOrgCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TRANS_BRANCH_CD", property = "transBranchCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TRANS_ACCOUNT_NO", property = "transAccountNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TRANS_DEPOSITOR", property = "transDepositor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "RESPONSE_CD", property = "responseCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REFUSE_CD", property = "refuseCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ORG_RESPONSE_CD", property = "orgResponseCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ORG_CD", property = "orgCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_MMDD", property = "dealMmdd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ADMIS_ORG", property = "admisOrg", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_STATUS", property = "dealStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_HOUR", property = "dealHour", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_MI", property = "dealMi", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_SEC", property = "dealSec", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ERROR_STATUS", property = "errorStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_TIME_TYPE", property = "dealTimeType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "JOIN_ORG_DEAL_NO", property = "joinOrgDealNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEPT_CD", property = "deptCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "OFFICE_CD", property = "officeCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TEAM_CD", property = "teamCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "INSERT_DATE", property = "insertDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "UPDATE_DATE", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "NET_ORG_CD", property = "netOrgCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CHECK_AMT", property = "checkAmt", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FDEPT_CD", property = "fdeptCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FOFFICE_CD", property = "fofficeCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_AMT_10000", property = "dealAmt10000", jdbcType = JdbcType.DECIMAL),
            @Result(column = "DEAL_AMT_50000", property = "dealAmt50000", jdbcType = JdbcType.DECIMAL),
            @Result(column = "DEAL_AMT_100000", property = "dealAmt100000", jdbcType = JdbcType.DECIMAL),
            @Result(column = "DEAL_AMT_1000", property = "dealAmt1000", jdbcType = JdbcType.DECIMAL),
            @Result(column = "DEAL_AMT_5000", property = "dealAmt5000", jdbcType = JdbcType.DECIMAL),
            @Result(column = "BRAND_ORG_CD", property = "brandOrgCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "USER_TEL_NO", property = "userTelNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GIFT_INITIAL", property = "giftInitial", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GIFT_SUB_CD", property = "giftSubCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GIFT_SEQ_NO", property = "giftSeqNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ORIGIN_DEAL_DATE", property = "originDealDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ORIGIN_DEAL_NO", property = "originDealNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GIFT_JUM_CD", property = "giftJumCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CALC_DATE", property = "calcDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ARPC_FAULT", property = "arpcFault", jdbcType = JdbcType.VARCHAR) })
    List<TFnNiceTran> selectBySpec(TFnNiceTranSpec spec);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @Select({
            "select",
            "DEAL_YEAR, DEAL_NO, DEAL_DATE, MAC_NO, ATM_DEAL_NO, DEAL_TYPE, DEAL_CLSS, TRACK_NO, ",
            "INST_ORG_CD, INST_BRANCH_CD, ACCOUNT_NO, REAL_ACCOUNT_NO, DEAL_AMT, CUST_FEE, ",
            "BANK_FEE, TRANS_ORG_CD, TRANS_BRANCH_CD, TRANS_ACCOUNT_NO, TRANS_DEPOSITOR, ",
            "RESPONSE_CD, REFUSE_CD, ORG_RESPONSE_CD, ORG_CD, DEAL_MMDD, ADMIS_ORG, DEAL_STATUS, ",
            "DEAL_HOUR, DEAL_MI, DEAL_SEC, ERROR_STATUS, DEAL_TIME_TYPE, JOIN_ORG_DEAL_NO, ",
            "DEPT_CD, OFFICE_CD, TEAM_CD, INSERT_DATE, UPDATE_DATE, NET_ORG_CD, CHECK_AMT, ",
            "FDEPT_CD, FOFFICE_CD, DEAL_AMT_10000, DEAL_AMT_50000, DEAL_AMT_100000, DEAL_AMT_1000, ",
            "DEAL_AMT_5000, BRAND_ORG_CD, USER_TEL_NO, GIFT_INITIAL, GIFT_SUB_CD, GIFT_SEQ_NO, ",
            "ORIGIN_DEAL_DATE, ORIGIN_DEAL_NO, GIFT_JUM_CD, CALC_DATE, ARPC_FAULT",
            "from OP.T_FN_NICE_TRAN",
            "where DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}",
            "and DEAL_NO = #{dealNo,jdbcType=VARCHAR}" })
    @Results({
            @Result(column = "DEAL_YEAR", property = "dealYear", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "DEAL_NO", property = "dealNo", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "DEAL_DATE", property = "dealDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "MAC_NO", property = "macNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ATM_DEAL_NO", property = "atmDealNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_TYPE", property = "dealType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_CLSS", property = "dealClss", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TRACK_NO", property = "trackNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "INST_ORG_CD", property = "instOrgCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "INST_BRANCH_CD", property = "instBranchCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ACCOUNT_NO", property = "accountNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REAL_ACCOUNT_NO", property = "realAccountNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_AMT", property = "dealAmt", jdbcType = JdbcType.DECIMAL),
            @Result(column = "CUST_FEE", property = "custFee", jdbcType = JdbcType.DECIMAL),
            @Result(column = "BANK_FEE", property = "bankFee", jdbcType = JdbcType.DECIMAL),
            @Result(column = "TRANS_ORG_CD", property = "transOrgCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TRANS_BRANCH_CD", property = "transBranchCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TRANS_ACCOUNT_NO", property = "transAccountNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TRANS_DEPOSITOR", property = "transDepositor", jdbcType = JdbcType.VARCHAR),
            @Result(column = "RESPONSE_CD", property = "responseCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REFUSE_CD", property = "refuseCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ORG_RESPONSE_CD", property = "orgResponseCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ORG_CD", property = "orgCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_MMDD", property = "dealMmdd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ADMIS_ORG", property = "admisOrg", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_STATUS", property = "dealStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_HOUR", property = "dealHour", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_MI", property = "dealMi", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_SEC", property = "dealSec", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ERROR_STATUS", property = "errorStatus", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_TIME_TYPE", property = "dealTimeType", jdbcType = JdbcType.VARCHAR),
            @Result(column = "JOIN_ORG_DEAL_NO", property = "joinOrgDealNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEPT_CD", property = "deptCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "OFFICE_CD", property = "officeCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "TEAM_CD", property = "teamCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "INSERT_DATE", property = "insertDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "UPDATE_DATE", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "NET_ORG_CD", property = "netOrgCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CHECK_AMT", property = "checkAmt", jdbcType = JdbcType.DECIMAL),
            @Result(column = "FDEPT_CD", property = "fdeptCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FOFFICE_CD", property = "fofficeCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "DEAL_AMT_10000", property = "dealAmt10000", jdbcType = JdbcType.DECIMAL),
            @Result(column = "DEAL_AMT_50000", property = "dealAmt50000", jdbcType = JdbcType.DECIMAL),
            @Result(column = "DEAL_AMT_100000", property = "dealAmt100000", jdbcType = JdbcType.DECIMAL),
            @Result(column = "DEAL_AMT_1000", property = "dealAmt1000", jdbcType = JdbcType.DECIMAL),
            @Result(column = "DEAL_AMT_5000", property = "dealAmt5000", jdbcType = JdbcType.DECIMAL),
            @Result(column = "BRAND_ORG_CD", property = "brandOrgCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "USER_TEL_NO", property = "userTelNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GIFT_INITIAL", property = "giftInitial", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GIFT_SUB_CD", property = "giftSubCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GIFT_SEQ_NO", property = "giftSeqNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ORIGIN_DEAL_DATE", property = "originDealDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ORIGIN_DEAL_NO", property = "originDealNo", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GIFT_JUM_CD", property = "giftJumCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "CALC_DATE", property = "calcDate", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ARPC_FAULT", property = "arpcFault", jdbcType = JdbcType.VARCHAR) })
    TFnNiceTran selectByPrimaryKey(TFnNiceTranKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @UpdateProvider(type = TFnNiceTranSqlProvider.class, method = "updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnNiceTran record,
            @Param("spec") TFnNiceTranSpec spec);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @UpdateProvider(type = TFnNiceTranSqlProvider.class, method = "updateBySpec")
    int updateBySpec(@Param("record") TFnNiceTran record,
            @Param("spec") TFnNiceTranSpec spec);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @UpdateProvider(type = TFnNiceTranSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnNiceTran record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_FN_NICE_TRAN
     * @mbggenerated  Thu Oct 16 10:34:51 KST 2014
     */
    @Update({ "update OP.T_FN_NICE_TRAN",
            "set DEAL_DATE = #{dealDate,jdbcType=VARCHAR},",
            "MAC_NO = #{macNo,jdbcType=VARCHAR},",
            "ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR},",
            "DEAL_TYPE = #{dealType,jdbcType=VARCHAR},",
            "DEAL_CLSS = #{dealClss,jdbcType=VARCHAR},",
            "TRACK_NO = #{trackNo,jdbcType=VARCHAR},",
            "INST_ORG_CD = #{instOrgCd,jdbcType=VARCHAR},",
            "INST_BRANCH_CD = #{instBranchCd,jdbcType=VARCHAR},",
            "ACCOUNT_NO = #{accountNo,jdbcType=VARCHAR},",
            "REAL_ACCOUNT_NO = #{realAccountNo,jdbcType=VARCHAR},",
            "DEAL_AMT = #{dealAmt,jdbcType=DECIMAL},",
            "CUST_FEE = #{custFee,jdbcType=DECIMAL},",
            "BANK_FEE = #{bankFee,jdbcType=DECIMAL},",
            "TRANS_ORG_CD = #{transOrgCd,jdbcType=VARCHAR},",
            "TRANS_BRANCH_CD = #{transBranchCd,jdbcType=VARCHAR},",
            "TRANS_ACCOUNT_NO = #{transAccountNo,jdbcType=VARCHAR},",
            "TRANS_DEPOSITOR = #{transDepositor,jdbcType=VARCHAR},",
            "RESPONSE_CD = #{responseCd,jdbcType=VARCHAR},",
            "REFUSE_CD = #{refuseCd,jdbcType=VARCHAR},",
            "ORG_RESPONSE_CD = #{orgResponseCd,jdbcType=VARCHAR},",
            "ORG_CD = #{orgCd,jdbcType=VARCHAR},",
            "DEAL_MMDD = #{dealMmdd,jdbcType=VARCHAR},",
            "ADMIS_ORG = #{admisOrg,jdbcType=VARCHAR},",
            "DEAL_STATUS = #{dealStatus,jdbcType=VARCHAR},",
            "DEAL_HOUR = #{dealHour,jdbcType=VARCHAR},",
            "DEAL_MI = #{dealMi,jdbcType=VARCHAR},",
            "DEAL_SEC = #{dealSec,jdbcType=VARCHAR},",
            "ERROR_STATUS = #{errorStatus,jdbcType=VARCHAR},",
            "DEAL_TIME_TYPE = #{dealTimeType,jdbcType=VARCHAR},",
            "JOIN_ORG_DEAL_NO = #{joinOrgDealNo,jdbcType=VARCHAR},",
            "DEPT_CD = #{deptCd,jdbcType=VARCHAR},",
            "OFFICE_CD = #{officeCd,jdbcType=VARCHAR},",
            "TEAM_CD = #{teamCd,jdbcType=VARCHAR},",
            "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
            "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
            "NET_ORG_CD = #{netOrgCd,jdbcType=VARCHAR},",
            "CHECK_AMT = #{checkAmt,jdbcType=DECIMAL},",
            "FDEPT_CD = #{fdeptCd,jdbcType=VARCHAR},",
            "FOFFICE_CD = #{fofficeCd,jdbcType=VARCHAR},",
            "DEAL_AMT_10000 = #{dealAmt10000,jdbcType=DECIMAL},",
            "DEAL_AMT_50000 = #{dealAmt50000,jdbcType=DECIMAL},",
            "DEAL_AMT_100000 = #{dealAmt100000,jdbcType=DECIMAL},",
            "DEAL_AMT_1000 = #{dealAmt1000,jdbcType=DECIMAL},",
            "DEAL_AMT_5000 = #{dealAmt5000,jdbcType=DECIMAL},",
            "BRAND_ORG_CD = #{brandOrgCd,jdbcType=VARCHAR},",
            "USER_TEL_NO = #{userTelNo,jdbcType=VARCHAR},",
            "GIFT_INITIAL = #{giftInitial,jdbcType=VARCHAR},",
            "GIFT_SUB_CD = #{giftSubCd,jdbcType=VARCHAR},",
            "GIFT_SEQ_NO = #{giftSeqNo,jdbcType=VARCHAR},",
            "ORIGIN_DEAL_DATE = #{originDealDate,jdbcType=VARCHAR},",
            "ORIGIN_DEAL_NO = #{originDealNo,jdbcType=VARCHAR},",
            "GIFT_JUM_CD = #{giftJumCd,jdbcType=VARCHAR},",
            "CALC_DATE = #{calcDate,jdbcType=VARCHAR},",
            "ARPC_FAULT = #{arpcFault,jdbcType=VARCHAR}",
            "where DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}",
            "and DEAL_NO = #{dealNo,jdbcType=VARCHAR}" })
    int updateByPrimaryKey(TFnNiceTran record);

    /**
     * InN5000300Impl 에서 호출
     *
     * @author KDJ
     * @since  Tue Aug 01 14:21:01 KST 2014
     */
    @SelectProvider(type=TFnNiceTranMiscProvider.class, method="selectBNetCalc")
    @Results({
        @Result(column="DEAL_DATE",             property="dealDate",           jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_CD",                property="orgCd",              jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_NO",                property="macNo",              jdbcType=JdbcType.VARCHAR),
        @Result(column="CASH_CNT",              property="cashCnt",            jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_AMT",              property="cashAmt",            jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_CANCEL_CNT",       property="cashCancelCnt",      jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_CANCEL_AMT",       property="cashCancelAmt",      jdbcType=JdbcType.DECIMAL),
        @Result(column="SAME_CNT",              property="sameCnt",            jdbcType=JdbcType.DECIMAL),
        @Result(column="SAME_AMT",              property="sameAmt",            jdbcType=JdbcType.DECIMAL),
        @Result(column="DIFF_CNT",              property="diffCnt",            jdbcType=JdbcType.DECIMAL),
        @Result(column="DIFF_AMT",              property="diffAmt",            jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_SVC_CNT",          property="cashSvcCnt",         jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_SVC_AMT",          property="cashSvcAmt",         jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_IN_CNT",           property="cashInCnt",          jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_IN_AMT",           property="cashInAmt",          jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_IN_CANCEL_CNT",    property="cashInCancelCnt",    jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_IN_CANCEL_AMT",    property="cashInCancelAmt",    jdbcType=JdbcType.DECIMAL),
        @Result(column="REAL_TRADE_AMT",        property="realTradeAmt",       jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_CUST_FEE_AMT",      property="outCustFeeAmt",      jdbcType=JdbcType.DECIMAL),
        @Result(column="SAME_CUST_FEE_AMT",     property="sameCustFeeAmt",     jdbcType=JdbcType.DECIMAL),
        @Result(column="DIFF_CUST_FEE_AMT",     property="diffCustFeeAmt",     jdbcType=JdbcType.DECIMAL),
        @Result(column="AFTER_CASH_CNT",        property="afterCashCnt",       jdbcType=JdbcType.DECIMAL),
        @Result(column="AFTER_CASH_AMT",        property="afterCashAmt",       jdbcType=JdbcType.DECIMAL),
        @Result(column="AFTER_CASH_CANCEL_CNT", property="afterCashCancelCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="AFTER_CASH_CANCEL_AMT", property="afterCashCancelAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="AFTER_SAME_CNT",        property="afterSameCnt",       jdbcType=JdbcType.DECIMAL),
        @Result(column="AFTER_SAME_AMT",        property="afterSameAmt",       jdbcType=JdbcType.DECIMAL)
    })
    BNetCalc selectBNetCalc(BNetCalc cond);

    /**
     * InN5000410Impl 에서 호출
     *
     * @author KDJ
     * @since  Tue Aug 04 10:16:01 KST 2014
     */
    @Select({
        "SELECT DECODE(NMC.JOIN_CD, #{orgCd, jdbcType=VARCHAR}, 'B', /*  브랜드기기  */ ",
        "                                           'V') AS MAC_CL,  /*  제휴기기    */ ",
        "       /* 기기구분(제휴/브랜드/결합부스) */                                    ",
        "       OFC.TELE_NO AS TEL_NO,                               /* 관리전화번호 */ ",
        "       SITE.SITE_NM,                                        /* 사이트명     */ ",
        "       SITE.SET_ADDR                                        /* 사이트주소   */ ",
        "FROM   T_FN_NICE_TRAN TRAN                                                     ",
        "       LEFT JOIN T_CM_MAC MAC ON                                               ",
        "            TRAN.MAC_NO    = MAC.MAC_NO                                        ",
        "       LEFT JOIN T_CM_SITE SITE ON                                             ",
        "            MAC.ORG_CD     = SITE.ORG_CD                                       ",
        "       AND  MAC.JIJUM_CD   = SITE.JIJUM_CD                                     ",
        "       AND  MAC.SITE_CD    = SITE.SITE_CD                                      ",
        "       LEFT JOIN T_CM_OFFICE OFC ON                                            ",
        "            SITE.DEPT_CD   = OFC.DEPT_CD                                       ",
        "       AND  SITE.OFFICE_CD = OFC.OFFICE_CD                                     ",
        "       LEFT OUTER JOIN T_CT_NICE_MAC NMC ON                                    ",
        "            MAC.ORG_CD     = NMC.ORG_CD                                        ",
        "       AND  MAC.JIJUM_CD   = NMC.JIJUM_CD                                      ",
        "       AND  MAC.MAC_NO     = NMC.MAC_NO                                        ",
        "WHERE  TRAN.DEAL_YEAR = #{dealYear, jdbcType=VARCHAR}                          ",
        "AND    TRAN.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                          ",
        "AND    TRAN.DEAL_NO   = #{dealNo, jdbcType=VARCHAR}                            ",
        "AND    MAC.ORG_CD     = '096'                                                  "
    })
    @Results({
        @Result(column="MAC_CL",             property="macCl",           jdbcType=JdbcType.VARCHAR),
        @Result(column="TEL_NO",             property="telNo",           jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_NM",            property="siteNm",          jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_ADDR",           property="setAddr",         jdbcType=JdbcType.VARCHAR)
    })
    TFnNiceTranJoin selectByJoin1(TFnNiceTranJoin cond);

    /**
     * InN1000100Impl 에서 호출
     *
     * @author KDJ
     * @since  Tue Oct 16 11:26:01 KST 2014
     */
    @Select({
        "SELECT  DEAL_STATUS,                                                 ",
        "        ADMIS_ORG,                                                   ",
        "        OP.FC_FN_SECURITY(INST_BRANCH_CD, '2') AS INST_BRANCH_CD,    ",
        "        OP.FC_FN_SECURITY(ACCOUNT_NO, '2') AS ACCOUNT_NO,            ",
        "        OP.FC_FN_SECURITY(REAL_ACCOUNT_NO, '2') AS REAL_ACCOUNT_NO,  ",
        "        OP.FC_FN_SECURITY(TRANS_BRANCH_CD, '2') AS TRANS_BRANCH_CD,  ",
        "        OP.FC_FN_SECURITY(TRANS_ACCOUNT_NO, '2') AS TRANS_ACCOUNT_NO ",
        "FROM    OP.T_FN_NICE_TRAN                                            ",
        "WHERE   DEAL_DATE   = RTRIM(#{dealDate, jdbcType=VARCHAR})        ",
        "AND     MAC_NO      = RTRIM(#{macNo, jdbcType=VARCHAR})           ",
        "AND     ATM_DEAL_NO = RTRIM(#{atmDealNo, jdbcType=VARCHAR})       "
    })
    @Results({
        @Result(column="INST_BRANCH_CD",     property="instBranchCd",    jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCOUNT_NO",         property="accountNo",       jdbcType=JdbcType.VARCHAR),
        @Result(column="REAL_ACCOUNT_NO",    property="realAccountNo",   jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANS_BRANCH_CD",    property="transBranchCd",   jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANS_ACCOUNT_NO",   property="transAccountNo",  jdbcType=JdbcType.VARCHAR)
    })
    TFnNiceTran selectByCond1(TFnNiceTran cond);

    /**
     * InN1000100Impl 에서 호출
     *
     * @author KDJ
     * @since  Tue Oct 16 11:26:01 KST 2014
     */
    @Update({
        "UPDATE  OP.T_FN_NICE_TRAN                                                                           ",
        "   SET  ADMIS_ORG  = #{admisOrg, jdbcType=VARCHAR},                                                 ",
        "        REAL_ACCOUNT_NO = OP.FC_FN_SECURITY(RPAD(#{realAccountNo, jdbcType=VARCHAR}, 16, ' '), '1') ",
        "WHERE   DEAL_DATE   = RTRIM(#{dealDate, jdbcType=VARCHAR})                                          ",
        "AND     MAC_NO      = RTRIM(#{macNo, jdbcType=VARCHAR})                                             ",
        "AND     ATM_DEAL_NO = RTRIM(#{atmDealNo, jdbcType=VARCHAR})                                         "
    })
    int updateByCond1(TFnNiceTran record);

    /**
     * InN1000100Impl 에서 호출
     *
     * @author KDJ
     * @since  Tue Oct 16 11:26:01 KST 2014
     */
    @Update({
        "UPDATE  OP.T_FN_NICE_TRAN                                                                             ",
        "   SET  INST_BRANCH_CD  = OP.FC_FN_SECURITY(RPAD(#{instBranchCd, jdbcType=VARCHAR}, 4, ' '), '1'),    ",
        "        ACCOUNT_NO = OP.FC_FN_SECURITY(RPAD(#{accountNo, jdbcType=VARCHAR}, 20, ' ') , '1'),          ",
        "        REAL_ACCOUNT_NO = OP.FC_FN_SECURITY(RPAD(#{realAccountNo, jdbcType=VARCHAR}, 16, ' ') , '1'), ",
        "        TRANS_BRANCH_CD = OP.FC_FN_SECURITY(#{transBranchCd, jdbcType=VARCHAR}, '1'),                 ",
        "        TRANS_ACCOUNT_NO = OP.FC_FN_SECURITY(#{transAccountNo, jdbcType=VARCHAR}, '1')                ",
        "WHERE   DEAL_DATE   = RTRIM(#{dealDate, jdbcType=VARCHAR})                                            ",
        "AND     MAC_NO      = RTRIM(#{macNo, jdbcType=VARCHAR})                                               ",
        "AND     ATM_DEAL_NO = RTRIM(#{atmDealNo, jdbcType=VARCHAR})                                           "
    })
    int updateByCond2(TFnNiceTran record);

}
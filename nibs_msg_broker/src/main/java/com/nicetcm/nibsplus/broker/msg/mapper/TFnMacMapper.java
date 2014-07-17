package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnMac;
import com.nicetcm.nibsplus.broker.msg.model.TFnMacKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnMacSpec;

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

public interface TFnMacMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @SelectProvider(type=TFnMacSqlProvider.class, method="countBySpec")
    int countBySpec(TFnMacSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @DeleteProvider(type=TFnMacSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnMacSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_MAC",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnMacKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_MAC (ORG_CD, BRANCH_CD, ",
        "MAC_NO, SITE_CD, IN_MAC_AMT, ",
        "PRE_MAC_AMT, PRE_IN_MAC_AMT, ",
        "PRE_SAFE_NO, OFFICE_MAC_TEMP_AMT, ",
        "PRE_AMT, PLUS_CNT, ",
        "BEFORE_CLOSE_DATE, BEFORE_CLOSE_TIME, ",
        "OLD_CLOSE_DATE, OLD_CLOSE_TIME, ",
        "ATM_DEAL_NO, LAST_DEAL_TIME, ",
        "FIRST_DEAL_DATE, FIRST_ATM_DEAL_NO, ",
        "SEND_CHECK_AMT, IN_MAC_AMT_CW14, ",
        "IN_MAC_AMT_CW54, IN_MAC_AMT_CW15, ",
        "IN_MAC_AMT_CW11, IN_MAC_AMT_CW51, ",
        "IN_MAC_AMT_CW12, IN_MAC_AMT_CW52, ",
        "IN_MAC_AMT_CW13, IN_MAC_AMT_CW53, ",
        "INSERT_UID, INSERT_DATE, ",
        "UPDATE_UID, UPDATE_DATE)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{siteCd,jdbcType=VARCHAR}, #{inMacAmt,jdbcType=DECIMAL}, ",
        "#{preMacAmt,jdbcType=DECIMAL}, #{preInMacAmt,jdbcType=DECIMAL}, ",
        "#{preSafeNo,jdbcType=VARCHAR}, #{officeMacTempAmt,jdbcType=DECIMAL}, ",
        "#{preAmt,jdbcType=DECIMAL}, #{plusCnt,jdbcType=DECIMAL}, ",
        "#{beforeCloseDate,jdbcType=VARCHAR}, #{beforeCloseTime,jdbcType=VARCHAR}, ",
        "#{oldCloseDate,jdbcType=VARCHAR}, #{oldCloseTime,jdbcType=VARCHAR}, ",
        "#{atmDealNo,jdbcType=VARCHAR}, #{lastDealTime,jdbcType=TIMESTAMP}, ",
        "#{firstDealDate,jdbcType=VARCHAR}, #{firstAtmDealNo,jdbcType=VARCHAR}, ",
        "#{sendCheckAmt,jdbcType=DECIMAL}, #{inMacAmtCw14,jdbcType=DECIMAL}, ",
        "#{inMacAmtCw54,jdbcType=DECIMAL}, #{inMacAmtCw15,jdbcType=DECIMAL}, ",
        "#{inMacAmtCw11,jdbcType=DECIMAL}, #{inMacAmtCw51,jdbcType=DECIMAL}, ",
        "#{inMacAmtCw12,jdbcType=DECIMAL}, #{inMacAmtCw52,jdbcType=DECIMAL}, ",
        "#{inMacAmtCw13,jdbcType=DECIMAL}, #{inMacAmtCw53,jdbcType=DECIMAL}, ",
        "#{insertUid,jdbcType=VARCHAR}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{updateUid,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP})"
    })
    int insert(TFnMac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @InsertProvider(type=TFnMacSqlProvider.class, method="insertSelective")
    int insertSelective(TFnMac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @SelectProvider(type=TFnMacSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_MAC_AMT", property="inMacAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRE_MAC_AMT", property="preMacAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRE_IN_MAC_AMT", property="preInMacAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRE_SAFE_NO", property="preSafeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_MAC_TEMP_AMT", property="officeMacTempAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRE_AMT", property="preAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PLUS_CNT", property="plusCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="BEFORE_CLOSE_DATE", property="beforeCloseDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="BEFORE_CLOSE_TIME", property="beforeCloseTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLD_CLOSE_DATE", property="oldCloseDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLD_CLOSE_TIME", property="oldCloseTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATM_DEAL_NO", property="atmDealNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="LAST_DEAL_TIME", property="lastDealTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="FIRST_DEAL_DATE", property="firstDealDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="FIRST_ATM_DEAL_NO", property="firstAtmDealNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_CHECK_AMT", property="sendCheckAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW14", property="inMacAmtCw14", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW54", property="inMacAmtCw54", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW15", property="inMacAmtCw15", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW11", property="inMacAmtCw11", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW51", property="inMacAmtCw51", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW12", property="inMacAmtCw12", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW52", property="inMacAmtCw52", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW13", property="inMacAmtCw13", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW53", property="inMacAmtCw53", jdbcType=JdbcType.DECIMAL),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TFnMac> selectBySpec(TFnMacSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, BRANCH_CD, MAC_NO, SITE_CD, IN_MAC_AMT, PRE_MAC_AMT, PRE_IN_MAC_AMT, ",
        "PRE_SAFE_NO, OFFICE_MAC_TEMP_AMT, PRE_AMT, PLUS_CNT, BEFORE_CLOSE_DATE, BEFORE_CLOSE_TIME, ",
        "OLD_CLOSE_DATE, OLD_CLOSE_TIME, ATM_DEAL_NO, LAST_DEAL_TIME, FIRST_DEAL_DATE, ",
        "FIRST_ATM_DEAL_NO, SEND_CHECK_AMT, IN_MAC_AMT_CW14, IN_MAC_AMT_CW54, IN_MAC_AMT_CW15, ",
        "IN_MAC_AMT_CW11, IN_MAC_AMT_CW51, IN_MAC_AMT_CW12, IN_MAC_AMT_CW52, IN_MAC_AMT_CW13, ",
        "IN_MAC_AMT_CW53, INSERT_UID, INSERT_DATE, UPDATE_UID, UPDATE_DATE",
        "from OP.T_FN_MAC",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_MAC_AMT", property="inMacAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRE_MAC_AMT", property="preMacAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRE_IN_MAC_AMT", property="preInMacAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRE_SAFE_NO", property="preSafeNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_MAC_TEMP_AMT", property="officeMacTempAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PRE_AMT", property="preAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PLUS_CNT", property="plusCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="BEFORE_CLOSE_DATE", property="beforeCloseDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="BEFORE_CLOSE_TIME", property="beforeCloseTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLD_CLOSE_DATE", property="oldCloseDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="OLD_CLOSE_TIME", property="oldCloseTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATM_DEAL_NO", property="atmDealNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="LAST_DEAL_TIME", property="lastDealTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="FIRST_DEAL_DATE", property="firstDealDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="FIRST_ATM_DEAL_NO", property="firstAtmDealNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_CHECK_AMT", property="sendCheckAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW14", property="inMacAmtCw14", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW54", property="inMacAmtCw54", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW15", property="inMacAmtCw15", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW11", property="inMacAmtCw11", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW51", property="inMacAmtCw51", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW12", property="inMacAmtCw12", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW52", property="inMacAmtCw52", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW13", property="inMacAmtCw13", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_MAC_AMT_CW53", property="inMacAmtCw53", jdbcType=JdbcType.DECIMAL),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    TFnMac selectByPrimaryKey(TFnMacKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @UpdateProvider(type=TFnMacSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnMac record, @Param("spec") TFnMacSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @UpdateProvider(type=TFnMacSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnMac record, @Param("spec") TFnMacSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @UpdateProvider(type=TFnMacSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnMac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_MAC
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @Update({
        "update OP.T_FN_MAC",
        "set SITE_CD = #{siteCd,jdbcType=VARCHAR},",
          "IN_MAC_AMT = #{inMacAmt,jdbcType=DECIMAL},",
          "PRE_MAC_AMT = #{preMacAmt,jdbcType=DECIMAL},",
          "PRE_IN_MAC_AMT = #{preInMacAmt,jdbcType=DECIMAL},",
          "PRE_SAFE_NO = #{preSafeNo,jdbcType=VARCHAR},",
          "OFFICE_MAC_TEMP_AMT = #{officeMacTempAmt,jdbcType=DECIMAL},",
          "PRE_AMT = #{preAmt,jdbcType=DECIMAL},",
          "PLUS_CNT = #{plusCnt,jdbcType=DECIMAL},",
          "BEFORE_CLOSE_DATE = #{beforeCloseDate,jdbcType=VARCHAR},",
          "BEFORE_CLOSE_TIME = #{beforeCloseTime,jdbcType=VARCHAR},",
          "OLD_CLOSE_DATE = #{oldCloseDate,jdbcType=VARCHAR},",
          "OLD_CLOSE_TIME = #{oldCloseTime,jdbcType=VARCHAR},",
          "ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR},",
          "LAST_DEAL_TIME = #{lastDealTime,jdbcType=TIMESTAMP},",
          "FIRST_DEAL_DATE = #{firstDealDate,jdbcType=VARCHAR},",
          "FIRST_ATM_DEAL_NO = #{firstAtmDealNo,jdbcType=VARCHAR},",
          "SEND_CHECK_AMT = #{sendCheckAmt,jdbcType=DECIMAL},",
          "IN_MAC_AMT_CW14 = #{inMacAmtCw14,jdbcType=DECIMAL},",
          "IN_MAC_AMT_CW54 = #{inMacAmtCw54,jdbcType=DECIMAL},",
          "IN_MAC_AMT_CW15 = #{inMacAmtCw15,jdbcType=DECIMAL},",
          "IN_MAC_AMT_CW11 = #{inMacAmtCw11,jdbcType=DECIMAL},",
          "IN_MAC_AMT_CW51 = #{inMacAmtCw51,jdbcType=DECIMAL},",
          "IN_MAC_AMT_CW12 = #{inMacAmtCw12,jdbcType=DECIMAL},",
          "IN_MAC_AMT_CW52 = #{inMacAmtCw52,jdbcType=DECIMAL},",
          "IN_MAC_AMT_CW13 = #{inMacAmtCw13,jdbcType=DECIMAL},",
          "IN_MAC_AMT_CW53 = #{inMacAmtCw53,jdbcType=DECIMAL},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnMac record);
    
    /**
     * InN100100Impl.updateFNMacProc 에서 호출
     *
     * @author KDJ, on Tue Jul 02 17:02:00 KST 2014
     */
    @Select({
        "SELECT NVL( MAC.SVC_RELAY_YN, '0' ) AS SVC_RELAY_YN,        ",
        "       NVL( MAC.SHORT_CASH, 0 ) AS SHORT_CASH,              ",
        "       NVL( MAC.SHORT_CASH_NOTICE, 0 ) AS SHORT_CASH_NOTICE,",
        "       NVL( FMAC.IN_MAC_AMT, 0 ) AS IN_MAC_AMT,             ",
        "       NVL( FMAC.IN_MAC_AMT_CW14, 0 ) AS IN_MAC_AMT_CW14,   ",
        "       NVL( FMAC.IN_MAC_AMT_CW54, 0 ) AS IN_MAC_AMT_CW54,   ",
        "       NVL( FMAC.IN_MAC_AMT_CW15, 0 ) AS IN_MAC_AMT_CW15,   ",
        "       FMAC.FIRST_DEAL_DATE,                                ",
        "       FMAC.FIRST_ATM_DEAL_NO,                              ",
        "       FMAC.LAST_DEAL_TIME,                                 ",
        "       NM.REFLUX_5_ALLOW_YN /* 오만원권 환류 허용 여부 */   ",
        "FROM   T_CM_MAC MAC,                                        ",
        "       T_FN_MAC FMAC,                                       ",
        "       T_CT_NICE_MAC NM                                     ",
        "WHERE  MAC.ORG_CD     = #{orgCd, jdbcType=VARCHAR}          ",
        "AND    MAC.BRANCH_CD  = #{branchCd, jdbcType=VARCHAR}       ",
        "AND    MAC.MAC_NO     = #{macNo, jdbcType=VARCHAR}          ",
        "AND    MAC.ORG_CD     = FMAC.ORG_CD                         ",
        "AND    MAC.BRANCH_CD  = FMAC.BRANCH_CD                      ",
        "AND    MAC.MAC_NO     = FMAC.MAC_NO                         ",
        "AND    MAC.ORG_CD     = NM.ORG_CD                           ",
        "AND    MAC.BRANCH_CD  = NM.BRANCH_CD                        ",
        "AND    MAC.MAC_NO     = NM.MAC_NO                           "
    })
    @Results({
        @Result(column = "SVC_RELAY_YN", property = "svcRelayYn", jdbcType = JdbcType.CHAR),
        @Result(column = "SHORT_CASH", property = "shortCash", jdbcType = JdbcType.DECIMAL),
        @Result(column = "SHORT_CASH_NOTICE", property = "shortCashNotice", jdbcType = JdbcType.DECIMAL),
        @Result(column = "IN_MAC_AMT", property = "inMacAmt", jdbcType = JdbcType.DECIMAL),
        @Result(column = "LAST_DEAL_TIME", property = "lastDealTime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "REFLUX_5_ALLOW_YN", property = "reflux5AllowYn", jdbcType = JdbcType.CHAR) })
    TFnMac selectByJoin1(TFnMacKey cond);

    /**
     * InN100100Impl.updateFNMacProc 에서 호출
     *
     * @author KDJ, on Tue Jul 02 17:02:00 KST 2014
     */
    @Select({
        "SELECT NVL( MAC.SVC_RELAY_YN, '0' ) AS SVC_RELAY_YN,        ",
        "       NVL( MAC.SHORT_CASH, 0 ) AS SHORT_CASH,              ",
        "       NVL( MAC.SHORT_CASH_NOTICE, 0 ) AS SHORT_CASH_NOTICE,",
        "       NVL( FMAC.IN_MAC_AMT, 0 ) AS IN_MAC_AMT,             ",
        "       NVL( FMAC.IN_MAC_AMT_CW14, 0 ) AS IN_MAC_AMT_CW14,   ",
        "       NVL( FMAC.IN_MAC_AMT_CW54, 0 ) AS IN_MAC_AMT_CW54,   ",
        "       NVL( FMAC.IN_MAC_AMT_CW15, 0 ) AS IN_MAC_AMT_CW15,   ",
        "       FMAC.FIRST_DEAL_DATE,                                ",
        "       FMAC.FIRST_ATM_DEAL_NO,                              ",
        "       FMAC.LAST_DEAL_TIME,                                 ",
        "       NM.REFLUX_5_ALLOW_YN /* 오만원권 환류 허용 여부 */   ",
        "FROM   T_CM_MAC MAC,                                        ",
        "       T_FN_MAC FMAC,                                       ",
        "       T_CT_NICE_MAC NM                                     ",
        "WHERE  MAC.ORG_CD     = '0KK'                               ",
        "AND    MAC.BRANCH_CD  = #{branchCd, jdbcType=VARCHAR}       ",
        "AND    MAC.MAC_NO     = #{macNo, jdbcType=VARCHAR}          ",
        "AND    MAC.ORG_CD     = FMAC.ORG_CD                         ",
        "AND    MAC.BRANCH_CD  = FMAC.BRANCH_CD                      ",
        "AND    MAC.MAC_NO     = FMAC.MAC_NO                         ",
        "AND    NM.ORG_CD      = '096'                               ",
        "AND    MAC.BRANCH_CD  = NM.BRANCH_CD                        ",
        "AND    MAC.MAC_NO     = NM.MAC_NO                           "
    })
    @Results({
        @Result(column = "SVC_RELAY_YN", property = "svcRelayYn", jdbcType = JdbcType.CHAR),
        @Result(column = "SHORT_CASH", property = "shortCash", jdbcType = JdbcType.DECIMAL),
        @Result(column = "SHORT_CASH_NOTICE", property = "shortCashNotice", jdbcType = JdbcType.DECIMAL),
        @Result(column = "IN_MAC_AMT", property = "inMacAmt", jdbcType = JdbcType.DECIMAL),
        @Result(column = "LAST_DEAL_TIME", property = "lastDealTime", jdbcType = JdbcType.TIMESTAMP),
        @Result(column = "REFLUX_5_ALLOW_YN", property = "reflux5AllowYn", jdbcType = JdbcType.CHAR) })
    TFnMac selectByJoin2(TFnMacKey cond);

}
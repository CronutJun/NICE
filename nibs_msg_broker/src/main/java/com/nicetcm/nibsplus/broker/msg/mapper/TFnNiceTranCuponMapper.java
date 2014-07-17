package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranCupon;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranCuponKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranCuponSpec;
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

public interface TFnNiceTranCuponMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @SelectProvider(type=TFnNiceTranCuponSqlProvider.class, method="countBySpec")
    int countBySpec(TFnNiceTranCuponSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @DeleteProvider(type=TFnNiceTranCuponSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnNiceTranCuponSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_NICE_TRAN_CUPON",
        "where DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnNiceTranCuponKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_NICE_TRAN_CUPON (DEAL_DATE, MAC_NO, ",
        "ATM_DEAL_NO, DEAL_TYPE, ",
        "DEAL_CLSS, INST_ORG_CD, ",
        "INST_BRANCH_CD, ACCOUNT_NO, ",
        "REAL_ACCOUNT_NO, DEAL_AMT, ",
        "ORG_CD, DEAL_YEAR, ",
        "DEAL_MMDD, DEAL_NO, ",
        "DEAL_STATUS, DEAL_TIME_TYPE, ",
        "DEAL_TIME, DEAL_HOUR, ",
        "DEAL_MI, DEAL_SEC, ",
        "ORNZ_CD, NET_ORG_CD, ",
        "CHECK_AMT, FORNZ_CD, ",
        "DEAL_IN_AMT_CW15, ",
        "DEAL_IN_AMT_CW54, DEAL_IN_AMT_CW14, ",
        "DEAL_IN_AMT_CW53, DEAL_IN_AMT_CW13, ",
        "DEAL_OUT_AMT_CW54, DEAL_OUT_AMT_CW14, ",
        "DEAL_OUT_AMT_CW53, DEAL_OUT_AMT_CW13, ",
        "DEAL_OUT_AMT_CW52, DEAL_OUT_AMT_CW12, ",
        "DEAL_OUT_AMT_CW51, DEAL_OUT_AMT_CW11, ",
        "INSERT_DATE, INSERT_UID)",
        "values (#{dealDate,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
        "#{atmDealNo,jdbcType=VARCHAR}, #{dealType,jdbcType=VARCHAR}, ",
        "#{dealClss,jdbcType=VARCHAR}, #{instOrgCd,jdbcType=VARCHAR}, ",
        "OP.FC_FN_SECURITY(#{instBranchCd,jdbcType=VARCHAR}, '1'), OP.FC_FN_SECURITY(#{accountNo,jdbcType=VARCHAR}, '1'), ",
        "OP.FC_FN_SECURITY(#{realAccountNo,jdbcType=VARCHAR}, '1'), #{dealAmt,jdbcType=DECIMAL}, ",
        "#{orgCd,jdbcType=VARCHAR}, #{dealYear,jdbcType=VARCHAR}, ",
        "#{dealMmdd,jdbcType=VARCHAR}, #{dealNo,jdbcType=VARCHAR}, ",
        "#{dealStatus,jdbcType=VARCHAR}, #{dealTimeType,jdbcType=VARCHAR}, ",
        "#{dealTime,jdbcType=VARCHAR}, #{dealHour,jdbcType=VARCHAR}, ",
        "#{dealMi,jdbcType=VARCHAR}, #{dealSec,jdbcType=VARCHAR}, ",
        "OP.FC_GET_ORNZ_CD_BY_MACNO('D', '096', '9600', #{macNo,jdbcType=VARCHAR}), #{netOrgCd,jdbcType=VARCHAR}, ",
        "#{checkAmt,jdbcType=DECIMAL}, OP.FC_GET_ORNZ_CD_BY_MACNO('FD', '096', '9600', #{macNo,jdbcType=VARCHAR}), ",
        "#{dealInAmtCw15,jdbcType=DECIMAL}, ",
        "#{dealInAmtCw54,jdbcType=DECIMAL}, #{dealInAmtCw14,jdbcType=DECIMAL}, ",
        "#{dealInAmtCw53,jdbcType=DECIMAL}, #{dealInAmtCw13,jdbcType=DECIMAL}, ",
        "#{dealOutAmtCw54,jdbcType=DECIMAL}, #{dealOutAmtCw14,jdbcType=DECIMAL}, ",
        "#{dealOutAmtCw53,jdbcType=DECIMAL}, #{dealOutAmtCw13,jdbcType=DECIMAL}, ",
        "#{dealOutAmtCw52,jdbcType=DECIMAL}, #{dealOutAmtCw12,jdbcType=DECIMAL}, ",
        "#{dealOutAmtCw51,jdbcType=DECIMAL}, #{dealOutAmtCw11,jdbcType=DECIMAL}, ",
        "#{insertDate,jdbcType=TIMESTAMP}, #{insertUid,jdbcType=VARCHAR})"
    })
    int insert(TFnNiceTranCupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @InsertProvider(type=TFnNiceTranCuponSqlProvider.class, method="insertSelective")
    int insertSelective(TFnNiceTranCupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @SelectProvider(type=TFnNiceTranCuponSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ATM_DEAL_NO", property="atmDealNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_TYPE", property="dealType", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_CLSS", property="dealClss", jdbcType=JdbcType.VARCHAR),
        @Result(column="INST_ORG_CD", property="instOrgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="INST_BRANCH_CD", property="instBranchCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCOUNT_NO", property="accountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="REAL_ACCOUNT_NO", property="realAccountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_AMT", property="dealAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_YEAR", property="dealYear", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_MMDD", property="dealMmdd", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_NO", property="dealNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_STATUS", property="dealStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_TIME_TYPE", property="dealTimeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_TIME", property="dealTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_HOUR", property="dealHour", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_MI", property="dealMi", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_SEC", property="dealSec", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORNZ_CD", property="ornzCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="NET_ORG_CD", property="netOrgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHECK_AMT", property="checkAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="FORNZ_CD", property="fornzCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_IN_AMT_CW15", property="dealInAmtCw15", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_IN_AMT_CW54", property="dealInAmtCw54", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_IN_AMT_CW14", property="dealInAmtCw14", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_IN_AMT_CW53", property="dealInAmtCw53", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_IN_AMT_CW13", property="dealInAmtCw13", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW54", property="dealOutAmtCw54", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW14", property="dealOutAmtCw14", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW53", property="dealOutAmtCw53", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW13", property="dealOutAmtCw13", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW52", property="dealOutAmtCw52", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW12", property="dealOutAmtCw12", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW51", property="dealOutAmtCw51", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW11", property="dealOutAmtCw11", jdbcType=JdbcType.DECIMAL),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR)
    })
    List<TFnNiceTranCupon> selectBySpec(TFnNiceTranCuponSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @Select({
        "select",
        "DEAL_DATE, MAC_NO, ATM_DEAL_NO, DEAL_TYPE, DEAL_CLSS, INST_ORG_CD, INST_BRANCH_CD, ",
        "ACCOUNT_NO, REAL_ACCOUNT_NO, DEAL_AMT, ORG_CD, DEAL_YEAR, DEAL_MMDD, DEAL_NO, ",
        "DEAL_STATUS, DEAL_TIME_TYPE, DEAL_TIME, DEAL_HOUR, DEAL_MI, DEAL_SEC, ORNZ_CD, ",
        "NET_ORG_CD, CHECK_AMT, FORNZ_CD, DEAL_IN_AMT_CW15, DEAL_IN_AMT_CW54, ",
        "DEAL_IN_AMT_CW14, DEAL_IN_AMT_CW53, DEAL_IN_AMT_CW13, DEAL_OUT_AMT_CW54, DEAL_OUT_AMT_CW14, ",
        "DEAL_OUT_AMT_CW53, DEAL_OUT_AMT_CW13, DEAL_OUT_AMT_CW52, DEAL_OUT_AMT_CW12, ",
        "DEAL_OUT_AMT_CW51, DEAL_OUT_AMT_CW11, INSERT_DATE, INSERT_UID",
        "from OP.T_FN_NICE_TRAN_CUPON",
        "where DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ATM_DEAL_NO", property="atmDealNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_TYPE", property="dealType", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_CLSS", property="dealClss", jdbcType=JdbcType.VARCHAR),
        @Result(column="INST_ORG_CD", property="instOrgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="INST_BRANCH_CD", property="instBranchCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCOUNT_NO", property="accountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="REAL_ACCOUNT_NO", property="realAccountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_AMT", property="dealAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_YEAR", property="dealYear", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_MMDD", property="dealMmdd", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_NO", property="dealNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_STATUS", property="dealStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_TIME_TYPE", property="dealTimeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_TIME", property="dealTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_HOUR", property="dealHour", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_MI", property="dealMi", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_SEC", property="dealSec", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORNZ_CD", property="ornzCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="NET_ORG_CD", property="netOrgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHECK_AMT", property="checkAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="FORNZ_CD", property="fdornzCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_IN_AMT_CW15", property="dealInAmtCw15", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_IN_AMT_CW54", property="dealInAmtCw54", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_IN_AMT_CW14", property="dealInAmtCw14", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_IN_AMT_CW53", property="dealInAmtCw53", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_IN_AMT_CW13", property="dealInAmtCw13", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW54", property="dealOutAmtCw54", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW14", property="dealOutAmtCw14", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW53", property="dealOutAmtCw53", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW13", property="dealOutAmtCw13", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW52", property="dealOutAmtCw52", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW12", property="dealOutAmtCw12", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW51", property="dealOutAmtCw51", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEAL_OUT_AMT_CW11", property="dealOutAmtCw11", jdbcType=JdbcType.DECIMAL),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR)
    })
    TFnNiceTranCupon selectByPrimaryKey(TFnNiceTranCuponKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @UpdateProvider(type=TFnNiceTranCuponSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnNiceTranCupon record, @Param("spec") TFnNiceTranCuponSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @UpdateProvider(type=TFnNiceTranCuponSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnNiceTranCupon record, @Param("spec") TFnNiceTranCuponSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @UpdateProvider(type=TFnNiceTranCuponSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnNiceTranCupon record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_CUPON
     *
     * @mbggenerated Tue Jul 08 11:42:20 KST 2014
     */
    @Update({
        "update OP.T_FN_NICE_TRAN_CUPON",
        "set DEAL_TYPE = #{dealType,jdbcType=VARCHAR},",
          "DEAL_CLSS = #{dealClss,jdbcType=VARCHAR},",
          "INST_ORG_CD = #{instOrgCd,jdbcType=VARCHAR},",
          "INST_BRANCH_CD = #{instBranchCd,jdbcType=VARCHAR},",
          "ACCOUNT_NO = #{accountNo,jdbcType=VARCHAR},",
          "REAL_ACCOUNT_NO = #{realAccountNo,jdbcType=VARCHAR},",
          "DEAL_AMT = #{dealAmt,jdbcType=DECIMAL},",
          "ORG_CD = #{orgCd,jdbcType=VARCHAR},",
          "DEAL_YEAR = #{dealYear,jdbcType=VARCHAR},",
          "DEAL_MMDD = #{dealMmdd,jdbcType=VARCHAR},",
          "DEAL_NO = #{dealNo,jdbcType=VARCHAR},",
          "DEAL_STATUS = #{dealStatus,jdbcType=VARCHAR},",
          "DEAL_TIME_TYPE = #{dealTimeType,jdbcType=VARCHAR},",
          "DEAL_TIME = #{dealTime,jdbcType=VARCHAR},",
          "DEAL_HOUR = #{dealHour,jdbcType=VARCHAR},",
          "DEAL_MI = #{dealMi,jdbcType=VARCHAR},",
          "DEAL_SEC = #{dealSec,jdbcType=VARCHAR},",
          "ORNZ_CD = #{ornzCd,jdbcType=VARCHAR},",
          "NET_ORG_CD = #{netOrgCd,jdbcType=VARCHAR},",
          "CHECK_AMT = #{checkAmt,jdbcType=DECIMAL},",
          "FORNZ_CD = #{fornzCd,jdbcType=VARCHAR},",
          "DEAL_IN_AMT_CW15 = #{dealInAmtCw15,jdbcType=DECIMAL},",
          "DEAL_IN_AMT_CW54 = #{dealInAmtCw54,jdbcType=DECIMAL},",
          "DEAL_IN_AMT_CW14 = #{dealInAmtCw14,jdbcType=DECIMAL},",
          "DEAL_IN_AMT_CW53 = #{dealInAmtCw53,jdbcType=DECIMAL},",
          "DEAL_IN_AMT_CW13 = #{dealInAmtCw13,jdbcType=DECIMAL},",
          "DEAL_OUT_AMT_CW54 = #{dealOutAmtCw54,jdbcType=DECIMAL},",
          "DEAL_OUT_AMT_CW14 = #{dealOutAmtCw14,jdbcType=DECIMAL},",
          "DEAL_OUT_AMT_CW53 = #{dealOutAmtCw53,jdbcType=DECIMAL},",
          "DEAL_OUT_AMT_CW13 = #{dealOutAmtCw13,jdbcType=DECIMAL},",
          "DEAL_OUT_AMT_CW52 = #{dealOutAmtCw52,jdbcType=DECIMAL},",
          "DEAL_OUT_AMT_CW12 = #{dealOutAmtCw12,jdbcType=DECIMAL},",
          "DEAL_OUT_AMT_CW51 = #{dealOutAmtCw51,jdbcType=DECIMAL},",
          "DEAL_OUT_AMT_CW11 = #{dealOutAmtCw11,jdbcType=DECIMAL},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR}",
        "where DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnNiceTranCupon record);
}
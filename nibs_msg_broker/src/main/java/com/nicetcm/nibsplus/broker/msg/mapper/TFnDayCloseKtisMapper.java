package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseKtis;
import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseKtisKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnDayCloseKtisSpec;
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

public interface TFnDayCloseKtisMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @SelectProvider(type=TFnDayCloseKtisSqlProvider.class, method="countBySpec")
    int countBySpec(TFnDayCloseKtisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @DeleteProvider(type=TFnDayCloseKtisSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnDayCloseKtisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_DAY_CLOSE_KTIS",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and CLOSE_TYPE = #{closeType,jdbcType=VARCHAR}",
          "and CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}",
          "and CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnDayCloseKtisKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_DAY_CLOSE_KTIS (ORG_CD, BRANCH_CD, ",
        "MAC_NO, CLOSE_TYPE, ",
        "CLOSE_DATE, CLOSE_TIME, ",
        "CASH_OUT_AMT, UPDATE_DATE, ",
        "UPDATE_UID, DEPT_CD, ",
        "OFFICE_CD)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{closeType,jdbcType=VARCHAR}, ",
        "#{closeDate,jdbcType=VARCHAR}, #{closeTime,jdbcType=VARCHAR}, ",
        "#{cashOutAmt,jdbcType=DECIMAL}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{updateUid,jdbcType=VARCHAR}, #{deptCd,jdbcType=VARCHAR}, ",
        "#{officeCd,jdbcType=VARCHAR})"
    })
    int insert(TFnDayCloseKtis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @InsertProvider(type=TFnDayCloseKtisSqlProvider.class, method="insertSelective")
    int insertSelective(TFnDayCloseKtis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @SelectProvider(type=TFnDayCloseKtisSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TYPE", property="closeType", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_DATE", property="closeDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TIME", property="closeTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CASH_OUT_AMT", property="cashOutAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR)
    })
    List<TFnDayCloseKtis> selectBySpec(TFnDayCloseKtisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, BRANCH_CD, MAC_NO, CLOSE_TYPE, CLOSE_DATE, CLOSE_TIME, CASH_OUT_AMT, ",
        "UPDATE_DATE, UPDATE_UID, DEPT_CD, OFFICE_CD",
        "from OP.T_FN_DAY_CLOSE_KTIS",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and CLOSE_TYPE = #{closeType,jdbcType=VARCHAR}",
          "and CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}",
          "and CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TYPE", property="closeType", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_DATE", property="closeDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TIME", property="closeTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CASH_OUT_AMT", property="cashOutAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR)
    })
    TFnDayCloseKtis selectByPrimaryKey(TFnDayCloseKtisKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TFnDayCloseKtisSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnDayCloseKtis record, @Param("spec") TFnDayCloseKtisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TFnDayCloseKtisSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnDayCloseKtis record, @Param("spec") TFnDayCloseKtisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TFnDayCloseKtisSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnDayCloseKtis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_DAY_CLOSE_KTIS
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Update({
        "update OP.T_FN_DAY_CLOSE_KTIS",
        "set CASH_OUT_AMT = #{cashOutAmt,jdbcType=DECIMAL},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
          "DEPT_CD = #{deptCd,jdbcType=VARCHAR},",
          "OFFICE_CD = #{officeCd,jdbcType=VARCHAR}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and CLOSE_TYPE = #{closeType,jdbcType=VARCHAR}",
          "and CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}",
          "and CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnDayCloseKtis record);
}
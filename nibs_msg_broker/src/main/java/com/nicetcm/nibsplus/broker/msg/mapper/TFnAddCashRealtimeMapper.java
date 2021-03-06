package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnAddCashRealtime;
import com.nicetcm.nibsplus.broker.msg.model.TFnAddCashRealtimeKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnAddCashRealtimeSpec;
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

public interface TFnAddCashRealtimeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @SelectProvider(type=TFnAddCashRealtimeSqlProvider.class, method="countBySpec")
    int countBySpec(TFnAddCashRealtimeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @DeleteProvider(type=TFnAddCashRealtimeSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnAddCashRealtimeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_ADD_CASH_REALTIME",
        "where ADD_DATE = #{addDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and ADD_TIME = #{addTime,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnAddCashRealtimeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_ADD_CASH_REALTIME (ADD_DATE, ORG_CD, ",
        "BRANCH_CD, MAC_NO, ",
        "ADD_TIME, ADD_AMT, ",
        "ORG_SEND_YN, INSERT_DATE, ",
        "UPDATE_DATE, UPDATE_UID)",
        "values (#{addDate,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{branchCd,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
        "#{addTime,jdbcType=VARCHAR}, #{addAmt,jdbcType=DECIMAL}, ",
        "#{orgSendYn,jdbcType=VARCHAR}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{updateUid,jdbcType=VARCHAR})"
    })
    int insert(TFnAddCashRealtime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @InsertProvider(type=TFnAddCashRealtimeSqlProvider.class, method="insertSelective")
    int insertSelective(TFnAddCashRealtime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @SelectProvider(type=TFnAddCashRealtimeSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ADD_DATE", property="addDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ADD_TIME", property="addTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ADD_AMT", property="addAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    List<TFnAddCashRealtime> selectBySpec(TFnAddCashRealtimeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Select({
        "select",
        "ADD_DATE, ORG_CD, BRANCH_CD, MAC_NO, ADD_TIME, ADD_AMT, ORG_SEND_YN, INSERT_DATE, ",
        "UPDATE_DATE, UPDATE_UID",
        "from OP.T_FN_ADD_CASH_REALTIME",
        "where ADD_DATE = #{addDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and ADD_TIME = #{addTime,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ADD_DATE", property="addDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ADD_TIME", property="addTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ADD_AMT", property="addAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    TFnAddCashRealtime selectByPrimaryKey(TFnAddCashRealtimeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @UpdateProvider(type=TFnAddCashRealtimeSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnAddCashRealtime record, @Param("spec") TFnAddCashRealtimeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @UpdateProvider(type=TFnAddCashRealtimeSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnAddCashRealtime record, @Param("spec") TFnAddCashRealtimeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @UpdateProvider(type=TFnAddCashRealtimeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnAddCashRealtime record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ADD_CASH_REALTIME
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Update({
        "update OP.T_FN_ADD_CASH_REALTIME",
        "set ADD_AMT = #{addAmt,jdbcType=DECIMAL},",
          "ORG_SEND_YN = #{orgSendYn,jdbcType=VARCHAR},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR}",
        "where ADD_DATE = #{addDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and ADD_TIME = #{addTime,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnAddCashRealtime record);
}
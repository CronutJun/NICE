package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TPhUser;
import com.nicetcm.nibsplus.broker.msg.model.TPhUserSpec;
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

public interface TPhUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @SelectProvider(type=TPhUserSqlProvider.class, method="countBySpec")
    int countBySpec(TPhUserSpec Spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @DeleteProvider(type=TPhUserSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TPhUserSpec Spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @Delete({
        "delete from OP.T_PH_USER",
        "where USER_IDX = #{userIdx,jdbcType=DECIMAL}"
    })
    int deleteByPrimaryKey(Long userIdx);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @Insert({
        "insert into OP.T_PH_USER (USER_IDX, PHONE_NUMBER, ",
        "USER_NUMBER, STATUS, ",
        "DEVICE_TOKEN, DEVICE_MODEL, ",
        "DEVICE_VERSION, APP_VERSION, ",
        "CREATE_DATE, CREATE_TIME, ",
        "UPDATE_DATE, UPDATE_TIME, ",
        "WORKER)",
        "values (#{userIdx,jdbcType=DECIMAL}, #{phoneNumber,jdbcType=VARCHAR}, ",
        "#{userNumber,jdbcType=VARCHAR}, #{status,jdbcType=VARCHAR}, ",
        "#{deviceToken,jdbcType=VARCHAR}, #{deviceModel,jdbcType=VARCHAR}, ",
        "#{deviceVersion,jdbcType=VARCHAR}, #{appVersion,jdbcType=VARCHAR}, ",
        "#{createDate,jdbcType=VARCHAR}, #{createTime,jdbcType=VARCHAR}, ",
        "#{updateDate,jdbcType=VARCHAR}, #{updateTime,jdbcType=VARCHAR}, ",
        "#{worker,jdbcType=VARCHAR})"
    })
    int insert(TPhUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @InsertProvider(type=TPhUserSqlProvider.class, method="insertSelective")
    int insertSelective(TPhUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @SelectProvider(type=TPhUserSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="USER_IDX", property="userIdx", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="PHONE_NUMBER", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_NUMBER", property="userNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEVICE_TOKEN", property="deviceToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEVICE_MODEL", property="deviceModel", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEVICE_VERSION", property="deviceVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="APP_VERSION", property="appVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORKER", property="worker", jdbcType=JdbcType.VARCHAR)
    })
    List<TPhUser> selectBySpec(TPhUserSpec Spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @Select({
        "select",
        "USER_IDX, PHONE_NUMBER, USER_NUMBER, STATUS, DEVICE_TOKEN, DEVICE_MODEL, DEVICE_VERSION, ",
        "APP_VERSION, CREATE_DATE, CREATE_TIME, UPDATE_DATE, UPDATE_TIME, WORKER",
        "from OP.T_PH_USER",
        "where USER_IDX = #{userIdx,jdbcType=DECIMAL}"
    })
    @Results({
        @Result(column="USER_IDX", property="userIdx", jdbcType=JdbcType.DECIMAL, id=true),
        @Result(column="PHONE_NUMBER", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="USER_NUMBER", property="userNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEVICE_TOKEN", property="deviceToken", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEVICE_MODEL", property="deviceModel", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEVICE_VERSION", property="deviceVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="APP_VERSION", property="appVersion", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_TIME", property="updateTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORKER", property="worker", jdbcType=JdbcType.VARCHAR)
    })
    TPhUser selectByPrimaryKey(Long userIdx);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @UpdateProvider(type=TPhUserSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TPhUser record, @Param("Spec") TPhUserSpec Spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @UpdateProvider(type=TPhUserSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TPhUser record, @Param("Spec") TPhUserSpec Spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @UpdateProvider(type=TPhUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TPhUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_PH_USER
     *
     * @mbggenerated Thu Nov 06 17:17:41 KST 2014
     */
    @Update({
        "update OP.T_PH_USER",
        "set PHONE_NUMBER = #{phoneNumber,jdbcType=VARCHAR},",
          "USER_NUMBER = #{userNumber,jdbcType=VARCHAR},",
          "STATUS = #{status,jdbcType=VARCHAR},",
          "DEVICE_TOKEN = #{deviceToken,jdbcType=VARCHAR},",
          "DEVICE_MODEL = #{deviceModel,jdbcType=VARCHAR},",
          "DEVICE_VERSION = #{deviceVersion,jdbcType=VARCHAR},",
          "APP_VERSION = #{appVersion,jdbcType=VARCHAR},",
          "CREATE_DATE = #{createDate,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=VARCHAR},",
          "UPDATE_TIME = #{updateTime,jdbcType=VARCHAR},",
          "WORKER = #{worker,jdbcType=VARCHAR}",
        "where USER_IDX = #{userIdx,jdbcType=DECIMAL}"
    })
    int updateByPrimaryKey(TPhUser record);
}
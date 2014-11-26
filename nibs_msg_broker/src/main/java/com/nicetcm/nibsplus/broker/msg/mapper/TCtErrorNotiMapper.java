package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNoti;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNotiKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorNotiSpec;
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

public interface TCtErrorNotiMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @SelectProvider(type=TCtErrorNotiSqlProvider.class, method="countBySpec")
    int countBySpec(TCtErrorNotiSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @DeleteProvider(type=TCtErrorNotiSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCtErrorNotiSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @Delete({
        "delete from OP.T_CT_ERROR_NOTI",
        "where ERROR_NO = #{errorNo,jdbcType=VARCHAR}",
          "and CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and CREATE_TIME = #{createTime,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCtErrorNotiKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @Insert({
        "insert into OP.T_CT_ERROR_NOTI (ERROR_NO, CREATE_DATE, ",
        "CREATE_TIME, SEND_TYPE, ",
        "SEND_DATE, SEND_TIME, ",
        "SEND_NM, SEND_UID, ",
        "SEND_CHECK_YN, SEND_CHECK_DATETIME, ",
        "SEND_PLAN_DATETIME, SEND_PLAN_NM, ",
        "SEND_PLAN_UID, SEND_STATUS, ",
        "SEND_TOOL, SEND_SMS_STATUS, ",
        "ORG_USER_TYPE, ORG_USER_NM, ",
        "RECV_PLACE, RECV_USER_NM, ",
        "RECV_USER_UID, RECV_TELE_NO, ",
        "RECV_DATE, RECV_TIME, ",
        "RECV_PDA_DATE, RECV_PDA_TIME, ",
        "RECV_XPOS, RECV_YPOS, ",
        "RECV_SIDO, RECV_GUGUN, ",
        "RECV_DONG, ARRIVAL_EST_DATE, ",
        "ARRIVAL_EST_TIME, REG_DT, ",
        "REG_ID, UPDATE_DATE, ",
        "UPDATE_UID)",
        "values (#{errorNo,jdbcType=VARCHAR}, #{createDate,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=VARCHAR}, #{sendType,jdbcType=CHAR}, ",
        "#{sendDate,jdbcType=VARCHAR}, #{sendTime,jdbcType=VARCHAR}, ",
        "#{sendNm,jdbcType=VARCHAR}, #{sendUid,jdbcType=VARCHAR}, ",
        "#{sendCheckYn,jdbcType=CHAR}, #{sendCheckDatetime,jdbcType=TIMESTAMP}, ",
        "#{sendPlanDatetime,jdbcType=TIMESTAMP}, #{sendPlanNm,jdbcType=VARCHAR}, ",
        "#{sendPlanUid,jdbcType=VARCHAR}, #{sendStatus,jdbcType=CHAR}, ",
        "#{sendTool,jdbcType=VARCHAR}, #{sendSmsStatus,jdbcType=VARCHAR}, ",
        "#{orgUserType,jdbcType=VARCHAR}, #{orgUserNm,jdbcType=VARCHAR}, ",
        "#{recvPlace,jdbcType=VARCHAR}, #{recvUserNm,jdbcType=VARCHAR}, ",
        "#{recvUserUid,jdbcType=VARCHAR}, #{recvTeleNo,jdbcType=VARCHAR}, ",
        "#{recvDate,jdbcType=VARCHAR}, #{recvTime,jdbcType=VARCHAR}, ",
        "#{recvPdaDate,jdbcType=VARCHAR}, #{recvPdaTime,jdbcType=VARCHAR}, ",
        "#{recvXpos,jdbcType=VARCHAR}, #{recvYpos,jdbcType=VARCHAR}, ",
        "#{recvSido,jdbcType=VARCHAR}, #{recvGugun,jdbcType=VARCHAR}, ",
        "#{recvDong,jdbcType=VARCHAR}, #{arrivalEstDate,jdbcType=VARCHAR}, ",
        "#{arrivalEstTime,jdbcType=VARCHAR}, #{regDt,jdbcType=TIMESTAMP}, ",
        "#{regId,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{updateUid,jdbcType=VARCHAR})"
    })
    int insert(TCtErrorNoti record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @InsertProvider(type=TCtErrorNotiSqlProvider.class, method="insertSelective")
    int insertSelective(TCtErrorNoti record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @SelectProvider(type=TCtErrorNotiSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SEND_TYPE", property="sendType", jdbcType=JdbcType.CHAR),
        @Result(column="SEND_DATE", property="sendDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_TIME", property="sendTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_NM", property="sendNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_UID", property="sendUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_CHECK_YN", property="sendCheckYn", jdbcType=JdbcType.CHAR),
        @Result(column="SEND_CHECK_DATETIME", property="sendCheckDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SEND_PLAN_DATETIME", property="sendPlanDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SEND_PLAN_NM", property="sendPlanNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_PLAN_UID", property="sendPlanUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_STATUS", property="sendStatus", jdbcType=JdbcType.CHAR),
        @Result(column="SEND_TOOL", property="sendTool", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_SMS_STATUS", property="sendSmsStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_USER_TYPE", property="orgUserType", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_USER_NM", property="orgUserNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PLACE", property="recvPlace", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_USER_NM", property="recvUserNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_USER_UID", property="recvUserUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_TELE_NO", property="recvTeleNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_DATE", property="recvDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_TIME", property="recvTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PDA_DATE", property="recvPdaDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PDA_TIME", property="recvPdaTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_XPOS", property="recvXpos", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_YPOS", property="recvYpos", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_SIDO", property="recvSido", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_GUGUN", property="recvGugun", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_DONG", property="recvDong", jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_EST_DATE", property="arrivalEstDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_EST_TIME", property="arrivalEstTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="REG_DT", property="regDt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REG_ID", property="regId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    List<TCtErrorNoti> selectBySpec(TCtErrorNotiSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @Select({
        "select",
        "ERROR_NO, CREATE_DATE, CREATE_TIME, SEND_TYPE, SEND_DATE, SEND_TIME, SEND_NM, ",
        "SEND_UID, SEND_CHECK_YN, SEND_CHECK_DATETIME, SEND_PLAN_DATETIME, SEND_PLAN_NM, ",
        "SEND_PLAN_UID, SEND_STATUS, SEND_TOOL, SEND_SMS_STATUS, ORG_USER_TYPE, ORG_USER_NM, ",
        "RECV_PLACE, RECV_USER_NM, RECV_USER_UID, RECV_TELE_NO, RECV_DATE, RECV_TIME, ",
        "RECV_PDA_DATE, RECV_PDA_TIME, RECV_XPOS, RECV_YPOS, RECV_SIDO, RECV_GUGUN, RECV_DONG, ",
        "ARRIVAL_EST_DATE, ARRIVAL_EST_TIME, REG_DT, REG_ID, UPDATE_DATE, UPDATE_UID",
        "from OP.T_CT_ERROR_NOTI",
        "where ERROR_NO = #{errorNo,jdbcType=VARCHAR}",
          "and CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and CREATE_TIME = #{createTime,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SEND_TYPE", property="sendType", jdbcType=JdbcType.CHAR),
        @Result(column="SEND_DATE", property="sendDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_TIME", property="sendTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_NM", property="sendNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_UID", property="sendUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_CHECK_YN", property="sendCheckYn", jdbcType=JdbcType.CHAR),
        @Result(column="SEND_CHECK_DATETIME", property="sendCheckDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SEND_PLAN_DATETIME", property="sendPlanDatetime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="SEND_PLAN_NM", property="sendPlanNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_PLAN_UID", property="sendPlanUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_STATUS", property="sendStatus", jdbcType=JdbcType.CHAR),
        @Result(column="SEND_TOOL", property="sendTool", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_SMS_STATUS", property="sendSmsStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_USER_TYPE", property="orgUserType", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_USER_NM", property="orgUserNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PLACE", property="recvPlace", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_USER_NM", property="recvUserNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_USER_UID", property="recvUserUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_TELE_NO", property="recvTeleNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_DATE", property="recvDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_TIME", property="recvTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PDA_DATE", property="recvPdaDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PDA_TIME", property="recvPdaTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_XPOS", property="recvXpos", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_YPOS", property="recvYpos", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_SIDO", property="recvSido", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_GUGUN", property="recvGugun", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_DONG", property="recvDong", jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_EST_DATE", property="arrivalEstDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_EST_TIME", property="arrivalEstTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="REG_DT", property="regDt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REG_ID", property="regId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    TCtErrorNoti selectByPrimaryKey(TCtErrorNotiKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @UpdateProvider(type=TCtErrorNotiSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCtErrorNoti record, @Param("spec") TCtErrorNotiSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @UpdateProvider(type=TCtErrorNotiSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCtErrorNoti record, @Param("spec") TCtErrorNotiSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @UpdateProvider(type=TCtErrorNotiSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCtErrorNoti record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_NOTI
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @Update({
        "update OP.T_CT_ERROR_NOTI",
        "set SEND_TYPE = #{sendType,jdbcType=CHAR},",
          "SEND_DATE = #{sendDate,jdbcType=VARCHAR},",
          "SEND_TIME = #{sendTime,jdbcType=VARCHAR},",
          "SEND_NM = #{sendNm,jdbcType=VARCHAR},",
          "SEND_UID = #{sendUid,jdbcType=VARCHAR},",
          "SEND_CHECK_YN = #{sendCheckYn,jdbcType=CHAR},",
          "SEND_CHECK_DATETIME = #{sendCheckDatetime,jdbcType=TIMESTAMP},",
          "SEND_PLAN_DATETIME = #{sendPlanDatetime,jdbcType=TIMESTAMP},",
          "SEND_PLAN_NM = #{sendPlanNm,jdbcType=VARCHAR},",
          "SEND_PLAN_UID = #{sendPlanUid,jdbcType=VARCHAR},",
          "SEND_STATUS = #{sendStatus,jdbcType=CHAR},",
          "SEND_TOOL = #{sendTool,jdbcType=VARCHAR},",
          "SEND_SMS_STATUS = #{sendSmsStatus,jdbcType=VARCHAR},",
          "ORG_USER_TYPE = #{orgUserType,jdbcType=VARCHAR},",
          "ORG_USER_NM = #{orgUserNm,jdbcType=VARCHAR},",
          "RECV_PLACE = #{recvPlace,jdbcType=VARCHAR},",
          "RECV_USER_NM = #{recvUserNm,jdbcType=VARCHAR},",
          "RECV_USER_UID = #{recvUserUid,jdbcType=VARCHAR},",
          "RECV_TELE_NO = #{recvTeleNo,jdbcType=VARCHAR},",
          "RECV_DATE = #{recvDate,jdbcType=VARCHAR},",
          "RECV_TIME = #{recvTime,jdbcType=VARCHAR},",
          "RECV_PDA_DATE = #{recvPdaDate,jdbcType=VARCHAR},",
          "RECV_PDA_TIME = #{recvPdaTime,jdbcType=VARCHAR},",
          "RECV_XPOS = #{recvXpos,jdbcType=VARCHAR},",
          "RECV_YPOS = #{recvYpos,jdbcType=VARCHAR},",
          "RECV_SIDO = #{recvSido,jdbcType=VARCHAR},",
          "RECV_GUGUN = #{recvGugun,jdbcType=VARCHAR},",
          "RECV_DONG = #{recvDong,jdbcType=VARCHAR},",
          "ARRIVAL_EST_DATE = #{arrivalEstDate,jdbcType=VARCHAR},",
          "ARRIVAL_EST_TIME = #{arrivalEstTime,jdbcType=VARCHAR},",
          "REG_DT = #{regDt,jdbcType=TIMESTAMP},",
          "REG_ID = #{regId,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR}",
        "where ERROR_NO = #{errorNo,jdbcType=VARCHAR}",
          "and CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and CREATE_TIME = #{createTime,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCtErrorNoti record);
}
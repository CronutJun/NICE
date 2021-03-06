package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCmSetSchedule;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetScheduleKey;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetScheduleSpec;
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

public interface TCmSetScheduleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @SelectProvider(type=TCmSetScheduleSqlProvider.class, method="countBySpec")
    int countBySpec(TCmSetScheduleSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @DeleteProvider(type=TCmSetScheduleSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCmSetScheduleSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @Delete({
        "delete from OP.T_CM_SET_SCHEDULE",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and WORK_SEQ = #{workSeq,jdbcType=VARCHAR}",
          "and WORK_CLASS = #{workClass,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCmSetScheduleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @Insert({
        "insert into OP.T_CM_SET_SCHEDULE (ORG_CD, WORK_SEQ, ",
        "WORK_CLASS, CHANGE_NO, ",
        "ACCEPT_DATE, ACCEPT_TIME, ",
        "APPLY_DATE, SETUP_DATE, ",
        "SET_MAC_CNT, SET_FACIL_CNT, ",
        "BRANCH_NM, SITE_NM, ",
        "BRANCH_MAN_NM, TELE_NO, ",
        "WORK_MSG, WORK_RESULT, ",
        "UPDATE_DATE, UPDATE_UID, ",
        "DATA_TYPE)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{workSeq,jdbcType=VARCHAR}, ",
        "#{workClass,jdbcType=VARCHAR}, #{changeNo,jdbcType=DECIMAL}, ",
        "#{acceptDate,jdbcType=VARCHAR}, #{acceptTime,jdbcType=VARCHAR}, ",
        "#{applyDate,jdbcType=VARCHAR}, #{setupDate,jdbcType=VARCHAR}, ",
        "#{setMacCnt,jdbcType=DECIMAL}, #{setFacilCnt,jdbcType=DECIMAL}, ",
        "#{branchNm,jdbcType=VARCHAR}, #{siteNm,jdbcType=VARCHAR}, ",
        "#{branchManNm,jdbcType=VARCHAR}, #{teleNo,jdbcType=VARCHAR}, ",
        "#{workMsg,jdbcType=VARCHAR}, #{workResult,jdbcType=VARCHAR}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{updateUid,jdbcType=VARCHAR}, ",
        "#{dataType,jdbcType=VARCHAR})"
    })
    int insert(TCmSetSchedule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @InsertProvider(type=TCmSetScheduleSqlProvider.class, method="insertSelective")
    int insertSelective(TCmSetSchedule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @SelectProvider(type=TCmSetScheduleSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_SEQ", property="workSeq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_CLASS", property="workClass", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CHANGE_NO", property="changeNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="ACCEPT_DATE", property="acceptDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCEPT_TIME", property="acceptTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="APPLY_DATE", property="applyDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SETUP_DATE", property="setupDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_MAC_CNT", property="setMacCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SET_FACIL_CNT", property="setFacilCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="BRANCH_NM", property="branchNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_NM", property="siteNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_MAN_NM", property="branchManNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="TELE_NO", property="teleNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_MSG", property="workMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_RESULT", property="workResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="DATA_TYPE", property="dataType", jdbcType=JdbcType.VARCHAR)
    })
    List<TCmSetSchedule> selectBySpec(TCmSetScheduleSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, WORK_SEQ, WORK_CLASS, CHANGE_NO, ACCEPT_DATE, ACCEPT_TIME, APPLY_DATE, ",
        "SETUP_DATE, SET_MAC_CNT, SET_FACIL_CNT, BRANCH_NM, SITE_NM, BRANCH_MAN_NM, TELE_NO, ",
        "WORK_MSG, WORK_RESULT, UPDATE_DATE, UPDATE_UID, DATA_TYPE",
        "from OP.T_CM_SET_SCHEDULE",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and WORK_SEQ = #{workSeq,jdbcType=VARCHAR}",
          "and WORK_CLASS = #{workClass,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_SEQ", property="workSeq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_CLASS", property="workClass", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CHANGE_NO", property="changeNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="ACCEPT_DATE", property="acceptDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCEPT_TIME", property="acceptTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="APPLY_DATE", property="applyDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SETUP_DATE", property="setupDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_MAC_CNT", property="setMacCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SET_FACIL_CNT", property="setFacilCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="BRANCH_NM", property="branchNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_NM", property="siteNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_MAN_NM", property="branchManNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="TELE_NO", property="teleNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_MSG", property="workMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_RESULT", property="workResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="DATA_TYPE", property="dataType", jdbcType=JdbcType.VARCHAR)
    })
    TCmSetSchedule selectByPrimaryKey(TCmSetScheduleKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @UpdateProvider(type=TCmSetScheduleSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCmSetSchedule record, @Param("spec") TCmSetScheduleSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @UpdateProvider(type=TCmSetScheduleSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCmSetSchedule record, @Param("spec") TCmSetScheduleSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @UpdateProvider(type=TCmSetScheduleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCmSetSchedule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SCHEDULE
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @Update({
        "update OP.T_CM_SET_SCHEDULE",
        "set CHANGE_NO = #{changeNo,jdbcType=DECIMAL},",
          "ACCEPT_DATE = #{acceptDate,jdbcType=VARCHAR},",
          "ACCEPT_TIME = #{acceptTime,jdbcType=VARCHAR},",
          "APPLY_DATE = #{applyDate,jdbcType=VARCHAR},",
          "SETUP_DATE = #{setupDate,jdbcType=VARCHAR},",
          "SET_MAC_CNT = #{setMacCnt,jdbcType=DECIMAL},",
          "SET_FACIL_CNT = #{setFacilCnt,jdbcType=DECIMAL},",
          "BRANCH_NM = #{branchNm,jdbcType=VARCHAR},",
          "SITE_NM = #{siteNm,jdbcType=VARCHAR},",
          "BRANCH_MAN_NM = #{branchManNm,jdbcType=VARCHAR},",
          "TELE_NO = #{teleNo,jdbcType=VARCHAR},",
          "WORK_MSG = #{workMsg,jdbcType=VARCHAR},",
          "WORK_RESULT = #{workResult,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
          "DATA_TYPE = #{dataType,jdbcType=VARCHAR}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and WORK_SEQ = #{workSeq,jdbcType=VARCHAR}",
          "and WORK_CLASS = #{workClass,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCmSetSchedule record);
}
package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCmSetSiteInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetSiteInfoKey;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetSiteInfoSpec;
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

public interface TCmSetSiteInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @SelectProvider(type=TCmSetSiteInfoSqlProvider.class, method="countBySpec")
    int countBySpec(TCmSetSiteInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @DeleteProvider(type=TCmSetSiteInfoSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCmSetSiteInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @Delete({
        "delete from OP.T_CM_SET_SITE_INFO",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and WORK_SEQ = #{workSeq,jdbcType=VARCHAR}",
          "and WORK_CLASS = #{workClass,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCmSetSiteInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @Insert({
        "insert into OP.T_CM_SET_SITE_INFO (ORG_CD, WORK_SEQ, ",
        "WORK_CLASS, CHANGE_NO, ",
        "ORG_BRANCH_CD, ORG_BRANCH_NM, ",
        "ORG_SITE_CD, ORG_SITE_NM, ",
        "PLACE_TYPE, JP_TYPE, ",
        "SPECIAL_TYPE, OPER_TIME_TYPE, ",
        "OPER_START_TIME, OPER_END_TIME, ",
        "OPER_DAY, OPER_CHECK_YN, ",
        "SERVICE_FEE, GUARD_CD, ",
        "GUARD_NM, FACIL_RENT_YN, ",
        "SET_ADDR, SET_PLACE, ",
        "MSG, WORK_RESULT, ",
        "UPDATE_DATE, UPDATE_UID, ",
        "DATA_TYPE, OPER_START_TIME_H, ",
        "OPER_END_TIME_H, OUT_START_TIME, ",
        "OUT_END_TIME, OUT_START_TIME_H, ",
        "OUT_END_TIME_H, OUT_COM_CD, ",
        "CLEAN_COM_CD, SET_DATE)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{workSeq,jdbcType=VARCHAR}, ",
        "#{workClass,jdbcType=VARCHAR}, #{changeNo,jdbcType=DECIMAL}, ",
        "#{orgBranchCd,jdbcType=VARCHAR}, #{orgBranchNm,jdbcType=VARCHAR}, ",
        "#{orgSiteCd,jdbcType=VARCHAR}, #{orgSiteNm,jdbcType=VARCHAR}, ",
        "#{placeType,jdbcType=VARCHAR}, #{jpType,jdbcType=VARCHAR}, ",
        "#{specialType,jdbcType=VARCHAR}, #{operTimeType,jdbcType=VARCHAR}, ",
        "#{operStartTime,jdbcType=VARCHAR}, #{operEndTime,jdbcType=VARCHAR}, ",
        "#{operDay,jdbcType=VARCHAR}, #{operCheckYn,jdbcType=VARCHAR}, ",
        "#{serviceFee,jdbcType=VARCHAR}, #{guardCd,jdbcType=VARCHAR}, ",
        "#{guardNm,jdbcType=VARCHAR}, #{facilRentYn,jdbcType=VARCHAR}, ",
        "#{setAddr,jdbcType=VARCHAR}, #{setPlace,jdbcType=VARCHAR}, ",
        "#{msg,jdbcType=VARCHAR}, #{workResult,jdbcType=VARCHAR}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{updateUid,jdbcType=VARCHAR}, ",
        "#{dataType,jdbcType=VARCHAR}, #{operStartTimeH,jdbcType=VARCHAR}, ",
        "#{operEndTimeH,jdbcType=VARCHAR}, #{outStartTime,jdbcType=VARCHAR}, ",
        "#{outEndTime,jdbcType=VARCHAR}, #{outStartTimeH,jdbcType=VARCHAR}, ",
        "#{outEndTimeH,jdbcType=VARCHAR}, #{outComCd,jdbcType=VARCHAR}, ",
        "#{cleanComCd,jdbcType=VARCHAR}, #{setDate,jdbcType=VARCHAR})"
    })
    int insert(TCmSetSiteInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @InsertProvider(type=TCmSetSiteInfoSqlProvider.class, method="insertSelective")
    int insertSelective(TCmSetSiteInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @SelectProvider(type=TCmSetSiteInfoSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_SEQ", property="workSeq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_CLASS", property="workClass", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CHANGE_NO", property="changeNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_BRANCH_CD", property="orgBranchCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_BRANCH_NM", property="orgBranchNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SITE_CD", property="orgSiteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SITE_NM", property="orgSiteNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="PLACE_TYPE", property="placeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="JP_TYPE", property="jpType", jdbcType=JdbcType.VARCHAR),
        @Result(column="SPECIAL_TYPE", property="specialType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_TIME_TYPE", property="operTimeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_START_TIME", property="operStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_END_TIME", property="operEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_DAY", property="operDay", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_CHECK_YN", property="operCheckYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="SERVICE_FEE", property="serviceFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="GUARD_CD", property="guardCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="GUARD_NM", property="guardNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="FACIL_RENT_YN", property="facilRentYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_ADDR", property="setAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_PLACE", property="setPlace", jdbcType=JdbcType.VARCHAR),
        @Result(column="MSG", property="msg", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_RESULT", property="workResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="DATA_TYPE", property="dataType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_START_TIME_H", property="operStartTimeH", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_END_TIME_H", property="operEndTimeH", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_START_TIME", property="outStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_END_TIME", property="outEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_START_TIME_H", property="outStartTimeH", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_END_TIME_H", property="outEndTimeH", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_COM_CD", property="outComCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLEAN_COM_CD", property="cleanComCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_DATE", property="setDate", jdbcType=JdbcType.VARCHAR)
    })
    List<TCmSetSiteInfo> selectBySpec(TCmSetSiteInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, WORK_SEQ, WORK_CLASS, CHANGE_NO, ORG_BRANCH_CD, ORG_BRANCH_NM, ORG_SITE_CD, ",
        "ORG_SITE_NM, PLACE_TYPE, JP_TYPE, SPECIAL_TYPE, OPER_TIME_TYPE, OPER_START_TIME, ",
        "OPER_END_TIME, OPER_DAY, OPER_CHECK_YN, SERVICE_FEE, GUARD_CD, GUARD_NM, FACIL_RENT_YN, ",
        "SET_ADDR, SET_PLACE, MSG, WORK_RESULT, UPDATE_DATE, UPDATE_UID, DATA_TYPE, OPER_START_TIME_H, ",
        "OPER_END_TIME_H, OUT_START_TIME, OUT_END_TIME, OUT_START_TIME_H, OUT_END_TIME_H, ",
        "OUT_COM_CD, CLEAN_COM_CD, SET_DATE",
        "from OP.T_CM_SET_SITE_INFO",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and WORK_SEQ = #{workSeq,jdbcType=VARCHAR}",
          "and WORK_CLASS = #{workClass,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_SEQ", property="workSeq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_CLASS", property="workClass", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CHANGE_NO", property="changeNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_BRANCH_CD", property="orgBranchCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_BRANCH_NM", property="orgBranchNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SITE_CD", property="orgSiteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SITE_NM", property="orgSiteNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="PLACE_TYPE", property="placeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="JP_TYPE", property="jpType", jdbcType=JdbcType.VARCHAR),
        @Result(column="SPECIAL_TYPE", property="specialType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_TIME_TYPE", property="operTimeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_START_TIME", property="operStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_END_TIME", property="operEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_DAY", property="operDay", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_CHECK_YN", property="operCheckYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="SERVICE_FEE", property="serviceFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="GUARD_CD", property="guardCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="GUARD_NM", property="guardNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="FACIL_RENT_YN", property="facilRentYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_ADDR", property="setAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_PLACE", property="setPlace", jdbcType=JdbcType.VARCHAR),
        @Result(column="MSG", property="msg", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_RESULT", property="workResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="DATA_TYPE", property="dataType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_START_TIME_H", property="operStartTimeH", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_END_TIME_H", property="operEndTimeH", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_START_TIME", property="outStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_END_TIME", property="outEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_START_TIME_H", property="outStartTimeH", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_END_TIME_H", property="outEndTimeH", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_COM_CD", property="outComCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLEAN_COM_CD", property="cleanComCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_DATE", property="setDate", jdbcType=JdbcType.VARCHAR)
    })
    TCmSetSiteInfo selectByPrimaryKey(TCmSetSiteInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @UpdateProvider(type=TCmSetSiteInfoSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCmSetSiteInfo record, @Param("spec") TCmSetSiteInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @UpdateProvider(type=TCmSetSiteInfoSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCmSetSiteInfo record, @Param("spec") TCmSetSiteInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @UpdateProvider(type=TCmSetSiteInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCmSetSiteInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_SITE_INFO
     *
     * @mbggenerated Thu Aug 07 09:04:14 KST 2014
     */
    @Update({
        "update OP.T_CM_SET_SITE_INFO",
        "set CHANGE_NO = #{changeNo,jdbcType=DECIMAL},",
          "ORG_BRANCH_CD = #{orgBranchCd,jdbcType=VARCHAR},",
          "ORG_BRANCH_NM = #{orgBranchNm,jdbcType=VARCHAR},",
          "ORG_SITE_CD = #{orgSiteCd,jdbcType=VARCHAR},",
          "ORG_SITE_NM = #{orgSiteNm,jdbcType=VARCHAR},",
          "PLACE_TYPE = #{placeType,jdbcType=VARCHAR},",
          "JP_TYPE = #{jpType,jdbcType=VARCHAR},",
          "SPECIAL_TYPE = #{specialType,jdbcType=VARCHAR},",
          "OPER_TIME_TYPE = #{operTimeType,jdbcType=VARCHAR},",
          "OPER_START_TIME = #{operStartTime,jdbcType=VARCHAR},",
          "OPER_END_TIME = #{operEndTime,jdbcType=VARCHAR},",
          "OPER_DAY = #{operDay,jdbcType=VARCHAR},",
          "OPER_CHECK_YN = #{operCheckYn,jdbcType=VARCHAR},",
          "SERVICE_FEE = #{serviceFee,jdbcType=VARCHAR},",
          "GUARD_CD = #{guardCd,jdbcType=VARCHAR},",
          "GUARD_NM = #{guardNm,jdbcType=VARCHAR},",
          "FACIL_RENT_YN = #{facilRentYn,jdbcType=VARCHAR},",
          "SET_ADDR = #{setAddr,jdbcType=VARCHAR},",
          "SET_PLACE = #{setPlace,jdbcType=VARCHAR},",
          "MSG = #{msg,jdbcType=VARCHAR},",
          "WORK_RESULT = #{workResult,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
          "DATA_TYPE = #{dataType,jdbcType=VARCHAR},",
          "OPER_START_TIME_H = #{operStartTimeH,jdbcType=VARCHAR},",
          "OPER_END_TIME_H = #{operEndTimeH,jdbcType=VARCHAR},",
          "OUT_START_TIME = #{outStartTime,jdbcType=VARCHAR},",
          "OUT_END_TIME = #{outEndTime,jdbcType=VARCHAR},",
          "OUT_START_TIME_H = #{outStartTimeH,jdbcType=VARCHAR},",
          "OUT_END_TIME_H = #{outEndTimeH,jdbcType=VARCHAR},",
          "OUT_COM_CD = #{outComCd,jdbcType=VARCHAR},",
          "CLEAN_COM_CD = #{cleanComCd,jdbcType=VARCHAR},",
          "SET_DATE = #{setDate,jdbcType=VARCHAR}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and WORK_SEQ = #{workSeq,jdbcType=VARCHAR}",
          "and WORK_CLASS = #{workClass,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCmSetSiteInfo record);
}
package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtError;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCond;
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

public interface TCtErrorMapper {

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @SelectProvider(type = TCtErrorSqlProvider.class, method = "countBySpec")
    int countBySpec(TCtErrorSpec spec);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @DeleteProvider(type = TCtErrorSqlProvider.class, method = "deleteBySpec")
    int deleteBySpec(TCtErrorSpec spec);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @Delete({ "delete from OP.T_CT_ERROR",
            "where ERROR_CD = #{errorCd,jdbcType=VARCHAR}",
            "and ORG_CD = #{orgCd,jdbcType=VARCHAR}" })
    int deleteByPrimaryKey(TCtErrorKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @Insert({ "insert into OP.T_CT_ERROR (ERROR_CD, ORG_CD, ",
            "ERROR_MSG, ERROR_GRADE, ", "GROUP_ERROR_CD, AUTO_SEND_YN, ",
            "WAIT_TIME1, ADD_JOB1_DESC, ", "WAIT_TIME2, ERROR_MOT_YN, ",
            "GUARD_YN, ALARM_MON_YN, ", "REMOTE_YN, REG_DT, ",
            "REG_UID, EDIT_DATE, ", "EDIT_UID)",
            "values (#{errorCd,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
            "#{errorMsg,jdbcType=VARCHAR}, #{errorGrade,jdbcType=VARCHAR}, ",
            "#{groupErrorCd,jdbcType=VARCHAR}, #{autoSendYn,jdbcType=CHAR}, ",
            "#{waitTime1,jdbcType=DECIMAL}, #{addJob1Desc,jdbcType=VARCHAR}, ",
            "#{waitTime2,jdbcType=DECIMAL}, #{errorMotYn,jdbcType=CHAR}, ",
            "#{guardYn,jdbcType=CHAR}, #{alarmMonYn,jdbcType=VARCHAR}, ",
            "#{remoteYn,jdbcType=VARCHAR}, #{regDt,jdbcType=TIMESTAMP}, ",
            "#{regUid,jdbcType=VARCHAR}, #{editDate,jdbcType=TIMESTAMP}, ",
            "#{editUid,jdbcType=VARCHAR})" })
    int insert(TCtError record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @InsertProvider(type = TCtErrorSqlProvider.class, method = "insertSelective")
    int insertSelective(TCtError record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @SelectProvider(type = TCtErrorSqlProvider.class, method = "selectBySpec")
    @Results({
            @Result(column = "ERROR_CD", property = "errorCd", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "ORG_CD", property = "orgCd", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "ERROR_MSG", property = "errorMsg", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ERROR_GRADE", property = "errorGrade", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GROUP_ERROR_CD", property = "groupErrorCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "AUTO_SEND_YN", property = "autoSendYn", jdbcType = JdbcType.CHAR),
            @Result(column = "WAIT_TIME1", property = "waitTime1", jdbcType = JdbcType.DECIMAL),
            @Result(column = "ADD_JOB1_DESC", property = "addJob1Desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WAIT_TIME2", property = "waitTime2", jdbcType = JdbcType.DECIMAL),
            @Result(column = "ERROR_MOT_YN", property = "errorMotYn", jdbcType = JdbcType.CHAR),
            @Result(column = "GUARD_YN", property = "guardYn", jdbcType = JdbcType.CHAR),
            @Result(column = "ALARM_MON_YN", property = "alarmMonYn", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REMOTE_YN", property = "remoteYn", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REG_DT", property = "regDt", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "REG_UID", property = "regUid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "EDIT_DATE", property = "editDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "EDIT_UID", property = "editUid", jdbcType = JdbcType.VARCHAR) })
    List<TCtError> selectBySpec(TCtErrorSpec spec);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @Select({
            "select",
            "ERROR_CD, ORG_CD, ERROR_MSG, ERROR_GRADE, GROUP_ERROR_CD, AUTO_SEND_YN, WAIT_TIME1, ",
            "ADD_JOB1_DESC, WAIT_TIME2, ERROR_MOT_YN, GUARD_YN, ALARM_MON_YN, REMOTE_YN, ",
            "REG_DT, REG_UID, EDIT_DATE, EDIT_UID", "from OP.T_CT_ERROR",
            "where ERROR_CD = #{errorCd,jdbcType=VARCHAR}",
            "and ORG_CD = #{orgCd,jdbcType=VARCHAR}" })
    @Results({
            @Result(column = "ERROR_CD", property = "errorCd", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "ORG_CD", property = "orgCd", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "ERROR_MSG", property = "errorMsg", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ERROR_GRADE", property = "errorGrade", jdbcType = JdbcType.VARCHAR),
            @Result(column = "GROUP_ERROR_CD", property = "groupErrorCd", jdbcType = JdbcType.VARCHAR),
            @Result(column = "AUTO_SEND_YN", property = "autoSendYn", jdbcType = JdbcType.CHAR),
            @Result(column = "WAIT_TIME1", property = "waitTime1", jdbcType = JdbcType.DECIMAL),
            @Result(column = "ADD_JOB1_DESC", property = "addJob1Desc", jdbcType = JdbcType.VARCHAR),
            @Result(column = "WAIT_TIME2", property = "waitTime2", jdbcType = JdbcType.DECIMAL),
            @Result(column = "ERROR_MOT_YN", property = "errorMotYn", jdbcType = JdbcType.CHAR),
            @Result(column = "GUARD_YN", property = "guardYn", jdbcType = JdbcType.CHAR),
            @Result(column = "ALARM_MON_YN", property = "alarmMonYn", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REMOTE_YN", property = "remoteYn", jdbcType = JdbcType.VARCHAR),
            @Result(column = "REG_DT", property = "regDt", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "REG_UID", property = "regUid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "EDIT_DATE", property = "editDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "EDIT_UID", property = "editUid", jdbcType = JdbcType.VARCHAR) })
    TCtError selectByPrimaryKey(TCtErrorKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @UpdateProvider(type = TCtErrorSqlProvider.class, method = "updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCtError record,
            @Param("spec") TCtErrorSpec spec);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @UpdateProvider(type = TCtErrorSqlProvider.class, method = "updateBySpec")
    int updateBySpec(@Param("record") TCtError record,
            @Param("spec") TCtErrorSpec spec);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @UpdateProvider(type = TCtErrorSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCtError record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table OP.T_CT_ERROR
     * @mbggenerated  Tue Jul 01 09:57:16 KST 2014
     */
    @Update({ "update OP.T_CT_ERROR",
            "set ERROR_MSG = #{errorMsg,jdbcType=VARCHAR},",
            "ERROR_GRADE = #{errorGrade,jdbcType=VARCHAR},",
            "GROUP_ERROR_CD = #{groupErrorCd,jdbcType=VARCHAR},",
            "AUTO_SEND_YN = #{autoSendYn,jdbcType=CHAR},",
            "WAIT_TIME1 = #{waitTime1,jdbcType=DECIMAL},",
            "ADD_JOB1_DESC = #{addJob1Desc,jdbcType=VARCHAR},",
            "WAIT_TIME2 = #{waitTime2,jdbcType=DECIMAL},",
            "ERROR_MOT_YN = #{errorMotYn,jdbcType=CHAR},",
            "GUARD_YN = #{guardYn,jdbcType=CHAR},",
            "ALARM_MON_YN = #{alarmMonYn,jdbcType=VARCHAR},",
            "REMOTE_YN = #{remoteYn,jdbcType=VARCHAR},",
            "REG_DT = #{regDt,jdbcType=TIMESTAMP},",
            "REG_UID = #{regUid,jdbcType=VARCHAR},",
            "EDIT_DATE = #{editDate,jdbcType=TIMESTAMP},",
            "EDIT_UID = #{editUid,jdbcType=VARCHAR}",
            "where ERROR_CD = #{errorCd,jdbcType=VARCHAR}",
            "and ORG_CD = #{orgCd,jdbcType=VARCHAR}" })
    int updateByPrimaryKey(TCtError record);

    /**
     * CommonPackImpl.getAutoSendInfo 에서 호출
     *
     * @author KDJ, on Tue Jul 02 17:02:00 KST 2014
     */
    @Select({
        "SELECT  BRAN.AUTO_SEND_YN,          /* 자동통보유무*/                      ",
        "        CASE WHEN #{errorCd,jdbcType=VARCHAR} = 'NI101' THEN               ",
        "             DECODE(#{macGrade,jdbcType=VARCHAR}, 'A', 20, BRAN.WAIT_TIME1)",
        "        ELSE BRAN.WAIT_TIME1                                               ",
        "        END AS WAIT_TIME1,          /* 자동통보 대기시간 */                ",
        "        BRAN.ERROR_MOT_YN,                                                 ",
        "        ERR.GROUP_ERROR_CD,                                                ",
        "        BRAN.GUARD_YN               /* 경비사 통보 여부 */                 ",
        "FROM    OP.T_CT_ERROR_BRAND BRAN,                                          ",
        "        OP.T_CT_ERROR  ERR                                                 ",
        "WHERE   ERR.ORG_CD      = #{orgCd,jdbcType=VARCHAR}                        ",
        "AND     ERR.ERROR_CD    = #{errorCd,jdbcType=VARCHAR}                      ",
        "AND     BRAN.ORG_CD     = ERR.ORG_CD                                       ",
        "AND     BRAN.ERROR_CD   = ERR.ERROR_CD                                     ",
        "AND     BRAN.BRAND_ORG_CD = #{brandOrgCd,jdbcType=VARCHAR}                 "
    })
    @Results({
        @Result(column = "AUTO_SEND_YN", property = "autoSendYn", jdbcType = JdbcType.CHAR),
        @Result(column = "WAIT_TIME1", property = "waitTime1", jdbcType = JdbcType.DECIMAL),
        @Result(column = "ERROR_MOT_YN", property = "errorMotYn", jdbcType = JdbcType.CHAR),
        @Result(column = "GROUP_ERROR_CD", property = "groupErrorCd", jdbcType = JdbcType.VARCHAR),
        @Result(column = "GUARD_YN", property = "guardYn", jdbcType = JdbcType.CHAR) })
    TCtError selectByJoin1(TCtErrorCond cond);

    /**
     * CommonPackImpl.getAutoSendInfo 에서 호출
     *
     * @author KDJ, on Tue Jul 02 17:02:00 KST 2014
     */
    @Select({
        "SELECT  AUTO_SEND_YN,     /* 자동통보유무*/                                                                                     ",
        "        DECODE(#{orgCd,jdbcType=VARCHAR}, '096',                                                                                ",
        "          DECODE(#{errorCd,jdbcType=VARCHAR}, 'NI101',                                                                          ",
        "            DECODE(#{macGrade,jdbcType=VARCHAR}, 'A',  20, WAIT_TIME1),                                                         ",
        "            'NI912',                                                                                                            ",
        "              DECODE(#{macGrade,jdbcType=VARCHAR},'A', WAIT_TIME1, 'S', WAIT_TIME1,                                             ",
        "                      CASE                                                                                                      ",
        "                      WHEN TO_CHAR(SYSDATE, 'HH24') >= '22' THEN                                                                ",
        "                           ROUND((TO_DATE(TO_CHAR(SYSDATE + 2, 'YYYYMMDD')||'080000', 'YYYYMMDDHH24MISS') - SYSDATE) * 24*60)   ",
        "                      WHEN TO_CHAR(SYSDATE, 'HH24') < '08' THEN                                                                 ",
        "                           ROUND((TO_DATE(TO_CHAR(SYSDATE + 1, 'YYYYMMDD') || '080000', 'YYYYMMDDHH24MISS') - SYSDATE) * 24*60) ",
        "                      ELSE 24*60                                                                                                ",
        "                      END),                                                                                                     ",
        "            WAIT_TIME1 ),                                                                                                       ",
        "          WAIT_TIME1)                                                                                                           ",
        "        AS WAIT_TIME, /* 자동통보 대기시간 */                                                                                   ",
        "        ERROR_MOT_YN,                                                                                                           ",
        "        GROUP_ERROR_CD,                                                                                                         ",
        "        GUARD_YN  /* 경비사 통보 여부 */                                                                                        ",
        "FROM    OP.T_CT_ERROR                                                                                                           ",
        "WHERE   ORG_CD      = #{orgCd, jdbcType=VARCHAR}                                                                                ",
        "AND     ERROR_CD    = #{errorCd, jdbcType=VARCHAR}                                                                              "
    })
    @Results({
        @Result(column = "AUTO_SEND_YN", property = "autoSendYn", jdbcType = JdbcType.CHAR),
        @Result(column = "WAIT_TIME1", property = "waitTime1", jdbcType = JdbcType.DECIMAL),
        @Result(column = "ERROR_MOT_YN", property = "errorMotYn", jdbcType = JdbcType.CHAR),
        @Result(column = "GROUP_ERROR_CD", property = "groupErrorCd", jdbcType = JdbcType.VARCHAR),
        @Result(column = "GUARD_YN", property = "guardYn", jdbcType = JdbcType.CHAR) })
    TCtError selectByCond1(TCtErrorCond cond);

}
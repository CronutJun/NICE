package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtRemoteHistory;
import com.nicetcm.nibsplus.broker.msg.model.TCtRemoteHistoryKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtRemoteHistorySpec;
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

public interface TCtRemoteHistoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @SelectProvider(type=TCtRemoteHistorySqlProvider.class, method="countBySpec")
    int countBySpec(TCtRemoteHistorySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @DeleteProvider(type=TCtRemoteHistorySqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCtRemoteHistorySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @Delete({
        "delete from OP.T_CT_REMOTE_HISTORY",
        "where CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and CREATE_SEQ = #{createSeq,jdbcType=OTHER}"
    })
    int deleteByPrimaryKey(TCtRemoteHistoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @Insert({
        "insert into OP.T_CT_REMOTE_HISTORY (CREATE_DATE, CREATE_SEQ, ",
        "CREATE_TIME, ERROR_NO, ",
        "ERROR_MSG, ORG_CD, ",
        "ORG_NM, BRANCH_CD, ",
        "BRANCH_NM, SITE_CD, ",
        "SITE_NM, MAC_NO, MAC_NM, ",
        "MEMBER_ID, MEMBER_NM, ",
        "RMT_CD, RMT_MSG, APP_DATE, ",
        "RCV_DATE, EXE_DATE, ",
        "RMT_STATUS, RMT_CNT, ",
        "CREATE_DATE_REC, ERROR_NO_REC, ",
        "REGDATE)",
        "values (#{createDate,jdbcType=VARCHAR}, #{createSeq,jdbcType=OTHER}, ",
        "#{createTime,jdbcType=VARCHAR}, #{errorNo,jdbcType=VARCHAR}, ",
        "#{errorMsg,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{orgNm,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{branchNm,jdbcType=VARCHAR}, #{siteCd,jdbcType=VARCHAR}, ",
        "#{siteNm,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, #{macNm,jdbcType=VARCHAR}, ",
        "#{memberId,jdbcType=VARCHAR}, #{memberNm,jdbcType=VARCHAR}, ",
        "#{rmtCd,jdbcType=VARCHAR}, #{rmtMsg,jdbcType=VARCHAR}, #{appDate,jdbcType=TIMESTAMP}, ",
        "#{rcvDate,jdbcType=TIMESTAMP}, #{exeDate,jdbcType=TIMESTAMP}, ",
        "#{rmtStatus,jdbcType=VARCHAR}, #{rmtCnt,jdbcType=OTHER}, ",
        "#{createDateRec,jdbcType=VARCHAR}, #{errorNoRec,jdbcType=VARCHAR}, ",
        "#{regdate,jdbcType=TIMESTAMP})"
    })
    int insert(TCtRemoteHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @InsertProvider(type=TCtRemoteHistorySqlProvider.class, method="insertSelective")
    int insertSelective(TCtRemoteHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @SelectProvider(type=TCtRemoteHistorySqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_SEQ", property="createSeq", jdbcType=JdbcType.OTHER, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_MSG", property="errorMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_NM", property="orgNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_NM", property="branchNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_NM", property="siteNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_NM", property="macNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="MEMBER_ID", property="memberId", jdbcType=JdbcType.VARCHAR),
        @Result(column="MEMBER_NM", property="memberNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="RMT_CD", property="rmtCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="RMT_MSG", property="rmtMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="APP_DATE", property="appDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="RCV_DATE", property="rcvDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="EXE_DATE", property="exeDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="RMT_STATUS", property="rmtStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="RMT_CNT", property="rmtCnt", jdbcType=JdbcType.OTHER),
        @Result(column="CREATE_DATE_REC", property="createDateRec", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_NO_REC", property="errorNoRec", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGDATE", property="regdate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TCtRemoteHistory> selectBySpec(TCtRemoteHistorySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @Select({
        "select",
        "CREATE_DATE, CREATE_SEQ, CREATE_TIME, ERROR_NO, ERROR_MSG, ORG_CD, ORG_NM, BRANCH_CD, ",
        "BRANCH_NM, SITE_CD, SITE_NM, MAC_NO, MAC_NM, MEMBER_ID, MEMBER_NM, RMT_CD, RMT_MSG, ",
        "APP_DATE, RCV_DATE, EXE_DATE, RMT_STATUS, RMT_CNT, CREATE_DATE_REC, ERROR_NO_REC, ",
        "REGDATE",
        "from OP.T_CT_REMOTE_HISTORY",
        "where CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and CREATE_SEQ = #{createSeq,jdbcType=OTHER}"
    })
    @Results({
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_SEQ", property="createSeq", jdbcType=JdbcType.OTHER, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_MSG", property="errorMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_NM", property="orgNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_NM", property="branchNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_NM", property="siteNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_NM", property="macNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="MEMBER_ID", property="memberId", jdbcType=JdbcType.VARCHAR),
        @Result(column="MEMBER_NM", property="memberNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="RMT_CD", property="rmtCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="RMT_MSG", property="rmtMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="APP_DATE", property="appDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="RCV_DATE", property="rcvDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="EXE_DATE", property="exeDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="RMT_STATUS", property="rmtStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="RMT_CNT", property="rmtCnt", jdbcType=JdbcType.OTHER),
        @Result(column="CREATE_DATE_REC", property="createDateRec", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_NO_REC", property="errorNoRec", jdbcType=JdbcType.VARCHAR),
        @Result(column="REGDATE", property="regdate", jdbcType=JdbcType.TIMESTAMP)
    })
    TCtRemoteHistory selectByPrimaryKey(TCtRemoteHistoryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @UpdateProvider(type=TCtRemoteHistorySqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCtRemoteHistory record, @Param("spec") TCtRemoteHistorySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @UpdateProvider(type=TCtRemoteHistorySqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCtRemoteHistory record, @Param("spec") TCtRemoteHistorySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @UpdateProvider(type=TCtRemoteHistorySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCtRemoteHistory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_REMOTE_HISTORY
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @Update({
        "update OP.T_CT_REMOTE_HISTORY",
        "set CREATE_TIME = #{createTime,jdbcType=VARCHAR},",
          "ERROR_NO = #{errorNo,jdbcType=VARCHAR},",
          "ERROR_MSG = #{errorMsg,jdbcType=VARCHAR},",
          "ORG_CD = #{orgCd,jdbcType=VARCHAR},",
          "ORG_NM = #{orgNm,jdbcType=VARCHAR},",
          "BRANCH_CD = #{branchCd,jdbcType=VARCHAR},",
          "BRANCH_NM = #{branchNm,jdbcType=VARCHAR},",
          "SITE_CD = #{siteCd,jdbcType=VARCHAR},",
          "SITE_NM = #{siteNm,jdbcType=VARCHAR},",
          "MAC_NO = #{macNo,jdbcType=VARCHAR},",
          "MAC_NM = #{macNm,jdbcType=VARCHAR},",
          "MEMBER_ID = #{memberId,jdbcType=VARCHAR},",
          "MEMBER_NM = #{memberNm,jdbcType=VARCHAR},",
          "RMT_CD = #{rmtCd,jdbcType=VARCHAR},",
          "RMT_MSG = #{rmtMsg,jdbcType=VARCHAR},",
          "APP_DATE = #{appDate,jdbcType=TIMESTAMP},",
          "RCV_DATE = #{rcvDate,jdbcType=TIMESTAMP},",
          "EXE_DATE = #{exeDate,jdbcType=TIMESTAMP},",
          "RMT_STATUS = #{rmtStatus,jdbcType=VARCHAR},",
          "RMT_CNT = #{rmtCnt,jdbcType=OTHER},",
          "CREATE_DATE_REC = #{createDateRec,jdbcType=VARCHAR},",
          "ERROR_NO_REC = #{errorNoRec,jdbcType=VARCHAR},",
          "REGDATE = #{regdate,jdbcType=TIMESTAMP}",
        "where CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and CREATE_SEQ = #{createSeq,jdbcType=OTHER}"
    })
    int updateByPrimaryKey(TCtRemoteHistory record);
}
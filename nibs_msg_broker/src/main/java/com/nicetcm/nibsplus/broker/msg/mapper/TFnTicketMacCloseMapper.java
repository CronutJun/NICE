package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacClose;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacCloseKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketMacCloseSpec;
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

public interface TFnTicketMacCloseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @SelectProvider(type=TFnTicketMacCloseSqlProvider.class, method="countBySpec")
    int countBySpec(TFnTicketMacCloseSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @DeleteProvider(type=TFnTicketMacCloseSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnTicketMacCloseSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_TICKET_MAC_CLOSE",
        "where CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and TICKET_CD = #{ticketCd,jdbcType=VARCHAR}",
          "and TICKET_GUBUN_CD = #{ticketGubunCd,jdbcType=VARCHAR}",
          "and DEAL_TYPE = #{dealType,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnTicketMacCloseKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_TICKET_MAC_CLOSE (CLOSE_DATE, ORG_CD, ",
        "BRANCH_CD, MAC_NO, ",
        "TICKET_CD, TICKET_GUBUN_CD, ",
        "DEAL_TYPE, DEPT_CD, ",
        "OFFICE_CD, TEAM_CD, ",
        "CLOSE_TIME, TICKET1_EMIT_CNT, ",
        "TICKET1_BACK_CNT, INSERT_DATE, ",
        "INSERT_UID)",
        "values (#{closeDate,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{branchCd,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
        "#{ticketCd,jdbcType=VARCHAR}, #{ticketGubunCd,jdbcType=VARCHAR}, ",
        "#{dealType,jdbcType=VARCHAR}, #{deptCd,jdbcType=VARCHAR}, ",
        "#{officeCd,jdbcType=VARCHAR}, #{teamCd,jdbcType=VARCHAR}, ",
        "#{closeTime,jdbcType=VARCHAR}, #{ticket1EmitCnt,jdbcType=OTHER}, ",
        "#{ticket1BackCnt,jdbcType=OTHER}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{insertUid,jdbcType=VARCHAR})"
    })
    int insert(TFnTicketMacClose record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @InsertProvider(type=TFnTicketMacCloseSqlProvider.class, method="insertSelective")
    int insertSelective(TFnTicketMacClose record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @SelectProvider(type=TFnTicketMacCloseSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="CLOSE_DATE", property="closeDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TICKET_CD", property="ticketCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TICKET_GUBUN_CD", property="ticketGubunCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_TYPE", property="dealType", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEAM_CD", property="teamCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_TIME", property="closeTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="TICKET1_EMIT_CNT", property="ticket1EmitCnt", jdbcType=JdbcType.OTHER),
        @Result(column="TICKET1_BACK_CNT", property="ticket1BackCnt", jdbcType=JdbcType.OTHER),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR)
    })
    List<TFnTicketMacClose> selectBySpec(TFnTicketMacCloseSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @Select({
        "select",
        "CLOSE_DATE, ORG_CD, BRANCH_CD, MAC_NO, TICKET_CD, TICKET_GUBUN_CD, DEAL_TYPE, ",
        "DEPT_CD, OFFICE_CD, TEAM_CD, CLOSE_TIME, TICKET1_EMIT_CNT, TICKET1_BACK_CNT, ",
        "INSERT_DATE, INSERT_UID",
        "from OP.T_FN_TICKET_MAC_CLOSE",
        "where CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and TICKET_CD = #{ticketCd,jdbcType=VARCHAR}",
          "and TICKET_GUBUN_CD = #{ticketGubunCd,jdbcType=VARCHAR}",
          "and DEAL_TYPE = #{dealType,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="CLOSE_DATE", property="closeDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TICKET_CD", property="ticketCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TICKET_GUBUN_CD", property="ticketGubunCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_TYPE", property="dealType", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEAM_CD", property="teamCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_TIME", property="closeTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="TICKET1_EMIT_CNT", property="ticket1EmitCnt", jdbcType=JdbcType.OTHER),
        @Result(column="TICKET1_BACK_CNT", property="ticket1BackCnt", jdbcType=JdbcType.OTHER),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR)
    })
    TFnTicketMacClose selectByPrimaryKey(TFnTicketMacCloseKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @UpdateProvider(type=TFnTicketMacCloseSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnTicketMacClose record, @Param("spec") TFnTicketMacCloseSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @UpdateProvider(type=TFnTicketMacCloseSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnTicketMacClose record, @Param("spec") TFnTicketMacCloseSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @UpdateProvider(type=TFnTicketMacCloseSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnTicketMacClose record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_TICKET_MAC_CLOSE
     *
     * @mbggenerated Wed Jul 30 15:45:45 KST 2014
     */
    @Update({
        "update OP.T_FN_TICKET_MAC_CLOSE",
        "set DEPT_CD = #{deptCd,jdbcType=VARCHAR},",
          "OFFICE_CD = #{officeCd,jdbcType=VARCHAR},",
          "TEAM_CD = #{teamCd,jdbcType=VARCHAR},",
          "CLOSE_TIME = #{closeTime,jdbcType=VARCHAR},",
          "TICKET1_EMIT_CNT = #{ticket1EmitCnt,jdbcType=OTHER},",
          "TICKET1_BACK_CNT = #{ticket1BackCnt,jdbcType=OTHER},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR}",
        "where CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and TICKET_CD = #{ticketCd,jdbcType=VARCHAR}",
          "and TICKET_GUBUN_CD = #{ticketGubunCd,jdbcType=VARCHAR}",
          "and DEAL_TYPE = #{dealType,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnTicketMacClose record);
}
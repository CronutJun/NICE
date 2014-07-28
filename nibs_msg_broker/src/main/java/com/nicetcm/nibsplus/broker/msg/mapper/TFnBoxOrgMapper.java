package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrg;
import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrgKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrgSpec;
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

public interface TFnBoxOrgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @SelectProvider(type=TFnBoxOrgSqlProvider.class, method="countBySpec")
    int countBySpec(TFnBoxOrgSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @DeleteProvider(type=TFnBoxOrgSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnBoxOrgSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_BOX_ORG",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and SEQ = #{seq,jdbcType=VARCHAR}",
          "and BOX_SEQ = #{boxSeq,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnBoxOrgKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_BOX_ORG (ORG_CD, BRANCH_CD, ",
        "MAC_NO, DEAL_DATE, ",
        "SEQ, BOX_SEQ, BOX_GUBUN1, ",
        "BOX_GUBUN2, KJ_GUBUN, ",
        "KJ_CNT, KJ_AMT, TICKET_SEQ, ",
        "UPDATE_DATE, UPDATE_UID)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{dealDate,jdbcType=VARCHAR}, ",
        "#{seq,jdbcType=VARCHAR}, #{boxSeq,jdbcType=VARCHAR}, #{boxGubun1,jdbcType=VARCHAR}, ",
        "#{boxGubun2,jdbcType=VARCHAR}, #{kjGubun,jdbcType=VARCHAR}, ",
        "#{kjCnt,jdbcType=DECIMAL}, #{kjAmt,jdbcType=DECIMAL}, #{ticketSeq,jdbcType=VARCHAR}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{updateUid,jdbcType=VARCHAR})"
    })
    int insert(TFnBoxOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @InsertProvider(type=TFnBoxOrgSqlProvider.class, method="insertSelective")
    int insertSelective(TFnBoxOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @SelectProvider(type=TFnBoxOrgSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SEQ", property="seq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BOX_SEQ", property="boxSeq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BOX_GUBUN1", property="boxGubun1", jdbcType=JdbcType.VARCHAR),
        @Result(column="BOX_GUBUN2", property="boxGubun2", jdbcType=JdbcType.VARCHAR),
        @Result(column="KJ_GUBUN", property="kjGubun", jdbcType=JdbcType.VARCHAR),
        @Result(column="KJ_CNT", property="kjCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="KJ_AMT", property="kjAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TICKET_SEQ", property="ticketSeq", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    List<TFnBoxOrg> selectBySpec(TFnBoxOrgSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, BRANCH_CD, MAC_NO, DEAL_DATE, SEQ, BOX_SEQ, BOX_GUBUN1, BOX_GUBUN2, ",
        "KJ_GUBUN, KJ_CNT, KJ_AMT, TICKET_SEQ, UPDATE_DATE, UPDATE_UID",
        "from OP.T_FN_BOX_ORG",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and SEQ = #{seq,jdbcType=VARCHAR}",
          "and BOX_SEQ = #{boxSeq,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SEQ", property="seq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BOX_SEQ", property="boxSeq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BOX_GUBUN1", property="boxGubun1", jdbcType=JdbcType.VARCHAR),
        @Result(column="BOX_GUBUN2", property="boxGubun2", jdbcType=JdbcType.VARCHAR),
        @Result(column="KJ_GUBUN", property="kjGubun", jdbcType=JdbcType.VARCHAR),
        @Result(column="KJ_CNT", property="kjCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="KJ_AMT", property="kjAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TICKET_SEQ", property="ticketSeq", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    TFnBoxOrg selectByPrimaryKey(TFnBoxOrgKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @UpdateProvider(type=TFnBoxOrgSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnBoxOrg record, @Param("spec") TFnBoxOrgSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @UpdateProvider(type=TFnBoxOrgSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnBoxOrg record, @Param("spec") TFnBoxOrgSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @UpdateProvider(type=TFnBoxOrgSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnBoxOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_BOX_ORG
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @Update({
        "update OP.T_FN_BOX_ORG",
        "set BOX_GUBUN1 = #{boxGubun1,jdbcType=VARCHAR},",
          "BOX_GUBUN2 = #{boxGubun2,jdbcType=VARCHAR},",
          "KJ_GUBUN = #{kjGubun,jdbcType=VARCHAR},",
          "KJ_CNT = #{kjCnt,jdbcType=DECIMAL},",
          "KJ_AMT = #{kjAmt,jdbcType=DECIMAL},",
          "TICKET_SEQ = #{ticketSeq,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and SEQ = #{seq,jdbcType=VARCHAR}",
          "and BOX_SEQ = #{boxSeq,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnBoxOrg record);
}
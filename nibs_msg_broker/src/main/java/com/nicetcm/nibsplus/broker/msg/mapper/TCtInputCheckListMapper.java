package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckList;
import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckListKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtInputCheckListSpec;
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

public interface TCtInputCheckListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @SelectProvider(type=TCtInputCheckListSqlProvider.class, method="countBySpec")
    int countBySpec(TCtInputCheckListSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @DeleteProvider(type=TCtInputCheckListSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCtInputCheckListSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @Delete({
        "delete from OP.T_CT_INPUT_CHECK_LIST",
        "where FROM_DATE = #{fromDate,jdbcType=VARCHAR}",
          "and TO_DATE = #{toDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCtInputCheckListKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @Insert({
        "insert into OP.T_CT_INPUT_CHECK_LIST (FROM_DATE, TO_DATE, ",
        "ORG_CD, BRANCH_CD, ",
        "MAC_NO, OWN_CHECK_CNT, ",
        "OWN_CHECK_AMT, OTHER_CHECK_CNT, ",
        "OTHER_CHECK_AMT, TOT_CNT, ",
        "TOT_AMT, INSERT_DATE, ",
        "UPDATE_DATE)",
        "values (#{fromDate,jdbcType=VARCHAR}, #{toDate,jdbcType=VARCHAR}, ",
        "#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{ownCheckCnt,jdbcType=DECIMAL}, ",
        "#{ownCheckAmt,jdbcType=DECIMAL}, #{otherCheckCnt,jdbcType=DECIMAL}, ",
        "#{otherCheckAmt,jdbcType=DECIMAL}, #{totCnt,jdbcType=DECIMAL}, ",
        "#{totAmt,jdbcType=DECIMAL}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{updateDate,jdbcType=TIMESTAMP})"
    })
    int insert(TCtInputCheckList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @InsertProvider(type=TCtInputCheckListSqlProvider.class, method="insertSelective")
    int insertSelective(TCtInputCheckList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @SelectProvider(type=TCtInputCheckListSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="FROM_DATE", property="fromDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TO_DATE", property="toDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="OWN_CHECK_CNT", property="ownCheckCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OWN_CHECK_AMT", property="ownCheckAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OTHER_CHECK_CNT", property="otherCheckCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OTHER_CHECK_AMT", property="otherCheckAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_CNT", property="totCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_AMT", property="totAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TCtInputCheckList> selectBySpec(TCtInputCheckListSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @Select({
        "select",
        "FROM_DATE, TO_DATE, ORG_CD, BRANCH_CD, MAC_NO, OWN_CHECK_CNT, OWN_CHECK_AMT, ",
        "OTHER_CHECK_CNT, OTHER_CHECK_AMT, TOT_CNT, TOT_AMT, INSERT_DATE, UPDATE_DATE",
        "from OP.T_CT_INPUT_CHECK_LIST",
        "where FROM_DATE = #{fromDate,jdbcType=VARCHAR}",
          "and TO_DATE = #{toDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="FROM_DATE", property="fromDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TO_DATE", property="toDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="OWN_CHECK_CNT", property="ownCheckCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OWN_CHECK_AMT", property="ownCheckAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OTHER_CHECK_CNT", property="otherCheckCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OTHER_CHECK_AMT", property="otherCheckAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_CNT", property="totCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_AMT", property="totAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    TCtInputCheckList selectByPrimaryKey(TCtInputCheckListKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @UpdateProvider(type=TCtInputCheckListSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCtInputCheckList record, @Param("spec") TCtInputCheckListSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @UpdateProvider(type=TCtInputCheckListSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCtInputCheckList record, @Param("spec") TCtInputCheckListSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @UpdateProvider(type=TCtInputCheckListSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCtInputCheckList record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_INPUT_CHECK_LIST
     *
     * @mbggenerated Thu Jul 17 17:30:56 KST 2014
     */
    @Update({
        "update OP.T_CT_INPUT_CHECK_LIST",
        "set OWN_CHECK_CNT = #{ownCheckCnt,jdbcType=DECIMAL},",
          "OWN_CHECK_AMT = #{ownCheckAmt,jdbcType=DECIMAL},",
          "OTHER_CHECK_CNT = #{otherCheckCnt,jdbcType=DECIMAL},",
          "OTHER_CHECK_AMT = #{otherCheckAmt,jdbcType=DECIMAL},",
          "TOT_CNT = #{totCnt,jdbcType=DECIMAL},",
          "TOT_AMT = #{totAmt,jdbcType=DECIMAL},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}",
        "where FROM_DATE = #{fromDate,jdbcType=VARCHAR}",
          "and TO_DATE = #{toDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCtInputCheckList record);
}
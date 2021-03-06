package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnForeigncurrency;
import com.nicetcm.nibsplus.broker.msg.model.TFnForeigncurrencyKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnForeigncurrencySpec;
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

public interface TFnForeigncurrencyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @SelectProvider(type=TFnForeigncurrencySqlProvider.class, method="countBySpec")
    int countBySpec(TFnForeigncurrencySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @DeleteProvider(type=TFnForeigncurrencySqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnForeigncurrencySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_FOREIGNCURRENCY",
        "where DEPO_DATE = #{depoDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnForeigncurrencyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_FOREIGNCURRENCY (DEPO_DATE, ORG_CD, ",
        "BRANCH_CD, DEPT_CD, ",
        "OFFICE_CD, DEPO_SDATE, ",
        "DEPO_STIME, DEPO_EDATE, ",
        "DEPO_ETIME, DOLLAR_AMT, ",
        "EURO_AMT, YEN_AMT, YUAN_AMT, ",
        "INSERT_UID, INSERT_DATE, ",
        "UPDATE_UID, UPDATE_DATE, ",
        "ORG_SEND_YN, DEPO_CLOSE_SDATE, ",
        "DEPO_CLOSE_EDATE, DEPO_CLOSE_STIME, ",
        "DEPO_CLOSE_ETIME)",
        "values (#{depoDate,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{branchCd,jdbcType=VARCHAR}, #{deptCd,jdbcType=VARCHAR}, ",
        "#{officeCd,jdbcType=VARCHAR}, #{depoSdate,jdbcType=VARCHAR}, ",
        "#{depoStime,jdbcType=VARCHAR}, #{depoEdate,jdbcType=VARCHAR}, ",
        "#{depoEtime,jdbcType=VARCHAR}, #{dollarAmt,jdbcType=OTHER}, ",
        "#{euroAmt,jdbcType=OTHER}, #{yenAmt,jdbcType=OTHER}, #{yuanAmt,jdbcType=OTHER}, ",
        "#{insertUid,jdbcType=VARCHAR}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{updateUid,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{orgSendYn,jdbcType=VARCHAR}, #{depoCloseSdate,jdbcType=VARCHAR}, ",
        "#{depoCloseEdate,jdbcType=VARCHAR}, #{depoCloseStime,jdbcType=VARCHAR}, ",
        "#{depoCloseEtime,jdbcType=VARCHAR})"
    })
    int insert(TFnForeigncurrency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @InsertProvider(type=TFnForeigncurrencySqlProvider.class, method="insertSelective")
    int insertSelective(TFnForeigncurrency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @SelectProvider(type=TFnForeigncurrencySqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="DEPO_DATE", property="depoDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_SDATE", property="depoSdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_STIME", property="depoStime", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_EDATE", property="depoEdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_ETIME", property="depoEtime", jdbcType=JdbcType.VARCHAR),
        @Result(column="DOLLAR_AMT", property="dollarAmt", jdbcType=JdbcType.OTHER),
        @Result(column="EURO_AMT", property="euroAmt", jdbcType=JdbcType.OTHER),
        @Result(column="YEN_AMT", property="yenAmt", jdbcType=JdbcType.OTHER),
        @Result(column="YUAN_AMT", property="yuanAmt", jdbcType=JdbcType.OTHER),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_CLOSE_SDATE", property="depoCloseSdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_CLOSE_EDATE", property="depoCloseEdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_CLOSE_STIME", property="depoCloseStime", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_CLOSE_ETIME", property="depoCloseEtime", jdbcType=JdbcType.VARCHAR)
    })
    List<TFnForeigncurrency> selectBySpec(TFnForeigncurrencySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @Select({
        "select",
        "DEPO_DATE, ORG_CD, BRANCH_CD, DEPT_CD, OFFICE_CD, DEPO_SDATE, DEPO_STIME, DEPO_EDATE, ",
        "DEPO_ETIME, DOLLAR_AMT, EURO_AMT, YEN_AMT, YUAN_AMT, INSERT_UID, INSERT_DATE, ",
        "UPDATE_UID, UPDATE_DATE, ORG_SEND_YN, DEPO_CLOSE_SDATE, DEPO_CLOSE_EDATE, DEPO_CLOSE_STIME, ",
        "DEPO_CLOSE_ETIME",
        "from OP.T_FN_FOREIGNCURRENCY",
        "where DEPO_DATE = #{depoDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="DEPO_DATE", property="depoDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_SDATE", property="depoSdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_STIME", property="depoStime", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_EDATE", property="depoEdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_ETIME", property="depoEtime", jdbcType=JdbcType.VARCHAR),
        @Result(column="DOLLAR_AMT", property="dollarAmt", jdbcType=JdbcType.OTHER),
        @Result(column="EURO_AMT", property="euroAmt", jdbcType=JdbcType.OTHER),
        @Result(column="YEN_AMT", property="yenAmt", jdbcType=JdbcType.OTHER),
        @Result(column="YUAN_AMT", property="yuanAmt", jdbcType=JdbcType.OTHER),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_CLOSE_SDATE", property="depoCloseSdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_CLOSE_EDATE", property="depoCloseEdate", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_CLOSE_STIME", property="depoCloseStime", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPO_CLOSE_ETIME", property="depoCloseEtime", jdbcType=JdbcType.VARCHAR)
    })
    TFnForeigncurrency selectByPrimaryKey(TFnForeigncurrencyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @UpdateProvider(type=TFnForeigncurrencySqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnForeigncurrency record, @Param("spec") TFnForeigncurrencySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @UpdateProvider(type=TFnForeigncurrencySqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnForeigncurrency record, @Param("spec") TFnForeigncurrencySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @UpdateProvider(type=TFnForeigncurrencySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnForeigncurrency record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_FOREIGNCURRENCY
     *
     * @mbggenerated Mon Jul 28 16:08:07 KST 2014
     */
    @Update({
        "update OP.T_FN_FOREIGNCURRENCY",
        "set DEPT_CD = #{deptCd,jdbcType=VARCHAR},",
          "OFFICE_CD = #{officeCd,jdbcType=VARCHAR},",
          "DEPO_SDATE = #{depoSdate,jdbcType=VARCHAR},",
          "DEPO_STIME = #{depoStime,jdbcType=VARCHAR},",
          "DEPO_EDATE = #{depoEdate,jdbcType=VARCHAR},",
          "DEPO_ETIME = #{depoEtime,jdbcType=VARCHAR},",
          "DOLLAR_AMT = #{dollarAmt,jdbcType=OTHER},",
          "EURO_AMT = #{euroAmt,jdbcType=OTHER},",
          "YEN_AMT = #{yenAmt,jdbcType=OTHER},",
          "YUAN_AMT = #{yuanAmt,jdbcType=OTHER},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "ORG_SEND_YN = #{orgSendYn,jdbcType=VARCHAR},",
          "DEPO_CLOSE_SDATE = #{depoCloseSdate,jdbcType=VARCHAR},",
          "DEPO_CLOSE_EDATE = #{depoCloseEdate,jdbcType=VARCHAR},",
          "DEPO_CLOSE_STIME = #{depoCloseStime,jdbcType=VARCHAR},",
          "DEPO_CLOSE_ETIME = #{depoCloseEtime,jdbcType=VARCHAR}",
        "where DEPO_DATE = #{depoDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnForeigncurrency record);
}
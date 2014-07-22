package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnKibMacDsum;
import com.nicetcm.nibsplus.broker.msg.model.TFnKibMacDsumKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnKibMacDsumSpec;
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

public interface TFnKibMacDsumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @SelectProvider(type=TFnKibMacDsumSqlProvider.class, method="countBySpec")
    int countBySpec(TFnKibMacDsumSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @DeleteProvider(type=TFnKibMacDsumSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnKibMacDsumSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_KIB_MAC_DSUM",
        "where DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnKibMacDsumKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_KIB_MAC_DSUM (DEAL_DATE, ORG_CD, ",
        "BRANCH_CD, MAC_NO, ",
        "PRE_CLOSE_OUT_AMT, PRE_CLOSE_IN_AMT, ",
        "AFTER_CLOSE_OUT_AMT, AFTER_CLOSE_IN_AMT, ",
        "OUT_TOTAL_AMT, IN_TOTAL_AMT, ",
        "INSERT_DATE, INSERT_UID)",
        "values (#{dealDate,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{branchCd,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
        "#{preCloseOutAmt,jdbcType=OTHER}, #{preCloseInAmt,jdbcType=OTHER}, ",
        "#{afterCloseOutAmt,jdbcType=OTHER}, #{afterCloseInAmt,jdbcType=OTHER}, ",
        "#{outTotalAmt,jdbcType=OTHER}, #{inTotalAmt,jdbcType=OTHER}, ",
        "#{insertDate,jdbcType=TIMESTAMP}, #{insertUid,jdbcType=VARCHAR})"
    })
    int insert(TFnKibMacDsum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @InsertProvider(type=TFnKibMacDsumSqlProvider.class, method="insertSelective")
    int insertSelective(TFnKibMacDsum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @SelectProvider(type=TFnKibMacDsumSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="PRE_CLOSE_OUT_AMT", property="preCloseOutAmt", jdbcType=JdbcType.OTHER),
        @Result(column="PRE_CLOSE_IN_AMT", property="preCloseInAmt", jdbcType=JdbcType.OTHER),
        @Result(column="AFTER_CLOSE_OUT_AMT", property="afterCloseOutAmt", jdbcType=JdbcType.OTHER),
        @Result(column="AFTER_CLOSE_IN_AMT", property="afterCloseInAmt", jdbcType=JdbcType.OTHER),
        @Result(column="OUT_TOTAL_AMT", property="outTotalAmt", jdbcType=JdbcType.OTHER),
        @Result(column="IN_TOTAL_AMT", property="inTotalAmt", jdbcType=JdbcType.OTHER),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR)
    })
    List<TFnKibMacDsum> selectBySpec(TFnKibMacDsumSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Select({
        "select",
        "DEAL_DATE, ORG_CD, BRANCH_CD, MAC_NO, PRE_CLOSE_OUT_AMT, PRE_CLOSE_IN_AMT, AFTER_CLOSE_OUT_AMT, ",
        "AFTER_CLOSE_IN_AMT, OUT_TOTAL_AMT, IN_TOTAL_AMT, INSERT_DATE, INSERT_UID",
        "from OP.T_FN_KIB_MAC_DSUM",
        "where DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="PRE_CLOSE_OUT_AMT", property="preCloseOutAmt", jdbcType=JdbcType.OTHER),
        @Result(column="PRE_CLOSE_IN_AMT", property="preCloseInAmt", jdbcType=JdbcType.OTHER),
        @Result(column="AFTER_CLOSE_OUT_AMT", property="afterCloseOutAmt", jdbcType=JdbcType.OTHER),
        @Result(column="AFTER_CLOSE_IN_AMT", property="afterCloseInAmt", jdbcType=JdbcType.OTHER),
        @Result(column="OUT_TOTAL_AMT", property="outTotalAmt", jdbcType=JdbcType.OTHER),
        @Result(column="IN_TOTAL_AMT", property="inTotalAmt", jdbcType=JdbcType.OTHER),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR)
    })
    TFnKibMacDsum selectByPrimaryKey(TFnKibMacDsumKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TFnKibMacDsumSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnKibMacDsum record, @Param("spec") TFnKibMacDsumSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TFnKibMacDsumSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnKibMacDsum record, @Param("spec") TFnKibMacDsumSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TFnKibMacDsumSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnKibMacDsum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_KIB_MAC_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Update({
        "update OP.T_FN_KIB_MAC_DSUM",
        "set PRE_CLOSE_OUT_AMT = #{preCloseOutAmt,jdbcType=OTHER},",
          "PRE_CLOSE_IN_AMT = #{preCloseInAmt,jdbcType=OTHER},",
          "AFTER_CLOSE_OUT_AMT = #{afterCloseOutAmt,jdbcType=OTHER},",
          "AFTER_CLOSE_IN_AMT = #{afterCloseInAmt,jdbcType=OTHER},",
          "OUT_TOTAL_AMT = #{outTotalAmt,jdbcType=OTHER},",
          "IN_TOTAL_AMT = #{inTotalAmt,jdbcType=OTHER},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR}",
        "where DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnKibMacDsum record);
}
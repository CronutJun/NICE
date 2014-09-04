package com.nicetcm.nibsplus.broker.ams.mapper;

import com.nicetcm.nibsplus.broker.ams.model.TPmUpdsMac;
import com.nicetcm.nibsplus.broker.ams.model.TPmUpdsMacKey;
import com.nicetcm.nibsplus.broker.ams.model.TPmUpdsMacSpec;
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

public interface TPmUpdsMacMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @SelectProvider(type=TPmUpdsMacSqlProvider.class, method="countBySpec")
    int countBySpec(TPmUpdsMacSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @DeleteProvider(type=TPmUpdsMacSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TPmUpdsMacSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @Delete({
        "delete from IN.T_PM_UPDS_MAC",
        "where GRP_CD = #{grpCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TPmUpdsMacKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @Insert({
        "insert into IN.T_PM_UPDS_MAC (GRP_CD, MAC_NO, ",
        "INSERT_DATE, INSERT_UID)",
        "values (#{grpCd,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
        "#{insertDate,jdbcType=TIMESTAMP}, #{insertUid,jdbcType=VARCHAR})"
    })
    int insert(TPmUpdsMac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @InsertProvider(type=TPmUpdsMacSqlProvider.class, method="insertSelective")
    int insertSelective(TPmUpdsMac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @SelectProvider(type=TPmUpdsMacSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="GRP_CD", property="grpCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR)
    })
    List<TPmUpdsMac> selectBySpec(TPmUpdsMacSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @Select({
        "select",
        "GRP_CD, MAC_NO, INSERT_DATE, INSERT_UID",
        "from IN.T_PM_UPDS_MAC",
        "where GRP_CD = #{grpCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="GRP_CD", property="grpCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR)
    })
    TPmUpdsMac selectByPrimaryKey(TPmUpdsMacKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @UpdateProvider(type=TPmUpdsMacSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TPmUpdsMac record, @Param("spec") TPmUpdsMacSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @UpdateProvider(type=TPmUpdsMacSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TPmUpdsMac record, @Param("spec") TPmUpdsMacSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @UpdateProvider(type=TPmUpdsMacSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TPmUpdsMac record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_PM_UPDS_MAC
     *
     * @mbggenerated Wed Sep 03 16:12:13 KST 2014
     */
    @Update({
        "update IN.T_PM_UPDS_MAC",
        "set INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR}",
        "where GRP_CD = #{grpCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TPmUpdsMac record);
}
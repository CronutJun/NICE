package com.nicetcm.nibsplus.broker.ams.mapper;

import com.nicetcm.nibsplus.broker.ams.model.TRcIniInfHis;
import com.nicetcm.nibsplus.broker.ams.model.TRcIniInfHisKey;
import com.nicetcm.nibsplus.broker.ams.model.TRcIniInfHisSpec;
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

public interface TRcIniInfHisMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @SelectProvider(type=TRcIniInfHisSqlProvider.class, method="countBySpec")
    int countBySpec(TRcIniInfHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @DeleteProvider(type=TRcIniInfHisSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TRcIniInfHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @Delete({
        "delete from IN.T_RC_INI_INF_HIS",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and INI_FILE_NM = #{iniFileNm,jdbcType=VARCHAR}",
          "and INI_SECT_NM = #{iniSectNm,jdbcType=VARCHAR}",
          "and INI_KEY_NM = #{iniKeyNm,jdbcType=VARCHAR}",
          "and TRX_DATE = #{trxDate,jdbcType=VARCHAR}",
          "and TRX_NO = #{trxNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TRcIniInfHisKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @Insert({
        "insert into IN.T_RC_INI_INF_HIS (ORG_CD, BRANCH_CD, ",
        "MAC_NO, INI_FILE_NM, ",
        "INI_SECT_NM, INI_KEY_NM, ",
        "TRX_DATE, TRX_NO, ",
        "INSERT_UID, INSERT_DATE, ",
        "INI_VAL)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{iniFileNm,jdbcType=VARCHAR}, ",
        "#{iniSectNm,jdbcType=VARCHAR}, #{iniKeyNm,jdbcType=VARCHAR}, ",
        "#{trxDate,jdbcType=VARCHAR}, #{trxNo,jdbcType=VARCHAR}, ",
        "#{insertUid,jdbcType=VARCHAR}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{iniVal,jdbcType=VARCHAR})"
    })
    int insert(TRcIniInfHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @InsertProvider(type=TRcIniInfHisSqlProvider.class, method="insertSelective")
    int insertSelective(TRcIniInfHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @SelectProvider(type=TRcIniInfHisSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INI_FILE_NM", property="iniFileNm", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INI_SECT_NM", property="iniSectNm", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INI_KEY_NM", property="iniKeyNm", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_DATE", property="trxDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_NO", property="trxNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INI_VAL", property="iniVal", jdbcType=JdbcType.VARCHAR)
    })
    List<TRcIniInfHis> selectBySpec(TRcIniInfHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, BRANCH_CD, MAC_NO, INI_FILE_NM, INI_SECT_NM, INI_KEY_NM, TRX_DATE, TRX_NO, ",
        "INSERT_UID, INSERT_DATE, INI_VAL",
        "from IN.T_RC_INI_INF_HIS",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and INI_FILE_NM = #{iniFileNm,jdbcType=VARCHAR}",
          "and INI_SECT_NM = #{iniSectNm,jdbcType=VARCHAR}",
          "and INI_KEY_NM = #{iniKeyNm,jdbcType=VARCHAR}",
          "and TRX_DATE = #{trxDate,jdbcType=VARCHAR}",
          "and TRX_NO = #{trxNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INI_FILE_NM", property="iniFileNm", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INI_SECT_NM", property="iniSectNm", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INI_KEY_NM", property="iniKeyNm", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_DATE", property="trxDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_NO", property="trxNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INI_VAL", property="iniVal", jdbcType=JdbcType.VARCHAR)
    })
    TRcIniInfHis selectByPrimaryKey(TRcIniInfHisKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @UpdateProvider(type=TRcIniInfHisSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TRcIniInfHis record, @Param("spec") TRcIniInfHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @UpdateProvider(type=TRcIniInfHisSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TRcIniInfHis record, @Param("spec") TRcIniInfHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @UpdateProvider(type=TRcIniInfHisSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TRcIniInfHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RC_INI_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @Update({
        "update IN.T_RC_INI_INF_HIS",
        "set INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "INI_VAL = #{iniVal,jdbcType=VARCHAR}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and INI_FILE_NM = #{iniFileNm,jdbcType=VARCHAR}",
          "and INI_SECT_NM = #{iniSectNm,jdbcType=VARCHAR}",
          "and INI_KEY_NM = #{iniKeyNm,jdbcType=VARCHAR}",
          "and TRX_DATE = #{trxDate,jdbcType=VARCHAR}",
          "and TRX_NO = #{trxNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TRcIniInfHis record);
}
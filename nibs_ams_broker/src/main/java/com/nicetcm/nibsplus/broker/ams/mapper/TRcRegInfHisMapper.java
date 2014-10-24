package com.nicetcm.nibsplus.broker.ams.mapper;

import com.nicetcm.nibsplus.broker.ams.model.TRcRegInfHis;
import com.nicetcm.nibsplus.broker.ams.model.TRcRegInfHisKey;
import com.nicetcm.nibsplus.broker.ams.model.TRcRegInfHisSpec;
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

public interface TRcRegInfHisMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @SelectProvider(type=TRcRegInfHisSqlProvider.class, method="countBySpec")
    int countBySpec(TRcRegInfHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @DeleteProvider(type=TRcRegInfHisSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TRcRegInfHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @Delete({
        "delete from AMS.T_RC_REG_INF_HIS",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and REG_BASE_KEY = #{regBaseKey,jdbcType=VARCHAR}",
          "and REG_KEY_PATH = #{regKeyPath,jdbcType=VARCHAR}",
          "and REG_KEY_NM = #{regKeyNm,jdbcType=VARCHAR}",
          "and TRX_DATE = #{trxDate,jdbcType=VARCHAR}",
          "and TRX_NO = #{trxNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TRcRegInfHisKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @Insert({
        "insert into AMS.T_RC_REG_INF_HIS (ORG_CD, BRANCH_CD, ",
        "MAC_NO, REG_BASE_KEY, ",
        "REG_KEY_PATH, REG_KEY_NM, ",
        "TRX_DATE, TRX_NO, ",
        "INSERT_UID, INSERT_DATE, ",
        "REG_VAL)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{regBaseKey,jdbcType=VARCHAR}, ",
        "#{regKeyPath,jdbcType=VARCHAR}, #{regKeyNm,jdbcType=VARCHAR}, ",
        "#{trxDate,jdbcType=VARCHAR}, #{trxNo,jdbcType=VARCHAR}, ",
        "#{insertUid,jdbcType=VARCHAR}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{regVal,jdbcType=VARCHAR})"
    })
    int insert(TRcRegInfHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @InsertProvider(type=TRcRegInfHisSqlProvider.class, method="insertSelective")
    int insertSelective(TRcRegInfHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @SelectProvider(type=TRcRegInfHisSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REG_BASE_KEY", property="regBaseKey", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REG_KEY_PATH", property="regKeyPath", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REG_KEY_NM", property="regKeyNm", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_DATE", property="trxDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_NO", property="trxNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REG_VAL", property="regVal", jdbcType=JdbcType.VARCHAR)
    })
    List<TRcRegInfHis> selectBySpec(TRcRegInfHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, BRANCH_CD, MAC_NO, REG_BASE_KEY, REG_KEY_PATH, REG_KEY_NM, TRX_DATE, ",
        "TRX_NO, INSERT_UID, INSERT_DATE, REG_VAL",
        "from AMS.T_RC_REG_INF_HIS",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and REG_BASE_KEY = #{regBaseKey,jdbcType=VARCHAR}",
          "and REG_KEY_PATH = #{regKeyPath,jdbcType=VARCHAR}",
          "and REG_KEY_NM = #{regKeyNm,jdbcType=VARCHAR}",
          "and TRX_DATE = #{trxDate,jdbcType=VARCHAR}",
          "and TRX_NO = #{trxNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REG_BASE_KEY", property="regBaseKey", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REG_KEY_PATH", property="regKeyPath", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REG_KEY_NM", property="regKeyNm", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_DATE", property="trxDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_NO", property="trxNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REG_VAL", property="regVal", jdbcType=JdbcType.VARCHAR)
    })
    TRcRegInfHis selectByPrimaryKey(TRcRegInfHisKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @UpdateProvider(type=TRcRegInfHisSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TRcRegInfHis record, @Param("spec") TRcRegInfHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @UpdateProvider(type=TRcRegInfHisSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TRcRegInfHis record, @Param("spec") TRcRegInfHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @UpdateProvider(type=TRcRegInfHisSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TRcRegInfHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table AMS.T_RC_REG_INF_HIS
     *
     * @mbggenerated Tue Aug 19 16:35:31 KST 2014
     */
    @Update({
        "update AMS.T_RC_REG_INF_HIS",
        "set INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "REG_VAL = #{regVal,jdbcType=VARCHAR}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and REG_BASE_KEY = #{regBaseKey,jdbcType=VARCHAR}",
          "and REG_KEY_PATH = #{regKeyPath,jdbcType=VARCHAR}",
          "and REG_KEY_NM = #{regKeyNm,jdbcType=VARCHAR}",
          "and TRX_DATE = #{trxDate,jdbcType=VARCHAR}",
          "and TRX_NO = #{trxNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TRcRegInfHis record);
}
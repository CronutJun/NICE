package com.nicetcm.nibsplus.broker.ams.mapper;

import com.nicetcm.nibsplus.broker.ams.model.TJmFile;
import com.nicetcm.nibsplus.broker.ams.model.TJmFileKey;
import com.nicetcm.nibsplus.broker.ams.model.TJmFileSpec;
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

public interface TJmFileMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @SelectProvider(type=TJmFileSqlProvider.class, method="countBySpec")
    int countBySpec(TJmFileSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @DeleteProvider(type=TJmFileSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TJmFileSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @Delete({
        "delete from IN.T_JM_FILE",
        "where MAC_TRX_DATE = #{macTrxDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and FILE_NAME = #{fileName,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TJmFileKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @Insert({
        "insert into IN.T_JM_FILE (MAC_TRX_DATE, ORG_CD, ",
        "BRANCH_CD, MAC_NO, ",
        "FILE_NAME, INSERT_DATE, ",
        "INSERT_UID, FILE_PATH)",
        "values (#{macTrxDate,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{branchCd,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
        "#{fileName,jdbcType=VARCHAR}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{insertUid,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR})"
    })
    int insert(TJmFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @InsertProvider(type=TJmFileSqlProvider.class, method="insertSelective")
    int insertSelective(TJmFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @SelectProvider(type=TJmFileSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="MAC_TRX_DATE", property="macTrxDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="FILE_NAME", property="fileName", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_PATH", property="filePath", jdbcType=JdbcType.VARCHAR)
    })
    List<TJmFile> selectBySpec(TJmFileSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @Select({
        "select",
        "MAC_TRX_DATE, ORG_CD, BRANCH_CD, MAC_NO, FILE_NAME, INSERT_DATE, INSERT_UID, ",
        "FILE_PATH",
        "from IN.T_JM_FILE",
        "where MAC_TRX_DATE = #{macTrxDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and FILE_NAME = #{fileName,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="MAC_TRX_DATE", property="macTrxDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="FILE_NAME", property="fileName", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="FILE_PATH", property="filePath", jdbcType=JdbcType.VARCHAR)
    })
    TJmFile selectByPrimaryKey(TJmFileKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @UpdateProvider(type=TJmFileSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TJmFile record, @Param("spec") TJmFileSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @UpdateProvider(type=TJmFileSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TJmFile record, @Param("spec") TJmFileSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @UpdateProvider(type=TJmFileSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TJmFile record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_JM_FILE
     *
     * @mbggenerated Thu Sep 04 09:48:36 KST 2014
     */
    @Update({
        "update IN.T_JM_FILE",
        "set INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
          "FILE_PATH = #{filePath,jdbcType=VARCHAR}",
        "where MAC_TRX_DATE = #{macTrxDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and FILE_NAME = #{fileName,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TJmFile record);
}
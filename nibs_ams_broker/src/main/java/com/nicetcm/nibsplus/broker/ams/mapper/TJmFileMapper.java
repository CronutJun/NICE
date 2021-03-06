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
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @SelectProvider(type = TJmFileSqlProvider.class, method = "countByExample")
    int countByExample(TJmFileSpec example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @DeleteProvider(type = TJmFileSqlProvider.class, method = "deleteByExample")
    int deleteByExample(TJmFileSpec example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @Delete({ "delete from AMS.T_JM_FILE",
            "where MAC_TRX_DATE = #{macTrxDate,jdbcType=VARCHAR}",
            "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
            "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
            "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
            "and FILE_NAME = #{fileName,jdbcType=VARCHAR}" })
    int deleteByPrimaryKey(TJmFileKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @Insert({
            "insert into AMS.T_JM_FILE (MAC_TRX_DATE, ORG_CD, ",
            "BRANCH_CD, MAC_NO, ",
            "FILE_NAME, INSERT_DATE, ",
            "INSERT_UID, UPDATE_DATE, ",
            "UPDATE_UID, FILE_CL, ",
            "ZIP_FILE_NAME, FILE_PATH)",
            "values (#{macTrxDate,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
            "#{branchCd,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
            "#{fileName,jdbcType=VARCHAR}, #{insertDate,jdbcType=TIMESTAMP}, ",
            "#{insertUid,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP}, ",
            "#{updateUid,jdbcType=VARCHAR}, #{fileCl,jdbcType=VARCHAR}, ",
            "#{zipFileName,jdbcType=VARCHAR}, #{filePath,jdbcType=VARCHAR})" })
    int insert(TJmFile record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @InsertProvider(type = TJmFileSqlProvider.class, method = "insertSelective")
    int insertSelective(TJmFile record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @SelectProvider(type = TJmFileSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "MAC_TRX_DATE", property = "macTrxDate", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "ORG_CD", property = "orgCd", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "BRANCH_CD", property = "branchCd", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "MAC_NO", property = "macNo", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "FILE_NAME", property = "fileName", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "INSERT_DATE", property = "insertDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "INSERT_UID", property = "insertUid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "UPDATE_DATE", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "UPDATE_UID", property = "updateUid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FILE_CL", property = "fileCl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ZIP_FILE_NAME", property = "zipFileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FILE_PATH", property = "filePath", jdbcType = JdbcType.VARCHAR) })
    List<TJmFile> selectByExample(TJmFileSpec example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @Select({
            "select",
            "MAC_TRX_DATE, ORG_CD, BRANCH_CD, MAC_NO, FILE_NAME, INSERT_DATE, INSERT_UID, ",
            "UPDATE_DATE, UPDATE_UID, FILE_CL, ZIP_FILE_NAME, FILE_PATH",
            "from AMS.T_JM_FILE",
            "where MAC_TRX_DATE = #{macTrxDate,jdbcType=VARCHAR}",
            "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
            "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
            "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
            "and FILE_NAME = #{fileName,jdbcType=VARCHAR}" })
    @Results({
            @Result(column = "MAC_TRX_DATE", property = "macTrxDate", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "ORG_CD", property = "orgCd", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "BRANCH_CD", property = "branchCd", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "MAC_NO", property = "macNo", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "FILE_NAME", property = "fileName", jdbcType = JdbcType.VARCHAR, id = true),
            @Result(column = "INSERT_DATE", property = "insertDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "INSERT_UID", property = "insertUid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "UPDATE_DATE", property = "updateDate", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "UPDATE_UID", property = "updateUid", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FILE_CL", property = "fileCl", jdbcType = JdbcType.VARCHAR),
            @Result(column = "ZIP_FILE_NAME", property = "zipFileName", jdbcType = JdbcType.VARCHAR),
            @Result(column = "FILE_PATH", property = "filePath", jdbcType = JdbcType.VARCHAR) })
    TJmFile selectByPrimaryKey(TJmFileKey key);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @UpdateProvider(type = TJmFileSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") TJmFile record,
            @Param("example") TJmFileSpec example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @UpdateProvider(type = TJmFileSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") TJmFile record,
            @Param("example") TJmFileSpec example);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @UpdateProvider(type = TJmFileSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TJmFile record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table AMS.T_JM_FILE
     * @mbggenerated  Tue Jan 06 16:03:41 KST 2015
     */
    @Update({ "update AMS.T_JM_FILE",
            "set INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
            "INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
            "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
            "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
            "FILE_CL = #{fileCl,jdbcType=VARCHAR},",
            "ZIP_FILE_NAME = #{zipFileName,jdbcType=VARCHAR},",
            "FILE_PATH = #{filePath,jdbcType=VARCHAR}",
            "where MAC_TRX_DATE = #{macTrxDate,jdbcType=VARCHAR}",
            "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
            "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
            "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
            "and FILE_NAME = #{fileName,jdbcType=VARCHAR}" })
    int updateByPrimaryKey(TJmFile record);
}
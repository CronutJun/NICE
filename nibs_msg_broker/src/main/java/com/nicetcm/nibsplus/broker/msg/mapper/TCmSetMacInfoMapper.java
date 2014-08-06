package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCmSetMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetMacInfoKey;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetMacInfoSpec;
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

public interface TCmSetMacInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @SelectProvider(type=TCmSetMacInfoSqlProvider.class, method="countBySpec")
    int countBySpec(TCmSetMacInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @DeleteProvider(type=TCmSetMacInfoSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCmSetMacInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @Delete({
        "delete from OP.T_CM_SET_MAC_INFO",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and WORK_SEQ = #{workSeq,jdbcType=VARCHAR}",
          "and WORK_CLASS = #{workClass,jdbcType=VARCHAR}",
          "and MAC_SEQ_NO = #{macSeqNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCmSetMacInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @Insert({
        "insert into OP.T_CM_SET_MAC_INFO (ORG_CD, WORK_SEQ, ",
        "WORK_CLASS, MAC_SEQ_NO, ",
        "CLOSE_TYPE, CHANGE_NO, ",
        "ORG_BRANCH_CD, ORG_SITE_CD, ",
        "MAC_NO, MADE_COM_CD, ",
        "MAC_MODEL, MAC_RENT_YN, ",
        "MAC_SET_TYPE, MAC_FEE, ",
        "GW_IP, MAC_IP, SUBNET_MASK, ",
        "EXPECT_DATE, EXPECT_TIME, ",
        "CLOSE_ORG_BRANCH_CD, CLOSE_ORG_SITE_CD, ",
        "CLOSE_MAC_NO, CLOSE_MADE_COM_CD, ",
        "CLOSE_MAC_MODEL, CLOSE_MAC_RENT_YN, ",
        "WORK_RESULT, WORK_END_DATE, ",
        "WORK_MSG, UPDATE_DATE, ",
        "UPDATE_UID, DATA_TYPE)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{workSeq,jdbcType=VARCHAR}, ",
        "#{workClass,jdbcType=VARCHAR}, #{macSeqNo,jdbcType=VARCHAR}, ",
        "#{closeType,jdbcType=VARCHAR}, #{changeNo,jdbcType=DECIMAL}, ",
        "#{orgBranchCd,jdbcType=VARCHAR}, #{orgSiteCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{madeComCd,jdbcType=VARCHAR}, ",
        "#{macModel,jdbcType=VARCHAR}, #{macRentYn,jdbcType=VARCHAR}, ",
        "#{macSetType,jdbcType=VARCHAR}, #{macFee,jdbcType=VARCHAR}, ",
        "#{gwIp,jdbcType=VARCHAR}, #{macIp,jdbcType=VARCHAR}, #{subnetMask,jdbcType=VARCHAR}, ",
        "#{expectDate,jdbcType=VARCHAR}, #{expectTime,jdbcType=VARCHAR}, ",
        "#{closeOrgBranchCd,jdbcType=VARCHAR}, #{closeOrgSiteCd,jdbcType=VARCHAR}, ",
        "#{closeMacNo,jdbcType=VARCHAR}, #{closeMadeComCd,jdbcType=VARCHAR}, ",
        "#{closeMacModel,jdbcType=VARCHAR}, #{closeMacRentYn,jdbcType=VARCHAR}, ",
        "#{workResult,jdbcType=VARCHAR}, #{workEndDate,jdbcType=VARCHAR}, ",
        "#{workMsg,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{updateUid,jdbcType=VARCHAR}, #{dataType,jdbcType=VARCHAR})"
    })
    int insert(TCmSetMacInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @InsertProvider(type=TCmSetMacInfoSqlProvider.class, method="insertSelective")
    int insertSelective(TCmSetMacInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @SelectProvider(type=TCmSetMacInfoSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_SEQ", property="workSeq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_CLASS", property="workClass", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_SEQ_NO", property="macSeqNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TYPE", property="closeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHANGE_NO", property="changeNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_BRANCH_CD", property="orgBranchCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SITE_CD", property="orgSiteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="MADE_COM_CD", property="madeComCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_MODEL", property="macModel", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_RENT_YN", property="macRentYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_SET_TYPE", property="macSetType", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_FEE", property="macFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="GW_IP", property="gwIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_IP", property="macIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="SUBNET_MASK", property="subnetMask", jdbcType=JdbcType.VARCHAR),
        @Result(column="EXPECT_DATE", property="expectDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="EXPECT_TIME", property="expectTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_ORG_BRANCH_CD", property="closeOrgBranchCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_ORG_SITE_CD", property="closeOrgSiteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_MAC_NO", property="closeMacNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_MADE_COM_CD", property="closeMadeComCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_MAC_MODEL", property="closeMacModel", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_MAC_RENT_YN", property="closeMacRentYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_RESULT", property="workResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_END_DATE", property="workEndDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_MSG", property="workMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="DATA_TYPE", property="dataType", jdbcType=JdbcType.VARCHAR)
    })
    List<TCmSetMacInfo> selectBySpec(TCmSetMacInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, WORK_SEQ, WORK_CLASS, MAC_SEQ_NO, CLOSE_TYPE, CHANGE_NO, ORG_BRANCH_CD, ",
        "ORG_SITE_CD, MAC_NO, MADE_COM_CD, MAC_MODEL, MAC_RENT_YN, MAC_SET_TYPE, MAC_FEE, ",
        "GW_IP, MAC_IP, SUBNET_MASK, EXPECT_DATE, EXPECT_TIME, CLOSE_ORG_BRANCH_CD, CLOSE_ORG_SITE_CD, ",
        "CLOSE_MAC_NO, CLOSE_MADE_COM_CD, CLOSE_MAC_MODEL, CLOSE_MAC_RENT_YN, WORK_RESULT, ",
        "WORK_END_DATE, WORK_MSG, UPDATE_DATE, UPDATE_UID, DATA_TYPE",
        "from OP.T_CM_SET_MAC_INFO",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and WORK_SEQ = #{workSeq,jdbcType=VARCHAR}",
          "and WORK_CLASS = #{workClass,jdbcType=VARCHAR}",
          "and MAC_SEQ_NO = #{macSeqNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_SEQ", property="workSeq", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="WORK_CLASS", property="workClass", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_SEQ_NO", property="macSeqNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TYPE", property="closeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHANGE_NO", property="changeNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="ORG_BRANCH_CD", property="orgBranchCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SITE_CD", property="orgSiteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="MADE_COM_CD", property="madeComCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_MODEL", property="macModel", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_RENT_YN", property="macRentYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_SET_TYPE", property="macSetType", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_FEE", property="macFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="GW_IP", property="gwIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_IP", property="macIp", jdbcType=JdbcType.VARCHAR),
        @Result(column="SUBNET_MASK", property="subnetMask", jdbcType=JdbcType.VARCHAR),
        @Result(column="EXPECT_DATE", property="expectDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="EXPECT_TIME", property="expectTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_ORG_BRANCH_CD", property="closeOrgBranchCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_ORG_SITE_CD", property="closeOrgSiteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_MAC_NO", property="closeMacNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_MADE_COM_CD", property="closeMadeComCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_MAC_MODEL", property="closeMacModel", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_MAC_RENT_YN", property="closeMacRentYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_RESULT", property="workResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_END_DATE", property="workEndDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_MSG", property="workMsg", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="DATA_TYPE", property="dataType", jdbcType=JdbcType.VARCHAR)
    })
    TCmSetMacInfo selectByPrimaryKey(TCmSetMacInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @UpdateProvider(type=TCmSetMacInfoSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCmSetMacInfo record, @Param("spec") TCmSetMacInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @UpdateProvider(type=TCmSetMacInfoSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCmSetMacInfo record, @Param("spec") TCmSetMacInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @UpdateProvider(type=TCmSetMacInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCmSetMacInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SET_MAC_INFO
     *
     * @mbggenerated Wed Aug 06 09:58:31 KST 2014
     */
    @Update({
        "update OP.T_CM_SET_MAC_INFO",
        "set CLOSE_TYPE = #{closeType,jdbcType=VARCHAR},",
          "CHANGE_NO = #{changeNo,jdbcType=DECIMAL},",
          "ORG_BRANCH_CD = #{orgBranchCd,jdbcType=VARCHAR},",
          "ORG_SITE_CD = #{orgSiteCd,jdbcType=VARCHAR},",
          "MAC_NO = #{macNo,jdbcType=VARCHAR},",
          "MADE_COM_CD = #{madeComCd,jdbcType=VARCHAR},",
          "MAC_MODEL = #{macModel,jdbcType=VARCHAR},",
          "MAC_RENT_YN = #{macRentYn,jdbcType=VARCHAR},",
          "MAC_SET_TYPE = #{macSetType,jdbcType=VARCHAR},",
          "MAC_FEE = #{macFee,jdbcType=VARCHAR},",
          "GW_IP = #{gwIp,jdbcType=VARCHAR},",
          "MAC_IP = #{macIp,jdbcType=VARCHAR},",
          "SUBNET_MASK = #{subnetMask,jdbcType=VARCHAR},",
          "EXPECT_DATE = #{expectDate,jdbcType=VARCHAR},",
          "EXPECT_TIME = #{expectTime,jdbcType=VARCHAR},",
          "CLOSE_ORG_BRANCH_CD = #{closeOrgBranchCd,jdbcType=VARCHAR},",
          "CLOSE_ORG_SITE_CD = #{closeOrgSiteCd,jdbcType=VARCHAR},",
          "CLOSE_MAC_NO = #{closeMacNo,jdbcType=VARCHAR},",
          "CLOSE_MADE_COM_CD = #{closeMadeComCd,jdbcType=VARCHAR},",
          "CLOSE_MAC_MODEL = #{closeMacModel,jdbcType=VARCHAR},",
          "CLOSE_MAC_RENT_YN = #{closeMacRentYn,jdbcType=VARCHAR},",
          "WORK_RESULT = #{workResult,jdbcType=VARCHAR},",
          "WORK_END_DATE = #{workEndDate,jdbcType=VARCHAR},",
          "WORK_MSG = #{workMsg,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
          "DATA_TYPE = #{dataType,jdbcType=VARCHAR}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and WORK_SEQ = #{workSeq,jdbcType=VARCHAR}",
          "and WORK_CLASS = #{workClass,jdbcType=VARCHAR}",
          "and MAC_SEQ_NO = #{macSeqNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCmSetMacInfo record);
}
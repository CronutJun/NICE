package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCmSiteEnvCheck;
import com.nicetcm.nibsplus.broker.msg.model.TCmSiteEnvCheckKey;
import com.nicetcm.nibsplus.broker.msg.model.TCmSiteEnvCheckSpec;
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

public interface TCmSiteEnvCheckMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @SelectProvider(type=TCmSiteEnvCheckSqlProvider.class, method="countBySpec")
    int countBySpec(TCmSiteEnvCheckSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @DeleteProvider(type=TCmSiteEnvCheckSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCmSiteEnvCheckSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @Delete({
        "delete from OP.T_CM_SITE_ENV_CHECK",
        "where CHECK_DATE = #{checkDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and SITE_CD = #{siteCd,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCmSiteEnvCheckKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @Insert({
        "insert into OP.T_CM_SITE_ENV_CHECK (CHECK_DATE, ORG_CD, ",
        "BRANCH_CD, SITE_CD, ",
        "MAC_FRONT_GLASS_CHECK, MAC_FRONT_CHECK, ",
        "MAC_GAP_CHECK, MONITOR_CHECK, ",
        "BANKBOOK_GAP_CHECK, CARD_GAP_CHECK, ",
        "INTERPHONE_CHECK, FIX_CHECK, ",
        "FLOOR_CHECK, IN_WALL_CHECK, ",
        "OUT_WALL_CHECK, BOOTH_GLASS_CHECK, ",
        "UP_CHECK, LAMP_CHECK, ",
        "STICKER_CHECK, GABAGE_CHECK, ",
        "OUTDOOR_HEATER_CHECK, SITE_ARROUD_CHECK, ",
        "PHOTO_URL, MAC_FRONT_GLASS_PHOTO, ",
        "MAC_FRONT_PHOTO, MAC_GAP_PHOTO, ",
        "MONITOR_PHOTO, BANKBOOK_GAP_PHOTO, ",
        "CARD_GAP_PHOTO, INTERPHONE_PHOTO, ",
        "FIX_PHOTO, FLOOR_PHOTO, ",
        "IN_WALL_PHOTO, OUT_WALL_PHOTO, ",
        "BOOTH_GLASS_PHOTO, UP_PHOTO, ",
        "LAMP_PHOTO, STICKER_PHOTO, ",
        "GABAGE_PHOTO, OUTDOOR_HEATER_PHOTO, ",
        "SITE_ARROUD_PHOTO, REMARK, ",
        "MEMBER_ID, PHOTO_YN, ",
        "ORG_SEND_YN, ORG_SEND_DATE, ",
        "UPDATE_DATE, UPDATE_UID)",
        "values (#{checkDate,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{branchCd,jdbcType=VARCHAR}, #{siteCd,jdbcType=VARCHAR}, ",
        "#{macFrontGlassCheck,jdbcType=VARCHAR}, #{macFrontCheck,jdbcType=VARCHAR}, ",
        "#{macGapCheck,jdbcType=VARCHAR}, #{monitorCheck,jdbcType=VARCHAR}, ",
        "#{bankbookGapCheck,jdbcType=VARCHAR}, #{cardGapCheck,jdbcType=VARCHAR}, ",
        "#{interphoneCheck,jdbcType=VARCHAR}, #{fixCheck,jdbcType=VARCHAR}, ",
        "#{floorCheck,jdbcType=VARCHAR}, #{inWallCheck,jdbcType=VARCHAR}, ",
        "#{outWallCheck,jdbcType=VARCHAR}, #{boothGlassCheck,jdbcType=VARCHAR}, ",
        "#{upCheck,jdbcType=VARCHAR}, #{lampCheck,jdbcType=VARCHAR}, ",
        "#{stickerCheck,jdbcType=VARCHAR}, #{gabageCheck,jdbcType=VARCHAR}, ",
        "#{outdoorHeaterCheck,jdbcType=VARCHAR}, #{siteArroudCheck,jdbcType=VARCHAR}, ",
        "#{photoUrl,jdbcType=VARCHAR}, #{macFrontGlassPhoto,jdbcType=VARCHAR}, ",
        "#{macFrontPhoto,jdbcType=VARCHAR}, #{macGapPhoto,jdbcType=VARCHAR}, ",
        "#{monitorPhoto,jdbcType=VARCHAR}, #{bankbookGapPhoto,jdbcType=VARCHAR}, ",
        "#{cardGapPhoto,jdbcType=VARCHAR}, #{interphonePhoto,jdbcType=VARCHAR}, ",
        "#{fixPhoto,jdbcType=VARCHAR}, #{floorPhoto,jdbcType=VARCHAR}, ",
        "#{inWallPhoto,jdbcType=VARCHAR}, #{outWallPhoto,jdbcType=VARCHAR}, ",
        "#{boothGlassPhoto,jdbcType=VARCHAR}, #{upPhoto,jdbcType=VARCHAR}, ",
        "#{lampPhoto,jdbcType=VARCHAR}, #{stickerPhoto,jdbcType=VARCHAR}, ",
        "#{gabagePhoto,jdbcType=VARCHAR}, #{outdoorHeaterPhoto,jdbcType=VARCHAR}, ",
        "#{siteArroudPhoto,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{memberId,jdbcType=VARCHAR}, #{photoYn,jdbcType=VARCHAR}, ",
        "#{orgSendYn,jdbcType=VARCHAR}, #{orgSendDate,jdbcType=TIMESTAMP}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{updateUid,jdbcType=VARCHAR})"
    })
    int insert(TCmSiteEnvCheck record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @InsertProvider(type=TCmSiteEnvCheckSqlProvider.class, method="insertSelective")
    int insertSelective(TCmSiteEnvCheck record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @SelectProvider(type=TCmSiteEnvCheckSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="CHECK_DATE", property="checkDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_FRONT_GLASS_CHECK", property="macFrontGlassCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_FRONT_CHECK", property="macFrontCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_GAP_CHECK", property="macGapCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="MONITOR_CHECK", property="monitorCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANKBOOK_GAP_CHECK", property="bankbookGapCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="CARD_GAP_CHECK", property="cardGapCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="INTERPHONE_CHECK", property="interphoneCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="FIX_CHECK", property="fixCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="FLOOR_CHECK", property="floorCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_WALL_CHECK", property="inWallCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_WALL_CHECK", property="outWallCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="BOOTH_GLASS_CHECK", property="boothGlassCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="UP_CHECK", property="upCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="LAMP_CHECK", property="lampCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="STICKER_CHECK", property="stickerCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="GABAGE_CHECK", property="gabageCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUTDOOR_HEATER_CHECK", property="outdoorHeaterCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_ARROUD_CHECK", property="siteArroudCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHOTO_URL", property="photoUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_FRONT_GLASS_PHOTO", property="macFrontGlassPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_FRONT_PHOTO", property="macFrontPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_GAP_PHOTO", property="macGapPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="MONITOR_PHOTO", property="monitorPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANKBOOK_GAP_PHOTO", property="bankbookGapPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="CARD_GAP_PHOTO", property="cardGapPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="INTERPHONE_PHOTO", property="interphonePhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="FIX_PHOTO", property="fixPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="FLOOR_PHOTO", property="floorPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_WALL_PHOTO", property="inWallPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_WALL_PHOTO", property="outWallPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="BOOTH_GLASS_PHOTO", property="boothGlassPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="UP_PHOTO", property="upPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="LAMP_PHOTO", property="lampPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="STICKER_PHOTO", property="stickerPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="GABAGE_PHOTO", property="gabagePhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUTDOOR_HEATER_PHOTO", property="outdoorHeaterPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_ARROUD_PHOTO", property="siteArroudPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="MEMBER_ID", property="memberId", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHOTO_YN", property="photoYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_DATE", property="orgSendDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    List<TCmSiteEnvCheck> selectBySpec(TCmSiteEnvCheckSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @Select({
        "select",
        "CHECK_DATE, ORG_CD, BRANCH_CD, SITE_CD, MAC_FRONT_GLASS_CHECK, MAC_FRONT_CHECK, ",
        "MAC_GAP_CHECK, MONITOR_CHECK, BANKBOOK_GAP_CHECK, CARD_GAP_CHECK, INTERPHONE_CHECK, ",
        "FIX_CHECK, FLOOR_CHECK, IN_WALL_CHECK, OUT_WALL_CHECK, BOOTH_GLASS_CHECK, UP_CHECK, ",
        "LAMP_CHECK, STICKER_CHECK, GABAGE_CHECK, OUTDOOR_HEATER_CHECK, SITE_ARROUD_CHECK, ",
        "PHOTO_URL, MAC_FRONT_GLASS_PHOTO, MAC_FRONT_PHOTO, MAC_GAP_PHOTO, MONITOR_PHOTO, ",
        "BANKBOOK_GAP_PHOTO, CARD_GAP_PHOTO, INTERPHONE_PHOTO, FIX_PHOTO, FLOOR_PHOTO, ",
        "IN_WALL_PHOTO, OUT_WALL_PHOTO, BOOTH_GLASS_PHOTO, UP_PHOTO, LAMP_PHOTO, STICKER_PHOTO, ",
        "GABAGE_PHOTO, OUTDOOR_HEATER_PHOTO, SITE_ARROUD_PHOTO, REMARK, MEMBER_ID, PHOTO_YN, ",
        "ORG_SEND_YN, ORG_SEND_DATE, UPDATE_DATE, UPDATE_UID",
        "from OP.T_CM_SITE_ENV_CHECK",
        "where CHECK_DATE = #{checkDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and SITE_CD = #{siteCd,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="CHECK_DATE", property="checkDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_FRONT_GLASS_CHECK", property="macFrontGlassCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_FRONT_CHECK", property="macFrontCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_GAP_CHECK", property="macGapCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="MONITOR_CHECK", property="monitorCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANKBOOK_GAP_CHECK", property="bankbookGapCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="CARD_GAP_CHECK", property="cardGapCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="INTERPHONE_CHECK", property="interphoneCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="FIX_CHECK", property="fixCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="FLOOR_CHECK", property="floorCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_WALL_CHECK", property="inWallCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_WALL_CHECK", property="outWallCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="BOOTH_GLASS_CHECK", property="boothGlassCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="UP_CHECK", property="upCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="LAMP_CHECK", property="lampCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="STICKER_CHECK", property="stickerCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="GABAGE_CHECK", property="gabageCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUTDOOR_HEATER_CHECK", property="outdoorHeaterCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_ARROUD_CHECK", property="siteArroudCheck", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHOTO_URL", property="photoUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_FRONT_GLASS_PHOTO", property="macFrontGlassPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_FRONT_PHOTO", property="macFrontPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_GAP_PHOTO", property="macGapPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="MONITOR_PHOTO", property="monitorPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANKBOOK_GAP_PHOTO", property="bankbookGapPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="CARD_GAP_PHOTO", property="cardGapPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="INTERPHONE_PHOTO", property="interphonePhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="FIX_PHOTO", property="fixPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="FLOOR_PHOTO", property="floorPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_WALL_PHOTO", property="inWallPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_WALL_PHOTO", property="outWallPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="BOOTH_GLASS_PHOTO", property="boothGlassPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="UP_PHOTO", property="upPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="LAMP_PHOTO", property="lampPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="STICKER_PHOTO", property="stickerPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="GABAGE_PHOTO", property="gabagePhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUTDOOR_HEATER_PHOTO", property="outdoorHeaterPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_ARROUD_PHOTO", property="siteArroudPhoto", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="MEMBER_ID", property="memberId", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHOTO_YN", property="photoYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_DATE", property="orgSendDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    TCmSiteEnvCheck selectByPrimaryKey(TCmSiteEnvCheckKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @UpdateProvider(type=TCmSiteEnvCheckSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCmSiteEnvCheck record, @Param("spec") TCmSiteEnvCheckSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @UpdateProvider(type=TCmSiteEnvCheckSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCmSiteEnvCheck record, @Param("spec") TCmSiteEnvCheckSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @UpdateProvider(type=TCmSiteEnvCheckSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCmSiteEnvCheck record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_SITE_ENV_CHECK
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @Update({
        "update OP.T_CM_SITE_ENV_CHECK",
        "set MAC_FRONT_GLASS_CHECK = #{macFrontGlassCheck,jdbcType=VARCHAR},",
          "MAC_FRONT_CHECK = #{macFrontCheck,jdbcType=VARCHAR},",
          "MAC_GAP_CHECK = #{macGapCheck,jdbcType=VARCHAR},",
          "MONITOR_CHECK = #{monitorCheck,jdbcType=VARCHAR},",
          "BANKBOOK_GAP_CHECK = #{bankbookGapCheck,jdbcType=VARCHAR},",
          "CARD_GAP_CHECK = #{cardGapCheck,jdbcType=VARCHAR},",
          "INTERPHONE_CHECK = #{interphoneCheck,jdbcType=VARCHAR},",
          "FIX_CHECK = #{fixCheck,jdbcType=VARCHAR},",
          "FLOOR_CHECK = #{floorCheck,jdbcType=VARCHAR},",
          "IN_WALL_CHECK = #{inWallCheck,jdbcType=VARCHAR},",
          "OUT_WALL_CHECK = #{outWallCheck,jdbcType=VARCHAR},",
          "BOOTH_GLASS_CHECK = #{boothGlassCheck,jdbcType=VARCHAR},",
          "UP_CHECK = #{upCheck,jdbcType=VARCHAR},",
          "LAMP_CHECK = #{lampCheck,jdbcType=VARCHAR},",
          "STICKER_CHECK = #{stickerCheck,jdbcType=VARCHAR},",
          "GABAGE_CHECK = #{gabageCheck,jdbcType=VARCHAR},",
          "OUTDOOR_HEATER_CHECK = #{outdoorHeaterCheck,jdbcType=VARCHAR},",
          "SITE_ARROUD_CHECK = #{siteArroudCheck,jdbcType=VARCHAR},",
          "PHOTO_URL = #{photoUrl,jdbcType=VARCHAR},",
          "MAC_FRONT_GLASS_PHOTO = #{macFrontGlassPhoto,jdbcType=VARCHAR},",
          "MAC_FRONT_PHOTO = #{macFrontPhoto,jdbcType=VARCHAR},",
          "MAC_GAP_PHOTO = #{macGapPhoto,jdbcType=VARCHAR},",
          "MONITOR_PHOTO = #{monitorPhoto,jdbcType=VARCHAR},",
          "BANKBOOK_GAP_PHOTO = #{bankbookGapPhoto,jdbcType=VARCHAR},",
          "CARD_GAP_PHOTO = #{cardGapPhoto,jdbcType=VARCHAR},",
          "INTERPHONE_PHOTO = #{interphonePhoto,jdbcType=VARCHAR},",
          "FIX_PHOTO = #{fixPhoto,jdbcType=VARCHAR},",
          "FLOOR_PHOTO = #{floorPhoto,jdbcType=VARCHAR},",
          "IN_WALL_PHOTO = #{inWallPhoto,jdbcType=VARCHAR},",
          "OUT_WALL_PHOTO = #{outWallPhoto,jdbcType=VARCHAR},",
          "BOOTH_GLASS_PHOTO = #{boothGlassPhoto,jdbcType=VARCHAR},",
          "UP_PHOTO = #{upPhoto,jdbcType=VARCHAR},",
          "LAMP_PHOTO = #{lampPhoto,jdbcType=VARCHAR},",
          "STICKER_PHOTO = #{stickerPhoto,jdbcType=VARCHAR},",
          "GABAGE_PHOTO = #{gabagePhoto,jdbcType=VARCHAR},",
          "OUTDOOR_HEATER_PHOTO = #{outdoorHeaterPhoto,jdbcType=VARCHAR},",
          "SITE_ARROUD_PHOTO = #{siteArroudPhoto,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "MEMBER_ID = #{memberId,jdbcType=VARCHAR},",
          "PHOTO_YN = #{photoYn,jdbcType=VARCHAR},",
          "ORG_SEND_YN = #{orgSendYn,jdbcType=VARCHAR},",
          "ORG_SEND_DATE = #{orgSendDate,jdbcType=TIMESTAMP},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR}",
        "where CHECK_DATE = #{checkDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and SITE_CD = #{siteCd,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCmSiteEnvCheck record);
}
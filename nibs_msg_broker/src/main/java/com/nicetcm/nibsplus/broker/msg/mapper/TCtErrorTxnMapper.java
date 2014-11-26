package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxnKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxnSpec;
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

public interface TCtErrorTxnMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @SelectProvider(type=TCtErrorTxnSqlProvider.class, method="countBySpec")
    int countBySpec(TCtErrorTxnSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @DeleteProvider(type=TCtErrorTxnSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCtErrorTxnSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @Delete({
        "delete from OP.T_CT_ERROR_TXN",
        "where ERROR_NO = #{errorNo,jdbcType=VARCHAR}",
          "and CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and CREATE_TIME = #{createTime,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCtErrorTxnKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @Insert({
        "insert into OP.T_CT_ERROR_TXN (ERROR_NO, CREATE_DATE, ",
        "CREATE_TIME, REPAIR_DATE, ",
        "REPAIR_TIME, FINISH_DATE, ",
        "FINISH_TIME, FINISH_PDA_DATE, ",
        "FINISH_PDA_TIME, FINISH_XPOS, ",
        "FINISH_YPOS, FINISH_SIDO, ",
        "FINISH_GUGUN, FINISH_DONG, ",
        "FINISH_TYPE, FINISH_NM, ",
        "FINISH_UID, REG_DT, ",
        "REG_ID, UPDATE_DATE, ",
        "UPDATE_UID)",
        "values (#{errorNo,jdbcType=VARCHAR}, #{createDate,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=VARCHAR}, #{repairDate,jdbcType=VARCHAR}, ",
        "#{repairTime,jdbcType=VARCHAR}, #{finishDate,jdbcType=VARCHAR}, ",
        "#{finishTime,jdbcType=VARCHAR}, #{finishPdaDate,jdbcType=VARCHAR}, ",
        "#{finishPdaTime,jdbcType=VARCHAR}, #{finishXpos,jdbcType=VARCHAR}, ",
        "#{finishYpos,jdbcType=VARCHAR}, #{finishSido,jdbcType=VARCHAR}, ",
        "#{finishGugun,jdbcType=VARCHAR}, #{finishDong,jdbcType=VARCHAR}, ",
        "#{finishType,jdbcType=VARCHAR}, #{finishNm,jdbcType=VARCHAR}, ",
        "#{finishUid,jdbcType=VARCHAR}, #{regDt,jdbcType=TIMESTAMP}, ",
        "#{regId,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{updateUid,jdbcType=VARCHAR})"
    })
    int insert(TCtErrorTxn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @InsertProvider(type=TCtErrorTxnSqlProvider.class, method="insertSelective")
    int insertSelective(TCtErrorTxn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @SelectProvider(type=TCtErrorTxnSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REPAIR_DATE", property="repairDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="REPAIR_TIME", property="repairTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_DATE", property="finishDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_TIME", property="finishTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_PDA_DATE", property="finishPdaDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_PDA_TIME", property="finishPdaTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_XPOS", property="finishXpos", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_YPOS", property="finishYpos", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_SIDO", property="finishSido", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_GUGUN", property="finishGugun", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_DONG", property="finishDong", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_TYPE", property="finishType", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_NM", property="finishNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_UID", property="finishUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="REG_DT", property="regDt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REG_ID", property="regId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    List<TCtErrorTxn> selectBySpec(TCtErrorTxnSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @Select({
        "select",
        "ERROR_NO, CREATE_DATE, CREATE_TIME, REPAIR_DATE, REPAIR_TIME, FINISH_DATE, FINISH_TIME, ",
        "FINISH_PDA_DATE, FINISH_PDA_TIME, FINISH_XPOS, FINISH_YPOS, FINISH_SIDO, FINISH_GUGUN, ",
        "FINISH_DONG, FINISH_TYPE, FINISH_NM, FINISH_UID, REG_DT, REG_ID, UPDATE_DATE, ",
        "UPDATE_UID",
        "from OP.T_CT_ERROR_TXN",
        "where ERROR_NO = #{errorNo,jdbcType=VARCHAR}",
          "and CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and CREATE_TIME = #{createTime,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REPAIR_DATE", property="repairDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="REPAIR_TIME", property="repairTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_DATE", property="finishDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_TIME", property="finishTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_PDA_DATE", property="finishPdaDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_PDA_TIME", property="finishPdaTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_XPOS", property="finishXpos", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_YPOS", property="finishYpos", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_SIDO", property="finishSido", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_GUGUN", property="finishGugun", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_DONG", property="finishDong", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_TYPE", property="finishType", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_NM", property="finishNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_UID", property="finishUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="REG_DT", property="regDt", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="REG_ID", property="regId", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    TCtErrorTxn selectByPrimaryKey(TCtErrorTxnKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @UpdateProvider(type=TCtErrorTxnSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCtErrorTxn record, @Param("spec") TCtErrorTxnSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @UpdateProvider(type=TCtErrorTxnSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCtErrorTxn record, @Param("spec") TCtErrorTxnSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @UpdateProvider(type=TCtErrorTxnSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCtErrorTxn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_TXN
     *
     * @mbggenerated Wed Nov 26 18:06:13 KST 2014
     */
    @Update({
        "update OP.T_CT_ERROR_TXN",
        "set REPAIR_DATE = #{repairDate,jdbcType=VARCHAR},",
          "REPAIR_TIME = #{repairTime,jdbcType=VARCHAR},",
          "FINISH_DATE = #{finishDate,jdbcType=VARCHAR},",
          "FINISH_TIME = #{finishTime,jdbcType=VARCHAR},",
          "FINISH_PDA_DATE = #{finishPdaDate,jdbcType=VARCHAR},",
          "FINISH_PDA_TIME = #{finishPdaTime,jdbcType=VARCHAR},",
          "FINISH_XPOS = #{finishXpos,jdbcType=VARCHAR},",
          "FINISH_YPOS = #{finishYpos,jdbcType=VARCHAR},",
          "FINISH_SIDO = #{finishSido,jdbcType=VARCHAR},",
          "FINISH_GUGUN = #{finishGugun,jdbcType=VARCHAR},",
          "FINISH_DONG = #{finishDong,jdbcType=VARCHAR},",
          "FINISH_TYPE = #{finishType,jdbcType=VARCHAR},",
          "FINISH_NM = #{finishNm,jdbcType=VARCHAR},",
          "FINISH_UID = #{finishUid,jdbcType=VARCHAR},",
          "REG_DT = #{regDt,jdbcType=TIMESTAMP},",
          "REG_ID = #{regId,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR}",
        "where ERROR_NO = #{errorNo,jdbcType=VARCHAR}",
          "and CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and CREATE_TIME = #{createTime,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCtErrorTxn record);
}
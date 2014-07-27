package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnInoutStat;
import com.nicetcm.nibsplus.broker.msg.model.TFnInoutStatKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnInoutStatSpec;
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

public interface TFnInoutStatMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @SelectProvider(type=TFnInoutStatSqlProvider.class, method="countBySpec")
    int countBySpec(TFnInoutStatSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @DeleteProvider(type=TFnInoutStatSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnInoutStatSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_INOUT_STAT",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and TRADE_DATE = #{tradeDate,jdbcType=VARCHAR}",
          "and KJ_GB = #{kjGb,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnInoutStatKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_INOUT_STAT (ORG_CD, BRANCH_CD, ",
        "MAC_NO, TRADE_DATE, ",
        "KJ_GB, SITE_CD, IN_01, ",
        "IN_01_CNT, OUT_01, ",
        "OUT_01_CNT, IN_02, ",
        "IN_02_CNT, OUT_02, ",
        "OUT_02_CNT, IN_03, ",
        "IN_03_CNT, OUT_03, ",
        "OUT_03_CNT, IN_04, ",
        "IN_04_CNT, OUT_04, ",
        "OUT_04_CNT, IN_05, ",
        "IN_05_CNT, OUT_05, ",
        "OUT_05_CNT, IN_06, ",
        "IN_06_CNT, OUT_06, ",
        "OUT_06_CNT, IN_07, ",
        "IN_07_CNT, OUT_07, ",
        "OUT_07_CNT, IN_08, ",
        "IN_08_CNT, OUT_08, ",
        "OUT_08_CNT, IN_09, ",
        "IN_09_CNT, OUT_09, ",
        "OUT_09_CNT, IN_10, ",
        "IN_10_CNT, OUT_10, ",
        "OUT_10_CNT, IN_11, ",
        "IN_11_CNT, OUT_11, ",
        "OUT_11_CNT, IN_12, ",
        "IN_12_CNT, OUT_12, ",
        "OUT_12_CNT, IN_13, ",
        "IN_13_CNT, OUT_13, ",
        "OUT_13_CNT, IN_14, ",
        "IN_14_CNT, OUT_14, ",
        "OUT_14_CNT, IN_15, ",
        "IN_15_CNT, OUT_15, ",
        "OUT_15_CNT, IN_16, ",
        "IN_16_CNT, OUT_16, ",
        "OUT_16_CNT, IN_17, ",
        "IN_17_CNT, OUT_17, ",
        "OUT_17_CNT, IN_18, ",
        "IN_18_CNT, OUT_18, ",
        "OUT_18_CNT, IN_19, ",
        "IN_19_CNT, OUT_19, ",
        "OUT_19_CNT, IN_20, ",
        "IN_20_CNT, OUT_20, ",
        "OUT_20_CNT, IN_21, ",
        "IN_21_CNT, OUT_21, ",
        "OUT_21_CNT, IN_22, ",
        "IN_22_CNT, OUT_22, ",
        "OUT_22_CNT, IN_23, ",
        "IN_23_CNT, OUT_23, ",
        "OUT_23_CNT, IN_24, ",
        "IN_24_CNT, OUT_24, ",
        "OUT_24_CNT, UPDATE_DATE)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{tradeDate,jdbcType=VARCHAR}, ",
        "#{kjGb,jdbcType=VARCHAR}, #{siteCd,jdbcType=VARCHAR}, #{in01,jdbcType=DECIMAL}, ",
        "#{in01Cnt,jdbcType=DECIMAL}, #{out01,jdbcType=DECIMAL}, ",
        "#{out01Cnt,jdbcType=DECIMAL}, #{in02,jdbcType=DECIMAL}, ",
        "#{in02Cnt,jdbcType=DECIMAL}, #{out02,jdbcType=DECIMAL}, ",
        "#{out02Cnt,jdbcType=DECIMAL}, #{in03,jdbcType=DECIMAL}, ",
        "#{in03Cnt,jdbcType=DECIMAL}, #{out03,jdbcType=DECIMAL}, ",
        "#{out03Cnt,jdbcType=DECIMAL}, #{in04,jdbcType=DECIMAL}, ",
        "#{in04Cnt,jdbcType=DECIMAL}, #{out04,jdbcType=DECIMAL}, ",
        "#{out04Cnt,jdbcType=DECIMAL}, #{in05,jdbcType=DECIMAL}, ",
        "#{in05Cnt,jdbcType=DECIMAL}, #{out05,jdbcType=DECIMAL}, ",
        "#{out05Cnt,jdbcType=DECIMAL}, #{in06,jdbcType=DECIMAL}, ",
        "#{in06Cnt,jdbcType=DECIMAL}, #{out06,jdbcType=DECIMAL}, ",
        "#{out06Cnt,jdbcType=DECIMAL}, #{in07,jdbcType=DECIMAL}, ",
        "#{in07Cnt,jdbcType=DECIMAL}, #{out07,jdbcType=DECIMAL}, ",
        "#{out07Cnt,jdbcType=DECIMAL}, #{in08,jdbcType=DECIMAL}, ",
        "#{in08Cnt,jdbcType=DECIMAL}, #{out08,jdbcType=DECIMAL}, ",
        "#{out08Cnt,jdbcType=DECIMAL}, #{in09,jdbcType=DECIMAL}, ",
        "#{in09Cnt,jdbcType=DECIMAL}, #{out09,jdbcType=DECIMAL}, ",
        "#{out09Cnt,jdbcType=DECIMAL}, #{in10,jdbcType=DECIMAL}, ",
        "#{in10Cnt,jdbcType=DECIMAL}, #{out10,jdbcType=DECIMAL}, ",
        "#{out10Cnt,jdbcType=DECIMAL}, #{in11,jdbcType=DECIMAL}, ",
        "#{in11Cnt,jdbcType=DECIMAL}, #{out11,jdbcType=DECIMAL}, ",
        "#{out11Cnt,jdbcType=DECIMAL}, #{in12,jdbcType=DECIMAL}, ",
        "#{in12Cnt,jdbcType=DECIMAL}, #{out12,jdbcType=DECIMAL}, ",
        "#{out12Cnt,jdbcType=DECIMAL}, #{in13,jdbcType=DECIMAL}, ",
        "#{in13Cnt,jdbcType=DECIMAL}, #{out13,jdbcType=DECIMAL}, ",
        "#{out13Cnt,jdbcType=DECIMAL}, #{in14,jdbcType=DECIMAL}, ",
        "#{in14Cnt,jdbcType=DECIMAL}, #{out14,jdbcType=DECIMAL}, ",
        "#{out14Cnt,jdbcType=DECIMAL}, #{in15,jdbcType=DECIMAL}, ",
        "#{in15Cnt,jdbcType=DECIMAL}, #{out15,jdbcType=DECIMAL}, ",
        "#{out15Cnt,jdbcType=DECIMAL}, #{in16,jdbcType=DECIMAL}, ",
        "#{in16Cnt,jdbcType=DECIMAL}, #{out16,jdbcType=DECIMAL}, ",
        "#{out16Cnt,jdbcType=DECIMAL}, #{in17,jdbcType=DECIMAL}, ",
        "#{in17Cnt,jdbcType=DECIMAL}, #{out17,jdbcType=DECIMAL}, ",
        "#{out17Cnt,jdbcType=DECIMAL}, #{in18,jdbcType=DECIMAL}, ",
        "#{in18Cnt,jdbcType=DECIMAL}, #{out18,jdbcType=DECIMAL}, ",
        "#{out18Cnt,jdbcType=DECIMAL}, #{in19,jdbcType=DECIMAL}, ",
        "#{in19Cnt,jdbcType=DECIMAL}, #{out19,jdbcType=DECIMAL}, ",
        "#{out19Cnt,jdbcType=DECIMAL}, #{in20,jdbcType=DECIMAL}, ",
        "#{in20Cnt,jdbcType=DECIMAL}, #{out20,jdbcType=DECIMAL}, ",
        "#{out20Cnt,jdbcType=DECIMAL}, #{in21,jdbcType=DECIMAL}, ",
        "#{in21Cnt,jdbcType=DECIMAL}, #{out21,jdbcType=DECIMAL}, ",
        "#{out21Cnt,jdbcType=DECIMAL}, #{in22,jdbcType=DECIMAL}, ",
        "#{in22Cnt,jdbcType=DECIMAL}, #{out22,jdbcType=DECIMAL}, ",
        "#{out22Cnt,jdbcType=DECIMAL}, #{in23,jdbcType=DECIMAL}, ",
        "#{in23Cnt,jdbcType=DECIMAL}, #{out23,jdbcType=DECIMAL}, ",
        "#{out23Cnt,jdbcType=DECIMAL}, #{in24,jdbcType=DECIMAL}, ",
        "#{in24Cnt,jdbcType=DECIMAL}, #{out24,jdbcType=DECIMAL}, ",
        "#{out24Cnt,jdbcType=DECIMAL}, #{updateDate,jdbcType=TIMESTAMP})"
    })
    int insert(TFnInoutStat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @InsertProvider(type=TFnInoutStatSqlProvider.class, method="insertSelective")
    int insertSelective(TFnInoutStat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @SelectProvider(type=TFnInoutStatSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRADE_DATE", property="tradeDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="KJ_GB", property="kjGb", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_01", property="in01", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_01_CNT", property="in01Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_01", property="out01", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_01_CNT", property="out01Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_02", property="in02", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_02_CNT", property="in02Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_02", property="out02", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_02_CNT", property="out02Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_03", property="in03", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_03_CNT", property="in03Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_03", property="out03", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_03_CNT", property="out03Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_04", property="in04", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_04_CNT", property="in04Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_04", property="out04", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_04_CNT", property="out04Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_05", property="in05", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_05_CNT", property="in05Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_05", property="out05", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_05_CNT", property="out05Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_06", property="in06", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_06_CNT", property="in06Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_06", property="out06", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_06_CNT", property="out06Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_07", property="in07", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_07_CNT", property="in07Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_07", property="out07", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_07_CNT", property="out07Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_08", property="in08", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_08_CNT", property="in08Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_08", property="out08", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_08_CNT", property="out08Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_09", property="in09", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_09_CNT", property="in09Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_09", property="out09", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_09_CNT", property="out09Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10", property="in10", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10_CNT", property="in10Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10", property="out10", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10_CNT", property="out10Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_11", property="in11", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_11_CNT", property="in11Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_11", property="out11", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_11_CNT", property="out11Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_12", property="in12", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_12_CNT", property="in12Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_12", property="out12", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_12_CNT", property="out12Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_13", property="in13", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_13_CNT", property="in13Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_13", property="out13", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_13_CNT", property="out13Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_14", property="in14", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_14_CNT", property="in14Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_14", property="out14", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_14_CNT", property="out14Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_15", property="in15", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_15_CNT", property="in15Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_15", property="out15", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_15_CNT", property="out15Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_16", property="in16", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_16_CNT", property="in16Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_16", property="out16", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_16_CNT", property="out16Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_17", property="in17", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_17_CNT", property="in17Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_17", property="out17", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_17_CNT", property="out17Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_18", property="in18", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_18_CNT", property="in18Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_18", property="out18", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_18_CNT", property="out18Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_19", property="in19", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_19_CNT", property="in19Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_19", property="out19", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_19_CNT", property="out19Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_20", property="in20", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_20_CNT", property="in20Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_20", property="out20", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_20_CNT", property="out20Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_21", property="in21", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_21_CNT", property="in21Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_21", property="out21", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_21_CNT", property="out21Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_22", property="in22", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_22_CNT", property="in22Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_22", property="out22", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_22_CNT", property="out22Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_23", property="in23", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_23_CNT", property="in23Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_23", property="out23", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_23_CNT", property="out23Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_24", property="in24", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_24_CNT", property="in24Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_24", property="out24", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_24_CNT", property="out24Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TFnInoutStat> selectBySpec(TFnInoutStatSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, BRANCH_CD, MAC_NO, TRADE_DATE, KJ_GB, SITE_CD, IN_01, IN_01_CNT, OUT_01, ",
        "OUT_01_CNT, IN_02, IN_02_CNT, OUT_02, OUT_02_CNT, IN_03, IN_03_CNT, OUT_03, ",
        "OUT_03_CNT, IN_04, IN_04_CNT, OUT_04, OUT_04_CNT, IN_05, IN_05_CNT, OUT_05, ",
        "OUT_05_CNT, IN_06, IN_06_CNT, OUT_06, OUT_06_CNT, IN_07, IN_07_CNT, OUT_07, ",
        "OUT_07_CNT, IN_08, IN_08_CNT, OUT_08, OUT_08_CNT, IN_09, IN_09_CNT, OUT_09, ",
        "OUT_09_CNT, IN_10, IN_10_CNT, OUT_10, OUT_10_CNT, IN_11, IN_11_CNT, OUT_11, ",
        "OUT_11_CNT, IN_12, IN_12_CNT, OUT_12, OUT_12_CNT, IN_13, IN_13_CNT, OUT_13, ",
        "OUT_13_CNT, IN_14, IN_14_CNT, OUT_14, OUT_14_CNT, IN_15, IN_15_CNT, OUT_15, ",
        "OUT_15_CNT, IN_16, IN_16_CNT, OUT_16, OUT_16_CNT, IN_17, IN_17_CNT, OUT_17, ",
        "OUT_17_CNT, IN_18, IN_18_CNT, OUT_18, OUT_18_CNT, IN_19, IN_19_CNT, OUT_19, ",
        "OUT_19_CNT, IN_20, IN_20_CNT, OUT_20, OUT_20_CNT, IN_21, IN_21_CNT, OUT_21, ",
        "OUT_21_CNT, IN_22, IN_22_CNT, OUT_22, OUT_22_CNT, IN_23, IN_23_CNT, OUT_23, ",
        "OUT_23_CNT, IN_24, IN_24_CNT, OUT_24, OUT_24_CNT, UPDATE_DATE",
        "from OP.T_FN_INOUT_STAT",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and TRADE_DATE = #{tradeDate,jdbcType=VARCHAR}",
          "and KJ_GB = #{kjGb,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRADE_DATE", property="tradeDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="KJ_GB", property="kjGb", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_01", property="in01", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_01_CNT", property="in01Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_01", property="out01", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_01_CNT", property="out01Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_02", property="in02", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_02_CNT", property="in02Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_02", property="out02", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_02_CNT", property="out02Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_03", property="in03", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_03_CNT", property="in03Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_03", property="out03", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_03_CNT", property="out03Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_04", property="in04", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_04_CNT", property="in04Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_04", property="out04", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_04_CNT", property="out04Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_05", property="in05", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_05_CNT", property="in05Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_05", property="out05", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_05_CNT", property="out05Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_06", property="in06", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_06_CNT", property="in06Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_06", property="out06", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_06_CNT", property="out06Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_07", property="in07", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_07_CNT", property="in07Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_07", property="out07", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_07_CNT", property="out07Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_08", property="in08", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_08_CNT", property="in08Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_08", property="out08", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_08_CNT", property="out08Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_09", property="in09", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_09_CNT", property="in09Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_09", property="out09", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_09_CNT", property="out09Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10", property="in10", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10_CNT", property="in10Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10", property="out10", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10_CNT", property="out10Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_11", property="in11", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_11_CNT", property="in11Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_11", property="out11", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_11_CNT", property="out11Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_12", property="in12", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_12_CNT", property="in12Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_12", property="out12", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_12_CNT", property="out12Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_13", property="in13", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_13_CNT", property="in13Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_13", property="out13", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_13_CNT", property="out13Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_14", property="in14", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_14_CNT", property="in14Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_14", property="out14", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_14_CNT", property="out14Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_15", property="in15", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_15_CNT", property="in15Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_15", property="out15", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_15_CNT", property="out15Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_16", property="in16", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_16_CNT", property="in16Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_16", property="out16", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_16_CNT", property="out16Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_17", property="in17", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_17_CNT", property="in17Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_17", property="out17", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_17_CNT", property="out17Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_18", property="in18", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_18_CNT", property="in18Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_18", property="out18", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_18_CNT", property="out18Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_19", property="in19", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_19_CNT", property="in19Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_19", property="out19", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_19_CNT", property="out19Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_20", property="in20", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_20_CNT", property="in20Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_20", property="out20", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_20_CNT", property="out20Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_21", property="in21", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_21_CNT", property="in21Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_21", property="out21", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_21_CNT", property="out21Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_22", property="in22", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_22_CNT", property="in22Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_22", property="out22", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_22_CNT", property="out22Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_23", property="in23", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_23_CNT", property="in23Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_23", property="out23", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_23_CNT", property="out23Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_24", property="in24", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_24_CNT", property="in24Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_24", property="out24", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_24_CNT", property="out24Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    TFnInoutStat selectByPrimaryKey(TFnInoutStatKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @UpdateProvider(type=TFnInoutStatSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnInoutStat record, @Param("spec") TFnInoutStatSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @UpdateProvider(type=TFnInoutStatSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnInoutStat record, @Param("spec") TFnInoutStatSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @UpdateProvider(type=TFnInoutStatSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnInoutStat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_INOUT_STAT
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Update({
        "update OP.T_FN_INOUT_STAT",
        "set SITE_CD = #{siteCd,jdbcType=VARCHAR},",
          "IN_01 = #{in01,jdbcType=DECIMAL},",
          "IN_01_CNT = #{in01Cnt,jdbcType=DECIMAL},",
          "OUT_01 = #{out01,jdbcType=DECIMAL},",
          "OUT_01_CNT = #{out01Cnt,jdbcType=DECIMAL},",
          "IN_02 = #{in02,jdbcType=DECIMAL},",
          "IN_02_CNT = #{in02Cnt,jdbcType=DECIMAL},",
          "OUT_02 = #{out02,jdbcType=DECIMAL},",
          "OUT_02_CNT = #{out02Cnt,jdbcType=DECIMAL},",
          "IN_03 = #{in03,jdbcType=DECIMAL},",
          "IN_03_CNT = #{in03Cnt,jdbcType=DECIMAL},",
          "OUT_03 = #{out03,jdbcType=DECIMAL},",
          "OUT_03_CNT = #{out03Cnt,jdbcType=DECIMAL},",
          "IN_04 = #{in04,jdbcType=DECIMAL},",
          "IN_04_CNT = #{in04Cnt,jdbcType=DECIMAL},",
          "OUT_04 = #{out04,jdbcType=DECIMAL},",
          "OUT_04_CNT = #{out04Cnt,jdbcType=DECIMAL},",
          "IN_05 = #{in05,jdbcType=DECIMAL},",
          "IN_05_CNT = #{in05Cnt,jdbcType=DECIMAL},",
          "OUT_05 = #{out05,jdbcType=DECIMAL},",
          "OUT_05_CNT = #{out05Cnt,jdbcType=DECIMAL},",
          "IN_06 = #{in06,jdbcType=DECIMAL},",
          "IN_06_CNT = #{in06Cnt,jdbcType=DECIMAL},",
          "OUT_06 = #{out06,jdbcType=DECIMAL},",
          "OUT_06_CNT = #{out06Cnt,jdbcType=DECIMAL},",
          "IN_07 = #{in07,jdbcType=DECIMAL},",
          "IN_07_CNT = #{in07Cnt,jdbcType=DECIMAL},",
          "OUT_07 = #{out07,jdbcType=DECIMAL},",
          "OUT_07_CNT = #{out07Cnt,jdbcType=DECIMAL},",
          "IN_08 = #{in08,jdbcType=DECIMAL},",
          "IN_08_CNT = #{in08Cnt,jdbcType=DECIMAL},",
          "OUT_08 = #{out08,jdbcType=DECIMAL},",
          "OUT_08_CNT = #{out08Cnt,jdbcType=DECIMAL},",
          "IN_09 = #{in09,jdbcType=DECIMAL},",
          "IN_09_CNT = #{in09Cnt,jdbcType=DECIMAL},",
          "OUT_09 = #{out09,jdbcType=DECIMAL},",
          "OUT_09_CNT = #{out09Cnt,jdbcType=DECIMAL},",
          "IN_10 = #{in10,jdbcType=DECIMAL},",
          "IN_10_CNT = #{in10Cnt,jdbcType=DECIMAL},",
          "OUT_10 = #{out10,jdbcType=DECIMAL},",
          "OUT_10_CNT = #{out10Cnt,jdbcType=DECIMAL},",
          "IN_11 = #{in11,jdbcType=DECIMAL},",
          "IN_11_CNT = #{in11Cnt,jdbcType=DECIMAL},",
          "OUT_11 = #{out11,jdbcType=DECIMAL},",
          "OUT_11_CNT = #{out11Cnt,jdbcType=DECIMAL},",
          "IN_12 = #{in12,jdbcType=DECIMAL},",
          "IN_12_CNT = #{in12Cnt,jdbcType=DECIMAL},",
          "OUT_12 = #{out12,jdbcType=DECIMAL},",
          "OUT_12_CNT = #{out12Cnt,jdbcType=DECIMAL},",
          "IN_13 = #{in13,jdbcType=DECIMAL},",
          "IN_13_CNT = #{in13Cnt,jdbcType=DECIMAL},",
          "OUT_13 = #{out13,jdbcType=DECIMAL},",
          "OUT_13_CNT = #{out13Cnt,jdbcType=DECIMAL},",
          "IN_14 = #{in14,jdbcType=DECIMAL},",
          "IN_14_CNT = #{in14Cnt,jdbcType=DECIMAL},",
          "OUT_14 = #{out14,jdbcType=DECIMAL},",
          "OUT_14_CNT = #{out14Cnt,jdbcType=DECIMAL},",
          "IN_15 = #{in15,jdbcType=DECIMAL},",
          "IN_15_CNT = #{in15Cnt,jdbcType=DECIMAL},",
          "OUT_15 = #{out15,jdbcType=DECIMAL},",
          "OUT_15_CNT = #{out15Cnt,jdbcType=DECIMAL},",
          "IN_16 = #{in16,jdbcType=DECIMAL},",
          "IN_16_CNT = #{in16Cnt,jdbcType=DECIMAL},",
          "OUT_16 = #{out16,jdbcType=DECIMAL},",
          "OUT_16_CNT = #{out16Cnt,jdbcType=DECIMAL},",
          "IN_17 = #{in17,jdbcType=DECIMAL},",
          "IN_17_CNT = #{in17Cnt,jdbcType=DECIMAL},",
          "OUT_17 = #{out17,jdbcType=DECIMAL},",
          "OUT_17_CNT = #{out17Cnt,jdbcType=DECIMAL},",
          "IN_18 = #{in18,jdbcType=DECIMAL},",
          "IN_18_CNT = #{in18Cnt,jdbcType=DECIMAL},",
          "OUT_18 = #{out18,jdbcType=DECIMAL},",
          "OUT_18_CNT = #{out18Cnt,jdbcType=DECIMAL},",
          "IN_19 = #{in19,jdbcType=DECIMAL},",
          "IN_19_CNT = #{in19Cnt,jdbcType=DECIMAL},",
          "OUT_19 = #{out19,jdbcType=DECIMAL},",
          "OUT_19_CNT = #{out19Cnt,jdbcType=DECIMAL},",
          "IN_20 = #{in20,jdbcType=DECIMAL},",
          "IN_20_CNT = #{in20Cnt,jdbcType=DECIMAL},",
          "OUT_20 = #{out20,jdbcType=DECIMAL},",
          "OUT_20_CNT = #{out20Cnt,jdbcType=DECIMAL},",
          "IN_21 = #{in21,jdbcType=DECIMAL},",
          "IN_21_CNT = #{in21Cnt,jdbcType=DECIMAL},",
          "OUT_21 = #{out21,jdbcType=DECIMAL},",
          "OUT_21_CNT = #{out21Cnt,jdbcType=DECIMAL},",
          "IN_22 = #{in22,jdbcType=DECIMAL},",
          "IN_22_CNT = #{in22Cnt,jdbcType=DECIMAL},",
          "OUT_22 = #{out22,jdbcType=DECIMAL},",
          "OUT_22_CNT = #{out22Cnt,jdbcType=DECIMAL},",
          "IN_23 = #{in23,jdbcType=DECIMAL},",
          "IN_23_CNT = #{in23Cnt,jdbcType=DECIMAL},",
          "OUT_23 = #{out23,jdbcType=DECIMAL},",
          "OUT_23_CNT = #{out23Cnt,jdbcType=DECIMAL},",
          "IN_24 = #{in24,jdbcType=DECIMAL},",
          "IN_24_CNT = #{in24Cnt,jdbcType=DECIMAL},",
          "OUT_24 = #{out24,jdbcType=DECIMAL},",
          "OUT_24_CNT = #{out24Cnt,jdbcType=DECIMAL},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and TRADE_DATE = #{tradeDate,jdbcType=VARCHAR}",
          "and KJ_GB = #{kjGb,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnInoutStat record);
}
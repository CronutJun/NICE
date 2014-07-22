package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnRealtimeTrade;
import com.nicetcm.nibsplus.broker.msg.model.TFnRealtimeTradeKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnRealtimeTradeSpec;
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

public interface TFnRealtimeTradeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @SelectProvider(type=TFnRealtimeTradeSqlProvider.class, method="countBySpec")
    int countBySpec(TFnRealtimeTradeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @DeleteProvider(type=TFnRealtimeTradeSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnRealtimeTradeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_REALTIME_TRADE",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and DEAL_TIME = #{dealTime,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnRealtimeTradeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_REALTIME_TRADE (ORG_CD, BRANCH_CD, ",
        "MAC_NO, DEAL_DATE, ",
        "DEAL_TIME, REMAIN_AMT, ",
        "IN_10000_REMCNT, IN_5000_REMCNT, ",
        "IN_1000_REMCNT, OUT_10000_REMCNT, ",
        "OUT_5000_REMCNT, OUT_1000_REMCNT, ",
        "OUT_100_REMCNT, IN_10000_CNT, ",
        "IN_5000_CNT, IN_1000_CNT, ",
        "OUT_10000_CNT, OUT_5000_CNT, ",
        "OUT_1000_CNT, OUT_100_CNT, ",
        "NO_RTN_AMT, NO_RTN_NO, ",
        "UPDATE_DATE, UPDATE_UID, ",
        "IN_50000_REMCNT, IN_50000_CNT, ",
        "IN_500_REMCNT, IN_100_REMCNT, ",
        "IN_50_REMCNT, IN_10_REMCNT, ",
        "IN_500_CNT, IN_100_CNT, ",
        "IN_50_CNT, IN_10_CNT, ",
        "OUT_50000_REMCNT, OUT_50000_CNT, ",
        "OUT_500_REMCNT, OUT_500_CNT, ",
        "OUT_50_REMCNT, OUT_50_CNT, ",
        "OUT_10_REMCNT, OUT_10_CNT, ",
        "SUB_ORG_CD, ATM_DEAL_NO, ",
        "TRANS_SEQ, TRANS_DATE)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{dealDate,jdbcType=VARCHAR}, ",
        "#{dealTime,jdbcType=VARCHAR}, #{remainAmt,jdbcType=DECIMAL}, ",
        "#{in10000Remcnt,jdbcType=DECIMAL}, #{in5000Remcnt,jdbcType=DECIMAL}, ",
        "#{in1000Remcnt,jdbcType=DECIMAL}, #{out10000Remcnt,jdbcType=DECIMAL}, ",
        "#{out5000Remcnt,jdbcType=DECIMAL}, #{out1000Remcnt,jdbcType=DECIMAL}, ",
        "#{out100Remcnt,jdbcType=DECIMAL}, #{in10000Cnt,jdbcType=DECIMAL}, ",
        "#{in5000Cnt,jdbcType=DECIMAL}, #{in1000Cnt,jdbcType=DECIMAL}, ",
        "#{out10000Cnt,jdbcType=DECIMAL}, #{out5000Cnt,jdbcType=DECIMAL}, ",
        "#{out1000Cnt,jdbcType=DECIMAL}, #{out100Cnt,jdbcType=DECIMAL}, ",
        "#{noRtnAmt,jdbcType=DECIMAL}, #{noRtnNo,jdbcType=VARCHAR}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{updateUid,jdbcType=VARCHAR}, ",
        "#{in50000Remcnt,jdbcType=DECIMAL}, #{in50000Cnt,jdbcType=DECIMAL}, ",
        "#{in500Remcnt,jdbcType=DECIMAL}, #{in100Remcnt,jdbcType=DECIMAL}, ",
        "#{in50Remcnt,jdbcType=DECIMAL}, #{in10Remcnt,jdbcType=DECIMAL}, ",
        "#{in500Cnt,jdbcType=DECIMAL}, #{in100Cnt,jdbcType=DECIMAL}, ",
        "#{in50Cnt,jdbcType=DECIMAL}, #{in10Cnt,jdbcType=DECIMAL}, ",
        "#{out50000Remcnt,jdbcType=DECIMAL}, #{out50000Cnt,jdbcType=DECIMAL}, ",
        "#{out500Remcnt,jdbcType=DECIMAL}, #{out500Cnt,jdbcType=DECIMAL}, ",
        "#{out50Remcnt,jdbcType=DECIMAL}, #{out50Cnt,jdbcType=DECIMAL}, ",
        "#{out10Remcnt,jdbcType=DECIMAL}, #{out10Cnt,jdbcType=DECIMAL}, ",
        "#{subOrgCd,jdbcType=VARCHAR}, #{atmDealNo,jdbcType=DECIMAL}, ",
        "#{transSeq,jdbcType=VARCHAR}, #{transDate,jdbcType=VARCHAR})"
    })
    int insert(TFnRealtimeTrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @InsertProvider(type=TFnRealtimeTradeSqlProvider.class, method="insertSelective")
    int insertSelective(TFnRealtimeTrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @SelectProvider(type=TFnRealtimeTradeSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_TIME", property="dealTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REMAIN_AMT", property="remainAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10000_REMCNT", property="in10000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_5000_REMCNT", property="in5000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_1000_REMCNT", property="in1000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10000_REMCNT", property="out10000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_5000_REMCNT", property="out5000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_1000_REMCNT", property="out1000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_100_REMCNT", property="out100Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10000_CNT", property="in10000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_5000_CNT", property="in5000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_1000_CNT", property="in1000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10000_CNT", property="out10000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_5000_CNT", property="out5000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_1000_CNT", property="out1000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_100_CNT", property="out100Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="NO_RTN_AMT", property="noRtnAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="NO_RTN_NO", property="noRtnNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_50000_REMCNT", property="in50000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_50000_CNT", property="in50000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_500_REMCNT", property="in500Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_100_REMCNT", property="in100Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_50_REMCNT", property="in50Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10_REMCNT", property="in10Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_500_CNT", property="in500Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_100_CNT", property="in100Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_50_CNT", property="in50Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10_CNT", property="in10Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_50000_REMCNT", property="out50000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_50000_CNT", property="out50000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_500_REMCNT", property="out500Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_500_CNT", property="out500Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_50_REMCNT", property="out50Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_50_CNT", property="out50Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10_REMCNT", property="out10Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10_CNT", property="out10Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SUB_ORG_CD", property="subOrgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATM_DEAL_NO", property="atmDealNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRANS_SEQ", property="transSeq", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANS_DATE", property="transDate", jdbcType=JdbcType.VARCHAR)
    })
    List<TFnRealtimeTrade> selectBySpec(TFnRealtimeTradeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, BRANCH_CD, MAC_NO, DEAL_DATE, DEAL_TIME, REMAIN_AMT, IN_10000_REMCNT, ",
        "IN_5000_REMCNT, IN_1000_REMCNT, OUT_10000_REMCNT, OUT_5000_REMCNT, OUT_1000_REMCNT, ",
        "OUT_100_REMCNT, IN_10000_CNT, IN_5000_CNT, IN_1000_CNT, OUT_10000_CNT, OUT_5000_CNT, ",
        "OUT_1000_CNT, OUT_100_CNT, NO_RTN_AMT, NO_RTN_NO, UPDATE_DATE, UPDATE_UID, IN_50000_REMCNT, ",
        "IN_50000_CNT, IN_500_REMCNT, IN_100_REMCNT, IN_50_REMCNT, IN_10_REMCNT, IN_500_CNT, ",
        "IN_100_CNT, IN_50_CNT, IN_10_CNT, OUT_50000_REMCNT, OUT_50000_CNT, OUT_500_REMCNT, ",
        "OUT_500_CNT, OUT_50_REMCNT, OUT_50_CNT, OUT_10_REMCNT, OUT_10_CNT, SUB_ORG_CD, ",
        "ATM_DEAL_NO, TRANS_SEQ, TRANS_DATE",
        "from OP.T_FN_REALTIME_TRADE",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and DEAL_TIME = #{dealTime,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_TIME", property="dealTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="REMAIN_AMT", property="remainAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10000_REMCNT", property="in10000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_5000_REMCNT", property="in5000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_1000_REMCNT", property="in1000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10000_REMCNT", property="out10000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_5000_REMCNT", property="out5000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_1000_REMCNT", property="out1000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_100_REMCNT", property="out100Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10000_CNT", property="in10000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_5000_CNT", property="in5000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_1000_CNT", property="in1000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10000_CNT", property="out10000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_5000_CNT", property="out5000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_1000_CNT", property="out1000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_100_CNT", property="out100Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="NO_RTN_AMT", property="noRtnAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="NO_RTN_NO", property="noRtnNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_50000_REMCNT", property="in50000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_50000_CNT", property="in50000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_500_REMCNT", property="in500Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_100_REMCNT", property="in100Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_50_REMCNT", property="in50Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10_REMCNT", property="in10Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_500_CNT", property="in500Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_100_CNT", property="in100Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_50_CNT", property="in50Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_10_CNT", property="in10Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_50000_REMCNT", property="out50000Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_50000_CNT", property="out50000Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_500_REMCNT", property="out500Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_500_CNT", property="out500Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_50_REMCNT", property="out50Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_50_CNT", property="out50Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10_REMCNT", property="out10Remcnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUT_10_CNT", property="out10Cnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SUB_ORG_CD", property="subOrgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="ATM_DEAL_NO", property="atmDealNo", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRANS_SEQ", property="transSeq", jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANS_DATE", property="transDate", jdbcType=JdbcType.VARCHAR)
    })
    TFnRealtimeTrade selectByPrimaryKey(TFnRealtimeTradeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @UpdateProvider(type=TFnRealtimeTradeSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnRealtimeTrade record, @Param("spec") TFnRealtimeTradeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @UpdateProvider(type=TFnRealtimeTradeSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnRealtimeTrade record, @Param("spec") TFnRealtimeTradeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @UpdateProvider(type=TFnRealtimeTradeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnRealtimeTrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_REALTIME_TRADE
     *
     * @mbggenerated Tue Jul 22 10:58:58 KST 2014
     */
    @Update({
        "update OP.T_FN_REALTIME_TRADE",
        "set REMAIN_AMT = #{remainAmt,jdbcType=DECIMAL},",
          "IN_10000_REMCNT = #{in10000Remcnt,jdbcType=DECIMAL},",
          "IN_5000_REMCNT = #{in5000Remcnt,jdbcType=DECIMAL},",
          "IN_1000_REMCNT = #{in1000Remcnt,jdbcType=DECIMAL},",
          "OUT_10000_REMCNT = #{out10000Remcnt,jdbcType=DECIMAL},",
          "OUT_5000_REMCNT = #{out5000Remcnt,jdbcType=DECIMAL},",
          "OUT_1000_REMCNT = #{out1000Remcnt,jdbcType=DECIMAL},",
          "OUT_100_REMCNT = #{out100Remcnt,jdbcType=DECIMAL},",
          "IN_10000_CNT = #{in10000Cnt,jdbcType=DECIMAL},",
          "IN_5000_CNT = #{in5000Cnt,jdbcType=DECIMAL},",
          "IN_1000_CNT = #{in1000Cnt,jdbcType=DECIMAL},",
          "OUT_10000_CNT = #{out10000Cnt,jdbcType=DECIMAL},",
          "OUT_5000_CNT = #{out5000Cnt,jdbcType=DECIMAL},",
          "OUT_1000_CNT = #{out1000Cnt,jdbcType=DECIMAL},",
          "OUT_100_CNT = #{out100Cnt,jdbcType=DECIMAL},",
          "NO_RTN_AMT = #{noRtnAmt,jdbcType=DECIMAL},",
          "NO_RTN_NO = #{noRtnNo,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
          "IN_50000_REMCNT = #{in50000Remcnt,jdbcType=DECIMAL},",
          "IN_50000_CNT = #{in50000Cnt,jdbcType=DECIMAL},",
          "IN_500_REMCNT = #{in500Remcnt,jdbcType=DECIMAL},",
          "IN_100_REMCNT = #{in100Remcnt,jdbcType=DECIMAL},",
          "IN_50_REMCNT = #{in50Remcnt,jdbcType=DECIMAL},",
          "IN_10_REMCNT = #{in10Remcnt,jdbcType=DECIMAL},",
          "IN_500_CNT = #{in500Cnt,jdbcType=DECIMAL},",
          "IN_100_CNT = #{in100Cnt,jdbcType=DECIMAL},",
          "IN_50_CNT = #{in50Cnt,jdbcType=DECIMAL},",
          "IN_10_CNT = #{in10Cnt,jdbcType=DECIMAL},",
          "OUT_50000_REMCNT = #{out50000Remcnt,jdbcType=DECIMAL},",
          "OUT_50000_CNT = #{out50000Cnt,jdbcType=DECIMAL},",
          "OUT_500_REMCNT = #{out500Remcnt,jdbcType=DECIMAL},",
          "OUT_500_CNT = #{out500Cnt,jdbcType=DECIMAL},",
          "OUT_50_REMCNT = #{out50Remcnt,jdbcType=DECIMAL},",
          "OUT_50_CNT = #{out50Cnt,jdbcType=DECIMAL},",
          "OUT_10_REMCNT = #{out10Remcnt,jdbcType=DECIMAL},",
          "OUT_10_CNT = #{out10Cnt,jdbcType=DECIMAL},",
          "SUB_ORG_CD = #{subOrgCd,jdbcType=VARCHAR},",
          "ATM_DEAL_NO = #{atmDealNo,jdbcType=DECIMAL},",
          "TRANS_SEQ = #{transSeq,jdbcType=VARCHAR},",
          "TRANS_DATE = #{transDate,jdbcType=VARCHAR}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and DEAL_TIME = #{dealTime,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnRealtimeTrade record);
}
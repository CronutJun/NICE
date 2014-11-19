package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseTmp;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseTmpKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceCloseTmpSpec;
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

public interface TFnNiceCloseTmpMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @SelectProvider(type=TFnNiceCloseTmpSqlProvider.class, method="countBySpec")
    int countBySpec(TFnNiceCloseTmpSpec Spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @DeleteProvider(type=TFnNiceCloseTmpSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnNiceCloseTmpSpec Spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_NICE_CLOSE_TMP",
        "where MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}",
          "and CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnNiceCloseTmpKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_NICE_CLOSE_TMP (MAC_NO, CLOSE_DATE, ",
        "CLOSE_TIME, TRACK1_EMIT_CNT, ",
        "TRACK1_BACK_CNT, TRACK2_EMIT_CNT, ",
        "TRACK2_BACK_CNT, TRACK3_EMIT_CNT, ",
        "TRACK3_BACK_CNT, TRACK4_EMIT_CNT, ",
        "TRACK4_BACK_CNT, TOT_EMIT_CNT, ",
        "TOT_EMIT_AMT, DEPOSIT_CNT, ",
        "DEPOSIT_AMT, SERVICE_CNT, ",
        "SERVICE_AMT, ABROAD_CNT, ",
        "ABROAD_AMT, ETC_CNT, ",
        "ETC_AMT, CASH_NOT_TAKEN_CNT, ",
        "CASH_NOT_TAKEN_AMT, UNDETERMINED_BILLS_CNT, ",
        "UNDETERMINED_BILLS_AMT, SEND_FAILS_CNT, ",
        "SENT_FAILS_AMT, TIME_OUT_CNT, ",
        "TIME_OUT_AMT, INSERT_DATE, ",
        "INSERT_UID, TOT_IN_CNT, ",
        "TOT_IN_AMT, CHECK_IN_CNT, ",
        "CHECK_IN_AMT, CHECK_OUT_CNT, ",
        "CHECK_OUT_AMT, EMIT_CNT_CW14, ",
        "EMIT_CNT_CW54, EMIT_CNT_CW15, ",
        "IN_CNT_CW14, IN_CNT_CW54, ",
        "IN_CNT_CW15, IN_AMT_ETC, ",
        "TRACK_USE_TYPE)",
        "values (#{macNo,jdbcType=VARCHAR}, #{closeDate,jdbcType=VARCHAR}, ",
        "#{closeTime,jdbcType=VARCHAR}, #{track1EmitCnt,jdbcType=DECIMAL}, ",
        "#{track1BackCnt,jdbcType=DECIMAL}, #{track2EmitCnt,jdbcType=DECIMAL}, ",
        "#{track2BackCnt,jdbcType=DECIMAL}, #{track3EmitCnt,jdbcType=DECIMAL}, ",
        "#{track3BackCnt,jdbcType=DECIMAL}, #{track4EmitCnt,jdbcType=DECIMAL}, ",
        "#{track4BackCnt,jdbcType=DECIMAL}, #{totEmitCnt,jdbcType=DECIMAL}, ",
        "#{totEmitAmt,jdbcType=DECIMAL}, #{depositCnt,jdbcType=DECIMAL}, ",
        "#{depositAmt,jdbcType=DECIMAL}, #{serviceCnt,jdbcType=DECIMAL}, ",
        "#{serviceAmt,jdbcType=DECIMAL}, #{abroadCnt,jdbcType=DECIMAL}, ",
        "#{abroadAmt,jdbcType=DECIMAL}, #{etcCnt,jdbcType=DECIMAL}, ",
        "#{etcAmt,jdbcType=DECIMAL}, #{cashNotTakenCnt,jdbcType=DECIMAL}, ",
        "#{cashNotTakenAmt,jdbcType=DECIMAL}, #{undeterminedBillsCnt,jdbcType=DECIMAL}, ",
        "#{undeterminedBillsAmt,jdbcType=DECIMAL}, #{sendFailsCnt,jdbcType=DECIMAL}, ",
        "#{sentFailsAmt,jdbcType=DECIMAL}, #{timeOutCnt,jdbcType=DECIMAL}, ",
        "#{timeOutAmt,jdbcType=DECIMAL}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{insertUid,jdbcType=VARCHAR}, #{totInCnt,jdbcType=DECIMAL}, ",
        "#{totInAmt,jdbcType=DECIMAL}, #{checkInCnt,jdbcType=DECIMAL}, ",
        "#{checkInAmt,jdbcType=DECIMAL}, #{checkOutCnt,jdbcType=DECIMAL}, ",
        "#{checkOutAmt,jdbcType=DECIMAL}, #{emitCntCw14,jdbcType=DECIMAL}, ",
        "#{emitCntCw54,jdbcType=DECIMAL}, #{emitCntCw15,jdbcType=DECIMAL}, ",
        "#{inCntCw14,jdbcType=DECIMAL}, #{inCntCw54,jdbcType=DECIMAL}, ",
        "#{inCntCw15,jdbcType=DECIMAL}, #{inAmtEtc,jdbcType=DECIMAL}, ",
        "#{trackUseType,jdbcType=CHAR})"
    })
    int insert(TFnNiceCloseTmp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @InsertProvider(type=TFnNiceCloseTmpSqlProvider.class, method="insertSelective")
    int insertSelective(TFnNiceCloseTmp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @SelectProvider(type=TFnNiceCloseTmpSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_DATE", property="closeDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TIME", property="closeTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRACK1_EMIT_CNT", property="track1EmitCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK1_BACK_CNT", property="track1BackCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK2_EMIT_CNT", property="track2EmitCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK2_BACK_CNT", property="track2BackCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK3_EMIT_CNT", property="track3EmitCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK3_BACK_CNT", property="track3BackCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK4_EMIT_CNT", property="track4EmitCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK4_BACK_CNT", property="track4BackCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_EMIT_CNT", property="totEmitCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_EMIT_AMT", property="totEmitAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEPOSIT_CNT", property="depositCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEPOSIT_AMT", property="depositAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SERVICE_CNT", property="serviceCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SERVICE_AMT", property="serviceAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ABROAD_CNT", property="abroadCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ABROAD_AMT", property="abroadAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ETC_CNT", property="etcCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ETC_AMT", property="etcAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_NOT_TAKEN_CNT", property="cashNotTakenCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_NOT_TAKEN_AMT", property="cashNotTakenAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="UNDETERMINED_BILLS_CNT", property="undeterminedBillsCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="UNDETERMINED_BILLS_AMT", property="undeterminedBillsAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SEND_FAILS_CNT", property="sendFailsCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SENT_FAILS_AMT", property="sentFailsAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TIME_OUT_CNT", property="timeOutCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TIME_OUT_AMT", property="timeOutAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="TOT_IN_CNT", property="totInCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_IN_AMT", property="totInAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_IN_CNT", property="checkInCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_IN_AMT", property="checkInAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_OUT_CNT", property="checkOutCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_OUT_AMT", property="checkOutAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="EMIT_CNT_CW14", property="emitCntCw14", jdbcType=JdbcType.DECIMAL),
        @Result(column="EMIT_CNT_CW54", property="emitCntCw54", jdbcType=JdbcType.DECIMAL),
        @Result(column="EMIT_CNT_CW15", property="emitCntCw15", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_CNT_CW14", property="inCntCw14", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_CNT_CW54", property="inCntCw54", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_CNT_CW15", property="inCntCw15", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_AMT_ETC", property="inAmtEtc", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK_USE_TYPE", property="trackUseType", jdbcType=JdbcType.CHAR)
    })
    List<TFnNiceCloseTmp> selectBySpec(TFnNiceCloseTmpSpec Spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @Select({
        "select",
        "MAC_NO, CLOSE_DATE, CLOSE_TIME, TRACK1_EMIT_CNT, TRACK1_BACK_CNT, TRACK2_EMIT_CNT, ",
        "TRACK2_BACK_CNT, TRACK3_EMIT_CNT, TRACK3_BACK_CNT, TRACK4_EMIT_CNT, TRACK4_BACK_CNT, ",
        "TOT_EMIT_CNT, TOT_EMIT_AMT, DEPOSIT_CNT, DEPOSIT_AMT, SERVICE_CNT, SERVICE_AMT, ",
        "ABROAD_CNT, ABROAD_AMT, ETC_CNT, ETC_AMT, CASH_NOT_TAKEN_CNT, CASH_NOT_TAKEN_AMT, ",
        "UNDETERMINED_BILLS_CNT, UNDETERMINED_BILLS_AMT, SEND_FAILS_CNT, SENT_FAILS_AMT, ",
        "TIME_OUT_CNT, TIME_OUT_AMT, INSERT_DATE, INSERT_UID, TOT_IN_CNT, TOT_IN_AMT, ",
        "CHECK_IN_CNT, CHECK_IN_AMT, CHECK_OUT_CNT, CHECK_OUT_AMT, EMIT_CNT_CW14, EMIT_CNT_CW54, ",
        "EMIT_CNT_CW15, IN_CNT_CW14, IN_CNT_CW54, IN_CNT_CW15, IN_AMT_ETC, TRACK_USE_TYPE",
        "from OP.T_FN_NICE_CLOSE_TMP",
        "where MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}",
          "and CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_DATE", property="closeDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TIME", property="closeTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRACK1_EMIT_CNT", property="track1EmitCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK1_BACK_CNT", property="track1BackCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK2_EMIT_CNT", property="track2EmitCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK2_BACK_CNT", property="track2BackCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK3_EMIT_CNT", property="track3EmitCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK3_BACK_CNT", property="track3BackCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK4_EMIT_CNT", property="track4EmitCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK4_BACK_CNT", property="track4BackCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_EMIT_CNT", property="totEmitCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_EMIT_AMT", property="totEmitAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEPOSIT_CNT", property="depositCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="DEPOSIT_AMT", property="depositAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SERVICE_CNT", property="serviceCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SERVICE_AMT", property="serviceAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ABROAD_CNT", property="abroadCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ABROAD_AMT", property="abroadAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ETC_CNT", property="etcCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="ETC_AMT", property="etcAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_NOT_TAKEN_CNT", property="cashNotTakenCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_NOT_TAKEN_AMT", property="cashNotTakenAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="UNDETERMINED_BILLS_CNT", property="undeterminedBillsCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="UNDETERMINED_BILLS_AMT", property="undeterminedBillsAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SEND_FAILS_CNT", property="sendFailsCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SENT_FAILS_AMT", property="sentFailsAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TIME_OUT_CNT", property="timeOutCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TIME_OUT_AMT", property="timeOutAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="TOT_IN_CNT", property="totInCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_IN_AMT", property="totInAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_IN_CNT", property="checkInCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_IN_AMT", property="checkInAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_OUT_CNT", property="checkOutCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_OUT_AMT", property="checkOutAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="EMIT_CNT_CW14", property="emitCntCw14", jdbcType=JdbcType.DECIMAL),
        @Result(column="EMIT_CNT_CW54", property="emitCntCw54", jdbcType=JdbcType.DECIMAL),
        @Result(column="EMIT_CNT_CW15", property="emitCntCw15", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_CNT_CW14", property="inCntCw14", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_CNT_CW54", property="inCntCw54", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_CNT_CW15", property="inCntCw15", jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_AMT_ETC", property="inAmtEtc", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK_USE_TYPE", property="trackUseType", jdbcType=JdbcType.CHAR)
    })
    TFnNiceCloseTmp selectByPrimaryKey(TFnNiceCloseTmpKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @UpdateProvider(type=TFnNiceCloseTmpSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnNiceCloseTmp record, @Param("Spec") TFnNiceCloseTmpSpec Spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @UpdateProvider(type=TFnNiceCloseTmpSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnNiceCloseTmp record, @Param("Spec") TFnNiceCloseTmpSpec Spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @UpdateProvider(type=TFnNiceCloseTmpSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnNiceCloseTmp record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_CLOSE_TMP
     *
     * @mbggenerated Tue Nov 18 16:35:30 KST 2014
     */
    @Update({
        "update OP.T_FN_NICE_CLOSE_TMP",
        "set TRACK1_EMIT_CNT = #{track1EmitCnt,jdbcType=DECIMAL},",
          "TRACK1_BACK_CNT = #{track1BackCnt,jdbcType=DECIMAL},",
          "TRACK2_EMIT_CNT = #{track2EmitCnt,jdbcType=DECIMAL},",
          "TRACK2_BACK_CNT = #{track2BackCnt,jdbcType=DECIMAL},",
          "TRACK3_EMIT_CNT = #{track3EmitCnt,jdbcType=DECIMAL},",
          "TRACK3_BACK_CNT = #{track3BackCnt,jdbcType=DECIMAL},",
          "TRACK4_EMIT_CNT = #{track4EmitCnt,jdbcType=DECIMAL},",
          "TRACK4_BACK_CNT = #{track4BackCnt,jdbcType=DECIMAL},",
          "TOT_EMIT_CNT = #{totEmitCnt,jdbcType=DECIMAL},",
          "TOT_EMIT_AMT = #{totEmitAmt,jdbcType=DECIMAL},",
          "DEPOSIT_CNT = #{depositCnt,jdbcType=DECIMAL},",
          "DEPOSIT_AMT = #{depositAmt,jdbcType=DECIMAL},",
          "SERVICE_CNT = #{serviceCnt,jdbcType=DECIMAL},",
          "SERVICE_AMT = #{serviceAmt,jdbcType=DECIMAL},",
          "ABROAD_CNT = #{abroadCnt,jdbcType=DECIMAL},",
          "ABROAD_AMT = #{abroadAmt,jdbcType=DECIMAL},",
          "ETC_CNT = #{etcCnt,jdbcType=DECIMAL},",
          "ETC_AMT = #{etcAmt,jdbcType=DECIMAL},",
          "CASH_NOT_TAKEN_CNT = #{cashNotTakenCnt,jdbcType=DECIMAL},",
          "CASH_NOT_TAKEN_AMT = #{cashNotTakenAmt,jdbcType=DECIMAL},",
          "UNDETERMINED_BILLS_CNT = #{undeterminedBillsCnt,jdbcType=DECIMAL},",
          "UNDETERMINED_BILLS_AMT = #{undeterminedBillsAmt,jdbcType=DECIMAL},",
          "SEND_FAILS_CNT = #{sendFailsCnt,jdbcType=DECIMAL},",
          "SENT_FAILS_AMT = #{sentFailsAmt,jdbcType=DECIMAL},",
          "TIME_OUT_CNT = #{timeOutCnt,jdbcType=DECIMAL},",
          "TIME_OUT_AMT = #{timeOutAmt,jdbcType=DECIMAL},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
          "TOT_IN_CNT = #{totInCnt,jdbcType=DECIMAL},",
          "TOT_IN_AMT = #{totInAmt,jdbcType=DECIMAL},",
          "CHECK_IN_CNT = #{checkInCnt,jdbcType=DECIMAL},",
          "CHECK_IN_AMT = #{checkInAmt,jdbcType=DECIMAL},",
          "CHECK_OUT_CNT = #{checkOutCnt,jdbcType=DECIMAL},",
          "CHECK_OUT_AMT = #{checkOutAmt,jdbcType=DECIMAL},",
          "EMIT_CNT_CW14 = #{emitCntCw14,jdbcType=DECIMAL},",
          "EMIT_CNT_CW54 = #{emitCntCw54,jdbcType=DECIMAL},",
          "EMIT_CNT_CW15 = #{emitCntCw15,jdbcType=DECIMAL},",
          "IN_CNT_CW14 = #{inCntCw14,jdbcType=DECIMAL},",
          "IN_CNT_CW54 = #{inCntCw54,jdbcType=DECIMAL},",
          "IN_CNT_CW15 = #{inCntCw15,jdbcType=DECIMAL},",
          "IN_AMT_ETC = #{inAmtEtc,jdbcType=DECIMAL},",
          "TRACK_USE_TYPE = #{trackUseType,jdbcType=CHAR}",
        "where MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and CLOSE_DATE = #{closeDate,jdbcType=VARCHAR}",
          "and CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnNiceCloseTmp record);

    /**
     * InN3000100Impl에서 호출
     *
     * @author KDJ, on Wed Jul 30 17:09:00 KST 2014
     */
    @Select({
        "SELECT  CLOSE_DATE,  CLOSE_TIME                       ",
        "FROM    T_FN_NICE_CLOSE_TMP                           ",
        "WHERE   CLOSE_DATE  <= #{closeDate, jdbcType=VARCHAR} ",
        "AND     MAC_NO       = #{macNo, jdbcType=VARCHAR}     ",
        "AND     ROWNUM       < 2                              ",
        "ORDER BY CLOSE_DATE, CLOSE_TIME DESC                  "
    })
    @Results({
        @Result(column="CLOSE_DATE", property="closeDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_TIME", property="closeTime", jdbcType=JdbcType.VARCHAR)
    })
    List<TFnNiceCloseTmp> selectByCond1( TFnNiceCloseTmp cond );

    /**
     * InN3000100Impl에서 호출
     *
     * @author KDJ, on Wed Jul 30 18:28:00 KST 2014
     */
    @Select({
        "SELECT NVL( SUM( TRACK1_BACK_CNT ),       0 ) + #{track1BackCnt,        jdbcType=DECIMAL} AS TRACK1_BACK_CNT       , ",
        "       NVL( SUM( TRACK2_EMIT_CNT ),       0 ) + #{track2EmitCnt,        jdbcType=DECIMAL} AS TRACK2_EMIT_CNT       , ",
        "       NVL( SUM( TRACK2_BACK_CNT ),       0 ) + #{track2BackCnt,        jdbcType=DECIMAL} AS TRACK2_BACK_CNT       , ",
        "       NVL( SUM( TRACK3_EMIT_CNT ),       0 ) + #{track3EmitCnt,        jdbcType=DECIMAL} AS TRACK3_EMIT_CNT       , ",
        "       NVL( SUM( TRACK3_BACK_CNT ),       0 ) + #{track3BackCnt,        jdbcType=DECIMAL} AS TRACK3_BACK_CNT       , ",
        "       NVL( SUM( TRACK4_EMIT_CNT ),       0 ) + #{track4EmitCnt,        jdbcType=DECIMAL} AS TRACK4_EMIT_CNT       , ",
        "       NVL( SUM( TRACK4_BACK_CNT ),       0 ) + #{track4BackCnt,        jdbcType=DECIMAL} AS TRACK4_BACK_CNT       , ",
        "       NVL( SUM( TOT_EMIT_CNT ),          0 ) + #{totEmitCnt,           jdbcType=DECIMAL} AS TOT_EMIT_CNT          , ",
        "       NVL( SUM( TOT_EMIT_AMT ),          0 ) + #{totEmitAmt,           jdbcType=DECIMAL} AS TOT_EMIT_AMT          , ",
        "       NVL( SUM( EMIT_CNT_CW14 ),         0 ) + #{emitCntCw14,          jdbcType=DECIMAL} AS EMIT_CNT_CW14         , ",
        "       NVL( SUM( EMIT_CNT_CW54 ),         0 ) + #{emitCntCw54,          jdbcType=DECIMAL} AS EMIT_CNT_CW54         , ",
        "       NVL( SUM( EMIT_CNT_CW15 ),         0 ) + #{emitCntCw15,          jdbcType=DECIMAL} AS EMIT_CNT_CW15         , ",
        "       NVL( SUM( DEPOSIT_CNT ),           0 ) + #{depositCnt,           jdbcType=DECIMAL} AS DEPOSIT_CNT           , ",
        "       NVL( SUM( DEPOSIT_AMT ),           0 ) + #{depositAmt,           jdbcType=DECIMAL} AS DEPOSIT_AMT           , ",
        "       NVL( SUM( SERVICE_CNT ),           0 ) + #{serviceCnt,           jdbcType=DECIMAL} AS SERVICE_CNT           , ",
        "       NVL( SUM( SERVICE_AMT ),           0 ) + #{serviceAmt,           jdbcType=DECIMAL} AS SERVICE_AMT           , ",
        "       NVL( SUM( ABROAD_CNT ),            0 ) + #{abroadCnt,            jdbcType=DECIMAL} AS ABROAD_CNT            , ",
        "       NVL( SUM( ABROAD_AMT ),            0 ) + #{abroadAmt,            jdbcType=DECIMAL} AS ABROAD_AMT            , ",
        "       NVL( SUM( ETC_CNT ),               0 ) + #{etcCnt,               jdbcType=DECIMAL} AS ETC_CNT               , ",
        "       NVL( SUM( ETC_AMT ),               0 ) + #{etcAmt,               jdbcType=DECIMAL} AS ETC_AMT               , ",
        "       NVL( SUM( TOT_IN_CNT ),            0 ) + #{totInCnt,             jdbcType=DECIMAL} AS TOT_IN_CNT            , ",
        "       NVL( SUM( TOT_IN_AMT ),            0 ) + #{totInAmt,             jdbcType=DECIMAL} AS TOT_IN_AMT            , ",
        "       NVL( SUM( IN_CNT_CW14 ),           0 ) + #{inCntCw14,            jdbcType=DECIMAL} AS IN_CNT_CW14           , ",
        "       NVL( SUM( IN_CNT_CW54 ),           0 ) + #{inCntCw54,            jdbcType=DECIMAL} AS IN_CNT_CW54           , ",
        "       NVL( SUM( IN_CNT_CW15 ),           0 ) + #{inCntCw15,            jdbcType=DECIMAL} AS IN_CNT_CW15           , ",
        "       NVL( SUM( IN_AMT_ETC ),            0 ) + #{inAmtEtc,             jdbcType=DECIMAL} AS IN_AMT_ETC            , ",
        "       NVL( SUM( CASH_NOT_TAKEN_CNT ),    0 ) + #{cashNotTakenCnt,      jdbcType=DECIMAL} AS CASH_NOT_TAKEN_CNT    , ",
        "       NVL( SUM( CASH_NOT_TAKEN_AMT ),    0 ) + #{cashNotTakenAmt,      jdbcType=DECIMAL} AS CASH_NOT_TAKEN_AMT    , ",
        "       NVL( SUM( UNDETERMINED_BILLS_CNT), 0 ) + #{undeterminedBillsCnt, jdbcType=DECIMAL} AS UNDETERMINED_BILLS_CNT, ",
        "       NVL( SUM( UNDETERMINED_BILLS_AMT), 0 ) + #{undeterminedBillsAmt, jdbcType=DECIMAL} AS UNDETERMINED_BILLS_AMT, ",
        "       NVL( SUM( SEND_FAILS_CNT ),        0 ) + #{sendFailsCnt,         jdbcType=DECIMAL} AS SEND_FAILS_CNT        , ",
        "       NVL( SUM( SENT_FAILS_AMT ),        0 ) + #{sentFailsAmt,         jdbcType=DECIMAL} AS SENT_FAILS_AMT        , ",
        "       NVL( SUM( TIME_OUT_CNT ),          0 ) + #{timeOutCnt,           jdbcType=DECIMAL} AS TIME_OUT_CNT          , ",
        "       NVL( SUM( TIME_OUT_AMT ),          0 ) + #{timeOutAmt,           jdbcType=DECIMAL} AS TIME_OUT_AMT          , ",
        "       NVL( SUM( CHECK_IN_CNT ),          0 ) + #{checkInCnt,           jdbcType=DECIMAL} AS CHECK_IN_CNT          , ",
        "       NVL( SUM( CHECK_IN_AMT ),          0 ) + #{checkInAmt,           jdbcType=DECIMAL} AS CHECK_IN_AMT          , ",
        "       NVL( SUM( CHECK_OUT_CNT ),         0 ) + #{checkOutCnt,          jdbcType=DECIMAL} AS CHECK_OUT_CNT         , ",
        "       NVL( SUM( CHECK_OUT_AMT ),         0 ) + #{checkOutAmt,          jdbcType=DECIMAL} AS CHECK_OUT_AMT           ",
        "FROM   T_FN_NICE_CLOSE_TMP                                                                                           ",
        "WHERE  MAC_NO      = #{macNo, jdbcType=VARCHAR}                                                                      ",
        "AND    CLOSE_DATE  < #{closeDate, jdbcType=VARCHAR}                                                                  ",
        "AND    CLOSE_TIME  != '999999'                                                                                       "
    })
    @Results({
        @Result(column="TRACK1_EMIT_CNT",        property="track1EmitCnt",        jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK1_BACK_CNT",        property="track1BackCnt",        jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK2_EMIT_CNT",        property="track2EmitCnt",        jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK2_BACK_CNT",        property="track2BackCnt",        jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK3_EMIT_CNT",        property="track3EmitCnt",        jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK3_BACK_CNT",        property="track3BackCnt",        jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK4_EMIT_CNT",        property="track4EmitCnt",        jdbcType=JdbcType.DECIMAL),
        @Result(column="TRACK4_BACK_CNT",        property="track4BackCnt",        jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_EMIT_CNT",           property="totEmitCnt",           jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_EMIT_AMT",           property="totEmitAmt",           jdbcType=JdbcType.DECIMAL),
        @Result(column="EMIT_CNT_CW14",          property="emitCntCw14",          jdbcType=JdbcType.DECIMAL),
        @Result(column="EMIT_CNT_CW54",          property="emitCntCw54",          jdbcType=JdbcType.DECIMAL),
        @Result(column="EMIT_CNT_CW15",          property="emitCntCw15",          jdbcType=JdbcType.DECIMAL),
        @Result(column="DEPOSIT_CNT",            property="depositCnt",           jdbcType=JdbcType.DECIMAL),
        @Result(column="DEPOSIT_AMT",            property="depositAmt",           jdbcType=JdbcType.DECIMAL),
        @Result(column="SERVICE_CNT",            property="serviceCnt",           jdbcType=JdbcType.DECIMAL),
        @Result(column="SERVICE_AMT",            property="serviceAmt",           jdbcType=JdbcType.DECIMAL),
        @Result(column="ABROAD_CNT",             property="abroadCnt",            jdbcType=JdbcType.DECIMAL),
        @Result(column="ABROAD_AMT",             property="abroadAmt",            jdbcType=JdbcType.DECIMAL),
        @Result(column="ETC_CNT",                property="etcCnt",               jdbcType=JdbcType.DECIMAL),
        @Result(column="ETC_AMT",                property="etcAmt",               jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_IN_CNT",             property="totInCnt",             jdbcType=JdbcType.DECIMAL),
        @Result(column="TOT_IN_AMT",             property="totInAmt",             jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_CNT_CW14",            property="inCntCw14",            jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_CNT_CW54",            property="inCntCw54",            jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_CNT_CW15",            property="inCntCw15",            jdbcType=JdbcType.DECIMAL),
        @Result(column="IN_AMT_ETC",             property="inAmtEtc",             jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_NOT_TAKEN_CNT",     property="cashNotTakenCnt",      jdbcType=JdbcType.DECIMAL),
        @Result(column="CASH_NOT_TAKEN_AMT",     property="cashNotTakenAmt",      jdbcType=JdbcType.DECIMAL),
        @Result(column="UNDETERMINED_BILLS_CNT", property="undeterminedBillsCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="UNDETERMINED_BILLS_AMT", property="undeterminedBillsAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="SEND_FAILS_CNT",         property="sendFailsCnt",         jdbcType=JdbcType.DECIMAL),
        @Result(column="SENT_FAILS_AMT",         property="sentFailsAmt",         jdbcType=JdbcType.DECIMAL),
        @Result(column="TIME_OUT_CNT",           property="timeOutCnt",           jdbcType=JdbcType.DECIMAL),
        @Result(column="TIME_OUT_AMT",           property="timeOutAmt",           jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_IN_CNT",           property="checkInCnt",           jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_IN_AMT",           property="checkInAmt",           jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_OUT_CNT",          property="checkOutCnt",          jdbcType=JdbcType.DECIMAL),
        @Result(column="CHECK_OUT_AMT",          property="checkOutAmt",          jdbcType=JdbcType.DECIMAL)
    })
    TFnNiceCloseTmp selectBySum1( TFnNiceCloseTmp cond );

}
package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnChalsiDealDsum;
import com.nicetcm.nibsplus.broker.msg.model.TFnChalsiDealDsumKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnChalsiDealDsumSpec;
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

public interface TFnChalsiDealDsumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @SelectProvider(type=TFnChalsiDealDsumSqlProvider.class, method="countBySpec")
    int countBySpec(TFnChalsiDealDsumSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @DeleteProvider(type=TFnChalsiDealDsumSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnChalsiDealDsumSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_CHALSI_DEAL_DSUM",
        "where DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and DEAL_TIME = #{dealTime,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and CLOSE_TYPE = #{closeType,jdbcType=VARCHAR}",
          "and CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnChalsiDealDsumKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_CHALSI_DEAL_DSUM (DEAL_DATE, DEAL_TIME, ",
        "ORG_CD, BRANCH_CD, ",
        "MAC_NO, CLOSE_TYPE, ",
        "CLOSE_TIME, CW15_CNT, ",
        "CW54_CNT, CW14_CNT, CW53_CNT, ",
        "CW13_CNT, CW52_CNT, CW12_CNT, ",
        "CW51_CNT, CW11_CNT, HW16_CNT, ",
        "HW55_CNT, HW35_CNT, HW15_CNT, ",
        "CHECK_ETC_AMT, BOX_CASH_AMT, ",
        "BOX_COIN_AMT, BOX_CHECK_AMT, ",
        "BOX_INCOM_AMT, BOX_OUTCOM_AMT, ",
        "BOX_PPCARD_AMT, INSERT_DATE, ",
        "INSERT_UID, IN_TICKET_AMT, ",
        "OUT_TICKET_AMT)",
        "values (#{dealDate,jdbcType=VARCHAR}, #{dealTime,jdbcType=VARCHAR}, ",
        "#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{closeType,jdbcType=VARCHAR}, ",
        "#{closeTime,jdbcType=VARCHAR}, #{cw15Cnt,jdbcType=OTHER}, ",
        "#{cw54Cnt,jdbcType=OTHER}, #{cw14Cnt,jdbcType=OTHER}, #{cw53Cnt,jdbcType=OTHER}, ",
        "#{cw13Cnt,jdbcType=OTHER}, #{cw52Cnt,jdbcType=OTHER}, #{cw12Cnt,jdbcType=OTHER}, ",
        "#{cw51Cnt,jdbcType=OTHER}, #{cw11Cnt,jdbcType=OTHER}, #{hw16Cnt,jdbcType=OTHER}, ",
        "#{hw55Cnt,jdbcType=OTHER}, #{hw35Cnt,jdbcType=OTHER}, #{hw15Cnt,jdbcType=OTHER}, ",
        "#{checkEtcAmt,jdbcType=OTHER}, #{boxCashAmt,jdbcType=OTHER}, ",
        "#{boxCoinAmt,jdbcType=OTHER}, #{boxCheckAmt,jdbcType=OTHER}, ",
        "#{boxIncomAmt,jdbcType=OTHER}, #{boxOutcomAmt,jdbcType=OTHER}, ",
        "#{boxPpcardAmt,jdbcType=OTHER}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{insertUid,jdbcType=VARCHAR}, #{inTicketAmt,jdbcType=OTHER}, ",
        "#{outTicketAmt,jdbcType=OTHER})"
    })
    int insert(TFnChalsiDealDsum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @InsertProvider(type=TFnChalsiDealDsumSqlProvider.class, method="insertSelective")
    int insertSelective(TFnChalsiDealDsum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @SelectProvider(type=TFnChalsiDealDsumSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_TIME", property="dealTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TYPE", property="closeType", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TIME", property="closeTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CW15_CNT", property="cw15Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW54_CNT", property="cw54Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW14_CNT", property="cw14Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW53_CNT", property="cw53Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW13_CNT", property="cw13Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW52_CNT", property="cw52Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW12_CNT", property="cw12Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW51_CNT", property="cw51Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW11_CNT", property="cw11Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="HW16_CNT", property="hw16Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="HW55_CNT", property="hw55Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="HW35_CNT", property="hw35Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="HW15_CNT", property="hw15Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CHECK_ETC_AMT", property="checkEtcAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_CASH_AMT", property="boxCashAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_COIN_AMT", property="boxCoinAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_CHECK_AMT", property="boxCheckAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_INCOM_AMT", property="boxIncomAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_OUTCOM_AMT", property="boxOutcomAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_PPCARD_AMT", property="boxPpcardAmt", jdbcType=JdbcType.OTHER),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_TICKET_AMT", property="inTicketAmt", jdbcType=JdbcType.OTHER),
        @Result(column="OUT_TICKET_AMT", property="outTicketAmt", jdbcType=JdbcType.OTHER)
    })
    List<TFnChalsiDealDsum> selectBySpec(TFnChalsiDealDsumSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Select({
        "select",
        "DEAL_DATE, DEAL_TIME, ORG_CD, BRANCH_CD, MAC_NO, CLOSE_TYPE, CLOSE_TIME, CW15_CNT, ",
        "CW54_CNT, CW14_CNT, CW53_CNT, CW13_CNT, CW52_CNT, CW12_CNT, CW51_CNT, CW11_CNT, ",
        "HW16_CNT, HW55_CNT, HW35_CNT, HW15_CNT, CHECK_ETC_AMT, BOX_CASH_AMT, BOX_COIN_AMT, ",
        "BOX_CHECK_AMT, BOX_INCOM_AMT, BOX_OUTCOM_AMT, BOX_PPCARD_AMT, INSERT_DATE, INSERT_UID, ",
        "IN_TICKET_AMT, OUT_TICKET_AMT",
        "from OP.T_FN_CHALSI_DEAL_DSUM",
        "where DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and DEAL_TIME = #{dealTime,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and CLOSE_TYPE = #{closeType,jdbcType=VARCHAR}",
          "and CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_TIME", property="dealTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TYPE", property="closeType", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CLOSE_TIME", property="closeTime", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CW15_CNT", property="cw15Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW54_CNT", property="cw54Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW14_CNT", property="cw14Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW53_CNT", property="cw53Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW13_CNT", property="cw13Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW52_CNT", property="cw52Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW12_CNT", property="cw12Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW51_CNT", property="cw51Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CW11_CNT", property="cw11Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="HW16_CNT", property="hw16Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="HW55_CNT", property="hw55Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="HW35_CNT", property="hw35Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="HW15_CNT", property="hw15Cnt", jdbcType=JdbcType.OTHER),
        @Result(column="CHECK_ETC_AMT", property="checkEtcAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_CASH_AMT", property="boxCashAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_COIN_AMT", property="boxCoinAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_CHECK_AMT", property="boxCheckAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_INCOM_AMT", property="boxIncomAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_OUTCOM_AMT", property="boxOutcomAmt", jdbcType=JdbcType.OTHER),
        @Result(column="BOX_PPCARD_AMT", property="boxPpcardAmt", jdbcType=JdbcType.OTHER),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_TICKET_AMT", property="inTicketAmt", jdbcType=JdbcType.OTHER),
        @Result(column="OUT_TICKET_AMT", property="outTicketAmt", jdbcType=JdbcType.OTHER)
    })
    TFnChalsiDealDsum selectByPrimaryKey(TFnChalsiDealDsumKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TFnChalsiDealDsumSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnChalsiDealDsum record, @Param("spec") TFnChalsiDealDsumSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TFnChalsiDealDsumSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnChalsiDealDsum record, @Param("spec") TFnChalsiDealDsumSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TFnChalsiDealDsumSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnChalsiDealDsum record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_CHALSI_DEAL_DSUM
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Update({
        "update OP.T_FN_CHALSI_DEAL_DSUM",
        "set CW15_CNT = #{cw15Cnt,jdbcType=OTHER},",
          "CW54_CNT = #{cw54Cnt,jdbcType=OTHER},",
          "CW14_CNT = #{cw14Cnt,jdbcType=OTHER},",
          "CW53_CNT = #{cw53Cnt,jdbcType=OTHER},",
          "CW13_CNT = #{cw13Cnt,jdbcType=OTHER},",
          "CW52_CNT = #{cw52Cnt,jdbcType=OTHER},",
          "CW12_CNT = #{cw12Cnt,jdbcType=OTHER},",
          "CW51_CNT = #{cw51Cnt,jdbcType=OTHER},",
          "CW11_CNT = #{cw11Cnt,jdbcType=OTHER},",
          "HW16_CNT = #{hw16Cnt,jdbcType=OTHER},",
          "HW55_CNT = #{hw55Cnt,jdbcType=OTHER},",
          "HW35_CNT = #{hw35Cnt,jdbcType=OTHER},",
          "HW15_CNT = #{hw15Cnt,jdbcType=OTHER},",
          "CHECK_ETC_AMT = #{checkEtcAmt,jdbcType=OTHER},",
          "BOX_CASH_AMT = #{boxCashAmt,jdbcType=OTHER},",
          "BOX_COIN_AMT = #{boxCoinAmt,jdbcType=OTHER},",
          "BOX_CHECK_AMT = #{boxCheckAmt,jdbcType=OTHER},",
          "BOX_INCOM_AMT = #{boxIncomAmt,jdbcType=OTHER},",
          "BOX_OUTCOM_AMT = #{boxOutcomAmt,jdbcType=OTHER},",
          "BOX_PPCARD_AMT = #{boxPpcardAmt,jdbcType=OTHER},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
          "IN_TICKET_AMT = #{inTicketAmt,jdbcType=OTHER},",
          "OUT_TICKET_AMT = #{outTicketAmt,jdbcType=OTHER}",
        "where DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and DEAL_TIME = #{dealTime,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and CLOSE_TYPE = #{closeType,jdbcType=VARCHAR}",
          "and CLOSE_TIME = #{closeTime,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnChalsiDealDsum record);
}
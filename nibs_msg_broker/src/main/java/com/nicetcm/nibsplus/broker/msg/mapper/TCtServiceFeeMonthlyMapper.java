package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtServiceFeeMonthly;
import com.nicetcm.nibsplus.broker.msg.model.TCtServiceFeeMonthlyKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtServiceFeeMonthlySpec;
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

public interface TCtServiceFeeMonthlyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @SelectProvider(type=TCtServiceFeeMonthlySqlProvider.class, method="countBySpec")
    int countBySpec(TCtServiceFeeMonthlySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @DeleteProvider(type=TCtServiceFeeMonthlySqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCtServiceFeeMonthlySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @Delete({
        "delete from OP.T_CT_SERVICE_FEE_MONTHLY",
        "where YEAR_MON = #{yearMon,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and SITE_CD = #{siteCd,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCtServiceFeeMonthlyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @Insert({
        "insert into OP.T_CT_SERVICE_FEE_MONTHLY (YEAR_MON, ORG_CD, ",
        "BRANCH_CD, SITE_CD, ",
        "MAC_NO, BRANCH_NM, ",
        "SITE_NM, OFFICE_NM, ",
        "MAC_CNT, BASE_FEE, ",
        "MONTH_FEE, ADD_FEE, ",
        "FEE_VAT_ESC, OVERTIME_FEE, ",
        "CHECK_FEE, GUARD_MAC_FEE, ",
        "SURTAX, DEMAND_FEE, ",
        "SITE_FEE, OPER_DAY, ",
        "PENALTY_AMT, JUM_SUM, ",
        "REMARK, CHECK_YN, ",
        "ORG_SEND_YN, AGREE_YN, ",
        "LOCATE_TYPE, OPER_TIME_TYPE, ",
        "INSERT_DATE, UPDATE_DATE, ",
        "MAC_ADD_YN, OVERTIME_YN, ",
        "CHECK_IN_YN, CHECK_IN_FEE, ",
        "FEE_TYPE, INCENTIVE_AMT)",
        "values (#{yearMon,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{branchCd,jdbcType=VARCHAR}, #{siteCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{branchNm,jdbcType=VARCHAR}, ",
        "#{siteNm,jdbcType=VARCHAR}, #{officeNm,jdbcType=VARCHAR}, ",
        "#{macCnt,jdbcType=VARCHAR}, #{baseFee,jdbcType=VARCHAR}, ",
        "#{monthFee,jdbcType=VARCHAR}, #{addFee,jdbcType=VARCHAR}, ",
        "#{feeVatEsc,jdbcType=VARCHAR}, #{overtimeFee,jdbcType=VARCHAR}, ",
        "#{checkFee,jdbcType=VARCHAR}, #{guardMacFee,jdbcType=VARCHAR}, ",
        "#{surtax,jdbcType=VARCHAR}, #{demandFee,jdbcType=VARCHAR}, ",
        "#{siteFee,jdbcType=VARCHAR}, #{operDay,jdbcType=VARCHAR}, ",
        "#{penaltyAmt,jdbcType=VARCHAR}, #{jumSum,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{checkYn,jdbcType=VARCHAR}, ",
        "#{orgSendYn,jdbcType=VARCHAR}, #{agreeYn,jdbcType=VARCHAR}, ",
        "#{locateType,jdbcType=VARCHAR}, #{operTimeType,jdbcType=VARCHAR}, ",
        "#{insertDate,jdbcType=TIMESTAMP}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{macAddYn,jdbcType=VARCHAR}, #{overtimeYn,jdbcType=VARCHAR}, ",
        "#{checkInYn,jdbcType=VARCHAR}, #{checkInFee,jdbcType=VARCHAR}, ",
        "#{feeType,jdbcType=VARCHAR}, #{incentiveAmt,jdbcType=DECIMAL})"
    })
    int insert(TCtServiceFeeMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @InsertProvider(type=TCtServiceFeeMonthlySqlProvider.class, method="insertSelective")
    int insertSelective(TCtServiceFeeMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @SelectProvider(type=TCtServiceFeeMonthlySqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="YEAR_MON", property="yearMon", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_NM", property="branchNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_NM", property="siteNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_NM", property="officeNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_CNT", property="macCnt", jdbcType=JdbcType.VARCHAR),
        @Result(column="BASE_FEE", property="baseFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="MONTH_FEE", property="monthFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADD_FEE", property="addFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="FEE_VAT_ESC", property="feeVatEsc", jdbcType=JdbcType.VARCHAR),
        @Result(column="OVERTIME_FEE", property="overtimeFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHECK_FEE", property="checkFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="GUARD_MAC_FEE", property="guardMacFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="SURTAX", property="surtax", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEMAND_FEE", property="demandFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_FEE", property="siteFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_DAY", property="operDay", jdbcType=JdbcType.VARCHAR),
        @Result(column="PENALTY_AMT", property="penaltyAmt", jdbcType=JdbcType.VARCHAR),
        @Result(column="JUM_SUM", property="jumSum", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHECK_YN", property="checkYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="AGREE_YN", property="agreeYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCATE_TYPE", property="locateType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_TIME_TYPE", property="operTimeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MAC_ADD_YN", property="macAddYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="OVERTIME_YN", property="overtimeYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHECK_IN_YN", property="checkInYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHECK_IN_FEE", property="checkInFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="FEE_TYPE", property="feeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="INCENTIVE_AMT", property="incentiveAmt", jdbcType=JdbcType.DECIMAL)
    })
    List<TCtServiceFeeMonthly> selectBySpec(TCtServiceFeeMonthlySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @Select({
        "select",
        "YEAR_MON, ORG_CD, BRANCH_CD, SITE_CD, MAC_NO, BRANCH_NM, SITE_NM, OFFICE_NM, ",
        "MAC_CNT, BASE_FEE, MONTH_FEE, ADD_FEE, FEE_VAT_ESC, OVERTIME_FEE, CHECK_FEE, ",
        "GUARD_MAC_FEE, SURTAX, DEMAND_FEE, SITE_FEE, OPER_DAY, PENALTY_AMT, JUM_SUM, ",
        "REMARK, CHECK_YN, ORG_SEND_YN, AGREE_YN, LOCATE_TYPE, OPER_TIME_TYPE, INSERT_DATE, ",
        "UPDATE_DATE, MAC_ADD_YN, OVERTIME_YN, CHECK_IN_YN, CHECK_IN_FEE, FEE_TYPE, INCENTIVE_AMT",
        "from OP.T_CT_SERVICE_FEE_MONTHLY",
        "where YEAR_MON = #{yearMon,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and SITE_CD = #{siteCd,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="YEAR_MON", property="yearMon", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_NM", property="branchNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_NM", property="siteNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_NM", property="officeNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_CNT", property="macCnt", jdbcType=JdbcType.VARCHAR),
        @Result(column="BASE_FEE", property="baseFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="MONTH_FEE", property="monthFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="ADD_FEE", property="addFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="FEE_VAT_ESC", property="feeVatEsc", jdbcType=JdbcType.VARCHAR),
        @Result(column="OVERTIME_FEE", property="overtimeFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHECK_FEE", property="checkFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="GUARD_MAC_FEE", property="guardMacFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="SURTAX", property="surtax", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEMAND_FEE", property="demandFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_FEE", property="siteFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_DAY", property="operDay", jdbcType=JdbcType.VARCHAR),
        @Result(column="PENALTY_AMT", property="penaltyAmt", jdbcType=JdbcType.VARCHAR),
        @Result(column="JUM_SUM", property="jumSum", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHECK_YN", property="checkYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="AGREE_YN", property="agreeYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCATE_TYPE", property="locateType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPER_TIME_TYPE", property="operTimeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="MAC_ADD_YN", property="macAddYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="OVERTIME_YN", property="overtimeYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHECK_IN_YN", property="checkInYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHECK_IN_FEE", property="checkInFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="FEE_TYPE", property="feeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="INCENTIVE_AMT", property="incentiveAmt", jdbcType=JdbcType.DECIMAL)
    })
    TCtServiceFeeMonthly selectByPrimaryKey(TCtServiceFeeMonthlyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @UpdateProvider(type=TCtServiceFeeMonthlySqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCtServiceFeeMonthly record, @Param("spec") TCtServiceFeeMonthlySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @UpdateProvider(type=TCtServiceFeeMonthlySqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCtServiceFeeMonthly record, @Param("spec") TCtServiceFeeMonthlySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @UpdateProvider(type=TCtServiceFeeMonthlySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCtServiceFeeMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_SERVICE_FEE_MONTHLY
     *
     * @mbggenerated Thu Jul 31 14:31:00 KST 2014
     */
    @Update({
        "update OP.T_CT_SERVICE_FEE_MONTHLY",
        "set MAC_NO = #{macNo,jdbcType=VARCHAR},",
          "BRANCH_NM = #{branchNm,jdbcType=VARCHAR},",
          "SITE_NM = #{siteNm,jdbcType=VARCHAR},",
          "OFFICE_NM = #{officeNm,jdbcType=VARCHAR},",
          "MAC_CNT = #{macCnt,jdbcType=VARCHAR},",
          "BASE_FEE = #{baseFee,jdbcType=VARCHAR},",
          "MONTH_FEE = #{monthFee,jdbcType=VARCHAR},",
          "ADD_FEE = #{addFee,jdbcType=VARCHAR},",
          "FEE_VAT_ESC = #{feeVatEsc,jdbcType=VARCHAR},",
          "OVERTIME_FEE = #{overtimeFee,jdbcType=VARCHAR},",
          "CHECK_FEE = #{checkFee,jdbcType=VARCHAR},",
          "GUARD_MAC_FEE = #{guardMacFee,jdbcType=VARCHAR},",
          "SURTAX = #{surtax,jdbcType=VARCHAR},",
          "DEMAND_FEE = #{demandFee,jdbcType=VARCHAR},",
          "SITE_FEE = #{siteFee,jdbcType=VARCHAR},",
          "OPER_DAY = #{operDay,jdbcType=VARCHAR},",
          "PENALTY_AMT = #{penaltyAmt,jdbcType=VARCHAR},",
          "JUM_SUM = #{jumSum,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "CHECK_YN = #{checkYn,jdbcType=VARCHAR},",
          "ORG_SEND_YN = #{orgSendYn,jdbcType=VARCHAR},",
          "AGREE_YN = #{agreeYn,jdbcType=VARCHAR},",
          "LOCATE_TYPE = #{locateType,jdbcType=VARCHAR},",
          "OPER_TIME_TYPE = #{operTimeType,jdbcType=VARCHAR},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "MAC_ADD_YN = #{macAddYn,jdbcType=VARCHAR},",
          "OVERTIME_YN = #{overtimeYn,jdbcType=VARCHAR},",
          "CHECK_IN_YN = #{checkInYn,jdbcType=VARCHAR},",
          "CHECK_IN_FEE = #{checkInFee,jdbcType=VARCHAR},",
          "FEE_TYPE = #{feeType,jdbcType=VARCHAR},",
          "INCENTIVE_AMT = #{incentiveAmt,jdbcType=DECIMAL}",
        "where YEAR_MON = #{yearMon,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and SITE_CD = #{siteCd,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCtServiceFeeMonthly record);
}
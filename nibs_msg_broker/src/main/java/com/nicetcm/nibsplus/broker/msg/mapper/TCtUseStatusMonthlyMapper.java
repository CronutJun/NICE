package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtUseStatusMonthly;
import com.nicetcm.nibsplus.broker.msg.model.TCtUseStatusMonthlyKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtUseStatusMonthlySpec;
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

public interface TCtUseStatusMonthlyMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @SelectProvider(type=TCtUseStatusMonthlySqlProvider.class, method="countBySpec")
    int countBySpec(TCtUseStatusMonthlySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @DeleteProvider(type=TCtUseStatusMonthlySqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCtUseStatusMonthlySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Delete({
        "delete from OP.T_CT_USE_STATUS_MONTHLY",
        "where YEAR_MON = #{yearMon,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCtUseStatusMonthlyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Insert({
        "insert into OP.T_CT_USE_STATUS_MONTHLY (YEAR_MON, ORG_CD, ",
        "BRANCH_CD, MAC_NO, ",
        "SET_TYPE, SET_DATE, ",
        "USE_DAY, MONTH_FEE, ",
        "BOOTH_TYPE, USE_CNT_TOTAL_WITHDRAW, ",
        "USE_CNT_TOTAL_TRANSFER, USE_CNT_TOTAL_DEPOSIT, ",
        "USE_CNT_TOTAL_SUM, USE_CNT_SAME_WITHDRAW, ",
        "USE_CNT_SAME_TRANSFER, USE_CNT_SAME_DEPOSIT, ",
        "USE_CNT_SAME_SUM, USE_CNT_DIFF_WITHDRAW, ",
        "USE_CNT_DIFF_TRANSFER, USE_CNT_DIFF_DEPOSIT, ",
        "USE_CNT_DIFF_SUM, BANKBOOK_UPDATE_CNT, ",
        "EARNED_FEE_IBK, EARNED_FEE_VAN, ",
        "ORG_SEND_YN, INSERT_UID, ",
        "INSERT_DATE, UPDATE_UID, ",
        "UPDATE_DATE, BRANCH_NM)",
        "values (#{yearMon,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{branchCd,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
        "#{setType,jdbcType=VARCHAR}, #{setDate,jdbcType=VARCHAR}, ",
        "#{useDay,jdbcType=VARCHAR}, #{monthFee,jdbcType=VARCHAR}, ",
        "#{boothType,jdbcType=VARCHAR}, #{useCntTotalWithdraw,jdbcType=VARCHAR}, ",
        "#{useCntTotalTransfer,jdbcType=VARCHAR}, #{useCntTotalDeposit,jdbcType=VARCHAR}, ",
        "#{useCntTotalSum,jdbcType=VARCHAR}, #{useCntSameWithdraw,jdbcType=VARCHAR}, ",
        "#{useCntSameTransfer,jdbcType=VARCHAR}, #{useCntSameDeposit,jdbcType=VARCHAR}, ",
        "#{useCntSameSum,jdbcType=VARCHAR}, #{useCntDiffWithdraw,jdbcType=VARCHAR}, ",
        "#{useCntDiffTransfer,jdbcType=VARCHAR}, #{useCntDiffDeposit,jdbcType=VARCHAR}, ",
        "#{useCntDiffSum,jdbcType=VARCHAR}, #{bankbookUpdateCnt,jdbcType=VARCHAR}, ",
        "#{earnedFeeIbk,jdbcType=VARCHAR}, #{earnedFeeVan,jdbcType=VARCHAR}, ",
        "#{orgSendYn,jdbcType=VARCHAR}, #{insertUid,jdbcType=VARCHAR}, ",
        "#{insertDate,jdbcType=TIMESTAMP}, #{updateUid,jdbcType=VARCHAR}, ",
        "#{updateDate,jdbcType=TIMESTAMP}, #{branchNm,jdbcType=VARCHAR})"
    })
    int insert(TCtUseStatusMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @InsertProvider(type=TCtUseStatusMonthlySqlProvider.class, method="insertSelective")
    int insertSelective(TCtUseStatusMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @SelectProvider(type=TCtUseStatusMonthlySqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="YEAR_MON", property="yearMon", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SET_TYPE", property="setType", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_DATE", property="setDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_DAY", property="useDay", jdbcType=JdbcType.VARCHAR),
        @Result(column="MONTH_FEE", property="monthFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="BOOTH_TYPE", property="boothType", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_TOTAL_WITHDRAW", property="useCntTotalWithdraw", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_TOTAL_TRANSFER", property="useCntTotalTransfer", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_TOTAL_DEPOSIT", property="useCntTotalDeposit", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_TOTAL_SUM", property="useCntTotalSum", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_SAME_WITHDRAW", property="useCntSameWithdraw", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_SAME_TRANSFER", property="useCntSameTransfer", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_SAME_DEPOSIT", property="useCntSameDeposit", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_SAME_SUM", property="useCntSameSum", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_DIFF_WITHDRAW", property="useCntDiffWithdraw", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_DIFF_TRANSFER", property="useCntDiffTransfer", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_DIFF_DEPOSIT", property="useCntDiffDeposit", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_DIFF_SUM", property="useCntDiffSum", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANKBOOK_UPDATE_CNT", property="bankbookUpdateCnt", jdbcType=JdbcType.VARCHAR),
        @Result(column="EARNED_FEE_IBK", property="earnedFeeIbk", jdbcType=JdbcType.VARCHAR),
        @Result(column="EARNED_FEE_VAN", property="earnedFeeVan", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="BRANCH_NM", property="branchNm", jdbcType=JdbcType.VARCHAR)
    })
    List<TCtUseStatusMonthly> selectBySpec(TCtUseStatusMonthlySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Select({
        "select",
        "YEAR_MON, ORG_CD, BRANCH_CD, MAC_NO, SET_TYPE, SET_DATE, USE_DAY, MONTH_FEE, ",
        "BOOTH_TYPE, USE_CNT_TOTAL_WITHDRAW, USE_CNT_TOTAL_TRANSFER, USE_CNT_TOTAL_DEPOSIT, ",
        "USE_CNT_TOTAL_SUM, USE_CNT_SAME_WITHDRAW, USE_CNT_SAME_TRANSFER, USE_CNT_SAME_DEPOSIT, ",
        "USE_CNT_SAME_SUM, USE_CNT_DIFF_WITHDRAW, USE_CNT_DIFF_TRANSFER, USE_CNT_DIFF_DEPOSIT, ",
        "USE_CNT_DIFF_SUM, BANKBOOK_UPDATE_CNT, EARNED_FEE_IBK, EARNED_FEE_VAN, ORG_SEND_YN, ",
        "INSERT_UID, INSERT_DATE, UPDATE_UID, UPDATE_DATE, BRANCH_NM",
        "from OP.T_CT_USE_STATUS_MONTHLY",
        "where YEAR_MON = #{yearMon,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="YEAR_MON", property="yearMon", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SET_TYPE", property="setType", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_DATE", property="setDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_DAY", property="useDay", jdbcType=JdbcType.VARCHAR),
        @Result(column="MONTH_FEE", property="monthFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="BOOTH_TYPE", property="boothType", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_TOTAL_WITHDRAW", property="useCntTotalWithdraw", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_TOTAL_TRANSFER", property="useCntTotalTransfer", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_TOTAL_DEPOSIT", property="useCntTotalDeposit", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_TOTAL_SUM", property="useCntTotalSum", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_SAME_WITHDRAW", property="useCntSameWithdraw", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_SAME_TRANSFER", property="useCntSameTransfer", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_SAME_DEPOSIT", property="useCntSameDeposit", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_SAME_SUM", property="useCntSameSum", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_DIFF_WITHDRAW", property="useCntDiffWithdraw", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_DIFF_TRANSFER", property="useCntDiffTransfer", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_DIFF_DEPOSIT", property="useCntDiffDeposit", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_CNT_DIFF_SUM", property="useCntDiffSum", jdbcType=JdbcType.VARCHAR),
        @Result(column="BANKBOOK_UPDATE_CNT", property="bankbookUpdateCnt", jdbcType=JdbcType.VARCHAR),
        @Result(column="EARNED_FEE_IBK", property="earnedFeeIbk", jdbcType=JdbcType.VARCHAR),
        @Result(column="EARNED_FEE_VAN", property="earnedFeeVan", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_YN", property="orgSendYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="BRANCH_NM", property="branchNm", jdbcType=JdbcType.VARCHAR)
    })
    TCtUseStatusMonthly selectByPrimaryKey(TCtUseStatusMonthlyKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TCtUseStatusMonthlySqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCtUseStatusMonthly record, @Param("spec") TCtUseStatusMonthlySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TCtUseStatusMonthlySqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCtUseStatusMonthly record, @Param("spec") TCtUseStatusMonthlySpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @UpdateProvider(type=TCtUseStatusMonthlySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCtUseStatusMonthly record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_USE_STATUS_MONTHLY
     *
     * @mbggenerated Tue Jul 22 11:13:55 KST 2014
     */
    @Update({
        "update OP.T_CT_USE_STATUS_MONTHLY",
        "set SET_TYPE = #{setType,jdbcType=VARCHAR},",
          "SET_DATE = #{setDate,jdbcType=VARCHAR},",
          "USE_DAY = #{useDay,jdbcType=VARCHAR},",
          "MONTH_FEE = #{monthFee,jdbcType=VARCHAR},",
          "BOOTH_TYPE = #{boothType,jdbcType=VARCHAR},",
          "USE_CNT_TOTAL_WITHDRAW = #{useCntTotalWithdraw,jdbcType=VARCHAR},",
          "USE_CNT_TOTAL_TRANSFER = #{useCntTotalTransfer,jdbcType=VARCHAR},",
          "USE_CNT_TOTAL_DEPOSIT = #{useCntTotalDeposit,jdbcType=VARCHAR},",
          "USE_CNT_TOTAL_SUM = #{useCntTotalSum,jdbcType=VARCHAR},",
          "USE_CNT_SAME_WITHDRAW = #{useCntSameWithdraw,jdbcType=VARCHAR},",
          "USE_CNT_SAME_TRANSFER = #{useCntSameTransfer,jdbcType=VARCHAR},",
          "USE_CNT_SAME_DEPOSIT = #{useCntSameDeposit,jdbcType=VARCHAR},",
          "USE_CNT_SAME_SUM = #{useCntSameSum,jdbcType=VARCHAR},",
          "USE_CNT_DIFF_WITHDRAW = #{useCntDiffWithdraw,jdbcType=VARCHAR},",
          "USE_CNT_DIFF_TRANSFER = #{useCntDiffTransfer,jdbcType=VARCHAR},",
          "USE_CNT_DIFF_DEPOSIT = #{useCntDiffDeposit,jdbcType=VARCHAR},",
          "USE_CNT_DIFF_SUM = #{useCntDiffSum,jdbcType=VARCHAR},",
          "BANKBOOK_UPDATE_CNT = #{bankbookUpdateCnt,jdbcType=VARCHAR},",
          "EARNED_FEE_IBK = #{earnedFeeIbk,jdbcType=VARCHAR},",
          "EARNED_FEE_VAN = #{earnedFeeVan,jdbcType=VARCHAR},",
          "ORG_SEND_YN = #{orgSendYn,jdbcType=VARCHAR},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "BRANCH_NM = #{branchNm,jdbcType=VARCHAR}",
        "where YEAR_MON = #{yearMon,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCtUseStatusMonthly record);
}
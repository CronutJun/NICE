package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtStorekeeperCashFault;
import com.nicetcm.nibsplus.broker.msg.model.TCtStorekeeperCashFaultKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtStorekeeperCashFaultSpec;
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

public interface TCtStorekeeperCashFaultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @SelectProvider(type=TCtStorekeeperCashFaultSqlProvider.class, method="countBySpec")
    int countBySpec(TCtStorekeeperCashFaultSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @DeleteProvider(type=TCtStorekeeperCashFaultSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCtStorekeeperCashFaultSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @Delete({
        "delete from OP.T_CT_STOREKEEPER_CASH_FAULT",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCtStorekeeperCashFaultKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @Insert({
        "insert into OP.T_CT_STOREKEEPER_CASH_FAULT (ORG_CD, BRANCH_CD, ",
        "MAC_NO, DEAL_DATE, ",
        "ATM_DEAL_NO, DEAL_TIME, ",
        "FAULT_GB, DEAL_AMT, ",
        "PART_OUT_AMT, FIELD_PAY_AMT, ",
        "MADE_ERROR_CD, INSERT_DATE, ",
        "INSERT_UID, UPDATE_DATE, ",
        "UPDATE_UID)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{dealDate,jdbcType=VARCHAR}, ",
        "#{atmDealNo,jdbcType=VARCHAR}, #{dealTime,jdbcType=VARCHAR}, ",
        "#{faultGb,jdbcType=VARCHAR}, #{dealAmt,jdbcType=DECIMAL}, ",
        "#{partOutAmt,jdbcType=DECIMAL}, #{fieldPayAmt,jdbcType=DECIMAL}, ",
        "#{madeErrorCd,jdbcType=VARCHAR}, #{insertDate,jdbcType=TIMESTAMP}, ",
        "#{insertUid,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{updateUid,jdbcType=VARCHAR})"
    })
    int insert(TCtStorekeeperCashFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @InsertProvider(type=TCtStorekeeperCashFaultSqlProvider.class, method="insertSelective")
    int insertSelective(TCtStorekeeperCashFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @SelectProvider(type=TCtStorekeeperCashFaultSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ATM_DEAL_NO", property="atmDealNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_TIME", property="dealTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="FAULT_GB", property="faultGb", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_AMT", property="dealAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PART_OUT_AMT", property="partOutAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="FIELD_PAY_AMT", property="fieldPayAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="MADE_ERROR_CD", property="madeErrorCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    List<TCtStorekeeperCashFault> selectBySpec(TCtStorekeeperCashFaultSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, BRANCH_CD, MAC_NO, DEAL_DATE, ATM_DEAL_NO, DEAL_TIME, FAULT_GB, DEAL_AMT, ",
        "PART_OUT_AMT, FIELD_PAY_AMT, MADE_ERROR_CD, INSERT_DATE, INSERT_UID, UPDATE_DATE, ",
        "UPDATE_UID",
        "from OP.T_CT_STOREKEEPER_CASH_FAULT",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ATM_DEAL_NO", property="atmDealNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_TIME", property="dealTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="FAULT_GB", property="faultGb", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEAL_AMT", property="dealAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="PART_OUT_AMT", property="partOutAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="FIELD_PAY_AMT", property="fieldPayAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="MADE_ERROR_CD", property="madeErrorCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR)
    })
    TCtStorekeeperCashFault selectByPrimaryKey(TCtStorekeeperCashFaultKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @UpdateProvider(type=TCtStorekeeperCashFaultSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCtStorekeeperCashFault record, @Param("spec") TCtStorekeeperCashFaultSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @UpdateProvider(type=TCtStorekeeperCashFaultSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCtStorekeeperCashFault record, @Param("spec") TCtStorekeeperCashFaultSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @UpdateProvider(type=TCtStorekeeperCashFaultSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCtStorekeeperCashFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_STOREKEEPER_CASH_FAULT
     *
     * @mbggenerated Thu Jul 31 17:29:33 KST 2014
     */
    @Update({
        "update OP.T_CT_STOREKEEPER_CASH_FAULT",
        "set DEAL_TIME = #{dealTime,jdbcType=VARCHAR},",
          "FAULT_GB = #{faultGb,jdbcType=VARCHAR},",
          "DEAL_AMT = #{dealAmt,jdbcType=DECIMAL},",
          "PART_OUT_AMT = #{partOutAmt,jdbcType=DECIMAL},",
          "FIELD_PAY_AMT = #{fieldPayAmt,jdbcType=DECIMAL},",
          "MADE_ERROR_CD = #{madeErrorCd,jdbcType=VARCHAR},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and DEAL_DATE = #{dealDate,jdbcType=VARCHAR}",
          "and ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCtStorekeeperCashFault record);
}
package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranNhFee;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranNhFeeKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnNiceTranNhFeeSpec;
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

public interface TFnNiceTranNhFeeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @SelectProvider(type=TFnNiceTranNhFeeSqlProvider.class, method="countBySpec")
    int countBySpec(TFnNiceTranNhFeeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @DeleteProvider(type=TFnNiceTranNhFeeSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnNiceTranNhFeeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @Delete({
        "delete from OP.T_FN_NICE_TRAN_NH_FEE",
        "where DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}",
          "and DEAL_NO = #{dealNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnNiceTranNhFeeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @Insert({
        "insert into OP.T_FN_NICE_TRAN_NH_FEE (DEAL_YEAR, DEAL_NO, ",
        "SRC_CUST_FEE, SRC_BANK_FEE, ",
        "SRC_DEAL_TIME_TYPE, UPDATE_DATE)",
        "values (#{dealYear,jdbcType=VARCHAR}, #{dealNo,jdbcType=VARCHAR}, ",
        "#{srcCustFee,jdbcType=DECIMAL}, #{srcBankFee,jdbcType=DECIMAL}, ",
        "#{srcDealTimeType,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP})"
    })
    int insert(TFnNiceTranNhFee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @InsertProvider(type=TFnNiceTranNhFeeSqlProvider.class, method="insertSelective")
    int insertSelective(TFnNiceTranNhFee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @SelectProvider(type=TFnNiceTranNhFeeSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="DEAL_YEAR", property="dealYear", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_NO", property="dealNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SRC_CUST_FEE", property="srcCustFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="SRC_BANK_FEE", property="srcBankFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="SRC_DEAL_TIME_TYPE", property="srcDealTimeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TFnNiceTranNhFee> selectBySpec(TFnNiceTranNhFeeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @Select({
        "select",
        "DEAL_YEAR, DEAL_NO, SRC_CUST_FEE, SRC_BANK_FEE, SRC_DEAL_TIME_TYPE, UPDATE_DATE",
        "from OP.T_FN_NICE_TRAN_NH_FEE",
        "where DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}",
          "and DEAL_NO = #{dealNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="DEAL_YEAR", property="dealYear", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_NO", property="dealNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="SRC_CUST_FEE", property="srcCustFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="SRC_BANK_FEE", property="srcBankFee", jdbcType=JdbcType.DECIMAL),
        @Result(column="SRC_DEAL_TIME_TYPE", property="srcDealTimeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    TFnNiceTranNhFee selectByPrimaryKey(TFnNiceTranNhFeeKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @UpdateProvider(type=TFnNiceTranNhFeeSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnNiceTranNhFee record, @Param("spec") TFnNiceTranNhFeeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @UpdateProvider(type=TFnNiceTranNhFeeSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnNiceTranNhFee record, @Param("spec") TFnNiceTranNhFeeSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @UpdateProvider(type=TFnNiceTranNhFeeSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnNiceTranNhFee record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_NICE_TRAN_NH_FEE
     *
     * @mbggenerated Tue Feb 03 14:18:21 KST 2015
     */
    @Update({
        "update OP.T_FN_NICE_TRAN_NH_FEE",
        "set SRC_CUST_FEE = #{srcCustFee,jdbcType=DECIMAL},",
          "SRC_BANK_FEE = #{srcBankFee,jdbcType=DECIMAL},",
          "SRC_DEAL_TIME_TYPE = #{srcDealTimeType,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}",
        "where DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}",
          "and DEAL_NO = #{dealNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnNiceTranNhFee record);
}
package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TFnArpcFault;
import com.nicetcm.nibsplus.broker.msg.model.TFnArpcFaultKey;
import com.nicetcm.nibsplus.broker.msg.model.TFnArpcFaultSpec;
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

public interface TFnArpcFaultMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @SelectProvider(type=TFnArpcFaultSqlProvider.class, method="countBySpec")
    int countBySpec(TFnArpcFaultSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @DeleteProvider(type=TFnArpcFaultSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TFnArpcFaultSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @Delete({
        "delete from OP.T_FN_ARPC_FAULT",
        "where DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}",
          "and DEAL_NO = #{dealNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TFnArpcFaultKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @Insert({
        "insert into OP.T_FN_ARPC_FAULT (DEAL_YEAR, DEAL_NO, ",
        "DEAL_DATE, APPLY_YN, ",
        "UPDATE_DATE)",
        "values (#{dealYear,jdbcType=VARCHAR}, #{dealNo,jdbcType=VARCHAR}, ",
        "#{dealDate,jdbcType=VARCHAR}, #{applyYn,jdbcType=VARCHAR}, ",
        "#{updateDate,jdbcType=TIMESTAMP})"
    })
    int insert(TFnArpcFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @InsertProvider(type=TFnArpcFaultSqlProvider.class, method="insertSelective")
    int insertSelective(TFnArpcFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @SelectProvider(type=TFnArpcFaultSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="DEAL_YEAR", property="dealYear", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_NO", property="dealNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="APPLY_YN", property="applyYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TFnArpcFault> selectBySpec(TFnArpcFaultSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @Select({
        "select",
        "DEAL_YEAR, DEAL_NO, DEAL_DATE, APPLY_YN, UPDATE_DATE",
        "from OP.T_FN_ARPC_FAULT",
        "where DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}",
          "and DEAL_NO = #{dealNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="DEAL_YEAR", property="dealYear", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_NO", property="dealNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="DEAL_DATE", property="dealDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="APPLY_YN", property="applyYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    TFnArpcFault selectByPrimaryKey(TFnArpcFaultKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @UpdateProvider(type=TFnArpcFaultSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TFnArpcFault record, @Param("spec") TFnArpcFaultSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @UpdateProvider(type=TFnArpcFaultSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TFnArpcFault record, @Param("spec") TFnArpcFaultSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @UpdateProvider(type=TFnArpcFaultSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TFnArpcFault record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_FN_ARPC_FAULT
     *
     * @mbggenerated Fri Oct 17 10:53:18 KST 2014
     */
    @Update({
        "update OP.T_FN_ARPC_FAULT",
        "set DEAL_DATE = #{dealDate,jdbcType=VARCHAR},",
          "APPLY_YN = #{applyYn,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}",
        "where DEAL_YEAR = #{dealYear,jdbcType=VARCHAR}",
          "and DEAL_NO = #{dealNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TFnArpcFault record);
}
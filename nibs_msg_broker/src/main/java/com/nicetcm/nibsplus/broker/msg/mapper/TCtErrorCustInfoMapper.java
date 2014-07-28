package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCustInfo;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCustInfoKey;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorCustInfoSpec;
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

public interface TCtErrorCustInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @SelectProvider(type=TCtErrorCustInfoSqlProvider.class, method="countBySpec")
    int countBySpec(TCtErrorCustInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @DeleteProvider(type=TCtErrorCustInfoSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TCtErrorCustInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @Delete({
        "delete from OP.T_CT_ERROR_CUST_INFO",
        "where CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TCtErrorCustInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @Insert({
        "insert into OP.T_CT_ERROR_CUST_INFO (CREATE_DATE, ORG_CD, ",
        "BRANCH_CD, MAC_NO, ",
        "ATM_DEAL_NO, CREATE_TIME, ",
        "CUST_ORG_CD, CUST_ACCOUNT_NO, ",
        "UPDATE_DATE)",
        "values (#{createDate,jdbcType=VARCHAR}, #{orgCd,jdbcType=VARCHAR}, ",
        "#{branchCd,jdbcType=VARCHAR}, #{macNo,jdbcType=VARCHAR}, ",
        "#{atmDealNo,jdbcType=VARCHAR}, #{createTime,jdbcType=VARCHAR}, ",
        "#{custOrgCd,jdbcType=VARCHAR}, #{custAccountNo,jdbcType=VARCHAR}, ",
        "#{updateDate,jdbcType=TIMESTAMP})"
    })
    int insert(TCtErrorCustInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @InsertProvider(type=TCtErrorCustInfoSqlProvider.class, method="insertSelective")
    int insertSelective(TCtErrorCustInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @SelectProvider(type=TCtErrorCustInfoSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ATM_DEAL_NO", property="atmDealNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="CUST_ORG_CD", property="custOrgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CUST_ACCOUNT_NO", property="custAccountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    List<TCtErrorCustInfo> selectBySpec(TCtErrorCustInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @Select({
        "select",
        "CREATE_DATE, ORG_CD, BRANCH_CD, MAC_NO, ATM_DEAL_NO, CREATE_TIME, CUST_ORG_CD, ",
        "CUST_ACCOUNT_NO, UPDATE_DATE",
        "from OP.T_CT_ERROR_CUST_INFO",
        "where CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="CREATE_DATE", property="createDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ATM_DEAL_NO", property="atmDealNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="CUST_ORG_CD", property="custOrgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="CUST_ACCOUNT_NO", property="custAccountNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP)
    })
    TCtErrorCustInfo selectByPrimaryKey(TCtErrorCustInfoKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @UpdateProvider(type=TCtErrorCustInfoSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TCtErrorCustInfo record, @Param("spec") TCtErrorCustInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @UpdateProvider(type=TCtErrorCustInfoSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TCtErrorCustInfo record, @Param("spec") TCtErrorCustInfoSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @UpdateProvider(type=TCtErrorCustInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCtErrorCustInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CT_ERROR_CUST_INFO
     *
     * @mbggenerated Mon Jul 28 09:33:17 KST 2014
     */
    @Update({
        "update OP.T_CT_ERROR_CUST_INFO",
        "set CREATE_TIME = #{createTime,jdbcType=VARCHAR},",
          "CUST_ORG_CD = #{custOrgCd,jdbcType=VARCHAR},",
          "CUST_ACCOUNT_NO = #{custAccountNo,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP}",
        "where CREATE_DATE = #{createDate,jdbcType=VARCHAR}",
          "and ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and ATM_DEAL_NO = #{atmDealNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCtErrorCustInfo record);
}
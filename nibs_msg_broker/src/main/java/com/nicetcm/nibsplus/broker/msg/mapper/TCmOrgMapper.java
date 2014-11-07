package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.model.TCmOrg;
import com.nicetcm.nibsplus.broker.msg.model.TCmOrgSpec;
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

public interface TCmOrgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @SelectProvider(type=TCmOrgSqlProvider.class, method="countByspec")
    int countByspec(TCmOrgSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @DeleteProvider(type=TCmOrgSqlProvider.class, method="deleteByspec")
    int deleteByspec(TCmOrgSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @Delete({
        "delete from OP.T_CM_ORG",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String orgCd);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @Insert({
        "insert into OP.T_CM_ORG (ORG_CD, ORG_NM, ",
        "ORG_SHORT_NM, ORG_TYPE, ",
        "INTERPHONE_NO, ZIP_NO, ",
        "ORG_ADDR, CHARGE_NM, ",
        "TELE_NO, RECV_PLACE, ",
        "FAX_NO, USUAL_START_TIME, ",
        "USUAL_END_TIME, SAT_START_TIME, ",
        "SAT_END_TIME, HOL_START_TIME, ",
        "HOL_END_TIME, IN_START_TIME, ",
        "IN_END_TIME, OUT_START_TIME, ",
        "OUT_END_TIME, NIGHT_START_TIME, ",
        "NIGHT_END_TIME, SVC_TYPE, ",
        "AMT_UNIT, TRAN_YN, ",
        "BRANCH_YN, RETURN_YN, ",
        "NICE_ORG_YN, UMS_YN, ",
        "OPEN_YN, ARRIVAL_YN, ",
        "MEMO, UPDATE_DATE, ",
        "UPDATE_UID, SITE_ADD_YN, ",
        "KFTC_ORG_CD, START_DATE, ",
        "END_DATE, USE_YN, ",
        "IN_FEE, OUT_FEE, SEND_IN_FEE, ",
        "SEND_OUT_FEE, SAT_CLOSE_YN, ",
        "NO_ACNT_TERM, NICE_SERVICE_YN, ",
        "SVC_STATUS, SVCOUT_START_DATE, ",
        "SVCOUT_END_DATE, SVCOUT_START_TIME, ",
        "SVCOUT_END_TIME, VOUCHER_CASH_N_COUPON)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{orgNm,jdbcType=VARCHAR}, ",
        "#{orgShortNm,jdbcType=VARCHAR}, #{orgType,jdbcType=VARCHAR}, ",
        "#{interphoneNo,jdbcType=VARCHAR}, #{zipNo,jdbcType=VARCHAR}, ",
        "#{orgAddr,jdbcType=VARCHAR}, #{chargeNm,jdbcType=VARCHAR}, ",
        "#{teleNo,jdbcType=VARCHAR}, #{recvPlace,jdbcType=VARCHAR}, ",
        "#{faxNo,jdbcType=VARCHAR}, #{usualStartTime,jdbcType=VARCHAR}, ",
        "#{usualEndTime,jdbcType=VARCHAR}, #{satStartTime,jdbcType=VARCHAR}, ",
        "#{satEndTime,jdbcType=VARCHAR}, #{holStartTime,jdbcType=VARCHAR}, ",
        "#{holEndTime,jdbcType=VARCHAR}, #{inStartTime,jdbcType=VARCHAR}, ",
        "#{inEndTime,jdbcType=VARCHAR}, #{outStartTime,jdbcType=VARCHAR}, ",
        "#{outEndTime,jdbcType=VARCHAR}, #{nightStartTime,jdbcType=VARCHAR}, ",
        "#{nightEndTime,jdbcType=VARCHAR}, #{svcType,jdbcType=VARCHAR}, ",
        "#{amtUnit,jdbcType=DECIMAL}, #{tranYn,jdbcType=VARCHAR}, ",
        "#{branchYn,jdbcType=VARCHAR}, #{returnYn,jdbcType=VARCHAR}, ",
        "#{niceOrgYn,jdbcType=VARCHAR}, #{umsYn,jdbcType=VARCHAR}, ",
        "#{openYn,jdbcType=VARCHAR}, #{arrivalYn,jdbcType=VARCHAR}, ",
        "#{memo,jdbcType=VARCHAR}, #{updateDate,jdbcType=TIMESTAMP}, ",
        "#{updateUid,jdbcType=VARCHAR}, #{siteAddYn,jdbcType=VARCHAR}, ",
        "#{kftcOrgCd,jdbcType=VARCHAR}, #{startDate,jdbcType=VARCHAR}, ",
        "#{endDate,jdbcType=VARCHAR}, #{useYn,jdbcType=VARCHAR}, ",
        "#{inFee,jdbcType=VARCHAR}, #{outFee,jdbcType=VARCHAR}, #{sendInFee,jdbcType=VARCHAR}, ",
        "#{sendOutFee,jdbcType=VARCHAR}, #{satCloseYn,jdbcType=VARCHAR}, ",
        "#{noAcntTerm,jdbcType=VARCHAR}, #{niceServiceYn,jdbcType=VARCHAR}, ",
        "#{svcStatus,jdbcType=VARCHAR}, #{svcoutStartDate,jdbcType=VARCHAR}, ",
        "#{svcoutEndDate,jdbcType=VARCHAR}, #{svcoutStartTime,jdbcType=VARCHAR}, ",
        "#{svcoutEndTime,jdbcType=VARCHAR}, #{voucherCashNCoupon,jdbcType=VARCHAR})"
    })
    int insert(TCmOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @InsertProvider(type=TCmOrgSqlProvider.class, method="insertSelective")
    int insertSelective(TCmOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @SelectProvider(type=TCmOrgSqlProvider.class, method="selectByspec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_NM", property="orgNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SHORT_NM", property="orgShortNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_TYPE", property="orgType", jdbcType=JdbcType.VARCHAR),
        @Result(column="INTERPHONE_NO", property="interphoneNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ZIP_NO", property="zipNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_ADDR", property="orgAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHARGE_NM", property="chargeNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="TELE_NO", property="teleNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PLACE", property="recvPlace", jdbcType=JdbcType.VARCHAR),
        @Result(column="FAX_NO", property="faxNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="USUAL_START_TIME", property="usualStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="USUAL_END_TIME", property="usualEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SAT_START_TIME", property="satStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SAT_END_TIME", property="satEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOL_START_TIME", property="holStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOL_END_TIME", property="holEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_START_TIME", property="inStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_END_TIME", property="inEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_START_TIME", property="outStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_END_TIME", property="outEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="NIGHT_START_TIME", property="nightStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="NIGHT_END_TIME", property="nightEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVC_TYPE", property="svcType", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMT_UNIT", property="amtUnit", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRAN_YN", property="tranYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_YN", property="branchYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="RETURN_YN", property="returnYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="NICE_ORG_YN", property="niceOrgYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="UMS_YN", property="umsYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPEN_YN", property="openYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_YN", property="arrivalYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="MEMO", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_ADD_YN", property="siteAddYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="KFTC_ORG_CD", property="kftcOrgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="START_DATE", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="END_DATE", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_YN", property="useYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_FEE", property="inFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_FEE", property="outFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_IN_FEE", property="sendInFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_OUT_FEE", property="sendOutFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="SAT_CLOSE_YN", property="satCloseYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="NO_ACNT_TERM", property="noAcntTerm", jdbcType=JdbcType.VARCHAR),
        @Result(column="NICE_SERVICE_YN", property="niceServiceYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVC_STATUS", property="svcStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVCOUT_START_DATE", property="svcoutStartDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVCOUT_END_DATE", property="svcoutEndDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVCOUT_START_TIME", property="svcoutStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVCOUT_END_TIME", property="svcoutEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="VOUCHER_CASH_N_COUPON", property="voucherCashNCoupon", jdbcType=JdbcType.VARCHAR)
    })
    List<TCmOrg> selectByspec(TCmOrgSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, ORG_NM, ORG_SHORT_NM, ORG_TYPE, INTERPHONE_NO, ZIP_NO, ORG_ADDR, CHARGE_NM, ",
        "TELE_NO, RECV_PLACE, FAX_NO, USUAL_START_TIME, USUAL_END_TIME, SAT_START_TIME, ",
        "SAT_END_TIME, HOL_START_TIME, HOL_END_TIME, IN_START_TIME, IN_END_TIME, OUT_START_TIME, ",
        "OUT_END_TIME, NIGHT_START_TIME, NIGHT_END_TIME, SVC_TYPE, AMT_UNIT, TRAN_YN, ",
        "BRANCH_YN, RETURN_YN, NICE_ORG_YN, UMS_YN, OPEN_YN, ARRIVAL_YN, MEMO, UPDATE_DATE, ",
        "UPDATE_UID, SITE_ADD_YN, KFTC_ORG_CD, START_DATE, END_DATE, USE_YN, IN_FEE, ",
        "OUT_FEE, SEND_IN_FEE, SEND_OUT_FEE, SAT_CLOSE_YN, NO_ACNT_TERM, NICE_SERVICE_YN, ",
        "SVC_STATUS, SVCOUT_START_DATE, SVCOUT_END_DATE, SVCOUT_START_TIME, SVCOUT_END_TIME, ",
        "VOUCHER_CASH_N_COUPON",
        "from OP.T_CM_ORG",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ORG_NM", property="orgNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SHORT_NM", property="orgShortNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_TYPE", property="orgType", jdbcType=JdbcType.VARCHAR),
        @Result(column="INTERPHONE_NO", property="interphoneNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ZIP_NO", property="zipNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_ADDR", property="orgAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHARGE_NM", property="chargeNm", jdbcType=JdbcType.VARCHAR),
        @Result(column="TELE_NO", property="teleNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PLACE", property="recvPlace", jdbcType=JdbcType.VARCHAR),
        @Result(column="FAX_NO", property="faxNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="USUAL_START_TIME", property="usualStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="USUAL_END_TIME", property="usualEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SAT_START_TIME", property="satStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SAT_END_TIME", property="satEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOL_START_TIME", property="holStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOL_END_TIME", property="holEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_START_TIME", property="inStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_END_TIME", property="inEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_START_TIME", property="outStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_END_TIME", property="outEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="NIGHT_START_TIME", property="nightStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="NIGHT_END_TIME", property="nightEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVC_TYPE", property="svcType", jdbcType=JdbcType.VARCHAR),
        @Result(column="AMT_UNIT", property="amtUnit", jdbcType=JdbcType.DECIMAL),
        @Result(column="TRAN_YN", property="tranYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_YN", property="branchYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="RETURN_YN", property="returnYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="NICE_ORG_YN", property="niceOrgYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="UMS_YN", property="umsYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="OPEN_YN", property="openYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_YN", property="arrivalYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="MEMO", property="memo", jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE", property="updateDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="UPDATE_UID", property="updateUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_ADD_YN", property="siteAddYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="KFTC_ORG_CD", property="kftcOrgCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="START_DATE", property="startDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="END_DATE", property="endDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="USE_YN", property="useYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="IN_FEE", property="inFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUT_FEE", property="outFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_IN_FEE", property="sendInFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_OUT_FEE", property="sendOutFee", jdbcType=JdbcType.VARCHAR),
        @Result(column="SAT_CLOSE_YN", property="satCloseYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="NO_ACNT_TERM", property="noAcntTerm", jdbcType=JdbcType.VARCHAR),
        @Result(column="NICE_SERVICE_YN", property="niceServiceYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVC_STATUS", property="svcStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVCOUT_START_DATE", property="svcoutStartDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVCOUT_END_DATE", property="svcoutEndDate", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVCOUT_START_TIME", property="svcoutStartTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="SVCOUT_END_TIME", property="svcoutEndTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="VOUCHER_CASH_N_COUPON", property="voucherCashNCoupon", jdbcType=JdbcType.VARCHAR)
    })
    TCmOrg selectByPrimaryKey(String orgCd);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @UpdateProvider(type=TCmOrgSqlProvider.class, method="updateByspecSelective")
    int updateByspecSelective(@Param("record") TCmOrg record, @Param("spec") TCmOrgSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @UpdateProvider(type=TCmOrgSqlProvider.class, method="updateByspec")
    int updateByspec(@Param("record") TCmOrg record, @Param("spec") TCmOrgSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @UpdateProvider(type=TCmOrgSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TCmOrg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table OP.T_CM_ORG
     *
     * @mbggenerated Fri Nov 07 11:06:21 KST 2014
     */
    @Update({
        "update OP.T_CM_ORG",
        "set ORG_NM = #{orgNm,jdbcType=VARCHAR},",
          "ORG_SHORT_NM = #{orgShortNm,jdbcType=VARCHAR},",
          "ORG_TYPE = #{orgType,jdbcType=VARCHAR},",
          "INTERPHONE_NO = #{interphoneNo,jdbcType=VARCHAR},",
          "ZIP_NO = #{zipNo,jdbcType=VARCHAR},",
          "ORG_ADDR = #{orgAddr,jdbcType=VARCHAR},",
          "CHARGE_NM = #{chargeNm,jdbcType=VARCHAR},",
          "TELE_NO = #{teleNo,jdbcType=VARCHAR},",
          "RECV_PLACE = #{recvPlace,jdbcType=VARCHAR},",
          "FAX_NO = #{faxNo,jdbcType=VARCHAR},",
          "USUAL_START_TIME = #{usualStartTime,jdbcType=VARCHAR},",
          "USUAL_END_TIME = #{usualEndTime,jdbcType=VARCHAR},",
          "SAT_START_TIME = #{satStartTime,jdbcType=VARCHAR},",
          "SAT_END_TIME = #{satEndTime,jdbcType=VARCHAR},",
          "HOL_START_TIME = #{holStartTime,jdbcType=VARCHAR},",
          "HOL_END_TIME = #{holEndTime,jdbcType=VARCHAR},",
          "IN_START_TIME = #{inStartTime,jdbcType=VARCHAR},",
          "IN_END_TIME = #{inEndTime,jdbcType=VARCHAR},",
          "OUT_START_TIME = #{outStartTime,jdbcType=VARCHAR},",
          "OUT_END_TIME = #{outEndTime,jdbcType=VARCHAR},",
          "NIGHT_START_TIME = #{nightStartTime,jdbcType=VARCHAR},",
          "NIGHT_END_TIME = #{nightEndTime,jdbcType=VARCHAR},",
          "SVC_TYPE = #{svcType,jdbcType=VARCHAR},",
          "AMT_UNIT = #{amtUnit,jdbcType=DECIMAL},",
          "TRAN_YN = #{tranYn,jdbcType=VARCHAR},",
          "BRANCH_YN = #{branchYn,jdbcType=VARCHAR},",
          "RETURN_YN = #{returnYn,jdbcType=VARCHAR},",
          "NICE_ORG_YN = #{niceOrgYn,jdbcType=VARCHAR},",
          "UMS_YN = #{umsYn,jdbcType=VARCHAR},",
          "OPEN_YN = #{openYn,jdbcType=VARCHAR},",
          "ARRIVAL_YN = #{arrivalYn,jdbcType=VARCHAR},",
          "MEMO = #{memo,jdbcType=VARCHAR},",
          "UPDATE_DATE = #{updateDate,jdbcType=TIMESTAMP},",
          "UPDATE_UID = #{updateUid,jdbcType=VARCHAR},",
          "SITE_ADD_YN = #{siteAddYn,jdbcType=VARCHAR},",
          "KFTC_ORG_CD = #{kftcOrgCd,jdbcType=VARCHAR},",
          "START_DATE = #{startDate,jdbcType=VARCHAR},",
          "END_DATE = #{endDate,jdbcType=VARCHAR},",
          "USE_YN = #{useYn,jdbcType=VARCHAR},",
          "IN_FEE = #{inFee,jdbcType=VARCHAR},",
          "OUT_FEE = #{outFee,jdbcType=VARCHAR},",
          "SEND_IN_FEE = #{sendInFee,jdbcType=VARCHAR},",
          "SEND_OUT_FEE = #{sendOutFee,jdbcType=VARCHAR},",
          "SAT_CLOSE_YN = #{satCloseYn,jdbcType=VARCHAR},",
          "NO_ACNT_TERM = #{noAcntTerm,jdbcType=VARCHAR},",
          "NICE_SERVICE_YN = #{niceServiceYn,jdbcType=VARCHAR},",
          "SVC_STATUS = #{svcStatus,jdbcType=VARCHAR},",
          "SVCOUT_START_DATE = #{svcoutStartDate,jdbcType=VARCHAR},",
          "SVCOUT_END_DATE = #{svcoutEndDate,jdbcType=VARCHAR},",
          "SVCOUT_START_TIME = #{svcoutStartTime,jdbcType=VARCHAR},",
          "SVCOUT_END_TIME = #{svcoutEndTime,jdbcType=VARCHAR},",
          "VOUCHER_CASH_N_COUPON = #{voucherCashNCoupon,jdbcType=VARCHAR}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TCmOrg record);
}
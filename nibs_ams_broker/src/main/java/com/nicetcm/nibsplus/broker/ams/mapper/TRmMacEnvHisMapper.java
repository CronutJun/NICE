package com.nicetcm.nibsplus.broker.ams.mapper;

import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnvHis;
import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnvHisKey;
import com.nicetcm.nibsplus.broker.ams.model.TRmMacEnvHisSpec;
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

public interface TRmMacEnvHisMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @SelectProvider(type=TRmMacEnvHisSqlProvider.class, method="countBySpec")
    int countBySpec(TRmMacEnvHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @DeleteProvider(type=TRmMacEnvHisSqlProvider.class, method="deleteBySpec")
    int deleteBySpec(TRmMacEnvHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @Delete({
        "delete from IN.T_RM_MAC_ENV_HIS",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and TRX_DATE = #{trxDate,jdbcType=VARCHAR}",
          "and TRX_NO = #{trxNo,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(TRmMacEnvHisKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @Insert({
        "insert into IN.T_RM_MAC_ENV_HIS (ORG_CD, BRANCH_CD, ",
        "MAC_NO, TRX_DATE, ",
        "TRX_NO, INSERT_UID, ",
        "INSERT_DATE, STS, ",
        "SITE_CD, DEPT_CD, ",
        "OFFICE_CD, TEAM_CD, ",
        "PRDC_NO, MKR_CD, MODEL_CD, ",
        "AP_VER, SET_PLACE, ",
        "PUB_IP_ADDR, PRI_IP_ADDR, ",
        "IP_PORT, HOST_IP_ADDR, ",
        "HOST_IP_PORT, AOC_IP_ADDR, ",
        "AOC_IP_PORT, REBOOT_TIME, ",
        "MAC_OS, MAC_CPU, MAC_MEM, ",
        "MAC_HDD, MODEM_RELAY_YN, ",
        "RPC_YN, CREDIT_CARD_YN, ",
        "CARD_LOAN_YN, CHRG_HIPASS_YN, ",
        "FRGN_TRAN_TYPE, OUR_MAX_WDR_AMT, ",
        "THR_MAX_WDR_AMT, OUR_MAX_DPS_AMT, ",
        "THR_MAX_DPS_AMT, OUR_MAX_TSF_AMT, ",
        "THR_MAX_TSF_AMT, MAX_SVC_WDR_AMT, ",
        "MAX_CASH_WDR_CNT, MAX_CASH_DPS_CNT, ",
        "MAX_CASH_SVC_WDR_CNT)",
        "values (#{orgCd,jdbcType=VARCHAR}, #{branchCd,jdbcType=VARCHAR}, ",
        "#{macNo,jdbcType=VARCHAR}, #{trxDate,jdbcType=VARCHAR}, ",
        "#{trxNo,jdbcType=VARCHAR}, #{insertUid,jdbcType=VARCHAR}, ",
        "#{insertDate,jdbcType=TIMESTAMP}, #{sts,jdbcType=VARCHAR}, ",
        "#{siteCd,jdbcType=VARCHAR}, #{deptCd,jdbcType=VARCHAR}, ",
        "#{officeCd,jdbcType=VARCHAR}, #{teamCd,jdbcType=VARCHAR}, ",
        "#{prdcNo,jdbcType=VARCHAR}, #{mkrCd,jdbcType=VARCHAR}, #{modelCd,jdbcType=VARCHAR}, ",
        "#{apVer,jdbcType=VARCHAR}, #{setPlace,jdbcType=VARCHAR}, ",
        "#{pubIpAddr,jdbcType=VARCHAR}, #{priIpAddr,jdbcType=VARCHAR}, ",
        "#{ipPort,jdbcType=VARCHAR}, #{hostIpAddr,jdbcType=VARCHAR}, ",
        "#{hostIpPort,jdbcType=VARCHAR}, #{aocIpAddr,jdbcType=VARCHAR}, ",
        "#{aocIpPort,jdbcType=VARCHAR}, #{rebootTime,jdbcType=VARCHAR}, ",
        "#{macOs,jdbcType=VARCHAR}, #{macCpu,jdbcType=VARCHAR}, #{macMem,jdbcType=VARCHAR}, ",
        "#{macHdd,jdbcType=VARCHAR}, #{modemRelayYn,jdbcType=VARCHAR}, ",
        "#{rpcYn,jdbcType=VARCHAR}, #{creditCardYn,jdbcType=VARCHAR}, ",
        "#{cardLoanYn,jdbcType=VARCHAR}, #{chrgHipassYn,jdbcType=VARCHAR}, ",
        "#{frgnTranType,jdbcType=VARCHAR}, #{ourMaxWdrAmt,jdbcType=DECIMAL}, ",
        "#{thrMaxWdrAmt,jdbcType=DECIMAL}, #{ourMaxDpsAmt,jdbcType=DECIMAL}, ",
        "#{thrMaxDpsAmt,jdbcType=DECIMAL}, #{ourMaxTsfAmt,jdbcType=DECIMAL}, ",
        "#{thrMaxTsfAmt,jdbcType=DECIMAL}, #{maxSvcWdrAmt,jdbcType=DECIMAL}, ",
        "#{maxCashWdrCnt,jdbcType=DECIMAL}, #{maxCashDpsCnt,jdbcType=DECIMAL}, ",
        "#{maxCashSvcWdrCnt,jdbcType=DECIMAL})"
    })
    int insert(TRmMacEnvHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @InsertProvider(type=TRmMacEnvHisSqlProvider.class, method="insertSelective")
    int insertSelective(TRmMacEnvHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @SelectProvider(type=TRmMacEnvHisSqlProvider.class, method="selectBySpec")
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_DATE", property="trxDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_NO", property="trxNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STS", property="sts", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEAM_CD", property="teamCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PRDC_NO", property="prdcNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="MKR_CD", property="mkrCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="MODEL_CD", property="modelCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="AP_VER", property="apVer", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_PLACE", property="setPlace", jdbcType=JdbcType.VARCHAR),
        @Result(column="PUB_IP_ADDR", property="pubIpAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="PRI_IP_ADDR", property="priIpAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="IP_PORT", property="ipPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOST_IP_ADDR", property="hostIpAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOST_IP_PORT", property="hostIpPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="AOC_IP_ADDR", property="aocIpAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="AOC_IP_PORT", property="aocIpPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="REBOOT_TIME", property="rebootTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_OS", property="macOs", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_CPU", property="macCpu", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_MEM", property="macMem", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_HDD", property="macHdd", jdbcType=JdbcType.VARCHAR),
        @Result(column="MODEM_RELAY_YN", property="modemRelayYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="RPC_YN", property="rpcYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREDIT_CARD_YN", property="creditCardYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CARD_LOAN_YN", property="cardLoanYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHRG_HIPASS_YN", property="chrgHipassYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="FRGN_TRAN_TYPE", property="frgnTranType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUR_MAX_WDR_AMT", property="ourMaxWdrAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="THR_MAX_WDR_AMT", property="thrMaxWdrAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUR_MAX_DPS_AMT", property="ourMaxDpsAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="THR_MAX_DPS_AMT", property="thrMaxDpsAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUR_MAX_TSF_AMT", property="ourMaxTsfAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="THR_MAX_TSF_AMT", property="thrMaxTsfAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="MAX_SVC_WDR_AMT", property="maxSvcWdrAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="MAX_CASH_WDR_CNT", property="maxCashWdrCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="MAX_CASH_DPS_CNT", property="maxCashDpsCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="MAX_CASH_SVC_WDR_CNT", property="maxCashSvcWdrCnt", jdbcType=JdbcType.DECIMAL)
    })
    List<TRmMacEnvHis> selectBySpec(TRmMacEnvHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @Select({
        "select",
        "ORG_CD, BRANCH_CD, MAC_NO, TRX_DATE, TRX_NO, INSERT_UID, INSERT_DATE, STS, SITE_CD, ",
        "DEPT_CD, OFFICE_CD, TEAM_CD, PRDC_NO, MKR_CD, MODEL_CD, AP_VER, SET_PLACE, PUB_IP_ADDR, ",
        "PRI_IP_ADDR, IP_PORT, HOST_IP_ADDR, HOST_IP_PORT, AOC_IP_ADDR, AOC_IP_PORT, ",
        "REBOOT_TIME, MAC_OS, MAC_CPU, MAC_MEM, MAC_HDD, MODEM_RELAY_YN, RPC_YN, CREDIT_CARD_YN, ",
        "CARD_LOAN_YN, CHRG_HIPASS_YN, FRGN_TRAN_TYPE, OUR_MAX_WDR_AMT, THR_MAX_WDR_AMT, ",
        "OUR_MAX_DPS_AMT, THR_MAX_DPS_AMT, OUR_MAX_TSF_AMT, THR_MAX_TSF_AMT, MAX_SVC_WDR_AMT, ",
        "MAX_CASH_WDR_CNT, MAX_CASH_DPS_CNT, MAX_CASH_SVC_WDR_CNT",
        "from IN.T_RM_MAC_ENV_HIS",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and TRX_DATE = #{trxDate,jdbcType=VARCHAR}",
          "and TRX_NO = #{trxNo,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ORG_CD", property="orgCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="MAC_NO", property="macNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_DATE", property="trxDate", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="TRX_NO", property="trxNo", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="INSERT_UID", property="insertUid", jdbcType=JdbcType.VARCHAR),
        @Result(column="INSERT_DATE", property="insertDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STS", property="sts", jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_CD", property="siteCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="TEAM_CD", property="teamCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="PRDC_NO", property="prdcNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="MKR_CD", property="mkrCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="MODEL_CD", property="modelCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="AP_VER", property="apVer", jdbcType=JdbcType.VARCHAR),
        @Result(column="SET_PLACE", property="setPlace", jdbcType=JdbcType.VARCHAR),
        @Result(column="PUB_IP_ADDR", property="pubIpAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="PRI_IP_ADDR", property="priIpAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="IP_PORT", property="ipPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOST_IP_ADDR", property="hostIpAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOST_IP_PORT", property="hostIpPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="AOC_IP_ADDR", property="aocIpAddr", jdbcType=JdbcType.VARCHAR),
        @Result(column="AOC_IP_PORT", property="aocIpPort", jdbcType=JdbcType.VARCHAR),
        @Result(column="REBOOT_TIME", property="rebootTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_OS", property="macOs", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_CPU", property="macCpu", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_MEM", property="macMem", jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_HDD", property="macHdd", jdbcType=JdbcType.VARCHAR),
        @Result(column="MODEM_RELAY_YN", property="modemRelayYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="RPC_YN", property="rpcYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREDIT_CARD_YN", property="creditCardYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CARD_LOAN_YN", property="cardLoanYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="CHRG_HIPASS_YN", property="chrgHipassYn", jdbcType=JdbcType.VARCHAR),
        @Result(column="FRGN_TRAN_TYPE", property="frgnTranType", jdbcType=JdbcType.VARCHAR),
        @Result(column="OUR_MAX_WDR_AMT", property="ourMaxWdrAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="THR_MAX_WDR_AMT", property="thrMaxWdrAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUR_MAX_DPS_AMT", property="ourMaxDpsAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="THR_MAX_DPS_AMT", property="thrMaxDpsAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="OUR_MAX_TSF_AMT", property="ourMaxTsfAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="THR_MAX_TSF_AMT", property="thrMaxTsfAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="MAX_SVC_WDR_AMT", property="maxSvcWdrAmt", jdbcType=JdbcType.DECIMAL),
        @Result(column="MAX_CASH_WDR_CNT", property="maxCashWdrCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="MAX_CASH_DPS_CNT", property="maxCashDpsCnt", jdbcType=JdbcType.DECIMAL),
        @Result(column="MAX_CASH_SVC_WDR_CNT", property="maxCashSvcWdrCnt", jdbcType=JdbcType.DECIMAL)
    })
    TRmMacEnvHis selectByPrimaryKey(TRmMacEnvHisKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @UpdateProvider(type=TRmMacEnvHisSqlProvider.class, method="updateBySpecSelective")
    int updateBySpecSelective(@Param("record") TRmMacEnvHis record, @Param("spec") TRmMacEnvHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @UpdateProvider(type=TRmMacEnvHisSqlProvider.class, method="updateBySpec")
    int updateBySpec(@Param("record") TRmMacEnvHis record, @Param("spec") TRmMacEnvHisSpec spec);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @UpdateProvider(type=TRmMacEnvHisSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TRmMacEnvHis record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table IN.T_RM_MAC_ENV_HIS
     *
     * @mbggenerated Wed Aug 20 14:41:50 KST 2014
     */
    @Update({
        "update IN.T_RM_MAC_ENV_HIS",
        "set INSERT_UID = #{insertUid,jdbcType=VARCHAR},",
          "INSERT_DATE = #{insertDate,jdbcType=TIMESTAMP},",
          "STS = #{sts,jdbcType=VARCHAR},",
          "SITE_CD = #{siteCd,jdbcType=VARCHAR},",
          "DEPT_CD = #{deptCd,jdbcType=VARCHAR},",
          "OFFICE_CD = #{officeCd,jdbcType=VARCHAR},",
          "TEAM_CD = #{teamCd,jdbcType=VARCHAR},",
          "PRDC_NO = #{prdcNo,jdbcType=VARCHAR},",
          "MKR_CD = #{mkrCd,jdbcType=VARCHAR},",
          "MODEL_CD = #{modelCd,jdbcType=VARCHAR},",
          "AP_VER = #{apVer,jdbcType=VARCHAR},",
          "SET_PLACE = #{setPlace,jdbcType=VARCHAR},",
          "PUB_IP_ADDR = #{pubIpAddr,jdbcType=VARCHAR},",
          "PRI_IP_ADDR = #{priIpAddr,jdbcType=VARCHAR},",
          "IP_PORT = #{ipPort,jdbcType=VARCHAR},",
          "HOST_IP_ADDR = #{hostIpAddr,jdbcType=VARCHAR},",
          "HOST_IP_PORT = #{hostIpPort,jdbcType=VARCHAR},",
          "AOC_IP_ADDR = #{aocIpAddr,jdbcType=VARCHAR},",
          "AOC_IP_PORT = #{aocIpPort,jdbcType=VARCHAR},",
          "REBOOT_TIME = #{rebootTime,jdbcType=VARCHAR},",
          "MAC_OS = #{macOs,jdbcType=VARCHAR},",
          "MAC_CPU = #{macCpu,jdbcType=VARCHAR},",
          "MAC_MEM = #{macMem,jdbcType=VARCHAR},",
          "MAC_HDD = #{macHdd,jdbcType=VARCHAR},",
          "MODEM_RELAY_YN = #{modemRelayYn,jdbcType=VARCHAR},",
          "RPC_YN = #{rpcYn,jdbcType=VARCHAR},",
          "CREDIT_CARD_YN = #{creditCardYn,jdbcType=VARCHAR},",
          "CARD_LOAN_YN = #{cardLoanYn,jdbcType=VARCHAR},",
          "CHRG_HIPASS_YN = #{chrgHipassYn,jdbcType=VARCHAR},",
          "FRGN_TRAN_TYPE = #{frgnTranType,jdbcType=VARCHAR},",
          "OUR_MAX_WDR_AMT = #{ourMaxWdrAmt,jdbcType=DECIMAL},",
          "THR_MAX_WDR_AMT = #{thrMaxWdrAmt,jdbcType=DECIMAL},",
          "OUR_MAX_DPS_AMT = #{ourMaxDpsAmt,jdbcType=DECIMAL},",
          "THR_MAX_DPS_AMT = #{thrMaxDpsAmt,jdbcType=DECIMAL},",
          "OUR_MAX_TSF_AMT = #{ourMaxTsfAmt,jdbcType=DECIMAL},",
          "THR_MAX_TSF_AMT = #{thrMaxTsfAmt,jdbcType=DECIMAL},",
          "MAX_SVC_WDR_AMT = #{maxSvcWdrAmt,jdbcType=DECIMAL},",
          "MAX_CASH_WDR_CNT = #{maxCashWdrCnt,jdbcType=DECIMAL},",
          "MAX_CASH_DPS_CNT = #{maxCashDpsCnt,jdbcType=DECIMAL},",
          "MAX_CASH_SVC_WDR_CNT = #{maxCashSvcWdrCnt,jdbcType=DECIMAL}",
        "where ORG_CD = #{orgCd,jdbcType=VARCHAR}",
          "and BRANCH_CD = #{branchCd,jdbcType=VARCHAR}",
          "and MAC_NO = #{macNo,jdbcType=VARCHAR}",
          "and TRX_DATE = #{trxDate,jdbcType=VARCHAR}",
          "and TRX_NO = #{trxNo,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(TRmMacEnvHis record);
}
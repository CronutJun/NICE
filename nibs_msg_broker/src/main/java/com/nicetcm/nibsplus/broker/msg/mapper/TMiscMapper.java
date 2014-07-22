package com.nicetcm.nibsplus.broker.msg.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;
import com.nicetcm.nibsplus.broker.msg.services.In03101110Impl.CloseAmt;
import com.nicetcm.nibsplus.broker.msg.services.In03101130Impl.CmCash;

public interface TMiscMapper {

    /**
     * CommonPackImpl.getAutoSendInfo 에서 호출
     *
     * @author KDJ, on Tue Jul 02 11:18:00 KST 2014
     */
    @Select({
        "SELECT OP.F_GET_HOLIDAY(TO_CHAR(SYSDATE,'YYYYMMDD')) AS HOLIDAY",
        "FROM DUAL"
    })
    @Results({
        @Result(column="HOLIDAY", property="holiday", jdbcType=JdbcType.VARCHAR)
    })
    TMisc selectHoliday();

    /**
     * CommonPackImpl.insertErrorMng 에서 호출
     *
     * @author KDJ, on Tue Jul 04 11:14:00 KST 2014
     */
    @Select({
        "SELECT OP.F_GET_NICE_BRANCH_CD(#{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, '', #{macNo, jdbcType=VARCHAR}) AS BRANCH_CD",
        "FROM DUAL"
    })
    @Results({
        @Result(column="BRANCH_CD", property="branchCd", jdbcType=JdbcType.VARCHAR)
    })
    TMisc getNiceBranchCd( TCtErrorBasic cond );

    /**
     * CommonPackImpl.insertErrorMng 에서 호출
     *
     * 장애등록 Key Generation
     *
     * @author KDJ, on Tue Jul 04 11:14:00 KST 2014
     */
    @Select({
        "SELECT LPAD( OP.SEQ_T_CT_ERROR_NO.NEXTVAL, 6, '0' ) AS ERROR_NO",
        "FROM DUAL"
    })
    @Results({
        @Result(column="ERROR_NO", property="errorNo", jdbcType=JdbcType.VARCHAR)
    })
    TMisc generateErrorNo();

    @Select({
        "SELECT (CASH.TOT_IN_AMT - CASH.CHECK_IN_AMT) AS CLOSE_IN_AMT     ",
        "      ,(CASH.TOT_OUT_AMT - CASH.CHECK_OUT_AMT) AS CLOSE_OUT_AMT  ",
        "FROM   OP.T_CM_CASH CASH                                         ",
        "      ,(SELECT ORG_CD, BRANCH_CD, MAC_NO                         ",
        "        FROM   OP.T_CM_MAC                                       ",
        "        WHERE      ORG_CD = #{orgCd, jdbcType=VARCHAR}           ",
        "               AND BRANCH_CD = #{branchCd, jdbcType=VARCHAR}     ",
        "               AND MAC_NO = #{macNo, jdbcType=VARCHAR}           ",
        "        MINUS                                                    ",
        "        SELECT ORG_CD, BRANCH_CD, MAC_NO                         ",
        "        FROM   OP.T_FN_SEND                                      ",
        "        WHERE      SEND_DATE = TO_CHAR(SYSDATE, 'YYYYMMDD')      ",
        "               AND SEND_TYPE = '1'                               ",
        "               AND ORG_CD = #{orgCd, jdbcType=VARCHAR}           ",
        "               AND BRANCH_CD = #{branchCd, jdbcType=VARCHAR}     ",
        "               AND MAC_NO = #{macNo, jdbcType=VARCHAR}) BB       ",
        "WHERE      CASH.CASH_DATE = TO_CHAR(SYSDATE, 'YYYYMMDD')         ",
        "       AND CASH.CASH_TYPE = '2'                                  ",
        "       AND CASH.ORG_CD = #{orgCd, jdbcType=VARCHAR}              ",
        "       AND CASH.BRANCH_CD = #{branchCd, jdbcType=VARCHAR}        ",
        "       AND CASH.MAC_NO = #{macNo, jdbcType=VARCHAR}              ",
        "       AND CASH.ORG_CD = BB.ORG_CD                               ",
        "       AND CASH.BRANCH_CD = BB.BRANCH_CD                         ",
        "       AND CASH.MAC_NO = BB.MAC_NO                               "
    })
    @Results({
        @Result(column="CLOSE_IN_AMT", property="closeInAmt", jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_OUT_AMT", property="closeOutAmt", jdbcType=JdbcType.VARCHAR)
    })
    CloseAmt selectCloseAmt(TMacInfo tMacInfo);

    @Select({
        "SELECT COUNT(*) AS HIS_CLOSE                         ",
        "FROM   OP.T_CM_CASH                                     ",
        "WHERE      CASH_DATE = #{inqDate, jdbcType=VARCHAR}  ",
        "       AND ORG_CD    = #{orgCd, jdbcType=VARCHAR}    ",
        "       AND BRANCH_CD = #{branchCd, jdbcType=VARCHAR}  ",
        "       AND MAC_NO    = #{macNo, jdbcType=VARCHAR}    ",
        "       AND CASH_TYPE = '2'                           "
    })
    @Results({
        @Result(column="hIS_CLOSE", property="hisClose", jdbcType=JdbcType.VARCHAR),
    })
    CmCash selectCountCmCash(CmCash cmCash);

    @Update({
        "update OP.T_FN_ATMS_COLLECT                                                                                                                                                                                                                                                                                                                                                            ",
        "set                                                                                                                                                                                                      ",
        "       ORG_SEND_YN = '1',                                                                                                                                                                                ",
        "       MATCH_YN    = '1',                                                                                                                                                                                ",
        "       UPDATE_DATE = SYSDATE,                                                                                                                                                                            ",
        "       UPDATE_UID  = 'APmng'                                                                                                                                                                             ",
        "where  ORG_CD      = DECODE(#{orgCd, jdbcType=VARCHAR}, 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                                                          ",
        "       and       branch_cd    = CASE WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39'                                                                                    ",
        "                                   THEN #{branchCd, jdbcType=VARCHAR}                                                                                                                                    ",
        "                                   ELSE DECODE (OP.FC_GET_MAP_branch_cd( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}),                                       ",
        "                                                NULL, #{branchCd, jdbcType=VARCHAR}, OP.FC_GET_MAP_branch_cd( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) ) ",
        "                                   END                                                                                                                                                                   ",
        "       and        MAC_NO      = CASE WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39'                                                                                   ",
        "                                   THEN #{macNo, jdbcType=VARCHAR}                                                                                                                                       ",
        "                                   ELSE DECODE (OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}),                                         ",
        "                                                NULL, #{macNo, jdbcType=VARCHAR}, OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) )      ",
        "                                   END                                                                                                                                                                   ",
        "       and        CLOSE_DATE    = :suBody.close_date                                                                                                                                                     "
    })
    void updateFnAtmsCollect(TMacInfo tMacInfo);

    @Update({
        "update OP.T_FN_ATMS_REPORT                                                                                                                                                                                                                                                                                                                                                                 ",
        "set                                                                                                                                                                                                        ",
        "       ORG_SEND_YN = '1',                                                                                                                                                                                  ",
        "       UPDATE_DATE = SYSDATE,                                                                                                                                                                              ",
        "       UPDATE_UID  = 'APmng'                                                                                                                                                                               ",
        "where  ORG_CD = DECODE(#{orgCd, jdbcType=VARCHAR}, 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                                                                 ",
        "  and       branch_cd    = CASE WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39'                                                                                           ",
        "                                THEN #{branchCd, jdbcType=VARCHAR}                                                                                                                                         ",
        "                                ELSE DECODE (OP.FC_GET_MAP_branch_cd( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}),                                            ",
        "                                                  NULL, #{branchCd, jdbcType=VARCHAR}, OP.FC_GET_MAP_branch_cd( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) ) ",
        "                          END                                                                                                                                                                              ",
        "  and        MAC_NO     = CASE WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39'                                                                                           ",
        "                               THEN #{macNo, jdbcType=VARCHAR}                                                                                                                                             ",
        "                                   ELSE DECODE (OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}),                                           ",
        "                                                 NULL, #{macNo, jdbcType=VARCHAR}, OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) )       ",
        "                          END                                                                                                                                                                              ",
        "  and     TRADE_DATE  = #{tradeDate, jdbcType=VARCHAR}                                                                                                                                                     ",
        "  and     SERIAL_NO   = #{serialNo, jdbcType=VARCHAR}                                                                                                                                                      "
    })
    void updateFnAtmsReport(TMacInfo tMacInfo);

    @Update({
        "update OP.T_FN_ATMS_DEMAND_MAC                                                                                                                                                                                                                                                                                                                                                                         ",
        "set                                                                                                                                                                                                                ",
        "       ORG_SEND_YN = 'c',                                                                                                                                                                                              ",
        "       UPDATE_DATE = SYSDATE,                                                                                                                                                                                          ",
        "       UPDATE_UID  = 'APmng'                                                                                                                                                                                           ",
        "where  ORG_CD        = DECODE(#{orgCd, jdbcType=VARCHAR}, 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                                                                  ",
        "        and       BRANCH_CD    = CASE WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39'                                                                                            ",
        "                                      THEN #{branchCd, jdbcType=VARCHAR}                                                                                                                                           ",
        "                                      ELSE DECODE (OP.FC_GET_MAP_BRANCH_CD( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR},#{macNo, jdbcType=VARCHAR}),                                              ",
        "                                                        NULL, #{branchCd, jdbcType=VARCHAR}, OP.FC_GET_MAP_BRANCH_CD( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR},#{macNo, jdbcType=VARCHAR}) )   ",
        "                                      END                                                                                                                                                                          ",
        "        and        MAC_NO      = CASE WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39'                                                                                            ",
        "                                      THEN#{macNo, jdbcType=VARCHAR}                                                                                                                                               ",
        "                                      ELSE DECODE (OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR},#{macNo, jdbcType=VARCHAR}),                                                 ",
        "                                                        NULL,#{macNo, jdbcType=VARCHAR}, OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR},#{macNo, jdbcType=VARCHAR}) )          ",
        "                                      END                                                                                                                                                                          ",
        "and        ORG_SEND_YN = '0'                                                                                                                                                                                           "
    })
    void updateFnAtmsDemandMac01(TMacInfo tMacInfo);

    @Update({
        "update OP.T_FN_ATMS_DEMAND_MAC                                                                                                                                                                                     ",
        "set                                                                                                                                                                                                                ",
        "       ORG_SEND_YN = '1',                                                                                                                                                                                              ",
        "       UPDATE_DATE = SYSDATE,                                                                                                                                                                                          ",
        "       UPDATE_UID  = 'APmng'                                                                                                                                                                                           ",
        "where  ORG_CD        = DECODE(#{orgCd, jdbcType=VARCHAR}, 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                                                                  ",
        "        and       BRANCH_CD    = CASE WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39'                                                                                            ",
        "                                      THEN #{branchCd, jdbcType=VARCHAR}                                                                                                                                           ",
        "                                      ELSE DECODE (OP.FC_GET_MAP_BRANCH_CD( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}),                                             ",
        "                                                        NULL, #{branchCd, jdbcType=VARCHAR}, OP.FC_GET_MAP_BRANCH_CD( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) )  ",
        "                                      END                                                                                                                                                                          ",
        "        and        MAC_NO      = CASE WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39'                                                                                            ",
        "                                      THEN #{macNo, jdbcType=VARCHAR}                                                                                                                                              ",
        "                                      ELSE DECODE (OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}),                                                ",
        "                                                        NULL, #{macNo, jdbcType=VARCHAR}, OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) )        ",
        "                                      END                                                                                                                                                                          ",
        "        and        DEMAND_DATE = #{reqDate, jdbcType=VARCHAR}                                                                                                                                                          "
    })
    void updateFnAtmsDemandMac02(TMacInfo tMacInfo);

    @Update({
        "update OP.T_FN_ATMS_OPERFUNDS_DEMAND                                                                                                                                                                               ",
        "set                                                                                                                                                                                                                ",
        "       ORG_SEND_YN = '1',                                                                                                                                                                                              ",
        "       UPDATE_DATE = SYSDATE,                                                                                                                                                                                          ",
        "       UPDATE_UID  = 'APmng'                                                                                                                                                                                           ",
        "where  ORG_CD      = DECODE(#{orgCd, jdbcType=VARCHAR}, 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                                                                      ",
        "and        BRANCH_CD   = DECODE(#{branchCd, jdbcType=VARCHAR}, '','9999',#{branchCd, jdbcType=VARCHAR})                                                                                                                  ",
        "and        REQ_DATE    = #{reqDate, jdbcType=VARCHAR}                                                                                                                                                                    "
    })
    void updateFnAtmsOperfundsDemand(TMacInfo tMacInfo);

    @Update({
        "update    OP.T_FN_ATMS_CASH_PLAN                                                                                                                                                                                       ",
        "            set                                                                                                                                                                                                        ",
        "                    ORG_SEND_CONFIRM_YN    = #{orgSendConfirmYn, jdbcType=VARCHAR},                                                                                                                                    ",
        "                    UPDATE_DATE            = SYSDATE,                                                                                                                                                                  ",
        "                    UPDATE_UID            = 'APmng'                                                                                                                                                                    ",
        "            where    ORG_CD        = DECODE(#{orgCd, jdbcType=VARCHAR}, 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                                                        ",
        "            and       branch_cd    = CASE WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39'                                                                                             ",
        "                                          THEN #{branchCd, jdbcType=VARCHAR}                                                                                                                                           ",
        "                                          ELSE DECODE (OP.FC_GET_MAP_branch_cd( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}),                                              ",
        "                                                            NULL, #{branchCd, jdbcType=VARCHAR}, OP.FC_GET_MAP_branch_cd( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) )   ",
        "                                          END                                                                                                                                                                          ",
        "            and        MAC_NO      = CASE WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39'                                                                                            ",
        "                                          THEN #{macNo, jdbcType=VARCHAR}                                                                                                                                              ",
        "                                          ELSE DECODE (OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}),                                                ",
        "                                                            NULL, #{macNo, jdbcType=VARCHAR}, OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) )        ",
        "                                          END                                                                                                                                                                          ",
        "            and        CASH_DATE   = #{cashDate, jdbcType=VARCHAR}                                                                                                                                                     "
    })
    void updateTFnAtmsCashPlan(TMisc tMisc);
}

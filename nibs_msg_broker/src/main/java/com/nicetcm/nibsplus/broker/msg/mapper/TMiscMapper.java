package com.nicetcm.nibsplus.broker.msg.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.nicetcm.nibsplus.broker.msg.model.TCmCheckMaster;
import com.nicetcm.nibsplus.broker.msg.model.TCmGoodsApply;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorBasic;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngMadeCom;
import com.nicetcm.nibsplus.broker.msg.model.TCtPenaltyList;
import com.nicetcm.nibsplus.broker.msg.model.TFnBoxOrg;
import com.nicetcm.nibsplus.broker.msg.model.TFnTicketDeal;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;
import com.nicetcm.nibsplus.broker.msg.model.TbPhsPushMessages;
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
        "UPDATE OP.T_FN_ATMS_COLLECT SET                                                                                                                                 ",
        "       ORG_SEND_YN = '1',                                                                                                                                       ",
        "       MATCH_YN    = '1',                                                                                                                                       ",
        "       UPDATE_DATE = SYSDATE,                                                                                                                                   ",
        "       UPDATE_UID  = 'APmng'                                                                                                                                    ",
        "WHERE  ORG_CD      = DECODE(CAST(#{orgCd, jdbcType=VARCHAR} AS VARCHAR), 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                 ",
        "AND    BRANCH_CD   = CASE                                                                                                                                       ",
        "                     WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39' THEN                                                     ",
        "                          #{branchCd, jdbcType=VARCHAR}                                                                                                         ",
        "                     ELSE                                                                                                                                       ",
        "                          CASE                                                                                                                                  ",
        "                          WHEN OP.FC_GET_MAP_BRANCH_CD( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR} ) IS NULL THEN ",
        "                               #{branchCd, jdbcType=VARCHAR}                                                                                                    ",
        "                          ELSE                                                                                                                                  ",
        "                               OP.FC_GET_MAP_BRANCH_CD( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR} )              ",
        "                          END                                                                                                                                   ",
        "                     END                                                                                                                                        ",
        "AND    MAC_NO      = CASE                                                                                                                                       ",
        "                     WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39' THEN                                                     ",
        "                          #{macNo, jdbcType=VARCHAR}                                                                                                            ",
        "                     ELSE                                                                                                                                       ",
        "                          CASE                                                                                                                                  ",
        "                          WHEN OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR} ) IS NULL THEN    ",
        "                               #{macNo, jdbcType=VARCHAR}                                                                                                       ",
        "                          ELSE                                                                                                                                  ",
        "                               OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR} )                 ",
        "                          END                                                                                                                                   ",
        "                     END                                                                                                                                        ",
        "AND    CLOSE_DATE  = #{closeDate, jdbcType=VARCHAR}                                                                                                             "
    })
    void updateFnAtmsCollect(TMacInfo tMacInfo);

    @Update({
        "UPDATE OP.T_FN_ATMS_REPORT SET                                                                                                                               ",
        "       ORG_SEND_YN = '1',                                                                                                                                    ",
        "       UPDATE_DATE = SYSDATE,                                                                                                                                ",
        "       UPDATE_UID  = 'APmng'                                                                                                                                 ",
        "WHERE  ORG_CD    = DECODE(CAST(#{orgCd, jdbcType=VARCHAR} AS VARCHAR), 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                ",
        "AND    BRANCH_CD = CASE                                                                                                                                      ",
        "                   WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39' THEN                                                    ",
        "                        #{branchCd, jdbcType=VARCHAR}                                                                                                        ",
        "                   ELSE CASE                                                                                                                                 ",
        "                        WHEN OP.FC_GET_MAP_BRANCH_CD( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) IS NULL THEN ",
        "                             #{branchCd, jdbcType=VARCHAR}                                                                                                   ",
        "                        ELSE                                                                                                                                 ",
        "                             OP.FC_GET_MAP_BRANCH_CD( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR})              ",
        "                        END                                                                                                                                  ",
        "                   END                                                                                                                                       ",
        "AND    MAC_NO    = CASE                                                                                                                                      ",
        "                   WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39' THEN                                                    ",
        "                        #{macNo, jdbcType=VARCHAR}                                                                                                           ",
        "                   ELSE CASE                                                                                                                                 ",
        "                        WHEN OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) IS NULL THEN    ",
        "                             #{macNo, jdbcType=VARCHAR}                                                                                                      ",
        "                        ELSE                                                                                                                                 ",
        "                             OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR})                 ",
        "                        END                                                                                                                                  ",
        "                   END                                                                                                                                       ",
        "AND    TRADE_DATE  = #{tradeDate, jdbcType=VARCHAR}                                                                                                          ",
        "AND    SERIAL_NO   = #{serialNo, jdbcType=VARCHAR}                                                                                                           "
    })
    void updateFnAtmsReport(TMacInfo tMacInfo);

    @Update({
        "update OP.T_FN_ATMS_DEMAND_MAC                                                                                                                                                                                                                                                                                                                                                                         ",
        "set                                                                                                                                                                                                                ",
        "       ORG_SEND_YN = 'c',                                                                                                                                                                                              ",
        "       UPDATE_DATE = SYSDATE,                                                                                                                                                                                          ",
        "       UPDATE_UID  = 'APmng'                                                                                                                                                                                           ",
        "where  ORG_CD        = DECODE(CAST(#{orgCd, jdbcType=VARCHAR} AS VARCHAR), 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                                                                  ",
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
        "where  ORG_CD        = DECODE(CAST(#{orgCd, jdbcType=VARCHAR} AS VARCHAR), 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                                                                  ",
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
        "where  ORG_CD      = DECODE(CAST(#{orgCd, jdbcType=VARCHAR} AS VARCHAR), 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                                                                      ",
        "and        BRANCH_CD   = DECODE(CAST(#{branchCd, jdbcType=VARCHAR} AS VARCHAR), '','9999',#{branchCd, jdbcType=VARCHAR})                                                                                                                  ",
        "and        REQ_DATE    = #{reqDate, jdbcType=VARCHAR}                                                                                                                                                                    "
    })
    void updateFnAtmsOperfundsDemand(TMacInfo tMacInfo);

    @Update({
        "UPDATE OP.T_FN_ATMS_CASH_PLAN SET                                                                                                                              ",
        "       ORG_SEND_CONFIRM_YN    = #{orgSendConfirmYn, jdbcType=VARCHAR},                                                                                         ",
        "       UPDATE_DATE            = SYSDATE,                                                                                                                       ",
        "       UPDATE_UID             = 'APmng'                                                                                                                        ",
        "WHERE  ORG_CD      = DECODE(CAST(#{orgCd, jdbcType=VARCHAR} AS VARCHAR), 'T20', '020', 'T39', '039', #{orgCd, jdbcType=VARCHAR})                                                ",
        "AND    BRANCH_CD   = CASE                                                                                                                                      ",
        "                     WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39' THEN                                                    ",
        "                          #{branchCd, jdbcType=VARCHAR}                                                                                                        ",
        "                     ELSE                                                                                                                                      ",
        "                          CASE                                                                                                                                 ",
        "                          WHEN OP.FC_GET_MAP_BRANCH_CD( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) IS NULL THEN ",
        "                               #{branchCd, jdbcType=VARCHAR}                                                                                                   ",
        "                          ELSE                                                                                                                                 ",
        "                               OP.FC_GET_MAP_BRANCH_CD( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR})              ",
        "                          END                                                                                                                                  ",
        "                     END                                                                                                                                       ",
        "AND    MAC_NO      = CASE                                                                                                                                      ",
        "                     WHEN #{orgCd, jdbcType=VARCHAR} <> 'T20' AND  #{orgCd, jdbcType=VARCHAR} <> 'T39' THEN                                                    ",
        "                          #{macNo, jdbcType=VARCHAR}                                                                                                           ",
        "                     ELSE                                                                                                                                      ",
        "                          CASE                                                                                                                                 ",
        "                          WHEN OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) IS NULL THEN    ",
        "                               #{macNo, jdbcType=VARCHAR}                                                                                                      ",
        "                          ELSE                                                                                                                                 ",
        "                               OP.FC_GET_MAP_MAC_NO( 1, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR})                 ",
        "                          END                                                                                                                                  ",
        "                     END                                                                                                                                       ",
        "AND    CASH_DATE   = #{cashDate, jdbcType=VARCHAR}                                                                                                             "
    })
    void updateTFnAtmsCashPlan(TMisc tMisc);

    @Select({
        "SELECT site.dept_cd,                                ",
        "       site.office_cd                               ",
        "from op.t_cm_mac mac,                               ",
        "     op.t_cm_site site                              ",
        "where site.org_cd = mac.org_cd                      ",
        "and    site.branch_cd = mac.branch_cd                 ",
        "and    site.site_cd = mac.site_cd                   ",
        "and    mac.org_cd = #{orgCd, jdbcType=VARCHAR}      ",
        "and    mac.branch_cd = #{branchCd, jdbcType=VARCHAR} ",
        "and    mac.mac_no = #{macNo, jdbcType=VARCHAR}      "
    })
    @Results({
        @Result(column="DEPT_CD", property="deptCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD", property="officeCd", jdbcType=JdbcType.VARCHAR)
    })
    TMacInfo getDeptOfficeCd(TMacInfo tMacInfo);

    @Select({
        "SELECT count(*) cnt                                                                                                                                                                        ",
        "FROM   OP.T_FN_CHALSI_DEAL_DSUM                                                                                                                                                            ",
        "where  deal_date      = decode(trim(#{closeType, jdbcType=VARCHAR}), '01', to_char(to_date(#{dealDate, jdbcType=VARCHAR}, 'YYYYMMDD') + 1, 'YYYYMMDD'), #{dealDate, jdbcType=VARCHAR} )    ",
        "  and  org_cd         = #{orgCd, jdbcType=VARCHAR}                                                                                                                                         ",
        "  and  branch_cd       = #{branchCd, jdbcType=VARCHAR}                                                                                                                                      ",
        "  and  mac_no         = #{macNo, jdbcType=VARCHAR}                                                                                                                                         ",
        "  and  close_type     = trim(#{closeType, jdbcType=VARCHAR})                                                                                                                               "
    })
    @Results({
        @Result(column="cnt", property="cnt", jdbcType=JdbcType.VARCHAR),
    })
    TMisc selectCountFnChalsiDealDsum(TMisc tMisc);

    /**
     *
     * 원거래 데이터가 존재하는지 체크
     * <pre>
     * DBGetOwnTradeSeqYN
     * </pre>
     *
     * @param tMisc
     * @return
     */
    @Select({
        "SELECT  COUNT(*) cnt                               ",
        "FROM   OP.T_FN_INOUT_ORG                           ",
        "WHERE  ORG_CD = #{orgCd, jdbcType=VARCHAR}         ",
        "AND    BRANCH_CD = #{branchCd, jdbcType=VARCHAR}   ",
        "AND    MAC_NO = #{macNo, jdbcType=VARCHAR}         ",
        "AND    DEAL_DATE = #{dealDate, jdbcType=VARCHAR}   ",
        "AND    SEQ = #{seq, jdbcType=VARCHAR}              "
    })
    @Results({
        @Result(column="cnt", property="cnt", jdbcType=JdbcType.VARCHAR),
    })
    TMisc getOwnTradeSeqYN(TMisc tMisc);

    /**
     *
     * BOX 데이터가 RETRY 되어 중복 수신되었을 경우 저장 하지 않고 정상으로 RETURN 처리
     * <pre>
     * DBGetBoxRecvYN
     * </pre>
     *
     * @param tMisc
     * @return
     */
    @Select({
        "SELECT  COUNT(*) cnt                                                                                                       ",
        "FROM   OP.T_FN_BOX_ORG                                                                                                     ",
        "WHERE  ORG_CD = #{orgCd, jdbcType=VARCHAR}                                                                                 ",
        "AND    BRANCH_CD = #{branchCd, jdbcType=VARCHAR}                                                                           ",
        "AND    MAC_NO = #{macNo, jdbcType=VARCHAR}                                                                                 ",
        "AND    DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                                           ",
        "AND    SEQ = #{seq, jdbcType=VARCHAR}                                                                                      ",
        "AND    BOX_GUBUN1 = #{boxGubun1, jdbcType=VARCHAR}                                                                         ",
        "AND    CASE WHEN NVL(BOX_GUBUN2, 'A') = 'A' THEN 'A'                                                                       ",
        "       ELSE  LPAD( BOX_GUBUN2, 4, '0')                                                                                     ",
        "       END = DECODE(NVL(#{boxGubun2, jdbcType=VARCHAR}, 'A'), 'A', 'A', LPAD( #{boxGubun2, jdbcType=VARCHAR}, 4, '0'))     ",
        "AND    CASE WHEN NVL(KJ_GUBUN , 'A') = 'A' THEN 'A'                                                                        ",
        "       ELSE  KJ_GUBUN                                                                                                      ",
        "       END = DECODE(NVL(#{kjGubun, jdbcType=VARCHAR}, 'A'), 'A', 'A', LPAD( #{kjGubun, jdbcType=VARCHAR}, 4, '0'))         "
    })
    @Results({
        @Result(column="cnt", property="cnt", jdbcType=JdbcType.VARCHAR),
    })
    TMisc getBoxRecvYN(TMisc tMisc);

    /**
     *
     * RETRY로 인한 중복 수신 처리
     * <pre>
     * DBGetTicketDealRecvYN
     * </pre>
     *
     * @param tMisc
     * @return
     */
    @Select({
        "SELECT  COUNT(*) cnt                                                                                ",
        "FROM   OP.T_FN_TICKET_DEAL                                                                          ",
        "WHERE  ORG_CD = #{orgCd, jdbcType=VARCHAR}                                                          ",
        "AND    BRANCH_CD = #{branchCd, jdbcType=VARCHAR}                                                    ",
        "AND    MAC_NO = #{macNo, jdbcType=VARCHAR}                                                               ",
        "AND    DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                            ",
        "AND    SEQ = #{seq, jdbcType=VARCHAR}                                                                  ",
        "AND    TICKET_CD = LPAD(#{ticketCd, jdbcType=VARCHAR},4,'0')                                                ",
        "AND    CASE WHEN NVL(KJ_GUBUN , 'A') = 'A' THEN 'A'                                                 ",
        "       ELSE  KJ_GUBUN                                                                               ",
        "       END = DECODE(NVL(#{kjGubun, jdbcType=VARCHAR}, 'A'), 'A', 'A', LPAD( #{kjGubun, jdbcType=VARCHAR}, 4, '0'))"
    })
    @Results({
        @Result(column="cnt", property="cnt", jdbcType=JdbcType.VARCHAR),
    })
    TMisc getTicketDealRecvYN(TMisc tMisc);

    @Select({
        "SELECT OP.SEQ_BOX.NEXTVAL AS boxseq",
        "FROM DUAL"
    })
    @Results({
        @Result(column="boxseq", property="boxSeq", jdbcType=JdbcType.VARCHAR)
    })
    TFnBoxOrg generateSeqBox();

    @Select({
        "SELECT OP.SEQ_TICKET_DEAL.NEXTVAL AS TICKET_DEAL_ID",
        "FROM DUAL"
    })
    @Results({
        @Result(column="TICKET_DEAL_ID", property="ticketDealId", jdbcType=JdbcType.VARCHAR)
    })
    TFnTicketDeal generateSeqTicketDeal();

    /**
     *
     * 기기 제조사 코드를 얻는다.
     * <pre>
     * int      DBGetMadeComCd(struct MACINFO_DATA *psuMacInfo, char * pMadeComCd, char * pMadeOrgCd)
     * </pre>
     *
     * @param orgCd
     * @param branchCd
     * @param macNo
     * @return
     */
    @Select({
        "SELECT  mac.made_com_cd,                              ",
        "        co1.cd_nm5                                    ",
        "  FROM  OP.T_CM_MAC mac,                                 ",
        "        OP.T_CM_COMMON co1                               ",
        " WHERE  mac.org_cd = #{orgCd, jdbcType=VARCHAR}       ",
        "   AND  mac.branch_cd = #{branchCd, jdbcType=VARCHAR} ",
        "   AND  mac.mac_no = #{macNo, jdbcType=VARCHAR}       ",
        "   AND  co1.large_cd = '2100'                         ",
        "   AND  co1.small_cd = mac.made_com_cd                "
    })
    @Results({
        @Result(column="made_com_cd", property="madeComCd", jdbcType=JdbcType.VARCHAR),
        @Result(column="cd_nm5", property="madeOrgCd", jdbcType=JdbcType.VARCHAR)
    })
    TMisc getMadeComCd(@Param("orgCd") String orgCd, @Param("branchCd") String branchCd, @Param("macNo") String macNo);

    @Select({
        "SELECT case when (nvl(retire_date,'0') < to_char(SYSDATE, 'YYYYMMDD')) ",
        "        then '0'                                                       ",
        "    else     '1'                                                       ",
        "end mbr_yn                                                             ",
        "FROM OP.T_CM_MEMBER                                                       ",
        "WHERE SUBSTR(resident_no,1,6) = SUBSTR(#{mbrIdNo, jdbcType=VARCHAR},1,6)           ",
        "AND member_nm = #{mbrNm, jdbcType=VARCHAR}                                        "
    })
    String getCmMemberYn(@Param("mbrIdNo") String mbrIdNo, @Param("mbrNm") String mbrNm);

    /**
     *
     * 코너코드 삽입
     * <pre>
     *           [기업은행] 브랜드제휴 패널티적용명세
     *            - 관리점코드와 코너(사이트)코드가 비어서 들어오므로,
     *              데이터가 입력되기 전에 강제로 넣어준다.
     * </pre>
     *
     * @param macNo
     * @return
     */
    @Select({
        "SELECT SITE.SITE_CD                             ",
        "FROM    OP.T_CM_SITE SITE,                      ",
        "        OP.T_CM_MAC  MAC                        ",
        "WHERE   SITE.ORG_CD = MAC.ORG_CD                ",
        "    AND SITE.BRANCH_CD = MAC.BRANCH_CD            ",
        "    AND SITE.SITE_CD  = MAC.SITE_CD             ",
        "    AND MAC.ORG_CD = '096'                      ",
        "    AND MAC.BRANCH_CD = '9600'                   ",
        "    ANd MAC.MAC_NO = #{macNo, jdbcType=VARCHAR} "
    })
    String getSiteCdOrg096(@Param("macNo") String macNo);

    @Select({
        "SELECT OP.SEQ_T_CT_PENALTY.NEXTVAL AS SEQ_NO",
        "FROM DUAL"
    })
    @Results({
        @Result(column="SEQ_NO", property="seqNo", jdbcType=JdbcType.VARCHAR)
    })
    TCtPenaltyList generateSeqPenaltyList();

    @Update({
        "UPDATE OP.T_CM_GOODS_APPLY                              ",
        "SET     ORG_SEND_YN = '1'                               ",
        "WHERE   APPLY_DATE = #{applyDate, jdbcType=VARCHAR}     ",
        "    AND ORG_CD     = #{orgCd, jdbcType=VARCHAR}         ",
        "    AND branch_cd  = #{branchCd, jdbcType=VARCHAR}      ",
        "    AND GOOD_CD    = #{goodCd, jdbcType=VARCHAR}        ",
        "    AND ( ORG_SEND_YN = '0' or ORG_SEND_YN IS NULL )    "
    })
    void updateCmGoodsApply(TCmGoodsApply tCmGoodsApply);

    @Select({
        "select Round(( To_Date(#{yyyymmddhh24miss1, jdbcType=VARCHAR}, 'yyyymmddhh24miss') - To_Date(#{yyyymmddhh24miss2, jdbcType=VARCHAR}, 'yyyymmddhh24miss') )*60*60*24) elapse_time ",
        "from dual                                                                                                                                                            "
    })
    String getElapseTime(@Param("yyyymmddhh24miss1") String yyyymmddhh24miss1, @Param("yyyymmddhh24miss2") String yyyymmddhh24miss2);

    @Update({
        "UPDATE  OP.T_CT_ERROR_MNG_MADE_COM                                                                                                                 ",
        "SET     ARRIVAL_EST_DATE = rtrim(#{arrivalEstDate, jdbcType=VARCHAR}),                                                                             ",
        "        ARRIVAL_EST_TIME = SUBSTR(rtrim(#{arrivalEstTime, jdbcType=VARCHAR}), 1, 4),                                                               ",
        "        COM_MAN_NM = DECODE(CAST(#{comManNm, jdbcType=VARCHAR} AS VARCHAR), NULL, COM_MAN_NM, #{comManNm, jdbcType=VARCHAR}),                                       ",
        "        COM_MAN_TEL_NO = DECODE(CAST(#{comManTelNo, jdbcType=VARCHAR} AS VARCHAR), NULL, COM_MAN_TEL_NO, OP.FC_FN_SECURITY(#{comManTelNo, jdbcType=VARCHAR}, '1')), ",
        "        UPDATE_DATE = SYSDATE,                                                                                                                     ",
        "        UPDATE_UID  = 'ERRmng'                                                                                                                     ",
        "WHERE   AS_ACPT_DATE = #{asAcptDate, jdbcType=VARCHAR}                                                                                             ",
        "  AND   ORG_CD = #{orgCd, jdbcType=VARCHAR}                                                                                                        ",
        "  AND   BRANCH_CD = #{branchCd, jdbcType=VARCHAR}                                                                                                  ",
        "  AND   MAC_NO = #{macNo, jdbcType=VARCHAR}                                                                                                        ",
        "  AND   ORG_CALL_CNT = #{orgCallCnt, jdbcType=SMALLINT}                                                                                             "
    })
    void updateCtErrorMngMadeCom(TCtErrorMngMadeCom tCtErrorMngMadeCom);

    @Update({
        "update OP.T_CT_ERROR_MNG_MADE_COM                                                                                                                  ",
        "set     ARRIVAL_DATE = #{arrivalDate, jdbcType=VARCHAR},                                                                             ",
        "        ARRIVAL_TIME = #{arrivalTime, jdbcType=VARCHAR},                                                               ",
        "        COM_MAN_NM = DECODE(CAST(#{comManNm, jdbcType=VARCHAR} AS VARCHAR), NULL, COM_MAN_NM, #{comManNm, jdbcType=VARCHAR}),                                       ",
        "        COM_MAN_TEL_NO = DECODE(CAST(#{comManTelNo, jdbcType=VARCHAR} AS VARCHAR), NULL, COM_MAN_TEL_NO, OP.FC_FN_SECURITY(#{comManTelNo, jdbcType=VARCHAR}, '1')), ",
        "        UPDATE_DATE = SYSDATE,                                                                                                                     ",
        "        UPDATE_UID  = 'ERRmng'                                                                                                                     ",
        "WHERE   AS_ACPT_date = #{asAcptDate, jdbcType=VARCHAR}                                                                                             ",
        "  AND   ORG_CD = #{orgCd, jdbcType=VARCHAR}                                                                                                        ",
        "  AND   BRANCH_CD = #{branchCd, jdbcType=VARCHAR}                                                                                                  ",
        "  AND   MAC_NO = #{macNo, jdbcType=VARCHAR}                                                                                                        ",
        "  AND   ORG_CALL_CNT = #{orgCallCnt, jdbcType=SMALLINT}                                                                                             "
    })
    void updateCtErrorMngMadeCom2(TCtErrorMngMadeCom tCtErrorMngMadeCom);

    @Update({
        "update OP.T_CT_ERROR_MNG_MADE_COM                                                                                                                  ",
        "set     FINISH_DATE = #{finishDate, jdbcType=VARCHAR},                                                                                             ",
        "        FINISH_TIME = #{finishTime, jdbcType=VARCHAR},                                                                                             ",
        "        FINISH_STATUS = #{finishStatus, jdbcType=VARCHAR},                                                                                         ",
        "        COM_MAN_NM  = DECODE(CAST(#{comManNm, jdbcType=VARCHAR} AS VARCHAR), NULL, COM_MAN_NM, #{comManNm, jdbcType=VARCHAR}),                                      ",
        "        COM_MAN_TEL_NO = DECODE(CAST(#{comManTelNo, jdbcType=VARCHAR} AS VARCHAR), NULL, COM_MAN_TEL_NO, OP.FC_FN_SECURITY(#{comManTelNo, jdbcType=VARCHAR}, '1')), ",
        "        ORG_MSG     = #{orgMsg, jdbcType=VARCHAR},                                                                                                 ",
        "        UPDATE_DATE = SYSDATE,                                                                                                                     ",
        "        UPDATE_UID  = 'ERRmng'                                                                                                                     ",
        "WHERE   AS_ACPT_date = #{asAcptDate, jdbcType=VARCHAR}                                                                                             ",
        "  AND   ORG_CD = #{orgCd, jdbcType=VARCHAR}                                                                                                        ",
        "  AND   BRANCH_CD = #{branchCd, jdbcType=VARCHAR}                                                                                                  ",
        "  AND   MAC_NO = #{macNo, jdbcType=VARCHAR}                                                                                                        ",
        "  AND   ORG_CALL_CNT = #{orgCallCnt, jdbcType=SMALLINT}                                                                                             "
    })
    void updateCtErrorMngMadeCom3(TCtErrorMngMadeCom tCtErrorMngMadeCom);

    @Update({
        "UPDATE  OP.T_CT_ERROR_MNG_MADE_COM SET                             ",
        "        ORG_MSG = ORG_MSG || #{orgMsg, jdbcType=VARCHAR},          ",
        "        CHANGE_SEND_YN = '1',                                      ",
        "        UPDATE_DATE = SYSDATE,                                     ",
        "        UPDATE_UID = 'ERRmng'                                      ",
        "WHERE   TRANS_DATE          = #{transDate, jdbcType=VARCHAR}       ",
        "AND     rtrim(ORG_MSG_NO)   = rtrim(#{orgMsgNo, jdbcType=VARCHAR}) ",
        "AND     ORG_CALL_CNT        = #{orgCallCnt, jdbcType=SMALLINT}      "
    })
    int updateCtErrorMngMadeCom4(TCtErrorMngMadeCom tCtErrorMngMadeCom);

    @Select({
        "SELECT  CO1.CD_NM1 as arrival_nm                                               ",
        "FROM    OP.T_CT_ERROR_MNG MNG,                                                 ",
        "        OP.T_CM_COMMON     CO1                                                 ",
        "where   MNG.CREATE_DATE = TO_NUMBER(rtrim(#{createDate, jdbcType=VARCHAR}))    ",
        "and     MNG.ERROR_NO    = rtrim(#{errorNo, jdbcType=VARCHAR})                  ",
        "and     CO1.large_cd = '2301'                                                  ",
        "and     CO1.small_cd = MNG.recv_place                                          "
    })
    String getArrivalNm(@Param("createDate") String createDate, @Param("errorNo") String errorNo);


    @Select({
        "select op.f_get_nice_branch_cd(#{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{orgSiteCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) from dual"
    })
    String fGetNiceJijumCd(@Param("orgCd") String orgCd, @Param("branchCd") String branchCd, @Param("orgSiteCd") String orgSiteCd, @Param("macNo") String macNo);

    @Select({
        "select op.FC_GET_MAP_SITE_CD(#{cType, jdbcType=VARCHAR}, #{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{siteCd, jdbcType=VARCHAR}) from dual"
    })
    String fcGetMapSiteCd(@Param("cType") String cType, @Param("orgCd") String orgCd, @Param("branchCd") String branchCd, @Param("siteCd") String siteCd);

    @Select({
        "SELECT  lpad(nvl(MAX(trans_seq_no), '0'), 7, '0')      ",
        "FROM    OP.T_IF_DATA_LOG                               ",
        "WHERE   ORG_CD = #{orgCd, jdbcType=VARCHAR}            ",
        "AND     TRANS_DATE  = #{transDate, jdbcType=VARCHAR}   ",
        "AND     TRANS_TYPE = 'QS'                              "
    })
    String getMaxTransSeqNo(@Param("orgCd") String orgCd, @Param("transDate") String transDate);

    @Select({
        "select op.f_get_org_branch_cd(#{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{siteCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) from dual"
    })
    String fGetOrgBranchCd(@Param("orgCd") String orgCd, @Param("branchCd") String branchCd, @Param("siteCd") String siteCd, @Param("macNo") String macNo);

    @Select({
        "select op.f_get_org_site_cd(#{orgCd, jdbcType=VARCHAR}, #{branchCd, jdbcType=VARCHAR}, #{siteCd, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}) from dual"
    })
    String fGetOrgSiteCd(@Param("orgCd") String orgCd, @Param("branchCd") String branchCd, @Param("siteCd") String siteCd, @Param("macNo") String macNo);

    @Select({
        "SELECT  accept_time, member_id                                                                                     ",
        "FROM    OP.T_CM_CHECK_MASTER                                                                                       ",
        "WHERE   org_cd      = #{orgCd, jdbcType=VARCHAR}                                                                   ",
        "and     branch_cd    = #{branchCd, jdbcType=VARCHAR}                                                                ",
        "and     site_cd     = #{siteCd, jdbcType=VARCHAR}                                                                  ",
        "and     accept_date = RTRIM(#{acceptDate, jdbcType=VARCHAR})                                                       ",
        "and     accept_date||accept_time <= #{acceptDate, jdbcType=VARCHAR}||substr(#{acceptTime, jdbcType=VARCHAR},1,4)   ",
        "and     arrival_time is null                                                                                       ",
        "and     complete_time is null                                                                                      ",
        "and     start_time is not null                                                                                     "
    })
    @Results({
        @Result(column="accept_time", property="acceptTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.VARCHAR)
    })
    List<TCmCheckMaster> selectCmCheckMaster(TCmCheckMaster tCmCheckMaster);

    @Select({
        "SELECT  accept_time, member_id                                                                                     ",
        "FROM    OP.T_CM_CHECK_MASTER                                                                                       ",
        "WHERE   org_cd      = #{orgCd, jdbcType=VARCHAR}                                                                   ",
        "and     branch_cd   = #{branchCd, jdbcType=VARCHAR}                                                                ",
        "and     site_cd     = #{siteCd, jdbcType=VARCHAR}                                                                  ",
        "and     accept_date = RTRIM(#{acceptDate, jdbcType=VARCHAR})                                                       ",
        "and     accept_date||accept_time <= #{acceptDate, jdbcType=VARCHAR}||substr(#{acceptTime, jdbcType=VARCHAR},1,4)   ",
        "and     complete_time is null                                                                                      ",
        "and     start_time is not null                                                                                     "
    })
    @Results({
        @Result(column="accept_time", property="acceptTime", jdbcType=JdbcType.VARCHAR),
        @Result(column="member_id", property="memberId", jdbcType=JdbcType.VARCHAR)
    })
    List<TCmCheckMaster> selectCmCheckMaster2(TCmCheckMaster tCmCheckMaster);

    @Select({
        "select  USER_IDX                                           ",
        "from (select user_idx                                      ",
        "        from tb_phs_user                                   ",
        "       where user_number = #{memberId, jdbcType=VARCHAR}   ",
        "         and status = 'active'                             ",
        "       order by update_date desc)                          ",
        "where rownum = 1                                           "
    })
    String getUserIdx(@Param("memberId") String memberId);

    @Select({
        "SELECT OP.SEQ_PHS_PUSH_MESSAGES.NEXTVAL AS PUSH_IDX",
        "FROM DUAL"
    })
    @Results({
        @Result(column="PUSH_IDX", property="pushIdx", jdbcType=JdbcType.VARCHAR)
    })
    TbPhsPushMessages getSeqPhsPushMessages();

    @Select({
        "select * from OP.T_CT_ERROR_MNG                                                                                        ",
        "where ORG_CD  = #{orgCd, jdbcType=VARCHAR}                                                                             ",
        "and branch_cd    = #{branchCd, jdbcType=VARCHAR}                                                                       ",
        "and site_cd       = #{siteCd, jdbcType=VARCHAR}                                                                        ",
        "and create_date >= TO_NUMBER(TO_CHAR( SYSDATE-10, 'YYYYMMDD'))                                                         ",
        "and send_time is NOT NULL                                                                                              ",
        "and nvl( send_status, '0' ) = '2'                                                                                      ",
        "and nvl(error_status, '0') not in ( '7000', '1011', '3011', '9011')                                                    ",
        "and nvl(error_status, '0') <> '2000'                                                                                   ",
        "and nvl(as_medium, '0000') = '0000'                                                                                    ",
        "and error_cd <> 'AFTMNG'                                                                                               ",
        "and     lock_time IS NULL                                                                                              ",
        "and unlock_time IS NOT NULL                                                                                            ",
        "and unlock_date||unlock_time < RTRIM(#{unlockDate, jdbcType=VARCHAR}    ) || RTRIM(#{unlockTime, jdbcType=VARCHAR})    "
    })
    @Results({
        @Result(column="ERROR_NO"               , property="errorNo"           , jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_DATE"            , property="createDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME"            , property="createTime"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CD"                 , property="orgCd"             , jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_CD"              , property="branchCd"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_NO"                 , property="macNo"             , jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_CD"                , property="siteCd"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_CD"               , property="errorCd"           , jdbcType=JdbcType.VARCHAR),
        @Result(column="MADE_ERR_CD"            , property="madeErrCd"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="MSG"                    , property="msg"               , jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_STATUS"           , property="errorStatus"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_YN"                , property="sendYn"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_MSG_NO"             , property="orgMsgNo"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="CURRENT_DATE"           , property="currentDate"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="CURRENT_UID"            , property="currentUid"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FORMAT_TYPE"            , property="formatType"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANS_DATE"             , property="transDate"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="MID_ERROR_CD"           , property="midErrorCd"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="GROUP_ERROR_CD"         , property="groupErrorCd"      , jdbcType=JdbcType.VARCHAR),
        @Result(column="ATM_STATE"              , property="atmState"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="AS_MEDIUM"              , property="asMedium"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_MSG"                , property="orgMsg"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_YN"            , property="orgSendYn"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPT_CD"                , property="deptCd"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD"              , property="officeCd"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="TEAM_CD"                , property="teamCd"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEC"                    , property="sec"               , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_TEL"           , property="orgCustTel"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_NM"            , property="orgCustNm"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_MSG"           , property="orgCustMsg"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_RECV_YN"       , property="orgCustRecvYn"     , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_START_TIME"        , property="sendStartTime"     , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_FINISH_TIME"       , property="sendFinishTime"    , jdbcType=JdbcType.VARCHAR),
        @Result(column="UNLOCK_DATE"            , property="unlockDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="UNLOCK_TIME"            , property="unlockTime"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_ERR_SEND_YN"        , property="orgErrSendYn"      , jdbcType=JdbcType.VARCHAR),
        @Result(column="CONFLICT_YN"            , property="conflictYn"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_STATUS"            , property="workStatus"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_COUNT"             , property="sendCount"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK"                 , property="remark"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SITE_CD"            , property="orgSiteCd"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCK_DATE"              , property="lockDate"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCK_TIME"              , property="lockTime"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="REAL_ERROR_CD"          , property="realErrorCd"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="REG_DT"                 , property="regDt"             , jdbcType=JdbcType.VARCHAR),
        @Result(column="REG_ID"                 , property="regId"             , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CALL_CNT"           , property="orgCallCnt"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="CRT_NO"                 , property="crtNo"             , jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE"            , property="updateDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_UID"             , property="updateUid"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_TYPE"              , property="sendType"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_DATE"              , property="sendDate"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_TIME"              , property="sendTime"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_NM"                , property="sendNm"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_UID"               , property="sendUid"           , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_CHECK_YN"          , property="sendCheckYn"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_CHECK_DATETIME"    , property="sendCheckDatetime" , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_PLAN_DATETIME"     , property="sendPlanDatetime"  , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_PLAN_NM"           , property="sendPlanNm"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_PLAN_UID"          , property="sendPlanUid"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_STATUS"            , property="sendStatus"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_TOOL"              , property="sendTool"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_SMS_STATUS"        , property="sendSmsStatus"     , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_USER_TYPE"          , property="orgUserType"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_USER_NM"            , property="orgUserNm"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PLACE"             , property="recvPlace"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_USER_NM"           , property="recvUserNm"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_USER_UID"          , property="recvUserUid"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_TELE_NO"           , property="recvTeleNo"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_DATE"              , property="recvDate"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_TIME"              , property="recvTime"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PDA_DATE"          , property="recvPdaDate"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PDA_TIME"          , property="recvPdaTime"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_XPOS"              , property="recvXpos"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_YPOS"              , property="recvYpos"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_SIDO"              , property="recvSido"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_GUGUN"             , property="recvGugun"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_DONG"              , property="recvDong"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_EST_DATE"       , property="arrivalEstDate"    , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_EST_TIME"       , property="arrivalEstTime"    , jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCEPT_DATE"            , property="acceptDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCEPT_TIME"            , property="acceptTime"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCEPT_NM"              , property="acceptNm"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCEPT_UID"             , property="acceptUid"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="REPAIR_DATE"            , property="repairDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="REPAIR_TIME"            , property="repairTime"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_DATE"            , property="finishDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_TIME"            , property="finishTime"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_PDA_DATE"        , property="finishPdaDate"     , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_PDA_TIME"        , property="finishPdaTime"     , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_XPOS"            , property="finishXpos"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_YPOS"            , property="finishYpos"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_SIDO"            , property="finishSido"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_GUGUN"           , property="finishGugun"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_DONG"            , property="finishDong"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_TYPE"            , property="finishType"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_NM"              , property="finishNm"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_UID"             , property="finishUid"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_DATE"           , property="arrivalDate"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_TIME"           , property="arrivalTime"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_PDA_DATE"       , property="arrivalPdaDate"    , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_PDA_TIME"       , property="arrivalPdaTime"    , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_XPOS"           , property="arrivalXpos"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_YPOS"           , property="arrivalYpos"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_SIDO"           , property="arrivalSido"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_GUGUN"          , property="arrivalGugun"      , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_DONG"           , property="arrivalDong"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_TYPE"           , property="arrivalType"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_UID"            , property="arrivalUid"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_NM"             , property="arrivalNm"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_YN"               , property="closeYn"           , jdbcType=JdbcType.VARCHAR),
        @Result(column="IVR_YN"                 , property="ivrYn"             , jdbcType=JdbcType.VARCHAR)
    })
    List<TCtErrorMng> selectCtErrorMng(TCtErrorMng tCtErrorMng);

    @Select({
        "select * from OP.T_CT_ERROR_MNG                                                                                        ",
        "where ORG_CD  = #{orgCd, jdbcType=VARCHAR}                                                                             ",
        "and branch_cd    = #{branchCd, jdbcType=VARCHAR}                                                                       ",
        "and site_cd       = #{siteCd, jdbcType=VARCHAR}                                                                        ",
        "and create_date >= TO_NUMBER(TO_CHAR( SYSDATE-10, 'YYYYMMDD'))                                                         ",
        "and send_time is NOT NULL                                                                                              ",
        "and nvl( send_status, '0' ) = '2'                                                                                      ",
        "and nvl(error_status, '0') not in ( '7000', '1011', '3011', '9011')                                                    ",
        "and nvl(error_status, '0') <> '2000'                                                                                   ",
        "and nvl(as_medium, '0000') = '0000'                                                                                    ",
        "and error_cd <> 'AFTMNG'                                                                                               ",
        "and unlock_time IS NULL                                                                                              ",
        "and ( arrival_time is null OR arrival_type is null )    "
    })
    @Results({
        @Result(column="ERROR_NO"               , property="errorNo"           , jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_DATE"            , property="createDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME"            , property="createTime"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CD"                 , property="orgCd"             , jdbcType=JdbcType.VARCHAR),
        @Result(column="BRANCH_CD"              , property="branchCd"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="MAC_NO"                 , property="macNo"             , jdbcType=JdbcType.VARCHAR),
        @Result(column="SITE_CD"                , property="siteCd"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_CD"               , property="errorCd"           , jdbcType=JdbcType.VARCHAR),
        @Result(column="MADE_ERR_CD"            , property="madeErrCd"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="MSG"                    , property="msg"               , jdbcType=JdbcType.VARCHAR),
        @Result(column="ERROR_STATUS"           , property="errorStatus"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_YN"                , property="sendYn"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_MSG_NO"             , property="orgMsgNo"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="CURRENT_DATE"           , property="currentDate"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="CURRENT_UID"            , property="currentUid"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FORMAT_TYPE"            , property="formatType"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="TRANS_DATE"             , property="transDate"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="MID_ERROR_CD"           , property="midErrorCd"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="GROUP_ERROR_CD"         , property="groupErrorCd"      , jdbcType=JdbcType.VARCHAR),
        @Result(column="ATM_STATE"              , property="atmState"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="AS_MEDIUM"              , property="asMedium"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_MSG"                , property="orgMsg"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SEND_YN"            , property="orgSendYn"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="DEPT_CD"                , property="deptCd"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="OFFICE_CD"              , property="officeCd"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="TEAM_CD"                , property="teamCd"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEC"                    , property="sec"               , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_TEL"           , property="orgCustTel"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_NM"            , property="orgCustNm"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_MSG"           , property="orgCustMsg"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CUST_RECV_YN"       , property="orgCustRecvYn"     , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_START_TIME"        , property="sendStartTime"     , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_FINISH_TIME"       , property="sendFinishTime"    , jdbcType=JdbcType.VARCHAR),
        @Result(column="UNLOCK_DATE"            , property="unlockDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="UNLOCK_TIME"            , property="unlockTime"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_ERR_SEND_YN"        , property="orgErrSendYn"      , jdbcType=JdbcType.VARCHAR),
        @Result(column="CONFLICT_YN"            , property="conflictYn"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="WORK_STATUS"            , property="workStatus"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_COUNT"             , property="sendCount"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK"                 , property="remark"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_SITE_CD"            , property="orgSiteCd"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCK_DATE"              , property="lockDate"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="LOCK_TIME"              , property="lockTime"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="REAL_ERROR_CD"          , property="realErrorCd"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="REG_DT"                 , property="regDt"             , jdbcType=JdbcType.VARCHAR),
        @Result(column="REG_ID"                 , property="regId"             , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CALL_CNT"           , property="orgCallCnt"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="CRT_NO"                 , property="crtNo"             , jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_DATE"            , property="updateDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="UPDATE_UID"             , property="updateUid"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_TYPE"              , property="sendType"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_DATE"              , property="sendDate"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_TIME"              , property="sendTime"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_NM"                , property="sendNm"            , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_UID"               , property="sendUid"           , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_CHECK_YN"          , property="sendCheckYn"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_CHECK_DATETIME"    , property="sendCheckDatetime" , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_PLAN_DATETIME"     , property="sendPlanDatetime"  , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_PLAN_NM"           , property="sendPlanNm"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_PLAN_UID"          , property="sendPlanUid"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_STATUS"            , property="sendStatus"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_TOOL"              , property="sendTool"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="SEND_SMS_STATUS"        , property="sendSmsStatus"     , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_USER_TYPE"          , property="orgUserType"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_USER_NM"            , property="orgUserNm"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PLACE"             , property="recvPlace"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_USER_NM"           , property="recvUserNm"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_USER_UID"          , property="recvUserUid"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_TELE_NO"           , property="recvTeleNo"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_DATE"              , property="recvDate"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_TIME"              , property="recvTime"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PDA_DATE"          , property="recvPdaDate"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_PDA_TIME"          , property="recvPdaTime"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_XPOS"              , property="recvXpos"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_YPOS"              , property="recvYpos"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_SIDO"              , property="recvSido"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_GUGUN"             , property="recvGugun"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="RECV_DONG"              , property="recvDong"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_EST_DATE"       , property="arrivalEstDate"    , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_EST_TIME"       , property="arrivalEstTime"    , jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCEPT_DATE"            , property="acceptDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCEPT_TIME"            , property="acceptTime"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCEPT_NM"              , property="acceptNm"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="ACCEPT_UID"             , property="acceptUid"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="REPAIR_DATE"            , property="repairDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="REPAIR_TIME"            , property="repairTime"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_DATE"            , property="finishDate"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_TIME"            , property="finishTime"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_PDA_DATE"        , property="finishPdaDate"     , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_PDA_TIME"        , property="finishPdaTime"     , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_XPOS"            , property="finishXpos"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_YPOS"            , property="finishYpos"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_SIDO"            , property="finishSido"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_GUGUN"           , property="finishGugun"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_DONG"            , property="finishDong"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_TYPE"            , property="finishType"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_NM"              , property="finishNm"          , jdbcType=JdbcType.VARCHAR),
        @Result(column="FINISH_UID"             , property="finishUid"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_DATE"           , property="arrivalDate"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_TIME"           , property="arrivalTime"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_PDA_DATE"       , property="arrivalPdaDate"    , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_PDA_TIME"       , property="arrivalPdaTime"    , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_XPOS"           , property="arrivalXpos"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_YPOS"           , property="arrivalYpos"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_SIDO"           , property="arrivalSido"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_GUGUN"          , property="arrivalGugun"      , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_DONG"           , property="arrivalDong"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_TYPE"           , property="arrivalType"       , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_UID"            , property="arrivalUid"        , jdbcType=JdbcType.VARCHAR),
        @Result(column="ARRIVAL_NM"             , property="arrivalNm"         , jdbcType=JdbcType.VARCHAR),
        @Result(column="CLOSE_YN"               , property="closeYn"           , jdbcType=JdbcType.VARCHAR),
        @Result(column="IVR_YN"                 , property="ivrYn"             , jdbcType=JdbcType.VARCHAR)
    })
    List<TCtErrorMng> selectCtErrorMng2(TCtErrorMng tCtErrorMng);

    /**
     * 전문일련번호 채번
     *
     * @author KDJ, on Tue Oct 21 09:08:00 KST 2014
     */
    @Select({
        "select op.f_tranfer from dual"
    })
    String fGeTransSeqNo();

}

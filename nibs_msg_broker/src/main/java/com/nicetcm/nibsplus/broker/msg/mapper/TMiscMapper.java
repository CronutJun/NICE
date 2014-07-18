package com.nicetcm.nibsplus.broker.msg.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
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
    TMisc getNiceBranchCd( TCtErrorMng cond );

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
        @Result(column="hIS_CLOSE", property="hisClose", jdbcType=JdbcType.VARCHAR),
    })
    CmCash selectCountCmCash(CmCash cmCash);
}

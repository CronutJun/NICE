package com.nicetcm.nibsplus.broker.msg.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;

import com.nicetcm.nibsplus.broker.msg.model.FnMacClose;
import com.nicetcm.nibsplus.broker.msg.model.IfCashInsert;
import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;
import com.nicetcm.nibsplus.broker.msg.services.In03101110Impl.IfCashInsertEmart;

public interface StoredProcMapper {

    @Select({
        "CALL op.sp_if_SendSMS( #{telNo, mode=IN, jdbcType=VARCHAR}, #{sendMsg, mode=IN, jdbcType=VARCHAR} )"
    })
    @Options( statementType = StatementType.CALLABLE )
    void sendSMS(TMisc cond);

    @Select({
        "CALL op.sp_if_SendSMSMacInfo( #{orgCd, mode=IN, jdbcType=VARCHAR}, #{branchCd, mode=IN, jdbcType=VARCHAR},",
        "                         #{orgSiteCd, mode=IN, jdbcType=VARCHAR}, #{macNo, mode=IN, jdbcType=VARCHAR}, 'EM')"
    })
    @Options( statementType = StatementType.CALLABLE )
    void SendSMSMacInfo(TMacInfo cond);

    @Select({
        "CALL OP.SP_IF_SENDSMSTRANCNTMISMATCH( #{sendMsg,  mode=IN, jdbcType=VARCHAR}, ",
        "                                      #{sendMode, mode=IN, jdbcType=FLOAT} )"
    })
    @Options( statementType = StatementType.CALLABLE )
    void spIfSendSMSTranCntMismatch( TMisc cond );

    @Select({
        "CALL op.test_if( #{orgCd, jdbcType=VARCHAR, mode=IN}, #{branchCd, jdbcType=VARCHAR, mode=IN},",
        "                         #{orgSiteCd, jdbcType=VARCHAR, mode=IN}, 'EM', #{macNo, jdbcType=VARCHAR, mode=OUT})"
    })
    @Options(statementType=StatementType.CALLABLE)
    void test(TMacInfo cond);

    @Select({
        "CALL op.SP_IF_GetPreAmt( #{hCashType, mode=IN ,  jdbcType=VARCHAR},",
                                      "#{inqDate  , mode=IN ,  jdbcType=VARCHAR},",
                                      "#{orgCd    , mode=IN ,  jdbcType=VARCHAR},",
                                      "#{branchCd , mode=IN ,  jdbcType=VARCHAR},",
                                      "#{macNo    , mode=IN ,  jdbcType=VARCHAR},",
                                      "#{hRtnVal  , mode=OUT,  jdbcType=DECIMAL},",
                                      "#{hRtnMsg  , mode=OUT,  jdbcType=VARCHAR},",
                                      "#{hPreAmt  , mode=OUT,  jdbcType=DECIMAL}",
        ")"
    })
    @Options(statementType=StatementType.CALLABLE)
    void spIfGetpreamt(TMisc tMisc);

    /**
     *
     * sp_if_CashInsert_EMART
     * <pre>
     * 이마트일경우 다른 테이블 이용
     * </pre>
     *
     * @param ifCashinsertEmart
     */
    @Select({
        "CALL op.sp_if_CashInsert_EMART( ",
            "#{nUpdateCheckYN   , mode=IN, jdbcType=VARCHAR},",
            "#{pOrgCd           , mode=IN, jdbcType=VARCHAR},",
            "#{pJijumCd         , mode=IN, jdbcType=VARCHAR},",
            "#{pMacNo           , mode=IN, jdbcType=VARCHAR},",
            "#{pCashType        , mode=IN, jdbcType=VARCHAR},",
            "#{pCashDate        , mode=IN, jdbcType=VARCHAR},",
            "#{pCashTime        , mode=IN, jdbcType=VARCHAR},",
            "#{pRemIn10Amt      , mode=IN, jdbcType=DECIMAL},",
            "#{pRemIn50Amt      , mode=IN, jdbcType=DECIMAL},",
            "#{pRemIn100Amt     , mode=IN, jdbcType=DECIMAL},",
            "#{pRemIn500Amt     , mode=IN, jdbcType=DECIMAL},",
            "#{pRemIn1000Amt    , mode=IN, jdbcType=DECIMAL},",
            "#{pRemIn5000Amt    , mode=IN, jdbcType=DECIMAL},",
            "#{pRemIn10000Amt   , mode=IN, jdbcType=DECIMAL},",
            "#{pRemIn50000Amt   , mode=IN, jdbcType=DECIMAL},",
            "#{pRemOut10Amt     , mode=IN, jdbcType=DECIMAL},",
            "#{pRemOut50Amt     , mode=IN, jdbcType=DECIMAL},",
            "#{pRemOut100Amt    , mode=IN, jdbcType=DECIMAL},",
            "#{pRemOut500Amt    , mode=IN, jdbcType=DECIMAL},",
            "#{pRemOut1000Amt   , mode=IN, jdbcType=DECIMAL},",
            "#{pRemOut5000Amt   , mode=IN, jdbcType=DECIMAL},",
            "#{pRemOut10000Amt  , mode=IN, jdbcType=DECIMAL},",
            "#{pRemOut50000Amt  , mode=IN, jdbcType=DECIMAL},",
            "#{vFirstInqYN      , mode=OUT, jdbcType=VARCHAR},",
            "#{vResult          , mode=OUT, jdbcType=VARCHAR},",
            "#{vResultMsg       , mode=OUT, jdbcType=VARCHAR} ",
        ")"
    })
    @Options(statementType=StatementType.CALLABLE)
    void spIfCashinsertEmart(IfCashInsertEmart ifCashInsertEmart);

    /**
     *
     * sp_if_CashInsert
     * <pre>
     * 현재시재의 마감조회 구분이 "1" 이라면 수표 잔액을 마감시재에 저장해 주고
     * 마감조회 후 자동 전송 건이므로 AP로 응답전송하지 않는다.
     * </pre>
     *
     * @param ifCashInsert
     */
    @Select({
        "CALL op.sp_if_CashInsert( ",
            "#{nUpdateCheckYN   ,   mode=IN        , jdbcType=VARCHAR},",
            "#{pOrgCd           ,   mode=IN        , jdbcType=VARCHAR},",
            "#{pJijumCd         ,   mode=IN        , jdbcType=VARCHAR},",
            "#{pMacNo           ,   mode=IN        , jdbcType=VARCHAR},",
            "#{pCashType        ,   mode=IN        , jdbcType=VARCHAR},",
            "#{pCashDate        ,   mode=IN        , jdbcType=VARCHAR},",
            "#{pCashTime        ,   mode=IN        , jdbcType=VARCHAR},",
            "#{pChargeAmt       ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pTotInAmt        ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pTotOutAmt       ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pMoneyInAmt      ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pMoneyOutAmt     ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pCheckInAmt      ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pCheckOutAmt     ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pRemainAmt       ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pInCnt           ,   mode=IN        , jdbcType=INTEGER},",
            "#{pOutCnt          ,   mode=IN        , jdbcType=INTEGER},",
            "#{pChkInCnt        ,   mode=IN        , jdbcType=INTEGER},",
            "#{pChkOutCnt       ,   mode=IN        , jdbcType=INTEGER},",
            "#{pAddAmt          ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pCollectAmt      ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pRemainCheckAmt  ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pRemain10Amt     ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pRemain50Amt     ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pRemain100Amt    ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pRemain500Amt    ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pRemain1000Amt   ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pRemain5000Amt   ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pRemain10000Amt  ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pRemain50000Amt  ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pMoneyIn50000Amt ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pMoneyOut50000Amt,   mode=IN        , jdbcType=DECIMAL},",
            "#{pMoneyIn5000Amt  ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pMoneyIn1000Amt  ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pTodayChargeAmt  ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pPreChargeAmt    ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pPreAddAmt       ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pHolyAddAmt      ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pTodayAddAmt     ,   mode=IN        , jdbcType=DECIMAL},",
            "#{pSafeNo          ,   mode=IN        , jdbcType=VARCHAR},",
            "#{vFirstInqYN      ,   mode=OUT       , jdbcType=VARCHAR},",
            "#{vResult          ,   mode=OUT       , jdbcType=VARCHAR},",
            "#{vResultMsg       ,   mode=OUT       , jdbcType=VARCHAR} ",
        ")"
    })
    @Options(statementType=StatementType.CALLABLE)
    void spIfCashinsert(IfCashInsert ifCashInsert);

    @Select({
        "CALL op.sp_fn_macClose( ",
            "#{closeDate   , mode=IN,  jdbcType=VARCHAR},",
            "#{orgCode     , mode=IN,  jdbcType=VARCHAR},",
            "#{jijumCode   , mode=IN,  jdbcType=VARCHAR},",
            "#{macNo       , mode=IN,  jdbcType=VARCHAR},",
            "#{userId      , mode=IN,  jdbcType=VARCHAR},",
            "#{result      , mode=OUT, jdbcType=VARCHAR} ",
        ")"
    })
    @Options(statementType=StatementType.CALLABLE)
    void spFnMacClose(FnMacClose fnMacClose);

    @Select({
        "CALL op.sp_fn_macClose_nh( ",
            "#{closeDate   , mode=IN,  jdbcType=VARCHAR},",
            "#{orgCode     , mode=IN,  jdbcType=VARCHAR},",
            "#{jijumCode   , mode=IN,  jdbcType=VARCHAR},",
            "#{macNo       , mode=IN,  jdbcType=VARCHAR},",
            "#{userId      , mode=IN,  jdbcType=VARCHAR},",
            "#{result      , mode=OUT, jdbcType=VARCHAR} ",
        ")"
    })
    @Options(statementType=StatementType.CALLABLE)
    void spFnMacCloseNh(FnMacClose fnMacClose);

    @Select({
        "CALL op.sp_fn_macClose_emart( ",
            "#{closeDate   , mode=IN,  jdbcType=VARCHAR},",
            "#{orgCode     , mode=IN,  jdbcType=VARCHAR},",
            "#{jijumCode   , mode=IN,  jdbcType=VARCHAR},",
            "#{macNo       , mode=IN,  jdbcType=VARCHAR},",
            "#{closeTime   , mode=IN,  jdbcType=VARCHAR},",
            "#{userId      , mode=IN,  jdbcType=VARCHAR},",
            "#{result      , mode=OUT, jdbcType=VARCHAR} ",
        ")"
    })
    @Options(statementType=StatementType.CALLABLE)
    void spFnMacCloseEmart(FnMacClose fnMacClose);

    @Select({
        "SELECT OP.FC_FN_SECURITY( #{argValue, jdbcType=VARCHAR}, #{argType, jdbcType=VARCHAR} ) AS SECURE_RESULT",
        "FROM   DUAL                                                                                           "
    })
    @Results({
        @Result(column="SECURE_RESULT", property="secureResult", jdbcType=JdbcType.VARCHAR)
    })
    TMisc fcFnSecurity(TMisc cond);

    @Select({
        "SELECT OP.FC_GET_ORNZ_CD_BY_MACNO(#{argType,  jdbcType=VARCHAR},                  ",
        "                                  #{orgCd,    jdbcType=VARCHAR},                  ",
        "                                  #{branchCd, jdbcType=VARCHAR},                  ",
        "                                  #{macNo,    jdbcType=VARCHAR}) AS ORNZ_CD       ",
        "FROM   DUAL                                                                       "
    })
    @Results({
        @Result(column="ORNZ_CD", property="ornzCd", jdbcType=JdbcType.VARCHAR)
    })
    TMisc fcGetOrnzCdByMacNo( TMisc cond );

    @Select({
        "CALL OP.SP_CM_TRANS_SEQNO( #{orgCd,        mode=IN ,  jdbcType=VARCHAR},",
                                   "#{createDate,   mode=IN ,  jdbcType=VARCHAR},",
                                   "#{transSeqNo,   mode=OUT,  jdbcType=VARCHAR}",
        ")"
    })
    @Options(statementType=StatementType.CALLABLE)
    void spCmTransSeqNo(TMisc tMisc);

}

package com.nicetcm.nibsplus.broker.msg.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.mapping.StatementType;

import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;
import com.nicetcm.nibsplus.broker.msg.model.TMisc;
import com.nicetcm.nibsplus.broker.msg.services.In03001110Impl.IfCashinsertEmart;

public interface StoredProcMapper {

    @Select({
        "CALL sp_if_SendSMSMacInfo( #{orgCd, mode=IN, jdbcType=VARCHAR}, #{branchCd, mode=IN, jdbcType=VARCHAR},",
        "                         #{orgSiteCd, mode=INOUT, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}, 'EM')"
    })
    @Options( statementType = StatementType.CALLABLE )
    void SendSMSMacInfo(TMacInfo cond);

    @Select({
        "CALL op.test_if( #{orgCd, jdbcType=VARCHAR, mode=IN}, #{branchCd, jdbcType=VARCHAR, mode=IN},",
        "                         #{orgSiteCd, jdbcType=VARCHAR, mode=IN}, 'EM', #{macNo, jdbcType=VARCHAR, mode=OUT})"
    })
    @Options(statementType=StatementType.CALLABLE)
    void test(TMacInfo cond);


    @Select({
        "CALL op.TEST_SP_IF_GetPreAmt( #{hCashType, mode=IN ,  jdbcType=VARCHAR},",
                                      "#{inqDate  , mode=IN ,  jdbcType=VARCHAR},",
                                      "#{orgCd    , mode=IN ,  jdbcType=VARCHAR},",
                                      "#{branchCd , mode=IN ,  jdbcType=VARCHAR},",
                                      "#{macNo    , mode=IN ,  jdbcType=VARCHAR},",
                                      "#{hRtnVal  , mode=OUT,  jdbcType=VARCHAR},",
                                      "#{hRtnMsg  , mode=OUT,  jdbcType=VARCHAR},",
                                      "#{hPreAmt  , mode=OUT,  jdbcType=VARCHAR}",
        ")"
    })
    @Options(statementType=StatementType.CALLABLE)
    void spIfGetpreamt(TMisc tMisc);

    @Select({
        "CALL op.sp_if_CashInsert_EMART( ",
            "#{nUpdateCheckYN   , mode=IN, jdbcType=VARCHAR},",
            "#{pOrgCd           , mode=IN, jdbcType=VARCHAR},",
            "#{pJijumCd         , mode=IN, jdbcType=VARCHAR},",
            "#{pMacNo           , mode=IN, jdbcType=VARCHAR},",
            "#{pCashType        , mode=IN, jdbcType=VARCHAR},",
            "#{pCashDate        , mode=IN, jdbcType=VARCHAR},",
            "#{pCashTime        , mode=IN, jdbcType=VARCHAR},",
            "#{pRemIn10Amt      , mode=IN, jdbcType=VARCHAR},",
            "#{pRemIn50Amt      , mode=IN, jdbcType=VARCHAR},",
            "#{pRemIn100Amt     , mode=IN, jdbcType=VARCHAR},",
            "#{pRemIn500Amt     , mode=IN, jdbcType=VARCHAR},",
            "#{pRemIn1000Amt    , mode=IN, jdbcType=VARCHAR},",
            "#{pRemIn5000Amt    , mode=IN, jdbcType=VARCHAR},",
            "#{pRemIn10000Amt   , mode=IN, jdbcType=VARCHAR},",
            "#{pRemIn50000Amt   , mode=IN, jdbcType=VARCHAR},",
            "#{pRemOut10Amt     , mode=IN, jdbcType=VARCHAR},",
            "#{pRemOut50Amt     , mode=IN, jdbcType=VARCHAR},",
            "#{pRemOut100Amt    , mode=IN, jdbcType=VARCHAR},",
            "#{pRemOut500Amt    , mode=IN, jdbcType=VARCHAR},",
            "#{pRemOut1000Amt   , mode=IN, jdbcType=VARCHAR},",
            "#{pRemOut5000Amt   , mode=IN, jdbcType=VARCHAR},",
            "#{pRemOut10000Amt  , mode=IN, jdbcType=VARCHAR},",
            "#{pRemOut50000Amt  , mode=IN, jdbcType=VARCHAR},",
            "#{vFirstInqYN      , mode=OUT, jdbcType=VARCHAR},",
            "#{vResult          , mode=OUT, jdbcType=VARCHAR},",
            "#{vResultMsg       , mode=OUT, jdbcType=VARCHAR} ",
        ")"
    })
    @Options(statementType=StatementType.CALLABLE)
    void spIfCashinsertEmart(IfCashinsertEmart ifCashinsertEmart);
}

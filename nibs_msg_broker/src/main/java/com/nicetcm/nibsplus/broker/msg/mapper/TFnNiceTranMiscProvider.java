package com.nicetcm.nibsplus.broker.msg.mapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.model.BNetCalc;

public class TFnNiceTranMiscProvider {

    private static final Logger logger = LoggerFactory.getLogger(TFnNiceTranMiscProvider.class);

    public String selectBNetCalc( BNetCalc cond ) {
        String sql = "";

        /*
         * 국민 BARND 제휴기기의 집계 등을 계산하여 가져옴
         * 국민과 정통부는 당일 실시간 집계가 필요하므로 별도
         */
        if( cond.getOrgCd().equals(MsgBrokerConst.KBST_CODE) ) {
            if ( cond.getMacNo().substring(0, 4).equals("NICE") ) { // 브랜드 제휴기기가 아닌 기기
                sql = "SELECT /*+ INDEX_DESC ( T PK_T_FN_NICE_TRAN) */                                            \n"
                    + "       T.DEAL_DATE,                                                                        \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', 1, 0))                              AS CASH_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', T.DEAL_AMT, 0))                     AS CASH_AMT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', 1, 0), 0))          AS SAME_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', T.DEAL_AMT, 0), 0)) AS SAME_AMT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', 0, 1), 0))          AS DIFF_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', 0, T.DEAL_AMT), 0)) AS DIFF_AMT,       \n"
                    + "       0                                                                AS CASH_CANCEL_CNT,\n"
                    + "       0                                                                AS CASH_CANCEL_AMT,\n"
                    + "       0                                                                AS CASH_SVC_CNT,   \n"
                    + "       0                                                                AS CASH_SVC_AMT    \n"
                    + "FROM   T_FN_NICE_TRAN T                                                                    \n"
                    + "       LEFT JOIN T_CT_NICE_MAC M ON                                                        \n"
                    + "                 T.MAC_NO = M.MAC_NO                                                       \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                         \n"
                    + "AND    (M.JOIN_CD IS NULL OR M.JOIN_CD <> #{orgCd, jdbcType=VARCHAR})                      \n"
                    + "AND    T.NET_ORG_CD = #{orgCd, jdbcType=VARCHAR}                                           \n"
                    + "AND    T.DEAL_STATUS = '0'                                                                 \n"
                    + "AND    (T.DEAL_TYPE = '0' OR T.DEAL_TYPE = '4')                                            \n"
                    + "GROUP BY T.DEAL_DATE                                                                       \n";
            }
            else if( cond.getMacNo().substring(0, 4).equals("BRAN") ) { // 브랜드 제휴기기
                sql = "SELECT T.DEAL_DATE,                                                                        \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', 1, 0))                              AS CASH_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', T.DEAL_AMT, 0))                     AS CASH_AMT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', 1, 0), 0))          AS SAME_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', T.DEAL_AMT, 0), 0)) AS SAME_AMT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', 0, 1), 0))          AS DIFF_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', 0, T.DEAL_AMT), 0)) AS DIFF_AMT,       \n"
                    + "       0                                                                AS CASH_CANCEL_CNT,\n"
                    + "       0                                                                AS CASH_CANCEL_AMT,\n"
                    + "       0                                                                AS CASH_SVC_CNT,   \n"
                    + "       0                                                                AS CASH_SVC_AMT    \n"
                    + "FROM   T_FN_NICE_TRAN T,                                                                   \n"
                    + "       LEFT JOIN T_CT_NICE_MAC M ON                                                        \n"
                    + "                 T.MAC_NO = M.MAC_NO                                                       \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                         \n"
                    + "AND    M.JOIN_CD = #{orgCd, jdbcType=VARCHAR}                                              \n"
                    + "AND    T.NET_ORG_CD = #{orgCd, jdbcType=VARCHAR}                                           \n"
                    + "AND    T.DEAL_STATUS = '0'                                                                 \n"
                    + "AND    (T.DEAL_TYPE = '0' OR T.DEAL_TYPE = '4')                                            \n"
                    + "GROUP BY T.DEAL_DATE                                                                       \n";
            }
            else { //기기별
                sql = "SELECT T.DEAL_DATE,                                                                        \n"
                    + "       T.MAC_NO,                                                                           \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', 1, 0))                              AS CASH_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', T.DEAL_AMT, 0))                     AS CASH_AMT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', 1, 0), 0))          AS SAME_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', T.DEAL_AMT, 0), 0)) AS SAME_AMT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', 0, 1), 0))          AS DIFF_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4',                                                        \n"
                    + "                     DECODE(T.TRANS_ORG_CD, '0004', 0, T.DEAL_AMT), 0)) AS DIFF_AMT,       \n"
                    + "       0                                                                AS CASH_CANCEL_CNT,\n"
                    + "       0                                                                AS CASH_CANCEL_AMT,\n"
                    + "       0                                                                AS CASH_SVC_CNT,   \n"
                    + "       0                                                                AS CASH_SVC_AMT    \n"
                    + "FROM   OP.T_FN_NICE_TRAN T                                                                 \n"
                    + "       LEFT JOIN OP.T_CT_NICE_MAC M ON                                                     \n"
                    + "                 T.MAC_NO = M.MAC_NO                                                       \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                         \n"
                    + "AND    T.MAC_NO = #{macNo, jdbcType=VARCHAR}                                               \n"
                    + "AND    T.NET_ORG_CD = #{orgCd, jdbcType=VARCHAR}                                           \n"
                    + "AND    T.DEAL_STATUS = '0'                                                                 \n"
                    + "AND    (T.DEAL_TYPE = '0' OR T.DEAL_TYPE = '4')                                            \n"
                    + "GROUP BY T.DEAL_DATE, T.MAC_NO                                                             \n";
            }
        }
        /*
         * 제일은행 브랜드제휴 집계  계산하여 가져옴
         */
        /*
         *  deal_status = '0':정상, '1':취소, '2':미완료, '3':거절
         *  deal_type   = '0':출금, '1':입금, '3':조회,   '4':이체
         *
         *  NET_ORG_CD 가 '99'는 공동망 브랜드 제휴는 '23' 계좌이체의 경우 우리은행 개별망을 이용하여 '20'
         */
        else if( cond.getOrgCd().equals(MsgBrokerConst.JL_CODE) ) {
            if( cond.getMacNo().substring(0, 4).equals("BRAN") ) { // 브랜드 제휴기기
                sql = "SELECT T.DEAL_DATE,                                                                                        \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', 1, 0), 0) )          AS CASH_CNT,           \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0), 0) ) AS CASH_AMT,           \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', 1, 0), 0) )          AS CASH_CANCEL_CNT,    \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT, 0), 0) ) AS CASH_CANCEL_AMT,    \n"
                    + "       0                                                                            AS SAME_CNT,           \n"
                    + "       0                                                                            AS SAME_AMT,           \n"
                    + "       0                                                                            AS DIFF_CNT,           \n"
                    + "       0                                                                            AS DIFF_AMT,           \n"
                    + "       0                                                                            AS CASH_SVC_CNT,       \n"
                    + "       0                                                                            AS CASH_SVC_AMT,       \n"
                    + "       0                                                                            AS CASH_IN_CNT,        \n"
                    + "       0                                                                            AS CASH_IN_AMT,        \n"
                    + "       0                                                                            AS CASH_IN_CANCEL_CNT, \n"
                    + "       0                                                                            AS CASH_IN_CANCEL_AMT, \n"
                    + "       0                                                                            AS REAL_TRADE_AMT      \n"
                    + "FROM   OP.T_FN_NICE_TRAN T                                                                                 \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                         \n"
                    + "AND    T.ORG_CD = '0J1'                                                                                    \n"
                    + "AND    T.NET_ORG_CD = '0J1'                                                                                \n"
                    + "AND    (T.DEAL_STATUS = '0' OR T.DEAL_STATUS = '1')                                                        \n"
                    + "AND    (T.DEAL_TYPE = '0' )                                                                                \n"
                    + "GROUP BY T.DEAL_DATE                                                                                       \n";
            }
            else {
                logger.info( ">>>  집계데이터 파악 실패[err-조회조건오류-{}-브랜드 제휴조회(BRAN)만 허용]", cond.getMacNo());
                sql = "SELECT 'ERROR' AS DEAL_DATE,          \n"
                    + "       -2      AS CASH_CNT,           \n"
                    + "       0       AS CASH_AMT,           \n"
                    + "       0       AS CASH_CANCEL_CNT,    \n"
                    + "       0       AS CASH_CANCEL_AMT,    \n"
                    + "       0       AS SAME_CNT,           \n"
                    + "       0       AS SAME_AMT,           \n"
                    + "       0       AS DIFF_CNT,           \n"
                    + "       0       AS DIFF_AMT,           \n"
                    + "       0       AS CASH_SVC_CNT,       \n"
                    + "       0       AS CASH_SVC_AMT,       \n"
                    + "       0       AS CASH_IN_CNT,        \n"
                    + "       0       AS CASH_IN_AMT,        \n"
                    + "       0       AS CASH_IN_CANCEL_CNT, \n"
                    + "       0       AS CASH_IN_CANCEL_AMT, \n"
                    + "       0       AS REAL_TRADE_AMT      \n"
                    + "FROM   DUAL                           \n";
            }
        }
        /*
         * 정통부 BARND 제휴기기의 집계 등을 계산하여 가져옴
         */
        else if( cond.getOrgCd().equals(MsgBrokerConst.JTB2_CODE) ) {
            if ( cond.getMacNo().substring(0, 4).equals("NICE") ) { // 브랜드 제휴기기가 아닌 기기
                sql = "SELECT T.DEAL_DATE,                                                                                                 \n"
                    + "       0                  AS CASH_CNT,                                                                              \n"
                    + "       0                  AS CASH_AMT,                                                                              \n"
                    + "       0                  AS CASH_CANCEL_CNT,                                                                       \n"
                    + "       0                  AS CASH_CANCEL_AMT,                                                                       \n"
                    + "       SUM(T.SAME_CNT)    AS SAME_CNT,                                                                              \n"
                    + "       SUM(T.SAME_AMT)    AS SAME_AMT,                                                                              \n"
                    + "       0                  AS DIFF_CNT,                                                                              \n"
                    + "       0                  AS DIFF_AMT,                                                                              \n"
                    + "       0                  AS CASH_SVC_CNT,                                                                          \n"
                    + "       0                  AS CASH_SVC_AMT,                                                                          \n"
                    + "       SUM(T.CASH_IN_CNT) AS CASH_IN_CNT,                                                                           \n"
                    + "       SUM(T.CASH_IN_AMT) AS CASH_IN_AMT                                                                            \n"
                    + "FROM   OP.T_FN_UNIT_DSUM_ORG T                                                                                      \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                                  \n"
                    + "/* 이체거래만 전송하도록 김희수 요청 20090527 */                                                                    \n"
                    + "/* 이체거래는 개별망으로만 요청되므로 브랜드제휴 기기에서 계좌이체 거래 안됨 차후 바뀌면 희수 머리박음 */           \n"
                    + "/* 20090803 브랜드제휴기기에서 이체거래됨에따라 집계에는 브랜드 제휴기기에서 일어난 건을 제외하고 집계하도록 수정 */\n"
                    + "/* TRAN테이블 집계가 너무 오래걸려서 T_FN_UNIT_DSUM_ORG에 따로 집계넣음. 20121108 최락경 */                         \n"
                    + "AND    T.ORG_CD = '072'                                                                                             \n"
                    + "GROUP BY T.DEAL_DATE                                                                                                \n";
            }
            else if( cond.getMacNo().substring(0, 4).equals("BRAN") ) { // 브랜드 제휴기기
                sql = "SELECT /*+ INDEX_DESC ( T IX_T_FN_NICE_TRAN_02 ) */                                                   \n"
                    + "       T.DEAL_DATE,                                                                                   \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', 1, 0), 0))          AS CASH_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0), 0)) AS CASH_AMT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', 1, 0), 0))          AS CASH_CANCEL_CNT,\n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT, 0), 0)) AS CASH_CANCEL_AMT,\n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.DEAL_STATUS, '0', 1, 0), 0))          AS SAME_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0), 0)) AS SAME_AMT,       \n"
                    + "       0                                                                           AS DIFF_CNT,       \n"
                    + "       0                                                                           AS DIFF_AMT,       \n"
                    + "       0                                                                           AS CASH_SVC_CNT,   \n"
                    + "       0                                                                           AS CASH_SVC_AMT    \n"
                    + "FROM   OP.T_FN_NICE_TRAN T                                                                            \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                    \n"
                    + "AND    T.NET_ORG_CD = '071'                                                                           \n"
                    + "AND    (T.DEAL_STATUS = '0' OR T.DEAL_STATUS = '1')                                                   \n"
                    + "AND    (T.DEAL_TYPE = '0' OR T.DEAL_TYPE = '4')                                                       \n"
                    + "AND    T.BRAND_ORG_CD = '071'                                                                         \n"
                    + "GROUP BY T.DEAL_DATE                                                                                  \n";
            }
            else {
                logger.info( ">>>  집계데이터 파악 실패[err-조회조건오류-{}-브랜드 제휴조회(BRAN)만 허용]", cond.getMacNo());
                sql = "SELECT 'ERROR' AS DEAL_DATE,          \n"
                    + "       -2      AS CASH_CNT,           \n"
                    + "       0       AS CASH_AMT,           \n"
                    + "       0       AS CASH_CANCEL_CNT,    \n"
                    + "       0       AS CASH_CANCEL_AMT,    \n"
                    + "       0       AS SAME_CNT,           \n"
                    + "       0       AS SAME_AMT,           \n"
                    + "       0       AS DIFF_CNT,           \n"
                    + "       0       AS DIFF_AMT,           \n"
                    + "       0       AS CASH_SVC_CNT,       \n"
                    + "       0       AS CASH_SVC_AMT,       \n"
                    + "       0       AS CASH_IN_CNT,        \n"
                    + "       0       AS CASH_IN_AMT,        \n"
                    + "       0       AS CASH_IN_CANCEL_CNT, \n"
                    + "       0       AS CASH_IN_CANCEL_AMT, \n"
                    + "       0       AS REAL_TRADE_AMT      \n"
                    + "FROM   DUAL                           \n";
            }
        }
        /*
         * 정통부 BARND 제휴기기의 집계 등을 계산해 가져옴
         */
        else if( cond.getOrgCd().equals(MsgBrokerConst.DJ_CODE) ) {
            if ( cond.getMacNo().substring(0, 4).equals("NICE") ) { // 개별망 전체 집계
                sql = "SELECT T.DEAL_DATE,                                                                                         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', 1, 0 ), 0 ) )          AS CASH_CNT,          \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0 ), 0 ) ) AS CASH_AMT,          \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', 1, 0 ), 0 ) )          AS CASH_CANCEL_CNT,   \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT, 0 ), 0 ) ) AS CASH_CANCEL_AMT,   \n"
                    + "       0                                                                              AS SAME_CNT,          \n"
                    + "       0                                                                              AS SAME_AMT,          \n"
                    + "       0                                                                              AS DIFF_CNT,          \n"
                    + "       0                                                                              AS DIFF_AMT,          \n"
                    + "       0                                                                              AS CASH_SVC_CNT,      \n"
                    + "       0                                                                              AS CASH_SVC_AMT,      \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '1', DECODE(T.DEAL_STATUS, '0', 1, 0 ), 0 ) )          AS CASH_IN_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '1', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0 ), 0 ) ) AS CASH_IN_AMT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '1', DECODE(T.DEAL_STATUS, '1', 1, 0 ), 0 ) )          AS CASH_IN_CANCEL_CNT,\n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '1', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT, 0 ), 0 ) ) AS CASH_IN_CANCEL_AMT,\n"
                    + "       0 AS REAL_TRADE_AMT                                                                                  \n"
                    + "FROM   T_FN_NICE_TRAN T                                                                                     \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                          \n"
                    + "AND    T.NET_ORG_CD = '0DJ'    /* 브랜드제휴가 아닌기기 */                                                  \n"
                    + "AND    (T.DEAL_STATUS = '0' OR T.DEAL_STATUS = '1')                                                         \n"
                    + "AND    (T.DEAL_TYPE = '0' OR T.DEAL_TYPE = '1' )                                                            \n"
                    + "GROUP BY T.DEAL_DATE                                                                                        \n";
            }
            else if( cond.getMacNo().substring(0, 4).equals("BRAN") ) { // 브랜드 제휴기기
                sql = "SELECT T.DEAL_DATE,                                                                                       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', 1, 0), 0) )          AS CASH_CNT,          \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0), 0) ) AS CASH_AMT,          \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', 1, 0), 0) )          AS CASH_CANCEL_CNT,   \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT, 0), 0) ) AS CASH_CANCEL_AMT,   \n"
                    + "       0                                                                            AS SAME_CNT,          \n"
                    + "       0                                                                            AS SAME_AMT,          \n"
                    + "       0                                                                            AS DIFF_CNT,          \n"
                    + "       0                                                                            AS DIFF_AMT,          \n"
                    + "       0                                                                            AS CASH_SVC_CNT,      \n"
                    + "       0                                                                            AS CASH_SVC_AMT,      \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '1', DECODE(T.DEAL_STATUS, '0', 1, 0), 0) )          AS CASH_IN_CNT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '1', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0), 0) ) AS CASH_IN_AMT,       \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '1', DECODE(T.DEAL_STATUS, '1', 1, 0), 0) )          AS CASH_IN_CANCEL_CNT,\n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '1', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT, 0), 0) ) AS CASH_IN_CANCEL_AMT,\n"
                    + "       0                                                                            AS REAL_TRADE_AMT     \n"
                    + "FROM   T_FN_NICE_TRAN T                                                                                   \n"
                    + "       LEFT JOIN T_CT_NICE_MAC M ON                                                                       \n"
                    + "                 T.MAC_NO = M.MAC_NO                                                                      \n"
                    + "WHERE T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                         \n"
                    + "AND   T.NET_ORG_CD = '0DB'                                                                                \n"
                    + "AND   (T.DEAL_STATUS = '0' OR T.DEAL_STATUS = '1')                                                        \n"
                    + "AND   (T.DEAL_TYPE = '0' OR T.DEAL_TYPE = '1' )                                                           \n"
                    + "GROUP BY T.DEAL_DATE;                                                                                     \n";
            }
            else { //기기별 -  동양종금은 는 기기별 없음
                logger.info( ">>>  집계데이터 파악 실패[err-조회조건오류-{}-브랜드 제휴조회(BRAN)만 허용]", cond.getMacNo());
                sql = "SELECT 'ERROR' AS DEAL_DATE,          \n"
                    + "       -2      AS CASH_CNT,           \n"
                    + "       0       AS CASH_AMT,           \n"
                    + "       0       AS CASH_CANCEL_CNT,    \n"
                    + "       0       AS CASH_CANCEL_AMT,    \n"
                    + "       0       AS SAME_CNT,           \n"
                    + "       0       AS SAME_AMT,           \n"
                    + "       0       AS DIFF_CNT,           \n"
                    + "       0       AS DIFF_AMT,           \n"
                    + "       0       AS CASH_SVC_CNT,       \n"
                    + "       0       AS CASH_SVC_AMT,       \n"
                    + "       0       AS CASH_IN_CNT,        \n"
                    + "       0       AS CASH_IN_AMT,        \n"
                    + "       0       AS CASH_IN_CANCEL_CNT, \n"
                    + "       0       AS CASH_IN_CANCEL_AMT, \n"
                    + "       0       AS REAL_TRADE_AMT      \n"
                    + "FROM   DUAL                           \n";
            }
        }
        /*
         * 대우증권 전체 집계 등을 계산하여 가져옴
         */
        /* deal_status = '0':정상, '1':취소, '2':미완료, '3':거절
         * deal_type   = '0':출금, '1':입금, '3': 조회,  '4':이체
         */
        else if( cond.getOrgCd().equals(MsgBrokerConst.DW_CODE) ) {
            if ( cond.getMacNo().substring(0, 4).equals("TOTA") ) { //  전체 집계
                sql = "SELECT T.DEAL_DATE,                                                                                                              \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', 1, 0) )                                                    AS CASH_CNT,                      \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', T.DEAL_AMT+T.CUST_FEE, 0) )                                AS CASH_AMT,                      \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', 1, 0), 0) )                     AS CASH_CANCEL_CNT,               \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT+T.CUST_FEE, 0), 0) ) AS CASH_CANCEL_AMT,               \n"
                    + "       0                                                                                       AS SAME_CNT,                      \n"
                    + "       0                                                                                       AS SAME_AMT,                      \n"
                    + "       0                                                                                       AS DIFF_CNT,                      \n"
                    + "       0                                                                                       AS DIFF_AMT,                      \n"
                    + "       0                                                                                       AS CASH_SVC_CNT,                  \n"
                    + "       0                                                                                       AS CASH_SVC_AMT,                  \n"
                    + "       0                                                                                       AS CASH_IN_CNT,                   \n"
                    + "       0                                                                                       AS CASH_IN_AMT,                   \n"
                    + "       0                                                                                       AS CASH_IN_CANCEL_CNT,            \n"
                    + "       0                                                                                       AS CASH_IN_CANCEL_AMT,            \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0), 0))             AS REAL_TRADE_AMT /* 실거래금액 */\n"
                    + "FROM   T_FN_NICE_TRAN T                                                                                                          \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                                               \n"
                    + "AND    T.ORG_CD = #{orgCd, jdbcType=VARCHAR}                                                                                     \n"
                    + "AND    (T.DEAL_STATUS = '0' OR T.DEAL_STATUS = '1')                                                                              \n"
                    + "AND    (T.DEAL_TYPE = '0')                                                                                                       \n"
                    + "GROUP BY T.DEAL_DATE                                                                                                             \n";
            }
            else { // 대우증권은 전체조회만 있음
                logger.info( ">>>  집계데이터 파악 실패[err-조회조건오류-{}-브랜드 제휴조회(TOTA)만 허용]", cond.getMacNo());
                sql = "SELECT 'ERROR' AS DEAL_DATE,          \n"
                    + "       -2      AS CASH_CNT,           \n"
                    + "       0       AS CASH_AMT,           \n"
                    + "       0       AS CASH_CANCEL_CNT,    \n"
                    + "       0       AS CASH_CANCEL_AMT,    \n"
                    + "       0       AS SAME_CNT,           \n"
                    + "       0       AS SAME_AMT,           \n"
                    + "       0       AS DIFF_CNT,           \n"
                    + "       0       AS DIFF_AMT,           \n"
                    + "       0       AS CASH_SVC_CNT,       \n"
                    + "       0       AS CASH_SVC_AMT,       \n"
                    + "       0       AS CASH_IN_CNT,        \n"
                    + "       0       AS CASH_IN_AMT,        \n"
                    + "       0       AS CASH_IN_CANCEL_CNT, \n"
                    + "       0       AS CASH_IN_CANCEL_AMT, \n"
                    + "       0       AS REAL_TRADE_AMT      \n"
                    + "FROM   DUAL                           \n";
            }
        }
        /*
         * 미래에셋 집계 등을 계산하여 가져옴
         */
        else if( cond.getOrgCd().equals(MsgBrokerConst.MR_CODE) ) {
            if ( cond.getMacNo().substring(0, 4).equals("TOTA") ) { //  미래에셋 전체 집계
                sql = "SELECT /*+ INDEX_DESC ( T IX_T_FN_NICE_TRAN_02 ) */                                                                   \n"
                    + "        T.DEAL_DATE,                                                                                                  \n"
                    + "        SUM(DECODE(T.DEAL_STATUS, '0', DECODE( T.DEAL_TYPE, '0', 1, 0), 0))                        AS CASH_CNT,       \n"
                    + "        SUM(DECODE(T.DEAL_STATUS, '0', DECODE( T.DEAL_TYPE, '0', T.DEAL_AMT+T.CUST_FEE, 0), 0))    AS CASH_AMT,       \n"
                    + "        SUM(DECODE(T.DEAL_STATUS, '1', DECODE( T.DEAL_TYPE, '0', 1, 0), 0))                        AS CASH_CANCEL_CNT,\n"
                    + "        SUM(DECODE(T.DEAL_STATUS, '1', DECODE( T.DEAL_TYPE, '0', T.DEAL_AMT, 0), 0))               AS CASH_CANCEL_AMT,\n"
                    + "        SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0230', 1, 0), 0))                     AS SAME_CNT,       \n"
                    + "        SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0230', T.DEAL_AMT+T.CUST_FEE, 0), 0)) AS SAME_AMT,       \n"
                    + "        SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0230', 0, 1), 0))                     AS DIFF_CNT,       \n"
                    + "        SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0230', 0, T.DEAL_AMT+T.CUST_FEE), 0)) AS DIFF_AMT,       \n"
                    + "        0                                                                                          AS CASH_SVC_CNT,   \n"
                    + "        0                                                                                          AS CASH_SVC_AMT    \n"
                    + "FROM    T_FN_NICE_TRAN T                                                                                              \n"
                    + "WHERE   T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                                   \n"
                    + "AND     T.ORG_CD = #{orgCd, jdbcType=VARCHAR}                                                                         \n"
                    + "AND     (T.DEAL_STATUS = '0' OR T.DEAL_STATUS = '1')                                                                  \n"
                    + "GROUP BY T.DEAL_DATE                                                                                                  \n";
            }
            else if ( cond.getMacNo().substring(0, 4).equals("BRAN") ) { //  미래에셋은 브랜드 제휴기기 없음
                logger.info( ">>>  집계데이터 파악 실패[err-조회조건오류-{}-브랜드 제휴조회(BRAN)없음]", cond.getMacNo());
                sql = "SELECT 'ERROR' AS DEAL_DATE,          \n"
                    + "       -2      AS CASH_CNT,           \n"
                    + "       0       AS CASH_AMT,           \n"
                    + "       0       AS CASH_CANCEL_CNT,    \n"
                    + "       0       AS CASH_CANCEL_AMT,    \n"
                    + "       0       AS SAME_CNT,           \n"
                    + "       0       AS SAME_AMT,           \n"
                    + "       0       AS DIFF_CNT,           \n"
                    + "       0       AS DIFF_AMT,           \n"
                    + "       0       AS CASH_SVC_CNT,       \n"
                    + "       0       AS CASH_SVC_AMT,       \n"
                    + "       0       AS CASH_IN_CNT,        \n"
                    + "       0       AS CASH_IN_AMT,        \n"
                    + "       0       AS CASH_IN_CANCEL_CNT, \n"
                    + "       0       AS CASH_IN_CANCEL_AMT, \n"
                    + "       0       AS REAL_TRADE_AMT      \n"
                    + "FROM   DUAL                           \n";
            }
            else {
                logger.info( ">>>  집계데이터 파악 실패[err-조회조건오류-{}-브랜드 제휴조회(기기별 없음)]", cond.getMacNo());
                sql = "SELECT 'ERROR' AS DEAL_DATE,          \n"
                    + "       -2      AS CASH_CNT,           \n"
                    + "       0       AS CASH_AMT,           \n"
                    + "       0       AS CASH_CANCEL_CNT,    \n"
                    + "       0       AS CASH_CANCEL_AMT,    \n"
                    + "       0       AS SAME_CNT,           \n"
                    + "       0       AS SAME_AMT,           \n"
                    + "       0       AS DIFF_CNT,           \n"
                    + "       0       AS DIFF_AMT,           \n"
                    + "       0       AS CASH_SVC_CNT,       \n"
                    + "       0       AS CASH_SVC_AMT,       \n"
                    + "       0       AS CASH_IN_CNT,        \n"
                    + "       0       AS CASH_IN_AMT,        \n"
                    + "       0       AS CASH_IN_CANCEL_CNT, \n"
                    + "       0       AS CASH_IN_CANCEL_AMT, \n"
                    + "       0       AS REAL_TRADE_AMT      \n"
                    + "FROM   DUAL                           \n";
            }
        }
        /*
         * 기업은행 브랜드제휴 집계  계산하여 가져옴
         */
        else if( cond.getOrgCd().equals(MsgBrokerConst.BK_CODE) ) {
            if ( cond.getMacNo().substring(0, 4).equals("BRAN") ) { //  브랜드 제휴 집계
                sql = "SELECT  T.DEAL_DATE,                                                                                                       \n"
                    + "        SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', 1, 0), 0))          AS CASH_CNT,                           \n"
                    + "        SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0), 0)) AS CASH_AMT,                           \n"
                    + "        SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', 1, 0), 0))          AS CASH_CANCEL_CNT,                    \n"
                    + "        SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT, 0), 0)) AS CASH_CANCEL_AMT,                    \n"
                    + "        0                                                                           AS SAME_CNT,                           \n"
                    + "        0                                                                           AS SAME_AMT,                           \n"
                    + "        0                                                                           AS DIFF_CNT,                           \n"
                    + "        0                                                                           AS DIFF_AMT,                           \n"
                    + "        0                                                                           AS CASH_SVC_CNT,                       \n"
                    + "        0                                                                           AS CASH_SVC_AMT,                       \n"
                    + "        SUM(DECODE(T.DEAL_STATUS, '0', 1, 0))                                       AS CASH_IN_CNT,        /*CUST_FEE_CNT*/\n"
                    + "        SUM(DECODE(T.DEAL_STATUS, '0', T.CUST_FEE, 0))                              AS CASH_IN_AMT,        /*CUST_FEE_AMT*/\n"
                    + "        SUM(DECODE(T.DEAL_STATUS, '0', 1, 0))                                       AS CASH_IN_CANCEL_CNT, /*BANK_FEE_CNT*/\n"
                    + "        SUM(DECODE(T.DEAL_STATUS, '0', T.BANK_FEE, 0))                              AS CASH_IN_CANCEL_AMT, /*BANK_FEE_AMT*/\n"
                    + "        0 AS REAL_TRADE_AMT                                                                                                \n"
                    + "FROM    T_FN_NICE_TRAN T                                                                                                   \n"
                    + "WHERE   T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                                        \n"
                    + "AND     T.ORG_CD = '0BK'                                                                                                   \n"
                    + "AND     T.NET_ORG_CD = '0BK'                                                                                               \n"
                    + "AND     (T.DEAL_STATUS = '0' OR T.DEAL_STATUS = '1')                                                                       \n"
                    + "AND     (T.DEAL_TYPE = '0')                                                                                                \n"
                    + "GROUP BY T.DEAL_DATE                                                                                                       \n";
            }
            else { // 기업 브랜드는 브랜드 제휴조회만 있음
                logger.info( ">>>  집계데이터 파악 실패[err-조회조건오류-{}-브랜드 제휴조회(BRAN)없음]", cond.getMacNo());
                sql = "SELECT 'ERROR' AS DEAL_DATE,          \n"
                    + "       -2      AS CASH_CNT,           \n"
                    + "       0       AS CASH_AMT,           \n"
                    + "       0       AS CASH_CANCEL_CNT,    \n"
                    + "       0       AS CASH_CANCEL_AMT,    \n"
                    + "       0       AS SAME_CNT,           \n"
                    + "       0       AS SAME_AMT,           \n"
                    + "       0       AS DIFF_CNT,           \n"
                    + "       0       AS DIFF_AMT,           \n"
                    + "       0       AS CASH_SVC_CNT,       \n"
                    + "       0       AS CASH_SVC_AMT,       \n"
                    + "       0       AS CASH_IN_CNT,        \n"
                    + "       0       AS CASH_IN_AMT,        \n"
                    + "       0       AS CASH_IN_CANCEL_CNT, \n"
                    + "       0       AS CASH_IN_CANCEL_AMT, \n"
                    + "       0       AS REAL_TRADE_AMT      \n"
                    + "FROM   DUAL                           \n";
            }
        }
        /*
         * 우리투자증권 집계 등을 계산하여 가져옴
         */
        else if( cond.getOrgCd().equals(MsgBrokerConst.WM_CODE) ) {
            if ( cond.getMacNo().substring(0, 4).equals("TOTA") ) { //  우리투자증권 전체 집계
                sql = "SELECT /*+ INDEX_DESC ( T IX_T_FN_NICE_TRAN_02 ) */                                                         \n"
                    + "       T.DEAL_DATE,                                                                                         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', 1, 0), 0))              AS CASH_CNT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0), 0))     AS CASH_AMT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', 1, 0), 0))              AS CASH_CANCEL_CNT,  \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT, 0), 0))     AS CASH_CANCEL_AMT,  \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 1, 0), 0))          AS SAME_CNT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', T.DEAL_AMT, 0), 0)) AS SAME_AMT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 0, 1), 0))          AS DIFF_CNT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 0, T.DEAL_AMT), 0)) AS DIFF_AMT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.CUST_FEE, 0), 0))     AS OUT_CUST_FEE_AMT, \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', T.CUST_FEE, 0), 0)) AS SAME_CUST_FEE_AMT,\n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 0, T.CUST_FEE), 0)) AS DIFF_CUST_FEE_AMT \n"
                    + "FROM   T_FN_NICE_TRAN T                                                                                     \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                          \n"
                    + "AND    T.ORG_CD = '0WM'                                                                                     \n"
                    + "AND    T.DEAL_TIME_TYPE = #{dealTimeType, jdbcType=VARCHAR} /* 시간내:1, 시간외:2 */                        \n"
                    + "AND    T.NET_ORG_CD = '0WM'                                                                                 \n"
                    + "AND    (T.DEAL_STATUS = '0' OR T.DEAL_STATUS = '1' )                                                        \n"
                    + "GROUP BY T.DEAL_DATE                                                                                        \n";
            }
            else if ( cond.getMacNo().substring(0, 4).equals("NICE") ) {
                logger.info( ">>>  집계데이터 파악 실패[err-조회조건오류-{}-브랜드 제휴조회(NICE)없음]", cond.getMacNo());
                sql = "SELECT 'ERROR' AS DEAL_DATE,          \n"
                    + "       -2      AS CASH_CNT,           \n"
                    + "       0       AS CASH_AMT,           \n"
                    + "       0       AS CASH_CANCEL_CNT,    \n"
                    + "       0       AS CASH_CANCEL_AMT,    \n"
                    + "       0       AS SAME_CNT,           \n"
                    + "       0       AS SAME_AMT,           \n"
                    + "       0       AS DIFF_CNT,           \n"
                    + "       0       AS DIFF_AMT,           \n"
                    + "       0       AS CASH_SVC_CNT,       \n"
                    + "       0       AS CASH_SVC_AMT,       \n"
                    + "       0       AS CASH_IN_CNT,        \n"
                    + "       0       AS CASH_IN_AMT,        \n"
                    + "       0       AS CASH_IN_CANCEL_CNT, \n"
                    + "       0       AS CASH_IN_CANCEL_AMT, \n"
                    + "       0       AS REAL_TRADE_AMT      \n"
                    + "FROM   DUAL                           \n";
            }
            else if ( cond.getMacNo().substring(0, 4).equals("BRAN") ) { // 우리투자증권은 브랜드 제휴기기 없음
                logger.info( ">>>  집계데이터 파악 실패[err-조회조건오류-{}-브랜드 제휴조회(BRAN)없음]", cond.getMacNo());
                sql = "SELECT 'ERROR' AS DEAL_DATE,          \n"
                    + "       -2      AS CASH_CNT,           \n"
                    + "       0       AS CASH_AMT,           \n"
                    + "       0       AS CASH_CANCEL_CNT,    \n"
                    + "       0       AS CASH_CANCEL_AMT,    \n"
                    + "       0       AS SAME_CNT,           \n"
                    + "       0       AS SAME_AMT,           \n"
                    + "       0       AS DIFF_CNT,           \n"
                    + "       0       AS DIFF_AMT,           \n"
                    + "       0       AS CASH_SVC_CNT,       \n"
                    + "       0       AS CASH_SVC_AMT,       \n"
                    + "       0       AS CASH_IN_CNT,        \n"
                    + "       0       AS CASH_IN_AMT,        \n"
                    + "       0       AS CASH_IN_CANCEL_CNT, \n"
                    + "       0       AS CASH_IN_CANCEL_AMT, \n"
                    + "       0       AS REAL_TRADE_AMT      \n"
                    + "FROM   DUAL                           \n";
            }
            else if ( cond.getMacNo().substring(0, 2).equals("MS") ) { // 우리투자증권 MS 카드 거래건 집계
                sql = "SELECT /*+ INDEX_DESC ( T IX_T_FN_NICE_TRAN_02 ) */                                                         \n"
                    + "       T.DEAL_DATE,                                                                                         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', 1, 0), 0))              AS CASH_CNT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0), 0))     AS CASH_AMT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', 1, 0), 0))              AS CASH_CANCEL_CNT,  \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT, 0), 0))     AS CASH_CANCEL_AMT,  \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 1, 0), 0))          AS SAME_CNT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', T.DEAL_AMT, 0), 0)) AS SAME_AMT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 0, 1), 0))          AS DIFF_CNT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 0, T.DEAL_AMT), 0)) AS DIFF_AMT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.CUST_FEE, 0), 0))     AS OUT_CUST_FEE_AMT, \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', T.CUST_FEE, 0), 0)) AS SAME_CUST_FEE_AMT,\n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 0, T.CUST_FEE), 0)) AS DIFF_CUST_FEE_AMT \n"
                    + "FROM   T_FN_NICE_TRAN T                                                                                     \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                          \n"
                    + "AND    T.ORG_CD = '0WM'                                                                                     \n"
                    + "AND    T.DEAL_TIME_TYPE = #{dealTimeType, jdbcType=VARCHAR} /* 시간내:1, 시간외:2 */                        \n"
                    + "AND    T.NET_ORG_CD = '0WM'                                                                                 \n"
                    + "AND    (T.DEAL_STATUS = '0' OR T.DEAL_STATUS = '1')                                                         \n"
                    + "AND    T.TRACK_NO = '3' /* MS카드 */                                                                        \n"
                    + "GROUP BY T.DEAL_DATE                                                                                        \n";
            }
            else if ( cond.getMacNo().substring(0, 2).equals("IC") ) { // 우리투자증권 IC카드 거래 집계
                sql = "SELECT /*+ INDEX_DESC ( T IX_T_FN_NICE_TRAN_02 ) */                                                         \n"
                    + "       T.DEAL_DATE,                                                                                         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', 1, 0), 0))              AS CASH_CNT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.DEAL_AMT, 0), 0))     AS CASH_AMT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', 1, 0), 0))              AS CASH_CANCEL_CNT,  \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '1', T.DEAL_AMT, 0), 0))     AS CASH_CANCEL_AMT,  \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 1, 0), 0))          AS SAME_CNT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', T.DEAL_AMT, 0), 0)) AS SAME_AMT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 0, 1), 0))          AS DIFF_CNT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 0, T.DEAL_AMT), 0)) AS DIFF_AMT,         \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '0', DECODE(T.DEAL_STATUS, '0', T.CUST_FEE, 0), 0))     AS OUT_CUST_FEE_AMT, \n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', T.CUST_FEE, 0), 0)) AS SAME_CUST_FEE_AMT,\n"
                    + "       SUM(DECODE(T.DEAL_TYPE, '4', DECODE(T.TRANS_ORG_CD, '0247', 0, T.CUST_FEE), 0)) AS DIFF_CUST_FEE_AMT \n"
                    + "FROM   T_FN_NICE_TRAN T                                                                                     \n"
                    + "WHERE  T.DEAL_DATE = #{dealDate, jdbcType=VARCHAR}                                                          \n"
                    + "AND    T.ORG_CD = '0WM'                                                                                     \n"
                    + "AND    T.DEAL_TIME_TYPE = #{dealTimeType, jdbcType=VARCHAR} /* 시간내:1, 시간외:2 */                        \n"
                    + "AND    T.NET_ORG_CD = '0WM'                                                                                 \n"
                    + "AND    (T.DEAL_STATUS = '0' OR T.DEAL_STATUS = '1')                                                         \n"
                    + "AND    T.TRACK_NO = '4' /* IC카드 */                                                                        \n"
                    + "GROUP BY T.DEAL_DATE                                                                                        \n";
            }
            else { // 우리투자증권은 기기별 없음
                logger.info( ">>>  집계데이터 파악 실패[err-조회조건오류-{}-브랜드 제휴조회(기기별)없음]", cond.getMacNo());
                sql = "SELECT 'ERROR' AS DEAL_DATE,          \n"
                    + "       -2      AS CASH_CNT,           \n"
                    + "       0       AS CASH_AMT,           \n"
                    + "       0       AS CASH_CANCEL_CNT,    \n"
                    + "       0       AS CASH_CANCEL_AMT,    \n"
                    + "       0       AS SAME_CNT,           \n"
                    + "       0       AS SAME_AMT,           \n"
                    + "       0       AS DIFF_CNT,           \n"
                    + "       0       AS DIFF_AMT,           \n"
                    + "       0       AS CASH_SVC_CNT,       \n"
                    + "       0       AS CASH_SVC_AMT,       \n"
                    + "       0       AS CASH_IN_CNT,        \n"
                    + "       0       AS CASH_IN_AMT,        \n"
                    + "       0       AS CASH_IN_CANCEL_CNT, \n"
                    + "       0       AS CASH_IN_CANCEL_AMT, \n"
                    + "       0       AS REAL_TRADE_AMT      \n"
                    + "FROM   DUAL                           \n";
            }
        }

        return sql;
    }
}

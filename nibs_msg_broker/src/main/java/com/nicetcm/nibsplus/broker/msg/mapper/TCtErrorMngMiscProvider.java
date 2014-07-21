package com.nicetcm.nibsplus.broker.msg.mapper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMngSpec;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorTxn;
import com.nicetcm.nibsplus.broker.msg.services.In03000131Impl;

public class TCtErrorMngMiscProvider {

    private static final Logger logger = LoggerFactory.getLogger(TCtErrorMngMiscProvider.class);
    
    public String selectByCond4( Map<String, Object> parameter ) {
        TCtErrorMng mng = (TCtErrorMng) parameter.get("mng");
        TCtErrorTxn txn = (TCtErrorTxn) parameter.get("txn");
        
        logger.debug("selectByCond4");
        logger.debug("OrgCd = {}", mng.getOrgCd());
        String sql = "";
        sql  = "SELECT MNG.*, TXN.REPAIR_DATE, TXN.REPAIR_TIME   \n"
             + "FROM   OP.T_CT_ERROR_MNG MNG                     \n"
             + "       LEFT JOIN OP.T_CT_ERROR_TXN TXN ON        \n"
             + "       MNG.ERROR_NO = TXN.ERROR_NO AND           \n"
             + "       MNG.CREATE_DATE = TXN.CREATE_DATE         \n"      
             + "WHERE  ORG_CD   = #{mng.orgCd, jdbcType=VARCHAR} \n";
        
        if( mng.getOrgCd().equals(MsgBrokerConst.SL_CODE) ) 
             sql += "AND    BRANCH_CD = OP.F_GET_NICE_BRANCH_CD( #{mng.orgCd, jdbcType=VARCHAR}, \n"
                  + "  #{mng.branchCd, jdbcType=VARCHAR}, '', #{mng.macNo, jdbcType=VARCHAR} ) \n";
        else
             sql += "AND    BRANCH_CD = #{mng.branchCd, jdbcType=VARCHAR} \n";
        sql += "AND    MAC_NO   = #{mng.macNo, jdbcType=VARCHAR}  \n";
        if( mng.getOrgCd().equals(MsgBrokerConst.KBST_CODE) )
            /*
             *  상태장애 (현금부족 등 ) 을 제외한 HW 장애만 CELAR 하도록
             * 단 이상개국을 제외한  출동요청 장애는 Clear 하지 않는다.
             * (이상개국 출동요청은 Clear )
             * 20061108 상태장애중 송수신장애, 회선장애 전원OFF장애는 복구
             * 20090708 국민은행의 경우 출동요청도 Clear 요청자 김희천
             * 20090715 국민은행 출동요청은 도착이 있는경우만 김희천
             * 20110530 출동요청(KB901)에 대한 복구를 도착이 없는 경우도 구공호
             * 20110905 출동요청(KB901)의 고객대기 건이 복구에 의해 통보 안됨에따라
             * 도착이 있는경우만 복구 하도록 원복
             * 20120117 출동요청_코너전원오프(KB90A)
             *          출동요청_기기전원오프(KB90B) 의경우 도착 상관없이
             *          복구 - 운총 김자년 요청
             */
            sql += "AND (   (SUBSTR(ERROR_CD, 1, 2) != 'NE' AND             \n"
                 + "          SUBSTR(ERROR_CD, 1, 2) != 'KB')               \n" 
                 + "      OR                                                \n"
                 + "        (SUBSTR(ERROR_CD, 1, 2) = 'NE' AND              \n"
                 + "          SUBSTR(ERROR_CD, 4, 2) IN ('10', '11', '12')) \n"
                 + "      OR (SUBSTR(ERROR_CD, 1, 2) = 'KB'    AND          \n"
                 + "          ARRIVAL_TIME IS NOT NULL )                    \n"
                 + "      OR (ERROR_CD IN ('KB906', 'KB90A', 'KB90B') )     \n"
                 + "     )                                                  \n";
        else if( mng.getOrgCd().equals(MsgBrokerConst.KIUP_CODE) )
            /*
             *  상태장애 (현금부족 등 ) 을 제외한 HW 장애만 CELAR 하도록
             * 출동요청 장애는 Clear 하지 않는다.
             * 실제로는 기업은행의 경우 장애상태 전문이 없으므로 해당 없다
             * 20061108 상태장애중 송수신장애, 회선장애 전원OFF장애는 복구
             * 20090804 기업은행 상태전문 중 개국신호만 받음
             *  출동요청 중 미개국점검(KU909) 만 복구
             */
            sql += "AND (   (SUBSTR(ERROR_CD, 1, 2) != 'NE' AND             \n"
                 + "         SUBSTR(ERROR_CD, 1, 2) != 'KI' )               \n"
                 + "     OR                                                 \n"
                 + "        (SUBSTR(ERROR_CD, 1, 2) = 'NE' AND              \n"
                 + "         SUBSTR(ERROR_CD, 4, 2) IN ('10', '11', '12'))  \n"
                 + "     OR (ERROR_CD = 'KI909')                            \n"
                 + "    )   AND ERROR_CD != 'AFTMNG'                        \n";   /* 미완료고객장애 제외 */
        else if( mng.getOrgCd().equals(MsgBrokerConst.SHATMS_CODE) )
            /*
             *  상태장애 (현금부족 등 ) 을 제외한 HW 장애만 CELAR 하도록
             * 출동요청 장애는 Clear 하지 않는다.
             * 20061108 상태장애중 송수신장애, 회선장애 전원OFF장애는 복구
             * 20100429 후처리장애는 복구시키지 않음
             */
            sql += "AND (   SUBSTR(ERROR_CD, 1, 2) != 'NE'                  \n"
                 + "     OR                                                 \n"
                 + "        (SUBSTR(ERROR_CD, 1, 2) = 'NE' AND              \n"
                 + "         SUBSTR(ERROR_CD, 4, 2) IN ('10', '11', '12'))  \n"
                 + "    )                                                   \n"
                 + "AND     SUBSTR(ERROR_CD, 1, 2) != 'SH'                  \n"
                 + "AND     ERROR_CD != 'AFTMNG'                            \n";
        else if( mng.getOrgCd().equals(MsgBrokerConst.HANAATMS_CODE) )
            /*
             *  하나은행의 경우
             * 상태장애 (현금부족 등 ) 을 제외한 HW 장애만 CELAR 하도록
             * 현금부족출동요청(HN90B) 와 수표부족출동요청(HN90E)는 복구시키지
             *  않도록 한다.
             * 20061108 상태장애중 송수신장애, 회선장애 전원OFF장애는 복구
             */
            sql += "AND (   SUBSTR(ERROR_CD, 1, 2) != 'NE'                  \n"
                 + "     OR                                                 \n"
                 + "        (SUBSTR(ERROR_CD, 1, 2) = 'NE' AND              \n"
                 + "         SUBSTR(ERROR_CD, 4, 2) IN ('10', '11', '12',   \n"
                 + "            '50', '51', '52', '53', '54', '55', '56',   \n"
                 + "            '57', '58', '59', '60', '61') )             \n"
                 + "    )                                                   \n"
                 + "AND      ERROR_CD NOT IN ('HN90B', 'HN90E')             \n";
        else if( mng.getOrgCd().equals(MsgBrokerConst.WRATMS_CODE) )
            /*
             *  상태장애 (현금부족 등 ) 을 제외한 HW 장애만 CELAR 하도록
             * 출동요청 장애는 Clear 하지 않는다.
             * 20061108 상태장애중 송수신장애, 회선장애 전원OFF장애는 복구
             * 20110331 출동요청 중 미개국점검(WR409) 만 복구(양유석요청)
             */
            sql += "AND (   (SUBSTR(ERROR_CD, 1, 2) != 'NE' AND             \n"
                 + "         SUBSTR(ERROR_CD, 1, 2) != 'WR' )               \n"
                 + "     OR                                                 \n"
                 + "        (SUBSTR(ERROR_CD, 1, 2) = 'NE' AND              \n"
                 + "         SUBSTR(ERROR_CD, 4, 2) IN ('10', '11', '12'))  \n"
                 + "     OR (ERROR_CD = 'WR409')                            \n"
                 + "    )                                                   \n";
        else
            /*
             *  상태장애 (현금부족 등 ) 을 제외한 HW 장애만 CELAR 하도록
             * 20061108 상태장애중 송수신장애, 회선장애 전원OFF장애는 복구
             */
            sql += "AND (   SUBSTR(ERROR_CD, 1, 2) != 'NE'                  \n"
                 + "     OR                                                 \n"
                 + "        (SUBSTR(ERROR_CD, 1, 2) = 'NE' AND              \n"
                 + "         SUBSTR(ERROR_CD, 4, 2) IN ('10', '11', '12',   \n"
                 + "            '50', '51', '52', '53', '54', '55', '56',   \n"
                 + "            '57', '58', '59', '60', '61') )             \n"
                 + "    )                                                   \n";
            
        sql += "AND    ERROR_CD <> 'ERR02'                                             \n"
             + "AND    TXN.REPAIR_TIME = '999999'                                      \n"
             + "AND    MNG.CREATE_DATE || MNG.CREATE_TIME <=                           \n"
             + "                      RTRIM(#{txn.repairDate, jdbcType=VARCHAR})||RTRIM(#{txn.repairTime, jdbcType=VARCHAR}) \n"
             + "AND    MNG.CREATE_DATE > TO_NUMBER(TO_CHAR( SYSDATE-10, 'YYYYMMDD' ))  ";
//             + "FOR UPDATE OF ERROR_NO, CREATE_DATE, CREATE_TIME ";
        
        logger.debug("SQL = {}", sql );
        return sql;
    }
}

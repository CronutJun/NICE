package com.nicetcm.nibsplus.broker.msg.mapper;

import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;

public class TCtErrorMngMiscProvider {

    public String selectByCond4( TCtErrorMng cond ) {
        String sql = "";
        sql  = "SELECT *                                     "
             + "FROM   OP.T_CT_ERROR_MNG                     "
             + "WHERE  ORG_CD   = #{orgCd, jdbcType=VARCHAR} ";
        
        if( cond.getOrgCd().equals(MsgBrokerConst.SL_CODE) ) 
             sql += "AND    BRANCH_CD = OP.F_GET_NICE_BRANCH_CD( #{orgCd, jdbcType=VARCHAR},         "
                  + "  #{branchCd, jdbcType=VARCHAR}, '', #{macNo, jdbcType=VARCHAR} ) ";
        else
             sql += "AND    BRANCH_CD = #{branchCd, jdbcType=VARCHAR} ";
        sql += "AND    MAC_NO   = #{macNo, jdbcType=VARCHAR}  ";
        if( cond.getOrgCd().equals(MsgBrokerConst.KBST_CODE) )
            sql += "AND (   (SUBSTR(ERROR_CD, 1, 2) != 'NE' AND             "
                 + "          SUBSTR(ERROR_CD, 1, 2) != 'KB')               " 
                 + "      OR                                                "
                 + "        (SUBSTR(ERROR_CD, 1, 2) = 'NE' AND              "
                 + "          SUBSTR(ERROR_CD, 4, 2) IN ('10', '11', '12')) "
                 + "      OR (SUBSTR(ERROR_CD, 1, 2) = 'KB'    AND          "
                 + "          ARRIVAL_TIME IS NOT NULL )                    "
                 + "      OR (ERROR_CD IN ('KB906', 'KB90A', 'KB90B') )     "
                 + "     )                                                  ";
        sql += "AND    ERROR_CD <> 'ERR02'                                             "
             + "AND    REPAIR_TIME = '999999'                                          "
             + "AND    CREATE_DATE || CREATE_TIME <=                                   "
             + "                      RTRIM(#{repairDate, jdbcType=VARCHAR})||RTRIM(#{repairTime, jdbcType=VARCHAR}) "
             + "AND    CREATE_DATE > TO_NUMBER(TO_CHAR( SYSDATE-10, 'YYYYMMDD' ))      "
             + "FOR UPDATE OF ERROR_NO, CREATE_DATE, CREATE_TIME ";
        
        return sql;
    }
}

package com.nicetcm.nibsplus.broker.msg.mapper;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.nicetcm.nibsplus.broker.msg.model.TMisc;
import com.nicetcm.nibsplus.broker.msg.model.TCtErrorMng;

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
}

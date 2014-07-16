package com.nicetcm.nibsplus.broker.msg.mapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.mapping.StatementType;

import com.nicetcm.nibsplus.broker.msg.model.TMacInfo;

public interface StoredProcMapper {

    @Select({
        "CALL sp_if_SendSMSMacInfo( #{orgCd, mode=IN, jdbcType=VARCHAR}, #{branchCd, mode=IN, jdbcType=VARCHAR},",
        "                         #{orgSiteCd, mode=INOUT, jdbcType=VARCHAR}, #{macNo, jdbcType=VARCHAR}, 'EM')"
    })
    @Options( statementType = StatementType.CALLABLE )
    void SendSMSMacInfo(TMacInfo cond);
    
}

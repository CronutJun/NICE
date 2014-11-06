package com.nicetcm.nibsplus.broker.msg.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 결번요청 응답
 *
 * <pre>
 * MngIQ_AP_SaveReqLostNo
 * </pre>
 *
 * @author   K.D.J
 * @since    2014.10.31
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmCommonMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmCommon;
import com.nicetcm.nibsplus.broker.msg.model.TCmCommonSpec;

@Service("in04102110")
public class In04102110Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In04102110Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCmCommonMapper tCmCommonMap;


    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmCommon cmCommon = new TCmCommon();
        TCmCommonSpec cmCommonSpec = new TCmCommonSpec();

        if( comPack.getError(parsed.getString("CM.ret_cd_src"), parsed.getString("CM.org_cd"), parsed.getString("CM.ret_cd")) == 0 ) {
            /**
             *  최종일련번호 요청이라면 t_cm_common에 저장 처리
             */
            if( parsed.getString("req_code").equals("9") )  {
                cmCommon.setCdNm ( parsed.getString("CM.org_cd") );
                cmCommon.setCdNm1( parsed.getString("req_date")  );
                cmCommon.setCdNm2( parsed.getString("final_seq") );
                try {
                    cmCommonSpec.createCriteria().andLargeCdEqualTo("8950")
                                                 .andSmallCdEqualTo( parsed.getString("CM.org_cd") + "0" );
                    if( tCmCommonMap.updateBySpecSelective( cmCommon, cmCommonSpec ) == 0 ) {
                        cmCommon.setLargeCd( "8950" );
                        cmCommon.setSmallCd( parsed.getString("CM.org_cd") + "0" );
                        tCmCommonMap.insertSelective( cmCommon );
                    }
                }
                catch( Exception e ) {
                    logger.warn("[T_CM_COMMON-large_cd 8950] INSERT/UPDATE Err!! [%s]", e.getLocalizedMessage() );
                    throw e;
                }
            }
        }

    }//end method
}

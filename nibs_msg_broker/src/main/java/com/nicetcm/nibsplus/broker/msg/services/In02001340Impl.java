package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 코너(기기)현황
 *
 * <pre>
 * MngSM_SaveSiteSetState
 * </pre>
 *
 *           2014. 08. 07    K.D.J.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSiteStateMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSiteState;
import com.nicetcm.nibsplus.broker.msg.model.TCmSiteStateSpec;

@Service("in02001340")
public class In02001340Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02001340Impl.class);

    @Autowired private StoredProcMapper   splMap;
    @Autowired private TCmSiteStateMapper cmSiteStateMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmSiteState     cmSiteState = new TCmSiteState();
        TCmSiteStateSpec spec        = new TCmSiteStateSpec();
        /*
         * 전송 조건에 따라 내용이 달라짐.
         * 현재, 테이블이 정해지지 않았음.
         * 그냥 한번 전송하고 말 것인지 정해야함.
         */

        /*
         *  응답코드 B99 로 오면 재송신 하지 않고 차후 모니터링 하도록 처리
         */
        if( parsed.getString("CM.ret_cd_src").equals("B") ) {
            if( parsed.getString("CM.ret_cd").equals("00") ) {
                cmSiteState.setOrgSendYn("1");
            }
            else {
                cmSiteState.setOrgSendYn("b");
            }
        }
        else {
            cmSiteState.setOrgSendYn("");
        }
        spec.createCriteria().andOrgCdEqualTo   ( "096"  )
                             .andBranchCdEqualTo( "9600" )
                             .andMacNoEqualTo   ( parsed.getString("mac_no") );
        try {
            cmSiteStateMap.updateBySpecSelective( cmSiteState, spec );
        }
        catch( Exception e ) {
            logger.info( "[T_CM_SITE_STATE] Update Error MAC_NO[{}] [{}]", parsed.getString("mac_no"), e.getLocalizedMessage() );
            throw e;
        }
    }

}

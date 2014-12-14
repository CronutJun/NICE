package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker NICE 기기정보 MASTER
 *
 * <pre>
 * MngNI_AP_SaveNiceMacMaster
 * </pre>
 *
 *           2014. 08. 01    K.D.J.
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmMacMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmMac;

@Service("inN4101500")
public class InN4101500Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InN4101500Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCmMacMapper cmMacMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmMac cmMac = new TCmMac();
        /*
         *  이전일 경우로 인해 삭제시에만 org_send_close를 수정하도록 20091228
         */
        if( parsed.getString("job_type").equals("2") ) {
            cmMac.setOrgCd       ( parsed.getString("CM.org_cd") );
            cmMac.setBranchCd    ( "9600"                        );
            cmMac.setMacNo       ( parsed.getString("mac_no")    );
            cmMac.setOrgSendClose( "2"                           );
            cmMac.setUpdateDate  ( safeData.getDSysDate()        );
            cmMac.setUpdateUid   ( "APmng"                       );
            try {
                cmMacMap.updateByPrimaryKeySelective( cmMac );
            }
            catch( Exception e ) {
                logger.warn( "[T_CM_MAC] Update Error [{}]", e.getLocalizedMessage() );
                throw e;
            }
        }
        logger.warn( "T_CM_MAC] Update OK" );
    }
}

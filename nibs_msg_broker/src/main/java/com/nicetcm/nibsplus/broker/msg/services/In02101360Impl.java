package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 시설물 설치/철수 처리결과
 *
 *
 *           2014. 12. 17    K.D.J.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmSetFacInfoMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmSetFacInfo;

@Service("in02101360")
public class In02101360Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In02101360Impl.class);

    @Autowired private StoredProcMapper    splMap;
    @Autowired private TCmSetFacInfoMapper cmSetFacInfoMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmSetFacInfo  cmSetFacInfo = new TCmSetFacInfo();

        cmSetFacInfo.setOrgCd    ( parsed.getString("CM.org_cd") );
        cmSetFacInfo.setWorkSeq  ( parsed.getString("work_seq")  );
        cmSetFacInfo.setWorkClass( parsed.getString("set_type")  );
        cmSetFacInfo.setFacSeqNo ( parsed.getString("fac_seq_no"));
        cmSetFacInfo.setOrgSendYn( "1" );
        try {
            cmSetFacInfoMap.updateByPrimaryKeySelective( cmSetFacInfo );
        }
        catch( Exception e ) {
            logger.warn( "[T_CM_SET_FAC_INFO] Update Error [{}] [{}]", parsed.getString("work_seq"), e.getLocalizedMessage() );
            throw e;
        }
    }

}

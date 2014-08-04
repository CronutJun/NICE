package com.nicetcm.nibsplus.broker.msg.services;

/*
 * Copyright 2014 The NIBS+ Project
 *
 * MSG Broker 지점정보
 *
 * <pre>
 * MngMM_NiceJijumInfo
 * </pre>
 *
 *           2014. 08. 04    K.D.J.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;
import com.nicetcm.nibsplus.broker.msg.mapper.TCmBranchEmartsuperMapper;
import com.nicetcm.nibsplus.broker.msg.model.TCmBranchEmartsuper;

@Service("inM1000100")
public class InM1000100Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(InM1000100Impl.class);

    @Autowired private StoredProcMapper splMap;
    @Autowired private TCmBranchEmartsuperMapper cmBranchEmartsuperMap;

    @Override
    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

        TCmBranchEmartsuper rslt = null;

        try {
            rslt = cmBranchEmartsuperMap.selectByPrimaryKey( parsed.getString("brch_cd") );
            if( rslt == null ) {
                throw new MsgBrokerException( "ERROR_ILL_JIJUM", -85 );
            }
        }
        catch( Exception e ) {
            logger.info(">>> [T_CM_JIJUM_EMARTSUPER] ERROR [{}]", e.getLocalizedMessage() );
            throw e;
        }
        parsed.setString( "brch_nm",      rslt.getBranchNm() )
              .setString( "br_master_nm", rslt.getStorekeeperNm() );
    }
}

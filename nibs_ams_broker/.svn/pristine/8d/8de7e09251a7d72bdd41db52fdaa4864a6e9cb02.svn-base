package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * In11001003Impl
 *
 *  폐국전문 수신처리
 *
 *
 * @author  K.D.J
 * @since   2014.08.26
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmTrxMapper;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("in11001003")
public class In11001003Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In11001003Impl.class);

    @Autowired TRmTrxMapper rmTrxMap;

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        TRmTrx rmTrx = new TRmTrx();

        rmTrx.setTrxDate( safeData.getMsgDate() );
        rmTrx.setTrxNo  ( rmTrxMap.generateKey() );
        rmTrx.setTrxCd  ( parsed.getString( "CM._AOCServiceCode") );
        rmTrx.setActCd  ( parsed.getString( "CM._AOCMsgCode" ) );
        rmTrx.setTrxUid ( parsed.getString( "CM._SSTNo").substring(2) );

        comPack.insUpdMacEnv(safeData, parsed, rmTrx);

    }
}

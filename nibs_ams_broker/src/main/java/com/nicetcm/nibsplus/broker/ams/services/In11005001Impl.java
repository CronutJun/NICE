package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * In11005001Impl
 *
 *  파일 자동 업로드 (From 기기)
 *
 *
 * @author  K.D.J
 * @since   2014.08.29
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

@Service("in11005001")
public class In11005001Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In11005001Impl.class);

    @Autowired TRmTrxMapper rmTrxMap;

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        // 먼저 fileLoc의 Temp파일을 정해진 위치로 이동
//        TRmTrx rmTrx = new TRmTrx();
//
//        rmTrx.setTrxDate( safeData.getMsgDate() );
//        rmTrx.setTrxNo  ( rmTrxMap.generateKey() );
//        rmTrx.setTrxCd  ( parsed.getString( "CM._AOCServiceCode") );
//        rmTrx.setActCd  ( parsed.getString( "CM._AOCMsgCode" ) );
//        rmTrx.setTrxUid ( parsed.getString( "CM._SSTNo").substring(2) );
//
//        comPack.insUpdMacEnv(safeData, parsed, rmTrx);
//
    }
}

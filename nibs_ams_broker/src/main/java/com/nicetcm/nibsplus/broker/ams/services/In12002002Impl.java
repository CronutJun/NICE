package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * In12002002Impl
 *
 *  원격환경조회 응답처리
 *
 *
 * @author  K.D.J
 * @since   2014.08.19
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;

@Service("in12002002")
public class In12002002Impl extends InMsgHandlerImpl {

    private static final Logger logger = LoggerFactory.getLogger(In12002002Impl.class);

    @Override
    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        TRmTrx rmTrx = new TRmTrx();

        rmTrx.setTrxDate( reqJob.getTrxDate() );
        rmTrx.setTrxNo( reqJob.getTrxNo() );
        rmTrx.setTrxCd( reqJob.getTrxCd() );
        rmTrx.setActCd( reqJob.getActCd() );
        rmTrx.setTrxUid( reqJob.getTrxUid() );

        comPack.insUpdMacEnv(safeData, parsed, rmTrx);

    }

}

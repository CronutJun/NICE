package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * InMsgHandlerImpl
 *
 *  수신전문 전처리
 *
 *
 * @author  K.D.J
 * @since   2014.08.18
 */

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.common.MsgParser;

public abstract class InMsgHandlerImpl implements InMsgHandler {

    @Autowired protected SqlSession                   sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Autowired protected CommonPack                   comPack;

    @Override
    public final void inMsgHandle(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

        safeData.setTXS(amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setSysDate( AMSBrokerLib.getSysDate() );
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );
        try {
            inMsgBizProc( safeData, parsed, reqJob, fileLoc );
            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }

    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, String fileLoc) throws Exception {

    }
}

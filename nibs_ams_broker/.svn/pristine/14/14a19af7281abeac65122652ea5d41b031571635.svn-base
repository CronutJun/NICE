package com.nicetcm.nibsplus.broker.ams.services;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;

public abstract class InMsgHandlerImpl implements InMsgHandler {

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Override
    public void inMsgHandle(AMSBrokerData safeData, MsgParser parsed, String fileLoc) throws Exception {
        safeData.setTXS(amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        try {
            inMsgBizProc( safeData, parsed, fileLoc );
            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }

    public void inMsgBizProc(AMSBrokerData safeData, MsgParser parsed, String fileLoc) throws Exception {

    }
}

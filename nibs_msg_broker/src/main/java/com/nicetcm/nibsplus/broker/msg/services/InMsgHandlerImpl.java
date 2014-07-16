package com.nicetcm.nibsplus.broker.msg.services;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;

public abstract class InMsgHandlerImpl implements InMsgHandler {

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager msgTX;
    
    @Autowired protected CommonPack comPack;
    
    @Override
    public final void inMsgHandle(MsgParser parsed) throws Exception {
        TransactionStatus status = msgTX.getTransaction( MsgBrokerTransaction.defMSGTX );
        try {
            inMsgBizProc( parsed );
            msgTX.commit(status);
        }
        catch( Exception e ) {
            msgTX.rollback(status);
            throw e;
        }
    }
    
    public void inMsgBizProc(MsgParser parsed) throws Exception {
        
    }

}

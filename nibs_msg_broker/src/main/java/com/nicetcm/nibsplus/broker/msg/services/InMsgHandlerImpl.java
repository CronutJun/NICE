package com.nicetcm.nibsplus.broker.msg.services;

import java.util.Date;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;

public abstract class InMsgHandlerImpl implements InMsgHandler {

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager msgTX;
    
    @Autowired protected CommonPack comPack;
    
    protected String sSysDate;
    protected String sNSysDate;
    protected String sSysTime;
    protected Date   dSysDate;
    
    @Override
    public final void inMsgHandle(MsgParser parsed) throws Exception {
        TransactionStatus status = msgTX.getTransaction( MsgBrokerTransaction.defMSGTX );
        try {
            sSysDate  = MsgBrokerLib.SysDate();
            sNSysDate = MsgBrokerLib.SysDate(1);
            sSysTime  = MsgBrokerLib.SysTime();
            dSysDate  = MsgBrokerLib.SysDateD(0);
            
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

package com.nicetcm.nibsplus.broker.msg.services;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;
import com.nicetcm.nibsplus.broker.msg.mapper.StoredProcMapper;

@Service("in01001320")
public class In01001320Impl implements InMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(In01001320Impl.class);
    
    @Autowired private SqlSession sqlSession;
    @Autowired private DataSourceTransactionManager msgTX;
    
    @Autowired private CommonPack comPack;
    @Autowired private StoredProcMapper splMap;
    
    @Override
    public void inMsgHandle(MsgParser parsed) throws Exception {
        TransactionStatus status = msgTX.getTransaction( MsgBrokerTransaction.defMSGTX );
        try {
            logger.debug("Msg Received");
            logger.debug(parsed.getString("CM.work_type"));

            
            
            msgTX.commit(status);
        }
        catch( Exception e ) {
            msgTX.rollback(status);
            throw e;
        }
    }

}

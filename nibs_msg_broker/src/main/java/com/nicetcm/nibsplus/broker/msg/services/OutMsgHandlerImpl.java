package com.nicetcm.nibsplus.broker.msg.services;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;

public abstract class OutMsgHandlerImpl implements OutMsgHandler {

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager msgTX;

    @Autowired protected CommonPack comPack;

    @Override
    public final void outMsgHandle(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        safeData.setTXS(msgTX.getTransaction( MsgBrokerTransaction.defMSGTX ));
        try {
            safeData.setSysDate(MsgBrokerLib.SysDate());
            safeData.setNSysDate(MsgBrokerLib.SysDate(1));
            safeData.setSysTime(MsgBrokerLib.SysTime());
            safeData.setDSysDate(MsgBrokerLib.SysDateD(0));

            outMsgBizProc( safeData, parsed );
            msgTX.commit(safeData.getTXS());
        }
        catch( MsgBrokerException me ) {
            if( me.getErrorCode() == -99 ) {
                msgTX.commit(safeData.getTXS());
            }
            else {
                msgTX.rollback(safeData.getTXS());
                throw me;
            }
        }
        catch( Exception e ) {
            msgTX.rollback(safeData.getTXS());
            throw e;
        }
    }

    public void outMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

    }

}

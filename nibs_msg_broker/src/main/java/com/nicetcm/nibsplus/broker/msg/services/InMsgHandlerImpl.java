package com.nicetcm.nibsplus.broker.msg.services;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerConst;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerData;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerException;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerLib;
import com.nicetcm.nibsplus.broker.msg.MsgBrokerTransaction;

public abstract class InMsgHandlerImpl implements InMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(InMsgHandlerImpl.class);

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager msgTX;

    @Autowired protected CommonPack comPack;

    @Override
    public final void inMsgHandle(MsgBrokerData safeData, MsgParser parsed) throws Exception {
        safeData.setTXS(msgTX.getTransaction( MsgBrokerTransaction.defMSGTX ));
        try {
            try {
                if( parsed.getString("CM.org_cd").equals(MsgBrokerConst.EMART_CODE)
                ||  parsed.getString("CM.org_cd").equals(MsgBrokerConst.ELAND_CODE) ) {
                    comPack.insertIfDataLog( safeData, "I", parsed );
                    msgTX.commit(safeData.getTXS());
                    safeData.setTXS(msgTX.getTransaction( MsgBrokerTransaction.defMSGTX ));
                }
            }
            catch( Exception e) {
                msgTX.rollback(safeData.getTXS());
                safeData.setTXS(msgTX.getTransaction( MsgBrokerTransaction.defMSGTX ));
            }
            safeData.setSysDate(MsgBrokerLib.SysDate());
            safeData.setNSysDate(MsgBrokerLib.SysDate(1));
            safeData.setSysTime(MsgBrokerLib.SysTime());
            safeData.setDSysDate(MsgBrokerLib.SysDateD(0));

            inMsgBizProc( safeData, parsed );
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

    public void inMsgBizProc(MsgBrokerData safeData, MsgParser parsed) throws Exception {

    }

}

package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * ReqMsgHandlerImpl
 *
 *  수신전문 선 처리
 *
 *
 * @author  K.D.J
 * @since   2014.08.25
 */

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMacEnvMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmTrxMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMsgMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgHis;
import com.nicetcm.nibsplus.broker.common.MsgParser;

@Service("rcvMsg")
public class RcvMsgHandlerImpl implements RcvMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(RcvMsgHandlerImpl.class);

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Autowired private   CommonPack                   comPack;
    @Autowired private   TRmMacEnvMapper              macEnvMap;
    @Autowired private   TRmTrxMapper                 trxMap;
    @Autowired private   TRmMsgMapper                 msgMap;

    @Override
    public void rcvMsgHandle(AMSBrokerData safeData, MsgParser parsed, TRmTrx trx, TRmMsg msg) throws Exception {

        safeData.setSysDate( AMSBrokerLib.getSysDate() );

        safeData.setTXS( amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );
        try {
            /**
             * 거래내역 생성
             */
            trx.setTrxDate( safeData.getMsgDate()   );
            trx.setTrxNo  ( trxMap.generateKey()    );
            trx.setTrxTime(  safeData.getMsgTime()  );
            trx.setTrxCd  ( parsed.getString( "CM._AOCServiceCode")     );
            trx.setActCd  ( parsed.getString( "CM._AOCMsgCode" )        );
            trx.setTrxUid(  parsed.getString( "CM__SSTNo").substring(2) );

            trxMap.insert( trx );

            TRmMsgHis msgHis = new TRmMsgHis();

            msg.setMsgSeq( msgMap.generateKey() );
            msg.setIoCl( "I" );
            msg.setMsgSts( "0" );
            msg.setMsgCd( parsed.getString( "CM._AOCMsgCode" )        );
            msg.setSvcCd( parsed.getString( "CM._AOCServiceCode")     );
            msg.setMacNo( parsed.getString( "CM__SSTNo").substring(2) );

            /**
             * 개국
             */
            if( msg.getMsgCd().equals("1100") && msg.getSvcCd().equals("1001") ) {
                msg.setMsgType( "SM" );
            }
            /**
             * 폐국
             */
            else if( msg.getMsgCd().equals("1100") && msg.getSvcCd().equals("1007") ) {
                msg.setMsgType( "SM" );
            }

            byte[] read = new byte[parsed.getMessage().limit()];
            parsed.getMessage().position(0);
            parsed.getMessage().get(read);
            logger.debug(new String(read));

            msgHis.setMsgCd( new String(read) );

            comPack.insUpdMsg(safeData, msg.getMacNo(), trx, msg, msgHis);

            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }

}

package com.nicetcm.nibsplus.broker.ams.services;

/**
 * Copyright 2014 The NIBS+ Project
 *
 * ReqMsgHandlerImpl
 *
 *  요청전문 구성
 *
 *
 * @author  K.D.J
 * @since   2014.08.18
 */


import java.nio.ByteBuffer;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;

import com.nicetcm.nibsplus.broker.ams.services.CommonPack;

import com.nicetcm.nibsplus.broker.ams.mapper.TRmMsgMapper;

import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgHis;

@Service("reqMsg")
public class ReqMsgHandlerImpl implements ReqMsgHandler {

    private static final Logger logger = LoggerFactory.getLogger(ReqMsgHandlerImpl.class);

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Autowired private   CommonPack                   comPack;
    @Autowired private   TRmMsgMapper                 msgMap;

    @Override
    public void reqMsgHandle(AMSBrokerData safeData, AMSBrokerReqJob reqJob, AMSBrokerReqInfo reqInfo) throws Exception {

        safeData.setSysDate( AMSBrokerLib.getSysDate() );

        safeData.setTXS(amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );
        try {
            logger.debug("Start reqMsgHandle");
            reqInfo.setDestIP("10.3.28.114");
            reqInfo.setDestPort(33001);
            logger.debug( "TrxCd = {}, ActCd = {}", reqJob.getTrxCd(), reqJob.getActCd() );

            TRmMsg msg       = new TRmMsg();
            TRmMsgHis msgHis = new TRmMsgHis();

            msg.setMsgSeq( msgMap.generateKey() );

            /**
             * 환경정보 조회
             */
            if( reqJob.getTrxCd().equals("1000") && reqJob.getActCd().equals("1000") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "2002" );
                msg.setIoCl( "O" );
                msg.setMsgSts( "0" );
                msg.setMsgType( "RC" );

                reqInfo.setMsg( ByteBuffer.allocateDirect(MsgCommon.READ_BUF_SIZE) );
                MsgParser msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path") + "21002002.json").newMessage(reqInfo.getMsg());
                reqInfo.getMsg().position(0);
                msgPsr.setString("CM._AOCMsgCode",     msg.getMsgCd())
                      .setString("CM._AOCServiceCode", msg.getSvcCd())
                      .setString("CM._AOCMsgSendDate", safeData.getMsgDate())
                      .setString("CM._AOCMsgSendTime", safeData.getMsgTime())
                      .setInt   ("CM._AOCMsgLen",      msgPsr.getMessageLength() - 9)
                      .setString("CM._AOCTranNo",      msg.getMsgSeq())
                      .syncMessage();
                logger.debug("Message Length = "  + msgPsr.getMessageLength());
                logger.debug("Last Position = "   + msgPsr.lastPosition());
                reqInfo.getMsg().limit(msgPsr.lastPosition());
                byte[] read = new byte[reqInfo.getMsg().limit()];
                reqInfo.getMsg().position(0);
                reqInfo.getMsg().get(read);
                logger.debug(new String(read));

                msgHis.setMsgCtx( new String(read) );
            }
            /**
             * Registry 정보 조회
             */
            else if( reqJob.getTrxCd().equals("2000") && reqJob.getActCd().equals("1000") ) {
                msg.setMsgCd( "2100" );
                msg.setSvcCd( "2011" );
                msg.setIoCl( "O" );
                msg.setMsgSts( "0" );
                msg.setMsgType( "RC" );

                reqInfo.setMsg( ByteBuffer.allocateDirect(MsgCommon.READ_BUF_SIZE) );
                MsgParser msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path") + "21002011.json").newMessage(reqInfo.getMsg());
                reqInfo.getMsg().position(0);
                msgPsr.setString( "CM._AOCMsgCode",           msg.getMsgCd())
                      .setString( "CM._AOCServiceCode",       msg.getSvcCd())
                      .setString( "CM._AOCMsgSendDate",       safeData.getMsgDate() )
                      .setString( "CM._AOCMsgSendTime",       safeData.getMsgTime() )
                      .setInt   ( "CM._AOCMsgLen",            msgPsr.getMessageLength() - 9 )
                      .setString( "CM._AOCTranNo",            msg.getMsgSeq() )
                      .setString( "_AocReqRegBaseKey",        reqJob.getReqRegInfo().getBaseKey() )
                      .setString( "_AocReqRegSubKey",         reqJob.getReqRegInfo().getKeyPath() )
                      .setString( "_AocReqRegValueKey",       reqJob.getReqRegInfo().getKeyName() )
                      .syncMessage();
                logger.debug("Message Length = "  + msgPsr.getMessageLength());
                logger.debug("Last Position = "   + msgPsr.lastPosition());
                reqInfo.getMsg().limit(msgPsr.lastPosition());
                byte[] read = new byte[reqInfo.getMsg().limit()];
                reqInfo.getMsg().position(0);
                reqInfo.getMsg().get(read);
                logger.debug(new String(read));

                msgHis.setMsgCtx( new String(read) );
            }

            comPack.insUpdMsg(safeData, reqJob, msg, msgHis);

            reqJob.setOrgCreateDate( msg.getCreateDate() );
            reqJob.setOrgMsgSeq( msg.getMsgSeq() );

            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }

}

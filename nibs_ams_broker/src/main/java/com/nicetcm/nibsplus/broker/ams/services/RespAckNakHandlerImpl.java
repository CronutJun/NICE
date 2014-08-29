package com.nicetcm.nibsplus.broker.ams.services;

import io.netty.channel.ChannelHandlerContext;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMacEnvMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmMsgMapper;
import com.nicetcm.nibsplus.broker.ams.mapper.TRmTrxMapper;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsgHis;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;

@Service("respAckNak")
public class RespAckNakHandlerImpl implements RespAckNakHandler {

    private static final Logger logger = LoggerFactory.getLogger(RespAckNakHandlerImpl.class);

    @Autowired protected SqlSession sqlSession;
    @Autowired protected DataSourceTransactionManager amsTX;

    @Autowired private   CommonPack                   comPack;
    @Autowired private   TRmMacEnvMapper              macEnvMap;
    @Autowired private   TRmTrxMapper                 trxMap;
    @Autowired private   TRmMsgMapper                 msgMap;

    @Override
    public void procAckNak( ChannelHandlerContext ctx, AMSBrokerData safeData, MsgParser parsed, TRmTrx trx, TRmMsg msg, String ackNak, String errMessage ) throws Exception {

        byte[] read = null;

        safeData.setSysDate( AMSBrokerLib.getSysDate() );

        safeData.setTXS( amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );
        msg.setMsgSts( ackNak );
        /**
         * POLL
         */
        if( msg.getSvcCd().equals(AMSBrokerConst.SVC_CD_NTI_POL)) {
            amsTX.rollback(safeData.getTXS());
            return;
        }
        /**
         * 개국
         */
        else if( msg.getSvcCd().equals(AMSBrokerConst.SVC_CD_NTI_OPN)) {
            msg.setMsgCd( AMSBrokerConst.MSG_CD_RSP );
        }
        /**
         * 폐국
         */
        else if( msg.getSvcCd().equals(AMSBrokerConst.SVC_CD_NTI_OPN)) {
            msg.setMsgCd( AMSBrokerConst.MSG_CD_RSP );
        }
        else {
            msg.setMsgCd( AMSBrokerConst.MSG_CD_RSP );
        }
        try {
            TRmMsgHis msgHis = new TRmMsgHis();

            parsed.setString( "CM._AOCMsgCode", msg.getMsgCd() );
            if( parsed.getResponseInfo().getType() != null
            &&  parsed.getResponseInfo().getType().equals("COMMON") ) {
                read = new byte[AMSBrokerConst.COMMON_LEN];
                parsed.setInt( "CM._AOCMsgLen", AMSBrokerConst.COMMON_LEN - 9 );
            }
            else if( parsed.getResponseInfo().getType() != null
                  &&  parsed.getResponseInfo().getType().equals("SELF") ) {
                read = new byte[parsed.getMessage().limit()];
            }
            else if( parsed.getResponseInfo().getType() != null
                    &&  parsed.getResponseInfo().getType().equals("JSON") ) {
                //MsgParser msgPsr = MsgParser.getInstance(incFile)
                  read = new byte[parsed.getMessage().limit()];
              }
            else {
                throw new Exception("Response Type is Invalid [" + parsed.getResponseInfo().getType() + "]" );
            }
            if( ackNak.equals("9") )
                parsed.setString( "CM._AOCRespCode", "000" );
            else
                parsed.setString( "CM._AOCRespCode", "009" );

            parsed.getMessage().position(0);
            parsed.getMessage().get(read);
            logger.debug(new String(read));

            if( ackNak.equals("9") ) {
                msgHis.setMsgCtx( new String(read) );
            }
            else {
                msgHis.setMsgCtx( errMessage );
            }

            comPack.insUpdMsg(safeData, msg.getMacNo(), trx, msg, msgHis);

            /**
             * Client 응답처리
             */
            ctx.writeAndFlush(ctx.alloc().buffer(read.length).writeBytes(read));

            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }
}

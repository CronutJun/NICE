package com.nicetcm.nibsplus.broker.ams.services;

import java.nio.ByteBuffer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.buffer.ByteBuf;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParser;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerConst;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerData;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerLib;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerSpringMain;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerTransaction;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqJob;
import com.nicetcm.nibsplus.broker.ams.AMSBrokerReqInfo;
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
    public void procAckNak( ChannelHandlerContext ctx, AMSBrokerData safeData, MsgParser parsed, AMSBrokerReqJob reqJob, TRmTrx trx, TRmMsg msg, String ackNak, String errMessage ) throws Exception {

        byte[] read = null;
        ByteBuf retBuf = null;

        MsgParser outPsr = parsed;
        AMSBrokerReqInfo reqInfo = new AMSBrokerReqInfo();

        safeData.setSysDate( AMSBrokerLib.getSysDate() );

        safeData.setTXS( amsTX.getTransaction( AMSBrokerTransaction.defAMSTX ));
        safeData.setMsgDate( AMSBrokerLib.getMsgDate(safeData.getSysDate()) );
        safeData.setMsgTime( AMSBrokerLib.getMsgTime(safeData.getSysDate()) );

        msg.setMsgSts( ackNak );
        msg.setMsgCd( AMSBrokerConst.MSG_CD_RSP );
        /**
         * POLL
         */
        if( msg.getSvcCd().equals(AMSBrokerConst.SVC_CD_NTI_POL)) {
            amsTX.rollback(safeData.getTXS());
            return;
        }
        try {
            TRmMsgHis msgHis = new TRmMsgHis();

            outPsr.setString( "CM._AOCMsgCode", msg.getMsgCd() );
            if( outPsr.getResponseInfo().getType() != null
            &&  outPsr.getResponseInfo().getType().equals("COMMON") ) {
                outPsr.setInt( "CM._AOCMsgLen", AMSBrokerConst.COMMON_LEN - AMSBrokerConst.MSG_LEN_INFO_LEN );
                if( ackNak.equals("9") )
                    outPsr.setString( "CM._AOCRespCode", "000" );
                else
                    outPsr.setString( "CM._AOCRespCode", "009" );

                read = new byte[AMSBrokerConst.COMMON_LEN];
                outPsr.getMessage().position(0);
                outPsr.getMessage().get(read);
            }
            else if( outPsr.getResponseInfo().getType() != null
                  &&  outPsr.getResponseInfo().getType().equals("SELF") ) {
                if( ackNak.equals("9") )
                    outPsr.setString( "CM._AOCRespCode", "000" );
                else
                    outPsr.setString( "CM._AOCRespCode", "009" );

                read = new byte[outPsr.getMessage().limit()];
                outPsr.getMessage().position(0);
                outPsr.getMessage().get(read);
            }
            else if( outPsr.getResponseInfo().getType() != null
                    &&  outPsr.getResponseInfo().getType().equals("JSON") ) {
                OutMsgHandler outMsg = (OutMsgHandler)AMSBrokerSpringMain.sprCtx.getBean(String.format("out%s%s", msg.getMsgCd(), msg.getSvcCd()));
                reqInfo.setMsg( ByteBuffer.allocateDirect(MsgCommon.READ_BUF_SIZE) );
                outPsr = MsgParser.getInstance(String.format("%s%s%s.json", MsgCommon.msgProps.getProperty("schema_path"), msg.getMsgCd(), msg.getSvcCd()))
                                              .newMessage(reqInfo.getMsg());
                try {

                    outMsg.outMsgHandle(outPsr, safeData, reqJob, reqInfo, msg);

                    if( ackNak.equals("9") )
                        outPsr.setString( "CM._AOCRespCode", "000" );
                    else
                        outPsr.setString( "CM._AOCRespCode", "009" );
                    logger.debug("Message Length = "  + outPsr.getMessageLength());
                    logger.debug("Last Position = "   + outPsr.lastPosition());
                    reqInfo.getMsg().limit(outPsr.lastPosition());
                    read = new byte[reqInfo.getMsg().limit()];
                    reqInfo.getMsg().position(0);
                    reqInfo.getMsg().get(read);
                    logger.debug(new String(read));
                }
                finally {
                    outPsr.clearMessage();
                }
            }
            else {
                throw new Exception("Response Type is Invalid [" + outPsr.getResponseInfo().getType() + "]" );
            }
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
            while ( reqInfo.getStrm() != null && reqInfo.getStrm().available() > 0 ) {
                read = reqInfo.getStrm().available() > MsgCommon.READ_BUF_SIZE ? new byte[MsgCommon.READ_BUF_SIZE]
                     : new byte[reqInfo.getStrm().available()];
                reqInfo.getStrm().read(read);
                retBuf = ctx.alloc().buffer(read.length);
                retBuf.writeBytes(read);
                logger.debug("Send File size = " + read.length);
                ctx.writeAndFlush(retBuf);
            }
            if( reqInfo.getStrm() != null )
                reqInfo.getStrm().close();

            amsTX.commit(safeData.getTXS());
        }
        catch( Exception e ) {
            amsTX.rollback(safeData.getTXS());
            throw e;
        }
    }
}

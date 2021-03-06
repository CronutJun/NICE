package com.nicetcm.nibsplus.broker.ams;

/*
 * Copyright 2014 The NIBS Project
 *
 * AMS 저널/배포/원격 관리
 *
 * AMSBrokerServerHandler
 *
 * 기기로 부터의 요청을 처리하기 위한 서버소켓 처리 클래스
 *
 * @author  K.D.J
 * @since   2014.05.23
 */

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.*;
import com.nicetcm.nibsplus.broker.ams.model.TRmTrx;
import com.nicetcm.nibsplus.broker.ams.model.TRmMsg;
import com.nicetcm.nibsplus.broker.ams.services.RespAckNakHandler;
import com.nicetcm.nibsplus.broker.ams.services.RcvMsgHandler;

/**
 * Handler implementation for the echo server.
 */
@Sharable
public class AMSBrokerServerHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerServerHandler.class);


    private byte[]    bMsgLen  = new byte[9];
    private byte[]    bMsgType = new byte[8];
    private byte[]    remainBytes;
    private int       iMsgLen, iRemain;
    private AMSBrokerData amsSafeData = new AMSBrokerData();
    private AMSBrokerReqJob reqJob = new AMSBrokerReqJob("SERVER", false);
    private TRmTrx     rmTrx = null;
    private TRmMsg     rmMsg = null;
    private MsgParser  msgPsr;
    private ByteBuffer waitBuf = null;
    private ByteBuffer wrkBuf;
    private boolean   isContinue = false;

    private AMSBrokerBizHandler biz = new AMSBrokerBizHandler();

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.warn("Context name = " + ctx.name());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf;
        if( waitBuf != null && waitBuf.capacity() > 0 ) {
            buf = ctx.alloc().buffer( ((ByteBuf)msg).capacity() + waitBuf.capacity() );
            waitBuf.rewind();
            buf.writeBytes(waitBuf);
            buf.writeBytes((ByteBuf)msg);
            buf.readerIndex(0);
            waitBuf.clear();
            waitBuf = null;
        }
        else {
            buf = (ByteBuf)msg;
        }
        logger.warn("================================================================");
        logger.warn("Channel Read - ByteBuf = {}", msg);
        logger.warn("Thread ID = " + Thread.currentThread().getId());
        logger.warn("className      = " + msg.getClass().getName());
        logger.warn("capacity       = " + buf.capacity());
        logger.warn("readableBytes  = " + buf.readableBytes());
        logger.warn("readerIndex    = {}", buf.readerIndex() );
        logger.warn("readable       = {}", buf.isReadable() );
        logger.warn("isContinue     = " + isContinue);
        logger.warn("================================================================");

        if( !isContinue) {
            /**
             * 선두의 전체 전문길이 정보가 들어오기를 대기한다.
             */
            if( buf.readableBytes() < AMSBrokerConst.MSG_LEN_INFO_LEN ) {
                waitBuf = ByteBuffer.allocateDirect(buf.readableBytes());
                buf.readBytes(waitBuf);
                return;
            }

            buf.markReaderIndex();

            buf.readBytes(bMsgLen);
            logger.warn("First 9 Bytes = " + new String(bMsgLen));
            iMsgLen = Integer.parseInt(new String(bMsgLen)) + AMSBrokerConst.MSG_LEN_INFO_LEN;

            /**
             *  전문타입 정보 대기
             */
            logger.warn("readableBytes#1  = " + buf.readableBytes());
            if( buf.readableBytes() < 8 ) {
                waitBuf = ByteBuffer.allocateDirect(buf.readableBytes());
                buf.readBytes(waitBuf);
                return;
            }

            buf.readBytes(bMsgType);

            msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path") + new String(bMsgType) + ".json");
            logger.warn("readableBytes#2  = " + buf.readableBytes() + ", schema_length = " + msgPsr.getSchemaLength());
            logger.warn("msgPsr's response type = {}", msgPsr.getResponseInfo().getType());
            buf.resetReaderIndex();
            /**
             *  전문파싱 완료 대기
             */
            if( buf.readableBytes() < msgPsr.getSchemaLength() ) {
                waitBuf = ByteBuffer.allocateDirect(buf.readableBytes());
                buf.readBytes(waitBuf);
                return;
            }
            try {
                buf.resetReaderIndex();
                wrkBuf = ByteBuffer.allocateDirect(buf.readableBytes());
                buf.readBytes(wrkBuf);
                msgPsr.parseMessage(wrkBuf);
            }
            catch ( Exception err) {
                logger.error(err.getMessage());
                msgPsr.clearMessage();
                throw err;
            }
            logger.warn("msgPsr's response type = {}", msgPsr.getResponseInfo().getType());

            iRemain = iMsgLen - wrkBuf.limit();

            remainBytes = new byte[wrkBuf.limit() - msgPsr.getMessageLength()];
            wrkBuf.get(remainBytes);

            if( iMsgLen > msgPsr.getMessageLength() ) {
                isContinue = true;
                logger.warn("Continue set msgPsr length = " + msgPsr.getMessageLength());
            }

            /**
             * Thread-ID Setting
             */
            Thread.currentThread().setName(String.format("<T>%s-S-%s:%s", msgPsr.getString("CM._SSTNo").substring(2), Thread.currentThread().getId(), AMSBrokerMain.serverNo));

            rmTrx = new TRmTrx();
            rmMsg = new TRmMsg();

            try {
                RcvMsgHandler rcv = (RcvMsgHandler)AMSBrokerSpringMain.sprCtx.getBean("rcvMsg");
                rcv.rcvMsgHandle( amsSafeData, msgPsr, rmTrx, rmMsg );

                biz.classifyMessage(ctx, msg, msgPsr, reqJob, remainBytes, isContinue);
                if( !isContinue ) {
                    RespAckNakHandler resp = (RespAckNakHandler)AMSBrokerSpringMain.sprCtx.getBean("respAckNak");
                    resp.procAckNak( ctx,  amsSafeData, msgPsr, reqJob, rmTrx, rmMsg, "9", null );
                    msgPsr.clearMessage();
                }
            }
            catch( Exception e ) {
                if( !isContinue ) {
                    RespAckNakHandler resp = (RespAckNakHandler)AMSBrokerSpringMain.sprCtx.getBean("respAckNak");
                    resp.procAckNak( ctx,  amsSafeData, msgPsr, reqJob, rmTrx, rmMsg, "5", e.getMessage() );
                    msgPsr.clearMessage();
                    throw e;
                }
            }

        }
        else {
            try {
                logger.warn("Continue.. msgPsr length = " + msgPsr.getMessageLength());
                wrkBuf = ByteBuffer.allocateDirect(buf.readableBytes());
                buf.readBytes(wrkBuf);
                wrkBuf.position(0);
                remainBytes = new byte[wrkBuf.limit() - wrkBuf.position()];
                wrkBuf.get(remainBytes);

                iRemain = iRemain - wrkBuf.limit();
                logger.warn("iRemain = " + iRemain);

                if( iRemain <= 0 ) isContinue = false;

                biz.classifyMessage(ctx, msg, msgPsr, reqJob, remainBytes, isContinue);
                if( !isContinue ) {
                    RespAckNakHandler resp = (RespAckNakHandler)AMSBrokerSpringMain.sprCtx.getBean("respAckNak");
                    resp.procAckNak( ctx,  amsSafeData, msgPsr, reqJob, rmTrx, rmMsg, "9", null );
                    msgPsr.clearMessage();
                }
            }
            catch( Exception e ) {
                if( !isContinue ) {
                    RespAckNakHandler resp = (RespAckNakHandler)AMSBrokerSpringMain.sprCtx.getBean("respAckNak");
                    resp.procAckNak( ctx,  amsSafeData, msgPsr, reqJob, rmTrx, rmMsg, "5", e.getMessage() );
                    msgPsr.clearMessage();
                    throw e;
                }
            }
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
        //logger.warn("channelReadComplete Context name = " + ctx.name());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        logger.warn("Unexpected exception from downstream. {}", cause.getMessage() );
        for( StackTraceElement se: cause.getStackTrace() )
            logger.warn(se.toString());
        ctx.close();
    }

}
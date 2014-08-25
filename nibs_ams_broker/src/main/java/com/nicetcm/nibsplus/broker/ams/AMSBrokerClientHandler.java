package com.nicetcm.nibsplus.broker.ams;

/*
 * Copyright 2014 The NIBS Project
 *
 * AMSBrokerClient
 *
 * 기기로 요청을 보낼때 기기의 서버소켓과 통신을 하기위한 클라이언트 소켓 객체
 *
 * @author  K.D.J
 * @since   2014.05.23
 */

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.common.MsgParseException;
import com.nicetcm.nibsplus.broker.common.MsgParser;


/**
 * Handler implementation for the echo client.  It initiates the ping-pong
 * traffic between the echo client and server by sending the first message to
 * the server.
 */
public class AMSBrokerClientHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerClientHandler.class);

    private byte[]    bMsgLen  = new byte[9];
    private byte[]    bMsgType = new byte[8];
    private byte[]    remainBytes;
    private int       iMsgLen, iRemain;
    private MsgParser  msgPsr;
    private ByteBuffer wrkBuf;
    private boolean   isContinue = false;

    private AMSBrokerBizHandler biz = new AMSBrokerBizHandler();

    private final AMSBrokerReqJob        reqJob;
    private final BlockingQueue<ByteBuf> ans;

    /**
     * Creates a client-side handler.
     */
    public AMSBrokerClientHandler(AMSBrokerReqJob reqJob, BlockingQueue<ByteBuf> ans) {
        this.reqJob = reqJob;
        this.ans = ans;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf)msg;
        try {
            logger.debug("Channel Read ");
            logger.debug("Thread ID = " + Thread.currentThread().getId());
            logger.debug("className      = " + msg.getClass().getName());
            logger.debug("capacity       = " + buf.capacity());
            logger.debug("readableBytes  = " + buf.readableBytes());
            logger.debug("isContinue     = " + isContinue);

            if( !isContinue) {
                /**
                 * 선두의 전체 전문길이 정보가 들어오기를 대기한다.
                 */
                if( buf.readableBytes() < 9 ) return;

                buf.markReaderIndex();

                buf.readBytes(bMsgLen);
                logger.debug("First 9 Bytes = " + new String(bMsgLen));
                iMsgLen = Integer.parseInt(new String(bMsgLen)) + 9;

                /**
                 *  전문타입 정보 대기
                 */
                logger.debug("readableBytes#1  = " + buf.readableBytes());
                if( buf.readableBytes() < 8 ) {
                    buf.resetReaderIndex();
                    return;
                }

                buf.readBytes(bMsgType);

                msgPsr = MsgParser.getInstance(MsgCommon.msgProps.getProperty("schema_path") + new String(bMsgType) + ".json");
                logger.debug("readableBytes#2  = " + buf.readableBytes() + ", schema_length = " + msgPsr.getSchemaLength());
                /**
                 *  전문파싱 완료 대기
                 */
                try {
                    buf.resetReaderIndex();
                    wrkBuf = ByteBuffer.allocateDirect(buf.readableBytes());
                    buf.readBytes(wrkBuf);

                    byte[] bytes = new byte[wrkBuf.capacity()];
                    wrkBuf.position(0);
                    wrkBuf.get(bytes);
                    logger.debug("parse data = {}", new String(bytes));
                    wrkBuf.position(0);
                    msgPsr.parseMessage(wrkBuf);
                }
                catch ( MsgParseException me) {
                    buf.resetReaderIndex();
                    return;
                }
                catch ( Exception err) {
                    logger.error(err.getMessage());
                    msgPsr.clearMessage();
                }

                iRemain = iMsgLen - wrkBuf.capacity();

                remainBytes = new byte[wrkBuf.capacity() - msgPsr.getMessageLength()];
                wrkBuf.get(remainBytes);

                if( iMsgLen > msgPsr.getMessageLength() ) {
                    isContinue = true;
                    logger.debug("Continue set msgPsr length = " + msgPsr.getMessageLength());
                }

                biz.classifyMessage(ctx,  msg, msgPsr, reqJob, remainBytes, isContinue);
                if( !isContinue ) {
                    ans.put(buf);
                    msgPsr.clearMessage();
                }
            }
            else {
                logger.debug("Continue.. msgPsr length = " + msgPsr.getMessageLength());
                wrkBuf = ByteBuffer.allocateDirect(buf.readableBytes());
                buf.readBytes(wrkBuf);
                wrkBuf.position(0);
                remainBytes = new byte[wrkBuf.capacity() - wrkBuf.position()];
                wrkBuf.get(remainBytes);

                iRemain = iRemain - wrkBuf.capacity();
                logger.debug("iRemain = " + iRemain);

                if( iRemain <= 0 ) isContinue = false;

                biz.classifyMessage(ctx,  msg, msgPsr, reqJob, remainBytes, isContinue);
                if( !isContinue ) {
                    ans.put(buf);
                    msgPsr.clearMessage();
                }
            }
        }
        catch( Exception e ) {
            logger.debug("read Exception: {}", e.getMessage());
            ans.put(buf);
            throw e;
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
       ctx.flush();
       //ctx.close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Close the connection when an exception is raised.
        logger.warn("Unexpected exception from downstream.", cause);
        ctx.close();
    }
}
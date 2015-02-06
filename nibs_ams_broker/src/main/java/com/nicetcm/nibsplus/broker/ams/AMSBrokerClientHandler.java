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
    private ByteBuffer waitBuf = null;
    private ByteBuffer wrkBuf;
    private boolean   isContinue = false;

    private AMSBrokerBizHandler biz = new AMSBrokerBizHandler();

    private final AMSBrokerReqJob reqJob;
    private final BlockingQueue<AMSBrokerClientQData> ans;

    /**
     * Creates a client-side handler.
     */
    public AMSBrokerClientHandler(AMSBrokerReqJob reqJob, BlockingQueue<AMSBrokerClientQData> ans) {
        this.reqJob = reqJob;
        this.ans = ans;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        //ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf;

        Thread.currentThread().setName(String.format("<T>%s-C-%s:%s", reqJob.getMacNo(), Thread.currentThread().getId(), AMSBrokerMain.serverNo));

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

        try {
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
                //byte tData[] = new byte[buf.readableBytes()];
                //buf.readBytes(tData);
                //logger.warn("data = {}", new String(tData));
                //buf.resetReaderIndex();
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

                    byte[] bytes = new byte[wrkBuf.limit()];
                    wrkBuf.position(0);
                    wrkBuf.get(bytes);
                    logger.warn("parse data = {}", new String(bytes));
                    wrkBuf.position(0);
                    msgPsr.parseMessage(wrkBuf);
                }
                catch ( Exception err) {
                    logger.error(err.getMessage());
                    msgPsr.clearMessage();
                    throw err;
                }

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
                Thread.currentThread().setName(String.format("<T>%s-C-%s:%s", msgPsr.getString("CM._SSTNo").substring(2), Thread.currentThread().getId(), AMSBrokerMain.serverNo));

                biz.classifyMessage(ctx,  msg, msgPsr, reqJob, remainBytes, isContinue);
                ans.put(new AMSBrokerClientQData(isContinue, buf));
                if( !isContinue ) {
                    msgPsr.clearMessage();
                }
            }
            else {
                logger.warn("Continue.. msgPsr length = " + msgPsr.getMessageLength());
                wrkBuf = ByteBuffer.allocateDirect(buf.readableBytes());
                buf.readBytes(wrkBuf);
                wrkBuf.position(0);
                remainBytes = new byte[wrkBuf.limit() - wrkBuf.position()];
                wrkBuf.get(remainBytes);

                iRemain = iRemain - wrkBuf.limit();
                logger.warn("iRemain = " + iRemain);

                if( iRemain <= 0 ) isContinue = false;

                biz.classifyMessage(ctx,  msg, msgPsr, reqJob, remainBytes, isContinue);
                ans.put(new AMSBrokerClientQData(isContinue, buf));
                if( !isContinue ) {
                    msgPsr.clearMessage();
                }
            }
        }
        catch( Exception e ) {
            logger.warn("read Exception: {}", e.getMessage());
            ans.put(new AMSBrokerClientQData(false, buf));
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
package com.nicetcm.nibsplus.broker.ams;

/*
 * Copyright 2014 The NIBS Project
 *
 * AMS 기기관리시스템 - AMSBrokerClientHandler
 *
 * 접속한 클라이언트의 요청 처리 객체
 *
 * @author  K.D.J
 * @since   2014.05.23
 */


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.nio.ByteBuffer;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;
import com.nicetcm.nibsplus.broker.ams.rmi.AMSBrokerTimeoutException;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.buffer.ByteBuf;

public class AMSBrokerClient {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerClient.class);
//    private static final Map<String, AMSBrokerClient> clientPool = new HashMap<String, AMSBrokerClient>();

    private final String host;
    private final int port;
    private final AMSBrokerReqJob        reqJob;
    private final BlockingQueue<ByteBuf> ans;
    private ByteBuf reqBuf;

    public AMSBrokerClient(String host, int port, AMSBrokerReqJob reqJob) {
        this.host = host;
        this.port = port;
        this.reqJob = reqJob;
        this.ans  = new LinkedBlockingQueue<ByteBuf>();
    }

    public ByteBuffer outboundCall(ByteBuffer data, InputStream strm, int timeOut) throws Exception {
        // Configure the client.
        ByteBuf rslt = null;
        ByteBuffer ret = null;
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .option(ChannelOption.TCP_NODELAY, true)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(
                             new AMSBrokerClientHandler(reqJob, ans));
                 }
             });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();
            logger.debug("channel opened = " + f.channel().isOpen());
            logger.debug("data size = " + data.limit());
            byte[] read = new byte[data.limit()];
            data.position(0);
            data.get(read);
            logger.debug(new String(read));
            //for(byte a: read)
            // logger.debug(String.format("%x", a) );

            data.position(0);
            reqBuf = f.channel().alloc().buffer(data.limit());
            reqBuf.writeBytes(data);
            logger.debug("going to send");
            f.channel().writeAndFlush(reqBuf);
            while ( strm != null && strm.available() > 0 ) {
                read = strm.available() > MsgCommon.READ_BUF_SIZE ? new byte[MsgCommon.READ_BUF_SIZE]
                     : new byte[strm.available()];
                strm.read(read);
                reqBuf = f.channel().alloc().buffer(read.length);
                reqBuf.writeBytes(read);
                logger.debug("Send File size = " + read.length);
                f.channel().writeAndFlush(reqBuf);
            }
            if( strm != null )
                strm.close();
            boolean interrupted = false;
            try {
                String defTimeOut = MsgCommon.msgProps.getProperty("ams.req.defTimeout");
                if( defTimeOut == null )
                    defTimeOut = "60";
                rslt = ans.poll(timeOut == 0 ? Integer.parseInt(defTimeOut) : timeOut, TimeUnit.SECONDS);
                if( rslt == null ) {
                    throw new AMSBrokerTimeoutException("timeout");
                }
            } catch (InterruptedException e) {
                interrupted = true;
            }

            if (interrupted) {
                Thread.currentThread().interrupt();
            }
            f.channel().close().sync();
            //f.channel().closeFuture().sync();
            logger.debug("answer count = " + ans.size());
            logger.debug("channel opened = " + f.channel().isOpen());


            // Wait until the connection is closed.
            //f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
        rslt.resetReaderIndex();
        ret = ByteBuffer.allocateDirect(rslt.readableBytes());
        rslt.readBytes(ret);
        return ret;
    }
 }
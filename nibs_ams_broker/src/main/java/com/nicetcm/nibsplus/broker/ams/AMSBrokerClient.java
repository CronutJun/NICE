package com.nicetcm.nibsplus.broker.ams;

/*
 * Copyright 2014 The NIBS Project
 *
 * AMS ����/����/���� ����
 *
 *           2014. 05. 23    K.D.J
 */
//import java.util.HashMap;
//import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.nio.ByteBuffer;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.common.MsgCommon;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.buffer.ByteBuf;

/**
 * Sends one message when a connection is open and echoes back any received
 * data to the server.  Simply put, the echo client initiates the ping-pong
 * traffic between the echo client and server by sending the first message to
 * the server.
 */
public class AMSBrokerClient {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerClient.class);
//    private static final Map<String, AMSBrokerClient> clientPool = new HashMap<String, AMSBrokerClient>();
    
    private final String host;
    private final int port;
    private ByteBuf reqBuf;
    private final BlockingQueue<ByteBuf> ans;

    public AMSBrokerClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.ans  = new LinkedBlockingQueue<ByteBuf>();
    }

    public void outboundCall(ByteBuffer data, InputStream strm) throws Exception {
        // Configure the client.
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
                             new AMSBrokerClientHandler(ans));
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

            data.position(0);
            reqBuf = f.channel().alloc().buffer(data.limit());
            reqBuf.writeBytes(data);
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
            boolean interrupted = false;
            for (;;) {
                try {
                    ans.poll(5000L, TimeUnit.MILLISECONDS);
                    break;
                } catch (InterruptedException e) {
                    interrupted = true;
                }
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
    }
 }
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


import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicetcm.nibsplus.broker.ams.rmi.AMSBrokerTimeoutException;
import com.nicetcm.nibsplus.broker.common.MsgCommon;

public class AMSBrokerClient {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerClient.class);

    private static final ConcurrentMap<String, AMSBrokerClient> clientPool = new ConcurrentHashMap<String, AMSBrokerClient>();

    private final String host;
    private final int    port;
    private final BlockingQueue<AMSBrokerClientQData> ans;
    private ByteBuf reqBuf;
    private AMSBrokerReqJob reqJob;
    private int idleTimeOut;

    public static AMSBrokerClient getInstance(String host, int port, AMSBrokerReqJob reqJob) {

        AMSBrokerClient client = null;

        if( clientPool.containsKey(host) ) {
            client =  clientPool.get(host);
            client.setReqJob(reqJob);
        }
        else {
            client = new AMSBrokerClient( host, port, reqJob );
            clientPool.put( host, client );
        }
        return client;

    }

    private AMSBrokerClient(String host, int port, AMSBrokerReqJob reqJob) {
        this.host = host;
        this.port = port;
        this.reqJob = reqJob;
        this.ans  = new LinkedBlockingQueue<AMSBrokerClientQData>();
    }

    private void setReqJob( AMSBrokerReqJob reqJob ) {
        this.reqJob = reqJob;
    }

    public ByteBuffer outboundCall(ByteBuffer data, InputStream strm, int timeOut) throws Exception {
        // Configure the client.
        AMSBrokerClientQData lstRslt = null, fstRslt = null;
        ByteBuffer ret = null;
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            String defTimeOut = MsgCommon.msgProps.getProperty("ams.req.timeout", "180");
            this.idleTimeOut = timeOut == 0 ? Integer.parseInt(defTimeOut) : timeOut;

            Bootstrap b = new Bootstrap();
            b.group(group)
             .channel(NioSocketChannel.class)
             .option(ChannelOption.TCP_NODELAY, true)
             .handler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new AMSBrokerClientHandler(reqJob, ans))
                                  .addLast("idleStateHandler", new IdleStateHandler(idleTimeOut, idleTimeOut, 0))
                                  .addLast("amsBrokerStateHandler", new AMSBrokerStateHandler(reqJob, ans));
                 }
             });

            // Start the client.
            ChannelFuture f = b.connect(host, port).sync();
            logger.warn("channel opened = " + f.channel().isOpen());
            logger.warn("data size = " + data.limit());
            byte[] read = new byte[data.limit()];
            data.position(0);
            data.get(read);
            logger.warn(new String(read));

            data.position(0);
            reqBuf = f.channel().alloc().buffer(data.limit());
            reqBuf.writeBytes(data);
            logger.warn("going to send");
            f.channel().writeAndFlush(reqBuf);

            if( reqJob.getDownComplete() != null )
                reqJob.getDownComplete().reset();

            long tot = 0;
            int  limit = 0;
            while ( strm != null && strm.available() > 0 ) {

                limit = strm.available() > MsgCommon.READ_BUF_SIZE ? MsgCommon.READ_BUF_SIZE : strm.available();
                if( tot < reqJob.getDownWritePos() && (tot + limit) > reqJob.getDownWritePos() )
                    limit = (int)(reqJob.getDownWritePos() - tot);
                read = new byte[limit];
                tot += strm.read(read);

                if( reqJob.getDownComplete() != null )
                    reqJob.getDownComplete().update( read, 0, read.length );

                if( tot > reqJob.getDownWritePos() ) {
                    reqBuf = f.channel().alloc().buffer(read.length);
                    reqBuf.writeBytes(read);
                    logger.warn("Send File size = " + read.length);
                    f.channel().writeAndFlush(reqBuf);
                }

            }
            if( strm != null )
                strm.close();

            for (;;) {
                try {
                    lstRslt = ans.poll(3600, TimeUnit.SECONDS);
                    if( lstRslt == null ) {
                        if( reqJob.getfOut() != null )
                            reqJob.getfOut().close();
                        f.channel().close().sync();
                        throw new AMSBrokerTimeoutException("timeout");
                    }
                    if( lstRslt.isTimeout() ) {
                        if( reqJob.getfOut() != null )
                            reqJob.getfOut().close();
                        f.channel().close().sync();
                        throw new AMSBrokerTimeoutException("timeout");
                    }
                    if( lstRslt.isError() ) {
                        if( reqJob.getfOut() != null )
                            reqJob.getfOut().close();
                        f.channel().close().sync();
                        throw new Exception("Exception is raised at socket channel");
                    }
                    if( fstRslt == null )
                        fstRslt = lstRslt;

                }
                catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                // 필요하다면 바로 밑에 콜백함수를 사용하여 이벤트 처리(Read)할 수 있다.
                if( !lstRslt.isContinue() )
                    break;
            }

            f.channel().close().sync();
            //f.channel().closeFuture().sync();
            logger.warn("answer count = " + ans.size());
            logger.warn("channel opened = " + f.channel().isOpen());


            // Wait until the connection is closed.
            //f.channel().closeFuture().sync();
        } finally {
            // Shut down the event loop to terminate all threads.
            group.shutdownGracefully();
        }
        fstRslt.getClientData().resetReaderIndex();
        ret = ByteBuffer.allocateDirect(fstRslt.getClientData().readableBytes());
        fstRslt.getClientData().readBytes(ret);

        return ret;
    }
 }
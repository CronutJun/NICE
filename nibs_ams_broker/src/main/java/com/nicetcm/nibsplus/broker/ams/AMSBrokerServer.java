package com.nicetcm.nibsplus.broker.ams;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.ChannelFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AMSBrokerServer {

    private static final Logger logger = LoggerFactory.getLogger(AMSBrokerServer.class);
    private static AMSBrokerServer sockServer;

    private final int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBs;
    private ChannelFuture channelFuture;

    public static AMSBrokerServer getServer(int Port) {
        if( sockServer == null )
            sockServer = new AMSBrokerServer( Port );

        return sockServer;
    }

    public static AMSBrokerServer getServer() {

        return sockServer;
    }

    private AMSBrokerServer(int port) {
        this.port = port;

        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();
        serverBs = new ServerBootstrap();
    }

    public void run() throws Exception {
        try {
            serverBs.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class)
             .childHandler(new ChannelInitializer<SocketChannel>() {
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(
                             new AMSBrokerServerHandler());
                 }
             });

            channelFuture = serverBs.bind(port);
            channelFuture.sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void close() {
        try {
            channelFuture.channel().close();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        catch( Exception e ) {
            logger.warn("Server Close Exception: {}", e.getMessage() );
        }
    }
}
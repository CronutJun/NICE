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

    private final int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBs;
    private ChannelFuture channelFuture;

    public AMSBrokerServer(int port) {
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
            channelFuture.sync().channel().close();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        catch( Exception e ) {
            logger.debug("Server Close Exception: {}", e.getMessage() );
        }
    }
}
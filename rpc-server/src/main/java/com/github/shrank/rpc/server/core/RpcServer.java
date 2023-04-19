package com.github.shrank.rpc.server.core;

import com.github.shrank.rpc.server.constant.RpcServerConst;
import com.github.shrank.rpc.server.handler.RpcServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author shrank
 * @version 1.0
 * @description rpc 服务端
 * @date 2023/4/19 17:00
 */
public class RpcServer extends Thread{
    private static final Logger log = LoggerFactory.getLogger(RpcServer.class);

    private final int port;

    public RpcServer() {
        this.port = RpcServerConst.DEFAULT_PORT;
    }

    public RpcServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        //启动服务端
        log.info("RPC服务端启动");
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(); //监听客户端请求
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(); //处理每条链接的读写

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast(new RpcServerHandler());
                        }
                    })
                    // 临时存放已完成三次握手的请求的队列的最大长度。
                    // 如果未设置或所设置的值小于1，Java将使用默认值50。
                    // 如果大于队列的最大长度，请求会被拒绝
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 这个参数只是过一段时间内客户端没有响应，服务端会发送一个 ack 包，以判断客户端是否还活着。
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture channelFuture = serverBootstrap.bind(port).syncUninterruptibly();
            log.info("RPC服务端启动完成，监听【"+port+"】端口");
            channelFuture.channel().closeFuture().syncUninterruptibly();
            log.info("RPC 服务端关闭完成");
        } catch (Exception e) {
            log.error("RPC 服务yichang", e);
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}

package com.github.shrank.rpc.client.core;

import com.github.shrank.rpc.client.constant.RpcClientConst;
import com.github.shrank.rpc.client.decoder.CalculateResponseDecoder;
import com.github.shrank.rpc.client.encoder.CalculateRequestEncoder;
import com.github.shrank.rpc.client.handler.RpcClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shrank
 * @version 1.0
 * @description rpc客户端
 * @date 2023/4/19 20:23
 */
public class RpcClient extends Thread {

    private static final Logger log = LoggerFactory.getLogger(RpcClient.class);
    private final int port;
    private final String host;

    public RpcClient(int port, String host) {
        this.port = port;
        this.host = host;
    }

    public RpcClient() {
        this(RpcClientConst.DEFAULT_PORT, RpcClientConst.DEFAULT_HOST);
    }

    @Override
    public void run() {
        //启动服务端
        log.info("RPC客户端启动");
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            ChannelFuture channelFuture = bootstrap.group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<Channel>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline()
                                    .addLast(new LoggingHandler(LogLevel.INFO))
                                    .addLast(new CalculateRequestEncoder())
                                    .addLast(new CalculateResponseDecoder())
                                    .addLast(new RpcClientHandler());
                        }
                    })
                    .connect(host, port)
                    .syncUninterruptibly();
            log.info("RPC 服务启动客户端完成，监听端口：" + port);
            channelFuture.channel().closeFuture().syncUninterruptibly();
            log.info("RPC 服务开始客户端已关闭");
        } catch (Exception e) {
            log.error("RPC 客户端遇到异常", e);
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}

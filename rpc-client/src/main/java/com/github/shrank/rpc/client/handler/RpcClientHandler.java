package com.github.shrank.rpc.client.handler;

import com.github.shrank.rpc.common.model.CalculateRequest;
import com.github.shrank.rpc.common.model.CalculateResponse;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shrank
 * @version 1.0
 * @description
 * @date 2023/4/19 20:46
 */
public class RpcClientHandler extends SimpleChannelInboundHandler {
    private static final Logger log = LoggerFactory.getLogger(RpcClientHandler.class);

    /**
     * TCP连接上时，就会执行这个方法，在这个方法里直接发送请求，进行测试
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        CalculateRequest request = new CalculateRequest(1, 2);
        ctx.writeAndFlush(request);
        log.info("[Client] request is :{}", request);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        CalculateResponse response = (CalculateResponse) msg;
        log.info("[Client] response is :{}", response);
    }
}

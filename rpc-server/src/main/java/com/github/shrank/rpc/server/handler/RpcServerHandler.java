package com.github.shrank.rpc.server.handler;

import com.github.shrank.rpc.common.model.CalculateRequest;
import com.github.shrank.rpc.common.model.CalculateResponse;
import com.github.shrank.rpc.server.service.CalculatorService;
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
 * @date 2023/4/19 19:43
 */
public class RpcServerHandler extends SimpleChannelInboundHandler {
    private static final Logger log = LoggerFactory.getLogger(RpcServerHandler.class);
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        final String id = ctx.channel().id().asLongText();
        CalculateRequest request = (CalculateRequest) msg;

        log.info("[Server] receive channel {} request: {} from ", id, request);

        CalculatorService calculator = new CalculatorService();
        CalculateResponse response = calculator.sum(request);
        ctx.writeAndFlush(response);
        log.info("[Server] channel {} response {}", id, response);
    }

    //一旦通道变为活动状态(对于TCP，这意味着通道已连接)，就会调用channelActive(...)，而一旦收到消息，就会调用channelRead(...)
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final String id = ctx.channel().id().asLongText();
        log.info("[Server] channel {} connected " + id);
    }
}

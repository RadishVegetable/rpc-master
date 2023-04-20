package com.github.shrank.rpc.client.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.concurrent.EventExecutorGroup;

/**
 * @author shrank
 * @version 1.0
 * @description
 * @date 2023/4/19 20:46
 */
public class RpcClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }
}

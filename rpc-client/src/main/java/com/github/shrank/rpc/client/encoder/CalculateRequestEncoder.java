package com.github.shrank.rpc.client.encoder;

import com.github.shrank.rpc.common.model.CalculateRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author shrank
 * @version 1.0
 * @description 编码
 * @date 2023/4/20 16:18
 */
public class CalculateRequestEncoder extends MessageToByteEncoder<CalculateRequest> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CalculateRequest calculateRequest, ByteBuf byteBuf) throws Exception {
        int a = calculateRequest.getA();
        int b = calculateRequest.getB();
        byteBuf.writeInt(a);
        byteBuf.writeByte(b);
    }
}

package com.github.shrank.rpc.server.encoder;

import com.github.shrank.rpc.common.model.CalculateResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author shrank
 * @version 1.0
 * @description
 * @date 2023/4/20 16:33
 */
public class CalculateResponseEncoder extends MessageToByteEncoder<CalculateResponse> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, CalculateResponse calculateResponse, ByteBuf byteBuf) throws Exception {
        boolean success = calculateResponse.isSuccess();
        int sum = calculateResponse.getSum();
        byteBuf.writeBoolean(success);
        byteBuf.writeInt(sum);
    }
}

package com.github.shrank.rpc.client.decoder;

import com.github.shrank.rpc.common.model.CalculateResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author shrank
 * @version 1.0
 * @description
 * @date 2023/4/20 16:29
 */
public class CalculateResponseDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        boolean success = byteBuf.readBoolean();
        int sum = byteBuf.readInt();

        CalculateResponse res = new CalculateResponse(success, sum);
        list.add(res);
    }
}

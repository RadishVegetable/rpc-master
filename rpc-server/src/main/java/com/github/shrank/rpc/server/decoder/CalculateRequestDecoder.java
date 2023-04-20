package com.github.shrank.rpc.server.decoder;



import com.github.shrank.rpc.common.model.CalculateRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author shrank
 * @version 1.0
 * @description
 * @date 2023/4/20 16:32
 */
public class CalculateRequestDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int a = byteBuf.readInt();
        int b = byteBuf.readInt();
        CalculateRequest request = new CalculateRequest(a, b);
        list.add(request);
    }
}

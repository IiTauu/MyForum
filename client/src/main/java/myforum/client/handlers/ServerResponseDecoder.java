package myforum.client.handlers;

import base.packet.Packet;
import base.request.ClientRequest;
import base.response.ConnectionResponse;
import base.response.ServerResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.concurrent.EventExecutorGroup;

import java.util.List;

public class ServerResponseDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        ServerResponse response = Packet.decodeResponse(byteBuf);
        if (response.getResponseType() != ConnectionResponse.class || !response.isSuccess())
            System.out.println("Receive a " + response.getResponseType() + "(" + response.isSuccess() + ")"
                    + " from " + ctx.channel().remoteAddress());
        out.add(response);
    }
}

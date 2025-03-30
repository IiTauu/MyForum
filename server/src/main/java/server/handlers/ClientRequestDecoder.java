package server.handlers;

import base.packet.Packet;
import base.request.ClientRequest;
import base.request.ConnectionRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class ClientRequestDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception {
        ClientRequest request = Packet.decodeRequest(byteBuf);
        if (request.getResponseType() != ConnectionRequest.class)
            System.out.println("Receive a " + request.getResponseType() + " from " + ctx.channel().remoteAddress());
        out.add(request);
    }
}

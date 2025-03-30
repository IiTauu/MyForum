package server.handlers;

import base.packet.Packet;
import base.response.ConnectionResponse;
import base.response.LoginResponse;
import base.response.ServerResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

public class ServerResponseEncoder extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ServerResponse) {
            ServerResponse response = (ServerResponse) msg;
            if (response.getResponseType() != ConnectionResponse.class || !response.isSuccess())
                System.out.println("send a " + response.getResponseType() + "(" + response.isSuccess() + ")"
                        + " to " + ctx.channel().remoteAddress());
            ByteBuf out = Packet.encode(response);
            ctx.write(out, promise);
        } else {
            ctx.write(msg, promise);
        }
    }
}

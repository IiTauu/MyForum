package myforum.client.handlers;

import base.packet.Packet;
import base.request.ClientRequest;
import base.request.ConnectionRequest;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.util.concurrent.EventExecutorGroup;

public class ClientRequestEncoder extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if (msg instanceof ClientRequest) {
            ClientRequest request = (ClientRequest) msg;
            if (request.getResponseType() != ConnectionRequest.class)
                System.out.println("send a " + request.getResponseType() + " to server");
            ByteBuf out = Packet.encode(request);
            ctx.write(out, promise);
        } else {
            ctx.write(msg, promise);
        }
    }
}

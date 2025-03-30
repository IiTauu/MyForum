package myforum.client.handlers;

import base.request.PostRequest;
import base.request.TestRequest;
import base.response.ServerResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ConnectionTestHandler extends SimpleChannelInboundHandler<ServerResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ServerResponse serverResponse) throws Exception {
        System.out.println("received server response: "+serverResponse.isSuccess());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("send a test request");
        ctx.writeAndFlush(new TestRequest("This is a test request"));
    }
}

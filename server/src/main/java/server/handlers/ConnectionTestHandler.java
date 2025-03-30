package server.handlers;

import base.request.ClientRequest;
import base.request.PostRequest;
import base.request.RequestType;
import base.request.TestRequest;
import base.response.TestResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ConnectionTestHandler extends SimpleChannelInboundHandler<ClientRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ClientRequest request) throws Exception {
        System.out.println("connection from" + ctx.channel().remoteAddress());
        if (request.getRequestCode() == RequestType.TEST_REQUEST) {
            System.out.println("successful connection");
            TestRequest testRequest = (TestRequest) request;
            ctx.writeAndFlush(new TestResponse("This is a test response"));
        }
    }
}

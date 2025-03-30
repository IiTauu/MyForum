package server;

import server.handlers.ClientRequestDecoder;
import server.handlers.ForumServerHandler;
import server.handlers.HeartbeatServerHandler;
import server.handlers.ServerResponseEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ForumServer {

    private static int port = 8080;

    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }));
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ServerChannelInitializer());

            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println("Server is running on port " + port);
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline pipeline = socketChannel.pipeline();
            //入站管线
            pipeline.addLast(new LengthFieldBasedFrameDecoder(102400, 0, 4, 0, 4));
            pipeline.addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
            pipeline.addLast(new ClientRequestDecoder());
            pipeline.addLast(new HeartbeatServerHandler());
            //测试连接用Handler
//            pipeline.addLast(new ConnectionTestHandler());
            pipeline.addLast(new ForumServerHandler());
            //出站管线
            pipeline.addFirst(new ServerResponseEncoder());
            pipeline.addFirst(new LengthFieldPrepender(4));
        }
    }
}

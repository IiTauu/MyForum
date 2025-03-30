package myforum.client;

import base.model.User;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;
import myforum.client.handlers.*;
import myforum.ui.ClientApplication;


public class ForumClient {
    public static void main(String[] args) throws InterruptedException {
        new ForumClient("localhost", 8080).start();
    }

    //region attributes user channel
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //endregion

    private final String host;
    private final int port;

    public ForumClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws InterruptedException {
        // 创建线程池
        EventLoopGroup group = new NioEventLoopGroup();
        //保证关闭
        Runtime.getRuntime().addShutdownHook(new Thread(group::shutdownGracefully));
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer());

            ChannelFuture future = bootstrap.connect(host, port).sync();
            if (future.isSuccess()) {
                ClientApplication.app.setChannel(future.channel());
                System.out.println("Connected to server: " + host + ":" + port);
            } else {
                System.err.println("Failed to connect to server: " + host + ":" + port);
                Throwable cause = future.cause();
                if (cause != null) {
                    System.out.println(cause.getMessage());
                }
            }
            // 等待关闭
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }

    private class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline pipeline = socketChannel.pipeline();
            //入站管线
            pipeline.addLast(new LengthFieldBasedFrameDecoder(102400, 0, 4, 0, 4));
            pipeline.addLast(new IdleStateHandler(0, 5, 0));
            pipeline.addLast(new ServerResponseDecoder());
            pipeline.addLast(new HeartbeatClientHandler());
    //        pipeline.addLast(new ConnectionTestHandler());
            pipeline.addLast(new ForumClientHandler());
            //出战管线
            pipeline.addFirst(new ClientRequestEncoder());
            pipeline.addFirst(new LengthFieldPrepender(4));
        }
    }
}

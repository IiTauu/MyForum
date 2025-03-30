package server.handlers;

import base.model.User;
import base.request.ConnectionRequest;
import base.request.LoginRequest;
import base.response.ConnectionResponse;
import base.response.LoginResponse;
import server.ChannelManager;
import server.business.Dispatcher;
import base.request.ClientRequest;

import base.response.ServerResponse;
import server.business.UserState;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import server.repository.manager.RegisterManager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForumServerHandler extends SimpleChannelInboundHandler<ClientRequest> {
    //每一个Channel有一个线程池来处理业务操作
    private final ExecutorService sharedExecutor = Executors.newFixedThreadPool(10);

    {
        //保证业务线程池在异常情况下也能关闭
        Runtime.getRuntime().addShutdownHook(
                new Thread(sharedExecutor::shutdown)
        );
    }

    private int userId = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ClientRequest request) throws Exception {
        sharedExecutor.submit(() -> {
            //检查是否占线
            if (userId != 0 && ctx.channel() != ChannelManager.getChannelById(userId)) {
                ctx.channel().close();
            }
            //执行业务操作
            Dispatcher dispatcher = new Dispatcher();
            ServerResponse response = dispatcher.dispatch(request);
            //登录成功后操作
            if (response.getResponseType() == LoginResponse.class && response.isSuccess()) {
                System.out.println("Bind user with channel");
                userId = ((LoginResponse) response).getUser().getUserId();
                ChannelManager.registerChannel(userId, ctx.channel());
            }
            ctx.writeAndFlush(response);
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+" is connected");
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel is closed: " + ctx.channel().remoteAddress());
        //关闭业务线程池
        sharedExecutor.shutdown();

        if (userId == 0)
            super.channelInactive(ctx);
        //更新注册表中用户状态，通道已关闭，不在乎是否阻塞
        try {
            RegisterManager registerManager = new RegisterManager();
            registerManager.updateState(userId, UserState.OFFLINE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //移除映射
        ChannelManager.removeChannel(userId, ctx.channel());
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println(ctx.channel().remoteAddress() + " closed the connection");
        ctx.close();
    }
}

package myforum.client.handlers;

import base.request.ConnectionRequest;
import base.response.ServerResponse;
import io.netty.channel.*;
import javafx.application.Platform;
import myforum.client.business.Dispatcher;
import myforum.ui.ClientApplication;
import myforum.ui.utils.Generator;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForumClientHandler extends SimpleChannelInboundHandler<ServerResponse> {
    //每一个Channel有一个线程池来处理业务操作
    //因为是客户端，只开一个线程，避免线程冲突
    private final ExecutorService sharedExecutor = Executors.newFixedThreadPool(1);

    {
        //保证业务线程池在异常情况下也能关闭
        Runtime.getRuntime().addShutdownHook(
                new Thread(sharedExecutor::shutdown)
        );
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ServerResponse response) throws Exception {
        sharedExecutor.submit(() -> {
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.dispatch(response);
        });
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Request for connection");
        ConnectionRequest connectionRequest = new ConnectionRequest();
        ctx.writeAndFlush(connectionRequest);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Channel is closed");
        //关闭业务线程池
        sharedExecutor.shutdown();
        Platform.runLater(()-> {
            try {
                ClientApplication.app.closeOtherStages();
                if(ClientApplication.app.getMainStage()!=null)
                  Generator.turnToErrorStage("""
            失去与服务器的连接。。。
            
            可能原因：
            1. 账户已在其他地方登录，请确保账户安全；
            2. 服务器关闭。""",
                          ClientApplication.app.getMainStage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Something went wrong");
        ctx.close();
    }
}

package server;

import base.response.ConnectionResponse;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChannelManager {
    // 整个服务端一个全局映射表存储当前活跃的Channel所绑定用户的id
    private static final ConcurrentMap<Integer, Channel> channelMap = new ConcurrentHashMap<>();

    private static final AttributeKey<Integer> USER_ID_KEY = AttributeKey.valueOf("USER_ID");

    public static void registerChannel(int id, Channel channel) {
        channelMap.compute(id, (key, oldChannel) -> {
            if (oldChannel != null) {
                System.out.println("Close old channel from " + oldChannel.remoteAddress());
                oldChannel.writeAndFlush(new ConnectionResponse());
            }
            channel.attr(USER_ID_KEY).set(id);
            System.out.println("Registered channel from " + channel.remoteAddress());
            return channel;
        });
    }

    public static Channel getChannelById(int id) {
        return channelMap.get(id);
    }

    public static void removeChannel(int id, Channel channel) {
        channelMap.remove(id, channel);
    }

}

package com.luo.netty.manager;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;


/**
 * @author luoliyuan
 * @version 1.0
 * @description
 * @date 2021/8/18 13:50
 */
@Slf4j
public class ClientFactory {

    /**
     * 客户端连接
     */
    private static Bootstrap bootstrap;

    /**
     * 服务端连接
     */
    private static ServerBootstrap serverBootstrap;

    public static void init(NettyBean nettyBean) {
        if (nettyBean.getConnectType().equals(ConnectOption.Client.getType())) {
            bootstrap = NettyClient.getBootstrap();
            if (nettyBean.getFuture() == null) {
                nettyBean.setFuture(createFuture(nettyBean));
            }
        } else if (nettyBean.getConnectType().equals(ConnectOption.Server.getType())) {
            serverBootstrap = NettyServer.init(nettyBean.getIp(), nettyBean.getPort());
        }
    }

    /**
     * 创建管道
     *
     * @param nettyBean
     * @return
     */
    private static ChannelFuture createFuture(NettyBean nettyBean) {
        return bootstrap.connect(nettyBean.getIp(), nettyBean.getPort());
    }

    /**
     * 添加监听事件
     *
     * @param listener
     */
    public static void addListener(ChannelListener listener) {
        listener.getNettyBean().getFuture().addListener(listener);
    }

    /**
     * 添加处理器
     *
     * @param handler
     */
    public static void addHandler(ClientHandler handler) {
        handler.getNettyBean().getFuture().channel().pipeline().addLast(handler);
    }

    /**
     * 写数据
     *
     * @param channel
     * @param bytes
     */
    public static void writeAsync(Channel channel, byte[] bytes) {
        try {
            if (channel != null && channel.isActive()) {
                channel.writeAndFlush(Unpooled.buffer().writeBytes(bytes));
            }
        } catch (Exception e) {
            e.printStackTrace();
            // TODO 记录异常
        }
    }
}

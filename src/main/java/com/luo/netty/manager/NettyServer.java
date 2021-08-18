package com.luo.netty.manager;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * @author luoliyuan
 * @version 1.0
 * @description Netty服务端
 * @date 2021/8/17 16:40
 */
public class NettyServer {

    private static ServerBootstrap serverBootstrap;

    public static ServerBootstrap init(String ip, Integer port) {
        serverBootstrap = new ServerBootstrap();
        // parentGroup用来处理accept事件，childGroup用来处理通道的读写事件
        // parentGroup获取客户端连接，连接接收到之后再将连接转发给childGroup去处理
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        try {
            serverBootstrap.group(parentGroup, childGroup);
            // 用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。
            // 用来初始化服务端可连接队列
            // 服务端处理客户端连接请求是按顺序处理的，所以同一时间只能处理一个客户端连接，多个客户端来的时候，服务端将不能处理的客户端连接请求放在队列中等待处理，backlog参数指定了队列的大小。
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            serverBootstrap.childOption(ChannelOption.TCP_NODELAY, true);
            serverBootstrap.channel(NioServerSocketChannel.class);
            //第3步绑定handler，处理读写事件，ChannelInitializer是给通道初始化
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel channel) throws Exception {
                    channel.pipeline().addLast(new ServerHandler())
                            .addLast(new LineBasedFrameDecoder(1024))
                            .addLast(new StringDecoder())
                            // TODO 读超时时间 写超时时间 所有超时时间
                            .addLast(new IdleStateHandler(5000, 5000, 5000));
                }
            });
            // 绑定ip和端口
            ChannelFuture future = serverBootstrap.bind(ip, port).sync();
            System.out.println("服务端启动：ip----" + ip + "，端口----" + port);
            //当通道关闭了，就继续往下走
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            // TODO 记录日志
        } finally {
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
        }
        return null;
    }
}

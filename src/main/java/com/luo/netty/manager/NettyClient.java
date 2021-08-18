package com.luo.netty.manager;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * @author luoliyuan
 * @version 1.0
 * @description Netty客户端
 * @date 2021/8/17 16:37
 */
public class NettyClient {

    private static Bootstrap bootstrap;

    public static Bootstrap getBootstrap() {
        if (bootstrap == null) {
            init();
        }
        return bootstrap;
    }

    private static void init() {
        bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup());
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.TCP_NODELAY, true);
        bootstrap.handler(new ChannelInitializer<NioSocketChannel>() {  //通道是NioSocketChannel
            @Override
            protected void initChannel(NioSocketChannel channel) throws Exception {
                //字符串编码器，一定要加在ClientHandler 的上面
                channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
                channel.pipeline().addLast(new StringDecoder());
            }
        });
    }
}

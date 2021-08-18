package com.luo.netty.manager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * @author luoliyuan
 * @version 1.0
 * @description 服务端处理类
 * @date 2021/8/17 17:13
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端接收到客户端请求");
        if (msg instanceof ByteBuf) {
            System.out.println("我是服务端，收到客户端" + ctx.channel().id() + "消息:" + ((ByteBuf) msg).toString(Charset.defaultCharset()));
        } else {
            System.out.println("我是服务端，收到客户端" + ctx.channel().id() + "消息:" + msg);
        }
        // 回复消息
        ctx.channel().writeAndFlush(Unpooled.buffer().writeBytes("你好，现在向你发送数据".getBytes()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("接入新的Channel,id = " + ctx.channel().id());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("服务端断开连接");
    }
}

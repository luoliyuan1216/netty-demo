package com.luo.netty.manager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author luoliyuan
 * @version 1.0
 * @description 客户端处理类
 * @date 2021/8/17 16:54
 */
@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler {

    @Getter
    private NettyBean nettyBean;

    public ClientHandler(NettyBean nettyBean) {
        this.nettyBean = nettyBean;
    }

    /**
     * 当从服务端收到消息时触发
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端接收到服务端回复");
        if (msg instanceof ByteBuf) {
            String value = ((ByteBuf) msg).toString(Charset.defaultCharset());
            System.out.println("收到来自服务端的重型四向车边缘数据:" + value);
        }

        AttributeKey<String> key = AttributeKey.valueOf("ServerData");
        ctx.channel().attr(key).set("客户端处理完毕");

        //把客户端的通道关闭
        ctx.channel().close();
    }

    /**
     * 建立连接时触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端建立连接");
        //发送数据给服务器
        ctx.channel().writeAndFlush(Unpooled.buffer().writeBytes("请给我重型四向车数据".getBytes()));
        System.out.println("客户端发送请求成功");
    }


    /**
     * 建立新连接时触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    /**
     * 连接断开时触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接");
    }

    /**
     * 捕获到异常时触发
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    /**
     * 心跳检测
     *
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }
}

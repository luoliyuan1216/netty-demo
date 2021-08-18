package com.luo.netty.device;

import com.luo.netty.manager.ClientFactory;
import com.luo.netty.manager.ClientHandler;
import com.luo.netty.manager.NettyBean;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.nio.charset.Charset;

/**
 * @author luoliyuan
 * @version 1.0
 * @description
 * @date 2021/8/18 15:57
 */
public class DeviceClientHandler extends ClientHandler {


    public DeviceClientHandler(NettyBean nettyBean) {
        super(nettyBean);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端接收到服务端回复");
        if (msg instanceof ByteBuf) {
            String value = ((ByteBuf) msg).toString(Charset.defaultCharset());
            System.out.println("收到来自服务端的数据:" + value);
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("来自Handler----客户端建立连接");
        //发送数据给服务器
        ClientFactory.writeAsync(ctx.channel(), "请向我发送数据".getBytes());
        System.out.println("客户端发送请求成功");
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("来自Handler----客户端断开连接");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("来自Handler----出错了");
        cause.printStackTrace();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {

    }
}

package com.luo.netty.device;

import com.luo.netty.manager.ChannelListener;
import com.luo.netty.manager.NettyBean;
import io.netty.channel.ChannelFuture;

/**
 * @author luoliyuan
 * @version 1.0
 * @description
 * @date 2021/8/18 16:27
 */
public class DeviceChannelListener extends ChannelListener {
    public DeviceChannelListener(NettyBean nettyBean) {
        super(nettyBean);
    }

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        if (!future.isSuccess()) {
            System.out.println("来自Listener----服务器连接失败");
        } else {
            System.out.println("来自Listener----服务器连接成功");
        }
    }
}

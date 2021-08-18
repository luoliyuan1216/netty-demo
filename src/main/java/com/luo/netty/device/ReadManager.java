package com.luo.netty.device;

import com.luo.netty.manager.ClientFactory;
import com.luo.netty.manager.NettyBean;

/**
 * @author luoliyuan
 * @version 1.0
 * @description
 * @date 2021/8/18 17:07
 */
public class ReadManager {

    public static void read() {
        NettyBean<User> nettyBean = new NettyBean("127.0.0.1", 8019, "client");
        ClientFactory.init(nettyBean);
        ClientFactory.addHandler(new DeviceClientHandler(nettyBean));
        ClientFactory.addListener(new DeviceChannelListener(nettyBean));
        ClientFactory.writeAsync(nettyBean.getFuture().channel(), "hello,netty".getBytes());
    }
}

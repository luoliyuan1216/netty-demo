package com.luo.netty.manager;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author luoliyuan
 * @version 1.0
 * @description
 * @date 2021/8/18 15:29
 */
@Slf4j
public class ChannelListener implements ChannelFutureListener {

    @Getter
    private NettyBean nettyBean;

    public ChannelListener(NettyBean nettyBean) {
        this.nettyBean = nettyBean;
    }

    @Override
    public void operationComplete(ChannelFuture future) throws Exception {

    }
}

package com.luo.netty.manager;

import io.netty.channel.ChannelFuture;
import lombok.Data;
import lombok.NonNull;

/**
 * @author luoliyuan
 * @version 1.0
 * @description
 * @date 2021/8/18 13:41
 */
@Data
public class NettyBean<T> {

    public NettyBean(@NonNull String ip, @NonNull Integer port, @NonNull String connectType) {
        this.ip = ip;
        this.port = port;
        this.connectType = connectType;
    }

    private ChannelFuture future;
    private T data;
    private String ip;
    private Integer port;
    /**
     * client/server
     */
    private String connectType;
}

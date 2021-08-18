package com.luo.netty.manager;

/**
 * @author luoliyuan
 * @version 1.0
 * @description
 * @date 2021/8/18 14:19
 */
public enum ConnectOption {
    /**
     * 客户端形式连接
     */
    Client("client"),
    /**
     * 服务端形式连接
     */
    Server("server");

    private String type;

    ConnectOption(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

package com.saike.ucm.client.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by huawei on 11/17/15.
 */
public class UcmServer {

    private String host;
    private int port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

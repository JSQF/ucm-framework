package com.saike.ucm.web.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by huawei on 12/30/15.
 */
public class ListEnvironmentIpRecord {

    private Integer id;
    private Integer environmentId;
    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Integer environmentId) {
        this.environmentId = environmentId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

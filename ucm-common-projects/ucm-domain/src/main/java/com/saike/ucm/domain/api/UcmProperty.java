package com.saike.ucm.domain.api;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * Created by huawei on 11/17/15.
 */
public class UcmProperty {

    private String name;
    private String value;
    private boolean display;
    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isDisplay() {
        return display;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}

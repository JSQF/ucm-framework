package com.saike.ucm.domain;

/**
 * Created by huawei on 12/26/15.
 */
public enum EventType {

    USER_LOGIN(0, "用户登录"), ADD_USER(1, "添加用户"),
    ADD_PROJECT(2, "添加项目");

    private String description;
    private int eventType;

    EventType(int eventType, String description) {
        this.eventType = eventType;
        this.description = description;
    }

    public static EventType getEventType(int eventType) {
        switch (eventType) {
            case 0:
                return USER_LOGIN;
            case 1:
                return ADD_USER;
            case 2:
                return ADD_PROJECT;
            default:
                return null;
        }
    }
}

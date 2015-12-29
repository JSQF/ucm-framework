package com.saike.ucm.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huawei on 12/27/15.
 */
public enum ProjectType {

    WEB(0, "Web项目"), VENUS(1, "Venus项目"), REDIS(2, "Redis项目"), MONGO(3, "MongoDB项目"), ACTIVEMQ(4, "ActiveMQ项目");

    private Integer type;
    private String desc;

    ProjectType(Integer type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static ProjectType getProjectType(int type) {
        switch (type) {
            case 0:
                return WEB;
            case 1:
                return VENUS;
            case 2:
                return REDIS;
            case 3:
                return MONGO;
            case 4:
                return ACTIVEMQ;
            default:
                return null;
        }
    }

    public static Map<Integer, String> getProjectTypeMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(WEB.type, WEB.desc);
        map.put(VENUS.type, VENUS.desc);
        map.put(REDIS.type, REDIS.desc);
        map.put(MONGO.type, MONGO.desc);
        map.put(ACTIVEMQ.type, ACTIVEMQ.desc);
        return map;
    }

    public static Map<Integer, String> getNormalProjectTypeMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(WEB.type, WEB.desc);
        map.put(VENUS.type, VENUS.desc);
        return map;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

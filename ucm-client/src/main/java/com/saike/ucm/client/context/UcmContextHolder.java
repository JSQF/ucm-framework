package com.saike.ucm.client.context;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huawei on 11/17/15.
 */
public final class UcmContextHolder {

    private static UcmContextHolder holder = new UcmContextHolder();
    private Map<String, String> maps = new HashMap<String, String>();

    private UcmContextHolder() {

    }

    public static UcmContextHolder getHolder() {
        return holder;
    }

    public void put(String key, String value) {
        maps.put(key, value);
    }

    public String get(String key) {
        return maps.get(key);
    }


}

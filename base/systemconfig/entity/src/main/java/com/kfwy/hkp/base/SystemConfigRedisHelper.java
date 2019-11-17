package com.kfwy.hkp.base;

public class SystemConfigRedisHelper {

    public static final String getConfigKey(String key) {
        return "kfwy:base:config:" + key;
    }

}

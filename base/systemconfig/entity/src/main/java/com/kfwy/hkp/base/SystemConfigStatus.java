package com.kfwy.hkp.base;


import com.gniuu.framework.exception.IllegalArgsException;

public enum SystemConfigStatus {

    YouXiao(1, "有效"),
    WuXiao(2, "无效");

    private int code;
    private String name;

    private SystemConfigStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static SystemConfigStatus getCode(int code) {
        for (SystemConfigStatus temp : SystemConfigStatus.values()) {
            if (temp.getCode() == code) {
                return temp;
            }
        }
        throw new IllegalArgsException("未能找到匹配的SystemConfigStatus:" + code);
    }
}

package com.kfwy.hkp.quit.entity.enums;

import com.kfwy.hkp.common.exception.RemoveStackBusinessException;

/**
 * 数据转移类型 枚举
 * 此枚举与erp互通 若修改erp最好也改
 */
public enum TransferType {

    House(1,"房源"),
    Customer(2,"客户"),
    Owner(3,"业主"),
    PartTime(4,"兼职");

    private int code;
    private String name;

    private TransferType(int code, String name){
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

    public static TransferType getCode(int code) {
        for (TransferType status : TransferType.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new RemoveStackBusinessException ("未找到对应的枚举" + code);
    }

}

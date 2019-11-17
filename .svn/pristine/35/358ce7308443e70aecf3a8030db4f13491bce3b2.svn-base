package com.kfwy.hkp.payment.enums;


public enum PaymentType {
    ZhiFuBao("1", "支付宝"),
    WeiXin("2", "微信");

    private String code;
    private String name;

    private PaymentType(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PaymentType getType(String code) {
        for (PaymentType t : PaymentType.values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        throw new IllegalArgumentException(String.format("支付类型{%s}未被定义", code));
    }
}

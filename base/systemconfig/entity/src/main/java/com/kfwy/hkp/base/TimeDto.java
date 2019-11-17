package com.kfwy.hkp.base;


import com.gniuu.framework.service.AbstractDto;

public class TimeDto extends AbstractDto {

    private Integer afterNumber;
    private String afterFormat;

    public Integer getAfterNumber() {
        return afterNumber;
    }

    public void setAfterNumber(Integer afterNumber) {
        this.afterNumber = afterNumber;
    }

    public String getAfterFormat() {
        return afterFormat;
    }

    public void setAfterFormat(String afterFormat) {
        this.afterFormat = afterFormat;
    }

}

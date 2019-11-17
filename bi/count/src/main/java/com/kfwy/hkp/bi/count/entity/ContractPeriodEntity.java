package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Create By lzp on 2019/6/21
 */
public class ContractPeriodEntity extends BaseEntity {

    /**
     * 合同月份
     */
    private String contractMonth;
    /**
     * 合同年份
     */
    private String contractYear;
    /**
     * 合同数量
     */
    private int contractNum;

    public String getContractMonth() {
        return contractMonth;
    }

    public void setContractMonth(String contractMonth) {
        this.contractMonth = contractMonth;
    }

    public String getContractYear() {
        return contractYear;
    }

    public void setContractYear(String contractYear) {
        this.contractYear = contractYear;
    }

    public int getContractNum() {
        return contractNum;
    }

    public void setContractNum(int contractNum) {
        this.contractNum = contractNum;
    }
}

package com.kfwy.hkp.crm.customer.entity;

import com.gniuu.framework.entity.BaseEntity;

public class FocusCusReceiveDeptLogEntity extends BaseEntity {

    protected String cusCode;

    protected String deptCodes;

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getDeptCodes() {
        return deptCodes;
    }

    public void setDeptCodes(String deptCodes) {
        this.deptCodes = deptCodes;
    }
}

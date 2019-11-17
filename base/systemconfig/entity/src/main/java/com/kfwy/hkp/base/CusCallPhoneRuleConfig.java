package com.kfwy.hkp.base;

public class CusCallPhoneRuleConfig {
    /**
     * 拨打电话间隔
     */
    private Integer callPhoneInterval;
    /**
     * 拨打电话间隔单位
     */
    private String callPhoneUnit;
    /**
     * 拨打客户的人数
     */
    private Integer callPhoneCount;
    /**
     * 拨打客户人数时间区间
     */
    private Integer callPhoneCountInterval;

    public Integer getCallPhoneCreateTimeInterval () {
        if (callPhoneCreateTimeInterval==null){
            callPhoneCreateTimeInterval=48;
        }
        return callPhoneCreateTimeInterval;
    }

    public void setCallPhoneCreateTimeInterval (Integer callPhoneCreateTimeInterval) {
        this.callPhoneCreateTimeInterval = callPhoneCreateTimeInterval;
    }

    /**
     * 拨打新增客户时间区间
     */
    private Integer callPhoneCreateTimeInterval;

    public Integer getCallPhoneInterval () {
        if (callPhoneInterval==null){
            callPhoneInterval=30;
        }
        return callPhoneInterval;
    }

    public void setCallPhoneInterval (Integer callPhoneInterval) {
        this.callPhoneInterval = callPhoneInterval;
    }

    public String getCallPhoneUnit () {
        if (callPhoneUnit==null){
            callPhoneUnit="mm";
        }
        return callPhoneUnit;
    }

    public void setCallPhoneUnit (String callPhoneUnit) {
        this.callPhoneUnit = callPhoneUnit;
    }

    public Integer getCallPhoneCount () {
        if (callPhoneCount==null){
            callPhoneCount=5;
        }
        return callPhoneCount;
    }

    public void setCallPhoneCount (Integer callPhoneCount) {
        this.callPhoneCount = callPhoneCount;
    }

    public Integer getCallPhoneCountInterval () {
        if (callPhoneCountInterval==null){
            callPhoneCountInterval=-1;
        }
        return callPhoneCountInterval;
    }

    public void setCallPhoneCountInterval (Integer callPhoneCountInterval) {
        this.callPhoneCountInterval = callPhoneCountInterval;
    }

}

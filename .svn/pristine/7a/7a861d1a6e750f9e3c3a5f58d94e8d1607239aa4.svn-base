package com.kfwy.hkp.crm.customer.bo;

import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.base.SystemConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
public class FocusCusUserScoreMatchBo extends BaseEntity implements Comparable<FocusCusUserScoreMatchBo> {

    protected String userCode;

    protected String cusCode;

    protected Date entryTime;

    protected Double houseCount;

    protected Double addCusCount;

    protected Double addHosCount;

    protected Double callPhoneCount;

    protected Double followupCount;

    public Date getEntryTime () {
        return entryTime;
    }

    public void setEntryTime (Date entryTime) {
        this.entryTime = entryTime;
    }
    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public Double getHouseCount() {
        return houseCount;
    }

    public void setHouseCount(Double houseCount) {
        this.houseCount = houseCount;
    }

    public Double getAddCusCount() {
        return addCusCount;
    }

    public void setAddCusCount(Double addCusCount) {
        this.addCusCount = addCusCount;
    }

    public Double getAddHosCount() {
        return addHosCount;
    }

    public void setAddHosCount(Double addHosCount) {
        this.addHosCount = addHosCount;
    }

    public Double getCallPhoneCount() {
        return callPhoneCount;
    }

    public void setCallPhoneCount(Double callPhoneCount) {
        this.callPhoneCount = callPhoneCount;
    }

    public Double getFollowupCount() {
        return followupCount;
    }

    public void setFollowupCount(Double followupCount) {
        this.followupCount = followupCount;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public int compareTo(FocusCusUserScoreMatchBo o) {
        Double thatTotal = getTotalScore(o);
        Double curTotal = getTotalScore(this);
        return thatTotal.compareTo(curTotal);
    }

    private Double getTotalScore(FocusCusUserScoreMatchBo o) {
        FocusCusConfig focusCusConfig;
        if (FocusCusConfig.focusCusConfig == null) {
            FocusCusConfig.focusCusConfig = SystemConfig.create().getObject("focus_cus_config", FocusCusConfig.class);
            focusCusConfig = FocusCusConfig.focusCusConfig;
        } else {
            focusCusConfig = FocusCusConfig.focusCusConfig;
        }

        if (focusCusConfig == null) {
            focusCusConfig.setHouseCountRate(0.2);
            focusCusConfig.setAddCusCountRate(0.1);
            focusCusConfig.setAddHosCountRate(0.2);
            focusCusConfig.setCallPhoneCountRate(0.1);
            focusCusConfig.setFollowupCount(0.4);
        }

        return o.getHouseCount() * focusCusConfig.getHouseCountRate()
                + o.getAddCusCount() * focusCusConfig.getAddCusCountRate()
                + o.getAddHosCount() * focusCusConfig.getAddHosCountRate()
                + o.getCallPhoneCount() * focusCusConfig.getCallPhoneCountRate()
                + o.getFollowupCount() * focusCusConfig.getFollowupCount();
    }

    public static void main(String[] args) {

        FocusCusUserScoreMatchBo a = new FocusCusUserScoreMatchBo();
        a.setAddCusCount(Double.valueOf(1));
        a.setAddHosCount(Double.valueOf(1));
        a.setCallPhoneCount(Double.valueOf(1));
        a.setFollowupCount(Double.valueOf(1));
        a.setHouseCount(Double.valueOf(1));
        a.setUserCode("a");
        FocusCusUserScoreMatchBo b = new FocusCusUserScoreMatchBo();
        b.setAddCusCount(Double.valueOf(3));
        b.setAddHosCount(Double.valueOf(3));
        b.setCallPhoneCount(Double.valueOf(3));
        b.setFollowupCount(Double.valueOf(3));
        b.setHouseCount(Double.valueOf(3));
        b.setUserCode("b");
        FocusCusUserScoreMatchBo c = new FocusCusUserScoreMatchBo();
        c.setAddCusCount(Double.valueOf(2));
        c.setAddHosCount(Double.valueOf(2));
        c.setCallPhoneCount(Double.valueOf(2));
        c.setFollowupCount(Double.valueOf(2));
        c.setHouseCount(Double.valueOf(2));
        c.setUserCode("c");
        List<FocusCusUserScoreMatchBo> list = new ArrayList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.forEach(focusCusUserScoreMatchBo -> System.out.println(focusCusUserScoreMatchBo.getUserCode()));
        Collections.sort(list);
        System.out.println();
        list.forEach(focusCusUserScoreMatchBo -> System.out.println(focusCusUserScoreMatchBo.getUserCode()));


    }

    public List<FocusCusUserScoreMatchBo> compareScoreAndSort(List<FocusCusUserScoreMatchBo> list) {
        Collections.sort(list);
        return list;
    }

    @Override
    public String toString() {
        return "FocusCusUserScoreMatchBo{" +
                "userCode='" + userCode + '\'' +
                ", houseCount=" + houseCount +
                ", addCusCount=" + addCusCount +
                ", addHosCount=" + addHosCount +
                ", callPhoneCount=" + callPhoneCount +
                ", followupCount=" + followupCount +
                ",allScore" + getTotalScore(this) + "}";
    }
}

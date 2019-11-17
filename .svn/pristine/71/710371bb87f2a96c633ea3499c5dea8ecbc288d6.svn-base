package com.kfwy.hkp.hrm.org.dept.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.hrm.org.dept.enums.ProfitShareType;

/**
 * @Auther: HJH
 * @Date: 2019/8/5
 * @Description: TODO
 */
public class DeptProfitShareEntity extends BaseEntity {

    // 所属部门
    private String deptCode;
    private String deptName;

    // 个人 公司
    /**
     * @see ProfitShareType
     */
    private String profitShareType;

    // 个人 公司
    private String profitShareTypeName;

    /*
     *  客户类
     *  cpyPlatForm
     *  personForm
     * */

    // 获客分成 OrderPercentageType.CUSTOMER
    private Integer acquireCus;

    // 客户推送 OrderPercentageType.CUSPUSHFEN
    private Integer cusPush;

    /*
     *  带看成交
     * */

    // 带看 OrderPercentageType.ASSIST_FOLLOW
    private Integer followupSee;

    // 成交 OrderPercentageType.DEAL
    private Integer orderDeal;

    /*
     *   房源类
     * */

    // 房源开发 OrderPercentageType.HOUSE
    private Integer houseDevelop;

    // 房源委托  OrderPercentageType.PROXY
    private Integer houseEntrust;

    // 总数
    private Integer sum;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getProfitShareType() {
        return profitShareType;
    }

    public void setProfitShareType(String profitShareType) {
        this.profitShareType = profitShareType;
    }

    public String getProfitShareTypeName() {
        if (this.profitShareTypeName == null && null != this.profitShareType) {
            this.profitShareTypeName = DictionaryStorage.get(ProfitShareType.class.getName(), this.getProfitShareType()).getName();
        }
        return profitShareTypeName;
    }

    public void setProfitShareTypeName(String profitShareTypeName) {
        this.profitShareTypeName = profitShareTypeName;
    }

    public Integer getAcquireCus() {
        return acquireCus;
    }

    public void setAcquireCus(Integer acquireCus) {
        this.acquireCus = acquireCus;
    }

    public Integer getCusPush() {
        return cusPush;
    }

    public void setCusPush(Integer cusPush) {
        this.cusPush = cusPush;
    }

    public Integer getFollowupSee() {
        return followupSee;
    }

    public void setFollowupSee(Integer followupSee) {
        this.followupSee = followupSee;
    }

    public Integer getOrderDeal() {
        return orderDeal;
    }

    public void setOrderDeal(Integer orderDeal) {
        this.orderDeal = orderDeal;
    }

    public Integer getHouseDevelop() {
        return houseDevelop;
    }

    public void setHouseDevelop(Integer houseDevelop) {
        this.houseDevelop = houseDevelop;
    }

    public Integer getHouseEntrust() {
        return houseEntrust;
    }

    public void setHouseEntrust(Integer houseEntrust) {
        this.houseEntrust = houseEntrust;
    }

    public Integer getSum() {
        if (this.acquireCus != null && this.cusPush != null
                && this.followupSee != null && this.orderDeal != null && this.houseDevelop != null && this.houseEntrust != null) {
            this.sum = this.acquireCus + this.cusPush + this.followupSee + this.orderDeal + this.houseDevelop + this.houseEntrust;
            return sum;
        }
        return null;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}

package com.kfwy.hkp.trade.wage.entity;



import com.gniuu.framework.entity.BaseEntity;
import java.math.BigDecimal;
import java.util.Date;


/**
 *
 * @author lzp
 * @date 2019/03/20
 */
public class CommissionRatioEntity extends BaseEntity {


    private Date orderTime;
        /**
     * 提成编号
     */
    private String commissionRatioCode;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 分成编号
     */
    private String peOrderCode;
    /**
     * 分成比例
     */
    private BigDecimal percentage;
    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 提成所属人
     */
    private String userCode;
    /**
     * 提成比例
     */
    private BigDecimal commissionRatio;
    /**
     * 薪资类型
     */
    private String salaryType;
    /**
     * 组长
     */
    private String groupUserCode;
    /**
     * 组长管理提成比例
     */
    private BigDecimal groupPercentage;
    /**
     * 经理
     */
    private String leaderCode;

    /**
     * 经理管理提成
     */
    private BigDecimal leaderPercentage;
    /**
     * 资深经理
     */
    private String managerUserCode;
    /**
     * 资深经理提成
     */
    private BigDecimal managerPercentage;
    /**
     * 推荐人编码
     */
    private String recommendCode;
    /**
     * 推荐人提成
     */
    private BigDecimal recommendPercentage;

    /**
     * 是否修改过
     */
    private Boolean hasChanged;

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getCommissionRatio() {
        return commissionRatio;
    }

    public void setCommissionRatio(BigDecimal commissionRatio) {
        this.commissionRatio = commissionRatio;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public Boolean getHasChanged() {
        return hasChanged;
    }

    public void setHasChanged(Boolean hasChanged) {
        this.hasChanged = hasChanged;
    }

    public String getCommissionRatioCode() {
        return commissionRatioCode;
    }

    public void setCommissionRatioCode(String commissionRatioCode) {
        this.commissionRatioCode = commissionRatioCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPeOrderCode() {
        return peOrderCode;
    }

    public void setPeOrderCode(String peOrderCode) {
        this.peOrderCode = peOrderCode;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public void setPercentage(BigDecimal percentage) {
        this.percentage = percentage;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getSalaryType() {
        return salaryType;
    }

    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public String getGroupUserCode() {
        return groupUserCode;
    }

    public void setGroupUserCode(String groupUserCode) {
        this.groupUserCode = groupUserCode;
    }


    public String getLeaderCode() {
        return leaderCode;
    }

    public void setLeaderCode(String leaderCode) {
        this.leaderCode = leaderCode;
    }


    public String getManagerUserCode() {
        return managerUserCode;
    }

    public void setManagerUserCode(String managerUserCode) {
        this.managerUserCode = managerUserCode;
    }


    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
    }

    public BigDecimal getGroupPercentage() {
        return groupPercentage;
    }

    public void setGroupPercentage(BigDecimal groupPercentage) {
        this.groupPercentage = groupPercentage;
    }

    public BigDecimal getLeaderPercentage() {
        return leaderPercentage;
    }

    public void setLeaderPercentage(BigDecimal leaderPercentage) {
        this.leaderPercentage = leaderPercentage;
    }

    public BigDecimal getManagerPercentage() {
        return managerPercentage;
    }

    public void setManagerPercentage(BigDecimal managerPercentage) {
        this.managerPercentage = managerPercentage;
    }

    public BigDecimal getRecommendPercentage() {
        return recommendPercentage;
    }

    public void setRecommendPercentage(BigDecimal recommendPercentage) {
        this.recommendPercentage = recommendPercentage;
    }
}

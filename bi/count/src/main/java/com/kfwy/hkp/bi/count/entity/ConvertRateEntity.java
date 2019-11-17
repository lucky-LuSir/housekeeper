package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Create By hjh on 2019/1/14
 */
public class ConvertRateEntity extends BaseEntity {

    /**
     * 显示信息名称
     */
    private String showName;
    /**
     * 转换率类型
     */
    private String convertType;
    /**
     * 58付费
     */
    private String wbSource;
    /**
     * 赶集付费
     */
    private String gjSource;
    /**
     * 官网
     */
    private String frontSource;
    /**
     * 兼职
     */
    private String partSource;
    /**
     * 户外广告
     */
    private String outdoorsSource;
    /**
     * 扫街
     */
    private String sweepSource;
    /**
     * 其他
     */
    private String otherSource;
    /**
     * 总计
     */
    private String countSource;
    /**
     * 400 客服
     */
    private String cusService;
    /**
     * 名单拨打
     */
    private String mdbdSource;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getConvertType() {
        return convertType;
    }

    public void setConvertType(String convertType) {
        this.convertType = convertType;
    }

    public String getWbSource() {
        return wbSource;
    }

    public void setWbSource(String wbSource) {
        this.wbSource = wbSource;
    }

    public String getGjSource() {
        return gjSource;
    }

    public void setGjSource(String gjSource) {
        this.gjSource = gjSource;
    }

    public String getFrontSource() {
        return frontSource;
    }

    public void setFrontSource(String frontSource) {
        this.frontSource = frontSource;
    }

    public String getPartSource() {
        return partSource;
    }

    public void setPartSource(String partSource) {
        this.partSource = partSource;
    }

    public String getOutdoorsSource() {
        return outdoorsSource;
    }

    public void setOutdoorsSource(String outdoorsSource) {
        this.outdoorsSource = outdoorsSource;
    }

    public String getSweepSource() {
        return sweepSource;
    }

    public void setSweepSource(String sweepSource) {
        this.sweepSource = sweepSource;
    }

    public String getOtherSource() {
        return otherSource;
    }

    public void setOtherSource(String otherSource) {
        this.otherSource = otherSource;
    }

    public String getCountSource() {
        return countSource;
    }

    public void setCountSource(String countSource) {
        this.countSource = countSource;
    }

    public String getCusService() {
        return cusService;
    }

    public void setCusService(String cusService) {
        this.cusService = cusService;
    }

    public String getMdbdSource() {
        return mdbdSource;
    }

    public void setMdbdSource(String mdbdSource) {
        this.mdbdSource = mdbdSource;
    }
}

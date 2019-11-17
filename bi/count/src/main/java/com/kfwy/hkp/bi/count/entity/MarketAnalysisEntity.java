package com.kfwy.hkp.bi.count.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Create By hjh on 2019/1/9
 */
public class MarketAnalysisEntity extends BaseEntity {

    //时间间隔
    private String timeNumber;
    //客户行业性质
    private String industry;
    // 需求面积 0-499
    private long marketOne;
    // 需求面积 500-999
    private long marketTwo;
    // 需求面积 1000－1999平
    private long marketThree;
    // 需求面积 2000－2999平
    private long marketFour;
    // 需求面积 3000－4999平
    private long marketFive;
    // 需求面积 5000平及以上
    private long marketSix;
    // 需求面积 总客户
    private long marketCount;

    public String getTimeNumber() {
        return timeNumber;
    }

    public void setTimeNumber(String timeNumber) {
        this.timeNumber = timeNumber;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public long getMarketOne() {
        return marketOne;
    }

    public void setMarketOne(long marketOne) {
        this.marketOne = marketOne;
    }

    public long getMarketTwo() {
        return marketTwo;
    }

    public void setMarketTwo(long marketTwo) {
        this.marketTwo = marketTwo;
    }

    public long getMarketThree() {
        return marketThree;
    }

    public void setMarketThree(long marketThree) {
        this.marketThree = marketThree;
    }

    public long getMarketFour() {
        return marketFour;
    }

    public void setMarketFour(long marketFour) {
        this.marketFour = marketFour;
    }

    public long getMarketFive() {
        return marketFive;
    }

    public void setMarketFive(long marketFive) {
        this.marketFive = marketFive;
    }

    public long getMarketSix() {
        return marketSix;
    }

    public void setMarketSix(long marketSix) {
        this.marketSix = marketSix;
    }

    public long getMarketCount() {
        return marketCount;
    }

    public void setMarketCount(long marketCount) {
        this.marketCount = marketCount;
    }
}

package com.kfwy.hkp.bi.count.entity;

import java.util.List;

/**
 * @Auther: HJH
 * @Date: 2019/4/16
 * @Description: TODO
 */
public class ThreadQuery {

    private String showName;

    private String showTime;

    private String quitTime;

    private List<String> params;

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getQuitTime() {
        return quitTime;
    }

    public void setQuitTime(String quitTime) {
        this.quitTime = quitTime;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }
}

package com.kfwy.hkp.bi.count.entity;

/**
 * Create By hjh on 2018/11/13
 */
public class TableHead {

    private String prop;
    private String label;

    public TableHead() {

    }

    public TableHead(String prop, String label) {
        this.prop = prop;
        this.label = label;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}

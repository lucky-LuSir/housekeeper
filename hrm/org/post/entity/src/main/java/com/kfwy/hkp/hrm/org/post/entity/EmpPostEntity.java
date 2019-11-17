package com.kfwy.hkp.hrm.org.post.entity;

import com.gniuu.framework.entity.BaseEntity;

public class EmpPostEntity extends BaseEntity {
    /**
     * 岗位编码
     */
    private String empPostCode;

    /**
     * 岗位名称
     */
    private String empPostName;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建者编号
     */
    private String empCode;

    /**
     * 创建姓名
     */
    private String empName;

    /*等级*/
    private String level;
    public String getEmpPostCode() {
        return empPostCode;
    }

    public void setEmpPostCode(String empPostCode) {
        this.empPostCode = empPostCode;
    }

    public String getEmpPostName() {
        return empPostName;
    }

    public void setEmpPostName(String empPostName) {
        this.empPostName = empPostName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}

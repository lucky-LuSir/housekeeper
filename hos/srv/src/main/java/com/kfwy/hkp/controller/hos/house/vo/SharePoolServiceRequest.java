package com.kfwy.hkp.controller.hos.house.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.hos.house.entity.SharePoolEntity;

import java.util.List;

public class SharePoolServiceRequest extends AbstractServiceRequest<SharePoolEntity> {

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * 关键字
     */
    private String keyword;

    private String shareCode;


    private String phoneNumber;

    private String applyRecordCode;
    /**
     * 共享类型
     * 1、分别共享，递归子部门
     * 2、统一共享，递归子部门
     * 3、精确共享，不去递归子部门
     */
    private String shareType;
    private List<String> joinPoolDeptCode;

    public List<String> getJoinPoolDeptCode() {
        return joinPoolDeptCode;
    }

    public void setJoinPoolDeptCode(List<String> joinPoolDeptCode) {
        this.joinPoolDeptCode = joinPoolDeptCode;
    }

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getApplyRecordCode() {
        return applyRecordCode;
    }

    public void setApplyRecordCode(String applyRecordCode) {
        this.applyRecordCode = applyRecordCode;
    }
}

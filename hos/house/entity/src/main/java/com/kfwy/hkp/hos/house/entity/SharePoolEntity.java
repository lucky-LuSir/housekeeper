package com.kfwy.hkp.hos.house.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.common.dic.FireLevelType;
import com.kfwy.hkp.hos.house.enums.SharePoolType;

/**
 * @author kfwy_it_013
 */
public class SharePoolEntity extends BaseEntity{
    /**
     * 共享池编码
     */
    private String shareCode;
    /**
     * 共享池名
     */
    private String shareName;
    /**
     * 共享类型
     */
    private String shareType;


    private String shareTypeName;
    /**
     * 共享部门编码
     */
    private String shareDeptCode;
    /**
     * 共享部门名
     */
    private String shareDeptName;

    private String leaderImg;
    private String leaderName;

    /**
     * 申请记录唯一编码
     */
    private String applyRecordCode;

    public String getShareCode() {
        return shareCode;
    }

    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
        if (this.shareTypeName == null && null != this.shareType){
            this.shareTypeName = DictionaryStorage.get(SharePoolType.class.getName(),this.getShareType()).getName();
        }
    }

    public String getShareDeptCode() {
        return shareDeptCode;
    }

    public void setShareDeptCode(String shareDeptCode) {
        this.shareDeptCode = shareDeptCode;
    }

    public String getShareDeptName() {
        return shareDeptName;
    }

    public void setShareDeptName(String shareDeptName) {
        this.shareDeptName = shareDeptName;
    }
    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public String getShareTypeName() {
        if (this.shareTypeName == null && null != this.shareType){
            this.shareTypeName = DictionaryStorage.get(SharePoolType.class.getName(),this.getShareType()).getName();
        }
        return shareTypeName;
    }

    public void setShareTypeName(String shareTypeName) {

        this.shareTypeName = shareTypeName;
    }

    public String getLeaderImg() {
        return leaderImg;
    }

    public void setLeaderImg(String leaderImg) {
        this.leaderImg = leaderImg;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getApplyRecordCode() {
        return applyRecordCode;
    }

    public void setApplyRecordCode(String applyRecordCode) {
        this.applyRecordCode = applyRecordCode;
    }
}

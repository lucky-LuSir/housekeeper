package com.kfwy.hkp.sys.user.entity;


import com.gniuu.framework.user.entity.BaseUserEntity;

/**
 * Created by lzp 2019/03/21.
 * <p>
 * user薪资关系
 */
public class UserRelationEntity extends BaseUserEntity {

    /**
     * 人员组长
     */
    private String userGroupCode;
    /**
     * 人员组长
     */
    private String leaderCode;
    /**
     * 资深经理
     */
    private String managerCode;
    /**
     * 推荐人编码
     */
    private String recommendCode;


    public String getUserGroupCode() {
        return userGroupCode;
    }

    public void setUserGroupCode(String userGroupCode) {
        this.userGroupCode = userGroupCode;
    }

    public String getLeaderCode() {
        return leaderCode;
    }

    public void setLeaderCode(String leaderCode) {
        this.leaderCode = leaderCode;
    }

    public String getManagerCode() {
        return managerCode;
    }

    public void setManagerCode(String managerCode) {
        this.managerCode = managerCode;
    }

    public String getRecommendCode() {
        return recommendCode;
    }

    public void setRecommendCode(String recommendCode) {
        this.recommendCode = recommendCode;
    }
}

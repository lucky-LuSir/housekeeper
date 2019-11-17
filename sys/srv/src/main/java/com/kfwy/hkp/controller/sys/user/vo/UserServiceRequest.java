package com.kfwy.hkp.controller.sys.user.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;

/**
 *
 * @author davidcun
 * @date 2018/5/18
 */
public class UserServiceRequest extends AbstractServiceRequest<UserEntity> {


    /**
     * 多账号登陆使用
     */
    private String keyword;


    private List<String> roleCodes;

    private String applyRecordCode;

    /**
     * 新密码
     */
    private String newPwd;
    private String activationCode;


    private String ssoKey;

    public String getSsoKey() {
        return ssoKey;
    }

    public void setSsoKey(String ssoKey) {
        this.ssoKey = ssoKey;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> getRoleCodes() {
        return roleCodes;
    }

    public void setRoleCodes(List<String> roleCodes) {
        this.roleCodes = roleCodes;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getApplyRecordCode() {
        return applyRecordCode;
    }

    public void setApplyRecordCode(String applyRecordCode) {
        this.applyRecordCode = applyRecordCode;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }
}

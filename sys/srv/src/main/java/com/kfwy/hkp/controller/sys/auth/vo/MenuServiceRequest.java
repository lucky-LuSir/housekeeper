package com.kfwy.hkp.controller.sys.auth.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.sys.auth.entity.MenuEntity;

/**
 * @author liwensihan
 */
public class MenuServiceRequest extends AbstractServiceRequest<MenuEntity> {
    /**
     * 通过用户编码查询用户权限
     */
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}

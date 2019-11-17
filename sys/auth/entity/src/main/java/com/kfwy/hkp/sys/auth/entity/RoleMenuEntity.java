package com.kfwy.hkp.sys.auth.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Created by davidcun on 2018/5/19.
 */
public class RoleMenuEntity extends BaseEntity {

    /**
     * 角色编码
     */
    private String roleCode;

    /**
     * 菜单编码
     */
    private String menuCode;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
}

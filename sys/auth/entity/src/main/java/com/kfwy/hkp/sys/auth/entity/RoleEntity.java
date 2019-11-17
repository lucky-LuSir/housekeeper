package com.kfwy.hkp.sys.auth.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Created by davidcun on 2018/5/19.
 */
public class RoleEntity extends BaseEntity {

    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 人员编号
     */
    private String userCode;

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

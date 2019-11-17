package com.kfwy.hkp.sys.user.entity;


import com.gniuu.framework.common.enums.SexType;
import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.hrm.org.dept.entity.DeptAreaEntity;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.auth.entity.MenuEntity;
import com.kfwy.hkp.sys.auth.entity.RoleEntity;
import com.kfwy.hkp.sys.user.enums.InviteState;
import com.kfwy.hkp.sys.user.enums.UserCAStatus;
import com.kfwy.hkp.sys.user.enums.UserSalaryType;
import com.kfwy.hkp.sys.user.enums.UserType;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by davidcun on 2018/5/16.
 * <p>
 * 此User是当前系统的用户登录返回视图对象
 */
public class UserTeamVo extends UserEntity {

    DeptEntity deptEntity;

    List<MenuEntity> judgeMenuTreeList;

    public DeptEntity getDeptEntity() {
        return deptEntity;
    }

    public void setDeptEntity(DeptEntity deptEntity) {
        this.deptEntity = deptEntity;
    }

    public List<MenuEntity> getJudgeMenuTreeList() {
        return judgeMenuTreeList;
    }

    public void setJudgeMenuTreeList(List<MenuEntity> judgeMenuTreeList) {
        this.judgeMenuTreeList = judgeMenuTreeList;
    }
}

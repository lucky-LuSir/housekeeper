package com.kfwy.hkp.sys.auth.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.auth.entity.RoleMenuEntity;

import java.util.List;

/**
 * Created by davidcun on 2018/5/22.
 */
public interface IRoleMenuDbDao extends IMyBatisBaseDao<RoleMenuEntity,Long> {
    int deleteByRoleCode(String roleCode);

    String findRootMenuCode();

}

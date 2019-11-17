package com.kfwy.hkp.sys.auth.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.auth.entity.RoleEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
public interface IRoleDbDao extends IMyBatisBaseDao<RoleEntity,Long> {
    public List<RoleEntity> selectRoleListByUser(String userCode);

    public String findBindingPostCodeByRoleCode(String roleCode);

    public int editBindingPost(Map map);
}

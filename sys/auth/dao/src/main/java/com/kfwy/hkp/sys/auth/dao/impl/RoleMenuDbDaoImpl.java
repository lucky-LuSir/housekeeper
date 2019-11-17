package com.kfwy.hkp.sys.auth.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.auth.dao.IRoleMenuDbDao;
import com.kfwy.hkp.sys.auth.entity.RoleMenuEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by davidcun on 2018/5/22.
 */
@Repository
public class RoleMenuDbDaoImpl extends AbstractMyBatisDao<RoleMenuEntity,Long> implements IRoleMenuDbDao {
    @Override
    public int deleteByRoleCode(String roleCode) {
        return this.getSqlSession().delete(this.mapperNamespace+"deleteByRoleCode",roleCode);
    }

    @Override
    public String findRootMenuCode() {
        return this.getSqlSession().selectOne(this.mapperNamespace+"findRootMenuCode");
    }
}

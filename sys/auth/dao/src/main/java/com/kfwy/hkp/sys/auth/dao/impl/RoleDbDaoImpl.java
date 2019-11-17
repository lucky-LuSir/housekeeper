package com.kfwy.hkp.sys.auth.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.auth.dao.IRoleDbDao;
import com.kfwy.hkp.sys.auth.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author liwensihan
 * @date 2018/11/9
*/
@Repository
public class RoleDbDaoImpl extends AbstractMyBatisDao<RoleEntity,Long> implements IRoleDbDao {
    public static String selectRoleListByUser = "selectRoleListByUser";
    @Override
    public List<RoleEntity> selectRoleListByUser(String userCode) {
        Map<String,Object> param = new HashMap<String,Object>();

        param.put("userCode",userCode);
        param.put("isDeleted",Boolean.FALSE);

        return this.selectByStatement(selectRoleListByUser,param);
    }

    @Override
    public String findBindingPostCodeByRoleCode(String roleCode) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"findBindingPostCodeByRoleCode",roleCode);
    }

    @Override
    public int editBindingPost(Map map) {
        return this.getSqlSession().update(this.mapperNamespace+"editBindingPost",map);
    }
}

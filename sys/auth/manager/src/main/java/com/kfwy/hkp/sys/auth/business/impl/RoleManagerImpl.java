package com.kfwy.hkp.sys.auth.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.RestBusinessException;
import com.kfwy.hkp.sys.auth.business.IRoleManager;
import com.kfwy.hkp.sys.auth.dao.IMenuDbDao;
import com.kfwy.hkp.sys.auth.dao.IRoleDbDao;
import com.kfwy.hkp.sys.auth.dao.IRoleMenuDbDao;
import com.kfwy.hkp.sys.auth.entity.RoleEntity;
import com.kfwy.hkp.sys.auth.entity.RoleMenuEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 *
 * @author davidcun
 * @date 2018/5/22
 */
@Service
public class RoleManagerImpl extends AbstractManager<RoleEntity> implements IRoleManager {

    @Autowired
    private IRoleDbDao roleDbDao;

    @Autowired
    private IRoleMenuDbDao roleMenuDbDao;

    @Autowired
    private IMenuDbDao menuDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return roleDbDao;
    }

    @Override
    protected void beforeCreate(RoleEntity roleEntity) {
        roleEntity.setRoleCode(CodeGenUtils.generate());
    }

    @Override
    protected void beforeUpdate(RoleEntity roleEntity) {

        if (StringUtils.isEmpty(roleEntity.getRoleCode())
                && roleEntity.getId() == null) {
            throw new RestBusinessException("更新角色，ID或者角色编码不能为空");
        }


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(RoleEntity role, List<String> menus) {

        if (menus != null && menus.size() > 0) {
            roleMenuDbDao.deleteByRoleCode(role.getRoleCode());
        }
        int i = roleDbDao.update(role);
        createRoleMenu(role.getRoleCode(), menus);
        return i;
    }


    private void createRoleMenu(String roleCode, List<String> menus) {
        List<RoleMenuEntity> list = new ArrayList<RoleMenuEntity>();
        for (String menu:menus){
            RoleMenuEntity pme = new RoleMenuEntity();
            pme.setRoleCode(roleCode);
            pme.setMenuCode(menu);
            list.add(pme);
        }
        roleMenuDbDao.batchInsert(list);
    }

    private void findParentMenuCode(String roleCode, Set<String> menuSet, List<RoleMenuEntity> list, String menu) {

        String parentCode = menuDbDao.findParentCodeByChildCode(menu);
        if (parentCode != null && !menuSet.contains(parentCode)) {
            RoleMenuEntity parentMenu = new RoleMenuEntity();
            parentMenu.setRoleCode(roleCode);
            parentMenu.setMenuCode(parentCode);
            list.add(parentMenu);
            menuSet.add(parentCode);
            findParentMenuCode(roleCode, menuSet, list, parentCode);
        }
    }


    @Override
    public List<RoleEntity> findRoleListByUser(String userCode) {
        if (StringUtils.isEmpty(userCode)) {
            throw new RestBusinessException("查询角色列表，用户编码userCode不能为空");
        }

        List<RoleEntity> roles = roleDbDao.selectRoleListByUser(userCode);

        return roles;
    }

    @Override
    public List<RoleEntity> findRoleListByUsers(List<String> userCodes) {
        Map<String, Object> param = new HashMap<>(16);
        if (null != userCodes && userCodes.size() > 0) {
            param.put("userCodes", userCodes);
            List<RoleEntity> roles = roleDbDao.selectByStatement("selectRoleListByUsers", param);
            return roles;
        }
        return null;
    }

    @Override
    public String findBindingPostCodeByRoleCode(String roleCode) {
        return roleDbDao.findBindingPostCodeByRoleCode(roleCode);
    }

    @Override
    public int editBindingPost(RoleEntity entity, String empPostCode, String empPostName) {
        Map map = new HashMap(4);
        map.put("roleCode", entity.getRoleCode());
        map.put("empPostCode", empPostCode);
        map.put("empPostName", empPostName);
        if (StringUtils.isEmpty(empPostCode) && StringUtils.isEmpty(empPostName)) {
            map.put("bindingStatus", Boolean.FALSE);
        } else {
            map.put("bindingStatus", Boolean.TRUE);
        }
        return roleDbDao.editBindingPost(map);
    }
}

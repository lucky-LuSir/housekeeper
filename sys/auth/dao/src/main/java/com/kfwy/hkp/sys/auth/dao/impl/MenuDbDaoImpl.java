package com.kfwy.hkp.sys.auth.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.auth.dao.IMenuDbDao;
import com.kfwy.hkp.sys.auth.entity.MenuEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description TODO
 * @auth davidCun
 * @date 2018/6/29 11:53
 * @version 1.0
 * @return
 */
@Repository
public class MenuDbDaoImpl extends AbstractMyBatisDao<MenuEntity,Long> implements IMenuDbDao {

    public static String selectUserMenuByRoleRelation = "selectUserMenuByRoleRelation";

    public static String selectUserMenuByPostRelation = "selectUserMenuByPostRelation";

    public static String selectMenuListByRole = "selectMenuListByRole";

    @Override
    public List<MenuEntity> selectUserMenuByRoleRelation(String userCode) {

        Map<String,Object> param = new HashMap<String,Object>();

        param.put("userCode",userCode);
        param.put("isDeleted",Boolean.FALSE);
        param.put("orderBy","sort");
        param.put("order", "ASC");
        return this.selectByStatement(selectUserMenuByRoleRelation,param);
    }

    @Override
    public List<MenuEntity> selectMenuByRoleRelation(String userCode) {
        Map<String,Object> param = new HashMap<String,Object>(16);

        param.put("userCode",userCode);
        param.put("isDeleted",Boolean.FALSE);
        param.put("orderBy","sort");
        param.put("order", "ASC");
        return this.selectByStatement(selectUserMenuByRoleRelation,param);
    }


    @Override
    public List<MenuEntity> selectUserMenuByPostRelation(String userCode) {

        Map<String,Object> param = new HashMap<String,Object>();

        param.put("userCode",userCode);
        param.put("isDeleted",Boolean.FALSE);
        param.put("orderBy","sort");
        param.put("order", "ASC");
        return this.selectByStatement(selectUserMenuByPostRelation,param);
    }

    @Override
    public List<MenuEntity> selectMenuListByRole(String roleCode) {
        Map<String,Object> param = new HashMap<String,Object>(2);

        param.put("roleCode",roleCode);
        param.put("isDeleted",Boolean.FALSE);
        param.put("orderBy","sort");
        param.put("order", "ASC");
        return this.selectByStatement(selectMenuListByRole,param);
    }

    @Override
    public List<MenuEntity> selectBtnListByRoleRelation(String userCode) {
        Map<String,Object> param = new HashMap<String,Object>(2);
        param.put("userCode",userCode);
        param.put("isDeleted",Boolean.FALSE);
        param.put("type",2);
        param.put("orderBy","sort");
        param.put("order", "ASC");
        return this.selectByStatement(selectMenuListByRole,param);
    }

    @Override
    public List<MenuEntity> findMenuListByPostCode(Map map) {
        map.put("orderBy","sort");
        map.put("order", "ASC");
        return this.getSqlSession().selectList(this.mapperNamespace+"findMenuListByPostCode",map);
    }

    @Override
    public String findParentCodeByChildCode(String  menuCode) {

        return this.getSqlSession().selectOne(this.mapperNamespace+"findParentCodeByChildCode",menuCode);
    }

    @Override
    public List<String> queryAllMenuCodeByUserCodeAndPostCode(Map map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"queryAllMenuCodeByUserCodeAndPostCode",map);
    }

    @Override
    public List<MenuEntity> queryListByParentCode(String parentCode) {
        return this.getSqlSession().selectList(this.mapperNamespace+"queryListByParentCode",parentCode);
    }

    @Override
    public MenuEntity queryMenuByChildMenuCode(String menuCode) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"queryMenuByChildMenuCode",menuCode);
    }

    @Override
    public List<MenuEntity> queryAllMenuByUserCodeAndPostCode(Map map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"queryAllMenuByUserCodeAndPostCode",map);
    }

    @Override
    public List<MenuEntity> getUserJudgeMenuList(Map map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getUserJudgeMenuList",map);
    }

    @Override
    public List<MenuEntity> selectAllMenu(Map map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"selectAllMenu",map);
    }

    @Override
    public List<MenuEntity> findAllRecursion() {
        return this.getSqlSession().selectList(this.mapperNamespace+"findAllRecursion");
    }

}

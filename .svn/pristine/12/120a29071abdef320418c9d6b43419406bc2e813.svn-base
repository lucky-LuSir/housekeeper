package com.kfwy.hkp.sys.auth.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.auth.entity.MenuEntity;
import org.apache.ibatis.annotations.Param;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @description TODO
 * @auth davidCun
 * @date 2018/6/29 10:55
 * @version 1.0
 * @return
 */
public interface IMenuDbDao extends IMyBatisBaseDao<MenuEntity,Long>{


    /**
     * 根据角色关系查询用户菜单
     * @param userCode
     * @return
     */
    public List<MenuEntity> selectUserMenuByRoleRelation(String userCode);
    public List<MenuEntity> selectMenuByRoleRelation(String userCode);
    /**
     * 根据岗位关联关系查询用户菜单
     * @param userCode
     * @return
     */
    public List<MenuEntity> selectUserMenuByPostRelation(String userCode);

    List<MenuEntity> selectMenuListByRole(String roleCode);

    List<MenuEntity> selectBtnListByRoleRelation(String userCode);

    List<MenuEntity> findMenuListByPostCode(Map map);

    String findParentCodeByChildCode(@Param("menuCode") String menuCode);

    List<String> queryAllMenuCodeByUserCodeAndPostCode(Map map);

    List<MenuEntity> queryListByParentCode(String parentCode);

    MenuEntity queryMenuByChildMenuCode(String menuCode);

    List<MenuEntity> queryAllMenuByUserCodeAndPostCode(Map map);

    List<MenuEntity> getUserJudgeMenuList(Map map);


    List<MenuEntity> selectAllMenu(Map map);

    List<MenuEntity> findAllRecursion();


}

package com.kfwy.hkp.sys.auth.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.sys.auth.entity.MenuEntity;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 * 用户菜单权限管理
 */
public interface IMenuManager extends IManager<MenuEntity> {

    /**
     * 通过用户编码查询用户的权限，通过角色关联关系
     * @param userCode
     * @return
     */
    public List<MenuEntity> findUserMenuToTreeByRoleRelation(String userCode);

    /**
     * 通过用户编码查询用户的权限，通过角色关联关系
     * @param userCode
     * @return
     */
    public List<MenuEntity> findMenuToTreeByRoleRelation(String userCode);

    /**
     * 通过用户编码查询用户的权限，通过岗位关联权限关系
     * @param postCode
     * @return
     */
    public List<MenuEntity> findUserMenuToTreeByPostRelation(String postCode);


    /**
     * 返回所有菜单树形结构
     * @return
     */
    public List<MenuEntity> findAllMenuTree();

    /**
     * @Description 描述:根据角色查菜单列表(目前不用转化为树)
     * @Auth liwenishan
     * @Date 2018/8/16 14:48
     * @Version 1.0
     * @Param [roleCode]
     * @return java.util.List<com.kfwy.hkp.sys.auth.entity.MenuEntity>
     **/
    public List<MenuEntity> findMenuListByRole(String roleCode);

    /**
     * 通过用户编码查询用户的权限，通过角色及岗位关联关系
     * @param userCode
     * @return
     */
    public List<MenuEntity> findUserMenuToTreeByUserCode(String userCode);

    List<MenuEntity> findBtnByRoleRelation(String userCode);

    List<MenuEntity> findJudgeMenuToTreeByPostCodeAndUserCode(String empPostCode, String userCode);

    List<MenuEntity> findMenuToTreeByPostCodeAndUserCode(String empPostCode, String userCode);

    List<MenuEntity> getUserMenuTreeList(String empPostCode, String userCode);

    /**
     * 查询用户的所有菜单ID
     */
    List<String> queryAllMenuCodeByUserCodeAndPostCode(Map map);

    List<MenuEntity> queryAllMenuByUserCodeAndPostCode(Map map);

    /**
     * 查询所有菜单集合
     * @param menuCodeList
     * @return
     */
    List<MenuEntity> getAllMenuList(List<String> menuCodeList);



    /**
     * 通过父菜单code，查询所有子菜单集合
     * @param parentCode
     * @param menuCodeList
     * @return
     */
    List<MenuEntity> queryMenuListByParentCode(String parentCode,List<String>menuCodeList);

    MenuEntity queryMenuByChildMenuCode(String menuCode);


    List<MenuEntity> queryMenuListByParentCode(String parentCode);

    List<MenuEntity> getUserJudgeMenuList(String empPostCode, String userCode);

    List<MenuEntity> selectAllMenu(Map map);

    List<MenuEntity> findAllRecursion();
}

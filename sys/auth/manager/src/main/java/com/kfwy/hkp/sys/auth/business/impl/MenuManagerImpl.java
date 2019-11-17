package com.kfwy.hkp.sys.auth.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.RestBusinessException;
import com.kfwy.hkp.sys.auth.business.IMenuManager;
import com.kfwy.hkp.sys.auth.dao.IMenuDbDao;
import com.kfwy.hkp.sys.auth.entity.MenuEntity;
import com.kfwy.hkp.sys.auth.enums.MenuType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by davidcun on 2018/5/22.
 */
@Service
public class MenuManagerImpl extends AbstractManager<MenuEntity> implements IMenuManager {

    @Autowired
    private IMenuDbDao menuDbDao;


    @Override
    protected IMyBatisBaseDao<MenuEntity, Long> getMyBatisDao() {
        return this.menuDbDao;
    }

    @Override
    protected void beforeCreate(MenuEntity menuEntity) {
        menuEntity.setMenuCode(CodeGenUtils.generate());

        /**
         * 如果是顶级菜单就默认设置为0
         */
        if (menuEntity.getParentCode() == null) {
            menuEntity.setParentCode(MenuType.ROOT_MENU);
        }
    }

    @Override
    protected void beforeUpdate(MenuEntity menuEntity) {

        if (StringUtils.isEmpty(menuEntity.getMenuCode())
                && menuEntity.getId() == null) {
            throw new RestBusinessException("更新数据，menuCode或者ID不能都为空");
        }

    }


    @Override
    public List<MenuEntity> findUserMenuToTreeByRoleRelation(String userCode) {

        if (StringUtils.isEmpty(userCode)) {
            throw new RestBusinessException("查询用户菜单权限，用户编码不能为空");
        }
        List<MenuEntity> menus = menuDbDao.selectUserMenuByRoleRelation(userCode);
        List<MenuEntity> entireMenuList = new ArrayList<MenuEntity>();
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("parentCode", MenuType.ROOT_MENU);
        param.put("isDeleted", false);
        MenuEntity one = new MenuEntity();
        one = this.findOne(param);
        entireMenuList.add(one);
        //设置菜单集合
        setMenuList(menus, entireMenuList);
        //5 根据总叶子权限递归遍历获得完整权限树
        return processMenuToTree(entireMenuList);
    }

    @Override
    public List<MenuEntity> findMenuToTreeByRoleRelation(String userCode) {
        if (StringUtils.isEmpty(userCode)) {
            throw new RestBusinessException("查询用户菜单权限，用户编码不能为空");
        }
        List<MenuEntity> menus = menuDbDao.selectMenuByRoleRelation(userCode);
        List<MenuEntity> entireMenuList = new ArrayList<MenuEntity>();
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("parentCode", MenuType.ROOT_MENU);
        param.put("isDeleted", false);
        MenuEntity one = new MenuEntity();
        one = this.findOne(param);
        entireMenuList.add(one);
        Map<String,MenuEntity> menuMap = queryMenuMap();
        findMenuItem(menus, entireMenuList,menuMap);
        //5 根据总叶子权限递归遍历获得完整权限树
        return processMenuToTree(entireMenuList);
    }

    /**
     * 设置菜单集合
     *
     * @param menus
     * @param entireMenuList
     */
    private void setMenuList(List<MenuEntity> menus, List<MenuEntity> entireMenuList) {
        for (int i = 0; i < menus.size(); i++) {
            MenuEntity leaf = new MenuEntity();
            leaf = menus.get(i);
            List<MenuEntity> partList = recursive(leaf);
            entireMenuList.addAll(partList);
        }
    }

    @Override
    public List<MenuEntity> findUserMenuToTreeByPostRelation(String postCode) {

        if (StringUtils.isEmpty(postCode)) {
            throw new RestBusinessException("查询用户菜单权限，用户编码不能为空");
        }

        List<MenuEntity> menus = menuDbDao.selectUserMenuByPostRelation(postCode);

        return processMenuToTree(menus);
    }


    @Override
    public List<MenuEntity> findAllMenuTree() {

        Map<String, Object> param = new HashMap<String, Object>(16);

        param.put("isDeleted", Boolean.FALSE);

        List<MenuEntity> menus = this.findByMap(param);

        return processMenuToTree(menus);
    }

    @Override
    public List<MenuEntity> findMenuListByRole(String roleCode) {
        if (StringUtils.isEmpty(roleCode)) {
            throw new RestBusinessException("查询菜单权限，角色编码不能为空");
        }

        List<MenuEntity> menus = menuDbDao.selectMenuListByRole(roleCode);

        return menus;
    }

    @Override
    public List<MenuEntity> findUserMenuToTreeByUserCode(String userCode) {
        if (StringUtils.isEmpty(userCode)) {
            throw new RestBusinessException("查询用户菜单权限，用户编码不能为空");
        }

        List<MenuEntity> menus = menuDbDao.selectUserMenuByRoleRelation(userCode);

        List<MenuEntity> menus2 = menuDbDao.selectUserMenuByPostRelation(userCode);

        menus.addAll(menus2);

        Map<String, MenuEntity> map = new HashMap<String, MenuEntity>();

        for (MenuEntity me : menus) {
            map.put(me.getMenuCode(), me);
        }

        List<MenuEntity> mn = new ArrayList<MenuEntity>();

        for (Map.Entry<String, MenuEntity> entry : map.entrySet()) {

            mn.add(entry.getValue());

        }

        return processMenuToTree(mn);
    }

    @Override
    public List<MenuEntity> findBtnByRoleRelation(String userCode) {
        if (StringUtils.isEmpty(userCode)) {
            throw new RestBusinessException("查询用户菜单权限，用户编码不能为空");
        }
        List<MenuEntity> btnEntityList = menuDbDao.selectBtnListByRoleRelation(userCode);
        return btnEntityList;
    }

    @Override
    public List<MenuEntity> findJudgeMenuToTreeByPostCodeAndUserCode(String empPostCode, String userCode) {
        //先通过岗位找到角色绑定的菜单，然后通过用户编码找到所匹配的菜单，然后融合。
        Map map = new HashMap(16);
        map.put("postCode", empPostCode);

        //通过userCode找到该用户绑定的角色，通过角色菜单中间表，找到菜单权限
        List<MenuEntity> menus = menuDbDao.selectUserMenuByRoleRelation(userCode);
        List<MenuEntity> menuEntityList = menuDbDao.findMenuListByPostCode(map);
        //清除通过角色查到的菜单权限中的，通过岗位编码查到的可能重复的菜单权限，再添加进去，避免同一个菜单功能在集合中存在两个以上
        menus.removeAll(menuEntityList);
        menus.addAll(menuEntityList);

        List<MenuEntity> entireMenuList = new ArrayList<MenuEntity>(10);
        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("parentCode", MenuType.ROOT_MENU);
        param.put("isDeleted", false);
        MenuEntity one = new MenuEntity();
        one = this.findOne(param);
        entireMenuList.add(one);

        setMenuList(menus, entireMenuList);
        //5 根据总叶子权限递归遍历获得完整权限树
        return processMenuToTree(entireMenuList);
    }

    public List<MenuEntity> treeFind(MenuEntity leaf,Map<String,MenuEntity> menuMap) {
        String parentCode = leaf.getParentCode();
        if (parentCode.equals(MenuType.ROOT_MENU)) {
            return new ArrayList<MenuEntity>();
        } else {
            List<MenuEntity> tempList = new ArrayList<MenuEntity>();
            //如果是菜单则加入，如果是功能code则不加入
            if (leaf.getMenuType().equals(MenuType.MENU)) {
                tempList.add(leaf);
            }
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("menuCode", parentCode);
            param.put("isDeleted", false);
            MenuEntity one = new MenuEntity();
            one = menuMap.get(parentCode);
            tempList.addAll(treeFind(one,menuMap));
            return tempList;
        }
    }

    public List<MenuEntity> recursive(MenuEntity leaf) {
        String parentCode = leaf.getParentCode();
        if (parentCode.equals(MenuType.ROOT_MENU)) {
            return new ArrayList<MenuEntity>();
        } else {
            List<MenuEntity> tempList = new ArrayList<MenuEntity>(10);
            tempList.add(leaf);
            Map<String, Object> param = new HashMap<String, Object>(16);
            param.put("menuCode", parentCode);
            param.put("isDeleted", false);
            MenuEntity one = new MenuEntity();
            one = this.findOne(param);
            tempList.addAll(recursive(one));
            return tempList;
        }
    }

    @Override
    public List<MenuEntity> findMenuToTreeByPostCodeAndUserCode(String empPostCode, String userCode) {
        Map map = new HashMap();
        if (StringUtils.isNotEmpty(empPostCode)) {
            map.put("postCode", empPostCode);
        }
        if (StringUtils.isNotEmpty(userCode)) {
            map.put("userCode", userCode);
        }
        List<MenuEntity> menus = queryAllMenuByUserCodeAndPostCode(map);
        Collections.sort(menus);
        Map<String,MenuEntity> menuMap = queryMenuMap();

        List<MenuEntity> entireMenuList = new ArrayList<MenuEntity>();
        Map<String, Object> param = new HashMap<String, Object>(10);
        param.put("parentCode", MenuType.ROOT_MENU);
        param.put("isDeleted", false);
        MenuEntity one = new MenuEntity();
        one = this.findOne(param);
        entireMenuList.add(one);


        findMenuItem(menus, entireMenuList,menuMap);

        //5 根据总叶子权限递归遍历获得完整权限树
        return processMenuToTree(entireMenuList);
    }

    private Map<String,MenuEntity> queryMenuMap() {

        List<MenuEntity> allMenu = this.menuDbDao.selectAllMenu(null);
        Map<String,MenuEntity> menuEntityMap = new HashMap<>();

        for (MenuEntity menu : allMenu) {
            menuEntityMap.put(menu.getMenuCode(),menu);
        }

        return menuEntityMap;
    }


    public void findMenuItem(List<MenuEntity> menus, List<MenuEntity> entireMenuList,Map<String,MenuEntity> menuMap) {
        for (int i = 0; i < menus.size(); i++) {
            MenuEntity leaf = new MenuEntity();
            leaf = menus.get(i);
            List<MenuEntity> partList = treeFind(leaf,menuMap);
            entireMenuList.addAll(partList);
        }
    }

    /**
     * 根据总叶子权限递归遍历获得完整权限树
     *
     * @param menus
     * @return
     */
    private List<MenuEntity> processMenuToTree(List<MenuEntity> menus) {

        Map<String, MenuEntity> map = new HashMap<String, MenuEntity>();
        List<MenuEntity> result = new ArrayList<MenuEntity>();
        //将所有的菜单对象通过key=菜单编码,value=菜单对象的形式存在map中
        for (MenuEntity m : menus) {
            map.put(m.getMenuCode(), m);
            //取得父ID存result中
            if (StringUtils.isEmpty(m.getParentCode())
                    || m.getParentCode().equals(MenuType.ROOT_MENU)) {
                result.add(m);
            }
        }
        //将map集合去重转换成set集合遍历，如果当前menu对象存在父id，
        //则将当前这个menu对象的父menu对象找到，再去判断这个父menu对象下没有子menu对象集合，
        //如果没有子menu对象集合 则new一个子menu对象集合,将当前menu对象存到父menu对象的子menu集合中去
        for (Map.Entry<String, MenuEntity> entry : map.entrySet()) {
            MenuEntity me = map.get(entry.getValue().getParentCode());
            if (me != null) {
                if (me.getChildren() == null) {
                    me.setChildren(new ArrayList<MenuEntity>());
                }
                me.getChildren().add(entry.getValue());
                Collections.sort(me.getChildren());
            }
        }
        return result;

    }


    @Override
    public List<MenuEntity> getUserMenuTreeList(String empPostCode, String userCode) {
        Map map = new HashMap();
        if (StringUtils.isNotEmpty(empPostCode)) {
            map.put("postCode", empPostCode);
        }
        if (StringUtils.isNotEmpty(userCode)) {
            map.put("userCode", userCode);
        }
        List<String> menuCodeList = queryAllMenuCodeByUserCodeAndPostCode(map);

        //用户菜单列表
        return getAllMenuList(menuCodeList);

    }

    @Override
    public List<String> queryAllMenuCodeByUserCodeAndPostCode(Map map) {
        return menuDbDao.queryAllMenuCodeByUserCodeAndPostCode(map);
    }

    @Override
    public List<MenuEntity> queryAllMenuByUserCodeAndPostCode(Map map) {
        return this.menuDbDao.queryAllMenuByUserCodeAndPostCode(map);
    }

    @Override
    public List<MenuEntity> getAllMenuList(List<String> menuCodeList) {
        //查询根菜单列表
        List<MenuEntity> menuList = queryMenuListByParentCode("0", menuCodeList);
        //递归获取子菜单
        getMenuTreeList(menuList, menuCodeList);

        return menuList;
    }

    @Override
    public List<MenuEntity> queryMenuListByParentCode(String parentCode, List<String> menuCodeList) {
        List<MenuEntity> menuList = queryMenuListByParentCode(parentCode);
        if (menuList == null) {
            return menuList;
        }
        //每次返回的子节点集合
        List<MenuEntity> userMenuList = new ArrayList<>();
        for (MenuEntity menu : menuList) {
            if ((menuCodeList.contains(menu.getMenuCode())
                    || menu.getMenuType().equals(MenuType.ROOT_MENU)
                    || menu.getMenuType().equals(MenuType.MENU)
            ) && !userMenuList.contains(menu)) {
                userMenuList.add(menu);
            }
        }

        return userMenuList;
    }


    @Override
    public MenuEntity queryMenuByChildMenuCode(String menuCode) {
        return this.menuDbDao.queryMenuByChildMenuCode(menuCode);
    }

    @Override
    public List<MenuEntity> queryMenuListByParentCode(String parentCode) {
        return menuDbDao.queryListByParentCode(parentCode);
    }

    @Override
    public List<MenuEntity> getUserJudgeMenuList(String empPostCode, String userCode) {
        Map map = new HashMap();
        if (StringUtils.isNotEmpty(empPostCode)) {
            map.put("postCode", empPostCode);
        }
        if (StringUtils.isNotEmpty(userCode)) {
            map.put("userCode", userCode);
        }
        List<MenuEntity> judgeMenuList = menuDbDao.getUserJudgeMenuList(map);
        return judgeMenuList;
    }

    @Override
    public List<MenuEntity> selectAllMenu(Map map) {
        return this.menuDbDao.selectAllMenu(map);
    }

    @Override
    public List<MenuEntity> findAllRecursion() {
        return this.menuDbDao.findAllRecursion();
    }

    /**
     * 递归
     */
    private List<MenuEntity> getMenuTreeList(List<MenuEntity> menuList, List<String> menuCodeList) {
        List<MenuEntity> subMenuList = new ArrayList<MenuEntity>();

        for (MenuEntity entity : menuList) {
            //目录
            if (entity.getMenuType().equals(MenuType.ROOT_MENU)
                    || entity.getMenuType().equals(MenuType.MENU)) {
                List<MenuEntity> childMenuList = getMenuTreeList(queryMenuListByParentCode(entity.getMenuCode(), menuCodeList), menuCodeList);
                if (childMenuList.size() > 0) {
                    entity.setChildren(childMenuList);
                } else {
                    entity.setChildren(null);
                }
            }
            if (!entity.getMenuType().equals(MenuType.FUNCTION)) {
                subMenuList.add(entity);
            }
        }

        return subMenuList;
    }
}

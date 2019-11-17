package com.kfwy.hkp.hrm.org.dept.util;

import com.google.common.collect.Lists;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/7/4
 * @Description: TODO
 */
public class DeptTreeUtils {

    private List<DeptEntity> rootList; // 部门根节点对象存放到这里

    private List<DeptEntity> deptList; // 部门列表节点存放到这里，可包含根节点

    public DeptTreeUtils(List<DeptEntity> rootList, List<DeptEntity> deptList) {
        this.rootList = rootList;
        this.deptList = deptList;
    }

    public List<DeptEntity> getTree() {
        if (deptList != null && !deptList.isEmpty()) {
            // 传递根对象和一个空map 声明一个map，用来过滤已操作过的数据
            rootList.forEach(dept -> getChild(dept, new HashMap<>(deptList.size())));
            return rootList;
        }
        return null;
    }

    public void getChild(DeptEntity dept, Map<String, String> map) {
        List<DeptEntity> childList = Lists.newArrayList();
        deptList.stream()
                .filter(e -> !map.containsKey(e.getDeptCode()))// map内不包含子节点的code
                .filter(e -> e.getParentCode().equals(dept.getDeptCode()))// 子节点的父code==根节点的code 继续循环
                .forEach(e -> {
                    map.put(e.getDeptCode(), e.getParentCode());// 当前节点code和父节点code
                    getChild(e, map);// 递归调用
                    childList.add(e);
                });
        dept.setChildren(childList);
    }
}
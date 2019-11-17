package com.kfwy.hkp.controller.sys.auth.vo;

import com.kfwy.hkp.sys.auth.entity.MenuEntity;
import com.kfwy.hkp.sys.auth.entity.RoleEntity;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
* @Description 角色封装类，封装角色可以查看目录集合
* @Auth liwensihan
* @Date 2018/11/9
* @return
**/
public class RoleDto extends RoleEntity {

    private List<MenuEntity> menus;

    public RoleDto(){}

    public RoleDto(RoleEntity role){
        BeanUtils.copyProperties(role,this);
    }


    public List<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
    }


}

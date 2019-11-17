package com.kfwy.hkp.sys.user.api;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.kfwy.hkp.sys.user.entity.UserDto;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/26 21:26
 */
public class UserApiResponse extends AbstractServiceResponse {

    /**
     * 分页的请求结果
     */
    private PageResult<UserEntity> result;

    /**
     * 详情请求结果
     */
    private UserEntity user;

    /**
     * 无分页的请求结果
     */
    private List<UserEntity> list;

    /**
     * 无分页的请求结果
     */
    private List<UserDto> userDtos;

    public List<UserDto> getUserDtos() {
        return userDtos;
    }

    public void setUserDtos(List<UserDto> userDtos) {
        this.userDtos = userDtos;
    }

    @Override
    public PageResult<UserEntity> getResult() {
        return result;
    }

    public void setResult(PageResult<UserEntity> result) {
        this.result = result;
    }

    public List<UserEntity> getList() {
        return list;
    }

    public void setList(List<UserEntity> list) {
        this.list = list;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}

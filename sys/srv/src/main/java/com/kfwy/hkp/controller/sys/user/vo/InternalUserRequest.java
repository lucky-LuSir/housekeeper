package com.kfwy.hkp.controller.sys.user.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.sys.user.api.UserApiVo;
import com.kfwy.hkp.sys.user.entity.InternalUserEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;

/**
 *
 * @author davidcun
 * @date 2018/5/18
 */
public class InternalUserRequest extends AbstractServiceRequest<UserEntity> {

    private String keyword;


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}

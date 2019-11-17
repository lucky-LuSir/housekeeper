package com.kfwy.hkp.controller.sys.user.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.sys.user.entity.UserApplyTeamEntity;

/**
 * Create By xpp on 2018/7/31
 */
public class UserApplyTeamServiceRequest extends AbstractServiceRequest<UserApplyTeamEntity> {

    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

}

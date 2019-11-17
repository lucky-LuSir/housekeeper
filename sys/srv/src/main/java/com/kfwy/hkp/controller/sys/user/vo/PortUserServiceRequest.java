package com.kfwy.hkp.controller.sys.user.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.sys.user.entity.PortUserEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;


public class PortUserServiceRequest extends AbstractServiceRequest<PortUserEntity> {
    private String fileCode;

    private String keyword;

    public String getKeyword () {
        return keyword;
    }

    public void setKeyword (String keyword) {
        this.keyword = keyword;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }
}

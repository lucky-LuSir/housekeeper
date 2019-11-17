package com.kfwy.hkp.controller.hos.house.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.hos.house.entity.*;

/**
 * Create By xpp on 2018/7/31
 */
public class SharePoolApplyServiceRequest extends AbstractServiceRequest<SharePoolApplyEntity> {

    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

}

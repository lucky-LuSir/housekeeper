package com.kfwy.hkp.controller.quit.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.hkp.quit.entity.SpecialEntity;

public class ToolsDataTransferRequest extends AbstractServiceRequest<SpecialEntity> {

    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

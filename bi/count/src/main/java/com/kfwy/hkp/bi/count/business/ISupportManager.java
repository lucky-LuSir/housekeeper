package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.bi.count.entity.SupportEntity;




public interface ISupportManager extends IManager<SupportEntity> {

    public void selectSupport();

}

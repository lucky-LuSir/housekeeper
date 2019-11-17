package com.kfwy.hkp.quit.manager;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.quit.entity.SpecialEntity;

public interface IToolsDataTransferManger extends IManager<SpecialEntity> {

    /**
     * 工具箱转移数据
     * @param specialEntity
     * @see com.kfwy.hkp.quit.entity.enums.TransferType
     * @param type 转移的类型
     * @return
     */
    SpecialEntity transfer(SpecialEntity specialEntity,Integer type);
}

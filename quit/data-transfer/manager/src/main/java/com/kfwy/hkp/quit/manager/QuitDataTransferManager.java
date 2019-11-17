package com.kfwy.hkp.quit.manager;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.quit.entity.QuitEntity;

/**
 * 离职数据转移业务层接口
 */
public interface QuitDataTransferManager extends IManager<QuitEntity> {

    /**
     * 离职数据转移
     * @param quitEntity
     * @return
     */
    int update(QuitEntity quitEntity);

    /**
     * 在转移前 获取房源 业主客户 兼职 等编码集合
     * @return
     */
    QuitEntity getQuitEntity(QuitEntity quitEntity);

    /**
     * 数据撤回
     * @param quitEntity
     * @return
     */
    int withdraw(QuitEntity quitEntity);

    /**
     * 关闭账号
     * @param quitEntity
     * @return
     */
    int deleteEmp(QuitEntity quitEntity);
}

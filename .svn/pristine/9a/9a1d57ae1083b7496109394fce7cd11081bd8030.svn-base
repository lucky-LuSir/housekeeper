package com.kfwy.hkp.quit.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.quit.entity.SpecialEntity;

import java.util.List;

/**
 * 工具箱转移 dao接口
 */
public interface IToolsDataTransferDao extends IMyBatisBaseDao<SpecialEntity,Long> {

    /**
     * 获取需要转移的房源
     * @param specialEntity
     * @return
     */
    List<String> getTransferFromHouse(SpecialEntity specialEntity);

    /**
     * 获取需要转移的客户
     * @param specialEntity
     * @return
     */
    List<String> getTransferFromCustomer(SpecialEntity specialEntity);

    /**
     * 获取需要转移的业主
     * @param specialEntity
     * @return
     */
    List<String> getTransferFromOwner(SpecialEntity specialEntity);

    /**
     * 获取需要转移的兼职
     * @param specialEntity
     * @return
     */
    List<String> getTransferFromPartTime(SpecialEntity specialEntity);

    /**
     * 转移房源
     * @param specialEntity
     * @return
     */
    int transferFromHouse(SpecialEntity specialEntity);

    /**
     * 转移客户
     * @param specialEntity
     * @return
     */
    int transferFromCustomer(SpecialEntity specialEntity);

    /**
     * 转移业主
     * @param specialEntity
     * @return
     */
    int transferFromOwner(SpecialEntity specialEntity);

    /**
     * 兼职转移
     * @param specialEntity
     * @return
     */
    int transferFromPartTime(SpecialEntity specialEntity);
}

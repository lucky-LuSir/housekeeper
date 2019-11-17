package com.kfwy.hkp.base;


import com.gniuu.framework.dataaccess.PageResult;

import java.util.List;
import java.util.Map;

public interface ISystemConfigManager {

    /**
     * 查询系统配置参数
     *
     * @param map
     * @param start
     * @param pageSize
     * @return
     */
    PageResult<SystemConfigEntity> select(Map<String, Object> map, Integer start, Integer pageSize);

    /**
     * 通过key获取配置参数信息
     *
     * @param key
     * @return
     */
    SystemConfigEntity getKey(String key);

    /**
     * 通过key获取配置value
     *
     * @param key
     * @return
     */
    String getValue(String key);

    /**
     * 更新系统配置参数
     *
     * @param sce
     * @return
     */
    int update(SystemConfigEntity sce);

    /**
     * 新增系统配置参数
     *
     * @param sce
     * @return
     */
    int insert(SystemConfigEntity sce);

    /**
     * 批量删除系统参数配置
     *
     * @param ids
     * @return
     */
    int deleted(List<Long> ids);
}

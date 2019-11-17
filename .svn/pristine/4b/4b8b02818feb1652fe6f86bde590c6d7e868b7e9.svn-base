package com.kfwy.hkp.base.impl;

import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.base.*;
import com.kfwy.hkp.common.utils.DataSourceEnum;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.DynamicDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SystemConfigManagerImpl implements ISystemConfigManager {

    @Autowired
    private ISystemConfigDbDao systemConfigDbDao;

    @Autowired
    private ISystemConfigRedisDao systemConfigRedisDao;

    @Override
    public PageResult<SystemConfigEntity> select(Map<String, Object> map, Integer start, Integer pageSize) {
        if (map == null) {
            map = new HashMap<String, Object>();
        }
        map.put("isDeleted", Boolean.FALSE);
        DynamicDataSource.setDataSource (DataSourceEnum.SLAVE.getName ());
        PageResult<SystemConfigEntity> result = this.systemConfigDbDao.selectByMap (map, start, pageSize, "create_time", Boolean.FALSE);
        DynamicDataSource.clearDataSource ();
        return result;
    }

    @Override
    public SystemConfigEntity getKey(String key) {
       /* SystemConfigEntity sce = this.systemConfigRedisDao.getConfig(key);
        if (sce == null || StringUtils.isNotEmpty(sce.getKey())) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("isDeleted", Boolean.FALSE);
            map.put("status", SystemConfigStatus.YouXiao.getCode());
            map.put("key", key);
            DynamicDataSource.setDataSource (DataSourceEnum.SLAVE.getName ());
            List<SystemConfigEntity> sces = this.systemConfigDbDao.selectByMap(map);
            DynamicDataSource.clearDataSource ();
            if (sces != null && !sces.isEmpty()) {
                sce = sces.get(0);
                this.systemConfigRedisDao.addConfig(sce);
            }
        }*/

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", Boolean.FALSE);
        map.put("status", SystemConfigStatus.YouXiao.getCode());
        map.put("key", key);
        DynamicDataSource.setDataSource (DataSourceEnum.SLAVE.getName ());
        List<SystemConfigEntity> sces = this.systemConfigDbDao.selectByMap(map);
        DynamicDataSource.clearDataSource ();
        SystemConfigEntity sce= null;
        if (sces != null && !sces.isEmpty()){
            sce = sces.get(0);
        }
        return sce;
    }

    @Override
    public String getValue(String key) {
        DynamicDataSource.setDataSource (DataSourceEnum.SLAVE.getName ());
        String value = this.systemConfigRedisDao.getConfigValue(key);
        if (StringUtils.isEmpty(value) && StringUtils.isNotEmpty(key)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("isDeleted", Boolean.FALSE);
            map.put("status", SystemConfigStatus.YouXiao.getCode());
            map.put("key", key);
            List<SystemConfigEntity> sces = this.systemConfigDbDao.selectByMap(map);
            if (sces != null && !sces.isEmpty()) {
                this.systemConfigRedisDao.addConfig(sces.get(0));
                return sces.get(0).getValue();
            }
        }
        DynamicDataSource.clearDataSource ();
        return value;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(SystemConfigEntity sce) {
        //判断key是否存在
        if (sce != null) {
            sce.setLastUpdateTime(DateFormatUtil.getCurrentDateTime());
            int i = this.systemConfigDbDao.update(sce);
            this.systemConfigRedisDao.deleted(sce.getKey());
            return i;
        }
        return -1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insert(SystemConfigEntity sce) {
        if (sce != null && StringUtils.isNotEmpty(sce.getKey())) {
            SystemConfigEntity temp = this.getKey(sce.getKey());
            if (temp != null && StringUtils.isNotEmpty(sce.getKey())) {
                temp.setValue(sce.getValue());
                temp.setDescribe(sce.getValue());
                temp.setStatus(sce.getStatus());
                return this.update(temp);
            } else {
                sce.setCreateTime(DateFormatUtil.getCurrentDateTime());
                sce.setLastUpdateTime(DateFormatUtil.getCurrentDateTime());
                sce.setIsDeleted(Boolean.FALSE);
                return this.systemConfigDbDao.insert(sce);
            }
        }
        return -1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleted(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            //查询存在的参数信息
            Map<String, Object> map = new HashMap<String, Object>(2);
            map.put("ids", ids);
            List<SystemConfigEntity> sces = this.systemConfigDbDao.selectByMap(map);
            //首先删除参数信息
            int i = this.systemConfigDbDao.batchDeleteByIds(ids);
            if (sces != null && !sces.isEmpty()) {
                for (SystemConfigEntity sce : sces) {
                    this.systemConfigRedisDao.deleted(sce.getKey());
                }
            }
            return i;
        }
        return -1;
    }
}

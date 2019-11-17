package com.kfwy.hkp.sys.version.dao;


import com.gniuu.framework.common.enums.ClientType;
import com.gniuu.framework.dataaccess.redis.IRedisDao;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.sys.version.entity.AppInfoEntity;

/**
 * Created by davidcun on 2015-8-26.
 *
 * @author davidcun
 */
public interface IAppInfoRedisDao extends IRedisDao<AppInfoEntity> {

    /**
     * 获取渠道app信息
     *
     * @param type
     * @return
     */
    AppInfoEntity getAppInfo(String type);

    /**
     * 保存渠道app信息
     *
     * @param appInfo
     */
    void saveAppInfo(AppInfoEntity appInfo);

    /**
     * 获取料吧app信息
     *
     * @param type
     * @return
     */
    AppInfoEntity getOfficeAppInfo(String type);

    /**
     * 保存料吧app信息
     *
     * @param appInfo
     */
    void saveOfficeAppInfo(AppInfoEntity appInfo);
}

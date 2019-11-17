package com.kfwy.hkp.sys.user.dao;

import com.gniuu.framework.dataaccess.redis.IRedisDao;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

/**
 * Created by davidcun on 2018/5/16.
 */
public interface IUserRedisDao<T extends BaseEntity> extends IRedisDao<T>{

    /**
     * 通过authKey获取登录用户的用户编码
     * @param authKey
     * @return
     */
    public String getLoginUserCode(String authKey);

    /**
     * 保存登录authKey与userCode关系
     * @param authKey
     * @param userCode
     * @return
     */
    public void setLoginUserCode(String authKey, String userCode);


    /**
     * 更新redis用户登录授权码authKey的超时时间
     * @param authKey
     * @param expire 单位是秒
     */
    public void updateAuthKeyExpire(String authKey, long expire);


    /**
     * 通过userCode保存user信息
     * @param userCode
     * @param user
     */
    public void setUserInfo(String userCode, UserEntity user);

    /**
     * 用户userCode获取用户信息
     * @param userCode
     */
    public UserEntity getUserInfo(String userCode);



    /**
     * 删除用户登录授权信息
     * @param authKey
     */
    public void deleteAuthKey(String authKey);



}

package com.kfwy.hkp.log.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.log.entity.OperationEntity;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2019/1/19 14:02
 */
public interface IOperationDbDao extends IMyBatisBaseDao<OperationEntity,Long> {

    public List<String> getCallPhonePeopleCount(Map map);

    public OperationEntity getLastCallCusPhoneLog(Map map);

    public String callPhoneIsHandle(Map map);

    /**
     * 更新拨打电话强制跟进处理状态
     * @param map
     * @return
     */
    public int updateCallPhoneHandle(Map map);

}

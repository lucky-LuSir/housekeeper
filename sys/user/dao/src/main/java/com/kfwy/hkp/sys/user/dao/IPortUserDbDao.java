package com.kfwy.hkp.sys.user.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.user.entity.PortUserEntity;

import java.util.Map;


public interface IPortUserDbDao extends IMyBatisBaseDao<PortUserEntity,Long> {
    /**
     * 删除所有
     * @return
     */
    int deleteAll();

   PortUserEntity findPortUserByLittlePhone(String caller);
}

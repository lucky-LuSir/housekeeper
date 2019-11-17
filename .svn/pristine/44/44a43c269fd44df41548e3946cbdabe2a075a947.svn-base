package com.kfwy.hkp.sys.user.business;

import com.gniuu.framework.common.auth.Token;
import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.user.entity.PortUserEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * @description TODO
 * @auth davidCun
 * @date 2018/6/29 17:55
 * @version 1.0
 * @return
 */
public interface IPortUserManager extends IManager<PortUserEntity> {

    int deleteAll();

    int createAll(List<PortUserEntity> list);

    PortUserEntity findPortUserByLittlePhone (String caller);

}

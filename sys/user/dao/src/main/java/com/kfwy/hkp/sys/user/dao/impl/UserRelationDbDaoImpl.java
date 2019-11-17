package com.kfwy.hkp.sys.user.dao.impl;


import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.user.dao.impl.AbstractBaseUserDbDao;
import com.kfwy.hkp.sys.user.dao.IUserRelationDbDao;
import com.kfwy.hkp.sys.user.entity.UserRelationEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


/**
 * Created by lzp on 2019/4/16.
 */
@Repository
public class UserRelationDbDaoImpl extends AbstractBaseUserDbDao<UserRelationEntity> implements IUserRelationDbDao {


    @Override
    public UserRelationEntity getUserRelationEntity(String userCode) {
        HashMap<Object, Object> param = new HashMap<>();
        param.put("userCode",userCode);
        List<UserRelationEntity> userRelationEntities=this.getSqlSession().selectList("getUserRelationEntity",param);
        if (null==userRelationEntities||userRelationEntities.size()>1){
            throw new BusinessException("分成人所在组或所在部门的组长和部门经理不唯一,提成明细生成失败,请联系人事确认组织架构后再录入订单");
        }else {
            return userRelationEntities.get(0);

        }

    }
}

package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.crm.customer.dao.ISelectAddressDbDao;
import com.kfwy.hkp.crm.customer.entity.SelectAddressEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
* @Description 描述:
* @Auth xpp
* @Date 2018/11/27 10:41
* @Version 1.0
* @Param
* @return
**/
@Repository
public class SelectAddressDbDaoImpl extends AbstractMyBatisDao<SelectAddressEntity,Long> implements ISelectAddressDbDao {

    @Override
    public List<SelectAddressEntity> query(String cusCode) {
        return this.getSqlSession().selectList(this.mapperNamespace+"query",cusCode);
    }
}

package com.kfwy.hkp.hos.house.dao.impl;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hos.house.dao.ISharePoolDeptDbDao;
import com.kfwy.hkp.hos.house.entity.SharePoolDeptEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class SharePoolDeptDbDaoImpl extends AbstractMyBatisDao<SharePoolDeptEntity,Long> implements ISharePoolDeptDbDao {

    @Override
    public int add(SharePoolDeptEntity sharePoolDeptEntity) {
        return this.getSqlSession().insert(this.mapperNamespace+"add",sharePoolDeptEntity);
    }

    @Override
    public PageResult<SharePoolDeptEntity> query(Map map) {
        return null;
    }

    @Override
    public List<SharePoolDeptEntity> selectByShareCodeMap(HashMap<String, Object> spdeptmap) {
        List<SharePoolDeptEntity> spdList = this.getSqlSession().selectList(this.mapperNamespace + "selectByShareCodeMap", spdeptmap);
        return spdList;
    }

}

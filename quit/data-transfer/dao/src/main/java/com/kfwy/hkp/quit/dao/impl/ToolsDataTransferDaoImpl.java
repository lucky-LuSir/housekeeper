package com.kfwy.hkp.quit.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.quit.dao.IToolsDataTransferDao;
import com.kfwy.hkp.quit.entity.SpecialEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 工具箱转移dao实现
 */
@Repository
public class ToolsDataTransferDaoImpl extends AbstractMyBatisDao<SpecialEntity,Long> implements IToolsDataTransferDao {


    @Override
    public List<String> getTransferFromHouse(SpecialEntity specialEntity) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getTransferFromHouse",specialEntity);
    }

    @Override
    public List<String> getTransferFromCustomer(SpecialEntity specialEntity) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getTransferFromCustomer",specialEntity);
    }

    @Override
    public List<String> getTransferFromOwner(SpecialEntity specialEntity) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getTransferFromOwner",specialEntity);
    }

    @Override
    public List<String> getTransferFromPartTime(SpecialEntity specialEntity) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getTransferFromPartTime",specialEntity);
    }

    @Override
    public int transferFromHouse(SpecialEntity specialEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"transferFromHouse",specialEntity);
    }

    @Override
    public int transferFromCustomer(SpecialEntity specialEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"transferFromCustomer",specialEntity);
    }

    @Override
    public int transferFromOwner(SpecialEntity specialEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"transferFromOwner",specialEntity);
    }

    @Override
    public int transferFromPartTime(SpecialEntity specialEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"transferFromPartTime",specialEntity);
    }
}

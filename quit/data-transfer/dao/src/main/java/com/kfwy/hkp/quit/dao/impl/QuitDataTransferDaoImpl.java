package com.kfwy.hkp.quit.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.quit.dao.QuitDataTransferDao;
import com.kfwy.hkp.quit.entity.QuitEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class QuitDataTransferDaoImpl extends AbstractMyBatisDao<QuitEntity,Long> implements QuitDataTransferDao {

    @Override
    public int updateHouse(QuitEntity quitEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"updateHouse",quitEntity);
    }

    @Override
    public int updateHouseOwner(QuitEntity quitEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"updateHouseOwner",quitEntity);
    }

    @Override
    public int updateCustomer(QuitEntity quitEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"updateCustomer",quitEntity);
    }

    @Override
    public int updatePartTimer(Map<String,Object> map) {
        return this.getSqlSession().update(this.mapperNamespace+"updatePartTimer",map);
    }

    @Override
    public List<String> getHouseList(QuitEntity quitEntity) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getHouseList",quitEntity);
    }

    @Override
    public List<String> getCustomerList(QuitEntity quitEntity) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getCustomerList",quitEntity);
    }

    @Override
    public List<String> getPartTimerList(QuitEntity quitEntity) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getPartTimerList",quitEntity);
    }

    @Override
    public List<String> getHouseOwnerList(QuitEntity quitEntity) {
        return this.getSqlSession().selectList(this.mapperNamespace+"getHouseOwnerList",quitEntity);
    }

    @Override
    public String getUserPhoneByCode(String partTimeDataTransferCode) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"getUserPhoneByCode",partTimeDataTransferCode);
    }

    @Override
    public int withdrawHouseOwner(QuitEntity quitEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"withdrawHouseOwner",quitEntity);
    }

    @Override
    public int withdrawHouse(QuitEntity quitEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"withdrawHouse",quitEntity);
    }

    @Override
    public int withdrawCustomer(QuitEntity quitEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"withdrawCustomer",quitEntity);
    }

    @Override
    public int withdrawPartTimer(Map<String, Object> map) {
        return this.getSqlSession().update(this.mapperNamespace+"withdrawPartTimer",map);
    }

    @Override
    public int deleteEmp(Map<String, Object> map) {
        return this.getSqlSession().update(this.mapperNamespace+"deleteEmp",map);
    }

    @Override
    public int deleteEmpDeptCusService(Map<String, Object> map) {
        return this.getSqlSession().update(this.mapperNamespace+"deleteEmpDeptCusService",map);
    }
}

package com.kfwy.hkp.hos.house.dao.impl;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.enums.DataAccessErrorMsg;
import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.gniuu.framework.utils.ValidateUtils;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseUpdownEntity;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class HouseDbDaoImpl extends AbstractMyBatisDao<HouseEntity,Long> implements IHouseDbDao {
    @Override
    public int add(HouseEntity houseEntity) {
        int i = this.getSqlSession().insert(this.mapperNamespace + "insert", houseEntity);
        return i;
    }

    @Override
    public List<EmployeeEntity> selectByHosApply(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectByHosApply", param);
    }

    @Override
    public int selectByCoo(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectByCoo", param);
    }

    @Override
    public int selectByCusSum(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectByCusSum", param);
    }

    @Override
    public int selectByHosSum(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectByHosSum", param);
    }

    @Override
    public int selectByFollowupSum(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectByFollowupSum", param);
    }

    @Override
    public int toDayByCount(Map<String, Object> param) {

        return ((Integer)this.getSqlSession().selectOne(this.mapperNamespace + "toDayByCount", param)).intValue();
    }

    @Override
    public void upAndDownShelf(Map<String, Object> param) {
        this.getSqlSession().selectOne(this.mapperNamespace + "upAndDownShelf", param);
    }

    @Override
    public void onAndOffOpenFlag(HouseEntity house) {
        this.getSqlSession().update(this.mapperNamespace + "onAndOffOpenFlag", house);
    }

    @Override
    public void onAndOffCooOpenFlag(HouseEntity house) {
        this.getSqlSession().update(this.mapperNamespace + "onAndOffCooOpenFlag", house);

    }

    @Override
    public void onAndOffShareOpenFlag(HouseEntity house) {
        this.getSqlSession().update(this.mapperNamespace + "onAndOffShareOpenFlag", house);

    }

    @Override
    public HouseEntity detail(String houseCode) {
        return  this.getSqlSession().selectOne(this.mapperNamespace + "detail", houseCode);
    }

    @Override
    public int update(HouseEntity houseEntity) {
        ValidateUtils.notNull(houseEntity, DataAccessErrorMsg.UpdateArgsEmpty);
        List<HouseEntity> entities = new ArrayList<>();
        entities.add(houseEntity);
        return this.batchUpdate(entities);
    }

    @Override
    public int updateForOrder(HouseEntity houseEntity) {
        ValidateUtils.notNull(houseEntity, DataAccessErrorMsg.UpdateArgsEmpty);
        List<HouseEntity> entities = new ArrayList<>();
        entities.add(houseEntity);
        return this.batchUpdate(entities);
    }

    @Override
    public List<HouseEntity> selectCusSawHouses(Map<String, String> param) {
        return this.getSqlSession().selectList("selectCusSawHouses",param);
    }

    @Override
    public int selectCountByMap(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectCountByMap", param);
    }

    @Override
    public PageResult<HouseUpdownEntity> upDownQuery(Map map,Integer start,Integer pageSize) {
        return this.selectByStatement("upDownQuery",map,start,pageSize,"create_time", Boolean.FALSE);
    }

    @Override
    public void setUpAndDownLog(HouseUpdownEntity houseUpdownEntity) {
         this.getSqlSession().insert(this.mapperNamespace + "setUpAndDownLog", houseUpdownEntity);
    }

    @Override
    public List<HouseEntity> export(Map<String, Object> param) {
        return this.getSqlSession().selectList("exportHouse",param);
    }

    @Override
    public PageResult<HouseEntity> selectContractCensus(Map<String,Object> map, Integer start, Integer pageSize) {
        return this.selectByStatement("selectContractCensus",map,start,pageSize,"lease_expiration",false);
    }

    @Override
    public PageResult<HouseEntity> selectCusMatchingHos(Map<String, Object> map, Integer start, Integer pageSize, String orderBy, boolean isAse) {
        return this.selectByStatement("selectCusMatchingHos",map,start,pageSize,orderBy,isAse);
    }
}

package com.kfwy.hkp.crm.customer.dao.impl;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.enums.DataAccessErrorMsg;
import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.gniuu.framework.dataaccess.mybatis.StatementIdConstants;
import com.gniuu.framework.utils.ValidateUtils;
import com.kfwy.hkp.crm.customer.bo.FocusCusUserScoreMatchBo;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/7/16.
 */
@Repository
public class CustomerDbDaoImpl extends AbstractMyBatisDao<CustomerEntity,Long> implements ICustomerDbDao {

    @Override
    public CustomerEntity detail(String cusCode) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "detail", cusCode);
    }

    @Override
    public CustomerEntity detailCut(String cusCode) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "detailCut", cusCode);
    }

    @Override
    public int queryApplyCount(Map param) {
        return ((Integer) this.getSqlSession().selectOne(this.mapperNamespace + "queryApplyCount", param)).intValue();
    }


    @Override
    public int queryCooperateCount(Map param) {
        return ((Integer) this.getSqlSession().selectOne(this.mapperNamespace + "queryCooperateCount", param)).intValue();
    }

    @Override
    public int selectCountByMap(Map param) {
        return this.getSqlSession().selectOne(this.mapperNamespace+"selectCountByMap",param);
    }

    @Override
    public List<CustomerEntity> selectListByMap(Map param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectListByMap", param);
    }

    @Override
    public List<CustomerEntity> selectValidCusByMap(Map param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectValidCusByMap", param);
    }

    @Override
    public List<CustomerEntity> selectOrderCusByMap(Map param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectOrderCusByMap", param);
    }

    @Override
    public List<CustomerEntity> queryCustomerListByUnfollow(Map param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "queryCustomerListByUnfollow", param);
    }

    @Override
    public void upDownShelf(CustomerEntity customer) {
         this.getSqlSession().update(this.mapperNamespace + "upDownShelf", customer);
    }

    @Override
    public void onAndOffOpenFlag(CustomerEntity customer) {
         this.getSqlSession().update(this.mapperNamespace + "onAndOffOpenFlag", customer);
    }

    @Override
    public void updateLastFollowupTime(CustomerEntity customerEntity) {
        if (StringUtils.isNotEmpty(customerEntity.getCusCode())){
            this.update(customerEntity);
        }
    }

    @Override
    public int update(CustomerEntity customerEntity) {
        ValidateUtils.notNull(customerEntity, DataAccessErrorMsg.UpdateArgsEmpty);
        List<CustomerEntity> entities = new ArrayList<>();
        entities.add(customerEntity);
        return this.batchUpdate(entities);
    }
    @Override
    public int updateForOrder(CustomerEntity customerEntity) {
        ValidateUtils.notNull(customerEntity, DataAccessErrorMsg.UpdateArgsEmpty);
        List<CustomerEntity> entities = new ArrayList<>();
        entities.add(customerEntity);
        return this.batchUpdate(entities);
    }

    @Override
    public PageResult<CustomerEntity> takeLookByCustomer(String houseCode, Integer start, Integer pageSize, String orderByValue, Boolean isAsc) {
        Map<String,Object> param =new HashMap<>();
        param.put("houseCode",houseCode);
        param.put("self_page", true);
        return this.selectByStatement("takeLookByCustomer",param,start,pageSize,"create_time",false);

    }

    @Override
    public int releasePrivate(CustomerEntity customer) {
        return this.getSqlSession().update(this.mapperNamespace+"releasePrivate",customer);
    }

    @Override
    public int pullPrivate(CustomerEntity customerEntity) {
        return this.getSqlSession().update(this.mapperNamespace+"pullPrivate",customerEntity);
    }

    @Override
    public List<CustomerEntity> remindRelease() {
        return this.getSqlSession().selectList(this.mapperNamespace+"remindRelease");
    }

    @Override
    public PageResult<CustomerEntity> selectContractCensus(Map map,Integer start,Integer pageSize) {
        return this.selectByStatement("selectContractCensus",map,start,pageSize,"contract_end_time",false);
    }

    @Override
    public PageResult<CustomerEntity> selectHouseMatchingCus(Map map, Integer start, Integer pageSize, String orderBy, Boolean isAse) {
        return this.selectByStatement("selectHouseMatchingCus",map,start,pageSize,orderBy,isAse);
    }

    @Override
    public int batchUpdateCusType(List<CustomerEntity> list) {
        return getSqlSession().update(this.mapperNamespace + "batchUpdateCusType", list);
    }

    @Override
    public int batchUpdateCusOpenFlag(List<CustomerEntity> list) {
        return getSqlSession().update(this.mapperNamespace + "batchUpdateCusOpenFlag", list);
    }

    @Override
    public List<FocusCusUserScoreMatchBo> focusCusUserScoreMatch(Map map) {
        return this.getSqlSession().selectList(this.mapperNamespace+"focusCusUserScoreMatch",map);
    }
}

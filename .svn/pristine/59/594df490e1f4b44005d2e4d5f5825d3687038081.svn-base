package com.kfwy.hkp.crm.customer.dao;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.bo.FocusCusUserScoreMatchBo;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;

import java.util.List;
import java.util.Map;


/**
 * Created by zjp on 2018/7/16.
 */
public interface ICustomerDbDao extends IMyBatisBaseDao<CustomerEntity,Long> {

    CustomerEntity detail(String cusCode);

    CustomerEntity detailCut(String cusCode);

    int queryApplyCount(Map param);

//    List<EmployeeEntity> queryApplyList(Map param);

    int queryCooperateCount(Map param);

    /*
    *  统计客户数量
    * */
    int selectCountByMap(Map param);

    /*
    *  统计客户数量
    * */
    List<CustomerEntity> selectListByMap(Map param);

    /*
    *  查询有效客户
    * */
    List<CustomerEntity> selectValidCusByMap(Map param);

    /*
    *  查询开单客户
    * */
    List<CustomerEntity> selectOrderCusByMap(Map param);

    List<CustomerEntity> queryCustomerListByUnfollow(Map param);

    /**
     * 客户下架和上架
     * @param customer
     */
    void upDownShelf(CustomerEntity customer);

    /**
     * 客户公开(true)和隐藏(false)
     * @param customer
     */
    void onAndOffOpenFlag(CustomerEntity customer);

    void updateLastFollowupTime(CustomerEntity customerEntity);

    int updateForOrder(CustomerEntity customerEntity);

    PageResult<CustomerEntity> takeLookByCustomer(String houseCode, Integer start, Integer pageSize, String orderByValue, Boolean isAsc);

    int releasePrivate(CustomerEntity customer);


    int pullPrivate(CustomerEntity customerEntity);

    /**
     * 客户自动释放提醒  在释放前提醒
     * @return
     */
    List<CustomerEntity> remindRelease();

    PageResult<CustomerEntity> selectContractCensus(Map map,Integer start,Integer pageSize);

    /**
     * 房源智能匹配客户
     * @param map
     * @param start
     * @param pageSize
     * @param orderBy
     * @param isAse
     * @return
     */
    PageResult<CustomerEntity> selectHouseMatchingCus(Map map,Integer start,Integer pageSize,String orderBy,Boolean isAse);

    int batchUpdateCusType(List<CustomerEntity> list);

    int batchUpdateCusOpenFlag(List<CustomerEntity> list);

    List<FocusCusUserScoreMatchBo> focusCusUserScoreMatch(Map map);
}

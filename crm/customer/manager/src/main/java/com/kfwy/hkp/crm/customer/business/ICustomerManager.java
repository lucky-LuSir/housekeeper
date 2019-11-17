package com.kfwy.hkp.crm.customer.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerUpdownEntity;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;
import java.util.Map;


public interface ICustomerManager extends IManager<CustomerEntity>{


    /**
     * 通过cusCode来查询详情
     *
     * @param cusCode
     * @return
     */
    CustomerEntity detail(String cusCode);



    /**
     * 客户拉私
     *
     * @param cusCode
     * @return
     */
    String pullPrivate(String cusCode,String reason);

    /**
     * 根据未跟进情况来查询客户列表
     *
     * @param param
     * @return
     */
    List<CustomerEntity> queryCustomerListByUnfollow(Map param);

    /**
     * 客户下架
     *
     * @param customerEntity
     */
    String downShelf(CustomerEntity customerEntity);

    /**
     * 查询客户上下架记录
     * @param map
     * @param start
     * @param pageSize
     * @param orderBy
     * @param isAsc
     * @return
     */
    PageResult<CustomerUpdownEntity> upDownShelfQuery(Map<String,Object> map, int start, int pageSize, String orderBy, boolean isAsc);
    /**
     * 客户上架
     *
     * @param customerEntity
     */
    void upShelf(CustomerEntity customerEntity);

    /**
     * 客服上架客户
     * @param customerEntity
     */
    void cusServiceUpshelf(CustomerEntity customerEntity);

    /**
     * 公开(打开)
     *
     * @param cusCode
     */
    void onOpenFlag(String cusCode);

    /**
     * 公开(关闭)--表示隐藏
     *
     * @param cusCode
     */
    void offOpenFlag(String cusCode,String reason);

    /**
     * 客户拉私释放
     *
     * @param cusCode
     */
    void releasePrivate(String cusCode);

    /**
     * 看房客户集合
     *
     * @param param
     * @return
     */
    List<CustomerEntity> seeHouseCusList(Map param);

    /**
     * 加载操作人和所属人信息
     *
     * @param curCode
     * @param ownerCode
     * @return
     */
    Map loadCurrentUserAndOwnerUser(String curCode, String ownerCode);


    String findOwnerNameByCusCode(String cusCode);

    /**
     * 返回当前手机号的所有客户实体
     * @param cusPhone
     * @return
     */
    List<CustomerEntity> checkCustomerPhone(String cusPhone);

    Boolean checkCusPermissions(String checkCode, CustomerEntity customer, UserEntity cur);

    PageResult<CustomerEntity> selectContractCensus(Map map, Integer start, Integer pageSize);

    PageResult<CustomerEntity> selectHouseMatchingCus(Map map,Integer start,Integer pageSize,String orderBy,boolean isAse);



    /**
     * 是否能拨打手机号
     * @param cusCode
     */
    String checkCallCusPhone(String cusCode);

    /**
     * 查询用户的拉私客户数
     */
     int selectUserPrivateCustomeCount(String userCode);

    int updateSimple (CustomerEntity entity);

    public List<CustomerEntity> checkCustomerCanCreate (String cusPhone);

    int deleteCus(String cusCode);
}

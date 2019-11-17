package com.kfwy.hkp.hos.house.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseUpdownEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/7/24
 */
public interface IHouseManager extends IManager<HouseEntity> {

    public HouseEntity edit(Map<String,Object> param);

    //描述:查询编辑前的接口
    public HouseEntity editTwoVersion(Map<String,Object> param);

    public int  updateTwoVersion(HouseEntity houseEntity);

    public HouseEntity detail(String houseCode);

    public HouseEntity detailTwoVersion(String houseCode);

    public void up(Map<String,Object> param);

    public String down(Map<String,Object> param);

    public void onOpenFlag(String houseCode);

    public void offOpenFlag(String houseCode);

    public void onShareOpenFlag(String houseCode);

    public void offShareOpenFlag(String houseCode);

    public void onCooOpenFlag(String houseCode);

    public void offCooOpenFlag(String houseCode);

    public int updateCut(HouseEntity houseEntity);
    PageResult<CustomerEntity> takeLookByCustomer(String houseCode, Integer start, Integer pageSize, String orderByValue, Boolean isAsc);

    /**
     * 查询客户看过的房源集合
     * @param param
     * @return
     */
    List<HouseEntity> selectCusSawHouses(Map<String,String> param);



    Boolean checkFollowupRecord(HouseEntity oldHouseEntity, UserEntity userEntity, UserEntity houseUserEntity);

    Boolean checkAuthOfDetails(HouseEntity oldHouseEntity, UserEntity userEntity, UserEntity houseUserEntity);

    Boolean checkAuthOfFollowup(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity);

    Boolean checkAuthOfUpdate(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity);

    Boolean checkAuthOfAddVisitCustomer(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity);

    Boolean checkAuthOfUp(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity);

    Boolean checkAuthOfDown(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity);

    Boolean checkAuthOfShow(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity);

    Boolean checkAuthOfHide(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity);

    Boolean checkAuthOfVisitCustomer(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity);

    Boolean checkAuthOfAddVisitOwner(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity);

    Boolean checkAuthOfVisitOwner(HouseEntity oldHouseEntity,UserEntity userEntity,UserEntity houseUserEntity);

    String findEmpNameByHouseCode(String houseCode);

    PageResult<HouseUpdownEntity> upDownQuery(Map map,Integer start,Integer pageSize);

    List<HouseEntity> export(Map<String,Object> param);

    HouseEntity editByWeb(Map<String, Object> param);

    /**
     * 检查操作
     * @param checkCode
     * @param house
     * @param cur
     * @return
     */
    public Boolean checkHosPermissions(String checkCode, HouseEntity house, UserEntity cur);

    /**
     * 查询房源合同周期
     * @param map
     * @param start
     * @param pageSize
     * @return
     */
    PageResult<HouseEntity> selectContractCensus(Map<String,Object> map,Integer start,Integer pageSize);

    /**
     * 客户智能匹配房源
     * @param map
     * @param start
     * @param pageSize
     * @param orderBy
     * @param isAse
     * @return
     */
    PageResult<HouseEntity> selectCusMatchingHos(Map<String,Object> map,Integer start,Integer pageSize,String orderBy,boolean isAse);

    HouseEntity createTwoVersion(HouseEntity houseEntity);
}

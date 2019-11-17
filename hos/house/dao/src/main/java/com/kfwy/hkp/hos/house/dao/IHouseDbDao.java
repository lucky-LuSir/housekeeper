package com.kfwy.hkp.hos.house.dao;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseUpdownEntity;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;

import java.util.List;
import java.util.Map;

/*
*   Create By hjh @Date 2018/7/24
* */
public interface IHouseDbDao extends IMyBatisBaseDao<HouseEntity,Long> {

    public int add(HouseEntity houseEntity);

    public int toDayByCount(Map<String,Object> param);

    public int selectByCoo(Map<String,Object> param);

    public int selectByCusSum(Map<String,Object> param);

    public int selectByHosSum(Map<String,Object> param);

    public int selectByFollowupSum(Map<String,Object> param);

    public List<EmployeeEntity> selectByHosApply(Map<String,Object> param);

    public void upAndDownShelf(Map<String,Object> param);

    public void onAndOffOpenFlag(HouseEntity house);

    public void onAndOffCooOpenFlag(HouseEntity house);

    public void onAndOffShareOpenFlag(HouseEntity house);

    public HouseEntity detail(String houseCode);

    public int updateForOrder(HouseEntity houseEntity);

    List<HouseEntity> selectCusSawHouses(Map<String, String> param);

    public int selectCountByMap(Map<String, Object> param);

    PageResult<HouseUpdownEntity> upDownQuery(Map map,Integer start,Integer pageSize);

    /**
     * 房源上下架记录
     * @param houseUpdownEntity
     */
    void setUpAndDownLog(HouseUpdownEntity houseUpdownEntity);

    /**
     * 导出接口
     * @param param
     * @return
     */
    public List<HouseEntity> export(Map<String,Object> param);

    /**
     * 房源合同周期查询
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
}

package com.kfwy.hkp.quit.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.quit.entity.QuitEntity;

import java.util.List;
import java.util.Map;

public interface QuitDataTransferDao extends IMyBatisBaseDao<QuitEntity,Long> {

    /**
     * 离职数据转移  房源
     * @param quitEntity
     * @return
     */
    int updateHouse(QuitEntity quitEntity);

    /**
     * 离职 转移业主
     * @param quitEntity
     * @return
     */
    int updateHouseOwner(QuitEntity quitEntity);
    /**
     * 离职数据转移  客户
     * @param quitEntity
     * @return
     */
    int updateCustomer(QuitEntity quitEntity);

    /**
     * 离职数据转移  兼职
     * @param map
     * @return
     */
    int updatePartTimer(Map<String,Object> map);

    /**
     * 查询转移前的房源集合
     * @return
     */
    List<String> getHouseList(QuitEntity quitEntity);

    /**
     * 查询转移前的客户集合
     * @return
     */
    List<String> getCustomerList(QuitEntity quitEntity);

    /**
     * 查询转移前的兼职集合
     * @return
     */
    List<String> getPartTimerList(QuitEntity quitEntity);

    /**
     * 查询转移前的业主集合
     * @param
     * @return
     */
    List<String> getHouseOwnerList(QuitEntity quitEntity);

    /**
     * 获取转移兼职人的手机号
     * @param partTimeDataTransferCode
     * @return
     */
    String getUserPhoneByCode(String partTimeDataTransferCode);

    /**
     * 业主 数据撤回
     * @param quitEntity
     * @return
     */
    int withdrawHouseOwner(QuitEntity quitEntity);

    /**
     * 房源数据撤回
     * @param quitEntity
     * @return
     */
    int withdrawHouse(QuitEntity quitEntity);

    /**
     * 客户数据撤回
     * @param quitEntity
     * @return
     */
    int withdrawCustomer(QuitEntity quitEntity);

    /**
     * 兼职数据撤回
     * @param map
     * @return
     */
    int withdrawPartTimer(Map<String,Object> map);

    /**
     * 关闭用户账号
     * @param map
     * @return
     */
    int deleteEmp(Map<String, Object> map);

    /**
     * 假如用户为客服 直接删除其配置的部门分成
     * @param map
     * @return
     */
    int deleteEmpDeptCusService(Map<String, Object> map);

}

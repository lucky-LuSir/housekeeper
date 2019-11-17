package com.kfwy.hkp.quit.manager.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.quit.dao.QuitDataTransferDao;
import com.kfwy.hkp.quit.dao.QuitDelRedisDao;
import com.kfwy.hkp.quit.entity.QuitEntity;
import com.kfwy.hkp.quit.manager.QuitDataTransferManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 离职数据转移 业务层实现
 */
@Service
@Transactional
public class QuitDataTransferManagerImpl extends AbstractManager<QuitEntity> implements QuitDataTransferManager {


    @Autowired
    private QuitDataTransferDao quitDataTransferDao;

    @Autowired
    private QuitDelRedisDao quitDelRedisDao;

    @Override
    public int update(QuitEntity quitEntity) {
        String partTimeDataTransferPhone=quitDataTransferDao.getUserPhoneByCode(quitEntity.getPartTimeDataTransferCode());
        quitEntity.setLastUpdateTime(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("partTimeDataTransferPhone",partTimeDataTransferPhone);
        map.put("partTimeDataTransferCode",quitEntity.getPartTimeDataTransferCode());
        map.put("partTimeDataTransfer",quitEntity.getPartTimeDataTransfer());
        map.put("createCode",quitEntity.getCreateCode());
        map.put("lastUpdateTime",new Date());
        map.put("headerCode",quitEntity.getHeaderCode());
        map.put("inDeptTeamTime",quitEntity.getInDeptTeamTime());
        Integer d = 0;
        Integer a = 0;
        Integer c = 0;
        if(StringUtils.isNotEmpty(quitEntity.getPartTimeDataTransferCode())){
            c = quitDataTransferDao.updatePartTimer(map);//转移兼职
        }
        if(StringUtils.isNotEmpty(quitEntity.getPartTimeDataTransferCode())){
            d = quitDataTransferDao.updateHouseOwner(quitEntity);//转移业主
            a = quitDataTransferDao.updateHouse(quitEntity);//转移房源
        }
        Integer b = quitDataTransferDao.updateCustomer(quitEntity);//转移客户
        return a+b+c+d;
    }

    @Override
    public QuitEntity getQuitEntity(QuitEntity quitEntity) {
        List<String> customerList = quitDataTransferDao.getCustomerList(quitEntity);//获取客户编码集合
        List<String> houseList = quitDataTransferDao.getHouseList(quitEntity);//获取房源编码集合
        List<String> partTimerList = quitDataTransferDao.getPartTimerList(quitEntity);//获取兼职编码集合
        List<String> ownerList = quitDataTransferDao.getHouseOwnerList(quitEntity);//获取业主编码集合

        QuitEntity result = new QuitEntity();

        customerList = Optional.ofNullable(customerList).orElseGet(() -> new ArrayList<>());
        houseList = Optional.ofNullable(houseList).orElseGet(() -> new ArrayList<>());
        partTimerList = Optional.ofNullable(partTimerList).orElseGet(() -> new ArrayList<>());
        ownerList = Optional.ofNullable(ownerList).orElseGet(() -> new ArrayList<>());

        result.setCusCodes(customerList);
        result.setHosCodes(houseList);
        result.setUserCodes(partTimerList);
        result.setOwnCodes(ownerList);
        return result;
    }

    @Override
    public int withdraw(QuitEntity quitEntity) {
        Integer a=0,b=0,c=0,d=0;
        quitEntity.setLastUpdateTime(new Date());
        if (quitEntity.getCusCodes()!=null && quitEntity.getCusCodes().size()>0){
            a=quitDataTransferDao.withdrawCustomer(quitEntity);
        }
        if (quitEntity.getHosCodes()!=null && quitEntity.getHosCodes().size()>0){
            b=quitDataTransferDao.withdrawHouse(quitEntity);
        }
        if (quitEntity.getOwnCodes()!=null && quitEntity.getOwnCodes().size()>0){
            c=quitDataTransferDao.withdrawHouseOwner(quitEntity);
        }
        if (quitEntity.getUserCodes()!=null && quitEntity.getUserCodes().size()>0){
            String partTimeDataTransferPhone=quitDataTransferDao.getUserPhoneByCode(quitEntity.getNewEmpCode());
            Map<String,Object> map = new HashMap<>();
            map.put("partTimeDataTransferPhone",partTimeDataTransferPhone);
            map.put("partTimeDataTransferCode",quitEntity.getNewEmpCode());
            map.put("partTimeDataTransfer",quitEntity.getNewEmpName());
            map.put("hisEmpCode",quitEntity.getHisEmpCode());
            map.put("userCodes",quitEntity.getUserCodes());
            map.put("lastUpdateTime",new Date());
            map.put("headerCode",quitEntity.getHeaderCode());
            map.put("inDeptTeamTime",quitEntity.getInDeptTeamTime());
            d=quitDataTransferDao.withdrawPartTimer(map);
        }
        return a+b+c+d;
    }

    @Override
    public int deleteEmp(QuitEntity quitEntity) {
        //quitEntity.setLastUpdateTime(new Date());
        String userCode = quitEntity.getCreateCode();//需要关闭的 用户编号
        Map<String, Object> map = new HashMap<>();
        map.put("userCode",userCode);
        map.put("isDeleted",Boolean.TRUE);
        map.put("lastUpdateTime",new Date());
        int result = quitDataTransferDao.deleteEmp(map);//关闭user表
        //假如用户为客服 直接删除其配置的部门分成
        quitDataTransferDao.deleteEmpDeptCusService(map);
        quitDelRedisDao.deleteEmp(userCode);
        if (result>0){
            result = 1;
        }
        return result;
    }

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.quitDataTransferDao;
    }
}

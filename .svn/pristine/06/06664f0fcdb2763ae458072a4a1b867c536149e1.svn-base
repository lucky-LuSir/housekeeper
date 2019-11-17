package com.kfwy.hkp.crm.houseowner.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.PhoneUtils;
import com.kfwy.hkp.cooperate.dao.ICooperateDbDao;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.cooperate.enums.CooStatus;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerManager;
import com.kfwy.hkp.crm.houseowner.dao.IHouseownerDbDao;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/7/31.
 */
@Service
public class HouseownerManagerImpl extends AbstractManager<HouseownerEntity> implements IHouseownerManager {

    @Autowired
    private IHouseownerDbDao houseownerDbDao;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private ICooperateDbDao cooperateDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.houseownerDbDao;
    }

    @Override
    protected void beforeCreate(HouseownerEntity houseownerEntity) {
        // 手机号格式
        if (!PhoneUtils.isMobile(houseownerEntity.getHouseownerPhone())) {
            throw new RemoveStackBusinessException ("业主手机号码格式错误");
        }
        // 名下是否存在
        Map map = new HashMap();
        map.put("createCode", CurrentContext.getUserCode());
        map.put("empCode", CurrentContext.getUserCode());
        map.put("houseownerPhone",houseownerEntity.getHouseownerPhone());
        HouseownerEntity oldEntity = houseownerDbDao.selectUniqueByMap(map);
        if (oldEntity != null) {
            throw new RemoveStackBusinessException("您已创建过该业主手机号!");
        }
        //业主编码生成
        houseownerEntity.setHouseownerCode(CodeGenUtils.generate());
    }

    @Override
    protected void beforeUpdate(HouseownerEntity HouseownerEntity) {

    }

    @Override
    public HouseownerEntity findOne(Map<String,Object> map) {

        HouseownerEntity houseownerEntity = houseownerDbDao.selectUniqueByMap(map);
        map.clear();

        if(!CurrentContext.getUserCode().equals(houseownerEntity.getCreateCode()) &
                !CurrentContext.getUserInfo().getOwnerDeptCode().equals(houseownerEntity.getCreateDeptCode())){

            // 如果不是我的 不是同部门的
            map.put("isDeleted",Boolean.FALSE);
            map.put("ownerCode",houseownerEntity.getHouseownerCode());

            // 如果业主的房源和操作人客户合作过 则可以跟进
            List<HouseEntity> houseEntityList = houseDbDao.selectByMap(map);
            map.clear();
            map.put("isDeleted",Boolean.FALSE);
            map.put("empCode",CurrentContext.getUserCode());
            // 查询操作人的客户
            List<CustomerEntity> customerEntityList = customerManager.findByMap(map);
            map.clear();
            if(CollectionUtil.isNotEmpty(houseEntityList) && CollectionUtil.isNotEmpty(customerEntityList)){

                List<String> houseCodes = new ArrayList<>();
                List<String> cusCodes = new ArrayList<>();

                for (HouseEntity h : houseEntityList) {
                    houseCodes.add(h.getHouseCode());
                }

                for (CustomerEntity c : customerEntityList) {
                    cusCodes.add(c.getCusCode());
                }

                List<String> cooStatuses = new ArrayList<>();
                cooStatuses.add(CooStatus.ACCEPT);
                cooStatuses.add(CooStatus.FOLLOW);
                cooStatuses.add(CooStatus.CONTRACT);
                cooStatuses.add(CooStatus.END);

                // 查询是否合作过
                map.put("cusCodes",cusCodes);
                map.put("houseCodes",houseCodes);
                map.put("cooStatuses",cooStatuses);
                map.put("isDeleted",Boolean.FALSE);
                PageResult<CooperateEntity> result = cooperateDbDao.selectByMap(map, 0, 5);

                if(null == result.getData()){
                    houseownerEntity.setHouseownerPhone(null);
                }
            }else{
                houseownerEntity.setHouseownerPhone(null);
            }
        }

        return houseownerEntity;
    }

    @Override
    public HouseownerEntity details(Map<String, Object> map) {
        HouseownerEntity houseownerEntity = houseownerDbDao.selectUniqueByMap(map);
        return houseownerEntity;
    }

    @Override
    public HouseownerEntity queryHouseownerByHouseCode(String houseCode) {
        Map map = new HashMap();
        map.put("houseCode", houseCode);
        HouseownerEntity HouseownerEntity = houseownerDbDao.queryHouseownerByHouseCode(map);
        return HouseownerEntity;
    }

    @Override
    @Transactional
    public HouseownerEntity createAndReturn(HouseownerEntity houseownerEntity) {
        OperateFillUtils.fill(houseownerEntity);
        this.beforeCreate(houseownerEntity);
        int rs = this.getMyBatisDao().insert(houseownerEntity);
        this.afterCreate(houseownerEntity);
        return houseownerEntity;
    }
}

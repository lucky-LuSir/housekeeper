package com.kfwy.hkp.trade.order.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.business.IOrderPercentageManager;
import com.kfwy.hkp.trade.order.dao.IOrderPercentageDbDao;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;
import com.kfwy.hkp.trade.order.enums.OrderPercentageType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/15.
 */
@Service
public class OrderPercentageManagerImpl extends AbstractManager<OrderPercentageEntity> implements IOrderPercentageManager {

    @Autowired
    private IOrderPercentageDbDao orderPercentageDbDao;
    @Autowired
    private IUserManager userManager;

    @Override
    protected IMyBatisBaseDao<OrderPercentageEntity, Long> getMyBatisDao() {
        return this.orderPercentageDbDao;
    }

    @Override
    @Transactional
    public int create(OrderPercentageEntity orderPercentageEntity) {

        // 设置分成部门
        if (StringUtils.isEmpty(orderPercentageEntity.getPeDeptCode())) {
            UserEntity userEntity = userManager.findUserByUserCode(orderPercentageEntity.getPeEmpCode());
            orderPercentageEntity.setPeDeptCode(userEntity.getOwnerDeptCode());
        }
        // 设置分成名字
        if (StringUtils.isEmpty(orderPercentageEntity.getPercentageName())) {
            orderPercentageEntity.setPercentageName(DictionaryStorage.get(OrderPercentageType.class.getName(), orderPercentageEntity.getPercentageType()).getName());
        }

        orderPercentageEntity.setPercentageCode(CodeGenUtils.generate());
        OperateFillUtils.fill(orderPercentageEntity);
        return orderPercentageDbDao.insert(orderPercentageEntity);
    }

    @Override
    public void update(List<OrderPercentageEntity> orderPercentageEntityList, OrderEntity orderEntity) {
        List<Long> ids = null; // 不删除的id集合
        if(CollectionUtil.isNotEmpty(orderPercentageEntityList)){
            ids = new ArrayList<>();
            for(OrderPercentageEntity orderPercentageEntity : orderPercentageEntityList){
                // 设置分成业绩
                orderPercentageEntity.setProfit(orderEntity.getCommission().multiply(orderPercentageEntity.getPercentage()).divide(new BigDecimal(100)));

                if(orderPercentageEntity.getId() == null){
                    // 保存
                    if (StringUtils.isEmpty(orderPercentageEntity.getOrderCode())) {
                        orderPercentageEntity.setOrderCode(orderEntity.getOrderCode());
                    }
                    this.create(orderPercentageEntity);
                }else {
                    // 修改
                    this.update(orderPercentageEntity);
                }

                ids.add(orderPercentageEntity.getId());
            }
        }
        // 删除ownerCode内 不包含的id
        Map<String,Object> map = new HashMap<>();
        map.put("notIds",ids);
        map.put("orderCode",orderEntity.getOrderCode());
        orderPercentageDbDao.deletePercentageByOrderCode(map);
    }

    @Override
    public int createServicePe(OrderPercentageEntity orderPercentageEntity,String userCode) {
        // 设置分成部门
        UserEntity userEntity = userManager.findUserByUserCode(userCode);

        orderPercentageEntity.setPeDeptCode(userEntity.getOwnerDeptCode());

        // 设置分成名字
        if (StringUtils.isEmpty(orderPercentageEntity.getPercentageName())) {
            orderPercentageEntity.setPercentageName(DictionaryStorage.get(OrderPercentageType.class.getName(), orderPercentageEntity.getPercentageType()).getName());
        }

        orderPercentageEntity.setPercentageCode(CodeGenUtils.generate());
        OperateFillUtils.fill(orderPercentageEntity);
        return orderPercentageDbDao.insert(orderPercentageEntity);
    }
}

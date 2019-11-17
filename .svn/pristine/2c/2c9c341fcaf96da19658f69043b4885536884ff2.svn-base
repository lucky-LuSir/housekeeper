package com.kfwy.hkp.trade.order.business.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.business.IOrderPercentageApplyManager;
import com.kfwy.hkp.trade.order.dao.IOrderPercentageApplyDbDao;
import com.kfwy.hkp.trade.order.entity.OrderPercentageApplyEntity;
import com.kfwy.hkp.trade.order.entity.OrderUpdateApplyEntity;
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
 * 订单分成修改 申请 manager实现
 */
@Service
public class OrderPercentageApplyManagerImpl extends AbstractManager<OrderPercentageApplyEntity> implements IOrderPercentageApplyManager {

    @Autowired
    private IOrderPercentageApplyDbDao orderPercentageApplyDbDao;
    @Autowired
    private IUserManager userManager;

    @Override
    protected IMyBatisBaseDao<OrderPercentageApplyEntity, Long> getMyBatisDao() {
        return this.orderPercentageApplyDbDao;
    }

    @Override
    @Transactional
    public int create(OrderPercentageApplyEntity orderPercentageApplyEntity) {

        // 设置分成部门
        if (StringUtils.isEmpty(orderPercentageApplyEntity.getPeDeptCode())) {
            UserEntity userEntity = userManager.findUserByUserCode(orderPercentageApplyEntity.getPeEmpCode());
            orderPercentageApplyEntity.setPeDeptCode(userEntity.getOwnerDeptCode());
        }
        // 设置分成名字
        if (StringUtils.isEmpty(orderPercentageApplyEntity.getPercentageName())) {
            orderPercentageApplyEntity.setPercentageName(DictionaryStorage.get(OrderPercentageType.class.getName(), orderPercentageApplyEntity.getPercentageType()).getName());
        }

        orderPercentageApplyEntity.setPercentageCode(CodeGenUtils.generate());
        OperateFillUtils.fill(orderPercentageApplyEntity);
        return orderPercentageApplyDbDao.insert(orderPercentageApplyEntity);
    }

    @Override
    public void update(List<OrderPercentageApplyEntity> orderPercentageApplyEntityList, OrderUpdateApplyEntity orderUpdateApplyEntity) {
        List<Long> ids = null; // 不删除的id集合
        if(CollectionUtil.isNotEmpty(orderPercentageApplyEntityList)){
            ids = new ArrayList<>();
            for(OrderPercentageApplyEntity orderPercentageApplyEntity : orderPercentageApplyEntityList){
                // 设置分成业绩
                orderPercentageApplyEntity.setProfit(orderUpdateApplyEntity.getCommission().multiply(orderPercentageApplyEntity.getPercentage()).divide(new BigDecimal(100)));

                if(orderPercentageApplyEntity.getId() == null){
                    // 保存
                    if (StringUtils.isEmpty(orderPercentageApplyEntity.getOrderCode())) {
                        orderPercentageApplyEntity.setOrderCode(orderUpdateApplyEntity.getOrderCode());
                    }
                    this.create(orderPercentageApplyEntity);
                }else {
                    // 修改
                    this.update(orderPercentageApplyEntity);
                }

                ids.add(orderPercentageApplyEntity.getId());
            }
        }
        // 删除ownerCode内 不包含的id
        Map<String,Object> map = new HashMap<>();
        map.put("notIds",ids);
        map.put("orderCode",orderUpdateApplyEntity.getOrderCode());
        orderPercentageApplyDbDao.deletePercentageByOrderCode(map);
    }

    @Override
    public int createServicePe(OrderPercentageApplyEntity orderPercentageApplyEntity,String userCode) {
        // 设置分成部门
        UserEntity userEntity = userManager.findUserByUserCode(userCode);

        orderPercentageApplyEntity.setPeDeptCode(userEntity.getOwnerDeptCode());

        // 设置分成名字
        if (StringUtils.isEmpty(orderPercentageApplyEntity.getPercentageName())) {
            orderPercentageApplyEntity.setPercentageName(DictionaryStorage.get(OrderPercentageType.class.getName(), orderPercentageApplyEntity.getPercentageType()).getName());
        }

        orderPercentageApplyEntity.setPercentageCode(CodeGenUtils.generate());
        OperateFillUtils.fill(orderPercentageApplyEntity);
        return orderPercentageApplyDbDao.insert(orderPercentageApplyEntity);
    }
}

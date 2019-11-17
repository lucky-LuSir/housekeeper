package com.kfwy.hkp.trade.order.business.impl;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.trade.order.business.*;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceApplyEntity;
import com.kfwy.hkp.trade.order.entity.OrderPaybackEntity;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderCheckManagerImpl implements IOrderCheckManager {

    @Autowired
    private IOrderInvoiceManager orderInvoiceManager;
    @Autowired
    private IOrderInvoiceApplyManager orderInvoiceApplyManager;
    @Autowired
    private IOrderPaybackManager orderPaybackManager;
    @Autowired
    private IOrderManager orderManager;
    @Autowired
    private StringRedisTemplate template;

    @Override
    public void orderInvoiceApplyCreateCheck(OrderInvoiceApplyEntity orderInvoiceApplyEntity) {

        OrderEntity orderEntity = orderManager.findOne("orderCode",orderInvoiceApplyEntity.getOrderCode());
        Map<String,Object> map = new HashMap<>(1);
        map.put("orderCode",orderEntity.getOrderCode());

        List<OrderPaybackEntity> backList = orderPaybackManager.selectByMap(map);
        List<OrderInvoiceApplyEntity> invoiceApplyList = orderInvoiceApplyManager.findByMap(map);

        //所有的开票金额不能大于总额
        BigDecimal totalMoney = new BigDecimal(0);
        for (OrderInvoiceApplyEntity i:invoiceApplyList){
            totalMoney = totalMoney.add(i.getInvoiceTotalPrice());
        }
        totalMoney = totalMoney.add(orderInvoiceApplyEntity.getInvoiceTotalPrice());
        if(totalMoney.compareTo(orderEntity.getCommission()) == 1){
            throw new BusinessException("开票金额不要大于佣金总计");
        }

        //已回款  回款时间不能小于当前时间
        // eg：5月份回款时间 你6月份就不让开了
        if(OrderStatus.BackPayment.equals(orderEntity.getOrderStatus())){
            int total = 0;
            for(OrderPaybackEntity i:backList){
                String currentDate = DateFormatUtil.getCurrentDate("yyyy-MM-dd");
                String backTimeDate = DateFormatUtil.getFormatDateTime(i.getBackTime(),"yyyy-MM-dd");

                //系统年 与回款年
                int currentYear = Integer.parseInt(currentDate.substring(0,4));
                int backTimeYear = Integer.parseInt(backTimeDate.substring(0,4));
                //系统月 与回款月
                int currentMonth = Integer.parseInt(currentDate.substring(5,7));
                int backTimeMonth = Integer.parseInt(backTimeDate.substring(5,7));
                //大于直接开票 所以看等于
                if(backTimeYear == currentYear){
                   if(backTimeMonth < currentMonth){
                       total++;
                   }
                }else if(backTimeYear < currentYear) {
                    total++;
                }
            }
            if(total == backList.size()){
                throw new BusinessException("回款月份小于当前月份或者回款时间小于当前时间");
            }
        }

    }

    @Override
    public String getCompanyCheck() {
        String deptCode = CurrentContext.getUserInfo().getOwnerDeptCode();
        Boolean flag = template.hasKey(qiChaChaCurrentRedisKey+ deptCode);
        Integer defaultNum;
        if(template.hasKey(qiChaChaDefaultRedisKey)){
            defaultNum = Integer.parseInt(template.opsForValue().get(qiChaChaDefaultRedisKey));
        }else {
            defaultNum = 20;
            template.opsForValue().set(qiChaChaDefaultRedisKey,defaultNum+"");
        }
        Integer currentNum = 0;
        if(flag){
            currentNum= Integer.parseInt(template.opsForValue().get(qiChaChaCurrentRedisKey+ deptCode));
            if(currentNum >= defaultNum){
                throw new BusinessException(String.format("当前部门本月超过%s次了，请手动输入",defaultNum));
            }
            template.opsForValue().set(qiChaChaCurrentRedisKey+ deptCode,++currentNum+"");
        }else {
            currentNum++;
            template.opsForValue().set(qiChaChaCurrentRedisKey+ deptCode,currentNum+"");
        }
        return defaultNum - currentNum+"";
    }
}

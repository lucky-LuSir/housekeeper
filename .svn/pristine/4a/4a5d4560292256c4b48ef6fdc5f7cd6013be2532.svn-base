package com.kfwy.hkp.job.trade.wage;

import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.trade.order.dao.IOrderDbDao;
import com.kfwy.hkp.trade.order.dao.IOrderInvoiceDbDao;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import com.kfwy.hkp.trade.wage.dao.ICommissionWageDbDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Auther: lzp
 * @Date: 2019/5/21
 * @Description: TODO
 */
@Component
public class WageInvoiceJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(WageInvoiceJob.class);

    @Autowired
    private IOrderInvoiceDbDao orderInvoiceDbDao;
    @Autowired
    private IOrderDbDao orderDbDao;
    @Autowired
    private ICommissionWageDbDao commissionWageDbDao;
    @Autowired
    private IDeptManager deptManager;


    public void start() {
        LOGGER.info("--------------[开票提成结算处理]-任务开始--------------");
        Map<String,Object> param = new HashMap<>();
        Date now = new Date();
        //获取本月一号凌晨
        //获取上个月一号凌晨
        Date startTime = new Date();
        Date endTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.set(Calendar.DAY_OF_MONTH,1);
        //获取所在月第一天
        endTime =DateFormatUtil.dayBegin(cal.getTime());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(now);
        cal2.add(Calendar.MONTH,-1);
        cal2.set(Calendar.DAY_OF_MONTH,1);
        //获取所在月上个月第一天
        startTime = DateFormatUtil.dayBegin(cal2.getTime());
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        List<String> deptCodes = deptManager.getWageAllDeptCodes();
        //查询当月所有开票
        List<OrderInvoiceEntity> orderInvoiceEntities =orderInvoiceDbDao.selectInvoiceWithoutPayBack(param);
        /**
         * 历史（处理开票所在月之前的历史开票不进行处理）
         */

        if (null!=orderInvoiceEntities&&orderInvoiceEntities.size()>0){
            for (OrderInvoiceEntity orderInvoiceEntity :orderInvoiceEntities){
                if (deptCodes.contains(orderInvoiceEntity.getDeptCode())){
                    updateCommissionWage(orderInvoiceEntity);
                }
            }

        }
        LOGGER.info("--------------[开票提成结算处理]-任务结束--------------");
    }

    private void updateCommissionWage(OrderInvoiceEntity orderInvoiceEntity){
        OrderEntity orderEntity = orderDbDao.detail(orderInvoiceEntity.getOrderCode());
        //不是回款中和已汇款的订单开票不进行结算
        if (!orderEntity.getOrderStatus().equals(OrderStatus.BackPaymenting)&&!orderEntity.getOrderStatus().equals(OrderStatus.BackPayment)){
            String preSql = "update  t_hkp_trade_order_commission_wage set is_settlement=false " +
                    " WHERE pay_back_order_code=\'%s\' and order_code=\'%s\'";
            String sql = String.format(preSql,orderInvoiceEntity.getInvoiceCode(),orderInvoiceEntity.getOrderCode());
            commissionWageDbDao.updateByNativeSql(sql);
        }

    }
}

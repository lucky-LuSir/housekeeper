package com.kfwy.hkp.trade.order.business.impl;

import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.utils.JsonMapper;
import com.kfwy.hkp.base.history.business.IHistoryManager;
import com.kfwy.hkp.base.history.entity.HistoryEntity;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.trade.order.dao.IOrderDbDao;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.SourceLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Auth xpp
 * @Date 2018/12/6
 * @Version 1.0
 */
@Component("orderAopLog")
@Aspect
public class OrderAopLog {

    private static Logger logger = LoggerFactory.getLogger(OrderAopLog.class);

    @Autowired
    private IHistoryManager historyManager;
    @Autowired
    private IOrderDbDao orderDbDao;



    //订单历史记录
    @Pointcut("execution(* com.kfwy.hkp.trade.order.dao.impl.OrderDbDaoImpl*.update(..))")
    public void orderEditLog(){}
    //前置通知
    @Before(value = "orderEditLog() && args(newOrder)")
    public void orderEditBefore(OrderEntity newOrder) throws IllegalAccessException {


        HistoryEntity historyEntity = new HistoryEntity();
        OrderEntity oldOrder = orderDbDao.selectUniqueByProp("orderCode",newOrder.getOrderCode());
        if(ObjectUtils.isEmpty(oldOrder)){
            //System.out.println("数据库中不存在这个订单"+newOrder.getOrderCode());
            return;
        }
        String className = oldOrder.getClass().getName();
        String optDataCode = oldOrder.getOrderCode();
        String oldEntity  = JsonMapper.nonDefaultMapper().toJson(oldOrder);
        String newEntity = JsonMapper.nonDefaultMapper().toJson(newOrder);


        historyEntity.setClassName(oldOrder.getClass().getName());
        historyEntity.setOptDataCode(oldOrder.getOrderCode());
        //加入批次编码
        historyEntity.setBatchCode(CodeGenUtils.generate());
        historyEntity.setOldEntity(oldEntity);
        historyEntity.setNewEntity(newEntity);

        Map<String, String> newObjMap = new HashMap<String, String>();
        ParamUtil<OrderEntity> paramUtilNew = new ParamUtil<>();
        paramUtilNew.objectToMap(newOrder,newObjMap);

        Map<String, String> oldObjMap = new HashMap<String, String>();
        ParamUtil<OrderEntity> paramUtilOld = new ParamUtil<>();
        paramUtilOld.objectToMap(oldOrder,oldObjMap);

        //要比对并写入日志的字段string类型集合
        String[] array = {"cusPhone", "empCode", "houseCode","cusCode",
                "unit", "orderStatus"};
        for(int i = 0; i < array.length; i++){

            String oldFieldVal = String.valueOf(oldObjMap.get(array[i]));
            String newFieldVal = String.valueOf(newObjMap.get(array[i]));
            if(newFieldVal != null){
                if(!(oldFieldVal.equals(newFieldVal))){
                    historyEntity.setField(array[i]);
                    historyEntity.setOldValue(oldFieldVal);
                    historyEntity.setNewValue(newFieldVal);
                    if(!newFieldVal.equals("null")){
                        historyManager.createHistory(historyEntity);
                    }

                }
            }


        }

        //比对写入日志的字段bigdecimal类型acreage
        BigDecimal  oldacreage = oldOrder.getAcreage();
        BigDecimal  newacreage = newOrder.getAcreage();
        if( newacreage!=null) {
            if (oldacreage.compareTo(newacreage) != 0) {
                historyEntity.setField("acreage");
                historyEntity.setOldValue(oldacreage.toString());
                historyEntity.setNewValue(newacreage.toString());
                historyManager.createHistory(historyEntity);
            }
        }

        //比对写入日志的字段bigdecimal类型price
        BigDecimal  oldprice = oldOrder.getPrice();
        BigDecimal  newprice = newOrder.getPrice();
        if(  newprice!=null){
            if (oldprice.compareTo(newprice) != 0) {
                historyEntity.setField("price");
                historyEntity.setOldValue(oldprice.toString());
                historyEntity.setNewValue(newprice.toString());
                historyManager.createHistory(historyEntity);
            }
        }

        //比对写入日志的字段bigdecimal类型cusRebate
        BigDecimal  oldcusRebate = oldOrder.getCusRebate();
        BigDecimal  newcusRebate = newOrder.getCusRebate();
        if(  newcusRebate!=null) {
            if (oldcusRebate.compareTo(newcusRebate) != 0) {
                historyEntity.setField("cusRebate");
                historyEntity.setOldValue(oldcusRebate.toString());
                historyEntity.setNewValue(newcusRebate.toString());
                historyManager.createHistory(historyEntity);
            }
        }

        //比对写入日志的字段bigdecimal类型ownerRebate
        BigDecimal  oldownerRebate = oldOrder.getOwnerRebate();
        BigDecimal  newownerRebate = newOrder.getOwnerRebate();
        if(   newownerRebate!=null) {
            if (oldownerRebate.compareTo(newownerRebate) != 0) {
                historyEntity.setField("ownerRebate");
                historyEntity.setOldValue(oldownerRebate.toString());
                historyEntity.setNewValue(newownerRebate.toString());
                historyManager.createHistory(historyEntity);
            }
        }

        //比对写入日志的字段bigdecimal类型cusParttimeMoney
        BigDecimal  oldcusParttimeMoney = oldOrder.getCusParttimeMoney();
        BigDecimal  newcusParttimeMoney = newOrder.getCusParttimeMoney();
        if(   newcusParttimeMoney!=null) {
            if (oldcusParttimeMoney.compareTo(newcusParttimeMoney) != 0) {
                historyEntity.setField("cusParttimeMoney");
                historyEntity.setOldValue(oldcusParttimeMoney.toString());
                historyEntity.setNewValue(newcusParttimeMoney.toString());
                historyManager.createHistory(historyEntity);
            }
        }


        //比对写入日志的字段bigdecimal类型ownerParttimeMoney
        BigDecimal  oldownerParttimeMoney = oldOrder.getOwnerParttimeMoney();
        BigDecimal  newownerParttimeMoney = newOrder.getOwnerParttimeMoney();
        if(   newownerParttimeMoney != null) {
            if (oldownerParttimeMoney.compareTo(newownerParttimeMoney) != 0 ) {
                historyEntity.setField("ownerParttimeMoney");
                historyEntity.setOldValue(oldownerParttimeMoney.toString());
                historyEntity.setNewValue(newownerParttimeMoney.toString());
                historyManager.createHistory(historyEntity);
            }
        }

        //比对写入日志的字段bigdecimal类型monthRent
        BigDecimal  oldmonthRent = oldOrder.getMonthRent();
        BigDecimal  newmonthRent = newOrder.getMonthRent();
        if( newmonthRent!=null) {
            if (oldmonthRent.compareTo(newmonthRent) != 0) {
                historyEntity.setField("monthRent");
                historyEntity.setOldValue(oldmonthRent.toString());
                historyEntity.setNewValue(newmonthRent.toString());
                historyManager.createHistory(historyEntity);
            }
        }



        //比对写入日志的字段bigdecimal类型receivableCus
        BigDecimal  oldreceivableCus = oldOrder.getReceivableCus();
        BigDecimal  newreceivableCus = newOrder.getReceivableCus();
        if(  newreceivableCus!=null) {
            if (oldreceivableCus.compareTo(newreceivableCus) != 0) {
                historyEntity.setField("receivableCus");
                historyEntity.setOldValue(oldreceivableCus.toString());
                historyEntity.setNewValue(newreceivableCus.toString());
                historyManager.createHistory(historyEntity);
            }
        }

        //比对写入日志的字段bigdecimal类型receivableHos
        BigDecimal  oldreceivableHos = oldOrder.getReceivableHos();
        BigDecimal  newreceivableHos = newOrder.getReceivableHos();
        if( newreceivableHos!=null) {
            if (oldreceivableHos.compareTo(newreceivableHos) != 0) {
                historyEntity.setField("receivableHos");
                historyEntity.setOldValue(oldreceivableHos.toString());
                historyEntity.setNewValue(newreceivableHos.toString());
                historyManager.createHistory(historyEntity);
            }
        }

        //比对写入日志的字段bigdecimal类型monthCount
        Integer  oldmonthCount = oldOrder.getMonthCount();
        Integer  newmonthCount = newOrder.getMonthCount();
        if( newmonthCount!=null) {
            if (oldmonthCount.compareTo(newmonthCount) != 0) {
                historyEntity.setField("monthCount");
                historyEntity.setOldValue(oldmonthCount.toString());
                historyEntity.setNewValue(newmonthCount.toString());
                historyManager.createHistory(historyEntity);
            }
        }





    }

}

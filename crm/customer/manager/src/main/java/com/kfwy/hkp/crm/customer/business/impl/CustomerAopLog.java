package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.base.history.business.IHistoryManager;
import com.kfwy.hkp.base.history.entity.HistoryEntity;
import com.kfwy.hkp.common.utils.HistoryAopUtils;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kfwy_it_013
 */
@Component("customerAopLog")
@Aspect
public class CustomerAopLog {

    private static Logger logger = LoggerFactory.getLogger(CustomerAopLog.class);

    @Autowired
    private IHistoryManager historyManager;
    @Autowired
    private ICustomerDbDao customerDbDao;

    /**
     * 订单历史记录
     */
    @Pointcut("execution(* com.kfwy.hkp.crm.customer.dao.impl.CustomerDbDaoImpl*.update(..))")
    public void customerEditLog() {
    }



    /**
     * 前置通知
     * @param newCustomer
     * @throws IllegalAccessException
     */
    @Before(value = "customerEditLog() && args(newCustomer)")
    public void HouseEditBefore(CustomerEntity newCustomer) throws IllegalAccessException {

        CustomerEntity oldCustomer = customerDbDao.selectUniqueByProp("cusCode", newCustomer.getCusCode());
        if (ObjectUtils.isEmpty(oldCustomer)) {
            //System.out.println("数据库中不存在这个房源"+newHouse.getHosueCode());
            return;
        }
        //加入批次编码
        String batchCode=CodeGenUtils.generate();
        String className=oldCustomer.getClass().getName();
        String dataCode=oldCustomer.getCusCode();


        Map<String, String> newObjMap = new HashMap<String, String>();
        ParamUtil<CustomerEntity> paramUtilNew = new ParamUtil<>();
        paramUtilNew.objectToMap(newCustomer, newObjMap);

        Map<String, String> oldObjMap = new HashMap<String, String>();
        ParamUtil<CustomerEntity> paramUtilOld = new ParamUtil<>();
        paramUtilOld.objectToMap(oldCustomer, oldObjMap);

        //要比对并写入日志的字段
        String[] array = {
                "cusStatus","cusType","cusFrom","cusName","cusPhone","cusSex","companyName","industry","products",
                "priceUnit","layerNum","expectTerm","fireLevel","needEia","needRegister",
                "needCertificate","needSingleBuilding","hasOfficeArea","openFlag","houseType","description","empCode","ptCode",
                "divideType","url","level","cusProp",
                "needNearbyFacilities","category"
        };

        List<HistoryEntity> lists = HistoryAopUtils.editHistoryList(array,oldObjMap,newObjMap,batchCode,className,dataCode);

        if(null==lists){
            lists=new ArrayList<>();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date oldEnterTime = oldCustomer.getEnterTime();
        Date newEnterTime = newCustomer.getEnterTime();

        if(null!=newEnterTime){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldEnterTime){
                historyEntity.setField("enterTime");
                historyEntity.setNewValue(sdf.format(newEnterTime));
                lists.add(historyEntity);
            }else if(null!=oldEnterTime&&!sdf.format(oldEnterTime).equals(sdf.format(newEnterTime))){
                historyEntity.setField("enterTime");
                historyEntity.setNewValue(sdf.format(newEnterTime));
                historyEntity.setOldValue(sdf.format(oldEnterTime));
                lists.add(historyEntity);
            }
        }



        BigDecimal oldAcreage = oldCustomer.getNeedAcreage();
        BigDecimal  newAcreage = newCustomer.getNeedAcreage();
        if(null!=newAcreage){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldAcreage){
                historyEntity.setField("needAcreage");
                historyEntity.setNewValue(newAcreage.toString());
                lists.add(historyEntity);
            } else if(oldAcreage.compareTo(newAcreage)!=0){
                historyEntity.setField("needAcreage");
                historyEntity.setOldValue(oldAcreage.toString());
                historyEntity.setNewValue(newAcreage.toString());
                lists.add(historyEntity);
            }
        }

        BigDecimal oldNeedPrice = oldCustomer.getNeedPrice();
        BigDecimal  newNeedPrice = newCustomer.getNeedPrice();
        if(null!=newNeedPrice){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldNeedPrice){
                historyEntity.setField("needPrice");
                historyEntity.setNewValue(newNeedPrice.toString());
                lists.add(historyEntity);
            } else if(oldNeedPrice.compareTo(newNeedPrice)!=0){
                historyEntity.setField("needPrice");
                historyEntity.setOldValue(oldNeedPrice.toString());
                historyEntity.setNewValue(newNeedPrice.toString());
                lists.add(historyEntity);
            }
        }

        BigDecimal oldLayerHeight = oldCustomer.getLayerHeight();
        BigDecimal  newLayerHeight = newCustomer.getLayerHeight();
        if(null!=newLayerHeight){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldLayerHeight){
                historyEntity.setField("layerHeight");
                historyEntity.setNewValue(newLayerHeight.toString());
                lists.add(historyEntity);
            } else if(oldLayerHeight.compareTo(newLayerHeight)!=0){
                historyEntity.setField("layerHeight");
                historyEntity.setOldValue(oldLayerHeight.toString());
                historyEntity.setNewValue(newLayerHeight.toString());
                lists.add(historyEntity);
            }
        }

        BigDecimal oldNeedVoltage = oldCustomer.getNeedVoltage();
        BigDecimal  newNeedVoltage = newCustomer.getNeedVoltage();
        if(null!=newNeedVoltage){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldNeedVoltage){
                historyEntity.setField("needVoltage");
                historyEntity.setNewValue(newNeedVoltage.toString());
                lists.add(historyEntity);
            } else if(oldNeedVoltage.compareTo(newNeedVoltage)!=0){
                historyEntity.setField("needVoltage");
                historyEntity.setOldValue(oldNeedVoltage.toString());
                historyEntity.setNewValue(newNeedVoltage.toString());
                lists.add(historyEntity);
            }
        }

        BigDecimal oldDivideRatio = oldCustomer.getDivideRatio();
        BigDecimal  newDivideRatio = newCustomer.getDivideRatio();
        if(null!=newDivideRatio){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldDivideRatio){
                historyEntity.setField("divideRatio");
                historyEntity.setNewValue(newDivideRatio.toString());
                lists.add(historyEntity);
            } else if(oldDivideRatio.compareTo(newDivideRatio)!=0){
                historyEntity.setField("divideRatio");
                historyEntity.setOldValue(oldDivideRatio.toString());
                historyEntity.setNewValue(newDivideRatio.toString());
                lists.add(historyEntity);
            }
        }

        BigDecimal oldDivideCash = oldCustomer.getDivideCash();
        BigDecimal  newDivideCash = newCustomer.getDivideCash();
        if(null!=newDivideCash){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldDivideCash){
                historyEntity.setField("divideCash");
                historyEntity.setNewValue(newDivideCash.toString());
                lists.add(historyEntity);
            } else if(oldDivideCash.compareTo(newDivideCash)!=0){
                historyEntity.setField("divideCash");
                historyEntity.setOldValue(oldDivideCash.toString());
                historyEntity.setNewValue(newDivideCash.toString());
                lists.add(historyEntity);
            }
        }

        if(null!=lists&&lists.size()>0){
            historyManager.createHistory(lists);

        }
    }

}

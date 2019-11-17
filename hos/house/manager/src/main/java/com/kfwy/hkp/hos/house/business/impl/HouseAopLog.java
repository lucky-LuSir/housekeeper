package com.kfwy.hkp.hos.house.business.impl;

import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.base.history.business.IHistoryManager;
import com.kfwy.hkp.base.history.entity.HistoryEntity;
import com.kfwy.hkp.common.utils.HistoryAopUtils;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Component("houseAopLog")
@Aspect
public class HouseAopLog {
    private static Logger logger = LoggerFactory.getLogger(HouseAopLog.class);

    @Autowired
    private IHistoryManager historyManager;
    @Autowired
    private IHouseDbDao houseDbDao;

    //订单历史记录
    @Pointcut("execution(* com.kfwy.hkp.hos.house.dao.impl.HouseDbDaoImpl*.update(..))")
    public void houseEditLog() {
    }

    //前置通知
    @Before(value = "houseEditLog() && args(newHouse)")
    public void HouseEditBefore(HouseEntity newHouse) throws IllegalAccessException {



        HouseEntity oldHouse = houseDbDao.selectUniqueByProp("houseCode", newHouse.getHouseCode());
        if (ObjectUtils.isEmpty(oldHouse)) {
            //System.out.println("数据库中不存在这个房源"+newHouse.getHosueCode());
            return;
        }

        //加入批次编码
        String batchCode=CodeGenUtils.generate();
        String className=oldHouse.getClass().getName();
        String dataCode=oldHouse.getHouseCode();

        Map<String, String> newObjMap = new HashMap<String, String>();
        ParamUtil<HouseEntity> paramUtilNew = new ParamUtil<>();
        paramUtilNew.objectToMap(newHouse, newObjMap);

        Map<String, String> oldObjMap = new HashMap<String, String>();
        ParamUtil<HouseEntity> paramUtilOld = new ParamUtil<>();
        paramUtilOld.objectToMap(oldHouse, oldObjMap);

        //要比对并写入日志的字段
        String[] array = {"houseType","houseStatus","housePurpose","houseStyle","locationCode","ownerCode",
                "empCode","hasAloneBuilding","whereBuilding","hasMonolayer","severalLayers",
                "whereLayer","hasEia","hasCut","hasCertificate","hasRegistry","hasShortLease","hasPlatform",
                "hasElevator","hasBathroom","openFlag","hasDischargeSewage",
                "unit","lessLease","moreLease","maxElectric",
                "fireLevel","houseStructure","industryRestriction","forInsdustry","features",
                "ptCode","divideType",
                "authDeptCode","authImage","authCode","propertyCompany",
                "maxPassCar","elevatorStandard","elevatorNumber","elevatorDoor"
//                "decorationType","hasCrane","minAcreage",
        };
        List<HistoryEntity> lists = HistoryAopUtils.editHistoryList(array,oldObjMap,newObjMap,batchCode,className,dataCode);

        //比对写入日志的字段bigdecimal类型price

        if(null==lists){
            lists=new ArrayList<>();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date oldEnterTime = oldHouse.getEnterTime();
        Date newEnterTime = newHouse.getEnterTime();

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


        BigDecimal oldPrice = oldHouse.getPrice();
        BigDecimal  newPrice = newHouse.getPrice();
        if (null!=newPrice){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldPrice){
                historyEntity.setField("price");
                historyEntity.setNewValue(newPrice.toString());
                lists.add(historyEntity);
            }else if(oldPrice.compareTo(newPrice)!=0){
                historyEntity.setField("price");
                historyEntity.setOldValue(oldPrice.toString());
                historyEntity.setNewValue(newPrice.toString());
                lists.add(historyEntity);
            }

        }

        BigDecimal oldArea = oldHouse.getArea();
        BigDecimal  newArea = newHouse.getArea();
        if(null!=newArea){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldArea){
                historyEntity.setField("area");
                historyEntity.setNewValue(newArea.toString());
                lists.add(historyEntity);
            } else if(oldArea.compareTo(newArea)!=0){
                historyEntity.setField("area");
                historyEntity.setOldValue(oldArea.toString());
                historyEntity.setNewValue(newArea.toString());
                lists.add(historyEntity);
            }
        }

        BigDecimal oldLayerArea = oldHouse.getLayerArea();
        BigDecimal  newLayerArea = newHouse.getLayerArea();
        if(null!=newLayerArea){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldLayerArea){
                historyEntity.setField("layerArea");
                historyEntity.setNewValue(newLayerArea.toString());
                lists.add(historyEntity);
            } else if(oldLayerArea.compareTo(newLayerArea)!=0){
                historyEntity.setField("layerArea");
                historyEntity.setOldValue(oldLayerArea.toString());
                historyEntity.setNewValue(newLayerArea.toString());
                lists.add(historyEntity);
            }
        }

        BigDecimal oldLayerHeight = oldHouse.getLayerHeight();
        BigDecimal  newLayerHeight = newHouse.getLayerHeight();
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


        BigDecimal oldPropertyFee = oldHouse.getPropertyFee();
        BigDecimal  newPropertyFee = newHouse.getPropertyFee();
        if(null!=newPropertyFee){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldPropertyFee){
                historyEntity.setField("propertyFee");
                historyEntity.setNewValue(newPropertyFee.toString());
                lists.add(historyEntity);
            } else if(oldPropertyFee.compareTo(newPropertyFee)!=0){
                historyEntity.setField("propertyFee");
                historyEntity.setOldValue(oldPropertyFee.toString());
                historyEntity.setNewValue(newPropertyFee.toString());
                lists.add(historyEntity);
            }
        }


        BigDecimal oldDivideRatio= oldHouse.getDivideRatio();
        BigDecimal  newDdivideRatio = newHouse.getDivideRatio();
        if(null!=newDdivideRatio){
            HistoryEntity historyEntity=new HistoryEntity();
            historyEntity.setBatchCode(batchCode);
            historyEntity.setClassName(className);
            historyEntity.setOptDataCode(dataCode);
            if(null==oldDivideRatio){
                historyEntity.setField("divideRatio");
                historyEntity.setNewValue(newDdivideRatio.toString());
                lists.add(historyEntity);
            } else if(oldDivideRatio.compareTo(newDdivideRatio)!=0){
                historyEntity.setField("divideRatio");
                historyEntity.setOldValue(oldDivideRatio.toString());
                historyEntity.setNewValue(newDdivideRatio.toString());
                lists.add(historyEntity);
            }
        }


        BigDecimal oldDivideCash = oldHouse.getDivideCash();
        BigDecimal  newDivideCash = newHouse.getDivideCash();
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

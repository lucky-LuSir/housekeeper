package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IGraphManager;
import com.kfwy.hkp.bi.count.dao.IGraphDbDao;
import com.kfwy.hkp.bi.count.entity.GraphEntity;

import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DataSourceEnum;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;


@Service
public class GraphManagerImpl extends AbstractManager<GraphEntity> implements IGraphManager {

    @Autowired
    private IGraphDbDao graphDbDao;

    @Override
    protected IMyBatisBaseDao<GraphEntity, Long> getMyBatisDao() {
        return this.graphDbDao;
    }

    @Override
    public GraphEntity selectEntrustAndBespeak(String timeStr, Date time,Map<String,Object> param) {
        Date startTime = new Date();
        Date endTime = new Date();
        startTime = DateFormatUtil.dayBegin(startTime);
        endTime = DateFormatUtil.dayEnd(endTime);
        Calendar cal = Calendar.getInstance();

        if (timeStr.equals("month")){
            if (null==time){
                cal.setTime(startTime);
                cal.add(Calendar.DATE,-30);
                startTime = cal.getTime();
                //默认30天
            }else {
                cal.setTime(time);
                cal.set(Calendar.DAY_OF_MONTH,1);
                startTime=cal.getTime();
                cal.add(Calendar.MONTH,1);
                cal.set(Calendar.DAY_OF_MONTH,0);
                endTime=cal.getTime();
                //所在月
            }
            timeStr="yyyy-MM-dd";
        }else if (timeStr.equals("year")){
            if (null==time){
                cal.setTime(startTime);
                cal.add(Calendar.MONTH,-11);
                cal.set(Calendar.DAY_OF_MONTH,1);
                startTime=cal.getTime();
                //默认12个月
            }else {
                cal.setTime(time);
                cal.set(Calendar.DAY_OF_YEAR,1);
                startTime=cal.getTime();
                cal.add(Calendar.YEAR,1);
                cal.set(Calendar.DAY_OF_YEAR,1);
                endTime =cal.getTime();
                //所在年
            }
            timeStr="yyyy-MM";
        }else {
            throw new RemoveStackBusinessException ("未知时间维度");
        }

        param.put("startTime",startTime);
        param.put("endTime",endTime);
        param.put("timeStr",timeStr);
        param.put("isDeleted",false);
        GraphEntity graphEntity = new GraphEntity();
        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        graphEntity.setBespeak(fullData(fillData(startTime,endTime,timeStr),graphDbDao.selectBespeakByMap(param)));
        graphEntity.setEntrust(fullData(fillData(startTime,endTime,timeStr),graphDbDao.selectEntrustByMap(param)));
        graphEntity.setCount(fullData(fillData(startTime,endTime,timeStr),graphDbDao.selectEntrustAndBespeakCountByMap(param)));
        DynamicDataSource.setDataSource(DataSourceEnum.MASTER.getName());
        return graphEntity;
    }

    private List<GraphEntity> fillData(Date startTime,Date endTime,String timeStr){
        List<String> timeStrs = new ArrayList<>();
        int count =0;
        SimpleDateFormat sdf =   new SimpleDateFormat( timeStr);

        Instant startInstant = startTime.toInstant();
        Instant endInstant = endTime.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localStartDate = startInstant.atZone(zoneId).toLocalDate();
        LocalDate localEndDate = endInstant.atZone(zoneId).toLocalDate();
        Period p = Period.between(localStartDate, localEndDate);


        Calendar cals = Calendar.getInstance();
        cals.setTime(startTime);
        long time1 = cals.getTimeInMillis();
        cals.setTime(endTime);
        long time2 = cals.getTimeInMillis();

        if (timeStr.equals("yyyy-MM")){
            if (p.getYears()==1){
                count=12;
            }else {
                count = p.getMonths()+1;
            }
        }else if (timeStr.equals("yyyy-MM-dd")){
            long between_days=(time2-time1+1)/(1000*3600*24);
            count = Integer.parseInt(String.valueOf(between_days));
        }else {
            throw new RemoveStackBusinessException("未知时间维度");
        }
        for (int i=0;i<count;i++){
            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            if (timeStr.equals("yyyy-MM")){
                cal.add(Calendar.MONTH,i);
            }else if (timeStr.equals("yyyy-MM-dd")){
                cal.add(Calendar.DATE,i);
            }else {
                throw new RemoveStackBusinessException("未知时间维度");
            }
            Date date = cal.getTime();
            timeStrs.add(sdf.format(date));
        }
        List<GraphEntity> graphEntities = new ArrayList<>();
        for (String str : timeStrs) {
            GraphEntity graphEntity = new GraphEntity();
            graphEntity.setNum(0);
            graphEntity.setTimeStr(str);
            graphEntities.add(graphEntity);
        }
        return graphEntities;
    }

    public List<GraphEntity> fullData(List<GraphEntity> fill,List<GraphEntity> data){
        for (GraphEntity graphEntity : fill) {
            for (GraphEntity datum : data) {
                if (graphEntity.getTimeStr().equals(datum.getTimeStr())){
                    graphEntity.setNum(datum.getNum());
                    break;
                }
            }
        }
        return fill;
    }
}

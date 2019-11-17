package com.kfwy.hkp.trade.wage.business.impl;

import com.gniuu.framework.common.business.AbstractManager;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.trade.wage.business.IWageManager;
import com.kfwy.hkp.trade.wage.dao.IWageDbDao;
import com.kfwy.hkp.trade.wage.entity.WageEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by lzp on 2019/5/8.
 */
@Service
public  class WageManagerImpl extends AbstractManager<WageEntity> implements IWageManager {

    @Autowired
    private IWageDbDao wageDbDao;
    @Override
    protected IMyBatisBaseDao<WageEntity, Long> getMyBatisDao() {
        return this.wageDbDao;
    }

    @Override
    public PageResult<WageEntity> wageReport(Map<String, Object> map, Integer start, Integer pageSize) throws ParseException {
        PageResult<WageEntity> pageResult = this.wageDbDao.selectByMap(map,start, pageSize,"work_number",false);
        if (null!=pageResult.getData()){
            pageResult.setData(getInserviceTime(pageResult.getData()));

        }
        return pageResult;
    }

    @Override
    public List<WageEntity> wageReport(Map<String, Object> map) throws ParseException {
        List<WageEntity> wageEntities = this.wageDbDao.selectByMap(map,"work_number",false);
        if (null!=wageEntities){
            getInserviceTime(wageEntities);
        }
        return wageEntities;
    }

    @Override
    public List<WageEntity> wageDetailsReport(Map<String, Object> param) throws ParseException {
        List<WageEntity> wageEntityLsit = wageDbDao.wageDetailsReport(param);
        if (null!=wageEntityLsit){
            getInserviceTime(wageEntityLsit);
        }
        return wageEntityLsit;
    }

    public List<WageEntity> getInserviceTime(List<WageEntity> wageEntityList) throws ParseException {
        for (int i =0;i<wageEntityList.size();i++){
            Date end= new Date();
            Date start= wageEntityList.get(i).getEntryTime();
            if (null!= wageEntityList.get(i).getQuitTime()){
                end= wageEntityList.get(i).getQuitTime();
            }
            SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
            start =sdf.parse(sdf.format(start));
            end =sdf.parse(sdf.format(end));
            Instant startInstant = start.toInstant();
            Instant endInstant = end.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            // atZone()方法返回在指定时区从此Instant生成的ZonedDateTime。
            LocalDate localStartDate = startInstant.atZone(zoneId).toLocalDate();
            LocalDate localEndDate = endInstant.atZone(zoneId).toLocalDate();
            Period p = Period.between(localStartDate, localEndDate);

            wageEntityList.get(i).setInserviceTime(p.getYears()+"年"+p.getMonths()+"个月"+p.getDays()+"天");
        }
        return wageEntityList;
    }
}

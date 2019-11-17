package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.ISupportManager;
import com.kfwy.hkp.bi.count.dao.ISupportAchievementsDbDao;
import com.kfwy.hkp.bi.count.dao.ISupportDbDao;
import com.kfwy.hkp.bi.count.entity.SupportAchievementsEntity;
import com.kfwy.hkp.bi.count.entity.SupportEntity;
import com.kfwy.hkp.common.utils.DataSourceEnum;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class SupportManagerImpl extends AbstractManager<SupportEntity> implements ISupportManager {

    @Autowired
    private ISupportDbDao supportDbDao;
    @Autowired
    private ISupportAchievementsDbDao supportAchievementsDbDao;
    @Override
    protected IMyBatisBaseDao<SupportEntity, Long> getMyBatisDao() {
        return this.supportDbDao;
    }

    @Override
    public void selectSupport() {
        Map<String, Object> param = new HashMap<>();
        SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM" );
        Date inTime = new Date();
        //获取上个月第一天
        Date time = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        cal.add(Calendar.MONTH,-1);
        cal.set(Calendar.DAY_OF_MONTH,1);
        time =DateFormatUtil.dayBegin(cal.getTime());
        param.put("endTime",time);
        List<SupportEntity> supportEntities =supportDbDao.selectSupport(param);
        List<SupportAchievementsEntity> sa = new ArrayList<>();

        for (SupportEntity supportEntity:supportEntities){
            Date startTime = new Date();
            Date endTime = new Date();
            Long lastOrder=supportEntity.getLastOrderTime().getTime();
            String supportCode = CodeGenUtils.generate("C");

            supportEntity.setSupportCode(supportCode);
            Calendar cals = Calendar.getInstance();
            cals.setTime(new Date());
            cals.set(Calendar.DAY_OF_MONTH,1);
            //本月第一天
            endTime = DateFormatUtil.dayBegin(cals.getTime());
            List<SupportAchievementsEntity> supportAchievementsEntities = new ArrayList<>();
            /**
             * 近三个月业绩数据
             */
            for (int i=0;i<3;i++){
                Map<String,Object> map = new HashMap<>();
                cals.setTime(endTime);
                cals.add(Calendar.MONTH,-1);
                cals.set(Calendar.DAY_OF_MONTH,1);
                startTime = cals.getTime();
                Long start = startTime.getTime();
                if (lastOrder<start){
                    //几个月未开单
                    supportEntity.setSupportType(i+1);
                }
                map.put("startTime",startTime);
                map.put("endTime",endTime);
                map.put("userCode",supportEntity.getUserCode());
                SupportAchievementsEntity com = supportAchievementsDbDao.selectSupportAchievements(map);

                if (null==com){
                    com = new SupportAchievementsEntity();
                }
                if (null==com.getDealNum()){
                    com.setDealNum(0);
                }
                if (null==com.getCustomerNum()){
                    com.setCustomerNum(0);
                }
                if (null==com.getHouseNum()){
                    com.setHouseNum(0);
                }
                if (null==com.getTakeLookNum()){
                    com.setTakeLookNum(0);
                }
                if (null==com.getAchievements()){
                    com.setAchievements(BigDecimal.ZERO);
                }
                com.setSupportCode(supportCode);
                com.setInMonth(sdf.format(startTime));
                supportAchievementsEntities.add(com);
                endTime=startTime ;
            }
//            DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
//            supportAchievementsDbDao.batchInsert(supportAchievementsEntities);
            sa.addAll(supportAchievementsEntities);
            supportEntity.setInTime(inTime);
//            supportEntity.setSupportAchievementsEntities(supportAchievementsEntities);
        }
        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        supportDbDao.batchInsert(supportEntities);
        supportAchievementsDbDao.batchInsert(sa);
        DynamicDataSource.clearDataSource();
    }
}

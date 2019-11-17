package com.kfwy.hkp.hos.house.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.hos.house.business.IHouseApplyManager;
import com.kfwy.hkp.hos.house.dao.IHouseApplyDbDao;
import com.kfwy.hkp.hos.house.entity.HouseApplyEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create By hjh on 2018/8/10
 */
@Service
public class HouseApplyManagerImpl extends AbstractManager<HouseApplyEntity> implements IHouseApplyManager {

    @Autowired
    private IHouseApplyDbDao houseApplyDbDao;

    @Override
    protected IMyBatisBaseDao<HouseApplyEntity, Long> getMyBatisDao() {
        return this.houseApplyDbDao;
    }

    @Override
    public int toDayByCount(Map<String, Object> param) {
        return houseApplyDbDao.toDayByCount(param);
    }

    @Override
    public int create(HouseApplyEntity houseApplyEntity) {
        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        if(!UserType.Employee.equals(user.getUserType())){
            throw new RemoveStackBusinessException ("非库房无忧官方经纪人不能申请查看平台资源");
        }
        OperateFillUtils.fill(houseApplyEntity);
        houseApplyEntity.setEmpCode(CurrentContext.getUserCode());

        hasApply(houseApplyEntity);

        return houseApplyDbDao.insert(houseApplyEntity);
    }

    private void hasApply(HouseApplyEntity houseApplyEntity){

        String preSql = "select count(*) from t_hkp_hos_apply " +
                "where emp_code=\'%s\' " +
                "and house_code=\'%s\'";

        String sql = String.format(preSql,CurrentContext.getUserCode(),houseApplyEntity.getHouseCode());

        int count = houseApplyDbDao.countByNativeSql(sql);

        if (count>0){
            throw new RemoveStackBusinessException("您已经申请过该房源，不必重新申请");
        }
        Map<String,Object> param=new HashMap<>();
        param.put("userCode",CurrentContext.getUserCode());
        param.put("createTimeStart",DateFormatUtil.dayBegin(new Date()));
        param.put("createTimeEnd",DateFormatUtil.dayEnd(new Date()));
        List<HouseApplyEntity> list= houseApplyDbDao.selectByMap(param);
        if (null!=list&&list.size()>10){
            throw new RemoveStackBusinessException("您今天申请次数已达到每天上限10次，今天无法申请！");
        }

    }

}

package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.ILastDayReportManager;
import com.kfwy.hkp.bi.count.dao.ILastDayReportDbDao;
import com.kfwy.hkp.bi.count.entity.LastDayReportEntity;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;


@Service
public class LastDayReportManagerImpl extends AbstractManager<LastDayReportEntity> implements ILastDayReportManager {

    @Autowired
    private ILastDayReportDbDao lastDayReportDbDao;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IUserManager userManager;

    @Override
    protected IMyBatisBaseDao<LastDayReportEntity, Long> getMyBatisDao() {
        return this.lastDayReportDbDao;
    }


    @Override
    public void getLastDayReport() throws ParseException {

        Date lastDay = new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(lastDay);
        ca.add(Calendar.DATE,-1);
        lastDay=ca.getTime();
        Date startTime = DateFormatUtil.dayBegin(lastDay);
        Date endTime = DateFormatUtil.dayEnd(lastDay);

        Map<String,Object> param = new HashMap<>();
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        List<LastDayReportEntity> list=lastDayReportDbDao.getLastDayReport(param);
        //填充数据
        List<String> deptCodes =deptManager.getDownAllDeptCode("DP201607040005");
        Map<String,Object>  map = new HashMap<>();
        map.put("isDeleted",false);
        map.put("ownerDeptCodes",deptCodes);
        List<UserEntity> userEntities = userDbDao.selectUserByDeptCodes(map);
        list =fillData(list,userEntities,lastDay);
        lastDayReportDbDao.batchInsert(list);
    }

    @Override
    public List<LastDayReportEntity> selectLastDayReport(Date date) {
        //只有部门经理可以查看该数据
        String userCode = CurrentContext.getUserCode();
        UserEntity userEntity = userManager.findUserByUserCode(userCode);
        if (StringUtils.isEmpty (userEntity.getEmpPostCode ())
                || !userEntity.getEmpPostCode().equals("PT201603310001")){
            //
            return null;
        }
        Map<String,Object> param = new HashMap<>();
        Date startTime = new Date();
        Date endTime = new Date();
        if(null==date){
            //默认为昨天
            Date lastDay = new Date();
            Calendar ca = Calendar.getInstance();
            ca.setTime(lastDay);
            ca.add(Calendar.DATE,-1);
            lastDay=ca.getTime();
            endTime = DateFormatUtil.dayEnd(lastDay);
            startTime = DateFormatUtil.dayBegin(lastDay);
        }else {
            endTime = DateFormatUtil.dayEnd(date);
            startTime = DateFormatUtil.dayBegin(date);
        }
        String deptCode = CurrentContext.getUserInfo().getOwnerDeptCode();
        param.put("deptCode",deptCode);
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        List<LastDayReportEntity> list=lastDayReportDbDao.selectLastDayReport(param);
        if (null!=list&&list.size()==0){
            return null;
        }
        return list;
    }

    private List<LastDayReportEntity> fillData(List<LastDayReportEntity> lastDayReportEntities,List<UserEntity> userEntities,Date lastDay){
        List<LastDayReportEntity> fill = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            Boolean isContains =false;
            for (LastDayReportEntity lastDayReportEntity : lastDayReportEntities) {
                if (lastDayReportEntity.getUserCode().equals(userEntity.getUserCode())){
                   isContains=true;
                }

            }
            if (!isContains){
                LastDayReportEntity ldr = new LastDayReportEntity();
                ldr.setUserCode(userEntity.getUserCode());
                ldr.setDeptCode(userEntity.getOwnerDeptCode());
                ldr.setCallCusPhone(0);
                ldr.setCallEmpPhone(0);
                ldr.setCallHouseOwnerPhone(0);
                ldr.setSeeCusDetail(0);
                ldr.setSeeHosDetail(0);
                ldr.setSeeHouseOwnerDetail(0);
                fill.add(ldr);
            }
        }
        lastDayReportEntities.addAll(fill);
        for (LastDayReportEntity lastDayReportEntity : lastDayReportEntities) {
            lastDayReportEntity.setInTime(lastDay);
        }
        return lastDayReportEntities;
    }
}

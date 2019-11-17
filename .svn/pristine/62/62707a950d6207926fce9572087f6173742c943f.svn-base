package com.kfwy.hkp.sys.remind.business.downNotice;

import com.gniuu.framework.common.context.CurrentContext;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.remind.business.AbstractDownNoticeAop;
import com.kfwy.hkp.sys.remind.entity.RemindDownEntity;
import com.kfwy.hkp.sys.remind.enums.DownType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
public class DownRemindNoticeAop extends AbstractDownNoticeAop {


    @Pointcut("target(com.kfwy.hkp.sys.remind.business.impl.RemindDownManagerImpl) " +
            " && execution(* create(..))")
    public void afterDownShelfPoint() {
    }

    @AfterReturning("afterDownShelfPoint()")
    public void afterDownShelfPoint(JoinPoint point){
        RemindDownEntity remindDownEntity = getArg(point);
        if(DownType.CUS.equals(remindDownEntity.getDownType())){
            noticeTaskExecutor.saveAndPush(create(remindDownEntity, NoticeOperateType.CusDownRemind));
        }else if(DownType.HOS.equals(remindDownEntity.getDownType())){
            noticeTaskExecutor.saveAndPush(create(remindDownEntity, NoticeOperateType.HouseDownRemind));
        }
    }


    @Override
    protected List<UserEntity> addTargetUsers(RemindDownEntity remindDownEntity) {
        List<UserEntity> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>(1);
        if(DownType.CUS.equals(remindDownEntity.getDownType())){
            map.put("cusCode",remindDownEntity.getOwnerCode());
            CustomerEntity customerEntity = customerDbDao.selectUniqueByMap(map);
            list.add(userManager.findOne("userCode",customerEntity.getEmpCode()));
        }else if(DownType.HOS.equals(remindDownEntity.getDownType())){

            map.put("houseCode",remindDownEntity.getOwnerCode());
            HouseEntity house = houseDbDao.selectUniqueByMap(map);
            list.add(userManager.findOne("userCode",house.getEmpCode()));
        }
        return distinct(list);
    }

    @Override
    protected void initNoticeTitleAndContent(NoticeEntity notice, RemindDownEntity remindDownEntity, String type) {

        StringBuffer title = new StringBuffer();
        StringBuffer content = new StringBuffer();

        UserEntity cur = (UserEntity)CurrentContext.getUserInfo();

        Map<String,Object> map = new HashMap<>(1);
        if(DownType.CUS.equals(remindDownEntity.getDownType())){
            title.append("客户下架提醒");
            content.append(String.format("下架提醒通知！<%s-%s>在跟进时发现此客户已经租好，" +
                            "请您及时核实处理，7日后将被系统自动下架，避免错误的推广哦。",
                    cur.getOwnerDeptName(),cur.getUserName()));
        }else if(DownType.HOS.equals(remindDownEntity.getDownType())){

            map.put("houseCode",remindDownEntity.getOwnerCode());
            HouseEntity house = houseDbDao.selectUniqueByMap(map);

            title.append("房源下架提醒");
            content.append(String.format("下架提醒通知！<%s-%s>在跟进时发现<%s>已被租出去，" +
                            "请您及时核实处理，7日后将被系统自动下架，避免错误的推广哦。",
                    cur.getOwnerDeptName(),cur.getUserName(),house.getHouseName()));
        }
        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }
}

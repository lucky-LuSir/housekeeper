package com.kfwy.hkp.hos.house.business.noticeAop;


import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.hos.house.business.AbstractHouseNoticeAop;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 房源修改推送消息
 */
@Aspect
@Component
public class HouseUpdateNoticeAop extends AbstractHouseNoticeAop {

    @Pointcut("target(com.kfwy.hkp.hos.house.business.impl.HouseManagerImpl) " +
            "&& execution(* update(..))")
    public void afterUpdatePoint(){}

    @AfterReturning("afterUpdatePoint()")
    public void afterUpdateAdvice(JoinPoint point){

        HouseEntity houseEntity = (HouseEntity) getArg(point);
        noticeTaskExecutor.saveAndPush(create(houseEntity,NoticeOperateType.Update));

    }

    @Override
    public List<UserEntity> getTargetUsers(HouseEntity house){
        List<UserEntity> targetUsers = new ArrayList<>();
        //添加本部门
        targetUsers.addAll(getUserListByDept());
        //添加合作人
        targetUsers.addAll(getCooperateUserList(house.getHouseCode()));

        return distinct(targetUsers);
    }

    @Override
    public void initNoticeTitleAndContent(NoticeEntity notice,HouseEntity house,UserEntity cur){

        StringBuilder title = new StringBuilder();
        StringBuilder content = new StringBuilder();
        title.append(String.format("%s%s了一个房源",
                cur.getUserName(), DictionaryStorage.get(NoticeOperateType.getKey(),NoticeOperateType.Update).getName()));

        content.append(String.format("<%s-%s>更新了房源信息：<%s>！",
                cur.getOwnerDeptName(),cur.getUserName(),house.getHouseName()
        ));

        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }
}

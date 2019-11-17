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

import java.util.List;

/**
 * 房源上架通知
 */
@Component
@Aspect
public class HouseUpNoticeAop extends AbstractHouseNoticeAop {


    @Pointcut("target(com.kfwy.hkp.hos.house.business.impl.HouseManagerImpl) " +
            "&& execution(* up(..))")
    public void houseUp(){}

    @AfterReturning("houseUp()")
    public void afterUpAdvice(JoinPoint point){
        HouseEntity houseEntity=getHouseCode(point);
        noticeTaskExecutor.saveAndPush(create(houseEntity,NoticeOperateType.Up));
    }

    @Override
    public List<UserEntity> getTargetUsers(HouseEntity house) {
        //推送给部门
        List<UserEntity> targetUsers = getUserListByDept();
        return distinct(targetUsers);
    }

    @Override
    public void initNoticeTitleAndContent(NoticeEntity notice, HouseEntity house, UserEntity cur) {
        StringBuilder title = new StringBuilder();
        StringBuilder content = new StringBuilder();
        title.append(String.format("%s%s了一个房源",
                cur.getUserName(), DictionaryStorage.get(NoticeOperateType.getKey(),NoticeOperateType.Up).getName()));

        content.append(String.format("新空出房源！<%s>！",
                house.getHouseName()
        ));

        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }
}

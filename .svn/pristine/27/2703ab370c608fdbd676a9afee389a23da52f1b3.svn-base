package com.kfwy.hkp.hos.house.business.noticeAop;

import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.hos.house.business.AbstractHouseNoticeAop;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 房源新增 推送消息
 */
@Aspect
@Component
public class HouseCreateNoticeAop extends AbstractHouseNoticeAop {

    @Pointcut("target(com.kfwy.hkp.hos.house.dao.impl.HouseDbDaoImpl) " +
            "&& execution(* batchInsert(..))")
    public void afterBatchInsertPoint(){}

    @Pointcut("target(com.kfwy.hkp.hos.house.dao.impl.HouseDbDaoImpl) " +
            "&& execution(* insert(..))")
    public void afterInsertPoint(){}

    @AfterReturning("afterBatchInsertPoint()")
    public void afterBatchInsertAdvice(JoinPoint point){

        List<HouseEntity> list = getArgs(point);
        if (!CollectionUtils.isEmpty(list)){
            for (HouseEntity house : list){
                noticeTaskExecutor.saveAndPush(create(house,NoticeOperateType.Add));
            }
        }
    }
    @AfterReturning("afterInsertPoint()")
    public void afterInsertAdvice(JoinPoint point){

        HouseEntity houseEntity = (HouseEntity) getArg(point);
        noticeTaskExecutor.saveAndPush(create(houseEntity,NoticeOperateType.Add));
    }


    @Override
    public List<UserEntity> getTargetUsers(HouseEntity house){
        HouseLocationEntity location = getHouseLocation(house);
        Map<String,Object> param = new HashMap<>();
        param.put("isDeleted",Boolean.FALSE);
        param.put("region",location.getRegion());

        List<UserEntity> targetUsers = userManager.findByMap(param);

        return distinct(targetUsers);
    }

    @Override
    public void initNoticeTitleAndContent(NoticeEntity notice,HouseEntity house,UserEntity cur){

        StringBuilder title = new StringBuilder();
        StringBuilder content = new StringBuilder();
        title.append(String.format("%s%s了一个房源",
                cur.getUserName(), DictionaryStorage.get(NoticeOperateType.getKey(),NoticeOperateType.Add).getName()));

        content.append(String.format("新房源，求合作！<%s㎡>可<%s>！来自<%s-%s>",
                house.getArea(),house.getHousePurposeName(),cur.getUserName(),cur.getOwnerDeptName()));

        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }
}

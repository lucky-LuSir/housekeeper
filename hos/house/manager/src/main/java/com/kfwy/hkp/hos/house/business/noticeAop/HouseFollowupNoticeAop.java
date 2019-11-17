package com.kfwy.hkp.hos.house.business.noticeAop;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.hos.house.business.AbstractHouseNoticeAop;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseFollowupEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 房源跟进 消息通知推送
 */
@Aspect
@Component
public class HouseFollowupNoticeAop extends AbstractHouseNoticeAop {


    @Pointcut("target(com.kfwy.hkp.hos.house.business.impl.HouseFollowupManagerImpl) " +
            "&& execution(* create(..))")
    public void followupPoint(){}

    @AfterReturning("followupPoint()")
    public void afterFollowupPoint(JoinPoint point){

        Object[] args = point.getArgs();
        Object obj = null;
        if (ArrayUtils.isNotEmpty(args)){
            obj = args[0];
        }
        HouseFollowupEntity houseFollowupEntity = (HouseFollowupEntity) obj;
        HouseEntity houseEntity = houseManager.findOne("houseCode",houseFollowupEntity.getHouseCode());
        noticeTaskExecutor.saveAndPush(create(houseEntity,NoticeOperateType.FollowUp));
    }


    @Override
    public List<UserEntity> getTargetUsers(HouseEntity house) {
        List<UserEntity> targetUsers = new ArrayList<>();
        //推送给部门
        /*Map<String,Object> param = new HashMap<>();
        param.put("isDeleted",Boolean.FALSE);
        param.put("ownerDeptCode",CurrentContext.getUserInfo().getOwnerDeptCode());
        targetUsers.addAll(userManager.findByMap(param));*/

        //房源所属者
        UserEntity houseOwnerEntity = userManager.findOne("userCode",house.getEmpCode());
        targetUsers.add(houseOwnerEntity);

        /*//添加合作人
        targetUsers.addAll(getCooperateUserList(house.getHouseCode()));*/
        return distinct(targetUsers);
    }

    @Override
    public void initNoticeTitleAndContent(NoticeEntity notice, HouseEntity house, UserEntity cur) {
        StringBuilder title = new StringBuilder();
        StringBuilder content = new StringBuilder();
        title.append(String.format("%s%s了一个房源",
                cur.getUserName(), DictionaryStorage.get(NoticeOperateType.getKey(),NoticeOperateType.FollowUp).getName()));

        content.append(String.format("房源动态！<%s-%s>跟进了<%s>！",
                cur.getOwnerDeptName(),cur.getUserName(),house.getHouseName()
        ));

        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }
}

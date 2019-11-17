package com.kfwy.hkp.cooperate.business.noticeAop;

import com.kfwy.hkp.cooperate.business.AbstractCooperateNoticeAop;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * 合作新增  消息通知
 */
@Aspect
@Component
public class CooperateCreateNoticeAop extends AbstractCooperateNoticeAop {


    @Pointcut("target(com.kfwy.hkp.cooperate.dao.impl.CooperateDbDaoImpl) " +
            "&& execution(* batchInsert(..))")
    public void cooperateBatchInsert(){}
    
    @AfterReturning("cooperateBatchInsert()")
    public void afterBatchInsertReturn(JoinPoint point){
        List<CooperateEntity> list = (List<CooperateEntity>) getArg(point);
        list.forEach((coo) ->{
            noticeTaskExecutor.saveAndPush(create(coo, NoticeOperateType.Add));
        });
    }

    @Override
    public List<UserEntity> getTargetUsers(CooperateEntity cooperate) {
        List<UserEntity> list = new ArrayList<>();
        if(StringUtils.isNotEmpty(cooperate.getHouseCode())){
            HouseEntity houseEntity = houseManager.findOne("houseCode",cooperate.getHouseCode());
            UserEntity userEntity = userManager.findOne("userCode",houseEntity.getEmpCode());
            cooperate.setHouseEntity(houseEntity);
            list.add(userEntity);
        }else if (StringUtils.isNotEmpty(cooperate.getCusCode())){
            CustomerEntity customerEntity = customerManager.findOne("cusCode",cooperate.getCusCode());
            UserEntity userEntity = userManager.findOne("userCode",customerEntity.getEmpCode());
            cooperate.setCustomerEntity(customerEntity);
            list.add(userEntity);
        }
        return distinct(list);
    }

    @Override
    public void initNoticeTitleAndContent(NoticeEntity notice, CooperateEntity cooperate, UserEntity cur) {
        StringBuffer title = new StringBuffer();
        StringBuffer content = new StringBuffer();

        if(StringUtils.isNotEmpty(cooperate.getHouseCode())){
            title.append("房源合作申请");
            content.append(String.format("<%s-%s>申请与您合作出租<%s>",
                        cur.getOwnerDeptName(),cur.getUserName(),cooperate.getHouseEntity().getHouseName())
            );
        }else if (StringUtils.isNotEmpty(cooperate.getCusCode())){
            title.append("客户合作申请");
            content.append(String.format("<%s-%s>申请与您合作为客户" +
                            "<%s+%s>找<%s㎡>用于<%s>！",
                    cur.getOwnerDeptName(),cur.getUserName(),
                    cooperate.getCustomerEntity().getCusName(),cooperate.getCustomerEntity().getCusSexName(),
                    cooperate.getCustomerEntity().getNeedAcreage(),cooperate.getCustomerEntity().getHouseTypeName()
            ));
        }
        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }
}

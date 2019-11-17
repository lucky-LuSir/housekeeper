package com.kfwy.hkp.cooperate.business.noticeAop;

import com.kfwy.hkp.cooperate.business.AbstractCooperateNoticeAop;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.cooperate.enums.CooStatus;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 同意合作 拒绝合作 结束合作  消息通知
 */
@Component
@Aspect
public class CooperateUpdateNoticeAop extends AbstractCooperateNoticeAop {


    @Pointcut("target(com.kfwy.hkp.cooperate.business.impl.CooperateManagerImpl) " +
            "&& execution(* update(..)) )")
    public void cooperateUpdate(){}

    @AfterReturning("cooperateUpdate()")
    public void afterCooperateUpdateReturn(JoinPoint point){

        CooperateEntity cooperateEntity = (CooperateEntity) getArg(point);
        if(CooStatus.REFUSE.equals(cooperateEntity.getCooStatus())){
            //拒绝合作
            noticeTaskExecutor.saveAndPush(create(cooperateEntity, NoticeOperateType.Refuse));
        }else if(CooStatus.ACCEPT.equals(cooperateEntity.getCooStatus())){
            //同意合作
            noticeTaskExecutor.saveAndPush(create(cooperateEntity, NoticeOperateType.Agree));
        }else if(CooStatus.END.equals(cooperateEntity.getCooStatus())){
            //结束合作
            noticeTaskExecutor.saveAndPush(create(cooperateEntity, NoticeOperateType.End));
        }
    }

    @Override
    public List<UserEntity> getTargetUsers(CooperateEntity cooperate) {
        //合作申请人
        List<UserEntity> list= getCooperateUser(cooperate);
        return distinct(list);
    }

    @Override
    public void initNoticeTitleAndContent(NoticeEntity notice, CooperateEntity cooperate, UserEntity cur) {
        StringBuffer title = new StringBuffer();
        StringBuffer content = new StringBuffer();

        //被申请人  房源或客户所有者
        UserEntity receive = userManager.findOne("userCode",cooperate.getReceiveCode());

        if(CooStatus.REFUSE.equals(cooperate.getCooStatus())){
            //拒绝合作 通知内容
            title.append("拒绝合作");
            content.append(String.format("很抱歉！<%s-%s>婉拒了您提出的合作请求！",
                    receive.getOwnerDeptName(),receive.getUserName()));
        }else if(CooStatus.ACCEPT.equals(cooperate.getCooStatus())){
            //同意合作 通知内容
            title.append("同意合作");
            content.append(String.format("申请通过！<%s-%s>接受了你的合作请求！你可以查看关键信息！",
                    receive.getOwnerDeptName(),receive.getUserName()));
        }else if(CooStatus.END.equals(cooperate.getCooStatus())){
            //结束合作 通知内容
            title.append("结束合作");

            if(StringUtils.isNotEmpty(cooperate.getHouseCode())){
                content.append(String.format("<%s-%s>结束了你的<%s>的房源合作",
                        cur.getOwnerDeptName(),cur.getUserName(),cooperate.getHouseName()));
            }else if(StringUtils.isNotEmpty(cooperate.getCusCode())){
                content.append(String.format("<%s-%s>结束了你的<%s>的客户合作",
                        cur.getOwnerDeptName(),cur.getUserName(),
                        getCusDemandAppend(customerManager.findOne("cusCode",cooperate.getCusCode()))
                ));
            }

        }

        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());

    }

    //获取客户需求拼接
    private String getCusDemandAppend(CustomerEntity customer){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder demand = new StringBuilder();

        demand.append(customer.getNeedAcreage());
        demand.append("㎡左右的");
        demand.append(customer.getHouseTypeName());
        demand.append(sdf.format(customer.getEnterTime()));
        demand.append("前入住");
        return demand.toString();
    }
}

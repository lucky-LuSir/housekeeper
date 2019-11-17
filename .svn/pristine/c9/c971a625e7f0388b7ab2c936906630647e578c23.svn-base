package com.kfwy.hkp.crm.customer.business.noticeAop;


import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.crm.customer.business.AbstractCustomerNoticeAop;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 客户跟进消息通知
 */
@Component
@Aspect
public class CustomerFollowupNoticeAop extends AbstractCustomerNoticeAop {

    private String followupTypeName;

    @Pointcut("target(com.kfwy.hkp.crm.customer.business.impl.CustomerFollowupManagerImpl) " +
            " && execution(* create(..))")
    public void afterFollowupPoint() {
    }


    @AfterReturning("afterFollowupPoint()")
    public void afterFollowupAdvice(JoinPoint point) {
        CustomerEntity customerEntity = getCus(point);
        noticeTaskExecutor.saveAndPush(create(customerEntity, NoticeOperateType.FollowUp));
    }

    private CustomerEntity getCus(JoinPoint point){
        Object[] args = point.getArgs();
        Object obj = null;
        if (ArrayUtils.isNotEmpty(args)) {
            obj = args[0];
        }
        CustomerFollowupEntity customerFollowupEntity = (CustomerFollowupEntity) obj;
        this.followupTypeName = customerFollowupEntity.getFollowupTypeName();
        return customerDbDao.detail(customerFollowupEntity.getCusCode());
    }

    @Override
    protected List<UserEntity> addTargetUsers(CustomerEntity customerEntity) {
        List<UserEntity> list = new ArrayList<>();
        //添加客户所属者
        list.add(getUserByCusOwner(customerEntity));
        /*//添加合作人
        list.addAll(getUserListByCooperation(customerEntity));
        //添加部门
        list.addAll(getUserListByDept(customerEntity));
        //获取房客需求推送接收部门所有人员，房客需求推送接收人
        list.addAll(getUserListByPushDeptAndUser(customerEntity));*/

        return  distinct(list);
    }

    @Override
    protected void initNoticeTitleAndContent(NoticeEntity notice, CustomerEntity customer, String type) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        StringBuffer title = new StringBuffer();
        StringBuffer content = new StringBuffer();

        title.append(String.format("%s%s了一个的客户", cur.getUserName(),
                DictionaryStorage.get(NoticeOperateType.getKey(), type).getName()));
        content.append(String.format("<%s-%s>%s<%s>的客户",
                cur.getOwnerDeptName(),cur.getUserName(),
                this.followupTypeName,getCusDemandAppend(customer)));

        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }

    @Override
    protected void initNoticeExternalApplication(NoticeEntity notice, CustomerEntity customer, String type, List<UserEntity> userEntities,UserEntity cur) {

    }


    //获取客户需求拼接
    private String getCusDemandAppend(CustomerEntity customer){
        StringBuilder demand = new StringBuilder();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        demand.append(customer.getNeedAcreage());
        demand.append("㎡左右的");
        demand.append(customer.getHouseTypeName());
        demand.append(sdf.format(customer.getEnterTime()));
        demand.append("前入住");
        return demand.toString();
    }
}

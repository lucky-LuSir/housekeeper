package com.kfwy.hkp.crm.customer.business.noticeAop;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.crm.customer.business.AbstractCustomerNoticeAop;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 客户修改消息通知
 */
@Aspect
@Component
public class CustomerUpdateNoticeAop extends AbstractCustomerNoticeAop {


    @Pointcut("target(com.kfwy.hkp.crm.customer.business.impl.CustomerManagerImpl) " +
            "&& execution(* update(..))")
    public void afterUpdatePoint() {
    }

    @AfterReturning("afterUpdatePoint()")
    public void afterUpdateAdvice(JoinPoint point){
        CustomerEntity customerEntity =  getArg(point);
        customerEntity = customerDbDao.detail(customerEntity.getCusCode());
        noticeTaskExecutor.saveAndPush(create(customerEntity, NoticeOperateType.Update));
    }

    @Override
    protected List<UserEntity> addTargetUsers(CustomerEntity customerEntity) {

        List<UserEntity> list = new ArrayList<>();
        //添加客户所属者
        list.add(getUserByCusOwner(customerEntity));
        //添加部门
        list.addAll(getUserListByDept(customerEntity));
        //添加合作人
        list.addAll(getUserListByCooperation(customerEntity));
        //获取房客需求推送接收部门所有人员，房客需求推送接收人
        list.addAll(getUserListByPushDeptAndUser(customerEntity));

        return distinct(list);
    }

    @Override
    protected void initNoticeTitleAndContent(NoticeEntity notice, CustomerEntity customer, String type) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        StringBuffer title = new StringBuffer();
        StringBuffer content = new StringBuffer();

        title.append(String.format("%s%s了一个的客户", cur.getUserName(),
                DictionaryStorage.get(NoticeOperateType.getKey(), type).getName()));
        content.append(String.format("<%s-%s>修改了客户<%s+%s>的找房需求！",
                cur.getOwnerDeptName(),cur.getUserName(),
                customer.getCusName(),customer.getCusSexName()));

        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }

    @Override
    protected void initNoticeExternalApplication(NoticeEntity notice, CustomerEntity customer, String type, List<UserEntity> userEntities,UserEntity cur) {

    }
}

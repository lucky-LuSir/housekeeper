package com.kfwy.hkp.crm.customer.business.noticeAop;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.crm.customer.business.AbstractCustomerNoticeAop;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * 客户上架 消息通知
 * @author baiye
 */
@Component
@Aspect
public class CustomerUpNoticeAop extends AbstractCustomerNoticeAop {

    @Pointcut("target(com.kfwy.hkp.crm.customer.business.impl.CustomerManagerImpl) " +
            "&& execution(* upShelf(..))")
    public void afterUpPoint() {
    }

    @AfterReturning("afterUpPoint()")
    public void afterUpAdvice(JoinPoint point){
        CustomerEntity customerEntity = getArg(point);
        customerEntity = customerDbDao.selectUniqueByProp("cusCode",customerEntity.getCusCode());
        if (!customerEntity.getCusType().equals(CustomerType.FOCUS_CUS)){
            noticeTaskExecutor.saveAndPush(create(customerEntity, NoticeOperateType.Up));
        }
    }

    @Override
    protected List<UserEntity> addTargetUsers(CustomerEntity customerEntity) {
        //推送给 部门人
        List<UserEntity> list = getUserListByDept(customerEntity);
        return distinct(list);
    }

    @Override
    protected void initNoticeTitleAndContent(NoticeEntity notice, CustomerEntity customer, String type) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        StringBuffer title = new StringBuffer();
        StringBuffer content = new StringBuffer();

        content.append(String.format("求合作！<%s+%s>找<%s㎡>用于<%s>！来自<%s-%s>",
                customer.getCusName(), customer.getCusSexName(),
                customer.getNeedAcreage(), customer.getHouseTypeName(),
                cur.getOwnerDeptName(),cur.getUserName()));
        title.append(String.format("%s%s了一个的客户", cur.getUserName(),
                DictionaryStorage.get(NoticeOperateType.getKey(), type).getName()));

        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }

    @Override
    protected void initNoticeExternalApplication(NoticeEntity notice, CustomerEntity customer, String type, List<UserEntity> userEntities,UserEntity cur) throws IOException {

    }
}

package com.kfwy.hkp.job.crm.customer.systemNotice.customer;

import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.job.crm.customer.systemNotice.AbstractSystemNotice;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户自动释放通知
 */
@Component
public class CustomerAutoReleaseNoticeAop extends AbstractSystemNotice<CustomerEntity> {

    public void autoReleaseNotice(){
        List<CustomerEntity> cusList = customerDbDao.remindRelease();
        cusList.forEach((cus) -> {
            noticeTaskExecutor.saveAndPush(create(cus, NoticeOperateType.AutoRelease));
        });
    }

    @Override
    public List<UserEntity> getTargetUsers(CustomerEntity customerEntity) {
        List<UserEntity> list = new ArrayList<>(1);
        list.add(userManager.findOne("userCode",customerEntity.getEmpCode()));
        return list;
    }

    @Override
    public void initNoticeTitleAndContent(NoticeEntity notice, CustomerEntity customer) {
        StringBuilder title = new StringBuilder();
        StringBuilder content = new StringBuilder();
        title.append(String.format("拉私即将释放"));

        content.append(String.format("私有客户<%s+%s>找<%s>用于<%s>，" +
                        "因为长时间未跟进明天将被释放到平台！",
                customer.getCusName(),customer.getCusSexName(),customer.getNeedAcreage(),
                customer.getHouseTypeName()
        ));
        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }
}

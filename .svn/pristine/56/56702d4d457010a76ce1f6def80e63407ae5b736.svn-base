package com.kfwy.hkp.job.crm.customer.systemNotice.customer;

import com.kfwy.hkp.crm.customer.entity.CustomerFavoriteEntity;
import com.kfwy.hkp.job.crm.customer.systemNotice.AbstractSystemNotice;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户收藏 消息通知 每天定时处理
 */
@Component
public class CustomerCollectNoticeAop extends AbstractSystemNotice<CustomerFavoriteEntity> {


    public void afterCollectNotice(){
        List<CustomerFavoriteEntity> customerFavoritList =  customerFavoriteDbDao.getCountGroupByUserCode();
        customerFavoritList.forEach((cf) -> noticeTaskExecutor.saveAndPush(create(cf, NoticeOperateType.COLLECT)));
    }


    @Override
    public List<UserEntity> getTargetUsers(CustomerFavoriteEntity customerFavoriteEntity) {
        List<UserEntity> list = new ArrayList<>();
        list.add(userManager.findOne("userCode",customerFavoriteEntity.getEmpCode()));
        return list;
    }

    @Override
    public void initNoticeTitleAndContent(NoticeEntity notice, CustomerFavoriteEntity customerFavoriteEntity) {
        StringBuilder title = new StringBuilder();
        StringBuilder content = new StringBuilder();
        title.append(String.format("客户收藏"));

        content.append(String.format("昨天有%s位经纪人收藏了您的客户",
                customerFavoriteEntity.getCount()
        ));
        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }
}

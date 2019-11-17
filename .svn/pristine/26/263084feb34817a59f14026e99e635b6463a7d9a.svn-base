package com.kfwy.hkp.job.crm.customer.systemNotice.house;

import com.kfwy.hkp.hos.house.entity.HouseFavoriteEntity;
import com.kfwy.hkp.job.crm.customer.systemNotice.AbstractSystemNotice;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 房源收藏 定时消息通知
 */
@Component
public class HouseCollectNoticeAop extends AbstractSystemNotice<HouseFavoriteEntity> {

    public void afterCollectNotice(){
        List<HouseFavoriteEntity> favoriteEntityList = houseFavoriteManager.getCountGroupByUserCode();
        favoriteEntityList.forEach((f) -> {
            noticeTaskExecutor.saveAndPush(create(f,NoticeOperateType.COLLECT));
        });
    }

    @Override
    public List<UserEntity> getTargetUsers(HouseFavoriteEntity houseFavoriteEntity) {
        List<UserEntity> list = new ArrayList<>(1);
        //houseFavoriteEntity.getEmpCode() 这里的empCode 是房源的所属者 这里用收藏人字段接收一下
        list.add(userManager.findOne("userCode",houseFavoriteEntity.getEmpCode()));
        return list;
    }

    @Override
    public void initNoticeTitleAndContent(NoticeEntity notice,HouseFavoriteEntity houseFavoriteEntity) {
        StringBuilder title = new StringBuilder();
        StringBuilder content = new StringBuilder();
        title.append(String.format("房源收藏"));

        content.append(String.format("昨天有%s位经纪人收藏了您的房源",
                houseFavoriteEntity.getCount()
        ));
        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }
}

package com.kfwy.hkp.sys.announcement.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.sys.announcement.entity.AnnouncementEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;


import java.util.List;

/**
 * Created by davidcun on 2018/5/25.
 */
public interface IAnnouncementManager extends IManager<AnnouncementEntity> {

    public void createAnnouncement(List<String> deptCodes,String announcementType);

    public AnnouncementEntity query();
}

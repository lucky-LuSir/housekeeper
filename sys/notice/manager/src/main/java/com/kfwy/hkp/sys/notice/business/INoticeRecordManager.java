package com.kfwy.hkp.sys.notice.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;

import java.util.List;
import java.util.Map;


/**
 * Created by jupe on 2018/5/2.
 */
public interface INoticeRecordManager extends IManager<NoticeRecordEntity> {

    Map<String, Object> count(Map param);

    int batchCreate(List<NoticeRecordEntity> records);

    int updateReadAllSelf(NoticeRecordEntity noticeRecordEntity);
}

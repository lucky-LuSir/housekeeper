package com.kfwy.hkp.sys.notice.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;

import java.util.List;

/**
 * Created by jupe on 2018/5/2.
 */
public interface INoticeRecordDbDao extends IMyBatisBaseDao<NoticeRecordEntity, Long> {

    int updateReadAllSelf(List<NoticeRecordEntity> noticeRecordList);

    public List<NoticeRecordEntity> selectReadAllSelf(NoticeRecordEntity noticeRecordEntity);

    int deleteByRecordCode(List<String> recordCodes);
}

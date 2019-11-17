package com.kfwy.hkp.sys.notice.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.notice.dao.INoticeRecordDbDao;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jupe on 2018/5/3.
 */
@Repository
public class NoticeRecordDbDaoImpl extends AbstractMyBatisDao<NoticeRecordEntity, Long> implements INoticeRecordDbDao {

    @Override
    public int updateReadAllSelf(List<NoticeRecordEntity> noticeRecordList) {
        int i = 0;
        i = this.getSqlSession().update(this.mapperNamespace + "updateReadAllSelf", noticeRecordList);
        return i;
    }

    @Override
    public List<NoticeRecordEntity> selectReadAllSelf(NoticeRecordEntity noticeRecordEntity) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectReadAllSelf", noticeRecordEntity);
    }

    @Override
    public int deleteByRecordCode (List<String> recordCodes) {
        return this.getSqlSession ().delete (this.mapperNamespace+"deleteByRecordCode", recordCodes);
    }
}

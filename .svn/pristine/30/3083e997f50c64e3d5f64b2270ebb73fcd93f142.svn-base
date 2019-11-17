package com.kfwy.hkp.sys.notice.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.sys.notice.dao.INoticeRecordDbDao;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.sys.notice.business.INoticeRecordManager;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jupe on 2018/5/2.
 */
@Service
public class NoticeRecordManagerImpl extends AbstractManager<NoticeRecordEntity> implements INoticeRecordManager {

    @Autowired
    private INoticeRecordDbDao noticeRecordDbDao;

    @Override
    protected IMyBatisBaseDao<NoticeRecordEntity, Long> getMyBatisDao() {
        return this.noticeRecordDbDao;
    }

    @Override
    public int batchCreate(List<NoticeRecordEntity> records) {

        Calendar calendar = Calendar.getInstance();
        for (NoticeRecordEntity record : records){

            if (StringUtils.isEmpty(record.getRecordCode())){
                record.setRecordCode(CodeGenUtils.generate());
            }
            if (record.getIsDeleted()==null){
                record.setIsDeleted(Boolean.FALSE);
            }
            if (ObjectUtils.isEmpty(record.getCreateTime())){
                record.setCreateTime(calendar.getTime());
                record.setLastUpdateTime(calendar.getTime());
            }

            if (ObjectUtils.isEmpty(record.getHasRead())){
                record.setHasRead(Boolean.FALSE);
            }
        }
        return noticeRecordDbDao.batchInsert(records);
    }

    @Override
    public Map<String, Object> count(Map param ) {

        Map map = new HashMap<String, Object>();

        map.put("all", noticeRecordDbDao.countByMap(param));

        param.put("noticeType", NoticeType.HOUSE);
        map.put("house", noticeRecordDbDao.countByMap(param));

        param.put("noticeType", NoticeType.CUSTOMER);
        map.put("customer", noticeRecordDbDao.countByMap(param));

        param.put("noticeType", NoticeType.Push);
        map.put("push", noticeRecordDbDao.countByMap(param));

        param.put("noticeType", NoticeType.Cooperation);
        map.put("cooperation", noticeRecordDbDao.countByMap(param));

        param.put("noticeType", NoticeType.SYSTEM);
        map.put("system", noticeRecordDbDao.countByMap(param));

        param.put("noticeType", NoticeType.ORDER);
        map.put("order", noticeRecordDbDao.countByMap(param));

        param.put("noticeType", NoticeType.DOWN);
        map.put("down", noticeRecordDbDao.countByMap(param));

        param.put("noticeType",NoticeType.FOCUS_FOR_THE_GUEST);
        map.put("focusCus",noticeRecordDbDao.countByMap(param));

        param.put("noticeType",NoticeType.CUS_PUSH_FOR_ME);
        map.put("cusPushForMe",noticeRecordDbDao.countByMap(param));
        return map;
    }

    @Override
    public int update(NoticeRecordEntity noticeRecordEntity){
        return  noticeRecordDbDao.update(noticeRecordEntity);
    }

    @Override
    public int updateReadAllSelf(NoticeRecordEntity noticeRecordEntity){

        NoticeRecordEntity aNew = new NoticeRecordEntity();
        aNew.setEmpCode(noticeRecordEntity.getEmpCode());
        List<NoticeRecordEntity> list = noticeRecordDbDao.selectReadAllSelf(aNew);
        noticeRecordDbDao.updateReadAllSelf(list);
        return  list.size();
    }
}

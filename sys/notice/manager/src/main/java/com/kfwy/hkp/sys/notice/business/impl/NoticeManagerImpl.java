package com.kfwy.hkp.sys.notice.business.impl;


import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.sys.notice.business.INoticeManager;
import com.kfwy.hkp.sys.notice.business.INoticeRecordManager;
import com.kfwy.hkp.sys.notice.dao.INoticeDbDao;
import com.kfwy.hkp.sys.notice.dao.INoticeRecordDbDao;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import com.kfwy.hkp.sys.notice.push.PushManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NoticeManagerImpl extends AbstractManager<NoticeEntity> implements INoticeManager {

    public static Logger logger = LoggerFactory.getLogger(NoticeManagerImpl.class);
    @Autowired
    private INoticeDbDao noticeDbDao;
    @Autowired
    private INoticeRecordManager noticeRecordManager;
    @Autowired
    private INoticeRecordDbDao recordDbDao;

    @Override
    protected IMyBatisBaseDao<NoticeEntity, Long> getMyBatisDao() {
        return this.noticeDbDao;
    }

    @Override
    protected void beforeCreate(NoticeEntity noticeEntity) {

        if (StringUtils.isEmpty(noticeEntity.getNoticeCode())) {
            noticeEntity.setNoticeCode(CodeGenUtils.generate());
        }

        if (ObjectUtils.isEmpty(noticeEntity.getIsDeleted())) {
            noticeEntity.setIsDeleted(Boolean.FALSE);
        }
    }

    @Override
    protected void afterCreate(NoticeEntity notice) {

        if (!CollectionUtils.isEmpty(notice.getNoticeRecordEntityList())) {
            for (NoticeRecordEntity record : notice.getNoticeRecordEntityList()) {

                if (StringUtils.isEmpty(record.getNoticeCode())) {
                    record.setNoticeCode(notice.getNoticeCode());
                }
            }
            if (notice.getNoticeType().equals(NoticeType.FOCUS_FOR_THE_GUEST)) {
                int i = 0;
                for (NoticeRecordEntity record : notice.getNoticeRecordEntityList()){
                    int count = i;
                    //TODO
                    if (StringUtils.isEmpty(record.getRecordCode())){
                        record.setRecordCode(CodeGenUtils.generate());
                    }
                    if (record.getIsDeleted()==null){
                        record.setIsDeleted(Boolean.FALSE);
                    }
                    if (ObjectUtils.isEmpty(record.getCreateTime())){
                        record.setCreateTime(new Date());
                        record.setLastUpdateTime(new Date());
                    }
                    if (ObjectUtils.isEmpty(record.getHasRead())){
                        record.setHasRead(Boolean.FALSE);
                    }
                    if (logger.isDebugEnabled()){
                        logger.debug("创建子表通知 第："+(count+1) +"  条,时间:"+ DateUtil.formatDateTime(new Date()));
                    }
                    noticeRecordManager.create(record);
                    i++;
                }

            } else {
                noticeRecordManager.batchCreate(notice.getNoticeRecordEntityList());
            }
        }
    }


    @Override
    public void saveAndPush(NoticeEntity noticeEntity) {
        try {

            noticeEntity.setIsDeleted(Boolean.FALSE);
            noticeEntity.setCreateTime(DateFormatUtil.getCurrentDateTime());
            noticeEntity.setLastUpdateTime(DateFormatUtil.getCurrentDateTime());
            noticeEntity.setNoticeCode(CodeGenUtils.generate("NM"));

            List<NoticeRecordEntity> noticeRecordEntityList = noticeEntity.getNoticeRecordEntityList();

            if (noticeRecordEntityList.size() == 1) {

                NoticeRecordEntity noticeRecordEntity = noticeRecordEntityList.get(0);
                noticeRecordEntity.setRecordCode(CodeGenUtils.generate("NM"));
                noticeRecordEntity.setNoticeCode(noticeEntity.getNoticeCode());
                noticeRecordEntity.setHasRead(false);
                noticeRecordEntity.setCreateTime(DateFormatUtil.getCurrentDateTime());
                noticeRecordEntity.setLastUpdateTime(DateFormatUtil.getCurrentDateTime());
                noticeRecordEntity.setIsDeleted(false);

                create(noticeEntity);

                //单播
                if (StringUtils.isNotEmpty(noticeRecordEntity.getDeviceToken())) {
                    PushManager push = new PushManager(noticeEntity.getNoticeTitle(), noticeEntity.getNoticeContent());
                    push.setExtraField("code", noticeRecordEntity.getRecordCode());
                    push.push(PushManager.PushType.unicast, noticeRecordEntity.getDeviceToken());
                }
            }

            if (noticeRecordEntityList.size() > 1) {
                for (NoticeRecordEntity noticeRecordEntity : noticeRecordEntityList) {

                    noticeRecordEntity.setRecordCode(CodeGenUtils.generate("NM"));
                    noticeRecordEntity.setNoticeCode(noticeEntity.getNoticeCode());
                    noticeRecordEntity.setHasRead(false);
                    noticeRecordEntity.setCreateTime(DateFormatUtil.getCurrentDateTime());
                    noticeRecordEntity.setLastUpdateTime(DateFormatUtil.getCurrentDateTime());
                    noticeRecordEntity.setIsDeleted(false);

                    if (StringUtils.isNotEmpty(noticeRecordEntity.getDeviceToken())) {

                        PushManager push = new PushManager(noticeEntity.getNoticeTitle(), noticeEntity.getNoticeContent());
                        push.setExtraField("code", noticeRecordEntity.getRecordCode());
                        push.push(PushManager.PushType.unicast, noticeRecordEntity.getDeviceToken());
                    }
                }

                create(noticeEntity);
            }


        } catch (Exception e) {
            if (logger.isErrorEnabled()) {
                logger.error("保存通知消息信息失败", e);
            }
        }
    }


    @Override
    public List<NoticeEntity> upDownNotice() {
        String preSql = "SELECT * FROM t_hkp_sys_notice nt left join t_hkp_sys_user_user u\n" +
                "on nt.create_code=u.user_code\n" +
                "WHERE nt.notice_type=\'%s\' and u.cpy_code=\'%s\' ORDER BY nt.create_time desc LIMIT 10";
        UserEntity user=(UserEntity) CurrentContext.getUserInfo();
        String sql = String.format(preSql, NoticeType.ORDER,user.getCpyCode());
        List<NoticeEntity> list = noticeDbDao.selectByNativeSql(sql);
        return list;
    }

    @Override
    public int deleteByBussinessCode (String bussinessCode) {
        try {
            Map map  = new HashMap ();
            map.put ("bussinessCode",bussinessCode);
            List<NoticeEntity> result = noticeDbDao.selectByMap (map);
            if (result!=null){
                for (NoticeEntity noticeEntity : result) {
                    List<String> recordCodes = noticeEntity.getNoticeRecordEntityList ()
                            .stream().map(p -> p.getRecordCode ()).collect(Collectors.toList());
                    if (recordCodes!=null && recordCodes.size ()>0){
                        recordDbDao.deleteByRecordCode (recordCodes);
                    }
                }
            }
            noticeDbDao.deleteByBussinessCode (bussinessCode);
        } catch (Exception e) {
            throw new RemoveStackBusinessException ("删除通知信息失败");
        }

        return 1;
    }
}

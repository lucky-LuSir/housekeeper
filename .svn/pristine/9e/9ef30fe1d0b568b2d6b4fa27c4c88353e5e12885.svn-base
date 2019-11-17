package com.kfwy.hkp.job.crm.customer.systemNotice;

import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.hkp.cooperate.dao.ICooperateDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerFavoriteDbDao;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerManager;
import com.kfwy.hkp.hos.house.business.IHouseFavoriteManager;
import com.kfwy.hkp.hos.house.business.IHouseLocationManager;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.sys.notice.business.INoticeManager;
import com.kfwy.hkp.sys.notice.business.NoticeTaskExecutor;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统通知 模板
 */
public abstract class AbstractSystemNotice<T extends BaseEntity> {

    @Autowired
    protected NoticeTaskExecutor noticeTaskExecutor;
    @Autowired
    protected IUserManager userManager;
    @Autowired
    protected IHouseLocationManager houseLocationManager;
    @Autowired
    protected IHouseManager houseManager;
    @Autowired
    protected INoticeManager noticeManager;
    @Autowired
    protected IHouseownerManager houseownerManager;
    @Autowired
    protected ICooperateDbDao cooperateDbDao;
    @Autowired
    protected IHouseFavoriteManager houseFavoriteManager;
    @Autowired
    protected ICustomerFavoriteDbDao customerFavoriteDbDao;
    @Autowired
    protected ICustomerDbDao customerDbDao;

    protected NoticeEntity create(T t,String type){
        //初始化通知消息
        NoticeEntity notice = new NoticeEntity();

        notice.setNoticeCode(CodeGenUtils.generate());
        notice.setNoticeType(NoticeType.SYSTEM);
        notice.setBussinessEmpName("系统");
        notice.setBussinessDeptName("");
        notice.setIsDeleted(Boolean.FALSE);
        notice.setBussinessType(type);
        notice.setCreateTime(new Date());
        notice.setLastUpdateTime(new Date());

        //设置推送内容
        initNoticeTitleAndContent(notice,t);

        //获取推送目标人群
        List<UserEntity> targetUsers = getTargetUsers(t);

        //设置推送目标人员
        addNoticeRecord(notice,targetUsers);
        return notice;
    }

    //设置推送人群
    public abstract List<UserEntity> getTargetUsers(T t);

    //设置推送内容
    public abstract void initNoticeTitleAndContent(NoticeEntity notice,T t);

    //设置推送人员
    protected void addNoticeRecord(NoticeEntity notice,List<UserEntity> targetUsers){

        if (!CollectionUtils.isEmpty(targetUsers)){
            List<NoticeRecordEntity> recordEntityList = new ArrayList<NoticeRecordEntity>();

            for (UserEntity user : targetUsers){

                if (StringUtils.isNotEmpty(user.getDeviceToken())){
                    NoticeRecordEntity record = new NoticeRecordEntity();

                    record.setRecordCode(CodeGenUtils.generate());
                    record.setNoticeCode(notice.getNoticeCode());
                    record.setHasRead(Boolean.FALSE);
                    record.setEmpCode(user.getUserCode());
                    record.setDeviceToken(user.getDeviceToken());
                    record.setCreateCode(user.getUserCode());
                    record.setIsDeleted(Boolean.FALSE);
                    record.setLastUpdateCode(user.getUserCode());
                    recordEntityList.add(record);
                }

            }
            notice.setNoticeRecordEntityList(recordEntityList);
        }
    }
}

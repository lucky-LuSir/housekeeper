package com.kfwy.hkp.sys.remind.business;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerPushDbDao;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.sys.notice.business.INoticeManager;
import com.kfwy.hkp.sys.notice.business.NoticeTaskExecutor;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import com.kfwy.hkp.sys.remind.entity.RemindDownEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class AbstractDownNoticeAop {


    @Autowired
    protected NoticeTaskExecutor noticeTaskExecutor;
    @Autowired
    protected IUserManager userManager;
    @Autowired
    protected INoticeManager noticeManager;
    @Autowired
    protected IHouseDbDao houseDbDao;
    @Autowired
    protected ICustomerDbDao customerDbDao;
    @Autowired
    protected ICustomerPushDbDao customerPushDbDao;

    //创建通知
    protected NoticeEntity create(RemindDownEntity remindDownEntity, String type) {

        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();

        //初始化推送通知
        NoticeEntity notice = new NoticeEntity();
        notice.setNoticeCode(CodeGenUtils.generate());

        notice.setBussinessCode(remindDownEntity.getRemindCode());

        notice.setIsDeleted(Boolean.FALSE);
        notice.setNoticeType(NoticeType.DOWN);
        notice.setBussinessType(type);
        notice.setCreateCode(cur.getUserCode());
        notice.setLastUpdateCode(cur.getUserCode());

        notice.setBussinessEmpName(cur.getUserName());
        notice.setBussinessDeptName(cur.getOwnerDeptName());
        //添加推送 人群
        List<UserEntity> targetUsers = addTargetUsers(remindDownEntity);

        //设置标题内容
        initNoticeTitleAndContent(notice,remindDownEntity,type);

        //添加推送人员
        processTarget(notice,targetUsers);
        return notice;
    }

    //推送人群  需要子类重写
    protected abstract List<UserEntity> addTargetUsers(RemindDownEntity remindDownEntity);

    //推送内容   需要子类重写
    protected abstract void initNoticeTitleAndContent(NoticeEntity notice,RemindDownEntity remindDownEntity,String type);
    //去重
    protected List<UserEntity> distinct(List<UserEntity> userList){
        if(userList == null || userList.size() == 0){
            return new ArrayList<>();
        }
        userList = userList.stream().collect( Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(UserEntity::getUserCode))), ArrayList::new)
        );
        return userList;
    }
    //推送人员
    protected void processTarget(NoticeEntity notice,List<UserEntity> targetUsers){
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        //设置推送人员
        List<NoticeRecordEntity> records = new ArrayList<NoticeRecordEntity>();
        if (!CollectionUtils.isEmpty(targetUsers)){
            for (UserEntity target : targetUsers){
                if(cur != null){
                    if (target.getUserCode().equals(cur.getUserCode())){
                        continue;
                    }

                    if (cur.getCpyCode ()!=null){
                        UserEntity userEntity = userManager.findOne ("userCode", target.getUserCode ());
                        if (userEntity.getCpyCode ()==null
                                ||(userEntity.getCpyCode () !=null && !cur.getCpyCode ().equals (userEntity.getCpyCode ()))){
                            continue;
                        }
                    }
                }
                if (StringUtils.isNotEmpty(target.getDeviceToken())){

                    NoticeRecordEntity record = new NoticeRecordEntity();
                    record.setNoticeCode(notice.getNoticeCode());
                    record.setEmpCode(target.getUserCode());
                    record.setDeviceToken(target.getDeviceToken());
                    record.setRecordCode(CodeGenUtils.generate());
                    record.setHasRead(Boolean.FALSE);
                    record.setIsDeleted(Boolean.FALSE);

                    record.setCreateCode(cur.getUserCode());
                    record.setLastUpdateCode(cur.getUserCode());
                    record.setOwnerDeptName(target.getOwnerDeptName());
                    record.setOwnerDeptCode(target.getOwnerDeptCode());

                    records.add(record);
                }
            }
        }
        notice.setNoticeRecordEntityList(records);
    }

    protected RemindDownEntity getArg(JoinPoint point) {
        Object[] args = point.getArgs();
        Object obj = null;
        if (ArrayUtils.isNotEmpty(args)) {
            obj = args[0];
        }
        return obj instanceof RemindDownEntity ?(RemindDownEntity)obj:null;
    }






}

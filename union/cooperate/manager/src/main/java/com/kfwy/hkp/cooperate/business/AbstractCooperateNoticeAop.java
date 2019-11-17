package com.kfwy.hkp.cooperate.business;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.sys.notice.business.NoticeTaskExecutor;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 合作通知模板
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/12/19 17:41
 */
public abstract class AbstractCooperateNoticeAop {

    @Autowired
    protected IUserManager userManager;
    @Autowired
    protected NoticeTaskExecutor noticeTaskExecutor;
    @Autowired
    protected IHouseManager houseManager;
    @Autowired
    protected ICustomerManager customerManager;

    protected Object getArg(JoinPoint point){
        Object[] args = point.getArgs();
        Object obj = null;
        if (ArrayUtils.isNotEmpty(args)){
            obj = args[0];
        }
        return obj;
    }

    //获取该合作申请人与被申请人
    protected List<UserEntity> getCooperateUser(CooperateEntity cooperateEntity){
        List<UserEntity> list = new ArrayList<>(1);
        UserEntity apply = userManager.findOne("userCode",cooperateEntity.getApplyCode());
        UserEntity receive = userManager.findOne("userCode",cooperateEntity.getReceiveCode());
        list.add(apply);
        list.add(receive);
        return list;
    }

    //初始化通知
    protected NoticeEntity create(CooperateEntity cooperate, String type){
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        //初始化通知消息
        NoticeEntity notice = new NoticeEntity();

        notice.setNoticeCode(CodeGenUtils.generate());
        notice.setNoticeType(NoticeType.Cooperation);
        notice.setBussinessCode(cooperate.getCooCode());
        notice.setBussinessEmpName(cur.getUserName());
        notice.setBussinessDeptName(cur.getOwnerDeptName());
        notice.setCreateCode(cur.getUserCode());
        notice.setLastUpdateCode(cur.getUserCode());
        notice.setIsDeleted(Boolean.FALSE);
        notice.setCreateTime(new Date());
        notice.setLastUpdateTime(new Date());
        notice.setBussinessType(type);

        //获取推送目标人群
        List<UserEntity> targetUsers = getTargetUsers(cooperate);

        //设置推送内容
        initNoticeTitleAndContent(notice,cooperate,cur);

        //设置推送目标人员
        addNoticeRecord(notice,targetUsers,cur);

        return notice;
    }

    //设置推送人群
    public abstract List<UserEntity> getTargetUsers(CooperateEntity cooperate);


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
    //设置推送内容
    public abstract void initNoticeTitleAndContent(NoticeEntity notice,CooperateEntity cooperate,UserEntity cur);

    //设置推送人员
    protected void addNoticeRecord(NoticeEntity notice,List<UserEntity> targetUsers,UserEntity cur) {

        if (!CollectionUtils.isEmpty(targetUsers)){
            List<NoticeRecordEntity> recordEntityList = new ArrayList<NoticeRecordEntity>();

            for (UserEntity target : targetUsers){
                if(cur != null){
                    if (target.getUserCode().equals(cur.getUserCode())){
                        continue;
                    }
                    if (cur.getCpyCode ()!=null){
                        UserEntity userEntity = userManager.findOne ("userCode", target.getUserCode ());
                        if (userEntity.getCpyCode ()==null
                                || (userEntity.getCpyCode () !=null && !cur.getCpyCode ().equals (userEntity.getCpyCode ()))){
                            continue;
                        }
                    }
                }
                if (StringUtils.isNotEmpty(target.getDeviceToken())){
                    NoticeRecordEntity record = new NoticeRecordEntity();

                    record.setRecordCode(CodeGenUtils.generate());
                    record.setNoticeCode(notice.getNoticeCode());
                    record.setEmpCode(target.getUserCode());
                    record.setHasRead(Boolean.FALSE);
                    record.setDeviceToken(target.getDeviceToken());
                    record.setIsDeleted(Boolean.FALSE);
                    record.setCreateCode(cur.getUserCode());
                    record.setLastUpdateCode(cur.getUserCode());
                    recordEntityList.add(record);
                }

            }
            notice.setNoticeRecordEntityList(recordEntityList);
        }
    }


}

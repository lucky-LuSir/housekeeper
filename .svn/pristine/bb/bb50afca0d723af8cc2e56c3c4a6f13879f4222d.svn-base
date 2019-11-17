package com.kfwy.hkp.trade.order.business.impl;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.sys.notice.business.NoticeTaskExecutor;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2019/1/7.
 */
@Aspect
@Component
public class OrderNoticeAop {

    @Autowired
    private NoticeTaskExecutor noticeTaskExecutor;
    @Autowired
    private IUserManager userManager;

    @Pointcut("target(com.kfwy.hkp.trade.order.dao.IOrderDbDao) " +
            "&& execution(* insert(..))")
    public void afterInsertPoint(){}


    @AfterReturning("afterInsertPoint()")
    public void afterInsertPoint(JoinPoint point){

        OrderEntity orderEntity = (OrderEntity) getArg(point);
        noticeTaskExecutor.saveAndPush(create(orderEntity, NoticeOperateType.Add));
    }

    private OrderEntity getArg(JoinPoint point){
        Object[] args = point.getArgs();
        Object obj = null;
        if (ArrayUtils.isNotEmpty(args)){
            obj = args[0];
        }
        return obj instanceof OrderEntity? (OrderEntity)obj :null;
    }

    private NoticeEntity create(OrderEntity orderEntity, String type){

        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();

        //初始化通知消息
        NoticeEntity notice = new NoticeEntity();

        notice.setNoticeCode(CodeGenUtils.generate());
        notice.setNoticeType(NoticeType.ORDER);
        notice.setBussinessCode(orderEntity.getOrderCode());
        notice.setBussinessType(type);
        notice.setBussinessEmpName(cur.getUserName());
        notice.setBussinessDeptName(cur.getOwnerDeptName());
//        noticeEntity.setNoticeTitle(title.toString());
//        noticeEntity.setNoticeContent(content.toString());
        notice.setCreateCode(orderEntity.getCreateCode());
        notice.setLastUpdateCode(orderEntity.getLastUpdateCode());
        notice.setIsDeleted(Boolean.FALSE);

        initNoticeTitleAndContent(notice,orderEntity,cur,type);

        //获取推送目标人群
        List<UserEntity> targetUsers = getTargetUsers();

        //设置推送目标人员
        addNoticeRecord(notice,targetUsers,cur);

        return notice;

    }

    public void initNoticeTitleAndContent(NoticeEntity notice,OrderEntity orderEntity,UserEntity cur,String type){

        StringBuilder title = new StringBuilder();
        StringBuilder content = new StringBuilder();
        title.append(String.format("%s%s了一个订单",
                cur.getUserName(), DictionaryStorage.get(NoticeOperateType.getKey(),type).getName()));

        content.append(String.format("恭喜%s%s斩获%s元订单!",
                orderEntity.getCreateDeptName(),
                userManager.findUserByUserCode(orderEntity.getEmpCode()).getUserName(),
                orderEntity.getCommission()));

        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }

    public List<UserEntity> getTargetUsers(){
        Map<String,Object> param = new HashMap<>();
        List<UserEntity> targetUsers = userManager.userAllList();
        return targetUsers;
    }
    public void addNoticeRecord(NoticeEntity notice,List<UserEntity> targetUsers,UserEntity cur){

        if (!CollectionUtils.isEmpty(targetUsers)){
            List<NoticeRecordEntity> recordEntityList = new ArrayList<NoticeRecordEntity>();

            for (UserEntity target : targetUsers){
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

                if (StringUtils.isNotEmpty(target.getDeviceToken())){
                    NoticeRecordEntity record = new NoticeRecordEntity();

                    record.setRecordCode(CodeGenUtils.generate());
                    record.setNoticeCode(notice.getNoticeCode());
                    record.setHasRead(Boolean.FALSE);
                    record.setEmpCode(target.getUserCode());
                    record.setDeviceToken(target.getDeviceToken());
                    record.setCreateCode(target.getUserCode());
                    record.setLastUpdateCode(target.getUserCode());
                    record.setIsDeleted(Boolean.FALSE);
                    recordEntityList.add(record);
                }

            }
            notice.setNoticeRecordEntityList(recordEntityList);
        }
    }

}

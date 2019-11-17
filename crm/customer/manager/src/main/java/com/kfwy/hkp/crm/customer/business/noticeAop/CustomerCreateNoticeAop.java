package com.kfwy.hkp.crm.customer.business.noticeAop;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.common.qiYeWeChat.send.WeChatMsgSend;
import com.kfwy.hkp.crm.customer.business.AbstractCustomerNoticeAop;
import com.kfwy.hkp.crm.customer.config.FocusCusConfigUtil;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Aspect
public class CustomerCreateNoticeAop extends AbstractCustomerNoticeAop {


    @Pointcut ("target(com.kfwy.hkp.crm.customer.business.impl.CustomerManagerImpl) " + " && execution(* create(..))")
    public void afterInsertPoint () {

    }

    @Pointcut ("target(com.kfwy.hkp.crm.customer.business.impl.CustomerManagerImpl) " + "&& execution(* cusServiceUpshelf(..))")
    public void afterCusServiceUpPoint () {
    }


    @Pointcut ("target(com.kfwy.hkp.crm.customer.dao.impl.FocusCusDbDaoImpl) " + " && execution(* updateFocusCusToPlatform(..))")
    public void afterQueryFocusCusToPlatform () {

    }

    @AfterReturning ("afterCusServiceUpPoint()")
    public void aftefUpAdvice (JoinPoint point) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        taskExecutor.execute (() -> {
            CustomerEntity customerEntity = getArg (point);
            CustomerEntity customer = customerDbDao.detail (customerEntity.getCusCode ());
            customerEntity.setCreateTime (customer.getCreateTime ());
            customerEntity.setLastUpshelfTime (customer.getLastUpshelfTime ());
            if (customerEntity.getNoticeUsers () != null && customerEntity.getNoticeUsers ().size () > 0) {
                customer.setNoticeUsers (customerEntity.getNoticeUsers ());
            }
            if (customerEntity.getNoticeDepts () != null && customerEntity.getNoticeDepts ().size () > 0) {
                customer.setNoticeDepts (customerEntity.getNoticeDepts ());
            }

            if (customer.getCusType ().equals (CustomerType.FOCUS_CUS)) {
                noticeTaskExecutor.saveAndPush (create (customer, NoticeOperateType.FOCUS_CUS, cur), customerEntity);
            }
        });
    }

    @AfterReturning ("afterInsertPoint()")
    public void afterInsertAdvice (JoinPoint point) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        taskExecutor.execute (() -> {
            CustomerEntity customerEntity = getArg (point);
            if (customerEntity.getCusType ().equals (CustomerType.FOCUS_CUS)) {
                noticeTaskExecutor.saveAndPushByOperateType (create (customerEntity, NoticeOperateType.FOCUS_CUS, cur), customerEntity);
            } else {
                noticeTaskExecutor.saveAndPush (create (customerEntity, NoticeOperateType.Add, cur));
            }
        });
    }

    @AfterReturning ("afterQueryFocusCusToPlatform()")
    public void afterQueryFocusCusToPlatformAdvice (JoinPoint point) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        taskExecutor.execute (() -> {
            List<CustomerEntity> customerEntityList = getArgs (point);
            if (customerEntityList != null && customerEntityList.size () > 0) {
                for (CustomerEntity entity : customerEntityList) {
                    noticeTaskExecutor.saveAndPush (create (entity, NoticeOperateType.Add, cur));
                }
            }

        });
    }

    @Override
    protected List<UserEntity> addTargetUsers (CustomerEntity customer) {
        List<UserEntity> targetUsers = new ArrayList<> ();
        Map<String, Object> param = new HashMap<> ();

        //非平台客户按需求区域对业务员进行客户新增通知
        if (! customer.getCusType ().equals (CustomerType.FOCUS_CUS)) {
            List<CustomerAreaEntity> cusAreas = getCusArea (customer);
            for (CustomerAreaEntity cusArea : cusAreas) {
                param.put ("region", cusArea.getRegion ());
                List<UserEntity> tmp = userManager.findByMap (param);
                if (! CollectionUtils.isEmpty (tmp)) {
                    targetUsers.addAll (tmp);
                }
            }
        } else {
            FocusCusConfig focusCusConfig = FocusCusConfigUtil.getFocusCusConfig(customer);
            targetUsers = focusCusStrategyContext.addTargetUsersForFocusCus (customer, targetUsers, focusCusConfig);

        }

        return distinct (targetUsers);
    }

    @Override
    protected void initNoticeTitleAndContent (NoticeEntity notice, CustomerEntity customer, String type) {

    }


    @Override
    public void initNoticeTitleAndContent (NoticeEntity notice, CustomerEntity customer, String type, UserEntity cur) {
        if (cur == null) {
            cur = platform;
        }
        StringBuffer title = new StringBuffer ();
        StringBuffer content = new StringBuffer ();
        //非平台客户新增通知
        if (! customer.getCusType ().equals (CustomerType.FOCUS_CUS)) {
            title.append (String.format ("%s%s了一个客户", cur.getUserName (), DictionaryStorage.get (NoticeOperateType.getKey (), type).getName ()));
            content.append (String.format ("求合作！<%s%s>找<%s㎡>用于<%s>！来自<%s-%s>", customer.getCusName (), customer.getCusSexName (), customer.getNeedAcreage (), customer.getHouseTypeName (), cur.getOwnerDeptName (), cur.getUserName ()));
        } else {
            //TODO 新增平台客户，优选通知
            title.append (String.format ("此客户为公司获客，优先推送给您，请及时跟进！"));
            content.append (String.format ("%s %s,在%s-%s找%s㎡的仓库用于%s。该客户只推送给包括您在内的三个人，请珍惜客户资源，尽快邀约带看！", customer.getCusName (), customer.getCusSexName (), customer.getCustomerAreaEntityList ().get (0).getRegionName (), customer.getCustomerAreaEntityList ().get (0).getStreetName () == null ? "" : customer.getCustomerAreaEntityList ().get (0).getStreetName (), customer.getNeedAcreage (), customer.getHouseTypeName ()));
        }

        notice.setNoticeTitle (title.toString ());
        notice.setNoticeContent (content.toString ());
    }

    @Override
    protected void initNoticeExternalApplication (NoticeEntity notice, CustomerEntity customer, String type, List<UserEntity> userEntities,UserEntity cur) throws IOException {

        if (customer.getCusType ().equals (CustomerType.FOCUS_CUS)
                &&type.equals (NoticeOperateType.FOCUS_CUS)) {
            String sendMsg = "集中获客：\n" +
                    "系统于%s分向您推送了一个客户，请到库房管家APP中进行查看，并及时回电跟进！";
            StringBuffer toUser = new StringBuffer();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String time = df.format(new Date());
            StringBuffer msg = new StringBuffer();
            if (null != userEntities && userEntities.size() > 0) {
                if (userEntities.size() > 1) {
                    toUser = new StringBuffer(userEntities.get(0).getWorkNumber());
                    for (int i = 1; i < userEntities.size(); i++) {
                        toUser.insert(0, new StringBuffer(userEntities.get(i).getWorkNumber() + "|"));
                    }
                } else {
                    toUser.append(userEntities.get(0).getWorkNumber());
                }
                System.out.println(toUser.toString());
                msg.append(String.format(sendMsg, time));
                WeChatMsgSend.send(msg, toUser);

            }
        }
    }

}

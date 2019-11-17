package com.kfwy.hkp.crm.customer.business.noticeAop;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.hkp.common.qiYeWeChat.send.WeChatMsgSend;
import com.kfwy.hkp.crm.customer.business.AbstractCustomerNoticeAop;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerPushEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerPushType;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *客户推送 消息通知
 */
@Aspect
@Component
public class CustomerPushNoticeAop extends AbstractCustomerNoticeAop {

    //客户推送 实体 集合
    private List<CustomerPushEntity> cusPushlist;

    @Pointcut("target(com.kfwy.hkp.crm.customer.business.impl.CustomerPushManagerImpl) " +
            " && execution(* batchCreate(..))")
    public void afterPushPoint() {
    }


    @AfterReturning("afterPushPoint()")
    public void afterPushAdvice(JoinPoint point) {
        //获取被推送的客户信息
        CustomerEntity customerEntity = getCus(point);
        noticeTaskExecutor.saveAndPushOneByOperateType(create(customerEntity, NoticeOperateType.CusPush));
    }

    private CustomerEntity getCus(JoinPoint point){
        Object[] args = point.getArgs();
        Object obj = null;
        if (ArrayUtils.isNotEmpty(args)){
            obj = args[0];
        }
        this.cusPushlist = (List<CustomerPushEntity>)obj;
        CustomerPushEntity customerPushEntity = this.cusPushlist.get(0);
        CustomerEntity customerEntity = customerDbDao.detail(customerPushEntity.getCusCode());
        if (StringUtils.isNotEmpty(customerPushEntity.getPushMessage())){
            customerEntity.setRemark(customerPushEntity.getPushMessage());
        }
        return customerEntity;
    }

    @Override
    protected List<UserEntity> addTargetUsers(CustomerEntity customerEntity) {
        List<UserEntity> list = new ArrayList<>();
        //房客需求推送接收部门所有人员 及接收人
        List<CustomerPushEntity> cusPushList = this.cusPushlist;
        cusPushList.forEach(((cp) -> {
            if (CustomerPushType.Personal.equals(cp.getPushType())){
                list.add(userManager.findOne("userCode",cp.getReceiveCode()));
            }else if(CustomerPushType.Department.equals(cp.getPushType())){
                Map<String,Object> param = new HashMap<>();
                param.put("isDeleted",Boolean.FALSE);
                param.put("ownerDeptCode",cp.getReceiveDeptCode());
                list.addAll(userManager.findByMap(param));
            }
        }));

        return  distinct(list);
    }

    @Override
    protected void initNoticeTitleAndContent(NoticeEntity notice, CustomerEntity customer, String type) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        StringBuffer title = new StringBuffer();
        StringBuffer content = new StringBuffer();

        title.append(String.format("%s%s了一个的客户", cur.getUserName(),
                DictionaryStorage.get(NoticeOperateType.getKey(), type).getName()));
        content.append(String.format("<%s-%s>向您推送了客户：<%s+%s>找<%s㎡>用于<%s>",
                cur.getOwnerDeptName(),cur.getUserName(),
                customer.getCusName(),customer.getCusSexName(),
                customer.getNeedAcreage(),customer.getHouseTypeName()));

        notice.setNoticeTitle(title.toString());
        notice.setNoticeContent(content.toString());
    }

    @Override
    protected void initNoticeExternalApplication(NoticeEntity notice, CustomerEntity customer, String type, List<UserEntity> userEntities,UserEntity cur) throws IOException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = df.format(new Date());
        String sendMsg = "客户推送：\n" +
                "%s%s于%s分向您推送了客户%s，请到库房管家app中查看！";
        StringBuffer  toUser = new StringBuffer();
        StringBuffer msg = new StringBuffer();
        if (null!=userEntities&&userEntities.size()>0) {
            if (userEntities.size() > 1) {
                toUser = new StringBuffer(userEntities.get(0).getWorkNumber());
                for (int i = 1; i < userEntities.size(); i++) {
                    toUser.insert(0,new StringBuffer(userEntities.get(i).getWorkNumber()+"|"));
                }
            } else {
                toUser.append(userEntities.get(0).getWorkNumber());
            }
            msg.append(String.format(sendMsg, cur.getOwnerDeptName(), cur.getUserName(), time, customer.getCusName()));

            WeChatMsgSend.send(msg, toUser);
        }
    }
}

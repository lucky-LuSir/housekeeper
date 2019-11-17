package com.kfwy.hkp.sys.notice.business;

import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.base.TimeSplit;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.crm.customer.config.FocusCusConfigUtil;
import com.kfwy.hkp.crm.customer.dao.IFocusCusDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.FocusCusEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.sys.notice.push.PushManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author baiye
 * @version 1.0
 * @description TODO
 * @date 2018/12/19 11:26
 */
@Component
public class NoticeTaskExecutor {
    private static Logger logger = LoggerFactory.getLogger(NoticeTaskExecutor.class);


    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    private INoticeManager noticeManager;

    @Autowired
    private IFocusCusDbDao focusCusDbDao;


    public NoticeTaskExecutor() {
    }

    public void saveAndPush(NoticeEntity notice) {

        taskExecutor.execute(() -> {

            //1、保存数据库
            noticeManager.create(notice);

            //2、推送到移动端
            if (!CollectionUtils.isEmpty(notice.getNoticeRecordEntityList())) {
                for (NoticeRecordEntity record : notice.getNoticeRecordEntityList()) {
                    if (StringUtils.isNotEmpty(record.getDeviceToken())) {
                        PushManager push = new PushManager(notice.getNoticeTitle(), notice.getNoticeContent());
                        push.setExtraField("code", record.getRecordCode());
                        push.push(PushManager.PushType.unicast, record.getDeviceToken());
                    }
                }
            }
        });

    }
    //客户推送
    public void saveAndPushOneByOperateType(NoticeEntity notice) {

        taskExecutor.execute(() -> {

            //1、保存数据库
            noticeManager.create(notice);

            //2、推送到移动端
            if (!CollectionUtils.isEmpty(notice.getNoticeRecordEntityList())) {
                for (NoticeRecordEntity record : notice.getNoticeRecordEntityList()) {
                    if (StringUtils.isNotEmpty(record.getDeviceToken())) {
                        PushManager push = new PushManager(notice.getNoticeTitle(), notice.getNoticeContent());
                        push.setExtraField("code", record.getRecordCode());
                        push.pushByOperateType(PushManager.PushType.unicast, record.getDeviceToken(),notice);
                    }
                }
            }
        });

    }

    //集中获客
    public void saveAndPushByOperateType(NoticeEntity notice, CustomerEntity cus) {
        taskExecutor.execute(() -> {

            //1、保存数据库
            noticeManager.create(notice);

            //2、推送到移动端
            if (!CollectionUtils.isEmpty(notice.getNoticeRecordEntityList())) {
                int i = 0;
                for (NoticeRecordEntity record : notice.getNoticeRecordEntityList()) {
                    int count = i;
                    //推送消息
                    pushNoticeByOperateType(notice, record, count);

                    //新增集中获客子表信息
                    addFocusInfo(cus, record, count);
                }
            }
        });

    }

    public void saveAndPush(NoticeEntity notice, CustomerEntity cus) {
        taskExecutor.execute(() -> {

            //1、保存数据库
            noticeManager.create(notice);

            //2、推送到移动端
            if (!CollectionUtils.isEmpty(notice.getNoticeRecordEntityList())) {
                int i = 0;
                for (NoticeRecordEntity record : notice.getNoticeRecordEntityList()) {
                    int count = i;
                    //推送消息
                    pushNotice(notice, record, count);

                    //新增集中获客子表信息
                    addFocusInfo(cus, record, count);
                }
            }
        });

    }

    private void pushNotice(NoticeEntity notice, NoticeRecordEntity record, int count) {
        if (StringUtils.isNotEmpty(record.getDeviceToken())) {
            try {
                PushManager push = new PushManager(notice.getNoticeTitle(), notice.getNoticeContent());
                push.setExtraField("code", record.getRecordCode());
                push.push(PushManager.PushType.unicast, record.getDeviceToken());
                if (logger.isDebugEnabled()) {
                    logger.debug("push Guest 第：" + (count + 1) + DateUtil.formatDateTime(new Date()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void pushNoticeByOperateType(NoticeEntity notice, NoticeRecordEntity record, int count) {
        if (StringUtils.isNotEmpty(record.getDeviceToken())) {
            try {
                PushManager push = new PushManager(notice.getNoticeTitle(), notice.getNoticeContent());
                push.setExtraField("code", record.getRecordCode());
                push.pushByOperateType(PushManager.PushType.unicast, record.getDeviceToken(),notice);
                if (logger.isDebugEnabled()) {
                    logger.debug("push Guest 第：" + (count + 1) + DateUtil.formatDateTime(new Date()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addFocusInfo(CustomerEntity cus, NoticeRecordEntity record, int count) {
        FocusCusEntity focusCusEntity = new FocusCusEntity();
        focusCusEntity.setCusCode(cus.getCusCode());
        focusCusEntity.setHandle(Boolean.FALSE);
        focusCusEntity.setHandleStatus(0);
        focusCusEntity.setCreateCode(cus.getCreateCode());
        focusCusEntity.setCreateTime(new Date());
        focusCusEntity.setLastUpdateCode(cus.getCreateCode());
        focusCusEntity.setUserCode(record.getEmpCode());
        focusCusEntity.setLastUpdateTime(new Date());
        focusCusEntity.setIsDeleted(false);
        focusCusEntity.setFocusType("new");
        focusCusEntity.setUserDeptCode(record.getOwnerDeptCode());
        // 判断新增还是上架
        if(cus.getLastUpshelfTime() != null){
            long time = cus.getLastUpshelfTime().getTime() - cus.getCreateTime().getTime();
            // 时差24小时以上才算作重新上架
            if(time > 86400000){
                focusCusEntity.setFocusType("old");
            }
        }

        if(cus.getNoticeUsers ()!=null
                && cus.getNoticeUsers ().size ()>0){
            if (cus.getNoticeUsers ().contains (record.getEmpCode ())){

                focusCusEntity.setSpecialPush (Boolean.TRUE);
                focusCusEntity.setSeeCusTime (new Date ());

            }else{

                FocusCusConfig focusCusConfig = FocusCusConfigUtil.getFocusCusConfig(cus);

                TimeSplit delayNoticeTime = new TimeSplit (null, focusCusConfig.getDelayNoticeTime (), "mm");
                focusCusEntity.setSeeCusTime (DateFormatUtil.addDate (DateFormatUtil.getCurrentDateTime (),
                        delayNoticeTime.getAfterNumber (),
                        delayNoticeTime.getAfterFormat ()));
                focusCusEntity.setSpecialPush (Boolean.FALSE);

            }
        }else{
            focusCusEntity.setSpecialPush (Boolean.FALSE);
            focusCusEntity.setSeeCusTime (new Date ());
        }

        if (focusCusEntity!=null){
            try {
                focusCusDbDao.insert(focusCusEntity);
                if (logger.isDebugEnabled()) {
                    logger.debug("新增集中获客子表信息 第：" + (count + 1) + "个");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public  FocusCusConfig getFocusCusConfig (CustomerEntity cus) {
        FocusCusConfig focusCusConfig = SystemConfig.create ().getObject ("focus_cus_config", FocusCusConfig.class);
        if (cus.getNoticeDepts ()!=null){
            if (cus.getNoticeDepts ().size ()>1){
                List<FocusCusConfig> focusCusConfigs = new ArrayList<> ();
                for (String dept : cus.getNoticeDepts ()) {
                    FocusCusConfig deptFocusCusStrtegy = SystemConfig.create ().getObject (dept+"_focus_cus_config", FocusCusConfig.class);
                    if (deptFocusCusStrtegy==null){
                        deptFocusCusStrtegy = focusCusConfig;
                    }
                    focusCusConfigs.add (deptFocusCusStrtegy);
                }

                Boolean flag = true;
                FocusCusConfig strategy = SystemConfig.create ().getObject (cus.getNoticeDepts ().get (0)+"_focus_cus_config", FocusCusConfig.class);

                for (FocusCusConfig config : focusCusConfigs) {
                    if (!config.getStrategy ().equals (strategy.getStrategy ())){
                        flag=false;
                    }
                }

                if (flag){
                    focusCusConfig.setStrategy (strategy.getStrategy ());
                }

            }else if (cus.getNoticeDepts ().size ()==1){
                FocusCusConfig deptFocusCusStrtegy = SystemConfig.create ().getObject (cus.getNoticeDepts ().get (0)+"_focus_cus_config", FocusCusConfig.class);
                if (deptFocusCusStrtegy!=null){
                    focusCusConfig = deptFocusCusStrtegy;
                }
            }
        }
        return focusCusConfig;
    }
}

package com.kfwy.hkp.crm.customer.business;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.kfwy.hkp.cooperate.dao.ICooperateDbDao;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.cooperate.enums.CooStatus;
import com.kfwy.hkp.crm.customer.business.strategy.FocusCusStrategyContext;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerPushDbDao;
import com.kfwy.hkp.crm.customer.dao.IFocusCusBlackListDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerPushEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerPushType;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.notice.business.INoticeManager;
import com.kfwy.hkp.sys.notice.business.NoticeTaskExecutor;
import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.entity.NoticeRecordEntity;
import com.kfwy.hkp.sys.notice.enums.NoticeOperateType;
import com.kfwy.hkp.sys.notice.enums.NoticeType;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baiye
 * @version 1.0
 * @description TODO
 * @date 2019/6/20 14:04
 * 客户消息通知 模板
 */
public abstract class AbstractCustomerNoticeAop implements InitializingBean {

    @Autowired
    protected NoticeTaskExecutor noticeTaskExecutor;
    @Autowired
    protected IUserManager userManager;
    @Autowired
    protected ICustomerAreaManager customerAreaManager;
    @Autowired
    protected INoticeManager noticeManager;
    @Autowired
    protected IDeptManager deptManager;
    @Autowired
    protected ICustomerDbDao customerDbDao;
    @Autowired
    protected ICooperateDbDao cooperateDbDao;
    @Autowired
    protected ICustomerPushDbDao customerPushDbDao;
    @Autowired
    protected ICustomerManager customerManager;
    @Autowired
    protected IFocusCusManager focusCusManager;
    @Autowired
    protected TaskExecutor taskExecutor;
    protected static UserEntity platform;
    @Autowired
    protected IFocusCusBlackListDbDao focusCusBlackListDbDao;
    @Autowired
    protected IFocusCusReceiveDeptLogManager focusCusReceiveDeptLogManager;
    @Autowired
    protected FocusCusStrategyContext focusCusStrategyContext;

    @Override
    public void afterPropertiesSet () throws Exception {
        if (platform == null) {
            platform = userManager.selectUniqueByProp ("E201901220001bdc7");
            platform.setOwnerDeptName ("系统");
        }
    }

    //创建通知
    protected NoticeEntity create (CustomerEntity customer, String type) {

        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        if (cur == null) {
            cur = platform;
        }
        UserEntity cu =cur;
        //初始化推送通知
        NoticeEntity notice = initNoticeEntityInfo (customer, type, cur);
        //添加推送 人群
        List<UserEntity> targetUsers = addTargetUsers (customer);

        //设置标题内容
        initNoticeTitleAndContent (notice, customer, type);

        //添加推送人员
        processTarget (notice, targetUsers);

        taskExecutor.execute (() -> {
            try {
                initNoticeExternalApplication(notice, customer, type, targetUsers,cu);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        });
        return notice;
    }


    //创建通知
    protected NoticeEntity create (CustomerEntity customer, String type, UserEntity cur) {
        if (cur == null) {
            cur = platform;
        }
        UserEntity cu =cur;
        //初始化推送通知
        NoticeEntity notice = initNoticeEntityInfo (customer, type, cur);
        //添加推送 人群
        List<UserEntity> targetUsers = addTargetUsers (customer);

        //设置标题内容
        initNoticeTitleAndContent (notice, customer, type, cur);

        //添加推送人员
        processTarget (notice, targetUsers, cur);

        //企业微信推送消息
        taskExecutor.execute (() -> {
            try {
                initNoticeExternalApplication(notice, customer, type, targetUsers,cu);
            } catch (IOException e) {
                e.printStackTrace ();
            }
        });

        return notice;
    }

    private NoticeEntity initNoticeEntityInfo (CustomerEntity customer, String type, UserEntity cur) {
        NoticeEntity notice = new NoticeEntity ();
        notice.setNoticeCode (CodeGenUtils.generate ());
        notice.setBussinessCode (customer.getCusCode ());
        if (StringUtils.isNotEmpty(customer.getRemark())){
            notice.setNoticeMessage(customer.getRemark());
        }
        notice.setIsDeleted (Boolean.FALSE);
        if (customer.getCusType ().equals (CustomerType.FOCUS_CUS)
                && type.equals (NoticeOperateType.FOCUS_CUS)) {

            notice.setNoticeType (NoticeType.FOCUS_FOR_THE_GUEST);
        }

        if(type.equals(NoticeOperateType.CusPush)){
            notice.setNoticeType (NoticeType.CUS_PUSH_FOR_ME);
        } else {
            notice.setNoticeType (NoticeType.CUSTOMER);
        }

        notice.setBussinessType (type);
        notice.setCreateCode (cur.getUserCode ());
        notice.setLastUpdateCode (cur.getUserCode ());
        notice.setCreateTime (new Date ());
        notice.setLastUpdateTime (new Date ());
        notice.setBussinessEmpName (cur.getUserName ());
        if (cur.getOwnerDeptName () != null) {
            notice.setBussinessDeptName (cur.getOwnerDeptName ());
        }
        return notice;
    }

    // 获取客户需求区域
    protected List<CustomerAreaEntity> getCusArea (CustomerEntity customer) {
        List<CustomerAreaEntity> cusAreas = customer.getCustomerAreaEntityList ();
        Map<String, Object> param = new HashMap<> ();
        if (CollectionUtils.isEmpty (cusAreas)) {
            param.put ("cusCode", customer.getCusCode ());
            cusAreas = customerAreaManager.findByMap (param);
        }
        return cusAreas;
    }

    //推送人群  需要子类重写
    protected abstract List<UserEntity> addTargetUsers (CustomerEntity customerEntity);


    //推送内容   需要子类重写
    protected abstract void initNoticeTitleAndContent (NoticeEntity notice, CustomerEntity customer, String type);


    //推送内容   需要子类重写
    protected void initNoticeTitleAndContent (NoticeEntity notice, CustomerEntity customer, String type, UserEntity cur) {

    }

    //外部应用发送消息 需要子类重写
    protected abstract void initNoticeExternalApplication (NoticeEntity notice, CustomerEntity customer, String type,List<UserEntity> userEntities,UserEntity cur) throws IOException;

    //推送人员
    protected void processTarget (NoticeEntity notice, List<UserEntity> targetUsers) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        if (cur == null) {
            cur = platform;
        }
        //设置推送人员
        List<NoticeRecordEntity> records = new ArrayList<NoticeRecordEntity> ();
        if (! CollectionUtils.isEmpty (targetUsers)) {
            for (UserEntity target : targetUsers) {
                if (cur != null) {
                    //当前消息推送人和接收人是否是同一个公司的，如果不是，则不推送。
                    if (target.getUserCode ().equals (cur.getUserCode ())) {
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

                if (StringUtils.isNotEmpty (target.getDeviceToken ())) {

                    NoticeRecordEntity record = new NoticeRecordEntity ();
                    record.setNoticeCode (notice.getNoticeCode ());
                    record.setEmpCode (target.getUserCode ());
                    record.setDeviceToken (target.getDeviceToken ());
                    record.setRecordCode (CodeGenUtils.generate ());
                    record.setIsDeleted (Boolean.FALSE);
                    record.setHasRead (Boolean.FALSE);

                    record.setCreateCode (cur.getUserCode ());
                    record.setLastUpdateCode (cur.getUserCode ());
                    if (target.getOwnerDeptName () != null) {
                        record.setOwnerDeptName (target.getOwnerDeptName ());

                    }
                    if (target.getOwnerDeptCode () != null) {
                        record.setOwnerDeptCode (target.getOwnerDeptCode ());
                    }
                    records.add (record);
                }
            }
        }
        notice.setNoticeRecordEntityList (records);
    }


    //推送人员
    protected void processTarget (NoticeEntity notice, List<UserEntity> targetUsers, UserEntity cur) {
        if (cur == null) {
            cur = platform;
        }
        //设置推送人员
        List<NoticeRecordEntity> records = new ArrayList<> ();
        if (! CollectionUtils.isEmpty (targetUsers)) {
            for (UserEntity target : targetUsers) {
                if (cur != null) {
                    if (target.getUserCode ().equals (cur.getUserCode ())) {
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
                if (StringUtils.isNotEmpty (target.getDeviceToken ())) {

                    NoticeRecordEntity record = new NoticeRecordEntity ();
                    record.setNoticeCode (notice.getNoticeCode ());
                    record.setEmpCode (target.getUserCode ());
                    record.setDeviceToken (target.getDeviceToken ());
                    record.setRecordCode (CodeGenUtils.generate ());
                    record.setIsDeleted (Boolean.FALSE);
                    record.setHasRead (Boolean.FALSE);

                    record.setCreateCode (cur.getUserCode ());
                    record.setLastUpdateCode (cur.getUserCode ());
                    if (target.getOwnerDeptName () != null) {
                        record.setOwnerDeptName (target.getOwnerDeptName ());

                    }
                    if (target.getOwnerDeptCode () != null) {
                        record.setOwnerDeptCode (target.getOwnerDeptCode ());
                    }
                    records.add (record);
                }
            }
        }
        notice.setNoticeRecordEntityList (records);
    }


    protected CustomerEntity getArg (JoinPoint point) {
        Object[] args = point.getArgs ();
        Object obj = null;
        if (ArrayUtils.isNotEmpty (args)) {
            obj = args[0];
        }
        return obj instanceof CustomerEntity ? (CustomerEntity) obj : null;
    }

    protected List<CustomerEntity> getArgs (JoinPoint point) {
        Object[] args = point.getArgs ();
        Object obj = null;
        if (ArrayUtils.isNotEmpty (args)) {
            obj = args[0];
        }
        return obj instanceof List ? (List<CustomerEntity>) obj : null;
    }

    //去重
    protected List<UserEntity> distinct (List<UserEntity> userList) {
        if (userList == null || userList.size () == 0) {
            return new ArrayList<> ();
        }
        userList = userList.stream ().collect (Collectors.collectingAndThen (Collectors.toCollection (() -> new TreeSet<> (Comparator.comparing (UserEntity :: getUserCode))), ArrayList :: new));
        return userList;
    }

    //获取客户所属者
    protected UserEntity getUserByCusOwner (CustomerEntity customerEntity) {
        return userManager.findOne ("userCode", customerEntity.getEmpCode ());
    }

    //获取 当前人的部门人员
    protected List<UserEntity> getUserListByDept (CustomerEntity customerEntity) {
        List<UserEntity> list = new ArrayList<> ();

        UserEntity userEntity = (UserEntity) CurrentContext.getUserInfo ();
        if (UserLevel.K0.equals (userEntity.getUserLevel ())) {
            DeptEntity deptEntity = deptManager.getDeptEntity (userEntity.getOwnerDeptCode ());
            //如果部门间共享 且这个客户公开 就返回部门人员
            if (deptEntity.getHasShareCus () && customerEntity.getOpenFlag ()) {
                Map<String, Object> param = new HashMap<> ();
                param.put ("isDeleted", Boolean.FALSE);
                param.put ("ownerDeptCode", CurrentContext.getUserInfo ().getOwnerDeptCode ());
                list.addAll (userManager.findByMap (param));
            }
        }
        ;
        return list;
    }

    //获取合作人
    protected List<UserEntity> getUserListByCooperation (CustomerEntity customerEntity) {
        List<UserEntity> list = new ArrayList<> ();

        String userCode = CurrentContext.getUserCode ();

        Map<String, Object> cooMap = new HashMap<> ();
        cooMap.put ("cusCode", customerEntity.getCusCode ());
        cooMap.put ("isDeleted", Boolean.FALSE);
        List<CooperateEntity> cooperateEntityList = cooperateDbDao.selectByMap (cooMap);

        cooperateEntityList.forEach ((coo) -> {
            if (! coo.getCooStatus ().equals (CooStatus.END)) {
                return;
            }
            Map<String, Object> param = new HashMap<> ();
            //合作申请者
            param.put ("userCode", coo.getApplyCode ());
            param.put ("isDeleted", Boolean.FALSE);
            list.add (userManager.findOne (param));
        });

        return list;
    }

    ////获取房客需求推送接收部门所有人员，房客需求推送接收人
    protected List<UserEntity> getUserListByPushDeptAndUser (CustomerEntity customerEntity) {
        List<UserEntity> list = new ArrayList<> ();
        //获取客户推送的实体集合CustomerPushEntity
        List<CustomerPushEntity> cusPushList = customerPushDbDao.getPushDeptOrUser (customerEntity.getCusCode ());
        if (cusPushList == null || cusPushList.size () == 0) {
            return list;
        }
        cusPushList.forEach ((cp) -> {
            if (CustomerPushType.Department.equals (cp.getPushType ())) {
                Map<String, Object> param = new HashMap<> ();
                param.put ("isDeleted", Boolean.FALSE);
                param.put ("ownerDeptCode", cp.getReceiveDeptCode ());
                list.addAll (userManager.findByMap (param));
            } else if (CustomerPushType.Personal.equals (cp.getPushType ())) {
                list.add (userManager.findOne ("userCode", cp.getReceiveCode ()));
            }
        });
        return list;
    }
}

package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.common.enums.OperationType;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.sms.SmsEntity;
import com.kfwy.hkp.common.sms.send.ISmsTemplate;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.common.utils.PhoneUtils;
import com.kfwy.hkp.crm.customer.business.*;
import com.kfwy.hkp.crm.customer.dao.*;
import com.kfwy.hkp.crm.customer.entity.*;
import com.kfwy.hkp.crm.customer.enums.CustomerApplyHidOrPriType;
import com.kfwy.hkp.crm.customer.enums.CustomerCheckType;
import com.kfwy.hkp.crm.customer.enums.CustomerStatus;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.hrm.org.dept.dto.CusServiceDto;
import com.kfwy.hkp.log.manager.IOperationManager;
import com.kfwy.hkp.sys.area.business.ICompanyAreaManager;
import com.kfwy.hkp.sys.area.entity.CompanyAreaEntity;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.business.IFileRelationManager;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import com.kfwy.hkp.sys.notice.business.INoticeManager;
import com.kfwy.hkp.sys.remind.business.IRemindDownManager;
import com.kfwy.hkp.sys.remind.entity.RemindDownEntity;
import com.kfwy.hkp.sys.remind.enums.DownType;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by zjp on 2018/7/16.
 */
@Service
public class CustomerManagerImpl extends AbstractManager<CustomerEntity> implements ICustomerManager {
    /**
     * 平台所属code
     */
    private final String PLATFORM = "E201901220001bdc7";
    @Autowired
    private ICustomerDbDao customerDbDao;
    @Autowired
    private ICustomerContactsManager customerContactsManager;
    @Autowired
    private ICustomerAreaManager customerAreaManager;
    @Autowired
    private ICustomerFavoriteManager customerFavoriteManager;
    @Autowired
    private ICustomerUpdownDbDao customerUpdownDbDao;
    @Autowired
    private IFileRelationManager fileRelationManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private ICustomerApplyDbDao customerApplyDbDao;
    @Autowired
    private IRemindDownManager remindDownManager;
    @Autowired
    private ISmsTemplate smsTemplate;
    @Autowired
    protected SystemConfig systemConfig;
    @Autowired
    private ICompanyAreaManager companyAreaManager;
    @Resource (name = "k0CustomerCheckManager")
    private ICustomerCheckManager k0CheckManager;
    @Resource (name = "k1CustomerCheckManager")
    private ICustomerCheckManager k1CheckManager;
    @Resource (name = "k2CustomerCheckManager")
    private ICustomerCheckManager k2CheckManager;
    @Resource (name = "t0CustomerCheckManager")
    private ICustomerCheckManager t0CheckManager;
    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private IFileManager fileManager;
    @Autowired
    private ICustomerApplyHidOrPriManager customerApplyHidOrPriManager;
    @Autowired
    private ICustomerWaitAddManager customerWaitAddManager;
    @Autowired
    private IFocusCusDbDao focusCusDbDao;
    @Autowired
    private INoticeManager noticeManager;
    @Autowired
    private IOperationManager operationManager;

    @Override
    protected IMyBatisBaseDao getMyBatisDao () {
        return this.customerDbDao;
    }
    @Override
    public void afterCreate(CustomerEntity customerEntity){
        taskExecutor.execute (() -> {
            customerWaitAddManager.deleteCusWaitAdd (customerEntity.getCusPhone ());
        });
    }

    @Override
    public void beforeCreate(CustomerEntity customerEntity){
        // 手机号格式
        if (! PhoneUtils.isMobile (customerEntity.getCusPhone ())) {
            throw new RemoveStackBusinessException ("客户手机号码格式错误");
        }
        if (ListUtils.isEmpty (customerEntity.getCustomerAreaEntityList ())) {
            throw new RemoveStackBusinessException ("客户需求区域不能为空！");
        }
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        //检查是否可以创建,如果有值就抛出异常
        List<CustomerEntity> cusList = checkCustomerCanCreate (customerEntity.getCusPhone ());
        if (cusList != null && cusList.size () > 0) {
            if (cur.getEnterType ()!="1"){
                throw new RemoveStackBusinessException ("该客户已存在, 不能重复录入");
            }else{
                CustomerEntity entity = cusList.stream ().filter (cus -> cus.getCusType ().equals (CustomerType.PLATFORM)
                        || cus.getCusType ().equals (CustomerType.PRIVATE)
                        || cus.getCusType ().equals (CustomerType.FOCUS_CUS)).findAny ().orElse (null);
                if (entity!=null){
                    throw new RemoveStackBusinessException ("该客户已存在, 不能重复录入");
                }
            }
        }
        //填充基本信息，包括编码及默认值
        fillCustomerBaseInfo (customerEntity);
        //填充省
        fillProvince (customerEntity);
        // 保存更多联系人
        saveMoreContacts (customerEntity);
        //保存区域信息
        saveCustomerArea (customerEntity);
        // 保存关联的文件
        saveCustomerFile (customerEntity);

        //公开字段,默认公开
        if (customerEntity.getOpenFlag () == null) {
            customerEntity.setOpenFlag (true);
        } else if (! customerEntity.getOpenFlag ()) {
            // 超过限制值后
            if (! checkCusPermissions (CustomerCheckType.CHECK_CUSTOMER_OFF_OPEN_FLAG, customerEntity, cur)) {
                throw new RemoveStackBusinessException ("您的隐藏客户已经达到" + systemConfig.getValueInt ("hkp_cus_hide_count") + "个，已经不能再新增隐藏客户了,请您新增公开客户哦!");
            }
        }
        //通过客户的录入人设置客户类型
        setCusTypeByCreater (customerEntity);
        String userName = CurrentContext.getUserInfo ().getUserName ();
        if (customerEntity.getCusType ().equals (CustomerType.FOCUS_CUS)) {
            userName = "您的选址顾问";
        }
        //客户创建给客户发送短信
        if (checkCusCreateSend ()){
            smsTemplate.asyncSend (customerEntity.getCusPhone (), SmsEntity.cusCreateTemplate, new String[]{userName});
        }
    }

    @Override
    @Transactional
    public int create (CustomerEntity customerEntity) {
        beforeCreate(customerEntity);
        int i = customerDbDao.insert (customerEntity);
        afterCreate (customerEntity);
        return i;
    }

    /**
     * 是否发送短信
     */
    public Boolean checkCusCreateSend () {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        if (cur.getUserLevel ().equals (UserLevel.K0)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    //填充省 前端现在不传
    private void fillProvince (CustomerEntity customerEntity) {
        List<CustomerAreaEntity> areaList = customerEntity.getCustomerAreaEntityList ();
        areaList.forEach (a -> {
            if (StringUtils.isEmpty (a.getCity ()) || StringUtils.isEmpty (a.getRegion ())) {
                throw new RemoveStackBusinessException ("市区编码未传");
            }
            CompanyAreaEntity companyAreaEntity = companyAreaManager.findAreaByCode (a.getCity ());
            if (companyAreaEntity == null) {
                throw new RemoveStackBusinessException ("该市未启用" + a.getCityName ());
            }
            CompanyAreaEntity parentArea = companyAreaManager.findAreaByCode (companyAreaEntity.getParentCode ());
            if (parentArea == null) {
                throw new RemoveStackBusinessException ("该市所在的省未启用" + a.getCityName ());
            }
            a.setProvince (parentArea.getAreaCode ());
            a.setProvinceName (parentArea.getName ());
        });
    }

    /**
     * 如果当前录入人是客服，则默认客户归属平台，
     * 如果当前录入人是经理，且选择录入客户为平台类型进入当前判断
     * 否则全部归属个人
     *
     * @param customerEntity
     */
    private void setCusTypeByCreater (CustomerEntity customerEntity) {
        UserEntity user = (UserEntity) CurrentContext.getUserInfo ();

        if (String.valueOf (CustomerType.PLATFORM).equals (user.getEnterType ())
                || CustomerType.PLATFORM.equals (customerEntity.getCusType ())) {
            //TODO 注释掉平台获客功能
            customerEntity.setCusType (CustomerType.FOCUS_CUS);
            customerEntity.setEmpCode (PLATFORM);
        } else {
            customerEntity.setEmpCode (CurrentContext.getUserCode ());
            customerEntity.setCusType (CustomerType.AGENT);
        }
    }

    /**
     * 填充基本信息，包括编码及默认值
     *
     * @param customerEntity
     */
    private void fillCustomerBaseInfo (CustomerEntity customerEntity) {
        //客户编码生成及填充信息
        OperateFillUtils.fill (customerEntity);
        customerEntity.setLastUpshelfTime (new Date ());
        customerEntity.setEmpCode (CurrentContext.getUserCode ());
        customerEntity.setEmpName (CurrentContext.getUserInfo ().getUserName ());
        //客户创建的时候，默认把最后跟进事件设置为客户创建时间
        customerEntity.setLastFollowupTime (new Date ());
        // 客户的类型，根据当前人的角色而定
        if (String.valueOf (CustomerType.PLATFORM).equals (((UserEntity) CurrentContext.getUserInfo ()).getEnterType ())) {
            customerEntity.setCusType (CustomerType.PLATFORM);
        }
        if (StringUtils.isEmpty (customerEntity.getCusStatus ())) {
            customerEntity.setCusStatus (CustomerStatus.FOLLOWING);
        }
    }

    /**
     * 检查是否可以创建客户
     *
     * @param cusPhone
     */
    public List<CustomerEntity> checkCustomerCanCreate (String cusPhone) {
        // 手机号是否存在
        List<CustomerEntity> oldCus = findSimpleCustomer (cusPhone);
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        List<CustomerEntity> result = new ArrayList<> ();

        for (CustomerEntity cus : oldCus) {
            CustomerEntity ce = detail (cus.getCusCode ());
            if (! checkCusPermissions (CustomerCheckType.CHECK_CREATE_CUS, ce, cur)) {
                result.add (cus);
            }
        }
        return result;
    }

    @Override
    public int deleteCus (String cusCode) {
        if (cusCode==null){
            throw new RemoveStackBusinessException ("客户编码不能为空");
        }

        CustomerEntity detail = customerDbDao.detail (cusCode);
        if (detail!=null){

            try {

                operationManager.save (cusCode, OperationType.DELETE_CUS,detail.getEmpName ());
                customerDbDao.deleteById (detail.getId ());
                taskExecutor.execute (() -> {
                    focusCusDbDao.deletedByCusCode (cusCode);
                    customerAreaManager.deleteByCusCode (cusCode);
                    noticeManager.deleteByBussinessCode (cusCode);
                });


            } catch (Exception e) {
                throw new RemoveStackBusinessException ("删除失败");
            }
        }

        return 1;
    }

    /**
     * 通过手机号找到客户对象信息
     *
     * @param cusPhone
     * @return
     */
    private List<CustomerEntity> findSimpleCustomer (String cusPhone) {
        UserEntity cur = (UserEntity)CurrentContext.getUserInfo ();
        List<CustomerEntity> list = null;
        if (StringUtils.isNotEmpty (cusPhone)) {
            String preSql = "select * from t_hkp_crm_customer where cus_phone=\'%s\' \n";
            if (cur.getCpyCode ()!=null){
                preSql+= "and cpy_code=\'%s\' ";
                list = customerDbDao.selectByNativeSql (String.format (preSql, cusPhone,cur.getCpyCode ()));
            }else{
                list = customerDbDao.selectByNativeSql (String.format (preSql, cusPhone));
            }
        }

        return list;
    }

    /**
     * 保存更多联系人
     *
     * @param customerEntity
     */
    private void saveMoreContacts (CustomerEntity customerEntity) {
        if (! ListUtils.isEmpty (customerEntity.getCustomerContactsEntityList ())) {
            for (CustomerContactsEntity customerContactsEntity : customerEntity.getCustomerContactsEntityList ()) {
                customerContactsEntity.setCusCode (customerEntity.getCusCode ());
                customerContactsManager.create (customerContactsEntity);
            }
        }
    }

    /**
     * 保存区域信息
     *
     * @param customerEntity
     */
    private void saveCustomerArea (CustomerEntity customerEntity) {
        // 保存区域信息
        if (! ListUtils.isEmpty (customerEntity.getCustomerAreaEntityList ())) {
            for (CustomerAreaEntity customerAreaEntity : customerEntity.getCustomerAreaEntityList ()) {
                customerAreaEntity.setCusCode (customerEntity.getCusCode ());
                customerAreaManager.create (customerAreaEntity);
            }
        }
    }

    /**
     * 保存关联的文件
     *
     * @param customerEntity
     */
    private void saveCustomerFile (CustomerEntity customerEntity) {
        // 保存关联的文件
        if (! ListUtils.isEmpty (customerEntity.getFileRelationEntityList ())) {
            for (FileRelationEntity fileRelationEntity : customerEntity.getFileRelationEntityList ()) {
                fileRelationEntity.setOwnerCode (customerEntity.getCusCode ());
                fileRelationManager.create (fileRelationEntity);
            }
            fileManager.createFolderAndUpdateFile ("客户编号:" + customerEntity.getCusCode (), customerEntity.getCusCode (), customerEntity.getFileRelationEntityList ());
        }
    }

    @Override
    @Transactional
    public int update (CustomerEntity customerEntity) {
        CustomerEntity oldInfo = findOne ("cusCode", customerEntity.getCusCode ());

        if (oldInfo.getOpenFlag () == true && ! customerEntity.getOpenFlag ()) {
            if (! checkCusPermissions (CustomerCheckType.CHECK_CUSTOMER_OFF_OPEN_FLAG, customerEntity, (UserEntity) CurrentContext.getUserInfo ())) {
                throw new RemoveStackBusinessException ("您的隐藏客户已经达到" + systemConfig.getValueInt ("hkp_cus_hide_count") + "个，已经不能再进行隐藏操作!");
            }
        }
        if (oldInfo.getCusType ().equals (CustomerType.FOCUS_CUS)){
            customerEntity.setCusType (CustomerType.FOCUS_CUS);
        }





        // 手机号格式
        if (! PhoneUtils.isMobile (customerEntity.getCusPhone ())) {
            throw new RemoveStackBusinessException ("客户手机号码格式错误");
        }
        //添加修改平台类型客户权限
        UserEntity userInfo = (UserEntity) CurrentContext.getUserInfo();
        if (oldInfo.getCusType ().equals (CustomerType.PLATFORM)){
            if (!(userInfo.getUserLevel().equals(UserLevel.K0))) {
                throw new BusinessException("平台类型客户,需要K0等级修改,您的等级为:("+userInfo.getUserLevel()+")");

            }
            //平台客户不修改客户号码
            customerEntity.setCusPhone(null);
        }

        //添加修改集中获客类型客户权限
        if (oldInfo.getCusType ().equals (CustomerType.FOCUS_CUS)){
            //检查是否有权限查看号码
            Boolean flag = checkCusPermissions (CustomerCheckType.CHECK_CUSTOMER_PHONE_CAN_SEE, customerEntity, userInfo);
            if (!(flag)) {
                throw new BusinessException(flag+"-集中获客类型客户,只允许(可以看到客户号码的用户)才能修改,您可看这个集中获客状态为:(不可看)");

            }
            //集中获客客户暂不修改客户号码
            customerEntity.setCusPhone(null);
        }

        // 更新更多联系人
        customerContactsManager.update (customerEntity.getCustomerContactsEntityList (), customerEntity.getCusCode ());
        // 更新区域信息
        customerAreaManager.update (customerEntity.getCustomerAreaEntityList (), customerEntity.getCusCode ());
        // 更新关联的文件
        fileRelationManager.update (customerEntity.getFileRelationEntityList (), customerEntity.getCusCode (), customerEntity.getCusCode (), customerEntity.getCusCode (), customerEntity.getEmpCode ());

        Date date = new Date ();
        customerEntity.setLastUpdateTime (date);
        customerEntity.setLastUpdateCode (CurrentContext.getUserCode ());
        return customerDbDao.update (customerEntity);
    }


    @Override
    public CustomerEntity findOne (Map<String, Object> map) {
        CustomerEntity customerEntity = customerDbDao.selectUniqueByMap (map);
        map.clear ();
        // 联系人
        map.put ("cusCode", customerEntity.getCusCode ());
        List<CustomerContactsEntity> customerContactsEntityList = customerContactsManager.findByMap (map);
        customerEntity.setCustomerContactsEntityList (customerContactsEntityList);
        // 需求区域
        List<CustomerAreaEntity> customerAreaEntityList = customerAreaManager.findByMap (map);
        customerEntity.setCustomerAreaEntityList (customerAreaEntityList);
        // 关联文件
        map.clear ();
        map.put ("ownerCode", customerEntity.getCusCode ());
        List<FileRelationEntity> fileRelationEntityList = fileRelationManager.findByMap (map);
        customerEntity.setFileRelationEntityList (fileRelationEntityList);
        return customerEntity;
    }

    @Override
    public CustomerEntity detail (String cusCode) {
        //开始时间
        long starts = System.currentTimeMillis ();
        CustomerEntity customerEntity = customerDbDao.detail (cusCode);

        //查看申请人
        loadApplyInfo (customerEntity);

        //1、设置收藏
        Map param = new HashMap ();
        param.put ("empCode", CurrentContext.getUserCode ());
        param.put ("cusCode", cusCode);
        List<CustomerFavoriteEntity> list = customerFavoriteManager.findByMap (param);
        if (ListUtils.isNotEmpty (list)) {
            customerEntity.setHasCollect (true);
        } else {
            customerEntity.setHasCollect (false);
        }

        //3、所有的客户都可以跟进
        customerEntity.setHasCanFollowup (true);
        long costTime = System.currentTimeMillis () - starts;
        System.out.println ("cost " + costTime + "ms");
        return customerEntity;
    }

    private void loadApplyInfo (CustomerEntity customerEntity) {

        CustomerEntity customer = customerEntity;
        if (customer.getApplyUsers () == null || customer.getApplyUsers ().size () < 1) {
            Map<String, Object> param = new HashMap<> ();
            param.put ("cusCode", customer.getCusCode ());
            param.put ("isDeleted", Boolean.FALSE);
            PageResult<CustomerApplyEntity> applies = customerApplyDbDao.selectByMap (param, 0, 10, "id", false);

            setApplyUser (customer, applies);
        }
    }

    /**
     * 将申请查看过当前客户的用户设置到申请人集合中
     *
     * @param customerEntity 客户对象
     * @param applies        客户申请信息集合
     */
    private void setApplyUser (CustomerEntity customerEntity, PageResult<CustomerApplyEntity> applies) {
        List<UserEntity> users = new ArrayList<> ();

        if (applies.getTotalCount () > 0) {
            for (CustomerApplyEntity ap : applies.getData ()) {
                UserEntity tmp = userManager.findUserByUserCode (ap.getEmpCode ());
                if (tmp != null) {
                    UserEntity user = new UserEntity ();
                    user.setUserCode (tmp.getUserCode ());
                    user.setUserName (tmp.getUserName ());
                    user.setUserPhone (tmp.getUserPhone ());
                    user.setUserImg (tmp.getUserImg ());
                    users.add (user);
                }
            }
        }
        customerEntity.setApplyUsers (users);
    }


    @Override
    public Map loadCurrentUserAndOwnerUser (String curCode, String ownerCode) {
        Map map = new HashMap ();
        UserEntity currentUser = userManager.findUserByUserCode (curCode);
        UserEntity ownerUser = userManager.findUserByUserCode (ownerCode);
        map.put ("currentUser", currentUser);
        map.put ("ownerUser", ownerUser);
        return map;
    }

    @Override
    public String findOwnerNameByCusCode (String cusCode) {
        String preSql = "SELECT us.user_name as emp_name FROM \n" + "t_hkp_sys_user_user us LEFT JOIN\n"
                + "t_hkp_crm_customer cus ON us.user_code=cus.emp_code\n" + "WHERE \n" + "cus.cus_code='%s'";
        CustomerEntity entity = customerDbDao.selectOneByNativeSql (String.format (preSql, cusCode));
        return entity == null ? "无名氏" : entity.getEmpName ();
    }

    /**
     * 平台客户拉私的方法
     *
     * @param cusCode
     */
    @Override
    public String pullPrivate (String cusCode, String reason) {
        String message = "";

        Boolean check = checkCustomerCanHiddenOrPrivate (cusCode, CustomerCheckType.CHECK_CREATE_PULL_PRIVATE);
        if (check) {
            customerApplyHidOrPriManager.apply (cusCode, CustomerApplyHidOrPriType.PRIVATE, reason);
            message = "申请成功!";
        } else {
            UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
            //配置的部门内的员工跳过拉私判断
            CusServiceDto cusServiceConfig = SystemConfig.create ().getObject ("cus_service_config", CusServiceDto.class);
            List<String> cusDeptCodes = cusServiceConfig.getDeptCodes ();

            Integer todayPullPrivateCount = 0;
            if (! cusDeptCodes.contains (cur.getOwnerDeptCode ())) {
                //拉私总数量超过6次不可以再次拉私,每天拉私数量超过3人不能再拉私
                todayPullPrivateCount = checkPullPrivateByHadPullPrivateCount (cusCode);
            }

            //不是平台客户不能拉私
            CustomerEntity customerEntity = this.findOne ("cusCode", cusCode);
            // 仅限平台客户可以拉私
            if (! CustomerType.PLATFORM.equals (customerEntity.getCusType ()) && ! CustomerType.FOCUS_CUS.equals (customerEntity.getCusType ())) {
                throw new RemoveStackBusinessException ("该客户不是平台客户");
            }
            customerEntity.setOpenFlag (false);
            customerEntity.setEmpCode (CurrentContext.getUserCode ());
            customerEntity.setCusType (CustomerType.PRIVATE);
            customerEntity.setLastUpdateTime (new Date ());
            int isPullPrivate = customerDbDao.pullPrivate (customerEntity);
            if (isPullPrivate > 0) {
                todayPullPrivateCount = todayPullPrivateCount + 1;
                if (! cusDeptCodes.contains (cur.getOwnerDeptCode ())) {
                    message = "最多可拉私六位平台客户,单日不超过3个，您今天已拉私" + todayPullPrivateCount + "位!";
                } else {
                    message = "拉私成功!";
                }
            } else {
                throw new RemoveStackBusinessException ("拉私失败,请联系it进行处理");
            }
        }
        return message;
    }

    public Boolean checkCustomerCanHiddenOrPrivate (String cusCode, String checkCode) {
        Boolean check = false;
        CustomerEntity cus = customerDbDao.selectUniqueByProp ("cusCode", cusCode);
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        CustomerEntity ce = detail (cus.getCusCode ());
        check = checkCusPermissions (checkCode, ce, cur);
        return check;
    }

    private int checkPullPrivateByHadPullPrivateCount (String cusCode) {
        Map<String, Object> param = new HashMap ();
        param.put ("empCode", CurrentContext.getUserCode ());
        param.put ("cusType", CustomerType.PRIVATE);
        //每天只能够拉私三个客户
        int count = checkTodayPullPrivateCus (param);
        int ct = customerDbDao.countByMap (param);
        if (ct >= 6) {
            throw new RemoveStackBusinessException ("最多可以拉私六位客户,不可以再拉私");
        }
        return count;
    }

    /**
     * 当天拉私客户个数限制检查
     *
     * @param param
     * @return
     */
    private int checkTodayPullPrivateCus (Map<String, Object> param) {
        param.put ("createTimeStart", DateFormatUtil.dayBegin (new Date ()));
        param.put ("createTimeEnd", DateFormatUtil.dayEnd (new Date ()));
        String preSql = "select count(*) " + "from t_hkp_crm_customer where emp_code=\'%s\' " + "and cus_type=\'%s\' " + "and last_update_time>=\'%s\' " + "and last_update_time<=\'%s\'";
        String sql = String.format (preSql, param.get ("empCode"), param.get ("cusType"), param.get ("createTimeStart"), param.get ("createTimeEnd"));
        int count = customerDbDao.countByNativeSql (sql);
        if (count >= 3) {
            throw new RemoveStackBusinessException ("最多可拉私六位平台客户,单日不超过3个，您今天已拉私" + count + "位!,不能再进行拉私操作");
        }
        return count;
    }

    /**
     * 客户拉私释放
     *
     * @param cusCode
     */
    @Override
    public void releasePrivate (String cusCode) {
        CustomerEntity customerEntity = this.findOne ("cusCode", cusCode);
        // 仅限平台拉私客户可以释放
        if (! CustomerType.PRIVATE.equals (customerEntity.getCusType ())) {
            throw new RemoveStackBusinessException ("该客户不是平台拉私客户,无需释放!");
        }
        customerEntity.setEmpCode (PLATFORM);
        customerEntity.setOpenFlag (true);
        customerEntity.setCusType (CustomerType.PLATFORM);
        customerEntity.setLastUpdateTime (new Date ());
        customerDbDao.releasePrivate (customerEntity);
    }

    @Override
    public List<CustomerEntity> checkCustomerPhone (String cusPhone) {
        // 手机号是否存在
        List<CustomerEntity> oldCus = findSimpleCustomer (cusPhone);
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        List<CustomerEntity> result = new ArrayList<> ();
        //客服查看的时候弹出个人获客重复录入的客户信息，客户新增的时候还是可以新增
        for (CustomerEntity cus : oldCus) {
            CustomerEntity ce = detail (cus.getCusCode ());
            if (cur.getEnterType ().equals ("1")) {
                result.add (cus);
            }else if (! checkCusPermissions (CustomerCheckType.CHECK_CREATE_CUS, ce, cur)){
                result.add (cus);
            }
        }
        return result;
    }

    @Override
    public List<CustomerEntity> seeHouseCusList (Map param) {
        String preCusSql = "SELECT\n" + "\t*\n" + "FROM\n" + "\t\tt_hkp_crm_customer cus\n" + "WHERE\n" + "\tEXISTS (\n" + "\t\tSELECT\n" + "\t\t\tcusfol.cus_code\n" + "\t\tFROM\n" + "\t\t\tt_hkp_crm_customer_followup cusfol\n" + "\t\tWHERE\n" + "\t\t\tEXISTS (\n" + "\t\t\t\tSELECT\n" + "\t\t\t\t\t\tfolhos.followup_code\n" + "\t\t\t\tFROM\n" + "\t\t\t\t\tt_hkp_hos_house hos\n" + "\t\t\t\tINNER JOIN t_hkp_crm_followup_house folhos ON folhos.house_code = hos.house_code\n" + "\t\t\t\tWHERE\n" + "\t\t\t\thos.house_code = \'%s\'\n" + "\t\t\t\tAND folhos.followup_code=cusfol.followup_code\n" + "\t\t\t)\n" + "\t\tAND cusfol.cus_code=cus.cus_code\n" + "\t)";
        String cusSql = String.format (preCusSql, param.get ("houseCode"));
        return this.customerDbDao.selectByNativeSql (cusSql);
    }

    @Override
    public List<CustomerEntity> queryCustomerListByUnfollow (Map param) {
        return customerDbDao.queryCustomerListByUnfollow (param);
    }

    @Override
    public String downShelf (CustomerEntity customerEntity) {
        if (StringUtils.isEmpty (customerEntity.getDownShelfReason ())) {
            throw new RemoveStackBusinessException ("客户下架请先填写下架原因!");
        }
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        // 查询客户
        CustomerEntity c = customerDbDao.selectUniqueByProp ("cusCode", customerEntity.getCusCode ());
        // 判断操作人是否为本人
        if (cur.getUserCode ().equals (c.getEmpCode ()) || (cur.getUserLevel ().equals (UserLevel.K0) && c.getCusType ().equals (CustomerType.PLATFORM)) || cur.getEnterType ().equals ("1")) {

            // 更改提醒下架
            Map<String, Object> param = new HashMap<> ();
            param.put ("ownerCode", c.getCusCode ());
            param.put ("hasOperate", false);
            RemindDownEntity rd = remindDownManager.findOne (param);
            if (rd != null) {
                rd.setHasOperate (true);
                rd.setLastUpdateCode (CurrentContext.getUserCode ());
                rd.setLastUpdateTime (new Date ());
                remindDownManager.update (rd);
            }

            //当前操作人是客户的所属业务员才能下架
            CustomerEntity customer = setCusUpDownShelfInfo (customerEntity, CustomerStatus.HASDEAL);
            // 合同到期时间
            customer.setContractEndTime (customer.getContractEndTime ());
            //新增下架记录
            this.setCusUpDown (customerEntity, CustomerStatus.HASDEAL);

            if (customer.getCusType ().equals (CustomerType.PLATFORM) || customer.getCusType ().equals (CustomerType.FOCUS_CUS) || customer.getCusType ().equals (CustomerType.PRIVATE)) {
                customer.setEmpCode (PLATFORM);
                customer.setCusType (CustomerType.PLATFORM);
            }
            customerDbDao.upDownShelf (customer);
            this.onOpenFlag (customer.getCusCode ());

            return "下架成功！";
        } else {
            // 发送下架提醒
            RemindDownEntity rd = new RemindDownEntity ();
            rd.setUserCode (c.getEmpCode ());
            rd.setOwnerCode (c.getCusCode ());
            rd.setOwnerName (c.getCusName ());
            rd.setDownCause (customerEntity.getDownShelfReason ());
            rd.setLeaseExpiration (customerEntity.getContractEndTime ());
            rd.setDownType (DownType.CUS);
            remindDownManager.create (rd);
            return "已经提醒持有者下架！";
        }
    }

    @Override
    public PageResult<CustomerUpdownEntity> upDownShelfQuery (Map<String, Object> map, int start, int pageSize, String orderBy, boolean isAsc) {
        return this.customerUpdownDbDao.selectByMap (map, start, pageSize, orderBy, isAsc);
    }


    @Override
    public void upShelf (CustomerEntity customerEntity) {
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        CustomerEntity customer = setCusUpDownShelfInfo (customerEntity, CustomerStatus.FOLLOWING);

        if (customer.getCusType ().equals (CustomerType.PLATFORM)) {
            //如果是客服上架则当前客户归为平台客户
            if (cur.getEnterType ().equals ("1")) {
                customer.setEmpCode (PLATFORM);
                customer.setCusType (CustomerType.PLATFORM);
            } else {
                customer.setEmpCode (CurrentContext.getUserCode ());
                customer.setCusType (CustomerType.PRIVATE);
            }
        }

        customerDbDao.upDownShelf (customer);
        //新增上架记录
        this.setCusUpDown (customer, CustomerStatus.FOLLOWING);

    }

    @Override
    public void cusServiceUpshelf (CustomerEntity customerEntity) {
        CustomerEntity customer = findOne ("cusCode", customerEntity.getCusCode ());
        if (customer.getCusType ().equals (CustomerType.FOCUS_CUS)) {
            throw new RemoveStackBusinessException ("集中获客类型的客户不能被重复做集中获客操作！");
        }
        //集中获客校验
        if (customerEntity.getNoticeUsers () != null && customerEntity.getNoticeUsers ().size () > 0) {
            FocusCusConfig focusCusConfig = SystemConfig.create ().getObject ("focus_cus_config", FocusCusConfig.class);
            if (focusCusConfig.getFocusCusAllCount () != null && customerEntity.getNoticeUsers ().size () > focusCusConfig.getFocusCusAllCount ()) {
                throw new RemoveStackBusinessException ("集中获客推送人数总数为" + focusCusConfig.getFocusCusAllCount () + "个您所选的人数超过了限制！");
            }
            if (focusCusConfig.getFocusCusAllCount () > customerEntity.getNoticeUsers ().size () && (customerEntity.getNoticeDepts () == null || customerEntity.getNoticeDepts ().size () == 0)) {
                throw new RemoveStackBusinessException ("您选择的指定推送人不足" + focusCusConfig.getFocusCusAllCount () + "个请选择部门");
            }

        } else if (customerEntity.getNoticeDepts () == null || customerEntity.getNoticeDepts ().size () == 0) {
            throw new RemoveStackBusinessException ("集中获客必须选择推送部门!");
        }

        customer.setCusStatus (CustomerStatus.FOLLOWING);
        customer.setLastUpshelfTime (new Date ());
        customer.setCusUpUser (CurrentContext.getUserCode ());
        customer.setCusUpUserName (CurrentContext.getUserInfo ().getUserName ());
        customer.setLastUpdateCode (CurrentContext.getUserCode ());
        customer.setEmpCode (PLATFORM);
        customer.setCusType (CustomerType.FOCUS_CUS);
        customerDbDao.upDownShelf (customer);
        //新增上架记录
        this.setCusUpDown (customer, CustomerStatus.FOLLOWING);
    }

    private CustomerEntity setCusUpDownShelfInfo (CustomerEntity customerEntity, String upDownShelf) {
        CustomerEntity customer = new CustomerEntity ();
        customer = findOne ("cusCode", customerEntity.getCusCode ());

        if (upDownShelf.equals (CustomerStatus.FOLLOWING)) {
            if (customer.getCusStatus ().equals (CustomerStatus.FOLLOWING)) {
                throw new RemoveStackBusinessException ("跟进中的客户不能被重复上架");
            }

            customer.setCusStatus (CustomerStatus.FOLLOWING);
            customer.setLastUpshelfTime (new Date ());
            customer.setCusUpUser (CurrentContext.getUserCode ());

            customer.setEmpCode (CurrentContext.getUserCode ());
            customer.setCusUpUserName (CurrentContext.getUserInfo ().getUserName ());


        } else {
            customer.setCusStatus (CustomerStatus.HASDEAL);
            customer.setDownShelfReason (customerEntity.getDownShelfReason ());
            customer.setLastDownshelfTime (new Date ());
            customer.setCusDownUser (CurrentContext.getUserCode ());
            customer.setCusDownUserName (CurrentContext.getUserInfo ().getUserName ());
            //TODO 增加合同周期
            if (customerEntity.getContractStartTime () != null) {
                customer.setContractStartTime (customerEntity.getContractStartTime ());
            }
            if (customerEntity.getContractEndTime () != null) {
                customer.setContractEndTime (customerEntity.getContractEndTime ());
            }
        }
        customer.setLastUpdateCode (CurrentContext.getUserCode ());
        return customer;
    }

    private void setCusUpDown (CustomerEntity customerEntity, String upDownShelf) {
        CustomerUpdownEntity customerUpdown = new CustomerUpdownEntity ();
        CustomerEntity customer = findOne ("cusCode", customerEntity.getCusCode ());
        if (upDownShelf.equals (CustomerStatus.HASDEAL)) {
            customerUpdown.setCusDownType ("下架");
            customerUpdown.setCusDownReason (customerEntity.getDownShelfReason ());

            if (customerEntity.getContractStartTime () != null) {
                customerEntity.setContractStartTime (customerEntity.getContractStartTime ());
            }

            if (customerEntity.getContractEndTime () != null) {
                customerEntity.setContractEndTime (customerEntity.getContractEndTime ());
            }

        } else {
            customerUpdown.setCusDownType ("上架");
        }
        OperateFillUtils.fill (customerUpdown);
        customerUpdown.setCusCode (customerEntity.getCusCode ());
        customerUpdown.setEmpCode (CurrentContext.getUserCode ());
        customerUpdown.setEmpName (CurrentContext.getUserInfo ().getUserName ());
        customerUpdown.setCusEmpCode (customer.getEmpCode ());
        customerUpdown.setCusEmpName (userManager.selectUniqueByProp (customer.getEmpCode ()).getUserName ());
        customerUpdownDbDao.insert (customerUpdown);
    }

    @Override
    public void onOpenFlag (String cusCode) {
        CustomerEntity customer = new CustomerEntity ();
        customer.setCusCode (cusCode);
        customer.setOpenFlag (true);
        customerDbDao.onAndOffOpenFlag (customer);
    }

    @Override
    public void offOpenFlag (String cusCode, String reason) {
        Boolean check = checkCustomerCanHiddenOrPrivate (cusCode, CustomerCheckType.CHECK_OFF_OPEN_FLAG);
        if (check) {
            customerApplyHidOrPriManager.apply (cusCode, CustomerApplyHidOrPriType.HIDDEN, reason);
        } else {
            CustomerEntity customer = new CustomerEntity ();
            customer.setCusCode (cusCode);
            customer.setOpenFlag (false);
            if (! checkCusPermissions (CustomerCheckType.CHECK_CUSTOMER_OFF_OPEN_FLAG, customer, (UserEntity) CurrentContext.getUserInfo ())) {
                throw new RemoveStackBusinessException ("您的隐藏客户已经达到" + systemConfig.getValueInt ("hkp_cus_hide_count") + "个，已经不能再进行隐藏操作!");
            }
            customerDbDao.onAndOffOpenFlag (customer);
        }
    }

    @Override
    public Boolean checkCusPermissions (String checkCode, CustomerEntity customer, UserEntity cur) {
        Boolean checkPermission = false;

        String userLevel = cur.getUserLevel ();
        switch (userLevel) {

            case UserLevel.K0:
                checkPermission = k0CheckManager.checkCusPermissions (checkCode, customer, cur);
                break;

            case UserLevel.K1:
                checkPermission = k1CheckManager.checkCusPermissions (checkCode, customer, cur);
                break;

            case UserLevel.K2:
                checkPermission = k2CheckManager.checkCusPermissions (checkCode, customer, cur);
                break;

            case UserLevel.T0:
                checkPermission = t0CheckManager.checkCusPermissions (checkCode, customer, cur);
                break;
        }

        return checkPermission;
    }

    @Override
    public PageResult<CustomerEntity> selectContractCensus (Map map, Integer start, Integer pageSize) {
        return this.customerDbDao.selectContractCensus (map, start, pageSize);
    }

    @Override
    public PageResult<CustomerEntity> selectHouseMatchingCus (Map map, Integer start, Integer pageSize, String orderBy, boolean isAse) {
        return this.customerDbDao.selectHouseMatchingCus (map, start, pageSize, orderBy, isAse);
    }


    @Override
    public String checkCallCusPhone (String cusCode) {
        CustomerEntity customerEntity = detail (cusCode);
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();

        if (! cur.getEnterType ().equals ("1") && ! cur.getUserCode ().equals (customerEntity.getEmpCode ())) {

            //检查是否有权限查看号码
            Boolean flag = checkCusPermissions (CustomerCheckType.CHECK_CUSTOMER_PHONE_CAN_SEE, customerEntity, cur);

            if (flag) {
                //不是集中获客类型才判断客户电话号码拨打次数
                if (! customerEntity.getCusType ().equals (CustomerType.FOCUS_CUS)) {
                    checkCusPermissions (CustomerCheckType.CHECK_CUS_CALL_PHONE_NUM, customerEntity, cur);
                }
            } else {
                throw new RemoveStackBusinessException ("您没有权限拨打号码！" + "\n原因一: 已隐藏，表示客户经纪人隐藏了客户号码; " + "\n原因二: 已公开，仍不可拨打，因您与该客户所属经纪人非共享部门同事；");
            }
        }
        if (! cur.getEnterType ().equals ("1")){
            //拨打人数限制
            checkCusPermissions (CustomerCheckType.CHECK_CUSTOMER_CALL_PHONE_PEOPLE_COUNT, customerEntity, cur);
            //拨打时间间隔限制
            checkCusPermissions (CustomerCheckType.CHECK_CUSTOMER_CALL_PHONE_TIME_INTERVAL, customerEntity, cur);
        }

        return customerEntity.getCusPhone ();
    }

    @Override
    public int selectUserPrivateCustomeCount (String userCode) {
        Map<String, Object> map = new HashMap<String, Object> ();
        map.put ("empCode", userCode);
        map.put ("cusType", CustomerType.PRIVATE);
        return customerDbDao.selectCountByMap (map);
    }

    @Override
    public int updateSimple (CustomerEntity entity) {
            // 更新区域信息
        entity.setLastUpdateCode (CurrentContext.getUserCode ());
        entity.setLastFollowupTime (new Date ());
        entity.setLastUpdateTime (new Date ());
        customerAreaManager.update (entity.getCustomerAreaEntityList (), entity.getCusCode ());
        return customerDbDao.update (entity);
    }


}

package com.kfwy.hkp.controller.crm.customer;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.base.*;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.common.enums.OperationType;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.RedisLock;
import com.kfwy.hkp.controller.crm.customer.handler.CustomerQueryHandler;
import com.kfwy.hkp.controller.crm.customer.vo.*;
import com.kfwy.hkp.crm.customer.business.*;
import com.kfwy.hkp.crm.customer.dao.IRonglianLittlePhoneNoteDbDao;
import com.kfwy.hkp.crm.customer.dao.IRonglianPhoneNoteDbDao;
import com.kfwy.hkp.crm.customer.dto.CustomerFocusCusDto;
import com.kfwy.hkp.crm.customer.dto.FocusCusDto;
import com.kfwy.hkp.crm.customer.entity.*;
import com.kfwy.hkp.crm.customer.enums.CustomerCheckType;
import com.kfwy.hkp.crm.customer.enums.CustomerFollowupType;
import com.kfwy.hkp.crm.customer.enums.CustomerQueryType;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.hos.house.api.BespeakApi.BespeakApiClient;
import com.kfwy.hkp.hos.house.api.EntrustApi.EntrustApiClient;
import com.kfwy.hkp.hos.house.api.RecommendApi.RecommendApiClient;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.log.annotation.OperLog;
import com.kfwy.hkp.log.manager.IOperationManager;
import com.kfwy.hkp.sys.user.business.IPortUserManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.PortUserEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import com.kfwy.hkp.sys.user.enums.UserType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api (value = "客户功能操作接口", description = "客户功能操作接口")
@RestController
@RequestMapping (path = "/customer")
public class CustomerService extends SpringRestService {
    private static final int TIMEOUT = 5 * 1000; //超时时间 5s

    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IHouseManager houseManager;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IOperationManager operationManager;
    @Autowired
    private ICustomerUpdownMager customerUpdownManager;
    @Autowired
    private ICheckPermissionManager checkPermissionManager;
    @Autowired
    private ICustomerFollowupManager customerFollowupManager;
    @Autowired
    private CustomerQueryHandler customerQueryHandler;
    @Autowired
    private IFocusCusManager focusCusManager;
    @Autowired
    private RedisLock redisLock;

    @Autowired
    private ICustomerWaitAddManager customerWaitAddManager;
    @Autowired
    private IRonglianPhoneNoteManager ronglianPhoneNoteManager;
    @Autowired
    private IRonglianLittlePhoneNoteManager ronglianLittlePhoneNoteManager;
    @Autowired
    private IPortUserManager portUserManager;

    /**
     * 创建客户
     *
     * @param request
     * @return
     */
    @ApiOperation (value = "创建客户信息", notes = "创建客户信息，" + "在entity中加入客户信息字段,新增客户成功后，" + "根据客户来源（1、官网预约、2、官网委托、3、兼职转介绍）" + "更新erp数据库中的状态")
    @RequestMapping (path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create (@RequestBody CustomerServiceRequest request) {


        CustomerServiceResponse response = new CustomerServiceResponse ();
        CustomerVo customer = request.getEntity ();

        String curUserCode = CurrentContext.getUserCode ();

        if (curUserCode == null) {
            throw new RemoveStackBusinessException ("用户信息为空,请重新登陆." + curUserCode);
        }
        //集中获客校验
        if (customer.getNoticeUsers () != null && customer.getNoticeUsers ().size () > 0) {
            FocusCusConfig focusCusConfig = SystemConfig.create ().getObject ("focus_cus_config", FocusCusConfig.class);
            if (focusCusConfig.getFocusCusAllCount () > customer.getNoticeUsers ().size () && (customer.getNoticeDepts () == null || customer.getNoticeDepts ().size () == 0)) {
                throw new RemoveStackBusinessException ("您选择的指定推送人不足" + focusCusConfig.getFocusCusAllCount () + "个请选择部门");
            }

        } else if (CustomerType.PLATFORM.equals (customer.getCusType ()) && (customer.getNoticeDepts () == null || customer.getNoticeDepts ().size () == 0)) {
            throw new RemoveStackBusinessException ("集中获客必须选择推送部门!");
        }

        //加锁
        long time = System.currentTimeMillis () + TIMEOUT;
        if (! redisLock.lock ("ky:hkp:cus:" + customer.getCusPhone (), String.valueOf (time))) { //如果返回
            throw new RemoveStackBusinessException ("客户信息新增中，请不要重复提交~");
        }

        String cusCode = CodeGenUtils.generate ();
        customer.setCusCode (cusCode);

        int i = customerManager.create (customer);
        //erp推荐预约委托更新
        updateProcessed (i, customer);
        response.setResult (cusCode);
        //解锁
        redisLock.unlock ("ky:hkp:cus:" + customer.getCusPhone (), String.valueOf (time));
        return this.success (response);
    }

    /**
     * 进入编辑页面
     *
     * @param request
     * @return
     */
    @ApiOperation (value = "进入客户信息编辑页面", notes = "进入客户信息编辑页面，通过entity传入客户编码，去数据库查询客户对象")
    @RequestMapping (path = "/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> findOne (@RequestBody CustomerServiceRequest request) {

        CustomerServiceResponse response = new CustomerServiceResponse ();

        Map param = new HashMap<String, Object> ();
        param.put ("cusCode", request.getEntity ().getCusCode ());
        CustomerEntity customerEntity = customerManager.findOne (param);

        response.setResult (customerEntity);
        return this.success (response);
    }

    /**
     * 强制跟进修改客户信息
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/editCus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> editCus (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        CustomerVo entity = request.getEntity ();
        if (entity == null) {
            throw new RemoveStackBusinessException ("参数为空");
        }
        if (entity.getCusCode () == null || entity.getCusCode () == "") {
            throw new RemoveStackBusinessException ("客户编码为空");
        }

        try {
            customerManager.updateSimple (entity);
        } catch (Exception e) {
            throw new RemoveStackBusinessException ("修改客户信息失败！");
        }

        return this.success (response);
    }


    /**
     * 更新客户
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update (@RequestBody CustomerServiceRequest request) {

        CustomerServiceResponse response = new CustomerServiceResponse ();

        customerManager.update (request.getEntity ());

        return this.success (response);
    }


    /**
     * @return
     * @Description 多条件查询客户, 搜索框查询
     * @Param request
     */
    @ApiOperation (value = "根据条件查询客户列表", notes = "根据条件keyword,查询客户分页集合信息")
    @RequestMapping (path = "/query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query (@RequestBody CustomerServiceRequest request) throws IllegalAccessException {
        CustomerServiceResponse response = new CustomerServiceResponse ();

        UserEntity user = (UserEntity) CurrentContext.getUserInfo ();

        PageResult<CustomerEntity> result = null;

        if (CustomerQueryType.DEPT.equals (request.getEntity ().getQueryType ()) && UserType.Individual.equals (user.getUserType ())) {
            result = new PageResult<CustomerEntity> ();
        } else {
            result = customerPageQuery (request);
        }

        //客户集合未跟进天数添加
        cusListNotFolDay (result.getData ());
        response.setResult (result);
        return this.success (response);
    }


    /**
     * erp推荐预约委托更新
     *
     * @param
     * @param i
     * @param entity
     */
    private void updateProcessed (int i, CustomerVo entity) {
        if (i > 0 && entity.getCusFrom ().equals ("官网预约") && entity.getBespeakId () != null) {
            BespeakApiClient.updateProcessed (entity.getBespeakId ());
        } else if (i > 0 && entity.getCusFrom ().equals ("官网预约") && entity.getEntrustId () != null) {
            EntrustApiClient.updateProcessed (entity.getEntrustId ());
        } else if (i > 0 && entity.getCusFrom ().equals ("兼职推荐") && entity.getRecommendId () != null) {
            RecommendApiClient.updateProcessed (entity.getRecommendId (), entity.getCusCode ());
        }
    }

    /**
     * 客户分页查询
     *
     * @param request
     * @return
     */
    private PageResult<CustomerEntity> customerPageQuery (CustomerServiceRequest request) throws IllegalAccessException {
        Map param = customerQueryHandler.queryMap (request.getEntity ());
        //模糊搜索条件封装
        keyWordCondition (request, param);
/*        UserEntity user = (UserEntity) CurrentContext.getUserInfo ();
        if (user.getUserLevel ().equals (UserLevel.T0)) {
            param.put ("cusType", CustomerType.AGENT);
        }*/
        return customerManager.findByMap (param, request.getStart (), request.getPageSize (), (String) param.get ("orderBy"), (boolean) param.get ("descOrAsc"));
    }

    /**
     * 模糊搜索条件
     *
     * @param request
     * @param param
     */
    private void keyWordCondition (CustomerServiceRequest request, Map param) {
        //增加模糊查询
        if (StringUtils.isNotEmpty (request.getKeyword ())) {
            param.put ("keyword", StringUtils.deleteWhitespace (request.getKeyword ()));
        }
    }

    /**
     * 客户未跟进天数添加
     *
     * @param list
     * @param list
     */
    private void cusListNotFolDay (List<CustomerEntity> list) {
        Date nowDate = new Date ();
        if (CollectionUtil.isNotEmpty (list)) {
            for (CustomerEntity cusOne : list) {
                setCusNotFolDay (nowDate, cusOne);
            }
        }
    }

    private void setCusNotFolDay (Date nowDate, CustomerEntity cusOne) {
        Date lastFollupDate = cusOne.getLastFollowupTime ();
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat ("yyyy-MM-dd");
        long indays = 1234;
        if (lastFollupDate == null) {
            cusOne.setDaysNotFollowup (null);
        } else {
            indays = (nowDate.getTime () - lastFollupDate.getTime ()) / (24 * 60 * 60 * 1000);
            cusOne.setDaysNotFollowup (indays);
        }
    }

    /**
     * @return
     * @Description 查询客户详情
     * @Param request
     */
    @ApiOperation (value = "查询客户详情(Content-Type为application/json,通过客户的code查询客户详情,code放在request.entity.cusCode中)", notes = "查询客户详情(Content-Type为application/json,通过客户的code查询客户详情," + "code放在request.entity.cusCode中)，返回对象数据放在response.result中")
    @RequestMapping (path = "/detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> details (@RequestBody CustomerServiceRequest request) {

        CustomerServiceResponse response = new CustomerServiceResponse ();

        if (userManager.findUserByUserCode (CurrentContext.getUserCode ()) == null) {
            throw new RemoveStackBusinessException ("离职人员不能查看客户详情");
        }

        CustomerEntity customerEntity = request.getEntity ();

        customerEntity = customerManager.detail (customerEntity.getCusCode ());

        /**
         * 如果查看手机号检查为false则设置手机号为null
         */
        customerEntity.setCusPhone (null);

        Date nowDate = new Date ();
        //设置客户未跟进天数
        setCusNotFolDay (nowDate, customerEntity);

        operationManager.save (customerEntity.getCusCode (), OperationType.SEE_CUS_DETAIL, customerEntity.getEmpName ());

        response.setResult (customerEntity);
        return this.success (response);
    }

    /**
     * @return
     * @Description 客户拉私
     * @Param request
     */
    @ApiOperation (value = "客户拉私(Content-Type为application/json,通过客户的code进行拉私,code放在request.entity.cusCode中)", notes = "客户拉私(Content-Type为application/json,通过客户的code进行拉私,code放在request.entity.cusCode中)，拉私成功返回200")
    @RequestMapping (path = "/pullPrivate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> pullPrivate (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        String msg = customerManager.pullPrivate (request.getEntity ().getCusCode (), request.getEntity ().getReason ());
        return this.success (response, msg);
    }

    /**
     * @return
     * @Description 客户拉私释放
     * 平台拉私客户手动释放到平台
     * @Param request
     */
    @ApiOperation (value = "平台拉私客户手动释放到平台(Content-Type为application/json,通过客户的code进行拉私释放,平台拉私客户手动释放到平台,code放在request.entity.cusCode中)，释放成功返回200", notes = "平台拉私客户手动释放到平台(Content-Type为application/json,通过客户的code进行拉私释放,平台拉私客户手动释放到平台,code放在request.entity.cusCode中)，释放成功返回200")
    @RequestMapping (path = "/releasePrivate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> releasePrivate (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        UserEntity user = (UserEntity) CurrentContext.getUserInfo ();
        CustomerEntity customerEntity = request.getEntity ();
        customerManager.checkCusPermissions (CustomerCheckType.CHECK_CUSTOMER_RELEASE, customerEntity, user);
        customerManager.releasePrivate (request.getEntity ().getCusCode ());
        return this.success (response);
    }

    @ApiOperation (value = "客户下架(Content-Type为application/json,通过客户的code进行下架,code放在request.entity.cusCode中)，将客户状态改为2已租好，下架成功返回200", notes = "客户下架(Content-Type为application/json,通过客户的code进行下架,code放在request.entity.cusCode中)，将客户状态改为2已租好，下架成功返回200")
    @RequestMapping (path = "/downShelf", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> downShelf (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        CustomerEntity customerEntity = request.getEntity ();
        String message = customerManager.downShelf (customerEntity);
        return this.success (response, message);
    }

    @ApiOperation (value = "客户上架(Content-Type为application/json,通过客户的code进行上架,code放在request.entity.cusCode中)，将客户状态改为1跟进中，上架成功返回200", notes = "客户上架(Content-Type为application/json,通过客户的code进行上架,code放在request.entity.cusCode中)，将客户状态改为1跟进中，上架成功返回200")
    @RequestMapping (path = "/upShelf", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> upShelf (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        try {
            customerManager.upShelf (request.getEntity ());
        } catch (Exception e) {
            logger.error (e.getCause ());
            throw new RemoveStackBusinessException ("客户上架操作失败!");
        }
        return this.success (response);
    }

    @ApiOperation (value = "查询客户上下架记录", notes = "查询客户上下架记录")
    @RequestMapping (path = "/upDownQuery", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> upDownQuery (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        Map<String, Object> map = new HashMap<> ();
        if (StringUtils.isNotEmpty (request.getEntity ().getCusCode ())) {
            map.put ("cusCode", request.getEntity ().getCusCode ());
        }
        PageResult<CustomerUpdownEntity> result = customerManager.upDownShelfQuery (map, request.getStart (), request.getPageSize (), "create_time", false);
        response.setResult (result);
        return this.success (response);
    }

    @ApiOperation (value = "客户公开", notes = "客户公开")
    @RequestMapping (path = "/onOpenFlag", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> onOpenFlag (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        customerManager.onOpenFlag (request.getEntity ().getCusCode ());
        return this.success (response);
    }

    @ApiOperation (value = "客户信息不公开操作(Content-Type为application/json,通过客户的code进行客户信息不公开操作,code放在request.entity.cusCode中)，将客户状态改为BOOLEAN.FALSE，关闭公开成功返回200", notes = "客户信息不公开操作(Content-Type为application/json,通过客户的code进行客户信息不公开操作,code放在request.entity.cusCode中)，将客户状态改为BOOLEAN.FALSE，关闭公开成功返回200")
    @RequestMapping (path = "/offOpenFlag", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> offOpenFlag (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        customerManager.offOpenFlag (request.getEntity ().getCusCode (), request.getEntity ().getReason ());
        return this.success (response);
    }

    @ApiOperation (value = "当前客户手机号是否可以录入", notes = "当前客户手机号是否可以录入")
    @RequestMapping (path = "/checkCustomerPhone", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> checkCustomerPhone (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        List<CustomerEntity> cusList;
        if (StringUtils.isNotEmpty (request.getCusPhone ())) {
            cusList = customerManager.checkCustomerPhone (request.getCusPhone ());
            if (cusList != null && cusList.size () > 0) {
                response.setResult (cusList);
                response.setMessage ("当前客户不能录入,可能存在如下原因:\n" + "1、您已经录入过这个客户或者您部门内有人录入或者共享部门内有人录入\n" + "2、平台上存在这个客户、或者这个客户被拉私\n" + "3、该客户是集中获客客户，所以您不能重复录入\n" + "4、您与他人合作了这个客户或者他人推送了这个客户给您");
                response.setCode ("415");
                return new ResponseEntity<IServiceResponse> (response, HttpStatus.OK);
            }
        }
        return this.success (response, "当前客户可以录入");
    }

    @ApiOperation (value = "客户看过的房源集合", notes = "客户看过的房源集合")
    /**
     * 客户看过的房源集合
     * @param request
     * @return
     */
    @RequestMapping (path = "/cusSeeHouseList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cusSeeHouseList (@RequestBody CustomerFollowupServiceRequest request) {
        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse ();
        Map param = new HashMap<String, Object> ();
        param.put ("cusCode", request.getEntity ().getCusCode ());
        param.put ("followupType", CustomerFollowupType.SeeHouse);
        List<HouseEntity> houseList = houseManager.selectCusSawHouses (param);
        PageResult<HouseEntity> result = new PageResult<HouseEntity> ();
        result.setData (houseList);
        if (houseList != null) {
            result.setTotalCount (houseList.size ());
        } else {
            result.setTotalCount (0);
        }
        response.setResult (result);
        return this.success (response);
    }

    /**
     * 客户看过的房源集合
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/selectCusSeeHouseList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectCusSeeHouseList (@RequestBody CustomerFollowupServiceRequest request) {
        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse ();
        Map param = new HashMap<String, Object> ();
        param.put ("cusCode", request.getEntity ().getCusCode ());
        PageResult<FollowupHouseEntity> result = customerFollowupManager.selectCusSeeHouse (param, request.getStart (), request.getPageSize ());
        response.setResult (result);
        return this.success (response);
    }

    /**
     * 客户操作权限判断
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/checkCusPermissions", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> checkCusPermissions (@RequestBody CustomerCheckRequest request) {
        CustomerCheckResponse response = new CustomerCheckResponse ();
        //客户操作检查类
        CustomerCheckVo checkVo = request.getEntity ();
        if (StringUtils.isEmpty (checkVo.getCusCode ())) {
            throw new RemoveStackBusinessException ("传入的客户编码为空");
        } else if (StringUtils.isEmpty (checkVo.getCheckCode ())) {
            throw new RemoveStackBusinessException ("传入的权限检查编码为空");
        }
        String cusCode = checkVo.getCusCode ();
        String checkCode = checkVo.getCheckCode ();
        CustomerEntity customer = customerManager.findOne ("cusCode", cusCode);
        //如果是当前客户所属人的部门经理或客服，则跳过以下权限判断
        UserEntity owner = userManager.selectUniqueByProp (customer.getEmpCode ());
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        Boolean isNoPass = true;
        isNoPass = checkPermissionManager.checkNoPass (cur, owner, isNoPass);
        if (isNoPass) {
            checkPermissionManager.checkPermissions ("cus", checkCode, customer, cur);
        }
        return this.success (response);
    }

    /**
     * 客户电话拨打
     *
     * @param request
     * @return
     */
    @OperLog (optType = OperationType.CALL_CUS_PHONE)
    @RequestMapping (path = "/callCusPhone", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> callCusPhone (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();

        String cusCode = request.getEntity ().getCusCode ();

        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        //k0限制,每拨打一次号码都要查看之前拨打的号码是否做了跟进
        if (checkCallPhoneNotFollowup (response, cur)) {
            return new ResponseEntity<IServiceResponse> (response, HttpStatus.OK);
        }
        String ownerName = customerManager.findOwnerNameByCusCode (cusCode);

        String cusPhone = customerManager.checkCallCusPhone (cusCode);
        operationManager.save (cusCode, OperationType.CALL_CUS_PHONE, ownerName);
        response.setResult (cusPhone);
        return this.success (response);
    }

    private boolean checkCallPhoneNotFollowup (CustomerServiceResponse response, UserEntity cur) {
        if (cur.getUserLevel ().equals (UserLevel.K0) && cur.getEnterType () != "1") {
            Map map = MapUtil.newHashMap ();
            map.put ("userCode", cur.getUserCode ());
            String neeFollowupCusCode = operationManager.callPhoneIsHandle (map);
            if (neeFollowupCusCode != null && neeFollowupCusCode != "") {
                response.setMessage ("由于您上次拨打客户电话后未进行跟进，\n所以无法继续进行电话拨打！\n请完成上一次的跟进后继续拨打！");
                response.setResult (neeFollowupCusCode);
                response.setCode ("415");
                return true;
            }
        }
        return false;
    }

    @ApiOperation (value = "根据条件查询客户上下架记录", notes = "根据条件cusCode,查询客户上下架记录分页集合信息")
    @RequestMapping (path = "/cusUpDownLog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cusUpDownLog (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        String cusCode = request.getEntity ().getCusCode ();
        Map<String, Object> map = new HashMap<String, Object> ();
        if (StringUtils.isNotEmpty (cusCode)) {
            map.put ("cusCode", cusCode);
        }
        PageResult<CustomerUpdownEntity> result = customerUpdownManager.findByMap (map, request.getStart (), request.getPageSize (), "create_time", Boolean.FALSE);
        response.setResult (result);
        return this.success (response);
    }

    @RequestMapping (path = "/selectContractCensus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectContractCensus (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        Map<String, Object> map = new HashMap<String, Object> ();

        TimeSplit ts1 = new TimeSplit (null, 2, "MM");
        Date day = DateFormatUtil.getCurrentDateTime ();
        Date afterContract = DateFormatUtil.addDate (day, ts1.getAfterNumber (), ts1.getAfterFormat ());
        map.put ("contractEndTimeEnd", DateFormatUtil.dayBegin (afterContract));
        TimeSplit ts2 = new TimeSplit (SystemConfigKey.Cus_Followup_Census_Time.getCode (), - 7, "DD");
        Date afterFollowup = DateFormatUtil.addDate (day, ts2.getAfterNumber (), ts2.getAfterFormat ());
        map.put ("lastFollowupTime", DateFormatUtil.dayBegin (afterFollowup));

        UserEntity cur = (UserEntity) CurrentContext.getUserInfo ();
        map.put ("isDeleted", Boolean.FALSE);
        if (StringUtils.isNotEmpty (cur.getOwnerDeptCode ())) {
            String leaderCode = deptManager.getDeptEntity (cur.getOwnerDeptCode ()).getLeaderCode ();
            if (leaderCode.equals (CurrentContext.getUserCode ())) {
                List<String> deptCodes = deptManager.getDownDeptCode (cur.getOwnerDeptCode ());
                if (deptCodes != null && deptCodes.size () >= 1) {
                    map.put ("ownerDeptCodes", deptCodes);
                } else {
                    map.put ("ownerDeptCode", cur.getOwnerDeptCode ());
                }
            } else {
                map.put ("ownerDeptCode", cur.getOwnerDeptCode ());
            }
        } else {
            map.put ("empCode", cur.getUserCode ());
        }
        PageResult<CustomerEntity> result = customerManager.selectContractCensus (map, request.getStart (), request.getPageSize ());
        response.setResult (result);
        return this.success (response);
    }

    @IgnoreLog
    @RequestMapping (path = "/queryFocusCus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryFocusCus (@RequestBody FocusCusServiceRequest request) throws ParseException {
        FocusCusServiceResponse response = new FocusCusServiceResponse ();
        Map param = new HashMap (2);

        param.put ("userCode", CurrentContext.getUserCode ());
        param.put ("cusType", CustomerType.FOCUS_CUS);
        param.put ("self_page", true);
        PageResult<CustomerFocusCusDto> result = focusCusManager.findByMap (param, request.getStart (), 200, "last_upshelf_time", false);
        if (result != null && result.getData () != null && result.getData ().size () > 0) {
            setCloseTime (result.getData ());
        }
        response.setResult (result);
        return this.success (response);
    }

    private void setCloseTime (List<? extends CustomerFocusCusDto> result) throws ParseException {
        String focusToPlatformTime = null;
        FocusCusConfig focusCusConfig = SystemConfig.create ().getObject ("focus_cus_config", FocusCusConfig.class);
        if (focusCusConfig != null && focusCusConfig.getFocusToPlatformTime () == null) {
            focusToPlatformTime = focusCusConfig.getFocusToPlatformTime ();
        } else {
            focusToPlatformTime = "48hour";
        }
        String[] hours = focusToPlatformTime.split ("hour");
        String hour1 = hours[0];
        SimpleDateFormat simpleFormat = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        for (CustomerFocusCusDto focusCusDto : result) {
            //由于客户上架也走集中获客，所以时间间隔改成最新上架时间开始的48小时
            Date lastUpshelfTime = focusCusDto.getLastUpshelfTime ();
            Date closeTime = DateUtils.addHours (lastUpshelfTime, Integer.parseInt (hour1));
            String fromDate = simpleFormat.format (new Date ());
            String toDate = simpleFormat.format (closeTime);
            long from = simpleFormat.parse (fromDate).getTime ();
            long to = simpleFormat.parse (toDate).getTime ();
            double hour = (double) ((to - from) / (1000 * 60 * 60));
            if (focusCusDto.getCusType ().equals (CustomerType.FOCUS_CUS)) {
                focusCusDto.setCloseTime (hour > 0 ? hour : 0);
            } else {
                focusCusDto.setCloseTime (0);
            }
        }
    }

    @IgnoreLog
    @RequestMapping (path = "/checkFocusCusNotice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> checkFocusCusNotice () {
        FocusCusServiceResponse response = new FocusCusServiceResponse ();
        Map map = MapUtil.newHashMap ();
        map.put ("userCode", CurrentContext.getUserCode ());
        map.put ("cusType", CustomerType.FOCUS_CUS);
        map.put ("handle", false);
        Integer count = focusCusManager.checkFocusCusNotice (map);
        response.setResult (count);
        return this.success (response);
    }

    @IgnoreLog
    @RequestMapping (path = "/focusCusQuery", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> focusCusQuery (@RequestBody FocusCusServiceRequest request) throws ParseException {
        FocusCusServiceResponse response = new FocusCusServiceResponse ();
        PageResult<FocusCusDto> result = focusCusManager.focusCusQuery (request.getEntity (), request.getStart (), request.getPageSize ());
        if (result != null && result.getData () != null && result.getData ().size () > 0) {
            setCloseTime (result.getData ());
        }
        response.setResult (result);
        return this.success (response);
    }

    @IgnoreLog
    @RequestMapping (path = "/myDeptFocusCusQuery", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> myDeptFocusCusQuery (@RequestBody FocusCusServiceRequest request) throws ParseException {
        FocusCusServiceResponse response = new FocusCusServiceResponse ();
        Map map = MapUtil.newHashMap ();
        map.put ("ownerDeptCode", CurrentContext.getUserInfo ().getOwnerDeptCode ());
        FocusCusVo vo = request.getEntity ();

        if (vo != null) {
            if (vo.getCusPhone () != null) {
                map.put ("cusPhone", vo.getCusPhone ());
            }
            if (vo.getCusName () != null) {
                map.put ("cusName", vo.getCusName ());
            }
            if (vo.getUserName () != null) {
                map.put ("userName", vo.getUserName ());
            }
        }

        PageResult<FocusCusDto> result = focusCusManager.myDeptFocusCusQuery (map, request.getStart (), request.getPageSize ());
        if (result != null && result.getData () != null && result.getData ().size () > 0) {
            setCloseTime (result.getData ());
        }
        response.setResult (result);
        return this.success (response);
    }

    /**
     * 客服上架
     *
     * @param request
     * @return
     * @throws ParseException
     */
    @IgnoreLog
    @RequestMapping (path = "/cusServiceUpshelf", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cusServiceUpshelf (@RequestBody CustomerServiceRequest request) throws ParseException {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        Map map = MapUtil.newHashMap ();

        CustomerEntity customerEntity = request.getEntity ();
        customerManager.cusServiceUpshelf (customerEntity);
        return this.success (response);
    }

    @IgnoreLog
    @RequestMapping (path = "/privateCustomeCount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> privateCustomeCount (@RequestBody CustomerServiceRequest request) throws ParseException {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        int count = customerManager.selectUserPrivateCustomeCount (request.getUserCode ());
        response.setResult (count);
        return this.success (response);
    }

    @IgnoreLog
    @RequestMapping (path = "/cusWaitAddCreate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cusWaitAddCreate (@RequestBody CustomerWaitAddServiceRequest request) {

        CustomerWaitAddServiceResponse response = new CustomerWaitAddServiceResponse ();

        try {
            CustomerWaitAddEntity entity = request.getEntity ();
            customerWaitAddManager.create (entity);
        } catch (RemoveStackBusinessException e) {
            throw new RemoveStackBusinessException (e.getMessage ());
        } catch (Exception e) {
            logger.error ("客户待新增报错:", e);
            throw new RemoveStackBusinessException ("客户待新增失败!");
        }

        return this.success (response);
    }

    @IgnoreLog
    @RequestMapping (path = "/cusWaitAddDelete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cusWaitAddDelete (@RequestBody CustomerWaitAddServiceRequest request) {

        CustomerWaitAddServiceResponse response = new CustomerWaitAddServiceResponse ();

        try {
            CustomerWaitAddEntity entity = request.getEntity ();
            customerWaitAddManager.deleteCusWaitAdd (entity.getCusPhone ());
        } catch (RemoveStackBusinessException e) {
            throw new RemoveStackBusinessException (e.getMessage ());
        } catch (Exception e) {
            logger.error ("客户待新增删除报错:", e);
            throw new RemoveStackBusinessException ("客户待新增删除失败!");
        }

        return this.success (response);
    }

    @RequestMapping (path = "/cusWaitAddQuery", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cusWaitAddQuery (@RequestBody CustomerWaitAddServiceRequest request) {

        CustomerWaitAddServiceResponse response = new CustomerWaitAddServiceResponse ();

        try {
            Map map = new HashMap ();
            map.put ("createCode", CurrentContext.getUserCode ());
            PageResult result = customerWaitAddManager.findByMap (map, request.getStart (), request.getPageSize ());
            response.setResult (result);
        } catch (RemoveStackBusinessException e) {
            throw new RemoveStackBusinessException (e.getMessage ());
        } catch (Exception e) {
            logger.error ("客户待新增报错:", e);
            throw new RemoveStackBusinessException ("客户待新增失败!");
        }

        return this.success (response);
    }

    @RequestMapping (path = "/cusWaitAddDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cusWaitAddDetail (@RequestBody CustomerWaitAddServiceRequest request) {

        CustomerWaitAddServiceResponse response = new CustomerWaitAddServiceResponse ();

        try {
            CustomerWaitAddEntity entity = request.getEntity ();
            CustomerWaitAddEntity result = customerWaitAddManager.detail (entity.getCusPhone ());
            result.setId (null);
            response.setResult (result);
        } catch (RemoveStackBusinessException e) {
            throw new RemoveStackBusinessException (e.getMessage ());
        }
        return this.success (response);
    }

    @RequestMapping (path = "/delete", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> deleteCus (@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        CustomerVo entity = request.getEntity ();

        customerManager.deleteCus (entity.getCusCode ());

        return this.success (response);

    }

    @IgnoreLog
    @RequestMapping (path = "/rongLianPhoneNote",
            method = RequestMethod.GET)
    public ResponseEntity<IServiceResponse> rongLianPhoneNote (@RequestParam(required = false,defaultValue="") String CallNo,
                                                               @RequestParam(required = false,defaultValue="") String CallSheetID,
                                                               @RequestParam(required = false,defaultValue = "") String refCallSheetId,
                                                               @RequestParam(required = false,defaultValue="") String CalledNo,
                                                               @RequestParam(required = false,defaultValue="") String CallID,
                                                               @RequestParam(required = false,defaultValue="") String CallType,
                                                               @RequestParam(required = false,defaultValue="") Date Ring,
                                                               @RequestParam(required = false,defaultValue="") Date RingingDate,
                                                               @RequestParam(required = false,defaultValue="") Date RingingTimestamp,
                                                               @RequestParam(required = false,defaultValue="") Date Begin,
                                                               @RequestParam(required = false,defaultValue="") Date End,
                                                               @RequestParam(required = false,defaultValue="") Date QueueTime,
                                                               @RequestParam(required = false,defaultValue="") String Agent,
                                                               @RequestParam(required = false,defaultValue="") String AgentName,
                                                               @RequestParam(required = false,defaultValue="") String Queue,
                                                               @RequestParam(required = false,defaultValue="") String State,
                                                               @RequestParam(required = false,defaultValue="") String CallState,
                                                               @RequestParam(required = false,defaultValue = "") String ActionID,
                                                               @RequestParam(required = false,defaultValue = "") String WebcallActionID,
                                                               @RequestParam(required = false,defaultValue = "") String RecordFile,
                                                               @RequestParam(required = false,defaultValue = "") String FileServer,
                                                               @RequestParam(required = false,defaultValue = "") String Province,
                                                               @RequestParam(required = false,defaultValue = "") String District,
                                                               @RequestParam(required = false,defaultValue = "") String IVRKEY
                                                               ) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        System.out.println ("通话状态推送接口接收：");
        System.out.println ("-------------------------------------------");
        System.out.println ("被叫"+CalledNo);
        System.out.println ("主叫"+CallNo);
        System.out.println ("转接前通话的CallSheetID"+refCallSheetId);
        System.out.println ("通话记录ID"+CallSheetID);
        System.out.println ("通话ID"+CallID);
        System.out.println ("通话类型：dialout外呼通话,normal普通来电,transfer呼入转接,dialTransfer外呼转接:"+CallType);
        System.out.println ("通话振铃时间"+Ring);
        System.out.println ("被叫振铃开始时间"+RingingDate);
        System.out.println ("被叫振铃开始时间"+RingingTimestamp);
        System.out.println ("通话接通时间（双方开始通话的时间,如果被叫没接听的话为空）"+Begin);
        System.out.println ("通话结束时间"+End);
        System.out.println ("来电进入技能组时间"+QueueTime);
        System.out.println ("处理坐席的工号"+Agent);
        System.out.println ("处理坐席的姓名\n"+AgentName);
        System.out.println ("通话进入的技能组名称\n"+Queue);
        System.out.println ("接听状态：dealing（已接）,notDeal（振铃未接听）,leak（ivr放弃）,queueLeak（排队放弃）,blackList（黑名单）,voicemail（留言）,limit（并发限制）"+State);
        System.out.println ("事件状态：Ring, Ringing, Link, Hangup(Unlink也当成Hangup处理)\n"+CallState);
        System.out.println ("通过调用外呼接口时,该字段会保存请求的actionID,其它情况下该字段为空:"+ActionID);
        System.out.println ("通过调用webcall接口,该字段会保存请求的actionID,其它情况下该字段为空:"+WebcallActionID);
        System.out.println ("通话录音文件名:"+RecordFile);
        System.out.println ("通过FileServer中指定的地址加上RecordFile的值可以获取录音:"+FileServer);
        System.out.println ("目标号码的省:"+Province);
        System.out.println ("目标号码的市:"+District);
        System.out.println ("通话在系统中选择的按键菜单,10004@0。格式为：按键菜单的节点编号@选择的菜单按键。如果为多级菜单则为：10004@0-10005@1。"+IVRKEY);
        System.out.println ("-------------------------------------------");
        RonglianPhoneNoteEntity entity = new RonglianPhoneNoteEntity (CallNo,CalledNo,refCallSheetId,CallSheetID,CallID,CallType,Ring,RingingDate,RingingTimestamp,Begin,End,QueueTime,Agent,Queue,State,CallState,Province,District,IVRKEY);
        ronglianPhoneNoteManager.create (entity);
        return this.success (response);
    }

    @IgnoreLog
    @RequestMapping (path = "/rongLianLittlePhoneNote",
            method = RequestMethod.GET)
    public ResponseEntity<IServiceResponse> rongLianLittlePhoneNote (@RequestParam(required = false,defaultValue="") Date alerting_time,
                                                               @RequestParam(required = false,defaultValue="") String called,
                                                               @RequestParam(required = false,defaultValue = "") String recorder_id,
                                                               @RequestParam(required = false,defaultValue="") String caller_area,
                                                               @RequestParam(required = false,defaultValue="") Date begin_time,
                                                               @RequestParam(required = false,defaultValue="") String called_area,
                                                               @RequestParam(required = false,defaultValue="") String caller,
                                                               @RequestParam(required = false,defaultValue="") String called_show,
                                                               @RequestParam(required = false,defaultValue="") Date connect_time,
                                                               @RequestParam(required = false,defaultValue="") String result,
                                                               @RequestParam(required = false,defaultValue="") Date release_time,
                                                               @RequestParam(required = false,defaultValue="") String account,
                                                               @RequestParam(required = false,defaultValue="") String mappingId,
                                                               @RequestParam(required = false,defaultValue="") String appId,
                                                               @RequestParam(required = false,defaultValue="") String call_duration,
                                                               @RequestParam(required = false,defaultValue="") String userData,
                                                               @RequestParam(required = false,defaultValue="") Date answerTime,
                                                               @RequestParam(required = false,defaultValue="") String smallNumberType,
                                                               @RequestParam(required = false,defaultValue="") String calldisplay,
                                                               @RequestParam(required = false,defaultValue = "") String record_file_url) {
        CustomerServiceResponse response = new CustomerServiceResponse ();
        System.out.println ("-------------------------------------------");
        System.out.println ("小号话单数据推送接口接收：");
        System.out.println ("被叫振铃时间,格式为YYYY-MM-DD HH:mm:ss："+alerting_time);
        System.out.println ("被叫真实号码："+called);
        System.out.println ("企业本次通话唯一标识id："+recorder_id);
        System.out.println ("主叫归属地："+caller_area);
        System.out.println ("主叫拨通虚拟号码时刻 ，格式为YYYY-MM-DD HH:mm:ss："+begin_time);
        System.out.println ("被叫归属地："+called_area);
        System.out.println ("主叫号码："+caller);
        System.out.println ("中间号,小号："+called_show);
        System.out.println ("被叫接通时刻,格式为YYYY-MM-DD HH:mm:ss："+connect_time);
        System.out.println ("通话状态，通话状态的取值请查通话状态区分（0 成功 2无应答 9呼叫失败 11主叫号码与中间号没有关联）："+result);
        System.out.println ("通话结束时刻,格式为YYYY-MM-DD HH:mm:ss："+release_time);
        System.out.println ("帐号编号："+account);
        System.out.println ("绑定关系唯一Id："+mappingId);
        System.out.println ("应用id："+appId);
        System.out.println ("本次通话的时长，单位为秒："+call_duration);
        System.out.println ("用户自定义数据："+userData);
        System.out.println ("接通时间："+answerTime);
        System.out.println ("小号类型："+smallNumberType);
        System.out.println ("0显真实号 1 不显真实号 2强制不显真实号："+calldisplay);
        System.out.println ("通话录音地址:"+record_file_url);
        System.out.println ("-------------------------------------------");
        RonlianPhoneLittleNoteEntity entity = new RonlianPhoneLittleNoteEntity (alerting_time,
                called,
                recorder_id,
                caller_area,
                begin_time,
                called_area,
                caller,
                called_show,
                connect_time,
                result,release_time,
                account,mappingId,appId,call_duration,userData,answerTime,smallNumberType,calldisplay,record_file_url);
        ronglianLittlePhoneNoteManager.create (entity);
        return this.success (response);
    }
    @IgnoreLog
    @RequestMapping (path = "/findPortUserByCaller", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> findPortUserByCaller(@RequestBody CustomerServiceRequest request){
        CustomerServiceResponse response = new CustomerServiceResponse ();
        String cusPhone = request.getCusPhone ();
        if (cusPhone==null || cusPhone==""){
            throw new RemoveStackBusinessException ("请传入正确的客户号码");
        }

        PortUserEntity portUser = portUserManager.findPortUserByLittlePhone (cusPhone);
        if (portUser==null){
            throw new RemoveStackBusinessException ("当前客户号码没有找到端口发布人");
        }

        response.setResult (portUser);
        return this.success (response);

    }

}

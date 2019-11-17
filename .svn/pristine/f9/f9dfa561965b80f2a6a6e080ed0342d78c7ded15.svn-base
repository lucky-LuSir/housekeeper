package com.kfwy.hkp.controller.crm.customer;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerPushServiceRequest;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerPushServiceResponse;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerPushVo;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.business.ICustomerPushManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerPushDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerPushEntity;
import com.kfwy.hkp.crm.customer.enums.CusPushSortType;
import com.kfwy.hkp.crm.customer.enums.CustomerNotFollowupType;
import com.kfwy.hkp.crm.customer.enums.CustomerPushQueryType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/25 17:33
 * 客户推送功能
 */
@RestController
@RequestMapping(path = "/customerPush")
public class CustomerPushService extends SpringRestService {

    @Autowired
    private ICustomerPushManager customerPushManager;
    @Autowired
    private ICustomerPushDbDao customerPushDbDao;
    @Autowired
    private ICustomerManager customerManager;

    /**
     * 新增推送，其中pushType（推送类型）是必填，Personal是个人，Department是部门
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerPushServiceRequest request) {

        CustomerPushServiceResponse response = new CustomerPushServiceResponse();

        if (request.getPushEntities() == null || request.getPushEntities().size() < 1) {
            return this.error(response, "推送新增的参数为空");
        }

        customerPushManager.batchCreate(request.getPushEntities());

        return this.success(response);
    }

    @RequestMapping(path = "/query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerPushServiceRequest request) {
        CustomerPushServiceResponse response = new CustomerPushServiceResponse();
        if (isEmpty(request.getEntity().getQueryType())) {
            return this.error(response, "请求类型必填，1代表我推送的，2代表推送给我的，3代表推送给我们部门");
        }
        CustomerPushVo customerPushVo = request.getEntity();
        Map param = new HashMap();
        //模糊搜索条件
        keyWordCondition(request, param);
        //解析查询参数，如果返回false，则是独立经纪人查询
        if (!resolveQueryParam(customerPushVo, param)) {
            //如果解析参数返回false，就直接返回空值即可，通常的情况就是独立经纪人查询的推送给我们部门的数据
            response.setResult(new PageResult<CustomerPushService>());
            return this.success(response);
        }
        //设置排序类型
        String orderBy = "create_time";
        boolean descOrAsc = false;
        if (customerPushVo.getCusSortType() != null) {
            String[] s = DictionaryStorage.get(CusPushSortType.getKey(), customerPushVo.getCusSortType()).getName().split(",");
            orderBy = s[0];
            if (customerPushVo.getCusSortType().equals(CusPushSortType.ENTER_TIME_ASC.toString()) || customerPushVo.getCusSortType().equals(CusPushSortType.CREATE_TIME_ASC.toString()) || customerPushVo.getCusSortType().equals(CusPushSortType.LAST_FOLLOWUP_TIME_ASC.toString())) {
                descOrAsc = true;
            } else {
                descOrAsc = false;
            }
        }
        PageResult<CustomerPushEntity> result = customerPushManager.findByMap(param, request.getStart(), request.getPageSize(), orderBy, descOrAsc);
        //客户未跟进天数和查询类型添加
        cusNotFolDay(customerPushVo, result.getData());
        response.setResult(result);
        return this.success(response);
    }

    @RequestMapping(path = "/select", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> select(@RequestBody CustomerPushServiceRequest request) {
        CustomerPushServiceResponse response = new CustomerPushServiceResponse();

        CustomerPushVo customerPushVo = request.getEntity();
        Map param = new HashMap();

        if (customerPushVo == null) {
            response.setResult(new PageResult<CustomerPushService>());
            return this.success(response);
        }
        //设置查询参数
        setQueryParameter(customerPushVo, param);

        //设置排序类型
        String orderBy = "create_time";
        boolean descOrAsc = false;
        if (customerPushVo.getCusSortType() != null) {
            String[] s = DictionaryStorage.get(CusPushSortType.getKey(), customerPushVo.getCusSortType()).getName().split(",");
            orderBy = s[0];
            if (customerPushVo.getCusSortType().equals(CusPushSortType.ENTER_TIME_ASC.toString()) || customerPushVo.getCusSortType().equals(CusPushSortType.CREATE_TIME_ASC.toString()) || customerPushVo.getCusSortType().equals(CusPushSortType.LAST_FOLLOWUP_TIME_ASC.toString())) {
                descOrAsc = true;
            } else {
                descOrAsc = false;
            }
        }
        PageResult<CustomerPushEntity> result = customerPushDbDao.selectByStatement("selectPushByMap", param, request.getStart(), request.getPageSize(), orderBy, descOrAsc);
        //PageResult<CustomerPushEntity> result = customerPushManager.findByMap(param, request.getStart(), request.getPageSize(), orderBy, descOrAsc);
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 客户推送详情
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody CustomerPushServiceRequest request) {
        CustomerPushServiceResponse response = new CustomerPushServiceResponse();

        if (request.getEntity().getId() == null) {
            return this.error(response, "查询详情，id必填");
        }
        CustomerPushEntity push = customerPushManager.findOne("id", request.getEntity().getId());
        if (push == null) {
            return this.error(response, "查询的数据不存在");
        }
        CustomerEntity cus = customerManager.detail(push.getCusCode());

        push.setCustomer(cus);

        response.setResult(push);

        return this.success(response);
    }


    /**
     * 处理查询参数
     *
     * @param request
     * @param customerPushVo
     * @param param
     * @return
     */
    private boolean resolveQueryParam(CustomerPushVo customerPushVo, Map param) {
        //推送客户查询类型条件，如果返回的值是false，则是独立经纪人
        if (!pushQueryTypeCodition(customerPushVo, param)) {
            return false;
        }
        //设置查询参数
        setQueryParameter(customerPushVo, param);
        //客户未跟进天数查询条件
        needAreaCondition(customerPushVo, param);
        //设置需求面积
        needAcreage(customerPushVo, param);
        //需求电量条件
        needVoltage(customerPushVo, param);
        //需求价格条件
        needPriceCondition(customerPushVo, param);
        //入住时间条件
        enterTimeCondition(customerPushVo, param);
        //客户未跟进天数查询条件
        setCustomerNotFollowupCondition(customerPushVo.getCustomerNotFollowupType(), customerPushVo.getCustomerNotFollowupDay(), param);
        return true;
    }

    private boolean pushQueryTypeCodition(CustomerPushVo customerPushVo, Map param) {
        switch (customerPushVo.getQueryType()) {
            case CustomerPushQueryType.MINE:
                param.put("pushCode", CurrentContext.getUserCode());
                break;
            case CustomerPushQueryType.PUSH_TO_ME:
                //推送给我的
                param.put("receiveCode", CurrentContext.getUserCode());
                break;
            case CustomerPushQueryType.PUSH_TO_DEPT:
                //推送给我们部门的
                //如果部门编码不存在，那么可能就是独立经纪人，不需要查询数据库，不可能有别人推送给独立经纪人部门
                if (StringUtils.isEmpty(CurrentContext.getUserInfo().getOwnerDeptCode())) {
                    return false;
                } else {
                    param.put("receiveDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                }
                break;
        }
        return true;
    }

    /**
     * 设置查询参数
     *
     * @param customerPushVo
     * @param param
     */
    private void setQueryParameter(CustomerPushVo customerPushVo, Map param) {
        // 公司名字
        if (StringUtils.isNotEmpty(customerPushVo.getCompanyName())) {
            param.put("companyName", customerPushVo.getCompanyName());
        }
        // 客户手机号
        if (StringUtils.isNotEmpty(customerPushVo.getCusPhone())) {
            param.put("cusPhone", customerPushVo.getCusPhone());
        }
        // 推送人姓名
        if (StringUtils.isNotEmpty(customerPushVo.getPushName())) {
            param.put("pushName", customerPushVo.getPushName());
        }
        //所属经纪人
        if (StringUtils.isNotEmpty(customerPushVo.getEmpName())) {
            param.put("empName", customerPushVo.getEmpName());
        }
        // 客户状态
        if (customerPushVo.getCusStatus() != null) {
            param.put("cusStatus", customerPushVo.getCusStatus());
        }
        // 客户状态
        if (customerPushVo.getCusCode() != null) {
            param.put("cusCode", customerPushVo.getCusCode());
        }
        param.put("cusIsDeleted", false);
        //自己控制分页
        param.put("self_page", true);
        param.put("isDeleted", false);
    }

    /**
     * 需求面积条件
     *
     * @param customerPushVo
     * @param param
     */
    private void needAcreage(CustomerPushVo customerPushVo, Map param) {
        if (customerPushVo.getNeedAcreageStart() != null) { // 需求面积
            param.put("needAcreageStart", customerPushVo.getNeedAcreageStart());
        }
        if (customerPushVo.getNeedAcreageEnd() != null) { // 需求面积
            param.put("needAcreageEnd", customerPushVo.getNeedAcreageEnd());
        }
    }

    /**
     * 需求区域
     *
     * @param customerPushVo
     * @param param
     */
    private void needAreaCondition(CustomerPushVo customerPushVo, Map param) {
        // 街道
        if (StringUtils.isNotEmpty(customerPushVo.getStreet())) {
            param.put("street", customerPushVo.getStreet());
            // 区域
        } else if (StringUtils.isNotEmpty(customerPushVo.getRegion())) {
            param.put("region", customerPushVo.getRegion());
            // 城市
        } else if (StringUtils.isNotEmpty(customerPushVo.getCity())) {
            param.put("city", customerPushVo.getCity());
            // 省份
        } else if (StringUtils.isNotEmpty(customerPushVo.getProvince())) {
            param.put("province", customerPushVo.getProvince());
        }
    }

    /**
     * 需求电量条件
     *
     * @param request
     * @param customerPushVo
     * @param param
     */
    private void needVoltage(CustomerPushVo customerPushVo, Map param) {
        if (customerPushVo.getNeedVoltageStart() != null) {
            param.put("needVoltageStart", customerPushVo.getNeedVoltageStart());
        }
        if (customerPushVo.getNeedVoltageEnd() != null) {
            param.put("needVoltageEnd", customerPushVo.getNeedVoltageEnd());
        }
    }

    /**
     * 需求价格
     *
     * @param customerPushVo
     * @param param
     */
    private void needPriceCondition(CustomerPushVo customerPushVo, Map param) {
        if (customerPushVo.getNeedPriceStart() != null) { // 要求价格
            param.put("needPriceStart", customerPushVo.getNeedPriceStart());
        }
        if (customerPushVo.getNeedPriceEnd() != null) { // 要求价格
            param.put("needPriceEnd", customerPushVo.getNeedPriceEnd());
        }
    }

    /**
     * 入住时间
     *
     * @param customerPushVo
     * @param param
     */
    private void enterTimeCondition(CustomerPushVo customerPushVo, Map param) {
        if (customerPushVo.getEnterTimeStart() != null) { // 入住时间
            param.put("enterTimeStart", customerPushVo.getEnterTimeStart());
        }
        if (customerPushVo.getEnterTimeEnd() != null) { // 入住时间
            param.put("enterTimeEnd", customerPushVo.getEnterTimeEnd());
        }
    }

    /**
     * 客户未跟进天数添加
     *
     * @param list
     * @param list
     */
    private void cusNotFolDay(CustomerPushVo customerPushVo, List<CustomerPushEntity> list) {
        Date nowDate = new Date();
        if (CollectionUtil.isNotEmpty(list)) {
            for (CustomerPushEntity customerPushEntity : list) {
                //查询类型添加返回
                customerPushEntity.setQueryType(customerPushVo.getQueryType());
                if (!ObjectUtils.isEmpty(customerPushEntity.getCustomer())) {
                    Date lastFollupDate = customerPushEntity.getCustomer().getLastFollowupTime();
                    java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    long indays = 1234;
                    if (lastFollupDate == null) {
                        customerPushEntity.getCustomer().setDaysNotFollowup(null);
                    } else {
                        indays = (nowDate.getTime() - lastFollupDate.getTime()) / (24 * 60 * 60 * 1000);
                        customerPushEntity.getCustomer().setDaysNotFollowup(indays);
                    }
                }
            }
        }
    }

    /**
     * 客户未跟进天数查询条件
     *
     * @param customerVo
     * @param customerNotFollowupType
     * @param param
     */
    private void setCustomerNotFollowupCondition(Integer customerNotFollowupType, Integer customerNotFollowupDay, Map param) {
        Date date = new Date();
        if (customerNotFollowupType != null) {
            if (Objects.equals(customerNotFollowupType, CustomerNotFollowupType.ONEDAY)) {
                param.put("cusNotFolStart", DateFormatUtil.dayEnd(date));
                param.put("cusNotFolEnd", DateFormatUtil.dayBegin(date));
            } else if (Objects.equals(customerNotFollowupType, CustomerNotFollowupType.ONE_TO_THREE)) {
                param.put("cusNotFolStart", DateFormatUtil.dayEnd(DateFormatUtil.addDate(date, -2, "DD")));
                param.put("cusNotFolEnd", DateFormatUtil.dayBegin(DateFormatUtil.addDate(date, -4, "DD")));
            } else if (Objects.equals(customerNotFollowupType, CustomerNotFollowupType.THREE_TO_FIVE)) {
                param.put("cusNotFolStart", DateFormatUtil.dayEnd(DateFormatUtil.addDate(date, -4, "DD")));
                param.put("cusNotFolEnd", DateFormatUtil.dayBegin(DateFormatUtil.addDate(date, -6, "DD")));
            } else if (Objects.equals(customerNotFollowupType, CustomerNotFollowupType.FIVE_TO_TEN)) {
                param.put("cusNotFolStart", DateFormatUtil.dayEnd(DateFormatUtil.addDate(date, -6, "DD")));
                param.put("cusNotFolEnd", DateFormatUtil.dayBegin(DateFormatUtil.addDate(date, -11, "DD")));
            } else if (Objects.equals(customerNotFollowupType, CustomerNotFollowupType.MORE_THAN_TEN)) {
                param.put("cusNotFolStart", DateFormatUtil.dayEnd(DateFormatUtil.addDate(date, -11, "DD")));
            }
        } else if (customerNotFollowupDay != null) {
            param.put("cusNotFolStart", DateFormatUtil.dayEnd(DateFormatUtil.addDate(date, -(customerNotFollowupDay + 1), "DD")));
            param.put("cusNotFolEnd", DateFormatUtil.dayBegin(DateFormatUtil.addDate(date, -(customerNotFollowupDay + 1), "DD")));
        }
    }

    /**
     * 公开隐藏推送
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/openOrHidden", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> openOrHidden(@RequestBody CustomerPushServiceRequest request) {
        CustomerPushServiceResponse response = new CustomerPushServiceResponse();
        CustomerPushVo customerPushVo = request.getEntity();
        CustomerPushEntity pushEntity = new CustomerPushEntity();
        pushEntity.setId(customerPushVo.getId());
        pushEntity.setPushOpenFlag(customerPushVo.getPushOpenFlag());
        if (customerPushManager.update(pushEntity) > 0) {
            return this.success(response);
        } else {
            throw new RemoveStackBusinessException ("修改公开隐藏操作失败！");
        }
    }

    private void keyWordCondition(CustomerPushServiceRequest request, Map param) {

        if (StringUtils.isNotEmpty(request.getKeyword())) {
            param.put("keyword", request.getKeyword());
        }
    }
}

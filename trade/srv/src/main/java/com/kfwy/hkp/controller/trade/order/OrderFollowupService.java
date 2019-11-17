package com.kfwy.hkp.controller.trade.order;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.utils.BeanMapConvertUtils;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.controller.trade.order.vo.*;
import com.kfwy.hkp.cooperate.entity.CooperateEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.business.IOrderFollowupManager;
import com.kfwy.hkp.trade.order.business.IOrderManager;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderFollowupEntity;
import com.kfwy.hkp.trade.order.enums.OrderQueryType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
* @Description 描述:订单跟进表
* @Auth xpp
* @Date 2018/11/6 14:40
* @Version 1.0
* @Param 
* @return 
**/

@RestController
@RequestMapping(path = "/orderfollowup")
public class OrderFollowupService extends SpringRestService {

    @Autowired
    private IOrderFollowupManager OrderFollowupManager;

    /**
     * 新增订单跟进
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody OrderFollowupServiceRequest request) throws IllegalAccessException {

        OrderFollowupServiceResponse response = new OrderFollowupServiceResponse();
        // 根据业务需求对相应字段校验--start
        OrderFollowupEntity orderFollowOne = new OrderFollowupEntity();
        orderFollowOne.setRemark(request.getEntity().getRemark());
        orderFollowOne.setOrderCode(request.getEntity().getOrderCode());

        List<String> list = new ArrayList();
        list.add("remark");
        list.add("orderCode");
        ParamUtil<OrderFollowupEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(orderFollowOne,list);
        // 根据业务需求对相应字段校验--end

        OrderFollowupEntity orderFollowAddOne = request.getEntity();
        UserEntity nowUser = (UserEntity) CurrentContext.getUserInfo();
        orderFollowAddOne.setFollowupTime(new Date());
        orderFollowAddOne.setEmpCode(nowUser.getUserCode());
        orderFollowAddOne.setEmpName(nowUser.getUserName());
        OrderFollowupManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * 查询订单跟进列表
     * @param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody OrderFollowupServiceRequest request) {

        OrderFollowupServiceResponse response = new OrderFollowupServiceResponse();

        Map param = new HashMap<String, Object>();
        OrderFollowupEntity OrderFollowup = request.getEntity();
        param.putAll(BeanMapConvertUtils.convertExclude(OrderFollowup));
        PageResult<OrderFollowupEntity> result = OrderFollowupManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(result);

        return this.success(response);
    }





}
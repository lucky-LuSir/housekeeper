package com.kfwy.hkp.controller.trade.order;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.utils.BeanMapConvertUtils;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DataSourceEnum;
import com.kfwy.hkp.common.utils.DynamicDataSource;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.controller.trade.order.vo.OrderUpdateApplyEntityVo;
import com.kfwy.hkp.controller.trade.order.vo.OrderUpdateApplyServiceRequest;
import com.kfwy.hkp.controller.trade.order.vo.OrderUpdateApplyServiceResponse;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.business.IOrderManager;
import com.kfwy.hkp.trade.order.business.IOrderUpdateApplyManager;
import com.kfwy.hkp.trade.order.config.EmpPostConfig;
import com.kfwy.hkp.trade.order.entity.OrderPercentageApplyEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;
import com.kfwy.hkp.trade.order.entity.OrderUpdateApplyEntity;
import com.kfwy.hkp.trade.order.enums.OrderUpdateApplyStatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * 订单修改申请 service
 */
@RequestMapping(value = "order/update/apply")
@RestController
public class OrderUpdateApplyService extends SpringRestService {

    @Autowired
    private IOrderUpdateApplyManager orderUpdateApplyManager;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IOrderManager orderManager;
    /**
     * 订单修改申请创建
     * @param request
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody OrderUpdateApplyServiceRequest request) throws IllegalAccessException {
        OrderUpdateApplyServiceResponse response = new OrderUpdateApplyServiceResponse();

        // 根据业务需求对相应字段校验--start
        OrderUpdateApplyEntity orderOne = new OrderUpdateApplyEntity();
        orderOne.setOrderCode(request.getEntity().getOrderCode());
        orderOne.setEmpCode(request.getEntity().getEmpCode());
        orderOne.setHouseCode(request.getEntity().getHouseCode());
        orderOne.setCusCode(request.getEntity().getCusCode());
        orderOne.setAcreage(request.getEntity().getAcreage());
        orderOne.setPrice(request.getEntity().getPrice());
        orderOne.setUnit(request.getEntity().getUnit());
        orderOne.setOrderStatus(request.getEntity().getOrderStatus());

        orderOne.setReceivableHos(request.getEntity().getReceivableHos());
        orderOne.setReceivableCus(request.getEntity().getReceivableCus());
        orderOne.setCusRebate(request.getEntity().getCusRebate());
        orderOne.setOwnerRebate(request.getEntity().getOwnerRebate());
        orderOne.setCusParttimeMoney(request.getEntity().getCusParttimeMoney());
        orderOne.setOwnerParttimeMoney(request.getEntity().getOwnerParttimeMoney());
        orderOne.setMonthRent(request.getEntity().getMonthRent());

        List<String> list = new ArrayList<>();
        list.add("orderCode");
        list.add("empCode");//成交人
        list.add("houseCode");
        list.add("cusCode");
        list.add("acreage");
        list.add("price");
        list.add("unit");
        list.add("orderStatus");

        list.add("receivableHos");
        list.add("receivableCus");
        list.add("cusRebate");
        list.add("ownerRebate");
        list.add("cusParttimeMoney");
        list.add("ownerParttimeMoney");
        list.add("monthRent");
        ParamUtil<OrderUpdateApplyEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(orderOne,list);
        // 根据业务需求对相应字段校验--end
        this.orderUpdateApplyManager.create(request.getEntity());
        return this.success(response);
    }

    /**
     * 订单修改申请查询
     * @param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody OrderUpdateApplyServiceRequest request){
        OrderUpdateApplyServiceResponse response = new OrderUpdateApplyServiceResponse();
        OrderUpdateApplyEntityVo entityVo = request.getEntity();
        Map<String,Object> param  = new HashMap<>();
        fillQuery(entityVo,param);
        PageResult<OrderUpdateApplyEntity> result = orderUpdateApplyManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",Boolean.FALSE);
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 查看订单申请详情
     * @param request
     * @return
     */
    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody OrderUpdateApplyServiceRequest request) {

        OrderUpdateApplyServiceResponse response = new OrderUpdateApplyServiceResponse();
        OrderUpdateApplyEntity orderUpdateApplyEntity = orderUpdateApplyManager.orderUpdateApplyDetail(request.getEntity().getOrderCode());
        response.setResult(orderUpdateApplyEntity);

        return this.success(response);
    }

    /**
     * 审核成功 及驳回
     * 目前是大区总与财务审核
     * @param request
     * @return
     */
    @RequestMapping(path = "/audit",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> audit(@RequestBody OrderUpdateApplyServiceRequest request) throws RemoveStackBusinessException {
        OrderUpdateApplyServiceResponse response = new OrderUpdateApplyServiceResponse();
        String orderCode = request.getEntity().getOrderCode();
        String applyStatus = request.getEntity().getApplyStatus();

        UserEntity currentUser = (UserEntity) CurrentContext.getUserInfo();

        OrderUpdateApplyEntity param = new OrderUpdateApplyEntity();
        param.setOrderCode(orderCode);
        param.setLastUpdateTime(new Date());
        param.setLastUpdateCode(currentUser.getUserCode());

        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        EmpPostConfig regionalManager = SystemConfig.create().getObject("regional_manager", EmpPostConfig.class);
        EmpPostConfig finance = SystemConfig.create().getObject("finance", EmpPostConfig.class);
        DynamicDataSource.clearDataSource();

        //查看申请内容
        OrderUpdateApplyEntity orderUpdateApplyEntity = orderUpdateApplyManager.orderUpdateApplyDetail(orderCode);
        param.setId(orderUpdateApplyEntity.getId());

        if(regionalManager.regionalManager.contains(currentUser.getEmpPostCode())){
            if(OrderUpdateApplyStatusType.DISAGREE.equals(applyStatus)){
                //如果审核未不通过即审核驳回 这更改状态为驳回状态
                param.setApplyStatus(OrderUpdateApplyStatusType.DISAGREE);
                param.setAuditReason(request.getEntity().getAuditReason());
                orderUpdateApplyManager.auditFinish(param);
            }
            else
                //这是大区总审核成功哦
                param.setApplyStatus(OrderUpdateApplyStatusType.FINANCEAUDIT);
        } else if (finance.finance.contains(currentUser.getEmpPostCode())){
            if(OrderUpdateApplyStatusType.DISAGREE.equals(applyStatus)){
                param.setApplyStatus(OrderUpdateApplyStatusType.DISAGREE);
                param.setAuditReason(request.getEntity().getAuditReason());
            }
            else{
                //财务审核成功
                orderUpdateApplyEntity.setId(null);
                //填充参数
                List<OrderPercentageEntity> cusServerList = new ArrayList<>();
                List<OrderPercentageEntity> orderPercentageList = new ArrayList<>();
                if(orderUpdateApplyEntity.getCusServicePercentageApplyEntityList()!=null)
                    cusServerList.addAll(orderUpdateApplyEntity.getCusServicePercentageApplyEntityList());
                if(orderUpdateApplyEntity.getOrderPercentageApplyEntityList()!=null)
                    orderPercentageList.addAll(orderUpdateApplyEntity.getOrderPercentageApplyEntityList());
                orderUpdateApplyEntity.setCusServicePercentageEntityList(cusServerList);
                orderUpdateApplyEntity.setOrderPercentageEntityList(orderPercentageList);
                //财务审核通过，修改订单
                orderManager.update(orderUpdateApplyEntity);
                param.setApplyStatus(OrderUpdateApplyStatusType.AGREE);
            }
            //财务审核完成 逻辑删除申请 并更改状态
            orderUpdateApplyManager.auditFinish(param);
            return this.success(response);
        } else {
            throw new RemoveStackBusinessException ("对不起，您没有权限审核！");
        }

        orderUpdateApplyManager.update(param);
        return this.success(response);
    }


    private void fillQuery(OrderUpdateApplyEntityVo entityVo, Map<String, Object> param) {
        param.putAll(BeanMapConvertUtils.convertExclude(entityVo));
        UserEntity currentUser = (UserEntity) CurrentContext.getUserInfo();

        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        EmpPostConfig regionalManager = SystemConfig.create().getObject("regional_manager", EmpPostConfig.class);
        EmpPostConfig finance = SystemConfig.create().getObject("finance", EmpPostConfig.class);
        EmpPostConfig branchManager = SystemConfig.create().getObject("branch_manager", EmpPostConfig.class);
        DynamicDataSource.clearDataSource();

        if(regionalManager.regionalManager.contains(currentUser.getEmpPostCode())){
            List<String> deptCodes = deptManager.getDownDeptCode(currentUser.getOwnerDeptCode());
            param.put("deptCodes",deptCodes);
            param.put("applyStatus", OrderUpdateApplyStatusType.MANAGERAUDIT);
        } else if (finance.finance.contains(currentUser.getEmpPostCode())){
            //财务查看大区总审核通过的，财务审核中的
            param.put("applyStatus", OrderUpdateApplyStatusType.FINANCEAUDIT);
        } else if(branchManager.branchManager.contains(currentUser.getEmpPostCode())){
            param.put("deptCode",currentUser.getOwnerDeptCode());
        } else {
            //throw new BusinessException("对不起，您没有权限查看！");
        }
    }
}










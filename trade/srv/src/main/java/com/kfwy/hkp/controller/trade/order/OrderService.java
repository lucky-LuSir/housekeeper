package com.kfwy.hkp.controller.trade.order;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.hkp.common.easyexcel.export.FileExportor;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.hkp.common.easyexcel.export.exception.FileExportException;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.*;
import com.kfwy.hkp.controller.trade.order.vo.OrderServiceRequest;
import com.kfwy.hkp.controller.trade.order.vo.OrderServiceResponse;
import com.kfwy.hkp.controller.trade.order.vo.OrderVO;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.trade.order.business.IOrderManager;
import com.kfwy.hkp.trade.order.config.EmpPostConfig;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderPercentageEntity;
import com.kfwy.hkp.trade.order.enums.OrderExportType;
import com.kfwy.hkp.trade.order.enums.OrderQueryType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * Created by zjp on 2018/8/16.
 */

@RestController
@RequestMapping (path = "/order")
public class OrderService extends SpringRestService {
    private static final int TIMEOUT = 10 * 1000; //超时时间 10s
    @Autowired
    private RedisLock redisLock;
    @Autowired
    private IOrderManager OrderManager;
    @Autowired
    private IDeptManager deptManager;


    /**
     * 新增订单
     * @param request
     * @return
     */
    @RequestMapping (path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create (@RequestBody OrderServiceRequest request) throws IllegalAccessException {

        OrderServiceResponse response = new OrderServiceResponse ();
        // 根据业务需求对相应字段校验--start

        List<String> list = new ArrayList ();
        list.add ("cusRebate");
        list.add ("ownerRebate");
        list.add ("cusParttimeMoney");
        list.add ("ownerParttimeMoney");
        list.add ("monthRent");
        ParamUtil<OrderVO> paramUtil = new ParamUtil<> ();
        OrderVO entity = request.getEntity ();
        paramUtil.check (entity, list);
        // 根据业务需求对相应字段校验--end
        //加锁
        long time = System.currentTimeMillis () + TIMEOUT;
        if (! redisLock.lock ("ky:hkp:order:"+entity.getCusCode () + entity.getHouseCode () + entity.getEmpCode (), String.valueOf (time))) { //如果返回
            throw new RemoveStackBusinessException ("订单新增中，请不要重复提交~");
        }
        OrderManager.create (entity);
        redisLock.unlock (entity.getCusCode () + entity.getHouseCode () + entity.getEmpCode (),String.valueOf (time));
        return this.success (response);
    }

    /**
     * 查询订单列表
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query (@RequestBody OrderServiceRequest request) throws IllegalAccessException {

        OrderServiceResponse response = new OrderServiceResponse ();
        OrderVO OrderVO = request.getEntity ();
        Map param = OrderVO.queryMap ();
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        DynamicDataSource.setDataSource(DataSourceEnum.SLAVE.getName());
        EmpPostConfig lookAllOrderPost = SystemConfig.create().getObject("look_all_order_post", EmpPostConfig.class);
        DynamicDataSource.clearDataSource();
        if (OrderQueryType.ALL.equals (OrderVO.getQueryType ())) {
            if (! ("E2015112000001").equals (CurrentContext.getUserCode ()) && !lookAllOrderPost.lookAllOrderPost.contains(cur.getEmpPostCode())) {
                List<String> deptCodes = deptManager.getDownDeptCode (CurrentContext.getUserInfo ().getOwnerDeptCode ());
                if (deptCodes != null && ! deptCodes.isEmpty ()) {
                    param.put ("deptCodes", deptCodes);
                } else {
                    param.put ("empCode", CurrentContext.getUserCode ());
                }
            }

        }
        PageResult<OrderEntity> result = OrderManager.findByMap (param, request.getStart (), request.getPageSize (), "create_time", false);
        response.setResult (result);

        return this.success (response);
    }

    /**
     * 订单详情
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail (@RequestBody OrderServiceRequest request) {

        OrderServiceResponse response = new OrderServiceResponse ();
        OrderEntity OrderEntity = OrderManager.detail (request.getEntity ().getOrderCode ());
        response.setResult (OrderEntity);

        return this.success (response);
    }

    /**
     * 订单修改
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update (@RequestBody OrderServiceRequest request) throws IllegalAccessException {

        OrderServiceResponse response = new OrderServiceResponse ();

        // 根据业务需求对相应字段校验--start
        OrderEntity orderOne = new OrderEntity ();
        orderOne.setOrderCode (request.getEntity ().getOrderCode ());
        orderOne.setEmpCode (request.getEntity ().getEmpCode ());
        orderOne.setHouseCode (request.getEntity ().getHouseCode ());
        orderOne.setCusCode (request.getEntity ().getCusCode ());
        orderOne.setAcreage (request.getEntity ().getAcreage ());
        orderOne.setPrice (request.getEntity ().getPrice ());
        orderOne.setUnit (request.getEntity ().getUnit ());
        orderOne.setOrderStatus (request.getEntity ().getOrderStatus ());

        orderOne.setReceivableHos (request.getEntity ().getReceivableHos ());
        orderOne.setReceivableCus (request.getEntity ().getReceivableCus ());
        orderOne.setCusRebate (request.getEntity ().getCusRebate ());
        orderOne.setOwnerRebate (request.getEntity ().getOwnerRebate ());
        orderOne.setCusParttimeMoney (request.getEntity ().getCusParttimeMoney ());
        orderOne.setOwnerParttimeMoney (request.getEntity ().getOwnerParttimeMoney ());
        orderOne.setMonthRent (request.getEntity ().getMonthRent ());

        List<String> list = new ArrayList ();
        list.add ("orderCode");
        list.add ("empCode");//成交人
        list.add ("houseCode");
        list.add ("cusCode");
        list.add ("acreage");
        list.add ("price");
        list.add ("unit");
        list.add ("orderStatus");

        list.add ("receivableHos");
        list.add ("receivableCus");
        list.add ("cusRebate");
        list.add ("ownerRebate");
        list.add ("cusParttimeMoney");
        list.add ("ownerParttimeMoney");
        list.add ("monthRent");
        ParamUtil<OrderEntity> paramUtil = new ParamUtil<> ();
        paramUtil.check (orderOne, list);
        // 根据业务需求对相应字段校验--end

        OrderManager.update (request.getEntity ());

        return this.success (response);
    }

    @RequestMapping(path = "/querycusservice",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> querycusservice(@RequestBody OrderServiceRequest request) {
        OrderServiceResponse response = new OrderServiceResponse();
        if(request.getEntity().getEmpCode()==null){
            if(request.getEntity().getCusCode()!=null){
                throw new BusinessException("分成人未传,客户已传,请先选分成人再选客户!empCode("+request.getEntity().getEmpCode()+")"+"cusCode("+request.getEntity().getCusCode()+")");
            }
            throw new BusinessException("分成人不能为空!empCode");
        }
        if(request.getEntity().getCusCode()==null){
            throw new BusinessException("客户不能为空!cusCode");
        }
        OrderEntity OrderEntity = OrderManager.querycusservice(request.getEntity().getEmpCode(),request.getEntity().getCusCode());
        response.setResult(OrderEntity);

        return this.success(response);
    }


    @RequestMapping(path = "/querySysAutoDivide",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> querySysAutoDivide(@RequestBody OrderServiceRequest request) throws IllegalAccessException {
        OrderServiceResponse response = new OrderServiceResponse();
        OrderVO orderVEntity = request.getEntity();
        // 根据业务需求对相应字段校验--start
        List<String> list = new ArrayList();
        list.add("empCode");
        list.add("houseCode");
        list.add("cusCode");

        list.add("receivableHos");
        list.add("ownerRebate");
        list.add("ownerParttimeMoney");

        list.add("receivableCus");
        list.add("cusRebate");
        list.add("cusParttimeMoney");

        list.add("assistFlag");

        ParamUtil<OrderVO> paramUtil = new ParamUtil<>();
        paramUtil.check(orderVEntity,list);
        // 根据业务需求对相应字段校验--end

        //assistFlag为true校验协助分成列表
        Boolean flag = orderVEntity.getAssistFlag();
        if(flag){
            List<OrderPercentageEntity> subAssistList = orderVEntity.getAssistEntityList();
            if(subAssistList==null){
                throw new RemoveStackBusinessException("协助分成按钮为是,需要传subAssistList");
            }
            if(subAssistList.size()==0){
                throw new RemoveStackBusinessException("协助分成人列表数量需要大于0,当前为:"+subAssistList.size());
            }
            for (OrderPercentageEntity subEntity : subAssistList) {
                List<String> sublist = new ArrayList();

                sublist.add("peEmpCode");
                sublist.add("peEmpName");

                sublist.add("percentageType");
                sublist.add("moneyPercentType");

                sublist.add("percentage");
                ParamUtil<OrderPercentageEntity> subUtil = new ParamUtil<OrderPercentageEntity>();
                subUtil.check(subEntity,sublist);
            }
        }


        OrderEntity OrderEntity = OrderManager.querySysAutoDivide(orderVEntity);
        response.setResult(OrderEntity);

        return this.success(response);
    }

    @RequestMapping(path = "/updatestatus",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> udpateOrderStatus(@RequestBody OrderServiceRequest request) {
        OrderServiceResponse response = new OrderServiceResponse();
        OrderManager.appUdpateOrderStatus(request.getEntity());
        return this.success(response);
    }

    @RequestMapping(path = "/hasCommissionConfirm",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> hasCommissionConfirm(@RequestBody OrderServiceRequest request) {
        OrderServiceResponse response = new OrderServiceResponse();
        if(null == request.getEntity() || StringUtils.isBlank(request.getEntity().getOrderCode())){
            response.setResult("请选择订单或输入佣金确认书编号");
            return this.success(response);
        }
        OrderManager.hasCommissionConfirm(request.getEntity());
        return this.success(response);
    }
    @IgnoreLog
    @RequestMapping(path = "/orderExport",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, FileExportException {
        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;
        Map<String, Object> map = new HashMap<String, Object>(2);
        //获取业绩开始日期
        Date orderStartTime = DateFormatUtil.dateTransition(httpServletRequest.getParameter("orderStartTime"));
        //获取业绩结束日期
        Date orderEndTime = DateFormatUtil.dateTransition(httpServletRequest.getParameter("orderEndTime"));
        map.put("orderTimeStart",DateFormatUtil.dayBeginFormat(DateFormatUtil.getFormatDateTime(orderStartTime,DateFormatUtil.DateTimeFormatDay)));
        map.put("orderTimeEnd",DateFormatUtil.dayEndFormat(DateFormatUtil.getFormatDateTime(orderEndTime,DateFormatUtil.DateTimeFormatDay)));
        map.put("isDeleted", Boolean.FALSE);
        String orderExportType=httpServletRequest.getParameter("orderExportType");
        //初始化导出模板
        String path=null;
        String _name = null;
        List<OrderEntity> data=new ArrayList<OrderEntity>();
        if(OrderExportType.OrderStatistics.equals(orderExportType)){
             path = "/exportTemplate/" + "orderExport" + ".xml";
             data = OrderManager.orderStatistics(map);
             _name="订单统计导出.xlsx";
        }else if(OrderExportType.OrderDivideInto.equals(orderExportType)){
             path = "/exportTemplate/" + "orderDivideIntoExport" + ".xml";
             data = OrderManager.orderDivideInto(map);
            _name="订单分成导出.xlsx";
        }else if(OrderExportType.OrderTransDept.equals(orderExportType)){
             path = "/exportTemplate/" + "orderTransDeptExport" + ".xml";
             data = OrderManager.orderTransDept(map);
            _name="跨部门订单导出.xlsx";
        }else if(OrderExportType.OrderUpdateLog.equals(orderExportType)){
            path = "/exportTemplate/" + "orderUpdateLog" + ".xml";
            data = OrderManager.selectOrderHistory(map);
            _name="订单修改历史导出.xlsx";
        }
        ExportConfig exportConfig = ExportConfigFactory.getExportConfig(OrderService.class.getResourceAsStream(path));
        try {
            workbook=  FileExportor.getExportWorkbook(exportConfig,data);
            String filename = new String(_name.getBytes("UTF-8"), "ISO8859-1");
            httpServletResponse.setHeader("Content-Type", "application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + filename);
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setCharacterEncoding("UTF-8");
            out = httpServletResponse.getOutputStream();
            if (workbook != null) {
                workbook.write(out);
            } else {
                workbook.write(out);
            }
            out.flush();
        }catch (Exception e){

        }finally {

            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    printWriter = null;
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    out = null;
                }
            }

            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    workbook = null;
                }
            }

        }
    }

}

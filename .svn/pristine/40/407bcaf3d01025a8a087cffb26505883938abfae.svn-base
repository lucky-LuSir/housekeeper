package com.kfwy.hkp.controller.trade.order;


import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.hkp.common.easyexcel.export.FileExportor;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.hkp.common.easyexcel.export.exception.FileExportException;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.controller.trade.order.utils.QiChaChaInterface;
import com.kfwy.hkp.controller.trade.order.vo.OrderInvoiceApplyServiceRequest;
import com.kfwy.hkp.controller.trade.order.vo.OrderInvoiceApplyServiceResponse;
import com.kfwy.hkp.trade.order.business.IOrderCheckManager;
import com.kfwy.hkp.trade.order.business.IOrderInvoiceApplyManager;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceApplyEntity;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单开票申请
 */
@RestController
@RequestMapping(path = "/orderInvoice/apply")
public class OrderInvoiceApplyService extends SpringRestService {
    @Autowired
    private IOrderInvoiceApplyManager orderInvoiceApplyManager;
    @Autowired
    private IOrderCheckManager orderCheckManager;


    /**
     * 调用企查查  获取公司税务信息
     * @param request
     * @return
     */
    @RequestMapping(path = "/getCompany",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getCompany(@RequestBody OrderInvoiceApplyServiceRequest request) {
        //本月剩余调用次数
        String residueNum = orderCheckManager.getCompanyCheck();
        OrderInvoiceApplyServiceResponse response=new OrderInvoiceApplyServiceResponse();

        String cpyName = request.getEntity().getCpyName();
        OrderInvoiceApplyEntity orderInvoiceApplyEntity= QiChaChaInterface.getCompany(cpyName);
        orderInvoiceApplyEntity.setRemark(residueNum);
        response.setResult(orderInvoiceApplyEntity);
        return this.success(response);
    }

    /**
     * 订单开票
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> orderInvoice(@RequestBody OrderInvoiceApplyServiceRequest request) {
        OrderInvoiceApplyEntity orderInvoiceApplyEntity = request.getEntity();
        orderCheckManager.orderInvoiceApplyCreateCheck(orderInvoiceApplyEntity);
        OrderInvoiceApplyServiceResponse response=new OrderInvoiceApplyServiceResponse();
        orderInvoiceApplyManager.createApply(orderInvoiceApplyEntity);
        return this.success(response);
    }

    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody OrderInvoiceApplyServiceRequest request) {
        OrderInvoiceApplyServiceResponse response=new OrderInvoiceApplyServiceResponse();
        Map param = new HashMap<String, Object>();
        OrderInvoiceApplyEntity op = request.getEntity();
        if(StringUtils.isNotEmpty(op.getOrderCode())){
            param.put("orderCode",op.getOrderCode());
        }
        PageResult<OrderInvoiceApplyEntity> result = orderInvoiceApplyManager.findByMap(param, request.getStart(), request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }

    @IgnoreLog
    @RequestMapping(path = "/export", method = RequestMethod.GET)
    public void orderInvoiceExport(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, FileExportException {
        Map<String,Object> param = new HashMap<>();

        if (httpServletRequest.getParameter("invoiceTimeStart") != null && StringUtils.isNotEmpty(httpServletRequest.getParameter("invoiceTimeStart"))) {
            Date createStartTime = DateFormatUtil.dateTransition(httpServletRequest.getParameter("invoiceTimeStart"));
            Date startTime = DateFormatUtil.dayBeginFormat(DateFormatUtil.getFormatDateTime(createStartTime, DateFormatUtil.DateTimeFormatDay));
            param.put("invoiceTimeStart",startTime);
        } else {
            throw new RemoveStackBusinessException ("请选择订单开票开始时间");
        }
        if (httpServletRequest.getParameter("invoiceTimeEnd") != null&& StringUtils.isNotEmpty(httpServletRequest.getParameter("invoiceTimeEnd"))) {
            Date createEndTime = DateFormatUtil.dateTransition(httpServletRequest.getParameter("invoiceTimeEnd"));
            Date endTime = DateFormatUtil.dayEndFormat(DateFormatUtil.getFormatDateTime(createEndTime, DateFormatUtil.DateTimeFormatDay));
            param.put("invoiceTimeEnd",endTime);
        }
        //需要导出的数据
        List<OrderInvoiceApplyEntity> data = orderInvoiceApplyManager.findByMap(param,"invoice_time",Boolean.TRUE);
        if(data != null && data.size()>10000){
            throw new RemoveStackBusinessException("当前导出数据过大，请缩小导出范围");
        }

        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;

        String path = "/exportTemplate/" + "invoiceApplyExport" + ".xml";
        ExportConfig exportConfig = ExportConfigFactory.getExportConfig(OrderInvoiceApplyService.class.getResourceAsStream(path));
        StringBuffer name = new StringBuffer();

        try {
            workbook = FileExportor.getExportWorkbook(exportConfig, data);
            String _name = name.toString() + "开票申请列表.xlsx";
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
        } catch (Exception e) {

        } finally {

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

//    /**
//     * 填充准备创建的订单开票申请实体
//     * @param orderInvoiceApplyEntity
//     */
//    private void fillCreate(OrderInvoiceApplyEntity orderInvoiceApplyEntity){
//        Date date = new Date();
//        UserEntity userEntity =(UserEntity) CurrentContext.getUserInfo();
//        DeptEntity deptEntity = deptManager.findOne("deptCode",userEntity.getOwnerDeptCode());
//        DeptEntity parentDept = deptManager.findOne("deptCode",deptEntity.getParentCode());
//        orderInvoiceApplyEntity.setCpyCode(orderInvoiceApplyEntity.getCpyName());
//        orderInvoiceApplyEntity.setCreateCode(userEntity.getUserCode());
//        orderInvoiceApplyEntity.setCreateName(userEntity.getUserName());
//        orderInvoiceApplyEntity.setApplyCode(userEntity.getUserCode());
//        orderInvoiceApplyEntity.setApplyName(userEntity.getUserName());
//        orderInvoiceApplyEntity.setLastUpdateCode(userEntity.getUserCode());
//        orderInvoiceApplyEntity.setLastUpdateName(userEntity.getUserName());
//        orderInvoiceApplyEntity.setDeptCode(userEntity.getOwnerDeptCode());
//        orderInvoiceApplyEntity.setDeptName(userEntity.getOwnerDeptName());
//        orderInvoiceApplyEntity.setParentDeptCode(parentDept.getDeptCode());
//        orderInvoiceApplyEntity.setParentDeptName(parentDept.getDeptName());
//
//        orderInvoiceApplyEntity.setIsDeleted(Boolean.FALSE);
//        orderInvoiceApplyEntity.setCreateTime(date);
//        orderInvoiceApplyEntity.setLastUpdateTime(date);
//        orderInvoiceApplyEntity.setInvoiceTime(date);
//    }
//
//    /**
//     * 判断orderInvoiceApplyEntity部分值是否为空
//     * @param orderInvoiceApplyEntity
//     */
//    private void checkCreateIsNull(OrderInvoiceApplyEntity orderInvoiceApplyEntity){
//
//        ValidationResult result = ValidationUtils.validate(orderInvoiceApplyEntity,
//                "orderCode","creditCode","invoiceTaxRate","invoiceTotalPrice",
//                "consigneeName","consigneeAddress","consigneePhone","invoiceType");
//        if (result.getSuccess()){
//            if(StringUtils.isEmpty(orderInvoiceApplyEntity.getCpyName())){
//                throw new RemoveStackBusinessException("公司名称为空");
//            }
//        }else{
//            throw new RemoveStackBusinessException(result.getMessage());
//        }
//    }
}

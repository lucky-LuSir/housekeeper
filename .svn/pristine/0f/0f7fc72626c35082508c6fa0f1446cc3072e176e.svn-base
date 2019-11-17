package com.kfwy.hkp.controller.trade.order;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.MoneyConvertTool;
import com.kfwy.hkp.common.utils.PoiUtils;
import com.kfwy.hkp.controller.trade.order.vo.OrderInvoiceServiceRequest;
import com.kfwy.hkp.controller.trade.order.vo.OrderInvoiceServiceResponse;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import com.kfwy.hkp.trade.order.business.IOrderInvoiceApplyManager;
import com.kfwy.hkp.trade.order.business.IOrderInvoiceManager;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceApplyEntity;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceEntity;
import com.kfwy.hkp.trade.order.enums.InvoiceApplyStatus;
import com.kfwy.hkp.trade.order.enums.OrderInvoiceType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/11/9.
 */
@RestController
@RequestMapping(path = "/orderInvoice")
public class OrderInvoiceService extends SpringRestService {
    @Autowired
    private IOrderInvoiceManager orderInvoiceManager;
    @Autowired
    private IOrderInvoiceApplyManager orderInvoiceApplyManager;
    @Autowired
    private IFileManager fileManager;

    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody OrderInvoiceServiceRequest request) {
        OrderInvoiceServiceResponse response=new OrderInvoiceServiceResponse();
        Map param = new HashMap<String, Object>();
        OrderInvoiceEntity op=request.getEntity();
        if(StringUtils.isNotEmpty(op.getOrderCode())){
            param.put("orderCode",op.getOrderCode());
        }
        PageResult<OrderInvoiceEntity> result =orderInvoiceManager.selectByMap(param, request.getStart(), request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }

    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody OrderInvoiceServiceRequest request) {
        OrderInvoiceServiceResponse response=new OrderInvoiceServiceResponse();
        OrderInvoiceEntity payback=request.getEntity();
        payback.setLastUpdateTime(new Date());
        payback.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
        payback.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
        orderInvoiceManager.update(payback);
        return this.success(response);
    }

    @RequestMapping(path = "/uploadFile",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> uploadFile(@RequestBody OrderInvoiceServiceRequest request) {
        List<OrderInvoiceEntity> pe=new ArrayList<OrderInvoiceEntity>();
        OrderInvoiceServiceResponse response=new OrderInvoiceServiceResponse();
        List<Map> valueList=new ArrayList<>();
        String fielCode=request.getFileCode();
        FileEntity file=fileManager.findByCode(fielCode);
        try {
            valueList = PoiUtils.readExcel(file.getPathHead(),file.getFilePath());
            for(int k=0;k<valueList.size();k++) {
                OrderInvoiceEntity pay = new OrderInvoiceEntity();
                //回款金额
                String orderCode=valueList.get(k).get("订单编号").toString();
                Object oc = valueList.get(k).get("订单开票编号");
                String orderInvoiceCode = oc == null ? null : oc.toString();
                Object ic = valueList.get(k).get("发票号");
                String invoiceCode = ic == null ? null : ic.toString();
                if (StringUtils.isEmpty(invoiceCode)){
                    throw  new RemoveStackBusinessException ("请输入发票号");
                }
                BigDecimal invoiceTotalPrice=new BigDecimal(valueList.get(k).get("含税金额(开票金额)").toString());
                String invoiceUpperCase= MoneyConvertTool.change(Double.parseDouble(valueList.get(k).get("含税金额(开票金额)").toString()));
                Integer invoiceTaxRate=Integer.parseInt(valueList.get(k).get("税率").toString());
                BigDecimal invoiceTax =new BigDecimal((invoiceTotalPrice.doubleValue()/(1 + (Double.parseDouble(invoiceTaxRate.toString())/100)) * (Double.parseDouble(invoiceTaxRate.toString())/100))*100/100).setScale(2,BigDecimal.ROUND_HALF_UP);
                BigDecimal invoiceUnitAmount =new BigDecimal(Math.round(invoiceTotalPrice.subtract(invoiceTax).multiply(new BigDecimal(100)).divide(new BigDecimal(100)).doubleValue()));

                pay.setOrderCode(orderCode);
                //发票号
                pay.setInvoiceCode(invoiceCode);
                //开票总金额
                pay.setInvoiceTotalPrice(invoiceTotalPrice);
                //开票总金额(大写)
                pay.setInvoiceUpperCase(invoiceUpperCase);
                //税率
                pay.setInvoiceTaxRate(invoiceTaxRate);
                //invoiceTax税额
                pay.setInvoiceTax(invoiceTax);
                //金额
                pay.setInvoiceUnitAmount(invoiceUnitAmount);
                //创建人编码
                pay.setCreateCode(CurrentContext.getUserInfo().getUserCode());
                //创建人名字
                pay.setCreateName(CurrentContext.getUserInfo().getUserName());
                pay.setApplyCode(valueList.get(k).get("申请人编码").toString());
                pay.setApplyName(valueList.get(k).get("申请人名称").toString());
                pay.setDeptName(valueList.get(k).get("分部名称").toString());
                pay.setDeptCode(valueList.get(k).get("分部编码").toString());
                pay.setParentDeptCode(valueList.get(k).get("大区编码").toString());
                pay.setParentDeptName(valueList.get(k).get("大区名称").toString());
                if(valueList.get(k).get("电话") != null) pay.setPhone(valueList.get(k).get("电话").toString());
                pay.setCpyCode(valueList.get(k).get("公司名称").toString());
                pay.setCpyName(valueList.get(k).get("公司名称").toString());
                pay.setCreditCode(valueList.get(k).get("税号").toString());
                if(valueList.get(k).get("银行支行") != null) pay.setBankBranch(valueList.get(k).get("银行支行").toString());
                if(valueList.get(k).get("账号") != null) pay.setBankCard(valueList.get(k).get("账号").toString());
                if(valueList.get(k).get("地址") != null) pay.setAddress(valueList.get(k).get("地址").toString());
                if(valueList.get(k).get("备注") != null) pay.setRemark(valueList.get(k).get("备注").toString());
                pay.setConsigneeName(valueList.get(k).get("收件人名称").toString());
                pay.setConsigneePhone(valueList.get(k).get("收件人电话").toString());
                pay.setConsigneeAddress(valueList.get(k).get("收件人地址").toString());

                String type = valueList.get(k).get("发票类型").toString();
                OrderInvoiceType orderInvoiceType = new OrderInvoiceType();
                pay.setInvoiceType(orderInvoiceType.getKey(type));

                Date invoiceTime = new Date();
                try {
                    invoiceTime = DateFormatUtil.getCurrentDate(valueList.get(k).get("开票申请日期").toString(),DateFormatUtil.DateTimeFormatString);
                }catch (Exception e){
                    throw  new RemoveStackBusinessException ("回款时间格式异常，请修改后重新上传");
                }
                pay.setInvoiceTime(invoiceTime);

                Date date = new Date();
                pay.setLastUpdateTime(date);
                pay.setCreateTime(date);
                pay.setLastUpdateCode(CurrentContext.getUserInfo().getUserCode());
                pay.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
                pe.add(pay);

                // 更新开票申请状态
                if (StringUtils.isNotEmpty(orderInvoiceCode)) {
                    OrderInvoiceApplyEntity oe = orderInvoiceApplyManager.findOne("orderInvoiceCode", orderInvoiceCode);
                    oe.setApplyStatus(InvoiceApplyStatus.Success);
                    orderInvoiceApplyManager.update(oe);
                }
            }
            int i = orderInvoiceManager.insert(pe,response);
            return this.success(response);
        } catch (Exception e) {
           throw new BusinessException(e.toString());
        }
    }
}

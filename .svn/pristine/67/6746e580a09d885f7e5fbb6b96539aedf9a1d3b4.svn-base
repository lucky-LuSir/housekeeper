package com.kfwy.hkp.controller.trade.order;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.PoiUtils;
import com.kfwy.hkp.controller.trade.order.vo.OrderPaybackServiceRequest;
import com.kfwy.hkp.controller.trade.order.vo.OrderPaybackServiceResponse;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.file.entity.FileEntity;
import com.kfwy.hkp.trade.order.business.IOrderPaybackManager;
import com.kfwy.hkp.trade.order.entity.OrderPaybackEntity;
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
@RequestMapping(path = "/orderPayback")
public class OrderPaybackService extends SpringRestService {
    @Autowired
    private IOrderPaybackManager orderPaybackManager;
    @Autowired
    private IFileManager fileManager;

    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody OrderPaybackServiceRequest request) {
        OrderPaybackServiceResponse response=new OrderPaybackServiceResponse();
        Map param = new HashMap<String, Object>();
        OrderPaybackEntity op=request.getEntity();
        if(StringUtils.isNotEmpty(op.getOrderCode())){
            param.put("orderCode",op.getOrderCode());
        }
        PageResult<OrderPaybackEntity> result =orderPaybackManager.selectByMap(param, request.getStart(), request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }


    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody OrderPaybackServiceRequest request) {
        OrderPaybackServiceResponse response=new OrderPaybackServiceResponse();
        OrderPaybackEntity payback=request.getEntity();
        orderPaybackManager.create(payback);
        return this.success(response);
    }
    @RequestMapping(path = "/selectPayBackSum",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectPayBackSum(@RequestBody OrderPaybackServiceRequest request) {
        OrderPaybackServiceResponse response=new OrderPaybackServiceResponse();
        Date backTime=request.getEntity().getBackTime();
        Date startTime = new Date();
        Date endTime = new Date();
        if (null==backTime){
            backTime=new Date();
        }

        //所选月
        Calendar cal = Calendar.getInstance();
        cal.setTime(backTime);
        cal.set(Calendar.DAY_OF_MONTH,1);
        //获取所在月第一天
        startTime =DateFormatUtil.dayBegin(cal.getTime());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(backTime);
        cal2.add(Calendar.MONTH,1);
        cal2.set(Calendar.DAY_OF_MONTH,1);
        //获取所在月下月第一天
        endTime = DateFormatUtil.dayBegin(cal2.getTime());
        Map<String,Object> param=new HashMap<>();
        if (StringUtils.isNotEmpty(request.getEntity().getOrderCode())){
            param.put("orderCode",request.getEntity().getOrderCode());
        }
        param.put("backTimeStart",startTime);
        param.put("backTimeEnd",endTime);
        param.put("isDeleted",false);
        response.setResult(orderPaybackManager.selectPayBackSum(param));
        return this.success(response);
    }

    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody OrderPaybackServiceRequest request) {
        OrderPaybackServiceResponse response=new OrderPaybackServiceResponse();
        OrderPaybackEntity payback=request.getEntity();
        orderPaybackManager.update(payback);
        return this.success(response);
    }

    @RequestMapping(path = "/uploadFile",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> uploadFile(@RequestBody OrderPaybackServiceRequest request) throws Exception {
        List<OrderPaybackEntity> pe=new ArrayList<OrderPaybackEntity>();
        OrderPaybackServiceResponse response=new OrderPaybackServiceResponse();
        List<Map> valueList=new ArrayList<>();
        String fielCode=request.getFileCode();
        FileEntity file=fileManager.findByCode(fielCode);
            try {
                valueList = PoiUtils.readExcel(file.getPathHead(), file.getFilePath());
            } catch (Exception e) {
             throw new RemoveStackBusinessException ("文件解析异常，请联系管理员");

            }
        for(int k=0;k<valueList.size();k++) {
                OrderPaybackEntity pay = new OrderPaybackEntity();
                //回款金额
                String orderCode=valueList.get(k).get("订单号").toString();
                BigDecimal payment = BigDecimal.ZERO;
                try {
                    payment=new BigDecimal(valueList.get(k).get("回款金额").toString());
                }catch (Exception e){
                    throw  new RemoveStackBusinessException("回款金额格式异常，请修改后重新上传");
                }
                    String channel=valueList.get(k).get("付款渠道").toString();
                Date backTime = new Date();
                try {
                    backTime = DateFormatUtil.getCurrentDate(valueList.get(k).get("回款时间").toString(),DateFormatUtil.DateTimeFormatString);
                }catch (Exception e){
                    throw  new RemoveStackBusinessException("回款时间格式异常，请修改后重新上传");
                }
                String description= valueList.get(k).get("汇款描述").toString();
                String remark= valueList.get(k).get("回款说明").toString();
                pay.setOrderCode(orderCode);
                pay.setPayment(payment);
                pay.setChannel(channel);
                pay.setBackTime(backTime);
                pay.setDescription(description);
                pay.setRemark(remark);
                pe.add(pay);

            }
            int i = orderPaybackManager.insert(pe,response);
            if (i < 0) {
                return this.error(response,response.getMessage());
            }
            return this.success(response);

    }



}

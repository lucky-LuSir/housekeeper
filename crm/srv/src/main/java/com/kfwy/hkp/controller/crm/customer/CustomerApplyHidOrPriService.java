package com.kfwy.hkp.controller.crm.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.utils.BeanMapConvertUtils;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerApplyHidOrPriServiceRequest;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerApplyHidOrPriServiceResponse;
import com.kfwy.hkp.crm.customer.business.ICustomerApplyHidOrPriManager;
import com.kfwy.hkp.crm.customer.entity.CustomerApplyHidOrPriEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@Api(value = "客户申请隐藏拉私接口", description = "客户申请隐藏拉私接口")
@RestController
@RequestMapping(path = "/customerApplyHidOrPri")
public class CustomerApplyHidOrPriService extends SpringRestService{
    @Autowired
    private ICustomerApplyHidOrPriManager customerApplyHidOrPriManager;

    @ApiOperation(value = "根据条件查询客户申请隐藏拉私列表",
            notes = "根据条件keyword,查询客户分页集合信息")
    @RequestMapping(path = "/query",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerApplyHidOrPriServiceRequest request){
        CustomerApplyHidOrPriServiceResponse response=new CustomerApplyHidOrPriServiceResponse();
        Map map=new HashMap<>();
        map.putAll(BeanMapConvertUtils.convertExclude(request.getEntity()));
        PageResult<CustomerApplyHidOrPriEntity> result= customerApplyHidOrPriManager.selectByMap(map,request.getStart(),request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }

    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CustomerApplyHidOrPriServiceRequest request){
        CustomerApplyHidOrPriServiceResponse response=new CustomerApplyHidOrPriServiceResponse();
        customerApplyHidOrPriManager.check(request.getEntity());
        return this.success(response);
    }
}

package com.kfwy.hkp.controller.hrm.cusservice;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.hrm.cusservice.vo.CusServiceServiceRequest;
import com.kfwy.hkp.controller.hrm.cusservice.vo.CusServiceServiceResponse;
import com.kfwy.hkp.hrm.org.dept.business.ICusServiceManager;
import com.kfwy.hkp.hrm.org.dept.entity.CusServiceEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
* @Description 描述:
* @Auth xpp
* @Date 2019/1/7 10:08
* @Version 1.0
* @Param 
* @return 
**/
@RestController
@RequestMapping("/cusservice")
public class CusServiceService extends SpringRestService {

    @Autowired
    private ICusServiceManager cusServiceManager;


    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryAll(@RequestBody CusServiceServiceRequest request){

        CusServiceServiceResponse response = new CusServiceServiceResponse();

        Map<String,Object> map = new HashMap<>();
        map.put("keyword",request.getKeyword());
//        List<CusServiceEntity> result = cusServiceManager.findByMap(map);
        PageResult<CusServiceEntity> result = cusServiceManager.selectCusServiceList(map,request.getStart(),request.getPageSize());

        if (result == null){
            return this.error(response,"操纵异常了，请联系管理员");
        }
        response.setResult(result);

        return this.success(response);
    }
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CusServiceServiceRequest request){

        CusServiceServiceResponse response = new CusServiceServiceResponse();

        CusServiceEntity cusServiceEntity = request.getEntity();
        cusServiceManager.create(cusServiceEntity);


        return this.success(response);
    }

    @RequestMapping(path = "/delete",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody CusServiceServiceRequest request){

        CusServiceServiceResponse response = new CusServiceServiceResponse();

        CusServiceEntity cusServiceEntity = request.getEntity();
        if (StringUtils.isEmpty(cusServiceEntity.getCusserviceCode())){
            return this.error(response,"操纵异常了，请联系管理员");
        }
        cusServiceManager.delete(cusServiceEntity);


        return this.success(response);
    }

}

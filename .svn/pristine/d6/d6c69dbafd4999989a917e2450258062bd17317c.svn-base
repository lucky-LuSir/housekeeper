package com.kfwy.hkp.controller.sys.cpy;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.sys.cpy.vo.CompanyServiceRequest;
import com.kfwy.hkp.controller.sys.cpy.vo.CompanyServiceResponse;
import com.kfwy.hkp.sys.cpy.business.ICompanyManager;
import com.kfwy.hkp.sys.cpy.entity.CompanyEntity;
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
 * Created by davidcun on 2018/5/25.
 */

@RestController
@RequestMapping(path = "/company")
public class CompanyService extends SpringRestService {

    @Autowired
    private ICompanyManager companyManager;

    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CompanyServiceRequest request) {


        CompanyServiceResponse response = new CompanyServiceResponse();

        companyManager.create(request.getEntity());

        return this.success(response);
    }

    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CompanyServiceRequest request) {

        CompanyServiceResponse response = new CompanyServiceResponse();

        companyManager.update(request.getEntity());

        return this.success(response);
    }


    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CompanyServiceRequest request) {

        CompanyServiceResponse response = new CompanyServiceResponse();

//        companyManager.update(request.getEntity());

        Map<String,Object> param = new HashMap<String,Object>();

        //TODO 查询条件

        PageResult<CompanyEntity> result = companyManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",false);



        response.setResult(result);



        return this.success(response);
    }

}

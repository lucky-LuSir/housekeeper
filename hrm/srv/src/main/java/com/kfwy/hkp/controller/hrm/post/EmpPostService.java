package com.kfwy.hkp.controller.hrm.post;

import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.hrm.post.vo.EmpPostServiceRequest;
import com.kfwy.hkp.controller.hrm.post.vo.EmpPostServiceResponse;
import com.kfwy.hkp.hrm.org.post.api.EmpPostApiClient;
import com.kfwy.hkp.hrm.org.post.entity.EmpPostEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by davidcun on 2018/5/22.
 */
@RestController
@RequestMapping("/empPost")
public class EmpPostService extends SpringRestService {


    @RequestMapping(path = "/queryAll",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryAll(@RequestBody EmpPostServiceRequest request){

        EmpPostServiceResponse response = new EmpPostServiceResponse();

        List<EmpPostEntity> result = EmpPostApiClient.findEmpPostAll();

        if (result == null){
            return this.error(response,"操纵异常了，请联系管理员");
        }
        response.setResult(result);
        return this.success(response);
    }
}

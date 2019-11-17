package com.kfwy.hkp.controller.base.log;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.controller.base.log.vo.*;
import com.kfwy.hkp.log.entity.BaseOperationEntity;
import com.kfwy.hkp.log.manager.BaseOperationMongoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/baseOperation")
public class BaseOperationLogService extends SpringRestService {

    @Autowired
    private BaseOperationMongoManager baseOperationMongoManager;


    @IgnoreLog
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody BaseOperationLogRequest request) throws IllegalAccessException {

        OperationLogResponse response = new OperationLogResponse();

        Map<String,Object> map = request.getEntity().queryMap();

        PageResult<BaseOperationEntity> result = baseOperationMongoManager
                .findByMap(map, request.getStart(), request.getPageSize(),"create_time",Boolean.FALSE);
        response.setResult(result);
        return this.success(response);
    }


    @IgnoreLog
    @RequestMapping(path = "/findUrlsByOptCode",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> findUrlsByOptCode(@RequestBody BaseOperationLogRequest request) {

        OperationLogResponse response = new OperationLogResponse();
        BaseOperationVo operationVo = request.getEntity();
        if (operationVo!=null){
            List<BaseOperationEntity> urls = baseOperationMongoManager.findUrlsByOptCode();
            response.setResult(urls);
        }

        return this.success(response);
    }

    @IgnoreLog
    @RequestMapping(path = "/findClientType",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> findClientType(@RequestBody BaseOperationLogRequest request) {

        OperationLogResponse response = new OperationLogResponse();
        BaseOperationVo operationVo = request.getEntity();
        if (operationVo!=null){
            List<BaseOperationEntity> clientType = baseOperationMongoManager.findClientType();
            response.setResult(clientType);
        }

        return this.success(response);
    }



    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody BaseOperationLogRequest request) throws IllegalAccessException {

        OperationLogResponse response = new OperationLogResponse();
        BaseOperationEntity operationQuery = request.getEntity();

        Map map = new HashMap(16);

        if (operationQuery.getId() != null) {
            map.put("id", operationQuery.getId());
            map.put("isDeleted", Boolean.FALSE);
            BaseOperationEntity result = baseOperationMongoManager.findOne(map);
            response.setResult(result);
        }else {
            response.setResult(new BaseOperationEntity());
        }
        return this.success(response);

    }

}

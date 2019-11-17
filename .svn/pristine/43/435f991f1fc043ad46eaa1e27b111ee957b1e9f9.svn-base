package com.kfwy.hkp.controller.base.log;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.utils.ValidateUtils;
import com.kfwy.hkp.base.history.entity.HistoryEntity;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.common.utils.ParamUtilPro;
import com.kfwy.hkp.controller.base.history.vo.HistoryRequest;
import com.kfwy.hkp.controller.base.history.vo.HistoryResponse;
import com.kfwy.hkp.controller.base.log.vo.OperationLogRequest;
import com.kfwy.hkp.controller.base.log.vo.OperationLogResponse;
import com.kfwy.hkp.controller.base.log.vo.OperationVo;
import com.kfwy.hkp.log.entity.OperationEntity;
import com.kfwy.hkp.log.manager.IOperationManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liwensihan
 * @description TODO
 * @date 2019/3/13 16:11
 */
@RestController
@RequestMapping(path = "/operation")
public class OperationLogService extends SpringRestService {

    @Autowired
    private IOperationManager operationManager;

    /**
     * 查询操作历史记录
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody OperationLogRequest request) throws IllegalAccessException {

        OperationLogResponse response = new OperationLogResponse();

        Map map = request.getEntity().queryMap();

        PageResult<OperationEntity> result = operationManager.findByMap(map, request.getStart(), request.getPageSize(),"log.create_time",Boolean.FALSE);
        response.setResult(result);
        return this.success(response);
    }


    /**
     * 查询操作历史记录详情
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody OperationLogRequest request) throws IllegalAccessException {

        OperationLogResponse response = new OperationLogResponse();
        OperationEntity operationQuery = request.getEntity();

        Map map = new HashMap(16);

        if (operationQuery.getOptCode() != null) {
            map.put("optCode", operationQuery.getOptCode());
        }

        map.put("isDeleted", Boolean.FALSE);
        OperationEntity result = operationManager.findOne(map);
        response.setResult(result);
        return this.success(response);
    }


    /**
     * 记录拨打员工记录
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/recordEmpCallPhone",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> recordEmpCallPhone(@RequestBody OperationLogRequest request) throws IllegalAccessException {

        OperationLogResponse response = new OperationLogResponse();
        OperationEntity operationQuery = request.getEntity();
        //传参校验
        List<String> list = new ArrayList();
        list.add("content");
        ParamUtil<OperationEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(operationQuery,list);

        OperationEntity result = operationManager.recordEmpCallPhone(operationQuery);
        response.setResult(result);
        return this.success(response);
    }

}

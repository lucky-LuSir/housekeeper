package com.kfwy.hkp.controller.hos.house;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.controller.hos.house.handler.BesperkQueryHandler;
import com.kfwy.hkp.controller.hos.house.vo.*;
import com.kfwy.hkp.hos.house.api.BespeakApi.BespeakApiClient;
import com.kfwy.hkp.hos.house.entity.BespeakEntity;
import com.kfwy.hkp.hos.house.entity.HouseFavoriteEntity;
import com.kfwy.hkp.hrm.org.dept.api.DeptApiClient;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/10.
 */
@RestController
@RequestMapping(path = "/bespeak")
public class BespeakService  extends SpringRestService {

    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private BesperkQueryHandler besperkQueryHandler;

    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody BespeakServiceRequest request) throws ParseException, IllegalAccessException {

        BespeakServiceResponse response = new BespeakServiceResponse();
        BespeakEntity bespeakEntity = request.getEntity();
        Map<String, Object> param = besperkQueryHandler.queryMap(bespeakEntity);
        PageResult<BespeakEntity> list = BespeakApiClient.getData(param, request.getStart(),request.getPageSize());
        //PageResult<BespeakEntity> list=null;
        response.setResult(list);
        response.setTotal((null != list && CollectionUtils.isNotEmpty(list.getData()) ? list.getData().size() : 0));
        return this.success(response);
    }



    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody BespeakServiceRequest request) {

        BespeakServiceResponse response = new BespeakServiceResponse();
        BespeakApiClient.update(request.getEntity());
        return this.success(response);
    }

}

package com.kfwy.hkp.controller.sys.auth;

import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.sys.auth.vo.AccessServiceRequest;
import com.kfwy.hkp.controller.sys.auth.vo.AccessServiceResponse;
import com.kfwy.hkp.sys.auth.business.IReportAccessManager;
import com.kfwy.hkp.sys.auth.entity.ReportAccessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/7/11
 * @Description: TODO
 */
@RestController
@RequestMapping("/reportAccess")
public class ReportAccessService extends SpringRestService {

    @Autowired
    private IReportAccessManager reportAccessManager;

    /**
     * 报表权限创建
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<IServiceResponse> create(@RequestBody AccessServiceRequest request) {

        AccessServiceResponse response = new AccessServiceResponse();

        reportAccessManager.creates(request.getEntity(), request.getDeptCodes());

        return this.success(response);
    }

    /**
     * 报表权限创建
     */
    @RequestMapping(path = "/createAll",
            method = RequestMethod.POST)
    public ResponseEntity<IServiceResponse> createAll(@RequestBody AccessServiceRequest request) {

        AccessServiceResponse response = new AccessServiceResponse();

        reportAccessManager.createAll(request.getUserCodes(), request.getDeptCodes());

        return this.success(response);
    }

    /**
     * 权限更新
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody AccessServiceRequest request) {

        AccessServiceResponse response = new AccessServiceResponse();

        return this.success(response);
    }


    /**
     * 报表查看部门列表
     *
     * @param request
     * @return
     * @Autor liwensihan
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody AccessServiceRequest request) {

        AccessServiceResponse response = new AccessServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userCode", request.getEntity().getUserCode());
        List<ReportAccessEntity> byMap = reportAccessManager.findByMap(param);
        response.setResult(byMap);
        return this.success(response);
    }

    /**
     * 报表查看部门列表
     *
     * @param request
     * @return
     * @Autor liwensihan
     */
    @RequestMapping(path = "/selectDeptAccess",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectDeptAccess(@RequestBody AccessServiceRequest request) {

        AccessServiceResponse response = new AccessServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("deleteTimeStart", DateUtil.beginOfMonth(new Date()));
        param.put("createTimeEnd", DateUtil.endOfDay(new Date()));
        List<ReportAccessEntity> byMap = reportAccessManager.selectDeptAccess(param);
        response.setResult(byMap);
        return this.success(response);
    }

}

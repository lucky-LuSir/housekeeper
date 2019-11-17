package com.kfwy.hkp.controller.sys.erpNoticeApi;

import api.ErpNoticeApiClient;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.sys.erpNoticeApi.vo.ErpNoticeApiServiceRequest;
import com.kfwy.hkp.controller.sys.erpNoticeApi.vo.ErpNoticeApiServiceResponse;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.sys.notice.entity.ErpNoticeEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/20.
 */
@Controller
@RequestMapping(path = "/erpNoticeApi")
public class ErpNoticeApiService extends SpringRestService {

    @Autowired
    private IDeptManager deptManager;


    @RequestMapping(path = "/queryList",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> queryList(@RequestBody ErpNoticeApiServiceRequest request) {

        ErpNoticeApiServiceResponse response = new ErpNoticeApiServiceResponse();
        if (this.checkUserType()) {
            Map<String, Object> map = new HashMap<String, Object>();
            List<ErpNoticeEntity> list = ErpNoticeApiClient.findErpNoticeList(map);
            response.setResult(list);
        }
        return this.success(response);
    }


    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> detail(@RequestBody ErpNoticeApiServiceRequest request) {

        ErpNoticeApiServiceResponse response = new ErpNoticeApiServiceResponse();
        if (this.checkUserType()) {
            Map<String, Object> map = new HashMap<String, Object>();
            ErpNoticeEntity list = ErpNoticeApiClient.findErpNotice(request.getEntity().getNcCode());
            response.setResult(list);
        }
        return this.success(response);
    }

    @RequestMapping(path = "/queryListString",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> queryListString(@RequestBody ErpNoticeApiServiceRequest request) {
        ErpNoticeApiServiceResponse response = new ErpNoticeApiServiceResponse();
        if (this.checkUserType()) {
            Map<String, Object> map = new HashMap<String, Object>();
            List<String> list = ErpNoticeApiClient.findErpNoticeListString(map);
            response.setResult(list);
        }
        return this.success(response);
    }

    @RequestMapping(path = "/details/{code}", method = RequestMethod.GET)
    public String details(@PathVariable String code, ModelMap map) {
        ErpNoticeEntity e = ErpNoticeApiClient.findErpNotice(code);
        map.addAttribute("data", e);
        return "notice";
    }

    public boolean checkUserType() {
        UserEntity u = (UserEntity) CurrentContext.getUserInfo();
        if(u.getUserType().equals(UserType.Employee)){
            return true;
        }
        return false;
    }

}

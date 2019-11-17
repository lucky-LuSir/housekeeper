package com.kfwy.hkp.controller.count.home;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.bi.count.business.IWorkbenchManager;
import com.kfwy.hkp.bi.count.entity.WorkbenchEntity;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.controller.count.home.vo.HomeServiceRequest;
import com.kfwy.hkp.controller.count.home.vo.HomeServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/9/7
 */
@RestController
@RequestMapping(path = "/home")
public class HomeService extends SpringRestService {

    @Autowired
    private IWorkbenchManager workbenchManager;

    /**
     * 工作台信息
     * @param request
     * @return
     */
    @RequestMapping(path = "/personWorkCount",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> work(@RequestBody HomeServiceRequest request){

        HomeServiceResponse response = new HomeServiceResponse();
        Map<String, Object> param = new HashMap<> ();
        response.setResult(workbenchManager.queryWorkCount(param));
        return this.success(response);
    }

    /**
     * 富豪榜
     * @param request
     * @return
     */
    @RequestMapping(path = "/richList",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> richList(@RequestBody HomeServiceRequest request){
        HomeServiceResponse response = new HomeServiceResponse();
        response.setResult(workbenchManager.richList());
        return this.success(response);
    }
}

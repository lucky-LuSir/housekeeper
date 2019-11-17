package com.kfwy.hkp.controller.crm.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerFavoriteServiceRequest;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerFavoriteServiceResponse;
import com.kfwy.hkp.crm.customer.business.ICustomerFavoriteManager;
import com.kfwy.hkp.crm.customer.entity.CustomerFavoriteEntity;
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
 * Created by zjp on 2018/7/16.
 */
@RestController
@RequestMapping(path = "/customerFavorite")
public class CustomerFavoriteService extends SpringRestService {

    @Autowired
    private ICustomerFavoriteManager customerFavoriteManager;

    /**
     * 收藏客户
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerFavoriteServiceRequest request) {

        CustomerFavoriteServiceResponse response = new CustomerFavoriteServiceResponse();

        CustomerFavoriteEntity customerFavoriteEntity = request.getEntity();
        customerFavoriteEntity.setEmpCode(CurrentContext.getUserCode());
        customerFavoriteManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * 取消收藏
     * @param request
     * @return
     */
    @RequestMapping(path = "/cancel",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> findOne(@RequestBody CustomerFavoriteServiceRequest request) {

        CustomerFavoriteServiceResponse response = new CustomerFavoriteServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getCusCode())||StringUtils.isEmpty(CurrentContext.getUserCode())){
            throw new RemoveStackBusinessException ("取消收藏失败");
        }
        Map param = new HashMap<String, Object>();
        param.put("cusCode", request.getEntity().getCusCode());
        param.put("empCode", CurrentContext.getUserCode());
        customerFavoriteManager.cancelFavorite(param);

        return this.success(response);
    }

}

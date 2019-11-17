package com.kfwy.hkp.controller.crm.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.controller.crm.customer.vo.*;
import com.kfwy.hkp.crm.customer.business.ISelectAddressManager;
import com.kfwy.hkp.crm.customer.entity.SelectAddressEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
* @Description 描述: 选址报告
* @Auth xpp
* @Date 2018/11/27 10:57
* @Version 1.0
* @Param
* @return
**/
@RestController
@RequestMapping(path = "/selectaddress")
public class SelectAddressService extends SpringRestService {

    @Autowired
    private ISelectAddressManager selectAddressManager;


    /**
     * 创建选址报告
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody SelectAddressRequest request) {

        SelectAddressResponse response = new SelectAddressResponse();

        String curUserCode = CurrentContext.getUserCode();
        if(curUserCode == null){
            throw new RemoveStackBusinessException ("用户信息为空,请重新登陆."+curUserCode);
        }
        //selectAddressManager.create(request.getEntity());

        SelectAddressEntity saEntity = new SelectAddressEntity();
        saEntity = selectAddressManager.createAddress(request.getEntity());

        response.setResult(saEntity);
        return this.success(response);
    }


    /**
     * 创建选址报告
     * @param request
     * @return
     */
    @RequestMapping(path = "/createNew",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createNew(@RequestBody SelectAddressRequest request) {

        SelectAddressResponse response = new SelectAddressResponse();

        String curUserCode = CurrentContext.getUserCode();
        if(curUserCode == null){
            throw new RemoveStackBusinessException("用户信息为空,请重新登陆."+curUserCode);
        }
        SelectAddressEntity saEntity = new SelectAddressEntity();
        saEntity = selectAddressManager.createNewAddress(request.getEntity());

        response.setResult(saEntity);
        return this.success(response);
    }


    /**
     * 官网查询选址报告
     * @param request
     * @return
     */
    @RequestMapping(path = "/selectHos",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectHos(@RequestBody SelectAddressRequest request) {

        SelectAddressResponse response = new SelectAddressResponse();

        SelectAddressEntity saEntity = new SelectAddressEntity();
        saEntity = selectAddressManager.selectHos(request.getEntity());

        response.setResult(saEntity);
        return this.success(response);
    }

    /**
     * 官网查询选址报告
     * @param request
     * @return
     */
    @RequestMapping(path = "/mbwebquery",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> mbwebquery(@RequestBody SelectAddressRequest request) {

        SelectAddressResponse response = new SelectAddressResponse();

        SelectAddressEntity saEntity = new SelectAddressEntity();
        saEntity = selectAddressManager.mbwebquery(request.getEntity());

        response.setResult(saEntity);
        return this.success(response);
    }

    /**
     * web端选址报告集合查询
     * @param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody SelectAddressRequest request) {

        SelectAddressResponse response = new SelectAddressResponse();

        List<SelectAddressEntity> result = selectAddressManager.query(request.getEntity().getCusCode());

        response.setResult(result);
        return this.success(response);
    }
}

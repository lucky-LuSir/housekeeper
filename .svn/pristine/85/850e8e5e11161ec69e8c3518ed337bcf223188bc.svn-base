package com.kfwy.hkp.controller.crm.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerApplyServiceRequest;
import com.kfwy.hkp.controller.crm.customer.vo.CustomerApplyServiceResponse;
import com.kfwy.hkp.crm.customer.business.ICustomerApplyManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import com.kfwy.hkp.sys.user.enums.UserType;
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
 * Create By hjh on 2018/8/11
 */

@RestController
@RequestMapping(path = "/cusApply")
public class CustomerApplyService extends SpringRestService {

    @Autowired
    private ICustomerApplyManager customerApplyManager;
    @Autowired
    private ICustomerDbDao customerDbDao;


    /**
     * 新增申请
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerApplyServiceRequest request) {

        CustomerApplyServiceResponse response = new CustomerApplyServiceResponse();
        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        if (user.getUserLevel().equals(UserLevel.K2)
                || StringUtils.isEmpty(user.getUserLevel())){
            throw new RemoveStackBusinessException ("跳转到付费引导样式A");
        }

        CustomerEntity customerEntity = customerDbDao.detail(request.getEntity().getCusCode());
        if (customerEntity == null) {
            throw new RemoveStackBusinessException("该客户不存在!");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("empCode", CurrentContext.getUserCode());
        map.put("cusCode", request.getEntity().getCusCode());
        response.setResult("申请-客户-完毕,app端重新调详情接口!");
        customerApplyManager.create(request.getEntity());
        return this.success(response, customerApplyManager.checkApplyCountStatistics());
    }

}

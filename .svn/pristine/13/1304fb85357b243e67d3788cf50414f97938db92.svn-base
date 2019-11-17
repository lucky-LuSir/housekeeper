/*
package com.kfwy.hkp.controller.payment;

import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.utils.IpUtil;
import com.kfwy.hkp.controller.payment.vo.PaymentServiceRequest;
import com.kfwy.hkp.controller.payment.vo.PaymentServiceResponse;
import com.kfwy.hkp.payment.business.IPaymentManager;
import com.kfwy.hkp.payment.entity.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 *//*

@RestController
@RequestMapping("/payment")
public class PaymentService extends SpringRestService {
    private final static Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    private IPaymentManager paymentManager;

    @RequestMapping(path = "/pay",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> pay(@RequestBody PaymentServiceRequest request){
        PaymentServiceResponse response = new PaymentServiceResponse();
        String createIp =  IpUtil.getIpAddr();

        PaymentEntity paymentEntity = request.getEntity();
        String payType = request.getPayType();
        String payResult = paymentManager.pay(payType, paymentEntity);
        System.out.println(payResult);
        return this.success(response,payResult);
    }

}
*/

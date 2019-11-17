/*
package com.kfwy.hkp.controller.payment;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.payment.vo.NotifyServiceResponse;
import com.kfwy.hkp.payment.business.IPaymentManager;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/paymentNotify")
public class AliPayNotifyService extends SpringRestService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AliPayNotifyService.class);

    @RequestMapping(path = "/alipayNotify",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> alipayNotify(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        NotifyServiceResponse response = new NotifyServiceResponse();

        logger.debug("=========开始进入订单回调==========");

        //TODO 支付包成功回调，设置订单状态为支付成功，
        // 如果是VIP，设置用户状态VIP

        //获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = req.getParameterMap();

        logger.info("-------------alipay 回调参数:" + requestParams + "--------------");


        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        logger.info("----------alipay 回调参数解析:" + params + "-----------------------");

//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        try {
            boolean flag = AlipaySignature.rsaCheckV1(params, IPaymentManager.ALI_PUBLIC_KEY, IPaymentManager.ALI_CHARSET, IPaymentManager.ALI_SIGN_TYPE);

            logger.info("------------alipay 回调参数校验flag="+flag);

            if (flag){


            }

        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new BusinessException("支付宝回调认证数据认证异常!");
        }

        return this.success(response);
    }
}
*/

/*
package com.kfwy.hkp.payment.business.impl;


import cn.hutool.json.JSONArray;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.utils.JsonMapper;
import com.kfwy.hkp.common.utils.IpUtil;
import com.kfwy.hkp.common.utils.WXUtils;
import com.kfwy.hkp.payment.business.IPaymentManager;
import com.kfwy.hkp.payment.entity.PaymentEntity;
import com.kfwy.hkp.payment.enums.PaymentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service
public class PaymentManagerImpl implements IPaymentManager {

    private final Logger logger = LoggerFactory.getLogger(PaymentManagerImpl.class);


    @Override
    @Transactional(rollbackFor = Exception.class)
    public String createAndPayOrder(String odType, String payType, BigDecimal payMoney, String gdCode) {
        return "";
    }

    @Override
    public String queryPayInfo(String paymentType, String tradeNo, String outTradeNo) {

        AlipayClient alipayClient = new DefaultAlipayClient(
                IPaymentManager.ALI_SERVER_URL,
                IPaymentManager.ALI_APP_ID,
                IPaymentManager.ALI_PRIVATE_KEY,
                IPaymentManager.ALI_FORMAT,
                IPaymentManager.ALI_CHARSET,
                IPaymentManager.ALI_PUBLIC_KEY,
                IPaymentManager.ALI_SIGN_TYPE); //获得初始化的AlipayClient
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类

        JSONArray array = setJsonArray(tradeNo, outTradeNo);

        request.setBizContent(array.toString());//设置业务参数
        AlipayTradeQueryResponse response = null;//通过alipayClient调用API，获得对应的response类
        try {
            response = alipayClient.execute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return response.getBody();
    }

    public JSONArray setJsonArray(String tradeNo, String outTradeNo) {
        List list = new ArrayList();
        if (tradeNo != null) {
            list.add(tradeNo);
        }
        if (outTradeNo!=null){
            list.add(outTradeNo);
        }
        JSONArray array = new JSONArray();
        array.addAll(list);
        return array;
    }

    */
/**
     * 只是支持支付宝与微信的支付
     *
     * @param paymentType
     * @param order
     * @return
     *//*

    @Override
    public String pay(String paymentType, PaymentEntity paymentEntity) {

        logger.info("----支付方式");

        if (paymentType.equals(PaymentType.ZhiFuBao.getCode())) {
            logger.info("----------进入alipay------------");

            return alipay(paymentEntity);

        } else if (paymentType.equals(PaymentType.WeiXin.getCode())) {
            logger.info("------进入wxpay---------------");

            return weixinPay(paymentEntity);

        }

        return "已经余额支付成功";
    }

    public String alipay(PaymentEntity paymentEntity) {
        //选择了支付宝支付
        //实例化客户端
        AlipayClient alipayClient = DefaultAlipayClient
                .builder(IPaymentManager.ALI_SERVER_URL, IPaymentManager.ALI_APP_ID, IPaymentManager.ALI_PRIVATE_KEY)
                .format(IPaymentManager.ALI_FORMAT)
                .charset(IPaymentManager.ALI_CHARSET)
                .alipayPublicKey(IPaymentManager.ALI_PUBLIC_KEY)
                .signType(IPaymentManager.ALI_SIGN_TYPE)
             */
/*   .encryptType("AES")
                .encryptKey("Jm1QBidq2CjJVPWRYYG+sg==")*//*

                .build();

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest req = new AlipayTradeAppPayRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        model.setBody(paymentEntity.getDescription());
        model.setSubject("交易标题");
        model.setOutTradeNo(CodeGenUtils.generate("pay"));
        model.setTimeoutExpress("30m");
        model.setTotalAmount(paymentEntity.getPayMoney().setScale(2, BigDecimal.ROUND_HALF_UP).toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        model.setGoodsType("0");
        req.setBizModel(model);
        req.setNotifyUrl(IPaymentManager.ALI_NOTIFY_URL);
        try {
            logger.info("--------alipay start-------");
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse resp = alipayClient.sdkExecute(req);

            logger.info("------alipay end response:" + resp + "------------");
            //就是orderString 可以直接给客户端请求，无需再做处理。

            return resp.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            throw new BusinessException("调用支付宝支付API异常");
        }

    }

    public String weixinPay(PaymentEntity paymentEntity) {
        //选择了微信支付
        String nonceStr = WXUtils.CreateNoncestr();
        //支付类型
        String body = paymentEntity.getPayType();
        //支付描述
        String detail = paymentEntity.getDescription();
        String orderCode = paymentEntity.getPayCode();
        String totalFee = String.valueOf(paymentEntity.getPayMoney().multiply(new BigDecimal("100")).intValue());

        String notifyUrl = "http://m.kufangwuyou.com/kfwy-mobile/WxPayNotifyServlet.do";
        String tradeType = "APP";


        String createIp = IpUtil.getIpAddr();
        SortedMap<String, String> parameters = new TreeMap<String, String>();
        parameters.put("appid", WX_APP_ID);
        parameters.put("mch_id", WX_MCH_ID);
        parameters.put("attach", "库房币支付");
        parameters.put("nonce_str", nonceStr);
        parameters.put("body", body);
        parameters.put("out_trade_no", orderCode);
        parameters.put("total_fee", totalFee);
        parameters.put("notify_url", notifyUrl);
        parameters.put("trade_type", tradeType);
        parameters.put("spbill_create_ip", createIp);

        logger.info("---------wxpay参数:" + parameters + "------------");

        //获取签名
        String sign = WXUtils.createSign(WX_APP_SECRET, "UTF-8", parameters);
        parameters.put("sign", sign);

        logger.info("-------wxpay签名:" + sign + "---参数:" + parameters + "-------------");
        String req = WXUtils.getRequestXml(parameters);

        logger.info("--wx post 参数:" + req + "-----------");

        String result = WXUtils.httpsRequest(IPaymentManager.WX_SERVER_URL, "POST", req);

        logger.info("wx response :" + result);

        Map<String, String> map = WXUtils.doXMLParse(result);

        logger.info("----wx reponse 解析:" + map + "---");

        if ("FAIL".equals(map.get("result_code")) || "FAIL".equals(map.get("return_code"))) {
            throw new BusinessException(map.get("err_code_des"));
        }
        return map.get("prepay_id");
    }
}
*/

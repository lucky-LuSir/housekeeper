package com.kfwy.hkp.controller.trade.order.utils;

import com.alibaba.fastjson.JSON;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.hkp.trade.order.entity.OrderInvoiceApplyEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 * 调用企查查接口
 */
public class QiChaChaInterface {
    private static Logger logger = LoggerFactory.getLogger(QiChaChaInterface.class);
    private static Properties properties;
    private static String qiChaCha="qiChaCha-url";
    private static String qiChaChaKey="qiChaCha-key";
    private static String qiChaChaSecretKey="qiChaCha-secretKey";

    static {
        try {
            properties = PropertiesLoaderUtils.loadAllProperties("api.properties");
        } catch (IOException e) {
            logger.error("load properties error");
        }
    }

    /**
     * 根据公司名 调用企查查 获取公司税务信息
     * @param cpyName
     * @return
     */
    public static OrderInvoiceApplyEntity getCompany(String cpyName){
        String address = properties.get(qiChaCha).toString()+"?key="+
                    properties.get(qiChaChaKey).toString()+"&keyWord="+cpyName;
        String timeSpan = System.currentTimeMillis()+"";
        String timeSpans = timeSpan.substring(0,timeSpan.length()-3);//精确到秒的当前时间戳
        String token = getToken(properties.get(qiChaChaKey).toString(),timeSpans,properties.get(qiChaChaSecretKey).toString());
        try {
            URL url = new URL(address);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            // 设置超时时间
            connection.setConnectTimeout(3000);
            connection.setRequestProperty("Token",token);
            connection.setRequestProperty("Timespan",timeSpans);
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接

            Map<String,Object> map = JSON.parseObject(sb.toString(), Map.class);
            return getOrderInvoiceApplyEntity(map);
        } catch (Exception e) {
            throw new BusinessException("未查到该公司，请核对公司名称");
        }
    }

    private static OrderInvoiceApplyEntity getOrderInvoiceApplyEntity(Map<String,Object> map){
        Integer status = Integer.parseInt(map.get("Status").toString()) ;
        if(status != 200){
            throw new BusinessException((String) map.get("Message"));
        }
        Map<String,Object> company =(Map<String,Object>) map.get("Result");

        OrderInvoiceApplyEntity orderInvoiceApplyEntity = new OrderInvoiceApplyEntity();
        orderInvoiceApplyEntity.setCpyName((String) company.get("Name"));
        orderInvoiceApplyEntity.setAddress((String) company.get("Address"));
        orderInvoiceApplyEntity.setBankCard((String) company.get("BankAccount"));
        orderInvoiceApplyEntity.setBankBranch((String) company.get("Bank"));
        orderInvoiceApplyEntity.setPhone((String) company.get("Tel"));
        orderInvoiceApplyEntity.setCreditCode((String) company.get("CreditCode"));
        return orderInvoiceApplyEntity;
    }

    /**
     * 获取md5 制作的token
     * @param key 用户key
     * @param time 精确到秒的时间戳
     * @param secretKey 密钥
     * @return
     */
    public static String getToken(String key,String time,String secretKey){
        String token= DigestUtils.md5Hex(key + time+secretKey).toUpperCase();
        return token;
    }
}

package com.kfwy.hkp.common.sms.sendNew.impl;

import com.kfwy.hkp.common.sms.SmsEntity;
import com.kfwy.hkp.common.sms.sendNew.IHXTSmsTemplate;
import com.kfwy.hkp.common.sms.sendNew.SmsClientAccessTool;
import com.kfwy.hkp.common.sms.sendNew.XMLParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by moyi on 15-8-4.
 * 15-8-4
 */
public class HXTSmsTemplateImpl implements IHXTSmsTemplate {

    private static Logger logger = LoggerFactory.getLogger(HXTSmsTemplateImpl.class);

    private static final String USER_ID = "25642";

    private static final String ACCOUNT = "kufang";

    private static final String PASSWORD = "kufang";

    @Override
    public Map<String, Object> codeSender(SmsEntity entity) {
        Map<String, Object> map = this.send(entity);
        if (map.get("statusCode").equals("000000")) {
            return (Map<String, Object>) map.get("data");
        } else {
            logger.error("错误编码{},错误信息={}", map.get("statusCode"), map.get("statusMsg"));
            return null;
        }
    }


    @Override
    public Map<String, Object> send(SmsEntity se) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {

            String content = "【库房无忧】" + se.getSmsContent();
            String codingType = null, backEncodType = null, action = null;
            if (codingType == null || codingType.equals("")) {
                codingType = "UTF-8";
            }
            if (backEncodType == null || backEncodType.equals("")) {
                backEncodType = "UTF-8";
            }
            StringBuffer send = new StringBuffer();
            if (action != null && !action.equals("")) {
                send.append("action=").append(action);
            } else {
                send.append("action=send");
            }

            send.append("&userid=").append(USER_ID);
            send.append("&account=").append(URLEncoder.encode(ACCOUNT, codingType));
            send.append("&password=").append(URLEncoder.encode(PASSWORD, codingType));
            send.append("&mobile=").append(se.getTo());
            send.append("&content=").append(URLEncoder.encode(content, codingType));

            String sendResult = SmsClientAccessTool.getInstance().doAccessHTTPPost
                    ("http://www.lcqxt.com/sms.aspx", send.toString(), backEncodType);
            map = XMLParser.getMapFromXML(sendResult);
        } catch (Exception e) {
            map.put("returnstatus", "false");
            map.put("message", "转化类型出错");
            e.printStackTrace();
        }
        return map;
    }

}

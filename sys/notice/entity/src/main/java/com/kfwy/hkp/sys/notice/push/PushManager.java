package com.kfwy.hkp.sys.notice.push;

import com.kfwy.hkp.sys.notice.entity.NoticeEntity;
import com.kfwy.hkp.sys.notice.push.android.AndroidListcast;
import com.kfwy.hkp.sys.notice.push.android.AndroidUnicast;
import com.kfwy.hkp.sys.notice.push.ios.IOSListcast;
import com.kfwy.hkp.sys.notice.push.ios.IOSUnicast;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by kfwy_it_010 on 2018/4/28.
 */
public class PushManager {

    public PushManager () {

    }

    public PushManager (String title, String content) {
        this.title = title;
        this.content = content;
    }

    public PushManager (String title, String content, String extra_field_key, String extra_field_value) {
        this.title = title;
        this.content = content;
        this.extra_field_key = extra_field_key;
        this.extra_field_value = extra_field_value;
    }

    public String title = "库房无忧提醒";
    public String content = "找仓库 找厂房 找库房无忧！";
    public String extra_field_key = "";
    public String extra_field_value = "";

    public void setExtraField(String key, String value) {
        this.extra_field_key = key;
        this.extra_field_value = value;
    }

    public enum PushType {

        unicast,//单播
        listcast,//列播
        broadcast,//广播
        groupcast//组播

    }

    PushClient client = new PushClient();

    AndroidUnicast androidUnicast;
    IOSUnicast iosUnicast;
    AndroidListcast androidListcast;
    IOSListcast iosListcast;

    {
        try{

            androidUnicast = new AndroidUnicast(PushConfig.android_app_key, PushConfig.android_app_secret);
            iosUnicast = new IOSUnicast(PushConfig.ios_app_key, PushConfig.ios_app_secret);
            androidListcast = new AndroidListcast(PushConfig.android_app_key, PushConfig.android_app_secret);
            iosListcast = new IOSListcast(PushConfig.ios_app_key, PushConfig.ios_app_secret);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void push( PushType pushType, String device_tokens) {

        if (PushType.unicast.equals(pushType)) {
            unicast(device_tokens);
        } else if (PushType.listcast.equals(pushType)) {
            listcast(device_tokens);
        }

    }

    public void pushByOperateType( PushType pushType, String device_tokens,NoticeEntity notice) {

        if (PushType.unicast.equals(pushType)) {
            unicastByOperateType(device_tokens);
        } else if (PushType.listcast.equals(pushType)) {
            listcast(device_tokens);
        }

    }

    /**
     * 单播
     * @param device_token 设备token
     */
    public void unicastByOperateType(String device_token) {
        try{
            if(PushConfig.android_token_length == device_token.length()) {

                androidUnicast.setDeviceToken(device_token);
                androidUnicast.setTicker(title);
                androidUnicast.setTitle(title);
                androidUnicast.setText(content);
                androidUnicast.goAppAfterOpen();
                androidUnicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);

                androidUnicast.setSound("pushtocusa");
                // TODO Set 'production_mode' to 'false' if it's a test device.
                // For how to register a test device, please see the developer doc.
                androidUnicast.setProductionMode();
                // Set customized fields
                androidUnicast.setExtraField(extra_field_key, extra_field_value);
                client.send(androidUnicast);

            }else if(PushConfig.ios_token_length == device_token.length()) {

                iosUnicast.setDeviceToken(device_token);
                iosUnicast.setAlert(title);
                iosUnicast.setBadge( 0);
                iosUnicast.setSound("pushtocusa.mp3");
                // TODO set 'production_mode' to 'true' if your app is under production mode
                iosUnicast.setProductionMode();
                //iosUnicast.setTestMode();
                // Set customized fields
                iosUnicast.setCustomizedField(extra_field_key, extra_field_value);
                client.send(iosUnicast);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 单播
     * @param device_token 设备token
     */
    public void unicast(String device_token) {
        try{
            if(PushConfig.android_token_length == device_token.length()) {

                androidUnicast.setDeviceToken(device_token);
                androidUnicast.setTicker(title);
                androidUnicast.setTitle(title);
                androidUnicast.setText(content);
                androidUnicast.goAppAfterOpen();
                androidUnicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);

                //androidUnicast.setSound("pushtocusa");
                // TODO Set 'production_mode' to 'false' if it's a test device.
                // For how to register a test device, please see the developer doc.
                androidUnicast.setProductionMode();
                // Set customized fields
                androidUnicast.setExtraField(extra_field_key, extra_field_value);
                client.send(androidUnicast);

            }else if(PushConfig.ios_token_length == device_token.length()) {

                iosUnicast.setDeviceToken(device_token);
                iosUnicast.setAlert(title);
                iosUnicast.setBadge( 0);
                iosUnicast.setSound("default");
                // TODO set 'production_mode' to 'true' if your app is under production mode
                iosUnicast.setProductionMode();
                //iosUnicast.setTestMode();
                // Set customized fields
                iosUnicast.setCustomizedField(extra_field_key, extra_field_value);
                client.send(iosUnicast);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列播
     * @param device_tokens 设备token （不能超过500个，多个device_token用英文逗号分隔）
     */
    public void listcast(String device_tokens) {

        String[] tokenArray = device_tokens.split(",");
        String androidTokens = "";
        String iosTokens = "";
        for (String token : tokenArray) {
            if(PushConfig.android_token_length == token.length()){
                androidTokens += token + ",";
            }else if(PushConfig.ios_token_length == token.length()){
                iosTokens += token + ",";
            }
        }


        try{
            if(StringUtils.isNotEmpty(androidTokens)) {

                androidTokens = androidTokens.substring(androidTokens.length() - 1, androidTokens.length());

                androidListcast.setDeviceToken(androidTokens);
                androidListcast.setTicker(title);
                androidListcast.setTitle(title);
                androidListcast.setText(content);
                androidListcast.goAppAfterOpen();
                androidListcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
                // TODO Set 'production_mode' to 'false' if it's a test device.
                // For how to register a test device, please see the developer doc.
                androidListcast.setProductionMode();
                // Set customized fields
                androidListcast.setExtraField(extra_field_key, extra_field_value);
                client.send(androidListcast);

            }else if(StringUtils.isNotEmpty(iosTokens)) {

                iosTokens = iosTokens.substring(iosTokens.length() - 1, iosTokens.length());

                iosListcast.setDeviceToken(iosTokens);
                iosListcast.setAlert(title);
                iosListcast.setBadge( 0);
                iosListcast.setSound("default");
                // TODO set 'production_mode' to 'true' if your app is under production mode
                iosListcast.setProductionMode();
                // Set customized fields
                iosListcast.setCustomizedField(extra_field_key, extra_field_value);
                client.send(iosListcast);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

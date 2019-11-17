package com.kfwy.hkp.common.api;





import cn.hutool.core.codec.Base64;
import com.kfwy.hkp.common.utils.MD5;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * liwensihan
 */
public class RoLianYunApi {

    public static String ACCOUNTID = "N00000017111";

    public static String APISecret = "b2ff8f80-da81-11e9-bcb0-2db5da8a05bc";


    //通话记录查询apiUrl
    public static String URL ="https://apis.7moor.com/v20180426/cdr/getCCCdr/"+ACCOUNTID+"?sig=";


    /**
     * post方法
     * @param realUrl
     * @param o
     * @return
     */
    public static String Post(String realUrl, String  o,String authorization){
        HttpPost httpPost = new HttpPost (realUrl);
       CloseableHttpClient client = HttpClients.createDefault();
                String respContent = null;

                 StringEntity entity = new StringEntity (o,"utf-8");//解决中文乱码问题
                 entity.setContentEncoding("UTF-8");
                 entity.setContentType("application/json");
                 httpPost.setEntity(entity);
                 httpPost.addHeader ("Authorization",authorization);
                 System.out.println();

        HttpResponse resp = null;
        try {
            resp = client.execute(httpPost);
            if(resp.getStatusLine().getStatusCode() == 200) {
                HttpEntity he = resp.getEntity();
                respContent = EntityUtils.toString(he,"UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

       return respContent;
    }

    /**
     * get方法
     * @param realUrl
     * @return
     */
    public static String Get(String realUrl) {
        try {
            URL url = new URL (realUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 在连接之前设置属性

            // Content-Type实体头用于向接收方指示实体的介质类型，指定HEAD方法送到接收方的实体介质类型，或GET方法发送的请求介质类型
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            // 设置打开与此URLConnection引用的资源的通信链接时使用的指定超时值（以毫秒为单位）
            conn.setConnectTimeout(10000);
            // 将读取超时设置为指定的超时时间，以毫秒为单位。
            // conn.setReadTimeout(60000);
            conn.setRequestMethod("GET");
            // Post 请求不能使用缓存
            conn.setUseCaches(false);

            // 建立连接
            conn.connect();
            // 获取响应
            BufferedReader reader = new BufferedReader (new InputStreamReader (conn.getInputStream()));
            String line;
            String result = "";
            while ((line = reader.readLine()) != null) {
                result += line;
            }
            reader.close();
            conn.disconnect();
            return result;
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }


    public static String generateHHTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(date);
    }




    public static void main(String[]arge) throws JSONException {
        String timeUnix = RoLianYunApi.generateHHTime (new Date ());
        String SIG =  MD5.MD532 (ACCOUNTID+APISecret+timeUnix);
        String authorization =Base64.encode(ACCOUNTID +":"+timeUnix);
        JSONObject result;
        JSONObject entity = new JSONObject ();
        entity.put ("beginTime","2019-09-24 00:00:00");
        entity.put ("endTime",new Date ());
        result = new JSONObject (RoLianYunApi.Post (URL + SIG, entity.toString (), authorization));
        JSONArray data = result.getJSONArray ("data");
        if (data!=null && data.toList ().size ()>0){
            for (Object o : data.toList ()) {
                HashMap map = (HashMap) o;
                System.out.println ("-----------------------------------");
                System.out.println ("录音地址:"+map.get ("FILE_SERVER")+"/"+map.get ("RECORD_FILE_NAME"));
                System.out.println ("主叫号码:"+map.get ("CALL_NO"));
                System.out.println ("被叫号码:"+map.get ("CALLED_NO"));
                System.out.println ("呼叫发起时间："+map.get ("OFFERING_TIME"));
                System.out.println ("开始时间："+map.get ("BEGIN_TIME"));
                System.out.println ("通话时长："+map.get ("CALL_TIME_LENGTH"));
                System.out.println ("结束时间:"+map.get ("END_TIME"));
                System.out.println ("呼叫类型："+map.get ("CONNECT_TYPE"));
                System.out.println ("处理状态："+map.get ("STATUS"));
                System.out.println ("处理座席工号："+map.get ("EXTEN"));
                System.out.println ("来电进入技能组的时间："+map.get ("QueueTime"));
                System.out.println ("省："+map.get ("PROVINCE"));
                System.out.println ("市："+map.get ("DISTRICT"));
                System.out.println ("满意度调查:"+map.get ("INVESTIGATE"));
                System.out.println ("客户名称："+map.get ("CUSTOMER_NAME"));
                System.out.println ("-----------------------------------");
            }
        }
    }
}

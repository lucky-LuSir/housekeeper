package com.kfwy.hkp.common.api;





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

/**
 * liwensihan
 */
public class QiYeWeiXinApi {
    //企业ID
    public static String CORPID="wwf67836072640801e";
    //通讯录同步的Secret
    public static String CORPSECRET="s_eTau876BUhdBsIvbFrDRhS8tyfC6lDGafAQrYau6U";
    //查询access_token
    public static String GETACCESSTOKENURL="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+CORPID+"&corpsecret="+CORPSECRET+"";
    //查询部门的链接
    public static String getDepetIdURL="https://qyapi.weixin.qq.com/cgi-bin/department/list?";
    //删除人员的链接
    public static String deletedUrl="https://qyapi.weixin.qq.com/cgi-bin/user/delete?";
    //创建人员的链接
    public static String createUrl="https://qyapi.weixin.qq.com/cgi-bin/user/create?";
    //修改人员的链接
    public static String updateUrl="https://qyapi.weixin.qq.com/cgi-bin/user/update?";

    /**
     * 删除人员方法
     * @param userId  人员工号
     */
    public static void deletedUser(String userId) {
        String realUrl = deletedUrl+getAccessToken()+"&userid="+userId+"";
        QiYeWeiXinApi.Get(realUrl);
    }

    /**
     * 创建人员方法
     * @param parameter 参数
     */
    public static void createUser(String parameter) {
        String realUrl = createUrl+getAccessToken();
        QiYeWeiXinApi.Post(realUrl,parameter);
    }

    public static void updateUser(String parameter){
        String realUrl = updateUrl + getAccessToken();
        QiYeWeiXinApi.Post(realUrl,parameter);
    }
    /**
     * 获取access_token
     * @return
     */
    public static String getAccessToken(){
        QiYeWeiXinApi.Get(GETACCESSTOKENURL);
        JSONObject json = null;
        String access_token=null;
        try {
            json = new JSONObject (QiYeWeiXinApi.Get(GETACCESSTOKENURL));
             access_token = "access_token="+json.getString("access_token");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return access_token;
    }

    /**
     * 获取部门ID
     * @param deptName
     * @return
     */
    public static String getDeptId(String deptName){
        String realUrl = getDepetIdURL+getAccessToken()+"";
        JSONObject json = null;
        String deptId=null;
        try {
            json = new JSONObject (QiYeWeiXinApi.Get(realUrl));
            String department=json.getString("department");

            JSONArray array = new JSONArray (department);

            for(int i=0;i<array.length();i++){
                JSONObject json2 =array.getJSONObject(i);
                String name=json2.getString("name");
                if(name.equals(deptName)){
                    deptId=json2.getString("id");
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return deptId;
    }

    /**
     * post方法
     * @param realUrl
     * @param o
     * @return
     */
    public static String Post(String realUrl, String o){
        HttpPost httpPost = new HttpPost (realUrl);
       CloseableHttpClient client = HttpClients.createDefault();
                String respContent = null;

                 StringEntity entity = new StringEntity (o,"utf-8");//解决中文乱码问题
                 entity.setContentEncoding("UTF-8");
                 entity.setContentType("application/json");
                 httpPost.setEntity(entity);
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





    public static void main(String[]arge) throws JSONException {

    }
}

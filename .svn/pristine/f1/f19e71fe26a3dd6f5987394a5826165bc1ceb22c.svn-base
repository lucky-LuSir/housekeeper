package com.kfwy.hkp.common.sms;

import com.kfwy.hkp.common.utils.SecurityUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;

/**
 * Created by davidcun on 2017/11/7.
 */
@Deprecated
public class KuaiXunSmsSender {

    //供应商参数
    public static final String gid = "975a8f7";
    public static final String url = "http://jk.106api.cn/smsUTF8.aspx";
    public static final String username = "kx2382920016";
    public static final String psw = "Kfwy2017";

    //带看录入后发送给业主的模版
    public static final String Template_1000 ="d9df0a9a";
    public static final String Template_1005="ef5bbbd7";
    //public static final String Template_1000 = "【库房无忧】尊敬的%s，选址经理%s已完成客户带看，微信关注“库房无忧”或点击www.kufangwuyou.com了解代办环评 、代运营等企业服务，客服电话4008202058 退订回T";
   // public static final String Template_1005="【库房无忧】尊敬的%s，“库房无忧”APP可资源变现，推荐房源、客户信息成交后可拿该单佣金20%-40%，详情请在应用宝下载APP查看，客服电话4008202058 退订回T";
    //public static final String Template_1000 = "【库房无忧】尊敬的张总，选址经理寸代永（13917182631）已完成客户带看，微信关注“库房无忧”或点击www.kufangwuyou.com了解代办环评 、代运营等企业服务，客服电话4008202058 退订回T";
    //带看录入后发送给客户的模版
    public static final String Template_1002 = "ded9708c";
    public static final String Template_1004 = "d70f05a9";

    //public static final String Template_1002 = "【库房无忧】尊敬的李总，选址经理寸代永（13917182631）已陪同您完成带看，微信关注“库房无忧”或点击www.kufangwuyou.com了解更多房源信息和租金分期、代办环评等企业服务，客服电话4008202058";
    //创建房源之后给业主发送的短信模版
    public static final String Template_1003 = "e201ff2b";

     //客户录入后给客户发送的短信模板
    public static final String Template_1006="6283ea6c";
    public static final String Template_1007="d71350b2";
    //public static final String Template_1004 = "【库房无忧】尊敬的%s，选址经理左胜辉（18702186324）正在快马加鞭的为您匹配适合的房源，微信关注“库房无忧”或点击www.kufangwuyou.com查看更多仓库/厂房,地图找房全新体验";


//    public static String

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {


        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static String sendPost(String tmp, String[] param, String[] phones) {
        String ps = "";

        for (int i = 0; i < phones.length; i++) {
            ps += phones[i];
            if (i != phones.length) {
                ps += ",";
            }
        }
        return sendPost(tmp,param,ps);
    }

    public static String sendPost(String content, String[] param, String phones) {

        if (param!=null && param.length>0){
            String[] test={""};
            String[] jj =  ArrayUtils.addAll(test, param);
            for(int i=0;i<jj.length;i++){
                System.out.println("+++++++++++"+jj[i]);
            }
            content = MessageFormat.format(content, jj);
        }

        String postData = "type=send&username="+username+"&password="+ SecurityUtils.md5(psw)+"&gwid="+gid+"&mobile=" + phones + "&message=" + content + "";

        return sendPost(url,postData);

    }


    //调用方式
    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.printf(sendPost(Template_1000,new String[]{"寸代永"},new String[]{"17717652169"}));
    }

}

package com.kfwy.hkp.common.qiYeWeChat.utils;

public class WeChatUrlData {

    /**
     *  企业Id
     */
    private String corpid;
    /**
     * secret管理组的凭证密钥
     */
    private String corpsecret;
    /**
     * 获取ToKen的请求
     */
    private String Get_Token_Url;
    /**
     * 发送消息的请求
     */
    private String SendMessage_Url;
    public String getCorpid() {
        return corpid;
    }
    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }
    public String getCorpsecret() {
        return corpsecret;
    }
    public void setCorpsecret(String corpsecret) {
        this.corpsecret = corpsecret;
    }
    public void setGet_Token_Url(String corpid,String corpsecret) {
        this.Get_Token_Url ="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+corpid+"&corpsecret="+corpsecret;
    }
    public String getGet_Token_Url() {
        return Get_Token_Url;
    }
    public String getSendMessage_Url(){
        SendMessage_Url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";
        return SendMessage_Url;
    }
}

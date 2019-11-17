package com.kfwy.hkp.sys.notice.push.ios;


import com.kfwy.hkp.sys.notice.push.IOSNotification;

public class IOSUnicast extends IOSNotification {
	public IOSUnicast(String appkey, String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);
    }
}

package com.kfwy.hkp.sys.notice.push.ios;


import com.kfwy.hkp.sys.notice.push.IOSNotification;

public class IOSFilecast extends IOSNotification {
	public IOSFilecast(String appkey, String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "filecast");	
	}
	
	public void setFileId(String fileId) throws Exception {
    	setPredefinedKeyValue("file_id", fileId);
    }
}

package com.kfwy.hkp.sys.version.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gniuu.framework.dataaccess.redis.AbstractRedisDao;
import com.gniuu.framework.exception.IllegalArgsException;
import com.kfwy.hkp.sys.version.dao.IAppInfoRedisDao;
import com.kfwy.hkp.sys.version.entity.AppInfoEntity;
import com.kfwy.hkp.sys.version.enums.ClientType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;

/**
 * Created by davidcun on 2015-8-26.
 *
 * @author davidcun
 */

@Repository
public class AppInfoRedisDaoImpl extends AbstractRedisDao<AppInfoEntity> implements IAppInfoRedisDao {


    @Value("${hkp.android.update.url}")
    private String hkpAndroidUpdateUrl;

    @Value("${hkp.ios.update.url}")
    private String hkpIosUpdateUrl;

    @Override
    public AppInfoEntity getAppInfo(String type) {
        return this.getAppInfo(AppY.HKP_APP.getCode(), type);
    }

    @Override
    public void saveAppInfo(AppInfoEntity appInfo) {
        this.saveAppInfo(AppY.HKP_APP.getCode(), appInfo);
    }

    @Override
    public AppInfoEntity getOfficeAppInfo(String type) {
        return this.getAppInfo(AppY.HKP_APP.getCode(), type);
    }

    @Override
    public void saveOfficeAppInfo(AppInfoEntity appInfo) {
        this.saveAppInfo(AppY.HKP_APP.getCode(), appInfo);
    }

    /**
     * 获取app信息
     *
     * @param y
     * @param type
     * @return
     */
    protected AppInfoEntity getAppInfo(int y, String type) {
        AppInfoEntity appInfoEntity = null;
        try {
            String result = this.valueOps.get(this.getKey(y, type));
            if (result == null) {
                return this.initAppInfo(y, type);
            } else {
                appInfoEntity = parseEntity(this.getKey(y, type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appInfoEntity;
    }

    /**
     * 保存app信息
     *
     * @param y
     * @param appInfo
     * @return
     */
    protected void saveAppInfo(int y, AppInfoEntity appInfo) {
        try {
            saveEntity(this.getKey(y, String.valueOf(appInfo.getClientType())), appInfo);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取app信息的key
     *
     * @param y
     * @param type
     * @return
     */
    protected String getKey(int y, String type) {
        return appInfo(y, type);
    }

    /**
     * 初始化app基本信息
     *
     * @param y
     * @return
     */
    protected AppInfoEntity initAppInfo(int y, String type) {
        AppInfoEntity appInfo = new AppInfoEntity();
        appInfo.setVersion("1.0.0");
        appInfo.setLow(0);
        appInfo.setMidle(0);
        appInfo.setHight(1);
        appInfo.setDescription("部分bug优化");


        if (type.equals(String.valueOf(ClientType.Android))) {
            appInfo.setClientType(ClientType.Android);
            appInfo.setUpdateUrl(hkpAndroidUpdateUrl);

        } else if (type.equals(String.valueOf(ClientType.IOS))) {
            appInfo.setClientType(ClientType.IOS);
            appInfo.setUpdateUrl(hkpIosUpdateUrl);
        }
        //保存默认版本信息
        this.saveAppInfo(y, appInfo);
        return appInfo;
    }

    public static String appInfo(int y, String type) {
        StringBuffer sb = new StringBuffer("ky:hkp");
        switch (y) {
            case 3:
                sb.append(":app");
                break;
            case 2:
                sb.append(":office");
                break;
            default:
                sb.append(":info");
        }
        switch (type) {
            case "1":
                sb.append(":android");
                break;
            case "2":
                sb.append(":ios");
                break;
        }
        return sb.toString();
    }

    /**
     * 参数Y的枚举值
     */
    private enum AppY {

        KFWY(1, "库房无忧APP"),
        KFWY_OFFICE(2, "库房无忧移动办公"),
        HKP_APP(3, "库房管家");

        private int code;
        private String name;

        private AppY(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public static AppY getStatus(int code) {
            for (AppY status : AppY.values()) {
                if (status.getCode() == code) {
                    return status;
                }
            }
            throw new IllegalArgsException("未能找到匹配的AppY:" + code);
        }
    }

}

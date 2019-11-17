package com.kfwy.hkp.controller.version;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.controller.version.vo.AppServiceRequest;
import com.kfwy.hkp.controller.version.vo.AppServiceResponse;
import com.kfwy.hkp.sys.version.business.IAppManager;
import com.kfwy.hkp.sys.version.entity.AppInfoEntity;
import com.kfwy.hkp.sys.version.enums.ClientType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO service 抽出公用service
 * Created by davidcun on 2015-8-26.
 *
 * @author davidcun
 */

@RestController
@RequestMapping(path = "/appService")
public class AppServiceImpl extends SpringRestService {


    @Autowired
    private IAppManager appManager;
    @IgnoreLog
    @RequestMapping(path = "/getVersion", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> getVersionInfo(@RequestBody AppServiceRequest request) {
        AppServiceResponse response = new AppServiceResponse();
        AppInfoEntity appInfo = appManager.getEmpAppInfo(DictionaryStorage.get(ClientType.class.getName(),String.valueOf(request.getType())).getCode());

        response.setResult(appInfo);
        return success(response);
    }
    @IgnoreLog
    @RequestMapping(path = "/checkUpdate", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> checkUpdate(@RequestBody AppServiceRequest request) {

        AppServiceResponse response = new AppServiceResponse();

        AppInfoEntity appInfo = appManager.getEmpAppInfo(DictionaryStorage.get(ClientType.class.getName(),String.valueOf(request.getType())).getCode());

        String version = request.getVersion();

        Integer type = request.getType();

        if (StringUtils.isEmpty(version) || type == null) {
            throw new BusinessException("参数版本号不能为空");
        }else if (version.equals("2.4.0")){
            throw new BusinessException("当前版本过低，请通过二维码重新下载最新版本!");
        }
        String[] vs = version.split("\\.");
        String[] ints = new String[]{"0","0","0"};
        if (vs.length == 0) {
            ints[0] = "1";
            ints[1] = "0";
            ints[2] = "0";
        } else if (vs.length == 1) {
            ints[0] = vs[0];
            ints[1] = "0";
            ints[2] = "0";
        } else if (vs.length == 2) {
            ints[0] = vs[0];
            ints[1] = vs[1];
            ints[2] = "0";
        }else{
            ints[0] = vs[0];
            ints[1] = vs[1];
            ints[2] = vs[2];
        }

        int hight = Integer.valueOf(ints[0]);
        int midle = Integer.valueOf(ints[1]);
        int low = Integer.valueOf(ints[2]);

        boolean need = false;
        if (appInfo.getHight() > hight) {
            need = true;
        } else if (appInfo.getHight() == hight) {
            if (appInfo.getMidle() > midle) {
                need = true;
            } else if (appInfo.getMidle() == midle) {
                if (appInfo.getLow() > low) {
                    need = true;
                }
            }
        }

        AppInfo res = new AppInfo();
        if (need) {
            res.setIsNeedUpdate(true);
            res.setUrl(appInfo.getUpdateUrl());
            res.setDescription(appInfo.getDescription());
            res.setVersion(appInfo.getVersion());
        } else {
            res.setIsNeedUpdate(false);
        }

        response.setResult(res);
        return this.success(response);
        //return response;
    }

    private class AppInfo{
        /**
         * 是否需要更新
         */
        private Boolean isNeedUpdate;

        /**
         * 服务端版本
         */
        private String version;

        /**
         * 更新URL（1-Android，2-IOS)
         */
        private String url;

        /**
         * 更新描述
         */
        private String description;

        public Boolean getIsNeedUpdate() {
            return isNeedUpdate;
        }

        public void setIsNeedUpdate(Boolean needUpdate) {
            isNeedUpdate = needUpdate;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}

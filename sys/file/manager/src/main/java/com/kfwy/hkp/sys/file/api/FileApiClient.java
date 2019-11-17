package com.kfwy.hkp.sys.file.api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.sys.file.entity.FileEntity;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/11/7 16:12
 */
public class FileApiClient {

    public static FileEntity findDetail(String fileCode){

        FileApiRequest request = new FileApiRequest();

        request.setKey("@Housekeeper$");

        FileEntity file = new FileEntity();
        file.setFileCode(fileCode);
        request.setEntity(file);

        FileApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/fileApi/queryOne",
                FileApiResponse.class,"application/json;charset=UTF-8");

        if (response.getIsSuccess()){
            return response.getEntity();
        }

        return null;
    }

    public static void main(String[] args) {
        FileApiClient.findDetail("IMG201811070523a08d");
    }
}

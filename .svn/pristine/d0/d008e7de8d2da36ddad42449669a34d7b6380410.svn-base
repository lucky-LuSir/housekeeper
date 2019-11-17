package com.kfwy.hkp.sys.file.api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;

import java.util.List;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/7.
 */
public class ParkDicDataTransfer {
    public static List<FileRelationEntity> findDept(Integer start, Integer pageSize){

        ParkDicApiRequest request = new ParkDicApiRequest();
        request.setKey("@Housekeeper$");
        request.setStart(start);
        request.setPageSize(pageSize);
        ParkDicApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/fileFileApi/parkDicDataTransfer",
                ParkDicApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getFileList();
        }
        return null;
    }
}

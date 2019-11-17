package com.kfwy.hkp.sys.file.api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.kfwy.hkp.sys.file.entity.FileEntity;

import java.util.List;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/11/29.
 */

public class FileTransfer{

    public static  List<FileEntity> findDept(Integer start, Integer pageSize){

            FileApiRequest request = new FileApiRequest();
            request.setKey("@Housekeeper$");
            request.setStart(start);
            request.setPageSize(pageSize);
            FileApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/fileFileApi/fileTransfer",
                    FileApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getFileList();
        }
        return null;
    }
}

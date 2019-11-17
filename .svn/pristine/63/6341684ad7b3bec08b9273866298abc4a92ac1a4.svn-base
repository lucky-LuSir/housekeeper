package com.kfwy.hkp.hrm.org.post.api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.hrm.org.post.entity.EmpPostEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author liwensihan
 * @version 1.0
 * @description TODO
 * @date 2018/11/16 16:08
 */
public class EmpPostApiClient {
    public static List<EmpPostEntity> findEmpPostAll(){
        EmpPostApiRequest request = new EmpPostApiRequest();
        request.setKey("@Housekeeper$");
        EmpPostApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/empPostApi/queryAll",
                EmpPostApiResponse.class,"application/json;charset=UTF-8");
        if (response.getIsSuccess()){
            return response.getResult().getData();
        }
        return null;
    }
}

package com.kfwy.hkp.hrm.org.employee.api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.common.utils.ApiConfigUtils;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 19:08
 */
public class EmployeeApiClient {

    public static PageResult<EmployeeEntity> findEmployee(String keyword){


        EmployeeApiRequest request = new EmployeeApiRequest();

        request.setKey("@Housekeeper$");

        if (StringUtils.isNotEmpty(keyword)){
            request.setKeyword(keyword);
        }

        EmployeeApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("employeeApi-query"),
                EmployeeApiResponse.class,"application/json;charset=UTF-8");

        if (response.getIsSuccess()){
            return response.getResult();
        }

        return null;
    }

    public static List<EmployeeEntity> findEmployeeAll(){


        EmployeeApiRequest request = new EmployeeApiRequest();

        request.setKey("@Housekeeper$");


        EmployeeApiResponse response = HttpRequestUtils.requestPost(request,ApiConfigUtils.getMessage("employeeApi-queryAll"),
                EmployeeApiResponse.class,"application/json;charset=UTF-8");

        if (response.getIsSuccess()){
            return response.getResult().getData();
        }

        return null;
    }

    public static List<EmployeeEntity> queryByMap(Map<String,Object> param){


        EmployeeApiRequest request = new EmployeeApiRequest();

        request.setKey("@Housekeeper$");
        request.setParam(param);

        EmployeeApiResponse response = HttpRequestUtils.requestPost(request,ApiConfigUtils.getMessage("employeeApi-queryByMap"),
                EmployeeApiResponse.class,"application/json;charset=UTF-8");

        if (response.getIsSuccess()){
            return response.getResult().getData();
        }

        return null;
    }



    public static void main(String[] args) {
        findEmployeeAll();
    }
}

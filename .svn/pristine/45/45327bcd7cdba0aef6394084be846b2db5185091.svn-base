package com.kfwy.hkp.hrm.org.dept.api;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.common.utils.ApiConfigUtils;
import com.kfwy.hkp.hrm.org.dept.dto.DeptTreeDto;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/31 17:44
 */
public class DeptApiClient {


    public static PageResult<DeptEntity> findDept(String keyword, Integer start, Integer pageSize) {

        DeptApiRequest request = new DeptApiRequest();

        request.setStart(start);
        request.setPageSize(pageSize);


        if (StringUtils.isNotEmpty(keyword)) {
            request.setKeyword(keyword);
        }

        request.setKey("@Housekeeper$");

        DeptApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("deptApi-query"),
                DeptApiResponse.class, "application/json;charset=UTF-8");

        if (response.getIsSuccess()) {
            return response.getResult();
        }

        return null;
    }

    public static DeptEntity findDetail(String deptCode) {
        DeptApiRequest request = new DeptApiRequest();

        DeptEntity dept = new DeptEntity();

        dept.setDeptCode(deptCode);
        request.setEntity(dept);

        request.setKey("@Housekeeper$");

        DeptApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("deptApi-detail"),
                DeptApiResponse.class, "application/json;charset=UTF-8");

        if (response.getIsSuccess()) {
            return response.getEntity();
        }

        return null;
    }


    public static DeptEntity syncDeptDetail(String deptCode) {
        DeptApiRequest request = new DeptApiRequest();

        HashMap<String, Object> param = new HashMap<>();
        param.put("deptCode", deptCode);

        request.setKey("@Housekeeper$");
        request.setStart(null);
        request.setPageSize(null);
        request.setParam(param);

        DeptApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("deptApi-sync"),
                DeptApiResponse.class, "application/json;charset=UTF-8");

        if (response.getIsSuccess()) {
            return response.getDeptList() != null && response.getDeptList().size() > 0 ? response.getDeptList().get(0) : null;
        }

        return null;
    }

    public static PageResult<DeptEntity> syncDeptPage(DeptApiRequest request) {

        request.setKey("@Housekeeper$");
        DeptApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("deptApi-sync"),
                DeptApiResponse.class, "application/json;charset=UTF-8");

        return response.getPageDept();
    }

    public static List<DeptEntity> findAll() {
        DeptApiRequest request = new DeptApiRequest();

        DeptEntity dept = new DeptEntity();

        request.setKey("@Housekeeper$");

        DeptApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("deptApi-queryAll"),
                DeptApiResponse.class, "application/json;charset=UTF-8");

        if (response.getIsSuccess()) {
            return response.getResult().getData();
        }

        return null;
    }

    public static List<DeptTreeDto> deptTree(String userCode) {
        DeptApiRequest request = new DeptApiRequest();


        request.setKey("@Housekeeper$");
        request.setEmpCode(userCode);

        DeptApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("deptApi-deptTree"),
                DeptApiResponse.class, "application/json;charset=UTF-8");

        if (response.getIsSuccess()) {
            return response.getResultDto();
        }

        return null;
    }

    public static List<DeptTreeDto> deptAllTree(String userCode) {
        DeptApiRequest request = new DeptApiRequest();

        request.setKey("@Housekeeper$");
        request.setEmpCode(userCode);

        DeptApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("deptApi-deptAllTree"),
                DeptApiResponse.class, "application/json;charset=UTF-8");

        if (response.getIsSuccess()) {
            return response.getResultDto();
        }

        return null;
    }

//    public static DeptEntity deptDetails(String deptCode) {
//        DeptApiRequest request = new DeptApiRequest();
//        String userCode = CurrentContext.getUserCode();
//        DeptEntity deptEntity = new DeptEntity();
//        deptEntity.setDeptCode(deptCode);
//        request.setEmpCode(userCode);
//        request.setKey("@Housekeeper$");
//        request.setEntity(deptEntity);
//
//        DeptApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("deptApi-deptDetails"),
//                DeptApiResponse.class, "application/json;charset=UTF-8");
////        DeptApiResponse response= HttpRequestUtils.requestPost(request,"http://localhost:8083/mobile-office/rs/deptApi/deptDetails",
////                DeptApiResponse.class,"application/json;charset=UTF-8");
//        if (response.getIsSuccess()) {
//            return response.getEntity();
//        }s
//
//        return null;
//    }

    public static List<DeptEntity> queryByMap(Map<String, Object> map) {

        DeptApiRequest request = new DeptApiRequest();

        request.setKey("@Housekeeper$");

        request.setParam(map);
        DeptApiResponse response = HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("deptApi-query"),
                DeptApiResponse.class, "application/json;charset=UTF-8");

        if (response.getIsSuccess()) {
            return response.getResult().getData();
        }

        return null;
    }

//    public static List<DeptEntity> findDeptName() {
//
//        DeptApiRequest request = new DeptApiRequest();
//        DeptEntity deptEntity = new DeptEntity();
//        request.setKey("@Housekeeper$");
//        request.setEntity(deptEntity);
//
//
////        DeptApiResponse response = HttpRequestUtils.requestPost(request,"http://mo.kufangwuyou.com/kfwy-office/rs/deptApi/findDeptName",
////                DeptApiResponse.class,"application/json;charset=UTF-8");
//
//        DeptApiResponse response = HttpRequestUtils.requestPost(request, "http://mo.kufangwuyou.com/kfwy-office/rs/deptApi/findDeptName",
//                DeptApiResponse.class, "application/json;charset=UTF-8");
//
//        if (response.getIsSuccess()) {
//            return response.getResult().getData();
//        }
//
//        return null;
//    }

    public static void main(String[] args) {
        int i = 0 / 1;
        deptTree("E2015112000001");
    }
}

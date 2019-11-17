package com.kfwy.hkp.sys.user.api;

import com.gniuu.framework.common.utils.HttpRequestUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.hkp.common.utils.ApiConfigUtils;
import com.kfwy.hkp.sys.user.entity.UserDto;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/26 21:25
 */
public class UserApiClient {


    public static UserEntity findUserByUserCode(String userCode) {

        UserApiVo userVo = new UserApiVo();
        userVo.setUserCode(userCode);
        userVo.setStart(null);
        userVo.setPageSize(null);

        UserApiRequest request = new UserApiRequest();

        request.setKey("@Housekeeper$");
        request.setEntity(userVo);

//        userVo.setIsDeleted(Boolean.FALSE);

        UserApiResponse response = findUsers(request);

        List<UserEntity> list = response.getList();

        UserEntity user = null;
        if (list != null && list.size() > 0) {
            user = list.get(0);
        }

        return user;
    }


    public static PageResult<UserEntity> syncUserAll(UserApiVo param) {
        UserApiRequest request = new UserApiRequest();
        request.setKey("@Housekeeper$");
        request.setEntity(param);
        if (param.getStart() == null && param.getPageSize() == null) {
            throw new BusinessException("此方法需要传入分页参数");
        }
        UserApiResponse response = findUsers(request);
        return response.getResult();
    }

    private static UserApiResponse findUsers(UserApiRequest request) {

        return HttpRequestUtils.requestPost(request, ApiConfigUtils.getMessage("userApi-list"),
                UserApiResponse.class, "application/json;charset=UTF-8");
    }

//    public static List<UserEntity> queryUserByDept(UserEntity userEntity) {
//
//        UserApiRequest request = new UserApiRequest();
//
//        request.setKey("@Housekeeper$");
//        UserApiVo userApiVo=new UserApiVo();
//        userApiVo.setOwnerDeptCode(userEntity.getOwnerDeptCode());
//        userApiVo.setUserCode(userEntity.getUserCode());
//        request.setEntity(userApiVo);
//        userApiVo.setStart(null);
//        userApiVo.setPageSize(null);
//
//        userApiVo.setIsDeleted(Boolean.FALSE);
//
//        UserApiResponse response = findUsersByDept(request);
//
//        return response.getList();
//
//    }
//
//    private static UserApiResponse findUsersByDept(UserApiRequest request) {
//
//        return HttpRequestUtils.requestPost(request,
//                ApiConfigUtils.getMessage("userApi-queryUserByDept"),
//                UserApiResponse.class, "application/json;charset=UTF-8");
//    }

//    public static List<UserEntity> findUsersAll(UserEntity userEntity) {
//        UserApiRequest request = new UserApiRequest();
//
//        request.setKey("@Housekeeper$");
//        request.setEntity(userEntity);
//
//        userEntity.setIsDeleted(Boolean.FALSE);
//
//        UserApiResponse response = HttpRequestUtils.requestPost(request,
//                ApiConfigUtils.getMessage("userApi-findUsersAll"),
//                UserApiResponse.class, "application/json;charset=UTF-8");
//        return response.getList();
//    }

    public static List<UserDto> initUsersAll() {
        UserApiRequest request = new UserApiRequest();

        request.setKey("@Housekeeper$");

        UserApiResponse response = HttpRequestUtils.requestPost(request,
                ApiConfigUtils.getMessage("userApi-initUsersAll"),
                UserApiResponse.class, "application/json;charset=UTF-8");
        return response.getUserDtos();
    }
}

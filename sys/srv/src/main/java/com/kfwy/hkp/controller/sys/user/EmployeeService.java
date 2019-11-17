package com.kfwy.hkp.controller.sys.user;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.sys.user.vo.EmployeeServiceRequest;
import com.kfwy.hkp.controller.sys.user.vo.EmployeeServiceResponse;
import com.kfwy.hkp.quit.dao.QuitDelRedisDao;
import com.kfwy.hkp.sys.user.api.UserApiVo;
import com.kfwy.hkp.sys.user.business.IUserDataManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
@RestController
@RequestMapping(path = "/employee")
public class EmployeeService extends SpringRestService {
    @Autowired
    private IUserDataManager dataManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private QuitDelRedisDao quitDelRedisDao;
    /**
     * 查询人员列表
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody EmployeeServiceRequest request) {

        EmployeeServiceResponse response = new EmployeeServiceResponse();

        UserApiVo userApiVo = new UserApiVo();

        //BeanUtils.copyProperties(request.getEntity(),userApiVo);
        userApiVo.setStart(request.getStart());
        userApiVo.setPageSize(request.getPageSize());
        //PageResult<UserEntity> users = UserApiClient.findUserListPage(userApiVo);
        Map map = new HashMap();
        map.put("keyword",request.getEntity().getKeyword());
        map.put("isDeleted",Boolean.FALSE);
        map.put("ownerDeptCode",request.getEntity().getOwnerDeptCode());
//        PageResult<UserEntity> users =
////                dataManager.findByMap(map,request.getStart(),request.getPageSize());
//
//        userManager.findByMap(map,request.getStart(),request.getPageSize());

        PageResult<UserEntity> userEntityPageResult = userManager.selectUserBasisInfoByMap(map,request.getStart(),request.getPageSize());

        if (userEntityPageResult ==null){
            return this.error(response,"操作异常了，请联系管理员");
        }
        response.setResult(userEntityPageResult);

        return this.success(response);
    }

    @RequestMapping(path = "/frost",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map frost(@RequestBody Map map){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserCode((String) map.get("userCode"));
        userEntity.setIsFrost((boolean)map.get("isFrost"));
        Integer i = userManager.update(userEntity);
        quitDelRedisDao.deleteEmp((String) map.get("userCode"));
        map.clear();
        if(i != null && i>0){
            map.put("code","200");
            map.put("message","success");
        }
        return map;
    }
}

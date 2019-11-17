package com.kfwy.hkp.controller.sync;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.sync.vo.SyncControllerRequest;
import com.kfwy.hkp.controller.sync.vo.SyncControllerResponse;
import com.kfwy.hkp.hrm.org.dept.api.DeptApiClient;
import com.kfwy.hkp.hrm.org.dept.api.DeptApiRequest;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.api.UserApiClient;
import com.kfwy.hkp.sys.user.api.UserApiVo;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/12/11 12:02
 */
@RestController
@RequestMapping(path = "/sync")
public class SyncController extends SpringRestService {

    @Autowired
    private TaskExecutor taskExecutor;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IDeptManager deptManager;

    @RequestMapping(path = "/user/list", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> syncUser(@RequestBody SyncControllerRequest request) {

        SyncControllerResponse response = new SyncControllerResponse();

        if (CollectionUtils.isEmpty(request.getUserCodes())) {
            throw new BusinessException("参数不能为空");
        }

        for (String userCode : request.getUserCodes()) {
            taskExecutor.execute(() -> {
                UserEntity user = UserApiClient.findUserByUserCode(userCode);
                user.setCpyCode ("kufangwuyou");
                if (!ObjectUtils.isEmpty(user)){
                    userManager.saveOrUpdate(user);
                }
            });
        }
        return this.success(response);
    }


    @RequestMapping(path = "/user/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> syncUserAll() {

        SyncControllerResponse response = new SyncControllerResponse();

        int start = 0;
        int pageSize = 100;
        while (true){
            UserApiVo userApiVo = new UserApiVo();
            userApiVo.setStart(start);
            userApiVo.setPageSize(pageSize);

            PageResult<UserEntity> result = UserApiClient.syncUserAll(userApiVo);

            if (result.hasData()){

                for (UserEntity user : result.getData()){
                    taskExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            if (!ObjectUtils.isEmpty(user)){
                                userManager.saveOrUpdate(user);
                            }
                        }
                    });
                }

                start = start + result.getData().size();
            }

            if (!result.isHasNextPage() || !result.hasData()){
                break;
            }
        }
        return this.success(response);
    }

    @RequestMapping(path = "/dept/list", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> syncDept(@RequestBody SyncControllerRequest request) {

        SyncControllerResponse response = new SyncControllerResponse();

        if (CollectionUtils.isEmpty(request.getDeptCodes())) {
            throw new BusinessException("参数不能为空");
        }

        for (String deptCode : request.getDeptCodes()) {
            taskExecutor.execute(() -> {
                DeptEntity dept = DeptApiClient.syncDeptDetail(deptCode);
                if (!ObjectUtils.isEmpty(dept)){
                    deptManager.saveOrUpdate(dept);
                }
            });
        }
        return this.success(response);
    }

    @RequestMapping(path = "/dept/all", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> syncDeptAll() {

        SyncControllerResponse response = new SyncControllerResponse();

        int start = 0;
        int pageSize = 100;

        while (true){
            DeptApiRequest req = new DeptApiRequest();
            req.setStart(start);
            req.setPageSize(pageSize);

            PageResult<DeptEntity> result = DeptApiClient.syncDeptPage(req);
            if (result.hasData()){
                for (DeptEntity dept : result.getData()) {
                    taskExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            if (!ObjectUtils.isEmpty(dept)){
                                deptManager.saveOrUpdate(dept);
                            }
                        }
                    });
                }
                start = start + result.getData().size();
            }
            if (!result.isHasNextPage() || !result.hasData()){
                break;
            }
        }
        return this.success(response);
    }
}

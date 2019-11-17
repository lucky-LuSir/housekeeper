package com.kfwy.hkp.controller.hrm.dept;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.hrm.dept.vo.DeptProfitShareServiceRequest;
import com.kfwy.hkp.controller.hrm.dept.vo.DeptProfitShareServiceResponse;
import com.kfwy.hkp.hrm.org.dept.IDeptProfitShareManager;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptProfitShareEntity;
import com.kfwy.hkp.sys.auth.dao.IReportAccessDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/8/6
 * @Description: TODO
 */

@RestController
@RequestMapping("/profitShare")
public class DeptProfitShareService extends SpringRestService {

    @Autowired
    private IDeptProfitShareManager profitShareManager;
    @Autowired
    private IReportAccessDbDao reportAccessDbDao;
    @Autowired
    private IDeptManager deptManager;


    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody DeptProfitShareServiceRequest request) {

        DeptProfitShareServiceResponse response = new DeptProfitShareServiceResponse();

        profitShareManager.create(request.getEntity());

        return this.success(response);
    }


    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody DeptProfitShareServiceRequest request) {

        DeptProfitShareServiceResponse response = new DeptProfitShareServiceResponse();

        profitShareManager.update(request.getEntity());

        return this.success(response);
    }

    @RequestMapping(path = "/select",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> select(@RequestBody DeptProfitShareServiceRequest request) {

        DeptProfitShareServiceResponse response = new DeptProfitShareServiceResponse();

        // 获取可查看权限
        List<String> deptCodes = new ArrayList<>();

        String deptCode = request.getEntity().getDeptCode();

        if (StringUtils.isNotEmpty(deptCode)) {
            deptCodes = deptManager.getDownDeptCode(deptCode);
        } else {
            UserEntity user = (UserEntity) CurrentContext.getUserInfo();
            List<String> accessCodes = reportAccessDbDao.selectAccess(user.getUserCode());
            if (accessCodes.isEmpty()) {
                deptCodes = deptManager.getDownDeptCode(user.getOwnerDeptCode());
            } else {
                deptCodes = deptManager.getDownDeptCode(accessCodes.get(0));
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("deptCodes", deptCodes);
        map.put("isDeleted", false);
        PageResult<DeptProfitShareEntity> result = profitShareManager.findByMap(map, request.getStart(), request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }

}

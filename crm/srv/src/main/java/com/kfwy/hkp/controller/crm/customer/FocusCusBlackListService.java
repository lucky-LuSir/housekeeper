package com.kfwy.hkp.controller.crm.customer;


import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.base.*;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.controller.crm.customer.vo.*;
import com.kfwy.hkp.crm.customer.business.IFocusCusBlackListManager;
import com.kfwy.hkp.crm.customer.entity.FocusCusBlackListEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping (path = "/focusCusBlackList")
public class FocusCusBlackListService extends SpringRestService {

    @Autowired
    private IFocusCusBlackListManager manager;
    @Autowired
    private IUserManager userManager;

    /**
     * 新增黑名单
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create (@RequestBody FocusCusBlackListServiceRequest request) {
        FocusCusBlackListServiceResponse response = new FocusCusBlackListServiceResponse ();
        FocusCusBlackListEntity entity = request.getEntity ();
        if (entity.getUserCode () != null) {
            UserEntity user = userManager.findOne ("userCode", entity.getUserCode ());
            entity.setUserCode (user.getUserCode ());
            entity.setUserName (user.getUserName ());
            entity.setOwnerDeptCode (user.getOwnerDeptCode ());
            entity.setOwnerDeptName (user.getOwnerDeptName ());
            entity.setIsDeleted (false);
            int i = manager.create (entity);
            if (i > 0) {
                return this.success (response);
            } else {
              throw new RemoveStackBusinessException ("新增黑名单失败！");
            }
        }else{
            throw new RemoveStackBusinessException ("新增黑名单需要选择人员！");

        }

    }

    /**
     * 修改黑名单
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update (@RequestBody FocusCusBlackListServiceRequest request) {
        FocusCusBlackListServiceResponse response = new FocusCusBlackListServiceResponse ();
        FocusCusBlackListEntity entity = request.getEntity ();
        if (entity.getUserCode () != null) {

            int i = manager.update (entity);
            if (i > 0) {
                return this.success (response);
            } else {
                throw new RemoveStackBusinessException ("修改黑名单失败！");
            }
        }else{
            throw new RemoveStackBusinessException ("修改黑名单需要选择人员！");
        }
    }

    /**
     * 查询黑名单
     *
     * @param request
     * @return
     */
    @RequestMapping (path = "/query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query (@RequestBody FocusCusBlackListServiceRequest request) {
        FocusCusBlackListServiceResponse response = new FocusCusBlackListServiceResponse ();
        Map map = new HashMap ();
        FocusCusBlackListEntity entity = request.getEntity ();
        if (entity !=null){
            if (entity.getUserName ()!=null){
                map.put ("userName",entity.getUserName ());
            }
            if (entity.getUserCode ()!=null){
                map.put ("userCode",entity.getUserCode ());
            }
            if (entity.getOwnerDeptCode ()!=null){
                map.put ("ownerDeptCode",entity.getOwnerDeptCode ());
            }
        }
        map.put ("isDeleted",false);
        PageResult<FocusCusBlackListEntity> result = manager.findByMap (map, request.getStart (), request.getPageSize (), "create_time", false);
        response.setResult(result);
        return this.success (response);
    }

}

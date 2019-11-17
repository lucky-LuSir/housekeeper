package com.kfwy.hkp.controller.quit;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.quit.vo.HkpDataTransferRequest;
import com.kfwy.hkp.controller.quit.vo.HkpDataTransferResponse;
import com.kfwy.hkp.controller.quit.vo.TransferEntityVo;
import com.kfwy.hkp.quit.manager.IHkpDataTransferManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * hkp数据转移
 */
@RestController
@RequestMapping(value = "hkpTransfer")
public class HkpDataTransferService extends SpringRestService {


    @Autowired
    private IHkpDataTransferManager hkpDataTransferManager;
    @Autowired
    private IUserManager userManager;

    /**
     * 房源数据转移
     * @param request
     * @return
     */
    @RequestMapping(path = "/house",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> house(@RequestBody HkpDataTransferRequest request){
        HkpDataTransferResponse response = new HkpDataTransferResponse();
        Map<String,Object> param = new HashMap<>();
        fillArea(request.getEntity(),param);
        String result = hkpDataTransferManager.houseTransfer(request.getEntity(),param);
        if(StringUtils.isNotBlank(result)){
            response.setResult("这些房源不是本部门的不能转=》"+result);
        }
        return this.success(response);
    }


    /**
     * 查出当前部门的员工
     * 此接口主要是 分部经理查出数据转移转移人与接收人
     * @param request
     * @return
     */
    @RequestMapping(path = "/getUser",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getUserToCurrentDept(@RequestBody HkpDataTransferRequest request){
        HkpDataTransferResponse response = new HkpDataTransferResponse();
        Map<String,Object> param = new HashMap<>(2);
        String deptCode = CurrentContext.getUserInfo().getOwnerDeptCode();
        param.put("ownerDeptCode",deptCode);
        param.put("isDeleted",Boolean.FALSE);
        List<UserEntity> list = userManager.findByMap(param);
        response.setResult(list);
        return this.success(response);
    }

    private void fillArea(TransferEntityVo entityVo,Map<String, Object> param){
        if(StringUtils.isNotBlank(entityVo.getStreet())){
            param.put("street",entityVo.getStreet());
        }
        if(StringUtils.isNotBlank(entityVo.getRegion())){
            param.put("region",entityVo.getRegion());
        }
        if(StringUtils.isNotBlank(entityVo.getCity())){
            param.put("city",entityVo.getCity());
        }
        if(StringUtils.isNotBlank(entityVo.getProvince())){
            param.put("province",entityVo.getProvince());
        }
    }
}

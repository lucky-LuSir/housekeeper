package com.kfwy.hkp.controller.crm.houseowner;



import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.enums.OperationType;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.executor.LogManager;
import com.kfwy.hkp.controller.crm.houseowner.vo.HouseownerServiceRequest;
import com.kfwy.hkp.controller.crm.houseowner.vo.HouseownerServiceResponse;
import com.kfwy.hkp.controller.crm.houseowner.vo.HouseownerVo;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerManager;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.log.annotation.OperLog;
import com.kfwy.hkp.log.manager.IOperationManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zjp on 2018/7/31.
 */
@RestController
@RequestMapping(path = "/houseowner")
public class HouseownerService extends SpringRestService {

    @Autowired
    private IHouseownerManager houseownerManager;

    @Autowired
    private IOperationManager operationManager;

    @Autowired
    private IHouseManager houseManager;

    @Autowired
    private IUserManager userManager;
    /**
     * 创建业主
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody HouseownerServiceRequest request) {
        HouseownerServiceResponse response = new HouseownerServiceResponse();
        request.getEntity().setEmpCode(CurrentContext.getUserCode());
        houseownerManager.create(request.getEntity());
        response.setResult(request.getEntity());
        return this.success(response);
    }

    /**
     * 更新业主
     * @param request
     * @return
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody HouseownerServiceRequest request) {

        HouseownerServiceResponse response = new HouseownerServiceResponse();

        houseownerManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 多条件查询业主,搜索框查询
     * @Param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody HouseownerServiceRequest request) {

        if (StringUtils.isEmpty(request.getKeyword())) {
            throw new RemoveStackBusinessException ("查询关键字为空");
        }

        HouseownerServiceResponse response = new HouseownerServiceResponse();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("keyword",request.getKeyword());
        param.put("isDeleted",false);

        PageResult<HouseownerEntity> result = houseownerManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询业主列表
     * @Param request
     * @return
     */
    @RequestMapping(path = "/select",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> select(@RequestBody HouseownerServiceRequest request) {

        HouseownerServiceResponse response = new HouseownerServiceResponse();

        Map<String,Object> param = new HashMap<String,Object>();



        HouseownerVo queryEntity = request.getEntity();

        if (StringUtils.isNotEmpty(request.getKeyword())) {
            param.put("keyword",request.getKeyword());
        }

        if(null != queryEntity){

            if (StringUtils.isNotEmpty(queryEntity.getKeyword())){
                param.put("keyword",queryEntity.getKeyword());
            }
            if(StringUtils.isNotEmpty(queryEntity.getHouseownerName())){
                param.put("houseownerName",queryEntity.getHouseownerName());
            }

            if(StringUtils.isNotEmpty(queryEntity.getHouseownerPhone())){
                param.put("houseownerPhone",queryEntity.getHouseownerPhone());
            }

            if(StringUtils.isNotEmpty(queryEntity.getHouseownerType())){
                param.put("houseownerType",queryEntity.getHouseownerType());
            }

            if(StringUtils.isNotEmpty(queryEntity.getEmpName())){
                param.put("empName",queryEntity.getEmpName());
            }

            if(StringUtils.isNotEmpty(queryEntity.getDeptName())){
                param.put("deptName",queryEntity.getDeptName());
            }

            if(StringUtils.isNotEmpty(queryEntity.getCreateName())){
                param.put("createName",queryEntity.getCreateName());
            }

            if(StringUtils.isNotEmpty(queryEntity.getCreateDeptName())){
                param.put("createDeptName",queryEntity.getCreateDeptName());
            }

            //创建时间
            if (null != queryEntity.getCreateTimeStart()) {
                try {
                    param.put("createTimeStart", DateUtil.beginOfDay(queryEntity.getCreateTimeStart()));
                } catch (Exception e) {
                }
            }

            if (null != queryEntity.getCreateTimeEnd()) {
                try {
                    param.put("createTimeEnd", DateUtil.endOfDay(queryEntity.getCreateTimeEnd()));
                } catch (Exception e) {
                }
            }

        }

        param.put("isDeleted",false);

        PageResult<HouseownerEntity> result = houseownerManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询业主详情
     * @Param request
     * @return
     */
    @OperLog(optType = OperationType.SEE_HOUSE_OWNER_DETAIL)
    @RequestMapping(path = "/details",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> details(@RequestBody HouseownerServiceRequest request) {

        HouseownerServiceResponse response = new HouseownerServiceResponse();
        if (userManager.findUserByUserCode(CurrentContext.getUserCode())==null){
            throw new RemoveStackBusinessException("离职人员不能查看客户详情");
        }
        HouseownerEntity HouseownerEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>(16);
        param.put("houseownerCode",HouseownerEntity.getHouseownerCode());
        param.put("isDeleted",false);

        HouseownerEntity result = houseownerManager.findOne(param);

        operationManager.save(HouseownerEntity.getHouseownerCode(),OperationType.SEE_HOUSE_OWNER_DETAIL,result.getEmpName());

        response.setResult(result);
        return this.success(response);
    }

    /**
     * 房源业主电话拨打
     * @param request
     * @return
     */
    @OperLog(optType = OperationType.CALL_HOUSE_OWNER_PHONE)
    @RequestMapping(path = "/callHouseOwnerPhone",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> callHouseOwnerPhone(@RequestBody HouseownerServiceRequest request) {
        HouseownerServiceResponse response = new HouseownerServiceResponse();
        String houseCode = request.getEntity().getHouseCode();
        String empName = houseManager.findEmpNameByHouseCode(houseCode);
        operationManager.save(houseCode,OperationType.CALL_HOUSE_OWNER_PHONE,empName);
        return this.success(response);
    }
}

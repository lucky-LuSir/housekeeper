package com.kfwy.hkp.controller.crm.houseowner;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.controller.crm.houseowner.vo.HouseownerFollowupServiceRequest;
import com.kfwy.hkp.controller.crm.houseowner.vo.HouseownerFollowupServiceResponse;
import com.kfwy.hkp.controller.crm.houseowner.vo.HouseownerFollowupVo;
import com.kfwy.hkp.crm.houseowner.business.IHouseownerFollowupManager;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerFollowupEntity;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjh on 2018/10/18.
 */
@RestController
@RequestMapping(path = "/houseownerFollowup")
public class HouseownerFollowupService extends SpringRestService {

    @Autowired
    private IHouseownerFollowupManager houseownerFollowupManager;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private IHouseManager houseManager;
    @Autowired
    private IUserManager userManager;

    /**
     * 创建业主跟进
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody HouseownerFollowupServiceRequest request) {

        HouseownerFollowupServiceResponse response = new HouseownerFollowupServiceResponse();
        houseownerFollowupManager.create(request.getEntity());
        response.setResult(request.getEntity().getHouseownerCode());
        return this.success(response);
    }

    /**
     * @Description 查询业主跟进
     * @Param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody HouseownerFollowupServiceRequest request) {


        HouseownerFollowupServiceResponse response = new HouseownerFollowupServiceResponse();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("houseownerCode",request.getEntity().getHouseownerCode());
        param.put("isDeleted",false);

        PageResult<HouseownerFollowupEntity> result = houseownerFollowupManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询业主跟进web端
     * @Param request
     * @return
     */
    @RequestMapping(path = "/queryWeb",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryWeb(@RequestBody HouseownerFollowupServiceRequest request) {


        HouseownerFollowupServiceResponse response = new HouseownerFollowupServiceResponse();
        Map<String,Object> param = new HashMap<String,Object>(4);
        if(StringUtils.isEmpty(request.getHouseCode())){
            throw new RemoveStackBusinessException ("房源编码不能为空");
        }

        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",request.getHouseCode());

        param.put("houseownerCode",oldHouseEntity.getOwnerCode());
        param.put("isDeleted",false);

        PageResult<HouseownerFollowupEntity> result = houseownerFollowupManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(result);
        return this.success(response);
    }


    /**
     * @Description 查询业主跟进列表web端
     * @Param request
     * @return
     */
    @RequestMapping(path = "/queryListWeb",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryListWeb(@RequestBody HouseownerFollowupServiceRequest request) {


        HouseownerFollowupServiceResponse response = new HouseownerFollowupServiceResponse();
        Map<String,Object> param = new HashMap<String,Object>(16);
        HouseownerFollowupVo followupVo = request.getEntity();
        param =  followupVo.queryMap(followupVo);
        param.put("isDeleted",false);
        PageResult<HouseownerFollowupEntity> result = houseownerFollowupManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(result);
        return this.success(response);
    }


    /**
     * @Description 查询业主跟进详情
     * @Param request
     * @return
     */
    @RequestMapping(path = "/details",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> details(@RequestBody HouseownerFollowupServiceRequest request) {

        HouseownerFollowupServiceResponse response = new HouseownerFollowupServiceResponse();

        Map<String,Object> param = new HashMap<String,Object>(1);
        param.put("houseownerCode",request.getEntity().getHouseownerCode());

        HouseownerFollowupEntity result = houseownerFollowupManager.findOne(param);

        response.setResult(result);
        return this.success(response);
    }

}

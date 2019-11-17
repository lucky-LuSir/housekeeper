package com.kfwy.hkp.controller.hos.house;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.controller.hos.house.vo.HouseFollowupServiceRequest;
import com.kfwy.hkp.controller.hos.house.vo.HouseFollowupServiceResponse;
import com.kfwy.hkp.controller.hos.house.vo.HouseFollowupVo;
import com.kfwy.hkp.hos.house.business.IHouseFollowupManager;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseFollowupEntity;
import com.kfwy.hkp.hrm.org.dept.api.DeptApiClient;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/10/11
 */

@RestController
@RequestMapping(path = "/houseFollowup")
public class HouseFollowupService extends SpringRestService {

    @Autowired
    private IHouseFollowupManager houseFollowupManager;
    @Autowired
    private IDeptDbDao deptDbDao;
    @Autowired
    private IHouseManager houseManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IHouseDbDao houseDbDao;

    /**
     * 查询房源跟进
     * @param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody HouseFollowupServiceRequest request) {

        HouseFollowupServiceResponse response = new HouseFollowupServiceResponse();
        HouseEntity oldHouseEntity=houseDbDao.selectUniqueByProp("houseCode",request.getEntity().getHouseCode());
        UserEntity userEntity=userManager.findUserByUserCode(CurrentContext.getUserCode());
        UserEntity houseUserEntity=userManager.selectUniqueByProp(oldHouseEntity.getEmpCode());


        if (!houseManager.checkFollowupRecord(oldHouseEntity,userEntity,houseUserEntity)){
            //无权限
            throw new RemoveStackBusinessException ("不是房源所有者且不存在与房源所属者的合作关系无权查看!");
        }

        Map<String,Object> map = new HashMap<>(16);
        map.put("houseCode",request.getEntity().getHouseCode());

        PageResult<HouseFollowupEntity> byMap = houseFollowupManager.findByMap(map, request.getStart(), request.getPageSize(),"create_time",false);

        response.setResult(byMap);

        return this.success(response);
    }
    /**
     * 创建房源跟进
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody HouseFollowupServiceRequest request) {

        HouseFollowupServiceResponse response = new HouseFollowupServiceResponse();
        HouseFollowupEntity houseFollowupEntity = new HouseFollowupEntity();
        houseFollowupEntity.setHouseCode(request.getEntity().getHouseCode());
        houseFollowupEntity.setFollowupContext(request.getEntity().getFollowupContext());

        // 根据业务需求对应字段校验
        List<String> list = new ArrayList();
        list.add("houseCode");
        list.add("followupContext");
        ParamUtil<HouseFollowupEntity> paramUtil = new ParamUtil<>();
        try {
            //前端没有传houseCode
            paramUtil.check(houseFollowupEntity,list);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        houseFollowupManager.create(houseFollowupEntity);

        return this.success(response);
    }

    /**
     * 查询
     * @param request
     * @return
     */
    @RequestMapping(path = "/select",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> select(@RequestBody HouseFollowupServiceRequest request) {

        HouseFollowupServiceResponse response = new HouseFollowupServiceResponse();

        HouseFollowupVo houseFollowupVo = request.getEntity();



        Map param = new HashMap<String, Object>();
        if(null != houseFollowupVo){

            if (StringUtils.isNotEmpty(houseFollowupVo.getDeptName())) {
                param.put("deptName", houseFollowupVo.getDeptName());
            }
            if (StringUtils.isNotEmpty(houseFollowupVo.getEmpName())) {
                param.put("empName", houseFollowupVo.getEmpName());
            }
            if (StringUtils.isNotEmpty(houseFollowupVo.getHouseCode())) {
                param.put("houseCode", houseFollowupVo.getHouseCode());
            }

            //创建时间
            if (null != houseFollowupVo.getCreateTimeStart()) {
                try {
                    param.put("createTimeStart", DateUtil.beginOfDay(houseFollowupVo.getCreateTimeStart()));
                } catch (Exception e) {
                }
            }

            if (null != houseFollowupVo.getCreateTimeEnd()) {
                try {
                    param.put("createTimeEnd", DateUtil.endOfDay(houseFollowupVo.getCreateTimeEnd()));
                } catch (Exception e) {
                }
            }

            Map<String,Object> map = new HashMap<>();
            map.put("leaderCode", CurrentContext.getUserCode());
            List<DeptEntity> depts = deptDbDao.selectByMap(map);
            map.clear();
            if(CollectionUtil.isNotEmpty(depts)){
                // 查询该部门所属房源
                if(depts.get(0).getDeptName().contains("大区")){
                    map.put("parentCode",CurrentContext.getUserInfo().getOwnerDeptCode());
                    List<DeptEntity> deptEntities = DeptApiClient.queryByMap(map);
                    // 获取所有分部
                    if(CollectionUtil.isNotEmpty(deptEntities)){
                        List<String> deptCodes = new ArrayList<>();
                        for (DeptEntity dept : deptEntities){
                            deptCodes.add(dept.getDeptCode());
                        }
                        // 查询所有下级部门房源
                        param.put("createDeptCodes", deptCodes);
                    }
                }else if(depts.get(0).getDeptName().contains("分部")){
                    param.put("createDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                }else{
                    // 查询我的
                    param.put("empCode", CurrentContext.getUserCode());
                }
            }else{
                // 查询我的
                param.put("empCode", CurrentContext.getUserCode());
            }

        }



        param.put("isDeleted",false);
        PageResult<HouseFollowupEntity> result = houseFollowupManager.findByMap(param, request.getStart(), request.getPageSize(),"create_time",false);
        response.setResult(result);
        return this.success(response);
    }
}

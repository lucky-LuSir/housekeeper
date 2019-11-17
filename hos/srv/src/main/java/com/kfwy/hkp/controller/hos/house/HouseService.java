package com.kfwy.hkp.controller.hos.house;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.enums.SexType;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.base.ICheckPermissionManager;
import com.kfwy.hkp.base.SystemConfigKey;
import com.kfwy.hkp.base.TimeSplit;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.hkp.common.easyexcel.export.FileExportor;
import com.kfwy.hkp.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.hkp.common.easyexcel.export.exception.FileExportException;
import com.kfwy.hkp.common.enums.OperationType;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.executor.LogManager;
import com.kfwy.hkp.common.utils.*;
import com.kfwy.hkp.controller.hos.house.handler.HouseQueryHandler;
import com.kfwy.hkp.controller.hos.house.vo.*;
import com.kfwy.hkp.crm.customer.business.ICustomerFollowupManager;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerFollowupEntity;
import com.kfwy.hkp.crm.houseowner.entity.HouseownerEntity;
import com.kfwy.hkp.hos.house.api.BespeakApi.BespeakApiClient;
import com.kfwy.hkp.hos.house.api.EntrustApi.EntrustApiClient;
import com.kfwy.hkp.hos.house.api.RecommendApi.RecommendApiClient;
import com.kfwy.hkp.hos.house.business.IHouseLocationManager;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.hos.house.business.IHouseMapManager;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.entity.*;
import com.kfwy.hkp.hos.house.enums.*;
import com.kfwy.hkp.hrm.org.dept.api.DeptApiClient;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.log.annotation.OperLog;
import com.kfwy.hkp.log.manager.IOperationManager;
import com.kfwy.hkp.sys.file.business.IFileRelationManager;
import com.kfwy.hkp.sys.file.dictionary.FileType;
import com.kfwy.hkp.sys.file.entity.FileRelationEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserType;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


import org.apache.commons.beanutils.BeanUtils;


@RestController
@RequestMapping(path = "/house")
public class HouseService extends SpringRestService {

    @Autowired
    private IHouseMapManager houseMapManager;
    @Autowired
    private IHouseManager houseManager;
    @Autowired
    private IDeptDbDao deptDbDao;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private IOperationManager operationManager;
    @Autowired
    private IHouseLocationManager houseLocationManager;
    @Autowired
    private HouseQueryHandler houseQueryHandler;
    @Autowired
    private IFileRelationManager fileRelationManager;

    @Autowired
    private ICustomerFollowupManager customerFollowupManager;
    @Autowired
    private IDeptManager deptManager;

    @Autowired
    private ICheckPermissionManager checkPermissionManager;
    /**
     * 查询房源
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "根据条件查询房源列表",
            notes = "根据条件keyword,查询房源分页集合信息")
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody HouseServiceRequest request) throws IllegalAccessException {
        HouseServiceResponse response = new HouseServiceResponse();
        HouseQueryEntity houseQuery = request.getHouseQuery();

        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        if (UserType.Individual.equals(user.getUserType())
                && HouseQueryType.DEPT.equals(houseQuery.getQueryType())) {
            response.setResult(new PageResult<HouseEntity>());
            return this.success(response);
        }
        Map<String, Object> param = houseQueryHandler.queryMap(houseQuery);

        houseQueryHandler.keyword(param, request.getKeyword());

        if (StringUtils.isNotEmpty(request.getEmpCode())) {
            // 查询此编码所属的房源
            param.put("empCode", request.getEmpCode());
        }

        PageResult<HouseEntity> byMap = houseManager.findByMap(param, request.getStart(), request.getPageSize(), (String) param.get("orderBy"), (boolean) param.get("descOrAsc"));
        handlerDaysNotFollowup(byMap.getData());
        response.setResult(byMap);
        return this.success(response);
    }


    /**
     * 处理未跟进天数返回值
     *
     * @param dataList
     */
    public void handlerDaysNotFollowup(List<HouseEntity> dataList) {
        if (CollectionUtil.isNotEmpty(dataList)) {
            for (HouseEntity hosOne : dataList) {
                Date lastFollupDate = hosOne.getLastFollowupTime();
                long indays = 1234;
                if (lastFollupDate == null) {
                    hosOne.setDaysNotFollowup(null);
                } else {
                    Date nowDate = new Date();
                    indays = (nowDate.getTime() - lastFollupDate.getTime()) / (24 * 60 * 60 * 1000);
                    hosOne.setDaysNotFollowup(indays);
                }

            }
        }
    }

    public Map houseEntityQuery(Map param, HouseQueryEntity houseQuery) {
        if (null != houseQuery) {

            //设置自定义查询的多少天未跟进房源条件
            DaysNotFollowupType.setDaysNotFollowup(param, houseQuery.getDaysNotFollowupType(), houseQuery.getDaysNotFollowup());

            if (StringUtils.isNotEmpty(houseQuery.getHouseCode())) {
                param.put("houseCode", houseQuery.getHouseCode());
            }

            if (StringUtils.isNotEmpty(houseQuery.getHouseName())) {
                param.put("houseName", houseQuery.getHouseName());
            }

            if (StringUtils.isNotEmpty(houseQuery.getOwnerPhone())) {
                param.put("ownerPhone", houseQuery.getOwnerPhone());
            }
            if (StringUtils.isNotEmpty(houseQuery.getHouseOwnerName())) {
                param.put("houseOwnerName", houseQuery.getHouseOwnerName());
            }

            if (StringUtils.isNotEmpty(houseQuery.getQueryType())) {
                if (houseQuery.getQueryType().equals(HouseQueryType.MINE)) {
                    // 如果不是领导就查询我的
                    Map<String, Object> map = new HashMap<>();
                    map.put("leaderCode", CurrentContext.getUserCode());
                    List<DeptEntity> depts = deptDbDao.selectByMap(map);
                    map.clear();
                    if (CollectionUtil.isNotEmpty(depts)) {
                        // 查询该部门所属房源
                        if (depts.get(0).getDeptName().contains("大区")) {
                            map.put("parentCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                            List<DeptEntity> deptEntities = DeptApiClient.queryByMap(map);
                            // 获取所有分部
                            if (CollectionUtil.isNotEmpty(deptEntities)) {
                                List<String> deptCodes = new ArrayList<>();
                                for (DeptEntity dept : deptEntities) {
                                    deptCodes.add(dept.getDeptCode());
                                }
                                // 查询所有下级部门房源
                                param.put("createDeptCodes", deptCodes);
                            }
                        } else if (depts.get(0).getDeptName().contains("分部")) {
                            param.put("createDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                        } else {
                            // 查询我的
                            param.put("empCode", CurrentContext.getUserCode());
                        }
                    } else {
                        // 查询我的
                        param.put("empCode", CurrentContext.getUserCode());
                    }

                } else if (houseQuery.getQueryType().equals(HouseQueryType.COLLECT)) {
                    // 查询我的收藏
                    param.put("hasFavorite", Boolean.TRUE);
                    param.put("favEmpCode", CurrentContext.getUserCode());
                } else if (houseQuery.getQueryType().equals(HouseQueryType.AGENT)) {
                    // 经纪人
                    param.put("houseType", HouseType.AGENT);
                } else if (houseQuery.getQueryType().equals(HouseQueryType.PLATFORM)) {
                    // 平台
                    param.put("houseType", HouseType.PLATFORM);

                } else if (houseQuery.getQueryType().equals(HouseQueryType.NEARBY)) {
                    // 附近房源
                } else if (houseQuery.getQueryType().equals(HouseQueryType.ALL)) {

                } else if (houseQuery.getQueryType().equals(HouseQueryType.UNION)) {
                    // 我合作的经纪人房源
                    param.put("hasCoo", Boolean.TRUE);
                    param.put("cooEmpCode", CurrentContext.getUserCode());
                } else if (houseQuery.getQueryType().equals(HouseQueryType.DEPT)) {
                    // 同部门房源
                    param.put("createDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                }
            }

            if (StringUtils.isNotEmpty(houseQuery.getHouseStatus())) {
                param.put("houseStatus", houseQuery.getHouseStatus());
            }

            if (StringUtils.isNotEmpty(houseQuery.getHousePurpose())) {
                param.put("housePurpose", houseQuery.getHousePurpose());
            }

            if (StringUtils.isNotEmpty(houseQuery.getEmpCode())) {
                param.put("empCode", houseQuery.getEmpCode());
            }

            if (StringUtils.isNotEmpty(houseQuery.getEmpName())) {
                param.put("empName", houseQuery.getEmpName());
            }

            if (StringUtils.isNotEmpty(houseQuery.getFireLevel())) {
                param.put("fireLevel", houseQuery.getFireLevel());
            }

            if (null != houseQuery.getHasEia()) {
                param.put("hasEia", houseQuery.getHasEia());
            }

            if (null != houseQuery.getHasCut()) {
                param.put("hasCut", houseQuery.getHasCut());
            }

            if (null != houseQuery.getHasCertificate()) {
                param.put("hasCertificate", houseQuery.getHasCertificate());
            }

            if (null != houseQuery.getHasRegistry()) {
                param.put("hasRegistry", houseQuery.getHasRegistry());
            }

            if (null != houseQuery.getHasShortLease()) {
                param.put("hasShortLease", houseQuery.getHasShortLease());
            }

            if (null != houseQuery.getHasParking()) {
                param.put("hasParking", houseQuery.getHasParking());
            }

            if (null != houseQuery.getHasInstallCrane()) {
                param.put("hasInstallCrane", houseQuery.getHasInstallCrane());
            }

            if (null != houseQuery.getMinArea()) {
                param.put("minArea", houseQuery.getMinArea());
            }

            if (null != houseQuery.getMaxArea()) {
                param.put("maxArea", houseQuery.getMaxArea());
            }

            if (null != houseQuery.getMinPrice()) {
                param.put("minPrice", houseQuery.getMinPrice());
            }

            if (null != houseQuery.getMaxPrice()) {
                param.put("maxPrice", houseQuery.getMaxPrice());
            }
            //最小电量不为空
            if (null != houseQuery.getMinElectric()) {
                param.put("minElectric", houseQuery.getMinElectric());
            }
            //最大电量不为空
            if (null != houseQuery.getMaxElectric()) {
                param.put("maxElectric", houseQuery.getMaxElectric());
            }
            //省编号不为空
            if (StringUtils.isNotEmpty(houseQuery.getProvince())) {
                param.put("province", houseQuery.getProvince());
            }
            //城市编号不为空
            if (StringUtils.isNotEmpty(houseQuery.getCity())) {
                param.put("city", houseQuery.getCity());
            }
            //区域不为空
            if (StringUtils.isNotEmpty(houseQuery.getRegion())) {
                param.put("region", houseQuery.getRegion());
            }
            //街道编码不为空
            if (StringUtils.isNotEmpty(houseQuery.getStreet())) {
                param.put("street", houseQuery.getStreet());
            }
            if (StringUtils.isNotEmpty(houseQuery.getCommunity())) {
                param.put("community", houseQuery.getCommunity());
            }
            //详情地址不为空
            if (StringUtils.isNotEmpty(houseQuery.getDetailAddress())) {
                param.put("detailAddress", houseQuery.getDetailAddress());
            }
            //创建人编号不为空
            if (StringUtils.isNotEmpty(houseQuery.getCreateCode())) {
                param.put("createCode", houseQuery.getCreateCode());
            }
            //创建部门编号不为空
            if (StringUtils.isNotEmpty(houseQuery.getCreateDeptCode())) {
                param.put("createDeptCode", houseQuery.getCreateDeptCode());
            }

            //创建时间
            if (null != houseQuery.getCreateTimeStart()) {
                try {
                    param.put("createTimeStart", DateUtil.beginOfDay(houseQuery.getCreateTimeStart()));
                } catch (Exception e) {

                }
            }
            //创建结束时间
            if (null != houseQuery.getCreateTimeEnd()) {
                try {
                    param.put("createTimeEnd", DateUtil.endOfDay(houseQuery.getCreateTimeEnd()));
                } catch (Exception e) {

                }
            }

            if (StringUtils.isNotEmpty(houseQuery.getHouseownerType())) {
                param.put("houseownerType", houseQuery.getHouseownerType());
            }
            if (null != houseQuery.getLayerHeight()) {
                param.put("layerHeight", houseQuery.getLayerHeight());
            }
            if (null != houseQuery.getWhereLayer()) {

                param.put("whereLayer", houseQuery.getWhereLayer().toString());

            }

            if (StringUtils.isNotEmpty(houseQuery.getFloorFlag())) {
                if (houseQuery.getFloorFlag().equals("1")) {
                    param.put("underlayer", true);
                } else if (houseQuery.getFloorFlag().equals("2")) {
                    param.put("notUnderlayer", true);
                }
            }
            if (null != houseQuery.getStartEnterTime()) {
                try {
                    param.put("startEnterTime", DateUtil.beginOfDay(houseQuery.getStartEnterTime()));
                } catch (Exception e) {
                }
            }
            if (null != houseQuery.getEndEnterTime()) {
                try {
                    param.put("endEnterTime", DateUtil.endOfDay(houseQuery.getEndEnterTime()));
                } catch (Exception e) {
                }
            }
            if (StringUtils.isNotEmpty(houseQuery.getCompanyName())) {
                param.put("companyName", houseQuery.getCompanyName());
            }
            if (StringUtils.isNotEmpty(houseQuery.getForInsdustry())) {
                //行业性质
                param.put("forInsdustry", houseQuery.getForInsdustry());
            }
            if (houseQuery.getBearing() != null) {
                param.put("bearing", houseQuery.getBearing());
            }
            if (houseQuery.getLeaseNegotiable() != null) {
                param.put("leaseNegotiable", houseQuery.getLeaseNegotiable());
            }
            if (houseQuery.getOfTheArea() != null) {
                param.put("ofTheArea", houseQuery.getOfTheArea());
            }
            if (houseQuery.getHouseStyle() != null) {
                param.put("houseStyle", houseQuery.getHouseStyle());
            }
        }
        param.put("isDeleted", false);
        return param;
    }

    /**
     * 房源详情
     *
     * @param request
     * @return
     */
    @OperLog(optType = OperationType.SEE_HOS_DETAIL)
    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();
        HouseEntity one = houseManager.detail(request.getHouseCode());

        //LogManager.me ().executeLog (operationManager.save(one.getHouseCode(), OperationType.SEE_HOS_DETAIL, one.getEmpName()));
        operationManager.save(one.getHouseCode(), OperationType.SEE_HOS_DETAIL, one.getEmpName());
        response.setResult(one);
        return this.success(response);
    }


    /**
     * 房源详情v2.8.0新版
     *
     * @param request
     * @return
     */
    @OperLog(optType = OperationType.SEE_HOS_DETAIL)
    @RequestMapping(path = "/detailTwoVersion",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detailTwoVersion(@RequestBody HouseServiceRequest request) throws IllegalAccessException, InvocationTargetException {

        HouseServiceResponse response = new HouseServiceResponse();
        HouseEntity one = houseManager.detailTwoVersion(request.getHouseCode());

        operationManager.save(one.getHouseCode(), OperationType.SEE_HOS_DETAIL, one.getEmpName());
       // LogManager.me ().executeLog (operationManager.save(one.getHouseCode(), OperationType.SEE_HOS_DETAIL, one.getEmpName()));

        HouseCreateVo houseCreateVo = new HouseCreateVo();

        BeanUtils.copyProperties(houseCreateVo,one);

        //单独赋值start
        houseCreateVo.setHouseStyle(null);
        houseCreateVo.setHouseStyleName(null);
        houseCreateVo.setHousePurpose(null);
        houseCreateVo.setHousePurposeName(null);
        houseCreateVo.setWhereLayer(null);
        houseCreateVo.setLayerArea(null);
        houseCreateVo.setArea(null);
        houseCreateVo.setMinAcreage(null);
        houseCreateVo.setPrice(null);
        houseCreateVo.setUnit(null);
        houseCreateVo.setLayerHeight(null);
        houseCreateVo.setMaxElectric(null);
        houseCreateVo.setLessLease(null);
        houseCreateVo.setMoreLease(null);
        houseCreateVo.setEnterTime(null);
        houseCreateVo.setBearing(null);
        //单独赋值end

        //赋值子房源
        HouseEntity newSubHos = new HouseEntity();


        //单独赋值start
        newSubHos.setHouseStyle(one.getHouseStyle());
        newSubHos.setHouseStyleName(one.getHouseStyleName());
        newSubHos.setHousePurpose(one.getHousePurpose());
        newSubHos.setHousePurposeName(one.getHousePurposeName());
        newSubHos.setWhereLayer(one.getWhereLayer());
        newSubHos.setLayerArea(one.getLayerArea());
        newSubHos.setArea(one.getArea());
        newSubHos.setMinAcreage(one.getMinAcreage());
        newSubHos.setPrice(one.getPrice());
        newSubHos.setUnit(one.getUnit());
        newSubHos.setLayerHeight(one.getLayerHeight());
        newSubHos.setMaxElectric(one.getMaxElectric());
        newSubHos.setLessLease(one.getLessLease());
        newSubHos.setMoreLease(one.getMoreLease());
        newSubHos.setEnterTime(one.getEnterTime());
        newSubHos.setBearing(one.getBearing());
        //单独赋值end



        List<HouseEntity> subHosList = new ArrayList<HouseEntity>();
        subHosList.add(newSubHos);
        houseCreateVo.setSubHosList(subHosList);


        response.setResult(houseCreateVo);
        return this.success(response);
    }

    /**
     * 房源被哪些客户带看过
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/takeLookByCustomer",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> takeLookByCustomer(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();
        PageResult<CustomerEntity> result = houseManager.takeLookByCustomer(request.getHouseCode(), request.getStart(), request.getPageSize(), "create_time", false);
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 房源被哪些客户带看过
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/takeLookByCusEvaluate",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> takeLookByCusEvaluate(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();
        Map map = new HashMap();
        map.put("houseCode",request.getEntity().getHouseCode());
        PageResult<CustomerFollowupEntity> result = customerFollowupManager
                .takeLookByCusEvaluate(map,request.getStart(),request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }


    /**
     * 修改房源
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/edit",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> edit(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();

        if (StringUtils.isNotEmpty(request.getHouseCode())) {
            param.put("houseCode", request.getHouseCode());
        }

        HouseEntity one = houseManager.edit(param);
        response.setResult(one);
        return this.success(response);
    }


    /**
     * 修改房源--v2.8.0房源编辑新版接口
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/editTwoVersion",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> editTwoVersion(@RequestBody HouseServiceRequest request) throws IllegalAccessException, InvocationTargetException {

        HouseServiceResponse response = new HouseServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();

        if (StringUtils.isNotEmpty(request.getHouseCode())) {
            param.put("houseCode", request.getHouseCode());
        }

        HouseEntity one = houseManager.editTwoVersion(param);
        HouseCreateVo houseCreateVo = new HouseCreateVo();

        BeanUtils.copyProperties(houseCreateVo,one);

        //单独赋值start
        houseCreateVo.setHouseStyle(null);
        houseCreateVo.setHouseStyleName(null);
        houseCreateVo.setHousePurpose(null);
        houseCreateVo.setHousePurposeName(null);
        houseCreateVo.setWhereLayer(null);
        houseCreateVo.setLayerArea(null);
        houseCreateVo.setArea(null);
        houseCreateVo.setMinAcreage(null);
        houseCreateVo.setPrice(null);
        houseCreateVo.setUnit(null);
        houseCreateVo.setLayerHeight(null);
        houseCreateVo.setMaxElectric(null);
        houseCreateVo.setLessLease(null);
        houseCreateVo.setMoreLease(null);
        houseCreateVo.setEnterTime(null);
        houseCreateVo.setBearing(null);
        //单独赋值end

        //赋值子房源
        HouseEntity newSubHos = new HouseEntity();

        //单独赋值start
        newSubHos.setHouseStyle(one.getHouseStyle());
        newSubHos.setHouseStyleName(one.getHouseStyleName());
        newSubHos.setHousePurpose(one.getHousePurpose());
        newSubHos.setHousePurposeName(one.getHousePurposeName());
        newSubHos.setWhereLayer(one.getWhereLayer());
        newSubHos.setLayerArea(one.getLayerArea());
        newSubHos.setArea(one.getArea());
        newSubHos.setMinAcreage(one.getMinAcreage());
        newSubHos.setPrice(one.getPrice());
        newSubHos.setUnit(one.getUnit());
        newSubHos.setLayerHeight(one.getLayerHeight());
        newSubHos.setMaxElectric(one.getMaxElectric());
        newSubHos.setLessLease(one.getLessLease());
        newSubHos.setMoreLease(one.getMoreLease());
        newSubHos.setEnterTime(one.getEnterTime());
        newSubHos.setBearing(one.getBearing());
        newSubHos.setHosDescribe(one.getHosDescribe());
        newSubHos.setFileList(new ArrayList<FileRelationEntity>());
        for (FileRelationEntity fileOne : one.getFileList()) {
            if(fileOne.getFileUse().equals(HouseFileUse.HouseInner)){
                newSubHos.getFileList().add(fileOne);
            }
        }
        //单独赋值end

        List<HouseEntity> subHosList = new ArrayList<HouseEntity>();
        subHosList.add(newSubHos);
        houseCreateVo.setSubHosList(subHosList);

        response.setResult(houseCreateVo);
        return this.success(response);
    }

    /**
     * 修改房源
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/editByWeb",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> editByWeb(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();

        if (StringUtils.isNotEmpty(request.getHouseCode())) {
            param.put("houseCode", request.getHouseCode());
        }

        HouseEntity one = houseManager.editByWeb(param);
        response.setResult(one);
        return this.success(response);
    }

    /**
     * 创建房源
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody HouseServiceRequest request) {
        int i = 0;
        HouseServiceResponse response = new HouseServiceResponse();
        HouseEntity houseEntity = request.getEntity();
        if (null != request.getEntity()) {
            UserEntity userEntity = (UserEntity)CurrentContext.getUserInfo();
            //当前登录人是客服，则房源为平台房源，否则是经纪人房源
            if (userEntity.getEnterType().equals(HouseType.PLATFORM)) {
                request.getEntity().setHouseType(HouseType.PLATFORM);
            } else {
                request.getEntity().setHouseType(HouseType.AGENT);
            }
            if (StringUtils.isEmpty(request.getEntity().getHouseType())) {
                this.error(response, "新增房源类型不能为空");
            }
            // 获取编码
            houseEntity.setHouseCode(CodeGenUtils.generate("wh"));
            i = houseManager.create(houseEntity);
        } else {
            throw new RemoveStackBusinessException ("新增的房源信息为空");
        }
        if (i > 0 && request.getEntity().getHouseFrom().equals("官网预约") && request.getEntity().getBespeakId() != null) {
            BespeakApiClient.updateProcessed(request.getEntity().getBespeakId());
        } else if (i > 0 && request.getEntity().getHouseFrom().equals("官网预约") && request.getEntity().getEntrustId() != null) {
            EntrustApiClient.updateProcessed(request.getEntity().getEntrustId());
        } else if (i > 0 && request.getEntity().getHouseFrom().equals("兼职推荐") && request.getEntity().getRecommendId() != null) {
            RecommendApiClient.updateProcessed(request.getEntity().getRecommendId(),houseEntity.getHouseCode());
        }
        return this.success(response);
    }


    /**
     * 创建房源 v2.8.0
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/createTwoVersion",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createTwoVersion(@RequestBody HouseCreateServiceRequest request) throws IllegalAccessException, InvocationTargetException {
        int i = 0;
        HouseServiceResponse response = new HouseServiceResponse();
        HouseCreateVo houseCreateVo = request.getEntity();
        //1,校验前端必传字段
        // 根据业务需求对应字段校验
        List<String> list = new ArrayList();
        list.add("hasAloneBuilding");
        list.add("whereBuilding");
        list.add("severalLayers");
        //图片列表暂时不校验,在FileList中校验 list.add("houseYardImage");

        list.add("ownerType");

        list.add("houseFrom");
        list.add("commission");
        //图片列表暂时不校验,在FileList中校验list.add("houseProtocol");
        list.add("houseStructure");
        list.add("fireLevel");

        list.add("hasEia");
        list.add("hasRegistry");
        //list.add("hasCertificate");  产证齐全对应 hasCertificate
        list.add("hasParking");
        list.add("hasElevator");
        list.add("hasPlatform");

        list.add("hasInstallCrane");
        list.add("hasOfficeArea");
        list.add("hasWholeRental");

        list.add("locationCode");
        list.add("hasDischargeSewage");  //对应前端产证齐全字段
        list.add("industryRestriction"); //对应前端行业限制,用户填,或者选了无限制

        Map<String, String> checkName = houseCreateVo.usePramCheckNameNoReturn();
        ParamUtilPro<HouseCreateVo> paramUtilPro = new ParamUtilPro<>();
        paramUtilPro.check(houseCreateVo,list,checkName);

        // 根据业务需求对应字段校验 end

        List<HouseEntity> subHosList = houseCreateVo.getSubHosList();
        if(subHosList==null){
            throw new RemoveStackBusinessException("下属房源子列表不能为空");
        }
        if(subHosList.size()==0){
            throw new RemoveStackBusinessException("下属房源子列表数量需要大于0,当前为:"+subHosList.size());
        }
        for (HouseEntity subEntity : subHosList) {
            List<String> sublist = new ArrayList();

            sublist.add("houseStyle");
            sublist.add("housePurpose");
            sublist.add("whereLayer");

            sublist.add("layerArea");
            sublist.add("area");
            sublist.add("minAcreage");
            sublist.add("price");
            sublist.add("unit");

            sublist.add("layerHeight");
            sublist.add("maxElectric");
            sublist.add("lessLease");
            sublist.add("moreLease");
            sublist.add("enterTime");
            sublist.add("hosDescribe");
            //sublist.add("bearing");//层重前端校验,存在1层不填,其他填的情况

            ParamUtilPro<HouseEntity> subUtil = new ParamUtilPro<>();
            subUtil.check(subEntity,sublist,checkName);
        }

        for (HouseEntity subEntity : subHosList) {
            if(null==subEntity.getFileList()){
                throw new RemoveStackBusinessException("层中需要传入(推广图)!FileList为空");
            }
            if( subEntity.getFileList().size() == 0){
                throw new RemoveStackBusinessException("层中需要传入(推广图)!FileList为0个");
            }


        }

        //校验房源中的业主字段
        HouseownerEntity houseOwner = request.getEntity().getHouseowner();
        if(houseOwner==null){
            throw new RemoveStackBusinessException("业主houseowner实体不能为空");
        }
        List<String> listHouseOwner = new ArrayList();

        listHouseOwner.add("houseownerPhone");
        listHouseOwner.add("houseownerName");
        listHouseOwner.add("houseownerSex");
        houseOwner.setHouseownerType(houseCreateVo.getOwnerType());


        ParamUtil<HouseownerEntity> paramUtilHouseOwner = new ParamUtil<>();
        paramUtilHouseOwner.check(houseOwner,listHouseOwner);
        //校验房源中的业主字段end

        String sexCheck = houseOwner.getHouseownerSex();
        if((!sexCheck.equals(SexType.Sir)) && (!sexCheck.equals(SexType.Lady))){
            throw new RemoveStackBusinessException("请传入性别:Lady 或者 Sir");
        }
        //2,manager中增加默认值字段
        //3,最后新增
        houseCreateVo.setSubHosList(new ArrayList<>());//处理subHosList为空
        HouseEntity houseEntity = houseCreateVo;
        houseEntity.setBatchCode(CodeGenUtils.generate("bc"));
        if (null == request.getEntity()) {
            throw new RemoveStackBusinessException("新增的房源信息为空");
        }

            UserEntity userEntity = (UserEntity)CurrentContext.getUserInfo();
            //当前登录人是客服，则房源为平台房源，否则是经纪人房源
            if (userEntity.getEnterType().equals(HouseType.PLATFORM)) {
                request.getEntity().setHouseType(HouseType.PLATFORM);
            } else {
                request.getEntity().setHouseType(HouseType.AGENT);
            }
            if (StringUtils.isEmpty(request.getEntity().getHouseType())) {
                this.error(response, "新增房源类型不能为空");
            }
            // 获取编码

        houseEntity.setHasMonolayer(true);
        if(subHosList.size()>2){houseEntity.setHasMonolayer(false);}
        ArrayList<HouseEntity> resultList = new ArrayList<>();
        for (HouseEntity subEntity : subHosList) {

            houseEntity.setHouseStyle(subEntity.getHouseStyle());
            houseEntity.setHousePurpose(subEntity.getHousePurpose());
            houseEntity.setWhereLayer(subEntity.getWhereLayer());

            houseEntity.setLayerArea(subEntity.getLayerArea());
            houseEntity.setArea(subEntity.getArea());
            houseEntity.setMinAcreage(subEntity.getMinAcreage());
            houseEntity.setPrice(subEntity.getPrice());
            houseEntity.setUnit(subEntity.getUnit());

            houseEntity.setLayerHeight(subEntity.getLayerHeight());
            houseEntity.setMaxElectric(subEntity.getMaxElectric());
            houseEntity.setLessLease(subEntity.getLessLease());
            houseEntity.setMoreLease(subEntity.getMoreLease());
            houseEntity.setEnterTime(subEntity.getEnterTime());
            houseEntity.setBearing(subEntity.getBearing());
            houseEntity.setHosDescribe(subEntity.getHosDescribe());

            houseEntity.setHouseCode(CodeGenUtils.generate("wh"));
            //处理层房源中的fileList

            for (int i1 = 0; i1 < houseEntity.getFileList().size(); i1++) {
                if(houseEntity.getFileList().get(i1).getFileUse().equals(HouseFileUse.HouseInner)){
                    houseEntity.getFileList().remove(houseEntity.getFileList().get(i1));
                    i1 = -1;
                }
            }
            //处理子房源中fileList
            for (FileRelationEntity fileOne : subEntity.getFileList()) {
                if(fileOne.getFileUse().equals(HouseFileUse.HouseInner)){
                    houseEntity.getFileList().add(fileOne);
                }
            }
            HouseEntity hObjNewCopy = new HouseEntity();
            HouseEntity hObj1 = houseManager.createTwoVersion(houseEntity);
            BeanUtils.copyProperties(hObjNewCopy,hObj1);
            resultList.add(hObjNewCopy);
        }



        response.setResult(resultList);

        return this.success(response);
    }
    /**
     * 修改房源
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();

        if (null != request.getEntity()) {
            houseManager.update(request.getEntity());
        }

        return this.success(response);
    }

    /**
     * 修改房源v2.8.0
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/updateTwoVersion",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> updateTwoVersion(@RequestBody HouseCreateServiceRequest request) throws IllegalAccessException {

        HouseServiceResponse response = new HouseServiceResponse();

        HouseCreateVo uiEntity = request.getEntity();
        if(null==request.getEntity()){
            throw new RemoveStackBusinessException("请传入!(entity对象)");
        }
        HouseEntity uiLayerEntity = uiEntity.getSubHosList().get(0);
        if(null==uiLayerEntity){
            throw new RemoveStackBusinessException("层对象子房源为空,请传入!(subHosList)");
        }
        if(null==uiEntity.getLocationCode()){
            throw new RemoveStackBusinessException("位置编码为空,请传入!(locationCode)");
        }

        //1,校验前端必传字段
        // 根据业务需求对应字段校验
        List<String> list = new ArrayList();
        list.add("hasWholeRental");
        Map<String, String> checkNameParent = uiEntity.usePramCheckNameNoReturn();
        ParamUtilPro<HouseCreateVo> paramUtilPro = new ParamUtilPro<>();
        paramUtilPro.check(uiEntity,list,checkNameParent);


        //start校验
        List<String> sublist = new ArrayList();

        sublist.add("houseStyle");
        sublist.add("housePurpose");
        sublist.add("whereLayer");

        sublist.add("layerArea");
        sublist.add("area");
        sublist.add("minAcreage");
        sublist.add("price");
        sublist.add("unit");

        sublist.add("layerHeight");
        sublist.add("maxElectric");
        sublist.add("lessLease");
        sublist.add("moreLease");
        sublist.add("enterTime");
        Map<String, String> checkName = uiLayerEntity.usePramCheckNameNoReturn();
        //sublist.add("bearing");//层重前端校验,存在1层不填,其他填的情况

        ParamUtilPro<HouseEntity> subUtil = new ParamUtilPro<>();
        subUtil.check(uiLayerEntity,sublist,checkName);
        //end校验

        HouseCreateVo newEntity = new HouseCreateVo();

        newEntity.setHouseCode(uiEntity.getHouseCode());
        //单独赋值start
        newEntity.setHouseStyle(uiLayerEntity.getHouseStyle());
        newEntity.setHouseStyleName(uiLayerEntity.getHouseStyleName());
        newEntity.setHousePurpose(uiLayerEntity.getHousePurpose());
        newEntity.setHousePurposeName(uiLayerEntity.getHousePurposeName());
        newEntity.setWhereLayer(uiLayerEntity.getWhereLayer());
        newEntity.setLayerArea(uiLayerEntity.getLayerArea());
        newEntity.setArea(uiLayerEntity.getArea());
        newEntity.setMinAcreage(uiLayerEntity.getMinAcreage());
        newEntity.setPrice(uiLayerEntity.getPrice());
        newEntity.setUnit(uiLayerEntity.getUnit());
        newEntity.setLayerHeight(uiLayerEntity.getLayerHeight());
        newEntity.setMaxElectric(uiLayerEntity.getMaxElectric());
        newEntity.setLessLease(uiLayerEntity.getLessLease());
        newEntity.setMoreLease(uiLayerEntity.getMoreLease());
        newEntity.setEnterTime(uiLayerEntity.getEnterTime());
        newEntity.setBearing(uiLayerEntity.getBearing());
        //单独赋值end
        BaseUserEntity userEntity = CurrentContext.getUserInfo();
        newEntity.setLastUpdateCode(userEntity.getUserCode());
        newEntity.setLastUpdateName(userEntity.getUserName());
        newEntity.setLastUpdateTime(new Date());

        //处理子房源中fileList
        for (FileRelationEntity fileOne : uiLayerEntity.getFileList()) {
            if(fileOne.getFileUse().equals(HouseFileUse.HouseInner)){
                uiEntity.getFileList().add(fileOne);
            }
        }
        newEntity.setFileList(uiEntity.getFileList());
        newEntity.setHosDescribe(uiLayerEntity.getHosDescribe());
        newEntity.setLocationCode(uiEntity.getLocationCode());
        newEntity.setHasWholeRental(uiEntity.getHasWholeRental());



        if (null != request.getEntity()) {
            houseManager.updateTwoVersion(newEntity);
        }

        return this.success(response);
    }

    /**
     * 修改房源基本信息
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/updateCut",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> updateCut(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();

        if (null != request.getEntity()) {
            houseManager.updateCut(request.getEntity());
        }

        return this.success(response);
    }

    /**
     * 上架
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/up",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> up(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>(16);
        param.put("houseCode", request.getEntity().getHouseCode());
        houseManager.up(param);

        return this.success(response);
    }

    /**
     * 下架
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/down",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> down(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getHouseCode())) {
            throw new RemoveStackBusinessException("房源编码为空");
        }

        if (StringUtils.isEmpty(request.getEntity().getDownReason())) {
            throw new RemoveStackBusinessException("房源下架原因为空");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("houseCode", request.getEntity().getHouseCode());
        param.put("downReason", request.getEntity().getDownReason());
        // 租赁到期时间
        if(request.getEntity().getLeaseExpiration() != null){
            param.put("leaseExpiration", request.getEntity().getLeaseExpiration());
        }
        String message = houseManager.down(param);

        return this.success(response,message);
    }

    /**
     * 公开隐藏  房源详情对同部门是否可见
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/onOpenFlag",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> onOpenFlag(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();
        houseManager.onOpenFlag(request.getEntity().getHouseCode());
        return this.success(response);
    }

    @RequestMapping(path = "/offOpenFlag",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> offOpenFlag(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();
        houseManager.offOpenFlag(request.getEntity().getHouseCode());
        return this.success(response);
    }

    /**
     * 共享部门自定义房源详情是否可见  on/off
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/onShareOpenFlag",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> onShareOpenFlag(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();
        houseManager.onShareOpenFlag(request.getEntity().getHouseCode());
        return this.success(response);
    }

    @RequestMapping(path = "/offShareOpenFlag",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> offShareOpenFlag(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();
        houseManager.offShareOpenFlag(request.getEntity().getHouseCode());
        return this.success(response);
    }

    /**
     * 合作房源自定义房源详情是否可见
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/onCooOpenFlag",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> onCooOpenFlag(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();
        houseManager.onCooOpenFlag(request.getEntity().getHouseCode());
        return this.success(response);
    }

    @RequestMapping(path = "/offCooOpenFlag",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> offCooOpenFlag(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();
        houseManager.offCooOpenFlag(request.getEntity().getHouseCode());
        return this.success(response);
    }

    @RequestMapping(path = "/checkAuth",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> checkAuth(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();
        if (StringUtils.isEmpty(request.getEntity().getHouseCode())
                || StringUtils.isEmpty(request.getEntity().getCheckCode())) {
            throw new RemoveStackBusinessException("房源编码不能为空");
        }
        String houseCode = request.getEntity().getHouseCode();
        String checkCode = request.getEntity().getCheckCode();
        HouseEntity house = houseDbDao.selectUniqueByProp("houseCode", houseCode);
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();
        UserEntity owner = userManager.selectUniqueByProp(house.getEmpCode());
        Boolean isNoPass = true;
        isNoPass = checkPermissionManager.checkNoPass(cur, owner, isNoPass);

        if (isNoPass) {
            checkPermissionManager.checkPermissions("hos",checkCode,house,cur);
        }


        return this.success(response);

    }


    /**
     * 看过该房源的客户集合
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/seeHouseCusList",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> seeHouseCusList(@RequestBody HouseServiceRequest request) {

        HouseServiceResponse response = new HouseServiceResponse();

        Map param = new HashMap<String, Object>();
        param.put("houseCode", request.getEntity().getHouseCode());
        List<CustomerEntity> result = customerManager.seeHouseCusList(param);
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 查询房源
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "根据条件位置查询",
            notes = "根据条件locationCode,查询房源列表集合信息")
    @RequestMapping(path = "/queryHosByLoc",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryHosByLoc(@RequestBody HouseServiceRequest request) {
        HouseServiceResponse response = new HouseServiceResponse();
        HouseQueryEntity houseQuery = request.getHouseQuery();
        Map<String, Object> param = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(houseQuery.getLocationCode())) {
            // 查询此编码所属的房源
            param.put("locationCode", houseQuery.getLocationCode());
        }

        //增加模糊查询
        if (StringUtils.isNotEmpty(request.getKeyword())) {
            //判断模糊搜索是否是数字
            boolean isNumber = StringUtils.isNumeric(request.getKeyword());
            if (isNumber && request.getKeyword().length() != 11 &&request.getKeyword().length() != 8)  {
                Integer keywordNumber = Integer.parseInt(request.getKeyword());
                param.put("keywordNumber", keywordNumber);
            } else {
                param.put("keyword", request.getKeyword());
            }
        }

        //房源查询实体类不为空则进入以下方法体
        param = houseLocQuery(param, houseQuery);
        param.put("userCode", CurrentContext.getUserCode());

        List<HouseEntity> houseEntityList = houseManager.findByMap(param);
        Map<String, Object> fileParam = new HashMap<String, Object>();
        for (int i = 0; i < houseEntityList.size(); i++) {
            // 查询位置
//            HouseLocationEntity locationEntity = houseLocationManager.findOne("locationCode", houseEntityList.get(i).getLocationCode());
//            houseEntityList.get(i).setHouseLocation(locationEntity);

            fileParam.clear();
            fileParam.put("ownerCode", houseEntityList.get(i).getLocationCode());
            List<FileRelationEntity> fileLists = fileRelationManager.findByMap(fileParam);
            houseEntityList.get(i).getHouseLocation().setFileList(fileLists);

            fileParam.clear();
            fileParam.put("ownerCode", houseEntityList.get(i).getHouseCode());
            fileParam.put("fileUse", "1");//fileUse为1表示主图
            List<FileRelationEntity> fileListsHos = fileRelationManager.findByMap(fileParam);
            houseEntityList.get(i).setFileList(fileListsHos);

        }

        //加上未跟进天数
        handlerDaysNotFollowup(houseEntityList);
        response.setResult(houseEntityList);

        //加入位置中共有房源数量Has
        Map<String, Object> paramHas = new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(houseQuery.getLocationCode())) {
            // 查询此编码所属的房源
            paramHas.put("locationCode", houseQuery.getLocationCode());
        }
        List<HouseEntity> hosList = houseManager.findByMap(paramHas);
        int i = hosList.size();
        String count = i + "";
        response.setStackTrace(count);

        return this.success(response);

    }

    /**
     * 查询房源
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "地图使用--根据房源条件查询房源对应位置列表",
            notes = "根据条件keyword,查询房源集合信息,不分页")
    @RequestMapping(path = "/queryLocByHos",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryLocByHos(@RequestBody HouseServiceRequest request) throws IllegalAccessException {
        HouseServiceResponse response = new HouseServiceResponse();
        HouseQueryEntity houseQuery = request.getHouseQuery();


        //房源查询实体类不为空则进入以下方法体
        //param = houseLocQuery(param, houseQuery);
        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        if (UserType.Individual.equals(user.getUserType())
                && HouseQueryType.DEPT.equals(houseQuery.getQueryType())) {
            response.setResult(new PageResult<HouseEntity>());
            return this.success(response);
        }
        Map<String, Object> param = houseQueryHandler.queryMap(houseQuery);

        houseQueryHandler.keyword(param, request.getKeyword());

        if (StringUtils.isNotEmpty(request.getEmpCode())) {
            // 查询此编码所属的房源
            param.put("empCode", request.getEmpCode());
        }

        //加入可视区域进行查询
        HouseLocRegionEntity leftDown = request.getLeftDown();
        HouseLocRegionEntity rightUp = request.getRightUp();
        if (leftDown != null) {
            param.put("leftDownLng", leftDown.getLongitude());
            param.put("leftDownLat", leftDown.getLatitude());
        }
        if (rightUp != null) {
            param.put("rightUpLng", rightUp.getLongitude());
            param.put("rightUpLat", rightUp.getLatitude());
        }


        //地图查询点根据可视区域,不查省市区
        param.put("province", null);
        param.put("city", null);
        param.put("region", null);
        param.put("street", null);
        List<HouseLocationEntity> locTotalList = new ArrayList<HouseLocationEntity>();

        //查询参数单独加,外部人员只能查自己的房源点,内部人员可以查公司房源点
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        UserEntity curUser = userManager.selectUniqueByProp(userInfo.getUserCode());
        String userType = curUser.getUserType();
        if(userType.equals(UserType.Individual)){
            param.put("createCode",curUser.getUserCode());
            String hosQueryType = request.getHouseQuery().getQueryType();
            if(null!=hosQueryType){
                if(hosQueryType.equals(HouseQueryType.DEPT)){
                    if(null!=userInfo.getOwnerDeptCode()){
                        param.remove("createCode");
                        param.put("createDeptCode",userInfo.getOwnerDeptCode());
                    }

                }

            }
        }
        //查询参数单独加,外部人员end

        locTotalList = houseLocationManager.queryLocByHos(param);
        Map<String, Object> hlMap = new HashMap<>();

        for (HouseLocationEntity entity : locTotalList) {
            hlMap.clear();
            hlMap.put("locationCode",entity.getLocationCode());
            hlMap.put("houseStatus",HouseStatus.hotRenting);
            List<HouseEntity> hosList = houseManager.findByMap(hlMap);
            entity.setAllHouseStatus(HouseStatus.HasLease);//已成交
            if(hosList.size()>0){
                entity.setAllHouseStatus(HouseStatus.hotRenting);//有一个热租就是热租中
            }

        }
        response.setResult(locTotalList);
        return this.success(response);
    }

    /**
     * 房源地图聚合
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "地图使用--根据房源条件地图聚合",
            notes = "根据条件keyword,查询房源集合信息,不分页")
    @RequestMapping(path = "/togetherCount",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> togetherCount(@RequestBody HouseServiceTogetherRequest request) throws IllegalAccessException {
        HouseServiceResponse response = new HouseServiceResponse();
        HouseQueryEntity houseQuery = request.getHouseQuery();

        Map<String, Object> param = houseQueryHandler.queryMap(houseQuery);
        if (request.getCityOut() == null) {
            throw new RemoveStackBusinessException("城市cityOut字段不能为空!");
        }

        param.put("city", request.getCityOut());
//        if(request.getHouseQuery().getCity()!=null){
//            param.put("city", request.getHouseQuery().getCity());
//        }
        param.put("level", request.getLevel());

        //房源查询实体类不为空则进入以下方法体
        //param = houseLocQuery(param, houseQuery);
        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        if (UserType.Individual.equals(user.getUserType())
                && HouseQueryType.DEPT.equals(houseQuery.getQueryType())) {
            response.setResult(new PageResult<HouseEntity>());
            return this.success(response);
        }


        houseQueryHandler.keyword(param, request.getKeyword());

        if (StringUtils.isNotEmpty(request.getEmpCode())) {
            // 查询此编码所属的房源
            param.put("empCode", request.getEmpCode());
        }

        //加入可视区域进行查询
        HouseLocRegionEntity leftDown = request.getLeftDown();
        HouseLocRegionEntity rightUp = request.getRightUp();
//        if(leftDown!=null){
//            param.put("leftDownLng", leftDown.getLongitude());
//            param.put("leftDownLat", leftDown.getLatitude());
//        }
//        if(rightUp!=null){
//            param.put("rightUpLng", rightUp.getLongitude());
//            param.put("rightUpLat", rightUp.getLatitude());
//        }

        //统计不查可视区域,不查省市区
        param.put("leftDownLng", null);
        param.put("leftDownLat", null);
        param.put("rightUpLng", null);
        param.put("rightUpLat", null);

        //统计,不查省市区
        param.put("province", null);
        param.put("region", null);
        param.put("street", null);


        List<HouseMapEntity> locTotalList = new ArrayList<HouseMapEntity>();
        locTotalList = houseMapManager.togetherCount(param);
        response.setResult(locTotalList);
        return this.success(response);
    }

    public Map houseLocQuery(Map param, HouseQueryEntity houseQuery) {
        if (null != houseQuery) {

            //start 查询类型, 我的, 部门, 收藏
            if (StringUtils.isNotEmpty(houseQuery.getQueryType())) {
                if (houseQuery.getQueryType().equals(HouseQueryType.MINE)) {
                    // 如果不是领导就查询我的
                    Map<String, Object> map = new HashMap<>();
                    map.put("leaderCode", CurrentContext.getUserCode());
                    List<DeptEntity> depts = deptDbDao.selectByMap(map);
                    map.clear();
                    if (CollectionUtil.isNotEmpty(depts)) {
                        // 查询该部门所属房源
                        if (depts.get(0).getDeptName().contains("大区")) {
                            map.put("parentCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                            List<DeptEntity> deptEntities = DeptApiClient.queryByMap(map);
                            // 获取所有分部
                            if (CollectionUtil.isNotEmpty(deptEntities)) {
                                List<String> deptCodes = new ArrayList<>();
                                for (DeptEntity dept : deptEntities) {
                                    deptCodes.add(dept.getDeptCode());
                                }
                                // 查询所有下级部门房源
                                param.put("createDeptCodes", deptCodes);
                            }
                        } else if (depts.get(0).getDeptName().contains("分部")) {
                            param.put("createDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                        } else {
                            // 查询我的
                            param.put("empCode", CurrentContext.getUserCode());
                        }
                    } else {
                        // 查询我的
                        param.put("empCode", CurrentContext.getUserCode());
                    }

                } else if (houseQuery.getQueryType().equals(HouseQueryType.COLLECT)) {
                    // 查询我的收藏
                    param.put("hasFavorite", Boolean.TRUE);
                    param.put("favEmpCode", CurrentContext.getUserCode());
                } else if (houseQuery.getQueryType().equals(HouseQueryType.AGENT)) {
                    param.put("houseType", HouseType.AGENT); // 经纪人

                } else if (houseQuery.getQueryType().equals(HouseQueryType.PLATFORM)) {

                    param.put("houseType", HouseType.PLATFORM); // 平台

                } else if (houseQuery.getQueryType().equals(HouseQueryType.NEARBY)) {
                    // 附近房源
                } else if (houseQuery.getQueryType().equals(HouseQueryType.ALL)) {
                    // 全部房源
                    //公开
                    //param.put("openFlag", true);
                } else if (houseQuery.getQueryType().equals(HouseQueryType.UNION)) {
                    // 我合作的经纪人房源
                    param.put("hasCoo", Boolean.TRUE);
                    param.put("cooEmpCode", CurrentContext.getUserCode());
                } else if (houseQuery.getQueryType().equals(HouseQueryType.DEPT)) {
                    // 同部门房源
                    param.put("createDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
                }
            }
            //end 查询类型, 我的, 部门,收藏

            if (StringUtils.isNotEmpty(houseQuery.getHouseCode())) {
                param.put("houseCode", houseQuery.getHouseCode());
            }

            if (StringUtils.isNotEmpty(houseQuery.getHouseName())) {
                param.put("houseName", houseQuery.getHouseName());
            }

            if (StringUtils.isNotEmpty(houseQuery.getOwnerPhone())) {
                param.put("ownerPhone", houseQuery.getOwnerPhone());
            }
            if (StringUtils.isNotEmpty(houseQuery.getHouseOwnerName())) {
                param.put("houseOwnerName", houseQuery.getHouseOwnerName());
            }

            if (StringUtils.isNotEmpty(houseQuery.getHouseStatus())) {
                param.put("houseStatus", houseQuery.getHouseStatus());
            }

            if (StringUtils.isNotEmpty(houseQuery.getHousePurpose())) {
                param.put("housePurpose", houseQuery.getHousePurpose());
            }

            if (StringUtils.isNotEmpty(houseQuery.getEmpCode())) {
                param.put("empCode", houseQuery.getEmpCode());
            }

            if (StringUtils.isNotEmpty(houseQuery.getEmpName())) {
                param.put("empName", houseQuery.getEmpName());
            }

            if (StringUtils.isNotEmpty(houseQuery.getFireLevel())) {
                param.put("fireLevel", houseQuery.getFireLevel());
            }

            if (null != houseQuery.getHasEia()) {
                param.put("hasEia", houseQuery.getHasEia());
            }

            if (null != houseQuery.getHasCut()) {
                param.put("hasCut", houseQuery.getHasCut());
            }

            if (null != houseQuery.getHasCertificate()) {
                param.put("hasCertificate", houseQuery.getHasCertificate());
            }

            if (null != houseQuery.getHasRegistry()) {
                param.put("hasRegistry", houseQuery.getHasRegistry());
            }

            if (null != houseQuery.getHasShortLease()) {
                param.put("hasShortLease", houseQuery.getHasShortLease());
            }

            if (null != houseQuery.getHasParking()) {
                param.put("hasParking", houseQuery.getHasParking());
            }

            if (null != houseQuery.getMinArea()) {
                param.put("minArea", houseQuery.getMinArea());
            }

            if (null != houseQuery.getMaxArea()) {
                param.put("maxArea", houseQuery.getMaxArea());
            }

            if (null != houseQuery.getMinPrice()) {
                param.put("minPrice", houseQuery.getMinPrice());
            }

            if (null != houseQuery.getMaxPrice()) {
                param.put("maxPrice", houseQuery.getMaxPrice());
            }
            //最小电量不为空
            if (null != houseQuery.getMinElectric()) {
                param.put("minElectric", houseQuery.getMinElectric());
            }
            //最大电量不为空
            if (null != houseQuery.getMaxElectric()) {
                param.put("maxElectric", houseQuery.getMaxElectric());
            }
            //省编号不为空
            if (StringUtils.isNotEmpty(houseQuery.getProvince())) {
                param.put("province", houseQuery.getProvince());
            }
            //城市编号不为空
            if (StringUtils.isNotEmpty(houseQuery.getCity())) {
                param.put("city", houseQuery.getCity());
            }
            //区域不为空
            if (StringUtils.isNotEmpty(houseQuery.getRegion())) {
                param.put("region", houseQuery.getRegion());
            }
            //街道编码不为空
            if (StringUtils.isNotEmpty(houseQuery.getStreet())) {
                param.put("street", houseQuery.getStreet());
            }
            if (StringUtils.isNotEmpty(houseQuery.getCommunity())) {
                param.put("community", houseQuery.getCommunity());
            }
            //详情地址不为空
            if (StringUtils.isNotEmpty(houseQuery.getDetailAddress())) {
                param.put("detailAddress", houseQuery.getDetailAddress());
            }
            //创建人编号不为空
            if (StringUtils.isNotEmpty(houseQuery.getCreateCode())) {
                param.put("createCode", houseQuery.getCreateCode());
            }
            //创建部门编号不为空
            if (StringUtils.isNotEmpty(houseQuery.getCreateDeptCode())) {
                param.put("createDeptCode", houseQuery.getCreateDeptCode());
            }
            //创建部门编号不为空
            if (StringUtils.isNotEmpty(houseQuery.getHouseNumber())) {
                param.put("houseNumber", houseQuery.getHouseNumber());
            }

            //创建时间
            if (null != houseQuery.getCreateTimeStart()) {
                try {
                    param.put("createTimeStart", DateUtil.beginOfDay(houseQuery.getCreateTimeStart()));
                } catch (Exception e) {
                }
            }
            //创建结束时间
            if (null != houseQuery.getCreateTimeEnd()) {
                try {
                    param.put("createTimeEnd", DateUtil.endOfDay(houseQuery.getCreateTimeEnd()));
                } catch (Exception e) {
                }
            }

            if (StringUtils.isNotEmpty(houseQuery.getHouseownerType())) {
                param.put("houseownerType", houseQuery.getHouseownerType());
            }
            if (null != houseQuery.getLayerHeight()) {
                param.put("layerHeight", houseQuery.getLayerHeight());
            }
            if (null != houseQuery.getWhereLayer()) {
                param.put("whereLayer", houseQuery.getWhereLayer().toString());
            }
            if (StringUtils.isNotEmpty(houseQuery.getFloorFlag())) {
                if (houseQuery.getFloorFlag().equals("1")) {
                    param.put("underlayer", true);
                } else if (houseQuery.getFloorFlag().equals("2")) {
                    param.put("notUnderlayer", true);
                }
            }
            if (null != houseQuery.getStartEnterTime()) {
                try {
                    param.put("startEnterTime", DateUtil.beginOfDay(houseQuery.getStartEnterTime()));
                } catch (Exception e) {
                }
            }
            if (null != houseQuery.getEndEnterTime()) {
                try {
                    param.put("endEnterTime", DateUtil.endOfDay(houseQuery.getEndEnterTime()));
                } catch (Exception e) {
                }
            }
            if (StringUtils.isNotEmpty(houseQuery.getCompanyName())) {
                param.put("companyName", houseQuery.getCompanyName());
            }
            if (StringUtils.isNotEmpty(houseQuery.getForInsdustry())) {
                //行业性质
                param.put("forInsdustry", houseQuery.getForInsdustry());
            }
            if (StringUtils.isNotEmpty(houseQuery.getCommunity())) {
                param.put("community", houseQuery.getCommunity());
            }
            if (houseQuery.getBearing() != null) {
                param.put("bearing", houseQuery.getBearing());
            }
            if (houseQuery.getLeaseNegotiable() != null) {
                param.put("leaseNegotiable", houseQuery.getLeaseNegotiable());
            }
            if (houseQuery.getOfTheArea() != null) {
                param.put("ofTheArea", houseQuery.getOfTheArea());
            }
        }
        param.put("isDeleted", false);
        return param;
    }

    /**
     * 查询房源
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "根据条件查询房源上下架列表",
            notes = "根据条件keyword,查询房源分页集合信息")
    @RequestMapping(path = "/upDown/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> upDownQuery(@RequestBody HouseServiceRequest request) throws IllegalAccessException {
        HouseServiceResponse response = new HouseServiceResponse();
        HouseQueryVo houseQueryVo = request.getEntity();
        Map<String, Object> param = houseQueryVo.queryMap();

        PageResult<HouseUpdownEntity> result = houseManager.upDownQuery(param, request.getStart(), request.getPageSize());

        response.setResult(result);
        return this.success(response);
    }

    @IgnoreLog
    @RequestMapping(path = "/houseExport",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FileExportException, IOException {

        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;

        Map<String,Object> param = new HashMap<>();

        if (null != httpServletRequest.getParameter("createStartTime") && StringUtils.isNotEmpty(httpServletRequest.getParameter("createStartTime"))) {
            Date startTime = new Date();
            Date createStartTime = DateFormatUtil.dateTransition(httpServletRequest.getParameter("createStartTime"));
            startTime = DateFormatUtil.dayBeginFormat(DateFormatUtil.getFormatDateTime(createStartTime, DateFormatUtil.DateTimeFormatDay));
            param.put("startTime",startTime);
        } else {
            throw new RemoveStackBusinessException("请选择房源创建起止时间");
        }
        if (null != httpServletRequest.getParameter("createEndTime") && StringUtils.isNotEmpty(httpServletRequest.getParameter("createEndTime"))) {
            Date endTime = new Date();
            Date createEndTime = DateFormatUtil.dateTransition(httpServletRequest.getParameter("createEndTime"));
            endTime = DateFormatUtil.dayEndFormat(DateFormatUtil.getFormatDateTime(createEndTime, DateFormatUtil.DateTimeFormatDay));
            param.put("endTime",endTime);

        }
        if (null!=httpServletRequest.getParameter("houseStatus")&&StringUtils.isNotEmpty(httpServletRequest.getParameter("houseStatus"))){
            String houseStatus = httpServletRequest.getParameter("houseStatus");
            param.put("houseStatus",houseStatus);
        }

        //数据
        List<HouseEntity> data = houseManager.export(param);


        String dir =  "/exportTemplate/";
        String xmlName =  "houseExport.xml";
        String exportName = "本部门房源列表";
        String path =dir+xmlName;

        InputStream inputStream = this.getClass().getResourceAsStream(path);
        ExportConfigFactory.exportMethod(data,httpServletResponse,inputStream,exportName);
    }

    /**
     *房源合同周期查询
     * @param request
     * @return
     */
    @RequestMapping(path = "/selectContractCensus",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectContractCensus(@RequestBody HouseServiceRequest request){
        HouseServiceResponse response = new HouseServiceResponse();
        Map<String, Object> map = new HashMap<String, Object>();
        TimeSplit ts1 = new TimeSplit(null, 2, "MM");
        Date day = DateFormatUtil.getCurrentDateTime();

        Date afterContract = DateFormatUtil.addDate(day, ts1.getAfterNumber(), ts1.getAfterFormat());
        map.put("contractEndTimeEnd", DateFormatUtil.dayBegin(afterContract));

        TimeSplit ts2 = new TimeSplit(SystemConfigKey.Hos_Followup_Census_Time.getCode(), -7, "DD");

        Date afterFollowup = DateFormatUtil.addDate(day, ts2.getAfterNumber(), ts2.getAfterFormat());
        map.put("lastFollowupTime", DateFormatUtil.dayBegin(afterFollowup));
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();

        map.put("isDeleted",Boolean.FALSE);
        if (StringUtils.isNotEmpty(cur.getOwnerDeptCode())){
            String leaderCode = deptManager.getDeptEntity(cur.getOwnerDeptCode()).getLeaderCode();
            if (leaderCode.equals(CurrentContext.getUserCode())){
                List<String> deptCodes = deptManager.getDownDeptCode(cur.getOwnerDeptCode());
                if (deptCodes!=null && deptCodes.size()>0){
                    map.put("ownerDeptCodes",deptCodes);
                }else {
                    map.put("ownerDeptCode", cur.getOwnerDeptCode());
                }
            }else{
                map.put("ownerDeptCode", cur.getOwnerDeptCode());
            }
        }else {
            map.put("empCode",cur.getUserCode());
        }
        PageResult<HouseEntity> result = houseManager.selectContractCensus(map,request.getStart(),request.getPageSize());

        response.setResult(result);
        return this.success(response);
    }
}

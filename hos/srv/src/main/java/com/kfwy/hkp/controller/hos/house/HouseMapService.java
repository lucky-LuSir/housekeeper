package com.kfwy.hkp.controller.hos.house;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.hos.house.vo.HouseMapServiceRequest;
import com.kfwy.hkp.controller.hos.house.vo.HouseMapServiceResponse;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.enums.CustomerStatus;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.hos.house.business.IHouseMapManager;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.dto.HousePriceDto;
import com.kfwy.hkp.hos.house.dto.WareHouseDto;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.entity.HouseMapEntity;
import com.kfwy.hkp.hos.house.entity.HousePriceEntity;
import com.kfwy.hkp.hos.house.entity.WarehouseEntity;
import com.kfwy.hkp.hos.house.enums.HouseStatus;
import com.kfwy.hkp.trade.order.dao.IOrderDbDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/12/3
 *
 * @author kfwy_it_013
 */

@RestController
@RequestMapping(path = "/houseMap")
public class HouseMapService extends SpringRestService {

    @Autowired
    private IHouseMapManager houseMapManager;
    @Autowired
    private IHouseManager houseManager;
    @Autowired
    private IHouseDbDao houseDbDao;
    @Autowired
    private ICustomerDbDao customerDbDao;
    @Autowired
    private IOrderDbDao orderDbDao;


    /**
     * 查询房源
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody HouseMapServiceRequest request) {
        // 校验
        checkAuth(request.getKey());

        HouseMapServiceResponse response = new HouseMapServiceResponse();
        Map<String, Object> param = request.getParam();
        PageResult<WarehouseEntity> pageResult = houseMapManager.query(param, request.getStart(), request.getPageSize());
        response.setPageResult(pageResult);
        return this.success(response);
    }

    /**
     * 查询房源
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/count",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> count(@RequestBody HouseMapServiceRequest request) {
        // 校验
        checkAuth(request.getKey());

        HouseMapServiceResponse response = new HouseMapServiceResponse();
        Map<String, Object> param = request.getParam();
        List<HouseMapEntity> houseMapEntities = houseMapManager.count(param);
        response.setHouseEntities(houseMapEntities);
        return this.success(response);
    }

    /**
     * 查询房源百度地图点
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "point/count",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> pointCount(@RequestBody HouseMapServiceRequest request) {
        // 校验
        checkAuth(request.getKey());

        HouseMapServiceResponse response = new HouseMapServiceResponse();
        Map<String, Object> param = request.getParam();
        List<HouseMapEntity> houseMapEntities = houseMapManager.pointCount(param);
        response.setHouseEntities(houseMapEntities);
        return this.success(response);
    }

    /**
     * 每月价格
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/monthPrice",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> monthPrice(@RequestBody HouseMapServiceRequest request) {
        // 校验
        checkAuth(request.getKey());

        HouseMapServiceResponse response = new HouseMapServiceResponse();
        Map<String, Object> param = request.getParam();
        List<HousePriceDto> priceDtos = houseMapManager.monthPrice(param);
        response.setPriceDtos(priceDtos);
        return this.success(response);
    }

    /**
     * 月平均价格
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/avgPriceByMonth",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> avgPriceByMonth(@RequestBody HouseMapServiceRequest request) {
        // 校验
        checkAuth(request.getKey());

        HouseMapServiceResponse response = new HouseMapServiceResponse();
        Map<String, Object> param = request.getParam();
        HousePriceEntity avgPriceMonth = houseMapManager.avgPriceByMonth(param);
        response.setPriceEntity(avgPriceMonth);
        return this.success(response);
    }

    /**
     * 查询房源详情
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody HouseMapServiceRequest request) {
        // 校验
        checkAuth(request.getKey());

        HouseMapServiceResponse response = new HouseMapServiceResponse();
        WareHouseDto wareHouseDto = new WareHouseDto();
        Map<String, Object> param = new HashMap<>();
        param.put("houseCode", request.getHouseCode());
        HouseEntity houseEntity = houseManager.findOne(param);
        if (houseEntity != null) {
            WarehouseEntity warehouseEntity = getWarehouseEntity(houseEntity);
            // 查询所属者的客户和房源数
            param.clear();
            param.put("empCode", houseEntity.getEmpCode());
            param.put("houseStatus", HouseStatus.hotRenting);
            param.put("cusStatus", CustomerStatus.FOLLOWING);
            param.put("isDeleted", Boolean.FALSE);
            int houseCount = houseDbDao.selectCount("selectByMapCount", param);
            int cusCount = customerDbDao.selectCount("selectByMapCount", param);
            wareHouseDto.setWarehouseEntity(warehouseEntity);
            wareHouseDto.setHouseCount(houseCount);
            wareHouseDto.setCusCount(cusCount);
            response.setWareHouseDto(wareHouseDto);
        } else {
            response.setWareHouseDto(null);
        }
        return this.success(response);
    }


    /**
     * 查询业绩
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/achievement",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> achievement(@RequestBody HouseMapServiceRequest request) {
        // 校验
        checkAuth(request.getKey());

        HouseMapServiceResponse response = new HouseMapServiceResponse();
        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", Boolean.FALSE);
        int houseCount = houseDbDao.selectCount("selectByMapCount", param);
        int cusCount = customerDbDao.selectCount("selectByMapCount", param);
        int orderCount = orderDbDao.selectCount("selectByMapCount", param);
        long moneyReturn = orderDbDao.selectMoneyReturn();
        param.clear();
        param.put("houseCount", houseCount);
        param.put("cusCount", cusCount);
        param.put("orderCount", orderCount);
        param.put("moneyReturn", moneyReturn);
        response.setEntitys(param);
        return this.success(response);
    }

    /**
     * 转换为erp字段
     *
     * @param houseEntity
     * @return
     */
    private WarehouseEntity getWarehouseEntity(HouseEntity houseEntity) {
        WarehouseEntity warehouseEntity = new WarehouseEntity();
        //房源基本信息
        warehouseEntity.setHouseNumber(houseEntity.getHouseNumber());
        warehouseEntity.setWhCode(houseEntity.getHouseCode());
        warehouseEntity.setCode(houseEntity.getHouseCode());
        warehouseEntity.setHouseCode(houseEntity.getHouseCode());
        warehouseEntity.setHouseName(houseEntity.getHouseName());
        warehouseEntity.setHouseType(houseEntity.getHousePurpose());
        warehouseEntity.setWhName(houseEntity.getHouseName());
        warehouseEntity.setName(houseEntity.getHouseName());
        warehouseEntity.setWarehouseType(Integer.valueOf(houseEntity.getHouseStyle()));
        warehouseEntity.setWarehouseTypeName(houseEntity.getHouseStyleName());
        warehouseEntity.setCreateTime(houseEntity.getCreateTime());


        /*
         *  省市区
         * */
        warehouseEntity.setProvince(houseEntity.getProvince());
        warehouseEntity.setProvinceName(houseEntity.getProvinceName());
        warehouseEntity.setCity(houseEntity.getCity());
        warehouseEntity.setCityName(houseEntity.getCityName());
        warehouseEntity.setRegion(houseEntity.getRegion());
        warehouseEntity.setRegionName(houseEntity.getRegionName());
        warehouseEntity.setStreet(houseEntity.getStreet());
        warehouseEntity.setStreetName(houseEntity.getStreetName());
        if (houseEntity.getHouseLocation() != null) {
            warehouseEntity.setAddress(houseEntity.getHouseLocation().getDetailAddress());
            warehouseEntity.setLongitude(houseEntity.getHouseLocation().getLongitude());
            warehouseEntity.setTrafficFacilities(houseEntity.getHouseLocation().getTrafficFacilities());
            warehouseEntity.setLatitude(houseEntity.getHouseLocation().getLatitude());
        }

        if (houseEntity.getArea() != null) {
            // 可租面积
            warehouseEntity.setAcreage(houseEntity.getArea().floatValue());
            // 剩余面积
            warehouseEntity.setMinAcreage(houseEntity.getArea().floatValue());
        }
        // 总面积
        warehouseEntity.setTotalArea(houseEntity.getLayerArea() == null ? warehouseEntity.getAcreage()
                : houseEntity.getLayerArea().floatValue());

        // 价格
        warehouseEntity.setPrice(houseEntity.getPrice());
        // 价格单位
        warehouseEntity.setUnit(houseEntity.getUnit());
        // 层高
        if (houseEntity.getLayerHeight() != null) {
            warehouseEntity.setLayerHeight(houseEntity.getLayerHeight().floatValue());
        }
        // 总层数
        warehouseEntity.setLayerCount(Integer.valueOf(setReplace(houseEntity.getSeveralLayers())));
        // 所在层
        warehouseEntity.setNowLayer(houseEntity.getWhereLayer());
        // 最大电量
        if (houseEntity.getMaxElectric() != null) {
            warehouseEntity.setVoltage(houseEntity.getMaxElectric().toString());
        }

        // 消防等级
        warehouseEntity.setFireLevel(houseEntity.getFireLevel());
        // 最短租期
        warehouseEntity.setLessLease(houseEntity.getLessLease());
        // 房源结构
        warehouseEntity.setHouseStructure(houseEntity.getHouseStructure());
        warehouseEntity.setHouseStructureName(houseEntity.getHouseStructureName());
        // 物业费
        warehouseEntity.setPropertyFee(houseEntity.getPropertyFee());

        warehouseEntity.setHasEia(houseEntity.getHasEia());
        warehouseEntity.setHasPark(houseEntity.getHasParking());
        warehouseEntity.setHasCrane(houseEntity.getHasInstallCrane());
        warehouseEntity.setHasOfficeArea(houseEntity.getHasOfficeArea());
        warehouseEntity.setHasBathroom(houseEntity.getHasBathroom());
        warehouseEntity.setHasPlatform(houseEntity.getHasPlatform());
        warehouseEntity.setHasElevator(houseEntity.getHasElevator());
        warehouseEntity.setHasCut(houseEntity.getHasCut());
        warehouseEntity.setHasDischargeSewage(houseEntity.getHasDischargeSewage());
        warehouseEntity.setHasCertificate(houseEntity.getHasCertificate());
        warehouseEntity.setCanRegistryCompany(houseEntity.getHasRegistry());

        // 适合行业
        warehouseEntity.setForInsdustry(houseEntity.getForInsdustry());
        // 房源特色
        warehouseEntity.setFeatures(houseEntity.getFeatures());
        // 房源所属者
        warehouseEntity.setEmpCode(houseEntity.getEmpCode());
        warehouseEntity.setEmpPhone(houseEntity.getEmpPhone());
        warehouseEntity.setEmpName(houseEntity.getEmpName());
        warehouseEntity.setEmpUrl(houseEntity.getEmpImg());
        warehouseEntity.setFileImages(houseEntity.getFileList() == null ? null : houseEntity.getFileList());
        // 物业公司
        warehouseEntity.setPropertyCompany(houseEntity.getPropertyCompany());
        // 物业费
        warehouseEntity.setPropertyFee(houseEntity.getPropertyFee());
        // 最大通行车辆
        warehouseEntity.setMaxPassCar(houseEntity.getMaxPassCar());
        // 是否有行车
        warehouseEntity.setHasCrane(houseEntity.getHasCrane());
        // 货梯规格
        warehouseEntity.setElevatorNumber(houseEntity.getElevatorNumber());
        // 货梯开门
        warehouseEntity.setElevatorDoor(houseEntity.getElevatorDoor());
        // 货梯规格
        warehouseEntity.setElevatorStandard(houseEntity.getElevatorStandard());
        return warehouseEntity;
    }

    private String setReplace(String target) {
        if (target.length() > 1) {
            target = target.replace("层", "");
        }
        return target;
    }

    public void checkAuth(String key) {
        if (StringUtils.isEmpty(key) || !"@Housekeeper$".equals(key)) {
            throw new BusinessException("无权访问!");
        }
    }

}

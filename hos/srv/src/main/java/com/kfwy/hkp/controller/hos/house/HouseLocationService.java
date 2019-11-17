package com.kfwy.hkp.controller.hos.house;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.controller.hos.house.vo.HouseLocationServiceRequest;
import com.kfwy.hkp.controller.hos.house.vo.HouseLocationServiceResponse;
import com.kfwy.hkp.controller.hos.house.vo.HouseLocationTwoVersionServiceRequest;
import com.kfwy.hkp.hos.house.business.IHouseLocationManager;
import com.kfwy.hkp.hos.house.entity.HouseLocPolygonEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocRegionEntity;
import com.kfwy.hkp.hos.house.entity.HouseLocationEntity;
import com.kfwy.hkp.hos.house.enums.RepairReturn;
import com.kfwy.hkp.sys.area.business.ICompanyAreaManager;
import com.kfwy.hkp.sys.area.entity.CompanyAreaEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
 * Create By hjh on 2018/7/25
 */

@RestController
@RequestMapping(path = "/houseLocation")
public class HouseLocationService extends SpringRestService {

    @Autowired
    private IHouseLocationManager houseLocationManager;

    @Autowired
    private ICompanyAreaManager companyAreaManager;


    /**
     * 查询房源位置
     * @param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody HouseLocationServiceRequest request) {

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();

        Map<String,Object> map = new HashMap<>(16);

        if(StringUtils.isNotEmpty(request.getKeyWord())){
            map.put("keyWord",request.getKeyWord());
        }
        if (request.getEntity()!=null){
            if(StringUtils.isNotEmpty(request.getEntity().getCreateDeptCode())){
                map.put("createDeptCode",request.getEntity().getCreateDeptCode());
            }

            if(StringUtils.isNotEmpty(request.getEntity().getCreateCode())){
                map.put("createCode",request.getEntity().getCreateCode());
            }
        }
        PageResult<HouseLocationEntity> byMap = houseLocationManager.findByMap(map, request.getStart(), request.getPageSize());
        response.setResult(byMap);
        byMap.getTotalCount();
        return this.success(response);
    }

    /**
     * 根据地区编码查询房源位置,地图位置快捷查询
     * @param request
     * @return
     */
    @RequestMapping(path = "/queryMapLoc",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryMapLoc(@RequestBody HouseLocationServiceRequest request) {

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();

        Map<String,Object> map = new HashMap<>();

        map.put("street",request.getEntity().getStreet());

        List<HouseLocationEntity> byMap = houseLocationManager.findMapLoc(map);
        response.setResult(byMap);
        return this.success(response);
    }
    /**
     * 创建房源位置
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody HouseLocationServiceRequest request) {

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();
        HouseLocationEntity locationEntity = request.getEntity();
        // 查询位置是否存在
        Map<String,Object> map = new HashMap<>(16);
        if (locationEntity.getCommunity()!=null){
            map.put("community",locationEntity.getCommunity());
        }else{
            map.put("street",locationEntity.getStreet());
        }
        map.put("detailAddress", StringUtils.deleteWhitespace(locationEntity.getDetailAddress()));
        List<HouseLocationEntity> hlocList = houseLocationManager.findByMap(map);
        if(null != hlocList && !hlocList.isEmpty() ){
            return this.error(response,"该位置已存在!");
        }

        HouseLocRegionEntity  leftDown = request.getLeftDown();
        HouseLocRegionEntity  rightUp  = request.getRightUp();

        //处理省start
        if(locationEntity.getCity()==null){
            throw new RemoveStackBusinessException ("市区city必传");
        }
        CompanyAreaEntity cityEntity = companyAreaManager.findAreaByCode(locationEntity.getCity());
        if(cityEntity==null){
            throw new RemoveStackBusinessException("该市未启用:"+locationEntity.getCity());
        }
        String cityParentCode = cityEntity.getParentCode();
        CompanyAreaEntity provinceEntity = companyAreaManager.findAreaByCode(cityParentCode);
        if(provinceEntity ==null){
            throw new RemoveStackBusinessException("该市所在省未启用:"+locationEntity.getCity()+locationEntity.getCityName());
        }
        locationEntity.setProvince(provinceEntity.getAreaCode());
        locationEntity.setProvinceName(provinceEntity.getName());
        //处理省end

        houseLocationManager.createLoc(leftDown,rightUp,locationEntity);

        response.setResult(locationEntity);

        return this.success(response);
    }


    /**
     * v2.8.0新版简约版创建房源位置
     * @param request
     * @return
     */
    @RequestMapping(path = "/createTwoVersion",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createTwoVersion(@RequestBody HouseLocationTwoVersionServiceRequest request) throws IllegalAccessException {

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();
        HouseLocationEntity locationEntity = request.getEntity();

        // 根据业务需求对相应字段校验--start
        List<String> list = new ArrayList();
        //list.add("provinceName");//省后面根据市反查
        list.add("city");
        list.add("cityName");
        list.add("region");
        list.add("regionName");

        list.add("street");
        list.add("streetName");
        list.add("detailAddress");
        list.add("locationAlias");

        list.add("longitude");
        list.add("latitude");
        ParamUtil<HouseLocationEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(),list);
        // 根据业务需求对相应字段校验--end  处理带VO包装类需测试


        //经纬度相同的点不新增start
        Map<String,Object> mapLat = new HashMap<String,Object>();
        mapLat.put("cityName",locationEntity.getCityName());
        mapLat.put("streetName",locationEntity.getStreetName());
        mapLat.put("locationAlias",locationEntity.getLocationAlias());
        mapLat.put("detailAddress",locationEntity.getDetailAddress());
        List<HouseLocationEntity> hlocMany = houseLocationManager.findByMap(mapLat);
        if(null != hlocMany && !hlocMany.isEmpty() && hlocMany.size() !=0){
            hlocMany.get(0).setRepairReturn(RepairReturn.NOTREPAIR);
            response.setResult(hlocMany.get(0));
            response.setIsSuccess(Boolean.valueOf(true));
            response.setMessage("点位置可用!");
            response.setCode("200");
            return new ResponseEntity(response, HttpStatus.OK);
        }
        //经纬度相同的点不新增end

        String provinceName = request.getEntity().getProvinceName();
        String cityName = request.getEntity().getCityName();
        String regionName = request.getEntity().getRegionName();
        String streetName = request.getEntity().getStreetName();
        String streetCode = request.getEntity().getStreet();
        List<CompanyAreaEntity> totalList = new ArrayList<CompanyAreaEntity>();

        if(cityName==null){throw new RemoveStackBusinessException("市cityName需要传!");}
            Map<String,Object> cityMap = new HashMap<String,Object>();
            cityMap.put("name", cityName);
            cityMap.put("level",2);
            List<CompanyAreaEntity> cityList = companyAreaManager.findByMap(cityMap,"sort",true);

            if(cityList!=null){
                if(cityList.size()==0){
                    throw new RemoveStackBusinessException("该市未激活:"+cityName);
                }
                if(cityList.size()>0){
                    //单独处理东莞市加东莞区start
                    if(cityName.equals("东莞市")){
                        locationEntity.setRegionName("东莞区");
                        regionName="东莞区";
                    }
                    //单独处理东莞市加东莞区end
                    totalList.add(cityList.get(0));
                    locationEntity.setCity(cityList.get(0).getAreaCode());
                    locationEntity.setCityName(cityList.get(0).getName());
                    CompanyAreaEntity provinceEntity = companyAreaManager.findAreaByCode(cityList.get(0).getParentCode());
                    totalList.add(provinceEntity);
                    locationEntity.setProvince(provinceEntity.getAreaCode());
                    locationEntity.setProvinceName(provinceEntity.getName());
                }
            }

        if(regionName==null||regionName.equals("")){
            locationEntity.setRegion(null);//让前端补填
            locationEntity.setRepairReturn(RepairReturn.NEEDREPAIR);
            response.setResult(locationEntity);
//            response.setIsSuccess(Boolean.FALSE);
//            response.setMessage("请选择区!区域名称为空!");
//            response.setCode("200");
//            return new ResponseEntity(response, HttpStatus.OK);
            return this.error(response,"请选择区!区域名称为空!");

            }

            //对于类似光明新区传入光明区做可变处理

            Map<String,Object> regionMap = new HashMap<String,Object>();
            regionMap.put("name", regionName);
            regionMap.put("level",3);
            List<CompanyAreaEntity> regionList = companyAreaManager.findByMap(regionMap,"sort",true);

            if(regionList!=null){
                if(regionList.size()==0){
                    throw new RemoveStackBusinessException("该区未激活:"+regionName);
                }
                if(regionList.size()>0){
                    locationEntity.setRegion(regionList.get(0).getAreaCode());
                    locationEntity.setRegionName(regionList.get(0).getName());
                    totalList.add(regionList.get(0));
                }
            }

        if(streetName==null||streetName.equals("")){

//            locationEntity.setStreet(null);
//            locationEntity.setStreetName(null);
//            locationEntity.setRepairReturn(RepairReturn.NEEDREPAIR);
//            response.setResult(locationEntity);
//            response.setIsSuccess(Boolean.valueOf(true));
//            response.setMessage("请选择镇或街道!街道名称为空!");
//            response.setCode("200");
//            return new ResponseEntity(response, HttpStatus.OK);

            return this.error(response,"请选择镇或街道!街道名称为空!");

            }
            Map<String,Object> streetMap = new HashMap<String,Object>();
            streetMap.put("name", streetName);
            streetMap.put("level",4);
            List<CompanyAreaEntity> streetList = companyAreaManager.findByMap(streetMap,"sort",true);

            if(streetList!=null){
                if(streetList.size()==0){
//                        locationEntity.setStreet(null);
//                        locationEntity.setStreetName(null);
//                        locationEntity.setRepairReturn(RepairReturn.NEEDREPAIR);
//                        response.setResult(locationEntity);
//                        response.setIsSuccess(Boolean.valueOf(true));
//                        response.setMessage("请选择镇或街道!街道编码为空!");
//                        response.setCode("200");
//                        return new ResponseEntity(response, HttpStatus.OK);

                    return this.error(response,"请选择镇或街道!街道编码为空!");

                }
                if(streetList.size()>0){
                    totalList.add(streetList.get(0));
                    streetCode = streetList.get(0).getAreaCode();
                    locationEntity.setStreet(streetList.get(0).getAreaCode());
                    locationEntity.setStreetName(streetList.get(0).getName());
                }
            }
        if(streetCode==null||streetName.equals("")){

//            locationEntity.setStreet(null);
//            locationEntity.setStreetName(null);
//            locationEntity.setRepairReturn(RepairReturn.NEEDREPAIR);
//            response.setResult(locationEntity);
//            response.setIsSuccess(Boolean.valueOf(true));
//            response.setMessage("请选择镇或街道!街道编码为空!");
//            response.setCode("200");
//            return new ResponseEntity(response, HttpStatus.OK);

            return this.error(response,"请选择镇或街道!街道编码为空!");

        }

        Map<String,Object> communityMap = new HashMap<String,Object>();
        communityMap.put("parentCode", streetCode);
        List<CompanyAreaEntity> communityList = companyAreaManager.findByMap(communityMap,"sort",true);
        if(communityList!=null){
            if(communityList.size()>0 && locationEntity.getCommunity()==null){
//                locationEntity.setRepairReturn(RepairReturn.NEEDREPAIR);
//                response.setResult(locationEntity);
//                response.setIsSuccess(Boolean.valueOf(true));
//                response.setMessage(streetName+"存在社区,请选择社区!");
//                response.setCode("200");
//                return new ResponseEntity(response, HttpStatus.OK);

                return this.error(response,streetName+"存在社区,请选择社区!");
            }

            if(communityList.size()>0 && locationEntity.getCommunity()!=null){
                locationEntity.setCommunity(locationEntity.getCommunity());
                if(locationEntity.getCommunityName()==null){
                    throw new RemoveStackBusinessException("需要传社区名CommunityName");
                }
                locationEntity.setCommunityName(locationEntity.getCommunityName());
            }
        }

        locationEntity.setCityName(cityName);
        locationEntity.setCity(cityList.get(0).getAreaCode());
        locationEntity.setRegionName(regionName);
        locationEntity.setRegion(regionList.get(0).getAreaCode());
        locationEntity.setStreetName(streetName);
        locationEntity.setStreet(streetList.get(0).getAreaCode());
        locationEntity.setHasLocRegion(false);
//        String newDetailAddr ="默认值别名和详细地址";
//        newDetailAddr = locationEntity.getDetailAddress()+"("+locationEntity.getLocationAlias()+")";
//        locationEntity.setDetailAddress(newDetailAddr);

        houseLocationManager.create(locationEntity);

        locationEntity.setRepairReturn(RepairReturn.NOTREPAIR);
        response.setResult(locationEntity);
        response.setIsSuccess(Boolean.valueOf(true));
        response.setMessage("新增位置成功!");
        response.setCode("200");
        return new ResponseEntity(response, HttpStatus.OK);
    }


    /**
     * v2.8.0 补全创建前查询实体
     * @param request
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(path = "/searchTwoVersion",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> searchTwoVersion(@RequestBody HouseLocationTwoVersionServiceRequest request) throws IllegalAccessException {

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();
        HouseLocationEntity locationEntity = request.getEntity();

        // 根据业务需求对相应字段校验--start
        List<String> list = new ArrayList();
        //list.add("provinceName");//省后面根据市反查
        list.add("cityName");
        //list.add("regionName");//区补填
        //list.add("detailAddress");
        list.add("locationAlias");

        list.add("longitude");
        list.add("latitude");
        ParamUtil<HouseLocationEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(),list);
        // 根据业务需求对相应字段校验--end  处理带VO包装类需测试
        if(null==locationEntity.getDetailAddress()){
            locationEntity.setDetailAddress(locationEntity.getCityName()+locationEntity.getLocationAlias());
        }

        // 查询位置是否存在

        //经纬度相同的点不新增start
        Map<String,Object> mapLat = new HashMap<String,Object>();
        mapLat.put("cityName",locationEntity.getCityName());
        mapLat.put("locationAlias",locationEntity.getLocationAlias());
        List<HouseLocationEntity> hlocMany = houseLocationManager.findByMap(mapLat);
        if(null != hlocMany && !hlocMany.isEmpty() && hlocMany.size() !=0){
            hlocMany.get(0).setRepairReturn(RepairReturn.NOTREPAIR);
            response.setResult(hlocMany.get(0));
            response.setIsSuccess(Boolean.valueOf(true));
            response.setMessage("点位置可用!");
            response.setCode("200");
            return new ResponseEntity(response, HttpStatus.OK);
        }
        //经纬度相同的点不新增end



        String provinceName = request.getEntity().getProvinceName();
        String cityName = request.getEntity().getCityName();
        String regionName = request.getEntity().getRegionName();
        String streetName = request.getEntity().getStreetName();
        String streetCode = request.getEntity().getStreet();
        List<CompanyAreaEntity> totalList = new ArrayList<CompanyAreaEntity>();

        if(cityName==null){throw new RemoveStackBusinessException("市cityName需要传!");}
        Map<String,Object> cityMap = new HashMap<String,Object>();
        cityMap.put("name", cityName);
        cityMap.put("level",2);
        List<CompanyAreaEntity> cityList = companyAreaManager.findByMap(cityMap,"sort",true);

        if(cityList!=null){
            if(cityList.size()==0){
                throw new RemoveStackBusinessException("该市未激活:"+cityName);
            }
            if(cityList.size()>0){
                //单独处理东莞市加东莞区start
                if(cityName.equals("东莞市")){
                    locationEntity.setRegionName("东莞区");
                    regionName="东莞区";
                }
                //单独处理东莞市加东莞区end
                totalList.add(cityList.get(0));
                locationEntity.setCity(cityList.get(0).getAreaCode());
                locationEntity.setCityName(cityList.get(0).getName());
                CompanyAreaEntity provinceEntity = companyAreaManager.findAreaByCode(cityList.get(0).getParentCode());
                totalList.add(provinceEntity);
                locationEntity.setProvince(provinceEntity.getAreaCode());
                locationEntity.setProvinceName(provinceEntity.getName());
            }
        }

        locationEntity.setCityName(cityName);
        locationEntity.setCity(cityList.get(0).getAreaCode());
        String newDetailAddr ="默认值别名和详细地址";
        newDetailAddr = locationEntity.getDetailAddress()+"("+locationEntity.getLocationAlias()+")";
        locationEntity.setDetailAddress(newDetailAddr);

        if(regionName==null||regionName.equals("")){
            locationEntity.setRegion(null);//让前端补填
            locationEntity.setRepairReturn(RepairReturn.NEEDREPAIR);
            response.setResult(locationEntity);
            response.setIsSuccess(Boolean.valueOf(true));
            response.setMessage("请选择区!区域名称为空!");
            response.setCode("200");
            return new ResponseEntity(response, HttpStatus.OK);
        }

        //对于类似光明新区传入光明区做可变处理

        Map<String,Object> regionMap = new HashMap<String,Object>();
        regionMap.put("name", regionName);
        regionMap.put("level",3);
        List<CompanyAreaEntity> regionList = companyAreaManager.findByMap(regionMap,"sort",true);

        if(regionList!=null){
            if(regionList.size()==0){
                throw new RemoveStackBusinessException("该区未激活:"+regionName);
            }
            if(regionList.size()>0){
                locationEntity.setRegion(regionList.get(0).getAreaCode());
                locationEntity.setRegionName(regionList.get(0).getName());
                totalList.add(regionList.get(0));
            }
        }

        if(streetName==null||streetName.equals("")){

            locationEntity.setStreet(null);
            locationEntity.setStreetName(null);
            locationEntity.setRepairReturn(RepairReturn.NEEDREPAIR);
            response.setResult(locationEntity);
            response.setIsSuccess(Boolean.valueOf(true));
            response.setMessage("请选择镇或街道!街道名称为空!");
            response.setCode("200");
            return new ResponseEntity(response, HttpStatus.OK);

        }
        Map<String,Object> streetMap = new HashMap<String,Object>();
        streetMap.put("parentCode",regionList.get(0).getAreaCode());
        streetMap.put("name", streetName);
        streetMap.put("level",4);
        List<CompanyAreaEntity> streetList = companyAreaManager.findByMap(streetMap,"sort",true);

        if(streetList!=null){
            if(streetList.size()==0){
                locationEntity.setStreet(null);
                locationEntity.setStreetName(null);
                locationEntity.setRepairReturn(RepairReturn.NEEDREPAIR);
                response.setResult(locationEntity);
                response.setIsSuccess(Boolean.valueOf(true));
                response.setMessage("请选择镇或街道!街道编码为空!");
                response.setCode("200");
                return new ResponseEntity(response, HttpStatus.OK);

            }
            if(streetList.size()>0){
                totalList.add(streetList.get(0));
                streetCode = streetList.get(0).getAreaCode();
                locationEntity.setStreet(streetList.get(0).getAreaCode());
                locationEntity.setStreetName(streetList.get(0).getName());
            }
        }
        if(streetCode==null||streetName.equals("")){

            locationEntity.setStreet(null);
            locationEntity.setStreetName(null);
            locationEntity.setRepairReturn(RepairReturn.NEEDREPAIR);
            response.setResult(locationEntity);
            response.setIsSuccess(Boolean.valueOf(true));
            response.setMessage("请选择镇或街道!未查询到街道,街道编码为空!");
            response.setCode("200");
            return new ResponseEntity(response, HttpStatus.OK);

        }

        Map<String,Object> communityMap = new HashMap<String,Object>();
        communityMap.put("parentCode", streetCode);
        List<CompanyAreaEntity> communityList = companyAreaManager.findByMap(communityMap,"sort",true);
        if(communityList!=null){
            if(communityList.size()>0 && locationEntity.getCommunity()==null){
                locationEntity.setRepairReturn(RepairReturn.NEEDREPAIR);
                response.setResult(locationEntity);
                response.setIsSuccess(Boolean.valueOf(true));
                response.setMessage(streetName+"存在社区,请选择社区!");
                response.setCode("200");
                return new ResponseEntity(response, HttpStatus.OK);
            }

            if(communityList.size()>0 && locationEntity.getCommunity()!=null){
                locationEntity.setCommunity(locationEntity.getCommunity());
                if(locationEntity.getCommunityName()==null){
                    throw new RemoveStackBusinessException("需要传社区名CommunityName");
                }
                locationEntity.setCommunityName(locationEntity.getCommunityName());
            }
        }


        locationEntity.setRegionName(regionName);
        locationEntity.setRegion(regionList.get(0).getAreaCode());
        locationEntity.setStreetName(streetName);
        locationEntity.setStreet(streetList.get(0).getAreaCode());
        locationEntity.setHasLocRegion(false);


        //查询已经不用新增houseLocationManager.create(locationEntity);

        //查询返回保证locationCode为空
        locationEntity.setLocationCode(null);
        locationEntity.setRepairReturn(RepairReturn.NOTREPAIR);
        response.setResult(locationEntity);
        response.setIsSuccess(Boolean.valueOf(true));
        response.setMessage("信息完整,可去新增位置!");
        response.setCode("200");
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     * 房源位置详情
     * @param request
     * @return
     */
    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody HouseLocationServiceRequest request){

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("locationCode",request.getEntity().getLocationCode());
        HouseLocationEntity locationEntity = houseLocationManager.findOne(param);
        response.setResult(locationEntity);
        return this.success(response);
    }

    /**
     * 修改房源位置
     * @param request
     * @return
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody HouseLocationServiceRequest request) throws IllegalAccessException {

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();

        if(null==request.getEntity().getLocationCode()){
            throw new RemoveStackBusinessException("位置编码,不能为空(locationCode)");
        }

        // 根据业务需求对相应字段校验--start
        List<String> list = new ArrayList();
        //list.add("provinceName");//省后面根据市反查
        list.add("city");
        list.add("cityName");
        list.add("region");
        list.add("regionName");

        list.add("street");
        list.add("streetName");
        list.add("detailAddress");
        list.add("locationAlias");

        list.add("longitude");
        list.add("latitude");
        ParamUtil<HouseLocationEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(),list);
        // 根据业务需求对相应字段校验--end  处理带VO包装类需测试

        houseLocationManager.update(request.getEntity());

        response.setResult(request.getEntity());
        return this.success(response);
    }

    /**
     * 创建房源多边形区域
     * @param request
     * @return
     */
    @RequestMapping(path = "/createLocRegion",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createLocRegion(@RequestBody HouseLocationServiceRequest request) throws IllegalAccessException {

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();
        HouseLocationEntity locationEntity = request.getEntity();

        // 根据业务需求对相应字段校验--start
        HouseLocationEntity hLeOne = new HouseLocationEntity();
        hLeOne.setLocationCode(request.getEntity().getLocationCode());
        hLeOne.setLocationAlias(request.getEntity().getLocationAlias());

        List<String> list = new ArrayList();
        list.add("locationCode");
        list.add("locationAlias");
        ParamUtil<HouseLocationEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(hLeOne,list);
        // 根据业务需求对相应字段校验--end
        houseLocationManager.createLocRegion(locationEntity);

        return this.success(response);
    }

    /**
     * 查询可视区域位置
     * @param request
     * @return
     */
    @RequestMapping(path = "/querySeeArea",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> querySeeArea(@RequestBody HouseLocationServiceRequest request) {

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();

        Map<String,Object> map = new HashMap<>();
        HouseLocRegionEntity  leftDown = request.getLeftDown();
        HouseLocRegionEntity  rightUp  = request.getRightUp();
        String locationCode = request.getEntity().getLocationCode();
        List<HouseLocPolygonEntity> byMap = houseLocationManager.findSeeArea(leftDown,rightUp,locationCode);
        response.setResult(byMap);
        return this.success(response);
    }

    /**
     * 可视地图范围内位置区域重复判断,对外接口
     * @param request
     * @return
     */
    @RequestMapping(path = "/repeatRegion",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> repeatRegion(@RequestBody HouseLocationServiceRequest request) {

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();
        HouseLocationEntity locationEntity = request.getEntity();

        Map<String,Object> map = new HashMap<>();
        HouseLocRegionEntity  leftDown = request.getLeftDown();
        HouseLocRegionEntity  rightUp  = request.getRightUp();
        String locationCode = request.getEntity().getLocationCode();
        boolean isRepeat = houseLocationManager.findRepeatRegion(leftDown,rightUp,locationEntity);
        if(isRepeat){
            response.setResult(isRepeat);
            response.setStackTrace("重复了,与其他多边形重复");
        }else{
            response.setResult(isRepeat);
            response.setStackTrace("无重复");
        }
        ResponseEntity<IServiceResponse> rt = this.success(response);

        return this.success(response);
    }

    /**
     * 修改点和区域
     * @param request
     * @return
     */
    @RequestMapping(path = "/editPointAndRegion",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> editPointAndRegion(@RequestBody HouseLocationServiceRequest request) {

        HouseLocationServiceResponse response = new HouseLocationServiceResponse();
        HouseLocationEntity locationEntity = request.getEntity();

        HouseLocRegionEntity  leftDown = request.getLeftDown();
        HouseLocRegionEntity  rightUp  = request.getRightUp();


        houseLocationManager.editPointAndRegion(leftDown,rightUp,locationEntity);

        return this.success(response);
    }


}

package com.kfwy.hkp.cooperate.business.impl;

import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.cooperate.business.IMatchingManager;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.entity.CustomerAreaEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerHouseType;
import com.kfwy.hkp.crm.customer.enums.CustomerStatus;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hos.house.enums.HousePurposeType;
import com.kfwy.hkp.hos.house.enums.HouseStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchingManagerImpl implements IMatchingManager {
    @Autowired
    private IHouseManager houseManager;
    @Autowired
    private ICustomerManager customerManager;

    @Override
    public PageResult<CustomerEntity> matchCus(String houseCode, int start, int pageSize, String orderBy, boolean isAse) {
        PageResult<CustomerEntity> customerResultFromStreet = null;
        PageResult<CustomerEntity> customerResultFromRegion = null;
        PageResult<CustomerEntity> customerResultFromCity = null;

        //需要返回的客户集合
        List<CustomerEntity> cusList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        if(StringUtils.isEmpty(houseCode)){
            throw new RemoveStackBusinessException ("合作的房源去匹配客户,房源code必传,不能为空,"+houseCode);
        }

        map.put("houseCode",houseCode);
        HouseEntity houseEntity = houseManager.findOne(map);
        if(houseEntity == null){
            throw new RemoveStackBusinessException("合作的房源去匹配客户,无此房源,"+houseCode);
        }

        //客户状态为跟进中
        map.put("cusStatus", CustomerStatus.FOLLOWING);
        // 百分之二十面积浮动查询
        areaMatch(map, houseEntity);
        //房源类型
        houseTypesMatch(map, houseEntity);
        // 街道
        if (StringUtils.isNotEmpty(houseEntity.getStreet())) {
            map.put("street", houseEntity.getStreet());
            customerResultFromStreet = customerManager
                    .selectHouseMatchingCus(map, start, pageSize,orderBy,isAse);
            if(customerResultFromStreet != null && customerResultFromStreet.getData() != null && customerResultFromStreet.getData().size()!=0){
                cusList.addAll(customerResultFromStreet.getData());
            }
        }

        if(cusList!=null && (cusList.size()>=pageSize)){
            customerResultFromStreet.setData(cusList);
            return customerResultFromStreet;
        }

        // 区域
        if (StringUtils.isNotEmpty(houseEntity.getRegion())) {
            map.put("street", StringUtils.isEmpty(houseEntity.getStreet())?"'0'":houseEntity.getStreet());
            map.put("region", houseEntity.getRegion());
            int regionData = pageSize - cusList.size();

            customerResultFromRegion = customerManager.selectHouseMatchingCus(map, start, regionData,orderBy,isAse);
            //智能匹配区域就占用一页了 没必要查询城市了
            if(customerResultFromRegion.getData()!=null && (customerResultFromRegion.getData().size() >= regionData)){
                cusList.addAll(customerResultFromRegion.getData());
                customerResultFromRegion.setData(cusList);
                return customerResultFromRegion;
            }
            if(customerResultFromRegion != null && customerResultFromRegion.getData() != null && customerResultFromRegion.getData().size()!=0){
                cusList.addAll(customerResultFromRegion.getData());
            }
        }
        // 城市
        if (StringUtils.isNotEmpty(houseEntity.getCity())) {
            map.put("street", StringUtils.isEmpty(houseEntity.getStreet())?"'0'":houseEntity.getStreet());
            map.put("region", StringUtils.isEmpty(houseEntity.getRegion())?"'0'":houseEntity.getRegion());
            map.put("city", houseEntity.getCity());
            int cityData = pageSize - cusList.size();
            customerResultFromCity = customerManager.selectHouseMatchingCus(map, start, cityData,orderBy,isAse);
            if(customerResultFromCity != null && customerResultFromCity.getData() != null && customerResultFromCity.getData().size()!=0) {
                cusList.addAll(customerResultFromCity.getData());
                customerResultFromCity.setData(cusList);
            }
        }
        return customerResultFromCity;
    }

    @Override
    public PageResult<HouseEntity> matchHos(String cusCode, int start, int pageSize, String orderBy, boolean isAse) {

        Map<String, Object> map = new HashMap<>();
        map.put("cusCode", cusCode);
        if(cusCode==null || cusCode.equals("")){
            throw new RemoveStackBusinessException("客户去匹配房源,客户必传,不能为空,"+cusCode);
        }
        CustomerEntity ce = customerManager.findOne(map);
        if(ce == null){
            throw new RemoveStackBusinessException("客户去匹配房源,无此客户,"+cusCode);
        }
        PageResult<HouseEntity> hosResultFromStreet = null;
        PageResult<HouseEntity> hosResultFromRegion = null;
        PageResult<HouseEntity> hosResultFromCity = null;

        //需要返回的房源集合
        List<HouseEntity> hosList = new ArrayList<>();
        //根据需求面积, 需求区域
        Integer na = ce.getNeedAcreage().intValue();
        if (na != null) {
            List<CustomerAreaEntity> area = ce.getCustomerAreaEntityList();
            String city = "";
            String region = "";
            String street = "";
            for (CustomerAreaEntity cusArea : area){
                if (StringUtils.isNotEmpty(cusArea.getCity())) {
                    city += "'" + cusArea.getCity() + "',";
                }
                if (StringUtils.isNotEmpty(cusArea.getRegion())) {
                    region += "'" + cusArea.getRegion() + "',";
                }
                if (StringUtils.isNotEmpty(cusArea.getStreet())) {
                    street += "'" + cusArea.getStreet() + "',";
                }
            }
            map.clear();
            //匹配的条件
            matchCondition(map,ce);
            // 街道
            if (StringUtils.isNotEmpty(street)) {
                street = street.substring(0, street.length() - 1);
                map.put("cusStreet", street);
                hosResultFromStreet = houseManager
                        .selectCusMatchingHos(map, start, pageSize,orderBy,isAse);
                if(hosResultFromStreet !=null && hosResultFromStreet.getData() != null && hosResultFromStreet.getData().size()!=0){
                    hosList.addAll(hosResultFromStreet.getData());
                }
            }

            //智能匹配街道就占用一页了 没必要查询区域了
            if(hosResultFromStreet !=null && hosResultFromStreet.getData()!=null && (hosResultFromStreet.getData().size()>=pageSize)){
                hosResultFromStreet.setData(hosList);
                return hosResultFromStreet;
            }

            // 区域
            if (StringUtils.isNotEmpty(region)) {
                street = StringUtils.isEmpty(street) ?"'0'":street;
                region = region.substring(0, region.length() - 1);
                map.put("cusStreet", street);
                map.put("cusRegion", region);
                int regionData = pageSize - hosList.size();

                hosResultFromRegion = houseManager.selectCusMatchingHos(map, start, regionData,orderBy,isAse);
                //智能匹配区域就占用一页了 没必要查询城市了
                if(hosResultFromRegion !=null && hosResultFromRegion.getData()!=null && (hosResultFromRegion.getData().size() >= regionData)){
                    hosList.addAll(hosResultFromRegion.getData());
                    hosResultFromRegion.setData(hosList);
                    return hosResultFromRegion;
                }
                if(hosResultFromRegion.getData() != null && hosResultFromRegion.getData().size()!=0){
                    hosList.addAll(hosResultFromRegion.getData());
                }
            }
            // 城市
            if (StringUtils.isNotEmpty(city)) {
                street = StringUtils.isEmpty(street) ?"'0'":street;
                region = StringUtils.isEmpty(region) ?"'0'":region;
                city = city.substring(0, city.length() - 1);
                map.put("cusStreet", street);
                map.put("cusRegion", region);
                map.put("cusCity", city);
                int cityData = pageSize - hosList.size();
                hosResultFromCity = houseManager.selectCusMatchingHos(map, start, cityData,orderBy,isAse);
                if(hosResultFromCity != null && hosResultFromCity.getData() != null && hosResultFromCity.getData().size()!=0) {
                    hosList.addAll(hosResultFromCity.getData());
                }
            }
            return hosResultFromCity;
        }
        return null;
    }


    private void houseTypesMatch(Map<String, Object> map, HouseEntity houseEntity) {
        if(StringUtils.isNotEmpty(houseEntity.getHousePurpose())){
            map.put("isTypesString", true);
            if(houseEntity.getHousePurpose().equals(HousePurposeType.STORAGE)){
                map.put("houseTypes","'1','3'");
            }else if(houseEntity.getHousePurpose().equals(HousePurposeType.PRODUCT)) {
                map.put("houseTypes","'2','3'");
            }
        }
    }

    private void areaMatch(Map<String, Object> map, HouseEntity houseEntity) {
        int area = houseEntity.getArea().intValue();
        if(0 != area){
            Double num = area*0.2;
            double needAcreageStart = area - num;
            double needAcreageEnd = area + num;
            map.put("needAcreageEnd",needAcreageEnd);
            //房源不可分割时，下浮20%
            //房源可分割时：匹配结果包含需求面积小于可租用面积以下的所有客户
            if(!houseEntity.getHasCut()){
                map.put("needAcreageStart",needAcreageStart);
            }
        }
    }

    private void matchCondition(Map<String, Object> map,CustomerEntity cu){
        //匹配面积 上浮不封顶，但过滤不可分割的房源
        //下浮 20%，解释：如客户需求面积为1000㎡，则匹配房源面积为最小800㎡
        Integer na = cu.getNeedAcreage().intValue();
        double num = na * 0.2;
        Integer acreageStart = na - (int)num;
        //Integer acreageEnd = na + (int)num;
        map.put("minArea", acreageStart);
        //map.put("maxArea", acreageEnd);
        map.put("area",na);//客户需求面积
        map.put("hasCut",Boolean.TRUE);//上浮过滤不可分割 只要可分割的
        //匹配仓储还是生产
        map.put("isPurposeString", true);
        if (CustomerHouseType.STORAGE.equals(cu.getHouseType())) {
            map.put("housePurposes", "'1','3'");
        } else if (CustomerHouseType.PRODUCT.equals(cu.getHouseType())) {
            map.put("housePurposes", "'2','3'");
        }
        //不是匹配热租中的房源 都是耍流氓
        map.put("houseStatus", HouseStatus.hotRenting);
    }
}

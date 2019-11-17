package com.kfwy.hkp.controller.hos.house;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.controller.hos.house.vo.EntrustServiceRequest;
import com.kfwy.hkp.controller.hos.house.vo.EntrystServiceResponse;
import com.kfwy.hkp.hos.house.api.BespeakApi.BespeakApiClient;
import com.kfwy.hkp.hos.house.api.EntrustApi.EntrustApiClient;
import com.kfwy.hkp.hos.house.entity.EntrustEntity;
import com.kfwy.hkp.hos.house.enums.EntrustType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/10.
 */
@RestController
@RequestMapping(path = "/entrust")
public class EntrustService extends SpringRestService {


    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody EntrustServiceRequest request) {

        EntrystServiceResponse response = new EntrystServiceResponse();
        Map<String, Object> param = new HashMap<>();

        EntrustEntity bespeakEntity = request.getEntity();
        PageResult<EntrustEntity> list=null;
        if(bespeakEntity!=null){
            if (StringUtils.isNotEmpty(bespeakEntity.getStartTime())) {
                try {
                    param.put("startTime", DateFormatUtil.getCurrentDate(bespeakEntity.getStartTime(), DateFormatUtil.DateTimeFormatString));
                } catch (Exception e) {
                }
            }

            if (StringUtils.isNotEmpty(bespeakEntity.getEndTime())) {
                try {
                    param.put("endTime", DateFormatUtil.getCurrentDate(bespeakEntity.getStartTime(), DateFormatUtil.DateTimeFormatString));
                } catch (Exception e) {
                }
            }
            if (bespeakEntity.getEntrustType()!=null) {
                param.put("entrustType", Integer.valueOf(bespeakEntity.getEntrustType()));
            }
            if (bespeakEntity.getHouseType()!=null) {
                param.put("houseType", Integer.valueOf(bespeakEntity.getHouseType()));
            }
            if (bespeakEntity.getStatus()!=null) {
                param.put("status", Integer.valueOf(bespeakEntity.getStatus()));
            }
            if(StringUtils.isNotEmpty(bespeakEntity.getDevice())){
                param.put("device", bespeakEntity.getDevice());
            }
            if(StringUtils.isNotEmpty(bespeakEntity.getProvince())){
                param.put("province",bespeakEntity.getProvince());
            }
            if(StringUtils.isNotEmpty(bespeakEntity.getCity())){
                param.put("city",bespeakEntity.getCity());
            }
            if(StringUtils.isNotEmpty(bespeakEntity.getRegion())){
                param.put("region",bespeakEntity.getRegion());
            }
            if(StringUtils.isNotEmpty(bespeakEntity.getStreet())){
                param.put("street",bespeakEntity.getStreet());
            }
            if(StringUtils.isNotEmpty(bespeakEntity.getLinkmanPhone())){
                param.put("linkmanPhone",bespeakEntity.getLinkmanPhone());
            }
            if (StringUtils.isNotEmpty(bespeakEntity.getEmpCode())) {
                param.put("empCode", bespeakEntity.getEmpCode());
            }
            if (StringUtils.isNotEmpty(bespeakEntity.getEmpName())) {
                param.put("empName", bespeakEntity.getEmpName());
            }
        }

        //通过部门编码获取信息
        List<String> deptCodes= BespeakApiClient.findSelectBespeakPower(CurrentContext.getUserCode(),CurrentContext.getUserInfo().getOwnerDeptCode());
        if(StringUtils.isNotEmpty(CurrentContext.getUserInfo().getOwnerDeptCode())){
            param.put("deptCodes",deptCodes);
            list = EntrustApiClient.getData(param, request.getStart(),request.getPageSize());
        }

        response.setResult(list);
        response.setTotal((null != list && CollectionUtils.isNotEmpty(list.getData()) ? list.getData().size() : 0));
        return this.success(response);
    }



    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody EntrustServiceRequest request) {

        EntrystServiceResponse response = new EntrystServiceResponse();
        EntrustApiClient.update(request.getEntity());
        return this.success(response);
    }

}

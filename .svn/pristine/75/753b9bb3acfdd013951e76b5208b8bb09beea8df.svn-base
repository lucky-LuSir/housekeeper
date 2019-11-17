package com.kfwy.hkp.controller.hos.house;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.controller.hos.house.vo.RecommendServiceRequest;
import com.kfwy.hkp.controller.hos.house.vo.RecommendServiceResponse;
import com.kfwy.hkp.hos.house.api.BespeakApi.BespeakApiClient;
import com.kfwy.hkp.hos.house.api.RecommendApi.RecommendApiClient;
import com.kfwy.hkp.hos.house.entity.RecommendEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:ChenJie Created by kfwy_it_009 on 2018/12/10.
 */
@RestController
@RequestMapping(path = "/recommend")
public class RecommendService extends SpringRestService {


    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody RecommendServiceRequest request) {

        RecommendServiceResponse response = new RecommendServiceResponse();

        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();

        if (cur.getUserLevel().equals(UserLevel.K2)){
            throw new BusinessException("101","跳转到付费引导样式A");
        }

        Map<String, Object> param = new HashMap<>();

        RecommendEntity recommendEntity = request.getEntity();
        PageResult<RecommendEntity> list =null;
        if(recommendEntity!=null){
                if (StringUtils.isNotEmpty(recommendEntity.getStartTime())) {
                    try {
                        param.put("createTimeStart", DateFormatUtil.getCurrentDate(recommendEntity.getStartTime(), DateFormatUtil.DateTimeFormatDay));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (StringUtils.isNotEmpty(recommendEntity.getEndTime())) {
                    try {
                        param.put("createTimeEnd", DateFormatUtil.getCurrentDate(recommendEntity.getEndTime(), DateFormatUtil.DateTimeFormatDay));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (recommendEntity.getRecommendType() != null) {
                    param.put("recommendType", Integer.valueOf(recommendEntity.getRecommendType()));
                }
                if (recommendEntity.getStatus() != null) {
                    param.put("status", Integer.valueOf(recommendEntity.getStatus()));
                }
                if (StringUtils.isNotEmpty(recommendEntity.getEmpCode())) {
                    param.put("empCode", recommendEntity.getEmpCode());
                }
                if (StringUtils.isNotEmpty(recommendEntity.getEmpName())) {
                    param.put("empName", recommendEntity.getEmpName());
                }
                if (StringUtils.isNotEmpty(recommendEntity.getDeptCode())) {
                    param.put("deptCode", recommendEntity.getDeptCode());
                }
                if (StringUtils.isNotEmpty(recommendEntity.getDeptName())) {
                    param.put("deptName", recommendEntity.getDeptName());
                }
            }
        //通过部门编码获取信息

        List<String> deptCodes= BespeakApiClient.findSelectBespeakPower(CurrentContext.getUserCode(),CurrentContext.getUserInfo().getOwnerDeptCode());
        if(StringUtils.isNotEmpty(CurrentContext.getUserInfo().getOwnerDeptCode())){
            param.put("deptCodes",deptCodes);
            list = RecommendApiClient.getData(param, request.getStart(),request.getPageSize());
        }
        response.setResult(list);
        response.setTotal((null != list && CollectionUtils.isNotEmpty(list.getData()) ? list.getData().size() : 0));
        return this.success(response);
    }



    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody RecommendServiceRequest request) {

        RecommendServiceResponse response = new RecommendServiceResponse();
        RecommendApiClient.update(request.getEntity());
        return this.success(response);
    }

}

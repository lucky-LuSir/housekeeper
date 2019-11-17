package com.kfwy.hkp.controller.parttime;

import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.parttime.vo.ParttimeServiceRequest;
import com.kfwy.hkp.controller.parttime.vo.ParttimeServiceResponse;
import com.kfwy.hkp.controller.parttime.vo.ParttimeVo;
import com.kfwy.hkp.sys.parttime.business.IParttimeManager;
import com.kfwy.hkp.sys.parttime.entity.ParttimeEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
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
 * Create By hjh on 2018/11/7
 */

@RestController
@RequestMapping(path = "/parttime")
public class ParttimeService extends SpringRestService {

    @Autowired
    private IParttimeManager parttimeManager;

    /**
     * 新增
     * @param request
     * @return
     */
    @RequestMapping(path = "/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ParttimeServiceRequest request) {
        ParttimeServiceResponse response = new ParttimeServiceResponse();

        // 查询是否存在相同手机号兼职
        ParttimeEntity pt = parttimeManager.findOne("ptPhone", request.getEntity().getPtPhone());
        if(null != pt){
            return this.error(response,"该手机号已存在!");
        }
        request.getEntity().setOwnerCode(null);
        parttimeManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * 详情
     * @param request
     * @return
     */
    @RequestMapping(path = "/detail", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody ParttimeServiceRequest request) {
        ParttimeServiceResponse response = new ParttimeServiceResponse();

        ParttimeEntity parttimeEntity = parttimeManager.findOne("ptCode", request.getEntity().getPtCode());
        response.setResult(parttimeEntity);
        return this.success(response);
    }

    /**
     * 修改
     * @param request
     * @return
     */
    @RequestMapping(path = "/edit", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> edit(@RequestBody ParttimeServiceRequest request) {
        ParttimeServiceResponse response = new ParttimeServiceResponse();


        parttimeManager.update(request.getEntity());
        return this.success(response);
    }

    /**
     * 查询列表
     * @param request
     * @return
     */
    @RequestMapping(path = "/query", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ParttimeServiceRequest request) {
        ParttimeServiceResponse response = new ParttimeServiceResponse();
        UserEntity cur = (UserEntity) CurrentContext.getUserInfo();

        if (cur.getUserLevel().equals(UserLevel.K2)){
            throw new BusinessException("101","跳转到付费引导样式A");
        }

        Map<String, Object> map = new HashMap<>();

        ParttimeVo queryVo = request.getEntity();

        if(queryVo != null){
            if (StringUtils.isNotEmpty(queryVo.getKeyword())){
                map.put("keyword",queryVo.getKeyword());
            }
            if(StringUtils.isNotEmpty(queryVo.getPtName())){
                map.put("ptName",queryVo.getPtName());
            }

            if(StringUtils.isNotEmpty(queryVo.getPtPhone())){
                map.put("ptPhone",queryVo.getPtPhone());
            }

            if(StringUtils.isNotEmpty(queryVo.getCompany())){
                map.put("company",queryVo.getCompany());
            }

            if(StringUtils.isNotEmpty(queryVo.getOwnerPhone())){
                map.put("ownerPhone",queryVo.getOwnerPhone());
            }

            if(StringUtils.isNotEmpty(queryVo.getOwnerName())){
                map.put("ownerName",queryVo.getOwnerName());
            }

            if(StringUtils.isNotEmpty(queryVo.getOwnerDeptName())){
                map.put("ownerDeptName",queryVo.getOwnerDeptName());
            }

            //创建时间
            if (null != queryVo.getCreateTimeStart()) {
                try {
                    map.put("createTimeStart", DateUtil.beginOfDay(queryVo.getCreateTimeStart()));
                } catch (Exception e) {
                }
            }

            if (null != queryVo.getCreateTimeEnd()) {
                try {
                    map.put("createTimeEnd", DateUtil.endOfDay(queryVo.getCreateTimeEnd()));
                } catch (Exception e) {
                }
            }

        }

        PageResult<ParttimeEntity> byMap = parttimeManager.findByMap(map, request.getStart(), request.getPageSize(),"create_time",false);
        response.setResult(byMap);
        return this.success(response);
    }
}

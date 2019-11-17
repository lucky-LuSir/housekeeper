package com.kfwy.hkp.controller.sys.area;

import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.sys.area.vo.BaseAreaServiceRequest;
import com.kfwy.hkp.controller.sys.area.vo.BaseAreaServiceResponse;
import com.kfwy.hkp.sys.area.business.IBaseAreaManager;
import com.kfwy.hkp.sys.area.business.ICompanyAreaManager;
import com.kfwy.hkp.sys.area.dictionary.AreaStatus;
import com.kfwy.hkp.sys.area.entity.BaseAreaEntity;
import com.kfwy.hkp.sys.area.entity.CompanyAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.*;

/**
 * @author baiye
 * @Description 描述:省市区街道
 * @Auth xpp
 * @Date 2018/6/14 10:52
 * @Version 1.0T
 * @Param
 * @return
 **/
@Controller
@RequestMapping("/baseArea")
public class BaseAreaService extends SpringRestService {

    @Autowired
    private IBaseAreaManager baseAreaManager;

    @Autowired
    private ICompanyAreaManager companyAreaManager;


    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 描述:查询接口 queryNoPage
     * @Auth xpp
     * @Date 2018/8/13 16:10
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(value = "/queryNoPage", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody BaseAreaServiceRequest request) {

        BaseAreaServiceResponse response = new BaseAreaServiceResponse();

        //查询省市区，考虑到没有分页的情况，不能查询很多数据
        if (request.getEntity() == null
                || (StringUtils.isEmpty(request.getEntity().getAreaCode())
                && StringUtils.isEmpty(request.getEntity().getParentCode())
                && request.getEntity().getLevel() == null)
                || (request.getEntity().getLevel() != null
                && request.getEntity().getLevel() > 2
                && StringUtils.isEmpty(request.getEntity().getAreaCode())
                && StringUtils.isEmpty(request.getEntity().getParentCode()))
                ) {

            throw new BusinessException("此接口最多只支持查询不分页的不指定父级编码的区以上的数据");

        }

        Map<String, Object> param = new HashMap<String, Object>();

        if (!StringUtils.isEmpty(request.getEntity().getAreaCode())) {
            param.put("areaCode", request.getEntity().getAreaCode());

            response.setResult(baseAreaManager.findAreaByCode(request.getEntity().getAreaCode()));
            return this.success(response);

        }
        if (!StringUtils.isEmpty(request.getEntity().getParentCode())) {
            param.put("parentCode", request.getEntity().getParentCode());
        }
        if (!StringUtils.isEmpty(request.getEntity().getLevel())) {
            param.put("level", request.getEntity().getLevel());
        }
        param.put("status", AreaStatus.ENABLE);


        List<BaseAreaEntity> list = baseAreaManager.findByMap(param, "sort", true);


        response.setResult(list);

        return this.success(response);
    }

    /**
     * 激活指定省市区
     * xpp
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/enableArea", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> enableArea(@RequestBody BaseAreaServiceRequest request) {

        BaseAreaServiceResponse response = new BaseAreaServiceResponse();
        int enableFlag = request.getEnableFlag();
        BaseAreaEntity provinceObj = request.getProvinceObj();
        BaseAreaEntity cityObj = request.getCityObj();
        BaseAreaEntity regionObj = request.getRegionObj();
        List<BaseAreaEntity> readyInsertList = new ArrayList<BaseAreaEntity>();
        companyAreaManager.enableArea(enableFlag, provinceObj, cityObj, regionObj, readyInsertList);
        return this.success(response);
    }
}

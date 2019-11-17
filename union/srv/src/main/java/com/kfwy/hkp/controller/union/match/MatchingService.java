package com.kfwy.hkp.controller.union.match;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.union.match.vo.MatchingServiceRequest;
import com.kfwy.hkp.controller.union.match.vo.MatchingServiceResponse;
import com.kfwy.hkp.cooperate.business.IMatchingManager;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/11/7 10:47
 */
@RestController
//@RequestMapping(path = "/match")
public class MatchingService extends SpringRestService {

    @Autowired
    private IMatchingManager matchingManager;

    /**
     * 房源匹配客户信息
     * @param request
     * @return
     */
    @RequestMapping(path = "/houseApply/matchCus",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> matchCus(@RequestBody MatchingServiceRequest request) {

        MatchingServiceResponse response = new MatchingServiceResponse();
        PageResult<CustomerEntity> result = matchingManager.matchCus(request.getHouseCode(),request.getStart(),
                request.getPageSize(),"need_acreage",false);
        if (result.getData()==null || result.getData().size()==0){
            throw new BusinessException("房源匹配客户：根据房源的所在区域，根据面积、" +
                    "价格的浮动范围，没有匹配到合适的结果！\n" +
                    "您可以在客户管理中使用自定义条件进行筛选！");
        }
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 客户匹配房源信息
     * @param request
     * @return
     */
    @RequestMapping(path = "/cusApply/matchHos",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> matchHos(@RequestBody MatchingServiceRequest request) {

        MatchingServiceResponse response = new MatchingServiceResponse();

        PageResult<HouseEntity> result = matchingManager.matchHos(request.getCusCode(),request.getStart(),
                request.getPageSize(),"area",true);
        if (result.getData()==null || result.getData().size()==0){
            throw new BusinessException("客户匹配房源：根据客户的需求区域，" +
                                        "根据面积、价格的浮动范围，没有匹配到合适的结果！\n" +
                                        "您可以在房源管理中使用自定义条件进行筛选！！");
        }
        response.setResult(result);
        return this.success(response);
    }

}

package com.kfwy.hkp.controller.hos.house;

import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.hos.house.vo.HouseApplyServiceRequest;
import com.kfwy.hkp.controller.hos.house.vo.HouseApplyServiceResponse;
import com.kfwy.hkp.hos.house.business.IHouseApplyManager;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
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
 * Create By hjh on 2018/8/10
 */

@RestController
@RequestMapping(path = "/houseApply")
public class HouseApplyService extends SpringRestService {

    @Autowired
    private IHouseApplyManager houseApplyManager;

    @Autowired
    private IHouseManager houseManager;


    /**
     * 新增申请
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody HouseApplyServiceRequest request) {

        HouseApplyServiceResponse response = new HouseApplyServiceResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("houseCode",request.getEntity().getHouseCode());
        HouseEntity houseEntity = houseManager.findOne(map);

        if (houseEntity == null) {
            throw new BusinessException("该房源不存在");
        }

//        map.put("empCode", CurrentContext.getUserCode());
        houseApplyManager.create(request.getEntity());
        response.setResult(houseEntity.getHouseowner().getHouseownerPhone());
//        response.setResult("申请-房源-完毕,app端重新调详情接口!");
        return this.success(response);
    }


}

package com.kfwy.hkp.controller.hos.house;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.hos.house.vo.HouseFavoriteServiceRequest;
import com.kfwy.hkp.controller.hos.house.vo.HouseFavoriteServiceResponse;
import com.kfwy.hkp.hos.house.business.IHouseFavoriteManager;
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
 * Create By hjh on 2018/8/8
 */

@RestController
@RequestMapping(path = "/houseFavorite")
public class HouseFavoriteService extends SpringRestService {

    @Autowired
    private IHouseFavoriteManager houseFavoriteManager;

    /**
     * 新增收藏
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)    public ResponseEntity<IServiceResponse> create(@RequestBody HouseFavoriteServiceRequest request) {

        HouseFavoriteServiceResponse response = new HouseFavoriteServiceResponse();
        houseFavoriteManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * 删除收藏
     * @param request
     * @return
     */
    @RequestMapping(path = "/delete",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody HouseFavoriteServiceRequest request) {

        HouseFavoriteServiceResponse response = new HouseFavoriteServiceResponse();
        String houseCode = null;
        if (request.getEntity()!=null){
            houseCode = request.getEntity().getHouseCode();
        }else if (request.getHouseCode()!=null){
            houseCode = request.getHouseCode();
        }
        if (StringUtils.isEmpty(houseCode)||StringUtils.isEmpty(CurrentContext.getUserCode())){
            throw new BusinessException("取消收藏失败");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("houseCode", houseCode);
        param.put("empCode", CurrentContext.getUserCode());

        houseFavoriteManager.delete(param);

        return this.success(response);
    }
}

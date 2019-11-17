package com.kfwy.hkp.controller.sys.auth;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.sys.auth.vo.MenuServiceRequest;
import com.kfwy.hkp.controller.sys.auth.vo.MenuServiceResponse;
import com.kfwy.hkp.sys.auth.business.IMenuManager;
import com.kfwy.hkp.sys.auth.entity.MenuEntity;
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
 * @author
 *
 * Created by davidcun on 2018/5/22.
 */
@RestController
@RequestMapping("/menu")
public class MenuService extends SpringRestService {

    @Autowired
    private IMenuManager menuManager;
    /**
     * @Description 创建菜单
     * @Auth liwensihan
     * @Date 2018/11/8
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody MenuServiceRequest request) {

        MenuServiceResponse response = new MenuServiceResponse();

        menuManager.create(request.getEntity());

        return this.success(response);
    }
    /**
     * @Description 更新菜单
     * @Auth liwensihan
     * @Date 2018/11/8
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody MenuServiceRequest request){

        MenuServiceResponse response = new MenuServiceResponse();

        menuManager.update(request.getEntity());

        return this.success(response);

    }
    /**
     * @Description 根据menuCode查询
     * @Auth liwensihan
     * @Date 2018/11/8
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody MenuServiceRequest request){
        MenuServiceResponse response = new MenuServiceResponse();

        response.setResult(menuManager.findAllMenuTree());

        return this.success(response);
    }

    /**
     * @Description 根据menuCode查询
     * @Auth liwensihan
     * @Date 2018/11/8
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/queryByMenuCode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryByMenuCode(@RequestBody MenuServiceRequest request) {
        MenuServiceResponse response = new MenuServiceResponse();

        MenuEntity menuEntity = request.getEntity();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("menuCode", menuEntity.getMenuCode());
        param.put("isDeleted", false);
        PageResult<MenuEntity> result = menuManager.findByMap(param, request.getStart(), request.getPageSize(),"id",true);

        response.setResult(result);
        return this.success(response);
    }


    @RequestMapping(path = "/queryUserMenuTree"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryUserMenuTreeByUserCode(@RequestBody MenuServiceRequest request){

        MenuServiceResponse response = new MenuServiceResponse();

        response.setResult(menuManager.findUserMenuToTreeByUserCode(request.getUserCode()));

        return this.success(response);
    }


}

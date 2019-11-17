package com.kfwy.hkp.controller.sys.auth;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.controller.sys.auth.vo.RoleDto;
import com.kfwy.hkp.controller.sys.auth.vo.RoleServiceRequest;
import com.kfwy.hkp.controller.sys.auth.vo.RoleServiceResponse;
import com.kfwy.hkp.sys.auth.business.IMenuManager;
import com.kfwy.hkp.sys.auth.business.IRoleManager;
import com.kfwy.hkp.sys.auth.entity.RoleEntity;
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
 * @author davidcun
 * @date 2018/5/22
 */
@RestController
@RequestMapping("/role")
public class RoleService extends SpringRestService {

    @Autowired
    private IRoleManager roleManager;

    @Autowired
    private IMenuManager menuManager;

    /**
     * 角色创建
     *
     * @param request
     * @return
     * @Autor liwensihan
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST)
    public ResponseEntity<IServiceResponse> create(@RequestBody RoleServiceRequest request) {

        RoleServiceResponse response = new RoleServiceResponse();

        roleManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * 角色菜单权限更新
     *
     * @param request
     * @return
     * @Autor liwensihan
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody RoleServiceRequest request) {

        RoleServiceResponse response = new RoleServiceResponse();

        roleManager.update(request.getEntity(), request.getMenuCodes());

        return this.success(response);
    }

    /**
     * 角色列表集合查询
     *
     * @param request
     * @return
     * @Autor liwensihan
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody RoleServiceRequest request) {

        RoleServiceResponse response = new RoleServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        RoleEntity roleEntity = request.getEntity();
        if (StringUtils.isNotEmpty(roleEntity.getRoleName())) {
            param.put("roleName", roleEntity.getRoleName());
        }
        param.put("isDeleted", Boolean.FALSE);
        //param.put("cpyCode", CurrentContext.getUserInfo().getCpyCode());

        PageResult<RoleEntity> result = roleManager.findByMap(param, request.getStart(), request.getPageSize());

        response.setResult(result);

        return this.success(response);
    }


    @RequestMapping(path = "/queryBindingPost",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryBindingPost(@RequestBody RoleServiceRequest request) {
        RoleServiceResponse response = new RoleServiceResponse();
        String roleCode = request.getRoleCode();
        String empPostCode = roleManager.findBindingPostCodeByRoleCode(roleCode);
        response.setResult(empPostCode);
        return this.success(response);
    }

    @RequestMapping(path = "/editBindingPost",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> editBindingPost(@RequestBody RoleServiceRequest request) {
        RoleServiceResponse response = new RoleServiceResponse();
        int isBinding = roleManager.editBindingPost(request.getEntity(), request.getEmpPostCode(), request.getEmpPostName());
        if (isBinding > 0) {
            return this.success(response);
        } else {
            return this.error(response, "绑定岗位失败");
        }
    }

    /**
     * @param request
     * @return
     * @Description 查询当前角色所拥有的页面查看权限
     * @Autor liwensihan
     */
    @RequestMapping(path = "/queryOne",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryOne(@RequestBody RoleServiceRequest request) {
        RoleServiceResponse response = new RoleServiceResponse();

        RoleEntity role = roleManager.findOne("roleCode", request.getEntity().getRoleCode());

        RoleDto rd = new RoleDto(role);

        rd.setMenus(menuManager.findMenuListByRole(rd.getRoleCode()));

        rd.setRoleName(role.getRoleName());
        response.setResult(rd);

        return this.success(response);
    }

}

package com.kfwy.hkp.controller.sys.user;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.common.enums.ApplyState;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.controller.sys.user.vo.UserApplyTeamServiceRequest;
import com.kfwy.hkp.controller.sys.user.vo.UserApplyTeamServiceResponse;
import com.kfwy.hkp.hos.house.entity.SharePoolApplyEntity;
import com.kfwy.hkp.sys.user.business.IUserApplyTeamManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserApplyTeamEntity;
import com.kfwy.hkp.sys.user.enums.ApplyCategory;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author kfwy_it_013
 */

@RestController
@RequestMapping(path = "/userApplyTeamApply")
public class UserApplyTeamService extends SpringRestService {

    @Autowired
    private IUserApplyTeamManager userApplyTeamManager;


    /**
     * 创建申请记录,暂时不用
     * @param request
     * @return
     */
    @RequestMapping(path = "/create999",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody UserApplyTeamServiceRequest request) throws IllegalAccessException {

        UserApplyTeamServiceResponse response = new UserApplyTeamServiceResponse();
        UserApplyTeamEntity UserApplyTeamEntity = request.getEntity();

        // 根据业务需求对应字段校验
        List<String> list = new ArrayList();
        list.add("applyDeptCode");
        list.add("receiveDeptCode");
        ParamUtil<UserApplyTeamEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(),list);
        // 根据业务需求对应字段校验 end

        // 查询申请是否存在
        userApplyTeamManager.create(UserApplyTeamEntity);

        response.setResult("新增申请");

        return this.success(response);
    }

    /**
     * 分页查询申请记录
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "查询申请记录",
            notes = "分页,按时间排序")
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody UserApplyTeamServiceRequest request) throws IllegalAccessException {

        UserApplyTeamServiceResponse response = new UserApplyTeamServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        if(request.getEntity().getApplyDeptCode()==null){throw new BusinessException("本接口applyDeptCode字段需要传部门编码");}
        if(request.getEntity().getItemType()==null){throw new BusinessException("本接口itemType字段需要传join或者leave");}
        param.put("applyDeptCode",request.getEntity().getApplyDeptCode());
        param.put("itemType",request.getEntity().getItemType());
        PageResult<UserApplyTeamEntity> byMap = userApplyTeamManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);
        //对数据添加分类字段,我申请的还是别人申请我的start
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        if (userInfo == null) {
            throw new BusinessException("用户信息为空,请重新登陆." + userInfo.getUserCode());
        }
        String myDeptCode=userInfo.getOwnerDeptCode();
        List<UserApplyTeamEntity> spData = byMap.getData();


        //我申请的还是别人申请我的end
        response.setResult(byMap);
        return this.success(response);
    }


    /**
     * 创建申请记录
     * @param request
     * @return
     */
    @RequestMapping(path = "/invite",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> invite(@RequestBody UserApplyTeamServiceRequest request) throws IllegalAccessException {

        UserApplyTeamServiceResponse response = new UserApplyTeamServiceResponse();
        UserApplyTeamEntity UserApplyTeamEntity = request.getEntity();

        // 根据业务需求对应字段校验
        List<String> list = new ArrayList();
        list.add("receivePersonCode");
        ParamUtil<UserApplyTeamEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(),list);
        // 根据业务需求对应字段校验 end

        // 查询申请是否存在
        userApplyTeamManager.create(UserApplyTeamEntity);

        response.setResult("新增申请");

        return this.success(response);
    }


    /**
     * 申请脱离
     * @param request
     * @return
     */
    @RequestMapping(path = "/leaveDeptApply",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> leaveDeptApply(@RequestBody UserApplyTeamServiceRequest request) throws IllegalAccessException {

        UserApplyTeamServiceResponse response = new UserApplyTeamServiceResponse();
        UserApplyTeamEntity UserApplyTeamEntity = request.getEntity();

        // 根据业务需求对应字段校验
//        List<String> list = new ArrayList();
//        ParamUtil<UserApplyTeamEntity> paramUtil = new ParamUtil<>();
//        paramUtil.check(request.getEntity(),list);
        // 根据业务需求对应字段校验 end

        // 查询申请是否存在
        userApplyTeamManager.leaveDeptApply(UserApplyTeamEntity);

        response.setResult("离开申请已提交");

        return this.success(response);
    }


    /**
     * 取消脱离
     * @param request
     * @return
     */
    @RequestMapping(path = "/cancelLeave",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cancelLeave(@RequestBody UserApplyTeamServiceRequest request) throws IllegalAccessException {

        UserApplyTeamServiceResponse response = new UserApplyTeamServiceResponse();
        UserApplyTeamEntity UserApplyTeamEntity = request.getEntity();

        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        if (userInfo == null) {
            throw new BusinessException("用户信息为空,请重新登陆." + userInfo.getUserCode());
        }

        Map paramMap = new HashMap<String,String>();
        paramMap.put("applyPersonCode",userInfo.getUserCode());
        paramMap.put("itemType","leave");
        List<UserApplyTeamEntity> byMap = userApplyTeamManager.findByMap(paramMap);
        for (UserApplyTeamEntity entity : byMap) {
            userApplyTeamManager.delete(entity);
        }

        response.setResult("离开申请已取消");

        return this.success(response);
    }

    /**
     * 查询申请或取消按钮
     * @param request
     * @return
     */
    @RequestMapping(path = "/queryApplyOrCancel",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryApplyOrCancel(@RequestBody UserApplyTeamServiceRequest request) throws IllegalAccessException {

        UserApplyTeamServiceResponse response = new UserApplyTeamServiceResponse();
        UserApplyTeamEntity UserApplyTeamEntity = request.getEntity();

        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        if (userInfo == null) {
            throw new BusinessException("用户信息为空,请重新登陆." + userInfo.getUserCode());
        }

        Map paramMap = new HashMap<String,String>();
        paramMap.put("applyPersonCode",userInfo.getUserCode());
        paramMap.put("itemType","leave");
        List<UserApplyTeamEntity> byMap = userApplyTeamManager.findByMap(paramMap);
        if(byMap==null || byMap.size()==0){
            response.setResult(true);
        }else{
            response.setResult(false);
        }

        return this.success(response);
    }

    /**
     * 拒绝离开申请
     * @param request
     * @return
     */
    @RequestMapping(path = "/refuse",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> refuse(@RequestBody UserApplyTeamServiceRequest request) throws IllegalAccessException {

        UserApplyTeamServiceResponse response = new UserApplyTeamServiceResponse();
        UserApplyTeamEntity UserApplyTeamEntity = request.getEntity();

        // 根据业务需求对应字段校验
        List<String> list = new ArrayList();
        list.add("applyRecordCode");
        ParamUtil<UserApplyTeamEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(),list);
        // 根据业务需求对应字段校验 end
        HashMap<String, Object> map = new HashMap<>();
        map.put("applyRecordCode",request.getEntity().getApplyRecordCode());
        map.put("itemType","leave");
        map.put("isDeleted",false);

        UserApplyTeamEntity byMap = userApplyTeamManager.findOne(map);
        if(byMap==null){throw new BusinessException("未查到该条申请");}
        byMap.setApplyState(ApplyCategory.REJECTED);
        userApplyTeamManager.update(byMap);


        response.setResult("拒绝离开申请");

        return this.success(response);
    }

}

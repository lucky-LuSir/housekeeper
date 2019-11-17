package com.kfwy.hkp.controller.hos.house;

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
import com.kfwy.hkp.controller.hos.house.vo.*;
import com.kfwy.hkp.hos.house.business.ISharePoolApplyManager;
import com.kfwy.hkp.hos.house.business.ISharePoolDeptManager;
import com.kfwy.hkp.hos.house.business.ISharePoolManager;
import com.kfwy.hkp.hos.house.entity.*;
import com.kfwy.hkp.hos.house.enums.HouseQueryType;
import com.kfwy.hkp.hos.house.enums.ShareType;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserType;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @author kfwy_it_013
 */

@RestController
@RequestMapping(path = "/sharePoolApply")
public class SharePoolApplyService extends SpringRestService {

    @Autowired
    private ISharePoolApplyManager sharePoolApplyManager;


    /**
     * 创建申请记录
     * @param request
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody SharePoolApplyServiceRequest request) throws IllegalAccessException {
        //开始时间
        long starts = System.currentTimeMillis();

        SharePoolApplyServiceResponse response = new SharePoolApplyServiceResponse();
        SharePoolApplyEntity sharePoolApplyEntity = request.getEntity();

        // 根据业务需求对应字段校验
        List<String> list = new ArrayList();
        list.add("applyDeptCode");
        list.add("receiveDeptCode");
        ParamUtil<SharePoolApplyEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(),list);
        // 根据业务需求对应字段校验 end

        // 查询申请是否存在
        sharePoolApplyManager.create(sharePoolApplyEntity);

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
    public ResponseEntity<IServiceResponse> query(@RequestBody SharePoolApplyServiceRequest request) throws IllegalAccessException {

        SharePoolApplyServiceResponse response = new SharePoolApplyServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        if(request.getEntity().getReceiveDeptCode()==null){throw new BusinessException("本接口receiveDeptCode需要传部门编码");}
        param.put("receiveDeptCode",request.getEntity().getReceiveDeptCode());
        PageResult<SharePoolApplyEntity> byMap = sharePoolApplyManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);
        //对数据添加分类字段,我申请的还是别人申请我的start
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        if (userInfo == null) {
            throw new BusinessException("用户信息为空,请重新登陆." + userInfo.getUserCode());
        }
        String myDeptCode=userInfo.getOwnerDeptCode();
        List<SharePoolApplyEntity> spData = byMap.getData();
        if(spData!=null){
            for (int i = 0; i < spData.size(); i++) {
                if(spData.get(i).getApplyDeptCode().equals(myDeptCode)){
                    spData.get(i).setCategory("MyApply");
                }else if(spData.get(i).getReceiveDeptCode().equals(myDeptCode)){
                    spData.get(i).setCategory("MyReceive");
                }else{
                    spData.get(i).setCategory("notFind");
                }
            }
        }

        //我申请的还是别人申请我的end
        response.setResult(byMap);
        return this.success(response);
    }


    /**
     * 取消团队申请共享
     * @param request
     * @return
     */
    @RequestMapping(path = "/delete",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody SharePoolApplyServiceRequest request) throws IllegalAccessException {

        SharePoolApplyServiceResponse response = new SharePoolApplyServiceResponse();
        SharePoolApplyEntity sharePoolApplyEntity = request.getEntity();

        // 根据业务需求对应字段校验
        List<String> list = new ArrayList();
        list.add("applyDeptCode");
        list.add("receiveDeptCode");
        ParamUtil<SharePoolApplyEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(),list);
        // 根据业务需求对应字段校验 end

        //判断申请是否已经存在agree
        HashMap<String, Object> paramTwo = new HashMap<>();
        paramTwo.put("applyDeptCode",request.getEntity().getOwnerDeptCode());
        paramTwo.put("receiveDeptCode",request.getEntity().getReceiveDeptCode());
        paramTwo.put("applyState",ApplyState.SHARED);
        paramTwo.put("isDeleted",false);
        List<SharePoolApplyEntity> byMapTwo = sharePoolApplyManager.findByMap(paramTwo);
        if(byMapTwo!=null){
            if(byMapTwo.size()>0){
                throw new BusinessException("已经有正在共享的申请,需要先去共享团队中取消共享!");
            }
        }
        //判断申请是否已经存在agree  end

        HashMap<String, Object> map = new HashMap<>();
        map.put("applyDeptCode",request.getEntity().getApplyDeptCode());
        map.put("receiveDeptCode",request.getEntity().getReceiveDeptCode());
        map.put("isDeleted",false);

        List<SharePoolApplyEntity> byMap = sharePoolApplyManager.findByMap(map);
        if(byMap==null ||byMap.size()==0){throw new BusinessException("未查到该条申请");}
        for (SharePoolApplyEntity entity : byMap) {
            entity.setIsDeleted(true);
            sharePoolApplyManager.update(entity);
        }


        response.setResult("取消团队共享申请");

        return this.success(response);
    }


    /**
     * 拒绝团队申请共享
     * @param request
     * @return
     */
    @RequestMapping(path = "/refuse",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> refuse(@RequestBody SharePoolApplyServiceRequest request) throws IllegalAccessException {

        SharePoolApplyServiceResponse response = new SharePoolApplyServiceResponse();
        SharePoolApplyEntity sharePoolApplyEntity = request.getEntity();

        // 根据业务需求对应字段校验
        List<String> list = new ArrayList();
        list.add("applyRecordCode");
        ParamUtil<SharePoolApplyEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(),list);
        // 根据业务需求对应字段校验 end
        HashMap<String, Object> map = new HashMap<>();
        map.put("applyRecordCode",request.getEntity().getApplyRecordCode());
        map.put("isDeleted",false);

        SharePoolApplyEntity byMap = sharePoolApplyManager.findOne(map);
        if(byMap==null){throw new BusinessException("未查到该条申请");}
            byMap.setApplyState(ApplyState.REJECTED);
            sharePoolApplyManager.update(byMap);


        response.setResult("拒绝团队共享申请");

        return this.success(response);
    }


}

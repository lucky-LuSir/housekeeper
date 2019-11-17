package com.kfwy.hkp.controller.hos.house;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.common.enums.ApplyState;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.controller.hos.house.vo.SharePoolDeptServiceResponse;
import com.kfwy.hkp.controller.hos.house.vo.SharePoolServiceRequest;
import com.kfwy.hkp.controller.hos.house.vo.SharePoolServiceResponse;
import com.kfwy.hkp.hos.house.business.ISharePoolApplyManager;
import com.kfwy.hkp.hos.house.business.ISharePoolDeptManager;
import com.kfwy.hkp.hos.house.business.ISharePoolManager;
import com.kfwy.hkp.hos.house.entity.SharePoolApplyEntity;
import com.kfwy.hkp.hos.house.entity.SharePoolDeptEntity;
import com.kfwy.hkp.hos.house.entity.SharePoolEntity;
import com.kfwy.hkp.hos.house.enums.ShareType;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
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

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author kfwy_it_013
 */

@RestController
@RequestMapping(path = "/sharePool")
public class SharePoolService extends SpringRestService {

    @Autowired
    private ISharePoolManager sharePoolManager;
    @Autowired
    private ISharePoolDeptManager sharePoolDeptManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private ISharePoolApplyManager sharePoolApplyManager;

    /**
     * 查询共享池信息
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody SharePoolServiceRequest request) {
        SharePoolServiceResponse response = new SharePoolServiceResponse();
        Map map = new HashMap();
        map.put("keyword", request.getKeyword());
        map.put("isDeleted", Boolean.FALSE);
        PageResult<SharePoolEntity> result = sharePoolManager.selectSharePool(map, request.getStart(), request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 查询我的部门共享列表
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/queryByMyDept",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryByMyDept(@RequestBody SharePoolServiceRequest request) {
        SharePoolServiceResponse response = new SharePoolServiceResponse();
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        if (userInfo == null) {
            throw new RemoveStackBusinessException ("用户信息为空,请重新登陆." + userInfo.getUserCode());
        }
        String myDeptCode=userInfo.getOwnerDeptCode();
        Map map = new HashMap();
        map.put("shareDeptCode", myDeptCode);
        map.put("isDeleted", Boolean.FALSE);
        List<SharePoolEntity> result = sharePoolManager.selectByMyDept(map);
        response.setResult(result);
        return this.success(response);
    }

    @RequestMapping(path = "/addSharePool",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> addSharePool(@RequestBody SharePoolServiceRequest request) {
        SharePoolServiceResponse response = new SharePoolServiceResponse();
        SharePoolEntity sharePoolEntity = request.getEntity();
        sharePoolEntity.setShareCode(CodeGenUtils.generate("sp"));
        if (sharePoolEntity != null) {
            sharePoolEntity.setIsDeleted(Boolean.FALSE);
        }
        OperateFillUtils.fill(sharePoolEntity);
        int isAddSharePool = sharePoolManager.addSharePoolInfo(sharePoolEntity);
        if (isAddSharePool > 0) {
            //设置共享池内部门信息
            if (createSharePoolDeptInfo(sharePoolEntity) > 0) {
                return this.success(response);
            } else {
                return this.error(response, "新增部门共享信息异常");
            }

        } else {
            return this.error(response, "新增共享池信息异常");
        }
    }

    public int createSharePoolDeptInfo(SharePoolEntity sharePoolEntity) {
        SharePoolDeptEntity deptEntity = setSharePoolDeptInfo(sharePoolEntity);
        return sharePoolDeptManager.addSharePoolDeptInfo(deptEntity);
    }

    public SharePoolDeptEntity setSharePoolDeptInfo(SharePoolEntity sharePoolEntity) {
        SharePoolDeptEntity deptEntity = new SharePoolDeptEntity();
        deptEntity.setShareCode(sharePoolEntity.getShareCode());
        deptEntity.setShareType(sharePoolEntity.getShareType());
        deptEntity.setShareDeptCode(sharePoolEntity.getShareDeptCode());
        deptEntity.setShareDeptName(sharePoolEntity.getShareDeptName());
        deptEntity.setCreateCode(sharePoolEntity.getCreateCode());
        deptEntity.setCreateTime(sharePoolEntity.getCreateTime());
        deptEntity.setCreateDeptCode(sharePoolEntity.getCreateDeptCode());
        deptEntity.setCreateDeptName(sharePoolEntity.getCreateDeptName());
        deptEntity.setIsDeleted(Boolean.FALSE);
        return deptEntity;
    }

    /**
     * 加入一个或多个部门到共享池中
     * @param request
     * @return
     */
    @RequestMapping(path = "/addSharePoolDept",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> addSharePoolDept(@RequestBody SharePoolServiceRequest request) {
        SharePoolDeptServiceResponse response = new SharePoolDeptServiceResponse();
        //从前端将共享信息池对象传到后端
        String shareCode = request.getShareCode();
        SharePoolEntity sharePoolEntity = sharePoolManager.findSharePoolByShareCode(shareCode);
        List<DeptEntity> joinDeptList = new ArrayList<>();
        for (String deptCode :request.getJoinPoolDeptCode()){
            DeptEntity deptEntity = deptManager.getDeptEntity(deptCode);
            if (deptEntity!=null){
                joinDeptList.add(deptEntity);
            }
        }

        for (DeptEntity deptEntity : joinDeptList){
            SharePoolDeptEntity sharePoolDeptEntity = new SharePoolDeptEntity();
            sharePoolDeptEntity.setShareDeptCode(deptEntity.getDeptCode());
            sharePoolDeptEntity.setShareDeptName(deptEntity.getDeptName());
            sharePoolDeptEntity.setShareCode(sharePoolEntity.getShareCode());
            sharePoolDeptEntity.setShareType(sharePoolEntity.getShareType());
            sharePoolDeptEntity.setIsDeleted(Boolean.FALSE);
            OperateFillUtils.fill(sharePoolDeptEntity);
            int isAddSharePoolDept = sharePoolDeptManager.addSharePoolDeptInfo(sharePoolDeptEntity);
        }
        return this.success(response);
    }


    /**
     * 创建共享池，并分配加入共享池中的部门
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/addSharePoolWithEachDept",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> addSharePoolWithEachDept(@RequestBody SharePoolServiceRequest request) {
        SharePoolServiceResponse response = new SharePoolServiceResponse();
        SharePoolEntity sharePoolVo = request.getEntity();
        String shareType = request.getShareType();

        //加入共享池的部门集合
        List<String> joinPoolDeptCode = request.getJoinPoolDeptCode();

        //加入共享池的所有部门的子部门集合
        List<String> allJoinDeptCode = new ArrayList<>();

        //共享类型是分别共享或统一共享的则遍历子部门
        setIfShareTypeIsOneOrTwo(shareType, joinPoolDeptCode, allJoinDeptCode);

        //共享池主表集合
        List<SharePoolEntity> sharePoolEntityList = new ArrayList<SharePoolEntity>();
        //共享池子表集合
        List<SharePoolDeptEntity> sharePoolDeptEntityList = new ArrayList<SharePoolDeptEntity>();
        switch (shareType) {
            //创建部门与每个共享部门分别在不同的共享池中共享
            case ShareType.RESPECTIVELY_SHARING:
                if (allJoinDeptCode != null && allJoinDeptCode.size() > 0) {
                    //设置共享池创建部门和加入共享池的部门
                    setSharePoolDeptAndJoinSharePoolDeptTypeOne(sharePoolVo, allJoinDeptCode, sharePoolEntityList, sharePoolDeptEntityList);
                    break;
                }
                throw new RemoveStackBusinessException("创建分别共享类型的共享池异常");
                //统一共享
            case ShareType.UNIFIED_SHARING:
                if (allJoinDeptCode != null && allJoinDeptCode.size() > 0) {
                    setSharePoolDeptAndJoinSharePoolDeptTypeTwo(sharePoolVo, allJoinDeptCode, sharePoolEntityList, sharePoolDeptEntityList);
                    break;
                }
                throw new RemoveStackBusinessException("创建统一共享类型的共享池异常");
                //不递归子部门的统一共享
            case ShareType.SEPARATE_SHARING:
                if (joinPoolDeptCode != null && joinPoolDeptCode.size() > 0) {
                    setSharePoolDeptAndJoinSharePoolDeptTypeThree(sharePoolVo, joinPoolDeptCode, sharePoolEntityList, sharePoolDeptEntityList);
                    break;
                }
                throw new RemoveStackBusinessException("创建不递归子部门的统一共享类型的共享池异常");
        }

        return createSharePoolAndShareDeptPool(response, sharePoolEntityList, sharePoolDeptEntityList);
    }

    /**
     * 创建共享池，并分配加入共享池中的部门,同时创建房源共享和客户共享
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/addSharePoolTwice",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> addSharePoolTwice(@RequestBody SharePoolServiceRequest request) {
        SharePoolServiceResponse response = new SharePoolServiceResponse();
        SharePoolEntity sharePoolVo = request.getEntity();
        //业务字段校验
        if (request.getApplyRecordCode() == null) {
            throw new RemoveStackBusinessException("addSharePoolTwice接口applyRecordCode 必传");
        }
        if (request.getJoinPoolDeptCode() == null) {
            throw new RemoveStackBusinessException("addSharePoolTwice接口joinPoolDeptCode数组 必传");
        }
        if (request.getEntity().getShareDeptCode() == null) {
            throw new RemoveStackBusinessException("addSharePoolTwice接口shareDeptCode 必传");
        }
        //改变该条共享申请状态start
        HashMap<String, Object> mapApply = new HashMap<>();
        mapApply.put("applyRecordCode",request.getApplyRecordCode());
        mapApply.put("isDeleted",false);

        SharePoolApplyEntity byMap = sharePoolApplyManager.findOne(mapApply);
        if(byMap==null){throw new RemoveStackBusinessException("未查到该条申请");}
        byMap.setApplyState(ApplyState.SHARED);
        sharePoolApplyManager.update(byMap);
        //改变该条共享申请状态end
        sharePoolVo.setApplyRecordCode(request.getApplyRecordCode());
        if (sharePoolVo.getApplyRecordCode() == null) {
            throw new RemoveStackBusinessException("addSharePoolTwice接口sharePoolVo设置值ApplyRecordCode为空!");
        }
        String shareType = "3";
        for (int i = 0; i <2 ; i++) {
            if(i==0){sharePoolVo.setShareType("cus");}
            if(i==1){sharePoolVo.setShareType("hos");}

            //加入共享池的部门集合
            List<String> joinPoolDeptCode = request.getJoinPoolDeptCode();

            //加入共享池的所有部门的子部门集合
            List<String> allJoinDeptCode = new ArrayList<>();

            //共享类型是分别共享或统一共享的则遍历子部门
            setIfShareTypeIsOneOrTwo(shareType, joinPoolDeptCode, allJoinDeptCode);

            //共享池主表集合
            List<SharePoolEntity> sharePoolEntityList = new ArrayList<SharePoolEntity>();
            //共享池子表集合
            List<SharePoolDeptEntity> sharePoolDeptEntityList = new ArrayList<SharePoolDeptEntity>();
            switch (shareType) {
                //创建部门与每个共享部门分别在不同的共享池中共享
                case ShareType.RESPECTIVELY_SHARING:
                    if (allJoinDeptCode != null && allJoinDeptCode.size() > 0) {
                        //设置共享池创建部门和加入共享池的部门
                        setSharePoolDeptAndJoinSharePoolDeptTypeOne(sharePoolVo, allJoinDeptCode, sharePoolEntityList, sharePoolDeptEntityList);
                        break;
                    }
                    throw new RemoveStackBusinessException("创建分别共享类型的共享池异常");
                    //统一共享
                case ShareType.UNIFIED_SHARING:
                    if (allJoinDeptCode != null && allJoinDeptCode.size() > 0) {
                        setSharePoolDeptAndJoinSharePoolDeptTypeTwo(sharePoolVo, allJoinDeptCode, sharePoolEntityList, sharePoolDeptEntityList);
                        break;
                    }
                    throw new RemoveStackBusinessException("创建统一共享类型的共享池异常");
                    //不递归子部门的统一共享
                case ShareType.SEPARATE_SHARING:
                    if (joinPoolDeptCode != null && joinPoolDeptCode.size() > 0) {
                        setSharePoolDeptAndJoinSharePoolDeptTypeThree(sharePoolVo, joinPoolDeptCode, sharePoolEntityList, sharePoolDeptEntityList);
                        break;
                    }
                    throw new RemoveStackBusinessException("创建不递归子部门的统一共享类型的共享池异常");
            }

            ResponseEntity<IServiceResponse> sharePoolAndShareDeptPool = createSharePoolAndShareDeptPool(response, sharePoolEntityList, sharePoolDeptEntityList);

        }

        response.setMessage("新增成功");
        return this.success(response);
    }

    private void setSharePoolDeptAndJoinSharePoolDeptTypeThree(SharePoolEntity sharePoolVo, List<String> joinPoolDeptCode, List<SharePoolEntity> sharePoolEntityList, List<SharePoolDeptEntity> sharePoolDeptEntityList) {
        setSharePoolDeptAndJoinSharePoolDeptTypeTwo(sharePoolVo, joinPoolDeptCode, sharePoolEntityList, sharePoolDeptEntityList);
    }


    /**
     * 共享类型是1、2的则遍历子部门
     *
     * @param shareType
     * @param joinPoolDeptCode
     * @param allJoinDeptCode
     */
    public void setIfShareTypeIsOneOrTwo(String shareType, List<String> joinPoolDeptCode, List<String> allJoinDeptCode) {
        if (shareType.equals(ShareType.RESPECTIVELY_SHARING) || shareType.equals(ShareType.UNIFIED_SHARING)) {
            //遍历加入共享池中的部门code，如果有下级部门则加入
            for (String joinDeptCode : joinPoolDeptCode) {
                List<String> deptCode = deptManager.getDownDeptCode(joinDeptCode);
                //如果有下级部门就加入到allJoinDeptCode集合中
                if (deptCode != null && deptCode.size() > 0) {
                    allJoinDeptCode.addAll(deptCode);
                }
                if (!allJoinDeptCode.contains(joinDeptCode)){
                    allJoinDeptCode.add(joinDeptCode);
                }
            }
        }
    }

    /**
     * @param response
     * @param sharePoolEntityList
     * @param sharePoolDeptEntityList
     * @return
     */
    public ResponseEntity<IServiceResponse> createSharePoolAndShareDeptPool(SharePoolServiceResponse response, List<SharePoolEntity> sharePoolEntityList, List<SharePoolDeptEntity> sharePoolDeptEntityList) {
        if (batchInsertSharePoolAndSharePoolDeptInfoList(sharePoolEntityList, sharePoolDeptEntityList)) {
            return this.success(response);
        } else {
            throw new RemoveStackBusinessException("共享部门失败");
        }
    }

    /**
     * @param sharePoolVo
     * @param joinShareDeptCode
     * @param sharePoolEntityList
     * @param sharePoolDeptEntityList
     */
    public void setSharePoolDeptAndJoinSharePoolDept(SharePoolEntity sharePoolVo, String joinShareDeptCode, List<SharePoolEntity> sharePoolEntityList, List<SharePoolDeptEntity> sharePoolDeptEntityList) {
        //创建共享池主表信息
        SharePoolEntity sharePool = new SharePoolEntity();
        sharePool.setShareCode(CodeGenUtils.generate("sp"));
        sharePool.setShareType(sharePoolVo.getShareType());
        sharePool.setShareDeptCode(sharePoolVo.getShareDeptCode());
        sharePool.setShareDeptName(deptManager.getDeptEntity(sharePoolVo.getShareDeptCode()).getDeptName());
        OperateFillUtils.fill(sharePool);

        //共享池子表主部门信息设置
        SharePoolDeptEntity createPoolDeptEntity = new SharePoolDeptEntity();
        createPoolDeptEntity.setShareCode(sharePool.getShareCode());
        createPoolDeptEntity.setShareType(sharePool.getShareType());
        createPoolDeptEntity.setShareDeptCode(sharePool.getShareDeptCode());
        createPoolDeptEntity.setShareDeptName(sharePool.getShareDeptName());
        OperateFillUtils.fill(createPoolDeptEntity);
        sharePoolDeptEntityList.add(createPoolDeptEntity);

        //共享池子表加入部门信息设置
        SharePoolDeptEntity joinPoolDeptEntity = new SharePoolDeptEntity();
        DeptEntity deptEntity = deptManager.getDeptEntity(joinShareDeptCode);
        joinPoolDeptEntity.setShareCode(sharePool.getShareCode());
        joinPoolDeptEntity.setShareType(sharePool.getShareType());
        joinPoolDeptEntity.setShareDeptCode(deptEntity.getDeptCode());
        joinPoolDeptEntity.setShareDeptName(deptEntity.getDeptName());
        OperateFillUtils.fill(joinPoolDeptEntity);
        sharePoolDeptEntityList.add(joinPoolDeptEntity);
        sharePool.setShareName(sharePool.getShareDeptName() + "、" + joinPoolDeptEntity.getShareDeptName() + sharePool.getShareTypeName());
        sharePoolEntityList.add(sharePool);
    }

    /**
     * 分别和创建部门共享的共享池，
     * 加入共享池的各个部门之间除了和创建部门共享，和其他部门不共享
     *
     * @param sharePoolVo
     * @param sharePoolEntityList
     * @param sharePoolDeptEntityList
     */
    public void setSharePoolDeptAndJoinSharePoolDeptTypeOne(SharePoolEntity sharePoolVo, List<String> allJoinDeptCode, List<SharePoolEntity> sharePoolEntityList, List<SharePoolDeptEntity> sharePoolDeptEntityList) {
        for (String joinShareDeptCode : allJoinDeptCode) {
            if (sharePoolVo.getShareDeptCode().equals(joinShareDeptCode)){
                continue;
            }
            setSharePoolDeptAndJoinSharePoolDept(sharePoolVo, joinShareDeptCode, sharePoolEntityList, sharePoolDeptEntityList);
        }
    }

    /**
     * 加入共享池的各个部门包括子部门之间全部共享
     *
     * @param sharePoolVo
     * @param allJoinDeptCode
     * @param sharePoolEntityList
     * @param sharePoolDeptEntityList
     */
    public void setSharePoolDeptAndJoinSharePoolDeptTypeTwo(SharePoolEntity sharePoolVo, List<String> allJoinDeptCode, List<SharePoolEntity> sharePoolEntityList, List<SharePoolDeptEntity> sharePoolDeptEntityList) {
        //创建共享池主表信息
        SharePoolEntity sharePool = new SharePoolEntity();
        sharePool.setShareCode(CodeGenUtils.generate("sp"));
        sharePool.setShareType(sharePoolVo.getShareType());
        sharePool.setShareDeptCode(sharePoolVo.getShareDeptCode());
        sharePool.setShareDeptName(deptManager.getDeptEntity(sharePoolVo.getShareDeptCode()).getDeptName());
        OperateFillUtils.fill(sharePool);

        //共享池子表主部门信息设置
        SharePoolDeptEntity createPoolDeptEntity = new SharePoolDeptEntity();
        createPoolDeptEntity.setShareCode(sharePool.getShareCode());
        createPoolDeptEntity.setShareType(sharePool.getShareType());
        createPoolDeptEntity.setShareDeptCode(sharePool.getShareDeptCode());
        createPoolDeptEntity.setShareDeptName(sharePool.getShareDeptName());
        OperateFillUtils.fill(createPoolDeptEntity);
        sharePoolDeptEntityList.add(createPoolDeptEntity);
        //共享池名
        StringBuilder sharName = new StringBuilder(sharePool.getShareDeptName());

        for (String joinShareDeptCode : allJoinDeptCode) {
            if (sharePoolVo.getShareDeptCode().equals(joinShareDeptCode)){
                continue;
            }
            //共享池子表加入部门信息设置
            SharePoolDeptEntity joinPoolDeptEntity = new SharePoolDeptEntity();
            DeptEntity deptEntity = deptManager.getDeptEntity(joinShareDeptCode);
            joinPoolDeptEntity.setShareCode(sharePool.getShareCode());
            joinPoolDeptEntity.setShareType(sharePool.getShareType());
            joinPoolDeptEntity.setShareDeptCode(deptEntity.getDeptCode());
            joinPoolDeptEntity.setShareDeptName(deptEntity.getDeptName());
            OperateFillUtils.fill(joinPoolDeptEntity);
            sharePoolDeptEntityList.add(joinPoolDeptEntity);
            sharePool.setShareName(sharePool.getShareDeptName() + "、" + joinPoolDeptEntity.getShareDeptName() + sharePool.getShareTypeName());
            sharName.append("、" + joinPoolDeptEntity.getShareDeptName());
        }

        //加上共享池类型
        sharName.append("   " + sharePool.getShareTypeName());
        sharePool.setShareName(sharName.toString());
        sharePool.setApplyRecordCode(sharePoolVo.getApplyRecordCode());
        sharePoolEntityList.add(sharePool);
    }

    /**
     * 批量新增共享池方法
     *
     * @param sharePoolEntityList
     * @param sharePoolDeptEntityList
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean batchInsertSharePoolAndSharePoolDeptInfoList(List<SharePoolEntity> sharePoolEntityList, List<SharePoolDeptEntity> sharePoolDeptEntityList) {
        boolean isCreate = false;
        if (sharePoolManager.addSharePoolInfoList(sharePoolEntityList) > 0 &&
                sharePoolDeptManager.addSharePoolDeptInfo(sharePoolDeptEntityList) > 0) {
            isCreate = true;
        }
        return isCreate;
    }

    /**
     * 取消共享池
     *
     * @param request
     * @return
     */
    @Transactional
    @RequestMapping(path = "/delete",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody SharePoolServiceRequest request) {
        SharePoolServiceResponse response = new SharePoolServiceResponse();
        SharePoolEntity sharePoolVo = request.getEntity();
        String shareCode = sharePoolVo.getShareCode();
        if(shareCode==null){throw new RemoveStackBusinessException("shareCode字段必传");}


        //删除子表部门共享
        HashMap<String, Object> spdeptmap = new HashMap<>();
        spdeptmap.put("shareCode",shareCode);
        List<SharePoolDeptEntity> spdList = sharePoolDeptManager.findByShareCodeMap(spdeptmap);
        for (SharePoolDeptEntity entity : spdList) {
            DateTime wuMonth = DateUtil.parse("2019-05-19 23:59:59", "yyyy-MM-dd HH:mm:ss");
            Date dbCreate = entity.getCreateTime();
            if(dbCreate.before(wuMonth)){

                throw new BusinessException("5月20日之前,后台配置共享,暂时不允许取消!");
            }
            sharePoolDeptManager.delSharePoolDeptInfo(entity.getId());
        }

        //删除主表共享
        HashMap<String, Object> map = new HashMap<>();
        map.put("shareCode",shareCode);
        List<SharePoolEntity> SharePoolList = sharePoolManager.findByShareCodeMap(map);
        for (SharePoolEntity entity : SharePoolList) {


            //改变共享申请表状态
            HashMap<String, Object> mm = new HashMap<>();
            mm.put("applyRecordCode",entity.getApplyRecordCode());

            SharePoolApplyEntity byMap = sharePoolApplyManager.findOne(mm);
            if(byMap==null){throw new BusinessException("未查到共享对应申请");}
            byMap.setApplyState(ApplyState.DELETESHARE);
            sharePoolApplyManager.update(byMap);

            //删除共享
            sharePoolManager.delSharePoolInfo(entity.getId());
        }

        return this.success(response);
    }

}

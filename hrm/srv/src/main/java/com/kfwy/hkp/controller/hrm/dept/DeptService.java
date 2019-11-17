package com.kfwy.hkp.controller.hrm.dept;

import cn.hutool.core.collection.CollectionUtil;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.base.ActivationCodeDbDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.ParamUtil;
import com.kfwy.hkp.common.utils.ToolUtil;
import com.kfwy.hkp.controller.hrm.dept.vo.DeptServiceRequest;
import com.kfwy.hkp.controller.hrm.dept.vo.DeptServiceResponse;
import com.kfwy.hkp.hos.house.business.ISharePoolApplyManager;
import com.kfwy.hkp.hos.house.entity.SharePoolApplyEntity;
import com.kfwy.hkp.hrm.org.dept.api.DeptApiClient;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.dto.DeptTreeDto;
import com.kfwy.hkp.hrm.org.dept.entity.DeptAreaEntity;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.hrm.org.dept.enums.PayStatus;
import com.kfwy.hkp.sys.area.business.ICompanyAreaManager;
import com.kfwy.hkp.sys.area.entity.CompanyAreaEntity;
import com.kfwy.hkp.sys.auth.dao.IReportAccessDbDao;
import com.kfwy.hkp.sys.user.business.IUserDataManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by davidcun on 2018/5/22.
 */
@RestController
@RequestMapping("/dept")
public class DeptService extends SpringRestService {

    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IUserDataManager userDataManager;
    @Autowired
    private ISharePoolApplyManager sharePoolApplyManager;
    @Autowired
    private ActivationCodeDbDao activationCodeDbDao;
    @Autowired
    private ICompanyAreaManager companyAreaManager;
    @Autowired
    private IReportAccessDbDao reportAccessDbDao;


    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", request.getKeyword());
        PageResult<DeptEntity> result = deptManager.findByMap(map, request.getStart(), request.getPageSize());

        if (result == null) {
            return this.error(response, "操纵异常了，请联系管理员");
        }

        response.setResult(result);

        return this.success(response);
    }

    @RequestMapping(path = "/queryAll",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryAll(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("keyword", request.getKeyword());
        PageResult<DeptEntity> result = deptManager.findByMap(map, 1, 1000);

        if (result == null) {
            return this.error(response, "操纵异常了，请联系管理员");
        }
        response.setResult(result.getData());

        return this.success(response);
    }

    @RequestMapping(path = "/selectTrees",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectTrees(@RequestBody DeptServiceRequest request) {
        DeptServiceResponse response = new DeptServiceResponse();

        List<DeptEntity> tree = new ArrayList<>();

        UserEntity user = (UserEntity) CurrentContext.getUserInfo();

        if (user.getOwnerDeptCode() == null) {
            // 无部门返回自身
            DeptEntity d = new DeptEntity();
            d.setDeptCode(user.getUserCode());
            d.setDeptName(user.getUserName());
            tree.add(d);
        } else {
            // 获取可查看权限
            Map<String, Object> map = new HashMap<>();
            List<String> deptCodes = reportAccessDbDao.selectAccess(user.getUserCode());
            if (deptCodes.isEmpty()) {
                map.put("deptCode", user.getOwnerDeptCode());
            } else {
                map.put("deptCodes", deptCodes);
            }
            tree = deptManager.selectDeptTree(map);
        }
        response.setResult(tree);
        return this.success(response);
    }

    @RequestMapping(path = "/selectTree",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectTree(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();

        List<DeptTreeDto> deptTreeDtos = DeptApiClient.deptTree(CurrentContext.getUserCode());

        if (CollectionUtil.isEmpty(deptTreeDtos)) {
            deptTreeDtos = this.getTreeValue(deptTreeDtos);
        }

        response.setResult(deptTreeDtos);

        return this.success(response);
    }

    @RequestMapping(path = "/selectAllTree",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectAllTree(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("deptCode", "DP201605270001");
        List<DeptEntity> tree = deptManager.selectDeptTree(map);

        response.setResult(tree);

        return this.success(response);
    }

    private List<DeptTreeDto> getTreeValue(List<DeptTreeDto> deptTreeDtos) {
        deptTreeDtos = new ArrayList<>();
        UserEntity user = userManager.findUserByUserCode(CurrentContext.getUserCode());
        if (user != null) {
            DeptTreeDto dto = new DeptTreeDto();
            dto.setDeptCode(user.getUserCode());
            dto.setDeptName(user.getUserName());
            dto.setHasDept(2);
            deptTreeDtos.add(dto);
        }
        return deptTreeDtos;
    }

    @RequestMapping(path = "/deptDetails",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> deptDetails(@RequestBody DeptServiceRequest request) {


        String deptCode = request.getEntity().getDeptCode();
        DeptServiceResponse response = new DeptServiceResponse();
        DeptEntity deptEntity = deptManager.findOne("deptCode", deptCode);


        if (deptEntity == null) {
            return this.error(response, "操纵异常了，请联系管理员");
        }

        response.setResult(deptEntity);

        return this.success(response);
    }

    @RequestMapping(path = "/findDeptName",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> findDeptName(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();
        Map<String, Object> map = new HashMap<>();
        List<DeptEntity> result = deptManager.findByMap(map);

        if (result == null) {
            return this.error(response, "操纵异常了，请联系管理员");
        }
        response.setResult(result);

        return this.success(response);
    }

    @RequestMapping(path = "/createTeam",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createTeam(@RequestBody DeptServiceRequest request) throws IllegalAccessException {

        DeptServiceResponse response = new DeptServiceResponse();

        // 根据业务需求对应字段校验
        List<String> list = new ArrayList();
        list.add("deptName");
        list.add("hasRegister");
        //list.add("cpyShowName");
        list.add("hasFinanceProxy");
        ParamUtil<DeptEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(), list);
        // 根据业务需求对应字段校验 end

        for (DeptAreaEntity entity : request.getEntity().getDeptAreas()) {
            //处理省start
            if (entity.getCityCode() == null) {
                throw new RemoveStackBusinessException ("市区city必传");
            }
            CompanyAreaEntity cityEntity = companyAreaManager.findAreaByCode(entity.getCityCode());
            if (cityEntity == null) {
                throw new RemoveStackBusinessException("该市未启用:" + entity.getCityCode());
            }
            String cityParentCode = cityEntity.getParentCode();
            CompanyAreaEntity provinceEntity = companyAreaManager.findAreaByCode(cityParentCode);
            if (provinceEntity == null) {
                throw new RemoveStackBusinessException("该市所在省未启用:" + entity.getCityCode() + entity.getCityName());
            }
            entity.setProvinceCode(provinceEntity.getAreaCode());
            entity.setProvinceName(provinceEntity.getName());
            //处理省end
        }


        DeptEntity deptObj = deptManager.createTeam(request.getEntity());

        response.setResult(deptObj);

        return this.success(response);
    }

    @RequestMapping(path = "/activationTeam",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> activationTeam(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();
        String activationCode = request.getActivationCode();
        ToolUtil.validObjectNotNull(activationCode, "激活码不能为空");

        Map<String, String> map = activationCodeDbDao.getActivationInfoByActivationCode(activationCode);

        ToolUtil.validObjectNotNull(map, "激活码不存在，或者已经过期！请通知邀请人重新申请激活码");

        String userCode = map.get("userCode");
        String userName = map.get("userName");
        String ownerDeptCode = map.get("ownerDeptCode");
        String ownerDeptName = map.get("ownerDeptName");
        DeptEntity teamEntity = deptManager
                .findOne("leaderCode", CurrentContext.getUserCode());

        ToolUtil.validObjectNotNull(teamEntity, "当前登录人没有申请创建团队");
        if (teamEntity.getPayStatus().equals(PayStatus.FINISH)) {
            throw new RemoveStackBusinessException("已经激活的团队不需要重复激活");
        }

        teamEntity.setPayStatus(PayStatus.FINISH);
        teamEntity.setInviter(userName);
        teamEntity.setInviterCode(userCode);
        UserEntity userEntity = (UserEntity) CurrentContext.getUserInfo();
        userEntity.setUserLevel(UserLevel.T0);
        userEntity.setOwnerDeptCode(teamEntity.getDeptCode());
        userEntity.setOwnerDeptName(teamEntity.getDeptName());
        userEntity.setLastUpdateTime(new Date());
        userManager.update(userEntity);

        if (deptManager.update(teamEntity) > 0 && userManager.update(userEntity) > 0) {
            activationCodeDbDao.cleanActivationCode(map);
            if (activationCodeDbDao.getActivationCodeByUserCode(map.get("userCode")) == null
                    && activationCodeDbDao.getActivationInfoByActivationCode(map.get("activationCode")) == null) {
                response.setResult(PayStatus.FINISH);
                return this.success(response, "激活成功");

            } else {
                response.setResult(PayStatus.Begin);
                throw new RemoveStackBusinessException("激活失败");
            }
        } else {
            response.setResult(PayStatus.Begin);
            throw new RemoveStackBusinessException("激活失败");
        }
    }


    @RequestMapping(path = "/queryTeam",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryTeam(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", request.getKeyword());
        map.put("teamFlag", true);
        PageResult<DeptEntity> result = deptManager.findByMap(map, request.getStart(), request.getPageSize());

        ToolUtil.validObjectNotNull(result, "操纵异常了，请联系管理员");


        response.setResult(result);

        return this.success(response);
    }

    @RequestMapping(path = "/nearby",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> nearby(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        Map<String, Object> deptmap = new HashMap<>();
        deptmap.put("deptCode", userInfo.getOwnerDeptCode());
        DeptEntity deptEntity = deptManager.findOne(deptmap);
        List<DeptAreaEntity> deptAreas = deptEntity.getDeptAreas();
        List<DeptEntity> totalDeptList = new ArrayList<DeptEntity>();
        Map<String, Object> map = new HashMap<>();

        for (int i = 0; i < deptAreas.size(); i++) {
            map.clear();
            List<DeptEntity> tempList = new ArrayList<DeptEntity>();
            map.put("cityCode", deptAreas.get(i).getCityCode());
            map.put("isDeleted", false);
            tempList = deptManager.findNearby(map);
            if (tempList != null) {
                totalDeptList.addAll(tempList);
            }


        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("applyDeptCode", userInfo.getOwnerDeptCode());
        param.put("isDeleted", false);
        PageResult<SharePoolApplyEntity> byMap = sharePoolApplyManager.findByMap(param, 0, 8000, "create_time", false);
        List<SharePoolApplyEntity> spData = byMap.getData();
        //遍历删除,除去本身
        Iterator<DeptEntity> iterator = totalDeptList.iterator();
        while (iterator.hasNext()) {
            DeptEntity entity = iterator.next();
            //移除自己本身
            if (entity.getDeptCode().equals(userInfo.getOwnerDeptCode())) {
                iterator.remove();
            } else {
                entity.setShareBtnFlag(true);
                //加按钮标志
                if (spData != null) {
                    for (SharePoolApplyEntity spDatum : spData) {
                        if (entity.getDeptCode().equals(spDatum.getReceiveDeptCode())) {
                            entity.setShareBtnFlag(false);
                            entity.setApplyState(spDatum.getApplyState());
                            entity.setApplyStateName(spDatum.getApplyStateName());
                        }

                    }
                }
            }

        }


        //测试,加重复数据totalDeptList.addAll(totalDeptList);
        //去重
        List<DeptEntity> rdList = totalDeptList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(f -> f.getDeptCode()))), ArrayList::new));

        response.setResult(rdList);

        return this.success(response);
    }

    @RequestMapping(path = "/detailTeam",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detailTeam(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();
        Map<String, Object> map = new HashMap<>();
        if (request.getEntity().getDeptCode() == null) {
            throw new RemoveStackBusinessException("团队(部门)编码deptCode不能为空");
        }
        map.put("deptCode", request.getEntity().getDeptCode());
        DeptEntity result = deptManager.findOne(map);

        ToolUtil.validObjectNotNull(result, "未查到该部门团队");

        response.setResult(result);

        return this.success(response);
    }

    @RequestMapping(path = "/shareFlag",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> shareFlag(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();
        DeptEntity deptObj = new DeptEntity();
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        ToolUtil.validObjectNotNull(userInfo, "登陆失效,重新登陆");

        DeptEntity oldObj = deptManager.findOne("deptCode", request.getEntity().getDeptCode());

        ToolUtil.validObjectNotNull(oldObj, "未查询到该部门编码");
        ToolUtil.validObjectNotNull(request.getEntity().getDeptCode(), "部门编码不能为空");


        if (!userInfo.getUserCode().equals(oldObj.getLeaderCode())) {
            throw new RemoveStackBusinessException("只有部门经理可以操作!");
        }
        deptObj.setDeptCode(request.getEntity().getDeptCode());
        deptObj.setId(oldObj.getId());
        String msg = "未更新";
        if (request.getEntity().getHasShareCus() == null && request.getEntity().getHasShareHos() == null) {
            throw new RemoveStackBusinessException("HasShareCus 和 HasShareHos 不能同时为空.");
        }

        if (request.getEntity().getHasShareCus() != null) {
            deptObj.setHasShareCus(request.getEntity().getHasShareCus());
            int i = deptManager.update(deptObj);
            if (request.getEntity().getHasShareCus() == true) {
                msg = "已打开共享客户";
            }
            if (request.getEntity().getHasShareCus() == false) {
                msg = "已关闭共享客户";
            }
        } else if (request.getEntity().getHasShareHos() != null) {
            deptObj.setHasShareHos(request.getEntity().getHasShareHos());
            int i = deptManager.update(deptObj);
            if (request.getEntity().getHasShareHos() == true) {
                msg = "已打开共享房源";
            }
            if (request.getEntity().getHasShareHos() == false) {
                msg = "已关闭共享房源";
            }
        }


        response.setResult(msg);

        return this.success(response);
    }

    @RequestMapping(path = "/limitHidenFlag",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> limitHidenFlag(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();
        BaseUserEntity userInfo = CurrentContext.getUserInfo();

        ToolUtil.validObjectNotNull(userInfo, "登陆失效,重新登陆");

        DeptEntity deptObj = new DeptEntity();
        DeptEntity oldObj = deptManager.findOne("deptCode", request.getEntity().getDeptCode());

        ToolUtil.validObjectNotNull(oldObj, "未查询到该部门编码");

        ToolUtil.validObjectNotNull(request.getEntity().getDeptCode(), "部门编码不能为空");

        deptObj.setDeptCode(request.getEntity().getDeptCode());
        deptObj.setId(oldObj.getId());


        if (!userInfo.getUserCode().equals(oldObj.getLeaderCode())) {
            throw new RemoveStackBusinessException("只有部门经理可以操作!");
        }

        String msg = "未更新";
        if (request.getEntity().getHasLimitHiden() == null) {
            throw new RemoveStackBusinessException("HasLimitHiden 不能为空.");
        }

        deptObj.setHasLimitHiden(request.getEntity().getHasLimitHiden());
        int i = deptManager.update(deptObj);
        if (request.getEntity().getHasLimitHiden() == true) {
            msg = "已打开限制隐藏";
        }
        if (request.getEntity().getHasLimitHiden() == false) {
            msg = "已关闭限制隐藏";
        }


        response.setResult(msg);

        return this.success(response);
    }

    @RequestMapping(path = "/findAllDept",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> findAllDept(@RequestBody DeptServiceRequest request) throws IllegalAccessException {
        DeptServiceResponse response = new DeptServiceResponse();
        Map<String, Object> map = new TreeMap<String, Object>();
        Map param = new HashMap();
        param.put("keyword", request.getKeyword());
        param.put("isDeleted", Boolean.FALSE);
        List<DeptEntity> result = deptManager.findAllDeptByMap(param);

        List<DeptEntity> A = new ArrayList();
        List<DeptEntity> B = new ArrayList();
        List<DeptEntity> C = new ArrayList();
        List<DeptEntity> D = new ArrayList();
        List<DeptEntity> E = new ArrayList();
        List<DeptEntity> F = new ArrayList();
        List<DeptEntity> G = new ArrayList();
        List<DeptEntity> H = new ArrayList();
        List<DeptEntity> I = new ArrayList();
        List<DeptEntity> J = new ArrayList();
        List<DeptEntity> K = new ArrayList();
        List<DeptEntity> L = new ArrayList();
        List<DeptEntity> M = new ArrayList();
        List<DeptEntity> N = new ArrayList();
        List<DeptEntity> O = new ArrayList();
        List<DeptEntity> P = new ArrayList();
        List<DeptEntity> Q = new ArrayList();
        List<DeptEntity> R = new ArrayList();
        List<DeptEntity> S = new ArrayList();
        List<DeptEntity> T = new ArrayList();
        List<DeptEntity> U = new ArrayList();
        List<DeptEntity> V = new ArrayList();
        List<DeptEntity> W = new ArrayList();
        List<DeptEntity> X = new ArrayList();
        List<DeptEntity> Y = new ArrayList();
        List<DeptEntity> Z = new ArrayList();
        if (result != null) {
            for (int i = 0; i < result.size(); i++) {
                if ("a".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    A.add(result.get(i));
                } else if ("b".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    B.add(result.get(i));
                } else if ("c".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    if (!("CEO").equals(result.get(i).getDeptName())) {
                        C.add(result.get(i));
                    }
                } else if ("d".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    D.add(result.get(i));
                } else if ("e".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    E.add(result.get(i));
                } else if ("f".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    F.add(result.get(i));
                } else if ("g".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    G.add(result.get(i));
                } else if ("h".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    H.add(result.get(i));
                } else if ("i".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    I.add(result.get(i));
                } else if ("j".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    J.add(result.get(i));
                } else if ("k".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    K.add(result.get(i));
                } else if ("l".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    L.add(result.get(i));
                } else if ("m".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    M.add(result.get(i));
                } else if ("n".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    N.add(result.get(i));
                } else if ("o".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    O.add(result.get(i));
                } else if ("p".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    P.add(result.get(i));
                } else if ("q".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    Q.add(result.get(i));
                } else if ("r".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    R.add(result.get(i));
                } else if ("s".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    S.add(result.get(i));
                } else if ("t".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    T.add(result.get(i));
                } else if ("u".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    U.add(result.get(i));
                } else if ("v".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    V.add(result.get(i));
                } else if ("w".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    W.add(result.get(i));
                } else if ("x".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    X.add(result.get(i));
                } else if ("y".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    Y.add(result.get(i));
                } else if ("z".equalsIgnoreCase(ToolUtil.getFirstLetter(result.get(i).getDeptName()))) {
                    Z.add(result.get(i));
                }
            }
        }
        map.put("A", A);
        map.put("B", B);
        map.put("C", C);
        map.put("D", D);
        map.put("E", E);
        map.put("F", F);
        map.put("G", G);
        map.put("H", H);
        map.put("I", I);
        map.put("J", J);
        map.put("K", K);
        map.put("L", L);
        map.put("M", M);
        map.put("N", N);
        map.put("O", O);
        map.put("P", P);
        map.put("Q", Q);
        map.put("R", R);
        map.put("S", S);
        map.put("T", T);
        map.put("U", U);
        map.put("V", V);
        map.put("W", W);
        map.put("X", X);
        map.put("Y", Y);
        map.put("Z", Z);
        response.setResult(map);

        return this.success(response);
    }


}

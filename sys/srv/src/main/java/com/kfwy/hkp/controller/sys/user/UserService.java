package com.kfwy.hkp.controller.sys.user;

import com.gniuu.framework.common.auth.Token;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CookieHelper;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.base.ActivationCodeDbDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.utils.ToolUtil;
import com.kfwy.hkp.controller.sys.user.vo.UserDto;
import com.kfwy.hkp.controller.sys.user.vo.UserServiceRequest;
import com.kfwy.hkp.controller.sys.user.vo.UserServiceResponse;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptAreaEntity;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.hrm.org.dept.enums.PayStatus;
import com.kfwy.hkp.quit.entity.QuitEntity;
import com.kfwy.hkp.quit.manager.ITeamDataTransferManager;
import com.kfwy.hkp.sys.auth.business.IMenuManager;
import com.kfwy.hkp.sys.auth.business.IRoleManager;
import com.kfwy.hkp.sys.auth.entity.MenuEntity;
import com.kfwy.hkp.sys.user.api.SSOApiClient;
import com.kfwy.hkp.sys.user.business.IUserApplyTeamManager;
import com.kfwy.hkp.sys.user.business.IUserDataManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserApplyTeamEntity;
import com.kfwy.hkp.sys.user.entity.UserAreaEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserHasPass;
import com.kfwy.hkp.sys.user.enums.ApplyCategory;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import com.kfwy.hkp.sys.user.enums.UserType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by davidcun on 2018/5/17.
 * 登录用户信息
 */
@Controller
@Api(value = "用户管理", description = "用户管理")
@RequestMapping("/user")
public class UserService extends SpringRestService {

    @Autowired
    private IUserManager userManager;
    @Autowired
    private IUserDataManager userDataManager;

    @Autowired
    private IMenuManager menuManager;

    @Autowired
    private IUserDbDao userDbDao;

    @Autowired
    private IUserApplyTeamManager userApplyTeamManager;

    @Autowired
    private ITeamDataTransferManager teamDataTransferManager;
    @Autowired
    private IDeptManager deptManager;
    /**
     * 用户登录
     */
    @Autowired
    private IRoleManager roleManager;

    @Autowired
    private ActivationCodeDbDao activationCodeDbDao;

    @RequestMapping(path = "/ssoLogin",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse>  ssoClient(@RequestBody UserServiceRequest request,HttpServletRequest req, HttpServletResponse resp) {

        AbstractServiceResponse response = new AbstractServiceResponse();

        String userCode = SSOApiClient.sso(request.getSsoKey());

        if (StringUtils.isNotEmpty(userCode)) {
            UserEntity user = userManager.findUserByUserCode(userCode);
            if (user != null) {
                loginSuccess(user,response,req,resp);
            }
        }

        return this.success(response);

    }

    @ApiOperation(value = "用户登录", notes = "用户登录")
    @RequestMapping(path = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> login(@RequestBody UserServiceRequest request,
                                                  HttpServletRequest req, HttpServletResponse resp) {

        AbstractServiceResponse response = new AbstractServiceResponse();

        UserEntity user = userManager.login(request.getEntity());

        if (user != null) {
            loginSuccess(user,response,req,resp);
        }
        return this.success(response);
    }

    private Token loginSuccess(UserEntity user,AbstractServiceResponse response, HttpServletRequest req, HttpServletResponse resp) {
        //1、创建token
        Token token = userManager.generateToken(user.getUserCode());
        //2、把token写入cookie
        CookieHelper.addUserLoginTokenToCookie(req, resp, token);
        //3、保存authKey与userCode关系
        userManager.saveAuthKey(token.getAuthKey(), token.getUserCode());

        //判断权限树集合
        List<MenuEntity> judgeMenuTreeList = null;
        //菜单树
        List<MenuEntity> menuTreeList = null;
        //若是有岗位编码，则先要通过岗位去找绑定的角色
        judgeMenuTreeList = menuManager.getUserJudgeMenuList(user.getEmpPostCode(), user.getUserCode());
        //开始时间
        long starts = System.currentTimeMillis();
        menuTreeList = menuManager.findMenuToTreeByPostCodeAndUserCode(user.getEmpPostCode(),user.getUserCode());
        long costTime = System.currentTimeMillis() - starts;
        System.out.println("cost "+costTime+"ms");
        Map resultMap = putResultMap(user, token, judgeMenuTreeList, menuTreeList);
        //这里还得把登录用户的可查看菜单树集合put进去
        response.setResult(resultMap);

        return token;
    }

    private Map putResultMap(UserEntity user, Token token, List<MenuEntity> judgeMenuTreeList, List<MenuEntity> menuTreeList) {
        Map resultMap = new HashMap(16);
        resultMap.put("token", token.toBase64String());
        resultMap.put("userName", user.getUserName());
        resultMap.put("userCode", user.getUserCode());
        resultMap.put("userLevel", user.getUserLevel());
        resultMap.put ("entryType",user.getEnterType ());
        if (user.getUserLevel().equals(UserLevel.K0)) {
            resultMap.put("ownerDeptName", user.getOwnerDeptName());
        } else if (user.getUserLevel().equals(UserLevel.K1)) {
            resultMap.put("ownerDeptName", "加盟经纪人");
        } else if (user.getUserLevel().equals(UserLevel.K2)) {
            resultMap.put("ownerDeptName", "独立经纪人");
        }
        resultMap.put("menuTreeList", menuTreeList);
        resultMap.put("judgeMenuTreeList", judgeMenuTreeList);
        return resultMap;
    }

    /**
     * 用户退出
     *
     * @param req
     * @param resp
     * @return
     */
    @ApiOperation(value = "用户退出", notes = "用户退出")
    @RequestMapping(path = "/logout", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> logout(HttpServletRequest req, HttpServletResponse resp) {

        AbstractServiceResponse response = new AbstractServiceResponse();

        userManager.logout(CurrentContext.getToken());

        CookieHelper.invalidateUserLoginTokenCookie(req, resp);


        return this.success(response);


    }


    /**
     * 查询用户列表
     *
     * @param request
     * @return
     * @throws IllegalAccessException
     */
    @ApiOperation(value = "用户列表查询", notes = "用户列表查询")
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> query(@RequestBody UserServiceRequest request) throws IllegalAccessException {

        UserServiceResponse response = new UserServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        UserEntity user=request.getEntity();
        param.put("keyword", request.getKeyword());
        param.put("userType", "Individual");
        if(StringUtils.isNotEmpty(user.getHasPass())){
            param.put("hasPass", user.getHasPass());
        }
        PageResult<UserEntity> result = userManager.selectUserBasisInfoByMap(param, request.getStart(), request.getPageSize());
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 查询用户详情
     * 带部门的地址的
     * @param request
     * @return
     */
    @ApiOperation(value = "查询用户详情", notes = "查询用户详情")
    @RequestMapping(path = "/detail",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody UserServiceRequest request) {
        UserServiceResponse response = new UserServiceResponse();
        UserEntity uEntity = userDataManager.findOne("userCode", request.getEntity().getUserCode());

        UserDto pd = new UserDto(uEntity);

        pd.setRoles(roleManager.findRoleListByUser(pd.getUserCode()));
        DeptEntity deptEntity = deptManager.findOne("deptCode",pd.getOwnerDeptCode());
        List<DeptAreaEntity> deptAreaEntities = new ArrayList<>(1);
        DeptAreaEntity deptAreaEntity = new DeptAreaEntity();
        deptAreaEntity.setStreetName(deptEntity.getDeptAddress());
        deptAreaEntities.add(deptAreaEntity);
        pd.setDeptAreas(deptAreaEntities);
        response.setResult(pd);

        return this.success(response);
    }

    /**
     * @param request
     * @return
     * @Auth liwensihan
     */
    @ApiOperation(value = "查询单个用户", notes = "查询单个用户")
    @RequestMapping(path = "/queryOne",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryOne(@RequestBody UserServiceRequest request) {
        UserServiceResponse response = new UserServiceResponse();

        UserEntity uEntity = userDataManager.findOne("userCode", request.getEntity().getUserCode());

        UserDto pd = new UserDto(uEntity);

        pd.setRoles(roleManager.findRoleListByUser(pd.getUserCode()));

        response.setResult(pd);

        return this.success(response);
    }

    /**
     * @return
     * @Description 修改密码
     * @Auth liwensihan
     * @Date 2018/8/28 15:13
     * @Version 1.0
     * @Param
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody UserServiceRequest request) {

        UserServiceResponse response = new UserServiceResponse();
        UserEntity uEntity = request.getEntity();

        //查询当前登陆用户的信息
        UserEntity userEntity = userDataManager.queryCurrentUser();
        String pwd = userEntity.getPassword();

        //与前端旧密码对比
        if (pwd.equals(uEntity.getPassword())) {
            UserEntity ue = new UserEntity();
            ue.setPassword(request.getNewPwd());
            ue.setId(userEntity.getId());
            ue.setUserCode(userEntity.getUserCode());
            userDataManager.update(ue);
        } else {
            throw new BusinessException("原密码错误!");
        }

        return this.success(response);
    }


    @ApiOperation(value = "更新用户角色", notes = "更新用户角色")
    @RequestMapping(path = "/updateUserRoleList",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> updateUserRoleList(@RequestBody UserServiceRequest request) throws IllegalAccessException {

        UserServiceResponse response = new UserServiceResponse();

        userDataManager.updateUserRoleList(request.getEntity(), request.getRoleCodes());

        return this.success(response);
    }


    /**
     * 独立经纪人注册
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/registered", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> registered(@RequestBody UserServiceRequest request) {

        UserServiceResponse response = new UserServiceResponse();
        UserEntity user = request.getEntity();
        if (StringUtils.isEmpty(user.getUserPhone())) {
            throw new BusinessException("请输入手机号");
        }
        //判断是否已经注册
        List<UserEntity> userEntities = userDbDao.selectByNativeSql("select * from t_hkp_sys_user_user" +
                " where user_phone=\'" + user.getUserPhone() + "\'");
        if (null != userEntities && userEntities.size() > 0) {
            throw new BusinessException("该用户已注册，请直接登陆");
        }


        if (StringUtils.isEmpty(user.getUserName())) {
            throw new RemoveStackBusinessException ("请输入用户名");
        }
        if (StringUtils.isEmpty(user.getSex())) {
            throw new RemoveStackBusinessException("请选择用户性别");
        }
        if (StringUtils.isEmpty(user.getCard())) {
            throw new RemoveStackBusinessException("请输入身份证号");
        }
        if (StringUtils.isEmpty(user.getUserImg())) {
            throw new RemoveStackBusinessException("请输入头像");
        }
        if (StringUtils.isEmpty(user.getCardImageName())) {
            throw new RemoveStackBusinessException("请输入身份证正面");
        }

        if (user.getUserArea() == null || user.getUserArea().size() <= 0) {
            throw new BusinessException("请选择业务区域");
        }
        //业务区域 转换一下
        List<UserAreaEntity> userAreaEntities = getUserArea(user.getUserArea());
        user.setUserAreas(userAreaEntities);

        //创建独立经纪人 密码固定为12345678
        user.setPassword("25D55AD283AA400AF464C76D713C07AD");
        user.setSecure("12345678");

        //创建账号，绑定设备号,token
        user = userManager.registered(user);
        if (null == user) {
            throw new BusinessException("未选择负责区域，注册失败");
        }

        user.setPassword(null);
        user.setSecure(null);
        response.setResult(user);
        return success(response);


    }

    /**
     * 根据独立经纪人code 查出独立经纪人信息
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/getUser", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getUser(@RequestBody UserServiceRequest request) {

        UserServiceResponse response = new UserServiceResponse();
        //独立经纪人code
        String userCode = request.getEntity().getUserCode();
        if (StringUtils.isEmpty(userCode)) {
            throw new BusinessException("用户code为空");
        }

        UserEntity userEntity = this.userManager.getUserEntity(userCode);
        List<UserAreaEntity> list = userEntity.getUserAreas();
        userEntity.setUserArea(toGetUserArea(list));
        response.setResult(userEntity);
        return success(response);


    }

    /**
     * 根据用户编码修改独立经纪人信息
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/editUser", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> editUser(@RequestBody UserServiceRequest request) {

        UserServiceResponse response = new UserServiceResponse();


        //            UserEntity user = userManager.findUserByPhone(request.getEntity().getUserPhone());
        UserEntity user = request.getEntity();
        if (StringUtils.isEmpty(user.getUserName())) {
            throw new BusinessException("请输入用户名");
        }
        if (StringUtils.isEmpty(user.getSex())) {
            throw new BusinessException("请选择用户性别");
        }
        if (StringUtils.isEmpty(user.getCard())) {
            throw new BusinessException("请输入身份证号");
        }
        if (StringUtils.isEmpty(user.getUserImg())) {
            throw new BusinessException("请输入头像");
        }
        if (StringUtils.isEmpty(user.getCardImageName())) {
            throw new BusinessException("请输入身份证正面");
        }

        if (user.getUserArea() == null || user.getUserArea().size() <= 0) {
            throw new BusinessException("请选择业务区域");
        }
        //业务区域 转换一下
        List<UserAreaEntity> userAreaEntities = getUserArea(user.getUserArea());
        user.setUserAreas(userAreaEntities);

        this.userManager.editUser(user);

        return success(response);
    }


    @RequestMapping(path = "/registeredExamine", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> registeredExamine(@RequestBody UserServiceRequest request){
        UserServiceResponse response = new UserServiceResponse();
        UserEntity user=request.getEntity();
        if (StringUtils.isEmpty(user.getUserName())){
            throw new BusinessException("请输入用户名");
        }
        if (StringUtils.isEmpty(user.getSex())){
            throw new BusinessException("请选择用户性别");
        }
        if (StringUtils.isEmpty(user.getCard())){
            throw new BusinessException("请输入身份证号");
        }
        if (StringUtils.isEmpty(user.getUserImg())){
            throw new BusinessException("请输入头像");
        }
        if (StringUtils.isEmpty(user.getCardImageName())){
            throw new BusinessException("请输入身份证正面");
        }
        if(user.getUserArea()==null || user.getUserArea().size()<=0){
            throw new BusinessException("请选择业务区域");
        }
        //业务区域 转换一下
        List<UserAreaEntity> userAreaEntities = getUserArea(user.getUserArea());
        user.setUserAreas(userAreaEntities);
        user.setHasPass(UserHasPass.pass);
        this.userManager.editUser(user);
        return success(response);
    }

    @RequestMapping(path = "/mailLst", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> mailList(@RequestBody UserServiceRequest request){
        UserServiceResponse response = new UserServiceResponse();
        UserEntity user=request.getEntity();
        List<UserAreaEntity> userAreaEntities = getUserArea(user.getUserArea());
        response.setResult(userAreaEntities);
        return success(response);
    }

    /**
     * 获取省市区areaCode 一开始areaCode是code+name 这里主要是是分开
     * 第一个值是code  第二个值是name
     *
     * @param areaCode
     * @return
     */
    private String[] getAreaCode(String areaCode) {

        String[] areaCodeName = areaCode.split("\\,");//将name与code分开
        return areaCodeName;
    }

    /**
     * 业务区域 转换一下
     * 这个方法是奖合起来 返回code+name 传给web端
     *
     * @param areaList
     * @return
     */
    private List<String> toGetUserArea(List<UserAreaEntity> areaList) {
        List<String> list = new ArrayList<>();

        UserAreaEntity userAreaEntity = areaList.get(0);//业务区域 一般只有一个
        list.add(userAreaEntity.getProvinceCode() + "," + userAreaEntity.getProvinceName());
        list.add(userAreaEntity.getCityCode() + "," + userAreaEntity.getCityName());
        list.add(userAreaEntity.getRegionCode() + "," + userAreaEntity.getRegionName());
        list.add(userAreaEntity.getStreetCode() + "," + userAreaEntity.getStreetName());
        return list;
    }

    /**
     * 业务区域 转换一下
     * 比如 web端 传过来的是省编码,省name这个方法是奖罚分开 插入UserAreaEntity对象
     *
     * @param area
     * @return
     */
    private List<UserAreaEntity> getUserArea(List<String> area) {

        List<UserAreaEntity> list = new ArrayList<>();
        String[] province = getAreaCode(area.get(0));
        String[] city = getAreaCode(area.get(1));
        String[] region = getAreaCode(area.get(2));

        UserAreaEntity userAreaEntity = new UserAreaEntity();
        userAreaEntity.setProvinceCode(province[0]);
        userAreaEntity.setProvinceName(province[1]);
        userAreaEntity.setCityCode(city[0]);
        userAreaEntity.setCityName(city[1]);
        userAreaEntity.setRegionCode(region[0]);
        userAreaEntity.setRegionName(region[1]);
        list.add(userAreaEntity);
        return list;
    }

    /**
     * @return
     * @Description 经理把用户移出团队
     * @Auth xpp
     * @Date 2018/8/28 15:13
     * @Version 1.0
     * @Param
     */
    @ApiOperation(value = "把用户移出团队", notes = "把用户移出团队")
    @RequestMapping(path = "/updateMoveOut",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> updateMoveOut(@RequestBody UserServiceRequest request) {

        UserServiceResponse response = new UserServiceResponse();
        UserEntity uEntity = request.getEntity();
        if(uEntity.getUserCode()==null){throw new BusinessException("用户code必传!");}

        UserEntity ueDB = new UserEntity();
        HashMap<String, Object> param = new HashMap<>();
        param.put("userCode",uEntity.getUserCode());
        ueDB = userDataManager.findOne(param);
        if(ueDB==null){throw new BusinessException("未查到该移出成员!");}
        if(!ueDB.getUserType().equals(UserType.Individual) ){throw new BusinessException("移出功能只用于外部独立经纪人");}


            UserEntity ue = new UserEntity();
            ue.setOwnerDeptCode(ueDB.getOwnerDeptCode()+"移出out");
            ue.setOwnerDeptName(ueDB.getOwnerDeptName()+"移出out");
            ue.setId(ueDB.getId());
            ue.setUserCode(ueDB.getUserCode());
            ue.setUserType(UserType.Individual);
            userDataManager.update(ue);

        return this.success(response);
    }

    /**
     * @return
     * @Description 同意离开申请
     * @Auth xpp
     * @Date 2018/8/28 15:13
     * @Version 1.0
     * @Param
     */
    @ApiOperation(value = "同意离开申请", notes = "同意离开申请")
    @RequestMapping(path = "/agreeLeaveApply",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> agreeLeaveApply(@RequestBody UserServiceRequest request) {

        UserServiceResponse response = new UserServiceResponse();
        //校验
        if (request.getApplyRecordCode() == null) {
            throw new BusinessException("接口 agreeLeaveApply 的字段 applyRecordCode 必传!");
        }

        UserEntity uEntity = request.getEntity();
        if(uEntity.getUserCode()==null){throw new BusinessException("用户code必传!");}

        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        if (userInfo == null) {
            throw new BusinessException("用户信息为空,请重新登陆." + userInfo.getUserCode());
        }

        UserEntity ueDB = new UserEntity();
        HashMap<String, Object> param = new HashMap<>();
        param.put("userCode",uEntity.getUserCode());
        ueDB = userDataManager.findOne(param);
        if(ueDB==null){throw new BusinessException("未查到该移出成员!");}
        if(!ueDB.getUserType().equals(UserType.Individual) ){throw new BusinessException("移出功能只用于外部独立经纪人");}
        if(ueDB.getOwnerDeptCode()==null){
            throw new BusinessException("该成员无所属团队." + ueDB.getOwnerDeptCode());
        }
        DeptEntity deptEntity = deptManager.getDeptEntity(ueDB.getOwnerDeptCode());

        if(deptEntity == null){
            throw new BusinessException("未查询到成员部门." + ueDB.getOwnerDeptCode());
        }
        String loginUserCode = userInfo.getUserCode();
        String leaderCode = deptEntity.getLeaderCode();
        if(!loginUserCode.equals(leaderCode)){
            throw new BusinessException("您不是该离开成员团队经理,不能执行同意按钮操作!");
        }


        //对userApplyTeam申请表操作状态start
        HashMap<String, Object> map = new HashMap<>();
        map.put("applyRecordCode",request.getApplyRecordCode());
        map.put("itemType","leave");
        map.put("isDeleted",false);
        UserApplyTeamEntity byMap = userApplyTeamManager.findOne(map);
        if(byMap==null){throw new BusinessException("未查到该条申请");}
        byMap.setApplyState(ApplyCategory.AGREE);
        byMap.setLastUpdateTime(new Date());
        userApplyTeamManager.update(byMap);
        //对userApplyTeam申请表操作状态end

        //离开申请对user表操作 start
        UserEntity ue = new UserEntity();
        ue.setOwnerDeptCode(ueDB.getOwnerDeptCode()+"申请离开out");
        ue.setOwnerDeptName(ueDB.getOwnerDeptName()+"申请离开out");
        ue.setId(ueDB.getId());
        ue.setUserCode(ueDB.getUserCode());
        ue.setUserType(UserType.Individual);
        userDataManager.update(ue);
        //离开申请对user表操作 end



        //离开申请转移数据 start
        QuitEntity quitEntity = new QuitEntity();
        quitEntity.setHeaderCode(userInfo.getUserCode()); //当前操作人
        quitEntity.setCreateCode(ueDB.getUserCode());     //转移人code
        quitEntity.setCreateName(ueDB.getUserName());     //转移人name

        //以下为接收人
        quitEntity.setCusDataTransferCode(userInfo.getUserCode());
        quitEntity.setCusDataTransfer(userInfo.getUserName());
        quitEntity.setPartTimeDataTransferCode(userInfo.getUserCode());
        quitEntity.setPartTimeDataTransfer(userInfo.getUserName());
        quitEntity.setWareHouseDataTransferCode(userInfo.getUserCode());
        quitEntity.setWareHouseDataTransfer(userInfo.getUserName());

        Integer i = teamDataTransferManager.transfer(quitEntity);

        //离开申请转移数据 end
        response.setResult("操作成功,同时转移了:"+i+"条数据,到"+userInfo.getUserName()+"名下");

        return this.success(response);
    }

    /**
     * 激活个人vip
     * @param request
     * @return
     */
    @RequestMapping(path = "/activationVip",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> activationVip(@RequestBody UserServiceRequest request){

        UserServiceResponse response = new UserServiceResponse();
        String activationCode = request.getActivationCode();
        ToolUtil.validObjectNotNull(activationCode,"激活码不能为空");

        Map<String, String> map = activationCodeDbDao.getActivationInfoByActivationCode(activationCode);

        ToolUtil.validObjectNotNull(map,"激活码不存在，或者已经过期！请通知邀请人重新申请激活码");

        String userCode = map.get("userCode");
        String userName = map.get("userName");
        String ownerDeptCode = map.get("ownerDeptCode");
        String ownerDeptName = map.get("ownerDeptName");

        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        if (user.getPayStatus().equals(PayStatus.FINISH)){
            throw new BusinessException("已经激活的vip不需要重复激活");
        }
        user.setUserLevel(UserLevel.K1);
        user.setPayStatus(PayStatus.FINISH);
        user.setInviterCode(userCode);
        user.setInviter(userName);

        if (userManager.update(user)>0) {
            activationCodeDbDao.cleanActivationCode(map);
            if (activationCodeDbDao.getActivationCodeByUserCode(map.get("userCode"))==null
                    && activationCodeDbDao.getActivationInfoByActivationCode(map.get("activationCode"))==null){
                response.setResult(PayStatus.FINISH);
                return this.success(response,"激活成功");

            }else{
                response.setResult(PayStatus.Begin);
                throw new BusinessException("激活失败");
            }
        }else{
            response.setResult(PayStatus.Begin);
            throw new BusinessException("激活失败");
        }
    }



    /**
     * 查询所有用户集合
     *
     * @param request
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(path = "/queryAllUser",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> queryAllUser(@RequestBody UserServiceRequest request) throws IllegalAccessException {
        UserServiceResponse response = new UserServiceResponse();
        long startTime = System.currentTimeMillis();
        List<UserEntity> result = userManager.queryAllUser();
        long costTime = System.currentTimeMillis() - startTime;
        System.out.println("cost "+costTime+"ms");

        response.setResult(result);
        return this.success(response);
    }
}

package com.kfwy.hkp.controller.sys.user;

import cn.hutool.core.util.PinyinUtil;
import com.gniuu.framework.common.auth.Token;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CookieHelper;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.hkp.common.annotion.IgnoreLog;
import com.kfwy.hkp.common.sms.SmsEntity;
import com.kfwy.hkp.common.sms.redis.ISmsRedisDao;
import com.kfwy.hkp.common.sms.send.ISmsTemplate;
import com.kfwy.hkp.common.utils.*;
import com.kfwy.hkp.controller.sys.user.vo.AppUserServiceRequest;
import com.kfwy.hkp.controller.sys.user.vo.AppUserServiceResponse;
import com.kfwy.hkp.controller.sys.user.vo.UserServiceRequest;
import com.kfwy.hkp.controller.version.vo.AppServiceResponse;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.auth.business.IMenuManager;
import com.kfwy.hkp.sys.auth.entity.MenuEntity;
import com.kfwy.hkp.sys.file.business.IFileManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.entity.UserTeamVo;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import com.kfwy.hkp.sys.user.enums.UserType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zjp on 2018/8/7.
 * 登录用户信息
 */
@RestController
@RequestMapping("/appUser")
public class AppUserService extends SpringRestService {

    @Autowired
    private IUserManager userManager;
    @Autowired
    private ISmsRedisDao smsRedisDao;
    @Autowired(required = true)
    private ISmsTemplate smsTemplate;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IMenuManager menuManager;
    @Autowired
    private IFileManager fileManager;
    /**
     * 获取目标主机的ip
     *
     * @param request
     * @return
     */
    private String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return "0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip;
    }

    @IgnoreLog
    @RequestMapping(path = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> login(@RequestBody AppUserServiceRequest request) {
        AppUserServiceResponse response = new AppUserServiceResponse();
        UserEntity user = userManager.findUserByPhone(request.getUserPhone());
        if (ObjectUtils.isEmpty(user)) {
            throw new BusinessException(String.format("账号不存在{%s},请核实入职工作流或联系招聘人员",request.getUserPhone()));
        }

        //执行解绑操作
        Boolean isUnbind = userManager.doUnbind(request.getUnBindVerifyCode(), request.getUserPhone(), user);

        //校验设备号，更新设备号 如果当前登录人开通了needAuth或为独立经纪人，则不需要做设备号校验，
        validateIdentifyNumberOrUpdateIdentifyNumber(request, response, user, isUnbind);

        //登录操作
        user = login(user, isUnbind, request.getVerifyCode(), request.getUserPhone(), request.getSecure());



        if (user != null) {
            if (request.getDeviceToken()==null && request.getEntity().getDeviceToken()==null ){
                throw new BusinessException("友盟deviceToken传值为空!");
            }

            String deviceToken = request.getDeviceToken() == null ?request.getEntity().getDeviceToken() : request.getDeviceToken();
            setLoginUserInfo(response,user,deviceToken);
            return success(response);
        }
        throw new BusinessException("201", "账户或密码错误");
    }


    /**
     * 用户登录之后可以查询当前用户信息
     *
     * @return
     */
    @RequestMapping(path = "/userInfo", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody UserServiceRequest request) {

        AppServiceResponse response = new AppServiceResponse();

        if (request.getEntity() == null || StringUtils.isEmpty(request.getEntity().getUserCode())) {
            throw new BusinessException("用户编码必填");
        }

        UserEntity user = userManager.findUserByUserCode(request.getEntity().getUserCode());

        UserEntity tmp = new UserEntity();

        tmp.setUserCode(user.getUserCode());
        tmp.setUserName(user.getUserName());
        tmp.setUserPhone(user.getUserPhone());
        tmp.setOwnerDeptCode(user.getOwnerDeptCode());
        tmp.setOwnerDeptName(user.getOwnerDeptName());
        tmp.setUserImg(user.getUserImg());
        tmp.setUserType(user.getUserType());
        tmp.setHasManager(user.getHasManager());

        response.setResult(tmp);

        return this.success(response);

    }

    /**
     * 用户登录之后可以查询当前用户信息
     *
     * @return
     */
    @RequestMapping(path = "/detail", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail() {

        AppServiceResponse response = new AppServiceResponse();
        UserEntity user = (UserEntity) CurrentContext.getUserInfo();
        UserTeamVo userTeamVo= new UserTeamVo();
        BeanUtils.copyProperties(user,userTeamVo);
        Map map = new HashMap();
        map.put("leaderCode",user.getUserCode());
        map.put("isDeleted",Boolean.FALSE);

        DeptEntity teamEntity = deptManager
                .findOneDept(map);

        if (teamEntity!=null){
            userTeamVo.setDeptEntity(teamEntity);
        }else{
            userTeamVo.setDeptEntity(null);
        }
        response.setResult(userTeamVo);

        return this.success(response);

    }

    /**
     * 退出登录
     *
     * @param
     * * @param
     * @return
     */
    @RequestMapping(path = "/logout", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> logout() {

        AbstractServiceResponse response = new AbstractServiceResponse();

        userManager.logout(CurrentContext.getToken());

        return this.success(response);


    }

    /**
     * 修改密码
     *
     * @param request
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(path = "/remakePwd", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> remakePwd(@RequestBody AppUserServiceRequest request,
                                                      HttpServletRequest req, HttpServletResponse resp) {

        AbstractServiceResponse response = new AbstractServiceResponse();

        if (StringUtils.isEmpty(request.getVerifyCode()) ||
                StringUtils.isEmpty(request.getSecure()) ||
                StringUtils.isEmpty(request.getUserPhone())) {
            throw new BusinessException("手机号、验证码和密码不能为空");
        }

        if (!PhoneUtils.isMobile(request.getUserPhone())) {
            throw new BusinessException("请正确填写手机号");
        }

        // 验证码校验
        Map<String, String> map = smsRedisDao.getSmsCode(request.getUserPhone());
        if (map != null && map.get("verCode").equals(request.getVerifyCode())) {

            UserEntity user = userManager.findUserByPhone(request.getUserPhone());

            if (user == null) {
                throw new BusinessException(String.format("用户%s不存在!", request.getUserPhone()));
            }

            user.setSecure(request.getSecure());
            user.setPassword(MD5Utils.md5(request.getSecure(), MD5Utils.KEY));
            userManager.update(user);

        } else {
            throw new BusinessException("验证码错误");
        }
        return this.success(response);
    }

    /**
     * 验证码发送
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/verifyCode", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> sendVerifyCode(@RequestBody AppUserServiceRequest request) {

        AppUserServiceResponse response = new AppUserServiceResponse();

        // 判断手机号
        if (!PhoneUtils.isMobile(request.getUserPhone())) {
            return error(response, "201", "发送短信失败\n请正确填写手机号");
        }

        //随机码
        String code = SimpleSecurityCodeUtil.random(4) + "";
        //短信内容
        String[] smsStr = new String[]{"验证码", code};

        Map<String, String> redisMap = smsRedisDao.getSmsCode(request.getUserPhone());
        if (redisMap != null) {
            long lastTime = Long.valueOf(redisMap.get("lastTime"));
            long nowTime = System.currentTimeMillis();
            if (nowTime - lastTime < 55 * 1000) {
                return error(response, "201", "发送短信过于频繁,请您稍后再试");
            }
        }
        //code存入redis 20分钟过期
        smsRedisDao.setSmsCode(request.getUserPhone(), code);
        //发短信
        SmsEntity entity = new SmsEntity();
        entity.setTo(request.getUserPhone());
        entity.setTemplate(SmsEntity.smsVerTemplate);
        entity.setMessages(smsStr);
        Map<String, Object> map = smsTemplate.send(entity);
        if (map == null) {
            return error(response, "201", "发送短信失败\n请检查手机号码是否正确");
        }
        return success(response);
    }

    @RequestMapping(path = "/unbindPhone", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> unbindPhone(@RequestBody AppUserServiceRequest request) {
        AppUserServiceResponse response = new AppUserServiceResponse();
        if (StringUtils.isNotEmpty(request.getUserPhone())) {
            UserEntity userEntity = userManager.findUserByPhone(request.getUserPhone());
            if (ObjectUtils.isEmpty(userEntity)) {
                return error(response, "201", "解绑异常,获取不到用户数据");
            }
            Map map = new HashMap();
            map.put("userPhone", request.getUserPhone());
            map.put("userCode", userEntity.getUserCode());
            int isUnbind = userManager.updateIdentifyNumber(map);
            if (isUnbind > 0) {
                return success(response, "解绑成功");
            } else {
                return error(response, "解绑失败");
            }
        } else {
            return error(response, "201", "解绑手机的号码为空");
        }
    }



    /**
     * 发送解绑验证码，收到验证码后跳转登录接口
     *
     * @param request
     * @return
     */
    @RequestMapping(path = "/sendUnBindVerifyCode", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> sendUnBindVerifyCode(@RequestBody AppUserServiceRequest request) {

        AppUserServiceResponse response = new AppUserServiceResponse();
        // 判断手机号
        if (!PhoneUtils.isMobile(request.getUserPhone())) {
            return error(response, "201", "发送短信失败\n请正确填写手机号");
        }
        //随机码
        String code = SimpleSecurityCodeUtil.random(4) + "";
        //短信内容
        String[] smsStr = new String[]{"解绑验证码", code};

        Map<String, String> redisMap = smsRedisDao.getUnbindCode(request.getUserPhone());
        if (redisMap != null) {
            long lastTime = Long.valueOf(redisMap.get("lastTime"));
            long nowTime = System.currentTimeMillis();
            if (nowTime - lastTime < 55 * 1000) {
                return error(response, "201", "发送短信过于频繁,请您稍后再试");
            }
        }
        //code存入redis 20分钟过期
        smsRedisDao.setUnbindCode(request.getUserPhone(), code);
        //发短信
        SmsEntity entity = new SmsEntity();
        entity.setTo(request.getUserPhone());
        entity.setTemplate(SmsEntity.smsVerTemplate);
        entity.setMessages(smsStr);
        Map<String, Object> map = smsTemplate.send(entity);
        if (map == null) {
            return error(response, "201", "发送短信失败\n请检查手机号码是否正确");
        }
        return success(response);
    }



    /**
     * 校验设备号，更新设备号
     * 如果当前登录人开通了needAuth或为独立经纪人，则不需要做设备号校验
     *
     * @param request
     * @param response
     * @param user
     * @param isUnbind
     */
    public void validateIdentifyNumberOrUpdateIdentifyNumber(AppUserServiceRequest request, AppUserServiceResponse response, UserEntity user, Boolean isUnbind) {
        if (Boolean.TRUE.equals(user.getNeedAuth())) {
            if (!user.getUserType().equals(UserType.Individual)){
                if (StringUtils.isEmpty(request.getIdentifyNumber())) {
                    throw new BusinessException("登录的时候设备号必填");
                }
                //如果设备号为空或者设备解绑成功，则都更新设备号绑定为当前登录的手机
                if (StringUtils.isEmpty(user.getIdentifyNumber()) || isUnbind == true) {
                    user.setIdentifyNumber(request.getIdentifyNumber());
                    user.setDeviceBrand(request.getDeviceBrand());
                    //TODO 更新用户设备号
                    userManager.update(user);
                }
                if (!user.getIdentifyNumber().equals(request.getIdentifyNumber())) {
                    response.setDeviceBrand(user.getDeviceBrand());
                    throw new BusinessException("isBind", "您的设备已经与以下设备绑定!\n" + user.getDeviceBrand());
                }
            }
        }
    }

    /**
     * 不是解绑登录，则进入正常的登录流程
     * 有验证码优先进行验证码登录，否则进行账号密码登录
     *
     * @param user
     * @param isUnbind
     * @param verifyCode
     * @param userPhone
     * @param secure
     * @return
     */
    public UserEntity login(UserEntity user, Boolean isUnbind, String verifyCode, String userPhone, String secure) {
        //不是解绑登录，则进入正常的登录流程
        if (isUnbind == false) {
            if (StringUtils.isNotEmpty(verifyCode)) {
                // 验证码快速登陆
                Map<String, String> map = smsRedisDao.getSmsCode(userPhone);
                if (map != null && map.get("verCode").equals(verifyCode)) {
                    user = userManager.findUserByPhone(userPhone);
                } else {
                    throw new BusinessException("验证码错误");
                }
            } else {
                UserEntity userEntity = new UserEntity();
                userEntity.setUserPhone(userPhone);
                userEntity.setSecure(secure);
                user = userManager.login(userEntity);
            }
        }
        return user;
    }
    /**
     * 设置登录用户信息
     * 1、更新deviceToken {@link IUserManager#updateDeviceToken(UserEntity, String)}
     * 2、创建token {@link IUserManager#generateToken(String)}
     * 3、保存authKey与userCode关系 {@link IUserManager#saveAuthKey(String, String)}
     *  @param response
     * @param user
     * @param deviceToken
     */
    public void setLoginUserInfo(AppUserServiceResponse response, UserEntity user, String deviceToken) {

        //1、更新deviceToken
        userManager.updateDeviceToken(user,deviceToken);

        //2、创建token
        Token token = userManager.generateToken(user.getUserCode());

        //3、保存authKey与userCode关系
        userManager.saveAuthKey(token.getAuthKey(), token.getUserCode());


        //4、返回用户数据信息
        response.setToken(token.toBase64String());

        user.setPassword(null);
        user.setSecure(null);

        if (user.getDeptAreas().size() > 0) {
            String cityCode = user.getDeptAreas().get(0).getCityCode();
            String provinceCode = user.getDeptAreas().get(0).getProvinceCode();
            response.setCityCode(cityCode);
            response.setProvinceCode(provinceCode);
        }

        UserTeamVo userTeamVo= new UserTeamVo();
        BeanUtils.copyProperties(user,userTeamVo);
        Map map = new HashMap();
        map.put("leaderCode",user.getUserCode());
        map.put("isDeleted",Boolean.FALSE);

        DeptEntity teamEntity = deptManager.findOneDept(map);

        if (teamEntity!=null){
            userTeamVo.setDeptEntity(teamEntity);
        }else{
            userTeamVo.setDeptEntity(null);
        }

        //0910判断权限树集合
        List<MenuEntity> judgeMenuTreeList = null;
        //菜单树
        List<MenuEntity> menuTreeList = null;
        //若是有岗位编码，则先要通过岗位去找绑定的角色
        judgeMenuTreeList = menuManager.getUserJudgeMenuList(user.getEmpPostCode(), user.getUserCode());
        //开始时间
        long starts = System.currentTimeMillis();
        menuTreeList = menuManager.findMenuToTreeByPostCodeAndUserCode(user.getEmpPostCode(),user.getUserCode());
        //0910判断权限树end
        userTeamVo.setJudgeMenuTreeList(judgeMenuTreeList);

        response.setResult(userTeamVo);
    }

    /**
     * 注册
     * @param request
     * @return
     */
    @RequestMapping(path = "/registered", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> registered(@RequestBody AppUserServiceRequest request){

        AppUserServiceResponse response = new AppUserServiceResponse();


        if (StringUtils.isNotEmpty(request.getVerifyCode())) {

            List<UserEntity> userEntities =userDbDao.selectByNativeSql("select * from t_hkp_sys_user_user" +
                    " where user_phone=\'"+request.getEntity().getUserPhone()+"\'");
            Integer count = userDbDao.countByNativeSql("select count(*) from t_ky_flow_employee where phone_number = \'"+request.getEntity().getUserPhone()+"\'");
            if (null!=userEntities&&userEntities.size()>0){
                throw new BusinessException("该用户已注册，请直接登陆");
            }
            if (count>0){
                throw new BusinessException("请等待入职工作流审核通过直接登录！");
            }

            UserEntity user=request.getEntity();
            if (StringUtils.isEmpty(user.getUserName())){
                throw new BusinessException("请输入用户名");
            }
            if (StringUtils.isEmpty(user.getSex())){
                throw new BusinessException("请选择用户性别");
            }
            if(StringUtils.isEmpty(user.getCard())){
                throw new BusinessException("请输入身份证号");
            }
            String imgName=PinyinUtil.getPinYin(user.getUserName())+user.getCard().substring(14,18);
            if (user.getUserImg()==null) {
                throw new BusinessException("请输入头像");
            }else{
                user.setUserImg("pic" + File.separator+fileManager.uploadImages(user.getUserImg(),imgName)+".JPG");
            }
            user.setDeviceBrand(request.getDeviceBrand());
            String pwd=user.getPassword();
            String sec=user.getSecure();
            user.setPassword(sec);
            user.setSecure(pwd);
            user.setIdentifyNumber(request.getIdentifyNumber());
            //校验验证码
            Map<String, String> map = smsRedisDao.getSmsCode(request.getEntity().getUserPhone());

            if (map != null && map.get("verCode").equals(request.getVerifyCode())) {
                //创建账号，绑定设备号,token
                user=userManager.registered(user);
                if (null==user){
                    throw new BusinessException("未选择负责区域，注册失败");
                }

                return success(response,"注册成功，请等待审核");
            } else {
                throw new BusinessException("验证码错误");
            }
        }else {
            throw new BusinessException("请输入验证码");
        }

    }
}

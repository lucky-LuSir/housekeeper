package com.kfwy.hkp.sys.user.business.impl;

import com.gniuu.framework.common.auth.Token;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.business.impl.AbstractBaseUserManager;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.common.sms.redis.ISmsRedisDao;
import com.kfwy.hkp.common.utils.MD5Utils;
import com.kfwy.hkp.hrm.org.dept.entity.DeptAreaEntity;
import com.kfwy.hkp.sys.area.business.ICompanyAreaManager;
import com.kfwy.hkp.sys.area.entity.CompanyAreaEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.dao.IUserAreaDbDao;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.dao.impl.UserRedisDaoImpl;
import com.kfwy.hkp.sys.user.entity.UserAreaEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserCAStatus;
import com.kfwy.hkp.sys.user.enums.UserHasPass;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import com.kfwy.hkp.sys.user.enums.UserType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;


import java.util.*;

/**
 * Created by davidcun on 2018/5/16.
 */
@Service
@Transactional
public class UserManagerImpl extends AbstractBaseUserManager<UserEntity> implements IUserManager {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private IUserDbDao userDbDao;

    @Autowired
    private UserRedisDaoImpl userRedisDao;

    @Autowired
    private IUserAreaDbDao userAreaDbDao;
    @Autowired
    private ISmsRedisDao smsRedisDao;

    @Autowired
    private ICompanyAreaManager companyAreaManager;

    @Override
    public IMyBatisBaseDao<UserEntity, Long> getMyBatisDao() {
        return this.userDbDao;
    }


    @Override
    public PageResult<UserEntity> findByMap(Map<String, Object> param, int start, int pageSize) {
        param.put("self_page", Boolean.TRUE);
        return super.findByMap(param, start, pageSize);
    }

    @Override
    public PageResult<UserEntity> findByMap(Map<String, Object> param, int start, int pageSize, String orderBy, boolean isAsc) {
        param.put("self_page", Boolean.TRUE);
        return super.findByMap(param, start, pageSize, orderBy, isAsc);
    }

    @Override
    public long getTokenExpire() {
        return 0;
    }

    @Override
    public UserEntity login(UserEntity login) {
        if (StringUtils.isEmpty(login.getSecure())) {
            throw new RemoveStackBusinessException ("密码不能为空");
        }
        Map<String, Object> param = new HashMap<String, Object>();
        addParam(param, "userCode", login.getUserCode());
        addParam(param, "userPhone", login.getUserPhone());
        addParam(param, "workNumber", login.getWorkNumber());
        param.put("isDeleted",Boolean.FALSE);
        List<UserEntity> users = loginUserDbDao.selectByMap(param);
        UserEntity user = null;
        if (users == null || users.size() < 1) {
            throw new RemoveStackBusinessException("用户不存在");
        }else if(users.get(0).getIsFrost()){
            throw new RemoveStackBusinessException("用户账号被冻结");
        }else {
            user = users.get(0);
        }

        if (users.size() > 1) {
            throw new RemoveStackBusinessException("存在多个账号");
        }
        if (user == null) {
            throw new RemoveStackBusinessException("用户不存在");
        }
        if((UserLevel.K0.equals(user.getUserLevel())&&StringUtils.isEmpty(user.getHasPass()))&&UserHasPass.NoPass.equals(user.getHasPass())){
            throw new RemoveStackBusinessException("请完善入职工作流");
        }
        if(!UserLevel.K0.equals(user.getUserLevel())&&UserHasPass.NoPass.equals(user.getHasPass())){
            throw new RemoveStackBusinessException("账号还在审核中,请稍后再试！");
        }
        //前端必须要传MD5加密的密码到服务端
        if (!user.getPassword().equals(login.getSecure())
                && !MD5Utils.md5(user.getSecure(), "").equals(login.getSecure())) {

            throw new RemoveStackBusinessException("用户密码错误");
        }
        return user;
    }

    @Override
    public void logout(Token token) {

        if (token != null) {
            loginUserRedisDao.deleteAuthKey(token.getAuthKey());
        }
    }

    @Override
    public Token generateToken(String userCode) {

        String authKey = UUID.randomUUID().toString().replaceAll("-", "");

        Token token = new Token(userCode, authKey, getTokenExpire());
        return token;
    }

    private void addParam(Map<String, Object> param, String key, Object value) {
        if (param != null && key != null && value != null) {
            param.put(key, value);
        }
    }

    @Override
    public UserEntity findUserByPhone(String userPhone) {
        Map map  = new HashMap();
        map.put("userPhone",userPhone);
        map.put("isDeleted",false);
        UserEntity user = null;
        List<UserEntity> users = loginUserDbDao.selectByMap(map);
        if (users!=null && users.size()>0){
           user =  users.get(0);
        }
        return user;
    }

    @Override
    public int update(UserEntity userEntity) {
        int result = userDbDao.update(userEntity);

        try {
            userRedisDao.setUserInfo(userEntity.getUserCode(),userEntity);
        } catch (Exception e) {

        }
        //本地数据库不存在就更新远程数据库
//        if (result < 1) {
//            UserApiVo user = new UserApiVo();
//            user.setIdentifyNumber(userEntity.getIdentifyNumber());
//            user.setDeviceToken(userEntity.getDeviceToken());
//            user.setUserCode(userEntity.getUserCode());
//
//            if(UserApiClient.updateUser(user)){
//                result = 1;
//            }
//        }
        return result;
    }

    @Override
    public UserEntity findUserByUserCode(String userCode) {
        if (StringUtils.isEmpty(userCode)) {
            return null;
        } else {
            UserEntity user = this.loginUserRedisDao.getUserInfo(userCode);
            if (user == null) {
                Map map = new HashMap();
                map.put("userCode",userCode);
                map.put("isDeleted",Boolean.FALSE);
                user = this.userDbDao.login(map);
                if (user != null) {
                    this.loginUserRedisDao.setUserInfo(user.getUserCode(), user);
                }
            }
            return user;
        }
    }



    @Override
    public int create(UserEntity userEntity) {
        userEntity.setUserCode(CodeGenUtils.generate());
        userEntity.setSecure("123456");
        userEntity.setPassword("e10adc3949ba59abbe56e057f20f883e");
        OperateFillUtils.fill(userEntity);
        return userDbDao.insert(userEntity);
    }

    @Override
    public int updateIdentifyNumber(Map map) {
        return this.userDbDao.updateIdentifyNumber(map);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveOrUpdate(UserEntity user) {

        UserEntity tmp = userDbDao.selectUniqueByProp("userCode", user.getUserCode());

        if (ObjectUtils.isEmpty(tmp)) {
            userDbDao.insert(user);
        } else {
            //不能更新设备号
            user.setIdentifyNumber(null);
            user.setDeviceToken(null);
            if (null!=user.getHasReferrer()&&!user.getHasReferrer()){
                user.setReferrerCode("");
            }
            // 更新redis数据
            this.loginUserRedisDao.setUserInfo(user.getUserCode(), user);
            userDbDao.update(user);
        }
    }

    @Transactional
    @Override
    public UserEntity registered(UserEntity user) {
        user.setUserCode(CodeGenUtils.generate("U"));
        //个人
        user.setUserType(UserType.Individual);
        //未认证
        user.setUserCAStatus(UserCAStatus.UnCertified);
        //类型
        user.setEnterType("2");
        user.setUserLevel(UserLevel.K2);
        user.setHasManager(false);
        user.setNeedAuth(true);
        user.setIsDeleted(false);
        user.setEntryTime(new Date());
        OperateFillUtils.fill(user);
        user.setOwnerDeptCode(null);
        user.setOwnerDeptName(null);
        user.setHasPass(UserHasPass.NoPass);
        List<UserAreaEntity> userAreaEntities=user.getUserAreas();
        if(null!=userAreaEntities&&userAreaEntities.size()>0){
            boolean flag=true;
            for(UserAreaEntity userAreaEntity:userAreaEntities){
                if(StringUtils.isNotEmpty(userAreaEntity.getRegionCode())){
                    flag=false;
                }
                CompanyAreaEntity provinceEntity = companyAreaManager.findAreaByCode(userAreaEntity.getCityCode());
                if (provinceEntity!=null){
                    userAreaEntity.setProvinceCode(provinceEntity.getAreaCode());
                    userAreaEntity.setProvinceName(provinceEntity.getName());
                }
                userAreaEntity.setUserCode(user.getUserCode());
                userAreaEntity.setIsDeleted(false);
                userAreaEntity.setCreateCode(CurrentContext.getUserCode());
                userAreaEntity.setCreateTime(new Date());
            }
            if (flag) {
                throw new RemoveStackBusinessException("未选择负责区域，注册失败");
            }
            userAreaDbDao.batchInsert(userAreaEntities);
            userDbDao.insert(user);
        } else {
            return null;
        }

        return user;
    }

    @Override
    public UserEntity selectUniqueByProp(String userCode) {
        if (null != userCode && userCode != "") {
            Map<String, String> map = new HashMap<>();
            map.put("userCode", userCode);
            return userDbDao.selectUniqueByProp(map);
        }
        return null;
    }

    /**
     * 默认查询负责区域客户
     *
     * @return
     */
    @Override
    public List<String> getUserDutyRegions() {
        String userCode = CurrentContext.getUserInfo().getUserCode();

        UserEntity user = this.selectUniqueByProp(userCode);
        UserEntity userEntity = this.findUserByUserCode(userCode);
        List<String> regions = new ArrayList<>();
        if (userEntity.getUserType().equals("Employee")) {
            //内部用户根据部门查询负责区域
            if (null != user.getDeptAreas() && user.getDeptAreas().size() > 0) {
                for (DeptAreaEntity deptAreaEntity : user.getDeptAreas()) {
                    if (StringUtils.isNotEmpty(deptAreaEntity.getRegionCode())) {
                        regions.add(deptAreaEntity.getRegionCode());
                    }
                }
            }
        } else {
            //外部根据员工编号查询负责区域
            if (null != user.getUserAreas() && user.getUserAreas().size() > 0) {
                for (UserAreaEntity userAreaEntity : user.getUserAreas()) {
                    if (StringUtils.isNotEmpty(userAreaEntity.getRegionCode())) {
                        regions.add(userAreaEntity.getRegionCode());
                    }
                }
            }
        }
        if (regions.size() > 0) {
            return regions;
        } else {
            return null;
        }
    }

    @Override
    public PageResult<UserEntity> selectUserBasisInfoByMap(Map<String, Object> map, int i, int i1) {
        map.put("self_page", Boolean.TRUE);
        return this.userDbDao.selectUserBasisInfoByMap(map, i, i1);
    }

    @Override
    public List<UserEntity> selectUserBasisInfoByMap(Map<String, Object> map) {
        return this.userDbDao.selectUserBasisInfoByMap(map);
    }

    @Override
    public List<UserEntity> userAllList() {
        String sql = "select * from t_hkp_sys_user_user where is_deleted=false";
        return this.userDbDao.selectByNativeSql(sql);
    }

    @Override
    public UserEntity getUserEntity(String userCode) {
        return this.userDbDao.getUserEntity(userCode);
    }

    @Override
    public void editUser(UserEntity userEntity) {
        userEntity.setLastUpdateCode(CurrentContext.getUserCode());
        userEntity.setLastUpdateTime(new Date());
        List<UserAreaEntity> userAreaEntities=userEntity.getUserAreas();
        if(null!=userAreaEntities&&userAreaEntities.size()>0) {
            boolean flag = true;
            for (UserAreaEntity userAreaEntity : userAreaEntities) {
                if (StringUtils.isNotEmpty(userAreaEntity.getRegionCode())) {
                    flag = false;
                }
                userAreaEntity.setUserCode(userEntity.getUserCode());
                userAreaEntity.setLastUpdateCode(CurrentContext.getUserCode());
            }
            if (flag) {
                return;
            }
            userAreaDbDao.updateUserArea(userAreaEntities);
            List<UserEntity> userEntityList = new ArrayList<>();
            userEntityList.add(userEntity);
            userDbDao.batchUpdate(userEntityList);
        }
    }

    @Override
    @Cacheable(value="redisCache",key = "'ky:hkp:userListCache'")
    public List<UserEntity> queryAllUser() {
        return userDbDao.queryAllUser();
    }

    @Override
    public List<UserEntity> findUsersByDeptArea(Map map) {
        return userDbDao.findUsersByDeptArea(map);
    }

    @Override
    public List<UserEntity> findUsersByNoticeDepts(Map<String, Object> map) {
        return userDbDao.findUsersByNoticeDepts(map);
    }

    @Override
    public List<String> excludeUserCodesByFocusCusCount (Map map) {
        return this.userDbDao.excludeUserCodesByFocusCusCount (map);
    }


    @Override
    public Boolean doUnbind(String unBindVerifyCode, String userPhone, UserEntity user) {
        Boolean isUnbind = false;
        if (StringUtils.isNotEmpty(unBindVerifyCode)) {
            Map<String, String> map = smsRedisDao.getUnbindCode(userPhone);
            if (map != null && map.get("unBindVerifyCode").equals(unBindVerifyCode)) {
                map.clear();
                map.put("userPhone", user.getUserPhone());
                map.put("userCode", user.getUserCode());
                int unBind = updateIdentifyNumber(map);
                if (unBind > 0) {
                    isUnbind = true;
                } else {
                    throw new RemoveStackBusinessException("解绑失败");
                }
            } else {
                throw new RemoveStackBusinessException("解绑验证码错误");
            }
        }
        return isUnbind;
    }

    @Override
    /**
     * 友盟token更新
     * @param request
     * @param user
     * @param requestDeviceToken
     */
    public void updateDeviceToken(UserEntity user, String deviceToken) {
        if (Boolean.TRUE.equals(user.getNeedAuth())) {
            if (StringUtils.isNotEmpty(deviceToken) && !deviceToken.equals(user.getDeviceToken())) {
                user.setDeviceToken(deviceToken);
                this.update(user);
            }
        }
    }
}

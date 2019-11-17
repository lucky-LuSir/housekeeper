package com.kfwy.hkp.sys.user.business;

import com.gniuu.framework.common.auth.Token;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.business.IBaseUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * @description TODO
 * @auth davidCun
 * @date 2018/6/29 17:55
 * @version 1.0
 * @return
 */
public interface IUserManager extends IBaseUserManager<UserEntity> {



    IMyBatisBaseDao<UserEntity,Long> getMyBatisDao();

    /**
     * 用户登录，可能有多种登录方式
     * @param user
     * @return
     */
    public UserEntity login(UserEntity user);

    /**
     * 通过用户编码生成用户token
     * @param userCode
     * @return
     */
    public Token generateToken(String userCode);

    /**
     * 通过授权推出登录
     * @param token
     */
    public void logout(Token token);

    UserEntity findUserByPhone(String userPhone);

    public int update(UserEntity userEntity);

    /**
     * 通过userCode来查找用户
     * @param userCode
     * @return
     */
    @Override
    public UserEntity findUserByUserCode(String userCode);

    /**
     * 新增用户
     * @param userEntity
     * @return
     */
    public int create(UserEntity userEntity);

    public int updateIdentifyNumber(Map map);

    /**
     * 新增或者更新用户
     * @param user
     */
    public void saveOrUpdate(UserEntity user);

    /**
     * 注册用户
     * @param user
     */
    public UserEntity registered(UserEntity user);

    public UserEntity selectUniqueByProp(String userCode);

    /**
     * 获取用户负责区域
     * @param
     * @return
     */
    List<String> getUserDutyRegions();

    PageResult<UserEntity> selectUserBasisInfoByMap(Map<String, Object> map, int i, int i1);

    List<UserEntity> selectUserBasisInfoByMap(Map<String,Object> map);

    public List<UserEntity> userAllList();

    public Boolean doUnbind(String unBindVerifyCode,String userPhone, UserEntity user);

    /**
     * 友盟token更新
     * @param request
     * @param user
     * @param deviceToken
     */
    public void updateDeviceToken(UserEntity user, String deviceToken);

    /**
     * 根据独立经纪人code获取其信息
     * @param userCode
     * @return
     */
    UserEntity getUserEntity(String userCode);

    /**
     * 根据手机号修改独立经纪人信息
     * @param userEntity
     */
    void editUser(UserEntity userEntity);


    public List<UserEntity> queryAllUser();

    public List<UserEntity> findUsersByDeptArea(Map map);


    List<UserEntity> findUsersByNoticeDepts(Map<String, Object> map);

    List<String> excludeUserCodesByFocusCusCount(Map map);
}

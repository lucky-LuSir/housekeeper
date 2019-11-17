package com.kfwy.hkp.sys.user.dao;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.user.dao.IBaseUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/16.
 */
public interface IUserDbDao extends IBaseUserDbDao<UserEntity> {

    public int updateIdentifyNumber(Map map);

    public UserEntity selectUniqueByProp(Map map);

    PageResult<UserEntity> selectUserBasisInfoByMap(Map<String, Object> map, int start, int pageSize);

    List<UserEntity> selectUserBasisInfoByMap(Map<String, Object> map);

    List<String> selectUserCodeByMap(Map<String, Object> map);


    List<UserEntity> selectUserByDeptCodes(Map<String, Object> map);

    /*
    *  只查询用户基本信息 不包括区域,角色,部门
    * */
    List<UserEntity> selectSimpleUserByMap(Map<String, Object> map);

    /**
     * 根据独立经纪人Code 获取其信息
     * @param userCode
     * @return
     */
    UserEntity getUserEntity(String userCode);

    public UserEntity login(Map map);

    public List<UserEntity> queryAllUser();

    public List<UserEntity> findUsersByDeptArea(Map map);

    List<UserEntity> findUsersByNoticeDepts(Map<String, Object> map);

    List<String> excludeUserCodesByFocusCusCount (Map map);
}

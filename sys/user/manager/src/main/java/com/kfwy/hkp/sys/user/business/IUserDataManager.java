package com.kfwy.hkp.sys.user.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
* @author kfwy_it_013
 * @Description 描述:User通用封装方法
* @Auth 李文思汗
* @Date 2018/8/17 13:44
* @Version 1.0
* @Param 
* @return 
**/
public interface IUserDataManager extends IManager<UserEntity> {


    /**
    * @Description 描述:只更新用户关联角色个数
    * @Auth xpp
    * @Date 2018/8/18 9:06
    * @Version 1.0
    * @Param [entity, roleCodes]
    * @return int
    */
    int updateUserRoleList(UserEntity entity, List<String> roleCodes);

    /**
     * @Description 查询当前用户
     * @Auth luming
     * @Date 2018/8/28 13:51
     * @Version 1.0
     * @Param
     * @return
     */
    public UserEntity queryCurrentUser();

    public List<UserEntity> selectAllEmployee();

    public List<UserEntity> selectEmployeeByMap(Map map);
}

package com.kfwy.hkp.sys.user.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.user.business.IUserDataManager;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.dao.IUserRoleDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.entity.UserRoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kfwy_it_013
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/12 22:52
 * @Version 1.0
 */
@Service
public class UserDataManagerImpl extends AbstractManager<UserEntity> implements IUserDataManager {

    @Autowired
    private IUserDbDao userDbDao;

    @Autowired
    private IUserRoleDbDao userRoleDbDao;


    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return userDbDao;
    }


    @Override
    public int updateUserRoleList(UserEntity user, List<String> roleCodes) {
        if (roleCodes!=null){
            userRoleDbDao.deleteByUserCode(user.getUserCode());
        }

        createUserRole(user.getUserCode(),roleCodes);

        int i=1;
        return i;
    }

    private void createUserRole(String userCode,List<String> roleCodes){
        List<UserRoleEntity> list = new ArrayList<UserRoleEntity>();

        for (String role : roleCodes){
            UserRoleEntity ure = new UserRoleEntity();
            ure.setUserCode(userCode);
            ure.setRoleCode(role);
            ure.setCpyCode(CurrentContext.getCpyCode());

            list.add(ure);
        }

        userRoleDbDao.batchInsert(list);
    }

    @Override
    public UserEntity queryCurrentUser() {
        //查询当前登陆用户的信息
        Map<String, Object> map = new HashMap<>();
        map.put("userCode",CurrentContext.getUserInfo().getUserCode());
        map.put("id",CurrentContext.getUserInfo().getId());
        map.put("empCode",CurrentContext.getUserInfo().getUserCode());
        map.put("isDeleted",false);
        UserEntity ue = userDbDao.selectUniqueByMap(map);

        return ue;
    }

    @Override
    public List<UserEntity> selectAllEmployee() {
        Map map = new HashMap();
        //map.put("userType", UserType.Employee);
        //map.put("isDeleted",Boolean.FALSE);
        return this.userDbDao.selectByMap(map);
    }

    @Override
    public List<UserEntity> selectEmployeeByMap(Map map) {
        if (ObjectUtils.isEmpty(map)){
            map = new HashMap();
            //map.put("userType", UserType.Employee);
           // map.put("isDeleted",Boolean.FALSE);
        }
        return this.userDbDao.selectByMap(map);
    }
}

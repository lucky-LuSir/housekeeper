package com.kfwy.hkp.base;


import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.poi.ss.formula.functions.T;

import java.util.Map;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
public interface ICheckPermissionManager {

    public final static String CHECK_TYPE="checkType";


    public Boolean checkPermissions(String checkType, String checkCode, Object o, UserEntity cur);


    public Boolean checkNoPass(UserEntity cur, UserEntity owner, Boolean isNoPass);



}

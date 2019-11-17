package com.kfwy.hkp.base.impl;

import com.kfwy.hkp.base.ICheckPermissionManager;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.hos.house.business.IHouseManager;
import com.kfwy.hkp.hos.house.entity.HouseEntity;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @AUTHOR 李文思汗
 * @Description 类描述:
 */
@Service
public class CheckPermissionManagerImpl implements ICheckPermissionManager {

    @Autowired
    ICustomerManager customerManager;

    @Autowired
    IHouseManager houseManager;

    @Autowired
    IDeptManager deptManager;

    @Autowired
    IUserManager userManager;



    @Override
    public Boolean checkPermissions(String checkType, String checkCode, Object o, UserEntity cur) {
        Boolean checkResult = false;
        if (checkType!=null){
            switch (checkType){
                case "cus":
                    checkResult = customerManager.checkCusPermissions(checkCode,(CustomerEntity)o,cur);
                    break;
                case "hos":
                    checkResult = houseManager.checkHosPermissions(checkCode,(HouseEntity)o,cur);
                    break;
                case "bi":
                    checkResult = true;
                    break;
                default:
                    throw new RemoveStackBusinessException("未定义的检查类型");
            }
        }else{
            throw new RemoveStackBusinessException ("传入的操作权限检查类型不能为空");
        }
        return checkResult;
    }

    @Override
    public Boolean checkNoPass(UserEntity cur, UserEntity owner, Boolean isNoPass) {
        if (cur.getUserLevel().equals(UserLevel.K0) || cur.getUserLevel().equals(UserLevel.T0)){
            //当前客户的所属人有部门，且有领导，则进入
            if (owner.getOwnerDeptCode() != null
                    && owner.getLeaderCode() != null
                    && cur.getOwnerDeptCode() != null) {

                UserEntity leader = userManager.selectUniqueByProp(deptManager.getDeptEntity(owner.getOwnerDeptCode()).getLeaderCode());

                //当前客户所属人有领导
                if (leader != null) {
                    //当前操作人为领导或者客服则跳过判断
                    if (leader.getUserCode().equals(cur.getUserCode())) {

                        isNoPass = false;

                    } else {
                        //遍历当前操作人的所有子部门，如果子部门包含客户所属人的部门，则是大区总，大区总也可以做所有操作
                        List<String> deptCode = deptManager.getDownDeptCode(cur.getOwnerDeptCode());

                        if (deptCode != null
                                && deptCode.size() > 1
                                && deptCode.contains(owner.getOwnerDeptCode())
                                && deptManager.getDeptEntity(cur.getOwnerDeptCode()).getLeaderCode().equals(cur.getUserCode())) {

                            isNoPass = false;

                        }
                    }
                }
            }

            if (cur.getEnterType().equals("1")) {
                isNoPass = false;
            }
        }
        return isNoPass;
    }





}

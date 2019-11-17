package com.kfwy.hkp.sys.user.business.impl;

import com.gniuu.framework.exception.BusinessException;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.sys.user.business.IUserApplyTeamManager;
import com.kfwy.hkp.sys.user.dao.IUserApplyTeamDbDao;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserApplyTeamEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.ApplyCategory;
import com.kfwy.hkp.sys.user.enums.UserType;
import org.apache.commons.collections4.functors.ExceptionClosure;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.*;

/**
* @Description 描述:共享申请
* @Auth xpp
* @Date 2019/4/29 14:05
* @Version 1.0
* @Param
* @return
**/
@Service
public class UserApplyTeamManagerImpl extends AbstractManager<UserApplyTeamEntity> implements IUserApplyTeamManager {

    @Autowired
    private IUserApplyTeamDbDao userApplyTeamDbDao;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IDeptDbDao deptDbDao;

    @Override
    protected IMyBatisBaseDao<UserApplyTeamEntity, Long> getMyBatisDao() {
        return this.userApplyTeamDbDao;
    }

    /**
    * @Description 描述:覆盖抽象类
    * @Auth xpp
    * @Date 2019/5/5 9:14
    * @Version 1.0
    * @Param [t]
    * @return int
    **/
    public int create(UserApplyTeamEntity t) {
        String genCode ="UAT"+ CodeGenUtils.generate();
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        if (userInfo == null) {
            throw new RemoveStackBusinessException ("用户信息为空,请重新登陆." + userInfo.getUserCode());
        }
        Map umap = new HashMap<String,String>();
        umap.put("userCode",userInfo.getUserCode());
        UserEntity userEntity = userDbDao.selectUniqueByProp(umap);
        if(userEntity==null){throw new RemoveStackBusinessException ("未查到该成员"+t.getReceivePersonCode());}
        if(userEntity.getUserType().equals(UserType.Employee)){
            throw new RemoveStackBusinessException ("邀请成员只适合外部独立经纪人经理!");
        }


        t.setApplyRecordCode(genCode);
        t.setApplyPersonCode(userInfo.getUserCode());
        t.setApplyPersonName(userInfo.getUserName());
        t.setApplyPersonImg(userInfo.getUserImg());
        t.setApplyDeptCode(userInfo.getOwnerDeptCode());
        t.setApplyDeptName(userInfo.getOwnerDeptName());

        Map map = new HashMap<String,String>();
        map.put("userCode",t.getReceivePersonCode());
        UserEntity receiveEntity = userDbDao.selectUniqueByProp(map);
        if(receiveEntity==null){throw new RemoveStackBusinessException ("未查到该成员"+t.getReceivePersonCode());}

        t.setReceivePersonCode(receiveEntity.getUserCode());
        t.setReceivePersonName(receiveEntity.getUserName());
        t.setReceivePersonImg(receiveEntity.getUserImg());
        if(receiveEntity.getOwnerDeptCode() !=null){
            t.setReceiveDeptCode(receiveEntity.getOwnerDeptCode());
        }
        if(receiveEntity.getOwnerDeptName() !=null){
            t.setReceiveDeptName(receiveEntity.getOwnerDeptName());
        }

        t.setApplyState(ApplyCategory.WAITJOIN);
        t.setItemType("join");

        OperateFillUtils.fill(t);
        int rs = this.getMyBatisDao().insert(t);
        return rs;
    }


    @Override
    public int leaveDeptApply(UserApplyTeamEntity t) {
        String genCode ="ULe"+ CodeGenUtils.generate();
        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        if (userInfo == null) {
            throw new RemoveStackBusinessException("用户信息为空,请重新登陆." + userInfo.getUserCode());
        }

        t.setApplyRecordCode(genCode);
        t.setApplyPersonCode(userInfo.getUserCode());
        t.setApplyPersonName(userInfo.getUserName());
        t.setApplyPersonImg(userInfo.getUserImg());
        t.setApplyDeptCode(userInfo.getOwnerDeptCode());
        t.setApplyDeptName(userInfo.getOwnerDeptName());

        DeptEntity deptEntity = deptDbDao.selectUniqueByProp("deptCode",userInfo.getOwnerDeptCode());
        if(deptEntity==null){throw new RemoveStackBusinessException("未查到所在部门"+userInfo.getOwnerDeptCode());}

        Map leaderMap = new HashMap<String,String>();
        leaderMap.put("userCode",deptEntity.getLeaderCode());
        UserEntity receiveEntity = userDbDao.selectUniqueByProp(leaderMap);
        if(receiveEntity==null){throw new RemoveStackBusinessException("未查到该领导"+deptEntity.getLeaderCode());}

        t.setReceivePersonCode(receiveEntity.getUserCode());
        t.setReceivePersonName(receiveEntity.getUserName());
        t.setReceivePersonImg(receiveEntity.getUserImg());
        if(receiveEntity.getOwnerDeptCode() !=null){
            t.setReceiveDeptCode(receiveEntity.getOwnerDeptCode());
        }
        if(receiveEntity.getOwnerDeptName() !=null){
            t.setReceiveDeptName(receiveEntity.getOwnerDeptName());
        }

        t.setApplyState(ApplyCategory.ASKFOR);
        t.setItemType("leave");

        OperateFillUtils.fill(t);
        int rs = this.getMyBatisDao().insert(t);
        return rs;
    }

    @Override
    public void delete(UserApplyTeamEntity entity) {
        userApplyTeamDbDao.deleteById(entity.getId());
    }
}

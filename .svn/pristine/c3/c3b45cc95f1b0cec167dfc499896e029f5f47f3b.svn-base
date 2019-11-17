package com.kfwy.hkp.hos.house.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.hos.house.business.ISharePoolApplyManager;
import com.kfwy.hkp.hos.house.dao.ISharePoolApplyDbDao;
import com.kfwy.hkp.hos.house.entity.SharePoolApplyEntity;
import com.kfwy.hkp.common.enums.ApplyState;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.dao.IUserDbDao;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class SharePoolApplyManagerImpl extends AbstractManager<SharePoolApplyEntity> implements ISharePoolApplyManager {

    @Autowired
    private ISharePoolApplyDbDao sharePoolApplyDbDao;
    @Autowired
    private IUserDbDao userDbDao;
    @Autowired
    private IDeptDbDao deptDbDao;

    @Override
    protected IMyBatisBaseDao<SharePoolApplyEntity, Long> getMyBatisDao() {
        return this.sharePoolApplyDbDao;
    }

    /**
    * @Description 描述:覆盖抽象类
    * @Auth xpp
    * @Date 2019/5/5 9:14
    * @Version 1.0
    * @Param [t]
    * @return int
    **/
    public int create(SharePoolApplyEntity t) {

        BaseUserEntity userInfo = CurrentContext.getUserInfo();
        if (userInfo == null) {
            throw new RemoveStackBusinessException ("用户信息为空,请重新登陆." + userInfo.getUserCode());
        }
        //开始时间
        long starts = System.currentTimeMillis();

        //判断申请是否已经存在askfor
        HashMap<String, Object> param = new HashMap<>();
        param.put("applyDeptCode",userInfo.getOwnerDeptCode());
        param.put("receiveDeptCode",t.getReceiveDeptCode());
        param.put("applyState",ApplyState.ASKFOR);
        param.put("isDeleted",false);
        List<SharePoolApplyEntity> byMap = this.findByMap(param);
        if(byMap!=null){
            if(byMap.size()>0){
                throw new RemoveStackBusinessException("已经申请过,等待同意!");
            }
        }

        //判断申请是否已经存在agree
        HashMap<String, Object> paramTwo = new HashMap<>();
        paramTwo.put("applyDeptCode",userInfo.getOwnerDeptCode());
        paramTwo.put("receiveDeptCode",t.getReceiveDeptCode());
        paramTwo.put("applyState",ApplyState.SHARED);
        paramTwo.put("isDeleted",false);
        List<SharePoolApplyEntity> byMapTwo = this.findByMap(paramTwo);
        if(byMapTwo!=null){
            if(byMapTwo.size()>0){
                throw new RemoveStackBusinessException("已经有正在共享的申请!");
            }
        }
        long costTime = System.currentTimeMillis() - starts;
        System.out.println("cost "+costTime+"ms");

        String genCode ="SPA"+ CodeGenUtils.generate();
        t.setApplyRecordCode(genCode);
        t.setApplyPersonCode(userInfo.getUserCode());
        t.setApplyPersonName(userInfo.getUserName());
        t.setApplyPersonImg(userInfo.getUserImg());
        t.setApplyDeptCode(userInfo.getOwnerDeptCode());
        t.setApplyDeptName(userInfo.getOwnerDeptName());

        Map deptmap = new HashMap<String,String>();
        deptmap.put("deptCode",t.getReceiveDeptCode());
        DeptEntity deptEntity = deptDbDao.selectUniqueByMap(deptmap);

        if(deptEntity==null){
            throw new RemoveStackBusinessException("未查到该部门"+t.getReceiveDeptCode());
        }

        Map map = new HashMap<String,String>();
        map.put("userCode",deptEntity.getLeaderCode());
        UserEntity receiveEntity = userDbDao.selectUniqueByProp(map);
        if(receiveEntity==null){throw new RemoveStackBusinessException("未查到该领导人"+deptEntity.getManagerCode());}

        t.setReceivePersonCode(receiveEntity.getUserCode());
        t.setReceivePersonName(receiveEntity.getUserName());
        t.setReceivePersonImg(receiveEntity.getUserImg());
        t.setReceiveDeptCode(receiveEntity.getOwnerDeptCode());
        t.setReceiveDeptName(receiveEntity.getOwnerDeptName());

        t.setApplyState(ApplyState.ASKFOR);

        OperateFillUtils.fill(t);
        int rs = this.getMyBatisDao().insert(t);
        return rs;
    }



}

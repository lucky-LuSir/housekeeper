package com.kfwy.hkp.hrm.org.dept.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.hrm.org.dept.business.ICusServiceManager;
import com.kfwy.hkp.hrm.org.dept.dao.ICusServiceDbDao;

import com.kfwy.hkp.hrm.org.dept.entity.CusServiceEntity;
import com.kfwy.hkp.sys.user.business.IUserManager;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by davidcun on 2018/5/22.
 */
@Service
public class CusServiceManagerImpl extends AbstractManager<CusServiceEntity> implements ICusServiceManager {

    @Autowired
    private ICusServiceDbDao cusServiceDbDao;
    @Autowired
    private IUserManager userManager;

    @Override
    protected IMyBatisBaseDao<CusServiceEntity, Long> getMyBatisDao() {
        return this.cusServiceDbDao;
    }
    @Override
    public int create(CusServiceEntity cusServiceEntity){
        if (StringUtils.isNotEmpty(cusServiceEntity.getDeptCode())&&StringUtils.isNotEmpty(cusServiceEntity.getUserCode())){
            Map<String,Object> param=new HashMap<>();
            param.put("userCode",cusServiceEntity.getUserCode());
            param.put("deptCode",cusServiceEntity.getDeptCode());
            List<CusServiceEntity> cusServiceEntityList = cusServiceDbDao.selectCusServiceList(param);
            if (null!=cusServiceEntityList&&cusServiceEntityList.size()>0){
                throw new RemoveStackBusinessException ("该人员已经是此部门的客服，请勿重复添加");
            }
            UserEntity userEntity = userManager.selectUniqueByProp(cusServiceEntity.getUserCode());
            if (!userEntity.getEmpPostCode().equals("PT201712080001d5ba")&&!userEntity.getEmpPostCode().equals("PT201712210005ce7f")
                    &&!userEntity.getEmpPostCode().equals("PT2019010200017450")&&!userEntity.getEmpPostCode().equals("PT2018070700013ffb")){
                throw new RemoveStackBusinessException("此人员不是客服,请确认此人岗位再进行分配");
            }
            cusServiceEntity.setCusserviceCode(CodeGenUtils.generate("CS"));
            OperateFillUtils.fill(cusServiceEntity);
            cusServiceDbDao.insert(cusServiceEntity);
        }else {
            throw new RemoveStackBusinessException("部门或人员编码为空");
        }

        return 0;
    }
    @Override
    public void delete(CusServiceEntity cusServiceEntity){

        if (StringUtils.isNotEmpty(cusServiceEntity.getCusserviceCode())){
            String preSql = "update  t_hkp_org_dept_cusservice set is_deleted=true " +
                    " WHERE cusservice_code=\'%s\'";
            String sql = String.format(preSql,cusServiceEntity.getCusserviceCode());
            cusServiceDbDao.updateByNativeSql(sql);
        }else {
            throw new RemoveStackBusinessException("配置编码为空");

        }
    }

    @Override
    public PageResult<CusServiceEntity> selectCusServiceList(Map<String, Object> param, int start, int pageSize) {
        PageResult<CusServiceEntity> result = cusServiceDbDao.selectCusServiceList(param,start,pageSize);
        return result;
    }
}

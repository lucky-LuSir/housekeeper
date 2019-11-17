package com.kfwy.hkp.crm.customer.business.impl;


import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.hkp.base.SystemConfig;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.crm.customer.business.ICustomerApplyHidOrPriManager;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerApplyHidOrPriDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerApplyHidOrPriEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerApplyHidOrPriStatus;
import com.kfwy.hkp.crm.customer.enums.CustomerApplyHidOrPriType;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.hrm.org.dept.business.IDeptManager;
import com.kfwy.hkp.hrm.org.dept.entity.DeptEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerApplyHidOrPriManagerImpl extends AbstractManager<CustomerApplyHidOrPriEntity> implements ICustomerApplyHidOrPriManager {

    @Autowired
    private ICustomerApplyHidOrPriDbDao customerApplyHidOrPriDbDao;
    @Autowired
    private ICustomerDbDao customerDbDao;
    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    protected SystemConfig systemConfig;

    @Override
    protected IMyBatisBaseDao<CustomerApplyHidOrPriEntity, Long> getMyBatisDao() {
        return customerApplyHidOrPriDbDao;
    }

    @Override
    public PageResult<CustomerApplyHidOrPriEntity> selectByMap(Map map,Integer start,Integer pageSize){
        UserEntity user=(UserEntity)CurrentContext.getUserInfo();
        if(deptManager.hasDeptLeader(user.getOwnerDeptCode(),user.getUserCode())){
            map.put("createDeptCode",user.getOwnerDeptCode());
        }else{
            map.put("createCode",user.getUserCode());
        }
        map.put("self_page",Boolean.TRUE);
       return customerApplyHidOrPriDbDao.selectByMap(map,start,pageSize,"create_time",Boolean.FALSE);
    }

    @Override
    public int apply(String cusCode,Integer type,String reason){
        int i=0;
        UserEntity user=(UserEntity)CurrentContext.getUserInfo();
        DeptEntity dt=deptManager.getDeptEntity(user.getOwnerDeptCode());
        CustomerApplyHidOrPriEntity apply=new CustomerApplyHidOrPriEntity();
        apply.setCusApplyHopCode(CodeGenUtils.generate());
        apply.setOwnerCode(cusCode);
        apply.setCheckCode(dt.getLeaderCode());
        apply.setCheckName(dt.getLeaderName());
        apply.setCreateCode(user.getUserCode());
        apply.setCreateName(user.getUserName());
        apply.setCreateDeptCode(user.getOwnerDeptCode());
        apply.setCreateTime(new Date());
        apply.setType(type);
        apply.setStatus(CustomerApplyHidOrPriStatus.PENDING);
        apply.setIsDeleted(Boolean.FALSE);
        apply.setReason(reason);
        i=customerApplyHidOrPriDbDao.insert(apply);
        return i;
    }

    @Override
    public int check(CustomerApplyHidOrPriEntity customerApplyHidOrPri){
        UserEntity user=(UserEntity)CurrentContext.getUserInfo();
        int i=0;
        if(!deptManager.hasDeptLeader(user.getOwnerDeptCode(),user.getUserCode())){
            throw new BusinessException("你不是部门领导，无权操作");
        }else{
            i=customerApplyHidOrPriDbDao.update(customerApplyHidOrPri);
            this.updateCustomer(customerApplyHidOrPri);
        }
        return i;
    }

    private void updateCustomer(CustomerApplyHidOrPriEntity customerApplyHidOrPri){
        CustomerApplyHidOrPriEntity de = customerApplyHidOrPriDbDao.selectUniqueByProp("id",customerApplyHidOrPri.getId());
        if(customerApplyHidOrPri.getStatus().equals(CustomerApplyHidOrPriStatus.PASS)){
            if(de.getType().equals(CustomerApplyHidOrPriType.PRIVATE)){
                this.checkPullPrivateByHadPullPrivateCount(de.getCreateCode());
                CustomerEntity customerEntity = customerManager.findOne("cusCode", de.getOwnerCode());
                customerEntity.setOpenFlag(false);
                customerEntity.setEmpCode(de.getCreateCode());
                customerEntity.setCusType(CustomerType.PRIVATE);
                customerEntity.setLastUpdateTime(new Date());
                 customerDbDao.pullPrivate(customerEntity);
            }else{
                this.checkHasCount(de.getCreateCode(),de.getCreateDeptCode());
                CustomerEntity customer = new CustomerEntity ();
                customer.setCusCode (de.getOwnerCode());
                customer.setOpenFlag (false);
                customerDbDao.onAndOffOpenFlag (customer);
            }
        }
    }

    private void checkPullPrivateByHadPullPrivateCount (String empCode) {
        Map<String, Object> param = new HashMap ();
        param.put ("empCode", empCode);
        param.put ("cusType", CustomerType.PRIVATE);
        //每天只能够拉私三个客户
        checkTodayPullPrivateCus (param);
        int ct = customerDbDao.countByMap (param);
        if (ct >= 6) {
            throw new BusinessException ("最多可以拉私六位客户,不可以再拉私");
        }
    }

    private void checkTodayPullPrivateCus (Map<String, Object> param) {
        param.put ("createTimeStart", DateFormatUtil.dayBegin (new Date ()));
        param.put ("createTimeEnd", DateFormatUtil.dayEnd (new Date ()));
        String preSql = "select count(*) " + "from t_hkp_crm_customer where emp_code=\'%s\' " + "and cus_type=\'%s\' " + "and last_update_time>=\'%s\' " + "and last_update_time<=\'%s\'";
        String sql = String.format (preSql, param.get ("empCode"), param.get ("cusType"), param.get ("createTimeStart"), param.get ("createTimeEnd"));
        int count = customerDbDao.countByNativeSql (sql);
        if (count >= 3) {
            throw new BusinessException ("最多可拉私六位平台客户,单日不超过3个，他今天已拉私" + count + "位!,不能再进行拉私操作");
        }
    }


    private void  checkHasCount(String empCode,String createDeptCode) {
        // 判断团队是否限制隐藏客户
        DeptEntity deptEntity = deptManager.getDeptEntity (createDeptCode);
        if (deptEntity.getHasLimitHiden ()) {
            String preSql = "SELECT\n" + "\tCOUNT (*)\n" + "FROM\n" + "\tt_hkp_crm_customer\n" + "WHERE\n" + "\temp_code = \'%s\'\n" + "AND open_flag = FALSE\n" + "AND cus_type ='2'";
            Integer count = customerDbDao.countByNativeSql (String.format (preSql, empCode));
            int configValue = checkSeeCusLimitFromErp ();

            if (count >= configValue) {
                throw new BusinessException ("他的隐藏客户已经达到" + systemConfig.getValueInt ("hkp_cus_hide_count") + "个，已经不能再进行隐藏操作!");
            }
        }
    }

    private int checkSeeCusLimitFromErp () {
        // 切换erp数据库,查询限制客户配置数量
        int configValue = 0;
        try {
            configValue = systemConfig.getValueInt ("hkp_cus_hide_count");
        } catch (Exception e) {
            configValue = 3;
        }
        return configValue;
    }
}

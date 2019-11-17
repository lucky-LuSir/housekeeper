package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.crm.customer.business.ICustomerApplyManager;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerApplyDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerApplyEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.enums.UserType;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Create By hjh on 2018/8/11
 */
@Service
public class CustomerApplyManagerImpl extends AbstractManager<CustomerApplyEntity> implements ICustomerApplyManager {

    public static final String JIADINGERBU = "DP2018122800011e3a";
    public static final String KUNSHANSANBU = "DP201903010002ebd3";
    @Autowired
    private ICustomerApplyDbDao customerApplyDbDao;
    @Autowired
    private ICustomerManager customerManager;

    @Override
    protected IMyBatisBaseDao<CustomerApplyEntity, Long> getMyBatisDao() {
        return customerApplyDbDao;
    }

    @Override
    public int create(CustomerApplyEntity customerApplyEntity) {

        UserEntity user = (UserEntity) CurrentContext.getUserInfo();

        CustomerEntity customer = customerManager.findOne("cusCode", customerApplyEntity.getCusCode());

        if (!CustomerType.PLATFORM.equals(customer.getCusType())) {
            throw new RemoveStackBusinessException ("非平台客户不能进行申请查看号码!");
        }

        checkApplyCount();
        OperateFillUtils.fill(customerApplyEntity);
        customerApplyEntity.setEmpCode(CurrentContext.getUserCode());

        return customerApplyDbDao.insert(customerApplyEntity);
    }

    private void checkApplyCount() {

        String dt = DateUtils.formatDate(new Date(), "yyyy-MM-dd");

        String preSql = "select count(*) from t_hkp_crm_apply " +
                "where emp_code=\'%s\' " +
                "and create_time > \'%s\'";

        int count = customerApplyDbDao.countByNativeSql(String.format(preSql, CurrentContext.getUserCode(), dt));

        if (CurrentContext.getUserInfo().getOwnerDeptCode()!=null &&
                (CurrentContext.getUserInfo().getOwnerDeptCode().equals(JIADINGERBU)
                || CurrentContext.getUserInfo().getOwnerDeptCode().equals(KUNSHANSANBU))){
            if (count>=3){
                throw new RemoveStackBusinessException("每日最多可以申请查看平台客户数量为3，您已超限!");
            }
        }

        if (count >= 10) {
            throw new RemoveStackBusinessException("每日最多可以申请查看平台客户数量为10，您已超限!");
        }
    }

    @Override
    public String checkApplyCountStatistics() {

        String dt = DateUtils.formatDate(new Date(), "yyyy-MM-dd");
        String reminder = null;
        String preSql = "select count(*) from t_hkp_crm_apply " +
                "where emp_code=\'%s\' " +
                "and create_time > \'%s\'";

        int count = customerApplyDbDao.countByNativeSql(String.format(preSql, CurrentContext.getUserCode(), dt));
        if (count == 10) {
            reminder = "您每天可以查看10个平台客户号码,今天您已经申请看了" + count + "个,已经达到上限";
        } else {
            reminder = "您每天可以查看10个平台客户号码,今天您已经申请看了" + count + "个";
        }

        if (CurrentContext.getUserInfo().getOwnerDeptCode() != null
                && (CurrentContext.getUserInfo().getOwnerDeptCode().equals(JIADINGERBU)
                || CurrentContext.getUserInfo().getOwnerDeptCode().equals(KUNSHANSANBU))) {
            if (count == 3) {
                reminder = "您每天可以查看3个平台客户号码,今天您已经申请看了" + count + "个,已经达到上限";
            } else {
                reminder = "您每天可以查看3个平台客户,今天您已经申请查看了" + count + "个";
            }
        }

        return reminder;
    }


    @Override
    public int toDayByCount(Map<String, Object> map) {
        return customerApplyDbDao.toDayByCount(map);
    }

}

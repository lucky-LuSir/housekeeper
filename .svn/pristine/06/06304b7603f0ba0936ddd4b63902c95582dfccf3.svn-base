package com.kfwy.hkp.crm.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.common.exception.RemoveStackBusinessException;
import com.kfwy.hkp.crm.customer.business.ICustomerManager;
import com.kfwy.hkp.crm.customer.business.ICustomerPushManager;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerPushDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerPushEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerPushType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author baiye
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/10/25 17:28
 */
@Component
public class CustomerPushManagerImpl extends AbstractManager<CustomerPushEntity> implements ICustomerPushManager {

    @Autowired
    private ICustomerPushDbDao customerPushDbDao;

    @Autowired
    private ICustomerDbDao customerDbDao;

    @Autowired
    private ICustomerManager customerManager;


    @Override
    protected IMyBatisBaseDao<CustomerPushEntity, Long> getMyBatisDao() {
        return customerPushDbDao;
    }

    @Override
    protected void beforeCreate(CustomerPushEntity customerPushEntity) {
        super.beforeCreate(customerPushEntity);
        Date dt = new Date();
        customerPushEntity.setPushCode(CurrentContext.getUserCode());
        customerPushEntity.setPushName(CurrentContext.getUserInfo().getUserName());
        customerPushEntity.setCpyCode(CurrentContext.getCpyCode());
        customerPushEntity.setCpyName(CurrentContext.getUserInfo().getCpyName());
        customerPushEntity.setCreateCode(CurrentContext.getUserCode());
        customerPushEntity.setCreateDeptCode(CurrentContext.getUserInfo().getOwnerDeptCode());
        customerPushEntity.setCreateTime(dt);
        customerPushEntity.setLastUpdateCode(CurrentContext.getUserCode());
        customerPushEntity.setLastUpdateTime(dt);
    }


    @Override
    public int batchCreate(List<CustomerPushEntity> pushEntities) {
        //设置需要推送的客户信息
        Set<String> st = setPushCus(pushEntities);
        //不是我的客户不能推送
        //canNotPushBecauseNotMyCustomer(st);
        //TODO 消息推送
        return this.getMyBatisDao().batchInsert(pushEntities);
    }

    private Set<String> setPushCus(List<CustomerPushEntity> pushEntities) {
        Set<String> st = new HashSet<String>();
        for (CustomerPushEntity push : pushEntities) {
            if (StringUtils.isEmpty(push.getPushType())) {
                throw new RemoveStackBusinessException ("pushType不能为空");
            }
            if (push.getPushType().equals(CustomerPushType.Personal)) {
                if (StringUtils.isEmpty(push.getReceiveCode())
                        || StringUtils.isEmpty(push.getReceiveName())) {
                    throw new RemoveStackBusinessException("推送给个人时个人编码和姓名不能为空");
                }
            } else if (push.getPushType().equals(CustomerPushType.Department)) {
                if (StringUtils.isEmpty(push.getReceiveDeptCode())
                        || StringUtils.isEmpty(push.getReceiveDeptName())) {
                    throw new RemoveStackBusinessException("推送给部门时部门编码和名称不能为空");
                }
            } else {
                throw new RemoveStackBusinessException(String.format("传入的pushType{%s}不合法", push.getPushType()));
            }
            beforeCreate(push);
            st.add(push.getCusCode());
            //别人已经有相同号码的客户，不能推送
            canNotPushBecauseReceiverHadSameCustomer(push);
            //别人已经有相同号码的客户接收过推送，不能重复推送
            canNotPushBecauseHadPushed(push);
        }
        return st;
    }


    /**
     * 别人已经有相同号码的客户，不能推送
     */
    private void canNotPushBecauseReceiverHadSameCustomer(CustomerPushEntity customerPushEntity) {

        String preSql = "select count(*) from t_hkp_crm_customer " +
                " where cus_phone= ( select cus_phone from t_hkp_crm_customer where cus_code=\'%s\')" +
                " and emp_code=\'%s\'";

        String sql = String.format(preSql, customerPushEntity.getCusCode(), customerPushEntity.getReceiveCode());

        int count = customerDbDao.countByNativeSql(sql);

        if (count > 0) {
            throw new RemoveStackBusinessException("别人已经有相同号码的客户，不能推送");
        }

    }

    /**
     * 不是自己的客户，不能推送
     *
     * @param cusCodes
     */
    private void canNotPushBecauseNotMyCustomer(Set<String> cusCodes) {
        String sql = "select count(*) from t_hkp_crm_customer " +
                "where cus_code = \'%s\' and emp_code=\'%s\'";
        for (String cusCode : cusCodes) {
            String tmp = String.format(sql, cusCode, CurrentContext.getUserCode());
            int count = customerDbDao.countByNativeSql(tmp);
            if (count < 1) {
                //拉私的客户也允许推送
                throw new RemoveStackBusinessException("不是自己的客户不允许推送");
            }
        }
    }

    /**
     * 别人已经有相同号码的客户接收过推送，不能重复推送
     */
    private void canNotPushBecauseHadPushed(CustomerPushEntity customerPushEntity) {

        String preSql = "select count(*) from t_hkp_crm_customer_push " +
                " where receive_code=\'%s\' and cus_code in " +
                " ( select cus_code from t_hkp_crm_customer where cus_phone=" +
                "  (select cus_phone from t_hkp_crm_customer where cus_code=\'%s\'))";

        String sql = String.format(preSql, customerPushEntity.getReceiveCode(), customerPushEntity.getCusCode());

        int count = customerDbDao.countByNativeSql(sql);

        if (count > 0) {
            throw new RemoveStackBusinessException("别人已经有相同号码的客户接收过推送，不能重复推送");
        }
    }

}

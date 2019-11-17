package com.kfwy.hkp.job.crm.customer;


import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 三天未跟进的拉私客户转化为平台客户
 * Created by jupe on 2018/9/11.
 */
@Component
public class CustomerTransformJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerTransformJob.class);

    @Autowired
    private ICustomerDbDao customerDbDao;

    public void start() {
        LOGGER.info("--------------[客户任务]-拉私客户转平台客户任务开始--------------");

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cusType", CustomerType.PRIVATE);
        String  followTime = DateFormatUtil.checkOption("pre",DateFormatUtil.dateToString(new Date()), 3);
        param.put("followTime", DateFormatUtil.dayEndFormat(followTime));

        List<CustomerEntity> result = new ArrayList<>();
        List<CustomerEntity> customerEntityList = customerDbDao.queryCustomerListByUnfollow(param);
        if (ListUtils.isNotEmpty(customerEntityList)) {
            for (CustomerEntity customerEntity : customerEntityList) {
                CustomerEntity res = new CustomerEntity();
                res.setId(customerEntity.getId());
                res.setOpenFlag(true);
                res.setCusCode(customerEntity.getCusCode());
                res.setCusType(CustomerType.PLATFORM);
                res.setEmpCode("E201901220001bdc7");
                result.add(res);
            }
        }

        customerDbDao.batchUpdateCusType(result);

        LOGGER.info("--------------[客户任务]-拉私客户转平台客户任务结束--------------");
    }

}

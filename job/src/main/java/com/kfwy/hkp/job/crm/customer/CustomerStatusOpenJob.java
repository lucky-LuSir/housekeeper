package com.kfwy.hkp.job.crm.customer;


import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.ListUtils;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerStatus;
import com.kfwy.hkp.crm.customer.enums.CustomerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 三天未跟进的隐藏客户，转为公开
 * Created by liwensihan on 2019/6/03.
 */
@Component
public class CustomerStatusOpenJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerStatusOpenJob.class);

    @Autowired
    private ICustomerDbDao customerDbDao;

    public void start() {
        LOGGER.info("--------------[客户任务]-三天未跟进隐藏客户转公开客户任务开始--------------");

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("openFlag", false);
        String  followTime = DateFormatUtil.checkOption("pre",DateFormatUtil.dateToString(new Date()), 3);
        param.put("followTime", DateFormatUtil.dayEndFormat(followTime));

        List<CustomerEntity> result = new ArrayList<>();
        List<CustomerEntity> customerEntityList = customerDbDao.queryCustomerListByUnfollow(param);
        if (ListUtils.isNotEmpty(customerEntityList)) {
            for (CustomerEntity customerEntity : customerEntityList) {
                CustomerEntity res = new CustomerEntity();
                res.setId(customerEntity.getId());
                res.setCusCode(customerEntity.getCusCode());
                res.setOpenFlag(true);
                result.add(res);
            }
        }

        customerDbDao.batchUpdateCusOpenFlag(result);

        LOGGER.info("--------------[客户任务]-三天未跟进隐藏客户转公开客户任务结束--------------");
    }

}

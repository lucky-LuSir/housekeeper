package com.kfwy.hkp.job.trade.wage;

import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.trade.order.business.IOrderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Auther: lzp
 * @Date: 2019/7/1
 * @Description: TODO
 */
@Component
public class HrWageReportJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(HrWageReportJob.class);

    @Autowired
    private IOrderManager orderManager;


    public void start() {
        LOGGER.info("--------------[人事版工资报表税金返佣扣款]-任务开始--------------");
        Map<String,Object> param = new HashMap<>();
        String flag = "1";
        Date now = new Date();
        //获取本月一号凌晨
        //获取上个月一号凌晨
        Date startTime = new Date();
        Date endTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.set(Calendar.DAY_OF_MONTH,1);
        //获取所在月第一天
        endTime =DateFormatUtil.dayBegin(cal.getTime());
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(now);
        cal2.add(Calendar.MONTH,-1);
        cal2.set(Calendar.DAY_OF_MONTH,1);
        //获取所在月上个月第一天
        startTime = DateFormatUtil.dayBegin(cal2.getTime());
        param.put("startTime",startTime);
        param.put("endTime",endTime);
        orderManager.dealPayBack(startTime,endTime,flag);

        LOGGER.info("--------------[人事版工资报表税金返佣扣款]-任务结束--------------");
    }

}

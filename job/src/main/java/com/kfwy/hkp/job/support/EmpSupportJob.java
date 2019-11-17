package com.kfwy.hkp.job.support;


import com.kfwy.hkp.bi.count.business.ISupportManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Auther: lzp
 * @Date: 2019/7/29
 * @Description: TODO
 */
@Component
public class EmpSupportJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmpSupportJob.class);

    @Autowired
    private ISupportManager supportManager;

    public void start() {
        LOGGER.info("--------------[未开单人员]-数据整理任务开始--------------");

        supportManager.selectSupport();

        LOGGER.info("--------------[未开单人员]-数据整理架任务结束--------------");
    }



}

package com.kfwy.hkp.job.Report;


import com.kfwy.hkp.bi.count.business.ILastDayReportManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;


/**
 * @Auther: lzp
 * @Date: 2019/8/21
 * @Description: TODO
 */
@Component
public class LastDayReportJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(LastDayReportJob.class);

    @Autowired
    private ILastDayReportManager lastDayReportManager;

    public void start() throws ParseException {
        LOGGER.info("--------------[作日报告]-数据整理任务开始--------------");

        lastDayReportManager.getLastDayReport();

        LOGGER.info("--------------[作日报告]-数据整理架任务结束--------------");
    }



}

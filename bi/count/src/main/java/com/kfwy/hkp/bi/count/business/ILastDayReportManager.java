package com.kfwy.hkp.bi.count.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.bi.count.entity.LastDayReportEntity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface ILastDayReportManager extends IManager<LastDayReportEntity> {

    public void getLastDayReport() throws ParseException;

    public List<LastDayReportEntity> selectLastDayReport(Date date);

}

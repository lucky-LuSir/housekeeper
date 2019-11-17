package com.kfwy.hkp.bi.count.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.entity.LastDayReportEntity;

import java.util.List;
import java.util.Map;


/**
 * Create By lzp on 2019/08/20
 */
public interface ILastDayReportDbDao extends IMyBatisBaseDao<LastDayReportEntity,Long> {

    List<LastDayReportEntity> selectLastDayReport(Map<String,Object> param);

    List<LastDayReportEntity> getLastDayReport(Map<String,Object> param);

}

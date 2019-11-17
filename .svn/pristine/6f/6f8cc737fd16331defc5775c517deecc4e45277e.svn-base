package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.ILastDayReportDbDao;
import com.kfwy.hkp.bi.count.entity.LastDayReportEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Create By lzp on 2019/08/20
 */
@Repository
public class LastDayReportDbDaoImpl extends AbstractMyBatisDao<LastDayReportEntity,Long> implements ILastDayReportDbDao {


    @Override
    public List<LastDayReportEntity> selectLastDayReport(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectLastDayReport", param);
    }

    @Override
    public List<LastDayReportEntity> getLastDayReport(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "getLastDayReport", param);
    }
}

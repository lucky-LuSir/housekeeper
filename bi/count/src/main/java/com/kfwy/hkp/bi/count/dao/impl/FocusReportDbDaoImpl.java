package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IFocusReportDbDao;
import com.kfwy.hkp.bi.count.entity.FocusReportEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/8/27
 * @Description: TODO
 */
@Repository
public class FocusReportDbDaoImpl extends AbstractMyBatisDao<FocusReportEntity, Long> implements IFocusReportDbDao {

    @Override
    public int selectByMapFocusCount(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectByMapFocusCount", map);
    }

    @Override
    public int selectCusCountByMap(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectCusCountByMap", map);
    }
}

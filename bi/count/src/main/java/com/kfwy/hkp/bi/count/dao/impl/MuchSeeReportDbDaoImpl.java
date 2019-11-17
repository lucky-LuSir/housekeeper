package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IMuchSeeReportDbDao;
import com.kfwy.hkp.bi.count.entity.MuchSeeEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Create By hjh on 2018/11/28
 */
@Repository
public class MuchSeeReportDbDaoImpl extends AbstractMyBatisDao<MuchSeeEntity,Long> implements IMuchSeeReportDbDao {

    @Override
    public int manyTimesFollowup(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "manyTimesFollowup", param);
    }
}

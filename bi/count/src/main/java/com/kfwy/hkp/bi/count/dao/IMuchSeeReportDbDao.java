package com.kfwy.hkp.bi.count.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.entity.MuchSeeEntity;

import java.util.Map;

/**
 * Create By hjh on 2018/11/28
 */
public interface IMuchSeeReportDbDao extends IMyBatisBaseDao<MuchSeeEntity,Long> {

    public int manyTimesFollowup(Map<String, Object> param);
}

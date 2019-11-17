package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IFocusPayBackWageReportDbDao;
import com.kfwy.hkp.bi.count.dao.IGraphDbDao;
import com.kfwy.hkp.bi.count.entity.FocusPayBackWageReportEntity;
import com.kfwy.hkp.bi.count.entity.GraphEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Create By lzp on 2019/08/9
 */
@Repository
public class FocusPayBackWageReportDbDaoImpl extends AbstractMyBatisDao<FocusPayBackWageReportEntity,Long> implements IFocusPayBackWageReportDbDao {

    @Override
    public List<FocusPayBackWageReportEntity> selectFocusPayBackWageReportByMap(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectFocusPayBackWageReportByMap", param);
    }
}

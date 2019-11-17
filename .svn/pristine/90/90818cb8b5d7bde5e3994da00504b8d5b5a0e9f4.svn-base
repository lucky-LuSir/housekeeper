package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IReportDbDao;
import com.kfwy.hkp.bi.count.entity.ReportEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2018/10/26
 */
@Repository
public class ReportDbDaoImpl extends AbstractMyBatisDao<ReportEntity, Long> implements IReportDbDao {

    @Override
    public int selectHouseDepute(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectHouseDepute", param);

    }

    @Override
    public int selectValidCus(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectValidCus", param);
    }

    @Override
    public int selectCusUpCount(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectCusUpCount", param);
    }

    @Override
    public int selectHosUpCount(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectHosUpCount", param);
    }

    @Override
    public int selectByDeptFocusCount(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectByDeptFocusCount", param);
    }

    @Override
    public int selectByUserFocusCount(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectByUserFocusCount", param);
    }

    @Override
    public List<BigDecimal> selectOrderTotalMoney(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectOrderTotalMoney", param);
    }

    @Override
    public BigDecimal selectOrderActualMoney(Map<String, Object> param) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectOrderActualMoney", param);
    }
}

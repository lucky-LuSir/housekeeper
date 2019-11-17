package com.kfwy.hkp.trade.wage.dao.Impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.trade.wage.dao.ICommissionRatioDbDao;
import com.kfwy.hkp.trade.wage.dao.IWageDbDao;
import com.kfwy.hkp.trade.wage.entity.CommissionRatioEntity;
import com.kfwy.hkp.trade.wage.entity.WageEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * Created by lzp on 2019/3/26.
 */
@Repository
public class WageDbDaoImpl extends AbstractMyBatisDao<WageEntity,Long>   implements IWageDbDao {


    @Override
    public List<WageEntity> wageDetailsReport(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "wageDetailsReport",map);
    }
}

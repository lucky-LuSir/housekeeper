package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IContractReportDbDao;
import com.kfwy.hkp.bi.count.entity.ContractPeriodEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Create By hjh on 2019/06/21
 */
@Repository
public class ContractReportDbDaoImpl extends AbstractMyBatisDao<ContractPeriodEntity,Long> implements IContractReportDbDao {



    @Override
    public List<ContractPeriodEntity> selectContractPeriodCus(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectContractPeriodCus", param);

    }

    @Override
    public List<ContractPeriodEntity> selectContractPeriodHos(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectContractPeriodHos", param);
    }
}

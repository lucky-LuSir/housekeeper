package com.kfwy.hkp.trade.wage.dao.Impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.trade.wage.dao.ICommissionRatioDbDao;
import com.kfwy.hkp.trade.wage.entity.CommissionRatioEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;


/**
 * Created by lzp on 2019/3/26.
 */
@Repository
public class CommissionRatioDbDaoImpl extends AbstractMyBatisDao<CommissionRatioEntity,Long>   implements ICommissionRatioDbDao {

    @Override
    public void updateIsDeleteByOrderCode(Map<String, Object> map) {
        this.getSqlSession().delete(this.mapperNamespace + "updateIsDeleteByOrderCode", map);
    }

    @Override
    public int updateGroup(Map<String,Object> param) {
        return this.getSqlSession().update(this.mapperNamespace+"updateGroup",param);
    }

    @Override
    public int updateLeader(Map<String, Object> param) {
        return this.getSqlSession().update(this.mapperNamespace+"updateLeader",param);
    }


}

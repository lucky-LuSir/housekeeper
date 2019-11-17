package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.ILocationAnalysisDbDao;
import com.kfwy.hkp.bi.count.entity.LocationAnalysisEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Create By xpp on 2018/10/26
 */
@Repository
public class LocationAnalysisDbDaoImpl extends AbstractMyBatisDao<LocationAnalysisEntity,Long> implements ILocationAnalysisDbDao {



    @Override
    public int selectRentArea(Map<String, Object> param) {
        int i = 0;
        if(this.getSqlSession().selectOne(this.mapperNamespace + "selectRentArea", param) !=null){
            i=this.getSqlSession().selectOne(this.mapperNamespace + "selectRentArea", param);
        }
        return i;
    }

    @Override
    public int countSuitArea(Map<String, Object> param) {
        int i = 0;
        if(this.getSqlSession().selectOne(this.mapperNamespace + "countSuitArea", param) !=null){
            i= this.getSqlSession().selectOne(this.mapperNamespace + "countSuitArea", param);
        }
        return i;
    }




}

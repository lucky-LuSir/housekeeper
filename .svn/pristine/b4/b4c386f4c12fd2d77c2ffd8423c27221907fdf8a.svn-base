package com.kfwy.hkp.hrm.org.dept.dao.impl;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.hrm.org.dept.dao.ICusServiceDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.CusServiceEntity;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;


/**
 * Created by xpp on 2018/5/22.
 */
@Repository
public class CusServiceDbDaoImpl extends AbstractMyBatisDao<CusServiceEntity,Long> implements ICusServiceDbDao {

    @Override
    public PageResult<CusServiceEntity> selectCusServiceList(Map<String, Object> queryParam, int start, int pageSize) {
        return this.selectByStatement("selectCusServiceList",queryParam,start,pageSize);
    }

    @Override
    public List<CusServiceEntity> selectCusServiceList(Map<String, Object> queryParam) {
        return  this.selectByStatement("selectCusServiceList",queryParam);
    }
}



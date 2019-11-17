package com.kfwy.hkp.bi.count.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.bi.count.dao.IHosAndCusTotalDbDao;
import com.kfwy.hkp.bi.count.entity.HosAndCusTotalEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/7/1
 * @Description: TODO
 */
@Repository
public class HosAndCusTotalDbDaoImpl extends AbstractMyBatisDao<HosAndCusTotalEntity, Long> implements IHosAndCusTotalDbDao {

    @Override
    public List<HosAndCusTotalEntity> selectCusByMap(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectCusByMap", map);
    }

    @Override
    public List<HosAndCusTotalEntity> selectHosByMap(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectHosByMap", map);
    }
}

package com.kfwy.hkp.sys.auth.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.hkp.sys.auth.dao.IReportAccessDbDao;
import com.kfwy.hkp.sys.auth.entity.ReportAccessEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/7/11
 * @Description: TODO
 */
@Repository
public class ReportAccessDbDaoImpl extends AbstractMyBatisDao<ReportAccessEntity, Long> implements IReportAccessDbDao {

    @Override
    public List<ReportAccessEntity> selectDeptAccess(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectDeptAccess", param);
    }

    @Override
    public List<String> selectAccess(String userCode) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectAccess", userCode);
    }

    @Override
    public int deleteByUserCode(String userCode) {
        return this.getSqlSession().delete(this.mapperNamespace + "deleteByUserCode", userCode);
    }

    @Override
    public int deleteAllByUserCodes(List<String> userCodes) {
        return this.getSqlSession().delete(this.mapperNamespace + "deleteAllByUserCodes", userCodes);
    }
}

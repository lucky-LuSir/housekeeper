package com.kfwy.hkp.sys.auth.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.sys.auth.business.IReportAccessManager;
import com.kfwy.hkp.sys.auth.dao.IReportAccessDbDao;
import com.kfwy.hkp.sys.auth.entity.ReportAccessEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/7/11
 * @Description: TODO
 */
@Service
public class ReportAccessManagerImpl extends AbstractManager<ReportAccessEntity> implements IReportAccessManager {

    @Autowired
    private IReportAccessDbDao reportAccessDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return reportAccessDbDao;
    }

    @Override
    @Transactional
    public int creates(ReportAccessEntity ra, List<String> deptCodes) {
        // 先删除
        reportAccessDbDao.deleteByUserCode(ra.getUserCode());

        List<ReportAccessEntity> ras = Collections.synchronizedList(new ArrayList<>());
        deptCodes.forEach(e -> {
            ras.add(new ReportAccessEntity(ra.getUserCode(), e));
        });
        return reportAccessDbDao.batchInsert(ras);
    }

    @Override
    @Transactional
    public int createAll(List<String> userCodes, List<String> deptCodes) {
        // 先删除
        reportAccessDbDao.deleteAllByUserCodes(userCodes);

        List<ReportAccessEntity> ras = Collections.synchronizedList(new ArrayList<>());
        userCodes.forEach(u -> {
            deptCodes.forEach(d -> {
                ras.add(new ReportAccessEntity(u, d));
            });
        });
        return reportAccessDbDao.batchInsert(ras);
    }

    @Override
    public List<ReportAccessEntity> selectDeptAccess(Map<String, Object> param) {
        return reportAccessDbDao.selectDeptAccess(param);
    }
}

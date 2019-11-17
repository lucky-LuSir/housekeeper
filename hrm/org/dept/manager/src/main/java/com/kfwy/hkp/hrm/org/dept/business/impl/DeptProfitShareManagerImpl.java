package com.kfwy.hkp.hrm.org.dept.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.RestBusinessException;
import com.kfwy.hkp.hrm.org.dept.IDeptProfitShareManager;
import com.kfwy.hkp.hrm.org.dept.dao.IDeptProfitShareDbDao;
import com.kfwy.hkp.hrm.org.dept.entity.DeptProfitShareEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/8/6
 * @Description: TODO
 */

@Service
public class DeptProfitShareManagerImpl extends AbstractManager<DeptProfitShareEntity> implements IDeptProfitShareManager {

    @Autowired
    private IDeptProfitShareDbDao profitShareDbDao;

    @Override
    protected IMyBatisBaseDao<DeptProfitShareEntity, Long> getMyBatisDao() {
        return this.profitShareDbDao;
    }


    @Override
    public int create(DeptProfitShareEntity ps) {

        if (ps.getSum() == null) {
            throw new RestBusinessException("请填写完整!");
        }

        if (ps.getSum() != 100) {
            throw new RestBusinessException("分成比相加必须等于100");
        }

        // 查询重复
        Map<String, Object> map = new HashMap<>();
        map.put("deptCode", ps.getDeptCode());
        map.put("profitShareType", ps.getProfitShareType());
        DeptProfitShareEntity entity = profitShareDbDao.selectUniqueByMap(map);

        if (entity != null) {
            throw new RestBusinessException("不能重复新增" + ps.getProfitShareTypeName() + "配置！");
        }

        this.defaultConfig(ps);

        // 填充基本信息
        OperateFillUtils.fill(ps);

        return profitShareDbDao.insert(ps);
    }


    @Override
    public int update(DeptProfitShareEntity ps) {

        if (ps.getSum() == null) {
            throw new RestBusinessException("请填写完整!");
        }

        if (ps.getSum() != 100) {
            throw new RestBusinessException("分成比相加必须等于100");
        }

        this.defaultConfig(ps);

        ps.setProfitShareType(null);
        ps.setLastUpdateCode(CurrentContext.getUserCode());
        ps.setLastUpdateTime(new Date());
        return profitShareDbDao.update(ps);
    }


    private void defaultConfig(DeptProfitShareEntity ps) {
        ps.setCusPush(10);
    }

}

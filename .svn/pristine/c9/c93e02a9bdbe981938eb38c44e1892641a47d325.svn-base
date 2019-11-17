package com.kfwy.hkp.trade.order.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.trade.order.business.IOrderFollowupManager;
import com.kfwy.hkp.trade.order.dao.IOrderFollowupDbDao;
import com.kfwy.hkp.trade.order.entity.OrderEntity;
import com.kfwy.hkp.trade.order.entity.OrderFollowupEntity;
import com.kfwy.hkp.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.hkp.hrm.org.employee.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kfwy_it_003 on 2017/8/2.
 */
@Service
public class OrderFollowupManagerImpl extends AbstractManager<OrderFollowupEntity> implements IOrderFollowupManager {
    @Autowired
    private IOrderFollowupDbDao orderFollowupDbDao;

    @Autowired
    private IEmployeeManager employeeManager;

    @Override
    protected IMyBatisBaseDao<OrderFollowupEntity, Long> getMyBatisDao() {
        return this.orderFollowupDbDao;
    }

    @Override
    public int insert(OrderFollowupEntity orderFollowup) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("empCode",CurrentContext.getUserCode());
        EmployeeEntity emp = employeeManager.findOne(param);
        orderFollowup.setEmpCode(emp.getEmpCode());
        orderFollowup.setEmpName(emp.getEmpName());
        orderFollowup.setCreateCode(emp.getEmpCode());
        orderFollowup.setCreateName(emp.getEmpName());
        orderFollowup.setCreateDeptCode(emp.getDeptCode());
        orderFollowup.setCreateDeptName(emp.getDeptName());
        orderFollowup.setCreateTime(new Date());
        orderFollowup.setIsDeleted(false);
        return orderFollowupDbDao.insert(orderFollowup);
    }

    @Override
    public int update(OrderFollowupEntity orderFollowup) {
        return 0;
    }

    @Override
    public PageResult<OrderFollowupEntity> select(Map<String, Object> map, int start, int pageSize) {
        return orderFollowupDbDao.selectByMap(map, start, pageSize, "create_time", Boolean.FALSE);
    }
}

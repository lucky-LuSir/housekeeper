package com.kfwy.hkp.job.crm.customer;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.kfwy.hkp.crm.customer.dao.ICustomerDbDao;
import com.kfwy.hkp.crm.customer.dao.ICustomerUpdownDbDao;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.CustomerUpdownEntity;
import com.kfwy.hkp.crm.customer.enums.CustomerStatus;
import com.kfwy.hkp.hos.house.dao.IHouseDbDao;
import com.kfwy.hkp.hos.house.entity.HouseUpdownEntity;
import com.kfwy.hkp.hos.house.enums.HouseStatus;
import com.kfwy.hkp.sys.remind.business.IRemindDownManager;
import com.kfwy.hkp.sys.remind.entity.RemindDownEntity;
import com.kfwy.hkp.sys.remind.enums.DownType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: HJH
 * @Date: 2019/4/23
 * @Description: TODO
 */
@Component
public class RemindDownJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(RemindDownJob.class);

    @Autowired
    private IRemindDownManager remindDownManager;

    @Autowired
    private ICustomerDbDao customerDbDao;

    @Autowired
    private ICustomerUpdownDbDao customerUpdownDbDao;

    @Autowired
    private IHouseDbDao houseDbDao;

    public void start() {
        LOGGER.info("--------------[下架任务]-自动下架任务开始--------------");

        Map<String, Object> map = new HashMap<>();

        DateTime offsetDay = DateUtil.offsetDay(new Date(), -3);
        DateTime endOfDay = DateUtil.endOfDay(offsetDay);

        map.put("createTimeEnd", endOfDay);
        map.put("hasOperate", false);
        map.put("isDeleted", false);
        List<RemindDownEntity> reminds = remindDownManager.findByMap(map);
        map.clear();
        if (CollUtil.isNotEmpty(reminds)) {
            for (RemindDownEntity r : reminds) {
                if (r.getDownType().equals(DownType.CUS)) {
                    cusDown(r);
                } else if (r.getDownType().equals(DownType.HOS)) {
                    hosDown(r);
                }
                r.setHasOperate(true);
                r.setLastUpdateTime(new Date());
                r.setRemark("三天未处理自动下架");
                remindDownManager.update(r);
            }
        }
        LOGGER.info("--------------[下架任务]-自动下架任务结束--------------");
    }

    private void hosDown(RemindDownEntity r) {
        Map<String, Object> map = new HashMap<>();
        map.put("leaseExpiration", r.getLeaseExpiration());
        map.put("houseCode", r.getOwnerCode());
        map.put("downReason", r.getDownCause());
        map.put("isDeleted", Boolean.FALSE);
        map.put("houseStatus", HouseStatus.HasLease);
        map.put("remark", "三天未处理自动下架");
        houseDbDao.upAndDownShelf(map);
        // 下架记录
        this.setHosDownLog(r);
    }

    private void setHosDownLog(RemindDownEntity r) {
        HouseUpdownEntity houseUpdownEntity = new HouseUpdownEntity();
        //HouseEntity houseEntity = houseDbDao.selectUniqueByProp("houseCode", param.get("houseCode"));
        houseUpdownEntity.setUserCode(r.getUserCode());
        //下架
        houseUpdownEntity.setPreStatus(HouseStatus.hotRenting);
        houseUpdownEntity.setAftStatus(HouseStatus.HasLease);

        houseUpdownEntity.setHouseCode(r.getOwnerCode());
        houseUpdownEntity.setRemark(r.getDownCause()+"(三天未处理自动下架)");//下架原因

        houseUpdownEntity.setCreateCode(r.getCreateCode());
        houseUpdownEntity.setCreateDeptCode(r.getCreateDeptCode());
        houseUpdownEntity.setCreateTime(new Date());
        //houseUpdownEntity.setLastUpdateCode("");
        houseUpdownEntity.setLastUpdateTime(new Date());
        houseUpdownEntity.setIsDeleted(Boolean.FALSE);
        houseDbDao.setUpAndDownLog(houseUpdownEntity);
    }

    private void cusDown(RemindDownEntity r) {
        CustomerEntity cus = new CustomerEntity();
        cus.setCusCode(r.getOwnerCode());
        cus.setDownShelfReason(r.getDownCause());
        cus.setContractEndTime(r.getLeaseExpiration());
        cus.setCusStatus(CustomerStatus.HASDEAL);
        cus.setLastDownshelfTime(new Date());

        //cus.setCusDownUserName("自动下架");
        cus.setRemark("三天未处理自动下架");
        customerDbDao.upDownShelf(cus);
        setCusUpDown(r);
    }

    private void setCusUpDown(RemindDownEntity r) {
        CustomerUpdownEntity customerUpdown = new CustomerUpdownEntity();
        //CustomerEntity customer = customerDbDao.selectUniqueByProp("cusCode", customerEntity.getCusCode());
        customerUpdown.setCusDownType("下架");
        customerUpdown.setCusDownReason(r.getDownCause()+"(三天未处理自动下架)");

        customerUpdown.setCusCode(r.getOwnerCode());
        // 下架人
        customerUpdown.setEmpCode(r.getCreateCode());
        customerUpdown.setEmpName(r.getCreateName());
        //customerUpdown.setCusEmpCode(r.getUserCode());
        //customerUpdown.setCusEmpName(userManager.findUserByUserCode(r.getUserCode()).getUserName());
        customerUpdown.setCreateTime(new Date());
        // 创建人
        customerUpdown.setCreateCode(r.getCreateCode());
        customerUpdown.setCreateName(r.getCreateName());
        customerUpdown.setCreateDeptCode(r.getCreateDeptCode());
        customerUpdown.setCreateDeptName(r.getCreateDeptName());
        customerUpdownDbDao.insert(customerUpdown);
    }
}

package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IFocusPayBackWageReportManager;
import com.kfwy.hkp.bi.count.dao.IFocusPayBackWageReportDbDao;
import com.kfwy.hkp.bi.count.entity.FocusPayBackWageReportEntity;
import com.kfwy.hkp.trade.order.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
public class FocusPayBackWageReportManagerImpl extends AbstractManager<FocusPayBackWageReportEntity> implements IFocusPayBackWageReportManager {

    @Autowired
    private IFocusPayBackWageReportDbDao focusPayBackWageReportDbDao;
    @Autowired
    OrderStatus orderStatus;

    @Override
    protected IMyBatisBaseDao<FocusPayBackWageReportEntity, Long> getMyBatisDao() {
        return this.focusPayBackWageReportDbDao;
    }

    @Override
    public List<FocusPayBackWageReportEntity> selectFocusPayBackWageReportByMap(Map<String, Object> param) {
        List<FocusPayBackWageReportEntity> fpbws = focusPayBackWageReportDbDao.selectFocusPayBackWageReportByMap(param);

        if (null!=fpbws){
            for (FocusPayBackWageReportEntity fw : fpbws) {
                fw.setOrderStatus(orderStatus.produce().get(Integer.parseInt(fw.getOrderStatus())).getName());
                fw.setReceivableCost(fw.getReceivableCus().add(fw.getReceivableHos()));
                fw.setBackMoney(fw.getCusRebate().add(fw.getOwnerRebate()));
                fw.setActCommission(fw.getReceivableCost().subtract(fw.getBackMoney()));
                fw.setComeCommission(fw.getPayPayment().subtract(fw.getCommissionDeductions()).subtract(fw.getTaxDeductions()));
                fw.setFocusProfit(fw.getComeCommission().multiply(BigDecimal.valueOf(10).divide(BigDecimal.valueOf(100))));
                fw.setProfit(fw.getFocusProfit().multiply(fw.getPercentage().divide(BigDecimal.valueOf(100))));

            }
        }else {

        }

        return fpbws;
    }
}

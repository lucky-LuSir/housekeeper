package com.kfwy.hkp.bi.count.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.business.IFocusWageReportManager;
import com.kfwy.hkp.bi.count.dao.IFocusWageReportDbDao;
import com.kfwy.hkp.bi.count.entity.FocusWageReportEntity;

import com.kfwy.hkp.trade.order.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Service
public class FocusWageReportManagerImpl extends AbstractManager<FocusWageReportEntity> implements IFocusWageReportManager {

    @Autowired
    private IFocusWageReportDbDao focusWageReportDbDao;
    @Autowired
    OrderStatus orderStatus;

    @Override
    protected IMyBatisBaseDao<FocusWageReportEntity, Long> getMyBatisDao() {
        return this.focusWageReportDbDao;
    }

    @Override
    public List<FocusWageReportEntity> selectFocusWageReportByMap(Map<String, Object> param) {
        List<FocusWageReportEntity> fwrs =  focusWageReportDbDao.selectFocusWageReportByMap(param);
        if (null!=fwrs){
            for (FocusWageReportEntity fw : fwrs) {
                fw.setOrderStatus(orderStatus.produce().get(Integer.parseInt(fw.getOrderStatus())).getName());
                fw.setReceivableCost(fw.getReceivableCus().add(fw.getReceivableHos()));
                fw.setBackMoney(fw.getCusRebate().add(fw.getOwnerRebate()));
                fw.setActCommission(fw.getReceivableCost().subtract(fw.getBackMoney()));
                fw.setComeCommission(fw.getActCommission().subtract(fw.getCusPartTimeMoney()).subtract(fw.getOwnerPartTimeMoney()));
                fw.setFocusProfit(fw.getComeCommission().multiply(BigDecimal.valueOf(10).divide(BigDecimal.valueOf(100))));
                fw.setProfit(fw.getFocusProfit().multiply(fw.getPercentage().divide(BigDecimal.valueOf(100))));

            }
        }else {

        }

        return  fwrs;
    }
}

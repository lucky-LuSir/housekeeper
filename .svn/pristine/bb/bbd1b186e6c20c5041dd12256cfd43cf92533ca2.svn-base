package com.kfwy.hkp.crm.customer.business.strategy.impl;


import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.base.TimeSplit;
import com.kfwy.hkp.common.utils.DateFormatUtil;
import com.kfwy.hkp.common.utils.FilterUtil;
import com.kfwy.hkp.crm.customer.business.strategy.AbstractFocusCusStrategy;
import com.kfwy.hkp.crm.customer.business.strategy.FocusCusStrategy;
import com.kfwy.hkp.crm.customer.business.strategy.FocusCusStrategyConst;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import com.kfwy.hkp.sys.user.entity.UserFocusCusBo;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 平均获客算法
 */
@Component(FocusCusStrategyConst.AVERAGE_MATCH)
public class FocusCusAverageMatchStrategy extends AbstractFocusCusStrategy implements FocusCusStrategy {

    @Override
    public List<UserEntity> handleFocusCus (CustomerEntity customer, List<UserEntity> targetUsers, FocusCusConfig focusCusConfig, Integer remainFocusCusCount) {
        Map map = new HashMap ();
        TimeSplit averageCusRange = new TimeSplit (null, focusCusConfig.getAverageCusRange (), "DD");
        Date createTimeStart = DateFormatUtil.addDate (DateFormatUtil.getCurrentDateTime (), averageCusRange.getAfterNumber (), averageCusRange.getAfterFormat ());
        map.put ("ownerDeptCodes",customer.getNoticeDepts ());
        map.put ("createTimeStart",createTimeStart);
        map.put ("createTimeEnd",new Date ());
        FilterUtil.filterMap (map);
        List<UserFocusCusBo> userFocusCusBoList = focusCusDbDao.leastGetGuestQuery (map);
        if (userFocusCusBoList != null && userFocusCusBoList.size () > 0) {
            userFocusCusBoList = userFocusCusBoList.subList (0,remainFocusCusCount);
            for (UserFocusCusBo focusCusBo : userFocusCusBoList) {
                UserEntity userEntity = userManager.selectUniqueByProp (focusCusBo.getUserCode ());
                targetUsers.add (userEntity);
            }
        }

        return targetUsers;
    }
}

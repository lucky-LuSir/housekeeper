package com.kfwy.hkp.crm.customer.business.strategy.impl;


import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.crm.customer.business.strategy.AbstractFocusCusStrategy;
import com.kfwy.hkp.crm.customer.business.strategy.FocusCusStrategy;
import com.kfwy.hkp.crm.customer.business.strategy.FocusCusStrategyConst;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 指定推送算法
 */
@Component (FocusCusStrategyConst.SPECIAL_PUSH)
public class FocusCusSpecialPushStrategy extends AbstractFocusCusStrategy implements FocusCusStrategy {


    @Override
    public List<UserEntity> handle (CustomerEntity customer, List<UserEntity> targetUsers, FocusCusConfig focusCusConfig) {
        if (customer.getNoticeUsers () != null && customer.getNoticeUsers ().size () > 0) {
            for (String noticeUser : customer.getNoticeUsers ()) {
                UserEntity userEntity = userManager.selectUniqueByProp (noticeUser);
                targetUsers.add (userEntity);
            }
        }
        return targetUsers;
    }

}

package com.kfwy.hkp.crm.customer.business.strategy.impl;



import com.kfwy.hkp.base.FocusCusConfig;
import com.kfwy.hkp.common.utils.FilterUtil;
import com.kfwy.hkp.crm.customer.business.strategy.AbstractFocusCusStrategy;
import com.kfwy.hkp.crm.customer.business.strategy.FocusCusStrategy;
import com.kfwy.hkp.crm.customer.business.strategy.FocusCusStrategyConst;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.sys.user.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一获客算法
 */
@Component (FocusCusStrategyConst.ALL_PUSH)
public class FocusCusAllPushStrategy extends AbstractFocusCusStrategy implements FocusCusStrategy {


    @Override
    public List<UserEntity> handle (CustomerEntity customer, List<UserEntity> targetUsers, FocusCusConfig focusCusConfig) {

        if (customer.getNoticeUsers () != null && customer.getNoticeUsers ().size () > 0) {
            for (String noticeUser : customer.getNoticeUsers ()) {
                UserEntity userEntity = userManager.selectUniqueByProp (noticeUser);
                targetUsers.add (userEntity);
            }
        }

        Map map  = new HashMap<> ();
        map.put ("isDeleted", false);
        excludeByPostCodes(map);
        //排除已经被指定推送的人
        if (customer.getNoticeUsers () != null && customer.getNoticeUsers ().size () > 0) {
            map.put ("excludeUserCodesByNoticeUsers", customer.getNoticeUsers ());
        }
        //TODO 排除黑名单中的人
        List<String> excludeUserCodesByBlackList = excludeUserCodesByBlackList();
        if (excludeUserCodesByBlackList!=null && excludeUserCodesByBlackList.size ()>0){
            map.put ("excludeUserCodesByBlackList",excludeUserCodesByBlackList);
        }
        map.put ("noticeDepts", customer.getNoticeDepts ());
        FilterUtil.filterMap (map);
        List<UserEntity> users = userManager.findUsersByNoticeDepts (map);
        targetUsers.addAll (users);

        return targetUsers;
    }

}

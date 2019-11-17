package com.kfwy.hkp.sys.user.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.hkp.sys.user.entity.UserApplyTeamEntity;


/**
 * Create By xpp on 2018/7/31
 */
public interface IUserApplyTeamManager extends IManager<UserApplyTeamEntity> {

    int leaveDeptApply(UserApplyTeamEntity userApplyTeamEntity);

    void delete(UserApplyTeamEntity entity);
}

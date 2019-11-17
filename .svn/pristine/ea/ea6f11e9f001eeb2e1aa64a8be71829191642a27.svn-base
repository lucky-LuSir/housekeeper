package com.kfwy.hkp.crm.customer.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.user.entity.BaseUserEntity;
import com.kfwy.hkp.crm.customer.bo.FocusCusUserScoreMatchBo;
import com.kfwy.hkp.crm.customer.dto.CustomerFocusCusDto;
import com.kfwy.hkp.crm.customer.entity.FocusCusEntity;
import com.kfwy.hkp.sys.user.entity.UserFocusCusBo;

import java.util.List;
import java.util.Map;


public interface IFocusCusDbDao extends IMyBatisBaseDao<FocusCusEntity, Long> {

    int updateFocusCusToPlatform (List<CustomerFocusCusDto> list, Map map);

    List<CustomerFocusCusDto> findFocusCusToPlatForm (Map map);

    List<FocusCusUserScoreMatchBo> focusCusUserScoreMatch (Map map);

    Integer checkFocusCusNotice (Map map);

    List<UserFocusCusBo> leastGetGuestQuery (Map map);

   int deletedByCusCode(String cusCode);

}

package com.kfwy.hkp.crm.customer.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.crm.customer.bo.FocusCusUserScoreMatchBo;
import com.kfwy.hkp.crm.customer.dto.FocusCusDto;
import com.kfwy.hkp.crm.customer.entity.CustomerEntity;
import com.kfwy.hkp.crm.customer.entity.FocusCusEntity;

import java.util.List;
import java.util.Map;


public interface IFocusCusManager extends IManager<FocusCusEntity>{

    public List<FocusCusUserScoreMatchBo> focusCusUserScoreMatch(Map map);

    public Integer checkFocusCusNotice(Map map);

    public PageResult<FocusCusDto> focusCusQuery(CustomerEntity entity, Integer start, Integer pageSize);

    public PageResult <FocusCusDto> myDeptFocusCusQuery(Map map,Integer start,Integer pageSize);
}

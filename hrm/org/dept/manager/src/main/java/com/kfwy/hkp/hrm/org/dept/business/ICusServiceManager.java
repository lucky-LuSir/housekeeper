package com.kfwy.hkp.hrm.org.dept.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.hrm.org.dept.entity.CusServiceEntity;

import java.util.List;
import java.util.Map;

/**
* @Description 描述:
* @Auth xpp
* @Date 2019/1/7 9:37
* @Version 1.0
* @Param
* @return
**/
public interface ICusServiceManager extends IManager<CusServiceEntity> {

    public void delete(CusServiceEntity cusServiceEntity);

    PageResult<CusServiceEntity> selectCusServiceList(Map<String, Object> param, int start, int pageSize);
}

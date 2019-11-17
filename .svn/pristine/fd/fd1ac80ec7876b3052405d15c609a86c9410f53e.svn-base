package com.kfwy.hkp.hrm.org.dept.dao;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hrm.org.dept.entity.CusServiceEntity;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/12/11 11:46
 */
public interface ICusServiceDbDao extends IMyBatisBaseDao<CusServiceEntity,Long> {
    PageResult<CusServiceEntity> selectCusServiceList(Map<String, Object> queryParam, int start, int pageSize);
    List<CusServiceEntity> selectCusServiceList(Map<String, Object> queryParam);
}

package com.kfwy.hkp.hos.house.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.hos.house.entity.SharePoolDeptEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ISharePoolDeptManager extends IManager<SharePoolDeptEntity> {

   PageResult<SharePoolDeptEntity> selectSharePoolDept(Map map, Integer start, Integer pageSize);

   int addSharePoolDeptInfo(SharePoolDeptEntity sharePoolDeptEntity);

   int updateSharePoolDeptInfo(SharePoolDeptEntity sharePoolDeptEntity);

   int delSharePoolDeptInfo(Long id);

   int addSharePoolDeptInfo(List<SharePoolDeptEntity> sharePoolDeptEntityList);

    List<SharePoolDeptEntity> findByShareCodeMap(HashMap<String, Object> spdeptmap);
}

package com.kfwy.hkp.hos.house.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.hkp.hos.house.entity.SharePoolEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ISharePoolManager extends IManager<SharePoolEntity> {

   PageResult<SharePoolEntity> selectSharePool(Map map,Integer start,Integer pageSize);


   int addSharePoolInfo(SharePoolEntity sharePoolEntity);

   int updateSharePoolInfo(SharePoolEntity sharePoolEntity);

   int delSharePoolInfo(Long id);

   SharePoolEntity findSharePoolByShareCode(String shareCode);

   int addSharePoolInfoList(List<SharePoolEntity> sharePoolEntityList);

    List<SharePoolEntity> selectByMyDept(Map map);

    List<SharePoolEntity> findByShareCodeMap(HashMap<String, Object> map);
}

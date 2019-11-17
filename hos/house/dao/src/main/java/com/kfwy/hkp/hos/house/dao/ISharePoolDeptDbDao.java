package com.kfwy.hkp.hos.house.dao;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hos.house.entity.SharePoolDeptEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ISharePoolDeptDbDao extends IMyBatisBaseDao<SharePoolDeptEntity,Long> {

    public int add(SharePoolDeptEntity sharePoolDeptEntity);

    public PageResult<SharePoolDeptEntity> query(Map map);

    List<SharePoolDeptEntity> selectByShareCodeMap(HashMap<String, Object> spdeptmap);
}

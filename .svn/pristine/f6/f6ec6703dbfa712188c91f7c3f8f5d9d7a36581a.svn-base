package com.kfwy.hkp.hos.house.dao;

import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.hos.house.entity.SharePoolEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface ISharePoolDbDao extends IMyBatisBaseDao<SharePoolEntity,Long> {

    public int add(SharePoolEntity sharePoolEntity);

    public PageResult<SharePoolEntity> query(Map map);

    public List<SharePoolEntity> selectByMyDept(Map map);

    List<SharePoolEntity> selectByShareCodeMap(HashMap<String, Object> map);
}

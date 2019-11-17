package com.kfwy.hkp.bi.count.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.entity.GraphEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By lzp on 2019/08/9
 */
public interface IGraphDbDao extends IMyBatisBaseDao<GraphEntity,Long> {

    List<GraphEntity> selectEntrustByMap(Map<String,Object> param);

    List<GraphEntity> selectBespeakByMap(Map<String,Object> param);

    List<GraphEntity> selectEntrustAndBespeakCountByMap(Map<String,Object> param);

}

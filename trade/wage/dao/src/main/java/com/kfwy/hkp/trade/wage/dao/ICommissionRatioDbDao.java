package com.kfwy.hkp.trade.wage.dao;


import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.trade.wage.entity.CommissionRatioEntity;

import java.util.Map;

/**
 * Created by lzp on 2019/3/26.
 */
public interface ICommissionRatioDbDao extends IMyBatisBaseDao<CommissionRatioEntity,Long> {
    void updateIsDeleteByOrderCode(Map<String, Object> map);
    int updateGroup(Map<String,Object> param);
    int updateLeader(Map<String,Object> param);
}

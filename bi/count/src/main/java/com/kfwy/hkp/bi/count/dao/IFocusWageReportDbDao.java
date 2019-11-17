package com.kfwy.hkp.bi.count.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.entity.FocusWageReportEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By lzp on 2019/08/9
 */
public interface IFocusWageReportDbDao extends IMyBatisBaseDao<FocusWageReportEntity,Long> {

    List<FocusWageReportEntity> selectFocusWageReportByMap(Map<String,Object> param);

}

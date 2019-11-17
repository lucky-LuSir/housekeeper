package com.kfwy.hkp.bi.count.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.hkp.bi.count.entity.FocusPayBackWageReportEntity;

import java.util.List;
import java.util.Map;

/**
 * Create By lzp on 2019/08/9
 */
public interface IFocusPayBackWageReportDbDao extends IMyBatisBaseDao<FocusPayBackWageReportEntity,Long> {

    List<FocusPayBackWageReportEntity> selectFocusPayBackWageReportByMap(Map<String,Object> param);


}
